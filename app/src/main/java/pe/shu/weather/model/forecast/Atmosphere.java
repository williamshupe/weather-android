package pe.shu.weather.model.forecast;

/**
 * Created by William on 4/10/2017.
 */

public class Atmosphere {
    private int mHumidity;
    private double mPressure;
    private int mRising;
    private double mVisibility;

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double pressure) {
        mPressure = pressure;
    }

    public int getRising() {
        return mRising;
    }

    public void setRising(int rising) {
        mRising = rising;
    }

    public double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(double visibility) {
        mVisibility = visibility;
    }
}
