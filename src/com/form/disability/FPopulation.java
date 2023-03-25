package com.form.disability;

import com.form.FSeed;


public class FPopulation extends FSeed {

    private int id;
    private int id_tinh;
    private String dateEdit;
    private String tinhName;
    private int reportSession;//ky bao cao
    private int maleLessThan18;
    private int maleMoreThan18;
    private int famaleLessThan18;
    private int famaleMoreThan18;
    private String infoActive;
    private int[] infoActives;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;
    private int number7;
    private int number8;
    private int number9;
    private int number10;
    private int number11;
    private int number12;
    private int number13;
    private int number14;
    private int number15;
    
    private int period = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;    
    private int yearOfPeriod = getYear(getCurrentSqlDate());
    private int[] paramValue = new int[7];
    private int level_province;
    
    public void reset(){
        this.id=0;
        this.dateEdit=new FSeed().dateToString(new FSeed().getCurrentSqlDate());
        this.maleLessThan18=0;
        this.maleMoreThan18=0;
        this.famaleLessThan18=0;
        this.famaleMoreThan18=0;
        this.infoActive="";
        this.number1=0;
        this.number2=0;
        this.number3=0;
        this.number4=0;
        this.number5=0;
        this.number6=0;
        this.number7=0;
        this.number8=0;
        this.number9=0;
        this.number10=0;
        this.number11=0;
        this.number12=0;
        this.number13=0;
        this.number14=0;
        this.number15=0;
        this.setInfoActives(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tinh() {
        return id_tinh;
    }

    public void setId_tinh(int id_tinh) {
        this.id_tinh = id_tinh;
    }

    public String getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(String dateEdit) {
        this.dateEdit = dateEdit;
    }

    public int getMaleLessThan18() {
        return maleLessThan18;
    }

    public void setMaleLessThan18(int maleLessThan18) {
        this.maleLessThan18 = maleLessThan18;
    }

    public int getMaleMoreThan18() {
        return maleMoreThan18;
    }

    public void setMaleMoreThan18(int maleMoreThan18) {
        this.maleMoreThan18 = maleMoreThan18;
    }

    public int getFamaleLessThan18() {
        return famaleLessThan18;
    }

    public void setFamaleLessThan18(int famaleLessThan18) {
        this.famaleLessThan18 = famaleLessThan18;
    }

    public int getFamaleMoreThan18() {
        return famaleMoreThan18;
    }

    public void setFamaleMoreThan18(int famaleMoreThan18) {
        this.famaleMoreThan18 = famaleMoreThan18;
    }

    public String getInfoActive() {
        return infoActive;
    }

    public void setInfoActive(String infoActive) {
        this.infoActive = infoActive;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }

    public int getNumber6() {
        return number6;
    }

    public void setNumber6(int number6) {
        this.number6 = number6;
    }

    public int getNumber7() {
        return number7;
    }

    public void setNumber7(int number7) {
        this.number7 = number7;
    }

    public int[] getInfoActives() {
        return infoActives;
    }

   
    public void setInfoActives(int[] infoActives) {
        if(infoActives!=null && infoActives.length>0){
            String infoActive="#";
            for(int i=0;i<infoActives.length;i++){
                infoActive+=infoActives[i] + "#";
            }
            setInfoActive(infoActive);
        }
        this.infoActives =infoActives;
    }

    public int getReportSession() {
        return reportSession;
    }

    public void setReportSession(int reportSession) {
        this.reportSession = reportSession;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getYearOfPeriod() {
        return yearOfPeriod;
    }

    public void setYearOfPeriod(int yearOfPeriod) {
        this.yearOfPeriod = yearOfPeriod;
    }

    public int[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(int[] paramValue) {
        this.paramValue = paramValue;
    }
    public void setParamValue(int value,int index) {
        this.paramValue[index]=value;
    }

    public int getLevel_province() {
        return level_province;
    }

    public void setLevel_province(int level_province) {
        this.level_province = level_province;
    }

    public int getNumber8() {
        return number8;
    }

    public void setNumber8(int number8) {
        this.number8 = number8;
    }

    public int getNumber9() {
        return number9;
    }

    public void setNumber9(int number9) {
        this.number9 = number9;
    }

    public int getNumber10() {
        return number10;
    }

    public void setNumber10(int number10) {
        this.number10 = number10;
    }

    public int getNumber11() {
        return number11;
    }

    public void setNumber11(int number11) {
        this.number11 = number11;
    }

    public int getNumber12() {
        return number12;
    }

    public void setNumber12(int number12) {
        this.number12 = number12;
    }

    public int getNumber13() {
        return number13;
    }

    public void setNumber13(int number13) {
        this.number13 = number13;
    }

    public int getNumber14() {
        return number14;
    }

    public void setNumber14(int number14) {
        this.number14 = number14;
    }

    public int getNumber15() {
        return number15;
    }

    public void setNumber15(int number15) {
        this.number15 = number15;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }
}
