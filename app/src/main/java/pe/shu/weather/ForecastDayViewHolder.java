package pe.shu.weather;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pe.shu.weather.model.WeatherIcon;
import pe.shu.weather.model.forecast.ForecastDay;

/**
 * Created by William on 4/10/2017.
 */

public class ForecastDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mDateTextView;
    private TextView mConditionTextView;
    private TextView mHighTextView;
    private TextView mLowTextView;

    private ImageView mWeatherImage;

    private String mTempFormatString;

    private OnForecastDayClickedListener mListener;

    public ForecastDayViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        mDateTextView = (TextView)itemView.findViewById(R.id.forecast_day_date);
        mConditionTextView = (TextView)itemView.findViewById(R.id.forecast_day_condition);
        mHighTextView = (TextView)itemView.findViewById(R.id.forecast_day_high);
        mLowTextView = (TextView)itemView.findViewById(R.id.forecast_day_low);

        mWeatherImage = (ImageView)itemView.findViewById(R.id.weather_image);

        mTempFormatString = itemView.getContext().getString(R.string.temperature);
    }

    public void onBindViewHolder(ForecastDay forecastDay, OnForecastDayClickedListener listener) {
        mDateTextView.setText(forecastDay.getDate().toString("EEEE, MMM d"));
        mConditionTextView.setText(forecastDay.getCondition());
        mHighTextView.setText(String.format(mTempFormatString, forecastDay.getHigh()));
        mLowTextView.setText(String.format(mTempFormatString, forecastDay.getLow()));

        mWeatherImage.setImageResource(WeatherIcon.getIconFromCode(forecastDay.getCode()));

        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forecast_day_view_holder_container:
                if (mListener != null) {
                    mListener.onForecastDayClicked(getAdapterPosition());
                }
                break;
        }
    }

    public interface OnForecastDayClickedListener {
        void onForecastDayClicked(int position);
    }
}
