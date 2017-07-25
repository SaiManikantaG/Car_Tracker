package com.cartracker.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by sai on 6/28/17.
 */
@NamedQueries({
        @NamedQuery(name = "Alerts.displayAllAlerts", query = "select al from Alerts al")
})

@Entity
public class Alerts {

    @Id
    private String alertId;
    private String priority;
    private String type;
    private String vin;
    private Timestamp timestamp;

    public Alerts() {

// Automatically generating ID from UUID library
        this.alertId = UUID.randomUUID().toString();
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Alerts{" +
                "alertId='" + alertId + '\'' +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                ", vin='" + vin + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
