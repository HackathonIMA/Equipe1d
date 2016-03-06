package br.gov.sp.ima.hackathon.monitor156.repositories.remote;

import android.util.Log;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.api.MonitorModule;
import br.gov.sp.ima.hackathon.monitor156.api.SolicitationPayload;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RemoteSolicitationRepository implements SolicitationRepository {

    private static final int OFFSET = 0;
    private static final int LIMIT = 1;

    @Override
    public void fetchSolicitationByProtocolNumber(String requestYear, int type, long number, final SolicitationListener listener) {

        String filters =
                "anoSolicitacao:" + requestYear +
                        "tipoSolicitacao:" + String.valueOf(type) +
                        "numeroSolicitacao:" + String.valueOf(number);

        MonitorModule.getApi().fetchSolicitation(OFFSET, LIMIT, filters, new Callback<List<SolicitationPayload>>() {
            @Override
            public void success(List<SolicitationPayload> solicitationPayload, Response response) {
                if (solicitationPayload.size() > 0) {
                    listener.onFetchSolicitationSuccess(solicitationPayload.get(0));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", "");
            }
        });
    }
}
