package cz.dsw.actuator_examples.example02.component;

import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestA;
import cz.dsw.actuator_examples.example02.entity.service.ResponseA;
import cz.dsw.actuator_examples.example02.event.ProviderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Function;

@Service
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderA.class);

    @Autowired private TokenFactory factory;
    @Autowired private ApplicationEventPublisher publisher;
    @Autowired private Function<Object, String> toJson;

    @Override
    public ResponseA perform(RequestA request) {

        logger.debug("REQUEST:\n{}", toJson.apply(request));

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        response.setResult(request.getValue() + new Random().nextInt((int) Math.max(request.getValue() / 2, 10)));

        logger.debug("RESPONSE:\n{}", toJson.apply(response));

        publisher.publishEvent(new ProviderEvent(this, ProviderEvent.Phase.ANSWERED, request, response));

        logger.info("Service Provider name='{}' replied to request from node='{}'.", getInstanceName(), request.getName());
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }
}
