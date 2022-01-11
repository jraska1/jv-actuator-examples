package cz.dsw.actuator_examples.example02.component;

import cz.dsw.actuator_examples.example02.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example02.entity.service.RequestA;
import cz.dsw.actuator_examples.example02.entity.service.ResponseA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class ServiceProviderAFlawed implements ServiceProvider<RequestA, ResponseA> {

    @Autowired private TokenFactory factory;

    @Override
    public ResponseA perform(RequestA request) {

        ResponseA response = factory.tokenInstance(request.getTid(), ResponseA.class);
        response.setCode(ResponseCodeType.ERROR);
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider A - Flawed";
    }
}
