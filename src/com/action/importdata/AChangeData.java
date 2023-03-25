package com.action.importdata;


import com.action.ACore;

import com.bo.importdata.BImportData;

import com.dao.importdata.DRanDom;

import com.exp.EException;

import com.form.FBeans;
import com.form.importdata.FImportData;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AChangeData extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
       final String LOCATION = this + "->executeAction()";
       String target =_LOGIN;
       FImportData bean = (FImportData)form;
       String anchor=bean.getValue(APP_ANCHOR,"");
       ActionErrors errors = new ActionErrors();
        if(anchor.equals("_DATA_LIST")){
        FImportData beanImport=new FImportData();
        FBeans beans=new FBeans();
        
        beanImport.setContentSearch(bean.getContentSearch());
            beans=new BImportData().getAllTablesInData(beanImport);
            request.setAttribute("BTablesList",beans);
            if(beans.size()>0){
                beanImport=(FImportData)beans.get(0);
                FBeans beansColumn=new FBeans();
                beansColumn=new BImportData().getAllColumnsInTable(beanImport);
                request.setAttribute("BColumnsList",beansColumn);
                request.setAttribute("BRecordList",new BImportData().getAllRecord(beanImport));
                beanImport.setPageIndex(bean.getPageIndex());
                request.setAttribute("BPaging",new BImportData().getPaging(beanImport));
                bean.setNameSQL("select * from " + beanImport.getNameTable());
                bean.setNameTable(beanImport.getNameTable());
            }else{
                request.setAttribute("BColumnsList",null);
                request.setAttribute("BRecordList",null);
                request.setAttribute("BPaging",null);
            }
            request.setAttribute("importdata",bean);
            target=_SUCCESS;
        }else if(anchor.equals(_SEARCH)){
        FImportData beanImport=new FImportData();
        beanImport.setContentSearch(bean.getContentSearch());
        beanImport.setNameSQL(bean.getNameSQL());
        beanImport.setId_sql(bean.getId_sql());
            beanImport.setPageIndex(bean.getPageIndex());
            request.setAttribute("BTablesList",new BImportData().getAllTablesInData(beanImport));
            request.setAttribute("BColumnsList",new BImportData().getAllColumnsInTable(beanImport));
            request.setAttribute("BRecordList",new BImportData().getAllRecord(beanImport));
            request.setAttribute("BPaging",new BImportData().getPaging(beanImport));
            request.setAttribute("importdata",bean);
            target=_SUCCESS;
        }else if(anchor.equals("_ADD_TABLE")){
        FImportData beanImport=new FImportData();
        beanImport.setNameTableAdd(bean.getNameTableAdd());
            beanImport.setContentSearch(bean.getContentSearch());
            beanImport.setNameSQL(bean.getNameSQL());
            beanImport.setId_sql(bean.getId_sql());
            BImportData bo=new BImportData();
            if(bo.addTable(beanImport)){
                errors.add("alert",new ActionError("alert.insert.successfull"));   
            }else{
                errors.add("alert",new ActionError("alert.insert.error"));   
            }
            request.setAttribute("BTablesList",new BImportData().getAllTablesInData(beanImport));
            request.setAttribute("importdata",bean);
            target="_SELECT_TABLE";
        }else if(anchor.equals(_DELETE)){
        FImportData beanImport=new FImportData();
        beanImport.setNameTable(bean.getNameTable());
            beanImport.setContentSearch(bean.getContentSearch());
            beanImport.setNameSQL(bean.getNameSQL());
            beanImport.setId_sql(bean.getId_sql());
            BImportData bo=new BImportData();
            bo.deleteTable(beanImport);
            request.setAttribute("BTablesList",new BImportData().getAllTablesInData(beanImport));
            request.setAttribute("importdata",bean);
            target="_SELECT_TABLE";
        }else if(anchor.equals("_SELECT_SQL")){
        FImportData beanImport=new FImportData();
            BImportData bo=new BImportData();
        beanImport.setContentSearch(bean.getContentSearch());
        if((bean.getNameSQL().indexOf("delete ")>=0)||(bean.getNameSQL().indexOf("update ")>=0)){
            if(bean.getNameSQL().indexOf(" ",bean.getNameSQL().indexOf("from ")+5)>=0){
                beanImport.setNameTable(bean.getNameSQL().substring(bean.getNameSQL().indexOf("from ")+5,bean.getNameSQL().indexOf(" ",bean.getNameSQL().indexOf("from ")+5)));
            }else{
                beanImport.setNameTable(bean.getNameSQL().substring(bean.getNameSQL().indexOf("from ")+5));
            }
            beanImport.setId_sql(0);
            beanImport.setNameSQL(bean.getNameSQL());
            bo.update(beanImport);
        }else{
            beanImport.setId_sql(bean.getId_sql());
            beanImport.setNameSQL(bean.getNameSQL());
        }
            beanImport.setPageIndex(bean.getPageIndex());
            request.setAttribute("BColumnsList",new BImportData().getAllColumnsInTable(beanImport));
            request.setAttribute("BRecordList",new BImportData().getAllRecord(beanImport));
            request.setAttribute("BPaging",new BImportData().getPaging(beanImport));
            request.setAttribute("importdata",bean);
            target=anchor;
        }else if(anchor.equals("_SELECT_TABLE")){
        FImportData beanImport=new FImportData();
        beanImport.setContentSearch(bean.getContentSearch());
        beanImport.setNameSQL(bean.getNameSQL());
        beanImport.setId_sql(bean.getId_sql());
            beanImport.setPageIndex(bean.getPageIndex());
            request.setAttribute("BTablesList",new BImportData().getAllTablesInData(beanImport));
            request.setAttribute("importdata",bean);
            target=anchor;
        }else if(anchor.equals(_SELECT)){
        FImportData beanImport=new FImportData();
        FBeans beans=new FBeans();
        beanImport.setContentSearch(bean.getContentSearch());
        beanImport.setNameTable(bean.getNameTable());
            beanImport.setPageIndex(bean.getPageIndex());
            request.setAttribute("BTablesList",new BImportData().getAllTablesInData(beanImport));
            beans=new BImportData().getAllColumnsInTable(beanImport);
            request.setAttribute("BColumnsList",beans);
            request.setAttribute("BRecordList",new BImportData().getAllRecord(beanImport));
            request.setAttribute("BPaging",new BImportData().getPaging(beanImport));

            if(bean.getNameTable()!=null && !bean.getNameTable().equals("")){
                bean.setNameSQL("select * from " + bean.getNameTable());
            }
            request.setAttribute("importdata",bean);
            target=_SUCCESS;
        }else if(anchor.equals(_CREATE)){
            if((bean.getRecordNumber()>0)||(bean.getRecordNumber()<500001)){
            FBeans beans=new FBeans();
            FImportData beanImport=new FImportData();
            BImportData bo=new BImportData();
            beanImport.setContentSearch(bean.getContentSearch());
            beanImport.setNameTable(bean.getNameTable()); 
            beanImport.setRecordNumber(bean.getRecordNumber());
            beanImport.setFieldName(bean.getFieldName());
            beanImport.setPageIndex(bean.getPageIndex());
            beans=new BImportData().getAllColumnsInTable(beanImport);
            request.setAttribute("BColumnsList",beans);
            boolean errorValidate=true;
           String[][] mang2c=new String[beans.size()][];
            for (int j=0;j<beans.size();j++){
                FImportData beanT=(FImportData)beans.get(j);
                if((bean.getValue(beanT.getColumnName()+"from")==null)||(bean.getValue(beanT.getColumnName()+"from").equals(""))){
                }else{
                int start=bean.stringToInt(bean.getValue(beanT.getColumnName()+"from"));
                int end=bean.stringToInt(bean.getValue(beanT.getColumnName()+"to"));
                int rows=bean.getRecordNumber();
                if(start>end){
                    errors.add("alert",new ActionError("errors.insertdata.null"));   
                    errorValidate=false;
                    break;
                }
                    if(beanT.getNotNull()==1){
                        if((start<1)||(end<1)){
                            errors.add("alert",new ActionError("errors.insertdata.null"));   
                            errorValidate=false;
                            break;
                        
                        }
                    }

                if(beanT.getColumnTypeName().indexOf("int")==0){
                    int[] randomNumber=  new DRanDom().getRandomLong(start, end, rows);
                    mang2c[j]=new String[randomNumber.length];
                        for (int i=0;i<randomNumber.length;i++)  {
//                            //.println(randomNumber[i]);
                            mang2c[j][i]=randomNumber[i]+"";
                    }
                }else if(beanT.getColumnTypeName().indexOf("var")==0){
                    String[] randomString=  new DRanDom().getRandomString(start, end, rows);
                    mang2c[j]=new String[randomString.length];
                        for (int i=0;i<randomString.length;i++)  {
                            mang2c[j][i]=randomString[i];
                        }
                }else if((beanT.getColumnTypeName().indexOf("time")==0)||(beanT.getColumnTypeName().indexOf("date")==0)){
                }else{
                    String[] randomString=  new DRanDom().getRandomString(start, end, rows);
                    mang2c[j]=new String[randomString.length];
                        for (int i=0;i<randomString.length;i++)  {
                            mang2c[j][i]=randomString[i];
                        }
                }
                }
            }
            
            if(errorValidate){
                if(bo.insert(beans,beanImport,mang2c)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("alert.insert.error"));   
                }
            }
            }else{
                errors.add("alert",new ActionError("errors.insertdata.recordnumber"));
            }
                request.setAttribute("importdata",bean);
                target=anchor;

        }
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }

}
