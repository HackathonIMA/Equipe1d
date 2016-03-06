package br.gov.sp.ima.hackathon.monitor156.repositories;

public interface RepositoryFactory {
    MonitoringRepository forMonitoring();

    SolicitationRepository forSolicitation();
}
