package com.dao.foryou;


import com.dao.doc.docsrecv.DDocsrecv;
import com.dao.doc.docsrecv.DFilesRecv;
import com.dao.doc.docssend.DDocssend;
import com.dao.doc.docssend.DFilesSend;
import com.dao.messages.create.DCreate;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;
import com.form.foryou.FForYou;
import com.form.messages.create.FCreate;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DForYou extends DSqlForYou
{

  public FBeans getAll(Connection cnn) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAllFORM()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FBeans beans = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_FORYOU_SELECT_ALL_FORYOU);     
      rs = prpstm.executeQuery();
      beans = new FBeans();
      FForYou beantemp = null;
      while((rs != null) && (rs.next()))
      {
            beantemp = new FForYou();
            beantemp = getInformation(rs);    
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
  //nhan uy quyen
    public FBeans getByUserIdTo(Connection cnn,FSeed seed,int userIdTo) throws EException
    {
      final String LOCATION = this.toString() + "~~>getByUserIdTo()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FForYou bean=(FForYou)seed;
      try
      {
        List params =new ArrayList();
        params.add(userIdTo);
        prpstm = prepareStatement(cnn,SQL_FORYOU_SELECT_BY_USER_ID_TO + "ORDER BY A.DATECREATE DESC",params);
        
        rs = prpstm.executeQuery();
          beans = new FBeans();
          beans.setTotalRows(count(cnn,SQL_FORYOU_SELECT_BY_USER_ID_TO,params));
          beans.setPageIndex(bean.getPageIndex());
                if(beans.getFirstRecord()<=1){
                    rs.beforeFirst();
                }else{
                    rs.absolute(beans.getFirstRecord()-1);
                }
                int i=0;
        FForYou beantemp = null;
          while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {
        i++;
              beantemp = new FForYou();
              beantemp = getInformation(rs);    
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
    
    public boolean checkDelete(Connection cnn,int foryouId) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkDelete()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      boolean result = false;
      try
      {
        prpstm = cnn.prepareStatement(SQL_CHECK_DELETE_FORYOU);
        //println(SQL_CHECK_DELETE_FORYOU);
        prpstm.setInt(PARAM_01,foryouId);
        prpstm.setInt(PARAM_02,foryouId);        
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            result = true;
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
      return result;
    }
    //nhung uy quyen do minh tao ra
    public FBeans getByUserIdFrom(Connection cnn,FSeed seed,int userIdFrom) throws EException
    {
      final String LOCATION = this.toString() + "~~>getByUserIdTo()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FForYou bean =(FForYou)seed;
      String SQL_SELECT=SQL_FORYOU_SELECT_BY_USER_ID_FROM; 
      try
      {
        List params =new ArrayList();
        params.add(userIdFrom);       
//        if(bean.getStatus()>=0){
//            params.add(bean.getStatus());
//            SQL_SELECT+=AND + " A." + FORYOU_STATUS + EQUAL + QUESTION ;
//        }
        prpstm = prepareStatement(cnn,SQL_SELECT + ORDER_BY + "A." + FORYOU_DATECREATE + DESC,params);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL_SELECT,params));
        beans.setPageIndex(bean.getPageIndex());
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
              int i=0;
         FForYou beantemp=null;
          while((rs != null) && (rs.next())&& (i<AppConfigs.APP_ROWS_VIEW))
        {
            i++;
            beantemp = new FForYou();
            beantemp = getInformation(rs);    
            beantemp.setForWho(rs.getString("NHAN"));
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
    public FBeans getAllDiffUserId(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllDiffUserId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FForYou bean = (FForYou)seed;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_USER_DIFF_USER_ID);
        //println(SQL_SELECT_USER_DIFF_USER_ID);
        prpstm.setLong(PARAM_01,bean.getMeId());
        prpstm.setLong(PARAM_02,bean.getWorkflowId());
        //.println(SQL_SELECT_USER_DIFF_USER_ID);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FUser beantemp = null;
        while((rs != null) && (rs.next()))
        {
              beantemp = new FUser();
              beantemp.setId(rs.getInt(USERS_USER_ID));
              beantemp.setFullName(rs.getString(USERS_FULLNAME));
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
    
    public FBeans getAllDetailt(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllDetailt()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FForYou bean = (FForYou)seed;
      try
      {
        String SQL = SQL_SELECT_DETAIL_FORYOU_RECV;
        if (bean.getWorkflowId()==2){
            SQL = SQL_SELECT_DETAIL_FORYOU_SEND;
        }
        prpstm = cnn.prepareStatement(SQL);
        //println(SQL);
        prpstm.setLong(PARAM_01,bean.getUserIdTo());
        prpstm.setLong(PARAM_02,bean.getId());
        //.println(SQL_SELECT_USER_DIFF_USER_ID);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FDocsrecv beantemp = null;
        FDocssend beantemp1 = null;
        DDocsrecv daoRecv = new DDocsrecv();
        DDocssend daoSend = new DDocssend();
        DFilesRecv dao2=new DFilesRecv();
        DFilesSend dao1=new DFilesSend();
        while((rs != null) && (rs.next()))
        {
              if (bean.getWorkflowId()==1){
                  beantemp = new FDocsrecv();
                  beantemp = daoRecv.getInforById(rs,true);                                   
                  beantemp.setAllFiles(dao2.getAllByDocId(cnn,beantemp.getId()));
                  beans.add(beantemp);
              }else{
                  beantemp1 = new FDocssend();
                  beantemp1 = daoSend.getInformation(rs,false);                    
                  beantemp1.setAllFiles(dao1.getAllByDocId(cnn,beantemp1.getId(),0));
                  beans.add(beantemp1);                    
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
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    
    
    public FBeans getAllUsers(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllUsers()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_USERS);
        //.print(SQL_SELECT_USERS);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FUser beantemp = null;
        while((rs != null) && (rs.next()))
        {
              beantemp = new FUser();
              beantemp.setId(rs.getInt(USERS_USER_ID));
              beantemp.setFullName(rs.getString(USERS_FULLNAME));
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
    boolean result = false;
    PreparedStatement prstm = null;
    FForYou bean = null;
      FForYou beanF = (FForYou)seed;
    try
    {
        List params = new ArrayList();
        params = setParams(seed);
        result = execute(cnn,SQL_FORYOU_ADD_NEW,params)>0; 
        if (beanF.getPublicInfor()==1){
            DCreate daoC = new DCreate();
            FCreate beanC = new FCreate();
            beanC.setCreator(beanF.getUserIdFrom());
            beanC.setName(beanF.getTitleMess());
            beanC.setFulltext(beanF.getTempMess() + "<br>" + beanF.getProblem());
            beanC.setDepartmentID(0);
            daoC.insert(cnn,beanC);
            
        }
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
    FForYou bean = (FForYou)seed;
      FForYou beanF = (FForYou)seed;
    boolean result=false;
    try
    {     
        List params = new ArrayList();
        params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_FORYOU_UPDATE,params)>0; 
        if (beanF.getPublicInfor()==1){
            DCreate daoC = new DCreate();
            FCreate beanC = new FCreate();
            beanC.setCreator(beanF.getUserIdFrom());
            beanC.setName(beanF.getTitleMess());
            beanC.setFulltext(beanF.getTempMess() + "<br>" + beanF.getProblem());
            beanC.setDepartmentID(0);
            daoC.insert(cnn,beanC);
            
        }
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
  
    public boolean updateActive(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      FForYou bean = (FForYou)seed;    
      boolean result=false;
      try
      {     
          List params = new ArrayList();                
          params.add(bean.getCurrentSqlDate());
          result = execute(cnn,SQL_FORYOU_UPDATE_ACTIVE,params)>0;          
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
  
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    final String LOCATION = this.toString() + "~~>delete()";
    boolean result=false;
    FForYou bean = (FForYou)seed;
    try
    {
      result = delete(cnn, TABLE_FORYOU, FORYOU_ID + EQUAL + bean.getId())>0;
    }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      result=false;
    }
    return result;
  }  
  public FForYou getById(Connection cnn, FForYou bean) throws EException 
  {
    final String LOCATION = this.toString() + "getById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FForYou beantemp = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_FORYOU_SELECT_FORYOU_BY_ID);
      prpstm.setInt(PARAM_01,bean.getId());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next())) { 
        beantemp = getInformation(rs);                
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
     
    public FForYou getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FForYou beantemp = new FForYou();
         try {
             beantemp = new FForYou();
             beantemp.setId(rs.getInt(FORYOU_ID));            
             beantemp.setBoss(rs.getString(USERS_FULLNAME));
             beantemp.setUserIdFrom(rs.getInt(FORYOU_USER_ID_FROM));
             beantemp.setUserIdTo(rs.getInt(FORYOU_USER_ID_TO));
             beantemp.setProblem(rs.getString(FORYOU_PROBLEM));
             beantemp.setDateFrom(beantemp.dateToString(rs.getDate(FORYOU_DATEFROM)));
             beantemp.setDateTo(beantemp.dateToString(rs.getDate(FORYOU_DATETO)));
             beantemp.setStatus(rs.getInt(FORYOU_STATUS));
             beantemp.setDateCreate(beantemp.dateToString(new Date (rs.getTimestamp(FORYOU_DATECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
             beantemp.setWorkflowId(rs.getInt(FORYOU_WORKFLOW_ID));
             }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
  
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FForYou bean = (FForYou)seed;
        List params = new ArrayList();
         try {
            params.add(bean.getUserIdFrom());
            params.add(bean.getUserIdTo());
            params.add(bean.getProblem());
            params.add(bean.stringToSqlDate(bean.getDateFrom()));
            params.add(bean.stringToSqlDate(bean.getDateTo()));
            params.add(bean.getStatus());
            params.add(new java.sql.Timestamp(System.currentTimeMillis()));   
            params.add(bean.getWorkflowId());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    
    
    public FBeans getAllDocByForyouId(Connection cnn,int forYouId,int workflowId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllDocByForyouId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      
      String TABLE_DOC="";
      String TABLE_DOC_TRAILER="";
      if(workflowId==1){
          TABLE_DOC=TABLE_DOCSRECV;
          TABLE_DOC_TRAILER=TABLE_DOC_TRAILER_RECV;
      }else{
          TABLE_DOC=TABLE_DOCSSEND;
          TABLE_DOC_TRAILER=TABLE_DOC_TRAILER_SEND;
      }
        String SQL_SELECT="SELECT DOC_ID,DOCCODE,DOCDATE,ABSTRACT FROM " +TABLE_DOC+ " WHERE DOC_ID IN (SELECT DOC_ID FROM " + TABLE_DOC_TRAILER +  " WHERE  FORYOU_ID=?)";
      try
      {
        List params =new ArrayList();
        params.add(forYouId);
//      /  //.println(SQL_SELECT);
        prpstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FDocsrecv beanRecv = null;
        FDocssend beanSend = null;
          while((rs != null) && (rs.next()))
        {
            if(workflowId==1){
                beanRecv = new FDocsrecv();
                beanRecv.setId(rs.getInt(DOCSRECV_DOC_ID));
                beanRecv.setDocCode(rs.getString(DOCSRECV_DOCCODE));
                beanRecv.setDocDate(rs.getString(DOCSRECV_DOCDATE));
                beanRecv.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
                beanRecv.setWorkflowId(workflowId);
                beans.add(beanRecv);
            }else{
                beanSend = new FDocssend();
                beanSend.setId(rs.getInt(DOCSRECV_DOC_ID));
                beanSend.setDocCode(rs.getString(DOCSRECV_DOCCODE));
                beanSend.setDocDate(rs.getString(DOCSRECV_DOCDATE));
                beanSend.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
                beanSend.setWorkflowId(workflowId);
                beans.add(beanSend);
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
        closePreparedStatement(prpstm);
      }
      return beans;
    }
}
