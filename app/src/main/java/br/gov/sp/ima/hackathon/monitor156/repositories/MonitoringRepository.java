package br.gov.sp.ima.hackathon.monitor156.repositories;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

public interface MonitoringRepository {

    interface MonitoringListener {
        void onRegisterMonitoringSuccess();
        void onRegisterMonitoringFail();
    }

    void registerMonitoring(MonitoringListener listener);

    List<Monitoring> fetchMonitoring();
}
