package com.dao.disability.report;


import com.exp.EException;

import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FDanSoHuyen;
import com.form.disability.FDanSoTinh;
import com.form.disability.FPopulation;
import com.form.disability.report.FReportTotal;

import com.lib.AppConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportCommuneExport  extends FExportExcel
{
    public String ReportExcelCommune(FSeed seed,FReportTotal bean,FPopulation beanPoTruoc,FPopulation beanPo,FDanSoHuyen beanDSHuyenTruoc,FDanSoHuyen beanDSHuyen,FDanSoTinh beanDSTinhTruoc,FDanSoTinh beanDSTinh,int k,String excelFile) throws  EException,FileNotFoundException,IOException,SQLException {
        final String LOCATION = this.toString() + "~>ReportExcelAllReportAddTime()";      
        int i=1;
        String excelPath = AppConfigs.APP_SYSTEM_PATH +  "disability"+AppConfigs.SYSTEM_FILE_SCHIP+"report"+AppConfigs.SYSTEM_FILE_SCHIP+"xls";                
        String excelDown= excelPath + seed.me.getSessionID();        
        File file = new File(excelPath,excelFile);    
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.createSheet("Trang 1");        
        sheet.setAutobreaks(true);
        //tao phan Thang: mm/yyyy
        HSSFFont fontArial11 = getFont(wb,"Times New Roman", 11, false);
        HSSFFont fontArialBold11 = getFont(wb,"Times New Roman", 11, true);
        
        CellStyle style = getStyle(wb,fontArial11, CellStyle.ALIGN_CENTER,CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);
        CellStyle styleLeft = getStyle(wb,fontArial11, CellStyle.ALIGN_LEFT,CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);
        CellStyle styleBold = getStyle(wb,fontArialBold11, CellStyle.ALIGN_LEFT,CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);
        CellStyle styleBoder = getStyle(wb,fontArial11, CellStyle.ALIGN_LEFT,CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE);               
        
        createCell(sheet.createRow(3), 2, bean.getParamValue()[i++] ,wb, styleBoder);
        createCell(sheet.createRow(4), 2, bean.getParamValue()[4] ,wb, styleBoder);
        createCell(sheet.createRow(5), 2, bean.getParamValue()[5],wb, styleBoder);         
       
        //i = 5;
        createCell(sheet.createRow(11), 3, bean.getParamValue()[6],wb, style);        
        createCell(sheet.createRow(11), 5, bean.getParamValue()[7],wb, style);
        createCell(sheet.createRow(11), 7, bean.getParamValue()[8],wb, style);
        
        createCell(sheet.createRow(12), 3, bean.getParamValue()[9],wb, style);
        createCell(sheet.createRow(12), 5, bean.getParamValue()[10],wb, style);
        createCell(sheet.createRow(12), 7, bean.getParamValue()[11],wb, style);
        
        
        //createCell(sheet.createRow(11), (1), bean.getParamValue()[12],wb, styleBold);
        
        // Giau / Kha
        //Region regGK = new Region(16, 3, 16, 4);
        //sheet.addMergedRegion(regGK);
        createCell(sheet.createRow(16), (3),bean.ncrToString("Gi&#224;u/Kh&#225;: ")+bean.getParamValue()[13],wb, styleBoder);       
        
        // Trung Binh
        //Region regTB = new Region(16, 5, 16, 6);
        //sheet.addMergedRegion(regTB);
        createCell(sheet.createRow(16), (5), bean.ncrToString("Trung b&#236;nh: ")+bean.getParamValue()[15],wb, styleBoder);
        
        // Can Ngheo
        //Region regCanNgheo = new Region(16, 7, 16, 8);
        //sheet.addMergedRegion(regCanNgheo);
        createCell(sheet.createRow(16), 7, bean.ncrToString("C&#7853;n ngh&#232;o: ")+bean.getParamValue()[16],wb, styleBoder);
        
        // Ngheo
        createCell(sheet.createRow(16), 9, bean.ncrToString("Ngh&#232;o: ")+bean.getParamValue()[17],wb, styleBoder);
        
        // DB Ngheo
        //Region regNgheo = new Region(16,  10,16, 11);
        //sheet.addMergedRegion(regNgheo);        
        createCell(sheet.createRow(16), 10, bean.ncrToString("&#272;&#7863;c bi&#7879;t ngh&#232;o: ")+bean.getParamValue()[18],wb, styleBoder);              
        
        // So luong % NKT
        int totalNKT_Male = 0;
        int totalNKT_Female = 0;
        int totalNKT = 0;
        int totalPopulation =  Integer.parseInt(bean.getParamValue()[8]) + Integer.parseInt(bean.getParamValue()[11]);
        
        int rowNKT_Male = 22;
        int rowNKT_Female = 27;
        
        for(int count = rowNKT_Male;count<27;count++ ){
            totalNKT_Male = totalNKT_Male + Integer.parseInt(bean.getParamValue()[count]);
        }
        
        for(int count1 = rowNKT_Female;count1<32;count1++){
            totalNKT_Female = totalNKT_Female + Integer.parseInt(bean.getParamValue()[count1]);
        }        
        
        totalNKT = totalNKT_Male + totalNKT_Female;        
        String pecentNKT =  String.valueOf(Math.round(totalNKT/totalPopulation*100)) + "%";
        createCell(sheet.createRow(17), (3),totalNKT + " (" + pecentNKT + ")",wb, styleBoder);
        createCell(sheet.createRow(18), (3),bean.getParamValue()[20],wb, styleBoder);    
        
        i = 20;
        i++;
        //Nam
        //createCell(sheet.createRow(25), (2),bean.ncrToString("S&#7889; NKT:(")+ bean.getParamValue()[i++]+")",wb, styleBold);

        int rowT=22;
        for (int j=1;j<=14;j++ )  {
            if(j!=2)
                createCell(sheet.createRow(rowT), (2), bean.getParamValue()[i++],wb, j==1?styleBold:styleLeft);
            
            createCell(sheet.createRow(rowT), (5), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(rowT), (6), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(rowT), (7), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(rowT), (8), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(rowT), (9), bean.getParamValue()[i++],wb, style);
            sheet.getRow(rowT).setHeightInPoints(15);
            rowT++;
        }
        
        //loai khuyet tat
        int kt=43;  // #### Van dong ####
        for (int j=1;j<=12;j++){            
            if(j==12){
                createCell(sheet.createRow(42), (2), bean.getParamValue()[i++],wb, styleBold);                
            }else{
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        }
        
        kt=55;      // #### Nghe, Noi ####
        for (int j=1;j<=3;j++){
            if(j==3){
                createCell(sheet.createRow(54), (2), bean.getParamValue()[i++],wb, styleBold);
            } else {
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        }    
        
        kt=58;      // #### Nhin ####
        for (int j=1;j<=10;j++){
            if(j==10){
                createCell(sheet.createRow(57), (2), bean.getParamValue()[i++],wb, styleBold);
            } else {
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        } 
        
        kt=68;      // #### Tri tue - Nhan thuc ####
        for (int j=1;j<=3;j++){
            if(j==3){
                createCell(sheet.createRow(67), (2), bean.getParamValue()[i++],wb, styleBold);
            } else {
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        }

        kt=71;      // #### Than kinh - Tam than ####
        for (int j=1;j<=3;j++){
            if(j==3){
                createCell(sheet.createRow(70), (2), bean.getParamValue()[i++],wb, styleBold);
            } else {
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        } 
        
        kt=74;      // #### Khuyet tat khac ####
        for (int j=1;j<=6;j++){
            if(j==6){
                createCell(sheet.createRow(73), (2), bean.getParamValue()[i++],wb, styleBold);
            } else {
                createCell(sheet.createRow(kt), (6), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (7), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (8), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (9), bean.getParamValue()[i++],wb, style);
                createCell(sheet.createRow(kt), (10), bean.getParamValue()[i++],wb, style);
                sheet.getRow(kt).setHeightInPoints(15);
                kt++;
            }
        }

        //ho tro
        int ht=84;
        for (int j=1;j<=2;j++)  {
            createCell(sheet.createRow(ht), (6), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (8), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (9), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (10), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (2), bean.getParamValue()[i++],wb, styleLeft);
            sheet.getRow(ht).setHeightInPoints(15);
            ht++;
        }                
        
        for (int j=1;j<=13;j++)  {
            createCell(sheet.createRow(ht), (6), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (8), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (9), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (10), bean.getParamValue()[i++],wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            ht++;
        }
        
        for (int j=1;j<=6;j++)  {
            createCell(sheet.createRow(ht), (6), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (8), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (9), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (10), bean.getParamValue()[i++],wb, style);
            createCell(sheet.createRow(ht), (2), bean.getParamValue()[i++],wb, styleLeft);
            sheet.getRow(ht).setHeightInPoints(15);
            ht++;
        }

        createCell(sheet.createRow(ht), (6), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);
        ht++;
        createCell(sheet.createRow(ht), (7), bean.getParamValue()[i++],wb, style);
        sheet.getRow(ht).setHeightInPoints(15);

        //hoat dong PHCNDVCD
        int ph=115;
        if(k==3){
            createCell(sheet.createRow(ph), (PARAM_06),beanPoTruoc.getNumber1()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber1()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber1()+beanPo.getNumber1()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber2()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber2()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber2()+beanPo.getNumber2()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber3()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber3()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber3()+beanPo.getNumber3()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber4()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber4()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber4()+beanPo.getNumber4()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber5()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber5()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber5()+beanPo.getNumber5()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber6()+beanPo.getNumber6()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
        
        
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber7()+"":beanPoTruoc.getNumber7()+"("+Math.round(beanPoTruoc.getNumber7()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber7()+"":beanPo.getNumber7()+"("+Math.round(beanPo.getNumber7()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber7()+beanPo.getNumber7()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber8()+"":beanPoTruoc.getNumber8()+"("+Math.round(beanPoTruoc.getNumber8()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber8()+"":beanPo.getNumber8()+"("+Math.round(beanPo.getNumber8()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber8()+beanPo.getNumber8()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber9()+"":beanPoTruoc.getNumber9()+"("+Math.round(beanPoTruoc.getNumber9()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber9()+"":beanPo.getNumber9()+"("+Math.round(beanPo.getNumber9()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber9()+beanPo.getNumber9()+"",wb, style);
            sheet.getRow(ph).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber10()+"":beanPoTruoc.getNumber10()+"("+Math.round(beanPoTruoc.getNumber10()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber10()+"":beanPo.getNumber10()+"("+Math.round(beanPo.getNumber10()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber10()+beanPo.getNumber10()+"",wb, style);
            sheet.getRow(ph).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber11()+"":beanPoTruoc.getNumber11()+"("+Math.round(beanPoTruoc.getNumber11()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber11()+"":beanPo.getNumber11()+"("+Math.round(beanPo.getNumber11()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber11()+beanPo.getNumber11()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber12()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber12()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber12()+beanPo.getNumber12()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber6()==0?beanPoTruoc.getNumber13()+"":beanPoTruoc.getNumber13()+"("+Math.round(beanPoTruoc.getNumber13()/beanPoTruoc.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber6()==0?beanPo.getNumber13()+"":beanPo.getNumber13()+"("+Math.round(beanPo.getNumber13()/beanPo.getNumber6()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber13()+beanPo.getNumber13()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber14()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber14()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber14()+beanPo.getNumber14()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanPoTruoc.getNumber15()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanPo.getNumber15()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_10),beanPoTruoc.getNumber15()+beanPo.getNumber15()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
        
        }else if(k==2){
            createCell(sheet.createRow(ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue2()+"":beanDSHuyenTruoc.getParamValue2()+"("+Math.round(beanDSHuyenTruoc.getParamValue2()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue2()+"":beanDSHuyen.getParamValue2()+"("+Math.round(beanDSHuyen.getParamValue2()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue3()+"":beanDSHuyenTruoc.getParamValue3()+"("+Math.round(beanDSHuyenTruoc.getParamValue3()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue3()+"":beanDSHuyen.getParamValue3()+"("+Math.round(beanDSHuyen.getParamValue3()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue4()+"":beanDSHuyenTruoc.getParamValue4()+"("+Math.round(beanDSHuyenTruoc.getParamValue4()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue4()+"":beanDSHuyen.getParamValue4()+"("+Math.round(beanDSHuyen.getParamValue4()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue5()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue5()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue6()+"":beanDSHuyenTruoc.getParamValue6()+"("+Math.round(beanDSHuyenTruoc.getParamValue6()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue6()+"":beanDSHuyen.getParamValue6()+"("+Math.round(beanDSHuyen.getParamValue6()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue7()+"":beanDSHuyenTruoc.getParamValue7()+"("+Math.round(beanDSHuyenTruoc.getParamValue7()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue7()+"":beanDSHuyen.getParamValue7()+"("+Math.round(beanDSHuyen.getParamValue7()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue9()+"":beanDSHuyenTruoc.getParamValue9()+"("+Math.round(beanDSHuyenTruoc.getParamValue9()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue9()+"":beanDSHuyen.getParamValue9()+"("+Math.round(beanDSHuyen.getParamValue9()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()==0?beanDSHuyenTruoc.getParamValue10()+"":beanDSHuyenTruoc.getParamValue10()+"("+Math.round(beanDSHuyenTruoc.getParamValue10()/beanDSHuyenTruoc.getParamValue9()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()==0?beanDSHuyen.getParamValue10()+"":beanDSHuyen.getParamValue10()+"("+Math.round(beanDSHuyen.getParamValue10()/beanDSHuyen.getParamValue9()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()==0?beanDSHuyenTruoc.getParamValue11()+"":beanDSHuyenTruoc.getParamValue11()+"("+Math.round(beanDSHuyenTruoc.getParamValue11()/beanDSHuyenTruoc.getParamValue9()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()==0?beanDSHuyen.getParamValue11()+"":beanDSHuyen.getParamValue11()+"("+Math.round(beanDSHuyen.getParamValue11()/beanDSHuyen.getParamValue9()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()==0?beanDSHuyenTruoc.getParamValue12()+"":beanDSHuyenTruoc.getParamValue12()+"("+Math.round(beanDSHuyenTruoc.getParamValue12()/beanDSHuyenTruoc.getParamValue9()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()==0?beanDSHuyen.getParamValue12()+"":beanDSHuyen.getParamValue12()+"("+Math.round(beanDSHuyen.getParamValue12()/beanDSHuyen.getParamValue9()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()==0?beanDSHuyenTruoc.getParamValue13()+"":beanDSHuyenTruoc.getParamValue13()+"("+Math.round(beanDSHuyenTruoc.getParamValue13()/beanDSHuyenTruoc.getParamValue9()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()==0?beanDSHuyen.getParamValue13()+"":beanDSHuyen.getParamValue13()+"("+Math.round(beanDSHuyen.getParamValue13()/beanDSHuyen.getParamValue9()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue9()==0?beanDSHuyenTruoc.getParamValue14()+"":beanDSHuyenTruoc.getParamValue14()+"("+Math.round(beanDSHuyenTruoc.getParamValue14()/beanDSHuyenTruoc.getParamValue9()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue9()==0?beanDSHuyen.getParamValue14()+"":beanDSHuyen.getParamValue14()+"("+Math.round(beanDSHuyen.getParamValue14()/beanDSHuyen.getParamValue9()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);        
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue15()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue15()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue16()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue16()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);        
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue17()+"":beanDSHuyenTruoc.getParamValue17()+"("+Math.round(beanDSHuyenTruoc.getParamValue17()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue17()+"":beanDSHuyen.getParamValue17()+"("+Math.round(beanDSHuyen.getParamValue17()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue18()+"":beanDSHuyenTruoc.getParamValue18()+"("+Math.round(beanDSHuyenTruoc.getParamValue18()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue18()+"":beanDSHuyen.getParamValue18()+"("+Math.round(beanDSHuyen.getParamValue18()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getTongXa()==0?beanDSHuyenTruoc.getParamValue19()+"":beanDSHuyenTruoc.getParamValue19()+"("+Math.round(beanDSHuyenTruoc.getParamValue19()/beanDSHuyenTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getTongXa()==0?beanDSHuyen.getParamValue19()+"":beanDSHuyen.getParamValue19()+"("+Math.round(beanDSHuyen.getParamValue19()/beanDSHuyen.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue20()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue20()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue21()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue21()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue22()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue22()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue23()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue23()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue24()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue24()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue25()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue25()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSHuyenTruoc.getParamValue26()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSHuyen.getParamValue26()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
        
        }else{
            createCell(sheet.createRow(ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue3()+"":beanDSTinhTruoc.getParamValue3()+"("+Math.round(beanDSTinhTruoc.getParamValue3()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue3()+"":beanDSTinh.getParamValue3()+"("+Math.round(beanDSTinh.getParamValue3()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue4()+"":beanDSTinhTruoc.getParamValue4()+"("+Math.round(beanDSTinhTruoc.getParamValue4()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue4()+"":beanDSTinh.getParamValue4()+"("+Math.round(beanDSTinh.getParamValue4()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue6()+"":beanDSTinhTruoc.getParamValue6()+"("+Math.round(beanDSTinhTruoc.getParamValue6()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue6()+"":beanDSTinh.getParamValue6()+"("+Math.round(beanDSTinh.getParamValue6()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue7()+"":beanDSTinhTruoc.getParamValue7()+"("+Math.round(beanDSTinhTruoc.getParamValue7()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue7()+"":beanDSTinh.getParamValue7()+"("+Math.round(beanDSTinh.getParamValue7()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue8()+"":beanDSTinhTruoc.getParamValue8()+"("+Math.round(beanDSTinhTruoc.getParamValue8()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue11()+"":beanDSTinh.getParamValue8()+"("+Math.round(beanDSTinh.getParamValue8()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue9()+"":beanDSTinhTruoc.getParamValue9()+"("+Math.round(beanDSTinhTruoc.getParamValue9()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue9()+"":beanDSTinh.getParamValue9()+"("+Math.round(beanDSTinh.getParamValue9()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue10()+"":beanDSTinhTruoc.getParamValue10()+"("+Math.round(beanDSTinhTruoc.getParamValue10()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue10()+"":beanDSTinh.getParamValue10()+"("+Math.round(beanDSTinh.getParamValue10()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()==0?beanDSTinhTruoc.getParamValue12()+"":beanDSTinhTruoc.getParamValue12()+"("+Math.round(beanDSTinhTruoc.getParamValue12()/beanDSTinhTruoc.getParamValue11()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()==0?beanDSTinh.getParamValue12()+"":beanDSTinh.getParamValue12()+"("+Math.round(beanDSTinh.getParamValue12()/beanDSTinh.getParamValue11()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()==0?beanDSTinhTruoc.getParamValue13()+"":beanDSTinhTruoc.getParamValue13()+"("+Math.round(beanDSTinhTruoc.getParamValue13()/beanDSTinhTruoc.getParamValue11()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()==0?beanDSTinh.getParamValue13()+"":beanDSTinh.getParamValue13()+"("+Math.round(beanDSTinh.getParamValue13()/beanDSTinh.getParamValue11()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()==0?beanDSTinhTruoc.getParamValue14()+"":beanDSTinhTruoc.getParamValue14()+"("+Math.round(beanDSTinhTruoc.getParamValue14()/beanDSTinhTruoc.getParamValue11()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()==0?beanDSTinh.getParamValue14()+"":beanDSTinh.getParamValue14()+"("+Math.round(beanDSTinh.getParamValue14()/beanDSTinh.getParamValue11()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()==0?beanDSTinhTruoc.getParamValue15()+"":beanDSTinhTruoc.getParamValue15()+"("+Math.round(beanDSTinhTruoc.getParamValue15()/beanDSTinhTruoc.getParamValue11()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()==0?beanDSTinh.getParamValue15()+"":beanDSTinh.getParamValue15()+"("+Math.round(beanDSTinh.getParamValue15()/beanDSTinh.getParamValue11()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue11()==0?beanDSTinhTruoc.getParamValue16()+"":beanDSTinhTruoc.getParamValue16()+"("+Math.round(beanDSTinhTruoc.getParamValue16()/beanDSTinhTruoc.getParamValue11()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue11()==0?beanDSTinh.getParamValue16()+"":beanDSTinh.getParamValue16()+"("+Math.round(beanDSTinh.getParamValue16()/beanDSTinh.getParamValue11()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);        
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue17()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue17()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue18()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue18()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue17()+"":beanDSTinhTruoc.getParamValue17()+"("+Math.round(beanDSTinhTruoc.getParamValue17()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue17()+"":beanDSTinh.getParamValue17()+"("+Math.round(beanDSTinh.getParamValue17()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue18()+"":beanDSTinhTruoc.getParamValue18()+"("+Math.round(beanDSTinhTruoc.getParamValue18()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue18()+"":beanDSTinh.getParamValue18()+"("+Math.round(beanDSTinh.getParamValue18()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue19()+"":beanDSTinhTruoc.getParamValue19()+"("+Math.round(beanDSTinhTruoc.getParamValue19()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue19()+"":beanDSTinh.getParamValue19()+"("+Math.round(beanDSTinh.getParamValue19()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getTongXa()==0?beanDSTinhTruoc.getParamValue20()+"":beanDSTinhTruoc.getParamValue20()+"("+Math.round(beanDSTinhTruoc.getParamValue20()/beanDSTinhTruoc.getTongXa()*100)+"%)",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getTongXa()==0?beanDSTinh.getParamValue20()+"":beanDSTinh.getParamValue20()+"("+Math.round(beanDSTinh.getParamValue20()/beanDSTinh.getTongXa()*100)+"%)",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue21()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue21()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue22()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue22()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue23()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue23()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue24()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue24()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue25()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue25()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue26()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue26()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue27()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue27()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
            createCell(sheet.createRow(++ph), (PARAM_06),beanDSTinhTruoc.getParamValue28()+"",wb, style);
            createCell(sheet.createRow(ph), (PARAM_08),beanDSTinh.getParamValue28()+"",wb, style);
            sheet.getRow(ht).setHeightInPoints(15);
        }
        
        //can trang vao giua
        sheet.setHorizontallyCenter(true);
        //set margin
        sheet.setMargin(sheet.TopMargin, 0);
        sheet.setMargin(sheet.BottomMargin, 2.5);
        sheet.setMargin(sheet.LeftMargin, 0.25);
        sheet.setMargin(sheet.RightMargin, 0.25);            
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);        
        fos.close();   
        return excelDown;  
    }
}

