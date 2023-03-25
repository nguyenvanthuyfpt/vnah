//package com.dao.doc;
//
//import com.dao.admin.doc.rules.DDocRules;
//import com.dao.foryou.DSqlForYou;
//import com.form.FSeed;
//import com.exp.EException;
//import com.form.doc.assign.FDocAssign;
//import com.form.doc.docssend.FDocssend;
//import com.lib.AppConfigs;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class DUpdateReaded extends DSqlForYou
//{
//    public String getConvertParamsToString(List params) throws EException{
//        String result="";
//        for(int i=0;i<params.size();i++){
//            if(!result.equals("")) result+=",";
//            result+=params.get(i);
//        }
//        return result;
//    }
//    
//    public boolean updateRead(Connection cnn,int docId,int readed,int workflowId,String members ) throws EException
//    {
//      final String LOCATION = this.toString() + INSERT;
//      boolean result = false;     
//      try
//      {  
//          String SQL_UPDATE_REAED_DOC_TRAILER_SEND=UPDATE + TABLE_DOC_TRAILER_SEND + SET + DOC_TRAILER_SEND_READED + EQUAL + QUESTION + WHERE + DOC_TRAILER_SEND_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_SEND_USERRECV_ID + IN + OPEN + members + CLOSE;
//          String SQL_UPDATE_REAED_DOC_TRAILER_RECV=UPDATE + TABLE_DOC_TRAILER_RECV + SET + DOC_TRAILER_RECV_READED + EQUAL + QUESTION + WHERE + DOC_TRAILER_RECV_DOC_ID + EQUAL + QUESTION + AND + DOC_TRAILER_RECV_USERRECV_ID + IN + OPEN + members + CLOSE;
//          String SQL=workflowId==1?SQL_UPDATE_REAED_DOC_TRAILER_RECV:SQL_UPDATE_REAED_DOC_TRAILER_SEND;
//          List params =new ArrayList();
//          params.add(readed);
//          params.add(docId);
//          result =execute(cnn,SQL,params)>0;
//      }
//      catch(Exception sqle)
//      {
//      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      return result;
//    }
//    
//    public boolean updateReadReply(Connection cnn,int docId,int readed,int workflowId,long userReply,String members) throws EException
//    {
//      final String LOCATION = this.toString() + INSERT;
//      boolean result = false;     
//      try
//      {  
//          if(userReply>0 && docId>0 && workflowId>0){
//              List params =new ArrayList();
//              params.add(userReply);
//              result=updateRead(cnn,docId,readed,workflowId,members);            
//          }
//      }
//      catch(Exception sqle)
//      {
//      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      return result;
//    } 
//    
//    public boolean updateReadFast(Connection cnn,int ruleId,int docId,int readed,int workflowId) throws EException
//    {
//      final String LOCATION = this.toString() + INSERT;
//      boolean result = false;     
//      try
//      {  
//          if(ruleId>0 && docId>0 && workflowId>0){
//              List params =new ArrayList();
//              params.add(new DDocRules().getUserOfficerByRuleId(cnn,ruleId));
//              result=updateRead(cnn,docId,readed,workflowId,params);            
//          }
//      }
//      catch(Exception sqle)
//      {
//      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      return result;
//    }
//   
//   
//    public boolean updateReadPeopleRecv(Connection cnn,String userRecvId,int docId,int readed,int workflowId) throws EException
//    {
//      final String LOCATION = this.toString() + INSERT;
//      boolean result = false;     
//      try
//      {  
//          if(userRecvId!=null  && !userRecvId.equals("") && docId>0 && workflowId>0){
//              List params =new ArrayList();
//              String[] disposeUsers=null;
//              disposeUsers=userRecvId.split(",");
//              for(int i=0;i<disposeUsers.length;i++){
//                  if (disposeUsers[i]!=null && !disposeUsers[i].equals("")){
//                     //disposeUsers[i].split("#")[1];
//                  }
//              }
//              result=updateRead(cnn,docId,readed,workflowId,params);            
//          }
//      }
//      catch(Exception sqle)
//      {
//      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      return result;
//    }
//  
//    
//}
