package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Leonid Malygin on 28.07.2017.
 * lenyamalygin@gmail.com
 */
@MappedSuperclass
public class SuperClassTemp {
    private int id;
    private String phaseDiff;
    private String relFreqDiff;
    private long startTime;
    private int lacuna;
    private int stable;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phase_diff", nullable = false, length = 50)
    public String getPhaseDiff() {
        return phaseDiff;
    }

    public void setPhaseDiff(String phaseDiff) {
        this.phaseDiff = phaseDiff;
    }

    @Basic
    @Column(name = "rel_freq_diff", nullable = false, length = 50)
    public String getRelFreqDiff() {
        return relFreqDiff;
    }

    public void setRelFreqDiff(String relFreqDiff) {
        this.relFreqDiff = relFreqDiff;
    }

    @Basic
    @Column(name = "startTime", nullable = false)
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
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
    @Column(name = "stable", nullable = false)
    public int getStable() {
        return stable;
    }

    public void setStable(int stable) {
        this.stable = stable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperClassTemp temp = (SuperClassTemp) o;

        if (id != temp.id) return false;
        if (startTime != temp.startTime) return false;
        if (lacuna != temp.lacuna) return false;
        if (stable != temp.stable) return false;
        if (phaseDiff != null ? !phaseDiff.equals(temp.phaseDiff) : temp.phaseDiff != null) return false;
        if (relFreqDiff != null ? !relFreqDiff.equals(temp.relFreqDiff) : temp.relFreqDiff != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phaseDiff != null ? phaseDiff.hashCode() : 0);
        result = 31 * result + (relFreqDiff != null ? relFreqDiff.hashCode() : 0);
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + lacuna;
        result = 31 * result + stable;
        return result;
    }

    public String toString()
    {
        return "id  "+id+"   phaseDiff "+phaseDiff+" relFreqDiff "+relFreqDiff+" startTime "+startTime+" lacuna "+lacuna+"   stable "+stable;

    }
}
