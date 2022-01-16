package cz.dsw.actuator_examples.example04.component;

import cz.dsw.actuator_examples.example04.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example04.entity.service.RequestA;
import cz.dsw.actuator_examples.example04.entity.service.ResponseA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;

@Service
@Endpoint(id = "application")
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    @Autowired private TokenFactory factory;

    private long margin = 10;
    private long divider = 2;

    @Override
    public ResponseA perform(RequestA request) {

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        long value = new Random().nextInt((int) Math.max(Math.min(request.getValue() / divider, margin), 1));
        response.setResult(value);
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }

    @ReadOperation
    public Map<String, Long> getData() {
        return Map.of("margin",margin, "divider", divider);
    }

    @WriteOperation
    public void postData(String name, Long value) {
        if (name.equalsIgnoreCase("margin"))
            margin = value;
        else if (name.equalsIgnoreCase("divider"))
            divider = value;
    }
}
