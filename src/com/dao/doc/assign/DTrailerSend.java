package com.dao.doc.assign;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.assign.FDocAssign;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTrailerSend extends DSqlForYou
{
  
    public String  getUserName(Connection cnn, int userId) throws EException
    {
      final String LOCATION = this.toString() + "getUserName()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String result = "";
          
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_CREATOR_NAME);         
            prstm.setLong(PARAM_01,userId);       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next()))
            {            
                result = rs.getString(USERS_FULLNAME);
                
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
      return result;        
    }    
    
    public FBeans getDocAssignByDocId(Connection cnn, FDocAssign bean,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getDocAssignByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      
      try
      {
          List params =new ArrayList();
          String SQL ="";         
          if (bean.getCheckDocTranfer()==1 || bean.getObServer()==1){
              SQL = SQL_SELECT_DOC_TRAILER_SEND_MOVE_PUBLIC;
              params.add(bean.getDocId());              
          }else if( bean.getCheckDocTranfer()==2){
              SQL = SQL_SELECT_DOC_TRAILER_SEND_MOVE_PRIVATE;
              params.add(bean.getDocId());
              params.add(userId);
              params.add(userId);              
              params.add(userId);
              params.add(bean.getWorkflowId());
          }
          ////.println(SQL);
          prpstm = prepareStatement(cnn,SQL,params); 
          prpstm.setFetchSize((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
          prpstm.setMaxRows((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
          rs = prpstm.executeQuery();
          beans = new FBeans();
        String time = "";
          FDocAssign beansend =null;
          FDocAssign beanrecv =null;
        while(rs != null && rs.next())
        {
            long now = rs.getTimestamp(DOC_TRAILER_SEND_TIMESEND).getTime();
            if(!time.equals(rs.getInt(DOC_TRAILER_SEND_USERSEND_ID) + "|" + now)){
                beansend =new FDocAssign();
                beansend=getInformationAssignSend(rs);           
                if(beansend!=null) beans.add(beansend);
            }else{
                beanrecv =new FDocAssign();
                beanrecv=getInformationAssignSend(rs);           
                if(beanrecv!=null) beansend.setRecvUser(beanrecv);
            }
            time = beansend.getUsersAssign()+ "|" + now;
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
    
    public FDocAssign getInformationAssignSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformationAssignSend()";
        FDocAssign beanTemp = new FDocAssign();
         try {   
                 beanTemp.setDocId(rs.getInt(DOC_TRAILER_SEND_DOC_ID));
                 beanTemp.setUsersAssign(rs.getInt(DOC_TRAILER_SEND_USERSEND_ID));
                 beanTemp.setUsersRecv(rs.getInt(DOC_TRAILER_SEND_USERRECV_ID));
                 beanTemp.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
                 beanTemp.setTimeSend(beanTemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_SEND_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
                 beanTemp.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
                 beanTemp.setViews(rs.getInt("VIEWS"));
                 beanTemp.setSendUserName(rs.getString("USERSEND"));
                 beanTemp.setRecvUserName(rs.getString("USERRECV"));
                 beanTemp.setNameStatus(rs.getString(STATUS_NAME));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
   
    public boolean checkExitsDocId_In_Doc_trailer_send(Connection conn,int docId)throws EException{
    final String LOCATION = "->checkExitsDocId_In_Doc_trailer_send()";
    boolean result = false; 
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_BY_DOC_ID_DOC_TRAILER_SEND_CHECK);    
          pstmt.setInt(PARAM_01,docId);
          rs = pstmt.executeQuery();
          result = (rs!=null) && (rs.next());
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
    public void updateReaded(Connection cnn,int id,int readed,long userId,int foryouId) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;   
     
      boolean result = false;
      try
      {
                 
            prstm = cnn.prepareStatement(UPDATE + TABLE_DOC_TRAILER_SEND +   SET +   DOC_TRAILER_SEND_READED + EQUAL + QUESTION +  WHERE +  DOC_TRAILER_SEND_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_SEND_USERRECV_ID + EQUAL + QUESTION);
            //.println(UPDATE + TABLE_DOC_TRAILER_SEND +   SET +   DOC_TRAILER_SEND_READED + EQUAL + QUESTION +  WHERE +  DOC_TRAILER_SEND_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_SEND_USERRECV_ID + EQUAL + QUESTION);
            prstm.setInt(PARAM_01,readed);
            prstm.setInt(PARAM_02,id);
            prstm.setLong(PARAM_03,userId);
            result = prstm.executeUpdate()>0;
            
            if (foryouId>0){             
                
                prstm = cnn.prepareStatement(SQL_UPDATE_READED_FOR_PEOPLE_SEND_FORYOU);                
                prstm.setInt(PARAM_01,readed);
                prstm.setInt(PARAM_02,id);
                prstm.setLong(PARAM_03,userId);
                prstm.setLong(PARAM_04,foryouId);
                prstm.setInt(PARAM_05,id);
                result = prstm.executeUpdate()>0;
                
                //update read nguoi gui cho nguoi  uy quen
                 prstm = cnn.prepareStatement(SQL_UPDATE_READED_FOR_PEOPLE_SEND_TO_FORYOU);  
                 //.println(SQL_UPDATE_READED_FOR_PEOPLE_SEND_FORYOU);
                 prstm.setInt(PARAM_01,readed);
                 prstm.setInt(PARAM_02,id);
                 prstm.setInt(PARAM_03,id);
                 prstm.setLong(PARAM_04,userId);
                 prstm.setLong(PARAM_05,foryouId);
                 prstm.setInt(PARAM_06,id);
                 result = prstm.executeUpdate()>0;
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
    }
    
    public int  getUserForYou(Connection cnn, int userId,FSeed seed,int id) throws EException
    {
      final String LOCATION = this.toString() + "getUserForYou()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      int result = 0;
          
      try { 
           String SQL = SELECT + FORYOU_USER_ID_TO + FROM + TABLE_FORYOU + WHERE + FORYOU_STATUS + DIFF + 0 + AND + FORYOU_WORKFLOW_ID + EQUAL + 2 + AND + FORYOU_DATEFROM + "<=" + QUESTION + AND + FORYOU_DATETO + ">=" + QUESTION + AND + FORYOU_USER_ID_FROM + EQUAL + OPEN + SELECT + DOC_TRAILER_SEND_USERRECV_ID + FROM + TABLE_DOC_TRAILER_SEND + WHERE +  DOC_TRAILER_SEND_USERSEND_ID + EQUAL + QUESTION + AND + DOC_TRAILER_SEND_USERRECV_ID  + DIFF + QUESTION  + AND + DOC_TRAILER_SEND_DOC_ID + EQUAL + QUESTION + CLOSE;
           //.println(SQL);
            prstm = cnn.prepareStatement(SQL);         
            prstm.setDate(PARAM_01,seed.getCurrentSqlDate());  
            prstm.setDate(PARAM_02,seed.addDays(seed.getCurrentSqlDate(),1)); 
            prstm.setInt(PARAM_03,userId);     
            prstm.setInt(PARAM_04,userId);     
            prstm.setInt(PARAM_05,id);     
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next()))
            {            
                result = rs.getInt(FORYOU_USER_ID_TO);
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
      return result;        
    }    
    
    
    
    
    public void updateReadedOfSendPeople(Connection cnn,int id,int readed,int userId) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      try
      {
            prstm = cnn.prepareStatement(UPDATE + TABLE_DOC_TRAILER_SEND +   SET +   DOC_TRAILER_SEND_READED + EQUAL + QUESTION +  WHERE +  DOC_TRAILER_SEND_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_SEND_USERSEND_ID + EQUAL + QUESTION);
            prstm.setInt(PARAM_01,readed);
            prstm.setInt(PARAM_02,id);
            prstm.setInt(PARAM_03,userId);
            prstm.executeUpdate();
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
    }
}
