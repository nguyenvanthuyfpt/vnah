package com.util;

import com.bo.disability.jobs.BJobScheduler;

import com.form.FBeans;

import com.form.disability.jobs.FJobScheduler;

import com.scheduler.ExportJob;

import com.scheduler.ReportCommuneJob;
import com.scheduler.ReportIndicatorJob;

import com.scheduler.ReportObjectJob;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Tasks implements ServletContextListener {

    private Thread t = null;
    private ServletContext context;

    private static final String TRIGGER_NAME = "MyTriggerName";
    private static final String GROUP = "simple_Group";
    private Scheduler scheduler;
    
    private final static Logger logger  = Logger.getLogger(Tasks.class);
    
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(" QuartzSchedulerApp main thread: " + Thread.currentThread().getName());

        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            FJobScheduler job = new FJobScheduler();
            FBeans beans = new BJobScheduler().getSchedulerJobs(job);
            logger.info("Beans " + beans.size());
            for (int i=0;i<beans.size();i++) {
                job = (FJobScheduler)beans.get(i);
                Trigger trigger =  buildCronSchedulerTrigger(job);
                logger.info(job.getId()+"#"+job.getJobCode()+"#"+job.getJobExec()+"#"+job.getLocationId());
                scheduleJob(trigger, job.getId()+"#"+job.getJobCode()+"#"+job.getJobExec()+"#"+job.getLocationId(), job.getJobName(), job.getJobCode());
            }            
        } catch (SchedulerException e) {
            logger.error(e.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        } 
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }

    private void scheduleJob(Trigger trigger, String execJob, String jobName, String jobCode) throws Exception {
        ExportJob job = new ExportJob();        
        if ("_REPORT_EXPORT".equals(jobCode)){
            JobKey jobKey = new JobKey(jobName, GROUP);
            JobDetail someJobDetail = JobBuilder.newJob(ExportJob.class)
                .withDescription(execJob)
                .withIdentity(jobKey).build();
            scheduler.scheduleJob(someJobDetail, trigger);
        } else if ("_REPORT_INDICATOR".equals(jobCode)) {
            JobKey jobKey = new JobKey(jobName, GROUP);
            JobDetail someJobDetail = JobBuilder.newJob(ReportIndicatorJob.class)
                .withDescription(execJob)
                .withIdentity(jobKey).build();
            scheduler.scheduleJob(someJobDetail, trigger);
        } else if ("_REPORT_OBJECT".equals(jobCode)) {
            JobKey jobKey = new JobKey(jobName, GROUP);
            JobDetail someJobDetail = JobBuilder.newJob(ReportObjectJob.class)
                .withDescription(execJob)
                .withIdentity(jobKey).build();
            scheduler.scheduleJob(someJobDetail, trigger);
        } else if ("_REPORT_COMMUNE".equals(jobCode)) {
            JobKey jobKey = new JobKey(jobName, GROUP);
            JobDetail someJobDetail = JobBuilder.newJob(ReportCommuneJob.class)
                .withDescription(execJob)
                .withIdentity(jobKey).build();
            scheduler.scheduleJob(someJobDetail, trigger);
        }
    }

    private Trigger buildCronSchedulerTrigger(FJobScheduler job) {
        String CRON_EXPRESSION = job.getJobCron(); 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getJobName(), GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION)).build();
        return trigger;
    }
}
