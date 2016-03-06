package br.gov.sp.ima.hackathon.monitor156.repositories;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

public interface MonitoringRepository {

    interface MonitoringListener {
        void onRegisterMonitoringSuccess();
        void onRegisterMonitoringFail();
    }

    void registerMonitoring(String solicitationId, String cellNumber, final MonitoringListener listener);
    List<Monitoring> fetchMonitoring();
    void contestStatus(String solicitationId, String comment, MonitoringListener listener);
}
