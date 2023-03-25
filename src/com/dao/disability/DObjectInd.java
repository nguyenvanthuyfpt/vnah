package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FObjectInd;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DObjectInd extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        String sql = "insert into kpi_obj_ind(year, obj_id, ind_id) values (?,?,?)";
        try {
            List params = setParams(seed);
            result = execute(cnn, sql, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean exists(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FObjectInd bean = (FObjectInd)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int val = 0;
        String sql = "select count(1) from kpi_obj_ind where year=? and obj_id=? and ind_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getYear());
            pstmt.setInt(PARAM_02, bean.getObjId());
            pstmt.setInt(PARAM_03, bean.getIndId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                val = rs.getInt(1);
            }    
            result = val>0? true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FObjectInd bean = (FObjectInd)seed;
        return 0 < delete(cnn, "kpi_obj_ind", " year=" + bean.getYear() + " and obj_id=" + bean.getObjId());      
    }
    
    public FBeans getAll(Connection cnn, FSeed seed, String indIds) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FObjectInd bean = (FObjectInd)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        //String SQL = "";
        
        /* BAK
        String SQL = "SELECT DISTINCT CASE WHEN data.id IS NULL THEN 0 ELSE data.id END id, indicator.*, \n" + 
                    "CASE WHEN data.total IS NULL THEN 0 ELSE data.total END total  FROM\n" + 
                    "(SELECT map.*, ind.code, ind.name, ind.description, ind.type FROM kpi_obj_ind map, kpi_indicator ind \n" + 
                    "WHERE map.ind_id=ind.id AND map.year=? AND map.obj_id=?) indicator\n" + 
                    "LEFT OUTER JOIN kpi_v_data data on indicator.obj_id=data.obj_id \n" + 
                    "AND indicator.ind_id=data.ind_id AND indicator.year=data.year AND indicator.type=data.type";
        */
        String SQL = "SELECT 0 id, indicator.*, \n" + 
                  "CASE WHEN data.total IS NULL THEN 0 ELSE data.total END total  FROM\n" + 
                  "(SELECT map.*, ind.code, ind.name, ind.description, ind.type FROM kpi_obj_ind map, kpi_indicator ind \n" + 
                  "WHERE map.ind_id=ind.id AND map.year=? AND map.obj_id=?) indicator\n" + 
                  "LEFT OUTER JOIN kpi_v_data data on indicator.obj_id=data.obj_id \n" + 
                  "AND indicator.ind_id=data.ind_id AND indicator.year=data.year AND indicator.type=data.type";
        
        if (bean.getLocationId()==0){
            SQL = "SELECT 0 id, indicator.*, \n" + 
                    "CASE WHEN data.total IS NULL THEN 0 ELSE data.total END total  FROM\n" + 
                    "(SELECT map.*, ind.code, ind.name, ind.description, ind.type FROM kpi_obj_ind map, kpi_indicator ind \n" + 
                    "WHERE map.ind_id=ind.id AND map.year=? AND map.obj_id=?) indicator\n" + 
                    "LEFT OUTER JOIN kpi_v_data_tw data on indicator.obj_id=data.obj_id \n" + 
                    "AND indicator.ind_id=data.ind_id AND indicator.year=data.year AND indicator.type=data.type";
        }
            
        try {
            List params = new ArrayList();
            params.add(bean.getYear());
            params.add(bean.getObjId());
            if (bean.getLocationId()>0) {
                params.add(bean.getLocationId());
                SQL += " AND data.location_id=?";
            }
            
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            beans = new FBeans();    
            beans.setTotalRows(count(cnn, SQL, params));           
            int i = 0;  
            while (rs != null && rs.next()) {
                i ++ ;
                bean = new FObjectInd();
                bean = getInformation(rs, indIds);
                beans.add(bean);
            }
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FObjectInd getInformation(ResultSet rs, String indIds) throws EException {
        final String LOCATION = "->getInformation()";
        FObjectInd bean = new FObjectInd();
        try {
            int i = 1;
            bean.setId(rs.getInt(i++));
            bean.setYear(rs.getInt(i++));
            bean.setObjId(rs.getInt(i++));
            
            int indId = rs.getInt(i++);
            int ins = rs.getInt(i++);
            
            bean.setIndId(indId);
            if (!"".equals(indIds)) {
                  bean.setIns((indIds.indexOf(String.valueOf(indId))>-1)?1:0);
            } else {
                  bean.setIns(ins);  
            }
            
            bean.setCode(rs.getString(i++));
            bean.setName(rs.getString(i++));
            bean.setDescription(rs.getString(i++));
            bean.setType(rs.getInt(i++));
            bean.setTotal(rs.getInt(i++));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FObjectInd bean = (FObjectInd)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getYear());
            params.add(bean.getObjId());
            params.add(bean.getIndId());            
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
