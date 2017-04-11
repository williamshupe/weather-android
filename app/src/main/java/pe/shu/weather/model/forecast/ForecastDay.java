package pe.shu.weather.model.forecast;

import org.joda.time.DateTime;

/**
 * Created by William on 4/10/2017.
 */

public class ForecastDay {

    private int mCode;
    private DateTime mDate;
    private int mHigh;
    private int mLow;
    private String mCondition;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public DateTime getDate() {
        return mDate;
    }

    public void setDate(DateTime date) {
        mDate = date;
    }

    public int getHigh() {
        return mHigh;
    }

    public void setHigh(int high) {
        mHigh = high;
    }

    public int getLow() {
        return mLow;
    }

    public void setLow(int low) {
        mLow = low;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }
}
