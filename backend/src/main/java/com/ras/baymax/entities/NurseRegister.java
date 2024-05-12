package com.ras.baymax.entities;

public class NurseRegister {
    private String username;
    private String password;
    private String name;
    private String coren;
    private WorkHours workHours;
    private Phone phone;

    public NurseRegister(String username, String password, String name, String coren, WorkHours workHours, Phone phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.coren = coren;
        this.workHours = workHours;
        this.phone = phone;
    }

    public NurseRegister() {
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
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

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }
}
