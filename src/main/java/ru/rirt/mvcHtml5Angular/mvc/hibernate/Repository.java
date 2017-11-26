package ru.rirt.mvcHtml5Angular.mvc.hibernate;

import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTable;

import java.util.*;

/**
 * Created by Leonid Malygin on 01.08.2017.
 * lenyamalygin@gmail.com
 */
@Component
public class Repository {

    private int lastTaskID;
    private List<TaskTable> taskList;
    private List<TaskTable> taskLogList;
    private List<TaskTable> taskCompletedList;



    private int progressBarValue;
    private String firstGraphJson;
    private String secondGraphJson;
    private boolean dataBaseStatus;
    private long taskLogStatus;


    public Repository() {
        taskList = Collections.synchronizedList(new ArrayList<TaskTable>());
        taskLogList = Collections.synchronizedList(new ArrayList<TaskTable>());
        taskCompletedList = Collections.synchronizedList(new ArrayList<TaskTable>());

    }



    public int getLastTaskID() {
        return lastTaskID;
    }

    public void setLastTaskID(int lastTaskID) {
        this.lastTaskID = lastTaskID;
    }

    public List<TaskTable> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskTable> taskList) {
        this.taskList = taskList;
    }

    public List<TaskTable> getTaskLogList() {
        return taskLogList;
    }

    public void setTaskLogList(List<TaskTable> taskLogList) {
        this.taskLogList = taskLogList;
    }

    public int getProgressBarValue() {
        return progressBarValue;
    }

    public void setProgressBarValue(int progressBarValue) {
        this.progressBarValue = progressBarValue;
    }

    public String getFirstGraphJson() {
        return firstGraphJson;
    }

    public void setFirstGraphJson(String firstGraphJson) {
        this.firstGraphJson = firstGraphJson;
    }

    public String getSecondGraphJson() {
        return secondGraphJson;
    }

    public void setSecondGraphJson(String secondGraphJson) {
        this.secondGraphJson = secondGraphJson;
    }

    public boolean isDataBaseStatus() {
        return dataBaseStatus;
    }

    public void setDataBaseStatus(boolean dataBaseStatus) {
        this.dataBaseStatus = dataBaseStatus;
    }

    public long getTaskLogStatus() {
        return taskLogStatus;
    }

    public void setTaskLogStatus(long taskLogStatus) {
        this.taskLogStatus = taskLogStatus;
    }

    public List<TaskTable> getTaskCompletedList() {
        return taskCompletedList;
    }

    public void setTaskCompletedList(List<TaskTable> taskCompletedList) {
        this.taskCompletedList = taskCompletedList;
    }
}
