package com.ras.baymax.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "TB_WORK_HOURS")
public class WorkHours implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalTime startHour;
    private LocalTime endHour;

    public WorkHours() {
    }

    public WorkHours(Integer id, LocalTime start, LocalTime end) {
        this.id = id;
        this.startHour = start;
        this.endHour = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return startHour;
    }

    public void setStart(LocalTime start) {
        this.startHour = start;
    }

    public LocalTime getEnd() {
        return endHour;
    }

    public void setEnd(LocalTime end) {
        this.endHour = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkHours workHours = (WorkHours) o;
        return Objects.equals(id, workHours.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
