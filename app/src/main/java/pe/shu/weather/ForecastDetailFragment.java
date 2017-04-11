package pe.shu.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pe.shu.weather.model.Forecast;
import pe.shu.weather.model.ForecastService;
import pe.shu.weather.model.WeatherIcon;

public class ForecastDetailFragment extends Fragment {

    private Forecast mForecast;

    public ForecastDetailFragment() {
        // Required empty public constructor
    }

    public static ForecastDetailFragment newInstance() {
        ForecastDetailFragment fragment = new ForecastDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mForecast = ForecastService.getInstance().getForecast();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_detail, container, false);

        ((TextView)view.findViewById(R.id.forecast_detail_current_time)).setText(
                mForecast.getCondition().getDate().toString("MMMM d, h:mm a")
        );

        ((TextView)view.findViewById(R.id.forecast_detail_high)).setText(
                String.format(getString(R.string.high), mForecast.getForecast().get(0).getHigh())
        );

        ((TextView)view.findViewById(R.id.forecast_detail_low)).setText(
                String.format(getString(R.string.low), mForecast.getForecast().get(0).getLow())
        );

        ((TextView)view.findViewById(R.id.forecast_detail_current_temp)).setText(
                String.format(getString(R.string.current_temp), mForecast.getCondition().getTemp(), mForecast.getUnits().getTemperatureUnits())
        );

        ((TextView)view.findViewById(R.id.forecast_detail_condition)).setText(
                mForecast.getCondition().getText()
        );

        ((TextView)view.findViewById(R.id.forecast_detail_feels_like)).setText(
                String.format(getString(R.string.feels_like), mForecast.getWind().getChill())
        );

        ((ImageView)view.findViewById(R.id.forecast_detail_image)).setImageResource(WeatherIcon.getIconFromCode(mForecast.getCondition().getCode()));

        ((TextView)view.findViewById(R.id.forecast_detail_sunrise)).setText(
                String.format(getString(R.string.sunrise), mForecast.getAstronomy().getSunrise().toString("h:mm a"))
        );

        ((TextView)view.findViewById(R.id.forecast_detail_sunset)).setText(
                String.format(getString(R.string.sunset), mForecast.getAstronomy().getSunset().toString("h:mm a"))
        );

        ((TextView)view.findViewById(R.id.forecast_detail_wind)).setText(
                String.format(
                        getString(R.string.wind_display),
                        mForecast.getWind().getSpeed(),
                        mForecast.getUnits().getSpeedUnits(),
                        mForecast.getWind().getWindDirection()
                )
        );

        return view;
    }
}
