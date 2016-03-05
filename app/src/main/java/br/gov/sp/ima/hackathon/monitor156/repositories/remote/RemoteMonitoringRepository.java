package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.api.MonitoringPayload;
import br.gov.sp.ima.hackathon.monitor156.api.MonitorModule;
import br.gov.sp.ima.hackathon.monitor156.api.RegisterMonitoringPayload;
import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RemoteMonitoringRepository implements MonitoringRepository {

    @Override
    public void registerMonitoring(final MonitoringListener listener) {

        RegisterMonitoringPayload payload = new RegisterMonitoringPayload("", "");

        MonitorModule.europService().registerMonitoring(payload, new Callback<MonitoringPayload>() {
            @Override
            public void success(MonitoringPayload monitoringPayload, Response response) {
                listener.onRegisterMonitoringSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onRegisterMonitoringFail();
            }
        });
    }

    @Override
    public List<Monitoring> fetchMonitoring() {
        return null;
    }

}
