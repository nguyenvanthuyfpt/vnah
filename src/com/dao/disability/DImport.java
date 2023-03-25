package com.dao.disability;


import com.dao.DCore;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FImport;
import com.form.disability.FTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.ss.usermodel.Row;


public class DImport extends DCore {

    public FImport readFileExcel(String fileName,
                                 int rowBegin) throws ServletException,
                                                      IOException {
        FImport bean = new FImport();
        try {
            File xlsfile = new File(fileName);
            FileInputStream file = new FileInputStream(xlsfile);
            Workbook wb = Workbook.getWorkbook(file);
            Sheet sheet = null;
            int rows = 0, cols = 0, r = 0;
            // READ SHEET 0
            sheet = wb.getSheet(0);
            rows = sheet.getRows();
            cols = sheet.getColumns();
            r = 0;
            bean.setDataSheet0(new String[cols][rows - rowBegin]);
            for (int row = rowBegin; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    bean.setDataSheet0(sheet.getCell(col, row).getContents() +
                                       "", col, r);
                }
                r++;
            }

            // READ SHEET 1
            /*sheet = wb.getSheet(1);
            rows = sheet.getRows();
            cols = sheet.getColumns();
            r = 0;
            bean.setDataSheet1(new String[cols][rows-rowBegin]);
            for (int row = rowBegin; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    bean.setDataSheet1(sheet.getCell(col, row).getContents() + "", col, r);
                }
                r++;
            }

            // READ SHEET 2
            sheet = wb.getSheet(2);
            rows = sheet.getRows();
            cols = sheet.getColumns();
            r = 0;
            bean.setDataSheet2(new String[cols][rows-rowBegin]);
            for (int row = rowBegin; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    bean.setDataSheet2(sheet.getCell(col, row).getContents() + "", col, r);
                }
                r++;
            }

            // READ SHEET 3
            sheet = wb.getSheet(3);
            rows = sheet.getRows();
            cols = sheet.getColumns();
            r = 0;
            bean.setDataSheet3(new String[cols][rows-rowBegin]);
            for (int row = rowBegin; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    bean.setDataSheet3(sheet.getCell(col, row).getContents() + "", col, r);
                }
                r++;
            }*/

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return bean;
    }

    /*public boolean insert(Connection cnn, FSeed seed, String[][] data) throws EException {
        boolean result = false;
        PreparedStatement prstm = null;
        FImport bean = (FImport)seed;

        String SQL_SELECT = "INSERT INTO " + bean.getTableName() + "(#colum#) VALUES (#value#)";
        String fieldValue = "";
        String questions = "";

        boolean flag = false;
        try {
            for (int j = 0; j < bean.getNameField().length; j++) {
                if (!"".equals(fieldValue)) {
                    fieldValue += ",";
                    questions += ",";
                }

                fieldValue += bean.getNameField()[j];
                questions += "?";
            }

            SQL_SELECT = SQL_SELECT.replaceFirst("#colum#", fieldValue).replaceFirst("#value#", questions);
            prstm = cnn.prepareStatement(SQL_SELECT);
            int row, col;

            for (row = bean.getRowDataStart(); row <= data[0].length; row++) {
                for (col = 0; col < data.length; col++) {
                    validateDataType(prstm, data[col][row], bean.getTypeData()[col], col + 1);
                }

                prstm.addBatch();
                flag = true;

                if (flag) {
                    result = prstm.executeBatch().length > 0;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return result;
    }*/

    public boolean insert(Connection cnn, FSeed seed,
                          String[][] data) throws EException, SQLException {

        boolean result = false;
        PreparedStatement prstm = null;
        Statement stmt = null;
        FImport bean = (FImport)seed;
        String[] x = bean.getNameField();
        int typeImport = bean.getTypeImport();
        int posOfPrimaryKey = -1;
        int posOfMaso = -1;
        int posSonha = -1;
        int posIdTinh = -1;
        int posTen = -1;
        int posSex = -1;
        int posNamsinhChuho = -1;

        String data_sheet1[][] = bean.getDataSheet1();
        String data_sheet2[][] = bean.getDataSheet2();
        String data_sheet3[][] = bean.getDataSheet3();

        if (typeImport == 1) {
            for (int i = 0; i < x.length; i++) {
                if (x[i].equalsIgnoreCase("code"))
                    posOfPrimaryKey = i;
            }

        } else {
            for (int i = 0; i < x.length; i++) {
                if (x[i].equalsIgnoreCase("maso"))
                    posOfMaso = i;
                if (x[i].equalsIgnoreCase("ma"))
                    posOfPrimaryKey = i;
                if (x[i].equalsIgnoreCase("sonha"))
                    posSonha = i;
                if (x[i].equalsIgnoreCase("id_tinh"))
                    posIdTinh = i;
                if (x[i].equalsIgnoreCase("ten"))
                    posTen = i;
                if (x[i].equalsIgnoreCase("sex"))
                    posSex = i;
                if (x[i].equalsIgnoreCase("namsinh_chuho"))
                    posNamsinhChuho = i;
            }
        }


        //CODE TO UPDATE RECORDS
        int noMatchingRecordsFound = 0;
        int updatedRecords = 0;
        //perform update here.
        //preparing insert statement before loop 1 time
        String sqlInsert =
            "INSERT INTO " + bean.getTableName() + "(#colum#) VALUES (#value#)";
        String fieldValue = "";
        String questions = "";
        boolean flag = false;

        for (int j = 0; j < bean.getNameField().length; j++) {
            if (!fieldValue.equals("")) {
                fieldValue += ",";
                questions += ",";
            }
            fieldValue += bean.getNameField()[j];
            questions += "?";
        }

        sqlInsert =
                sqlInsert.replaceFirst("#colum#", fieldValue).replaceFirst("#value#",
                                                                           questions);
        System.err.println("INSERT String Prepared = " + sqlInsert);
        //##############################
        int rowsUpdated = 0;
        int rowsInserted = 0;
        final int batchSize = 1000;
        int count = 0;

        //LOOP THROUGH ALL THE RECORDS IN THE FILE
        for (int row = 1; row < data[0].length; row++) {
            boolean insert = true;
            int len = bean.getNameField().length;
            String sqlSelect = "SELECT COUNT(1)";

            //sqlSelect = sqlSelect + bean.getNameField()[len-1] +" FROM " + bean.getTableName()+ " WHERE 1=1";
            sqlSelect =
                    sqlSelect + " FROM " + bean.getTableName() + " WHERE 1=1";

            if (typeImport == 1) {
                if (posOfPrimaryKey > -1)
                    sqlSelect +=
                            " AND code='" + data[posOfPrimaryKey][row] + "'";
            } else {
                if (posOfPrimaryKey > -1)
                    sqlSelect +=
                            " AND ma='" + data[posOfPrimaryKey][row] + "'";
            }
            /*} else {
                if(posSonha>-1)         sqlSelect += " AND sonha='" + data[posSonha][row] + "'";
                if(posIdTinh>-1)        sqlSelect += " AND id_tinh=" + data[posIdTinh][row];
                if(posTen>-1)           sqlSelect += " AND ten='" +  data[posTen][row] + "'";
                if(posSex>-1)           sqlSelect += " AND sex=" + data[posSex][row];
                if(posNamsinhChuho>0)   sqlSelect += " AND namsinh_chuho=" + data[posNamsinhChuho][row];
            }*/

            //System.out.println("SELECT String Prepared = "+sqlSelect);

            try {
                Statement stmt1 =
                    cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt1.executeQuery(sqlSelect);
                while (rs.next()) {
                    noMatchingRecordsFound = rs.getInt(1);
                }
                //System.err.println("No of Rows Returned = " + noMatchingRecordsFound);
            } catch (SQLException ex) {
                System.err.println("--- SELECT FAILED SQLException caught ---");
                while (ex != null) {
                    System.out.println("Message:" + ex);
                    ex = ex.getNextException();
                    break;
                }
            }

            if (noMatchingRecordsFound == 1) {
                String sqlUpdate = "UPDATE " + bean.getTableName() + " SET ";
                for (int j = 0; j < len - 1; j++) {
                    sqlUpdate = sqlUpdate + bean.getNameField()[j] + " =?, ";
                }

                if (typeImport == 1) {
                    sqlUpdate =
                            sqlUpdate + bean.getNameField()[len - 1] + "=? WHERE code='" +
                            data[posOfPrimaryKey][row] + "'";
                } else {
                    sqlUpdate =
                            sqlUpdate + bean.getNameField()[len - 1] + "=? WHERE ma='" +
                            data[posOfPrimaryKey][row] + "'";
                }


                try {
                    prstm = cnn.prepareStatement(sqlUpdate);
                    for (int col = 0; col < data.length; col++) {
                        System.out.println("FOR UPDATE : Data[" + col + "]" +
                                           "[" + row + "]=" + data[col][row] +
                                           ", ");
                        validateDataType(prstm, data[col][row],
                                         bean.getTypeData()[col], col + 1);
                    }

                    //prstm.addBatch();
                    prstm.executeUpdate();
                    flag = true;
                    rowsUpdated++;
                    updatedRecords++;
                    prstm.close();
                } catch (SQLException exe) {
                    System.err.println("Error" + exe);
                    insert = false;
                    break;
                }
            } else {
                insert = true;
                /*if(noMatchingRecordsFound > 1){
                    System.err.println("MULTIPLE ROWS FOUND FOR [" + data[posTen][row] + "]");
                }
                if(noMatchingRecordsFound == 0){
                    System.err.println("ZERO ROWS FOUND FOR [" + data[posTen][row] + "] NOW INSERT");
                    insert=true;
                }*/
            }


            //IF THERE ARE NO RECORDS UPDATED AND IF THERE IS NO SQLEXCEPTION
            if (updatedRecords == 0 && insert) {
                //THEN PERFORM THE INSERT
                prstm = cnn.prepareStatement(sqlInsert);
                for (int col = 0; col < data.length; col++) {
                    System.out.println("FOR INSERT : Data[" + col + "]" + "[" +
                                       row + "]=" + data[col][row] + ", ");
                    validateDataType(prstm, data[col][row],
                                     bean.getTypeData()[col], col + 1);
                }
                prstm.addBatch();
                //for simple let us do 1 by 1
                //writing code.
                try {
                    System.out.println("Trying to insert [" +
                                       data[posOfPrimaryKey][row] + "]");
                    prstm.executeBatch(); //trying to check update alone by blocking insert.
                    flag = true;
                    rowsInserted++;
                    prstm.close();
                } catch (Exception exe) {

                }
            }
            updatedRecords = 0;
        }

        if (flag) {
            result = true;
            bean.setNoInsert(rowsInserted);
            bean.setNoUpdate(rowsUpdated);
        }
        //System.out.println("ROWS INSERTED = " + rowsInserted + ", ROWS UPDATED = " + rowsUpdated);
        return result;
    }

    public FBeans getAllTable(Connection cnn,
                              boolean isCategory) throws EException {
        FBeans beans = new FBeans();
        try {
            DatabaseMetaData dbmd = cnn.getMetaData();
            String[] types = { "TABLE" };
            ResultSet rs = dbmd.getTables(null, null, "%", types);

            while (rs.next()) {
                FTable bean = new FTable();
                String temp = rs.getString(3);
                if (temp.indexOf("dr_") >= 0) {
                    if (isCategory) {
                        if (!"dr_disabilitypeople".equals(temp)) {
                            bean.setTableName(temp);
                            beans.add(bean);
                        }
                    } else {
                        bean.setTableName(temp);
                        beans.add(bean);
                    }

                }
            }
        } catch (Exception ex) {
            //.println("Error: " + ex);
        }
        return beans;
    }

    public Map<String, String> getAllColumn(Connection cnn,
                                            String tableName) throws EException {
        FBeans beans = new FBeans();
        Map<String, String> map_table = new HashMap<String, String>();
        try {
            DatabaseMetaData dbmd = cnn.getMetaData();
            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "my_table";
            String columnNamePattern = null;
            ResultSet rs =
                dbmd.getColumns(catalog, schemaPattern, tableName, columnNamePattern);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("TYPE_NAME");
                map_table.put(columnName, columnType);
            }
        } catch (Exception ex) {

        }
        return map_table;
    }


    private void validateDataType(PreparedStatement prstm, Object Value,
                                  String dataType,
                                  int PARAM) throws EException {
        FImport bean = new FImport();
        try {
            int type = 0;
            if ("varchar".equalsIgnoreCase(dataType))
                type = 1;
            else if ("int8".equalsIgnoreCase(dataType) ||
                     "int4".equalsIgnoreCase(dataType) ||
                     "bigserial".equalsIgnoreCase(dataType))
                type = 2;
            else if ("timestamp".equalsIgnoreCase(dataType))
                type = 3;
            switch (type) {
            case 1:
                prstm.setString(PARAM,
                                "".equals(Value.toString()) ? "" : Value.toString());
                break;
            case 2:
                prstm.setInt(PARAM,
                             ("".equals(Value.toString()) || Value == null) ?
                             0 : Integer.parseInt(Value.toString()));
                break;
            case 3:
                prstm.setDate(PARAM, bean.stringToSqlDate(Value.toString()));
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public static int getRowCount(ResultSet set) throws SQLException {
        int rowCount;
        int currentRow = set.getRow(); // Get current row
        rowCount = set.last() ? set.getRow() : 0; // Determine number of rows
        if (currentRow == 0) // If there was no current row
            set.beforeFirst(); // We want next() to go to first row
        else // If there WAS a current row
            set.absolute(currentRow); // Restore it
        return rowCount;
    }

    public FImport readFile(String fileName,
                            int rowBegin) throws ServletException,
                                                 IOException {
        FImport bean = new FImport();
        try {
            File xlsfile = new File(fileName);
            FileInputStream file = new FileInputStream(xlsfile);
            Workbook wb = Workbook.getWorkbook(file);
            int rows = 0, cols = 0, r = 0;
            // READ SHEET 0

            Sheet sheet = wb.getSheet(0);
            cols = sheet.getColumns();
            r = 0;
            bean.setDataSheet0(new String[cols][rows - rowBegin]);
            for (int row = rowBegin; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    bean.setDataSheet0(sheet.getCell(col, row).getContents() +
                                       "", col, r);
                }
                r++;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return bean;
    }
}
