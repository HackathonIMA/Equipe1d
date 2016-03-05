package br.gov.sp.ima.hackathon.monitor156.api;

import com.google.gson.annotations.SerializedName;

public class RegisterMonitoringPayload {

    @SerializedName("bla") String bla;
    @SerializedName("ble") String ble;

    public RegisterMonitoringPayload(String bla, String ble) {
        this.bla = bla;
        this.ble = ble;
    }
}
