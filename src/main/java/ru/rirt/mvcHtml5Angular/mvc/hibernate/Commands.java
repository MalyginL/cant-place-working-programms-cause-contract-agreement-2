package ru.rirt.mvcHtml5Angular.mvc.hibernate;


import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rirt.mvcHtml5Angular.mvc.TaskSolve.ResultUploader;
import ru.rirt.mvcHtml5Angular.mvc.bean.EtalonUnstable;
import ru.rirt.mvcHtml5Angular.mvc.bean.SuperClass;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTable;
import ru.rirt.mvcHtml5Angular.mvc.bean.TaskTempValues;


import java.math.BigDecimal;
import java.util.*;

/**
 * Created by homePC on 27.07.2017.
 */
@Service
public class Commands {
    @Autowired
    ResultUploader resultUploader;

    @Autowired
    Unstable unstable;

    List<SuperClass> resultList;

    private String currtemp;
    private String temp;
    private String temp2;
    private String currtemp2;

    public Commands() {
        resultList = Collections.synchronizedList(new ArrayList<SuperClass>());
    }


    @Autowired
    SessionFactory sessionFactory;

    public void addObjectToDb(Object object) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        statelessSession.insert(object);
        statelessSession.getTransaction().commit();
        statelessSession.close();
    }

    public void updateObjectToDb(Object object) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        statelessSession.update(object);
        statelessSession.getTransaction().commit();
        statelessSession.close();
    }

    public void addArraytoDb(List objectList) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        objectList.forEach(object -> statelessSession.update(object));
        statelessSession.getTransaction().commit();
        statelessSession.close();
    }


    public synchronized List<SuperClass> getRawData(TaskTempValues tempValues, TaskTable task) {
        System.out.println("Получение исходных данных из базы");
        resultList.clear();
        //   List<SuperClass> resultList = Collections.synchronizedList(new ArrayList<SuperClass>());
        ScrollableResults scrollableResults;
        long lacuna;
        SuperClass temp;
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();

        scrollableResults = statelessSession.createQuery(String.format("FROM %s WHERE channel=%s AND date>=%s ORDER BY date ASC",
                task.getTableName(), task.getChannel(), tempValues.getProgramTime())).scroll(ScrollMode.FORWARD_ONLY);

        if (tempValues.getStable() == 0) {
            scrollableResults.next();
            temp = ((SuperClass) scrollableResults.get()[0]);
            resultList.add(temp);
            System.out.println("tyt");
            try {
               // System.out.println(":::" + temp.getDate());
                scrollableResults.next();
                lacuna = (((SuperClass) scrollableResults.get()[0]).getDate()) - temp.getDate();
                tempValues.setLacuna((int) lacuna);
                temp = ((SuperClass) scrollableResults.get()[0]);
                resultList.add(temp);
               // System.out.println(":::" + temp.getDate());
            } catch (NullPointerException ex) {
                System.out.println("null ex in raw");
                return null;
            }
        } else {
            scrollableResults.next();
            temp = ((SuperClass) scrollableResults.get()[0]);
            resultList.add(temp);
            lacuna = tempValues.getLacuna();
        }
        while (scrollableResults.next()) {
            if ((((SuperClass) scrollableResults.get()[0]).getDate() - temp.getDate()) == lacuna) {
                resultList.add(((SuperClass) scrollableResults.get()[0]));
                System.out.println("dobavil + " + ((SuperClass) scrollableResults.get()[0]).getDate());
                temp = (((SuperClass) scrollableResults.get()[0]));
                System.out.println(":::" + ((SuperClass) scrollableResults.get()[0]).getDate());
            } else {
                tempValues.setStable(0);
                System.out.println("v table ushlo" + ((SuperClass) scrollableResults.get()[0]).getDate());
                tempValues.setProgramTime(((SuperClass) scrollableResults.get()[0]).getDate());
                updateObjectToDb(tempValues);
                return resultList;
            }

        }
        System.out.println("SIZE" + resultList.size());
      //  resultList.forEach(o -> System.out.println("oppa" + o.getDate()));
        tempValues.setStable(1);
        tempValues.setProgramTime(temp.getDate() - tempValues.getLacuna());
        System.out.println("raznica" + (temp.getDate() - tempValues.getLacuna()));
        updateObjectToDb(tempValues);
        System.out.println("v table ushlo" + temp.getDate());
        return resultList;

    }


//    public synchronized void getRawData(String tablename, String channel, String startDate, String endDate) {
//        //   objectList.clear();
//        StatelessSession statelessSession = sessionFactory.openStatelessSession();
//        statelessSession.beginTransaction();
//        ScrollableResults scrollableResults = statelessSession.createQuery(String.format("FROM Etalon WHERE channel=%s AND date>%s  AND date <=%s ORDER BY id", channel, startDate, endDate)).scroll(ScrollMode.FORWARD_ONLY);
//
//
//        while (scrollableResults.next()) {
//            objectList.add(scrollableResults.get()[0]);
//            System.out.println(getObjectList().get(0));
//        }
//        statelessSession.getTransaction().commit();
//        statelessSession.close();
//    }

//    public synchronized void getRawData(String tablename, String channel, String startDate) {
//        //   objectList.clear();
//        StatelessSession statelessSession = sessionFactory.openStatelessSession();
//        statelessSession.beginTransaction();
//        ScrollableResults scrollableResults = statelessSession.createQuery(String.format("FROM Etalon WHERE channel=%s AND date>%s  ORDER BY id", channel, startDate)).scroll(ScrollMode.FORWARD_ONLY);
//        while (scrollableResults.next())
//            objectList.add(scrollableResults.get()[0]);
//        statelessSession.getTransaction().commit();
//        statelessSession.close();
//    }

    public SuperClass getLine(String tableName, long date, int channel) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        SuperClass result = (SuperClass) statelessSession.createQuery(String.format("FROM %s WHERE date=%s AND channel=%s ORDER BY id", tableName, date, channel)).uniqueResult();
        statelessSession.getTransaction().commit();
        statelessSession.close();
        return result;
    }

    public TaskTempValues getTempValue(int id) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        TaskTempValues result = (TaskTempValues) statelessSession.get(TaskTempValues.class, id);
        statelessSession.getTransaction().commit();
        statelessSession.close();
        return result;
    }


    public Object getLine(String tableName, int id) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        Object result = null;
        //   try {
        System.out.println("ru.rirt.mvcHtml5Angular.mvc.bean." + tableName + ".class");

        //   result = statelessSession.get((EtalonTemp.class), id);
        //   result = statelessSession.get(Class.forName("ru.rirt.mvcHtml5Angular.mvc.bean."+tableName+".class"),id);

        // } catch (ClassNotFoundException e) {
        //LOGGER
        //   }
        statelessSession.getTransaction().commit();
        statelessSession.close();
        return result;
    }


//    public List<SuperClass> getObjectList() {
//        return objectList;
//    }

    public synchronized List<SuperClass> cmdPhase_diff(List<SuperClass> data) {
   //     System.out.println("В блок обработки разности фаз добавлено " + data.size() + " элементов");
        data.forEach(result -> {
            result.setPhaseDiff(MathComparator.getPhase_diff(result.getPhase(), "1000000").toString());
            resultUploader.addResultForUpload(result);
        });
        return data;
    }

    public synchronized List<SuperClass> cmdRelFreq_diff(List<SuperClass> data, TaskTempValues tempValues, TaskTable task) {
        try {
            SuperClass superClass;
            //Для досчитывания
            //   if (tempValues.getStable() == 1) {
            //       superClass = data.get(0);
            //  superClass = getLine(task.getTableName(), tempValues.getProgramTime(), task.getChannel());
            // data.add(0, superClass);
            // } else {
            superClass = data.get(0);
            //  }
            data.forEach(result -> result.setPhaseDiff(MathComparator.getPhase_diff(result.getPhase(), "1000000").toString()));
            for (SuperClass item : data.subList(1, data.size())) {
                superClass.setRelFreqDiff((MathComparator.getrelFreq_diff(superClass.getPhaseDiff(), item.getPhaseDiff(), String.valueOf(tempValues.getLacuna()))).toString());
                //  resultUploader.addResultForUpload(superClass);
                superClass = item;
            }

            //   resultUploader.addResultForUpload(superClass);
            // return data;
        } catch (ArrayIndexOutOfBoundsException ex) {
            //  return data;
        } catch (NullPointerException ex) {
            // return data;
        } finally {
         //   System.out.println(data.get(data.size() - 1).getDate());
            return data;
        }

    }

    public synchronized List<SuperClass> cmdCurrVarRelFreq_diff(List<SuperClass> data) {


        SuperClass temp = null;

        for (SuperClass item : data) {
            try {
                System.out.println(item.getRelFreqDiff());
                temp.setCurVarRelFreqDiff(MathComparator.getcurrVarRelFreq_diff(temp.getRelFreqDiff(), item.getRelFreqDiff()).toString());

            } catch (NullPointerException ex) {
            //    System.out.println("ha-ha");
            }
            temp = item;
        }
        return data;
    }


    public long getRawCountForSelect(long start, int end, String tableName, int channel) {
        long result = 0L;
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        ScrollableResults scrollableResults = statelessSession.createQuery(String.format("select count(*) from %s where date>%s and date<%s and channel=%s", tableName, start, end, channel)).scroll(ScrollMode.FORWARD_ONLY);
        while (scrollableResults.next())
            result = (long) scrollableResults.get()[0];

        statelessSession.getTransaction().commit();
        statelessSession.close();
        System.out.println("getRawCountForSelect Result = " + result);
        return result;
    }

    //-----------------------------------------------ETALON NESTABILNOST------------------------------------------

    public void removeUnstableChannel(int channel) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        Query query = statelessSession.createQuery(String.format("delete UnstableDevices where channel='%s'", channel));
        query.executeUpdate();
        statelessSession.getTransaction().commit();
        statelessSession.close();
    }


    public void updateSkdoDataFromDB(List<Integer> channels) {
        unstable.getResultList().clear();
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        for (int channel : channels) {
            statelessSession.beginTransaction();
            ScrollableResults scrollableResults = statelessSession.createQuery(String.format("From EtalonUnstable where channel=%s ORDER BY id", channel)).scroll(ScrollMode.FORWARD_ONLY);
            while (scrollableResults.next())
                synchronized (unstable.getResultList()) {

                    unstable.getResultList().add((EtalonUnstable) scrollableResults.get()[0]);
                }
            statelessSession.getTransaction().commit();
        }
        statelessSession.close();
    }


    public List<SuperClass> getDataForUnstable(int startTime, int endTime, int channel) {
        System.out.println("Start" + startTime);
        System.out.println("end" + endTime);
        System.out.println("ch"+channel);
        List<SuperClass> result = new ArrayList<>();
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        ScrollableResults scrollableResults = statelessSession.createQuery(String.format("from Etalon Where channel=%s and date>=%s and date<%s ORDER BY Date ASC", channel, startTime, endTime)).scroll(ScrollMode.FORWARD_ONLY);

        while (scrollableResults.next()) {

            result.add((SuperClass) scrollableResults.get()[0]);
        }
        statelessSession.getTransaction().commit();
        statelessSession.close();

        return result;

    }

    public String avg(int timestart, int timeend, int channel) {
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        statelessSession.beginTransaction();
        int count = 0;
        BigDecimal Summ = new BigDecimal("0");
        ScrollableResults scrollableResults = statelessSession.createQuery(String.format("from Etalon Where channel=%s and date>%=s and time<%s", channel, timestart, timeend)).scroll(ScrollMode.FORWARD_ONLY);
        while (scrollableResults.next()) {
            Summ = Summ.add(new BigDecimal((String) scrollableResults.get()[0]));
            count++;
        }
        statelessSession.getTransaction().commit();
        statelessSession.close();
        return (Summ).divide(new BigDecimal(String.valueOf(count)), 40, BigDecimal.ROUND_CEILING).toString();
    }

}
