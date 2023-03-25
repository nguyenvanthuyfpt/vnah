package com.form.importdata;


import com.form.FBeans;
import com.form.FSeed;

public class FImportData extends FSeed
{
    private int id;
    private String nameTableAdd;
    private String nameTable;
    private String nameSQL;
    private String contentSearch;
    private String columnName;
    private String columnTypeName;
    private int fromNumber;
    private int toNumber;
    private int recordNumber;
    private int notNull;
    private FBeans temp;
    private String[] fieldName;
    private int id_sql;
    private int pageIndex;

    public void reset(){
        this.setId(0);


    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getNameSQL() {
        return nameSQL;
    }

    public void setNameSQL(String nameSQL) {
        this.nameSQL = nameSQL;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }


    public int getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(int fromNumber) {
        this.fromNumber = fromNumber;
    }

    public int getToNumber() {
        return toNumber;
    }

    public void setToNumber(int toNumber) {
        this.toNumber = toNumber;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public FBeans getTemp() {
        return temp;
    }

    public void setTemp(FBeans temp) {
        this.temp = temp;
    }

    public int getNotNull() {
        return notNull;
    }

    public void setNotNull(int notNull) {
        this.notNull = notNull;
    }

    public String[] getFieldName() {
        return fieldName;
    }

    public void setFieldName(String[] fieldName) {
        this.fieldName = fieldName;
    }

    public int getId_sql() {
        return id_sql;
    }

    public void setId_sql(int id_sql) {
        this.id_sql = id_sql;
    }

    public String getNameTableAdd() {
        return nameTableAdd;
    }

    public void setNameTableAdd(String nameTableAdd) {
        this.nameTableAdd = nameTableAdd;
    }
}
