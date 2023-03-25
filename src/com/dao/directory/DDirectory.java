package com.dao.directory;


import com.dao.admin.users.DUsers;
import com.dao.tasks.DSqlTasks;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.users.FUser;
import com.form.directory.FDirectory;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDirectory extends DSqlTasks{
   
    public FBeans getAllSearch(Connection cnn,FSeed seed) throws EException{
      final String LOCATION = this.toString() + "getAllSearch()";
      FBeans beans = null;
      FDirectory bean = (FDirectory)seed;
      PreparedStatement prstm = null;
      FUser beanTemp=null;    
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        String SQL = SQL_SELECT_ALL_USERS;
        if (bean.getName()!=null && !bean.getName().equals("")){
            SQL += AND + OPEN + SQL_SELECT_ALL_USERS_FULLNAME + SQL_SELECT_ALL_USERS_PHONE + SQL_SELECT_ALL_USERS_ADDRESS + CLOSE;
            params.add(PER_CENT + bean.getName() + PER_CENT);
            params.add(PER_CENT + bean.getName() + PER_CENT);
            params.add(PER_CENT + bean.getName() + PER_CENT); 
        }
        
        if (bean.getDepartmentId()>0){
            SQL += SQL_SELECT_ALL_USERS_DEPARTMENT;
            params.add(bean.getDepartmentId());
        }
        
        //println(SQL);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                          
        DUsers dao = new DUsers();
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            beanTemp =new FUser();            
            beanTemp = dao.getInformation(cnn,rs);          
            beans.add(beanTemp);
           
        }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }    
  
   
}
