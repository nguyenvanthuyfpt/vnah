package com.dao.doc.docreport;


import com.dao.admin.departments.DDepartments;
import com.dao.admin.users.DUsers;
import com.dao.doc.docsrecv.DFilesRecv;
import com.dao.doc.docssend.DFilesSend;
import com.dao.doc.from.DFrom;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.form.doc.docreport.FDocReport;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;

import com.inf.IConstants;
import com.inf.IRoles;

import com.lib.AppConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class DDocReport extends DSqlForYou
{
    public FBeans getUserByDepartmentID(Connection cnn,int idDepartment,int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getUserByDepartmentID()";
      FBeans beans = null;
      try
      {
        String SQL = SQL_SELECT_USERS_BY_DEPARTMENT_ID;        
        if(idDepartment>0){
            DDepartments dao = new DDepartments();            
            SQL += AND + USERS_DEPARTMENT_ID + IN + OPEN + dao.getMembers(cnn,idDepartment) + CLOSE;
        }
        beans = getMultiRecords(cnn,SQL,null,pageIndex);
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
      }
      return beans;        
    }
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params,int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
          prstm = prepareStatement(cnn,SQL_SELECT + AND + USERS_ROLE + DIFF + IRoles.PADMINISTRATOR + ORDER_BY + TABLE_USERS + STOP + USERS_FULLNAME + ASC,params);          
          rs = prstm.executeQuery();
          beans = new FBeans();
              rs.last(); 
              beans.setTotalRows(rs.getRow());
              beans.setPageIndex(pageIndex);
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
        int i=0;
        while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW)))
        {
            i++;
            FUser user = new DUsers().getInformation(cnn,rs);
            beans.add(user);
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
      public FBeans getTotalDocByStatus(Connection cnn,int workflowId,List params) throws EException 
    {
      final String LOCATION = this.toString() + "getTotalDocByStatus()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
        ////.println(workflowId==1?SQL_SELECT_TOTAL_DOC_STATUS_RECV:SQL_SELECT_TOTAL_DOC_STATUS_SEND);
        prpstm = prepareStatement(cnn,workflowId==1?SQL_SELECT_TOTAL_DOC_STATUS_RECV:SQL_SELECT_TOTAL_DOC_STATUS_SEND,params);
        rs = prpstm.executeQuery();
        FDocReport bean=null;
       
        
        while((rs != null) && (rs.next()))
        {
                bean= new FDocReport ();
                bean.setAmount(rs.getInt(PARAM_01));
                bean.setStatusName(rs.getString(PARAM_02));
                bean.setDescription(rs.getString(PARAM_02));
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
    
    public FBeans getTotalDocByTransfer(Connection cnn,int workflowId,List params) throws EException 
    {
      final String LOCATION = this.toString() + "getTotalDocByTransfer()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
        prpstm = prepareStatement(cnn,workflowId==1?SQL_SELECT_TOTAL_DOC_TRANSFER_RECV:SQL_SELECT_TOTAL_DOC_TRANSFER_SEND,params);
        rs = prpstm.executeQuery();
        FDocReport bean=null;
        while((rs != null) && (rs.next()))
        {
               bean= new FDocReport ();
               bean.setAmount(rs.getInt(PARAM_01));
               bean.setStatusName(rs.getString(PARAM_02));
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
    
    public FBeans getTotalDocByDocType(Connection cnn,int workflowId,List params) throws EException 
    {
      final String LOCATION = this.toString() + "getTotalDocByDocType()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
      //.println(workflowId==1?SQL_SELECT_TOTAL_DOC_TYPE_RECV:SQL_SELECT_TOTAL_DOC_TYPE_SEND);
          prpstm = prepareStatement(cnn,workflowId==1?SQL_SELECT_TOTAL_DOC_TYPE_RECV:SQL_SELECT_TOTAL_DOC_TYPE_SEND,params);
          rs = prpstm.executeQuery();
          FDocReport bean=null;
          while((rs != null) && (rs.next()))
          {
                 bean= new FDocReport ();
                 bean.setAmount(rs.getInt(PARAM_01));
                 bean.setStatusName(rs.getString(PARAM_02));
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
    
    public FBeans getTotalDocByBranch(Connection cnn,int workflowId,List params) throws EException 
    {
      final String LOCATION = this.toString() + "getTotalDocByBranch()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
      //.println(workflowId==1?SQL_SELECT_TOTAL_DOC_BRANCH_RECV:SQL_SELECT_TOTAL_DOC_BRANCH_SEND);
          prpstm = prepareStatement(cnn,workflowId==1?SQL_SELECT_TOTAL_DOC_BRANCH_RECV:SQL_SELECT_TOTAL_DOC_BRANCH_SEND,params);
          rs = prpstm.executeQuery();
          FDocReport bean=null;
          while((rs != null) && (rs.next()))
          {
                 bean= new FDocReport ();
                 bean.setAmount(rs.getInt(PARAM_01));
                 bean.setStatusName(rs.getString(PARAM_02));
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
    private String civiSql(String sql_select,int workflowId,int type,int totalReport,int checkObserver) throws EException{
        if(type==0 || type==6){
            sql_select=(totalReport==1 && checkObserver==1)?SQL_SELECT_TOTAL_DOC_BY_OBSERVER_PARAM[workflowId-1]:SQL_SELECT_TOTAL_DOC_BY_PARAM[workflowId-1];
        }else if(type==1){//nhom
            sql_select=SQL_SELECT_DOCS_GROUP[workflowId-1];
        }else if(type==2){//phong
            sql_select=SQL_SELECT_DOCS_DEP[workflowId-1];
        }else if(type==3){//noi nhan
           sql_select=SQL_SELECT_DOCS_FROM_RECV[workflowId-1];
        }else if(type==4){//noi nhan
             sql_select=SQL_SELECT_DOCS_BY_DOCTYPE[workflowId-1];
        }
        return sql_select;
    }
    private String civiSearch(FDocReport bean,String SQL_SELECT) throws EException{
           String SQL_WHERE = (bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID)?SQL_SELECT_TOTAL_DOC_RECV_BY_PARAM_ADD_USER_ID:SQL_SELECT_TOTAL_DOC_SEND_BY_PARAM_ADD_USER_ID;
            if (bean.getCheckObserver()==1 && bean.getTotalReport()==1){
                SQL_WHERE = "";
            }
            
            return SQL_SELECT + SQL_WHERE;
    }
    public FBeans getAllDoc(Connection cnn,FDocReport bean,int pageIndex,int views) throws EException 
    {
      final String LOCATION = this.toString() + "getAllDoc()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      List params = new ArrayList();
     
      params.add(bean.stringToSqlDate(bean.getFromDate()));
      params.add(bean.addDays(bean.stringToSqlDate(bean.getToDate()),1));
      if (bean.getCheckObserver()==0 || (bean.getCheckObserver()==1 && bean.getTotalReport()!=1)){
        params.add(bean.getUserId());   
      }
      String SQL_SELECT=civiSearch(bean,civiSql("",bean.getWorkflowId(),bean.getType(),bean.getTotalReport(),bean.getCheckObserver()));
      
      try
      {
          prpstm = prepareStatement(cnn,SQL_SELECT,params);
          prpstm.setFetchSize((1+pageIndex)*AppConfigs.APP_ROWS_VIEW);
          prpstm.setMaxRows((1+pageIndex)*AppConfigs.APP_ROWS_VIEW);
          rs = prpstm.executeQuery();
          beans = new FBeans();
          beans.setTotalRows(count(cnn,SQL_SELECT,params));
          beans.setPageIndex(pageIndex);
          if(beans.getFirstRecord()<=1){
          rs.beforeFirst();
          }else{
          rs.absolute(beans.getFirstRecord()-1);
          }
          int i=0;
          while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
          {
          i++;
                 if(bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID){
                    beans.add(getInforDocRecv(cnn,rs));
                 }else{
                    beans.add(getInforDocSend(cnn,rs,views));
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
    private FDocsrecv getInforDocRecv(Connection cnn,ResultSet rs) throws EException
    {
        FDocsrecv beantemp = new FDocsrecv();
         try {
             beantemp.setDocId(rs.getInt(DOCSRECV_DOC_ID));
             beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));
             beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
             beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
             Date date_docdate = rs.getDate(DOCSRECV_DOCDATE);
             if (date_docdate != null) beantemp.setDocDate(beantemp.dateToString(date_docdate,AppConfigs.APP_DATE_DEFAULT));                                  
             beantemp.setFromVnName(rs.getString(FROM_VN_NAME));
             beantemp.setAllFiles(new DFilesRecv().getAllByDocId(cnn,beantemp.getDocId()));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException("getInforDocRecv",sqle);
         }
         finally {
         }
         return beantemp;
    }
    
    private FDocssend getInforDocSend(Connection cnn,ResultSet rs,int views) throws EException
    {
        FDocssend beansend = new FDocssend();
         try {
             beansend.setDocId(rs.getInt(DOCSSEND_DOC_ID));
             beansend.setId(rs.getInt(DOCSSEND_DOC_ID));
             beansend.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
             beansend.setDocCode(rs.getString(DOCSSEND_DOCCODE));           
             Date date_docdate = rs.getDate(DOCSSEND_DOCDATE);
             if (date_docdate != null) beansend.setDocDate(beansend.dateToString(date_docdate,AppConfigs.APP_DATE_DEFAULT));                                               
             beansend.setSigner(rs.getString(DOCSSEND_SIGNER));
             beansend.setAllFiles(new DFilesSend().getAllByDocId(cnn,beansend.getDocId(),views));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException("getInforDocSend",sqle);
         }
         finally {
         }
         return beansend;
    }
    
    public FBeans getAllReportExcel(Connection cnn,FSeed seed,List params,int type) throws EException 
    {
      final String LOCATION = this.toString() + "getAllDoc()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocReport bean=(FDocReport)seed;
      if(!bean.getDocIds().equals("")){
      bean.setDocIds(bean.getDocIds().substring(1,bean.getDocIds().length()-1));
      }
      String SQL_SELECT="";String SQL_ORDER_BY="";
      FBeans beans =new FBeans();
      String table_name=bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID?TABLE_DOCSRECV:TABLE_DOCSSEND;
      String[] fieldsData=bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID?FIELDSDATE_RECV:FIELDSDATE_SEND;
      String Fields="";
        if(bean.getFields()!=null){
                for(int i=0;i<bean.getFields().length;i++){
                    if(Fields!="") Fields+=",";                  
                    Fields+=fieldsData[bean.getFields()[i]];
                }
        }else{return null;}
        Fields=Fields.replaceAll("#",table_name);
        
        if(type==0 || type==6){
            if (type==0){
                SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC[bean.getWorkflowId()-1].replaceAll("#",Fields) + SQL_WHERE_REPORT_EXCEL_DOC[bean.getWorkflowId()-1];           
//                //.println(SQL_SELECT);
            }else{
                SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC_DOC_ALL[bean.getWorkflowId()-1].replaceAll("#",Fields) + SQL_WHERE_REPORT_EXCEL_DOC_ALL[bean.getWorkflowId()-1];           
                if (bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID){
                    SQL_ORDER_BY=ORDER_BY +  TABLE_DOCSRECV + STOP + DOCSRECV_DOC_ID + DESC;
                }else{
                    SQL_ORDER_BY=ORDER_BY +  TABLE_DOCSSEND + STOP + DOCSSEND_DOC_ID + DESC;
                }
            }          
        }else if(type==1){//Groupt
         
             SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC[bean.getWorkflowId()-1].replaceAll("#",TABLE_GROUPS + STOP + GROUPS_NAME + COMMA + TABLE_GROUPS + STOP + GROUPS_GROUP_ID + COMMA + Fields) + SQL_ADD_REPORT_EXCEL_GROUPT[bean.getWorkflowId()-1] + SQL_WHERE_REPORT_EXCEL_GROUPT[bean.getWorkflowId()-1];//theo nhom         
             SQL_ORDER_BY=ORDER_BY +  TABLE_USERS + STOP + USERS_GROUP_ID;             
        }else if(type==2){//Phong
         
            SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC[bean.getWorkflowId()-1].replaceAll("#",TABLE_DEPARTMENTS + STOP + DEPARTMENTS_NAME + COMMA + TABLE_DEPARTMENTS + STOP + DEPARTMENTS_DEPARTMENT_ID + COMMA + Fields) + SQL_ADD_REPORT_EXCEL_DEP[bean.getWorkflowId()-1] + SQL_WHERE_REPORT_EXCEL_GROUPT[bean.getWorkflowId()-1];//theo nhom         
            SQL_ORDER_BY=ORDER_BY +  TABLE_USERS + STOP + USERS_DEPARTMENT_ID; 
            
        }else if(type==3){//Noi nhan
        
            SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC_FROM[bean.getWorkflowId()-1].replaceAll("#", Fields + COMMA + TABLE_DEPARTMENTS + STOP + DEPARTMENTS_NAME + COMMA + TABLE_DEPARTMENTS + STOP + DEPARTMENTS_DEPARTMENT_ID) + SQL_WHERE_REPORT_EXCEL_GROUPT[bean.getWorkflowId()-1];//theo nhom        
            SQL_ORDER_BY=ORDER_BY +  TABLE_DOCSRECV + STOP + DOCSRECV_STOREAGE_ID;            
        }else if(type==4){//Noi nhan
        
            SQL_SELECT=SQL_SELECT_REPORT_EXCEL_DOC_DOC_TYPE[bean.getWorkflowId()-1].replaceAll("#",Fields + COMMA + TABLE_DOCTYPE + STOP + DOCTYPE_NAME + COMMA + TABLE_DOCTYPE + STOP + DOCTYPE_DOCTYPE_ID) + SQL_WHERE_REPORT_EXCEL_GROUPT[bean.getWorkflowId()-1];//theo nhom        
            SQL_ORDER_BY=ORDER_BY +  TABLE_DOCTYPE + STOP + DOCTYPE_DOCTYPE_ID;
        }
        
      try
      {
//          //.println(SQL_SELECT + SQL_ORDER_BY);
          prpstm = prepareStatement(cnn,SQL_SELECT + SQL_ORDER_BY,params);
          rs = prpstm.executeQuery();
          beans = new FBeans();
          beans.setTotalRows(count(cnn,SQL_SELECT,params));
          while((rs != null) && (rs.next()))
          {
                 if(bean.getWorkflowId()==AppConfigs.DOCSRECV_WORKFLOWID){
                     beans.add(InforPrintRecv(rs,bean.getFields(),type));
                 }else{
                     beans.add(InforPrintSend(cnn,rs,bean.getFields(),type));
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
    public FDocsrecv  InforPrintRecv(ResultSet rs,int[] fiedls,int type) throws EException
    {
        final String LOCATION = "->InforPrintRecv()";
        FDocsrecv bean = new FDocsrecv();
        
         try {
         int len=fiedls.length;
         if(type>0 && type!=6){
             len+=2;
         }
         bean.createValues(len);
        for(int i=0;i<len;i++){
          
          if (((type>0 && i<len-2) || type==0) && (fiedls[i]==4 || fiedls[i]==5) || (i<len-2 && fiedls[i]==9) ){
              if (i<len-2 && fiedls[i]==9){
                  bean.setValues(i,"");
              }else{
                  Date date_docdate = rs.getDate(i+1);
                  if (date_docdate != null) bean.setValues(i,bean.dateToString(date_docdate,AppConfigs.APP_DATE_DEFAULT));
              }
          }else{   
               if ((type==6 && fiedls[i]==9) || (type==0 && fiedls[i]==9)){
                   bean.setValues(i,"");
               }else{
                bean.setValues(i,(rs.getString(i+1)==null)?"":rs.getString(i+1));
               }
          }
        }
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    public FDocssend  InforPrintSend(Connection cnn,ResultSet rs,int[] fiedls,int type) throws EException
    {
        final String LOCATION = "->InforPrintSend()";
        FDocssend bean = new FDocssend();
        String[] fieldsData=FIELDSDATE_SEND;
         try {
             int len=fiedls.length;
             if(type>0 && type!=6){                 
                 len+=2;
             }             
                bean.createValues(len);
                    for(int i=0;i<len;i++){                                               
                        if(((type>0 && i<len-2) || type==0) && fiedls[i]==7 && !rs.getString(i+1).equals("")){                                                        
                            bean.setValues(i,new DFrom().getAllInMember(cnn,rs.getString(i+1)));
                        }else if(((type>0 && i<len-2) || type==0) && (fiedls[i]==4 || fiedls[i]==5)){                                                        
                            Date date_docdate = rs.getDate(i+1);
                            if (date_docdate != null) bean.setValues(i,bean.dateToString(date_docdate,AppConfigs.APP_DATE_DEFAULT));                                  
                        }
                        else if ((type>0 && i<len-2 && fiedls[i]==7) || ((type==0 || type==6) && fiedls[i]==7)){
                            bean.setValues(i,new DFrom().getAllInMember(cnn,rs.getString(i+1)));
                        }else{
                            bean.setValues(i,rs.getString(i+1)==null?"":rs.getString(i+1));
                        }
                    }
                    
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    
    
    public String exportExcel(FBeans beans,FSeed seed,String excelFile,int type) throws  EException,FileNotFoundException,IOException {
        final String LOCATION = this.toString() + "~>exportExcel()";      
        FDocReport beanT=(FDocReport)seed;
//        //.println(AppConfigs.APP_SYSTEM_PATH);
//        //.println(AppConfigs.REPORT_FILE_PATH);
        String excelPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH;        
        String excelDown= excelPath + beanT.me.getSessionID();        
        File file = new File(excelPath,excelFile);    
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet=wb.getSheetAt(0);
        int x = AppConfigs.REPORT_SHEETLIST_X;
        int y = AppConfigs.REPORT_SHEETLIST_Y;
         if(beans!=null){
            setDS_Doc(beanT,beans,beanT.getFields(),y,x,sheet,wb,beanT.getWorkflowId(),type);              
         }
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);        
        fos.close();   
        return excelDown;     
     }
    private void setFiedlsSend(HSSFRow row, FDocssend bean,HSSFWorkbook wb)
    {
      for(int j=0;j<bean.getValues().length;j++){
          createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j) ,wb);
          setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
      }


    }
    private void setFiedlsRecv(HSSFRow row, FDocsrecv bean,HSSFWorkbook wb)
    {
      for(int j=0;j<bean.getValues().length;j++){
          createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),j!=9?bean.getValue(j):"" ,wb);
          setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
      }
    }
    private int setFiedlsRecvByGroup(HSSFSheet sheet,HSSFRow row, FDocsrecv bean,HSSFWorkbook wb,int groupId,int y,int index)
    {
        int a=Integer.parseInt(bean.getValue(bean.getValues().length-1).toString());
                 if(groupId!=a){
                     createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + 0),bean.getValue(bean.getValues().length-2),wb);
                     setStyleBold(row,(short)(AppConfigs.REPORT_SHEETLIST_X +0),wb);
                    // setColorBG(row,(short)(AppConfigs.REPORT_SHEETLIST_X +0),wb);
                     sheet.shiftRows(y,sheet.getLastRowNum(),1); 
                     row = sheet.createRow(y);
                     for(int j=0;j<bean.getValues().length-2;j++){
                             createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j),wb);
                             setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
                     }
                     y++; 
                 }else{
                     for(int j=0;j<bean.getValues().length-2;j++){
                             createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j),wb);
                             setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
                     }
                     
                 }
     return y;       
    }
    private int setFiedlsSendByGroup(HSSFSheet sheet,HSSFRow row, FDocssend bean,HSSFWorkbook wb,int groupId,int y)
    {
        //.println(bean.getValue(bean.getValues().length-1));
        int a=Integer.parseInt(bean.getValue(bean.getValues().length-1));
                 if(groupId!=a){
                     createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + 0),bean.getValue(bean.getValues().length-2) ,wb);
                     setStyleBold(row,(short)(AppConfigs.REPORT_SHEETLIST_X +0),wb);
                     sheet.shiftRows(y,sheet.getLastRowNum(),1); 
                     row = sheet.createRow(y);
                     for(int j=0;j<bean.getValues().length-2;j++){
                             createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j) ,wb);
                             setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
                     }
                     y++;
                 }else{
                     for(int j=0;j<bean.getValues().length-2;j++){
                             createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j) ,wb);
                             setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
                     }                       
                 }
         return y;   
    }
    private void setHeaderFiedls(HSSFRow row,int[] fields,HSSFWorkbook wb,int workflowId)
    {
      FSeed bean =new FSeed();
      for(int j=0;j<fields.length;j++){
        if (fields[j]==9){
            createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),workflowId==AppConfigs.DOCSRECV_WORKFLOWID?bean.ncrToString(workflowId==AppConfigs.DOCSRECV_WORKFLOWID?bean.ncrToString(com.inf.IConstants.HEADER_REPORT_RECV_DOCS[fields[j]]):bean.ncrToString(com.inf.IConstants.HEADER_REPORT_SEND_DOCS[fields[j]])):bean.ncrToString("N&#417;i nh&#7853;n"),wb);
        }else{
          createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),workflowId==AppConfigs.DOCSRECV_WORKFLOWID?bean.ncrToString(com.inf.IConstants.HEADER_REPORT_RECV_DOCS[fields[j]]):bean.ncrToString(com.inf.IConstants.HEADER_REPORT_SEND_DOCS[fields[j]]),wb);                
        }
          setStyleBold(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
      }
    }
      
    private void setDS_Doc(FDocReport bean,FBeans beans,int[] fields,int y,int x,HSSFSheet sheet,HSSFWorkbook wb,int workflowId,int type)
    {
            HSSFRow row;
            y++;
            HSSFRow rowHeader;
            
            FDocsrecv BRecv =new FDocsrecv();
            FDocssend BSend =new FDocssend();
            
            HSSFRow rowForm= sheet.createRow(2);
            if(workflowId==AppConfigs.DOCSRECV_WORKFLOWID){
            createCell(rowForm, (short)(AppConfigs.REPORT_SHEETLIST_X + 4),bean.ncrToString("S&#7892; C&#212;NG V&#258;N &#272;&#7870;N"),wb);
            }else{
            createCell(rowForm, (short)(AppConfigs.REPORT_SHEETLIST_X + 4),bean.ncrToString("S&#7892; C&#212;NG V&#258;N &#272;I"),wb);
            }
            setStyleBoldFont20(rowForm,(short)(AppConfigs.REPORT_SHEETLIST_X + 4),wb);
        
            HSSFRow rowTitle= sheet.createRow(3);
            createCell(rowTitle, (short)(AppConfigs.REPORT_SHEETLIST_X + 2),bean.ncrToString("S&#7893; theo d&#245;i ")+bean.ncrToString(IConstants.WORLFLOW_REPORT[workflowId-1]) + bean.ncrToString(" T&#7915; ng&#224;y ") + bean.getFromDate() + bean.ncrToString(" &#272;&#7871;n ng&#224;y ") + bean.getToDate(),wb);
            setStyleTitle(rowTitle,(short)(AppConfigs.REPORT_SHEETLIST_X + 2),wb);
        
            rowHeader = sheet.createRow(y++);
            setHeaderFiedls(rowHeader,fields,wb,workflowId);
            int groupId=0;
            int index=0;
            for(int k=0;k<beans.size();k++){                   
                index++;
                if(workflowId==1){
                    BRecv = (FDocsrecv)beans.get(k);
                }else{
                    BSend = (FDocssend)beans.get(k);
                }
                sheet.shiftRows(y,sheet.getLastRowNum(),1); 
                row = sheet.createRow(y++);
                if(workflowId==1){//cong van den
                    if(type>0 && type!=6){
                        y=setFiedlsRecvByGroup(sheet,row,BRecv,wb,groupId,y,index);
                        groupId=Integer.parseInt(BRecv.getValue(BRecv.getValues().length-1).toString());
                    }else{
                        setFiedlsRecv(row,BRecv,wb);
                    }
                }else{//cong van di 
                 if(type>0 && type!=6){
                     y=setFiedlsSendByGroup(sheet,row,BSend,wb,groupId,y);
                     groupId=Integer.parseInt(BSend.getValue(BSend.getValues().length-1).toString());
                 }else{
                     setFiedlsSend(row,BSend,wb);                          
                 }
                    
                }
            
            }               
    }
    //*************************************************************
    public void createCell(HSSFRow row, short column, String value,HSSFWorkbook wb){
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        //cell.setEncoding(wb.ENCODING_UTF_16);       
        cell.setCellValue(new HSSFRichTextString(value)+"");

    }  
    
    
    
    private void setStyle(HSSFRow row, short column, HSSFWorkbook wb)
    {
           HSSFCell cell = row.getCell(column);
           if(cell==null) cell = row.createCell(column);
           HSSFCellStyle style = wb.createCellStyle();
           style.setWrapText(true);       
           style.setBorderTop(HSSFCellStyle.BORDER_THIN);
           style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
           style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
           style.setBorderRight(HSSFCellStyle.BORDER_THIN);
           cell.setCellStyle(style);
           
        
    }
    
    //*************************************************************
    private void bold(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      
        style.setFont(font);           
        cell.setCellStyle(style);
    }
    ////***************
    private void wrap(HSSFRow row, short column, HSSFWorkbook wb)
    {
         HSSFCell cell = row.getCell(column);
         if(cell==null) cell = row.createCell(column);
         HSSFCellStyle style = wb.createCellStyle();              
         style.setWrapText(true);                                 
         cell.setCellStyle(style);
    }
    ////***************
    private void setStyleBold(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      
        font.setFontHeightInPoints((short) 10);
        font.setColor(HSSFColor.BLUE.index);
        style.setFont(font);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }
    private void setStyleTitle(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);      
        font.setFontHeightInPoints((short) 14);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);
        cell.setCellStyle(style);
    }
    private void setStyleBoldFont20(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 20);
        font.setFontName("VnTimeH");        
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);
        cell.setCellStyle(style);
    }
}
