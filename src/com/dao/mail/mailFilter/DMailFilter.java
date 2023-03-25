package com.dao.mail.mailFilter;


import com.dao.mail.DSqlMail;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.mail.mailFilter.FMailFilter;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DMailFilter extends DSqlMail
{
    
    public FBeans getAll(Connection cnn,long meId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAll()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FMailFilter bean =new FMailFilter();
      try
      {
        prpstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_SELECT_ALL);
        prpstm.setLong(PARAM_01,meId);  
        rs = prpstm.executeQuery();
        beans = new FBeans();
        while((rs != null) && (rs.next()))
        {
            bean = new FMailFilter();
            bean.setFrom(rs.getString(MAIL_FILTER_FROM));
            bean.setLikeFrom(rs.getInt(MAIL_FILTER_LIKEFROM));
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
    
    
    public FBeans getAllByMeId(Connection cnn,long meId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllByMeId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_SELECT_ALL);       
        prpstm.setLong(PARAM_01,meId);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FMailFilter beantemp = null;
        while((rs != null) && (rs.next()))
        {
          beantemp = new FMailFilter();
          beantemp.setId(rs.getInt(MAIL_FILTER_ID));
          beantemp.setFrom(rs.getString(MAIL_FILTER_FROM));
          beantemp.setLikeFrom(rs.getInt(MAIL_FILTER_LIKEFROM));  
          beantemp.setSubject(rs.getString(MAIL_FILTER_SUBJECT));
          beantemp.setLikeSubjec(rs.getInt(MAIL_FILTER_LIKESUBJECT));
          beantemp.setUserId(rs.getInt(MAIL_FILTER_USER_ID));
          beans.add(beantemp);
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
      final String LOCATION = this.toString() + "addNew()";
      boolean result = true;
      PreparedStatement prstm = null;
      FMailFilter bean = null;
      try
      {
        bean = (FMailFilter)seed;
        prstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_ADD_NEW);
        prstm.setString(PARAM_01,bean.getFrom());
        prstm.setInt(PARAM_02,bean.getLikeFrom());
        prstm.setString(PARAM_03,bean.getSubject());
        prstm.setInt(PARAM_04,bean.getLikeSubjec());
        prstm.setInt(PARAM_05,bean.getUserId());
        prstm.execute();
  
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        result= false;
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
    
    public boolean addBatch(Connection cnn,String[] froms,long meId) throws SQLException, EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = true;
      PreparedStatement prstm = null;
      try
      {
      prstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_ADD_NEW); 
      for (int i=0;i<froms.length;i++){                
           prstm.setString(PARAM_01,froms[i]);
           prstm.setInt(PARAM_02,1);
           prstm.setString(PARAM_03,"");
           prstm.setInt(PARAM_04,1);
           prstm.setLong(PARAM_05,meId);
           prstm.addBatch();
       }
       result=prstm.executeBatch().length>0;
     }
      catch(Exception ex)
      {
          ex.printStackTrace();
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
    
    
    
    public boolean update(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      FMailFilter bean = null;
      boolean result=false;
      try
      {
            bean = (FMailFilter)seed;
            prstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_UPDATE);
            prstm.setString(PARAM_01,bean.getFrom());
            prstm.setInt(PARAM_02,bean.getLikeFrom());
            prstm.setString(PARAM_03,bean.getSubject());
            prstm.setInt(PARAM_04,bean.getLikeSubjec());
            prstm.setInt(PARAM_05,bean.getUserId());
            prstm.setInt(PARAM_06,bean.getId());
            result=prstm.executeUpdate()>0;
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
    
    public boolean delete(Connection cnn, int id)throws EException
    {
      return   delete(cnn, TABLE_MAIL_FILTER, MAIL_FILTER_ID + EQUAL + id)>0;
     
     
    }  
    public boolean checkNameReportType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameReportType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FMailFilter bean = (FMailFilter)seed;
      try
      {
        bean = (FMailFilter)seed;
        prstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_CHECK_NAME);
        prstm.setString(PARAM_01,bean.getFrom());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;
    }

   
    public FMailFilter getById(Connection cnn,int id) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FMailFilter beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_MAIL_FILTER_TYPE_SELECT_BY_ID);
        prpstm.setInt(PARAM_01,id);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
          beantemp = new FMailFilter();
          beantemp.setId(rs.getInt(MAIL_FILTER_ID));
          beantemp.setFrom(rs.getString(MAIL_FILTER_FROM));
          beantemp.setLikeFrom(rs.getInt(MAIL_FILTER_LIKEFROM));  
          beantemp.setSubject(rs.getString(MAIL_FILTER_SUBJECT));
          beantemp.setLikeSubjec(rs.getInt(MAIL_FILTER_LIKESUBJECT));
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
   
}
