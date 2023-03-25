package com.dao.doc.assign;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.doc.assign.FDocAssign;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTrailerRecv extends DSqlForYou
{
    public void updateReadedOfSendPeople(Connection cnn,int id,int readed,int userId) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      try
      {
            prstm = cnn.prepareStatement(UPDATE + TABLE_DOC_TRAILER_RECV +   SET +   DOC_TRAILER_RECV_READED + EQUAL + QUESTION +  WHERE +  DOC_TRAILER_RECV_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_RECV_USERSEND_ID + EQUAL + QUESTION);
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
            if((rs != null) && (rs.next())){            
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
    
    public FBeans getDocRecvByDocId(Connection cnn, FDocAssign bean,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getDocRecvByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      
      try
      {   
          List params =new ArrayList();
          String SQL ="";         
          if (bean.getCheckDocTranfer()==1 || bean.getObServer()==1){//Xem mo rong
              SQL = SQL_SELECT_DOC_TRAILER_RECV_MOVE_PUBLIC;
              params.add(bean.getDocId());                       
          }else if( bean.getCheckDocTranfer()==2){//Xem ca nhan
              SQL = SQL_SELECT_DOC_TRAILER_RECV_MOVE_PRIVATE;
              params.add(bean.getDocId());
              params.add(userId);
              params.add(userId);              
              params.add(userId);
              params.add(bean.getWorkflowId());
          }
         
          
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
            long now = rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime();
            if(!time.equals(rs.getInt(DOC_TRAILER_RECV_USERSEND_ID) + "|" + now)){
                beansend =new FDocAssign();
                beansend=getInformationRecvSend(rs);
                if(beansend!=null) beans.add(beansend);
            }else{
                beanrecv =new FDocAssign();
                beanrecv=getInformationRecvSend(rs);
                if(beanrecv!=null) beansend.setRecvUser(beanrecv);
            }
            time = beansend.getUsersAssign()+ "|" +now;
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
   
    
    public FDocAssign getInformationRecvSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformationRecvSend()";
        FDocAssign beanTemp = new FDocAssign();
         try {   
                 beanTemp.setDocId(rs.getInt(DOC_TRAILER_RECV_DOC_ID));
    
                 beanTemp.setTimeSend(beanTemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
                 beanTemp.setUsersAssign(rs.getInt(DOC_TRAILER_RECV_USERSEND_ID));
    
                 beanTemp.setUsersRecv(rs.getInt(DOC_TRAILER_RECV_USERRECV_ID));
                 beanTemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                 beanTemp.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
                 beanTemp.setViews(rs.getInt(DOC_TRAILER_RECV_VIEWS));
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
    public boolean checkExitsDocId_In_Doc_trailer_recv(Connection conn,int docId)throws EException{
    final String LOCATION = "->checkExitsDocId_In_Doc_trailer_recv()";
    boolean result = false; 
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_BY_DOC_ID_DOC_TRAILER_RECV_CHECK);    
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
    
   
}
