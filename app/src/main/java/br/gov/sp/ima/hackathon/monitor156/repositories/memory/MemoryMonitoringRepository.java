package br.gov.sp.ima.hackathon.monitor156.repositories.memory;

import java.util.Arrays;
import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

public class MemoryMonitoringRepository implements MonitoringRepository {

    private static final List<Monitoring> FIXED_MONITORING_LIST = Arrays.asList(
            new Monitoring("1", "Monitoring 1"),
            new Monitoring("2", "Monitoring 2"),
            new Monitoring("3", "Monitoring 3")
    );

    @Override
    public void registerMonitoring(MonitoringListener listener) {
        // TODO:
    }

    @Override
    public List<Monitoring> fetchMonitoring() {
        return FIXED_MONITORING_LIST;
    }
}
