package cz.dsw.actuator_examples.example03.entity;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;

public abstract class Request extends Token {

    public Request(URI nid, String name) {
        super(nid, name);
    }

    public Request(URI nid, String name, URI tid) {
        super(nid, name, tid);
    }

    public Request(URI nid, String name, URI tid, Date ts) {
        super(nid, name, tid, ts);
    }

    @Override
    public String toString() {
        return MessageFormat.format("Request({0})", super.toString());
    }
}
