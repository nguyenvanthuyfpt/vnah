package com.dao.servey;


import com.dao.DSql;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.servey.FServeyQuestions;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DServeyQuestions  extends DSql
{
    public FBeans getAll(Connection cnn) throws EException{
        final String LOCATION = this.toString() + "getAll";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
            List params =new ArrayList();
            prstm = prepareStatement(cnn,SQL_QUESTIONS_SELECT_ALL,params);
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
    public FBeans getByServeyId(Connection cnn,int serveyId) throws EException{
        final String LOCATION = this.toString() + "getByServeyId";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
            List params =new ArrayList();
            params.add(serveyId);
            params.add(serveyId);
            prstm = prepareStatement(cnn,SQL_QUESTIONS_SELECT_BY_SERVEY_ID,params);
            rs = prstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
                FServeyQuestions bean = new FServeyQuestions();
                bean.setQuestionId(rs.getInt(QUESTIONS_ID));
                bean.setServeyId(rs.getInt(QUESTIONS_SERVEY_ID));
                bean.setQuestion(rs.getString(QUESTIONS_QUESTION));
                bean.setCount(rs.getInt(QUESTIONS_COUNT));
                bean.setOrders(rs.getInt(QUESTIONS_ORDERS));
                bean.setTotals(rs.getInt("TOTALS"));
                if(bean.getTotals()>0){
                    java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
                    bean.setPercent(df.format((float)(bean.getCount()*100)/bean.getTotals()));
                }
                if(bean.getCount()==0){
                    bean.setColor("#FFFAEF");
                }else{
                    bean.setColor("blue");
                }
                beans.add(bean);
            }
        }catch(SQLException sqle){
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
        }
        return beans;        
    }    
  
    public FServeyQuestions getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FServeyQuestions bean = new FServeyQuestions();
      try
      {
        prstm = cnn.prepareStatement(SQL_QUESTIONS_SELECT_BY_ID);
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
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_QUESTIONS_INSERT,params);
        result = prstm.executeUpdate()>0;
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
    public boolean count(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      PreparedStatement prstm = null;
      FServeyQuestions bean=(FServeyQuestions)seed;
      try
      {
          List params1 = new ArrayList();
          params1.add(bean.getQuestionId());
          prstm = prepareStatement(cnn,SQL_QUESTIONS_UPDATE_COUNT,params1);
          result = prstm.executeUpdate()>0;

          List params2 = new ArrayList();
          params2.add(bean.getQuestionId());
          params2.add(bean.me.getId());
          params2.add(new java.sql.Timestamp(System.currentTimeMillis()));
          prstm = prepareStatement(cnn,SQL_SERVEY_CHOSE_INSERT,params2);
          result = prstm.executeUpdate()>0;
          
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
        FServeyQuestions bean = (FServeyQuestions)seed;
        
        List params = new ArrayList();
        params.add(bean.getServeyId());
        params.add(bean.getQuestion());
        params.add(bean.getOrders());
        params.add(bean.getQuestionId());
        prstm = prepareStatement(cnn,SQL_QUESTIONS_UPDATE,params);
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

    public boolean delete(Connection cnn,int id,int userId) throws EException
    {
        delete(cnn,TABLE_SERVEY_CHOSE,CHOSE_QUESTION_ID + EQUAL + id + AND + CHOSE_USER_ID + EQUAL + userId);
      return delete(cnn,TABLE_SERVEY_QUESTIONS,QUESTIONS_ID + EQUAL + id)>0;
    }    
    public boolean checkExitsQuestionAndUserId(Connection conn,FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
    FServeyQuestions bean=(FServeyQuestions)seed;
     try {
        pstmt = conn.prepareStatement(SQL_QUESTIONS_CHECK_QUESTION_AND_USERID);  
        pstmt.setInt(PARAM_01,bean.getQuestionId());
         pstmt.setInt(PARAM_02,(int)bean.me.getId());
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
    public FServeyQuestions getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FServeyQuestions bean = new FServeyQuestions();
         try {
                bean.setQuestionId(rs.getInt(QUESTIONS_ID));
                bean.setServeyId(rs.getInt(QUESTIONS_SERVEY_ID));
                bean.setQuestion(rs.getString(QUESTIONS_QUESTION));
                
                bean.setCount(rs.getInt(QUESTIONS_COUNT));
             bean.setOrders(rs.getInt(QUESTIONS_ORDERS));
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public List setParams(FSeed seed) throws EException
    {
             FServeyQuestions bean = (FServeyQuestions)seed;
             List params = new ArrayList();
             params.add(bean.getServeyId());
             params.add(bean.getQuestion());
             params.add(bean.getCount());
             params.add(bean.getOrders());
           return params;
    }
}
