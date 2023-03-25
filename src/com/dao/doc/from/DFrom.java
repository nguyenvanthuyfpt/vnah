package com.dao.doc.from;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.from.FFrom;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DFrom extends DSqlForYou
{

    public String getAllInMember(Connection cnn,String members) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllInMember()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      String nameMembers="";      
      try
      {
      prpstm = cnn.prepareStatement(SQL_FROM_SELECT_ALL_FROM_IN_MEMBER.replaceAll("#",(members!=null && !members.equals(""))?members:-10+""));     
      rs = prpstm.executeQuery();
      while((rs != null) && (rs.next()))
        {
           nameMembers+=rs.getString(FROM_VN_NAME) + ";";
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
      return nameMembers;
    }
    
    
    public boolean isExistAdd(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExistAdd()";
    boolean result = false;
    FFrom bean = (FFrom)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_FROM_CHECK_CODE_ADD);    
          pstmt.setString(PARAM_01,bean.getCode());
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
    public boolean isExistUpdate(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExistUpdate()";
    boolean result = false;
    FFrom bean = (FFrom)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_FROM_CHECK_CODE_UPDATE);  
          pstmt.setInt(PARAM_01,bean.getId());
          pstmt.setString(PARAM_02,bean.getCode());
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
  public FBeans getAllFrom(Connection cnn,FSeed seed) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAllFrom()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FBeans beans = null;
    FFrom bean =(FFrom)seed;
    String SQL_SELECT= SQL_FROM_SELECT_ALL_FROM;
    String SQL_WHEHE=WHERE + TRUE;
    String SQL_ORDER_BY=ORDER_BY + FROM_ID;
    try
    {
    List params =new ArrayList();
    if(bean.getContentSearch()!=null &&  !bean.getContentSearch().equals("")){
        SQL_WHEHE+=AND + OPEN + FROM_CODE + LIKE + QUESTION;
        params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
        SQL_WHEHE+=OR + FROM_VN_NAME + LIKE + QUESTION;
        params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
        SQL_WHEHE+=OR + FROM_EN_NAME + LIKE + QUESTION;
        params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
        SQL_WHEHE+=OR + FROM_DESCRIPTION + LIKE + QUESTION + CLOSE;
        params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
        
    }
      prpstm = prepareStatement(cnn,SQL_SELECT+SQL_WHEHE+SQL_ORDER_BY,params);     
      //.println(SQL_SELECT+SQL_WHEHE+SQL_ORDER_BY);
      rs = prpstm.executeQuery();
      beans = new FBeans();
       
          while((rs != null) && (rs.next()))
      {
     
            bean = new FFrom();
            bean.setId(rs.getInt(FROM_ID));
            bean.setVnName(rs.getString(FROM_VN_NAME));
            bean.setEnName(rs.getString(FROM_EN_NAME));
            bean.setCode(rs.getString(FORM_CODE));
            bean.setDescription(rs.getString(FORM_DESCRIPTION));
            bean.setFomId(rs.getInt(FROM_FOM_ID));
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
  
  
    public FBeans getAllFomByFromId(Connection cnn,int  fomId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllFrom()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FFrom bean = null;
      String SQL_SELECT= SQL_FROM_SELECT_ALL_FROM;
      String SQL_WHEHE=WHERE + FROM_FOM_ID + EQUAL + QUESTION;
      String SQL_ORDER_BY=ORDER_BY + FROM_ID;
      try
      {
      List params =new ArrayList();      
      params.add(fomId);
        prpstm = prepareStatement(cnn,SQL_SELECT+SQL_WHEHE+SQL_ORDER_BY,params);     
        //.println(SQL_SELECT+SQL_WHEHE+SQL_ORDER_BY);
        rs = prpstm.executeQuery();
        beans = new FBeans();         
        while((rs != null) && (rs.next()))
        {
              bean = new FFrom();
              bean.setId(rs.getInt(FROM_ID));
              bean.setVnName(rs.getString(FROM_VN_NAME));
              bean.setEnName(rs.getString(FROM_EN_NAME));
              bean.setCode(rs.getString(FORM_CODE));
              bean.setDescription(rs.getString(FORM_DESCRIPTION));
              bean.setFomId(rs.getInt(FROM_FOM_ID));
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
    final String LOCATION = this.toString() + "addNew()";
    boolean result = true;
    PreparedStatement prstm = null;
    try
    {
        List params = setParams(seed);
        prstm = prepareStatement(cnn,SQL_FROM_ADD_NEW,params);
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
  
  public boolean update(Connection cnn, FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + UPDATE;
    PreparedStatement prstm = null;
    FFrom bean = null;
    boolean retult=false;
    try
    {
        bean = (FFrom)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_FROM_UPDATE,params);
        retult=prstm.executeUpdate()>0;
    }
    catch(Exception sqle)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
    }
    finally
    {
      closePreparedStatement(prstm);
    }
      return retult;
  }
  
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    final String LOCATION = this.toString() + "~~>delete()";
    FFrom bean = (FFrom)seed;
    boolean result=false;
    try
    {
      result=delete(cnn, TABLE_FROM, FROM_ID + EQUAL + bean.getId())>0;
    }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
    }
    return result;
  }  

  public FFrom getFromById(Connection cnn, FFrom bean) throws EException 
  {
    final String LOCATION = this.toString() + "getFromById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FFrom beantemp = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_FROM_SELECT_FROM_BY_ID);
      prpstm.setInt(PARAM_01,bean.getId());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
        beantemp = new FFrom();
        beantemp.setId(rs.getInt(FROM_ID));
        beantemp.setVnName(rs.getString(FROM_VN_NAME));
        beantemp.setEnName(rs.getString(FROM_EN_NAME));
        beantemp.setCode(rs.getString(FROM_CODE));
        beantemp.setDescription(rs.getString(FROM_DESCRIPTION));
        beantemp.setFomId(rs.getInt(FROM_FOM_ID));
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
 
    public FBeans getFromInID(Connection cnn,String fromsId) throws EException 
    {
      final String LOCATION = this.toString() + "getFromInID()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFrom beantemp = null;
        FBeans beans=null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_FROM_SELECT_IN_ID.replaceAll("#",fromsId));
        rs = prpstm.executeQuery();
        beans=new FBeans();
        int i=0;
        while((rs != null) && (rs.next()) && (i<=20))
        {
        i++;
          beantemp = new FFrom();
          beantemp.setId(rs.getInt(FROM_ID));
          beantemp.setVnName(rs.getString(FROM_VN_NAME));
          beantemp.setEnName(rs.getString(FROM_EN_NAME));
          beantemp.setCode(rs.getString(FROM_CODE));
          beantemp.setDescription(rs.getString(FROM_DESCRIPTION));
          beantemp.setFomId(rs.getInt(FROM_FOM_ID));
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
 
 
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FFrom bean = (FFrom)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getVnName());   
             params.add(bean.getEnName());   
             params.add(bean.getCode());   
             params.add(bean.getDescription());   
             params.add(bean.getFomId());   
             
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
}

