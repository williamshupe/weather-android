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

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @IntDef({TODAY_TYPE, FORECAST_DAY_TYPE, LOADING_TYPE, ERROR_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface ForecastViewType {}
    private static final int TODAY_TYPE = 0;
    private static final int FORECAST_DAY_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int ERROR_TYPE = 3;

    private boolean mIsLoading;
    private Forecast mForecast;
    private TodayViewHolder.OnTodayClickedListener mTodayClickedListener;
    private ErrorViewHolder.OnRetryClickedListener mRetryClickedListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ForecastViewType int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case FORECAST_DAY_TYPE:
                viewHolder = new ForecastDayViewHolder(inflater.inflate(R.layout.view_holder_forecast_day, parent, false));
                break;
            case LOADING_TYPE:
                viewHolder = new LoadingViewHolder(inflater.inflate(R.layout.view_holder_loading, parent, false));
                break;
            case TODAY_TYPE:
                viewHolder = new TodayViewHolder(inflater.inflate(R.layout.view_holder_today, parent, false));
                break;
            case ERROR_TYPE:
                viewHolder = new ErrorViewHolder(inflater.inflate(R.layout.view_holder_error, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case FORECAST_DAY_TYPE:
                ((ForecastDayViewHolder) holder).onBindViewHolder(mForecast.getForecast().get(position));
                break;
            case LOADING_TYPE:
                break;
            case TODAY_TYPE:
                ((TodayViewHolder) holder).onBindViewHolder(mForecast.getForecast().get(0), mTodayClickedListener);
                break;
            case ERROR_TYPE:
                ((ErrorViewHolder) holder).onBindView(mRetryClickedListener);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (!mIsLoading && mForecast != null && mForecast.getForecast() != null) {
            return mForecast.getForecast().size();
        } else {
            return 1;
        }
    }

    @Override
    public @ForecastViewType int getItemViewType(int position) {
        if (mIsLoading) {
            return LOADING_TYPE;
        } else if (mForecast == null) {
            return ERROR_TYPE;
        } else if (position == 0) {
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

    public void setTodayClickedListener(TodayViewHolder.OnTodayClickedListener todayClickedListener) {
        mTodayClickedListener = todayClickedListener;
        notifyItemChanged(0);
    }

    public void setRetryClickedListener(ErrorViewHolder.OnRetryClickedListener retryClickedListener) {
        mRetryClickedListener = retryClickedListener;
        notifyItemChanged(0);
    }
}
