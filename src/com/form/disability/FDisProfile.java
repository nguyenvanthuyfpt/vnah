package com.form.disability;

import com.form.FSeed;

public class FDisProfile extends FSeed {

    private int id;
    private int nktId;
    private int status = 0; // 0-Mo ca, 1-Dong ca
    private int resonId;
    private String createOn;
    private String createBy;
    private String updateOn;
    private int updateBy;
    private String assessment;

    public FDisProfile() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setResonId(int resonId) {
        this.resonId = resonId;
    }

    public int getResonId() {
        return resonId;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setUpdateOn(String updateOn) {
        this.updateOn = updateOn;
    }

    public String getUpdateOn() {
        return updateOn;
    }


    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getUpdateBy() {
        return updateBy;
    }
}
