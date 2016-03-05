package br.gov.sp.ima.hackathon.monitor156.api;

import com.google.gson.annotations.SerializedName;

public class MonitoringPayload {

    @SerializedName("bla") String bla;
    @SerializedName("ble") String ble;

    public String getBla() {
        return bla;
    }

    public String getBle() {
        return ble;
    }
}
