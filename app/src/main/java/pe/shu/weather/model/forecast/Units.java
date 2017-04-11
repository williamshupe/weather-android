package pe.shu.weather.model.forecast;

/**
 * Created by William on 4/10/2017.
 */

public class Units {
    private String mDistanceUnits;
    private String mPressureUnits;
    private String mSpeedUnits;
    private String mTemperatureUnits;

    public String getDistanceUnits() {
        return mDistanceUnits;
    }

    public void setDistanceUnits(String distanceUnits) {
        mDistanceUnits = distanceUnits;
    }

    public String getPressureUnits() {
        return mPressureUnits;
    }

    public void setPressureUnits(String pressureUnits) {
        mPressureUnits = pressureUnits;
    }

    public String getSpeedUnits() {
        return mSpeedUnits;
    }

    public void setSpeedUnits(String speedUnits) {
        mSpeedUnits = speedUnits;
    }

    public String getTemperatureUnits() {
        return mTemperatureUnits;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        mTemperatureUnits = temperatureUnits;
    }
}
