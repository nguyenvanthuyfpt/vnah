package com.form.servey;

import com.form.FBeans;
import com.form.FSeed;

public class FServey extends FSeed {
    private int serveyId;
    private String code;
    private String name;
    private String description;
    private String fromDate;
    private String toDate;
    private String createTime;
    private int orders;
    private String position;
    private int active;
    private int userId;
    private FBeans questions =new FBeans();
public void reset(){
        this.serveyId=0;
        this.setCode("");
        this.setName("");
        this.setDescription("");
        this.setFromDate("");
        this.setToDate("");
        this.setCreateTime("");
        this.setOrders(0);
        this.setPosition("");
        this.setActive(0);
        this.setUserId(0);
}


    public int getServeyId() {
        return serveyId;
    }

    public void setServeyId(int serveyId) {
        this.serveyId = serveyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

 

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public FBeans getQuestions() {
        return questions;
    }

    public void setQuestions(FBeans questions) {
        this.questions = questions;
    }
}
