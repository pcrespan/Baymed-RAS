package com.ras.baymax.entities;

import com.ras.baymax.entities.enums.Specialty;

public class DoctorRegister {
    private String username;
    private String password;
    private String name;
    private Specialty specialty;
    private String crm;
    private Phone phone;
    private WorkHours workHours;

    public DoctorRegister() {
    }

    public DoctorRegister(String username, String password, String name, Specialty specialty, String crm, Phone phone, WorkHours workHours) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.specialty = specialty;
        this.crm = crm;
        this.phone = phone;
        this.workHours = workHours;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
