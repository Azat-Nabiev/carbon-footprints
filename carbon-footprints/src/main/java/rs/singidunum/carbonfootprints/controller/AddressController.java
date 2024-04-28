package rs.singidunum.carbonfootprints.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressFullRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressFullResponseDto;
import rs.singidunum.carbonfootprints.controller.mapper.AddressMapper;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressСompactResponseDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.service.AddressService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    @Operation(summary = "Retrieving all ACTIVE addresses")
    public ResponseEntity<List<AddressСompactResponseDto>> retrieveAll() {
        List<Address> addressList = addressService.getAll();
        return ResponseEntity.ok(addressMapper.mapToCompactResponseDtoList(addressList));
    }

    @GetMapping("/compact/{id}")
    @Operation(summary = "Retrieving all ACTIVE addresses by ID")
    public ResponseEntity<List<AddressСompactResponseDto>> retrieveByIdCompact(@PathVariable(name = "id") Long id) {
        List<Address> addressList = addressService.getCompactAllByUserId(id);
        return ResponseEntity.ok(addressMapper.mapToCompactResponseDtoList(addressList));
    }

    @GetMapping("/full/{id}")
    @Operation(summary = "Retrieving all ACTIVE addresses by ID")
    public ResponseEntity<List<AddressFullResponseDto>> retrieveById(@PathVariable(name = "id") Long id) {
        List<AddressFullResponseDto> addressList = addressService.getFullAllByUserId(id);
        return ResponseEntity.ok(addressList);
    }

    @PostMapping
    @Operation(summary = "Adding an address")
    public ResponseEntity<AddressFullResponseDto> add(@RequestHeader("USER_ID") Long id,
                                                         @RequestBody AddressFullRequestDto addressRequestDto) {
        AddressFullResponseDto address = addressService.add(id, addressRequestDto);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editing an address by ID")
    public ResponseEntity<AddressFullResponseDto> edit(@PathVariable(name = "id") Long id,
                                                       @RequestHeader("USER_ID") Long userId,
                                                          @RequestBody AddressFullRequestDto addressRequestDto) {
        AddressFullResponseDto address = addressService.edit(id, userId, addressRequestDto);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an address by ID")
    public ResponseEntity<AddressСompactResponseDto> delete(@PathVariable(name = "id") Long id) {
        Address address = addressService.delete(id);
        return ResponseEntity.ok(addressMapper.mapToCompactAddressResponseDto(address));
    }


    @GetMapping("/report/{id}")
    @Operation(summary = "Getting the file by id")
    public ResponseEntity<?> getFileById(@PathVariable(name = "id") Long id) throws UnsupportedEncodingException {

        byte[] file = addressService.getXlsxReport(id);
        String fileName = String.format("%s", "Report.xlsx");

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .header(
                                     HttpHeaders.CONTENT_DISPOSITION,
                                     "attachment; filename=\"" + URLEncoder.encode(
                                             fileName,
                                             StandardCharsets.UTF_8.toString()
                                     ) + "\""
                             )
                             .body(file);
    }
}
