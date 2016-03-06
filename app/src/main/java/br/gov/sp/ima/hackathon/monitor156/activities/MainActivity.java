package br.gov.sp.ima.hackathon.monitor156.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.adapters.TabsAdapter;
import br.gov.sp.ima.hackathon.monitor156.api.payload.SolicitationPayload;
import br.gov.sp.ima.hackathon.monitor156.fragments.ArchivedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.FinishedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.InProgressMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.RegisterMonitoringDialogFragment;
import br.gov.sp.ima.hackathon.monitor156.infos.TelephoneData;
import br.gov.sp.ima.hackathon.monitor156.notificatioin.BigBangNotification;
import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.Repositories;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.database.DatabaseFactory;
import br.gov.sp.ima.hackathon.monitor156.repositories.database.MonitoringRecord;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements InProgressMonitoringFragment.OnInProgressInteractionListener,
        FinishedMonitoringFragment.OnFinishedInteractionListener, ArchivedMonitoringFragment.OnArchivedInteractionListener,
        RegisterMonitoringDialogFragment.OnRegisterMonitoringListener, SolicitationRepository.SolicitationListener, MonitoringRepository.MonitoringListener {

    public static final String BROADCAST_ACTION_SYNC = "sync_action";

    private static final String FROM_NOTIFICATION = "from_notification";

    private Dao<MonitoringRecord, Integer> monitoringDao;
    private String requestYear;
    private int type;
    private long number;
    private boolean firstTime = true;
    private String solicitationId;

    @Bind(android.R.id.content) View rootView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);

        DatabaseFactory databaseFactory = (DatabaseFactory) getApplication();

        try {
            monitoringDao = databaseFactory.getMonitoringDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean fromNotification = getIntent().getBooleanExtra(FROM_NOTIFICATION, false);
        String solicId = getIntent().getStringExtra(EvaluateSolicitationActivity.SOLICITATION_ID);
        if (fromNotification) {
            Intent intent = new Intent(getApplicationContext(), EvaluateSolicitationActivity.class);
            intent.putExtra(EvaluateSolicitationActivity.SOLICITATION_ID, solicId);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInProgressInteraction() {
        Snackbar.make(rootView, "InProgressInteraction", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onFabInteraction() {
        RegisterMonitoringDialogFragment fragment = RegisterMonitoringDialogFragment.newInstance();
        fragment.show(getSupportFragmentManager(), fragment.getClass().getSimpleName());
    }

    @Override
    public void onFinishedInteraction(Monitoring mItem) {
        Snackbar.make(rootView, "FinishedInteraction", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onArchivedInteraction(Monitoring mItem) {
        Snackbar.make(rootView, "ArchivedInteraction", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onConfirmRegisterDialog(String requestYear, int type, long number) {
        this.requestYear = requestYear;
        this.type = type;
        this.number = number;
        Repositories
                .repository()
                .forSolicitation()
                .fetchSolicitationByProtocolNumber(requestYear, type, number, this);
    }

    @Override
    public void onRegisterMonitoring() {
        Snackbar.make(rootView, "Registered", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onFetchSolicitationSuccess(SolicitationPayload payload) {
        solicitationId = payload.getSolicitationId();
        setupHandler();
        MonitoringRecord monitoringRecord = new MonitoringRecord(
                requestYear,
                type,
                number,
                payload.getDescription(),
                payload.getStatus(),
                payload.getStatusDescription()
        );
        try {
            monitoringDao.create(monitoringRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Repositories
                .repository()
                .forMonitoring()
                .registerMonitoring(payload.getSolicitationId(), TelephoneData.getPhoneNumber(this), this);

        sendBroadcast();
    }

    @Override
    public void onFetchSolicitationFail() {
        Snackbar.make(rootView, R.string.fail_solicitation, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onRegisterMonitoringSuccess() {
        // TODO:
    }

    @Override
    public void onRegisterMonitoringFail() {
        // TODO: show some message feedback
    }

    public void setupHandler() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 1000);
    }

    // just to simulate the change in the the api status, which should causes a notification in the app
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            Log.e("Handlers", "Calls");
            if (firstTime) {
                fakeNotification();
                firstTime = false;
            }
        }
    };

    private void fakeNotification() {
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra(FROM_NOTIFICATION, true);
        resultIntent.putExtra(EvaluateSolicitationActivity.SOLICITATION_ID, solicitationId);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        BigBangNotification.notify(
                this,
                R.string.notification_title,
                R.string.notification_message,
                resultPendingIntent);
    }

    private void sendBroadcast() {
        Intent broadcastIntent = new Intent(BROADCAST_ACTION_SYNC);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        broadcastManager.sendBroadcast(broadcastIntent);
    }
}
