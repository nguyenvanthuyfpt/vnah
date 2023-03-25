package com.util;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FSupport;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.log4j.Logger;


public class Utilities {

    private static Logger logger = Logger.getLogger(Utilities.class);

    private static final SimpleDateFormat sdf =
        new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private static final SimpleDateFormat formatDateShort4 =
        new SimpleDateFormat("HH:mm dd/MM/yyyy");

    public static boolean contains(final int[] arr, final int item) {
        boolean retval = false;
        if (arr!=null) {
          for (int i = 0; i < arr.length; i++) {
              if (arr[i] == item) {
                  retval = true;
              } else {
                  retval = false;
                  break;
              }
          }
        }
        return retval;
    }

    public static String parseArr2Str(final int[] arr,
                                      final String delimiter) {
        String retval = delimiter;
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    retval += String.valueOf(arr[i]) + delimiter;
                }
            }
        }
        return retval;
    }

    public static String getCurrentMonthYear() {
        Calendar cal = Calendar.getInstance();
        String result =
            cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1 < 10 ?
                                            "0" +
                                            (cal.get(Calendar.MONTH) + 1) :
                                            (cal.get(Calendar.MONTH) + 1));
        return result;
    }

    public static String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        String result = String.valueOf((cal.get(Calendar.MONTH) + 1));
        return result;
    }

    public static Boolean checkSpecialCharacter(String s) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static String getStringTemplate(String template,
                                           Map<String, String> params) {
        if (StringUtils.isNotBlank(template)) {
            StrSubstitutor sub = new StrSubstitutor(params);
            return sub.replace(template);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static String list2String(List lst) {
        StringBuilder str = new StringBuilder();
        if (lst != null && lst.size() > 0) {
            for (Object o : lst) {
                if (StringUtils.EMPTY.equals(str.toString())) {
                    str.append(o.toString());
                } else {
                    str.append(",");
                    str.append(o.toString());
                }
            }
        }
        return str.toString();
    }

    public static String removeBracket(String str) {
        return str != null ? str.replaceAll("\\[|\\]", "") : "";
    }

    public static String getStringDateFormat(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static Date getDate(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            logger.error("Can not get Date: " + date + " with format: " +
                         format, e);
            return null;
        }
    }

    public static String parseDateToTringType4(Date date) {
        try {
            return formatDateShort4.format(date);
        } catch (Exception es) {
        }
        return "";
    }

    /**
     * Check List is empty
     * @param list
     * @return boolean
     */
    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Find all items are duplicated in 2 list
     * @param list1
     * @param list2
     * @return duplicated items List
     */
    public static List findDuplicatedIn2List(List list1, List list2) {
        List lstDeplicated = new ArrayList();
        if (isEmpty(list1) || isEmpty(list2)) {
            return lstDeplicated;
        }

        Object obj;
        for (int i = 0; i <= list1.size(); i++) {
            obj = list1.get(i);
            if (list2.contains(obj)) {
                lstDeplicated.add(obj);
            }
        }

        return lstDeplicated;
    }

    /**
     * Find all items has on list1 and have no on list2
     * @param list1
     * @param list2
     * @return excluded items List
     */
    public static List findExcludeIn2List(List list1, List list2) {
        List lstExclude = new ArrayList();
        if (isEmpty(list1)) {
            return lstExclude;
        }

        if (isEmpty(list2)) {
            return list1;
        }

        Object obj;
        for (int i = 0; i <= list1.size(); i++) {
            obj = list1.get(i);
            if (!list2.contains(obj)) {
                lstExclude.add(obj);
            }
        }

        return lstExclude;
    }

    /**
     * Get list after remove duplicate items
     * @param list
     * @return
     */
    public static List removeListDuplicate(List list) {
        List lst = new ArrayList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (!lst.contains(list.get(i))) {
                    lst.add(list.get(i));
                }
            }
        }

        return lst;
    }

    public static String createFrom(int yearReport) {
        String retval = "01/10/" + (yearReport - 1);
        return retval;
    }

    public static String createTo(int yearReport) {
        String retval = "30/09/" + yearReport;
        return retval;
    }

    public static String startQuarter(int quarter, int yearReport) {
        String retval = "";
        if (quarter == 1) {
            retval = "10/" + (yearReport - 1);
        } else if (quarter == 2) {
            retval = "01/" + yearReport;
        } else if (quarter == 3) {
            retval = "04/" + yearReport;
        } else {
            retval = "07/" + yearReport;
        }
        return retval;
    }

    public static String endQuarter(int quarter, int yearReport) {
        String retval = "";
        if (quarter == 1) {
            retval = "12/" + (yearReport - 1);
        } else if (quarter == 2) {
            retval = "03/" + yearReport;
        } else if (quarter == 3) {
            retval = "06/" + yearReport;
        } else if (quarter == 4) {
            retval = "09/" + yearReport;
        }
        return retval;
    }

    public static int getCurrentYear(Date currentDate) {
        int retval = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int currYear = currentDate.getYear() + 1900;
        String dateBaseline = String.valueOf(currYear) + "-09-30";
        Date dateCompare;
        try {
            dateCompare = sdf.parse(dateBaseline);
            if (currentDate.after(dateCompare)) {
                retval = currYear + 1;
            } else {
                retval = currYear;
            }
        } catch (ParseException e) {

        }
        return retval;
    }

    public static int getCurrentQuarter(int month) {
        int period = 0;
        if (month == 10 || month == 11 || month == 12)
            period = 1;
        else if (month == 1 || month == 2 || month == 3)
            period = 2;
        else if (month == 4 || month == 5 || month == 6)
            period = 3;
        else
            period = 4;
        return period;
    }

    public static String formatDate(String date, String initDateFormat,
                                    String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }

    public static Timestamp getCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
    
    public static String showSupport(int nktId, String datecreate, int nguonId) throws EException, SQLException{
        StringBuffer sb = new StringBuffer();
        com.bo.disability.BSupport boSupport = new com.bo.disability.BSupport();
        sb.append("");
        FBeans beans = boSupport.getNumSupports(nktId, datecreate, nguonId);
        int inc = 0;
        for(int i=0; i<beans.size();i++) {
            FSupport support = (FSupport)beans.get(i);
            inc++;
            sb.append(support.getStt()+ inc<beans.size()-1? "," : "");
        }
        return sb.toString();
    }   
}
