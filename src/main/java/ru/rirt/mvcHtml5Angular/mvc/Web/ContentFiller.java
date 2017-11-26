package ru.rirt.mvcHtml5Angular.mvc.Web;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.rirt.mvcHtml5Angular.mvc.bean.*;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Repository;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Unstable;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by Leonid Malygin on 27.07.2017.
 * lenyamalygin@gmail.com
 */
@Component
public class ContentFiller {
    @Autowired
    Repository repository;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    Unstable unstable;

    @Scheduled(fixedRate = 60000, initialDelay = 10000)

    public void getLogCount() {
        System.out.println("TIME = " +  System.currentTimeMillis());
        try {
            StatelessSession statelessSession = sessionFactory.openStatelessSession();
            statelessSession.beginTransaction();
            repository.setTaskLogStatus(((long) statelessSession.createCriteria(TaskTable.class).setProjection(Projections.rowCount()).uniqueResult()));
            statelessSession.getTransaction().commit();
            statelessSession.close();
            repository.setDataBaseStatus(true);
        } catch (org.hibernate.exception.JDBCConnectionException ex) {
            //ADD LOGGER
            repository.setDataBaseStatus(false);
            repository.setTaskLogStatus(-1);
        }
    }

    @PostConstruct
    public void addPastTasks() {
        ScrollableResults scrollableResults;
        try {
            StatelessSession statelessSession = sessionFactory.openStatelessSession();
            statelessSession.beginTransaction();
            scrollableResults = statelessSession.createQuery("FROM TaskTable  ORDER BY finishTime ASC").scroll(ScrollMode.FORWARD_ONLY);
            while (scrollableResults.next()) {
                repository.getTaskLogList().add(0, (TaskTable) scrollableResults.get()[0]);
            }
            statelessSession.getTransaction().commit();

            statelessSession.beginTransaction();
            scrollableResults = statelessSession.createQuery("FROM TaskTable WHERE status = 2 ORDER BY finishTime ASC").scroll(ScrollMode.FORWARD_ONLY);
            while (scrollableResults.next()) {
                TaskTable temp = (TaskTable) scrollableResults.get()[0];
                repository.getTaskList().add(0, temp);
            }
            statelessSession.getTransaction().commit();
            statelessSession.beginTransaction();
            scrollableResults = (statelessSession.createQuery("FROM Config WHERE version='release'").scroll(ScrollMode.FORWARD_ONLY));
            if (scrollableResults.next())
                repository.setLastTaskID(((Config) scrollableResults.get()[0]).getLastTaskId());
            System.out.println(repository.getLastTaskID());
            statelessSession.getTransaction().commit();
            statelessSession.close();
        } catch (org.hibernate.exception.JDBCConnectionException ex) {
            //ADD LOGGER
        }
    }


    public int updateLastId() {
        int id = -1;
        try {

            StatelessSession statelessSession = sessionFactory.openStatelessSession();
//            statelessSession.beginTransaction();
//            ScrollableResults scrollableResults =  (statelessSession.createQuery("FROM Config WHERE version='release'").scroll(ScrollMode.FORWARD_ONLY));
//            if (scrollableResults.next())
//                id  = (((Config) scrollableResults.get()[0]).getLastTaskId());
//            statelessSession.getTransaction().commit();
            id = repository.getLastTaskID();
            statelessSession.beginTransaction();
            Query query = statelessSession.createQuery(String.format("update Config set lastTaskID=%s WHERE version ='release'", id + 1));
            query.executeUpdate();
            statelessSession.getTransaction().commit();
            statelessSession.close();
            repository.setLastTaskID(id + 1);
        } catch (org.hibernate.exception.JDBCConnectionException ex) {
        }
        return id;
    }

    @PostConstruct
    public void getWorkingChannelsFromDb() {
        try {
            StatelessSession statelessSession = sessionFactory.openStatelessSession();
            statelessSession.beginTransaction();
            ScrollableResults scrollableResults = (statelessSession.createQuery("from UnstableDevices").scroll(ScrollMode.FORWARD_ONLY));
            while (scrollableResults.next())
                unstable.getWorkingChannels().add(((UnstableDevices) scrollableResults.get()[0]).getChannel());
            statelessSession.getTransaction().commit();
            statelessSession.close();
        } catch (org.hibernate.exception.JDBCConnectionException ex) {
            //add logger
        }
    }
}

//    public int getLineCountForSelect(int start, int end, String tableName) {
//        StatelessSession statelessSession = sessionFactory.openStatelessSession();
//        statelessSession.beginTransaction();
//        return ((Integer) statelessSession.createQuery(String.format("select count(*) from %s login where startTime>%d and finishTime<%d", tableName, start, end)).scroll(ScrollMode.FORWARD_ONLY).get()[0]);
//    }
//}




