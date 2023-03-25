package com.scheduler;

import com.bo.disability.jobs.BJobLog;
import com.dao.connection.DBConnector;
import com.exp.EException;
import com.form.disability.jobs.FJobLog;
import com.util.DaoUtil;
import com.util.Utilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;

public class MyTask {
    /**
     * It should handle any runtime exception if the application should continue
     * when encounters a exception, otherwise the application will stop
     */
    final static Logger logger  = Logger.getLogger(MyTask.class);
    
    public void perform(String execJob) throws EException, SQLException {
        FJobLog fLog = new FJobLog();
        int logId = 0;
        boolean retval = false;
        try {
            //logger.info("BEGIN:perform " + execJob);
            String[] paramJob = execJob.split("#");
            int jobId = Integer.parseInt(paramJob[0]);
            String jobCode = paramJob[1];
            String jobStore = paramJob[2];
            int locationId = Integer.parseInt(paramJob[3]);
            
            fLog.setStartExec(Utilities.getCurrentTimestamp());
            fLog.setJobId(jobId);
            fLog.setLocationId(locationId);
            logId = new BJobLog().insert(fLog);
            Connection cnn = DBConnector.getConnection();            
            DaoUtil.execSchedulerJobs(cnn, jobStore);
            
            fLog.setId(logId);
            fLog.setMsgExec("");
            
            fLog.setEndExec(Utilities.getCurrentTimestamp());
            retval = new BJobLog().update(fLog);
            //logger.info("END:perform");
        } catch (EException ex) {
            fLog.setId(logId);
            fLog.setEndExec(Utilities.getCurrentTimestamp());
            fLog.setMsgExec(ex.toString());
            retval = new BJobLog().update(fLog);
            
            logger.error(ex.toString());
        }
    }
}
