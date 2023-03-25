package com.form.disability.jobs;

import com.form.FSeed;

import java.sql.Date;

public class FJobScheduler extends FSeed {
    private int id;
    private Date createDate;
    private String jobCode;
    private String jobName;
    private String jobExec;
    private String jobCron;
    private int locationId = 0;
    private int jobStatus;

    public FJobScheduler() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobExec(String jobExec) {
        this.jobExec = jobExec;
    }

    public String getJobExec() {
        return jobExec;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getJobStatus() {
        return jobStatus;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }
}
