package pe.shu.weather;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import pe.shu.weather.model.ForecastService;
import pe.shu.weather.model.forecast.Location;
import pe.shu.weather.rest.RestClient;
import pe.shu.weather.rest.YahooWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        TodayViewHolder.OnTodayClickedListener, FragmentManager.OnBackStackChangedListener,
        ErrorViewHolder.OnRetryClickedListener {

    private ForecastRecyclerAdapter mAdapter;

    private MenuItem mSearchMenuItem;

    private String mLastQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        displayHomeUp();

        RecyclerView forecastRecycler = (RecyclerView)findViewById(R.id.forecast_recycler);

        mAdapter = new ForecastRecyclerAdapter();
        mAdapter.setTodayClickedListener(this);
        mAdapter.setRetryClickedListener(this);
        forecastRecycler.setAdapter(mAdapter);
        forecastRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        forecastRecycler.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

        Forecast forecast = ForecastService.getInstance().getForecast();
        if (forecast != null) {
            mAdapter.setForecast(forecast);
            getSupportActionBar().setTitle(String.format(getString(R.string.location_title), forecast.getLocation().getCity(), forecast.getLocation().getRegion()));
        } else {
            getForecast("South Jordan");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setOnQueryTextListener(this);

        mSearchMenuItem.setVisible(!shouldDisplayUp());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        displayHomeUp();
    }

    private boolean shouldDisplayUp() {
        return getSupportFragmentManager().getBackStackEntryCount() > 0;
    }

    public void displayHomeUp() {
        boolean showUp = shouldDisplayUp();
        getSupportActionBar().setDisplayHomeAsUpEnabled(showUp);
        invalidateOptionsMenu();
    }

    private void getForecast(String location) {
        mAdapter.setLoading(true);
        mLastQuery = location;
        YahooWeatherService apiService = RestClient.getApiService();
        String yqlQuery = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);
        Call<Forecast> forecastCall = apiService.forecast(yqlQuery);
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                ForecastService.getInstance().setForecast(forecast);
                Location forecastLocation = forecast.getLocation();
                getSupportActionBar().setTitle(String.format(getString(R.string.location_title), forecastLocation.getCity(), forecastLocation.getRegion()));
                mAdapter.setForecast(forecast);
                mAdapter.setLoading(false);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                getSupportActionBar().setTitle("Weather");
                mAdapter.setForecast(null);
                mAdapter.setLoading(false);
                ForecastService.getInstance().setForecast(null);
                Log.e("Weather app", "Failed to get forecast", t);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.setLoading(true);
        getForecast(query);
        MenuItemCompat.collapseActionView(mSearchMenuItem);
        getSupportActionBar().setTitle(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onTodayClicked() {
        MenuItemCompat.collapseActionView(mSearchMenuItem);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_content, ForecastDetailFragment.newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onRetryClicked() {
        getForecast(mLastQuery);
    }
}
