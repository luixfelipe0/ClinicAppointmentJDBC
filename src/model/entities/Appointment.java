package model.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Appointment {

    private Integer id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String reason;

    public Appointment() {
    }

    public Appointment(Integer id, Patient patient, Doctor doctor, LocalDateTime dateTime, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", dateTime=" + dateTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}
