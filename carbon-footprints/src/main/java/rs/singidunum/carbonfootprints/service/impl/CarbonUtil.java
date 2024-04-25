package rs.singidunum.carbonfootprints.service.impl;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.model.ProducedCarbon;

import java.util.List;

@Service
public class CarbonUtil {

    // CARBON_DIOXIDE_CONVERSATION_FACTOR = 44/12
    private static final Double CARBON_DIOXIDE_CONVERSATION_FACTOR = 3.7;

    public ProducedCarbon getAllProducedCarbon(List<Carbon> carbons) {
        Double producedAmount = (double) 0;

        for (Carbon carbon: carbons) {
            producedAmount +=carbon.getProduced();
        }

        producedAmount = Math.ceil(producedAmount);

        return new ProducedCarbon(producedAmount);
    }

    // E = M * K1 * NCV * K2 * 44/12
    public Double countProducedCarbon(CarbonCoef carbonCoef, Double amount) {
        return Math.ceil(amount * carbonCoef.getCef() * carbonCoef.getCoc()
                * carbonCoef.getNcv() * CARBON_DIOXIDE_CONVERSATION_FACTOR);
    }
}
