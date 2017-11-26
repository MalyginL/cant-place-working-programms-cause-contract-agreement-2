package ru.rirt.mvcHtml5Angular.mvc.hibernate;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;
import ru.rirt.mvcHtml5Angular.mvc.bean.SuperClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Leonid Malygin on 31.07.2017.
 * lenyamalygin@gmail.com
 */
@Component
public class MathComparator {

    public static BigDecimal getskdo(List<String> CurrVarRelFreq_diff) {
        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("2");
        BigDecimal summ = new BigDecimal("0");

        for (String currVarRelFreq_diff : CurrVarRelFreq_diff) {
            summ = new BigDecimal(currVarRelFreq_diff).add(summ).pow(2);
        }
        System.out.println(summ);
        BigDecimal temp = new BigDecimal(CurrVarRelFreq_diff.size()).subtract(one);
        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
        BigDecimal result = sqrt(one.divide((temp.multiply(two)), 200, RoundingMode.HALF_UP).multiply(summ), mc).setScale(40, RoundingMode.CEILING);
        return result;
    }

    public static BigDecimal getrelFreq_diff(String phaseCurr_diff, String phaseNext_diff, String tau_time) {
        BigDecimal result = (new BigDecimal(phaseNext_diff)
                .subtract
                        (new BigDecimal(phaseCurr_diff)))
                .divide
                        (new BigDecimal(tau_time), BigDecimal.ROUND_HALF_UP)
                .setScale(40, RoundingMode.CEILING);

        return result;
    }

    public static BigDecimal getPhase_diff(String phase, String k) {
        BigDecimal result = (new BigDecimal(phase).divide(new BigDecimal(k))).setScale(40, RoundingMode.CEILING);
        return result;
    }

    public static BigDecimal getcurrVarRelFreq_diff(String curr, String next) {
//        System.out.println("CURR" + curr);
//        System.out.println("NEXT" + next);
        BigDecimal result;
        try {
            result = (new BigDecimal(next).subtract(new BigDecimal(curr)).setScale(40, RoundingMode.CEILING));
        } catch (NumberFormatException ex) {
            return null;
        }
        return result;
    }


    static public BigDecimal sqrt(final BigDecimal x, final MathContext mc) {
        if (x.compareTo(BigDecimal.ZERO) < 0)
            throw new ArithmeticException("negative argument " + x.toString() + " of square root");
        if (x.abs().subtract(new BigDecimal(Math.pow(10., -mc.getPrecision()))).compareTo(BigDecimal.ZERO) < 0)
            return scalePrec(BigDecimal.ZERO, mc.getPrecision());
        BigDecimal s = new BigDecimal(Math.sqrt(x.doubleValue()), mc);
        final BigDecimal half = new BigDecimal("2");
        MathContext locmc = new MathContext(mc.getPrecision() + 2, mc.getRoundingMode());
        final double eps = Math.pow(10.0, -mc.getPrecision());
        for (; ; ) {
            if (Math.abs(BigDecimal.ONE.subtract(x.divide(s.pow(2, locmc), locmc)).doubleValue()) < eps)
                break;
            s = s.add(x.divide(s, locmc)).divide(half, locmc);
        }
        return s;
    }


    static public BigDecimal scalePrec(final BigDecimal x, int d) {
        return x.setScale(d + x.scale());
    }


    public static BigDecimal sko(HashMap<String, String> map) throws NullPointerException {
        BigDecimal temp = new BigDecimal("0");
        BigDecimal summ = new BigDecimal("0");
        BigDecimal sko;
        for (HashMap.Entry<String,String> item : map.entrySet()) {
            temp = temp.add(new BigDecimal(item.getValue()));
        }
        temp = temp.divide(new BigDecimal(String.valueOf(map.size())), 40, RoundingMode.CEILING).setScale(40, RoundingMode.CEILING);
        System.out.println("sko temp" + temp);
        for (HashMap.Entry<String,String> item : map.entrySet()) {
            summ = summ.add((new BigDecimal(item.getValue()).subtract(temp)).pow(2, MathContext.UNLIMITED));
        }
        System.out.println("sko sum" + summ);
        System.out.println("size" + map.size());
        summ = summ.divide(new BigDecimal(String.valueOf(map.size()-1)), 100, RoundingMode.CEILING);
        System.out.println("summ2"+summ);
        sko= sqrtBabylon(summ,70);
        //sko = sqrt(summ.divide(new BigDecimal(String.valueOf(map.size())), 100, RoundingMode.CEILING), MathContext.UNLIMITED);
        System.out.println(sko.toString());
        return sko;
    }

    public static BigDecimal sko(List<SuperClass> list) {
        BigDecimal temp = new BigDecimal("0");
        BigDecimal summ = new BigDecimal("0");
        BigDecimal sko;
        for (SuperClass result : list) {
            temp = temp.add(new BigDecimal(result.getCurVarRelFreqDiff()));
        }
        temp = temp.divide(new BigDecimal(String.valueOf(list.size())), 40, RoundingMode.CEILING).setScale(40, RoundingMode.CEILING);
        for (SuperClass result : list) {
            summ = summ.add((new BigDecimal(result.getCurVarRelFreqDiff()).subtract(temp)).pow(2, MathContext.UNLIMITED));
        }
        sko = sqrt(summ.divide(new BigDecimal(String.valueOf(list.size())), 40, RoundingMode.CEILING), MathContext.UNLIMITED);
        System.out.println(sko.toString());
        return sko;
    }


    public static BigDecimal skdo (LinkedHashMap<String, String> map) throws NullPointerException{
        BigDecimal summ = new BigDecimal("0");
        BigDecimal skdo;
        BigDecimal temp = new BigDecimal("0");
        for (Map.Entry<String,String> item : map.entrySet()) {
            if (!temp.toString().equals("0")) {
                summ = summ.add(((new BigDecimal(item.getValue()).subtract(temp))).pow(2, MathContext.UNLIMITED));
            }
            temp = new BigDecimal(item.getValue());
        }
        System.out.println("summ1" + summ);
        summ = summ.divide(new BigDecimal(String.valueOf(map.size()-1)).multiply(new BigDecimal("2")), 100, RoundingMode.CEILING);
        System.out.println("summ skdo" + summ);

        skdo = sqrtBabylon(summ,70);
        return skdo;
    }




    public static BigDecimal sqrtBabylon(BigDecimal in, int scale){
//        System.out.println("IN TO STRING"+in.toString());
//        System.out.println("SCALE" + scale);
        BigDecimal sqrt = new BigDecimal(1);
        sqrt.setScale(scale + 3, RoundingMode.FLOOR);
        BigDecimal store = new BigDecimal(in.toString());
        boolean first = true;
        do{
            if (!first){
                store = new BigDecimal(sqrt.toString());
            }
            else first = false;
            store.setScale(scale + 3, RoundingMode.FLOOR);
            sqrt = in.divide(store, scale + 3, RoundingMode.FLOOR).add(store).divide(
                    BigDecimal.valueOf(2), scale + 3, RoundingMode.FLOOR);
        }while (!store.equals(sqrt));
        return sqrt.setScale(scale, RoundingMode.FLOOR);
    }





    public static LinkedHashMap<String, String> mnc(List<SuperClass> rawdata,int time) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        BigDecimal sumxy = new BigDecimal("0");
        BigDecimal sumx = new BigDecimal("0");
        BigDecimal sumy = new BigDecimal("0");
        BigDecimal sumx2 = new BigDecimal("0");
        BigDecimal num = new BigDecimal(String.valueOf(rawdata.size()));
        try {
            for (SuperClass item : rawdata) {
                sumxy = sumxy.add(new BigDecimal(String.valueOf(item.getDate()-time)).multiply(new BigDecimal(item.getRelFreqDiff()), MathContext.UNLIMITED));
                sumx = sumx.add(new BigDecimal(String.valueOf(item.getDate()-time)));
                sumy = sumy.add(new BigDecimal(item.getRelFreqDiff()));
                sumx2 = sumx2.add(new BigDecimal(String.valueOf(item.getDate()-time)).pow(2, MathContext.UNLIMITED));
            }
            BigDecimal a = new BigDecimal(((num.multiply(sumxy, MathContext.UNLIMITED)).subtract(sumx.multiply(sumy, MathContext.UNLIMITED)))
                    .divide(num.multiply(sumx2, MathContext.UNLIMITED).subtract(sumx.pow(2)), 40, RoundingMode.CEILING).toString());
            BigDecimal b = new BigDecimal((sumy.subtract(a.multiply(sumx, MathContext.UNLIMITED))).divide(num, 40, RoundingMode.CEILING).toString());
            System.out.println("--------------------------РАСЧЕТ МНК ДЛЯ МАЛЕНЬКИХ ПРОМЕЖУТКОВ");
            resultMap.put("a", a.setScale(40, RoundingMode.CEILING).toString());
            System.out.println("a   " + resultMap.get("a"));
            resultMap.put("b", b.setScale(40, RoundingMode.CEILING).toString());
            System.out.println("b   " + resultMap.get("b"));
            return resultMap;
        } catch (NullPointerException important) {
            System.out.println("massiv dannih bitiy" + important);
            return null;
        }
    }


    public static LinkedHashMap<String, String> mnc(LinkedHashMap<String, String> map) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        BigDecimal sumxy = new BigDecimal("0");
        BigDecimal sumx = new BigDecimal("0");
        BigDecimal sumy = new BigDecimal("0");
        BigDecimal sumx2 = new BigDecimal("0");
        BigDecimal num = new BigDecimal(String.valueOf(map.size()));
        try {
            for (HashMap.Entry<String,String> item : map.entrySet()) {
                sumxy = sumxy.add(new BigDecimal(item.getKey()).multiply(new BigDecimal(item.getValue()), MathContext.UNLIMITED));
                sumx = sumx.add(new BigDecimal(item.getKey()));
                sumy = sumy.add(new BigDecimal(item.getValue()));
                sumx2 = sumx2.add(new BigDecimal(item.getKey()).pow(2, MathContext.UNLIMITED));
            }
            System.out.println("--------------------------РАСЧЕТ МНК ДЛЯ ИТОГОВОГО ПРОМЕЖУТКА");
            System.out.println("sumXY   " + sumxy);
            System.out.println("sumX    " +  sumx);
            System.out.println("sumY    " + sumy);
            System.out.println("sumX2   " + sumx2);
            System.out.println("N   " + num);
            System.out.println("----------");
            BigDecimal a = new BigDecimal(((num.multiply(sumxy, MathContext.UNLIMITED)).subtract(sumx.multiply(sumy, MathContext.UNLIMITED)))
                    .divide(num.multiply(sumx2, MathContext.UNLIMITED).subtract(sumx.pow(2)), 40, RoundingMode.CEILING).toString());
            BigDecimal b = new BigDecimal((sumy.subtract(a.multiply(sumx, MathContext.UNLIMITED))).divide(num, 40, RoundingMode.CEILING).toString());
            resultMap.put("a", a.setScale(40, RoundingMode.CEILING).toString());
            System.out.println("a   " + resultMap.get("a"));
            resultMap.put("b", b.setScale(40, RoundingMode.CEILING).toString());
            System.out.println("b   " + resultMap.get("b"));
            return resultMap;
        } catch (NullPointerException important) {
            System.out.println("massiv dannih bitiy" + important);
            return null;
        }
    }


//    public static HashMap<String, List<SuperClass>> otbrakovka(List<SuperClass> rawdata){
//        HashMap<String, String> resultMap = new HashMap<>();
//        List<SuperClass> norm = new ArrayList<>();
//        List<SuperClass> otbrakovka = new ArrayList<>();
//
//        for(SuperClass item: rawdata){
//            if ()
//        }
//    }
}


