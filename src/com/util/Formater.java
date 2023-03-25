package com.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;


public class Formater {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static DecimalFormat df = (DecimalFormat)NumberFormat.getNumberInstance(Locale.US);
    private static final int[] quarters = new int[]{ 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1};
    
    public static Date str2date(String str) throws Exception {
        if (StringUtils.isBlank(str))
            return null;
        Date result = null;
        try {
            result = sdf.parse(str);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    
    public static int getPeriod(int month){
        int period = 0;     
        if (month==10 || month==11 || month==12)  
            period = 1;
        else if (month==1 || month==2 || month==3)
            period = 2;
        else if (month==4 || month==5 || month==6)
            period = 3;
        else
            period = 4;
        return period;
    }

    public static Calendar str2cal(String str) throws Exception {
        if (StringUtils.isBlank(str))
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(str2date(str));
        return cal;
    }

    public static java.sql.Date cal2date(Calendar cal) throws Exception {
        Date date = cal.getTime();
        return new java.sql.Date(date.getTime());
    }

    public static String cal2str(Calendar cal) throws Exception {
        Date date = cal.getTime();
        return date2str(date);
    }

    public static String cal2yyyymm(Calendar cal) throws Exception {
        int mm = cal.get(Calendar.MONTH) + 1;
        return cal.get(Calendar.YEAR) + ((mm < 10) ? ("0" + mm) : ("" + mm));
    }

    public static String cal2yyyyqq(Calendar cal) throws Exception {
        int mm = cal.get(Calendar.MONTH);
        return cal.get(Calendar.YEAR) + "0" + ((mm / 3) + 1);
    }

    public static String cal2my(Calendar cal) throws Exception {
        int mm = cal.get(Calendar.MONTH) + 1;
        return ((mm < 10) ? ("0" + mm) : ("" + mm)) + "/" + cal.get(Calendar.YEAR);
    }

    public static String cal2qy(Calendar cal) throws Exception {
        int mm = cal.get(Calendar.MONTH);
        return ((mm / 3) + 1) + "/" + cal.get(Calendar.YEAR);
    }

    public static String date2str(Date d) {
        if (d == null)
            return null;
        return sdf.format(d);
    }

    public static java.sql.Date str2sqldate(String str) throws Exception {
        if (StringUtils.isBlank(str))
            return null;
        return new java.sql.Date(str2date(str).getTime());
    }

    public static String sqldate2str(java.sql.Date d) {
        return date2str((Date)d);
    }

    public static String num2str(String number) {
        if (number!=null)
            return df.format(Double.parseDouble(number));
        else 
            return "0";
    }

    public static String num2str(double number) {
        return df.format(number);
    }

    public static String num2str(long number) {
        return df.format(number);
    }

    public static String num2str(int number) {
        return df.format(number);
    }

    public static Number str2num(String str) throws Exception {
        if (StringUtils.isBlank(str))
            return null;
        Number result = null;
        try {
            result = df.parse(str);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
        return result;
    }
}
