package rs.singidunum.carbonfootprints.service.mediator;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.model.Carbon;

@Service
public class CarbonMediator {

    public Carbon mediate(CarbonRequestDto carbonRequestDto, Carbon carbon) {
        if (carbonRequestDto.getAmount() != null) {
            carbon.setAmount(carbonRequestDto.getAmount());
        }
        return carbon;
    }
}
