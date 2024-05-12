package com.ras.baymax.entities;

import com.ras.baymax.entities.enums.Profession;

public class UserRegister {
    private String username;
    private String password;
    private Profession profession;
    private Phone phone;

    public UserRegister() {
    }

    public UserRegister(String username, String password, Profession profession, Phone phone) {
        this.username = username;
        this.password = password;
        this.profession = profession;
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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
