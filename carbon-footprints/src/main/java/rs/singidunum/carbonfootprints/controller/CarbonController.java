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
import rs.singidunum.carbonfootprints.controller.mapper.CarbonMapper;
import rs.singidunum.carbonfootprints.controller.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.CarbonResponseDto;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.ProducedCarbon;
import rs.singidunum.carbonfootprints.service.CarbonService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/carbon")
@RequiredArgsConstructor
public class CarbonController {
    private final CarbonService carbonService;
    private final CarbonMapper carbonMapper;

    @GetMapping
    @Operation(summary = "Retrieving all ACTIVE carbon")
    public ResponseEntity<List<CarbonResponseDto>> retrieveAll() {
        List<Carbon> carbon = carbonService.getAll();
        return ResponseEntity.ok(carbonMapper.mapToCarbonResponseDtoList(carbon));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieving all ACTIVE carbon coefs by USER ID")
    public ResponseEntity<List<CarbonResponseDto>> retrieveByUserId(@PathVariable(name = "id") Long userId) {
        List<Carbon> carbonList = carbonService.getAllByUserId(userId);
        return ResponseEntity.ok(carbonMapper.mapToCarbonResponseDtoList(carbonList));
    }

    @PostMapping
    @Operation(summary = "Adding a carbon")
    public ResponseEntity<CarbonResponseDto> add(@RequestHeader(name = "USER_ID") Long userId,
                                                 @RequestBody CarbonRequestDto carbonRequestDto) {
        Carbon carbon = carbonService.add(userId, carbonRequestDto);
        return ResponseEntity.ok(carbonMapper.mapToCarbonResponseDto(carbon));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editing a carbon")
    public ResponseEntity<CarbonResponseDto> edit(@PathVariable(name = "id") Long id, CarbonRequestDto carbonRequestDto) {
        Carbon carbon = carbonService.edit(id, carbonRequestDto);
        return ResponseEntity.ok(carbonMapper.mapToCarbonResponseDto(carbon));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting a carbon")
    public ResponseEntity<CarbonResponseDto> delete(@PathVariable(name = "id") Long id) {
        Carbon carbon = carbonService.delete(id);
        return ResponseEntity.ok(carbonMapper.mapToCarbonResponseDto(carbon));
    }

    @GetMapping("/amount/{id}")
    @Operation(summary = "Getting all produced carbon")
    public ResponseEntity<ProducedCarbon> getProducedCarbon(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(carbonService.getProducedCarbon(id));
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<Set<String>> getUsedResources(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(carbonService.getUsedRecources(id));
    }
}
