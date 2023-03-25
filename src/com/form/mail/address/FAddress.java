package com.form.mail.address;

import com.form.FSeed;


public class FAddress extends FSeed {
        private int addRessId; 
        private String fullName;
        private String email;
        private String nickYahoo;
        private String nickIcq;
        private String nickSkype;
        private String addRess;
        private String phone;
        private String fax;
        private int mobile;
        private String description;
    private String creatorMail;
    

    public void reset(){
        this.setFullName("");
        this.setEmail("");
        this.setNickYahoo("");
        this.setNickIcq("");
        this.setNickSkype("");
        this.setAddRess("");
        this.setPhone("");
        this.setMobile(0);
        this.setDescription("");
    }

    public int getAddRessId() {
        return addRessId;
    }

    public void setAddRessId(int addRessId) {
        this.addRessId = addRessId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickYahoo() {
        return nickYahoo;
    }

    public void setNickYahoo(String nickYahoo) {
        this.nickYahoo = nickYahoo;
    }

    public String getNickIcq() {
        return nickIcq;
    }

    public void setNickIcq(String nickIcq) {
        this.nickIcq = nickIcq;
    }

    public String getNickSkype() {
        return nickSkype;
    }

    public void setNickSkype(String nickSkype) {
        this.nickSkype = nickSkype;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorMail() {
        return creatorMail;
    }

    public void setCreatorMail(String creatorMail) {
        this.creatorMail = creatorMail;
    }
}
