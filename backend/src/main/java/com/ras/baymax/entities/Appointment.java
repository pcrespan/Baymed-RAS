package com.ras.baymax.entities;

import com.ras.baymax.entities.enums.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_APPOINTMENTS")
public class Appointment implements Serializable, Comparable<Appointment> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @JoinColumn(name = "NURSE_ID")
    @ManyToOne
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    private Date dateTime;

    @OneToMany(mappedBy = "appointment")
    private List<Symptom> symptoms;

    private Status status;

    public Appointment() {
    }

    public Appointment(Long id, Doctor doctor, Nurse nurse, Patient patient, Date dateTime, List<Symptom> symptoms, Status status) {
        this.id = id;
        this.doctor = doctor;
        this.nurse = nurse;
        this.patient = patient;
        this.dateTime = dateTime;
        this.symptoms = symptoms;
        this.status = status;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Appointment o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
