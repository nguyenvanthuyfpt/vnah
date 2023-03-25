package com.bo.disability.jobs;

import com.dao.connection.DBConnector;
import com.dao.disability.jobs.DJobScheduler;
import com.exp.EException;
import com.form.FBeans;
import com.form.FSeed;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.SQLException;

public class BJobScheduler {
    
    DJobScheduler dao = new DJobScheduler();
    
    public FBeans getSchedulerJobs(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->getSchedulerJobs()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.getSchedulerJobs(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
}
