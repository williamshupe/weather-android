package pe.shu.weather.model.forecast;

import org.joda.time.DateTime;

/**
 * Created by William on 4/10/2017.
 */

public class Astronomy {
    private DateTime mSunrise;
    private DateTime mSunset;

    public DateTime getSunrise() {
        return mSunrise;
    }

    public void setSunrise(DateTime sunrise) {
        mSunrise = sunrise;
    }

    public DateTime getSunset() {
        return mSunset;
    }

    public void setSunset(DateTime sunset) {
        mSunset = sunset;
    }
}
