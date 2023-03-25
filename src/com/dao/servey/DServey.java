package com.dao.servey;


import com.dao.DSql;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.servey.FServey;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DServey  extends DSql
{
    public FBeans getAll(Connection cnn) throws EException{
        final String LOCATION = this.toString() + "getAll";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
            List params =new ArrayList();
            prstm = prepareStatement(cnn,SQL_SERVEY_SELECT_ALL,params);
            rs = prstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
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
        closePreparedStatement(prstm);
        }
        return beans;        
    }

    public boolean isExistAddNew(Connection conn,String userMail,int id)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SERVEY_CHECK_CODE_INSERT);    
          pstmt.setString(PARAM_01,userMail);
          rs = pstmt.executeQuery();
          result = rs!=null && rs.next();
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
    
    public boolean isExistUpdate(Connection conn,String userMail,int id)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
        pstmt = conn.prepareStatement(SQL_SERVEY_CHECK_CODE_UPDATE);  
        pstmt.setInt(PARAM_01,id);
        pstmt.setString(PARAM_02,userMail);
        rs = pstmt.executeQuery();
        result = rs!=null && rs.next();
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
    public FServey getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FServey bean = new FServey();
      try
      {
        prstm = cnn.prepareStatement(SQL_SERVEY_SELECT_BY_ID);
        prstm.setInt(PARAM_01,id);
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs);
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
    public FBeans getByActiveTrue(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "getByActiveTrue()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FServey bean = new FServey();
      FBeans beans=new FBeans();
      try
      {
      List params =new ArrayList();
      params.add(1);//1:active (Have to active equal true)
      params.add(bean.getCurrentSqlDate());
      //.println(SQL_SERVEY_BY_ACTIVE_1);
        prstm = prepareStatement(cnn,SQL_SERVEY_BY_ACTIVE_1,params);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            bean=getInformation(rs);
            bean.setQuestions(new DServeyQuestions().getByServeyId(cnn,bean.getServeyId()));
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
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          List params = setParams(seed);
          result = execute(cnn,SQL_SERVEY_INSERT,params)>0;
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
    
    public boolean update(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FServey bean = (FServey)seed;
        List params = setParams(seed);
        params.add(bean.getServeyId());
        prstm = prepareStatement(cnn,SQL_SERVEY_UPDATE,params);
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

    public boolean delete(Connection cnn,int id) throws EException
    {
      delete(cnn,TABLE_SERVEY_QUESTIONS,QUESTIONS_SERVEY_ID + EQUAL + id);
      return delete(cnn,TABLE_SERVEY,SERVEY_ID + EQUAL + id)>0;
    }    
   
    public FServey getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FServey bean = new FServey();
         try {
                bean.setServeyId(rs.getInt(SERVEY_ID));
                bean.setCode(rs.getString(SERVEY_CODE));
                bean.setName(rs.getString(SERVEY_NAME));
                bean.setDescription(rs.getString(SERVEY_DESCRIPTION));
                bean.setFromDate(bean.dateToString(rs.getDate(SERVEY_FROM_DATE)));
                bean.setToDate(bean.dateToString(rs.getDate(SERVEY_TO_DATE)));
                bean.setCreateTime(bean.dateToString(new Date (rs.getTimestamp(SERVEY_CREATETIME).getTime()),AppConfigs.APP_DATE_TIME));
                bean.setOrders(rs.getInt(SERVEY_ORDERS));
                bean.setPosition(rs.getString(SERVEY_POSITION));
                bean.setActive(rs.getInt(SERVEY_ACTIVE));
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FServey bean = (FServey)seed;
        List params = new ArrayList();
        try{
        params.add(bean.getCode());
        params.add(bean.getName());
        params.add(bean.getDescription());
        params.add(bean.stringToSqlDate(bean.getFromDate()));
        params.add(bean.stringToSqlDate(bean.getToDate()));
        params.add(new java.sql.Timestamp(System.currentTimeMillis())); 
        params.add(bean.getOrders());
        params.add(bean.getPosition());
        params.add(bean.getActive());
        }
        catch (Exception exp) {            
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
        }
    return params;
    }
}
