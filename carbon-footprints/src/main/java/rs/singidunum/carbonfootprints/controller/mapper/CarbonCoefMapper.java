package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.response.CarbonCoefResponseDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

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
                .coef(carbonCoef.getCoef())
                .user(userMapper.mapToUserResponseDto(carbonCoef.getUser())).build();
    }
}
