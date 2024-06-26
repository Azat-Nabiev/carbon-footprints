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
import rs.singidunum.carbonfootprints.controller.mapper.CarbonCoefMapper;
import rs.singidunum.carbonfootprints.controller.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCoefDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.service.CarbonCoefService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carbon/coef")
@RequiredArgsConstructor
public class CarbonCoefController {

    private final CarbonCoefService carbonCoefService;
    private final CarbonCoefMapper carbonCoefMapper;

    @GetMapping
    @Operation(summary = "Retrieving all ACTIVE carbon coefs")
    public ResponseEntity<List<CarbonCoefDto>> retrieveAll() {
        List<CarbonCoef> carbonCoefList = carbonCoefService.getAll();
        return ResponseEntity.ok(carbonCoefMapper.mapToCarbonCoefResponseDtoList(carbonCoefList));
    }

    @PostMapping
    @Operation(summary = "Adding an carbon coefs")
    public ResponseEntity<CarbonCoefDto> add(@RequestHeader(name = "USER_ID", required = false) Long userId,
                                             @RequestBody CarbonCoefRequestDto carbonCoefRequestDto) {
        CarbonCoef carbonCoef = carbonCoefService.add(userId, carbonCoefRequestDto);
        return ResponseEntity.ok(carbonCoefMapper.mapToCarbonCoefResponseDto(carbonCoef));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an carbon coefs by user id")
    public ResponseEntity<CarbonCoefDto> edit(@PathVariable(name = "id") Long coefId,
                                              @RequestHeader(name = "USER_ID") Long userId,
                                              @RequestBody CarbonCoefRequestDto carbonCoefRequestDto) {
        CarbonCoef carbonCoef = carbonCoefService.edit(coefId, userId, carbonCoefRequestDto);
        return ResponseEntity.ok(carbonCoefMapper.mapToCarbonCoefResponseDto(carbonCoef));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an carbon coefs by user id")
    public ResponseEntity<CarbonCoefDto> delete(@PathVariable(name = "id") Long coefId) {

        CarbonCoef carbonCoef = carbonCoefService.delete(coefId);
        return ResponseEntity.ok(carbonCoefMapper.mapToCarbonCoefResponseDto(carbonCoef));
    }
}
