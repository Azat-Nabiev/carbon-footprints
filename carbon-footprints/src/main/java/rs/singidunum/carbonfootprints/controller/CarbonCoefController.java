package rs.singidunum.carbonfootprints.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.singidunum.carbonfootprints.controller.mapper.CarbonCoefMapper;
import rs.singidunum.carbonfootprints.dto.response.CarbonCoefResponseDto;
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
    public ResponseEntity<List<CarbonCoefResponseDto>> retrieveAll() {
        List<CarbonCoef> carbonCoefList = carbonCoefService.getAll();
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CarbonCoefResponseDto>> retrieveAllByUserId(@PathVariable(name = "id") Long userId) {
        List<CarbonCoef> carbonCoefList = carbonCoefService.getAllByUserId(userId);
        return null;
    }

    @PostMapping
    public ResponseEntity<CarbonCoefResponseDto> add(@RequestHeader(name = "USER_ID") Long userId) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarbonCoefResponseDto> edit(@PathVariable(name = "id") Long coefId) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarbonCoefResponseDto> delete(@PathVariable(name = "id") Long coefId) {
        return null;
    }


}
