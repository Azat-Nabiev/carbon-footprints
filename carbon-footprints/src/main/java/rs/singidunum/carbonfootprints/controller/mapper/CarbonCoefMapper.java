package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCoefDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

import java.util.List;

@Service
public class CarbonCoefMapper {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CarbonCoefDto mapToCarbonCoefResponseDto(CarbonCoef carbonCoef) {
        return CarbonCoefDto.builder()
                            .id(carbonCoef.getId())
                            .name(carbonCoef.getName())
                            .status(carbonCoef.getStatus().toString()).build();
    }

    public List<CarbonCoefDto> mapToCarbonCoefResponseDtoList(List<CarbonCoef> carbonCoefList) {
        return carbonCoefList.stream().map(this::mapToCarbonCoefResponseDto).toList();
    }
}
