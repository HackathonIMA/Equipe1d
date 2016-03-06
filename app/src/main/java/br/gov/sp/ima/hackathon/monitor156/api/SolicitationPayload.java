package br.gov.sp.ima.hackathon.monitor156.api;

import com.google.gson.annotations.SerializedName;

public class SolicitationPayload {

    @SerializedName("numeroSolicitacao") long number;
    @SerializedName("descricaoAssunto") String description;
    @SerializedName("statusSolicitacao") int status;
    @SerializedName("descricaoStatus") String statusDescription;

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
