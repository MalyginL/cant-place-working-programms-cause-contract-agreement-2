package ru.rirt.mvcHtml5Angular.mvc.TaskSolve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.bean.SuperClass;
import ru.rirt.mvcHtml5Angular.mvc.bean.SuperClassTemp;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTable;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTempValues;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Commands;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Repository;

/**
 * Created by Leonid Malygin on 28.07.2017.
 * lenyamalygin@gmail.com
 */

@Component
@EnableScheduling
public class TaskFactory {
    long currentTime;

    @Autowired
    Repository repository;

    @Autowired
    Commands commands;

    @Autowired
    ResultUploader resultUploader;


    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void startStrip() {

        System.out.println("Отклик таймера управления заданий");
        System.out.println("Удаление старых/выполненных заданий");
        //Удаление завершенных заданий из пула
        synchronized (repository.getTaskCompletedList()) {
            System.out.println("В листе заданий " + repository.getTaskList().size() + " элементов");
            System.out.println("В очереди на удаление " + repository.getTaskCompletedList().size() + " элементов");
            if (repository.getTaskCompletedList().size() > 0) {
                repository.getTaskCompletedList().forEach(task ->
                {
                    repository.getTaskList().remove(repository.getTaskLogList().stream().filter(log -> log.getId() == task.getId()).findAny().orElse(null));
                    task.setStatus(91);
                    commands.updateObjectToDb(task);
                });
                repository.getTaskCompletedList().clear();
                System.out.println("После очистки в текущих заданиях осталось " + repository.getTaskList().size() + " заданий");
            }
        }
        //Выполнение заданий
        synchronized (repository.getTaskList()) {
            System.out.println("Выполнение заданий");
            if (repository.getTaskList().size() > 0)
                repository.getTaskList().forEach(task -> {

                    currentTime = System.currentTimeMillis() / 1000L + 38838400;
                    doWork(task, currentTime);
                });
        }
    }


    private void doWork(TaskTable task, long currentTime) {
        System.out.println("Входная точка выполнения текущего задания");

        TaskTempValues tempValues = commands.getTempValue(task.getId());
        if (task.getStartTime() >= currentTime) {
            tempValues.setProgramTime((int)currentTime);
            commands.updateObjectToDb(tempValues);
            System.out.println("Задание начнется в будующем, ожидаю");
        } else {
            if ((commands.getRawCountForSelect(
                    tempValues.getProgramTime(),
                    task.getFinishTime(),
                    task.getTableName(),
                    task.getChannel()) == 0) && (task.getFinishTime() < currentTime)) {

                System.out.println("время окончания задания" + task.getFinishTime());
                System.out.println("Текущее время   " + currentTime);
                System.out.println("Задание завершено, отправляю на удаление");
                repository.getTaskCompletedList().add(task);
            } else {
                System.out.println("Переход к блоку вычислений");
                calculate(tempValues, task);
            }

        }

    }

    public synchronized void calculate(TaskTempValues tempValues, TaskTable task) {
        switch (task.getCommandName()) {
            case "разность фаз": {
                commands.addArraytoDb(commands.cmdPhase_diff(commands.getRawData(tempValues, task)));
                break;
            }
            case "относительная разность частот": {
                try {
                    commands.addArraytoDb(commands.cmdRelFreq_diff(commands.getRawData(tempValues, task), tempValues, task));
                } catch (Exception x) {
                    System.out.println("calculate ex" + x);
                }
                break;
            }
            case "текущие вариации относительной разности частот": {
                commands.addArraytoDb(commands.cmdCurrVarRelFreq_diff(
                        commands.cmdRelFreq_diff
                                (commands.getRawData(tempValues, task),
                                        tempValues,
                                        task)
                        ));
            }
            case "СКДО": {

            }
        }
    }
//    private void checkLacuna(int lacuna) {
//        System.out.println("RAZMER " + commands.getObjectList().size());
//        System.out.println(commands.getObjectList().get(1));
//        long lacunaBuffer = ((SuperClass) commands.getObjectList().get(0)).getDate() - lacuna;
//
//        for (Object line : commands.getObjectList()) {
//            if (lacuna == ((SuperClass) line).getDate() - lacunaBuffer)
//                lacunaBuffer = ((SuperClass) line).getDate();
//            else {
//                superClassTemp.setStable(0);
//                superClassTemp.setLacuna((int) (((SuperClass) line).getDate() - lacunaBuffer));
//                superClassTemp.setStartTime(((SuperClass) line).getDate());
//                superClassTemp.setRelFreqDiff("");
//                superClassTemp.setPhaseDiff("");
//                commands.updateObjectToDb(superClassTemp);
//                listCut.resultCut(commands.getObjectList().indexOf(line));
//                break;
//            }
//
//            superClassTemp.setStable(1);
//            superClassTemp.setLacuna(lacuna);
//            superClassTemp.setStartTime(((SuperClass) (commands.getObjectList()).get(commands.getObjectList().size() - 1)).getDate());
//            commands.updateObjectToDb(superClassTemp);
//        }
//
//    }
}



