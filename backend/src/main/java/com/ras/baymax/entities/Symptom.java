package com.ras.baymax.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_SYMPT")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String en;
    private String symptomKey;
    private String ptbr;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "APPOINTMENT_ID")
    private Appointment appointment;

    public Symptom() {
    }

    public Symptom(Long id, String en, String symptomKey, String ptbr, Appointment appointment) {
        this.id = id;
        this.en = en;
        this.symptomKey = symptomKey;
        this.ptbr = ptbr;
        this.appointment = appointment;
    }

    @JsonIgnore
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getSymptomKey() {
        return symptomKey;
    }

    public void setSymptomKey(String symptomKey) {
        this.symptomKey = symptomKey;
    }

    public String getPtbr() {
        return ptbr;
    }

    public void setPtbr(String ptbr) {
        this.ptbr = ptbr;
    }
}
