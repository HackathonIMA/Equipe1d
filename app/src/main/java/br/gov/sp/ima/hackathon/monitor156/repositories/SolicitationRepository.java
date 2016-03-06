package br.gov.sp.ima.hackathon.monitor156.repositories;

import br.gov.sp.ima.hackathon.monitor156.api.SolicitationPayload;

public interface SolicitationRepository {

    interface SolicitationListener {
        void onFetchSolicitationSuccess(SolicitationPayload solicitationPayload);
        void onFetchSolicitationFail();
    }

    void fetchSolicitationByProtocolNumber(String requestYear, int type, long number, SolicitationListener listener);
}
