package com.dao.servey;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.doc.category.dataBaseServer.FDataBaseServer;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DServeyChose  extends DSqlForYou
{
    public FBeans getAll(Connection cnn) throws EException{
        final String LOCATION = this.toString() + "getAll";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
            List params =new ArrayList();
            prstm = prepareStatement(cnn,SQL_DATABASE_SERVER_SELECT_ALL,params);
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
          pstmt = conn.prepareStatement(SQL_DATABASE_SERVER_CHECK_CODE_INSERT);    
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
        pstmt = conn.prepareStatement(SQL_DATABASE_SERVER_CHECK_CODE_UPDATE);  
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
    public FDataBaseServer getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FDataBaseServer bean = new FDataBaseServer();
      try
      {
        prstm = cnn.prepareStatement(SQL_DATABASE_SERVER_SELECT_BY_ID);
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
          prstm = prepareStatement(cnn,SQL_DATABASE_SERVER_INSERT,params);
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
        FDataBaseServer bean = (FDataBaseServer)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_DATABASE_SERVER_UPDATE,params);
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
      return delete(cnn,TABLE_DATABASE_SERVER,DATABASE_SERVER_ID + EQUAL + id)>0;
    }    
   
    public FDataBaseServer getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataBaseServer bean = new FDataBaseServer();
         try {
                bean.setId(rs.getInt(DATABASE_SERVER_ID));
                bean.setUrl(rs.getString(DATABASE_SERVER_URL));
                bean.setSelectSql(rs.getString(DATABASE_SERVER_SELECTSQL));
                bean.setDescription(rs.getString(DATABASE_SERVER_DESCRIPTION));
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public List setParams(FSeed seed) throws EException
    {
             FDataBaseServer bean = (FDataBaseServer)seed;
             List params = new ArrayList();
             params.add(bean.getUrl());
             params.add(bean.getSelectSql());
             params.add(bean.getDescription());
           return params;
    }
}
