package com.dao.disability.report;


import com.exp.EException;

import com.form.FExportExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;


public class DReportDis extends FExportExcel {
//    public String exportReport(FBeans beans, FSeed seed, 
//                           String excelFile) throws EException, 
//                                                    FileNotFoundException, 
//                                                    IOException {
//        final String LOCATION = this.toString() + "~>excelSearch()";
//        
//        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
//        String excelDown = excelPath + seed.me.getSessionID();
//        File file = new File(excelPath, excelFile);
//        FileInputStream fis = new FileInputStream(file);
//        POIFSFileSystem fs = new POIFSFileSystem(fis);
//        FSearch bean = (FSearch)seed;
//
//        HSSFWorkbook wb = new HSSFWorkbook(fs);
//        HSSFSheet sheet = wb.getSheetAt(0);
//        sheet.setAutobreaks(true);
//
//        HSSFFont font11 = getFont(wb, "Times New Roman", 11, false);
//        CellStyle style = 
//            getStyle(wb, font11, CellStyle.ALIGN_LEFT, 
//                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
//                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
//                     CellStyle.BORDER_THIN);
//
//        HSSFFont font11b= getFont(wb, "Times New Roman", 11, true);
//        CellStyle styleHeader = 
//            getStyle(wb, font11b, CellStyle.ALIGN_CENTER, 
//                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
//                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
//                     CellStyle.BORDER_THIN);
//        
//        Map<String, Object> mapObject = bean.getMapObject();
//        Map<String, String> mapDoiTuong = (Map<String, String>)mapObject.get("NKT_DOITUONG");
//
//        int dong = 1;
//        FSearch beanC = null;
//        
//        HSSFRow row = sheet.createRow(dong);
//        createCell(row, 0, "STT", wb, styleHeader); 
//        for (int h = 0; h < bean.getFields().length; h++) {
//            createCell(row, (h + 1), bean.ncrToString(IConstantsDisability.HEADER_VALUE[bean.getFields()[h]]), wb, styleHeader);
//        }
//        
//        dong++;
//        String arrSex[] = { "Nam", "N&#7919;" };
//        String arrSelect[] = {"Kh&#244;ng","C&#243;"};
//        String nguonHoTro = "";
//        String tdGiaoDuc = "";
//        String tdChuyenMon = "";
//        String ngheNghiepHt = "";
//        int nguonHotroId = 0;
//        int trinhDoId = 0;
//        int mucDoId = 0;
//        int ngheNghiepId = 0;
//        String mucdoKhuyetTat = "";
//        String giaoDucId = "";
//        String doiTuong = "";
//
//        for (int i = 0; i < beans.size(); i++) {
//            beanC = (FSearch)beans.get(i);            
//            row = sheet.createRow(dong);
//            createCell(row, (0), i + 1, wb, style);
//            
//            trinhDoId = beanC.getTdChuyenMon();
//            nguonHotroId = beanC.getNguonHotroId();
//            giaoDucId = beanC.getChucVuHT() != null ? beanC.getChucVuHT() : "0";
//            mucDoId = beanC.getMucDoKhuyetTat();
//            ngheNghiepId = beanC.getNgheNghiepHT();
//            doiTuong = mapDoiTuong.get(String.valueOf(beanC.getDoiTuong()));
//            
//            if (giaoDucId.equals("1")) {
//                tdGiaoDuc = bean.ncrToString("Kh&#244;ng &#273;i h&#7885;c");
//            } else if (giaoDucId.equals("2")) {
//                tdGiaoDuc = bean.ncrToString("M&#7851;u gi&#225;o/nh&#224; tr&#7867;");
//            } else if (giaoDucId.equals("3")) {
//                tdGiaoDuc = bean.ncrToString("&#272;ang h&#7885;c Ti&#7875;u h&#7885;c");
//            } else if (giaoDucId.equals("4")) {
//                tdGiaoDuc = bean.ncrToString("&#272;ang h&#7885;c THCS");
//            } else if (giaoDucId.equals("5")) {
//                tdGiaoDuc = bean.ncrToString("&#272;ang h&#7885;c PTTH");
//            } else if (giaoDucId.equals("6")) {
//                tdGiaoDuc = bean.ncrToString("&#272;ang h&#7885;c ngh&#7873; s&#417; c&#7845;p");
//            } else if (giaoDucId.equals("7")) {
//                tdGiaoDuc = bean.ncrToString("&#272;ang h&#7885;c tr&#432;&#7901;ng Trung h&#7885;c/C&#272; v&#224; &#272;&#7841;i h&#7885;c");
//            } else if (giaoDucId.equals("8")) {
//                tdGiaoDuc = bean.ncrToString("Kh&#225;c");
//            } else {
//                tdGiaoDuc = "";
//            }
//
//            switch (nguonHotroId) {
//            case 1:
//                nguonHoTro = bean.ncrToString("Nh&#224; n&#432;&#7899;c");
//                break;
//            case 2:
//                nguonHoTro = bean.ncrToString("VNAH");
//                break;
//            case 3:
//                nguonHoTro = bean.ncrToString("T&#7893; ch&#7913;c NGOs");
//                break;
//            case 4:
//                nguonHoTro = bean.ncrToString("T&#7893; ch&#7913;c x&#227; h&#7897;i, doanh nghi&#7879;p");
//                break;
//            case 6:
//                nguonHoTro = bean.ncrToString("East Meets West");
//                break;
//            case 7:
//                nguonHoTro = bean.ncrToString("Save the Children");
//                break;
//            case 8:
//                nguonHoTro = bean.ncrToString("DSP");
//                break;
//            case 5:
//                nguonHoTro = beanC.getNguonHt();
//                break;
//            default:
//                nguonHoTro = beanC.getNguonHt();
//                break;
//            }
//            
//            switch (trinhDoId) {
//            case 1:
//                tdChuyenMon = bean.ncrToString("Ch&#432;a qua &#273;&#224;o t&#7841;o");
//                break;
//            case 2:
//                tdChuyenMon = bean.ncrToString("S&#417;/trung h&#7885;c");
//                break;
//            case 3:
//                tdChuyenMon = bean.ncrToString("Cao &#273;&#7859;ng/&#273;&#7841;i h&#7885;c");
//                break;
//            case 4:
//                tdChuyenMon = bean.ncrToString("Tr&#234;n &#273;&#7841;i h&#7885;c");
//                break;
//            case 5:
//                tdChuyenMon = bean.ncrToString("Kh&#225;c");
//                break;
//            default:
//                tdChuyenMon = "";
//                break;
//            }
//            
//            switch (mucDoId) {
//            case 5:
//                mucdoKhuyetTat = bean.ncrToString("&#272;&#7863;c bi&#7879;t n&#7863;ng");
//                break;
//            case 4:
//                mucdoKhuyetTat = bean.ncrToString("N&#7863;ng");
//                break;
//            case 3:
//                mucdoKhuyetTat = bean.ncrToString("Nh&#7865;");
//                break;
//            case 2:
//                mucdoKhuyetTat = bean.ncrToString("Kh&#244;ng x&#225;c &#273;&#7883;nh");
//                break;
//            default:
//                mucdoKhuyetTat = "";
//                break;
//            }
//
//            switch (ngheNghiepId) {
//            case 1:
//                ngheNghiepHt = bean.ncrToString("C&#242;n nh&#7887;");
//                break;
//            case 2:
//                ngheNghiepHt = bean.ncrToString("N&#7897;i tr&#7907;");
//                break;
//            case 3:
//                ngheNghiepHt = bean.ncrToString("N&#244;ng nghi&#7879;p");
//                break;
//            case 4:
//                ngheNghiepHt = bean.ncrToString("C&#244;ng nh&#226;n - vi&#234;n ch&#7913;");
//                break;
//            case 5:
//                ngheNghiepHt = bean.ncrToString("C&#244;ng nh&#226;n");
//                break;
//            case 6:
//                ngheNghiepHt = bean.ncrToString("Th&#7907; th&#7911; c&#244;ng");
//                break;
//            case 7:
//                ngheNghiepHt = bean.ncrToString("D&#7883;ch v&#7909; bu&#244;n b&#225;n");
//                break;
//            case 8:
//                ngheNghiepHt = bean.ncrToString("Th&#7845;t nghi&#7879;p");
//                break;
//            case 9:
//                ngheNghiepHt = bean.ncrToString("B&#7879;nh t&#7853;t kh&#244;ng l&#224;m g&#236; &#273;&#432;&#7907;c");
//                break;
//            case 10:
//                ngheNghiepHt = bean.ncrToString("Kh&#225;c");
//                break;
//            default:
//                ngheNghiepHt = "";
//                break;
//            }
//            
//            //System.out.println("nkt_id " + beanC.getId() + " mucdo " + mucdoKhuyetTat + " ngheNghiepId " + ngheNghiepId);
//            
//            String columValues[] = 
//            {   beanC.getMaSo(), beanC.getMa(), beanC.getNkt(), beanC.ncrToString(arrSex[beanC.getSex()]),(beanC.getNgaySinh() != null ? beanC.getNgaySinh() : ""), 
//                beanC.getTinhName(), beanC.getCmnd(), beanC.getSoNha(), beanC.getThonTo(), beanC.getPhoneNumber(), 
//                beanC.getLastupdate(), beanC.getDonViCt(), tdGiaoDuc, tdChuyenMon, beanC.getChuhoName(), 
//                beanC.getChuhoNamSinh(), beanC.getNcs(), beanC.getNamSinhNcs(), beanC.getDieuKienName(), ngheNghiepHt, 
//                beanC.getNcHoTroCT(), beanC.getHoTroDaNhan(), beanC.getTgNhanHt(), nguonHoTro, beanC.getNguoiQlTrucTiep(), 
//                beanC.getDangTat(), mucdoKhuyetTat, beanC.ncrToString(arrSelect[beanC.getChatdocDaCam()]), 
//                beanC.ncrToString(arrSelect[beanC.getKhangChien()]), doiTuong};            
//            
//            for (int h = 0; h < bean.getFields().length; h++) {
//                createCell(row, (h + 1), columValues[bean.getFields()[h]], wb, style);
//            }
//
//            dong++;
//            nguonHotroId = 0;
//            tdGiaoDuc = "";
//        }
//        
//        auto_size_column(wb, bean.getFields().length+1);
//        sheet.setHorizontallyCenter(true);
//        sheet.setMargin(sheet.TopMargin, 0);
//        sheet.setMargin(sheet.BottomMargin, 2.5);
//        sheet.setMargin(sheet.LeftMargin, 0.25);
//        sheet.setMargin(sheet.RightMargin, 0.25);
//        FileOutputStream fos = new FileOutputStream(excelDown);
//        wb.write(fos);
//        fos.close();
//        return excelDown;
//    }

    private void auto_size_column(HSSFWorkbook wb, int columns) throws EException {
        Sheet sheet;
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            sheet = wb.getSheetAt(i);
            for (int j = 0; j < columns; j++)
                sheet.autoSizeColumn(j);
        }
    }
}
