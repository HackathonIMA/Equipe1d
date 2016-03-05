package br.gov.sp.ima.hackathon.monitor156.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.fragments.FinishedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Monitoring} and makes a call to the
 * specified {@link FinishedMonitoringFragment.OnFinishedInteractionListener}.
 */
public class FinishedMonitoringAdapter extends RecyclerView.Adapter<FinishedMonitoringAdapter.ViewHolder> {

    private final List<Monitoring> monitoringList;
    private final FinishedMonitoringFragment.OnFinishedInteractionListener listener;

    public FinishedMonitoringAdapter(List<Monitoring> monitoringList, FinishedMonitoringFragment.OnFinishedInteractionListener listener) {
        this.monitoringList = monitoringList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_monitoring_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = monitoringList.get(position);
        holder.mIdView.setText(monitoringList.get(position).id);
        holder.mContentView.setText(monitoringList.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onFinishedInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return monitoringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Monitoring mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
