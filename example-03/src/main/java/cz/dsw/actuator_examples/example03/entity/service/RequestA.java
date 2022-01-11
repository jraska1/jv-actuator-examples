package cz.dsw.actuator_examples.example03.entity.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import cz.dsw.actuator_examples.example03.entity.Request;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;

public class RequestA extends Request {

    public static final String AUDIT_EVENT = "applicant.serviceA";

    private long value;

    @JsonCreator
    @ConstructorProperties({"nid", "name"})
    public RequestA(URI nid, String name) {
        super(nid, name);
    }

    public RequestA(URI nid, String name, URI tid) {
        super(nid, name, tid);
    }

    public RequestA(URI nid, String name, URI tid, Date ts) {
        super(nid, name, tid, ts);
    }

    public long getValue() { return value; }
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("RequestA(value={0}, {1})", value, super.toString());
    }

    public String auditEvent() { return AUDIT_EVENT; }
}