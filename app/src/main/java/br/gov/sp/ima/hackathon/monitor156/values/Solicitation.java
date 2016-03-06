package br.gov.sp.ima.hackathon.monitor156.values;

public class Solicitation {

    private String requestYear;
    private int type;
    private long number;
    private String description;
    private int status;
    private String statusDescription;

    public Solicitation(String requestYear, int type, long number, String description, int status, String statusDescription) {
        this.requestYear = requestYear;
        this.type = type;
        this.number = number;
        this.description = description;
        this.status = status;
        this.statusDescription = statusDescription;
    }

    public String getRequestYear() {
        return requestYear;
    }

    public int getType() {
        return type;
    }

    public long getNumber() {
        return number;
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
