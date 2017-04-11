package pe.shu.weather;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pe.shu.weather.model.WeatherIcon;
import pe.shu.weather.model.forecast.ForecastDay;

/**
 * Created by William on 4/11/2017.
 */

public class TodayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mConditionTextView;
    private TextView mHighTextView;
    private TextView mLowTextView;

    private ImageView mWeatherImage;

    private String mTempFormatString;

    private OnTodayClickedListener mListener;

    public TodayViewHolder(View itemView) {
        super(itemView);

        mConditionTextView = (TextView)itemView.findViewById(R.id.forecast_day_condition);
        mHighTextView = (TextView)itemView.findViewById(R.id.forecast_day_high);
        mLowTextView = (TextView)itemView.findViewById(R.id.forecast_day_low);

        mWeatherImage = (ImageView)itemView.findViewById(R.id.weather_image);

        mTempFormatString = itemView.getContext().getString(R.string.temperature);

        itemView.setOnClickListener(this);
    }

    public void onBindViewHolder(ForecastDay forecastDay, OnTodayClickedListener listener) {
        mConditionTextView.setText(forecastDay.getCondition());
        mHighTextView.setText(String.format(mTempFormatString, forecastDay.getHigh()));
        mLowTextView.setText(String.format(mTempFormatString, forecastDay.getLow()));

        mWeatherImage.setImageResource(WeatherIcon.getIconFromCode(forecastDay.getCode()));

        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forecast_day_container:
                if (mListener != null) {
                    mListener.onTodayClicked();
                }
                break;
        }
    }

    public interface OnTodayClickedListener {
        void onTodayClicked();
    }
}