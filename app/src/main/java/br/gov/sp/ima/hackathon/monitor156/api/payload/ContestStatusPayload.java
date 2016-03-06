package br.gov.sp.ima.hackathon.monitor156.api.payload;

import com.google.gson.annotations.SerializedName;

public class ContestStatusPayload {

    @SerializedName("solicitation_id") String id;
    @SerializedName("observacao") String comment;

    public ContestStatusPayload(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
