package pe.shu.weather;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import pe.shu.weather.model.Forecast;
import pe.shu.weather.model.forecast.Location;
import pe.shu.weather.rest.RestClient;
import pe.shu.weather.rest.YahooWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ForecastRecyclerAdapter mAdapter;

    private MenuItem mSearchMenuItem;

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

        getForecast("South Jordan");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void getForecast(String location) {
        YahooWeatherService apiService = RestClient.getApiService();
        String yqlQuery = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);
        Call<Forecast> forecastCall = apiService.forecast(yqlQuery);
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                Location forecastLocation = forecast.getLocation();
                getSupportActionBar().setTitle(String.format(getString(R.string.location_title), forecastLocation.getCity(), forecastLocation.getRegion()));
                mAdapter.setForecast(forecast);
                mAdapter.setLoading(false);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("Weather app", "Failed to get forecast", t);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("query", query);
        getForecast(query);
        MenuItemCompat.collapseActionView(mSearchMenuItem);
        getSupportActionBar().setTitle(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("query change", newText);
        return false;
    }
}
