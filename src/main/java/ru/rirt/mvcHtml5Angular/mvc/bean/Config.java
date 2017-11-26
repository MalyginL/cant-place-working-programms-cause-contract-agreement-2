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
public class Config {
    private int id;
    private String version;
    private int lastTaskId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "version", nullable = false, length = 20)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "lastTaskID", nullable = false)
    public int getLastTaskId() {
        return lastTaskId;
    }

    public void setLastTaskId(int lastTaskId) {
        this.lastTaskId = lastTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (id != config.id) return false;
        if (lastTaskId != config.lastTaskId) return false;
        if (version != null ? !version.equals(config.version) : config.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + lastTaskId;
        return result;
    }
}
