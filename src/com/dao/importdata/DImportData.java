package com.dao.importdata;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.importdata.FImportData;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class DImportData  extends DSqlImportData
{
    public FBeans getAllTablesInData(Connection cnn,FSeed seed) throws EException
      {
        final String LOCATION = this.toString() + "getAllTablesInData()";
        FBeans beans = new FBeans();
        FImportData bean=(FImportData)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL_SELECT=SQL_SELECT_IMPORTDATA_TABLES;
        try {
          List params =new ArrayList();
            if(bean.getContentSearch()!=null && !bean.getContentSearch().equals("")){
                SQL_SELECT+= AND + IMPORTDATA_TABLENAME + LIKE + QUESTION ;
                params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
            }
            SQL_SELECT+=SQL_SELECT_IMPORTDATA_TABLES_ORDERBY;
          prstm = prepareStatement(cnn,SQL_SELECT,params);
          rs = prstm.executeQuery();
          beans = new FBeans();
          int i=0;
         while((rs != null) && (rs.next())){
              i++;
              bean = getInformation(rs);
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
    public FBeans getAllColumnsInTable(Connection cnn,FSeed seed) throws EException
      {
        final String LOCATION = this.toString() + "getAllColumnsInTable()";
        FBeans beans = new FBeans();
        FImportData bean=(FImportData)seed;
        PreparedStatement prstm = null;
        ResultSet rs=null;
        ResultSet rs2 = null;
        String SQL_SELECT=SQL_SELECT_IMPORTDATA_COLUMNS;
        try {
          List params =new ArrayList();
          SQL_SELECT+=bean.getNameTable();
            if(bean.getId_sql()==1){
                SQL_SELECT= bean.getNameSQL();
            }

          prstm = prepareStatement(cnn,SQL_SELECT,params);
          DatabaseMetaData dbmt = cnn.getMetaData();
          rs2 = dbmt.getPrimaryKeys(null,null,bean.getNameTable());
            
          String columnPrimaryKey="";
          while(rs2.next()){
            columnPrimaryKey+=rs2.getString("COLUMN_NAME");
          }
          rs = prstm.executeQuery();
            ResultSetMetaData rsmd =  rs.getMetaData();
          
            for(int i=1;i<rsmd.getColumnCount()+1;i++){
                FImportData beanT = new FImportData();
                if(columnPrimaryKey.equals(rsmd.getColumnName(i))){
                    beanT.setNotNull(1);
                }else{
                    beanT.setNotNull(0);
                }
                boolean rsmdata_NoNulls =(rsmd.isNullable(i)==java.sql.DatabaseMetaData.columnNoNulls);
                if(rsmdata_NoNulls){
                    beanT.setNotNull(beanT.getNotNull()+1);
                }
                beanT.setColumnName(rsmd.getColumnName(i));
                if(rsmd.getColumnTypeName(i).indexOf("int")==0){
                    beanT.setColumnTypeName("Integer");
                }else if((rsmd.getColumnTypeName(i).indexOf("time")==0)||(rsmd.getColumnTypeName(i).indexOf("date")==0)){
                    beanT.setColumnTypeName("Date");
                }else if(rsmd.getColumnTypeName(i).indexOf("float")==0){
                    beanT.setColumnTypeName("Float");
                }else{
                    beanT.setColumnTypeName("String");
                }
                
                beans.add(beanT);
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
    public FBeans getPaging(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getPaging()";
      FBeans beans = new FBeans();
      FImportData bean=(FImportData)seed;
      PreparedStatement prstm = null;
      ResultSet rs=null;
      String SQL_SELECT=SQL_SELECT_IMPORTDATA_COLUMNS;
      try {
        List params =new ArrayList();
        SQL_SELECT+=bean.getNameTable();
          if(bean.getId_sql()==1){
              SQL_SELECT= bean.getNameSQL();
          }
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
         beans = new FBeans();
         beans.setTotalRows(count(cnn,SQL_SELECT,params));
         beans.setPageIndex(bean.getPageIndex());
         if(beans.getFirstRecord()<=1){
           rs.beforeFirst();
         }else{
           rs.absolute(beans.getFirstRecord()-1);
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
    public String[][] getAllRecord(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAllRecord()";
      FBeans beans = new FBeans();
      FImportData bean=(FImportData)seed;
      PreparedStatement prstm = null;
      ResultSet rs=null;
      String SQL_SELECT=SQL_SELECT_IMPORTDATA_COLUMNS;
        String[][] mang=new String[20][];
      try {
        List params =new ArrayList();
        SQL_SELECT+=bean.getNameTable();
          if(bean.getId_sql()==1){
              SQL_SELECT= bean.getNameSQL();
          }
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
          ResultSetMetaData rsmd =  rs.getMetaData();
          for(int i=1;i<rsmd.getColumnCount()+1;i++){
              FImportData beanT = new FImportData();
              beanT.setColumnName(rsmd.getColumnName(i));
              beanT.setColumnTypeName(rsmd.getColumnTypeName(i));
              beans.add(beanT);
          }
         FBeans beansT = new FBeans();
         beansT.setTotalRows(count(cnn,SQL_SELECT,params));
         beansT.setPageIndex(bean.getPageIndex());
         if(beansT.getFirstRecord()<=1){
           rs.beforeFirst();
         }else{
           rs.absolute(beansT.getFirstRecord()-1);
         }
         int rows=beansT.getTotalRows();
         mang=new String[rows][];
         int i=0;
         while((rs != null) && (rs.next())&&(i<AppConfigs.APP_ROWS_VIEW)){
             i++;
               mang[i-1] = new String[beans.size()];
               for (int j = 0; j < beans.size(); j++){ 
                 mang[i-1][j] = rs.getString(j+1);
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
      return mang;        
    }     
    public FImportData getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FImportData bean = new FImportData();
         try {
             bean.setNameTable(rs.getString(IMPORTDATA_TABLENAME));
             
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
 
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FImportData bean = (FImportData)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getNameTable());
         }catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
 
    public boolean insert(Connection cnn,FBeans beans,FSeed seed,String[][] mang2c) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean flag = false;
        boolean result = false;
        PreparedStatement prstm = null;
        FImportData bean=(FImportData)seed;
          FImportData beanT=new FImportData();
        String SQL_INSERT=INSERT_INTO+bean.getNameTable();
        String columns="";
        try
        {
             List params =new ArrayList();
             for (int i=0;i<beans.size();i++)  {
                 beanT = (FImportData)beans.get(i);
                 if(beanT.getNotNull()==2){
                     params.remove(beanT.getColumnName());
                 }else{
                 columns+=","+beanT.getColumnName();
                 params.add(beanT.getColumnName());
                 }
             }
             String[] columns_values={columns.substring(1)};
             SQL_INSERT+=FIELDS(columns_values,true) + VALUES(params.size());
//             //.println(SQL_INSERT);
            prstm = cnn.prepareStatement(SQL_INSERT);
                 for (int i =0;i<bean.getRecordNumber();i++){
                     int k=1;
                     for (int j=0;j<beans.size();j++){
                          beanT = (FImportData)beans.get(j);
                          if(beanT.getNotNull()<2){
                              if(beanT.getColumnTypeName().indexOf("int")==0){
                                  if(mang2c[j]==null){
                                      prstm.setInt(k++,0);
                                  }else{
                                      prstm.setInt(k++,bean.stringToInt(mang2c[j][i]));
                                  }
                              }else if((beanT.getColumnTypeName().indexOf("time")==0)||(beanT.getColumnTypeName().indexOf("date")==0)){
                                  prstm.setTimestamp(k++,new Timestamp(System.currentTimeMillis()));  
                              }else{
                                if(mang2c[j]==null){
                                    prstm.setString(k++,"");
                                }else{
                                  prstm.setString(k++,mang2c[j][i]);
                                }
                              }
                          }                     
                    }
                         prstm.addBatch(); 
                         flag = true;
                 }
                  if (flag){
                      result=prstm.executeBatch().length>0;
                  }
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
    public boolean addTable(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
          PreparedStatement prstm1 = null;
        FImportData bean=(FImportData)seed;
        String SQL_EXIT=SELECT + STAR + FROM + bean.getNameTableAdd();
        try
        {
             List params =new ArrayList();
             params.add(bean.getNameTableAdd());
//             //.println(SQL_INSERT_IMPORTDATA_ADDTABLE);

             prstm1 = cnn.prepareStatement(SQL_EXIT);
             result=prstm1.execute();
            if(result){
             prstm = prepareStatement(cnn,SQL_INSERT_IMPORTDATA_ADDTABLE,params);
             result=prstm.execute();
             }
         }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
            result=false;
        }
        finally
        {
          closePreparedStatement(prstm);
        }
      return result;
      }   
      
    public boolean delete(Connection cnn, FSeed seed)throws EException
    {
      FImportData bean = (FImportData)seed;
      return delete(cnn, TABLE_IMPORTDATA, IMPORTDATA_TABLENAME + EQUAL + bean.getNameTable())>0;
    }        
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      String SQL_SELECT="";
      try
      {
        FImportData bean = (FImportData)seed;
          SQL_SELECT= bean.getNameSQL();
        prstm = cnn.prepareStatement(SQL_SELECT);
        result = prstm.executeUpdate()>0;
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
}
