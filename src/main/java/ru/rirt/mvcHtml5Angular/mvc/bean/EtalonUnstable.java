package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Leonid Malygin on 17.08.2017.
 * lenyamalygin@gmail.com
 */
@Entity
public class EtalonUnstable {
    private int id;
    private int channel;
    private int date;
    private String skdo;

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
    @Column(name = "SKDO", nullable = false, length = 50)
    public String getSkdo() {
        return skdo;
    }

    public void setSkdo(String skdo) {
        this.skdo = skdo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EtalonUnstable that = (EtalonUnstable) o;

        if (id != that.id) return false;
        if (channel != that.channel) return false;
        if (date != that.date) return false;
        if (skdo != null ? !skdo.equals(that.skdo) : that.skdo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + channel;
        result = 31 * result + date;
        result = 31 * result + (skdo != null ? skdo.hashCode() : 0);
        return result;
    }
}
