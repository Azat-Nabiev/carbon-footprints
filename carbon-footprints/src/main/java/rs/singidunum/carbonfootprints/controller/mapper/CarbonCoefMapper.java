package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.response.CarbonCoefResponseDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

import java.util.List;

@Service
public class CarbonCoefMapper {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CarbonCoefResponseDto mapToCarbonCoefResponseDto(CarbonCoef carbonCoef) {
        return CarbonCoefResponseDto.builder()
                                    .id(carbonCoef.getId())
                                    .name(carbonCoef.getName())
                                    .coef(carbonCoef.getCoef()).build();
    }

    public List<CarbonCoefResponseDto> mapToCarbonCoefResponseDtoList(List<CarbonCoef> carbonCoefList) {
        return carbonCoefList.stream().map(this::mapToCarbonCoefResponseDto).toList();
    }
}
