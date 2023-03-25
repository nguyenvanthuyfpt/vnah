package com.dao.disability;


import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FEventObjInd;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DEventObjInd extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_EVENT_OBJ_IND, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public FEventObjInd getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FEventObjInd bean = new FEventObjInd();
        try {
            int i = 1;
            bean.setYear(rs.getInt(i++));
            bean.setObjId(rs.getInt(i++));
            bean.setIndId(rs.getInt(i++));
            bean.setEventId(rs.getInt(i++));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FEventObjInd bean = (FEventObjInd)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getYear());
            params.add(bean.getObjId());
            params.add(bean.getIndId());
            params.add(bean.getEventId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
