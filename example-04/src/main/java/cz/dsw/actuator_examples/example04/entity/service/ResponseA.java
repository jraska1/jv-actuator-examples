package cz.dsw.actuator_examples.example04.entity.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import cz.dsw.actuator_examples.example04.entity.Response;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;

public class ResponseA extends Response {

    public static final String AUDIT_EVENT = "provider.serviceA";

    private long result;

    @JsonCreator
    @ConstructorProperties({"nid", "name"})
    public ResponseA(URI nid, String name) {
        super(nid, name);
    }

    public ResponseA(URI nid, String name, URI tid) {
        super(nid, name, tid);
    }

    public ResponseA(URI nid, String name, URI tid, Date ts) {
        super(nid, name, tid, ts);
    }

    public long getResult() {
        return result;
    }
    public void setResult(long result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MessageFormat.format("ResponseA(result={0}, {1})", result, super.toString());
    }

    public String auditEvent() { return AUDIT_EVENT; }
}