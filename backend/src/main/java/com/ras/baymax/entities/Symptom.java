package com.ras.baymax.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SYMPTOMS")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String en;
    private String key;
    private String ptbr;

    public Symptom() {
    }

    public Symptom(Long id, String en, String key, String ptbr) {
        this.id = id;
        this.en = en;
        this.key = key;
        this.ptbr = ptbr;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPtbr() {
        return ptbr;
    }

    public void setPtbr(String ptbr) {
        this.ptbr = ptbr;
    }
}
