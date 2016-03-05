package br.gov.sp.ima.hackathon.monitor156.repositories;

import br.gov.sp.ima.hackathon.monitor156.BuildConfig;
import br.gov.sp.ima.hackathon.monitor156.repositories.memory.MemoryRepositoryFactory;
import br.gov.sp.ima.hackathon.monitor156.repositories.remote.RemoteRepositoryFactory;

public class Repositories {

    /** no instances **/
    private Repositories() { }

    public static RepositoryFactory repository() {
        if (BuildConfig.PRODUCTION) {
            return new RemoteRepositoryFactory();
        } else {
            return new MemoryRepositoryFactory();
        }
    }
}
