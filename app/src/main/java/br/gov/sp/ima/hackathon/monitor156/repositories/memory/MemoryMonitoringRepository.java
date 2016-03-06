package br.gov.sp.ima.hackathon.monitor156.repositories.memory;

import java.util.Arrays;
import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

public class MemoryMonitoringRepository implements MonitoringRepository {

    private static final List<Monitoring> FIXED_MONITORING_LIST = Arrays.asList(
            new Monitoring("Buraco na via", 2, "Finalizado"),
            new Monitoring("Vazamento", 3, ""),
            new Monitoring("Monitoring", 4, "")
    );

    @Override
    public void registerMonitoring(String solicitationId, String cellNumber, final MonitoringListener listener) {
        // TODO:
    }

    @Override
    public List<Monitoring> fetchMonitoring() {
        return FIXED_MONITORING_LIST;
    }

    @Override
    public void contestStatus(String solicitationId, String comment, MonitoringListener listener) {
        // TODO:
    }
}
