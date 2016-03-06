package br.gov.sp.ima.hackathon.monitor156.api.payload;

import com.google.gson.annotations.SerializedName;

public class RegisterMonitoringPayload {

    @SerializedName("solicitation_id") String solicitationId;
    @SerializedName("cell") String cellNumber;

    public RegisterMonitoringPayload(String solicitationId, String cellNumber) {
        this.solicitationId = solicitationId;
        this.cellNumber = cellNumber;
    }
}
