package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.RepositoryFactory;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;

public class RemoteRepositoryFactory implements RepositoryFactory {

    private static RemoteMonitoringRepository remoteMonitoringRepository = new RemoteMonitoringRepository();
    private static RemoteSolicitationRepository remoteSolicitationRepository = new RemoteSolicitationRepository();

    @Override
    public MonitoringRepository forMonitoring() {
        return remoteMonitoringRepository;
    }

    @Override
    public SolicitationRepository forSolicitation() {
        return remoteSolicitationRepository;
    }
}
