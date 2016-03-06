package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import br.gov.sp.ima.hackathon.monitor156.api.MonitorModule;
import br.gov.sp.ima.hackathon.monitor156.api.payload.ContestStatusPayload;
import br.gov.sp.ima.hackathon.monitor156.api.payload.RegisterMonitoringPayload;
import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RemoteMonitoringRepository implements MonitoringRepository {

    @Override
    public void registerMonitoring(String solicitationId, String cellNumber, final MonitoringListener listener) {

        RegisterMonitoringPayload payload = new RegisterMonitoringPayload(solicitationId, cellNumber);

        MonitorModule.getHmlApi().registerMonitoring(payload, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                if (response.getStatus() == HttpsURLConnection.HTTP_OK) {
                    listener.onRegisterMonitoringSuccess();
                } else {
                    listener.onRegisterMonitoringFail();
                }
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

    @Override
    public void contestStatus(String solicitationId, String comment, final MonitoringListener listener) {
        ContestStatusPayload payload = new ContestStatusPayload(solicitationId, comment);

        MonitorModule.getHmlApi().contestStatus(payload, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                if (response.getStatus() == HttpsURLConnection.HTTP_OK) {
                    listener.onRegisterMonitoringSuccess();
                } else {
                    listener.onRegisterMonitoringFail();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onRegisterMonitoringFail();
            }
        });
    }

}
