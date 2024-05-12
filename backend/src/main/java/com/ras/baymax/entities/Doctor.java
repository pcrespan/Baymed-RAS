package com.ras.baymax.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ras.baymax.entities.enums.Specialty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_DOCTORS")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String crm;
    private Specialty specialty;
    @ManyToOne
    @JoinColumn(name = "WORK_HOUR_ID")
    private WorkHours workHours;

    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointments = new ArrayList<>();

    public Doctor(Long id, String name, String crm, Specialty specialty, WorkHours workHours, List<Appointment> appointments, User user) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.specialty = specialty;
        this.workHours = workHours;
        this.appointments = appointments;
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Doctor() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
