package br.gov.sp.ima.hackathon.monitor156.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface MonitorApi {

    @POST("/monitoramento")
    void registerMonitoring(
            @Body RegisterMonitoringPayload payload,
            Callback<MonitoringPayload> callback
    );

    @GET("/v1/atendimento")
    void fetchSolicitation(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("filters") String filters,
            Callback<List<SolicitationPayload>> callback
    );
}
