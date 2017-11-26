package ru.rirt.mvcHtml5Angular.mvc.hibernate;

import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.bean.EtalonUnstable;
import ru.rirt.mvcHtml5Angular.mvc.bean.UnstableDevices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Leonid Malygin on 17.08.2017.
 * lenyamalygin@gmail.com
 */
@Component
public class Unstable {
    private int time_part;
    private int time_full;
    private String itogovayaPopravka;

    private List<Integer> WorkingChannels;
    private List<EtalonUnstable> ResultList;
    private int sigma;
    private String CurrTime;

    private HashMap<Integer, Integer> unstableCurrentTime;
    private HashMap<Integer, Float> unstableSKOTable;
    private HashMap<Integer, Float> unstableDefectA;
    private HashMap<Integer, Float> unstableDefectB;
    private HashMap<Integer, Float> unstableSucessA;
    private HashMap<Integer, Float> unstableSucessB;
    private HashMap<Integer, Float> unstableCorrecion;
    private HashMap<Integer, Float> unstableDefectCorection;

    public Unstable() {
        unstableDefectCorection = new HashMap<>();
        unstableSKOTable = new HashMap<>();
        unstableCurrentTime = new HashMap<>();
        unstableDefectB = new HashMap<>();
        unstableSucessB = new HashMap<>();
        unstableDefectA = new HashMap<>();
        unstableSucessA = new HashMap<>();
        unstableCorrecion = new HashMap<>();
        WorkingChannels = Collections.synchronizedList(new ArrayList<Integer>());
        ResultList = Collections.synchronizedList(new ArrayList<EtalonUnstable>());
    }

    public String getWorkingChannelsToString(){
        StringBuilder sb = new StringBuilder();
        WorkingChannels.forEach(channel-> sb.append(" " + channel));
        return  sb.toString();
    }

    public String getCurrTime() {
        return CurrTime;
    }

    public void setCurrTime(String currTime) {
        CurrTime = currTime;
    }

    public String getItogovayaPopravka() {
        return itogovayaPopravka;
    }

    public void setItogovayaPopravka(String itogovayaPopravka) {
        this.itogovayaPopravka = itogovayaPopravka;
    }

    public int getSigma() {
        return sigma;
    }

    public void setSigma(int sigma) {
        this.sigma = sigma;
    }

    public HashMap<Integer, Float> getUnstableDefectCorection() {
        return unstableDefectCorection;
    }

    public void setUnstableDefectCorection(HashMap<Integer, Float> unstableDefectCorection) {
        this.unstableDefectCorection = unstableDefectCorection;
    }

    public HashMap<Integer, Integer> getUnstableCurrentTime() {
        return unstableCurrentTime;
    }

    public void setUnstableCurrentTime(HashMap<Integer, Integer> unstableCurrentTime) {
        this.unstableCurrentTime = unstableCurrentTime;
    }

    public HashMap<Integer, Float> getUnstableSKOTable() {
        return unstableSKOTable;
    }

    public void setUnstableSKOTable(HashMap<Integer, Float> unstableSKOTable) {
        this.unstableSKOTable = unstableSKOTable;
    }

    public HashMap<Integer, Float> getUnstableDefectA() {
        return unstableDefectA;
    }

    public void setUnstableDefectA(HashMap<Integer, Float> unstableDefectA) {
        this.unstableDefectA = unstableDefectA;
    }

    public HashMap<Integer, Float> getUnstableDefectB() {
        return unstableDefectB;
    }

    public void setUnstableDefectB(HashMap<Integer, Float> unstableDefectB) {
        this.unstableDefectB = unstableDefectB;
    }

    public HashMap<Integer, Float> getUnstableSucessA() {
        return unstableSucessA;
    }

    public void setUnstableSucessA(HashMap<Integer, Float> unstableSucessA) {
        this.unstableSucessA = unstableSucessA;
    }

    public HashMap<Integer, Float> getUnstableSucessB() {
        return unstableSucessB;
    }

    public void setUnstableSucessB(HashMap<Integer, Float> unstableSucessB) {
        this.unstableSucessB = unstableSucessB;
    }

    public HashMap<Integer, Float> getUnstableCorrecion() {
        return unstableCorrecion;
    }

    public void setUnstableCorrecion(HashMap<Integer, Float> unstableCorrecion) {
        this.unstableCorrecion = unstableCorrecion;
    }

    public List<Integer> getWorkingChannels() {
        return WorkingChannels;
    }

    public int getTime_part() {
        return time_part;
    }

    public void setTime_part(int time_part) {
        this.time_part = time_part;
    }

    public int getTime_full() {
        return time_full;
    }

    public void setTime_full(int time_full) {
        this.time_full = time_full;
    }

    public void setWorkingChannels(List<Integer> workingChannels) {
        WorkingChannels = workingChannels;
    }

    public List<EtalonUnstable> getResultList() {
        return ResultList;
    }

    public void setResultList(List<EtalonUnstable> resultList) {
        ResultList = resultList;
    }
}
