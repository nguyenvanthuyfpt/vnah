package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisProfile;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDisProfile extends DSqlDisability {
    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_KPI_DIS_PROFILE, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FDisProfile bean = (FDisProfile)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_DIS_PROFILE, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDisProfile bean = (FDisProfile)seed;
        return 0 <
            delete(cnn, TABLE_KPI_DIS_PROFILE, KPI_DIS_PROFILE_ID + EQUAL + bean.getId());
    }
    
    public FBeans getProfileByNktId(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getProfileByNktId";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisProfile bean = new FDisProfile();
        try {
            String sql = SELECT + STAR + FROM + TABLE_KPI_DIS_PROFILE + WHERE + KPI_DIS_PROFILE_NKT_ID + " = ? ORDER BY create_on DESC";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, nktId);
            rs = prstm.executeQuery();
            beans = new FBeans();            
            int i = 0;           
            while ((rs != null) && (rs.next())) {
                i++;
                bean = new FDisProfile();
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
    
    public FDisProfile getProfileById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getProfileByNktId";        
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisProfile profile = new FDisProfile();
        try {
            String sql = SELECT + STAR + FROM + TABLE_KPI_DIS_PROFILE + WHERE + KPI_DIS_PROFILE_ID + " = ? ORDER BY create_on DESC";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, id);
            rs = prstm.executeQuery();            
            if ((rs != null) && (rs.next())) {                
                profile = new FDisProfile();
                profile = getInformation(rs);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return profile;
    }

    public FDisProfile getProfileLastByNktId(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getProfileByNktId";        
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisProfile profile = new FDisProfile();
        try {
            String sql = SELECT + STAR + FROM + TABLE_KPI_DIS_PROFILE + WHERE + KPI_DIS_PROFILE_NKT_ID + " = ? ORDER BY create_on DESC LIMIT 1";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, nktId);
            rs = prstm.executeQuery();            
            if ((rs != null) && (rs.next())) {                
                profile = new FDisProfile();
                profile = getInformation(rs);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return profile;
    }

    public FDisProfile getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDisProfile profile = new FDisProfile();
        try {
            profile.setId(rs.getInt(KPI_DIS_PROFILE_ID));
            profile.setNktId(rs.getInt(KPI_DIS_PROFILE_NKT_ID));
            profile.setStatus(rs.getInt(KPI_DIS_PROFILE_STATUS));
            profile.setResonId(rs.getInt(KPI_DIS_PROFILE_RESON_ID));            
            profile.setCreateOn(profile.dateToString(rs.getDate(KPI_DIS_PROFILE_CREATE_ON)));
            profile.setCreateBy(rs.getString(KPI_DIS_PROFILE_CREATE_BY));            
            profile.setUpdateOn(profile.dateToString(rs.getDate(KPI_DIS_PROFILE_UPDATE_ON)));
            profile.setUpdateBy(rs.getInt(KPI_DIS_PROFILE_UPDATE_BY));            
            profile.setAssessment(rs.getString(KPI_DIS_PROFILE_ASSESSMENT));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {

        }
        return profile;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDisProfile profile = (FDisProfile)seed;
        List params = new ArrayList();
        try {
            params.add(profile.getNktId());
            params.add(profile.getStatus());
            params.add(profile.getResonId());
            params.add(profile.stringToSqlDate(profile.getCreateOn()));
            params.add(profile.getCreateBy());
            params.add(profile.stringToSqlDate(profile.getUpdateOn()));
            params.add(profile.getUpdateBy());
            params.add(profile.getAssessment());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {

        }
        return params;
    }
}
