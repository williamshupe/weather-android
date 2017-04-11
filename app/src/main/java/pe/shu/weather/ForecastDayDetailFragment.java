package pe.shu.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import pe.shu.weather.model.WeatherIcon;

/**
 * Created by William on 4/11/2017.
 */

public class ForecastDayDetailFragment extends Fragment {

    private static final String ARG_DATE = "date";
    private static final String ARG_CODE = "code";
    private static final String ARG_HIGH = "high";
    private static final String ARG_LOW = "low";
    private static final String ARG_CONDITION = "condition";

    private DateTime mDateTime;
    private int mCode;
    private int mHigh;
    private int mLow;
    private String mCondition;

    public ForecastDayDetailFragment() {}

    public static ForecastDayDetailFragment newInstance(DateTime date, int code, int high, int low, String condition) {
        ForecastDayDetailFragment fragment = new ForecastDayDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date.toString());
        args.putInt(ARG_CODE, code);
        args.putInt(ARG_HIGH, high);
        args.putInt(ARG_LOW, low);
        args.putString(ARG_CONDITION, condition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            mDateTime = DateTime.parse(args.getString(ARG_DATE));
            mCode = args.getInt(ARG_CODE);
            mHigh = args.getInt(ARG_HIGH);
            mLow = args.getInt(ARG_LOW);
            mCondition = args.getString(ARG_CONDITION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_day_detail, container, false);

        ((TextView)view.findViewById(R.id.forecast_day_detail_date)).setText(mDateTime.toString("EEEE, MMMM d, yyyy"));
        ((TextView)view.findViewById(R.id.forecast_day_detail_high)).setText(
                String.format(getString(R.string.high), mHigh)
        );
        ((TextView)view.findViewById(R.id.forecast_day_detail_low)).setText(
                String.format(getString(R.string.low), mLow)
        );
        ((TextView)view.findViewById(R.id.forecast_day_detail_condition)).setText(mCondition);

        ((ImageView)view.findViewById(R.id.weather_image)).setImageResource(WeatherIcon.getIconFromCode(mCode));

        return view;
    }
}
