package pe.shu.weather;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by William on 4/11/2017.
 */

public class ErrorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnRetryClickedListener mListener;

    public ErrorViewHolder(View itemView) {
        super(itemView);

        itemView.findViewById(R.id.retry_button).setOnClickListener(this);
    }

    public void onBindView(OnRetryClickedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retry_button:
                if (mListener != null) {
                    mListener.onRetryClicked();
                }
                break;
        }
    }

    public interface OnRetryClickedListener {
        void onRetryClicked();
    }
}
