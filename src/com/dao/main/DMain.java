package com.dao.main;


import com.dao.DSql;
import com.dao.admin.require.trailer.DRequireTrailer;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.doc.assign.FDocAssign;
import com.form.main.FMain;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DMain extends DSql{
    
    
    public  FRequireTrailer checkRulesRequire(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkRulesRequire()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;      
      FRequireTrailer bean = (FRequireTrailer)seed;
        FRequireTrailer beanTemp = new FRequireTrailer();
      try
      {  
          DRequireTrailer daoR = new DRequireTrailer();
          prpstm = cnn.prepareStatement(SQL_SELECT_REQUIRES_CHECK_RULES);            
          prpstm.setLong(PARAM_01,bean.getCreator()); 
          rs = prpstm.executeQuery();    
         
          if(rs != null && rs.next()){ 
                beanTemp = daoR.getInformation(rs,false);   
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
      return beanTemp;
    }
    
//    public  boolean checkSms(Connection cnn,FSeed seed) throws EException
//    {
//      final String LOCATION = this.toString() + "~~>checkRulesRequire()";
//      PreparedStatement prpstm = null;   
//      ResultSet rs = null;   
//      boolean retult=true;
////      String SELECT_SQL=SELECT + STAR + FROM + TABLE_USERS + WHERE + USERS_USER_ID + IN + OPEN + SELECT + SMS_USER_ID + FROM + TABLE_SMS + CLOSE ;
////      try
////      {  
////          prpstm = cnn.prepareStatement(SELECT_SQL);            
////          rs = prpstm.executeQuery();    
////          if(rs != null && rs.next()){ 
////              retult=true;
////          }
////      }
////      catch(SQLException sqle)
////      {
////        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
////      }
////      finally
////      {
////        closeResultSet(rs);
////        closePreparedStatement(prpstm);
////      }
//      return retult;
//    }
    
    
    
    public void updateMinimize(Connection cnn,String menuId,int userId,int minimize ) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      try
      {
          prstm = cnn.prepareStatement(UPDATE + TABLE_UPERMISION + SET + UPERMISION_MINIMIZE + EQUAL + QUESTION + WHERE +  UPERMISION_MENU_ID + EQUAL + QUESTION + AND + UPERMISION_USER_ID + EQUAL + QUESTION);
          prstm.setInt(PARAM_01,minimize);
          prstm.setString(PARAM_02,menuId);
          prstm.setInt(PARAM_03,userId);
          prstm.executeUpdate();
      }catch(Exception sqle){
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }finally{
        closePreparedStatement(prstm);
      }
    }
    
    public void updateMinimizeContactNew(Connection cnn,String menuId,int userId,int minimize ) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      boolean result=false;
      try
      {
          prstm = cnn.prepareStatement(UPDATE + TABLE_MENU + SET + MENU_ORDERING + EQUAL + QUESTION + WHERE +  MENU_ID + EQUAL + QUESTION);
          prstm.setInt(PARAM_01,minimize);
          prstm.setString(PARAM_02,menuId);        
          result=prstm.executeUpdate()>0;
      }catch(Exception sqle){
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }finally{
        closePreparedStatement(prstm);
      }
    }
  
    public FBeans getDocRecvByStatus(Connection cnn,FDocAssign beanAssign,int obServer) throws EException{
      final String LOCATION = this.toString() + "getDocRecvByStatus()";
      FBeans beans = null;
      String SQL=SQL_SQL_AMOUNT_RECV_BY_STATUS;//DA THEM BLOCK
      PreparedStatement prstm = null;
      FMain bean=null;    
      ResultSet rs = null;
      try{
          
          if(beanAssign.getStatusIds()==null || beanAssign.getStatusIds().equals("")){
              beanAssign.setStatusIds("1000");
          }
        List params = new ArrayList();       
        if(obServer>0){
            SQL=SQL_SQL_AMOUNT_RECV_BY_STATUS_OBSERVER;
        }else{   
            
            SQL=SQL.replaceAll("#",beanAssign.getStatusIds());
            params.add(beanAssign.getMeId());
        }
          
        //////.println(SQL);
        prstm = prepareStatement(cnn,SQL + ORDER_BY + TABLE_STATUS + STOP + STATUS_ID + ASC,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL,params));
        while((rs != null) && (rs.next())){
            bean =new FMain();            
            bean.setStatusId(rs.getInt(STATUS_ID));
            bean.setName(rs.getString(STATUS_NAME));
            bean.setAmount(rs.getInt("AMOUNT"));
            if(obServer>0){
                bean.setDecription(rs.getString(STATUS_DESCRIPTION));
            }
            beans.add(bean);           
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
    
    
    public FMain getDocRecvByRead(Connection cnn,FDocAssign beanAssign,int obServer,String statuses,int checkWait) throws EException{
      final String LOCATION = this.toString() + "getDocRecvByRead()";
      String SQL=SQL_SQL_AMOUNT_RECV_BY_READED;//DA THEM BLOCK=0
      //.println(SQL_SQL_AMOUNT_RECV_BY_READED);
      PreparedStatement prstm = null;
      FMain bean = new FMain();    
      ResultSet rs = null;
      try{        
        List params = new ArrayList();
        if(obServer>0){
            SQL=SQL_SQL_AMOUNT_RECV_BY_READED_OBSERVER;   
         //   params.add(beanAssign.getStatusIds());   
        }else{
            params.add(beanAssign.getMeId());
           // params.add(1);           
        }
        //.println(SQL);  
        prstm = prepareStatement(cnn,SQL,params);         
        rs = prstm.executeQuery();      
        while((rs != null) && (rs.next())){
                bean.setAmountRead(1);
                //bean.setAmountUnRead(rs.getInt(PARAM_01));
            bean.setAmount(rs.getInt(PARAM_01));
        }
        bean.setAmount(getAmountAllDocRecv(cnn,beanAssign.getMeId(),obServer,statuses,checkWait));
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }    
    
    public int getAmountAllDocRecv(Connection cnn,long userId,int obServer,String statuses,int checkWait) throws EException{
      final String LOCATION = this.toString() + "getAmountAllDocRecv()";    
      PreparedStatement prstm = null;
      int result = 0;    
      ResultSet rs = null;
      try{        
        List params = new ArrayList();
        String SQL = "";
        if (AppConfigs.DOC_STATUS_COUNT_NOT_IN==1){
               
             SQL = SQL_SQL_AMOUNT_ALL_RECV + SQL_SQL_AMOUNT_ALL_RECV_ADD_ALL_WHERE ;//+ AND + "READED<>0";
             SQL = SQL.replaceAll("#",(statuses!=null && !statuses.equals(""))?statuses:"1000");       
             if (checkWait==1){
                 SQL+= AND + DOC_TRAILER_RECV_READED + DIFF + 0 ;
             }
        }else{
            SQL = SQL_SQL_AMOUNT_ALL_RECV ;
        }
       
        if (obServer>0){
            SQL = SQL_SQL_AMOUNT_ALL_RECV_OBSERVER;
            
        }else{
            params.add(userId);                       
        }   
        
        //.println(SQL);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
        if((rs != null) && (rs.next())){
            result = (rs.getInt("AMOUNT"));
        } 
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return result;        
    }   
    
    
     public FBeans getAmountOfStatus(Connection cnn,int type,long userId,int obServer,String statusIds) throws EException{
      final String LOCATION = this.toString() + "getAmountOfStatus()";
      FBeans beans = null;
      String SQL="";
      PreparedStatement prstm = null;
      FMain bean=null;    
      ResultSet rs = null;
      try{
          if (!(statusIds!=null && !statusIds.equals(""))){
              statusIds="10000";
          }
        List params = new ArrayList();
            if(type==2){
                  if(obServer>0){
                      SQL=SQL_SQL_AMOUNT_SEND_DT_BY_STATUS_OBSERVER;
                  }else{
                      SQL=SQL_SQL_AMOUNT_SEND_DT_BY_STATUS;//DA THEM BLOCK
                      params.add(userId);
                      params.add(statusIds);            
                      
                  }
          }else{
              if(obServer>0){
                  SQL=SQL_SQL_AMOUNT_SEND_BY_STATUS_OBSERVER;
              }else{               
                  SQL=SQL_SQL_AMOUNT_SEND_BY_STATUS.replaceAll("#",statusIds);
                  params.add(userId);
              }
          }
        //////.println(SQL);  
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean =new FMain();            
            bean.setStatusId(rs.getInt(STATUS_ID));
            bean.setName(rs.getString(STATUS_NAME));
            bean.setAmount(rs.getInt("AMOUNT"));
            if (obServer>0){
                bean.setDecription(rs.getString(STATUS_DESCRIPTION));
            }
            beans.add(bean);           
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
    public FMain getDocsendRead(Connection cnn,int type,long userId,int obServer,int checkWaitSend,String status) throws EException{
      final String LOCATION = this.toString() + "getDocsendRead()";
      FBeans beans = null;
      String SQL="";
      PreparedStatement prstm = null;
      FMain bean  = new FMain();    
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        if(type==2){
                if(obServer>0){
                    SQL=SQL_SQL_AMOUNT_SEND_DT_BY_OBSERVER;
                }else{
                    SQL=SQL_SQL_AMOUNT_ALL_SEND_DT;//DA THEM BLOCK
                    params.add(userId);
                }
        }else{
            if(obServer>0){
                SQL=SQL_SQL_AMOUNT_SEND_BY_READED_OBSERVER;
            }else{                
                 if (AppConfigs.DOC_STATUS_COUNT_NOT_IN==1){
                    SQL = SQL_AMOUNT_ALL_SEND.replaceAll("#",SQL_SQL_AMOUNT_SEND_BY_READED_WHERE); 
                    SQL=SQL.replaceAll("#",status!=null && !status.equals("")?status:"1000");
                 }else{
                     SQL = SQL_AMOUNT_ALL_SEND.replaceAll("#",SQL_SQL_AMOUNT_SEND_BY_READED_WHERE); 
                     SQL=SQL.replaceAll("#","1000");
                 }
                params.add(userId);
            }
        }
        //.println(SQL);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        while((rs != null) && (rs.next())){
                bean.setAmountRead(1);
                bean.setAmount(rs.getInt(PARAM_01));
        }
       
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    public FBeans getTotalWaitSend(Connection cnn,long userId) throws EException{
      final String LOCATION = this.toString() + "getTotalWaitSend()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FMain bean  = null;
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        String SQL=SQL_SQL_AMOUNT_SEND_WAIT_AND_NOTINCHANGE;//DA SUA
        params.add(userId);
        //println(SQL);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        while((rs != null) && (rs.next())){
                bean=new FMain();
                bean.setId(rs.getInt(PARAM_01));       
                bean.setName(rs.getString(PARAM_02));
                bean.setAmount(rs.getInt(PARAM_03));
                beans.add(bean);
        }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FBeans getTotalWaitRecv(Connection cnn,long userId) throws EException{
      final String LOCATION = this.toString() + "getTotalWaitRecv()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FMain bean  = null;
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        String SQL=SQL_SQL_AMOUNT_RECV_WAIT_AND_NOTINCHANGE;
        //.println(SQL);
        params.add(userId);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean = new FMain();
            bean.setId(rs.getInt(PARAM_01));       
            bean.setName(rs.getString(PARAM_02));
            bean.setAmount(rs.getInt(PARAM_03));
            beans.add(bean);
        }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FBeans getPortletClose(Connection cnn,long userId) throws EException{
      final String LOCATION = this.toString() + "getPortletClose()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FMain bean=null;    
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        params.add(userId);
        params.add(2);
        params.add(1);
        prstm = prepareStatement(cnn,SQL_SELECT_PORTALET_CLOSE,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean =new FMain();            
            bean.setMenuId(rs.getString(MENU_ID));
            bean.setName(rs.getString(MENU_TITLE));
            beans.add(bean);           
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
   
}
