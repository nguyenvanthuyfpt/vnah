package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisReport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDisReport extends DSqlDisability {
    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_KPI_DIS_REPORT, params) > 0;
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
            FDisReport bean = (FDisReport)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_DIS_REPORT, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDisReport bean = (FDisReport)seed;
        return 0 <
            delete(cnn, TABLE_KPI_DIS_REPORT, KPI_DIS_REPORT_ID + EQUAL + bean.getId());
    }
    
    public FBeans getReportByNktId(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getReportByNktId";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisReport bean = new FDisReport();
        try {
            String sql = SELECT + STAR + FROM + TABLE_KPI_DIS_REPORT + WHERE + KPI_DIS_REPORT_NKT_ID + " = ? ORDER BY create_date DESC";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, nktId);
            rs = prstm.executeQuery();
            beans = new FBeans();            
            int i = 0;           
            while ((rs != null) && (rs.next())) {
                i++;
                bean = new FDisReport();
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
    
    public FDisReport getReportById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getReportById";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisReport bean = new FDisReport();
        try {
            String sql = SELECT + STAR + FROM + TABLE_KPI_DIS_REPORT + WHERE + KPI_DIS_REPORT_ID + " = ? ORDER BY create_date DESC";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, id);
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


    public FDisReport getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDisReport report = new FDisReport();
        try {
            report.setId(rs.getInt(KPI_DIS_REPORT_ID));
            report.setNktId(rs.getInt(KPI_DIS_REPORT_NKT_ID));
            
            report.setCreateDate(report.dateToString(rs.getDate(KPI_DIS_REPORT_CREATE_DATE)));
            report.setCreateBy(rs.getString(KPI_DIS_REPORT_CREATE_BY));
            
            report.setKtbtThuongXuyen(rs.getInt(KPI_DIS_REPORT_KTBT_THUONGXUYEN));
            report.setKtbtTapDung(rs.getInt(KPI_DIS_REPORT_KTBT_TAPDUNG));
            
            report.setDctgPhuHop(rs.getInt(KPI_DIS_REPORT_DCTG_PHUHOP));
            report.setDctgThuongXuyen(rs.getInt(KPI_DIS_REPORT_DCTG_THUONGXUYEN));
            report.setDctgBaoQuan(rs.getInt(KPI_DIS_REPORT_DCTG_BAOQUAN));
            
            report.setHdNcs(rs.getInt(KPI_DIS_REPORT_HD_NCS));
            report.setHuongCanThiep(rs.getString(KPI_DIS_REPORT_HUONGCANTHIEP));
            report.setHtroDuKien(report.dateToString(rs.getDate(KPI_DIS_REPORT_HTRO_DKIEN)));
            report.setObjId(rs.getInt(KPI_DIS_REPORT_DOITUONG));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {

        }
        return report;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDisReport report = (FDisReport)seed;
        List params = new ArrayList();
        try {
            params.add(report.getNktId());
            params.add(report.stringToSqlDate(report.getCreateDate()));
            params.add(report.getCreateBy());
            
            params.add(report.getKtbtThuongXuyen());
            params.add(report.getKtbtTapDung());
            
            params.add(report.getDctgPhuHop());
            params.add(report.getDctgThuongXuyen());
            params.add(report.getDctgBaoQuan());
            
            params.add(report.getHdNcs());
            params.add(report.getCanThiep());
            params.add(report.getHuongCanThiep());
            params.add(report.stringToSqlDate(report.getHtroDuKien()));
            params.add(report.getObjId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {

        }
        return params;
    }
}
