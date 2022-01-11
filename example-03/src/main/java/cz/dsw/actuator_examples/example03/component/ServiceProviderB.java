package cz.dsw.actuator_examples.example03.component;

import cz.dsw.actuator_examples.example03.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example03.entity.service.RequestB;
import cz.dsw.actuator_examples.example03.entity.service.ResponseB;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderB implements ServiceProvider<RequestB, ResponseB> {

    @Autowired private TokenFactory factory;

    private SummaryStatistics stat = new SummaryStatistics();

    @Override
    public ResponseB perform(RequestB request) {
        ResponseB response = factory.tokenInstance(request.getTid(), ResponseB.class);
        response.setCode(ResponseCodeType.OK);
        long length = request.getText().length();
        stat.addValue(length);
        response.setText("text length: " + length);
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider B";
    }

    @Bean
    public MeterBinder valueMinimum() {
        return (registry) -> Gauge.builder("services.B.minimum", stat::getMin).register(registry);
    }

    @Bean
    public MeterBinder valueMaximum() {
        return (registry) -> Gauge.builder("services.B.maximum", stat::getMax).register(registry);
    }
}
