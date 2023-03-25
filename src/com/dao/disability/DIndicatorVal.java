package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FIndicatorVal;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DIndicatorVal extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            FIndicatorVal bean = (FIndicatorVal)seed;
            List params = setParams(seed);            
            if (!isExist(cnn, bean)) {
                result = execute(cnn, SQL_INSERT_INDICATOR_VALUE, params) > 0;        
            }  
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, 
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FIndicatorVal bean = (FIndicatorVal)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_INDICATOR_VALUE, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FIndicatorVal bean = (FIndicatorVal)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String SQL = SQL_CHECK_EXISTS_INDICATOR;
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getYear());
            pstmt.setInt(PARAM_04, bean.getType());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
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
        boolean result = false;
        try {
            FIndicatorVal bean = (FIndicatorVal)seed;
            result = delete(cnn, TABLE_KPI_INDICATOR_VAL, " id=" + bean.getId()) > 0;
        } catch (Exception ex) {
            if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, ex);      
        } 
        return result;
    }

    public FIndicatorVal getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FIndicatorVal bean = new FIndicatorVal();
        try {
            int i = 1;
            bean.setId(rs.getInt(i++));            
            bean.setCreateDate(bean.dateToString(rs.getDate(i++)));
            bean.setModifyDate(bean.dateToString(rs.getDate(i++)));
            bean.setIndId(rs.getInt(i++));
            bean.setLocationId(rs.getInt(i++));            
            bean.setQuarter(rs.getInt(i++));
            bean.setYear(rs.getInt(i++));            
            bean.setVal(rs.getInt(i++));
            bean.setType(rs.getInt(i++));            
            bean.setLocationName(rs.getString(i++));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
    
    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FIndicatorVal bean    = (FIndicatorVal)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_INDICATOR_VALUE;
        try {
            List params = new ArrayList();
            
            SQL += " AND a.ind_id=?";
            params.add(bean.getIndId());                
            
            SQL += " AND ((?=0) OR (a.location_id=?))";
            params.add(bean.getLocationId());
            params.add(bean.getLocationId());
            
            if (bean.getType()>-1) {
              SQL += " AND a.type=?";
              params.add(bean.getType());
            }
            
            if (bean.getYear()>0) {
                SQL += " AND a.year=?";
                params.add(bean.getYear());
            }
            
            prstm = prepareStatement(cnn, SQL + ORDER_BY + " b.tinh_id, a.create_date" + DESC, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }            
            int i = 0;  
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FIndicatorVal();
                bean = getInformation(rs);
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
    
    public FIndicatorVal getDetailByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getDetailByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FIndicatorVal bean = new FIndicatorVal();
        bean = (FIndicatorVal)seed;
        String SQL = "SELECT a.*, b.name FROM kpi_ind_val a, dr_area b WHERE a.location_id=b.tinh_id AND a.id=?";
        try {
            List params = new ArrayList();
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformation(rs);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FIndicatorVal bean = (FIndicatorVal)seed;
        List params = new ArrayList();
        try {
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getIndId());
            params.add(bean.getLocationId());
            params.add(bean.getQuarter());
            params.add(bean.getYear());
            params.add(bean.getVal());
            params.add(bean.getType());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
