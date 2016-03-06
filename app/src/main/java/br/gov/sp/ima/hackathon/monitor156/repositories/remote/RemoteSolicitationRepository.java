package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.api.MonitorModule;
import br.gov.sp.ima.hackathon.monitor156.api.payload.SolicitationPayload;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RemoteSolicitationRepository implements SolicitationRepository {

    private static final int OFFSET = 0;
    private static final int LIMIT = 1;

    @Override
    public void fetchSolicitationByProtocolNumber(final String requestYear, final int type, final long number, final SolicitationListener listener) {

        String filters =
                "anoSolicitacao:" + requestYear +
                        "tipoSolicitacao:" + String.valueOf(type) +
                        "numeroSolicitacao:" + String.valueOf(number);

        MonitorModule.getProdApi().fetchSolicitation(OFFSET, LIMIT, filters, new Callback<List<SolicitationPayload>>() {
            @Override
            public void success(List<SolicitationPayload> payloadList, Response response) {
                if (payloadList.size() > 0) {
                    SolicitationPayload payload = payloadList.get(0);
                    listener.onFetchSolicitationSuccess(payload);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFetchSolicitationFail();
            }
        });
    }
}
