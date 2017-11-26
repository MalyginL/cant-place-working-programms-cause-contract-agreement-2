package ru.rirt.mvcHtml5Angular.mvc.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rirt.mvcHtml5Angular.mvc.TaskSolve.UnstableTask;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTable;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTempValues;
import ru.rirt.mvcHtml5Angular.mvc.bean.UnstableDevices;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Commands;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Repository;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Unstable;

import java.nio.channels.Channel;

/**
 * Created by Leonid Malygin on 27.07.2017.
 * lenyamalygin@gmail.com
 */
@Service
public class WebService {

    @Autowired
    Repository repository;

    @Autowired
    Unstable unstable;

    @Autowired
    Commands commands;

    @Autowired
    ContentFiller contentFiller;

    @Autowired
    UnstableTask unstableTask;


    public Repository generateAnswer_Home() {
        System.out.println("Request /home");
        return repository;
    }

    public Repository generateAnswer_Tasks() {
        System.out.println("Request /tasks");
        return repository;
    }


    public synchronized Repository addNewTask(String tableName, String cmd, int channel, int startTime, int endTime) {
        System.out.println("Request /addNewTask");
        contentFiller.updateLastId();
        System.out.println("Получил задание №" + repository.getLastTaskID());
        TaskTable newTask = new TaskTable();
        newTask.setId(repository.getLastTaskID());
        newTask.setTableName(tableName);
        newTask.setChannel(channel);
        newTask.setCommandName(cmd);
        newTask.setStartTime(startTime);
        newTask.setFinishTime(endTime);
        newTask.setStatus(3);
        repository.getTaskList().add(newTask);
        System.out.println("Добавляю задание в базу заданий");
        commands.addObjectToDb(newTask);

        TaskTempValues taskTempValues = new TaskTempValues();
        taskTempValues.setId(repository.getLastTaskID());
        taskTempValues.setLacuna(0);
        taskTempValues.setProgramTime(newTask.getStartTime());
        taskTempValues.setStable(0);
        System.out.println("Создаю строку конфигурации");
        commands.addObjectToDb(taskTempValues);


        repository.getTaskLogList().add(newTask);
        return repository;
    }


    public Repository removeTask(int id) {

        TaskTable oldTask = repository.getTaskLogList().stream().filter(log -> log.getId() == id).findAny().orElse(null);
        repository.getTaskCompletedList().add(oldTask);
        oldTask.setStatus(90);
        commands.updateObjectToDb(oldTask);
        return repository;
    }

    public Unstable addChannelForEtalon(int channel) {
        if (!unstable.getWorkingChannels().contains(channel)) {
            unstable.getWorkingChannels().add(channel);
            UnstableDevices ch = new UnstableDevices();
            ch.setChannel(channel);
            commands.addObjectToDb(ch);
        }
        commands.updateSkdoDataFromDB(unstable.getWorkingChannels());
        return unstable;
    }

    public Unstable removeChannelForEtalon(int channel) {
        unstable.getWorkingChannels().removeIf(ch -> ch == channel);
        commands.removeUnstableChannel(channel);
        commands.updateSkdoDataFromDB(unstable.getWorkingChannels());
        return unstable;
    }


    public Unstable generateAnswer_Unstable() {
        return unstable;
    }

    public Unstable unstableStart(int measurement, int observation, int sigma) {
        unstable.setSigma(sigma);
        unstable.setTime_full(observation);
        unstable.setTime_part(measurement);
        unstableTask.start();
        return unstable;
    }
}
