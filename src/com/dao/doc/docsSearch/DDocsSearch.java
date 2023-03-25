package com.dao.doc.docsSearch;


import com.dao.doc.docsrecv.DDocsrecv;
import com.dao.doc.docsrecv.DFilesRecv;
import com.dao.doc.docssend.DDocssend;
import com.dao.doc.docssend.DFilesSend;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsSearch.FDocsSearch;
import com.form.doc.docsSearch.FFilesSearch;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDocsSearch extends DSqlForYou
{
    
    public FBeans search(Connection cnn,FSeed seed) throws EException 
    {
      final String LOCATION = this.toString() + "search()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocsSearch bean =(FDocsSearch)seed;
      FBeans beans =null;      
      try
      {
        bean.setUserId((int)bean.me.getId());   
        List params = new ArrayList();
        String SQL ="";
        if(bean.getType()==1 || bean.getType()==2){
            SQL=getDocsSend(bean,params);
        }else{
            SQL=getDocsRec(bean,params);
        }
        ////.println(SQL);
        prpstm = prepareStatement(cnn,SQL,params);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if (bean.getPageIndex()!=-1){
            beans.setTotalRows(count(cnn,SQL,params));
            beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }
        }
        int i=0;
          FDocsrecv beanRecv=new FDocsrecv();
          FDocssend beansend=new FDocssend();
         while((rs != null) && (rs.next()) && (bean.getPageIndex()!=-1?(i<AppConfigs.APP_ROWS_VIEW):true))
        {      
                if(bean.getType()==1 || bean.getType()==2){
                    if(bean.getFields()==null){
                        beansend=new DDocssend().InforSearch__(rs);
                        beansend.setBlockUpdate(beansend.getUserId()==(int)bean.me.getId()?1:0);
                        beansend.setAllFiles(new DFilesSend().getAllByDocId(cnn,beansend.getId(),0));
                        beans.add(beansend);
                    }else{
                        beanRecv=InforPrintRecv(rs,bean.getFields());
                        beans.add(beanRecv);
                    }
                    i++;
                }else{
                    if(bean.getFields()==null){
                        beanRecv=new DDocsrecv().InforSearch(rs);
                        beanRecv.setBlockUpdate(beanRecv.getUserId()==(int)bean.me.getId()?1:0);
                        beanRecv.setAllFiles(new DFilesRecv().getAllByDocId(cnn,beanRecv.getId()));
                    }else{
                        beanRecv=InforPrintRecv(rs,bean.getFields());
                    }
                    beans.add(beanRecv);
                    i++;
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
    
    public FBeans searchDocReference(Connection cnn,FSeed seed) throws EException 
    {
      final String LOCATION = this.toString() + "searchDocReference()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocsSearch bean =(FDocsSearch)seed;
      String temp =  "," + bean.getTemp() + ",";
      FBeans beans =null;      
      try
      {
       // bean.setUserId((int)bean.me.getId());   
        List params = new ArrayList();
        String SQL ="";       
        SQL=getDocsRec(bean,params);                
        prpstm = prepareStatement(cnn,SQL,params);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if (bean.getPageIndex()!=-1){
            beans.setTotalRows(count(cnn,SQL,params));
            beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }
        }
        int i=0;
          FDocsrecv beanRecv=new FDocsrecv();         
         while((rs != null) && (rs.next()) && (bean.getPageIndex()!=-1?(i<AppConfigs.APP_ROWS_VIEW):true))
        {      
               
                    if(bean.getFields()==null){
                        beanRecv=new DDocsrecv().InforSearch(rs);
                        beanRecv.setBlockUpdate(beanRecv.getUserId()==bean.getUserId()?1:0);
                        beanRecv.setAllFiles(new DFilesRecv().getAllByDocId(cnn,beanRecv.getId()));
                        beanRecv.setDocTypeName(rs.getString(DOCTYPE_NAME));
                    }else{
                        beanRecv=InforPrintRecv(rs,bean.getFields());
                        beanRecv.setDocTypeName(rs.getString(DOCTYPE_NAME));
                    }
                    
                    if(temp!=null && temp.indexOf("," + beanRecv.getId() + ",")>=0){
                        beanRecv.setChecked("checked");
                    }else{
                        beanRecv.setChecked("");
                    }
                    
                    beans.add(beanRecv);
                    i++;
                
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
   
    private String getDocsSend(FDocsSearch bean,List params){
        String SQL_SELECT=SQL_DOCSSEND_SEARCH;
        String SQL_WHERE =WHERE + TRUE;
        String ELEMENT_SEND_FIELDS="";
        if(bean.getFields()!=null){
            ELEMENT_SEND_FIELDS+=getElementInDocsSend(bean);
            SQL_SELECT=getElementLeftJoinSend(SQL_SELECT,bean.getFields());
            SQL_SELECT=SQL_SELECT.replaceAll("#",ELEMENT_SEND_FIELDS);
        }else{
            SQL_SELECT=SQL_SEARCH_DOCSSEND.replaceAll("#",SQL_SEARCH_DOCSSEND_FIELDS);
            
        }
        
        if(bean.getUserId()>0)  {
            SQL_WHERE += " " + AND + OPEN + TABLE_DOCSSEND+STOP+ DOCSSEND_USER_ID + EQUAL + QUESTION;
            params.add(bean.getUserId());
            SQL_WHERE += " " + OR + TABLE_DOCSSEND+STOP+ DOCSSEND_DOC_ID + IN + OPEN + SELECT + DOC_TRAILER_SEND_DOC_ID + FROM + TABLE_DOC_TRAILER_SEND + WHERE + DOC_TRAILER_SEND_USERRECV_ID + EQUAL + QUESTION + CLOSE;
            params.add(bean.getUserId());
            SQL_WHERE += " " + OR + EXISTS + OPEN + SELECT + STAR + FROM + TABLE_DOC_OBSERVERS + WHERE + DOC_OBSERVERS_OBSERVER_ID + EQUAL + QUESTION + AND + DOC_OBSERVERS_WORKFLOW_ID + EQUAL + 2 + CLOSE + CLOSE ;
            params.add(bean.getUserId());
        }
        if(bean.getAbstracts()!=null && !bean.getAbstracts().equals(""))  {
            SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_ABSTRACT + LIKE + QUESTION;
            params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
        }
        if(bean.getIssue()!=null && !bean.getIssue().equals(""))  {
            SQL_WHERE += AND + TABLE_DOCSSEND+STOP+ DOCSSEND_DOC_ID + IN + OPEN + SELECT + DOC_REVIEW_SEND_DOC_ID + FROM + TABLE_DOC_REVIEW_SEND + WHERE + DOC_REVIEW_SEND_ISSUE + LIKE + QUESTION + CLOSE;
            params.add(PER_CENT + bean.getIssue() + PER_CENT);
        }
        if(bean.getLocalCode()!=null && !bean.getLocalCode().equals(""))  {
           SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_LOCALCODE + LIKE + QUESTION ;
           params.add(PER_CENT + bean.getLocalCode() + PER_CENT);
        }
        if(bean.getType()==2){
            SQL_WHERE += " " + AND + OPEN + TABLE_DOCSSEND+STOP+DOCSSEND_DOCCODE + EQUAL +  "''" + OR + TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE + IS + NULL +CLOSE;
        }else{
            if(!bean.getDocCode().equals("")){
                SQL_WHERE += " " +AND+ TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE + LIKE + QUESTION;
                params.add(PER_CENT + bean.getDocCode() + PER_CENT);
            }else{
                SQL_WHERE += AND + OPEN + TABLE_DOCSSEND+STOP+DOCSSEND_DOCCODE + DIFF +"''"+ AND + TABLE_DOCSSEND+STOP+DOCSSEND_DOCCODE + IS + NOT + NULL + CLOSE;
            }
        }
        if(bean.getStatusId()!=0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_STATUS_ID + EQUAL +QUESTION;
           params.add(bean.getStatusId());
        }
        if(bean.getTimeCreateFrom()!=null && !bean.getTimeCreateFrom().equals("") && bean.isDate(bean.getTimeCreateFrom()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_TIMECREATE + " >= " +QUESTION;
           params.add(bean.stringToSqlDate(bean.getTimeCreateFrom()));
        }
        if(bean.getTimeCreateTo()!=null && !bean.getTimeCreateTo().equals("") && bean.isDate(bean.getTimeCreateTo()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+ DOCSSEND_TIMECREATE + " < " + QUESTION;
         params.add(bean.addDays(bean.stringToDate(bean.getTimeCreateTo()),1));
        }
        if(bean.getLocalDateFrom()!=null && !bean.getLocalDateFrom().equals("") && bean.isDate(bean.getLocalDateFrom()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_LOCALDATE + " >= "+ QUESTION;
         params.add(bean.stringToSqlDate(bean.getLocalDateFrom()));
        }
        if(bean.getLocalDateTo()!=null && !bean.getLocalDateTo().equals("") && bean.isDate(bean.getLocalDateTo()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_LOCALDATE + " < " +QUESTION;
         params.add(bean.addDays(bean.stringToDate(bean.getLocalDateTo()),1));
        }
        if(bean.getDocDateFrom()!=null && !bean.getDocDateFrom().equals("") && bean.isDate(bean.getDocDateFrom()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_DOCDATE + " >= "+QUESTION;
         params.add(bean.stringToSqlDate(bean.getDocDateFrom()));
        }
        if(bean.getDocDateTo()!=null &&  !bean.getDocDateTo().equals("") && bean.isDate(bean.getDocDateTo()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_DOCDATE + " < " +QUESTION;
         params.add(bean.addDays(bean.stringToDate(bean.getDocDateTo()),1));
        }
        
        if(bean.getStoreAgeId()>0)  {
         SQL_WHERE+=" " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_STOREAGE_ID + EQUAL + QUESTION;
         params.add(bean.getStoreAgeId());
        }
        if(bean.getExpressId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_EXPRESS_ID + EQUAL + QUESTION;
         params.add(bean.getExpressId());
        }
        if(bean.getSecureId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_SECURE_ID + EQUAL + QUESTION;
         params.add(bean.getSecureId());
        }
        if(bean.getFromId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_FROM_ID + EQUAL + QUESTION;
         params.add(bean.getFromId());
        }
        
        if(bean.getDocsTypeId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_DOCTYPE_ID + EQUAL + QUESTION;
         params.add(bean.getDocsTypeId());
        }
        
        if(bean.getSigner()!=null && !bean.getSigner().equals(""))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_SIGNER + LIKE + QUESTION;
         params.add(bean.getSigner());
        }
        if(bean.getDeadLineFrom()!=null && !bean.getDeadLineFrom().equals("") &&  bean.isDate(bean.getDeadLineFrom()))  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_DEADLINE + " >= " +QUESTION;
         params.add(bean.stringToSqlDate(bean.getDeadLineFrom()));
        }
        if(bean.getDeadLineTo()!=null && !bean.getDeadLineTo().equals("") &&  bean.isDate(bean.getDeadLineTo()))  {
        SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_DEADLINE + " < " +QUESTION;
        params.add(bean.addDays(bean.stringToDate(bean.getDeadLineTo()),1));
        }
        if(bean.getDossierId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND +STOP + DOCSSEND_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
         params.add(2);
         params.add(bean.getDossierId());
        }
        if(bean.getWorkflowId()>0)  {
         SQL_WHERE += " " +AND+ TABLE_DOCSSEND+STOP+DOCSSEND_WORKFLOW_ID + EQUAL + QUESTION;
         params.add(bean.getWorkflowId());
        }
    return SQL_SELECT + SQL_WHERE;    
    }
    
    public String getDocsRec(FDocsSearch bean,List params){
        String SQL_WHERE =WHERE + TRUE;
        String ELEMENT_FIELDS="";
        String SQL_SELECT=SQL_SEARCH_DOCSRECV;
        ////.println(SQL_SEARCH_DOCSRECV);
        if(bean.getFields()!=null){
            ELEMENT_FIELDS+=getElementInDocs(bean);
            SQL_SELECT=getElementLeftJoin(SQL_SELECT,bean.getFields());
            ////.println(SQL_SELECT);    
            SQL_SELECT=SQL_SELECT.replaceAll("#",ELEMENT_FIELDS);
            ////.println(SQL_SELECT);
        }else{
            SQL_SELECT=SQL_DOCSRECV_SEARCH.replaceAll("#",SQL_SEARCH_DOCSRECV_FIELDS);
            ////.println(SQL_SELECT);
        }
        if(bean.getUserId()>0)  {
            SQL_WHERE += " " + AND + OPEN + TABLE_DOCSRECV+STOP+ DOCSRECV_USER_ID + EQUAL + QUESTION;
            params.add(bean.getUserId());
            SQL_WHERE += " " + OR + TABLE_DOCSRECV+STOP+ DOCSRECV_DOC_ID + IN + OPEN + SELECT + DOC_TRAILER_RECV_DOC_ID + FROM + TABLE_DOC_TRAILER_RECV + WHERE + DOC_TRAILER_RECV_USERRECV_ID + EQUAL + QUESTION + CLOSE;
            params.add(bean.getUserId());
            SQL_WHERE += " " + OR + EXISTS + OPEN + SELECT + STAR + FROM + TABLE_DOC_OBSERVERS + WHERE+ DOC_OBSERVERS_OBSERVER_ID + EQUAL + QUESTION + AND + DOC_OBSERVERS_WORKFLOW_ID + EQUAL + 1 + CLOSE + CLOSE;
            params.add(bean.getUserId());
        }
        if(bean.getIssue()!=null && !bean.getIssue().equals(""))  {
            SQL_WHERE += AND + TABLE_DOCSRECV+STOP+ DOCSRECV_DOC_ID + IN + OPEN + SELECT + DOC_REVIEW_RECV_DOC_ID + FROM + TABLE_DOC_REVIEW_RECV + WHERE + DOC_REVIEW_RECV_ISSUE + LIKE +QUESTION+ CLOSE;
            params.add(PER_CENT+bean.getIssue()+PER_CENT);
        }
        if(bean.getAbstracts()!=null && !bean.getAbstracts().equals(""))  {
             SQL_WHERE +=" " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_ABSTRACT + LIKE + QUESTION;
             params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
         }
        if(bean.getLocalCode()!=null && !bean.getLocalCode().equals(""))  {
            SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_LOCALCODE + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getLocalCode() + PER_CENT);
        }
        if(bean.getDocCode()!=null && !bean.getDocCode().equals(""))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_DOCCODE + LIKE + QUESTION;
            params.add(PER_CENT + bean.getDocCode() + PER_CENT);
        }
        if(bean.getStatusId()!=0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_STATUS_ID + EQUAL +QUESTION;
            params.add(bean.getStatusId());
        }
        if(bean.getTimeCreateFrom()!=null && !bean.getTimeCreateFrom().equals("") && bean.isDate(bean.getTimeCreateFrom()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_TIMECREATE + " >= " +QUESTION;
            params.add(bean.stringToSqlDate(bean.getTimeCreateFrom()));
        }
        if(bean.getTimeCreateTo()!=null && !bean.getTimeCreateTo().equals("") && bean.isDate(bean.getTimeCreateTo()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+ DOCSRECV_TIMECREATE + " < " + QUESTION;
          params.add(bean.addDays(bean.stringToDate(bean.getTimeCreateTo()),1));
        }
        if(bean.getLocalDateFrom()!=null && !bean.getLocalDateFrom().equals("") && bean.isDate(bean.getLocalDateFrom()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_LOCALDATE + " >= "+ QUESTION;
          params.add(bean.stringToSqlDate(bean.getLocalDateFrom()));
        }
        if(bean.getLocalDateTo()!=null && !bean.getLocalDateTo().equals("") && bean.isDate(bean.getLocalDateTo()))  {
          SQL_WHERE += " " + AND + TABLE_DOCSRECV + STOP + DOCSRECV_LOCALDATE + " < " +QUESTION;
          params.add(bean.addDays(bean.stringToDate(bean.getLocalDateTo()),1));
        }
        if(bean.getDocDateFrom()!=null && !bean.getDocDateFrom().equals("") && bean.isDate(bean.getDocDateFrom()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_DOCDATE + " >= "+QUESTION;
          params.add(bean.stringToSqlDate(bean.getDocDateFrom()));
        }
        if(bean.getDocDateTo()!=null && !bean.getDocDateTo().equals("") && bean.isDate(bean.getDocDateTo()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_DOCDATE + " < " +QUESTION;
          params.add(bean.addDays(bean.stringToDate(bean.getDocDateTo()),1));
        }
        
        if(bean.getStoreAgeId()>0)  {
          SQL_WHERE+=" " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_STOREAGE_ID + EQUAL + QUESTION;
          params.add(bean.getStoreAgeId());
        }
        if(bean.getExpressId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_EXPRESS_ID + EQUAL + QUESTION;
          params.add(bean.getExpressId());
        }
        if(bean.getSecureId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_SECURE_ID + EQUAL + QUESTION;
          params.add(bean.getSecureId());
        }
        if(bean.getFromId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_FROM_ID + EQUAL + QUESTION;
          params.add(bean.getFromId());
        }
        
        if(bean.getDocsTypeId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_DOCTYPE_ID + EQUAL + QUESTION;
          params.add(bean.getDocsTypeId());
        }
        
        if(bean.getSigner()!=null && !bean.getSigner().equals(""))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_SIGNER + LIKE + QUESTION;
          params.add(bean.getSigner());
        }
        if(bean.getDeadLineFrom()!=null && !bean.getDeadLineFrom().equals("") && bean.isDate(bean.getDeadLineFrom()))  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_DEADLINE + " >= " +QUESTION;
          params.add(bean.stringToSqlDate(bean.getDeadLineFrom()));
        }
        if(bean.getDeadLineTo()!=null && bean.getDeadLineTo().equals("") &&  bean.isDate(bean.getDeadLineTo()))  {
         SQL_WHERE += " " +AND+TABLE_DOCSRECV+STOP+DOCSRECV_DEADLINE + " < " +QUESTION;
         params.add(bean.addDays(bean.stringToDate(bean.getDeadLineTo()),1));
        }
        if(bean.getDossierId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV +STOP + DOCSRECV_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
          params.add(1);
          params.add(bean.getDossierId());
        }
        if(bean.getWorkflowId()>0)  {
          SQL_WHERE += " " +AND+ TABLE_DOCSRECV+STOP+DOCSRECV_WORKFLOW_ID + EQUAL + QUESTION;
          params.add(bean.getWorkflowId());
        }
     //   //.println(SQL_SELECT + SQL_WHERE);
    return SQL_SELECT + SQL_WHERE;    
    }
    
    public FDocsSearch getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocsSearch beantemp = new FDocsSearch();
         try {
             beantemp.setId(rs.getInt(DOCSSEND_DOC_ID));
             beantemp.setLocalCode(rs.getString(DOCSSEND_LOCALCODE));
             beantemp.setDocCode(rs.getString(DOCSSEND_DOCCODE));
             beantemp.setCreator(rs.getString(USERS_FULLNAME));
             beantemp.setUserId(rs.getInt(DOCSSEND_USER_ID));
             beantemp.setStatusId(rs.getInt(DOCSSEND_STATUS_ID));
             beantemp.setTimeCreate(beantemp.dateToString(rs.getDate(DOCSSEND_TIMECREATE)));
             beantemp.setLocalDate(beantemp.dateToString(rs.getDate(DOCSSEND_LOCALDATE)));
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSSEND_DOCDATE)));
             beantemp.setStoreAgeId(rs.getInt(DOCSSEND_STOREAGE_ID));
             beantemp.setExpressId(rs.getInt(DOCSSEND_EXPRESS_ID));
             beantemp.setSecureId(rs.getInt(DOCSSEND_SECURE_ID));
             beantemp.setViaId(rs.getInt(DOCSSEND_VIA_ID));
             beantemp.setFromId(rs.getInt(DOCSSEND_FROM_ID));
             beantemp.setAddress(rs.getString(DOCSSEND_ADDRESS));
             beantemp.setDocsTypeId(rs.getInt(DOCSSEND_DOCTYPE_ID));
             beantemp.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
             beantemp.setDescription(rs.getString(DOCSSEND_DESCRIPTION));
             beantemp.setSigner(rs.getString(DOCSSEND_SIGNER));
             beantemp.setDeadLine(beantemp.dateToString(rs.getDate(DOCSSEND_DEADLINE)));
             beantemp.setDossierId(rs.getInt(DOCSSEND_DOSSIERS_ID));
             beantemp.setWorkflowId(rs.getInt(DOCSSEND_WORKFLOW_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    
    
    
    
    public FDocsSearch getInforSend(ResultSet rs,FDocsSearch beantemp) throws EException
    {
        final String LOCATION = "->getInformation()";
         try {
             beantemp=getInformation(rs);
             beantemp.setFormName(rs.getString("NAME_FORM"));
             beantemp.setStatusName(rs.getString("NAME_STATUS"));
             beantemp.setExpressName(rs.getString("NAME_EXPRESS"));
             beantemp.setViaName(rs.getString("NAME_VIA"));
             beantemp.setDocTypeName(rs.getString("NAME_DOCTYPE"));
             beantemp.setSecureName(rs.getString("NAME_SECURE"));
             beantemp.setDossiersName(rs.getString("NAME_DOSSIERS"));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    
        public FDocsSearch getInforRecv(ResultSet rs,FDocsSearch beantemp) throws EException
        {
            final String LOCATION = "->getInformation()";
             try{   
                beantemp=getInformationRecv(rs);
                beantemp.setFormName(rs.getString("NAME_FORM"));
                beantemp.setStatusName(rs.getString("NAME_STATUS"));
                beantemp.setExpressName(rs.getString("NAME_EXPRESS"));
                beantemp.setViaName(rs.getString("NAME_VIA"));
                beantemp.setDocTypeName(rs.getString("NAME_DOCTYPE"));
                beantemp.setSecureName(rs.getString("NAME_SECURE"));
             }    
            catch (SQLException sqle) {            
                if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
            }
            finally {
            }
            return beantemp;
        }
    public FDocsSearch getInformationRecv(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocsSearch beantemp = new FDocsSearch();
         try {
                beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));
                beantemp.setFormId(rs.getInt(DOCSRECV_FORM_ID));
                beantemp.setLocalCode(rs.getString(DOCSRECV_LOCALCODE));
                beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
                beantemp.setTypeDoc(rs.getInt(DOCSRECV_DOCTYPE_ID));
                beantemp.setCreator(rs.getString(USERS_FULLNAME));
                beantemp.setUserId(rs.getInt(DOCSSEND_USER_ID));
                beantemp.setStatusId(rs.getInt(DOCSRECV_STATUS_ID));
                beantemp.setTimeCreate(beantemp.dateToString(rs.getDate(DOCSRECV_TIMECREATE)));
                beantemp.setLocalDate(beantemp.dateToString(rs.getDate(DOCSRECV_LOCALDATE)));
                beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSRECV_DOCDATE)));
                beantemp.setStoreAgeId(rs.getInt(DOCSRECV_STOREAGE_ID));
                beantemp.setExpressId(rs.getInt(DOCSRECV_EXPRESS_ID));
                beantemp.setSecureId(rs.getInt(DOCSRECV_SECURE_ID));
                beantemp.setViaId(rs.getInt(DOCSRECV_VIA_ID));
                beantemp.setFromId(rs.getInt(DOCSRECV_FROM_ID));
                beantemp.setAddress(rs.getString(DOCSRECV_ADDRESS));
                beantemp.setDocsTypeId(rs.getInt(DOCSRECV_DOCTYPE_ID));
                beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
                beantemp.setDescription(rs.getString(DOCSRECV_DESCRIPTION));
                beantemp.setSigner(rs.getString(DOCSRECV_SIGNER));
                beantemp.setDeadLine(rs.getString(DOCSRECV_DEADLINE));
                beantemp.setDossierId(rs.getInt(DOCSRECV_DOSSIERS_ID));
                beantemp.setWorkflowId(rs.getInt(DOCSRECV_WORKFLOW_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    public FBeans getFilesRecvById(Connection cnn,int DocId) throws EException 
    {
      final String LOCATION = this.toString() + "getFilesRecvById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
        FFilesSearch beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESRECV_BY_ID);
        prpstm.setInt(PARAM_01,DocId);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            beantemp = new FFilesSearch();
            beantemp.setIdFiles(rs.getInt(FILESRECV_ID));
            beantemp.setDocId(rs.getInt(FILESRECV_DOC_ID));
            beantemp.setFileName(rs.getString(FILESRECV_NAME));
            beantemp.setFile(rs.getString(FILESRECV_FILE));
            beantemp.setTyleDoc(0);
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
    
    public FBeans getFilesSendById(Connection cnn,int DocId) throws EException 
    {
      final String LOCATION = this.toString() + "getFilesSendById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
        FFilesSearch beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESSEND_BY_ID);
        prpstm.setInt(PARAM_01,DocId);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            beantemp = new FFilesSearch();
            beantemp.setIdFiles(rs.getInt(FILESSEND_ID));
            beantemp.setDocId(rs.getInt(FILESSEND_DOC_ID));
            beantemp.setFileName(rs.getString(FILESSEND_NAME));
            beantemp.setFile(rs.getString(FILESSEND_FILE));
            beantemp.setTyleDoc(1);
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
    
    
    public FFilesSearch getByIdFile_Recv(Connection cnn, FFilesSearch bean) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      FSeed seed =new FSeed();
      ResultSet rs = null;
      FFilesSearch beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILERECV_BY_ID);
        prpstm.setInt(PARAM_01,bean.getIdFiles());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp = new FFilesSearch();
            beantemp.setIdFiles(rs.getInt(FILESRECV_ID));
            beantemp.setDocId(rs.getInt(FILESRECV_DOC_ID));
            beantemp.setFileName(rs.getString(FILESRECV_NAME));
            beantemp.setFile(rs.getString(FILESRECV_FILE));
            beantemp.setPath(rs.getString(FILESRECV_PATH));
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
    
    public FFilesSearch getByIdFile_Send(Connection cnn, FFilesSearch bean) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      FSeed seed =new FSeed();
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFilesSearch beantemp =null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESEND_BY_ID);
        prpstm.setInt(PARAM_01,bean.getIdFiles());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp = new FFilesSearch();
            beantemp.setIdFiles(rs.getInt(FILESRECV_ID));
            beantemp.setDocId(rs.getInt(FILESRECV_DOC_ID));
            beantemp.setFileName(rs.getString(FILESRECV_NAME));
            beantemp.setFile(rs.getString(FILESRECV_FILE));
            beantemp.setPath(AppConfigs.DOC_FOLDER_ROOT+ AppConfigs.SYSTEM_FILE_SCHIP + seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD)+rs.getString(FILESRECV_PATH));
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
    private String getElementInDocsSend(FDocsSearch bean){
        int i=0;
        int totalFields=bean.getFields().length;
        String ELEMENT_FIELDS="";
        for(i=0;i<totalFields;i++){
            if(i==0){
                ELEMENT_FIELDS+=SQL_ELEMENT_IN_DOCS_SEND[bean.getFields()[i]];
            }else{
                ELEMENT_FIELDS+=" , " + SQL_ELEMENT_IN_DOCS_SEND[bean.getFields()[i]];
            }
        }
        return ELEMENT_FIELDS;
    }
    private String getElementInDocs(FDocsSearch bean){
        int i=0;
        int totalFields=bean.getFields().length;
        String ELEMENT_FIELDS="";
        for(i=0;i<totalFields;i++){
            if(i==0){
                ELEMENT_FIELDS+=SQL_ELEMENT_IN_DOCS[bean.getFields()[i]];
            }else{
                ELEMENT_FIELDS+=" , " + SQL_ELEMENT_IN_DOCS[bean.getFields()[i]];
            }
        }
        return ELEMENT_FIELDS;
    }
    
    private String getElementLeftJoin(String LEFT_JOIN_ELEMENTS,int[] fields){
        String fiedls1=LEFT_JOIN + TABLE_FORM + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_FORM_ID + EQUAL + TABLE_FORM + STOP + FORM_FORM_ID;
        String fiedls2=LEFT_JOIN + TABLE_DOSSIERS + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_DOSSIERS_ID + EQUAL + TABLE_DOSSIERS + STOP + DOSSIERS_ID;
        String fiedls3=LEFT_JOIN + TABLE_FROM + ON + TABLE_DOCSRECV + STOP + DOCSRECV_FROM_ID + EQUAL + TABLE_FROM + STOP + FROM_ID;
        String fiedls4=LEFT_JOIN + TABLE_DEPARTMENTS + ON + TABLE_DOCSRECV + STOP + DOCSRECV_STOREAGE_ID + EQUAL + TABLE_DEPARTMENTS + STOP + DEPARTMENTS_DEPARTMENT_ID;
        String fiedls5=LEFT_JOIN + TABLE_SECURE + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_SECURE_ID + EQUAL + TABLE_SECURE + STOP + SECURE_SECURE_ID;
        String fiedls6=LEFT_JOIN + TABLE_EXPRESS + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_EXPRESS_ID + EQUAL + TABLE_EXPRESS + STOP + EXPRESS_EXPRESS_ID;
        String fiedls7=LEFT_JOIN + TABLE_VIA + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_VIA_ID + EQUAL + TABLE_VIA + STOP + VIA_VIA_ID;
        String fiedls8=LEFT_JOIN + TABLE_DOCTYPE + ON + TABLE_DOCSRECV+ STOP + DOCSRECV_DOCTYPE_ID + EQUAL + TABLE_DOCTYPE + STOP + DOCTYPE_DOCTYPE_ID;
        String[] JOIN_TABLES={fiedls1,fiedls2,"","","","","",fiedls3,"",fiedls4,fiedls5,"",fiedls6,fiedls7,fiedls8,"",""};
        String members = "," + "2" + "," + "3" + "," + "4" + "," + "5" + "," + "8" + "," + "11" + "," + "15" + "," + "16" + "," ;        
        for(int i=0;i<fields.length;i++){
           if (members.indexOf("," + fields[i] + ",")<0){
            LEFT_JOIN_ELEMENTS+=JOIN_TABLES[fields[i]];
           }
        }
        return LEFT_JOIN_ELEMENTS;
    }
    
    private String getElementLeftJoinSend(String LEFT_JOIN_ELEMENTS,int[] fields){
        String fiedls1=LEFT_JOIN + TABLE_FORM + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_FORM_ID + EQUAL + TABLE_FORM + STOP + FORM_FORM_ID;
              String fiedls2=LEFT_JOIN + TABLE_DOSSIERS + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_DOSSIERS_ID + EQUAL + TABLE_DOSSIERS + STOP + DOSSIERS_ID;
              String fiedls3=LEFT_JOIN + TABLE_FROM + ON + TABLE_DOCSSEND + STOP + DOCSSEND_FROM_ID + EQUAL + TABLE_FROM + STOP + FROM_ID;
              String fiedls4=LEFT_JOIN + TABLE_DEPARTMENTS + ON + TABLE_DOCSSEND + STOP + DOCSSEND_STOREAGE_ID + EQUAL + TABLE_DEPARTMENTS + STOP + DEPARTMENTS_DEPARTMENT_ID;
              String fiedls5=LEFT_JOIN + TABLE_SECURE + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_SECURE_ID + EQUAL + TABLE_SECURE + STOP + SECURE_SECURE_ID;
              String fiedls6=LEFT_JOIN + TABLE_EXPRESS + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_EXPRESS_ID + EQUAL + TABLE_EXPRESS + STOP + EXPRESS_EXPRESS_ID;
              String fiedls7=LEFT_JOIN + TABLE_VIA + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_VIA_ID + EQUAL + TABLE_VIA + STOP + VIA_VIA_ID;
              String fiedls8=LEFT_JOIN + TABLE_DOCTYPE + ON + TABLE_DOCSSEND+ STOP + DOCSSEND_DOCTYPE_ID + EQUAL + TABLE_DOCTYPE + STOP + DOCTYPE_DOCTYPE_ID;
              String[] JOIN_TABLES={fiedls1,fiedls2,"","","","","",fiedls3,"",fiedls4,fiedls5,"",fiedls6,fiedls7,fiedls8,"",""};
              String members = "," + "2" + "," + "3" + "," + "4" + "," + "5" + "," + "8" + "," + "11" + "," + "15" + "," + "16" + "," ;        
              for(int i=0;i<fields.length;i++){
                 if (members.indexOf("," + fields[i] + ",")<0){
                  LEFT_JOIN_ELEMENTS+=JOIN_TABLES[fields[i]];
                 }
              }
        return LEFT_JOIN_ELEMENTS;
    } 
    public FDocsrecv  InforPrintRecv(ResultSet rs,int[] fiedls) throws EException
    {
        final String LOCATION = "->InforPrintRecv()";
        FDocsrecv bean = new FDocsrecv();
         try {
            bean.createValues(fiedls.length);
                    for(int i=0;i<fiedls.length;i++){
                       bean.setValues(i,rs.getString(i+1));
                    }
                    }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
}
