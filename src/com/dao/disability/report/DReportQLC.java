package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportQLC;

import com.lib.AppConfigs;

import com.util.Formater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportQLC extends FExportExcel{
    
    public String exportExcelDetail(int level,FReportQLC beanTemp, FSeed seed, String excelFile) throws EException, 
                                FileNotFoundException, IOException {
        
        final String LOCATION = this.toString() + "-->exportExcelDetail()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + 
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
            
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);

        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        
        HSSFFont f11 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont fb11 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb13 = getFont(wb, "Times New Roman", 13, true);
        HSSFFont fb14 = getFont(wb, "Times New Roman", 14, true);
        
        CellStyle cs_normal = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_normal.setWrapText(true);
        
        CellStyle cs_bold_header = getStyle(wb, fb11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_bold_header.setWrapText(true);
        
        CellStyle cs_title_13 = getStyle(wb, fb13, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_title_14 = getStyle(wb, fb14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_right = getStyle(wb, f11, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_center = getStyle(wb, f11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        FReportQLC bean = new FReportQLC();
        HSSFRow row = null;      
        int i=0,stt = 1,dong = 0;
        String[] arr_char = {"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"};
        String formula = "";
        
        row = sheet.getRow(0);
        String diaban = "";
        String title_nodata = bean.ncrToString("Kh&#244;ng c&#243; d&#7919; li&#7879;u");
        diaban = bean.ncrToString("UBND ") + beanTemp.getNameArea();    
        createCell(row, 0, diaban, wb, cs_title_13);
        
        row = sheet.getRow(4);
        String time = bean.ncrToString("Th&#225;ng: " + beanTemp.getKyBC() + "/" + beanTemp.getNamBC());
        createCell(row, 0, time, wb, cs_title_13);

        if (level != 3) {    // Quan-Huyen
            dong = 11;
            if (beanTemp.getStore().size()>0) {
                while (i<beanTemp.getStore().size()){
                    bean = (FReportQLC)beanTemp.getStore().get(i);
                    int cot = 0;
                    row = sheet.createRow(dong);
                    createCell(row, cot++, stt, wb, cs_right);
                    createCell(row, (cot++), bean.getDiaBan(), wb, cs_normal);
                    createCell(row, (cot++), bean.getTGia(), wb, cs_right);
                    createCell(row, (cot++), bean.getTGiaLaoDong(), wb, cs_right);
                    createCell(row, (cot++), bean.getTGiaYTe(), wb, cs_right);
                    createCell(row, (cot++), bean.getTGiaGiaoDuc(), wb, cs_right);
                    createCell(row, (cot++), bean.getLKe(), wb, cs_right);
                    createCell(row, (cot++), bean.getLKeLaoDong(), wb, cs_right);
                    createCell(row, (cot++), bean.getLKeYTe(), wb, cs_right);
                    createCell(row, (cot++), bean.getLKeGiaoDuc(), wb, cs_right);
                    i++;
                    stt++;
                    dong++;
                }

                formula = "SUM(@12:@#)";
                int cot = 2;
                i = 0;
                row = sheet.createRow(dong);
                createRegion(wb, row, dong, dong, 0, 1, cs_bold_header, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);
                while (cot<10){
                    formula = formula.replaceAll("#", "" + dong + "");
                    formula = formula.replaceAll("@", arr_char[i++]);
                    createCellFormula(row, cot++, formula, wb, cs_right);
                    formula = "SUM(@12:@#)";
                }

            } else {
                createRegion(wb, sheet.createRow(dong), dong, dong, 0, 9, cs_center, title_nodata, 1, 0);
            }

            // Danh sach 2
            row = sheet.createRow(dong+2);
            String title = "2. Danh s&#225;ch NKT k&#7871;t th&#250;c ch&#432;&#417;ng tr&#236;nh QLQTH (02)";
            createRegion(wb, row, dong+2, dong+2, 0, 6, cs_title_14, bean.ncrToString(title), 0, 0);

            row = sheet.createRow(dong+3);
            createRegion(wb, row, dong+3, dong+4, 0, 0, cs_bold_header, "STT", 1, 0);
            createRegion(wb, row, dong+3, dong+4, 1, 1, cs_bold_header, bean.ncrToString("Ph&#432;&#7901;ng/x&#227;"), 1, 0);
            createRegion(wb, row, dong+3, dong+4, 2, 3, cs_bold_header, bean.ncrToString("T&#7893;ng s&#7889; NKT k&#7871;t th&#250;c ch&#432;&#417;ng tr&#236;nh QLTH trong th&#225;ng"), 1, 0);
            createRegion(wb, row, dong+3, dong+3, 4, 7, cs_bold_header, bean.ncrToString("Nguy&#234;n nh&#226;n k&#7871;t th&#250;c QLTH"), 1, 0);
            createCell(sheet.getRow(dong+4), 4, bean.ncrToString("Ho&#224;n th&#224;nh k&#7871; ho&#7841;ch h&#7895; tr&#7907; c&#225; nh&#226;n"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 5, bean.ncrToString("&#272;i kh&#7887;i &#273;&#7883;a b&#224;n c&#243; x&#225;c &#273;&#7883;nh"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 6, bean.ncrToString("&#272;i kh&#7887;i &#273;&#7883;a b&#224;n kh&#244;ng x&#225;c &#273;&#7883;nh"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 7, bean.ncrToString("Ch&#7871;t"), wb, cs_bold_header);

            dong = dong+5;
            i=0;
            stt = 1;

            int row_sum = dong+1;
            int sum_kthuc = 0;
            formula = "SUM(@"+row_sum+":@#)";

            if (beanTemp.getStore1().size()> 0) {
                while (i<beanTemp.getStore1().size()){
                    bean = (FReportQLC)beanTemp.getStore1().get(i);
                    int cot = 0;
                    row = sheet.createRow(dong);
                    createCell(row, cot++, stt, wb, cs_right);
                    createCell(row, (cot++), bean.getDiaBan(), wb, cs_normal);
                    sum_kthuc += bean.getKThuc();
                    createRegion(wb, row, dong, dong, 2, 3, cs_right, Formater.num2str(bean.getKThuc()), 1, 0);

                    cot = 4;
                    createCell(row, (cot++), bean.getNgNhan1(), wb, cs_right);
                    createCell(row, (cot++), bean.getNgNhan2(), wb, cs_right);
                    createCell(row, (cot++), bean.getNgNhan3(), wb, cs_right);
                    createCell(row, (cot++), bean.getNgNhan4(), wb, cs_right);
                    i++;
                    stt++;
                    dong++;
                }

              int cot = 2;
              i = 0;
              row = sheet.createRow(dong);
              createRegion(wb, row, dong, dong, 0, 1, cs_bold_header, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);
              while (cot<8){
                  formula = formula.replaceAll("#", "" + dong + "");
                  formula = formula.replaceAll("@", arr_char[i++]);
                  if (cot==2) {
                    createRegion(wb, row, dong, dong, 2, 3, cs_right, String.valueOf(sum_kthuc), 1, 0);
                    cot = 3;
                  } else {
                    createCellFormula(row, cot++, formula, wb, cs_right);
                    formula = "SUM(@"+row_sum+":@#)";
                  }

              }

            } else {
                createRegion(wb, sheet.createRow(dong), dong, dong, 0, 7, cs_center, title_nodata, 1, 0);
            }
        } else {  // Phuong/xa
            dong = 9;
            if (beanTemp.getStore().size()>0) {
                while (i<beanTemp.getStore().size()){
                    bean = (FReportQLC)beanTemp.getStore().get(i);
                    int cot = 0;
                    row = sheet.createRow(dong);
                    createCell(row, cot++, stt, wb, cs_right);
                    createCell(row, (cot++), bean.getHoTen(), wb, cs_normal);
                    createCell(row, (cot++), bean.getMaSo(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), bean.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), bean.getDangTat(), wb, cs_normal);
                    createCell(row, (cot++), bean.getMucDoKTat(), wb, cs_normal);
                    createCell(row, (cot++), bean.getHoanCanhKTe(), wb, cs_normal);
                    createCell(row, (cot++), bean.getHoTenChuHo(), wb, cs_normal);
                    createCell(row, (cot++), bean.getTenNgQLy(), wb, cs_normal);
                    i++;
                    stt++;
                    dong++;
                }
            } else {
                createRegion(wb, sheet.createRow(dong), dong, dong, 0, 9, cs_center, title_nodata, 1, 0);
            }

            // Danh sach 2
            row = sheet.createRow(dong+2);
            String title = "2. Danh s&#225;ch NKT k&#7871;t th&#250;c ch&#432;&#417;ng tr&#236;nh QLQTH (02)";
            createRegion(wb, row, dong+2, dong+2, 0, 6, cs_title_14, bean.ncrToString(title), 0, 0);

            row = sheet.createRow(dong+3);
            createRegion(wb, row, dong+3, dong+4, 0, 0, cs_bold_header, "STT", 1, 0);
            createRegion(wb, row, dong+3, dong+4, 1, 1, cs_bold_header, bean.ncrToString("H&#7885; v&#224; t&#234;n NKT"), 1, 0);
            createRegion(wb, row, dong+3, dong+4, 2, 2, cs_bold_header, bean.ncrToString("M&#227; s&#7889; QLTH"), 1, 0);
            createRegion(wb, row, dong+3, dong+4, 3, 3, cs_bold_header, bean.ncrToString("N&#259;m sinh"), 1, 0);
            createRegion(wb, row, dong+3, dong+4, 4, 4, cs_bold_header, bean.ncrToString("&#272;&#7883;a ch&#7881;"), 1, 0);
            createRegion(wb, row, dong+3, dong+3, 5, 8, cs_bold_header, bean.ncrToString("Nguy&#234;n nh&#226;n k&#7871;t th&#250;c QLTH"), 1, 0);
            createCell(sheet.getRow(dong+4), 5, bean.ncrToString("Ho&#224;n th&#224;nh k&#7871; ho&#7841;ch h&#7895; tr&#7907; c&#225; nh&#226;n"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 6, bean.ncrToString("&#272;i kh&#7887;i &#273;&#7883;a b&#224;n c&#243; x&#225;c &#273;&#7883;nh"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 7, bean.ncrToString("&#272;i kh&#7887;i &#273;&#7883;a b&#224;n kh&#244;ng x&#225;c &#273;&#7883;nh"), wb, cs_bold_header);
            createCell(sheet.getRow(dong+4), 8, bean.ncrToString("Ch&#7871;t"), wb, cs_bold_header);
            createRegion(wb, row, dong+3, dong+4, 9, 9, cs_bold_header, bean.ncrToString("T&#234;n c&#225;n b&#7897; QLTH ph&#7909; tr&#225;ch"), 1, 0);

            dong = dong+5;
            i=0;
            stt = 1;
            if (beanTemp.getStore1().size()>0) {
                while (i<beanTemp.getStore1().size()){
                    bean = (FReportQLC)beanTemp.getStore1().get(i);
                    int cot = 0;
                    row = sheet.createRow(dong);
                    createCell(row, cot++, stt, wb, cs_right);
                    createCell(row, (cot++), bean.getHoTen(), wb, cs_normal);
                    createCell(row, (cot++), bean.getMaSo(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), bean.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNgNhan1(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNgNhan2(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNgNhan3(), wb, cs_normal);
                    createCell(row, (cot++), bean.getNgNhan4(), wb, cs_normal);
                    createCell(row, (cot++), bean.getTenNgQLy(), wb, cs_normal);
                    i++;
                    stt++;
                    dong++;
                }
            } else {
                createRegion(wb, sheet.createRow(dong), dong, dong, 0, 9, cs_center, title_nodata, 1, 0);
            }

        }

        sheet.setHorizontallyCenter(true);
        sheet.setMargin(sheet.TopMargin, 0);
        sheet.setMargin(sheet.BottomMargin, 2.5);
        sheet.setMargin(sheet.LeftMargin, 0.25);
        sheet.setMargin(sheet.RightMargin, 0.25);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }
    
    public String exportExcelCollect(int level,FReportQLC beanTemp, FSeed seed, String excelFile) throws EException, 
                                FileNotFoundException, IOException {
        
        final String LOCATION = this.toString() + "-->exportExcelCollect()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + 
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
            
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);

        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        
        HSSFFont f11 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont f11b = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb11 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb13 = getFont(wb, "Times New Roman", 13, true);
        HSSFFont fb14 = getFont(wb, "Times New Roman", 14, true);
        
        CellStyle cs_normal = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_normal.setWrapText(true);
        
        CellStyle cs_bold_header = getStyle(wb, fb11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_bold_header.setWrapText(true);
        
        CellStyle cs_title_13 = getStyle(wb, fb13, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_title_14 = getStyle(wb, fb14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_right = getStyle(wb, f11, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        FReportQLC bean = new FReportQLC();
        HSSFRow row = null;      
        int i=0,stt = 1,dong = 0;
        
        row = sheet.getRow(0);
        String diaban = "";
        diaban = bean.ncrToString("UBND ") + beanTemp.getNameArea();    
        createCell(row, 0, diaban, wb, cs_title_13);
        
        row = sheet.getRow(4);
        String time = bean.ncrToString("Th&#225;ng: " + beanTemp.getKyBC() + "/" + beanTemp.getNamBC());
        createCell(row, 0, time, wb, cs_title_13);
        
        dong = 10;
        if (beanTemp.getStore().size()>0) {
            while (i<beanTemp.getStore().size()){
                bean = (FReportQLC)beanTemp.getStore().get(i);
                createCell(sheet.getRow(dong), 6, Formater.num2str(bean.getNktDauKy()), wb, cs_right);
                //createCell(sheet.getRow(dong), 7, Formater.num2str(bean.getNktDauKyLDong()), wb, cs_right);
                //createCell(sheet.getRow(dong), 8, Formater.num2str(bean.getNktDauKyYTe()), wb, cs_right);
                //createCell(sheet.getRow(dong), 9, Formater.num2str(bean.getNktDauKyGDuc()), wb, cs_right);
                
                dong++;
                createCell(sheet.getRow(dong), 6, Formater.num2str(bean.getNktTang()), wb, cs_right);
                //createCell(sheet.getRow(dong), 7, Formater.num2str(bean.getNktTangLDong()), wb, cs_right);
                //createCell(sheet.getRow(dong), 8, Formater.num2str(bean.getNktTangYTe()), wb, cs_right);
                //createCell(sheet.getRow(dong), 9, Formater.num2str(bean.getNktTangGDuc()), wb, cs_right);
                
                dong++;
                createCell(sheet.getRow(dong), 6, Formater.num2str(bean.getNktGiam()), wb, cs_right);
                //createCell(sheet.getRow(dong), 7, Formater.num2str(bean.getNktGiamLDong()), wb, cs_right);
                //createCell(sheet.getRow(dong), 8, Formater.num2str(bean.getNktGiamYTe()), wb, cs_right);
                //createCell(sheet.getRow(dong), 9, Formater.num2str(bean.getNktGiamGDuc()), wb, cs_right);
                
                dong++;
                createCell(sheet.getRow(dong), 6, Formater.num2str(bean.getNktCuoiKy()), wb, cs_right);
                //createCell(sheet.getRow(dong), 7, Formater.num2str(bean.getNktCuoiKyLDong()), wb, cs_right);
                //createCell(sheet.getRow(dong), 8, Formater.num2str(bean.getNktCuoiKyYTe()), wb, cs_right);
                //createCell(sheet.getRow(dong), 9, Formater.num2str(bean.getNktCuoiKyGDuc()), wb, cs_right);
                i++;
            }
        }
        
        dong = 17;
        i=0;
        
        if (beanTemp.getStore1().size()>0) {
            while (i<beanTemp.getStore1().size()){
                bean = (FReportQLC)beanTemp.getStore1().get(i);
                int cot = 7;
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcDauKy()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcDauKyLDong()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcDauKyYTe()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcDauKyGDuc()), wb, cs_right);
                
                dong++;
                cot=7;
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcTang()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcTangLDong()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcTangYTe()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcTangGDuc()), wb, cs_right);
                
                dong++;
                cot=7;
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcGiam()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcGiamLDong()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcGiamYTe()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcGiamGDuc()), wb, cs_right);
                
                dong++;
                cot=7;
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcCuoiKy()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcCuoiKyLDong()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcCuoiKyYTe()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getQlcCuoiKyGDuc()), wb, cs_right);
                i++;
            }
        }
        
        dong = (level==3)?26:27;
        i=0;
        
        if (beanTemp.getStore2().size()>0) {
            while (i<beanTemp.getStore2().size()){
                bean = (FReportQLC)beanTemp.getStore2().get(i);
                int cot = 2;               
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotal()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalThang()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalLuyKe()), wb, cs_right);
                
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoi()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoiThang()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoiLuyKe()), wb, cs_right);
                             
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTe()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTeThang()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTeLuyKe()), wb, cs_right);
                
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDuc()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDucThang()), wb, cs_right);
                createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDucLuyKe()), wb, cs_right);
                dong++;
                i++;
            }
        }
        
        dong = level==3?30:31;
        i=0;
        if (beanTemp.getStore3().size()>0) {
          while (i<beanTemp.getStore3().size()){
              bean = (FReportQLC)beanTemp.getStore3().get(i);
              int cot = 2;              
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotal()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalThang()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalLuyKe()), wb, cs_right);
              
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoi()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoiThang()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalXaHoiLuyKe()), wb, cs_right);
                           
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTe()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTeThang()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalYTeLuyKe()), wb, cs_right);
              
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDuc()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDucThang()), wb, cs_right);
              createCell(sheet.getRow(dong), cot++, Formater.num2str(bean.getTotalGiaoDucLuyKe()), wb, cs_right);
              dong++;
              i++;
          }
        }  
            
        sheet.setHorizontallyCenter(true);
        sheet.setMargin(sheet.TopMargin, 0);
        sheet.setMargin(sheet.BottomMargin, 2.5);
        sheet.setMargin(sheet.LeftMargin, 0.25);
        sheet.setMargin(sheet.RightMargin, 0.25);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }
    
    public FBeans getDataDetail(Connection cnn, int tinh_id, int level, String ky_bc, String type) throws EException, SQLException {
        final String LOCATION =  this.toString() + "-->getDataDetail()";        
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        String sql = "";
        int pos = ky_bc.indexOf("/");
        String dau_nam = "01/" + ky_bc.substring(pos+1);
        if ("1".equals(type)) { // Chi tiet 1
            if (level==3)   sql = SQL_SELECT_REPORT_QLC_DETAIL1_PX;
            else            sql = SQL_SELECT_REPORT_QLC_DETAIL1_QH;
        } else {                // Chi tiet 2
            if (level==3)   sql = SQL_SELECT_REPORT_QLC_DETAIL2_PX;
            else            sql = SQL_SELECT_REPORT_QLC_DETAIL2_QH;
        }
        
        FBeans beans = new FBeans();
        FReportQLC bean = new FReportQLC();
        String str_tinhid = "";
        try {
            if (level==3) { // Phuong-Xa
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
                prpstm.setInt(2, tinh_id);
                prpstm.setString(3, ky_bc);
                prpstm.setString(4, ky_bc);                
            } else {        // Quan-Huyen
                str_tinhid = "(" + tinh_id + ")";
                prpstm = prepareStatement(cnn, SQL_SELECT_STR_TINH_ID, null);
                prpstm.setInt(1, tinh_id);
                rs = prpstm.executeQuery();
                while (rs!=null && rs.next()) {
                    str_tinhid = rs.getString(1);
                }
                prpstm = prepareStatement(cnn, sql.replaceAll("#", str_tinhid), null);
                int i = 1;
                if ("2".equals(type)) { // Chi tiet 2
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);
                    prpstm.setInt(i++, tinh_id);
                } else {                // Chi tiet 1
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);                
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);                
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);
                                        
                    prpstm.setString(i++, ky_bc);                    
                    prpstm.setString(i++, ky_bc);                    
                    prpstm.setString(i++, ky_bc);
                    prpstm.setString(i++, ky_bc);
                    prpstm.setInt(i++, tinh_id);
                }
            }
            
            rs = prpstm.executeQuery();
            if ("1".equals(type)) { // Chi tiet 1
                if (level==3){
                    while (rs!= null && rs.next()) {
                        int i = 1;
                        bean = new FReportQLC();
                        bean.setHoTen(rs.getString(i++));
                        bean.setMaSo(rs.getString(i++));
                        bean.setNamSinh(rs.getString(i++));
                        bean.setDiaChi(rs.getString(i++));
                        bean.setDangTat(rs.getString(i++));
                        bean.setMucDoKTat(rs.getString(i++));
                        bean.setHoanCanhKTe(rs.getString(i++));
                        bean.setHoTenChuHo(rs.getString(i++));
                        bean.setTenNgQLy(rs.getString(i++));
                        beans.add(bean);
                    }   
                } else {
                    while (rs!= null && rs.next()){             
                        int i = 1;
                        bean = new FReportQLC();
                        bean.setDiaBan(rs.getString(i++));
                        bean.setTGia(rs.getInt(i++));
                        bean.setTGiaLaoDong(rs.getInt(i++));
                        bean.setTGiaYTe(rs.getInt(i++));
                        bean.setTGiaGiaoDuc(rs.getInt(i++));
                        bean.setLKe(rs.getInt(i++));
                        bean.setLKeLaoDong(rs.getInt(i++));
                        bean.setLKeYTe(rs.getInt(i++));
                        bean.setLKeGiaoDuc(rs.getInt(i++));
                        beans.add(bean);
                    }
                }                
            } else {    // Chi tiet 2
                if (level==3) {
                    while (rs!= null && rs.next()){      
                        int i = 1;
                        bean = new FReportQLC();
                        bean.setHoTen(rs.getString(i++));
                        bean.setMaSo(rs.getString(i++));
                        bean.setNamSinh(rs.getString(i++));
                        bean.setDiaChi(rs.getString(i++));
                        bean.setNgNhan1(rs.getInt(i++));
                        bean.setNgNhan2(rs.getInt(i++));
                        bean.setNgNhan3(rs.getInt(i++));
                        bean.setNgNhan4(rs.getInt(i++));
                        bean.setTenNgQLy(rs.getString(i++));
                        beans.add(bean);
                    }
                } else {
                    while (rs!= null && rs.next()){             
                        int i = 1;
                        bean = new FReportQLC();
                        bean.setDiaBan(rs.getString(i++));
                        bean.setKThuc(rs.getInt(i++));
                        bean.setNgNhan1(rs.getInt(i++));
                        bean.setNgNhan2(rs.getInt(i++));
                        bean.setNgNhan3(rs.getInt(i++));
                        bean.setNgNhan4(rs.getInt(i++));
                        beans.add(bean);
                    } 
                }
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataCollect(Connection cnn, int tinh_id, String ky_bc, String loai_dl, String type) throws EException, SQLException {
        final String LOCATION =  this.toString() + "-->getDataCollect()";        
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        CallableStatement cstm = null;        
        int i;
        String sql="";
        cstm = cnn.prepareCall("{call report_collect_qlth(?, ?, ?)}");
        cstm.setInt(1, tinh_id);
        cstm.setString(2, ky_bc);
        cstm.setString(3, loai_dl);
        cstm.execute();
        FBeans beans = new FBeans();
        FReportQLC bean = new FReportQLC();
        try { 
            if ("ctkn".equals(loai_dl)) {
                if ("QLTH".equals(type))          sql = SQL_SELECT_REPORT_QLC_COLLECT_CTKN_QLTH;
                else if ("NC_QLTH".equals(type))  sql = SQL_SELECT_REPORT_QLC_COLLECT_CTKN_NCQLTH;
            } else {
                sql = SQL_SELECT_REPORT_QLC_COLLECT_NKT;
            }
            
            prpstm = prepareStatement(cnn, sql, null);
            if (!"ctkn".equals(loai_dl)) {
              prpstm.setString(1, loai_dl);  
            }
            rs = prpstm.executeQuery();            
            if ("nkt".equals(loai_dl)) {
                while (rs!= null && rs.next()){             
                    i=1;
                    bean = new FReportQLC();                
                    bean.setNktDauKy(rs.getString(i++));
                    bean.setNktDauKyLDong(rs.getString(i++));
                    bean.setNktDauKyYTe(rs.getString(i++));
                    bean.setNktDauKyGDuc(rs.getString(i++));
                    
                    bean.setNktTang(rs.getInt(i++));
                    bean.setNktTangLDong(rs.getInt(i++));
                    bean.setNktTangYTe(rs.getInt(i++));
                    bean.setNktTangGDuc(rs.getInt(i++));
                    
                    bean.setNktGiam(rs.getInt(i++));
                    bean.setNktGiamLDong(rs.getInt(i++));
                    bean.setNktGiamYTe(rs.getInt(i++));
                    bean.setNktGiamGDuc(rs.getInt(i++));
                    
                    bean.setNktCuoiKy(rs.getInt(i++));
                    bean.setNktCuoiKyLDong(rs.getInt(i++));
                    bean.setNktCuoiKyYTe(rs.getInt(i++));
                    bean.setNktCuoiKyGDuc(rs.getInt(i++));
                    beans.add(bean);
                }    
            } else if ("qlc".equals(loai_dl)) {
                while (rs!= null && rs.next()){             
                    i=1;
                    bean = new FReportQLC();                
                    bean.setQlcDauKy(rs.getInt(i++));
                    bean.setQlcDauKyLDong(rs.getInt(i++));
                    bean.setQlcDauKyYTe(rs.getInt(i++));
                    bean.setQlcDauKyGDuc(rs.getInt(i++));
                    
                    bean.setQlcTang(rs.getInt(i++));
                    bean.setQlcTangLDong(rs.getInt(i++));
                    bean.setQlcTangYTe(rs.getInt(i++));
                    bean.setQlcTangGDuc(rs.getInt(i++));
                    
                    bean.setQlcGiam(rs.getInt(i++));
                    bean.setQlcGiamLDong(rs.getInt(i++));
                    bean.setQlcGiamYTe(rs.getInt(i++));
                    bean.setQlcGiamGDuc(rs.getInt(i++));
                    
                    bean.setQlcCuoiKy(rs.getInt(i++));
                    bean.setQlcCuoiKyLDong(rs.getInt(i++));
                    bean.setQlcCuoiKyYTe(rs.getInt(i++));
                    bean.setQlcCuoiKyGDuc(rs.getInt(i++));
                    beans.add(bean);
                }     
            } else {
                while (rs!=null && rs.next()) {
                    i=1;
                    bean = new FReportQLC();
                    bean.setTotal(rs.getInt(i++));
                    bean.setTotalThang(rs.getInt(i++));
                    bean.setTotalLuyKe(rs.getInt(i++));
                    
                    bean.setTotalXaHoi(rs.getInt(i++));
                    bean.setTotalXaHoiThang(rs.getInt(i++));
                    bean.setTotalXaHoiLuyKe(rs.getInt(i++));
                    
                    bean.setTotalYTe(rs.getInt(i++));
                    bean.setTotalYTeThang(rs.getInt(i++));
                    bean.setTotalYTeLuyKe(rs.getInt(i++));
                    
                    bean.setTotalGiaoDuc(rs.getInt(i++));
                    bean.setTotalGiaoDucThang(rs.getInt(i++));
                    bean.setTotalGiaoDucLuyKe(rs.getInt(i++));
                    beans.add(bean);
                }
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
}
