package ru.anton.demo_stub.template;

public class KafkaMessage {
    private String msg_id;
    private long timestamp;
    private String method;
    private String uri;

    public KafkaMessage(String msg_id, long timestamp, String method, String uri) {
        this.msg_id = msg_id;
        this.timestamp = timestamp;
        this.method = method;
        this.uri = uri;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}