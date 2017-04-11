package pe.shu.weather;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pe.shu.weather.model.forecast.ForecastDay;

/**
 * Created by William on 4/10/2017.
 */

public class ForecastDayViewHolder extends RecyclerView.ViewHolder {

    private ForecastDay mForecastDay;

    private TextView mDateTextView;
    private TextView mConditionTextView;
    private TextView mHighTextView;
    private TextView mLowTextView;

    private String mTempFormatString;

    public ForecastDayViewHolder(View itemView) {
        super(itemView);

        mDateTextView = (TextView)itemView.findViewById(R.id.forecast_day_date);
        mConditionTextView = (TextView)itemView.findViewById(R.id.forecast_day_condition);
        mHighTextView = (TextView)itemView.findViewById(R.id.forecast_day_high);
        mLowTextView = (TextView)itemView.findViewById(R.id.forecast_day_low);

        mTempFormatString = itemView.getContext().getString(R.string.temperature);
    }

    public void onBindViewHolder(ForecastDay forecastDay) {
        mDateTextView.setText(forecastDay.getDate().toString("EEEE, MMM d"));
        mConditionTextView.setText(forecastDay.getCondition());
        mHighTextView.setText(String.format(mTempFormatString, forecastDay.getHigh()));
        mLowTextView.setText(String.format(mTempFormatString, forecastDay.getLow()));
    }
}
