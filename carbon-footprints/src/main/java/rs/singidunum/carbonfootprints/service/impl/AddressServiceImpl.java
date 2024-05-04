package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressFullRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressFullResponseDto;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCoefDto;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCompactDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.BuildingType;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.AddressRepository;
import rs.singidunum.carbonfootprints.repository.CarbonCoefRepository;
import rs.singidunum.carbonfootprints.repository.CarbonRepository;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.AddressService;
import rs.singidunum.carbonfootprints.service.mediator.AddressMediator;
import rs.singidunum.carbonfootprints.util.CarbonUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMediator addressMediator;
    private final UserRepository userRepository;
    private final CarbonRepository carbonRepository;
    private final CarbonUtil carbonUtil;
    private final CarbonCoefRepository carbonCoefRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Address> getCompactAllByUserId(Long id) {
        return addressRepository.getAllByUsersAddress(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressFullResponseDto> getFullAllByUserId(Long id) {
        List<Address> addresses = addressRepository.getAllByUsersAddress(id);

        return createFullAddressResponse(addresses);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAll() {
        return addressRepository.getAllByNonArchived();
    }

    @Override
    @Transactional
    public AddressFullResponseDto add(Long userId, AddressFullRequestDto addressRequestDto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalStateException("User doesnt exists"));

        Address address = mapToPm(addressRequestDto);
        address.setStatus(EntityStatus.ACTIVE);

        Address updatedAddress = addressRepository.save(address);
        user.getAddresses().add(updatedAddress);

        User updatedUser = userRepository.save(user);

        List<Carbon> carbons = mapToCarbon(updatedAddress, updatedUser, addressRequestDto.getCarbon());

        carbonRepository.saveAll(carbons);

        updatedAddress.setCarbon(carbons);
        return buildAddressFullResponseDto(updatedAddress);
    }

    @Override
    @Transactional
    public AddressFullResponseDto edit(Long id, Long userId, AddressFullRequestDto addressRequestDto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalStateException("User doesnt exists"));

        Address address = addressRepository.findById(id)
                                           .orElseThrow(() ->
                                                   new IllegalStateException(String.format("Cannot find an address by id: %s", id)));
        address = addressMediator.mediate(addressRequestDto, address);


        address.getCarbon().forEach(value -> value.setStatus(EntityStatus.ARCHIVED));
        Address updatedAddress = addressRepository.save(address);

        List<Carbon> carbons = mapToCarbon(updatedAddress, user, addressRequestDto.getCarbon());

        carbonRepository.saveAll(carbons);

        updatedAddress.setCarbon(carbons);
        return buildAddressFullResponseDto(updatedAddress);
    }

    @Override
    @Transactional
    public Address delete(Long id) {
        Address address = addressRepository.findById(id)
                                           .orElseThrow(() ->
                                                   new IllegalStateException(String.format("Cannot find an address by id: %s", id)));
        address.setStatus(EntityStatus.ARCHIVED);

        List<Carbon> carbons = address.getCarbon();

        for (Carbon carbon : carbons) {
            carbon.setStatus(EntityStatus.ARCHIVED);
        }

        carbonRepository.saveAll(carbons);
        addressRepository.save(address);

        return address;
    }

    @Override
    public byte[] getXlsxReport(Long userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalStateException("User doesnt exists"));
        List<AddressFullResponseDto> addresses = getFullAllByUserId(userId);

        ByteArrayOutputStream outputStream;

        try {
            XSSFWorkbook ekp = new XSSFWorkbook();
            outputStream = new ByteArrayOutputStream();

            Sheet sheet = ekp.createSheet("Report");

            int rowNum = 0;
            Row row;
            row = sheet.createRow(rowNum);

            Cell cell = row.createCell(0);
            cell.setCellValue("Id");
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue("Username");
            sheet.autoSizeColumn(1);

            cell = row.createCell(2);
            cell.setCellValue("Address");
            sheet.autoSizeColumn(2);

            cell = row.createCell(3);
            cell.setCellValue("Building type");
            sheet.autoSizeColumn(3);

            cell = row.createCell(4);
            cell.setCellValue("Postal code");
            sheet.autoSizeColumn(4);

            cell = row.createCell(5);
            cell.setCellValue("Resource");
            sheet.autoSizeColumn(5);

            cell = row.createCell(6);
            cell.setCellValue("Amount");
            sheet.autoSizeColumn(6);

            cell = row.createCell(7);
            cell.setCellValue("Produced");
            sheet.autoSizeColumn(7);

            cell = row.createCell(8);
            cell.setCellValue("Date");
            sheet.autoSizeColumn(8);


            for (AddressFullResponseDto address : addresses) {
                for(CarbonCompactDto carbon : address.getCarbon()) {
                    rowNum++;

                    row = sheet.createRow(rowNum);

                    cell = row.createCell(0);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(user.getId().toString());

                    cell = row.createCell(1);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(user.getEmail());

                    cell = row.createCell(2);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(prepareAddress(address));

                    cell = row.createCell(3);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(address.getBuildingType());

                    cell = row.createCell(4);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(address.getPostalCode());

                    cell = row.createCell(5);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(carbon.getCoef().getName());

                    cell = row.createCell(6);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(carbon.getAmount());

                    cell = row.createCell(7);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(carbon.getProduced());

                    cell = row.createCell(8);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(carbon.getLastUpd().toString());
                }
            }

            ekp.write(outputStream);
            outputStream.close();
        } catch (IOException ex) {
            throw new IllegalStateException("Exception during generating report");
        }
        return outputStream.toByteArray();
    }

    private List<Carbon> updateCarbon(Address address, List<CarbonCompactDto> carbons) {
        List<Carbon> carbonList = new ArrayList<>();

        for (CarbonCompactDto carbon : carbons) {
            Carbon carbonPm = Carbon.builder()
                                    .address(address)
                                    .status(EntityStatus.ACTIVE)
                                    .amount(carbon.getAmount())
                                    .lastUpdated(LocalDateTime.now())
                                    .produced(getProducedCarbon(carbon.getCoef(), carbon.getAmount()))
                                    .coef(CarbonCoef.builder()
                                                    .id(carbon.getCoef().getId())
                                                    .name(carbon.getCoef().getName())
                                                    .build())
                                    .build();

            carbonList.add(carbonPm);
        }

        return carbonList;
    }

    private List<Carbon> mapToCarbon(Address address, User user, List<CarbonCompactDto> carbons) {
        List<Carbon> carbonList = new ArrayList<>();

        for (CarbonCompactDto carbon : carbons) {
            Carbon carbonPm = Carbon.builder()
                    .address(address)
                    .user(user)
                    .status(EntityStatus.ACTIVE)
                    .amount(carbon.getAmount())
                    .lastUpdated(LocalDateTime.now())
                    .produced(getProducedCarbon(carbon.getCoef(), carbon.getAmount()))
                    .coef(CarbonCoef.builder()
                            .id(carbon.getCoef().getId())
                            .name(carbon.getCoef().getName())
                                    .build())
                    .build();

            carbonList.add(carbonPm);
        }

        return carbonList;
    }

    private Double getProducedCarbon(CarbonCoefDto carbonCoefDto, Double amount) {
        CarbonCoef carbonCoef = carbonCoefRepository.findById(carbonCoefDto.getId())
                                        .orElseThrow(() -> new IllegalStateException("Cannot find carbon coef by id"));

        return carbonUtil.countProducedCarbon(carbonCoef, amount);
    }

    private String prepareAddress(AddressFullResponseDto address) {
        return String.format("%s %s %s %s %s", address.getCountry(), address.getCity(), address.getStreet(),
                address.getFlat(), address.getHouse());
    }


    private Address mapToPm(AddressFullRequestDto addressRequestDto) {
        return Address.builder()
                .country(addressRequestDto.getCountry())
                .city(addressRequestDto.getCountry())
                .street(addressRequestDto.getStreet())
                .house(addressRequestDto.getHouse())
                .flat(addressRequestDto.getFlat())
                .postalCode(addressRequestDto.getPostalCode())
                .buildingType(BuildingType.valueOf(addressRequestDto.getBuildingType()))
                      .build();
    }

    private List<AddressFullResponseDto> createFullAddressResponse(List<Address> addresses) {
        List<AddressFullResponseDto> addressList = new ArrayList<>();
        for (Address address : addresses) {
            addressList.add(buildAddressFullResponseDto(address));
        }

        return addressList;
    }

    private AddressFullResponseDto buildAddressFullResponseDto(Address address) {
        AddressFullResponseDto addressFullResponseDto = new AddressFullResponseDto();

        addressFullResponseDto.setId(address.getId());
        addressFullResponseDto.setCity(address.getCity());
        addressFullResponseDto.setCountry(address.getCountry());
        addressFullResponseDto.setStreet(address.getStreet());
        addressFullResponseDto.setHouse(address.getHouse());
        addressFullResponseDto.setFlat(address.getFlat());
        addressFullResponseDto.setPostalCode(address.getPostalCode());
        addressFullResponseDto.setBuildingType(address.getBuildingType().toString());
        addressFullResponseDto.setCarbon(buildCarbonCompact(address.getCarbon()));

        return addressFullResponseDto;
    }

    private List<CarbonCompactDto> buildCarbonCompact(List<Carbon> carbons) {
        List<CarbonCompactDto> carbonCompactDtos = new ArrayList<>();
        for (Carbon carbon : carbons) {
            if (EntityStatus.ARCHIVED.equals(carbon.getStatus())) {
                continue;
            }
            CarbonCompactDto carbonCompactDto =
                    CarbonCompactDto.builder().id(carbon.getId())
                                    .amount(carbon.getAmount())
                                    .produced(carbon.getProduced())
                                    .coef(mapToCarbonCoefResponseDto(carbon.getCoef()))
                                    .lastUpd(carbon.getLastUpdated())
                                    .build();

            carbonCompactDtos.add(carbonCompactDto);
        }

        return carbonCompactDtos;
    }

    private CarbonCoefDto mapToCarbonCoefResponseDto(CarbonCoef carbonCoef) {
        return CarbonCoefDto.builder()
                            .id(carbonCoef.getId())
                            .name(carbonCoef.getName()).build();
    }
}
