package cz.dsw.actuator_examples.example04.entity;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;

public class Response extends Token {

    private ResponseCodeType code;

    public Response(URI nid, String name) {
        super(nid, name);
        this.code = ResponseCodeType.OK;
    }

    public Response(URI nid, String name, URI tid) {
        super(nid, name, tid);
        this.code = ResponseCodeType.OK;
    }

    public Response(URI nid, String name, URI tid, Date ts) {
        super(nid, name, tid, ts);
        this.code = ResponseCodeType.OK;
    }

    public ResponseCodeType getCode() { return code; }
    public void setCode(ResponseCodeType code) { this.code = code; }

    @Override
    public String toString() {
        return MessageFormat.format("Response(code={0}, {1})", code, super.toString());
    }
}