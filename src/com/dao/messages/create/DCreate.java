package com.dao.messages.create;


import com.dao.admin.departments.DDepartments;
import com.dao.admin.users.DUsers;
import com.dao.messages.DSqlMessages;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.users.FUser;
import com.form.messages.create.FCreate;
import com.form.messages.create.FUserExt;

import com.inf.IRoles;
import com.inf.messages.IConstantsMessages;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class DCreate  extends DSqlMessages
{
    public FBeans getAllDepartments(Connection cnn) throws EException
    {
      return getAllSubDepartments(cnn,0);    
    }
    public FBeans getAllSubDepartments(Connection cnn, int idDepartment) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT);
          rs = prstm.executeQuery();
          String members = ",";
          FDepartment bean = null;
          boolean all = idDepartment==0;
          boolean start = false;
          int id = -1; 
              while((rs != null) && (rs.next() && members!=null))
              {
                id = rs.getInt(PARAM_01);
                if((all && members.indexOf("," + id + ",")<0) || (!start && id == idDepartment)){
                   start = true;
                   bean = new FDepartment();
                   bean.setId(id);
                   bean.setName(rs.getString(PARAM_02));
                   bean.setParentID(rs.getInt(PARAM_03));
                   if(id>0){
                        members+= id +  ",";
                        beans.add(bean);
                   }
                }
                if(start){
                    if(all || members.indexOf("," + id + ",")>=0){
                        id = rs.getInt(PARAM_04);
                        bean = new FDepartment();
                        bean.setId(id);
                        bean.setName(rs.getString(PARAM_05));
                        bean.setParentID(rs.getInt(PARAM_06));            
                        if(id>0){
                              members+= id +  ",";
                              beans.add(bean);
                        }
                    }else if(!all){
                        members=null;
                    }
                 }
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

    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed,int departmentId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByDepartmentId()";
        FBeans beans = new FBeans();
        FUserExt bean= null;;
        PreparedStatement prstm = null;
        FCreate beanC = (FCreate)seed;
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_USER_BY_DEPARTMENT_ID + AND +  USERS_ROLE + DIFF + IRoles.RADMINISTRATOR);
          prstm.setInt(PARAM_01,beanC.getDepartmentID()>0?beanC.getDepartmentID():departmentId);   
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
              bean = new FUserExt();
              bean = getInformationUser(cnn,rs);
              bean.setChecked(getUserRevId(cnn,seed,beanC.getCreator(),bean.getId())); 
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
    
    public FUserExt getInformationUser(Connection cnn, ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformationUser()";
        FUserExt user = new FUserExt();
         try {
           user.setId(rs.getInt(USERS_USER_ID));
           user.setUsername(rs.getString(USERS_USERNAME));
           user.setPassword(rs.getString(USERS_PASSWORD));
           user.setFullName(rs.getString(USERS_FULLNAME));                           
           user.setPicture(rs.getString(USERS_PICTURE));
           user.setEmail(rs.getString(USERS_EMAIL));                           
           user.setPhone(rs.getString(USERS_PHONE));
           user.setAddress(rs.getString(USERS_ADDRESS));
           user.setDescription(rs.getString(USERS_DESCRIPTION));

           user.setDateCreate(user.dateToString(rs.getDate(USERS_DATE_CREATE)));
           user.setDatePassword(user.dateToString(rs.getDate(USERS_DATE_PASSWORD)));                           
           user.setDateLogin(user.dateToString(rs.getDate(USERS_DATE_LOGIN)));

           user.setSex(rs.getInt(USERS_SEX));
           user.setDepartmentID(rs.getInt(USERS_DEPARTMENT_ID));
             FDepartment department = new FDepartment();
             department.setId(user.getDepartmentID());
             DDepartments departments = new DDepartments();
             department = departments.getRecordByID(cnn,department);           
           user.setDepartmentName(department.getName());           
           user.setRole(rs.getInt(USERS_ROLE));
           user.setPrivilege(rs.getInt(USERS_PRIVILEGE));
           user.setActive(rs.getInt(USERS_ACTIVE));
           user.setPeriod(rs.getInt(USERS_PERIOD));
           user.setGroupID(rs.getInt(USERS_GROUP_ID));
           user.setApp(rs.getString(USERS_APP));
           user.setChangePassword(rs.getInt(USERS_CHANGE_PASSWORD));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return user;
    }
   

    
    public String getUserRevId(Connection cnn, FSeed seed,int userId,int userIdRev ) throws EException
    {
        final String LOCATION = this.toString() + "getUserRevId()";
        FBeans beans = new FBeans();
        FUser bean= null;;
        PreparedStatement prstm = null;
        FCreate beanC = (FCreate)seed;
        ResultSet rs = null;
        String result = "";
        try  {         
          prstm = cnn.prepareStatement(SQL_USER_IN_REC_TO_PERSON + SQL_USER_WHERE_DEPARTMENT);        
          //.println(SQL_USER_IN_REC_TO_PERSON);
          prstm.setInt(PARAM_01,beanC.getId());  
          prstm.setInt(PARAM_02,userId);
          prstm.setInt(PARAM_03,beanC.getDepartmentID());  
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
            if (rs.getInt(USERS_USER_ID)==userIdRev){
             result = "checked";
             break;
            }
            
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
    public FBeans getUserByMessageId(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByMessageId()";
        FBeans beans = new FBeans();
        FUser bean= null;;
        PreparedStatement prstm = null;
        FCreate beanC = (FCreate)seed;
        ResultSet rs = null;
        try
        {
         
          prstm = cnn.prepareStatement(SQL_USER_IN_REC_TO_PERSON);        
          prstm.setInt(PARAM_01,beanC.getId());  
          prstm.setInt(PARAM_02,userId);            
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
              bean = new FUser();
              DUsers dao = new DUsers(); 
              bean= dao.getInformation(cnn,rs);
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
    
    
    public FBeans getUserByInUserId(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByInUserId()";
        FBeans beans = new FBeans();
        FUser bean= null;;
        FCreate beanC= (FCreate)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
            String usersId = "";
            if (beanC.getEmps()!=null){
                String[] usersIdTemp = beanC.getEmps().split(",");         
                for (int i=0;i<usersIdTemp.length;i++){
                    if (usersIdTemp[i]!="") {
                       if (!usersId.equals("")) usersId += ",";                    
                        usersId += usersIdTemp[i];
                    }                
                }
            }else{
                
            }          
          usersId = !usersId.equals("")?usersId:"-1";   
          prstm = cnn.prepareStatement(SQL_USER_IN_USER_ID + WHERE + USERS_USER_ID + IN + OPEN + usersId + CLOSE + AND + USERS_USER_ID + DIFF + userId);
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
              bean = new FUser();
              DUsers dao = new DUsers(); 
              bean= dao.getInformation(cnn,rs);
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

    public FCreate getRecordById(Connection cnn, FSeed seed,int  userId) throws EException
    {
      final String LOCATION = this.toString() + "getRecordById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FCreate bean = (FCreate)seed;
      FCreate beanM = null; 
      boolean result = false;
      try
      {
        if (userId>0)  result = update(cnn,seed,userId);
        prstm = cnn.prepareStatement(SQL_SELECT_MESSAGES_BY_ID);       
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next())){           
            beanM = getInformation(rs);
            beanM.setType(bean.getType());
            beanM.setEmpsRev(new FBeans());                            
            beanM.getEmpsRev().add(getEmpsRecv(cnn,beanM,bean));      
            beanM.setAllFiles(new DCreateFiles().getAllFileByMessageId(cnn,bean.getId()));                       
            
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
      return beanM;        
    }
    
    public int getTopId(Connection cnn,long userId) throws EException 
    {
      final String LOCATION = this.toString() + "getTopId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      int id=0;
      try
      {
        prpstm = cnn.prepareStatement(SELECT + MAX(MESSAGES_ID) + FROM + TABLE_MESSAGES + WHERE + MESSAGES_CREATOR + EQUAL + QUESTION);
        prpstm.setLong(PARAM_01,userId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
                id=rs.getInt(PARAM_01);
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
      return id;    
    }
    
    
    public FCreate getEmpsRecv(Connection cnn,FCreate bean,FCreate beanC) throws EException{
      final String LOCATION = this.toString() + "getEmpsRecv()";
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      try{
        
        
        FCreate beanTemp = null;
        bean.setEmpsRev(new FBeans());
//        if (bean.getCreator()!=beanC.getCreator() || beanC.getReply()==1){
//          beanTemp =new FCreate();
//          beanTemp.setToPertion(bean.getCreator());
//          beanTemp.setUserFullName(bean.getUserFullName());
//          bean.getEmpsRev().add(beanTemp);   
//        }
          
//         if (beanC.getReply()!=1){
              List params = new ArrayList();    
              params.add(bean.getId());
              prstm = prepareStatement(cnn,SQL_SELECT_ALL_USERS_MESSAGE_RECV,params); 
              rs = prstm.executeQuery();
                while((rs != null) && (rs.next())){
                    beanTemp =new FCreate();
                    beanTemp.setToPertion(rs.getInt(RECIEVERS_TO_PERSON));
                    beanTemp.setUserFullName(rs.getString(USERS_FULLNAME));
                    bean.getEmpsRev().add(beanTemp);           
                }
         }
//      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
   
    
    public FBeans getAllMessRecByUserId(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        FCreate bean= (FCreate)seed;
        FCreate beanC= null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          List params = new ArrayList();            
          String SQL_ORDERMESS = ORDER_BY + TABLE_MESSAGES + STOP + MESSAGES_TIMECREATOR + DESC; 
          String SQL_SELECT = "";                          
          if (bean.getType()==2){
              SQL_SELECT = SQL_SELECT_SEND_MESSAGES ;
              ////.println(SQL_SELECT_SEND_MESSAGES);
              params.add(userId);   
          }else if (bean.getType()==1){
              
              SQL_SELECT = SQL_SELECT_RECV_MESSAGES ;
              params.add(userId);
              
          }else {
              SQL_SELECT = SQL_SELECT_DEL_MESSAGES ; 
              params.add(userId);   
              params.add(userId);   
          }                 
          if(bean.getReaded()==1){
              SQL_SELECT+= AND + TABLE_RECEIVERS + STOP + RECIEVERS_READ + EQUAL + 0; 
          }
          
          String SQLSEARCH ="";
            if (bean.getName()!=null){
                if (!bean.getName().trim().equals("")){
                    SQLSEARCH += SQL_SELECT_MESSAGES_WHERE_NAME;
                    SQLSEARCH +=SQL_SELECT_MESSAGES_WHERE_FULLTEXT;
                    params.add(PER_CENT + bean.getName() + PER_CENT);
                    params.add(PER_CENT + bean.getName() + PER_CENT);
                }
            }
            SQL_SELECT+=SQLSEARCH;
            ////.println(SQL_SELECT);
            prstm = prepareStatement(cnn, SQL_SELECT + SQL_ORDERMESS,params); 
            rs = prstm.executeQuery();
            beans = new FBeans();
            beans.setTotalRows(count(cnn,SQL_SELECT,params));
              beans.setPageIndex(bean.getPageIndex());
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
            int j = 0;
          while((rs != null) && rs.next() && (j++<AppConfigs.APP_ROWS_VIEW))
          {
              beanC = new FCreate();
              DCreate dao = new DCreate(); 
              beanC = dao.getInformation(rs);
              beanC.setType(bean.getType());
              beanC.setEmpsRev(new FBeans());                            
              beanC.getEmpsRev().add(getEmpsRecv(cnn,beanC,bean));      
              FCreate beanuser=new FCreate();
              if(beanC.getEmpsRev()!=null && beanC.getEmpsRev().size()>0){
                  beanuser=(FCreate)beanC.getEmpsRev().get(0);
                  if(bean.getType()==2){
                    String userFullName=beanuser.getUserFullName() + (beanC.getEmpsRev().size()>1?"...":"");
                    beanC.setUserFullName(userFullName);  
                  }
              }              
              beanC.setReaded(beanC.getType()==1?rs.getInt(RECIEVERS_READ):1);              
              beanC.setAllFiles(new DCreateFiles().getAllFileByMessageId(cnn,beanC.getId()));
              beans.add(beanC);
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
    
    
    
    public FCreate getAllAmount(Connection cnn, FSeed seed,int userId) throws EException{
        final String LOCATION = this.toString() + "getAllAmount()";      
        FCreate bean= (FCreate)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {                     
            List params = new ArrayList();         
            params.add(userId);                       
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_MESSAGES_SEND,params); 
            rs = prstm.executeQuery();                        
            if((rs != null) && rs.next()) {
              bean.setAmountSend(rs.getInt(PARAM_01));            
            }
            getAllAmountRev(cnn,bean,userId);
            bean.setAmountDel(getAllAmountDel(cnn,seed,userId));
            bean.setAmount(bean.getAmountRev() + bean.getAmountSend() + bean.getAmountDel());
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
    
    public FCreate getAllAmountRev(Connection cnn, FCreate bean,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllAmountRev()";               
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{         
            List params = new ArrayList();         
            params.add(userId);                    
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_MESSAGES_RECV,params); 
            rs = prstm.executeQuery();  
            while((rs != null) && rs.next()){
//                bean.setAmountRevUnRead(0);
//                bean.setAmountRevRead(0);
                if (rs.getInt(PARAM_01)==1){
                    bean.setAmountRevRead(rs.getInt(PARAM_02));
                }else{
                    bean.setAmountRevUnRead(rs.getInt(PARAM_02));
                }          
            }
            bean.setAmountRev(bean.getAmountRevRead() + bean.getAmountRevUnRead());
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
    public FBeans getEmpRecvById(Connection cnn,int id) throws EException
    {
        final String LOCATION = this.toString() + "getEmpRecvById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FBeans beans=new FBeans();
        FUser bean=new FUser();
        try{         
            List params = new ArrayList();         
            params.add(id);
            ////.println(SQL_SELECT_AMOUNT_MESSAGES_EMP_RECV);
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_MESSAGES_EMP_RECV,params); 
            rs = prstm.executeQuery();  
            while((rs != null) && rs.next()){
              bean=new FUser();
              bean.setId(rs.getInt(PARAM_01));
              bean.setFullName(rs.getString(PARAM_02));
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
    public int getAllAmountDel(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllAmountDel()";     
        PreparedStatement prstm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            List params = new ArrayList();         
            params.add(userId);                   
            params.add(userId);                     
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_MESSAGES_DEL,params); 
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
    
    public void getAllInforRevById(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FCreate bean= (FCreate)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try{
          prstm = cnn.prepareStatement(SQL_SELECT_INFOR_RECIEVER);  
          prstm.setLong(PARAM_01,bean.getId());                    
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next()){
             
              DCreate dao = new DCreate();               
              bean = dao.getInformationRev(rs,bean);              
            
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

    }
    
    public String getUsersName(Connection conn, int  userId)throws EException{
    final String LOCATION = "->getUsersName()";
    String result = "";   
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          
          pstmt = conn.prepareStatement(SQL_USERS_NAME + WHERE + USERS_USER_ID + EQUAL + userId);             
          rs = pstmt.executeQuery();
          if(rs != null && rs.next()){    
              result = rs.getString(USERS_FULLNAME);               
          }
          
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
    // end
    
    
    public int getMessageId(Connection cnn,int creator) throws EException
    {
      final String LOCATION = this.toString() + "getMessageId()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      int result = 0;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_MESSAGES_ID_TOP);    
        prstm.setInt(PARAM_01,creator);
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
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
    
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FCreate bean = (FCreate)seed;
      try
      {
          List params = setParams(seed);
          if (execute(cnn,SQL_INSERT_MESSAGES,params)>0){               
              result = insertUsersReciever(cnn,bean);
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    

 public boolean update(Connection cnn, FSeed seed ,int userId) throws  EException
 {
   final String LOCATION = this.toString() + UPDATE;
   PreparedStatement prstm = null;
   FCreate bean = (FCreate)seed;
   boolean result = false;
   try
   {    
     prstm = cnn.prepareStatement(SQL_UPDATE_READED);
     prstm.setInt(PARAM_01,1);
     prstm.setInt(PARAM_02,bean.getId());
     prstm.setLong(PARAM_03,userId);     
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
 

   

    public boolean insertUsersReciever(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FCreate bean = (FCreate)seed;
      try
      {
          String recvs = "";
          String SQL_INSERT_RECIEVERS = SQL_INSERT_RECIEVER;
          if(bean.getUsersId()!=null && bean.getSendConpany()==0){
              int[] lens = bean.getUsersId();
              for (int i =0;i<lens.length;i++){
                 if (lens[i]>0){ 
                    if(!recvs.equals(""))    recvs += ",";
                    recvs += lens[i];
                 }                  
              }
              if(!recvs.equals("")) SQL_INSERT_RECIEVERS += AND + USERS_USER_ID + IN + OPEN + recvs +CLOSE;
          }
          
          if(bean.getSendConpany()>0 || !recvs.equals("")){
              int messageId = getMessageId(cnn,bean.getCreator());
              List params = new ArrayList();
              params.add(messageId);
              params.add(0);
              params.add(0);
              params.add(IConstantsMessages.STATUS_READ[0]);
              params.add(bean.getSendMail()>0?1:0);
              result=execute(cnn,SQL_INSERT_RECIEVERS,params)>0;
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean updateRemove(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement pstmt = null;
        PreparedStatement pstmtR = null;
      boolean flag = false;
      try
      {
        
        
        FCreate bean = (FCreate)seed;
        pstmt = cnn.prepareStatement(SQL_UPDATE_REMOVE_MESSAGES);  
        pstmtR = cnn.prepareStatement(SQL_UPDATE_REMOVE_RECIEVER);  
        if (bean.getCheckEmp()!=null){
            for (int i=0;i<bean.getCheckEmp().length;i++){
               if (bean.getCheckEmp()[i]>0){
                   pstmt.setInt(PARAM_01,IConstantsMessages.STATUS_REMOVE[1]);
                   pstmt.setInt(PARAM_02,bean.getCheckEmp()[i]);
                   pstmt.setLong(PARAM_03,bean.me.getId());
                   pstmt.addBatch();
                   
                   pstmtR.setInt(PARAM_01,IConstantsMessages.STATUS_REMOVE[1]);
                   pstmtR.setInt(PARAM_02,bean.getCheckEmp()[i]);
                   pstmtR.setLong(PARAM_03,bean.me.getId());
                   pstmtR.addBatch();
                   flag = true;                                   
               }
            }
            if (flag){            
                result = pstmt.executeBatch().length>0 ;
                result = pstmtR.executeBatch().length>0;
            }
        }        
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
      return result;
    }
    
    public boolean updateUnRemove(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      ResultSet rs = null; 
      PreparedStatement pstmt = null;
        PreparedStatement pstmtR = null;
      boolean flag = false;
      try
      {
        FCreate bean = (FCreate)seed;
        pstmt = cnn.prepareStatement(SQL_UPDATE_REMOVE_MESSAGES);  
        pstmtR = cnn.prepareStatement(SQL_UPDATE_REMOVE_RECIEVER);  
        if (bean.getCheckEmp()!=null){
            for (int i=0;i<bean.getCheckEmp().length;i++){
               if (bean.getCheckEmp()[i]>0){
                   pstmt.setInt(PARAM_01,IConstantsMessages.STATUS_REMOVE[0]);
                   pstmt.setInt(PARAM_02,bean.getCheckEmp()[i]);
                   pstmt.setLong(PARAM_03,bean.me.getId());
                   pstmt.addBatch();
                   
                   pstmtR.setInt(PARAM_01,IConstantsMessages.STATUS_REMOVE[0]);
                   pstmtR.setInt(PARAM_02,bean.getCheckEmp()[i]);
                   pstmtR.setLong(PARAM_03,bean.me.getId());
                   pstmtR.addBatch();
                   flag = true;                                   
               }
            }
            if (flag){            
                result = pstmt.executeBatch().length>0 ;
                result = pstmtR.executeBatch().length>0;
            }
        }        
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
      return result;
    }
   
    
    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
        boolean result = false;
        FCreate bean = (FCreate)seed;
        if (bean.getDelete()>0){          
        
            String messages= "";
            if(bean.getCheckEmp().length>0){
                for (int i=0;i<bean.getCheckEmp().length;i++){
                      if (!messages.equals("")) messages +=",";
                        messages += bean.getCheckEmp()[i] ;
                }
            }   //Co van de khi xoa han thong tin
            if(!messages.equals("")){
            execute(cnn,UPDATE + TABLE_RECEIVERS + SET +  RECIEVERS_REMOVE +  EQUAL + PARAM_02  + WHERE + RECIEVERS_MESSAGES_ID + IN + OPEN + messages + CLOSE + AND + RECIEVERS_TO_PERSON + EQUAL + bean.getCreator());
            result = execute(cnn,UPDATE + TABLE_MESSAGES + SET +  MESSAGES_REMOVE +  EQUAL + PARAM_02  + WHERE + MESSAGES_ID + IN + OPEN + messages + CLOSE + AND + MESSAGES_CREATOR + EQUAL + bean.getCreator())>0 || delete(cnn,TABLE_RECEIVERS, RECIEVERS_MESSAGES_ID + IN + OPEN + messages + CLOSE + AND + RECIEVERS_TO_PERSON + EQUAL + bean.getCreator())>0 || delete(cnn, TABLE_MESSAGES, MESSAGES_ID + IN + OPEN + messages + CLOSE  + AND + MESSAGES_REMOVE + EQUAL + PARAM_02 + AND + NOT + EXISTS + OPEN + SELECT + RECIEVERS_MESSAGES_ID + FROM + TABLE_RECEIVERS + WHERE + RECIEVERS_MESSAGES_ID + IN + OPEN + messages + CLOSE + AND + RECIEVERS_REMOVE + DIFF + PARAM_02 + CLOSE)>0;                                                 
            }
        }
        
         if (AppConfigs.MESSAGES_DAYS_REMOVE>0){   
            String SQL = SELECT + TABLE_MESSAGES + STOP + MESSAGES_ID + FROM + TABLE_RECEIVERS + LEFT_JOIN + TABLE_MESSAGES + ON +  TABLE_RECEIVERS + STOP + RECIEVERS_MESSAGES_ID + EQUAL + TABLE_MESSAGES + STOP + MESSAGES_ID + WHERE + TABLE_RECEIVERS + STOP + RECIEVERS_TO_PERSON + EQUAL + bean.getCreator()  + AND + TABLE_MESSAGES + STOP + MESSAGES_TIMECREATOR + "<=" + QUESTION;                            
            List params = new ArrayList();
            params.add(bean.addDays(bean.getCurrentSqlDate(),-AppConfigs.MESSAGES_DAYS_REMOVE));             
            
            result=execute(cnn,DELETE + FROM + TABLE_RECEIVERS + WHERE +  RECIEVERS_MESSAGES_ID + IN + OPEN + SQL + CLOSE,params)>0;
            
            params = new ArrayList();
            params.add(bean.addDays(bean.getCurrentSqlDate(),-AppConfigs.MESSAGES_DAYS_REMOVE));             
            params.add(bean.addDays(bean.getCurrentSqlDate(),-AppConfigs.MESSAGES_DAYS_REMOVE));   
            
            String SQL1 = DELETE + FROM + TABLE_MESSAGES + WHERE + MESSAGES_ID + IN + OPEN + SELECT + MESSAGES_ID + FROM + TABLE_MESSAGES + WHERE + MESSAGES_CREATOR + EQUAL + bean.getCreator() + AND + MESSAGES_TIMECREATOR + "<=" + QUESTION  +  AND + MESSAGES_ID + NOT + IN + OPEN  + SELECT + RECIEVERS_ID + FROM + TABLE_RECEIVERS + WHERE + RECIEVERS_ID + IN + OPEN + SQL + CLOSE  + CLOSE + CLOSE;                     
            result=execute(cnn,SQL1,params)>0;
            
         }
        return result;
    }   
    

    public FCreate getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FCreate bean= new FCreate();
         try {
           bean.setId(rs.getInt(MESSAGES_ID));             
           bean.setName(rs.getString(MESSAGES_NAME));
           bean.setCreator(rs.getInt(MESSAGES_CREATOR));         
           bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(MESSAGES_TIMECREATOR).getTime()),AppConfigs.APP_DATE_TIME));            
           bean.setFileName(rs.getString(MESSAGES_FILES));          
           bean.setRemove(rs.getInt(MESSAGES_REMOVE));
           bean.setFulltext(rs.getString(MESSAGES_FULLTEXT)==null?"":rs.getString(MESSAGES_FULLTEXT));                
           bean.setPathFile(rs.getString(MESSAGES_PATH_FILE));
           bean.setUserFullName(rs.getString(USERS_FULLNAME));  
           bean.setSendMail(rs.getInt(RECIEVERS_EMAIL));
           
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    
    public FCreate getInformationRev(ResultSet rs,FCreate bean) throws EException
    {
        final String LOCATION = "->getInformationRev()";        
         try {
             bean.setIdRec(rs.getInt("RECIEVERSID"));
             bean.setToPertion(rs.getInt(RECIEVERS_TO_PERSON));
             bean.setHiddenRec(rs.getInt("RECIEVERSHIDDEN"));
             bean.setRemoveRec(rs.getInt("RECIEVERSREMOVE"));
             bean.setReaded(rs.getInt(RECIEVERS_READ));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
  
    public List setParams(FSeed seed) throws EException{
        final String LOCATION = "->setParams()";
        FCreate bean = (FCreate)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getName());
             params.add(bean.getCreator());            
             params.add(new Timestamp(System.currentTimeMillis()));                  
             params.add(bean.getFileName());                                                                                                                
             params.add(bean.getRemove());
             params.add(bean.getFulltext());
             params.add(bean.getPathFile());  
             params.add(bean.getSendMail()>0?1:0);  
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
  
}
