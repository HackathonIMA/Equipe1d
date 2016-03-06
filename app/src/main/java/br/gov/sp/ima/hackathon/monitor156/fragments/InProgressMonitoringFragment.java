package br.gov.sp.ima.hackathon.monitor156.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.activities.MainActivity;
import br.gov.sp.ima.hackathon.monitor156.adapters.InProgressMonitoringAdapter;
import br.gov.sp.ima.hackathon.monitor156.repositories.database.DatabaseFactory;
import br.gov.sp.ima.hackathon.monitor156.repositories.database.MonitoringRecord;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInProgressInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InProgressMonitoringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InProgressMonitoringFragment extends Fragment {

    private OnInProgressInteractionListener listener;

    @Bind(R.id.list) RecyclerView recyclerView;
    private InProgressMonitoringAdapter adapter;

    public InProgressMonitoringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InProgressMonitoringFragment.
     */
    public static InProgressMonitoringFragment newInstance() {
        return new InProgressMonitoringFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_in_progress_monitoring, container, false);
        ButterKnife.bind(this, view);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        DatabaseFactory databaseFactory = (DatabaseFactory) getActivity().getApplication();
        try {
            Dao<MonitoringRecord, Integer> monitoringDao = databaseFactory.getMonitoringDao();
            List<MonitoringRecord> inProgressListFromDB = monitoringDao.queryBuilder().where().isNotNull("status").query();
            List<Monitoring> inProgressList = new ArrayList<>();
            for (MonitoringRecord monitoringRecord : inProgressListFromDB) {
                inProgressList.add(monitoringRecord.toMonitoring());
            }

            if (inProgressList.size() > 0) {
                adapter = new InProgressMonitoringAdapter(inProgressList, listener);
                recyclerView.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return view;
    }

    @OnClick(R.id.fab)
    public void submit() {
        listener.onFabInteraction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInProgressInteractionListener) {
            listener = (OnInProgressInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInProgressInteractionListener");
        }

        IntentFilter filter = new IntentFilter(MainActivity.BROADCAST_ACTION_SYNC);
        filter.setPriority(1);
        LocalBroadcastManager.getInstance(context).registerReceiver(onSync, filter);
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
    public interface OnInProgressInteractionListener {
        void onInProgressInteraction();

        void onFabInteraction();
    }

    private BroadcastReceiver onSync = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (isAdded() && MainActivity.BROADCAST_ACTION_SYNC .equalsIgnoreCase(action)) {
                adapter.notifyDataSetChanged();
            }
        }
    };
}
