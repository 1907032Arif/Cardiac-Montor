package com.example.cardiac_monitor;


/**
 * The Record class represents a cardiac monitoring record.
 * It contains information about systolic, diastolic, pressure status, pulse, pulse status, date, time, and comments.
 */
public class Record implements Comparable<Record>{

    private String systolic;
    private String diastolic;
    private String pressure_status;
    private String pulse;
    private String pulse_status;
    private String date;
    private String time;
    private String comments;


    /**
     * Constructs a new Record object with the specified data.
     *
     * @param systolic        The systolic value.
     * @param diastolic       The diastolic value.
     * @param pressure_status The pressure status.
     * @param pulse           The pulse value.
     * @param pulse_status    The pulse status.
     * @param date            The date of the record.
     * @param time            The time of the record.
     * @param comments        Additional comments.
     */
    Record(String systolic, String diastolic, String pressure_status, String pulse, String pulse_status, String date, String time, String comments) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pressure_status = pressure_status;
        this.pulse = pulse;
        this.pulse_status = pulse_status;
        this.date = date;
        this.time = time;
        this.comments = comments;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getPressure_status() {
        return pressure_status;
    }

    public void setPressure_status(String pressure_status) {
        this.pressure_status = pressure_status;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getPulse_status() {
        return pulse_status;
    }

    public void setPulse_status(String pulse_status) {
        this.pulse_status = pulse_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(Record record) {
        return this.systolic.compareTo(record.systolic);
    }
}
