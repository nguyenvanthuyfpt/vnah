package com.form.disability.report;


import com.form.FBeans;
import com.form.FSeed;

public class FReportKpiInsurance extends FSeed {
    
    private int locationId;
    private String period;
    private String name;
    private int male;
    private int female;
    private String address;
    private String phoneNumber;
    private String methodIntervention;
    private int isDis;
    private int notDis;
    private String typeLevelDis;
    private int numberTimes;
    private int isInsurance;
    private int notInsurance;
    
    private FBeans store = new FBeans();

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getMale() {
        return male;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getFemale() {
        return female;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setMethodIntervention(String methodIntervention) {
        this.methodIntervention = methodIntervention;
    }

    public String getMethodIntervention() {
        return methodIntervention;
    }

    public void setIsDis(int isDis) {
        this.isDis = isDis;
    }

    public int getIsDis() {
        return isDis;
    }

    public void setNotDis(int notDis) {
        this.notDis = notDis;
    }

    public int getNotDis() {
        return notDis;
    }

    public void setTypeLevelDis(String typeLevelDis) {
        this.typeLevelDis = typeLevelDis;
    }

    public String getTypeLevelDis() {
        return typeLevelDis;
    }

    public void setNumberTimes(int numberTimes) {
        this.numberTimes = numberTimes;
    }

    public int getNumberTimes() {
        return numberTimes;
    }

    public void setIsInsurance(int isInsurance) {
        this.isInsurance = isInsurance;
    }

    public int getIsInsurance() {
        return isInsurance;
    }

    public void setNotInsurance(int notInsurance) {
        this.notInsurance = notInsurance;
    }

    public int getNotInsurance() {
        return notInsurance;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }
}
