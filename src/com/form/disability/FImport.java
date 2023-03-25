package com.form.disability;


import com.form.FSeed;

import org.apache.struts.upload.FormFile;


public class FImport extends FSeed {

    private String fileName;
    private String[] nameField;
    private String[] typeData;
    private int rowDataStart = 3;
    private int rowBegin = 3;
    private String tableName;
    private String dataSheet0[][];
    private String dataSheet1[][];
    private String dataSheet2[][];
    private String dataSheet3[][];

    private int typeImport = 1;
    private int noUpdate;
    private int noInsert;


    private FormFile upFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FormFile getUpFile() {
        return upFile;
    }

    public void setUpFile(FormFile upFile) {
        this.upFile = upFile;
    }

    // Sheet 0

    public String[][] getDataSheet0() {
        return dataSheet0;
    }

    public void setDataSheet0(String[][] data) {
        this.dataSheet0 = data;
    }

    public void setDataSheet0(String value, int row, int cols) {
        this.dataSheet0[row][cols] = value;
    }

    // Sheet 1

    public String[][] getDataSheet1() {
        return dataSheet1;
    }

    public void setDataSheet1(String[][] data) {
        this.dataSheet1 = data;
    }

    public void setDataSheet1(String value, int row, int cols) {
        this.dataSheet1[row][cols] = value;
    }

    // Sheet 2

    public String[][] getDataSheet2() {
        return dataSheet2;
    }

    public void setDataSheet2(String[][] data) {
        this.dataSheet2 = data;
    }

    public void setDataSheet2(String value, int row, int cols) {
        this.dataSheet2[row][cols] = value;
    }

    // Sheet 3

    public String[][] getDataSheet3() {
        return dataSheet3;
    }

    public void setDataSheet3(String[][] data) {
        this.dataSheet3 = data;
    }

    public void setDataSheet3(String value, int row, int cols) {
        this.dataSheet3[row][cols] = value;
    }

    public String[] getNameField() {
        return nameField;
    }

    public void setNameField(String[] nameField) {
        this.nameField = nameField;
    }


    public String[] getTypeData() {
        return typeData;
    }

    public void setTypeData(String[] typeData) {
        this.typeData = typeData;
    }

    public int getRowDataStart() {
        return rowDataStart;
    }

    public void setRowDataStart(int rowDataStart) {
        this.rowDataStart = rowDataStart;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setNoUpdate(int noUpdate) {
        this.noUpdate = noUpdate;
    }

    public int getNoUpdate() {
        return noUpdate;
    }

    public void setNoInsert(int noInsert) {
        this.noInsert = noInsert;
    }

    public int getNoInsert() {
        return noInsert;
    }

    public void setTypeImport(int typeImport) {
        this.typeImport = typeImport;
    }

    public int getTypeImport() {
        return typeImport;
    }

    public void setRowBegin(int rowBegin) {
        this.rowBegin = rowBegin;
    }

    public int getRowBegin() {
        return rowBegin;
    }
}
