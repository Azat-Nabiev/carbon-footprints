package rs.singidunum.carbonfootprints.service.mediator;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

@Service
public class CarbonCoefMediator {

    public CarbonCoef mediate(CarbonCoefRequestDto carbonCoefRequestDto, CarbonCoef carbonCoef) {

        if (StringUtils.isNotEmpty(carbonCoefRequestDto.getName())
                || StringUtils.isNotBlank(carbonCoefRequestDto.getName())) {
            carbonCoef.setName(carbonCoefRequestDto.getName());
        }

        if (carbonCoefRequestDto.getCoef() != null) {
            carbonCoef.setCoef(carbonCoefRequestDto.getCoef());
        }

        return carbonCoef;
    }

}
