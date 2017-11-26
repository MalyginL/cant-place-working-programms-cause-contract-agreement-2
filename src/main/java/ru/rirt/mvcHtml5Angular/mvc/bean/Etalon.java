package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.*;

/**
 * Created by Leonid Malygin on 16.08.2017.
 * lenyamalygin@gmail.com
 */
@Entity
@Table(name = "Etalon", schema = "ComparatorBase")
public class Etalon extends  SuperClass{
    private int id;
    private int channel;
    private int date;
    private String phase;
    private String phaseDiff;
    private String relFreqDiff;
    private String curVarRelFreqDiff;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "channel", nullable = false)
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Basic
    @Column(name = "phase", nullable = false, length = 50)
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
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
    @Column(name = "cur_var_rel_freq_diff", nullable = false, length = 50)
    public String getCurVarRelFreqDiff() {
        return curVarRelFreqDiff;
    }

    public void setCurVarRelFreqDiff(String curVarRelFreqDiff) {
        this.curVarRelFreqDiff = curVarRelFreqDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Etalon etalon = (Etalon) o;

        if (id != etalon.id) return false;
        if (channel != etalon.channel) return false;
        if (date != etalon.date) return false;
        if (phase != null ? !phase.equals(etalon.phase) : etalon.phase != null) return false;
        if (phaseDiff != null ? !phaseDiff.equals(etalon.phaseDiff) : etalon.phaseDiff != null) return false;
        if (relFreqDiff != null ? !relFreqDiff.equals(etalon.relFreqDiff) : etalon.relFreqDiff != null) return false;
        if (curVarRelFreqDiff != null ? !curVarRelFreqDiff.equals(etalon.curVarRelFreqDiff) : etalon.curVarRelFreqDiff != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + channel;
        result = 31 * result + date;
        result = 31 * result + (phase != null ? phase.hashCode() : 0);
        result = 31 * result + (phaseDiff != null ? phaseDiff.hashCode() : 0);
        result = 31 * result + (relFreqDiff != null ? relFreqDiff.hashCode() : 0);
        result = 31 * result + (curVarRelFreqDiff != null ? curVarRelFreqDiff.hashCode() : 0);
        return result;
    }
}
