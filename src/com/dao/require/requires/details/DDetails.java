package com.dao.require.requires.details;


import com.dao.DSql;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.require.requires.details.FDetail;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


public class DDetails  extends DSql
{
    public FDetail getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FDetail bean = new FDetail();
      bean = (FDetail)seed;
      try
      {
        String SQL ="" ;//SQL_SELECT_REQUIREDETAILS_BY_ID
        prstm = cnn.prepareStatement(SQL);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
           // bean = getInformation(cnn,rs);
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
      return bean;        
    }
    public boolean delete(Connection cnn, FDetail bean) throws EException
    {
      final String LOCATION = this.toString() + DELETE;     
        //delete(cnn, TABLE_REQUIREDETAILS, REQUIREDETAILS_REQUIRE_ID + EQUAL + bean.getRequire_id())
      return 0 < 1;
    }    
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
      FDetail bean = (FDetail)seed;
      try
      {
          delete(cnn,bean);
          if(bean.getAsset_ids()!=null && bean.getAsset_ids().length>0){
              List params = new ArrayList();  
              String SQL ="";//SQL_INSERT_REQUIREDETAILS_REQUIREDETAIL
              prstm = cnn.prepareStatement(SQL);
              for(int i=0;i<bean.getAsset_ids().length;i++){
                 if(bean.getAsset_ids()[i]>0 || (!bean.getNames()[i].trim().equals("") || !bean.getDescriptions()[i].trim().equals(""))){
                      params = setParams(i,seed);
                      for(int j=0;j<params.size();j++){
                        if(params.get(j)==null){
                          prstm.setNull(PARAM_01+j,Types.DATE);
                        }else{
                          prstm.setObject(PARAM_01+j,params.get(j));
                        }
                      }
                      prstm.addBatch();
                      result = true;
                 }
              }
              if(result){
                  //print(SQL_INSERT_REQUIREDETAILS_REQUIREDETAIL);
                  result = prstm.executeBatch().length>0;
              }else{
                  result = true;
              }
          }else{
              result = true;
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
    
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
       PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        String SQL ="";//SQL_SELECT_REQUIREDETAILS_BY_REQUIRE
        prstm = cnn.prepareStatement(SQL);
       
        if(params != null){
          for(int i=0;i<params.size();i++){
            if(params.get(i)==null){
              prstm.setNull(PARAM_01+i,Types.DATE);
            }else{
              prstm.setObject(PARAM_01+i,params.get(i));
            }
          }
        }
        rs = prstm.executeQuery();
        beans = new FBeans();
        while((rs != null) && (rs.next()))
        {
            //beans.add(getInformation(cnn,rs));
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
//    public FDetail getInformation(Connection cnn,ResultSet rs) throws EException
//    {
//        final String LOCATION = "->getInformation()";
//        FDetail detail = new FDetail();
//         try {
//             detail.setId(0);
//
////           FRequire require = new FRequire();
////           require.setId(detail.getRequire_id());
////           DRequires requires = new DRequires();
////           require = requires.getRecordByID(cnn,require);
////           if(require.getTypeRequire_id()==0){
////                FTypeAsset typeasset = new FTypeAsset();
////                typeasset.setId(detail.getAsset_id());
////                DTypeAssets typeassets = new DTypeAssets();
////                typeasset = typeassets.getRecordByID(cnn,typeasset);
////                detail.setCode(typeasset.getCode());
////                detail.setAssetName(typeasset.getName());
////           }else{
////               FAsset asset = new FAsset();
////               asset.setId(detail.getAsset_id());
////               DAssets assets = new DAssets();
////               asset = assets.getRecordByID(cnn,asset);
////               detail.setCode(asset.getCode());
////               detail.setAssetName(asset.getName());
////               detail.setStatusName(rs.getString(PROMOTE_STATUS));
////               detail.setPromote(rs.getString(PROMOTE_PROMOTE));
////           }
////             
//         }
//         catch (SQLException sqle) {            
//             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
//         }
//         finally {
//         }
//         return detail;
//    }
    public List setParams(int i,FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FDetail bean = (FDetail)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getRequire_id());
             params.add(bean.getAsset_ids()[i]);             
             params.add(bean.getNames()[i].replaceAll("-","")); 
             if (bean.getQuantitys()[i]!=null && !bean.getQuantitys()[i].equals("")){
                params.add(Integer.parseInt(bean.getQuantitys()[i].replaceAll(",","")));
             }else{
                params.add(0); 
             }
             if (bean.getPrices()[i]!=null && !bean.getPrices()[i].equals("")){             
                params.add(Long.parseLong(bean.getPrices()[i].replaceAll(",","")));
             }else{
                 params.add(0);  
             }
             params.add(bean.getDescriptions()[i]);
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
