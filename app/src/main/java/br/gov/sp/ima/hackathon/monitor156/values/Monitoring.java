package br.gov.sp.ima.hackathon.monitor156.values;

public class Monitoring {

    public String id;
    public String content;

    public Monitoring(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
