package cz.dsw.actuator_examples.example02.component;

import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestA;
import cz.dsw.actuator_examples.example02.entity.service.RequestB;
import cz.dsw.actuator_examples.example02.entity.service.ResponseA;
import cz.dsw.actuator_examples.example02.entity.service.ResponseB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("health-indicator")
public class ServicesHealthIndicator implements HealthIndicator {

    @Autowired(required = false) List<ServiceProvider<RequestA, ResponseA>> providersA;
    @Autowired(required = false) List<ServiceProvider<RequestB, ResponseB>> providersB;

    @Autowired private TokenFactory factory;

    @Override
    public Health health() {
        Map<String, Boolean> details = new HashMap<>();

        if (providersA != null)
            for (ServiceProvider<RequestA, ResponseA> provider : providersA) {
                RequestA request = factory.tokenInstance(RequestA.class);
                request.setValue(0);

                ResponseA response = provider.perform(request);
                details.put(provider.getInstanceName(), response.getCode() == ResponseCodeType.OK);
            }
        if (providersB != null) {
            for (ServiceProvider<RequestB, ResponseB> provider : providersB) {
                RequestB request = factory.tokenInstance(RequestB.class);
                request.setText("");

                ResponseB response = provider.perform(request);
                details.put(provider.getInstanceName(), response.getCode() == ResponseCodeType.OK);
            }
        }
        return ((details.values().stream().allMatch(b -> b)) ? Health.up() : Health.down()).withDetails(details).build();
    }
}
