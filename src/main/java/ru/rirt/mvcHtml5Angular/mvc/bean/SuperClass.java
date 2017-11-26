package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class SuperClass {

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
    @Column(name = "curr_var_rel_freq_diff", nullable = false, length = 50)
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

        SuperClass superClass = (SuperClass) o;

        if (id != superClass.id) return false;
        if (channel != superClass.channel) return false;
        if (date != superClass.date) return false;
        if (phase != null ? !phase.equals(superClass.phase) : superClass.phase != null) return false;
        if (phaseDiff != null ? !phaseDiff.equals(superClass.phaseDiff) : superClass.phaseDiff != null) return false;
        if (relFreqDiff != null ? !relFreqDiff.equals(superClass.relFreqDiff) : superClass.relFreqDiff != null) return false;
        if (curVarRelFreqDiff != null ? !curVarRelFreqDiff.equals(superClass.curVarRelFreqDiff) : superClass.curVarRelFreqDiff != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) channel;
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (phase != null ? phase.hashCode() : 0);
        result = 31 * result + (phaseDiff != null ? phaseDiff.hashCode() : 0);
        result = 31 * result + (relFreqDiff != null ? relFreqDiff.hashCode() : 0);
        result = 31 * result + (curVarRelFreqDiff != null ? curVarRelFreqDiff.hashCode() : 0);
        return result;
    }







}
