package pe.shu.weather;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import pe.shu.weather.model.Forecast;

/**
 * Created by William on 4/10/2017.
 */

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TodayViewHolder.OnTodayClickedListener {

    @IntDef({TODAY_TYPE, FORECAST_DAY_TYPE, LOADING_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface ForecastViewType {}
    private static final int TODAY_TYPE = 0;
    private static final int FORECAST_DAY_TYPE = 1;
    private static final int LOADING_TYPE = 2;

    private boolean mIsLoading;
    private Forecast mForecast;
    private TodayViewHolder.OnTodayClickedListener mListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ForecastViewType int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case FORECAST_DAY_TYPE:
                viewHolder = new ForecastDayViewHolder(inflater.inflate(R.layout.view_holder_forecast_day, parent, false));
                break;
            case LOADING_TYPE:
                break;
            case TODAY_TYPE:
                viewHolder = new TodayViewHolder(inflater.inflate(R.layout.view_holder_today, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((TodayViewHolder) holder).onBindViewHolder(mForecast.getForecast().get(0), mListener);
        } else {
            ((ForecastDayViewHolder) holder).onBindViewHolder(mForecast.getForecast().get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mForecast != null && mForecast.getForecast() != null) {
            return mForecast.getForecast().size();
        } else {
            return 0;
        }
    }

    @Override
    public @ForecastViewType int getItemViewType(int position) {
        if (position == 0) {
            return TODAY_TYPE;
        } else {
            return FORECAST_DAY_TYPE;
        }
    }

    public void setForecast(Forecast forecast) {
        mForecast = forecast;
        notifyDataSetChanged();
    }

    public void setLoading(boolean loading) {
        mIsLoading = loading;
        notifyDataSetChanged();
    }

    public void setListener(TodayViewHolder.OnTodayClickedListener listener) {
        mListener = listener;
        notifyItemChanged(0);
    }

    @Override
    public void onTodayClicked() {
        if (mListener != null) {
            mListener.onTodayClicked();
        }
    }
}
