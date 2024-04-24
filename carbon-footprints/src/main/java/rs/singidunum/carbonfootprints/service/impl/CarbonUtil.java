package rs.singidunum.carbonfootprints.service.impl;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.ProducedCarbon;

import java.util.List;

@Service
public class CarbonUtil {
    public ProducedCarbon getAllProducedCarbon(List<Carbon> carbons) {
        Double producedAmount = (double) 0;

        for (Carbon carbon: carbons) {
            producedAmount +=carbon.getProduced();
        }

        return new ProducedCarbon(producedAmount);
    }
}
