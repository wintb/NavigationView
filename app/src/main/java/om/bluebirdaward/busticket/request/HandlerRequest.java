package om.bluebirdaward.busticket.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import om.bluebirdaward.busticket.mics.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HandlerRequest {


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
