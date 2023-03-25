package com.dao.disability.report;


import com.dao.admin.doc.DSqlAdminDoc;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.reportSystem.FReportSystem;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DReport  extends DSqlAdminDoc
{
    public FBeans getMutiData(Connection cnn,FReportSystem bean,FBeans beanTs) throws EException
    {
      final String LOCATION = this.toString() + "~~>getDataToExport()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      String[] values=null;
      FBeans beans=new FBeans();
      try
      {
        List params =new ArrayList();
        if(bean.getFromDate()!=null && !bean.getFromDate().equals("")){
            params.add(bean.stringToSqlDate(bean.getFromDate()));
        }
        if(bean.getToDate()!=null && !bean.getToDate().equals("")){
            params.add(bean.stringToSqlDate(bean.getToDate()));
        }
        if(bean.getListId()>0){
            params.add(bean.getListId());
        }
        prpstm = prepareStatement(cnn,bean.getSelectSql(),params); 
        rs = prpstm.executeQuery();
            String[] fields=new String[rs.getMetaData().getColumnCount()];
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
            fields[i]=rs.getMetaData().getColumnLabel(i+1);
            }
            beans.add(fields);
        while(rs != null && rs.next())
        {
            values=new String[rs.getMetaData().getColumnCount()];
            for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                
                if(rs.getMetaData().getColumnTypeName(i+1).indexOf("timestamp")>=0){
                    values[i]=bean.dateToString(rs.getDate(i+1));
                }else if((rs.getMetaData().getColumnLabel(i+1).indexOf(bean.ncrToString("H&#7895; tr&#7907;"))>=0)||(rs.getMetaData().getColumnLabel(i+1).indexOf(bean.ncrToString("D&#7841;ng t&#7853;t"))>=0)){
                    String temp=rs.getString(i+1);
                    values[i]="";
                    if(temp!=null)
                    for(int t=0;t<beanTs.size();t++){
                    FTinh beanT= (FTinh)beanTs.get(t);
                        if(temp.indexOf("#"+beanT.getId()+"#")>=0){
                            values[i]+=beanT.getName()+";";
                        }
                    }
                }else{

                    values[i]=rs.getString(i+1);
                }
                if(values[i]==null)
                    values[i]="";
            }
            beans.add(values);
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
