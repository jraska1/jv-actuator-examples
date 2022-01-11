package cz.dsw.actuator_examples.example02.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestB;
import cz.dsw.actuator_examples.example02.entity.service.ResponseB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderB implements ServiceProvider<RequestB, ResponseB> {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderB.class);

    @Autowired private TokenFactory factory;

    @Autowired private ObjectMapper objectMapper;

    @Override
    public ResponseB perform(RequestB request) {

        try {
            logger.debug("REQUEST:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            logger.error("Object to JSON mapping failed!", e);
        }

        ResponseB response = factory.tokenInstance(request.getTid(), ResponseB.class);
        response.setCode(ResponseCodeType.OK);
        response.setText("text length: " + request.getText().length());

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
        return "Provider B";
    }
}
