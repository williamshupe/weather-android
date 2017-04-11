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
}
