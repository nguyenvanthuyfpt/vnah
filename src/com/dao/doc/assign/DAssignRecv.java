package com.dao.doc.assign;


import com.dao.admin.doc.category.transfer.DTransfer;
import com.dao.admin.doc.rules.DDocRules;
import com.dao.admin.users.DUsers;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.category.transfer.FTransfer;
import com.form.admin.groups.FGroup;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsrecv.FDocsrecv;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class DAssignRecv extends DSqlForYou
{
    public int getUserRecvDoc(Connection cnn,int docId,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getUserRecvDoc()";
        int result =0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL_USER_RECV_DOCRECV);
          prstm.setLong(PARAM_01,docId);
          prstm.setLong(PARAM_02,userId);
          rs = prstm.executeQuery();       
         
          if((rs != null) && rs.next()){ 
              result = rs.getInt(PARAM_01);
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
    public FBeans getAllRecordByRule(Connection cnn,FDocAssign bean,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getAllRecordByRule()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
     
      try
      {
           FBeans beansTranfer = new FBeans();                                   
           beansTranfer = new DTransfer().getAllTransfer(cnn);
//           //.println(SQL_DEPARTMENT_BY_DOC_RULE_RECV);
            prstm = cnn.prepareStatement(SQL_DEPARTMENT_BY_DOC_RULE_RECV);
            prstm.setLong(PARAM_01,userId);
            prstm.setInt(PARAM_02,bean.getRuleId());
            prstm.setInt(PARAM_03,bean.getWorkflowId());
            prstm.setLong(PARAM_04,userId);
            prstm.setInt(PARAM_05,bean.getRuleId());         
             prstm.setInt(PARAM_06,bean.getWorkflowId());
            rs = prstm.executeQuery();  
            FDepartment beantemp = null;
            beans = new FBeans();
            while((rs != null) && rs.next()) {
                beantemp = new FDepartment();
                beantemp.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
                beantemp.setName(rs.getString(DEPARTMENTS_NAME));
                beantemp.setTranfers(new FBeans());                            
                beantemp.getTranfers().add(getTransferDeparment(cnn,beantemp,beansTranfer)); 
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
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FDepartment getTransferDeparment(Connection cnn,FDepartment bean,FBeans beans) {     
     
        FTransfer beanTemp = null;
        bean.setTranfers(new FBeans());                      
        if (beans!=null){
            for (int i=0;i<beans.size();i++){
                beanTemp =new FTransfer();
                beanTemp = (FTransfer)beans.get(i);                
                bean.getTranfers().add(beanTemp); 
            }
        }    
      return bean;        
    }
    public boolean updateReadedReply(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          FDocAssign bean = (FDocAssign)seed;
          updateReadedMe(cnn,bean);          
          if (AppConfigs.DOC_READ_EXCUTE==1 && bean.getUserReply()!=null && !bean.getUserReply().equals("")){
              updateReadedRecvReply(cnn,bean); 
          }
         
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    
    public boolean updateReadedMe(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(1);
          params.add(bean.getDocId());         
          params.add(bean.getMeId());
          result =  execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_ME,params)>0;//da sua         
          if (AppConfigs.DOC_READ_EXCUTE==1){  
               updateReadedForYou(cnn,bean);  
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    
    public boolean updateReadedForYou(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
              List params = new ArrayList();   
               if (bean.getForYouId()>0 ){
                   params = new ArrayList();  
                   params.add(1);
                   params.add(bean.getDocId());
                   params.add(bean.getForyouCreator());//             
                   result = execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_ME,params)>0;//da sua                        
               }else{
                   if (bean.getIndexTrailer()==1){ // Chuyen lai 
                   
                       params = new ArrayList();                 
                       params.add(0);
                       params.add(bean.getDocId());
                       params.add((bean.getUserReply()!=null && !bean.getUserReply().equals(""))?Integer.parseInt(bean.getUserReply().split(",")[0]):0);                                            
                       params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                       params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                       execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_TO_FORYOU_INDEX1,params);                         
                       
                   }else if (bean.getIndexTrailer()==2){ // Chon nguoi nhan
                       String[] value = bean.getMembers().split(",");
                       String userRecv ="";
                       for (int i =0;i<value.length;i++){   
                            if (value[i]!=null && !value[i].equals("")){   
                              if (!userRecv.equals("")) userRecv +=",";
                                userRecv +=Integer.parseInt(value[i].split("#")[1]);
                            }
                        }
                        params = new ArrayList();                 
                        params.add(0);
                        params.add(bean.getDocId());  
                        params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                        params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                        execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_TO_FORYOU_INDEX2.replaceAll("#",!userRecv.equals("")?userRecv:0+""),params);                         
                       
                   }else if (bean.getIndexTrailer()==3){ // chuyen truc tiep
                     
                       String SQL_USER_RECV = bean.getDepartmentId()>0?SQL_SELECT_ALL_USER_PRIO_ADD_BY_DEPARTMENT_ID_SEND_USER_RECV:SQL_SELECT_ALL_USER_PRIO_SEND_USER_RECV;
                       params = new ArrayList();                 
                       params.add(0);
                       params.add(bean.getDocId());                         
                       if ( bean.getDepartmentId()>0){
                           params.add(bean.getDepartmentId());
                           params.add(AppConfigs.DOCSRECV_WORKFLOWID);
                           params.add(bean.getMeId());
                       }else {
                           params.add(AppConfigs.DOCSRECV_WORKFLOWID);
                           params.add(bean.getMeId());
                       }    
                       params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                       params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                       execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_TO_FORYOU_INDEX2.replaceAll("#",SQL_USER_RECV),params); 
                   }   
               }
           
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    
    public boolean updateStatus(Connection cnn,int id,int meId,int userReply) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(0);
          params.add(id);        
          params.add(userReply);                  
          result =  execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_FORYOU_RECV,params)>0;//DA SUA
        
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    public boolean updateReadedRecvReply(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try{ 
          List params = new ArrayList();       
          String[] userReply =bean.getUserReply().split(",");
          params.add(0);
          params.add(bean.getDocId());        
          params.add(Integer.parseInt(userReply[0].toString()));                        
          result =  execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_FORYOU_RECV,params)>0;//DA SUA        
           if (userReply.length>1 &&  Integer.parseInt(userReply[1])>0){
               params = new ArrayList();    
               params.add(0);
               params.add(bean.getDocId());  
               params.add(Integer.parseInt(userReply[1]));                
               execute(cnn,SQL_UPDATE_READED_RECV_FOR_FORYOU,params);//DA SUA 
           }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    public boolean updateReadedAssignRecv(Connection cnn,FDocAssign beanAssign) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      String SQL=SQL_UPDATE_READED_RECV_FOR_PEOPLE_RECV;//DA SUA
      try
      {  
          List params = new ArrayList();          
          params.add(beanAssign.getReaded());
          params.add(beanAssign.getDocId());
          if(beanAssign.getDisposeUser()==null){
              params.add(beanAssign.getWorkflowId());
              params.add( beanAssign.getForYouId()>0?beanAssign.getForyouCreator():beanAssign.getMeId());
              SQL=SQL.replaceAll("#",SQL_SELECT_PEOPLE_PRIORITIES);
          }else{
              String recv="";
              for (int i =0;i<beanAssign.getDisposeUser().length;i++){
                    if (beanAssign.getDisposeUser()[i]!=null && !beanAssign.getDisposeUser()[i].equals("")){
                              String temp = beanAssign.getDisposeUser()[i];
                              String[] value = temp.split("#");                             
                              if (!recv.equals("")) recv += ",";            
                                    recv += value[1]+"";
                              
                     }
              }
              SQL=SQL.replaceAll("#",recv.equals("")?"0":recv);
          }
          //.println(SQL);
          result =  execute(cnn,SQL,params)>0;                   
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    

    public FBeans getAllGroupByRuleAndTranfer(Connection cnn,long userId,int workFolowId) throws EException
    {
        final String LOCATION = this.toString() + "getAllGroupByRuleAndTranfer()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        
        try
        {//+ SQL_DEPARTMENT_ORDER_BY_TREE
         
         FBeans beansTranfer = new FBeans();                                   
         beansTranfer = new DTransfer().getAllTransfer(cnn);
         
         
          String SQL = SQL_GROUP_SELECT_ALL_GROUP_IN_RULE + SQL_GROUP_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_GROUP_SELECT_ADD_WHERE_IN_DOC_RULE) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,workFolowId);
          rs = prstm.executeQuery();       
          FGroup bean = null;
          while((rs != null) && rs.next()){ 
              
               bean = new FGroup();
               bean.setId(rs.getInt(GROUPS_GROUP_ID));
               bean.setName(rs.getString(GROUPS_NAME));
               bean.setParentID(rs.getInt(GROUPS_PARENT_ID)); 
               bean.setTranfers(new FBeans());                            
               bean.getTranfers().add(getTransferGroup(cnn,bean,beansTranfer));                 
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
   
    public FGroup getTransferGroup(Connection cnn,FGroup bean,FBeans beans) {     
     
        FTransfer beanTemp = null;
        bean.setTranfers(new FBeans());                      
        if (beans!=null){
            for (int i=0;i<beans.size();i++){
                beanTemp =new FTransfer();
                beanTemp = (FTransfer)beans.get(i);                
                bean.getTranfers().add(beanTemp); 
            }
        }    
      return bean;        
    }
    
    
    public FBeans getAllDepartments(Connection cnn, long userId) throws EException
    {
      return getAllSubDepartments(cnn,0,userId);    
    }
    public FBeans getAllSubDepartments(Connection cnn, int idDepartment,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT_IN_RULE + SQL_DEPARTMENT_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_DEPARTMENT_SELECT_ADD_WHERE_IN_DOC_RULE_PRIORITIES) ;        
          
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,AppConfigs.DOCSRECV_WORKFLOWID);
          rs = prstm.executeQuery();       
          FDepartment bean = null;
          while((rs != null) && rs.next()){ 
               bean = new FDepartment();
               bean.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
               bean.setName(rs.getString(DEPARTMENTS_NAME));
               bean.setParentID(rs.getInt(DEPARTMENTS_PARENT_ID));                
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
    
    
    public FBeans getAllGroupsByRule(Connection cnn,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllGroupsByRule()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {//+ SQL_DEPARTMENT_ORDER_BY_TREE
          String SQL = SQL_GROUP_SELECT_ALL_GROUP_IN_RULE + SQL_GROUP_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_GROUP_SELECT_ADD_WHERE_IN_DOC_RULE) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,AppConfigs.DOCSRECV_WORKFLOWID);
          rs = prstm.executeQuery();       
          FGroup bean = null;
          while((rs != null) && rs.next()){ 
               bean = new FGroup();
               bean.setId(rs.getInt(GROUPS_GROUP_ID));
               bean.setName(rs.getString(GROUPS_NAME));
               bean.setParentID(rs.getInt(GROUPS_PARENT_ID));                
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
    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed,int depId,int groupId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByGroupId()";
        FBeans beans = new FBeans();
        FBeans beansTranfer = new FBeans();
        PreparedStatement prstm = null;
        FDocAssign beanC = (FDocAssign)seed;
        ResultSet rs = null;
        try{
            if (beanC.getCheckShowTransfer()==1){                            
                beansTranfer = new DTransfer().getAllTransfer(cnn);
            }
        String SQL =SELECT +  TABLE_USERS + STOP + USERS_USER_ID + COMMA + TABLE_USERS + STOP + USERS_FULLNAME +  FROM  + TABLE_USERS + WHERE + USERS_USER_ID + IN + OPEN + SELECT +  TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_USER_ID +  FROM +  TABLE_DOC_OFFICERS +  LEFT_JOIN +  TABLE_DOC_RULES + ON + TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_RULE_ID + EQUAL  + TABLE_DOC_RULES + STOP + DOC_RULES_RULE_ID + LEFT_JOIN + TABLE_DOC_BOSS + ON + TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_RULE_ID + EQUAL + TABLE_DOC_BOSS +STOP + DOC_BOSS_RULE_ID +  WHERE +  TABLE_DOC_BOSS + STOP + DOC_BOSS_USER_ID + EQUAL + QUESTION +  AND + TABLE_DOC_RULES + STOP + DOC_RULES_ACTIVE + DIFF + 0 +  AND +  TABLE_DOC_RULES + STOP + DOC_RULES_WORKFLOW_ID + EQUAL + 1 +  CLOSE ;
        List params =new ArrayList();
        params.add(beanC.getMeId());
        if(groupId>0){
            SQL+= AND +  USERS_GROUP_ID + EQUAL + QUESTION;
            params.add(groupId);
        }
        if(depId>0){
            SQL+= AND +  USERS_DEPARTMENT_ID + EQUAL + QUESTION;
            params.add(depId);
        }
          prstm = prepareStatement(cnn,SQL + ORDER_BY + USERS_USER_ID,params);   
          rs = prstm.executeQuery();   
         FUser bean= null;
          while((rs != null) && rs.next()){
              bean = new FUser();            
              bean.setId(rs.getInt(USERS_USER_ID));
              bean.setFullName(rs.getString(USERS_FULLNAME));
              if (beanC.getCheckShowTransfer()==1){                  
                 bean.setTranfers(new FBeans());                            
                 bean.getTranfers().add(getTransfer(cnn,bean,beansTranfer));  
              } 
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

    public FUser getTransfer(Connection cnn,FUser bean,FBeans beans) {     
     
      FTransfer beanTemp = null;
        bean.setTranfers(new FBeans());                      
        if (beans!=null){
            for (int i=0;i<beans.size();i++){
                beanTemp =new FTransfer();
                beanTemp = (FTransfer)beans.get(i);                
                bean.getTranfers().add(beanTemp); 
            }
        }    
      return bean;        
    }
    
    
    
    public FBeans getUserByGroupId(Connection cnn, FSeed seed,int groupId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByGroupId()";
        FBeans beans = new FBeans();
        FUser bean= null;;
        PreparedStatement prstm = null;
        FDocAssign beanC = (FDocAssign)seed;
        ResultSet rs = null;
        try{
          
          String SQL =  SQL_USER_BY_GROUP_ID +  SQL_DOC_RULE_JOIN + AND + TABLE_USERS + STOP + USERS_USER_ID + NOT + IN +  OPEN + SQL_DOC_RULE_RECV_ASSIGN_BY_USER + CLOSE ;                  
          prstm = cnn.prepareStatement(SQL);   
          //.println(SQL);
          prstm.setInt(PARAM_01,groupId);   
          prstm.setLong(PARAM_02,beanC.getMeId());   
          prstm.setInt(PARAM_03,beanC.getWorkflowId());
          prstm.setLong(PARAM_04,beanC.getMeId());         
          prstm.setInt(PARAM_05,beanC.getWorkflowId());
          prstm.setLong(PARAM_06,beanC.getMeId());
          prstm.setInt(PARAM_07,groupId);
          prstm.setInt(PARAM_08,beanC.getId());   
          rs = prstm.executeQuery();   
          DUsers daoU = new DUsers(); 
          while((rs != null) && rs.next()){
              bean = new FUser();            
              bean = daoU.getInformation(cnn,rs);      
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
    
    
    
    
    
    public FBeans getDocAssignByDocId(Connection cnn, FDocAssign bean) throws EException 
    {
      final String LOCATION = this.toString() + "getDocAssignByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_DOC_TRAILER_SEND_BY_DOC_ID);
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if((rs != null) && (rs.next()))
        {
            FDocAssign beantemp =new FDocAssign();
            beantemp=getInformationRecvSend(rs);           
            beantemp.setRecvUserName(getUserName(cnn,beantemp.getUsersRecv()));
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
    
    
    public int getReviewIdTop(Connection cnn,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getReviewIdTop()";
        int result = 0;
        PreparedStatement prstm = null;        
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_SELECT_ALL_DOC_REVIEW_RECV_MAX_ID);   
            prstm.setInt(PARAM_01,userId);
          rs = prstm.executeQuery();           
          if((rs != null) && rs.next()){
              result = rs.getInt(PARAM_01);             
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
    

  
    public FBeans getAllReview(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllReview()";
      PreparedStatement prpstm = null;
      FDocAssign   bean = (FDocAssign)seed;
      ResultSet rs = null;
      FBeans beans = null;
        String SQL_SELECT=SQL_SELECT_ALL_DOC_REVIEW_RECV_PUPLIC;
        try{
            List params = new ArrayList();
            if(bean.getCheckViewReview()==1){//xem mo rong
              params.add(bean.getDocId());
            }else{//xem ca nhan
              SQL_SELECT=SQL_SELECT_ALL_DOC_REVIEW_RECV_PRIVATE;
              params.add(bean.getDocId());
              params.add(bean.getMeId());
              params.add(PER_CENT + "|" + bean.getMeId() + "|" + PER_CENT);
            }//SQL_SELECT_ALL_DOC_REVIEW_RECV_BY_DOC_ID
            //.println(SQL_SELECT);
          prpstm = prepareStatement(cnn,SQL_SELECT,params);   
          rs = prpstm.executeQuery();
          beans = new FBeans();       
          FDocAssign beantemp = null;
          int block=0;
          while(rs != null && rs.next()){          
              beantemp = new FDocAssign();
              beantemp = getInformationReviewRecv(rs);
              if(block==0){
              beantemp.setCheckEmp(beantemp.getCreator()!=bean.getMeId()?1:0);
                  if(beantemp.getCheckEmp()==1){
                      block=1;
                  }
              }
              checkReviewForYou(cnn,beantemp);
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
    
    public  void checkReviewForYou(Connection cnn,FDocAssign bean ) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkReviewForYou()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;
      FBeans beans = null;
      int result = 0;
      try
      {
         
          prpstm = cnn.prepareStatement(SQL_SELECT_CHECK_DOC_REVIEW_RECV_FORYOU);   
          //.println(SQL_SELECT_CHECK_DOC_REVIEW_RECV_FORYOU);
          prpstm.setInt(PARAM_01,bean.getDocId());
          prpstm.setInt(PARAM_02,bean.getReviewId());                   
          rs = prpstm.executeQuery();          
          if(rs != null && rs.next()){ 
              bean.setCheckReviewForYou(rs.getInt(DOC_TRAILER_SEND_USERSEND_ID));
              bean.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
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
    }
    
    public  FDocAssign getUserForYou(Connection cnn,int forYouId) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkAsignRule()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;  
      long result = 0 ;
      FDocAssign beanTemp = new FDocAssign();
      try
      {
          
          
          prpstm = cnn.prepareStatement(SQL_SELECT_USER_FROM_FORYOU);   
          prpstm.setInt(PARAM_01,forYouId); 
          prpstm.setDate(PARAM_02,beanTemp.getCurrentSqlDate()); 
          prpstm.setDate(PARAM_03,beanTemp.addDays(beanTemp.getCurrentSqlDate(),1)); 
          rs = prpstm.executeQuery();          
          if(rs != null && rs.next()){             
              beanTemp.setForyouCreator(rs.getInt(FORYOU_USER_ID_FROM));
              beanTemp.setActiveForyou(rs.getInt(FORYOU_STATUS));
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
    
    
    public  FDocAssign checkAsignRule(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkAsignRule()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {
          FDocAssign beanTemp = new FDocAssign();
          if (bean.getForYouId()>0){
              beanTemp = getUserForYou(cnn,bean.getForYouId());
          }else {
              beanTemp.setForyouCreator((int)bean.getMeId());  
          }
         
          
          prpstm = cnn.prepareStatement(SQL_SELECT_DOC_CHECK_RULES);   
//          //.println(SQL_SELECT_DOC_CHECK_RULES);
          prpstm.setInt(PARAM_01,bean.getWorkflowId()); 
          prpstm.setLong(PARAM_02,beanTemp.getForyouCreator());               
          rs = prpstm.executeQuery();          
          if(rs != null && rs.next())
          { 
               bean.setRuleId(rs.getInt(DOC_RULES_RULE_ID));      
               bean.setCheckDirect(rs.getInt(DOC_RULES_DIRECT));      
               bean.setCheckReview(rs.getInt(DOC_RULES_COMMENT)); //Xem y kiem     
               bean.setCheckSelectRecv(rs.getInt(DOC_RULES_SELECT_RECV));      
               bean.setCheckActive(rs.getInt(DOC_RULES_ACTIVE));      
               bean.setStatusId(rs.getInt(DOC_RULES_STATUS_ID));  
               bean.setCheckSelectDept(rs.getInt(DOC_RULES_SELECT_DEPARTMENT));
               bean.setCheckStore(rs.getInt(DOC_RULES_STORE));
               bean.setCheckViewReview(rs.getInt(DOC_RULES_REVIEW));//Duoc y kien (hien thi text len)
               bean.setCheckReply(rs.getInt(DOC_RULES_REPLY));
               bean.setCheckDetail(rs.getInt(DOC_RULES_DETAIL));
               bean.setCheckDeadline(rs.getInt(DOC_RULES_DEADLINE));
               
               String notIncharge = "," + rs.getString(DOC_RULES_NOTINCGARGE) + ",";
               bean.setCheckUnReaded(notIncharge.indexOf(",0,")<0?0:1);
               
               bean.setCheckNotIncharge(new DTransfer().getExitsInStr(cnn,rs.getString(DOC_RULES_NOTINCGARGE)));             
               bean.setStatusIds(rs.getString(DOC_RULES_STATUSES));
               bean.setCheckStoreDrapt(rs.getInt(DOC_RULES_STOREDRAP));
              
               bean.setCheckTcaption(rs.getString(DOC_RULES_TCAPTION));
               bean.setCheckRebcaption(rs.getString(DOC_RULES_REBCAPTION));
              //bean.setCheckDocreference(rs.getInt(DOC_RULES_DOCREFERENCE));
              bean.setCheckRedcaption(rs.getString(DOC_RULES_READCAPTION));
              bean.setCheckRecaption(rs.getString(DOC_RULES_RECAPTION));
               
              bean.setCheckDossier(rs.getInt(DOC_RULES_DOSSIER));
              bean.setCheckDocReply(rs.getInt(DOC_RULES_DOCREPLY));
              bean.setCheckDocTranfer(rs.getInt(DOC_RULES_DOCTRANFERS));
              bean.setCheckUpdateDraft(rs.getInt(DOC_RULES_UPDATEDRAFT)); 
              bean.setCheckReadOnly(rs.getInt(DOC_RULES_READONLY)); 
              
              bean.setCheckReviewFile(rs.getInt(DOC_RULES_REVIEWFILE)); 
              bean.setCheckExcuteNotView(rs.getInt(DOC_RULES_EXCUTENOTVIEW)); 
              bean.setCheckExcuteGroup(rs.getInt(DOC_RULES_EXCUTEGROUP)); 
              //bean.setCheckSendSms(rs.getInt(DOC_RULES_SENDSMS)); 
              bean.setCheckForyouAssign(rs.getInt(DOC_RULES_FORYOUASSIGN)); //uy quyen duoc xu ly
              
              bean.setCheckDefineFileEmit(rs.getInt(DOC_RULES_DEFINEFILEEMIT)); 
              bean.setCheckClassify(rs.getInt(DOC_RULES_CLASSIFY)); 
              bean.setCheckAssign(1);            
              bean.setForyouCreator(beanTemp.getForyouCreator());
              bean.setActiveForyou(beanTemp.getActiveForyou());             
              
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
      return bean;
    }
    
    
    
    
    public FDocAssign getByReviewId(Connection cnn,int reviewId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getByIdReview()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocAssign bean =new FDocAssign();
      try
      {
          List params = new ArrayList();
          params.add(reviewId);
          prpstm =prepareStatement(cnn,SELECT + STAR + FROM + TABLE_DOC_FILE_REVIEW_RECV + WHERE + DOC_FILE_REVIEW_RECV_REVIEW_ID + EQUAL + QUESTION ,params);   
          rs = prpstm.executeQuery();
          if(rs != null && rs.next())
          { 
              bean.setReviewId(rs.getInt(DOC_REVIEW_RECV_REVIEW_ID));
              bean.setFileName(rs.getString(DOC_FILE_REVIEW_RECV_FILES));
              bean.setPathFile(rs.getString(DOC_FILE_REVIEW_RECV_PATH_FILE));
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
      return bean;
    }
    
    public boolean insertDirect(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;      
      FDocAssign   bean = (FDocAssign)seed;
      boolean result =false;
      try
      { 
          
          String SQL_INSERT_DOC_TRAILERS_RECV_ = bean.getDepartmentId()>0?SQL_INSERT_DOC_TRAILER_RECV.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_ADD_BY_DEPARTMENT_ID_RECV):SQL_INSERT_DOC_TRAILER_RECV.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_RECV);                 
          List params = new ArrayList();
          params.add(bean.getDocId());
          params.add(bean.getMeId()); 
          params.add(0); 
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));                    
          params.add(0);
          params.add(bean.getViews());
          params.add(bean.getStatusId());
          params.add(0);
          if (bean.getDepartmentId()>0){
              params.add(bean.getDepartmentId());
              params.add(bean.getWorkflowId());
              params.add(bean.getForYouId()>0?bean.getForyouCreator():bean.getMeId());
          }else {
              params.add(bean.getWorkflowId());
              params.add(bean.getForYouId()>0?bean.getForyouCreator():bean.getMeId());
          }
          params.add(bean.getDocId());                    
          result=execute(cnn,SQL_INSERT_DOC_TRAILERS_RECV_,params)>0;           
          updateReadedMe(cnn,bean);
          if (result) {            
              updateStatusDoc(cnn,seed);
              insertForYou(cnn,seed,bean.getMeId());              
          }else if (AppConfigs.DOC_READ_EXCUTE==1){
               bean.setReaded(0);
               bean.setDisposeUser(null);
               updateReadedAssignRecv(cnn,bean);
          }
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }    
      return result;
    }
    
    
    public boolean insertForYou(Connection cnn,FSeed seed,long userId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;      
      FDocAssign   bean = (FDocAssign)seed;
      ResultSet rs = null;    
      boolean result =false;
      try
      { 
          
          //String SQL_INSERT_DOC_TRAILER_RECV_FORYOU_ = SQL_INSERT_DOC_TRAILER_RECV_FORYOU;         
          //.println(SQL_INSERT_DOC_TRAILER_RECV_FORYOU);
          List params = new ArrayList();
          params.add(bean.getDocId());          
          params.add(0);
          params.add(new Timestamp(System.currentTimeMillis()));                    
          params.add(bean.getViews());
          params.add(bean.getStatusId());
          params.add(0);
          params.add(bean.getWorkflowId());
          params.add(bean.getCurrentSqlDate());
          params.add(bean.getCurrentSqlDate());
          params.add(bean.getDocId());
          params.add(userId);
          
          params.add(bean.getDocId());
          params.add(bean.getWorkflowId()); 
          params.add(bean.getCurrentSqlDate());
          params.add(bean.getCurrentSqlDate());
          params.add(bean.getDocId());
          params.add(userId);
          //.println(SQL_INSERT_DOC_TRAILER_RECV_FORYOU);
          //.println(SQL_INSERT_DOC_TRAILER_RECV_FORYOU);
          result=execute(cnn,SQL_INSERT_DOC_TRAILER_RECV_FORYOU,params)>0;    
          
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }    
      return result;
    }
    
    public String getSelectUserTrailerRecvByDocId(Connection cnn, int docId) throws EException
    {
        final String LOCATION = this.toString() + "usersRecv()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        String usersRecv ="";
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_INSERT_DOC_TRAILER_RECV_BY_DOC_ID);
          prstm.setLong(PARAM_01,docId);         
          rs = prstm.executeQuery();       
          FDepartment bean = null;
          while((rs != null) && rs.next()){ 
            if (!usersRecv.equals("")) usersRecv += ",";            
              usersRecv += rs.getInt(PARAM_01)+"";
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
        return usersRecv;        

    }
  
    
 public boolean insert(Connection cnn,FSeed seed,int meId) throws EException
 {
   final String LOCATION = this.toString() + INSERT;
   boolean result = false;      
   FDocAssign bean = (FDocAssign)seed;
   PreparedStatement prstm = null;
   try
   {  
         prstm = cnn.prepareStatement(SQL_INSERT_DOC_TRAILER_RECV_SELECT);
           String[] value = bean.getMembers().split(",");
          
             for (int i =0;i<value.length;i++){                
               if (value[i]!=null && !value[i].equals("")){
                   if(!checkAddBatch(cnn,bean.getId(),Integer.parseInt(value[i].split("#")[1]))){
                     prstm.setInt(PARAM_01,bean.getId());
                     prstm.setLong(PARAM_02,bean.getMeId());                         
                     prstm.setInt(PARAM_03,0);                     
                     prstm.setTimestamp(PARAM_04,new java.sql.Timestamp(System.currentTimeMillis()));
                     prstm.setInt(PARAM_05,0);
                     prstm.setInt(PARAM_06, Integer.parseInt(value[i].split("#")[0]));   
                     prstm.setInt(PARAM_07,bean.getStatusId());   
                     prstm.setInt(PARAM_08,0);   
                     prstm.setInt(PARAM_09,Integer.parseInt(value[i].split("#")[1]));   
                     prstm.addBatch();   
                   }
               }           
             }  
             
             result=prstm.executeBatch().length>0; 
             updateReadedMe(cnn,bean);
         if (result) {                     
                insertForYou(cnn,seed,meId);
                updateStatusDoc(cnn,seed);
            }else if (AppConfigs.DOC_READ_EXCUTE==1){
               bean.setReaded(0);
               bean.setDisposeUser(value);
               updateReadedAssignRecv(cnn,bean);
            }
                 
        
       }
       catch(Exception sqle)
       {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
       }
       return result;
 } 
    public boolean checkAddBatch(Connection cnn, int docId,int userRecv_id) throws EException
    {
        final String LOCATION = this.toString() + "checkAddBatch()";
        PreparedStatement prstm = null;
        boolean result =false;
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_CHECK_DOC_TRAILER_RECV_ADDBATCH);
          prstm.setLong(PARAM_01,docId);         
            prstm.setLong(PARAM_02,userRecv_id);
            rs = prstm.executeQuery();
            result = (rs!=null) && (rs.next());     
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
    public boolean insertMe(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocsrecv bean = (FDocsrecv)seed;
      try
      {  
        String SQL_INSERT_DOC_TRAILERS_RECV = INSERT_INTO + TABLE_DOC_TRAILER_RECV + FIELDS(DOC_TRAILER_RECV_ALL_FIELDS, true) +  VALUES(DOC_TRAILER_RECV_ALL_FIELDS.length);
        List params = new ArrayList();
        params.add(bean.getId());
        params.add(bean.getUserId()); 
        params.add(1);
        params.add(new java.sql.Timestamp(System.currentTimeMillis()));                    
        params.add(0);
        params.add(0);
        params.add(AppConfigs.STATUS_NEW);
        params.add(0);
        params.add(bean.getUserId());        
        result=execute(cnn,SQL_INSERT_DOC_TRAILERS_RECV,params)>0;  
      }
      catch(Exception sqle)
      {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    } 
    
    public boolean updateStatusDoc(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FDocAssign bean = (FDocAssign)seed;
      try
      {  
         List params = new ArrayList(); 
         params.add(bean.getStatusId());
         params.add(bean.getDocId());                 
         result =  execute(cnn,SQL_UPDATE_DOC_RECV,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    
    public boolean updateReadReview(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {  
         List params = new ArrayList();          
         params.add(bean.getId());                 
         params.add(meId);      
         params.add(bean.getId());                 
         params.add(meId);   
          params.add(meId);   
         result =  execute(cnn,SQL_UPDATE_DOC_RECV_READ_REVIEW,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    
    public boolean insertReview(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {  
          if(bean.getReviewIds()==null){
              bean.setReviewIds(new DDocRules().getUserPrioritoesByRuleId(cnn,bean.getRuleId()));
          }
          List params = setParams(bean);
          result =  execute(cnn,SQL_INSERT_DOC_REVIEW_RECV,params)>0;            
           if (result && bean.getFileUpload()!=null && bean.getFileUpload().getFileSize()>0){
             int reviewId = getReviewIdTop(cnn,meId);
             insertReviewFile(cnn,bean,reviewId);
           }    
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean insertReviewForYou(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {  
          List params = new ArrayList();
          params.add(bean.getId());
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));                             
          params.add(bean.getTitle());
          params.add(bean.getIssue()==null?"":bean.getIssue());
          params.add(meId);
          params.add(bean.getDeadLine()==null?"":bean.getDeadLine());
          params.add(bean.getId());
          params.add(meId);
          result =  execute(cnn,SQL_INSERT_DOC_REVIEW_RECV_FORYOU,params)>0;
          if (result){
              if (bean.getFileUpload()!=null && bean.getFileUpload().getFileSize()>0){
                  int   reviewId = getReviewIdTop(cnn,meId);
                  result = insertReviewFile(cnn,bean,reviewId);
             }
             result = updateReadReview(cnn,bean,meId);
          }
        
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean insertReviewFile(Connection cnn,FSeed seed,int reviewId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      bean.setReviewId(reviewId);
      try
      {  
         List params = setParamsFile(seed);
         result =  execute(cnn,SQL_INSERT_DOC_REVIEW_FILE_RECV,params)>0;
      }catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
   
    public FDocAssign getInformationReviewRecv(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beantemp = new FDocAssign();
         try {             
                 beantemp.setReviewId(rs.getInt(DOC_REVIEW_RECV_REVIEW_ID));
                 beantemp.setDocId(rs.getInt(DOC_REVIEW_RECV_DOC_ID));
                 beantemp.setCreator(rs.getInt(DOC_REVIEW_RECV_CREATOR));
                 beantemp.setTimeCreate(beantemp.dateToString(new Date (rs.getTimestamp(DOC_REVIEW_RECV_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
                 beantemp.setTitle(rs.getString(DOC_REVIEW_RECV_TITLE));
                 beantemp.setIssue(rs.getString(DOC_REVIEW_RECV_ISSUE));
                 beantemp.setFileName(rs.getString(DOC_FILE_REVIEW_RECV_FILES));
                 beantemp.setPathFile(rs.getString(DOC_FILE_REVIEW_RECV_PATH_FILE));
                 beantemp.setIssue(rs.getString(DOC_REVIEW_RECV_ISSUE));
                 beantemp.setNameCreator(rs.getString(USERS_FULLNAME));  
                 beantemp.setForyouCreator(rs.getInt(DOC_REVIEW_RECV_FORYOU_CREATOR));  
                 beantemp.setDeadLine(rs.getString(DOC_REVIEW_RECV_DEADLINE));  
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    
        
    
    public FDocAssign getInformationRecvSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beanTemp = new FDocAssign();
         try {   
                 beanTemp.setDocId(rs.getInt(DOC_TRAILER_RECV_DOC_ID));
                 beanTemp.setUsersAssign(rs.getInt(DOC_TRAILER_RECV_USERSEND_ID));
                 beanTemp.setUsersRecv(rs.getInt(DOC_TRAILER_RECV_USERRECV_ID));
                 beanTemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                 beanTemp.setTimeSend(beanTemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
                 beanTemp.setSendUserName(rs.getString(USERS_FULLNAME));
                 beanTemp.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
    
   
   
    public List setParams(FDocAssign bean) throws EException
    {
        final String LOCATION = "->setParams()";
        //FDocAssign bean = (FDocAssign)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getDocId());
             params.add(bean.getCreator());                           
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                             
             params.add(bean.getTitle());
             params.add(bean.getIssue()==null?"":bean.getIssue());
             params.add(0);    // ForyouCreator
             params.add(bean.getDeadLine()==null?"":bean.getDeadLine());
             params.add(bean.getReviewIds()==null?"":bean.getReviewIds());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    
    public List setParamsFile(FSeed seed) throws EException
    {
        final String LOCATION = "->setParamsFile()";
        FDocAssign bean = (FDocAssign)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getReviewId());
             params.add("filex");                           
             params.add(bean.getFileName());
             params.add(bean.getPathFile());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
   
 
}
