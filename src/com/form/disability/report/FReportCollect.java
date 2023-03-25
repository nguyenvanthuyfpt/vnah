package com.form.disability.report;

import com.form.FBeans;
import com.form.FSeed;

public class FReportCollect extends FSeed {
    private int id;
    private int kyBC = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
    private int namBC = getYear(getCurrentSqlDate());
    private int id_tinh;
    private int level;
    private int quanlyca = 0;
    private String func;
    private String nameArea;
    private FBeans store = new FBeans();

    private String paramvalue_1;
    private String paramvalue_2;
    private String paramvalue_3;
    private String paramvalue_4;
    private String paramvalue_5;
    private String paramvalue_6;
    private String paramvalue_7;
    private String paramvalue_8;
    private String paramvalue_9;
    private String paramvalue_10;
    private String paramvalue_11;
    private String paramvalue_12;
    private String paramvalue_13;
    private String paramvalue_14;
    private String paramvalue_15;
    private String paramvalue_16;
    private String paramvalue_17;
    private String paramvalue_18;
    private String paramvalue_19;
    private String paramvalue_20;
    private String paramvalue_21;
    private String paramvalue_22;
    private String paramvalue_23;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setKyBC(int kyBC) {
        this.kyBC = kyBC;
    }

    public int getKyBC() {
        return kyBC;
    }

    public void setNamBC(int namBC) {
        this.namBC = namBC;
    }

    public int getNamBC() {
        return namBC;
    }

    public void setId_tinh(int id_tinh) {
        this.id_tinh = id_tinh;
    }

    public int getId_tinh() {
        return id_tinh;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public String getNameArea() {
        return nameArea;
    }


    public void setQuanlyca(int quanlyca) {
        this.quanlyca = quanlyca;
    }

    public int getQuanlyca() {
        return quanlyca;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setParamvalue_1(String paramvalue_1) {
        this.paramvalue_1 = paramvalue_1;
    }

    public String getParamvalue_1() {
        return paramvalue_1;
    }

    public void setParamvalue_2(String paramvalue_2) {
        this.paramvalue_2 = paramvalue_2;
    }

    public String getParamvalue_2() {
        return paramvalue_2;
    }

    public void setParamvalue_3(String paramvalue_3) {
        this.paramvalue_3 = paramvalue_3;
    }

    public String getParamvalue_3() {
        return paramvalue_3;
    }

    public void setParamvalue_4(String paramvalue_4) {
        this.paramvalue_4 = paramvalue_4;
    }

    public String getParamvalue_4() {
        return paramvalue_4;
    }

    public void setParamvalue_5(String paramvalue_5) {
        this.paramvalue_5 = paramvalue_5;
    }

    public String getParamvalue_5() {
        return paramvalue_5;
    }

    public void setParamvalue_6(String paramvalue_6) {
        this.paramvalue_6 = paramvalue_6;
    }

    public String getParamvalue_6() {
        return paramvalue_6;
    }

    public void setParamvalue_7(String paramvalue_7) {
        this.paramvalue_7 = paramvalue_7;
    }

    public String getParamvalue_7() {
        return paramvalue_7;
    }

    public void setParamvalue_8(String paramvalue_8) {
        this.paramvalue_8 = paramvalue_8;
    }

    public String getParamvalue_8() {
        return paramvalue_8;
    }

    public void setParamvalue_9(String paramvalue_9) {
        this.paramvalue_9 = paramvalue_9;
    }

    public String getParamvalue_9() {
        return paramvalue_9;
    }

    public void setParamvalue_10(String paramvalue_10) {
        this.paramvalue_10 = paramvalue_10;
    }

    public String getParamvalue_10() {
        return paramvalue_10;
    }

    public void setParamvalue_11(String paramvalue_11) {
        this.paramvalue_11 = paramvalue_11;
    }

    public String getParamvalue_11() {
        return paramvalue_11;
    }

    public void setParamvalue_12(String paramvalue_12) {
        this.paramvalue_12 = paramvalue_12;
    }

    public String getParamvalue_12() {
        return paramvalue_12;
    }

    public void setParamvalue_13(String paramvalue_13) {
        this.paramvalue_13 = paramvalue_13;
    }

    public String getParamvalue_13() {
        return paramvalue_13;
    }

    public void setParamvalue_14(String paramvalue_14) {
        this.paramvalue_14 = paramvalue_14;
    }

    public String getParamvalue_14() {
        return paramvalue_14;
    }

    public void setParamvalue_15(String paramvalue_15) {
        this.paramvalue_15 = paramvalue_15;
    }

    public String getParamvalue_15() {
        return paramvalue_15;
    }

    public void setParamvalue_16(String paramvalue_16) {
        this.paramvalue_16 = paramvalue_16;
    }

    public String getParamvalue_16() {
        return paramvalue_16;
    }

    public void setParamvalue_17(String paramvalue_17) {
        this.paramvalue_17 = paramvalue_17;
    }

    public String getParamvalue_17() {
        return paramvalue_17;
    }

    public void setParamvalue_18(String paramvalue_18) {
        this.paramvalue_18 = paramvalue_18;
    }

    public String getParamvalue_18() {
        return paramvalue_18;
    }

    public void setParamvalue_19(String paramvalue_19) {
        this.paramvalue_19 = paramvalue_19;
    }

    public String getParamvalue_19() {
        return paramvalue_19;
    }

    public void setParamvalue_20(String paramvalue_20) {
        this.paramvalue_20 = paramvalue_20;
    }

    public String getParamvalue_20() {
        return paramvalue_20;
    }

    public void setParamvalue_21(String paramvalue_21) {
        this.paramvalue_21 = paramvalue_21;
    }

    public String getParamvalue_21() {
        return paramvalue_21;
    }

    public void setParamvalue_22(String paramvalue_22) {
        this.paramvalue_22 = paramvalue_22;
    }

    public String getParamvalue_22() {
        return paramvalue_22;
    }

    public void setParamvalue_23(String paramvalue_23) {
        this.paramvalue_23 = paramvalue_23;
    }

    public String getParamvalue_23() {
        return paramvalue_23;
    }
}
