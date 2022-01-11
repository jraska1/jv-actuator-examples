package cz.dsw.actuator_examples.example01.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class ServiceInfo implements InfoContributor {

    @Autowired(required = false) List<ServiceProvider> allProviders;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("service-providers", (allProviders != null) ? allProviders.stream().map(ServiceProvider::getInstanceName).collect(Collectors.toList()) : Collections.emptyList());
    }
}
