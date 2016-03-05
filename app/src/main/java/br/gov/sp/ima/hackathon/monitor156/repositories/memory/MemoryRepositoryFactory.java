package br.gov.sp.ima.hackathon.monitor156.repositories.memory;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.RepositoryFactory;

public class MemoryRepositoryFactory implements RepositoryFactory {

    private static MemoryMonitoringRepository memoryMonitoringRepository = new MemoryMonitoringRepository();

    @Override
    public MonitoringRepository forMonitoring() {
        return memoryMonitoringRepository;
    }
}
