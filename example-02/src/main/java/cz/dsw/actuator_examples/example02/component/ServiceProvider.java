package cz.dsw.actuator_examples.example02.component;

import cz.dsw.actuator_examples.example02.entity.Request;
import cz.dsw.actuator_examples.example02.entity.Response;

public interface ServiceProvider <R extends Request, T extends Response> {

    T perform(R request);

    default String getInstanceName() {
        return "Provider";
    }
}
