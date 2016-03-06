package br.gov.sp.ima.hackathon.monitor156.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import br.gov.sp.ima.hackathon.monitor156.BuildConfig;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class MonitorModule {

    private static final int TIMEOUT = 60;
    private static final String CLIENT_ID = "client_id";
    private static final String ACCEPT = "Accept";

    public static MonitorApi getProdApi() {
        return setupApi(BuildConfig.PROD_BASE_URL);
    }

    public static MonitorApi getHmlApi() {
        return setupApi(BuildConfig.HML_BASE_URL);
    }

    private static MonitorApi setupApi(String baseUrl) {
        MonitorModule module = new MonitorModule();
        Gson gson = module.provideGson();
        RestAdapter adapter = module.provideRestAdapter(gson, baseUrl);
        return module.provideMonitor(adapter);
    }

    private Gson provideGson() {
        return new GsonBuilder().create();
    }

    private RestAdapter provideRestAdapter(Gson gson, String baseUrl) {
        return new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(provideOkHttpClient()))
                .setRequestInterceptor(provideRequestInterceptor())
                .build();
    }

    private OkHttpClient provideOkHttpClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        return okHttpClient;
    }

    private RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(ACCEPT, BuildConfig.APPLICATION_JSON);
                    request.addHeader(CLIENT_ID, BuildConfig.CLIENT_ID);
                }
            };
    }

    public MonitorApi provideMonitor(RestAdapter adapter) {
        return adapter.create(MonitorApi.class);
    }
}
