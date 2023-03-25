package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FUnit;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DUnit extends DSqlDisability {

    public FBeans getData(Connection cnn, int categoryId, int tinhId) throws EException {
        final String LOCATION = this.toString() + "getData()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = prepareStatement(cnn, SQL_COUNT_CATEGORY_UNIT, null);
            rs = prstm.executeQuery();
            FUnit bean = null;
            while (rs != null && rs.next()) {
                bean = new FUnit();
                bean.setTotal(rs.getInt(PARAM_01));
                bean.setId_type(rs.getInt(PARAM_02));
                bean.setName_type(rs.getString(PARAM_03));                
                bean.setSubBean(getSubData(cnn, categoryId != 0 ? categoryId : rs.getInt(PARAM_02), tinhId));
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

    public FBeans getSubData(Connection cnn, int categoryId, int tinhId) throws EException {
        final String LOCATION = this.toString() + "getData()";
        FBeans beans    = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            StringBuffer sb = new StringBuffer("SELECT * FROM DR_UNIT");
                                     sb.append(" WHERE CATEGORY_ID ='"+ categoryId + "'");
                            if(tinhId!=0){
                                     sb.append(" AND TINH_ID='"+ tinhId + "'");
                            }
        
            prstm = cnn.prepareStatement(sb.toString());
            rs = prstm.executeQuery();
            FUnit bean = null;
            while (rs != null && rs.next()) {
                bean = new FUnit();
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


    public FBeans getAllCategory(Connection cnn) throws EException {
        final String LOCATION = this.toString() + "getAllCategory()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FUnit bean = null;
        try {
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_CATEGORY_UNIT, null);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                bean = new FUnit();
                bean.setId_type(rs.getInt(PARAM_01));
                bean.setName_type(rs.getString(PARAM_02));
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

    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FUnit bean      = (FUnit)seed;
        FTinh beanTinh  = new FTinh();
        
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_ALL_DISABILITY_UNIT;
        try {
            List params = new ArrayList();
            if (bean.getId_type() > 0) {
                params.add(bean.getId_type());
                SQL += AND + DR_UNIT_CATEGORY_ID + EQUAL + QUESTION;
            }
            if (bean.getTinhId() > 0) {
                params.add(bean.getTinhId());
                SQL += AND + DR_UNIT_TINH_ID + EQUAL + QUESTION;
            }

            prstm = prepareStatement(cnn, SQL + ORDER_BY + DR_UNIT_ID, params);
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
                bean = new FUnit();
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

    public FUnit getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs    = null;
        FUnit bean      = new FUnit();
        FTinh beanTinh  = new FTinh();
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_BY_ID_FROM_DISABILITY_UNIT);
            prstm.setInt(PARAM_01, id);
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FUnit();
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

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = 
                    prepareStatement(cnn, SQL_INSERT_INTO_TABLE_DR_UNIT, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException, 
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FUnit bean = (FUnit)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = 
                    prepareStatement(cnn, SQL_UPDATE_INTO_TABLE_DR_UNIT, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }


    public boolean delete(Connection cnn, int id) throws EException {
        return delete(cnn, TABLE_DR_UNIT, DR_UNIT_ID + EQUAL + id) > 0;
    }


    public FUnit getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FUnit bean = new FUnit();
        try {
            bean.setName_type(rs.getString(PARAM_01));
            bean.setId(rs.getInt(DR_UNIT_ID));
            bean.setName(rs.getString(DR_UNIT_NAME));
            bean.setAddress(rs.getString(DR_UNIT_ADDRESS));
            bean.setPhone(rs.getString(DR_UNIT_PHONE));
            bean.setFax(rs.getString(DR_UNIT_FAX));
            bean.setEmail(rs.getString(DR_UNIT_EMAIL));
            bean.setId_type(rs.getInt(DR_UNIT_CATEGORY_ID));
            bean.setGioiThieu(rs.getString(DR_UNIT_GIOI_THIEU));
            bean.setNguoilienhe(rs.getString(DR_UNIT_NGUOI_LIEN_HE));
            bean.setTinhId(rs.getInt(DR_UNIT_TINH_ID));

        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FUnit bean = (FUnit)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getName());
            params.add(bean.getAddress());
            params.add(bean.getPhone());
            params.add(bean.getFax());
            params.add(bean.getEmail());
            params.add(bean.getId_type());
            params.add(bean.getGioiThieu());
            params.add(bean.getNguoilienhe());
            params.add(bean.getTinhId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
