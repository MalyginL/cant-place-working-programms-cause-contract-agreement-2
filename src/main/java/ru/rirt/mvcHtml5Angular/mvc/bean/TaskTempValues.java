package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Leonid Malygin on 16.08.2017.
 * lenyamalygin@gmail.com
 */
@Entity
public class TaskTempValues {
    private int id;
    private int stable;
    private int lacuna;
    private int programTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "stable", nullable = false)
    public int getStable() {
        return stable;
    }

    public void setStable(int stable) {
        this.stable = stable;
    }

    @Basic
    @Column(name = "lacuna", nullable = false)
    public int getLacuna() {
        return lacuna;
    }

    public void setLacuna(int lacuna) {
        this.lacuna = lacuna;
    }

    @Basic
    @Column(name = "programTime", nullable = false)
    public int getProgramTime() {
        return programTime;
    }

    public void setProgramTime(int programTime) {
        this.programTime = programTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskTempValues that = (TaskTempValues) o;

        if (id != that.id) return false;
        if (stable != that.stable) return false;
        if (lacuna != that.lacuna) return false;
        if (programTime != that.programTime) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + stable;
        result = 31 * result + lacuna;
        result = 31 * result + programTime;
        return result;
    }
}
