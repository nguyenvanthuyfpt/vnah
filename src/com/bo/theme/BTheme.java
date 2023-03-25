package com.bo.theme;


import com.dao.connection.DBConnector;
import com.dao.theme.DTheme;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.theme.FTheme;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BTheme {
 
    public FBeans getAll() throws  EException
    {
      final String LOCATION = this.toString() + "~>getAll()";
      Connection cnn = null;
      DTheme dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DTheme();
        beans = dao.getAll(cnn);                          
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
    public FBeans getAllActive() throws  EException
    {
      final String LOCATION = this.toString() + "~>getAll()";
      Connection cnn = null;
      DTheme dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DTheme();
        beans = dao.getAllActive(cnn);                          
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
    public FTheme getById(FTheme bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getById()";
     Connection cnn = null;    
     FTheme beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();       
       DTheme dao = new DTheme();
       beantemp = dao.getById(cnn, bean);    
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
     return beantemp;
    }
    
    
    public String[] getTopActive() throws  EException
    {
     final String LOCATION = this.toString() + "~>getById()";
     Connection cnn = null;    
     String[] themes=null;
     try
     {      
       cnn = DBConnector.getConnection();       
       DTheme dao = new DTheme();
       themes = dao.getTopActive(cnn);    
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
     return themes;
    }
    
    
    public boolean addNew(FSeed seed) throws  EException {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = true;
      Connection conn = null;    
      try{
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DTheme dao = new DTheme();             
          result = dao.addNew(conn,seed);
          DBConnector.endTransaction(conn);    
       }
       catch (EException ex) 
       {
          DBConnector.rollBackTransaction(conn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);         
       }
       finally 
       {
          DBConnector.closeConnection(conn);
       }    
       return result;
    }
    
    public boolean update(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;   
      boolean result = false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DTheme dao = new DTheme();                  
          result =dao.update(conn,seed);                          
          DBConnector.endTransaction(conn);            
       }
       catch (EException ex) 
       {
          DBConnector.rollBackTransaction(conn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
       }
       finally 
       {
          DBConnector.closeConnection(conn);
       }    
       return result;
    }  

    public boolean delete(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->delete()";
      Connection conn = null; 
      boolean result = false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DTheme dao = new DTheme();           
          result = dao.delete(conn,seed);
          DBConnector.endTransaction(conn);            
       }
       catch (EException ex) 
       {
          DBConnector.rollBackTransaction(conn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
       }
       finally 
       {
          DBConnector.closeConnection(conn);
      }    
      return result;
    }  
    
}
