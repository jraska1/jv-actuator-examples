package cz.dsw.actuator_examples.example02.component;

import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestB;
import cz.dsw.actuator_examples.example02.entity.service.ResponseB;
import cz.dsw.actuator_examples.example02.event.ProviderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ServiceProviderB implements ServiceProvider<RequestB, ResponseB> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderB.class);

    @Autowired private TokenFactory factory;
    @Autowired private ApplicationEventPublisher publisher;
    @Autowired private Function<Object, String> toJson;

    @Override
    public ResponseB perform(RequestB request) {

        logger.debug("REQUEST:\n{}", toJson.apply(request));

        ResponseB response = factory.tokenInstance(request.getTid(), ResponseB.class);
        response.setCode(ResponseCodeType.OK);
        response.setText("text length: " + request.getText().length());

        logger.debug("RESPONSE:\n{}", toJson.apply(response));

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ANSWERED, request, response));

        logger.info("Service Provider name='{}' replied to request from node='{}'.", getInstanceName(), request.getName());
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider B";
    }
}
