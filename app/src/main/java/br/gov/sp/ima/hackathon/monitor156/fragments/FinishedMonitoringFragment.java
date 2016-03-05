package br.gov.sp.ima.hackathon.monitor156.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.adapters.FinishedMonitoringAdapter;
import br.gov.sp.ima.hackathon.monitor156.repositories.Repositories;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFinishedInteractionListener}
 * interface.
 */
public class FinishedMonitoringFragment extends Fragment {

    private OnFinishedInteractionListener listener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FinishedMonitoringFragment() {
    }

    public static FinishedMonitoringFragment newInstance() {
        return new FinishedMonitoringFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            List<Monitoring> monitoringList = Repositories.repository().forMonitoring().fetchMonitoring();
            recyclerView.setAdapter(new FinishedMonitoringAdapter(monitoringList, listener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFinishedInteractionListener) {
            listener = (OnFinishedInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFinishedInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFinishedInteractionListener {
        void onFinishedInteraction(Monitoring mItem);
    }
}
