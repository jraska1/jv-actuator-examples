package cz.dsw.actuator_examples.example02.rest;

import cz.dsw.actuator_examples.example02.component.ServiceProvider;
import cz.dsw.actuator_examples.example02.entity.service.RequestA;
import cz.dsw.actuator_examples.example02.entity.service.RequestB;
import cz.dsw.actuator_examples.example02.entity.service.ResponseA;
import cz.dsw.actuator_examples.example02.entity.service.ResponseB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired private ServiceProvider<RequestA, ResponseA> serviceProviderA;
    @Autowired private ServiceProvider<RequestB, ResponseB> serviceProviderB;

    @RequestMapping(value = "/rest/serviceA", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseA restProviderA(@RequestBody RequestA request) {
        return serviceProviderA.perform(request);
    }

    @RequestMapping(value = "/rest/serviceB", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseB restProviderB(@RequestBody RequestB request) {
        return serviceProviderB.perform(request);
    }
}
