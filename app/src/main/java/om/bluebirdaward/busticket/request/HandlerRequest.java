package om.bluebirdaward.busticket.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HandlerRequest {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.SECONDS).writeTimeout(1, TimeUnit.SECONDS);
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }

}
