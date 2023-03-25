package com.dao.doc.docsSearch;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsSearch.FDocsSearch;
import com.form.doc.docsrecv.FDocsrecv;

import com.lib.AppConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class DDocsReport extends DSqlForYou
{
    public String exportExcel(FBeans beans,FSeed seed,String excelFile) throws  EException,FileNotFoundException,IOException {
        final String LOCATION = this.toString() + "~>exportExcel()";      
        FDocsSearch beanT=(FDocsSearch)seed;  
        String excelPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.REPORT_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP;        
        String excelDown= excelPath + beanT.me.getSessionID();        
        File file = new File(excelPath,excelFile);    
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet=wb.getSheetAt(0);
        int x = AppConfigs.REPORT_SHEETLIST_X;
        int y = AppConfigs.REPORT_SHEETLIST_Y; 
        setDS_Doc(beans,beanT.getFields(),y,x,sheet,wb,beanT.getWorkflowId());              
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);        
        fos.close();   
        return excelDown;     
     }
    private void setFiedls(HSSFRow row, FDocsrecv bean,HSSFWorkbook wb,int i)
    {
      for(int j=0;j<bean.getValues().length;j++){
          createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),bean.getValue(j) ,wb);
          setStyle(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
      }
    }
    
    private void setHeaderFiedls(HSSFRow row,int[] fields,HSSFWorkbook wb,int workflowId)
    {
      FSeed bean =new FSeed();
      for(int j=0;j<fields.length;j++){
          createCell(row, (short)(AppConfigs.REPORT_SHEETLIST_X + j),workflowId==AppConfigs.DOCSRECV_WORKFLOWID?bean.ncrToString(com.inf.IConstants.HEADER_REPORT_RECV_DOCS[fields[j]]):bean.ncrToString(com.inf.IConstants.HEADER_REPORT_SEND_DOCS[fields[j]]),wb);                
          setStyleBold(row,(short)(AppConfigs.REPORT_SHEETLIST_X + j),wb);
      }
    }
      
    private void setDS_Doc(FBeans beans,int[] fields,int y,int x,HSSFSheet sheet,HSSFWorkbook wb,int workflowId)
    {
            HSSFRow row;
            y++;
            HSSFRow rowHeader;        
            FDocsrecv bean =new FDocsrecv();
            int dem=0;
            rowHeader = sheet.createRow(1);
            setHeaderFiedls(rowHeader,fields,wb,workflowId);
            for(int k=0;k<beans.size();k++){                   
                bean = (FDocsrecv)beans.get(k);
                sheet.shiftRows(y,sheet.getLastRowNum(),1); 
                row = sheet.createRow(y++);
                setFiedls(row,bean,wb,++dem);                          
            
            }               
    }
    //*************************************************************
    public void createCell(HSSFRow row, short column, String value,HSSFWorkbook wb){
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        //cell.setEncoding(wb.ENCODING_UTF_16);       
        cell.setCellValue(value);           
    }  
    
    
    
    private void setStyle(HSSFRow row, short column, HSSFWorkbook wb)
    {
           HSSFCell cell = row.getCell(column);
           if(cell==null) cell = row.createCell(column);
           HSSFCellStyle style = wb.createCellStyle();
           style.setWrapText(true);       
           style.setBorderTop(HSSFCellStyle.BORDER_THIN);
           style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
           style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
           style.setBorderRight(HSSFCellStyle.BORDER_THIN);
           cell.setCellStyle(style);
    }
    
    //*************************************************************
    private void bold(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      
        style.setFont(font);           
        cell.setCellStyle(style);
    }
    ////***************
    private void wrap(HSSFRow row, short column, HSSFWorkbook wb)
    {
         HSSFCell cell = row.getCell(column);
         if(cell==null) cell = row.createCell(column);
         HSSFCellStyle style = wb.createCellStyle();              
         style.setWrapText(true);                                 
         cell.setCellStyle(style);
    }
    ////***************
    private void setStyleBold(HSSFRow row, short column, HSSFWorkbook wb)
    {
        HSSFCell cell = row.getCell(column);
        if(cell==null) cell = row.createCell(column);
        HSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true); 
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);      
        style.setFont(font);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }
}
