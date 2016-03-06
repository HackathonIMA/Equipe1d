package br.gov.sp.ima.hackathon.monitor156.values;

public class Monitoring {

    private String description;
    private int status;
    private String statusDescription;

    public Monitoring(String description, int status, String statusDescription) {
        this.description = description;
        this.status = status;
        this.statusDescription = statusDescription;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
