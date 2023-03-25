package com.scheduler;

import com.exp.EException;

import com.util.DaoUtil;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import org.joda.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReportIndicatorJob implements Job {
    
    private final static Logger logger  = Logger.getLogger(ReportIndicatorJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        MyTask mytask = new MyTask();
        String execJob = context.getJobDetail().getDescription();
        try {
            mytask.perform(execJob);
        } catch (EException ex) {
            logger.error(ex.toString());
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
    }
}
