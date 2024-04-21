package rs.singidunum.carbonfootprints.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
import rs.singidunum.carbonfootprints.controller.mapper.AddressMapper;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressResponseDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    @Operation(summary = "Retrieving all ACTIVE addresses")
    public ResponseEntity<List<AddressResponseDto>> retrieveAll() {
        List<Address> addressList = addressService.getAll();
        return ResponseEntity.ok(addressMapper.mapToResponseDtoList(addressList));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieving all ACTIVE addresses by ID")
    public ResponseEntity<List<AddressResponseDto>> retrieveById(@PathVariable(name = "id") Long id) {
        List<Address> addressList = addressService.getAllByUserId(id);
        return ResponseEntity.ok(addressMapper.mapToResponseDtoList(addressList));
    }

    @PostMapping
    @Operation(summary = "Adding an address")
    public ResponseEntity<AddressResponseDto> add(@RequestHeader("USER_ID") Long id,
            @RequestBody AddressRequestDto addressRequestDto) {
        Address address = addressService.add(addressRequestDto);
        return ResponseEntity.ok(addressMapper.mapToAddressResponseDto(address));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editing an address by ID")
    public ResponseEntity<AddressResponseDto> edit(@PathVariable(name = "id") Long id,
                                                   @RequestBody AddressRequestDto addressRequestDto) {
        Address address = addressService.edit(id, addressRequestDto);
        return ResponseEntity.ok(addressMapper.mapToAddressResponseDto(address));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an address by ID")
    public ResponseEntity<AddressResponseDto> delete(@PathVariable(name = "id") Long id) {
        Address address = addressService.delete(id);
        return ResponseEntity.ok(addressMapper.mapToAddressResponseDto(address));
    }
}
