package br.gov.sp.ima.hackathon.monitor156.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.gov.sp.ima.hackathon.monitor156.R;
import br.gov.sp.ima.hackathon.monitor156.repositories.MonitoringRepository;
import br.gov.sp.ima.hackathon.monitor156.repositories.Repositories;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluateSolicitationActivity extends AppCompatActivity implements MonitoringRepository.MonitoringListener {

    public static final String SOLICITATION_ID = "solicitation_id";

    private String solicitationId;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.form_container) LinearLayout formContainer;
    @Bind(R.id.comment_box) EditText commentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_solicitation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        solicitationId = getIntent().getStringExtra(SOLICITATION_ID);
    }

    @OnClick(R.id.confirm) public void closeCurrentScreen() {
        finish();
    }

    @OnClick(R.id.dont_confirm) public void showForm() {
        formContainer.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.send_report) public void sendReport() {
        Repositories.repository().forMonitoring().contestStatus(solicitationId, commentBox.getText().toString().trim(), this);
    }

    @Override
    public void onRegisterMonitoringSuccess() {
        Toast.makeText(EvaluateSolicitationActivity.this, "Reportado com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterMonitoringFail() {
        Toast.makeText(EvaluateSolicitationActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
    }

}
