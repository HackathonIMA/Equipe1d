package br.gov.sp.ima.hackathon.monitor156.api;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.api.payload.ContestStatusPayload;
import br.gov.sp.ima.hackathon.monitor156.api.payload.RegisterMonitoringPayload;
import br.gov.sp.ima.hackathon.monitor156.api.payload.SolicitationPayload;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface MonitorApi {

    @POST("/monitoramento")
    void registerMonitoring(
            @Body RegisterMonitoringPayload payload,
            Callback<Object> callback
    );

    @GET("/v1/atendimento")
    void fetchSolicitation(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("filters") String filters,
            Callback<List<SolicitationPayload>> callback
    );

    @POST("/contestaAtendimento")
    void contestStatus(
            @Body ContestStatusPayload payload,
            Callback<Object> callback
    );
}
