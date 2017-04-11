package pe.shu.weather;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import pe.shu.weather.model.Forecast;
import pe.shu.weather.rest.RestClient;
import pe.shu.weather.rest.YahooWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ForecastRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView forecastRecycler = (RecyclerView)findViewById(R.id.forecast_recycler);

        mAdapter = new ForecastRecyclerAdapter();
        mAdapter.setLoading(true);
        forecastRecycler.setAdapter(mAdapter);
        forecastRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        forecastRecycler.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

        getForecast();
    }

    private void getForecast() {
        YahooWeatherService apiService = RestClient.getApiService();
        String yqlQuery = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", "South Jordan");
        Call<Forecast> forecastCall = apiService.forecast(yqlQuery);
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                mAdapter.setForecast(forecast);
                mAdapter.setLoading(false);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("Weather app", "Failed to get forecast", t);
            }
        });
    }
}
