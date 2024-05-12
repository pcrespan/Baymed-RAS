package com.ras.baymax.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_NURSES")
public class Nurse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "WORK_HOUR_ID")
    private WorkHours workHours;

    private String coren;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Nurse() {
    }

    public Nurse(Long id, String name, WorkHours workHours, String coren, User user) {
        this.id = id;
        this.name = name;
        this.workHours = workHours;
        this.coren = coren;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }
}
