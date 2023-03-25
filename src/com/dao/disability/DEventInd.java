package com.dao.disability;


import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FEventInd;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DEventInd extends DSqlDisability {

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_EVENT_IND, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public FEventInd getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FEventInd bean = new FEventInd();
        try {
            int i = 1;
            bean.setEventId(rs.getInt(i++));;
            bean.setIndId(rs.getInt(i++));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FEventInd bean = (FEventInd)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getEventId());
            params.add(bean.getIndId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
