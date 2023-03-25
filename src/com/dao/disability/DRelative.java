package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FRelative;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DRelative  extends DSqlDisability{
    public FBeans getAllByIdNkt(Connection cnn,int idNkt) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try {
        List params = new ArrayList();
        params.add(idNkt);
        prstm = prepareStatement(cnn,SELECT_ALL_FROM_TABLE_RELATIVE_BY_NKT,params);
//        //.println(SELECT_ALL_FROM_TABLE_RELATIVE_BY_NKT);
        rs = prstm.executeQuery();
        FRelative bean=null;
        while(rs != null && rs.next()){
                bean=new FRelative();
                bean=getInformation(rs);
                bean.setTen(rs.getString(NKT_TEN));
                bean.setTenLyDo(rs.getString(LYDO_NAME));
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
    public boolean delete(Connection cnn,int id) throws EException{
            boolean result =delete(cnn, TABLE_RELATIVE,TN_NKT_ID + EQUAL + id )>0;
    return result;
    } 
    public FBeans getAll(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FRelative bean=(FRelative)seed;
      try {
        List params = new ArrayList();
        prstm = prepareStatement(cnn,SELECT_ALL_FROM_TABLE_RELATIVE,params);
        rs = prstm.executeQuery();
        beans = new FBeans();
          beans.setTotalRows(count(cnn,SELECT_ALL_FROM_TABLE_RELATIVE,params));
          beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }
            int i=0;
          while(rs != null && rs.next()&& i<AppConfigs.APP_ROWS_VIEW){
            i++;
                bean=new FRelative();
                bean=getInformation(rs);
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
    
 
 
    public FRelative getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FRelative bean = (FRelative)seed;
      try
      {
        prstm = cnn.prepareStatement(SELECT_GET_BY_ID_FROM_TABLE_RELATIVE);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = new FRelative();
            bean=getInformation(rs);
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
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_INSERT_INTO_TABLE_RELATIVE,params);
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
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FRelative bean = (FRelative)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_UPDATE_INTO_TABLE_RELATIVE,params);
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
 
 
    public boolean delete(Connection cnn,int[] ids) throws EException{
    for(int i=0;i<ids.length;i++){
        delete(cnn, TABLE_RELATIVE, TN_NKT_ID + EQUAL + ids[i] );
    }
      return true;
    }    
    

    public FRelative getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FRelative bean = new FRelative();
         try {
             bean.setId(rs.getInt(TN_NKT_ID));
//             bean.setIdNkt(rs.getInt(TN_NKT_ID_NKT));
//             bean.setLaNKT(rs.getInt(TN_NKT_LA_NKT));
//             bean.setLaChuHo(rs.getInt(TN_NKT_LA_CHUHO));
//             bean.setLaNChamSoc(rs.getInt(TN_NKT_LA_NCHAMSOC));
             bean.setIdRelativeNkt(rs.getInt(TN_NKT_ID_RELATIVE_NKT));
             bean.setLydoId(rs.getInt(TN_NKT_ID_LYDO));
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
        FRelative bean = (FRelative)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getIdNkt());
             params.add(bean.getIdRelativeNkt());
             params.add(bean.getLydoId());
             
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }


}
