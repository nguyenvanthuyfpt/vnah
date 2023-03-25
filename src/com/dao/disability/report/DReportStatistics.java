package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportStatistics;
import com.form.disability.report.FReportTotal;

import com.inf.disability.IKeyDisability;

import com.lib.AppConfigs;

import com.util.Constant;
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


public class DReportStatistics extends FExportExcel{
    public String ReportExcel(FReportStatistics beanTemp, FSeed seed, 
                              String excelFile) throws EException, 
                                                       FileNotFoundException, 
                                                       IOException {
        final String LOCATION = 
            this.toString() + "~>ReportExcelAllReportAddTime()";
        FReportTotal bean = (FReportTotal)seed;
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        
        HSSFFont f11 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont fb11 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb12 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb10 = getFont(wb, "Times New Roman", 10, true);
        HSSFFont fb13 = getFont(wb, "Times New Roman", 13, true);
        
        CellStyle cs_normal = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_none = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_bold = getStyle(wb, fb12, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_bold_center = getStyle(wb, fb12, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);

        CellStyle cs_center = getStyle(wb, f11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_bold_header = getStyle(wb, fb11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_header = getStyle(wb, f11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_title = getStyle(wb, fb13, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_right = getStyle(wb, f11, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_bold_header.setWrapText(true);
        cs_normal.setWrapText(true);
        
        String tuyen_bcao = bean.ncrToString("Tuy&#7871;n: ") + 
                            bean.getTinhName().substring(bean.getTinhName().lastIndexOf("-")+1, bean.getTinhName().length()).trim(); 
        String thoi_gian = bean.ncrToString("Th&#7901;i gian: t&#7915; th&#225;ng ") + "" + 
                           bean.getFromMonth() + "/" + bean.getFromYear() +
                           bean.ncrToString(" &#273;&#7871;n th&#225;ng ") + 
                           bean.getToMonth() + "/" + bean.getToYear();
        
        String donvi = "",diaban = "";
        if(bean.getFieldType().equals("1")) {      
            donvi = bean.ncrToString(Constant.report_title_sgddt);
        } else if (bean.getFieldType().equals("2")) {
            donvi = bean.ncrToString(Constant.report_title_syte);
        } else if (bean.getFieldType().equals("3")) {
            donvi = bean.ncrToString(Constant.report_title_sldtbxh);
        }
        
        diaban = bean.ncrToString(Constant.report_title_diaban);
        createCell(sheet.getRow(0), 0, donvi.toUpperCase(), wb, cs_bold_header);        
        createCell(sheet.getRow(1), 4, tuyen_bcao, wb, cs_bold_header); 
        createCell(sheet.getRow(1), 0, diaban.toUpperCase(), wb, cs_bold_header);
        createCell(sheet.getRow(2), 4, thoi_gian, wb, cs_bold_header);  
        
        int stt = 1,dong = 7;
        FReportStatistics beanG = null;
        HSSFRow row = null;
        
        if(IKeyDisability.FILE_REPORT_STATISTICS_GIAODUC.equals(excelFile)){            
            int i=0;            
            while (i<beanTemp.getStore().size()){
                int cot = 0;                  
                beanG = (FReportStatistics)beanTemp.getStore().get(i);                
                row = sheet.createRow(dong);
                createCell(row, (cot++), stt, wb, cs_right);
                createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getNgaySinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getMaNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDiaBan(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDangTat(), wb, cs_normal);
                createCell(row, (cot++), beanG.getMucDo(), wb, cs_normal);
                createCell(row, (cot++), beanG.getQuanLyCa(), wb, cs_normal);                   
                // Giao duc
                createCell(row, (cot++), beanG.getGducLopHoaNhap(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducLopChuyenBiet(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducLopBanChuyenBiet(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getGducHTroHocPhi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducHTroHocPhuDao(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducHTroDCuHocTap(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducHTroQAo(), wb, cs_normal);
                createCell(row, (cot++), beanG.getGducHTroKhac(), wb, cs_normal);
                // Bien dong
                createCell(row, (cot++), beanG.getBdongPSinhMoi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongPSinhChuyenDen(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongDaHoaNhap(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongBoCuoc(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChuyenDi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongXoa(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChet(), wb, cs_normal);
                dong++;
                stt++;
                i++;    
            }
        } else if (IKeyDisability.FILE_REPORT_STATISTICS_XAHOI.equals(excelFile)) {
            int i=0;            
            while (i<beanTemp.getStore().size()){
                int cot = 0;                  
                beanG = (FReportStatistics)beanTemp.getStore().get(i);                
                row = sheet.createRow(dong);
                createCell(row, (cot++), stt, wb, cs_right);
                createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getNgaySinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getMaNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDiaBan(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDangTat(), wb, cs_normal);
                createCell(row, (cot++), beanG.getMucDo(), wb, cs_normal);
                createCell(row, (cot++), beanG.getQuanLyCa(), wb, cs_normal);   
                // Xa hoi
                createCell(row, (cot++), beanG.getXhoiTCapThuongXuyen(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiTCapDotXuat(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiHTroNhaO(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getXhoiCThienVSNS(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiDTaoNghe(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiViecLam(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiTuDoanh(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiVayVon(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiTGiaTCXH(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiTGiaCSTKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getXhoiCSocCSBTXH(), wb, cs_normal);
                // Bien dong
                createCell(row, (cot++), beanG.getBdongPSinhMoi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongPSinhChuyenDen(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongDaHoaNhap(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongBoCuoc(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChuyenDi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongXoa(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChet(), wb, cs_normal); 
                dong++;
                stt++;
                i++;    
            }            
        } else if (IKeyDisability.FILE_REPORT_STATISTICS_YTE.equals(excelFile)) {
            int i=0;            
            while (i<beanTemp.getStore().size()){
                int cot = 0;                  
                beanG = (FReportStatistics)beanTemp.getStore().get(i);                
                row = sheet.createRow(dong);
                createCell(row, (cot++), stt, wb, cs_right);
                createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getNgaySinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                createCell(row, (cot++), beanG.getMaNKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDiaBan(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getDangTat(), wb, cs_normal);
                createCell(row, (cot++), beanG.getMucDo(), wb, cs_normal);
                createCell(row, (cot++), beanG.getQuanLyCa(), wb, cs_normal);                
                // Y te
                createCell(row, (cot++), beanG.getYteKhamXDKT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getYteTheBHYT(), wb, cs_normal);
                createCell(row, (cot++), beanG.getYteTapPHCN(), wb, cs_normal);                
                createCell(row, (cot++), beanG.getYteKhamBenh(), wb, cs_normal);
                createCell(row, (cot++), beanG.getYteDCuPHCN(), wb, cs_normal);
                createCell(row, (cot++), beanG.getYtePThuatChinhHinh(), wb, cs_normal);
                createCell(row, (cot++), beanG.getYteKhac(), wb, cs_normal);
                // Bien dong
                createCell(row, (cot++), beanG.getBdongPSinhMoi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongPSinhChuyenDen(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongDaHoaNhap(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongBoCuoc(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChuyenDi(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongXoa(), wb, cs_normal);
                createCell(row, (cot++), beanG.getBdongChet(), wb, cs_normal); 
                dong++;
                stt++;
                i++;    
            }                
        }
        
        String[] arr_char = {"J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB"};        
        String formula = "COUNTIF(@8:@#,\"x\")";        
        int cot = 9;
        int i = 0;
        row = sheet.createRow(dong);        
        if(IKeyDisability.FILE_REPORT_STATISTICS_GIAODUC.equals(excelFile)){
            createRegion(wb, row, dong, dong, 0, 8, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<25){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                createCellFormula(row, cot++, formula, wb, cs_normal);
                formula = "COUNTIF(@8:@#,\"x\")";
            }
        } else if (IKeyDisability.FILE_REPORT_STATISTICS_XAHOI.equals(excelFile)){
            createRegion(wb, row, dong, dong, 0, 8, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);
            // Quan ly ca
            formula = formula.replaceAll("#", "" + dong + "");
            formula = formula.replaceAll("@", arr_char[i++]);
            createCellFormula(row, cot++, formula, wb, cs_normal);
            
            formula = "COUNTIF(@8:@#,\"x\")";
            //createCell(row, cot++, "", wb, cs_normal);
            //createCell(row, cot++, "", wb, cs_normal);
            //i = i+2;
            while (cot<28){
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                createCellFormula(row, cot++, formula, wb, cs_normal);
                formula = "COUNTIF(@9:@#,\"x\")"; 
            }
        } else {
            createRegion(wb, row, dong, dong, 0, 8, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);
            while (cot<24){               
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                createCellFormula(row, cot++, formula, wb, cs_normal);
                formula = "COUNTIF(@8:@#,\"x\")"; 
            }
        }
        
        String total_nkt = Formater.num2str(beanTemp.getTotal());        
        String current_date = bean.dateToString(bean.getCurrentDate());
        String date[] = current_date.split("/");
        String date_time = bean.ncrToString(IKeyDisability.REPORT_FOOTER_POSITION_3).replace("{0}", date[0]).replace("{1}", date[1]).replace("{2}", date[2]);        
        
        row = sheet.createRow(dong+2);
        createRegion(wb, row, dong+2, dong+2, 0, 4, cs_bold, bean.ncrToString("T&#7893;ng s&#7889; ng&#432;&#7901;i trong danh s&#225;ch l&#224;: ") + total_nkt, 0, 0);
        
        row = sheet.createRow(dong+4);
        createRegion(wb, row, dong+4, dong+4, 0, 4, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_CREATED), 0, 0);
        createRegion(wb, row, dong+4, dong+4, 10, 14, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_DATETIME), 0, 0);
                
        row = sheet.createRow(dong+5);                        
        createRegion(wb, row, dong+5, dong+5, 10, 14, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_LEADER), 0, 0);
        
        row = sheet.createRow(dong+10);        
        createRegion(wb, row, dong+10, dong+10, 10, 14, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_SIGNER), 0, 0);
        
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

    public FBeans getData(Connection cnn, int tinh_id, String field, String from, String to, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataToExport()";
        CallableStatement state = cnn.prepareCall("{call report_statistics_nkt(?,?,?,?,?)}");
        state.setInt(1, tinh_id);
        state.setString(2, field);
        state.setString(3, from);
        state.setString(4, to);
        state.setString(5, String.valueOf(qlc));
        state.execute();
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportStatistics bean = new FReportStatistics();
        try {
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_STATISTICS_NKT, null);
            rs = prpstm.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            while (rs!= null && rs.next()){
                bean = new FReportStatistics();
                bean.setTinhId(rs.getString("tinh_id"));
                bean.setDiaBan(rs.getString("diaban"));
                bean.setMaNKT(rs.getString("ma_nkt"));
                bean.setHotenNKT(rs.getString("hoten_nkt"));
                bean.setNgaySinh(rs.getString("ngaysinh"));
                bean.setGioiTinh(rs.getString("gioitinh"));                
                bean.setDiaChi(rs.getString("diachi"));
                bean.setDangTat(rs.getString("dangtat"));
                bean.setMucDo(rs.getString("mucdo"));
                bean.setQuanLyCa(rs.getString("quanlyca"));
                
                // Giao duc
                bean.setGducLopHoaNhap(rs.getString("gduclophoanhap"));
                bean.setGducLopChuyenBiet(rs.getString("gduclopchuyenbiet"));
                bean.setGducLopBanChuyenBiet(rs.getString("gduclopbanchuyenbiet"));                  
                bean.setGducHTroHocPhi(rs.getString("gduchtrohocphi"));
                bean.setGducHTroHocPhuDao(rs.getString("gduchtrohocphudao"));
                bean.setGducHTroDCuHocTap(rs.getString("gduchtrodcuhoctap"));
                bean.setGducHTroQAo(rs.getString("gduchtroqao"));
                bean.setGducHTroKhac(rs.getString("gduchtrokhac"));
                
                // Xa hoi
                bean.setXhoiTCapThuongXuyen(rs.getString("xhoitcapthuongxuyen"));
                bean.setXhoiTCapDotXuat(rs.getString("xhoitcapdotxuat"));
                bean.setXhoiHTroNhaO(rs.getString("xhoihtronhao"));
                bean.setXhoiCThienVSNS(rs.getString("xhoicthienvsns"));  
                bean.setXhoiDTaoNghe(rs.getString("xhoidtaonghe"));
                bean.setXhoiViecLam(rs.getString("xhoivieclam"));                
                bean.setXhoiTuDoanh(rs.getString("xhoitudoanh"));  
                bean.setXhoiVayVon(rs.getString("xhoivayvon"));   
                bean.setXhoiTGiaTCXH(rs.getString("xhoitgiatcxh"));
                bean.setXhoiTGiaCSTKT(rs.getString("xhoitgiacstkt"));
                bean.setXhoiCSocCSBTXH(rs.getString("xhoicsoccsbtxh"));
                
                // Y te
                bean.setYteKhamXDKT(rs.getString("ytekhamxdkt"));
                bean.setYteTheBHYT(rs.getString("ytethebhyt"));
                bean.setYteTapPHCN(rs.getString("ytetapphcn"));  
                bean.setYteKhamBenh(rs.getString("ytekhambenh"));
                bean.setYteDCuPHCN(rs.getString("ytedcuphcn"));
                bean.setYtePThuatChinhHinh(rs.getString("ytepthuatchinhhinh"));
                bean.setYteKhac(rs.getString("ytekhac"));
                
                // Bien dong
                bean.setBdongPSinhMoi(rs.getString("bdongpsinhmoi"));
                bean.setBdongPSinhChuyenDen(rs.getString("bdongpsinhchuyenden"));   
                bean.setBdongDaHoaNhap(rs.getString("bdongdahoanhap"));
                bean.setBdongBoCuoc(rs.getString("bdongbocuoc"));
                bean.setBdongChuyenDi(rs.getString("bdongchuyendi"));   
                bean.setBdongXoa(rs.getString("bdongxoa"));
                bean.setBdongChet(rs.getString("bdongchet"));
                
                beans.add(bean);                
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
