package cz.dsw.actuator_examples.example02.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestA;
import cz.dsw.actuator_examples.example02.entity.service.ResponseA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderA.class);

    @Autowired private TokenFactory factory;

    @Autowired private ObjectMapper objectMapper;

    @Override
    public ResponseA perform(RequestA request) {

        try {
            logger.debug("REQUEST:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            logger.error("Object to JSON mapping failed!", e);
        }

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        response.setResult(request.getValue() + new Random().nextInt((int) Math.max(request.getValue() / 2, 10)));

        try {
            logger.debug("RESPONSE:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            logger.error("Object to JSON mapping failed!", e);
        }

        logger.info("Service Provider name='{}' replied to request from node='{}'.", getInstanceName(), request.getName());
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }
}
