package pe.shu.weather.model;

/**
 * Created by William on 4/11/2017.
 */

public class ForecastService {

    private static ForecastService mForecastService;
    private Forecast mForecast;

    private ForecastService() {}

    public static ForecastService getInstance() {
        if (mForecastService == null) {
            mForecastService = new ForecastService();
        }

        return mForecastService;
    }

    public Forecast getForecast() {
        return mForecast;
    }

    public void setForecast(Forecast forecast) {
        mForecast = forecast;
    }
}
