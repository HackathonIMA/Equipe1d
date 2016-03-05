package br.gov.sp.ima.hackathon.monitor156.repositories.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

@DatabaseTable(tableName = "monitoring")
public class MonitoringRecord {

    @DatabaseField(id = true) private String monitoringId;
    @DatabaseField private String content;

    /* for framework */
    MonitoringRecord() { }

    public MonitoringRecord(String monitoringId, String content) {
        this.monitoringId = monitoringId;
        this.content = content;
    }

    public Monitoring toMonitoring() {
        return new Monitoring(monitoringId, content);
    }
}
