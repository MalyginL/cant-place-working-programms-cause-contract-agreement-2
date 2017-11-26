package ru.rirt.mvcHtml5Angular.mvc.bean;

import javax.persistence.*;

/**
 * Created by Leonid Malygin on 17.08.2017.
 * lenyamalygin@gmail.com
 */
@Entity
@Table(name = "Unstable_devices", schema = "ComparatorBase", catalog = "")
public class UnstableDevices {
    private int id;
    private int channel;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnstableDevices that = (UnstableDevices) o;

        if (id != that.id) return false;
        if (channel != that.channel) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + channel;
        return result;
    }
}
