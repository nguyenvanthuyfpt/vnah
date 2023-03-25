package com.dao.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.form.report.FReport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DReport  extends DSqlReport
{
    public FBeans getEmpsRecv(Connection cnn,int id) throws EException{
      final String LOCATION = this.toString() + "getEmpsRecv()";
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      FReport beantemp=null;
      FBeans beans=new FBeans();
      try{
              List params = new ArrayList();    
              params.add(id);
              prstm = prepareStatement(cnn,SQL_SELECT_USERS_RECV_REPORT,params); 
              ////.println(SQL_SELECT_USERS_RECV_REPORT);
              rs = prstm.executeQuery();
                while((rs != null) && (rs.next())){
                    beantemp =new FReport();
                    beantemp.setToPertion(rs.getInt(REPORT_SHARE_USER_ID));
                    beantemp.setUserFullName(rs.getString(USERS_FULLNAME));
                    beantemp.setEmpsRev(new FBeans());
                    beantemp.getEmpsRev().add(beantemp);
                    beans.add(beantemp);
                }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FReport getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReport bean = new FReport();
      bean = (FReport)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_REPORT_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs,false);
            bean.setEmpsRev(getEmpsRecv(cnn,bean.getId()));
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
      FReport bean =(FReport)seed;
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_REPORT_INSERT,params);
          result = prstm.executeUpdate()>0;
          if(result && bean.getUserIdS()!=null){
              addBathReportUsers(cnn,seed);
          }
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
    
    
    public boolean addBathReportUsers(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean flag = false;
        boolean result = false;
        PreparedStatement prstm = null;
        FReport bean=(FReport)seed;
        if(bean.getId()==0){
        bean.setId(getTopId(cnn,bean.me.getId()));
        }
        try
        {
            prstm = cnn.prepareStatement(SQL_INSERT_REPORT_USERS);
                 for (int i =0;i<bean.getUserIdS().length;i++){
                         prstm.setInt(PARAM_01,bean.getId());
                         prstm.setInt(PARAM_02,bean.getUserIdS()[i]);
                         prstm.addBatch(); 
                         flag = true;
                 }
                  if (flag){
                      result=prstm.executeBatch().length>0;
                  }
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
      
    
    public boolean deleteReportUser(Connection cnn,int id) throws EException{
                return delete(cnn, TABLE_REPORTS_SHARE, REPORT_SHARE_REPORT_ID + EQUAL + id )>0;
        }    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FReport bean = (FReport)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_REPORT_UPDATE,params);
        result = prstm.executeUpdate()>0;
          if(result && bean.getUserIdS()!=null){
              deleteReportUser(cnn,bean.getId());
              addBathReportUsers(cnn,seed);
          }
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
    public boolean updatePermision(Connection cnn,int id,int permission) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
            prstm = cnn.prepareStatement(SQL_SELECT_UPDATE_PERMISION);
            prstm.setInt(PARAM_01,permission);
            prstm.setInt(PARAM_02,id);
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
    public boolean restore(Connection cnn,int[] ids,int value) throws SQLException, EException {
      final String LOCATION = this.toString() + UPDATE;
      boolean flag = false;
      PreparedStatement prstm = null;
      try{
            prstm = cnn.prepareStatement(SQL_SELECT_UPDATE_PERMISION);
             for (int i =0;i<ids.length;i++){
                 prstm.setInt(PARAM_01,value);
                 prstm.setInt(PARAM_02,ids[i]);
                 prstm.addBatch();
                 flag = true;
             }
             if (flag){
                 prstm.executeBatch();
             }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }finally{
        closePreparedStatement(prstm);
      }
      return flag;
    }
    public boolean delete(Connection cnn,int id) throws EException{
      return delete(cnn, TABLE_REPORTS, REPORT_ID + EQUAL + id )>0;
    }    
    public FReport getInformation(ResultSet rs,boolean full) throws EException
    {
        final String LOCATION = "->getInformation()";
        FReport bean = new FReport();
         try {
           bean.setId(rs.getInt(REPORT_ID));
             bean.setCreator(rs.getString(USERS_FULLNAME));
             bean.setUserId(rs.getInt(REPORT_USERS_ID));
             bean.setName(rs.getString(REPORT_NAME));
             bean.setDescription(rs.getString(REPORT_DESCRIPTION));
             bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(REPORT_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
             bean.setPermission(rs.getInt(REPORT_PERMISSION));
             bean.setReportType_id(rs.getInt(REPORT_TYPE_ID));
             bean.setFileStore(rs.getString(REPORT_FILESTORE));
             bean.setRealName(rs.getString(REPORT_REALNAME));
             if(full){
                 bean.setNameCategory(rs.getString(REPORT_NAME_CATEGORY));
             }
             
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }

    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FReport bean = (FReport)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getUserId());
             params.add(bean.getName());
             params.add(bean.getDescription());                           
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));
             params.add(1);                           
             params.add(bean.getReportType_id());                           
             params.add(bean.getFileStore());   
             params.add(bean.getRealName());   
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public FBeans getAllByType(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAllByType()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReport bean=(FReport)seed;
      try
      {
      String SQL_SELECT=SQL_SELECT_ALL_REPORT + " ";
      List params =new ArrayList();
      if(bean.getType()==0){
          params.add(bean.getMeId());
          SQL_SELECT+=AND + TABLE_REPORTS + STOP + REPORT_USERS_ID + EQUAL + QUESTION;
      }else{
          params.add(bean.getMeId());
          SQL_SELECT+=AND + TABLE_REPORTS_SHARE + STOP + REPORT_SHARE_USER_ID + EQUAL + QUESTION;
          if(bean.getUserId()>0){
              params.add(bean.getUserId());    
              SQL_SELECT+=AND + TABLE_REPORTS + STOP + REPORT_USERS_ID + EQUAL + QUESTION;
          }
      }
      
      if(bean.getTimeCreateForm()!=null && !bean.getTimeCreateForm().equals("")){
          params.add(bean.stringToSqlDate(bean.getTimeCreateForm()));
          SQL_SELECT+=AND +TABLE_REPORTS + STOP +  REPORT_TIMECREATE +">="+QUESTION;
      }
      if(bean.getTimeCreateTo()!=null && !bean.getTimeCreateTo().equals("")){
          params.add(bean.addDays(bean.stringToSqlDate(bean.getTimeCreateTo()),1));
          SQL_SELECT+=AND +TABLE_REPORTS + STOP + REPORT_TIMECREATE +"<"+QUESTION;
      }
      
      if(bean.getReportType_id()>0){
         params.add(bean.getReportType_id());
         SQL_SELECT+=AND + TABLE_REPORTS + STOP + REPORT_TYPE_ID + EQUAL + QUESTION;
      }
      ////.println(SQL_SELECT);
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
        beans = new FBeans();        
        beans.setTotalRows(count(cnn,SQL_SELECT,params));
        beans.setPageIndex(bean.getPageIndex());
          if(beans.getFirstRecord()<=1){
              rs.beforeFirst();
          }else{
              rs.absolute(beans.getFirstRecord()-1);
          }
          int i=0;
        while(rs != null && rs.next()&& i<AppConfigs.APP_ROWS_VIEW)
        {
            i++;
            bean=new FReport();
            bean=getInformation(rs,true);
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
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FBeans getUserByRules(Connection cnn,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getUserByRules()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
      List params =new ArrayList();
        params.add(userId);
        params.add(userId);
        prstm = prepareStatement(cnn,SQL_SELECT_USER_IN_RURES,params);
        rs = prstm.executeQuery();
        FUser bean =null;
        while((rs != null) && (rs.next()))
        {
            bean=new FUser();
            bean.setId(rs.getInt(USERS_USER_ID));
            bean.setFullName(rs.getString(USERS_FULLNAME));
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
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    
    public int getTopId(Connection cnn,long meId) throws EException 
        {
          final String LOCATION = this.toString() + "getTopId()";
          PreparedStatement prpstm = null;
          ResultSet rs = null;
          int id=0;
          try
          {
            prpstm = cnn.prepareStatement(SQL_SELECT_REPORT_BY_TOP);
            prpstm.setLong(PARAM_01,meId);
            rs = prpstm.executeQuery();
            if((rs != null) && (rs.next()))
            {
                    id=rs.getInt(PARAM_01);
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
          return id;    
        }
        
}
