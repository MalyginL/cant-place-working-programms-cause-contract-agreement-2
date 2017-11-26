package ru.rirt.mvcHtml5Angular.mvc.TaskSolve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.bean.EtalonUnstable;
import ru.rirt.mvcHtml5Angular.mvc.bean.HistoryChannels;
import ru.rirt.mvcHtml5Angular.mvc.bean.ResultUnstable;
import ru.rirt.mvcHtml5Angular.mvc.bean.SuperClass;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Commands;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.MathComparator;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Repository;
import ru.rirt.mvcHtml5Angular.mvc.hibernate.Unstable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Leonid Malygin on 17.08.2017.
 * lenyamalygin@gmail.com
 */


@Component
public class UnstableTask {

    String sigma;

    @Autowired
    Unstable unstable;

    @Autowired
    Commands commands;

    @Autowired
    MathComparator mathComparator;

    // @Scheduled(fixedRate = 900000, initialDelay = 60000)
    public void start() {

        unstable.getUnstableCorrecion().clear();
        unstable.getUnstableCurrentTime().clear();
        unstable.getUnstableSKOTable().clear();
        unstable.getUnstableDefectA().clear();
        unstable.getUnstableSucessA().clear();
        unstable.getUnstableDefectB().clear();
        unstable.getUnstableSucessB().clear();


        LinkedHashMap<String, String> usrednennie_rezulatati = new LinkedHashMap<>();
        LinkedHashMap<String, String> mnc;
        LinkedHashMap<String, String> brak = new LinkedHashMap<>();
        LinkedHashMap<String, String> norm = new LinkedHashMap<>();
        // int currenttime = 1122249614;
        int currenttime = ((int) (System.currentTimeMillis() / 1000L)) - 382838386;
        BigDecimal result;
        BigDecimal itogovayapopravka = new BigDecimal("0");
        int k = 0;
        //2 minuti zaderzhka
        for (int channel : unstable.getWorkingChannels()) {
            k++;
            work:
            {
                int num = (int) (unstable.getTime_full() / unstable.getTime_part());
                unstable.getUnstableCurrentTime().put(channel, currenttime);
                for (int i = 0; i < num; ++i) {
                    try {
                        int tempTime = currenttime - unstable.getTime_full() + i * unstable.getTime_part();
//                        if (unstable.getTime_part() > 900) {
//                            //усредняем по мнк
//                            LinkedHashMap<String, String> rez = MathComparator.mnc(commands.getDataForUnstable(tempTime, tempTime + unstable.getTime_part(), channel), tempTime);
//                            //  result = ((new BigDecimal(String.valueOf((int) ((unstable.getTime_part()
//                            result = ((new BigDecimal(String.valueOf((unstable.getTime_part() / 2)))).multiply(new BigDecimal(rez.get("a")))).add(new BigDecimal(rez.get("b")));
//                        } else {
                            //усредняем по сред арфим
                            int j = 0;
                            BigDecimal Summ = new BigDecimal("0");
                            for (SuperClass item : commands.getDataForUnstable(tempTime, tempTime + unstable.getTime_part(), channel)) {
                                Summ = Summ.add(new BigDecimal(item.getRelFreqDiff()));
                                j++;
                            }

                            result = Summ.divide(new BigDecimal(String.valueOf(j)), 40, BigDecimal.ROUND_CEILING);
                     //   }
                        System.out.println("RESULT" + result);
                        usrednennie_rezulatati.put(String.valueOf((int) (i * unstable.getTime_part() + (unstable.getTime_part() / 2))), result.toString());
                        ;


                        // usrednennie_rezulatati.put(String.valueOf((int) (tempTime + (unstable.getTime_part() / 2))), result.toString());
                    } catch (Exception ex) {
                        System.out.println("null v vibore unstable" + ex);
                    }
                    if (usrednennie_rezulatati.size() == 0) {
                        unstable.getUnstableCorrecion().put(channel, 0F);
                        unstable.getUnstableDefectB().put(channel, 0F);
                        unstable.getUnstableDefectA().put(channel, 0F);
                        unstable.getUnstableSKOTable().put(channel, 0F);
                        unstable.getUnstableSucessA().put(channel, 0F);
                        unstable.getUnstableSucessB().put(channel, 0F);
                        break work;
                    }

                }
                if (unstable.getSigma() == 1) {

                    sigma = MathComparator.skdo(usrednennie_rezulatati).toString();
                } else {
                    sigma = MathComparator.sko(usrednennie_rezulatati).toString();
                }
//                switch (unstable.getSigma()) {
//                    case 1: {
//                        String sigma = MathComparator.skdo(usrednennie_rezulatati).toString();
//                        break;
//                    }
//                    default: {
//                        String sigma = MathComparator.sko(usrednennie_rezulatati).toString();
//                    }
//                }
                unstable.getUnstableSKOTable().put(channel, Float.valueOf(sigma));
                mnc = MathComparator.mnc(usrednennie_rezulatati);
                for (Map.Entry<String, String> entry : usrednennie_rezulatati.entrySet()) {
                    if (
                            (((new BigDecimal(entry.getValue())).
                                    subtract(
                                            (new BigDecimal(mnc.get("a")).multiply(new BigDecimal(entry.getKey()), MathContext.UNLIMITED))
                                                    .add(new BigDecimal(mnc.get("b")))
                                    )).abs())
                                    .compareTo(new BigDecimal("3").multiply(new BigDecimal(sigma))) == -1
                            ) {
                        norm.put(entry.getKey(), entry.getValue());
//                        System.out.println("Время " + item.getKey() + "подошло под 3 ско");
//                        System.out.println("Потому что разность = " +
//
//                                (((new BigDecimal(item.getValue())).
//                                        subtract(
//                                                (new BigDecimal(mnc.get("a")).multiply(new BigDecimal(item.getKey()), MathContext.UNLIMITED))
//                                                        .add(new BigDecimal(mnc.get("b")))
//                                        )).abs()).subtract((new BigDecimal("3").multiply(new BigDecimal(sko))))
//
//                        );
                    } else {
                        brak.put(entry.getKey(), entry.getValue());
                    }
                }
                if (brak.size() > 1) {
                    LinkedHashMap brakmap = MathComparator.mnc(brak);
                    BigDecimal brakRez = (
                            new BigDecimal(String.valueOf(brakmap.get("a"))).
                                    multiply(new BigDecimal(String.valueOf(unstable.getTime_full())), MathContext.UNLIMITED)).
                            subtract(new BigDecimal(String.valueOf(brakmap.get("a"))));
                    unstable.getUnstableDefectCorection().put(channel, Float.valueOf(brakRez.toString()));
                    unstable.getUnstableDefectA().put(channel, Float.valueOf(brakmap.get("a").toString()));
                    unstable.getUnstableDefectB().put(channel, Float.valueOf(brakmap.get("b").toString()));
                } else {
                    unstable.getUnstableDefectA().put(channel, 0F);
                    unstable.getUnstableDefectB().put(channel, 0F);
                }

                if (norm.size() > 1) {
                    LinkedHashMap normmap = MathComparator.mnc(norm);
                    BigDecimal normRez = (new BigDecimal(String.valueOf(normmap.get("a"))).
                            multiply(new BigDecimal(String.valueOf(unstable.getTime_full())),
                                    MathContext.UNLIMITED)).subtract(new BigDecimal(String.valueOf(normmap.get("a"))));
                    unstable.getUnstableCorrecion().put(channel, Float.valueOf(normRez.toString()));
                    unstable.getUnstableSucessA().put(channel, Float.valueOf(normmap.get("a").toString()));
                    unstable.getUnstableSucessB().put(channel, Float.valueOf(normmap.get("b").toString()));
                    itogovayapopravka = itogovayapopravka.add(normRez);
                } else {
                    unstable.getUnstableSucessA().put(channel, 0F);
                    unstable.getUnstableSucessB().put(channel, 0F);
                }

            }
            HistoryChannels historyChannels = new HistoryChannels();
            historyChannels.setDate(unstable.getUnstableCurrentTime().get(channel));
            historyChannels.setChannel(channel);
            historyChannels.setA(unstable.getUnstableSucessA().get(channel).toString());
            historyChannels.setaBrak(unstable.getUnstableDefectA().get(channel).toString());
            historyChannels.setB(unstable.getUnstableSucessB().get(channel).toString());
            historyChannels.setbBrak(unstable.getUnstableDefectB().get(channel).toString());
            commands.addObjectToDb(historyChannels);

        }
        System.out.println(k);

        System.out.println(itogovayapopravka.toString());
        itogovayapopravka = (itogovayapopravka.divide(new BigDecimal(String.valueOf(k)), 40, RoundingMode.HALF_UP).setScale(40, RoundingMode.CEILING)).negate();
        unstable.setItogovayaPopravka(itogovayapopravka.toString());
        ResultUnstable resultUnstable = new ResultUnstable();
        resultUnstable.setChannels(unstable.getWorkingChannelsToString());
        resultUnstable.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date( )));
        unstable.setCurrTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date( )));
        resultUnstable.setResult(itogovayapopravka.toString());
        commands.addObjectToDb(resultUnstable);

    }


}
