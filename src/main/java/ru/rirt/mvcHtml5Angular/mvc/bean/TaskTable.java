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
public class TaskTable {
    private int id;
    private String tableName;
    private int channel;
    private String commandName;
    private int startTime;
    private int finishTime;
    private int status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tableName", nullable = false, length = 40)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
    @Column(name = "commandName", nullable = false, length = 60)
    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    @Basic
    @Column(name = "startTime", nullable = false)
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "finishTime", nullable = false)
    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskTable taskTable = (TaskTable) o;

        if (id != taskTable.id) return false;
        if (channel != taskTable.channel) return false;
        if (startTime != taskTable.startTime) return false;
        if (finishTime != taskTable.finishTime) return false;
        if (status != taskTable.status) return false;
        if (tableName != null ? !tableName.equals(taskTable.tableName) : taskTable.tableName != null) return false;
        if (commandName != null ? !commandName.equals(taskTable.commandName) : taskTable.commandName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + channel;
        result = 31 * result + (commandName != null ? commandName.hashCode() : 0);
        result = 31 * result + startTime;
        result = 31 * result + finishTime;
        result = 31 * result + status;
        return result;
    }
}
