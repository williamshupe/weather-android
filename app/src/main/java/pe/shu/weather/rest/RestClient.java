package pe.shu.weather.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import pe.shu.weather.model.Forecast;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by William on 4/10/2017.
 */

public class RestClient {

    private static final int NETWORK_TIMEOUT = 10;

    private RestClient(){}

    private static YahooWeatherService mApiService;

    public static YahooWeatherService getApiService() {
        if(mApiService == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Forecast.class, new ResponseAdapter())
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://query.yahooapis.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            mApiService = retrofit.create(YahooWeatherService.class);
        }

        return mApiService;
    }
}
