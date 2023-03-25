package com.dao.importdata;


import com.dao.cabin.DSqlCabin;

import com.inf.importdata.IFieldsImportData;
import com.inf.importdata.ITablesImportData;


public class DSqlImportData extends DSqlCabin implements ITablesImportData,IFieldsImportData{
 
    public final String SQL_SELECT_IMPORTDATA_TABLES = SELECT + STAR + FROM + TABLE_IMPORTDATA + WHERE + PARAM_01 + EQUAL + PARAM_01;
    public final String SQL_SELECT_IMPORTDATA_TABLES_ORDERBY = ORDER_BY + IMPORTDATA_TABLENAME;
    public final String SQL_SELECT_IMPORTDATA_COLUMNS = SELECT +STAR + FROM;
    public final String SQL_INSERT_IMPORTDATA_ADDTABLE = INSERT_INTO + TABLE_IMPORTDATA + " ("+IMPORTDATA_TABLENAME +CLOSE+ VALUES(PARAM_01);

}
