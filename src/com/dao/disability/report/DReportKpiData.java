package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FDisability;
import com.form.disability.report.FReportInforNKT;
import com.form.disability.search.FSearch;

import com.inf.disability.IConstantsDisability;

import com.lib.AppConfigs;

import com.util.Constant;
import com.util.Utilities;

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
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportKpiData extends FExportExcel {
    
    public String ReportExcel(FSearch beanTemp, FSeed seed, String excelFile) throws EException, 
                                                       FileNotFoundException, 
                                                       IOException {
        final String LOCATION = this.toString() + "~>ReportExcelAllReportAddTime()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        
        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;           
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {                    
                    fis.close();                    
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        
        sheet = wb.getSheetAt(0);
        // sheet.setAutobreaks(true);
        // sheet.createFreezePane(1, 2);
        HSSFFont fontArialBold14 = getFont(wb, "Times New Roman", 11, false);
        
        HSSFFont font11 = getFont(wb, "Times New Roman", 11, false);
        CellStyle style = 
            getStyle(wb, font11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle csLeft = getStyle(wb, fontArialBold14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);        
        csLeft.setWrapText(true);
        
        CellStyle csRight = getStyle(wb, fontArialBold14, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);        
        csLeft.setWrapText(false);
        
        CellStyle csLeftNone = getStyle(wb, fontArialBold14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);        
        csLeftNone.setWrapText(false);

        CellStyle csCenter = getStyle(wb, fontArialBold14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        csCenter.setWrapText(false);
        
        CellStyle csCenterWrap = getStyle(wb, fontArialBold14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);        
        csCenterWrap.setWrapText(true);
        
        HSSFFont font11b= getFont(wb, "Times New Roman", 11, true);
        CellStyle styleHeader = 
            getStyle(wb, font11b, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        styleHeader.setWrapText(false);
        
        HSSFRow row = null;        
        int dong = 3;
        int dataType = beanTemp.getDataType();
        
        if (dataType==Constant.KPI_DATA_DIS_COMMUNE) {
            dong = 4;    
        }
        int stt=1;
        
        FSearch beanC = null;       
        FDisability beanD = null;
        
        // Export-Date
        row = sheet.createRow(0);
        createCell(row, 0, beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") + Utilities.getStringDateFormat("dd/MM/yyyy"), wb, csLeftNone);
               
        // Data 
        if (dataType!=Constant.KPI_DATA_LIST_PERSON_HOURS
            && dataType!=Constant.KPI_DATA_LIST_COUNT
            && dataType!=Constant.KPI_DATA_LIST_DIS
            && dataType!=Constant.KPI_DATA_DIS_COMMUNE) {
            row = sheet.createRow(2);  
        }
        
        if (dataType==Constant.KPI_DATA_VALUE) {
            int[] fields = {0,1,2,3,4,5,6,7,8,9,10};
            beanTemp.setFields(fields);
            for (int h = 0; h < beanTemp.getFields().length; h++) {
                createCell(row, h, beanTemp.ncrToString(IConstantsDisability.HEADER_VALUE[beanTemp.getFields()[h]]), wb, styleHeader);
            }
        } else if (dataType==Constant.KPI_DATA_DIS) {
            for (int h = 0; h < beanTemp.getFields().length; h++) {
                createCell(row, (h+1), beanTemp.ncrToString(IConstantsDisability.HEADER_DIS[beanTemp.getFields()[h]]), wb, styleHeader);
            }            
        } else if (dataType==Constant.KPI_DATA_PERSON) {
            int[] fields = {0,1,2,3,4,5,6,7,8,9};
            beanTemp.setFields(fields);
            for (int h = 0; h < beanTemp.getFields().length; h++) {
                createCell(row, h, beanTemp.ncrToString(IConstantsDisability.HEADER_PER[beanTemp.getFields()[h]]), wb, styleHeader);
            }
        } else if (dataType==Constant.KPI_DATA_EVENT) {
            int[] fields = {0,1,2,3,4,5,6};            
            beanTemp.setFields(fields);
            for (int h = 0; h < beanTemp.getFields().length; h++) {
                createCell(row, h, beanTemp.ncrToString(IConstantsDisability.HEADER_EVENT[beanTemp.getFields()[h]]), wb, styleHeader);
            }
        } 
        
        String arrSex[] = { "N&#7919;", "Nam"};        
        String arrSelect[] = {"Kh&#244;ng","C&#243;"};
        String arrPeriod[] = {"Th&#225;ng","Qu&#253;","N&#259;m"};
        String arrDiaDiem[] = {"T&#7841;i nh&#224;","T&#7841;i BV T&#7881;nh/Huy&#7879;n","T&#7841;i tr&#7841;m Y t&#7871; X&#227;/Ph&#432;&#7901;ng","Kh&#225;c"};
        
        String period = "";
        int target = 0;
        int actual = 0;
        int curPerId = 0;
        boolean chkRow = false;

        if (dataType == Constant.KPI_DATA_LIST_COUNT_LATE) {  // List CountDown
            for (int i = 0; i < beanTemp.getStore().size(); i++) {
                int cot = 0;
                beanD = (FDisability)beanTemp.getStore().get(i);
                row = sheet.createRow(dong+i);

                createCell(row, cot++, stt+i, wb, csLeft);
                createCell(row, cot++, beanD.getTinhName(), wb, csLeft);
                createCell(row, cot++, beanD.getNkt(), wb, csLeft);
                createCell(row, cot++, beanD.getMa(), wb, csLeft);
                createCell(row, cot++, beanD.getNgaySinh(), wb, csLeft);
                createCell(row, cot++, beanD.getGioiTinh(), wb, csLeft);
                createCell(row, cot++, beanD.ncrToString(arrDiaDiem[beanD.getTkDiaDiem()-1]),wb, csLeft);
                createCell(row, cot++, beanD.getSoNha(),wb, csLeft);
                createCell(row, cot++, beanD.getPhoneNumber(),wb, csLeft);
                createCell(row, cot++, beanD.getLastDateSupport(), wb, csLeft);
                //createCell(row, cot++, beanD.getThoiDiemKT(), wb, csLeft);
                //createCell(row, cot++, beanD.getDay(), wb, csLeft);
            }
        } else if (dataType == Constant.KPI_DATA_DIS_COMMUNE) {
            for (int i = 0; i < beanTemp.getStore().size(); i++) {
                int cot = 0;
                beanC = (FSearch)beanTemp.getStore().get(i);
                row = sheet.createRow(dong+i);
                
                createCell(row, cot++, stt+i, wb, csLeft);
                createCell(row, cot++, beanC.getNkt(), wb, csLeft);
                createCell(row, cot++, beanC.getMaSo(), wb, csLeft);
                createCell(row, cot++, beanC.getSoNha(), wb, csLeft);
                createCell(row, cot++, beanC.getHuyenName(), wb, csLeft);
                createCell(row, cot++, beanC.getNamSinh(), wb, csLeft);
                createCell(row, cot++, beanC.getGioiTinh(), wb, csLeft);
                createCell(row, cot++, beanC.getPhoneNumber(), wb, csLeft);
                createCell(row, cot++, beanC.getDTatName(), wb, csLeft);
                createCell(row, cot++, beanC.getDTatTinhTrang(), wb, csLeft);
                createCell(row, cot++, beanC.getDTatMucDo(), wb, csLeft);
                
                createCell(row, cot++, (beanC.getCommCreateDate()!=null) ? beanC.getCommCreateDate() : "", wb, csCenter);
                if (beanC.getCommCreateDate()!=null) {
                    createCell(row, cot++, (beanC.getCommP1()!=null && beanC.getCommP1().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP2()!=null && beanC.getCommP2().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP3()!=null && beanC.getCommP3().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP4()!=null && beanC.getCommP4().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
//                    createCell(row, cot++, (beanC.getCommP5()!=null && beanC.getCommP5().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP6()!=null && beanC.getCommP6().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP7()!=null && beanC.getCommP7().equals("1")) ? beanC.ncrToString("C&#243;") : beanC.ncrToString("Kh&#244;ng"), wb, csCenter);
                    createCell(row, cot++, (beanC.getCommP8()!=null) ? beanC.getCommP8() : "", wb, csCenter);    
                } else {
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);
                    createCell(row, cot++, "", wb, csCenter);                    
                }
                 
                createCell(row, cot++, beanC.getLastupdate(), wb, csLeft);
                createCell(row, cot++, beanC.getPhcnCanThiep(), wb, csLeft);
                createCell(row, cot++, beanC.getPhcnDungCu(), wb, csLeft);
                createCell(row, cot++, beanC.getPhcnDungCuKhac(), wb, csLeft);
                createCell(row, cot++, beanC.getHtNhaO(), wb, csLeft);
                createCell(row, cot++, beanC.getHtCTVS(), wb, csLeft);
                createCell(row, cot++, beanC.getHtNgay(), wb, csLeft);
                createCell(row, cot++, beanC.getTrangthai(), wb, csLeft);
                createCell(row, cot++, beanC.getNgayDongHS(), wb, csLeft);
                createCell(row, cot++, beanC.getLydoDongHS(), wb, csLeft);
                createCell(row, cot++, "", wb, csLeft);
                createCell(row, cot++, beanC.getNguoiDongHS(), wb, csLeft);
                createCell(row, cot++, beanC.getDuAnHT(), wb, csLeft);
            }
        } else {
            for (int i = 0; i < beanTemp.getStore().size(); i++) {
                int cot = 0;
                beanC = (FSearch)beanTemp.getStore().get(i);
                row = sheet.createRow(dong+i);
                if (dataType==Constant.KPI_DATA_VALUE) {
                    if (beanC.getPeriodType()==0) {
                        period = beanC.getMonth() + "/" + beanC.getYear();
                        target = beanC.getTarget();
                    } else if (beanC.getPeriodType()==1) {
                        period = "Q" + beanC.getQuarter() + "/" + beanC.getYear();
                        target = beanC.getTarget();
                    } else {
                        period = "" + beanC.getYear();
                        target = beanC.getTarget();
                    }

                    createCell(row, cot++, stt+i, wb, csLeft);
                    createCell(row, cot++, beanC.getLocationName(), wb, csLeft);
                    createCell(row, cot++, beanC.getCreateDate(), wb, csCenter);
                    createCell(row, cot++, beanC.getUserFullName(), wb, csLeft);
                    createCell(row, cot++, beanC.getObjDesc(), wb, csLeft);
                    createCell(row, cot++, beanC.getIndDesc(), wb, csLeft);
                    createCell(row, cot++, beanTemp.ncrToString(arrPeriod[beanC.getPeriodType()]), wb, csLeft);
                    createCell(row, cot++, period, wb, csCenter);
                    createCell(row, cot++, target, wb, csRight);
                    createCell(row, cot++, beanC.getActual(), wb, csRight);
                    createCell(row, cot++, beanC.getNote(), wb, csLeft);
                } else if (dataType==Constant.KPI_DATA_DIS) {
                    beanC = (FSearch)beanTemp.getStore().get(i);
                    row = sheet.createRow(dong);
                    createCell(row, (0), i+1, wb, style);

                    String columValues[] =
                    {
                        //1
                        beanC.getTinhName(),  //0
                        beanC.getLastupdate(),
                        beanC.getMa(),
                        beanC.getNkt(),
                        beanC.getMaSo(),      //4
                        //2
                        beanC.getNgaySinh(),    //5
                        beanC.getPhoneNumber(), //6
                        beanC.getDanTocName(),  //7
                        beanC.getGioiTinh(),    //8
                        beanC.getDaCam(),       //9
                        //3
                        beanC.getNgheNghiep(),  //10
                        beanC.getTrangthai(),   //11
                        beanC.getDuAnHT(),      //12
                        
                        //4  NCS
                        beanC.getNcsTen(),          //13
                        beanC.getNcsNamSinh(),      //14
                        beanC.getNcsQuanHeName(),   //15
                        beanC.getNcsDienThoai(),    //16
                        beanC.getNcsGioiTinhName(), //17

                        //5 Phan-Loai
                        beanC.getDTatName(),          //18
                        beanC.getDTatNgayCapNhat(),   //19
                        beanC.getDTatNgayTaiKham(),   //20
                        beanC.getDTatDiaDiemKham(),   //21
                        beanC.getDTatTDiemKT(),       //22
                        beanC.getDTatTinhTrang(),     //23
                        beanC.getDTatNguyenNhan(),    //24
                        beanC.getDTatMucDo(),         //25

                        // 6 NhuCau-HoTro
                        beanC.getNcauName(),          //26
                        beanC.getNcauDungCuKhac(),    //27
                        beanC.getHtroName(),          //28
                        beanC.getHtroTgianNhan(),     //29
                        beanC.getNguonHoTro(),        //30
                        beanC.getKnChiTraName(),      //31
                        beanC.getTheBhyteName(),      //32
                        beanC.getSdTheName(),         //33
                        beanC.getSdThePhcnName(),     //34    
                        beanC.getMtieuGdinh(),        //35
                        beanC.getMtieuDtri(),         //36
                        beanC.getCtVltl(),            //37
                        beanC.getCtHdtl(),            //38
                        beanC.getCtAntl(),            //39
                        beanC.getMdoPtdl(),           //40
                        beanC.getMdoHlong(),          //41
                        beanC.getPhcnDungCuKhac(),    //42 
                        beanC.getMoTaDCTG(),          //43                      
                        String.valueOf(beanC.getNumHomeVisit())
                    };
                    
                    
                    for (int h = 0; h < beanTemp.getFields().length; h++) {
                        System.out.println("-- " + h + " - " + columValues[beanTemp.getFields()[h]]);
                        createCell(row, h+1, columValues[beanTemp.getFields()[h]], wb, style);
                    }

                    dong++;
                    row = sheet.createRow(dong);
                } else if (dataType==Constant.KPI_DATA_PERSON) {
                    createCell(row, cot++, stt+i, wb, csLeft);
                    createCell(row, cot++, beanC.getLocationName(), wb, csLeft);
                    createCell(row, cot++, beanC.getCreateDate(), wb, csCenter);
                    createCell(row, cot++, beanC.getUserFullName(), wb, csLeft);
                    createCell(row, cot++, beanC.getPerName(), wb, csLeft);
                    createCell(row, cot++, beanC.ncrToString(arrSex[beanC.getPerSex()]), wb, csLeft);
                    createCell(row, cot++, beanC.getPerTitle(), wb, csLeft);
                    createCell(row, cot++, beanC.getPerAgency(), wb, csLeft);
                    createCell(row, cot++, beanC.getPerContact(), wb, csLeft);
                    createCell(row, cot++, beanC.getPerAddress(), wb, csLeft);
                } else if (dataType==Constant.KPI_DATA_EVENT) {
                    createCell(row, cot++, stt+i, wb, csLeft);
                    createCell(row, cot++, beanC.getEventCode(), wb, csLeft);
                    createCell(row, cot++, beanC.getLocationName(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventActivity(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventLocation(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventStartDate(), wb, csCenter);
                    createCell(row, cot++, beanC.getEventEndDate(), wb, csCenter);
                } else if (dataType==Constant.KPI_DATA_LIST_DIS) {
                    createCell(row, cot++, stt+i, wb, csLeft);
                    createCell(row, cot++, beanC.getNkt(), wb, csLeft);
                    createCell(row, cot++, beanC.getMaSo(), wb, csLeft);
                    createCell(row, cot++, beanC.getSoNha(), wb, csLeft);
                    createCell(row, cot++, beanC.getHuyenName(), wb, csLeft);
                    createCell(row, cot++, beanC.getPxaName(), wb, csLeft);
                    
                    createCell(row, cot++, beanC.getNamSinh(), wb, csLeft);
                    createCell(row, cot++, beanC.getGioiTinh(), wb, csLeft);
                    createCell(row, cot++, beanC.getPhoneNumber(), wb, csLeft);
                    
                    // Jun-2023 Add
                    createCell(row, cot++, beanC.getDanTocName(), wb, csLeft);
                    createCell(row, cot++, beanC.getNgheNghiep(), wb, csLeft);                    
                    createCell(row, cot++, beanC.getDaCam(), wb, csLeft);
                    createCell(row, cot++, beanC.getNcsTen(), wb, csLeft);
                    createCell(row, cot++, beanC.getNcsQuanHeName(), wb, csLeft);
                    createCell(row, cot++, beanC.getNcsDienThoai(), wb, csLeft);
                  
                    createCell(row, cot++, beanC.getDTatName(), wb, csLeft);
                    createCell(row, cot++, beanC.getDTatTinhTrang(), wb, csLeft);
                    // Jun-2023
                    createCell(row, cot++, beanC.getDTatNguyenNhan(), wb, csLeft);
                    
                    createCell(row, cot++, beanC.getDTatMucDo(), wb, csLeft);
                    createCell(row, cot++, beanC.getLastupdate(), wb, csLeft);
                  
                    // Jun-203
                    createCell(row, cot++, beanC.getNcauName(), wb, csLeft);
                    
                    createCell(row, cot++, beanC.getPhcnCanThiep(), wb, csLeft);
                    createCell(row, cot++, beanC.getPhcnDungCu(), wb, csLeft);
                    createCell(row, cot++, beanC.getPhcnDungCuKhac(), wb, csLeft);
                    createCell(row, cot++, beanC.getMoTaDCTG(), wb, csLeft);
                    
                    createCell(row, cot++, beanC.getHtNhaO(), wb, csLeft);
                    createCell(row, cot++, beanC.getHtNgay(), wb, csLeft);
                    createCell(row, cot++, beanC.getTrangthai(), wb, csLeft);
                    createCell(row, cot++, beanC.getNgayDongHS(), wb, csLeft);
                    createCell(row, cot++, beanC.getLydoDongHS(), wb, csLeft);
                    createCell(row, cot++, beanC.getNguoiDongHS(), wb, csLeft);
                    createCell(row, cot++, beanC.getDuAnHT(), wb, csLeft);
                } else if (dataType==Constant.KPI_DATA_LIST_PERSON_HOURS) {
                    if (curPerId>0) {
                        chkRow = (curPerId==beanC.getId()) ? true:false;
                    }

                    createCell(row, cot++, chkRow ? "" : stt++, wb, csLeft);
                    createCell(row, cot++, chkRow ? "" : beanC.getPerName(), wb, csLeft);
                    createCell(row, cot++, chkRow ? "" : beanC.getPerMale(), wb, csLeft);
                    createCell(row, cot++, chkRow ? "" : beanC.getPerFemale(), wb, csLeft);
                    createCell(row, cot++, chkRow ? "" : beanC.getPerAgency(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventLocation(), wb, csLeft);
                    createCell(row, cot++, beanC.getLocationName(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventStartDate(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventEndDate(), wb, csLeft);

                    //createCell(row, cot++, beanC.getPerHours(), wb, csLeft);
                    createCell(row, cot++, beanC.getEventActivity(), wb, csLeft);

                    curPerId = beanC.getId();
                }
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

    public FBeans getData(Connection cnn, int tinh_id, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportInforNKT bean = new FReportInforNKT();
        try {
            String param_report = "#0#,#53#,#51#,#129#,#15#,#261#,#13#,#14#,#10#,#20#,#0#,#149#,#25#,#157#,#75#,#177#,#77#,#79#,#89#,#91#,#217#,#231#,#235#,#237#,#97#,#169#,#251#,#93#";
            CallableStatement state = cnn.prepareCall("{call report_infor_nkt(?, ?, ?)}");
            state.setInt(1, tinh_id);
            state.setString(2, param_report);
            state.setInt(3, qlc);
            state.execute();
            
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_INFOR_NKT, null);
            rs = prpstm.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            while (rs!= null && rs.next()){
                bean = new FReportInforNKT();
                bean.setTotal(size);
                bean.setId(rs.getInt("id"));
                bean.setMa(rs.getString("ma"));
                bean.setTen(rs.getString("ten"));
                bean.setNgaySinh(rs.getString("ngaysinh")==null?"":rs.getString("ngaysinh"));
                bean.setSex(rs.getString("sex"));
                bean.setQuanhuyen(rs.getString("quanhuyen"));
                bean.setPhuongxa(rs.getString("phuongxa"));
                bean.setDiachi(rs.getString("sonha"));
                bean.setThonto(rs.getString("thonto"));
                bean.setDacam(rs.getString("dacam"));
                bean.setDanhgia(rs.getString("danhgia_nkt"));
                bean.setDangTats(rs.getString("dangtat"));
                
                bean.setParamvalue_1(rs.getInt("paramvalue_1"));
                bean.setParamvalue_2(rs.getInt("paramvalue_2"));
                bean.setParamvalue_3(rs.getInt("paramvalue_3"));
                bean.setParamvalue_4(rs.getInt("paramvalue_4"));
                bean.setParamvalue_5(rs.getInt("paramvalue_5"));
                bean.setParamvalue_6(rs.getInt("paramvalue_6"));
                bean.setParamvalue_7(rs.getInt("paramvalue_7"));
                bean.setParamvalue_8(rs.getInt("paramvalue_8"));
                bean.setParamvalue_9(rs.getInt("paramvalue_9"));
                bean.setParamvalue_10(rs.getInt("paramvalue_10"));
                bean.setParamvalue_11(rs.getInt("paramvalue_11"));
                bean.setParamvalue_12(rs.getInt("paramvalue_12"));
                bean.setParamvalue_13(rs.getInt("paramvalue_13"));
                bean.setParamvalue_14(rs.getInt("paramvalue_14"));
                bean.setParamvalue_15(rs.getInt("paramvalue_15"));
                bean.setParamvalue_16(rs.getInt("paramvalue_16"));
                bean.setParamvalue_17(rs.getInt("paramvalue_17"));
                bean.setParamvalue_18(rs.getInt("paramvalue_18"));
                bean.setParamvalue_19(rs.getInt("paramvalue_19"));
                bean.setParamvalue_20(rs.getInt("paramvalue_20"));
                bean.setParamvalue_21(rs.getInt("paramvalue_21"));
                bean.setParamvalue_22(rs.getInt("paramvalue_22"));
                bean.setParamvalue_23(rs.getInt("paramvalue_23"));
                bean.setParamvalue_24(rs.getInt("paramvalue_24"));
                bean.setParamvalue_25(rs.getInt("paramvalue_25"));
                bean.setParamvalue_26(rs.getInt("paramvalue_26"));
                bean.setParamvalue_27(rs.getInt("paramvalue_27"));
                bean.setParamvalue_28(rs.getInt("paramvalue_28"));                
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