package pe.shu.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pe.shu.weather.model.Forecast;

/**
 * Created by William on 4/10/2017.
 */

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean mIsLoading;
    private Forecast mForecast;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        viewHolder = new ForecastDayViewHolder(inflater.inflate(R.layout.view_holder_forecast_day, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ForecastDayViewHolder)holder).onBindViewHolder(mForecast.getForecast().get(position));
    }

    @Override
    public int getItemCount() {
        if (mForecast != null && mForecast.getForecast() != null) {
            return mForecast.getForecast().size();
        } else {
            return 0;
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
}
