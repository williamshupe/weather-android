package pe.shu.weather.model.forecast;

import org.joda.time.DateTime;

/**
 * Created by William on 4/10/2017.
 */

public class Condition {
    private int mCode;
    private DateTime mDate;
    private int mTemp;
    private String mText;

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

    public int getTemp() {
        return mTemp;
    }

    public void setTemp(int temp) {
        mTemp = temp;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
