package br.gov.sp.ima.hackathon.monitor156.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class MonitorModule {

    public Gson providesGson() {
        return new GsonBuilder()
                .create();
    }

    public RestAdapter providesRestAdapter(Gson gson) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

        return new RestAdapter.Builder()
                .setEndpoint("")
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .build();
    }

    public MonitorApi providesEurop(RestAdapter adapter) {
        return adapter.create(MonitorApi.class);
    }

    public static MonitorApi europService() {
        MonitorModule module = new MonitorModule();
        Gson gson = module.providesGson();
        RestAdapter adapter = module.providesRestAdapter(gson);
        return module.providesEurop(adapter);
    }
}
