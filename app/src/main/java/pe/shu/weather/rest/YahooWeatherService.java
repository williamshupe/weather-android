package pe.shu.weather.rest;

import pe.shu.weather.model.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by William on 4/10/2017.
 */

public interface YahooWeatherService {

    @Headers("Accept: application/json")
    @GET("yql")
    Call<Forecast> forecast(
            @Query("q") String yql
    );
}
