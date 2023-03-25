package com.dao.disability;


import com.dao.connection.DBConnector;

import com.exp.EException;

import java.sql.Connection;

import org.apache.log4j.Logger;


public class DaoImpl implements Dao {
    private Connection conn = null;
    private static final Logger log = Logger.getLogger(DaoImpl.class);

    public DaoImpl() throws EException {
        conn = DBConnector.getConnection();
        //conn = ConnectionLoggingProxy.wrap(conn);
    }

    @Override
    public void close(){
        try {
            DBConnector.closeConnection(conn);
        } catch (EException e) {
        }
    }
}
