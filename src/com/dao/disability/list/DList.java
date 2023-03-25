package com.dao.disability.list;


import com.dao.disability.DSqlDisability;
import com.dao.disability.search.DSearch;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.list.FList;
import com.form.disability.search.FSearch;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


public class DList extends DSqlDisability
{
   
    public FList getListReportById(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getListReportById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FList bean = (FList)seed;
      
      try
      {
            prpstm = cnn.prepareStatement(SQL_GET_LISTREPORT_BY_ID);                     
            prpstm.setInt(PARAM_01,bean.getIdSelect()>0?bean.getIdSelect():bean.getListId());
            rs = prpstm.executeQuery();
            if((rs != null) && (rs.next()))
            {              
              bean.setListId(rs.getInt(LISTREPORT_LIST_ID));
              bean.setListName(rs.getString(LISTREPORT_LIST_NAME));                            
              bean.setEmpEstablised(rs.getInt(LISTREPORT_EMP_ESTABLISHED));
              bean.setListCode(rs.getString(LISTREPORT_LIST_CODE));   
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
      return bean;        
    }
    //******************************************************************
   
    public FBeans getAllListReport(Connection cnn,FList bean) throws  EException
    {
     final String LOCATION = this.toString() + "~~>getAllListReport()";
     PreparedStatement prpstm = null;
     ResultSet rs = null;
     FBeans beans = null;     
     try
     {       
       prpstm = cnn.prepareStatement(SQL_SELECT_ALL_LISTREPORT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
       prpstm.setInt(PARAM_01,bean.getUserId());     
       rs = prpstm.executeQuery();
       FList beantemp = null;
       beans = new FBeans();
       if (rs!=null) rs.last(); 
//           beans.setRowsView(AppConfigs.APP_ROWS_VIEW_LIST);
           beans.setTotalRows(rs.getRow());
           beans.setPageIndex(bean.getPageIndexList());
           if(beans.getFirstRecord()<=1){
               rs.beforeFirst();
           }else{
               rs.absolute(beans.getFirstRecord()-1);
           }          
           int i=0;   
        while((rs != null) && (rs.next()) && (i<beans.getRowsView()) ){      
             beantemp = new FList();             
             beantemp=getInformation(cnn,rs);
             if (bean.getRadioCheck()==beantemp.getListId()){
                 beantemp.setSelected(1);;
             }else beantemp.setSelected(0);;
             beantemp.setAmountEmp(getAmountEmp(cnn,rs.getInt(LISTREPORT_LIST_ID)));
             beans.add(beantemp);
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
    //******************************************************************
     public FBeans getAllListReportCreate(Connection cnn,FList bean) throws  EException
     {
      final String LOCATION = this.toString() + "~~>getAllListReport()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;     
      try
      {       
        prpstm = cnn.prepareStatement(SQL_SELECT_ALL_LISTREPORT); 
        prpstm.setInt(PARAM_01,bean.getUserId());     
        rs = prpstm.executeQuery();
        FList beantemp = null;
        beans = new FBeans();
         while((rs != null) && (rs.next())){      
              beantemp = new FList();             
              beantemp=getInformation(cnn,rs);
              if (bean.getListId()==beantemp.getListId()){
                  beantemp.setSelected(1);;
              }else beantemp.setSelected(0);;
              beantemp.setAmountEmp(getAmountEmp(cnn,rs.getInt(LISTREPORT_LIST_ID)));
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
     //******************************************************************

    public FBeans getEmpByListReport(Connection cnn,FList bean) throws  EException
    {
     final String LOCATION = this.toString() + "~~>getEmpByListReport()";
     PreparedStatement prpstm = null;
     ResultSet rs = null;
     FBeans beans = null;     
     try
     {       
       prpstm = cnn.prepareStatement(SQL_SELECT_EMP_BY_LISTREPORT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);         
       prpstm.setInt(PARAM_01,bean.getListId()>0?bean.getListId():bean.getRadioCheck());
       //.println(SQL_SELECT_EMP_BY_LISTREPORT);
       rs = prpstm.executeQuery();
       FList beantemp = null;
       beans = new FBeans();
       if (rs!=null)
         rs.last(); 
         beans.setTotalRows(rs.getRow());
         beans.setPageIndex(bean.getPageIndex());
         if(beans.getFirstRecord()<=1){
             rs.beforeFirst();
         }else{
             rs.absolute(beans.getFirstRecord()-1);
         }
         int i=0;      
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW) ){      
            FSearch beanS = new FSearch();             
             beanS=getInformationEmp(cnn,rs);               
             beans.add(beanS);
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

     //***************************************************************
     public boolean delete(Connection cnn, FSeed seed)throws EException
     {
       final String LOCATION = this.toString() + "~~>delete()";
         boolean result=true;
       FList bean = (FList)seed;
       try
       {
           delete(cnn, TABLE_LIST_EMP, LIST_EMP_LIST_ID + EQUAL + bean.getIdSelect());
           delete(cnn, TABLE_LISTREPORT, LISTREPORT_LIST_ID + EQUAL + bean.getIdSelect());         
       }
       catch(EException ex)
       {
         result=false;
         if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
       }
         return result;
     }     
   //********************************************    
     public boolean deleteEmp(Connection cnn, FSeed seed)throws EException
     {
       final String LOCATION = this.toString() + "~~>deleteList()";
         boolean result=false;
       FList bean = (FList)seed;
       try
       {
          int lisId=bean.getListId()>0?bean.getListId():bean.getIdSelect();
          if (bean.getCheckEmp()!=null){                   
               for (int i=0;i<bean.getCheckEmp().length;i++){                     
                     result=delete(cnn, TABLE_LIST_EMP, LIST_EMP_EMPLOYEE_ID + EQUAL + bean.getCheckEmp()[i] + AND + LIST_EMP_LIST_ID + EQUAL + lisId)>0;                   
               } 
          } 
       }
       catch(EException ex)
       {
         if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
       }
         return result;
     }     
     //********************************************
     public boolean update(Connection cnn, FSeed seed) throws  EException
     {
       final String LOCATION = this.toString() + UPDATE;
       PreparedStatement prstm = null;
       boolean result = false;
       FList bean = null;
       try
       {
         bean = (FList)seed;
         List params = setParams(bean);
             params.add(bean.getListId()>0?bean.getListId():bean.getIdSelect());                     
             prstm = cnn.prepareStatement(SQL_UPDATE_LISTREPORT);
            for(int i=0;i<params.size();i++){
                if(params.get(i)==null){
                    prstm.setNull(PARAM_01+i,Types.DATE);
                }else{
                    prstm.setObject(PARAM_01+i,params.get(i));
                }
            }
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
      //***********************************************
       public boolean addNew(Connection cnn, FSeed seed) throws  EException
       {
         final String LOCATION = this.toString() + "addNew()";
         boolean result = false;
         PreparedStatement prstm = null;
         FList bean=(FList)seed;
         try
         {
           List params = setParams(seed);
           prstm = prepareStatement(cnn,SQL_SELECT_LISTREPORT_ADDNEW,params);
           result = prstm.executeUpdate()>0 ;            
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
    public boolean isNotExitsEmployeeId(Connection cnn, int listId,int employeeId) throws EException
    {
      final String LOCATION = this.toString() + "~~>isExitsEmployeeId()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;    
      try
      {        
        prstm = cnn.prepareStatement(SQL_ADDNEW_LIST_EMP_CHECK_EMPLOYEE_ID);
//        //.println(SQL_ADDNEW_LIST_EMP_CHECK_EMPLOYEE_ID);
        prstm.setInt(PARAM_01,listId);
        prstm.setInt(PARAM_02,employeeId);
        rs = prstm.executeQuery();
        if ((rs != null) && rs.next()){
            result =false;
        }
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);      
      }
      return result;        
    }
        //******************************************** 
         public boolean addNewListEmp(Connection cnn, FSeed seed,int index) throws  EException
         {
           final String LOCATION = this.toString() + "addNewListEmp()";
           boolean result = false,check=false;
           PreparedStatement prstm = null;
           PreparedStatement prstm1 = null;
           FSearch bean=(FSearch)seed;
           try
           {             
             if (index==1){bean.setListId(getListIdDesc(cnn,bean));       
             }  
             if (bean.getCheckEmpAll()!=1){               
                 String id="";  
                 String temp="";
                 String SQL="";                
                   if (bean.getEmps().length()>=3){                       
                       temp+=SQL_LIST_EMP_UPDATE; 
                       id+=bean.getEmps().substring(1,bean.getEmps().length());
                       id=id.substring(0,id.length()-1);                       
                       SQL += temp.replaceFirst("#",id);
                       prstm1=cnn.prepareStatement(SQL);                                         
                       prstm1.setInt(PARAM_01,bean.getListId());
                       prstm1.setInt(PARAM_02,bean.getListId());                      
                       result = prstm1.executeUpdate()>0; 
                   }                
             }
             else{
                    prstm = cnn.prepareStatement(SQL_ADDNEW_LIST_EMP); 
//                    //.println(SQL_ADDNEW_LIST_EMP_TEMP);                  
                    FBeans beans=new FBeans();
                    DSearch daoS= new DSearch();
                    beans=daoS.getAll(cnn,bean);                              
                    FSearch beanemployee = new FSearch();
                    for(int i=0;i<beans.size();i++){  
                            beanemployee =(FSearch)beans.get(i);                                          
                            prstm.setInt(PARAM_01,bean.getListId());
                            prstm.setInt(PARAM_02,beanemployee.getId());                           
                            prstm.addBatch();
                            check=true;                    
                    }
                   if (check){                       
                       result=prstm.executeBatch().length>0;                                       
                   }
             }
           }
           catch(Exception sqle)
           {
//           //.println(sqle);
             if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);        
           }
           finally
           {
             closePreparedStatement(prstm);
           }
           return result;
         }
         
   
         //***********************************************
          public int  getListIdDesc(Connection cnn, FSearch bean) throws  EException
          {
           final String LOCATION = this.toString() + "~~>getListIdDesc()";
           PreparedStatement prpstm = null;
           ResultSet rs = null;      
           int listID=0;
           
           try
           {       
              prpstm = cnn.prepareStatement(SQL_SELECT_TOP_LISTREPORT);                  
             
              rs = prpstm.executeQuery();
              if ((rs != null) && rs.next()){                
                 listID=rs.getInt(LISTREPORT_LIST_ID);               
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
           return listID;
          }
          //***********************************
           public boolean deleteListEmp(Connection cnn, FSeed seed)throws EException
           {
             final String LOCATION = this.toString() + "~~>deleteListEmp()";
             boolean result=false;
             FSearch bean = (FSearch)seed;
             try
             {
                 result=delete(cnn, TABLE_LIST_EMP, LIST_EMP_LIST_ID + EQUAL + bean.getListId())>0;                
             }
             catch(EException ex)
             {
               if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
             }
             return result;
           }     
           
    //***********************************
     public boolean deleteListEmpAll(Connection cnn, FSeed seed)throws EException
     {
       final String LOCATION = this.toString() + "~~>deleteListEmp()";
       boolean result=false;
       FSearch bean = (FSearch)seed;
       try
       {
           result=delete(cnn, TABLE_LIST_EMP, LIST_EMP_LIST_ID_TEMP + EQUAL + bean.getListId())>0;                
       }
       catch(EException ex)
       {
         if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
       }
       return result;
     }     
           //********************************************
         
      public List setParams(FSeed seed) throws EException {
          final String LOCATION = "->setParams()";
          FList bean = (FList)seed;      
          List params = new ArrayList();
          try {
              params.add(bean.getListCode());  
              params.add(bean.getListName());                      
              params.add(new java.sql.Timestamp(new java.util.Date(System.currentTimeMillis()).getTime()));  
              params.add(bean.getUserId());
         
          } catch (Exception exp) {
              if (AppConfigs.APP_DEBUG)
                  throw new EException(LOCATION, exp);
          } finally {
          }
          return params;
      }
      
    //**********************************************************************
     public FList getInformation(Connection cnn, ResultSet rs) throws EException {
         final String LOCATION = "->getInformation()";
         FList bean = new FList();
         try {
             bean.setListId(rs.getInt(LISTREPORT_LIST_ID));
             bean.setListidEmp(rs.getInt(LISTREPORT_LIST_ID));
             bean.setListName(rs.getString(LISTREPORT_LIST_NAME));            
             bean.setDateCreate(bean.dateToString(rs.getDate(LISTREPORT_DATE_CREATE)));
             bean.setListCode(rs.getString(LISTREPORT_LIST_CODE));
             bean.setUserId(rs.getInt(LISTREPORT_USER_ID));
         } catch (SQLException sqle) {
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, sqle);
         } finally {
         }
         return bean;
     }    
     //**********************************************************************
       public FSearch getInformationEmp(Connection cnn, ResultSet rs) throws EException {
          final String LOCATION = "->getInformationEmp()";
          FSearch bean = new FSearch();
          try {
                 bean.setId(rs.getInt(NKT_ID));
                 bean.setMa(rs.getString(NKT_MA));
                 bean.setNkt(rs.getString(NKT_TEN));
                 bean.setCmnd(rs.getString(NKT_CMND));
                 if(rs.getDate(NKT_NGAYSINH)!=null && !rs.getDate(NKT_NGAYSINH).equals("")){
                     bean.setNgaySinh(bean.dateToString(rs.getDate(NKT_NGAYSINH)));
                 }
                 bean.setSex(rs.getInt(NKT_SEX));
                 bean.setSoNha(rs.getString(NKT_SONHA));
                 bean.setTinhId(rs.getInt(NKT_ID_TINH));
                 bean.setChuanDoan(rs.getString(NKT_CHUANDOAN));
                 bean.setDieuKienId(rs.getInt(NKT_ID_DIEUKIEN));
          } catch (SQLException sqle) {
              if (AppConfigs.APP_DEBUG)
                  throw new EException(LOCATION, sqle);
          } finally {
          }
          return bean;
      }
    //*************************************************************************
     public int  getAmountEmp(Connection cnn, int list_id) throws  EException
     {
      final String LOCATION = this.toString() + "~~>getAmountEmp()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;      
      int soluong=0;
      try
      {       
       
         prpstm = cnn.prepareStatement(SQL_SELECT_AMOUNT_EMP);       
         prpstm.setInt(PARAM_01,list_id);   
          rs = prpstm.executeQuery();        
         if((rs != null) && (rs.next())){
            soluong=rs.getInt(LIST_EMP_AMOUNT);
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
      return soluong;
     }
     //***********************************
}
