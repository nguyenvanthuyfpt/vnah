package com.bo.doc.docsSearch;


import com.dao.connection.DBConnector;
import com.dao.doc.docsSearch.DDocsReport;
import com.dao.doc.docsSearch.DDocsSearch;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsSearch.FFilesSearch;

import com.lib.AppConfigs;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;


public class BDocsSearch
{
    public String exportExcel(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException
    {
      final String LOCATION = this.toString() + "~>exportExcel()";
      Connection cnn = null;
      String result = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        DDocsReport dao = new DDocsReport();
        result = dao.exportExcel(beans, seed,excelFile);                          
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return result;
    } 
    
    public FBeans searchDocReference(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>searchDocReference()";
      Connection cnn = null;
      DDocsSearch dao = null;
      FBeans beans = null;
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsSearch();
        beans = dao.searchDocReference(cnn,seed);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return beans;
    }
    
    public FBeans search(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>search()";
      Connection cnn = null;
      DDocsSearch dao = null;
      FBeans beans = null;
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsSearch();
        beans = dao.search(cnn,seed);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return beans;
    }
   
   
   
   
    public FFilesSearch getByIdFile_Recv(FFilesSearch bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>search()";
      Connection cnn = null;
      DDocsSearch dao = null;
        FFilesSearch beanTemp=new FFilesSearch();
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsSearch();
        beanTemp = dao.getByIdFile_Recv(cnn,bean);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return beanTemp;
    }
    
    public FFilesSearch getByIdFile_Send(FFilesSearch bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>search()";
      Connection cnn = null;
      DDocsSearch dao = null;
        FFilesSearch beanTemp=new FFilesSearch();
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsSearch();
        beanTemp = dao.getByIdFile_Send(cnn,bean);
        DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return beanTemp;
    }
}
