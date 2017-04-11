package pe.shu.weather.model;

import java.util.List;

import pe.shu.weather.model.forecast.Astronomy;
import pe.shu.weather.model.forecast.Atmosphere;
import pe.shu.weather.model.forecast.Condition;
import pe.shu.weather.model.forecast.ForecastDay;
import pe.shu.weather.model.forecast.Location;
import pe.shu.weather.model.forecast.Units;
import pe.shu.weather.model.forecast.Wind;

/**
 * Created by William on 4/10/2017.
 */

public class Forecast {

    private Units mUnits;
    private Wind mWind;
    private Atmosphere mAtmosphere;
    private Astronomy mAstronomy;
    private Condition mCondition;
    private Location mLocation;
    private List<ForecastDay> mForecast;

    public Units getUnits() {
        return mUnits;
    }

    public void setUnits(Units units) {
        mUnits = units;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public Atmosphere getAtmosphere() {
        return mAtmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        mAtmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return mAstronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        mAstronomy = astronomy;
    }

    public Condition getCondition() {
        return mCondition;
    }

    public void setCondition(Condition condition) {
        mCondition = condition;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public List<ForecastDay> getForecast() {
        return mForecast;
    }

    public void setForecast(List<ForecastDay> forecast) {
        mForecast = forecast;
    }
}
