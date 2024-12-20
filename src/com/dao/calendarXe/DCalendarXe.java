package com.dao.calendarXe;


import com.dao.calendar.DCalendarLib;
import com.dao.calendar.agenda.DSqlMyagenda;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.calendarXe.FCalendarXe;

import com.inf.agenda.IKeyAgenda;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DCalendarXe extends DSqlMyagenda
{
    public FCalendarXe[] getCalByDay(Connection cnn,int userId, Date dayStart,int Days) throws EException{
        final String LOCATION = this.toString() + "~~>getEvents()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FCalendarXe bean = new FCalendarXe();
        FCalendarXe beanTemp = new FCalendarXe();
        String[] timeValues=IKeyAgenda.CALENDAR_TIMES_VALUES;
        FCalendarXe[] events = new FCalendarXe[timeValues.length];
        String SQL_SELECT=SQL_SELECT_ALL_CALENDAR_XE_FROMDATE_TO_TODATE;
        try
        {
            List params =new ArrayList();
            params.add(dayStart);
            params.add(bean.addDays(dayStart,Days));
            prpstm = prepareStatement(cnn,SQL_SELECT,params);
            rs = prpstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
                beanTemp = new FCalendarXe();
                beanTemp.setEventId(rs.getInt(CALENDAR_XE_MEETING_ID));
                beanTemp.setBaseEmp(rs.getString(CALENDAR_XE_BASE_EMP));
                beanTemp.setContent(rs.getString(CALENDAR_XE_CONTENT));
                beanTemp.setBienSo(rs.getString(CALENDAR_XE_DEVICE));
                beanTemp.setTimeEvent(bean.dateToString(rs.getDate(CALENDAR_XE_TIMEEVENT)));
                beanTemp.setTimeCreate(bean.dateToString(rs.getDate(CALENDAR_XE_TIMECREATE)));
                beanTemp.setTimes(rs.getString(CALENDAR_XE_FROM_TIME));
                beanTemp.setToTimes(rs.getString(CALENDAR_XE_TO_TIME));
                beanTemp.setUserCreate(rs.getInt(CALENDAR_XE_USER_ID));
                int indexTime=0;
                for(int i=0;i<timeValues.length;i++){
                    if(timeValues[i].equals(beanTemp.getTimes())) {
                        indexTime=i;
                    }
                }
                events[indexTime] = beanTemp;
            }
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prpstm);
        }
        return events;
    }
    
    
    public FBeans getEventsTest(Connection cnn,int userId, Date dayStart,int Days) throws EException{
        FBeans beans=new FBeans();
        FCalendarXe bean = new FCalendarXe();
        String[] timeValues=IKeyAgenda.CALENDAR_TIMES_VALUES;
        FCalendarXe[] events = new FCalendarXe[timeValues.length];
        DCalendarLib cal = new DCalendarLib();
       try  {
           for(int i=0;i<Days;i++){
                   bean = new FCalendarXe();
                   bean.setDay(bean.addDays(dayStart,i));
//                   //.println(bean.getDay());
                   bean.setDayId(cal.convertDateToID(bean.getDay()));
                   bean.setTimeEvent(bean.dateToString(bean.getDay()));
                   FCalendarXe beanTemp =new FCalendarXe();
                   events=getCalByDay(cnn,userId,bean.getDay(),1);
                   for(int j=0;j<timeValues.length;j++){
                       if(events[j]!=null && timeValues[j].equals(events[j].getTimes())){
                           bean.getEventsInDay().add(events[j]);
                       }else{    
                           beanTemp =new FCalendarXe();
                           beanTemp.setTimes(timeValues[j]);
                           beanTemp.setToTimes(timeValues[j]);
                           beanTemp.setTimeEvent(bean.dateToString(bean.getDay()));
                           beanTemp.setBaseEmp("");
                           beanTemp.setContent("");
                           bean.getEventsInDay().add(beanTemp);
                       }
                   }
                   beans.add(bean);
           }
       } catch (Exception ex)  {
           ex.printStackTrace();
       } finally  {
       }
       
        return beans;
    }
    
    public FBeans getEventsByMonth(Connection cnn,int userId, Date dayStart,int Days) throws EException{
        FBeans beans=new FBeans();
        FCalendarXe bean = new FCalendarXe();
        DCalendarLib calen=new DCalendarLib();
        try  {
           for(int i=0;i<Days;i++){
               bean = new FCalendarXe();
               bean.setDay(bean.addDays(dayStart,i));
               bean.setDayId(calen.convertDateToID(bean.getDay()));
               bean.setTimeEvent(bean.dateToString(bean.getDay()));
               bean.setEventsInDay(getCalByDayMonth(cnn,userId,bean.getDay(),1));
               beans.add(bean);
           }
        } catch (Exception ex)  {
           ex.printStackTrace();
        } finally  {
        }
        
        return beans;
    }
    
    public FBeans getCalByDayMonth(Connection cnn,int userId, Date dayStart,int Days) throws EException{
        final String LOCATION = this.toString() + "~~>getEvents()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans =new FBeans();
        FCalendarXe bean = new FCalendarXe();
        String SQL_SELECT=SQL_SELECT_ALL_CALENDAR_XE_FROMDATE_TO_TODATE;
        String SQL_ORDER= ORDER_BY + CALENDAR_XE_TIMEEVENT + COMMA + CALENDAR_XE_FROM_TIME;
        try
        {
            List params =new ArrayList();
            params.add(dayStart);
            params.add(bean.addDays(dayStart,Days));
            prpstm =prepareStatement(cnn,SQL_SELECT+SQL_ORDER,params);
            rs = prpstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
                bean= new FCalendarXe();
                bean.setEventId(rs.getInt(CALENDAR_XE_MEETING_ID));
                bean.setBaseEmp(rs.getString(CALENDAR_XE_BASE_EMP));
                bean.setContent(rs.getString(CALENDAR_XE_CONTENT));
                bean.setBienSo(rs.getString(CALENDAR_XE_DEVICE));
                bean.setTimeEvent(bean.dateToString(rs.getDate(CALENDAR_XE_TIMEEVENT)));
                bean.setTimeCreate(bean.dateToString(rs.getDate(CALENDAR_XE_TIMECREATE)));
                bean.setTimes(rs.getString(CALENDAR_XE_FROM_TIME));
                bean.setToTimes(rs.getString(CALENDAR_XE_TO_TIME));
                bean.setUserCreate(rs.getInt(CALENDAR_XE_USER_ID));
                beans.add(bean);
            }
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public boolean addNew(Connection cnn, FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try
        {
            List params = setParams(seed);
            result = prepareStatement(cnn,SQL_CALENDAR_XE_ADD_NEW,params).executeUpdate()>0;
        }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
        finally
        {
          closePreparedStatement(prstm);
        }
      return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws  EException{
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try
            {
            FCalendarXe bean=(FCalendarXe)seed;
            List params = setParams(seed);
            params.add(bean.getEventId());
            prstm = prepareStatement(cnn,SQL_CALENDAR_XE_UPDATE,params);
            result = prstm.executeUpdate()>0;
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
        finally
        {
          closePreparedStatement(prstm);
        }
      return result;
    }
   
    public FCalendarXe getById(Connection cnn, int id) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FCalendarXe beantemp =new FCalendarXe();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_CALENDAR_XE_BY_ID);
        prpstm.setInt(PARAM_01,id);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp=getInformation(rs);
            
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beantemp;    
    } 
    
    public FCalendarXe getTop(Connection cnn,long meId) throws EException 
    {
      final String LOCATION = this.toString() + "getTop()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FCalendarXe beantemp =new FCalendarXe();
      try
      {
        prpstm = cnn.prepareStatement(SELECT + STAR + FROM + TABLE_CALENDAR_XE + WHERE + CALENDAR_XE_MEETING_ID + EQUAL + OPEN + SELECT +  MAX + OPEN + CALENDAR_XE_MEETING_ID + CLOSE + FROM + TABLE_CALENDAR_XE + WHERE + CALENDAR_XE_USER_ID + EQUAL + QUESTION + CLOSE);
        prpstm.setLong(PARAM_01,meId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp=getInformation(rs);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beantemp;    
    } 
    
    
    public FCalendarXe getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FCalendarXe beanTemp = new FCalendarXe();
         try {
             beanTemp.setEventId(rs.getInt(CALENDAR_XE_MEETING_ID));
             beanTemp.setBaseEmp(rs.getString(CALENDAR_XE_BASE_EMP));
             beanTemp.setContent(rs.getString(CALENDAR_XE_CONTENT));
             beanTemp.setTimeEvent(beanTemp.dateToString(rs.getDate(CALENDAR_XE_TIMEEVENT)));
             beanTemp.setTimeCreate(rs.getString(CALENDAR_XE_TIMECREATE));
             beanTemp.setTimes(rs.getString(CALENDAR_XE_FROM_TIME));
             beanTemp.setToTimes(rs.getString(CALENDAR_XE_TO_TIME));
             beanTemp.setUserCreate(rs.getInt(CALENDAR_XE_USER_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
      public List setParams(FSeed seed) throws EException
      {
          final String LOCATION = "->setParams()";
          FCalendarXe bean = (FCalendarXe)seed;
          List params = new ArrayList();
           try {
               params.add(bean.getBaseEmp());   
               params.add(bean.getContent());   
               params.add(bean.getBienSo());   
               params.add(bean.stringToSqlDate(bean.getTimeEvent()));   
               params.add(bean.getCurrentSqlDate()); 
               params.add(bean.getTimes()); 
               params.add(bean.getToTimes()); 
               params.add(bean.getUserCreate()); 
           }
           catch (Exception exp) {            
               if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
           }
           finally {
           }
           return params;
      }
     
    public boolean delete(Connection cnn,int id)throws EException
    {
    return delete(cnn, TABLE_CALENDAR_XE, CALENDAR_XE_MEETING_ID + EQUAL + id)>0;
    }  
   
   
    public FBeans getCalendar(Connection cnn,Date day,long userId,int type,long departmentId) throws EException 
    {
      final String LOCATION = this.toString() + "getCalendar()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FCalendarXe beantemp =new FCalendarXe();
      FBeans beans =new FBeans();
      String SQL_SELECT=SQL_SELECT_CALENDA_MEETING_AT_MAIN;
      String SQL_ORDER=ORDER_BY + CALENDAR_XE_TIMEEVENT + COMMA + CALENDAR_XE_FROM_TIME;
      
      try
      {
        List params = new ArrayList();
        params.add(day);
        params.add(beantemp.addDays(day,IKeyAgenda.CALENDAR_VIEW_DAYS));
        prpstm = prepareStatement(cnn,SQL_SELECT + SQL_ORDER,params);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next())){
            beans.add(getInformation(rs));
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;    
    } 
 
}
