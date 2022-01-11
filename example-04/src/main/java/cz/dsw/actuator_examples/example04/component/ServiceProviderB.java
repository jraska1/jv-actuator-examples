package cz.dsw.actuator_examples.example04.component;

import cz.dsw.actuator_examples.example04.entity.ResponseCodeType;
import cz.dsw.actuator_examples.example04.entity.service.RequestB;
import cz.dsw.actuator_examples.example04.entity.service.ResponseB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderB implements ServiceProvider<RequestB, ResponseB> {

    @Autowired private TokenFactory factory;

    @Override
    public ResponseB perform(RequestB request) {
        ResponseB response = factory.tokenInstance(request.getTid(), ResponseB.class);
        response.setCode(ResponseCodeType.OK);
        long length = request.getText().length();
        response.setText("text length: " + length);
        return response;
    }

    @Override
    public String getInstanceName() {
        return "Provider B";
    }
}
