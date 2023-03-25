package com.dao.disability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisProfile;
import com.form.disability.FHomeVisit;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DHomeVisit extends DSqlDisability {

    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        FHomeVisit bean = (FHomeVisit)seed;

        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_HOME_VISIT_BY_PARAM;
        try {
            List params = new ArrayList();
            if (bean.getNktId() > 0) {
                SQL += " AND a.id_nkt=?";
                params.add(bean.getNktId());
            }

            if (bean.getSupportId() > 0) {
                SQL += " AND a.support_id=?";
                params.add(bean.getSupportId());
            }

            prstm =
                    prepareStatement(cnn, SQL + ORDER_BY + TABLE_DR_HOME_VISIT_START_AT +
                                     DESC, params);
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
            while (rs != null && rs.next() && (i < AppConfigs.APP_ROWS_VIEW)) {
                i++;
                bean = new FHomeVisit();
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

    public FHomeVisit getHomeVisit(Connection cnn, int nktId,
                                   String createdAt) throws EException {
        final String LOCATION = this.toString() + "getProfileByNktId";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FHomeVisit homeVisit = new FHomeVisit();
        try {
            String sql = " SELECT * from dr_home_visit a\n" +
                " WHERE a.id_nkt = ? \n" +
                " AND date_trunc('day',start_at)::date = ?" +
                " ORDER BY id desc";
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, nktId);
            prstm.setDate(PARAM_02, homeVisit.stringToSqlDate(createdAt));
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                homeVisit = new FHomeVisit();
                homeVisit = getInformation(rs);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return homeVisit;
    }

    public FHomeVisit getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FHomeVisit bean = new FHomeVisit();
        try {
            bean.setId(rs.getInt("id"));
            bean.setSupportId(rs.getInt("support_id"));
            bean.setId(rs.getInt("id_nkt"));
            bean.setStartAt(rs.getString("start_at"));
            bean.setEndAt(rs.getString("end_at"));
            bean.setCreateBy(rs.getInt("create_by"));
            bean.setLatitude(rs.getDouble("latitude"));
            bean.setLonggitude(rs.getDouble("longitude"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {

        }
        return bean;
    }
}
