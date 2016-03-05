package br.gov.sp.ima.hackathon.monitor156.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface MonitorApi {

    @POST("/cadastraApi")
    void registerMonitoring(
            @Body RegisterMonitoringPayload payload,
            Callback<MonitoringPayload> callback
    );

    @GET("/listaApi")
    void fetchTickets(
            @Body FetchMonitoringPayload payload,
            Callback<List<MonitoringPayload>> callback
    );
}
