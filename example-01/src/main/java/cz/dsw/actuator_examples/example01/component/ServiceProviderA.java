package cz.dsw.actuator_examples.example01.component;

import cz.dsw.actuator_examples.example01.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example01.entity.service.RequestA;
import cz.dsw.actuator_examples.example01.entity.service.ResponseA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceProviderA implements ServiceProvider<RequestA, ResponseA> {

    @Autowired private TokenFactory factory;

    @Override
    public ResponseA perform(RequestA request) {

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.OK);
        response.setResult(request.getValue() + new Random().nextInt((int) Math.max(request.getValue() / 2, 10)));
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A";
    }
}
