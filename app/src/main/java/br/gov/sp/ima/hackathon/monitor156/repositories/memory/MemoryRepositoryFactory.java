package br.gov.sp.ima.hackathon.monitor156.repositories.memory;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.RepositoryFactory;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;

public class MemoryRepositoryFactory implements RepositoryFactory {

    private static MemoryMonitoringRepository memoryMonitoringRepository = new MemoryMonitoringRepository();
    private static MemorySolicitationRepository memorySolicitationRepository = new MemorySolicitationRepository();

    @Override
    public MonitoringRepository forMonitoring() {
        return memoryMonitoringRepository;
    }

    @Override
    public SolicitationRepository forSolicitation() {
        return memorySolicitationRepository;
    }
}
