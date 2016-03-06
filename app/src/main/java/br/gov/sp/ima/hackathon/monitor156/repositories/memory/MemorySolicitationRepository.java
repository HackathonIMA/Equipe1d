package br.gov.sp.ima.hackathon.monitor156.repositories.memory;

import java.util.Arrays;
import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Solicitation;

public class MemorySolicitationRepository implements SolicitationRepository {

    private static final List<Solicitation> FIXED_SOLICITATIONS_LIST = Arrays.asList(
            new Solicitation("2012", 1, 123, "Description 1", 2, "Bla"),
            new Solicitation("2013", 1, 234, "Description 2", 3, "Ble"),
            new Solicitation("2014", 2, 345, "Description 3", 4, "Bli")
    );

    @Override
    public void fetchSolicitationByProtocolNumber(String requestYear, int type, long number, SolicitationListener listener) {
        for (Solicitation solicitation : FIXED_SOLICITATIONS_LIST) {
            if (solicitation.getRequestYear().equals(requestYear) && solicitation.getType() == type && solicitation.getNumber() == number) {
                listener.onFetchSolicitationSuccess(solicitationPayload.get(0));
            }
        }
    }
}
