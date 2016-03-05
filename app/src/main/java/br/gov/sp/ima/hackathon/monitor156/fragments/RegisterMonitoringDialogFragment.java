package br.gov.sp.ima.hackathon.monitor156.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import br.gov.sp.ima.hackathon.monitor156.R;

public class RegisterMonitoringDialogFragment extends DialogFragment {

    private OnRegisterMonitoringListener listener;

    public RegisterMonitoringDialogFragment() { }

    public static RegisterMonitoringDialogFragment newInstance() {
        return new RegisterMonitoringDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        dialog.setView(inflater.inflate(R.layout.dialog_fragment_register_monitoring, null))
                .setTitle(R.string.new_monitoring)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onRegisterMonitoring();
                    }
                });
        return dialog.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterMonitoringListener) {
            listener = (OnRegisterMonitoringListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterMonitoringListener");
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
    public interface OnRegisterMonitoringListener {
        void onRegisterMonitoring();
    }
}
