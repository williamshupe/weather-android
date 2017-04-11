package pe.shu.weather.model.forecast;

/**
 * Created by William on 4/10/2017.
 */

public class Wind {
    private int mChill;
    private int mDirection;
    private int mSpeed;

    public int getChill() {
        return mChill;
    }

    public void setChill(int chill) {
        mChill = chill;
    }

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int direction) {
        mDirection = direction;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public String getWindDirection() {
        if (mDirection > 11.25 && mDirection <= 33.75) {
            return "NNE";
        } else if (mDirection > 33.75 && mDirection <= 56.25) {
            return "NE";
        } else if (mDirection > 56.25 && mDirection <= 78.75) {
            return "ENE";
        } else if (mDirection > 78.75 && mDirection <= 101.25) {
            return "E";
        } else if (mDirection > 101.25 && mDirection <= 123.75) {
            return "ESE";
        } else if (mDirection > 123.75 && mDirection <= 146.25) {
            return "SE";
        } else if (mDirection > 146.25 && mDirection <= 168.75) {
            return "SSE";
        } else if (mDirection > 168.75 && mDirection <= 191.25) {
            return "S";
        } else if (mDirection > 191.25 && mDirection <= 213.75) {
            return "SSW";
        } else if (mDirection > 213.75 && mDirection <= 236.25) {
            return "SW";
        } else if (mDirection > 236.25 && mDirection <= 258.75) {
            return "WSW";
        } else if (mDirection > 258.75 && mDirection <= 281.25) {
            return "W";
        } else if (mDirection > 281.25 && mDirection <= 303.75) {
            return "WNW";
        } else if (mDirection > 303.75 && mDirection <= 326.25) {
            return "NW";
        } else if (mDirection > 326.25 && mDirection <= 348.75) {
            return "NNW";
        } else {
            return "N";
        }
    }
}
