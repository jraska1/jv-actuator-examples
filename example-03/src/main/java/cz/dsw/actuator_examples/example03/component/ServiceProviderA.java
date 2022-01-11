package cz.dsw.actuator_examples.example03.component;

import cz.dsw.actuator_examples.example03.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example03.entity.service.RequestA;
import cz.dsw.actuator_examples.example03.entity.service.ResponseA;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    @Autowired private TokenFactory factory;

    private SummaryStatistics stat = new SummaryStatistics();

    @Override
    public ResponseA perform(RequestA request) {

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        long value = request.getValue() + new Random().nextInt((int) Math.max(request.getValue() / 2, 10));
        stat.addValue(value);
        response.setResult(value);
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }

    @Bean
    public MeterBinder valueMean() {
        return (registry) -> Gauge.builder("services.A.mean", stat::getMean).register(registry);
    }

    @Bean
    public MeterBinder valueDeviation() {
        return (registry) -> Gauge.builder("services.A.deviation", stat::getStandardDeviation).register(registry);
    }
}
