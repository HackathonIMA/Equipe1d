package br.gov.sp.ima.hackathon.monitor156.repositories.database;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DatabaseFactory extends Application {

    private DatabaseHelper databaseHelper = null;

    private Dao<MonitoringRecord, Integer> monitoringDAO = null;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new DatabaseHelper(this);
    }

    public Dao<MonitoringRecord, Integer> getMonitoringDao() throws SQLException {
        if (monitoringDAO == null) {
            monitoringDAO = databaseHelper.getDao(MonitoringRecord.class);
        }
        return monitoringDAO;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
