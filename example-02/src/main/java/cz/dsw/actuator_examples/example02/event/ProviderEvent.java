package cz.dsw.actuator_examples.example02.event;

import cz.dsw.actuator_examples.example02.entity.Request;
import cz.dsw.actuator_examples.example02.entity.Response;

public class ProviderEvent extends CustomEvent {

    public enum Phase {
        ASKED,
        ANSWERED
    }

    private final Phase phase;
    private final Request request;
    private final Response response;

    public ProviderEvent(Object source, Phase phase, Request request) {
        super(source);
        this.phase = phase;
        this.request = request;
        this.response = null;
    }

    public ProviderEvent(Object source, Phase phase, Request request, Response response) {
        super(source);
        this.phase = phase;
        this.request = request;
        this.response = response;
    }

    public Phase getPhase() {
        return phase;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String getEvent() { return super.getEvent() + "." + phase.name(); }
}
