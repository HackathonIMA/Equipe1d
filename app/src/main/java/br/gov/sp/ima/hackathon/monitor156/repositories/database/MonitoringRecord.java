package br.gov.sp.ima.hackathon.monitor156.repositories.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.gov.sp.ima.hackathon.monitor156.values.Monitoring;

@DatabaseTable(tableName = "monitoring")
public class MonitoringRecord {

    @DatabaseField(id = true) private int id;
    @DatabaseField private String requestYear;
    @DatabaseField private int type;
    @DatabaseField long number;
    @DatabaseField String description;
    @DatabaseField int status;
    @DatabaseField String statusDescription;

    /* for framework */
    MonitoringRecord() { }

    public MonitoringRecord(String requestYear, int type, long number, String description, int status, String statusDescription) {
        this.requestYear = requestYear;
        this.type = type;
        this.number = number;
        this.description = description;
        this.status = status;
        this.statusDescription = statusDescription;
    }

    public Monitoring toMonitoring() {
        return new Monitoring(description, status, statusDescription);
    }
}
