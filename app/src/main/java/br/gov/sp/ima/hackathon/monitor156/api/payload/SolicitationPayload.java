package br.gov.sp.ima.hackathon.monitor156.api.payload;

import com.google.gson.annotations.SerializedName;

public class SolicitationPayload {

    @SerializedName("id") String solicitationId;
    @SerializedName("numeroSolicitacao") long number;
    @SerializedName("descricaoAssunto") String description;
    @SerializedName("statusSolicitacao") int status;
    @SerializedName("descricaoStatus") String statusDescription;

    public String getSolicitationId() {
        return solicitationId;
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
