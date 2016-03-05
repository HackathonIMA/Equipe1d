package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.RepositoryFactory;

public class RemoteRepositoryFactory implements RepositoryFactory {

    private static RemoteMonitoringRepository memoryAuthenticationRepository = new RemoteMonitoringRepository();

    @Override
    public MonitoringRepository forMonitoring() {
        return memoryAuthenticationRepository;
    }
}
