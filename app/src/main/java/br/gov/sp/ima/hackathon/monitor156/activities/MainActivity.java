package br.gov.sp.ima.hackathon.monitor156.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.adapters.TabsAdapter;
import br.gov.sp.ima.hackathon.monitor156.api.SolicitationPayload;
import br.gov.sp.ima.hackathon.monitor156.fragments.ArchivedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.FinishedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.InProgressMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.RegisterMonitoringDialogFragment;
import br.gov.sp.ima.hackathon.monitor156.repositories.Repositories;
import br.gov.sp.ima.hackathon.monitor156.repositories.SolicitationRepository;
import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements InProgressMonitoringFragment.OnInProgressInteractionListener,
        FinishedMonitoringFragment.OnFinishedInteractionListener, ArchivedMonitoringFragment.OnArchivedInteractionListener,
        RegisterMonitoringDialogFragment.OnRegisterMonitoringListener, SolicitationRepository.SolicitationListener {

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
        Repositories.repository().forSolicitation().fetchSolicitationByProtocolNumber(requestYear, type, number, this);
    }

    @Override
    public void onRegisterMonitoring() {
        Snackbar.make(rootView, "Registered", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onFetchSolicitationSuccess(SolicitationPayload solicitationPayload) {

    }

    @Override
    public void onFetchSolicitationFail() {
        Snackbar.make(rootView, "Falha", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
