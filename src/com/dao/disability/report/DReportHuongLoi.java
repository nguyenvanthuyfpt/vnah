package com.dao.disability.report;


import com.bo.disability.BDanhGia;
import com.bo.disability.BDisability;
import com.bo.disability.BPhanLoai;
import com.bo.disability.BRank;
import com.bo.disability.BSupport;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FDanhGia;
import com.form.disability.FDisability;
import com.form.disability.FPhanLoai;
import com.form.disability.FSupport;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import com.util.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.exception.NestableException;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportHuongLoi extends FExportExcel {
    
    private ExcelUtil eu;
    public String excelHuongLoi(FSeed seed, FDisability beanT, String excelFile) throws EException,
                                                                                        FileNotFoundException,
                                                                                        IOException, SQLException,
                                                                                        NestableException {
        final String LOCATION = this.toString() + "~>DReportHuongLoi()";
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP +
            "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);

        // Kiem tra
        BRank boRank;
        boRank = new BRank();
        Date now = new Date();

        BDisability boDisability = new BDisability();

        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);

        HSSFFont fontArialBold = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fontArial14 = getFont(wb, "Times New Roman", 11, false);

        CellStyle styleBold =
            getStyle(wb, fontArialBold, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_JUSTIFY,
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE,
                     CellStyle.BORDER_NONE);

        CellStyle style =
            getStyle(wb, fontArialBold, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN,
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);

        CellStyle style1 =
            getStyle(wb, fontArial14, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN,
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);

        CellStyle style2 =
            getStyle(wb, fontArial14, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE,
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE);

        FDisability bean = (FDisability)seed;

        // HTro Ca nhan
        String htCaNhan =
            boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#141#%') order by datecreate desc");

        // Ma so - So TT - Ngay cap nhat
        createCell(sheet.getRow(1), (2), beanT.getMa_nkt(), wb, style2);
        createCell(sheet.getRow(1), (6), beanT.getMa(), wb, style2);
        createCell(sheet.getRow(2), (2), beanT.getDateLastUpdate(), wb, style2);

        // Nguoi quan ly & don vi quan ly
        //Region regionTNQL = new Region(4, 2, 4, 4);
        createCell(sheet.getRow(1), (2), beanT.getMa_nkt(), wb, style2);
        
        //sheet.addMergedRegion(regionTNQL);        
        createCell(sheet.getRow(4), (2), beanT.getNguoiQuanLyTen(), wb, style2);
        
        //Region regionDVQL = new Region(5, 2, 5, 4);
        //sheet.addMergedRegion(regionDVQL);
        createCell(sheet.getRow(5), (2), beanT.getNguoiQuanLyDonVi(), wb, style2);
        createCell(sheet.getRow(9), (2), beanT.getNkt(), wb, style2);
        createCell(sheet.getRow(10), (2), beanT.getNgaySinh(), wb, style2);

        if (beanT.getSex() == 1)
            createCell(sheet.getRow(11), (2), bean.ncrToString("N&#7919;"), wb, style2);
        else
            createCell(sheet.getRow(11), (2), bean.ncrToString("Nam"), wb, style2);

        createCell(sheet.getRow(11), (6), beanT.getDantocName(), wb, style2);
        createCell(sheet.getRow(12), (2), beanT.getCmnd(), wb, style2);
        createCell(sheet.getRow(13), (6), beanT.getThonTo(), wb, style2);

        // So nha / Pho
        createCell(sheet.getRow(14), (2), beanT.getSoNha(), wb, style2);

        // Tinh / T.Pho
        createCell(sheet.getRow(15), (2), beanT.getTinhName(), wb, style2);

        // Dien thoai
        createCell(sheet.getRow(16), (2), beanT.getPhoneNumber(), wb, style2);

        // Tinh trang hon nhan
        String ttHonNhan = "";
        if (beanT.getTtHonNhanId() == 1)
            ttHonNhan = bean.ncrToString("Ch&#432;a k&#7871;t h&#244;n");
        else if (beanT.getTtHonNhanId() == 2)
            ttHonNhan = bean.ncrToString("&#272;&#227; k&#7871;t h&#244;n");
        else if (beanT.getTtHonNhanId() == 3)
            ttHonNhan = bean.ncrToString("Ly h&#244;n");
        else if (beanT.getTtHonNhanId() == 4)
            ttHonNhan = bean.ncrToString("G&#243;a");
        else if (beanT.getTtHonNhanId() == 5)
            ttHonNhan = beanT.getHonNhanKhac();

        createCell(sheet.getRow(17), (2), ttHonNhan, wb, style2);

        // Tong so con
        createCell(sheet.getRow(18), (2),
                   String.valueOf(bean.getTongSoCon()) != null ? bean.getTongSoCon() : 0, wb, style2);
        createCell(sheet.getRow(18), (6),
                   String.valueOf(bean.getTongSoConDuoi16()) != null ? bean.getTongSoConDuoi16() : 0, wb, style2);

        // Trinh do van hoa
        String tdVanHoa = "";
        if (beanT.getTrinhDoId() == 1)
            tdVanHoa = bean.ncrToString("Kh&#244;ng &#273;i h&#7885;c/m&#249; ch&#7919;");
        else if (beanT.getTrinhDoId() == 2)
            tdVanHoa = bean.ncrToString("Bi&#7871;t &#273;&#7885;c,bi&#7871;t vi&#7871;t");
        else if (beanT.getTrinhDoId() == 3)
            tdVanHoa = bean.ncrToString("Ti&#7875;u h&#7885;c");
        else if (beanT.getTrinhDoId() == 4)
            tdVanHoa = bean.ncrToString("THCS");
        else if (beanT.getTrinhDoId() == 5)
            tdVanHoa = bean.ncrToString("PTTH");
        createCell(sheet.createRow(19), (2), tdVanHoa, wb, style2);

        // Trinh do chuyen mon
        String tdChuyenMon = "";
        if ("1".equals(beanT.getTdChuyenMon()))
            tdChuyenMon = bean.ncrToString("Ch&#432;a qua &#273;&#224;o t&#7841;o");
        else if ("2".equals(beanT.getTdChuyenMon()))
            tdChuyenMon = bean.ncrToString("S&#417;/trung h&#7885;c");
        else if ("3".equals(beanT.getTdChuyenMon()))
            tdChuyenMon = bean.ncrToString("Cao &#273;&#7859;ng/&#273;&#7841;i h&#7885;c");
        else if ("4".equals(beanT.getTdChuyenMon()))
            tdChuyenMon = bean.ncrToString("Tr&#234;n &#273;&#7841;i h&#7885;c");
        else if ("5".equals(beanT.getTdChuyenMon()))
            tdChuyenMon = beanT.getChuyenMonKhac();
        createCell(sheet.createRow(19), (6), tdChuyenMon, wb, style2);

        // Nghe nghiep & chuc vu
        String ngheNghiepHT = "";
        if ("1".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("C&#242;n nh&#7887;");
        else if ("2".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("N&#7897;i tr&#7907;");
        else if ("3".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("N&#244;ng nghi&#7879;p");
        else if ("4".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("C&#244;ng nh&#226;n - vi&#234;n ch&#7913;c");
        else if ("5".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("C&#244;ng nh&#226;n");
        else if ("6".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("Th&#7907; th&#7911; c&#244;ng");
        else if ("7".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("D&#7883;ch v&#7909;/bu&#244;n b&#225;n");
        else if ("8".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("Th&#7845;t nghi&#7879;p");
        else if ("9".equals(beanT.getNgheNghiepHT()))
            ngheNghiepHT = bean.ncrToString("B&#7879;nh t&#7853;t kh&#244;ng l&#224;m g&#236; &#273;&#432;&#7907;c");
        else if ("10".equals(bean.getNgheNghiepHT()))
            ngheNghiepHT = bean.getNgheNghiepKhac();
        createCell(sheet.createRow(20), (2), ngheNghiepHT, wb, style2);

        // Tinh trang giao duc hien nay
        if (beanT.getChucVuHT().equals("1")) {
            createCell(sheet.createRow(20), (6), bean.ncrToString("Kh&#244;ng &#273;i h&#7885;c"), wb, style2);
        } else if (beanT.getChucVuHT().equals("2")) {
            createCell(sheet.getRow(20), (6), bean.ncrToString("M&#7851;u gi&#225;o/nh&#224; tr&#7867;"), wb,
                       style2);
        } else if (beanT.getChucVuHT().equals("3")) {
            createCell(sheet.getRow(20), (6), bean.ncrToString("&#272;ang h&#7885;c Ti&#7875;u h&#7885;c"),
                       wb, style2);
        } else if (beanT.getChucVuHT().equals("4")) {
            createCell(sheet.getRow(20), (6), bean.ncrToString("&#272;ang h&#7885;c THCS"), wb, style2);
        } else if (beanT.getChucVuHT().equals("5")) {
            createCell(sheet.getRow(20), (6), bean.ncrToString("&#272;ang h&#7885;c PTTH"), wb, style2);
        } else if (beanT.getChucVuHT().equals("6")) {
            createCell(sheet.getRow(20), (6),
                       bean.ncrToString("&#272;ang h&#7885;c ngh&#7873; s&#417; c&#7845;p"), wb, style2);
        } else if (beanT.getChucVuHT().equals("7")) {
            createCell(sheet.getRow(20), (6),
                       bean.ncrToString("&#272;ang h&#7885;c tr&#432;&#417;ng Trung h&#7885;c/C&#272; v&#224; &#272;&#7841;i h&#7885;c"),
                       wb, style2);
        } else if (beanT.getChucVuHT().equals("8")) {
            createCell(sheet.getRow(20), (6), bean.getGiaoDucKhac(), wb, style2);
        }

        // Ho tro ca nhan
        /*if((htCaNhan==null) || (htCaNhan.equals(""))){
             createCell(sheet.getRow(21), (3),bean.ncrToString("Kh&#244;ng"),wb, style2);
        }else{
             createCell(sheet.getRow(21), (3),bean.ncrToString("C&#243;"),wb, style2);
        }

        // TT Nhom tu luc
        String ttNhomTuLuc = boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt="+bean.getId()+" and danhgia_ids like ('%#107#%') order by datecreate desc");

        if((ttNhomTuLuc==null) || (ttNhomTuLuc.equals(""))){
            createCell(sheet.getRow(22), (3),bean.ncrToString("Kh&#244;ng"),wb, style2);
        }else{
            createCell(sheet.getRow(22), (3),bean.ncrToString("C&#243;"),wb, style2);
        }*/

        // Chat doc da cam
        if (beanT.getChatDocDaCam() == 1)
            createCell(sheet.getRow(21), (2), bean.ncrToString("C&#243;"), wb, style2);
        else
            createCell(sheet.getRow(21), (2), bean.ncrToString("Kh&#244;ng"), wb, style2);

        // Chuan doan
        createCell(sheet.getRow(22), (2), beanT.getChuanDoan(), wb, style2);

        // Thong tin ve Ho Gia Dinh
        createCell(sheet.getRow(24), (2), beanT.getTenChuHo(), wb, style2);
        createCell(sheet.getRow(24), (6), beanT.getNamSinhChuHo(), wb, style2);

        // Quan he voi NKT
        if (beanT.getQuanHeChuHo() == 1) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("B&#7889;"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 2) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("M&#7865;"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 3) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Ng&#432;&#7901;i ch&#259;m s&#243;c"), wb,
                       style2);
        } else if (beanT.getQuanHeChuHo() == 4) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Ng&#432;&#7901;i khuy&#7871;t t&#7853;t"),
                       wb, style2);
        } else if (beanT.getQuanHeChuHo() == 5) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Kh&#225;c"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 6) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("V&#7907;"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 7) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Ch&#7891;ng"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 8) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Con"), wb, style2);
        } else if (beanT.getQuanHeChuHo() == 9) {
            createCell(sheet.getRow(25), (2), bean.ncrToString("Ch&#225;u"), wb, style2);
        }

        // So nguoi trong ho
        createCell(sheet.getRow(26), (2), beanT.getSonguoi_chuho() != 0 ? beanT.getSonguoi_chuho() : 0, wb,
                   style2);
        createCell(sheet.getRow(26), (6), beanT.getSoNKT_chuho() != 0 ? beanT.getSoNKT_chuho() : 0, wb,
                   style2);

        // Dieukien K.Te
        createCell(sheet.getRow(27), (2), beanT.getDieuKienName(), wb, style2);

        // Nguon nuoc
        if (beanT.getNguonNuocId() == 1) {
            createCell(sheet.getRow(28), (2), bean.ncrToString("N&#432;&#7899;c m&#225;y"), wb, style2);
        } else if (beanT.getNguonNuocId() == 2) {
            createCell(sheet.getRow(28), (2), bean.ncrToString("N&#432;&#7899;c m&#432;a"), wb, style2);
        } else if (beanT.getNguonNuocId() == 3) {
            createCell(sheet.getRow(28), (2),
                       bean.ncrToString("Gi&#7871;ng t&#7921; &#273;&#224;o / Gi&#7871;ng khoan"), wb, style2);
        } else if (beanT.getNguonNuocId() == 4) {
            createCell(sheet.getRow(28), (2), bean.ncrToString("N&#432;&#7899;c ao, s&#244;ng, su&#7889;i"),
                       wb, style2);
        } else if (beanT.getNguonNuocId() == 5) {
            createCell(sheet.getRow(28), (2), bean.ncrToString("Ngu&#7891;n kh&#225;c"), wb, style2);
        }

        // Nha Ve Sinh
        if (beanT.getNhaVeSinhChuHo() == 0) {
            createCell(sheet.getRow(29), (2), bean.ncrToString("Kh&#244;ng"), wb, style2);
        } else if (beanT.getNhaVeSinhChuHo() == 1) {
            createCell(sheet.getRow(29), (2), bean.ncrToString("C&#243;"), wb, style2);
        }

        // NKT su dung Nha Ve Sinh
        if (beanT.getNhaVeSinhNKTChuHo() == 0) {
            createCell(sheet.getRow(30), (2), bean.ncrToString("Kh&#244;ng"), wb, style2);
        } else if (beanT.getNhaVeSinhNKTChuHo() == 1) {
            createCell(sheet.getRow(30), (2), bean.ncrToString("C&#243;"), wb, style2);
        }

        // Tinh trang nha o
        if (beanT.getNhaOId() == 1) {
            createCell(sheet.getRow(31), (2), bean.ncrToString("Nh&#224; t&#7841;m"), wb, style2);
        } else if (beanT.getNhaOId() == 2) {
            createCell(sheet.getRow(31), (2), bean.ncrToString("Nh&#224; b&#225;n ki&#234;n c&#7889;"), wb,
                       style2);
        } else if (beanT.getNhaOId() == 3) {
            createCell(sheet.getRow(31), (2), bean.ncrToString("Nh&#224; ki&#234;n c&#7889;"), wb, style2);
        }

        // Nguoi cham soc
        createCell(sheet.getRow(33), (2), beanT.getTenChamSoc(), wb, style2);
        createCell(sheet.getRow(33), (6), beanT.getNamSinhChamSoc(), wb, style2);

        if (beanT.getQuanHeChamSoc() == 1) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("&#212;ng"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 2) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("B&#224;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 3) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("C&#244;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 4) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("D&#236;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 5) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Ch&#250;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 6) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("B&#225;c"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 7) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Anh"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 8) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Ch&#7883;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 9) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Em"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 10) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("B&#7889;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 11) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("M&#7879;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 12) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Ch&#7891;ng"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 13) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("V&#7907;"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 14) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Kh&#225;c"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 15) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Con"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 16) {
            createCell(sheet.getRow(34), (2), bean.ncrToString("Ch&#225;u"), wb, style2);
        } else if (beanT.getQuanHeChamSoc() == 0) {
            createCell(sheet.getRow(34), (2), bean.ncrToString(""), wb, style2);
        }

        // Dien thoai lien lac
        createCell(sheet.getRow(34), (6), beanT.getSdtLienLac(), wb, style2);

        FBeans beanPLs = new FBeans();
        BPhanLoai boPhanLoai = new BPhanLoai();

        // DangTats
        beanPLs = boPhanLoai.getAllByIdNkt(bean.getId());
        String strDangTat = "";
        String strNguyenNhan = "";
        String strTinhTrang = "";

        int rowPL = 38;
        int sttPhanLoai = 1;

        for (int i = 0; i < beanPLs.size(); i++) {
            FPhanLoai beanFPhanLoai = (FPhanLoai)beanPLs.get(i);
            strDangTat = boDisability.getDangTat(beanT.getId(), beanFPhanLoai.getId());
            strNguyenNhan = boDisability.getNguyenNhan(beanT.getId(), beanFPhanLoai.getId());
            sheet.shiftRows(rowPL, sheet.getLastRowNum(), 1);
            
            createCell(sheet.getRow(rowPL), (0), sttPhanLoai++, wb, style1);
            createCell(sheet.getRow(rowPL), (1), beanFPhanLoai.getDateCreate(), wb, style1);
            createRegion(wb, sheet.getRow(rowPL), rowPL, rowPL, 2, 4, style1, strDangTat, 1, 0);
            createCell(sheet.getRow(rowPL), (5), beanFPhanLoai.getReson(), wb, style1);            
            createRegion(wb, sheet.getRow(rowPL), rowPL, rowPL, 6, 7, style1, strNguyenNhan, 1, 0);
            rowPL++;
        }

        String characters = "";
        String member = "";
        String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";
        FBeans beans = new FBeans();
        beans = new BTreeView().getTreeList(0, SQL_HOTRO, characters, member);

        int dong = rowPL + 7;
        int rowDG = rowPL + 7;
        int STT = 1;

        sheet.getRow(dong - 2).setHeightInPoints(30);
        sheet.getRow(dong - 1).setHeightInPoints(90);

        for (int i = 0; i < beans.size(); i++) {
            FTinh beanTree = (FTinh)beans.get(i);
            BSupport bo = new BSupport();
            FSupport beanSupport = new FSupport();
            beanSupport = bo.getSupportByNktID_HotroID(bean.getId(), beanTree.getId());

            if (beanTree.getLevel() == 0) {
                rowDG++;
                sheet.shiftRows(dong, sheet.getLastRowNum(), 1);                
                createRegion(wb, sheet.getRow(dong++), dong++, dong++, 0, 7, style, beanTree.getName(), 1, 0);

            } else if (beanTree.getLevel() == 1 || beanTree.getLevel() == 2 || beanTree.getLevel() == 3) {
                rowDG++;
                sheet.shiftRows(dong, sheet.getLastRowNum(), 1);
                createCell(sheet.getRow(dong), (0), STT++, wb, style1);

                if (beanTree.getLevel() == 1)
                    createCell(sheet.getRow(dong), (1), beanTree.getName(), wb, style);
                else if (beanTree.getLevel() == 2)
                    createCell(sheet.getRow(dong), (1), "  " + beanTree.getName(), wb, style1);
                else if (beanTree.getLevel() == 3)
                    createCell(sheet.getRow(dong), (1), "      " + beanTree.getName(), wb, style1);

                String hotroId = String.valueOf(beanTree.getId());
                boolean hasSupport = false;

                if (beanSupport.getHotroIds() != null) {
                    hasSupport = beanSupport.getHotroIds().indexOf(hotroId) > 0 ? true : false;
                }

                createCell(sheet.getRow(dong), (2), beanSupport.getDateCreate(), wb, style1);
                createCell(sheet.getRow(dong), (3), hasSupport ? bean.ncrToString("C&#243;"):bean.ncrToString("Kh&#244;ng"), wb, style1);
                
                if ((beanSupport.getDuocHTNgay() == null) && hasSupport) {
                    createCell(sheet.getRow(dong), (4), "X", wb, style1);
                } else {
                    createCell(sheet.getRow(dong), (4), "", wb, style1);
                }

                Date fromDate = null;
                Date toDate = null;

                if (beanSupport.getDuocHTNgay() != null && beanSupport.getDuocHTNgay() != "") {
                    String[] arrDate = beanSupport.getDuocHTNgay().split(" - ");
                    if (arrDate != null && arrDate.length > 0) {
                        fromDate = bean.stringToDate(arrDate[0]);
                        toDate = bean.stringToDate(arrDate[1]);
                    }
                }

                int compareFromDate = 0;
                int compareToDate = 0;

                if ((fromDate != null) && (toDate != null)) {
                    compareFromDate = now.compareTo(fromDate);
                    compareToDate = now.compareTo(toDate);
                }

                // Da duoc ho tro
                createCell(sheet.getRow(dong), (5),
                           ((compareFromDate > 0) && (compareToDate > 0)) ? beanSupport.getDuocHTCuThe() : "", wb,
                           style1);
                
                Map<String, String> map_nguonhotro =
                    (Map<String, String>)bean.getRequest().getSession().getAttribute("MAP_NGUONHOTRO");
                if ((compareFromDate > 0) && (compareToDate > 0)) {
                    if (beanSupport.getNguonHoTroId() == 1) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("Nh&#224; n&#432;&#7899;c"), wb,
                                   style1);
                    } else if (beanSupport.getNguonHoTroId() == 2) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("VNAH"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 3) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("T&#7893; ch&#7913;c NGOs"), wb,
                                   style1);
                    } else if (beanSupport.getNguonHoTroId() == 4) {
                        createCell(sheet.getRow(dong), (6),
                                   bean.ncrToString("T&#7893; ch&#7913;c x&#227; h&#7897;i, doanh nghi&#7879;p"), wb,
                                   style1);
                    } else if (beanSupport.getNguonHoTroId() == 6) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("East Meets West"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 7) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("Save the Children"), wb,
                                   style1);
                    } else if (beanSupport.getNguonHoTroId() == 8) {
                        createCell(sheet.getRow(dong), (6), bean.ncrToString("DSP"), wb, style1);
                    } else {
                        createCell(sheet.getRow(dong), (6), beanSupport.getNguonhotro(), wb, style1);
                    }

                    if(map_nguonhotro.containsKey(beanSupport.getNguonHoTroId())){
                        String nguonhotro = map_nguonhotro.get(beanSupport.getNguonHoTroId());
                        createCell(sheet.getRow(dong), (6), nguonhotro, wb, style1);
                    } else {
                        createCell(sheet.getRow(dong), (6), beanSupport.getNguonhotro(), wb, style1);
                    }
                } else {
                    createCell(sheet.getRow(dong), (6), "", wb, style1);
                }

                createCell(sheet.getRow(dong), (7),
                           ((compareFromDate > 0) && (compareToDate > 0)) ? beanSupport.getDuocHTNgay() : "", wb,
                           style1);


                // Dang duoc ho tro
                createCell(sheet.getRow(dong), (8),
                           ((compareFromDate >= 0) && (compareToDate < 0)) ?
                           beanSupport.getDuocHTCuThe() : "", wb, style1);

                if ((compareFromDate >= 0) && (compareToDate < 0)) {
                    if (beanSupport.getNguonHoTroId() == 1) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("Nh&#224; n&#432;&#7899;c"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 2) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("VNAH"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 3) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("T&#7893; ch&#7913;c NGOs"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 4) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("T&#7893; ch&#7913;c x&#227; h&#7897;i, doanh nghi&#7879;p"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 6) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("East Meets West"), wb, style1);
                    } else if (beanSupport.getNguonHoTroId() == 7) {
                        createCell(sheet.getRow(dong), (9), bean.ncrToString("Save the Children"), wb, style1);
                    } else {
                        createCell(sheet.getRow(dong), (9), beanSupport.getNguonhotro(), wb, style1);
                    }
                } else {
                    createCell(sheet.getRow(dong), (9), "", wb, style1);
                }


                createCell(sheet.getRow(dong), (10),
                           ((compareFromDate >= 0) && (compareToDate < 0)) ?
                           beanSupport.getDuocHTNgay() : "", wb, style1);

                dong++;
            }
        }

        //kiem tra xem co ke hoach ho tro ko?
        String tuNgay = "";
        String denNgay = "";

        {
            if (bean.getKyBC() == 1) {
                tuNgay = "'01/01/" + bean.getNamBC() + "'";
                denNgay = "'01/07/" + bean.getNamBC() + "'";
            } else {
                tuNgay = "'01/07/" + bean.getNamBC() + "'";
                int nam = bean.getNamBC() + 1;
                denNgay = "'01/01/" + nam + "'";
            }


            String ttHoTro =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like '%#141#%' " +
                                 " and dr_rank.datecreate>=to_date(" + tuNgay + ",'dd/mm/yyyy') " +
                                 " and dr_rank.datecreate<to_date(" + denNgay + ",'dd/mm/yyyy')");

            if ((ttHoTro == null) || (ttHoTro.equals(""))) {
                createCell(sheet.createRow(0), (0),
                           bean.ncrToString("H&#7890; S&#416; C&#193; NH&#194;N NG&#431;&#7900;I KHUY&#7870;T T&#7852;T"),
                           wb, styleBold);
            }

            int rowKQ = rowDG + 3;

            // Ket Hon
            String ttKetHon =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#109#%') order by datecreate desc");

            createCell(sheet.createRow(rowKQ), (5),
                       ((ttKetHon == null) || ttKetHon.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Thanh vien tc Xa hoi
            String ttThanhVien =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#111#%') order by datecreate desc");

            createCell(sheet.createRow(rowKQ + 1), (5),
                       ((ttThanhVien == null) || ttThanhVien.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Xay dung ke hoach ho tro ca nhan
            String ttXayDungKeHoach =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#141#%') order by datecreate desc");

            createCell(sheet.createRow(rowKQ + 2), (5),
                       ((ttXayDungKeHoach == null) || ttXayDungKeHoach.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Co muc tieu ...
            String ttMucTieu =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#113#%') order by datecreate desc");

            createCell(sheet.createRow(rowKQ + 3), (5),
                       ((ttMucTieu == null) || ttMucTieu.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Cai thien chuc nang ...
            String ttCaiThien =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#115#%') order by datecreate desc");

            createCell(sheet.createRow(rowKQ + 4), (5),
                       ((ttCaiThien == null) || ttCaiThien.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Dat duoc muc tieu ho tro ...
            String ttDatMucTieu =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#117#%') order by datecreate desc");

            createCell(sheet.getRow(rowKQ + 5), (5),
                       ((ttDatMucTieu == null) || ttDatMucTieu.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Tiep tuc tham gia chuong trinh ...
            String ttThamGia =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " and danhgia_ids like ('%#125#%') order by datecreate desc");

            createCell(sheet.getRow(rowKQ + 6), (5),
                       ((ttThamGia == null) || ttThamGia.equals("")) ? bean.ncrToString("Kh&#244;ng") :
                       bean.ncrToString("C&#243;"), wb, style2);

            // Roi khoi chuong trinh do ...
            String ttTinhTrang =
                boRank.getByQLCA("SELECT danhgia_ids FROM dr_rank where id_nkt=" + bean.getId() + " order by datecreate desc");

            if ((ttTinhTrang != null)) {
                if (ttTinhTrang.indexOf("#129#") >= 0) {
                    createCell(sheet.createRow(rowKQ + 7), (5),
                               bean.ncrToString("&#272;&#227; h&#242;a nh&#7853;p c&#7897;ng &#273;&#7891;ng"), wb,
                               style2);
                } else if (ttTinhTrang.indexOf("#131#") >= 0) {
                    createCell(sheet.createRow(rowKQ + 7), (5),
                               bean.ncrToString("Kh&#244;ng c&#243; k&#7871;t qu&#7843;, ch&#225;n n&#7843;n, b&#7887; cu&#7897;c"),
                               wb, style2);
                } else if (ttTinhTrang.indexOf("#133#") >= 0) {
                    createCell(sheet.createRow(rowKQ + 7), (5),
                               bean.ncrToString("Chuy&#7875;n &#273;i n&#417;i kh&#225;c"), wb, style2);
                } else if (ttTinhTrang.indexOf("#135#") >= 0) {
                    createCell(sheet.createRow(rowKQ + 7), (5), bean.ncrToString("Ch&#7871;t"), wb, style2);
                } else if (ttTinhTrang.indexOf("#137#") >= 0) {
                    createCell(sheet.createRow(rowKQ + 7), (5), bean.ncrToString("Kh&#225;c"), wb, style2);
                }
            }

            FBeans beanDGs = new FBeans();
            FDanhGia beanDG = new FDanhGia();
            FDanhGia beanDSKT = new FDanhGia();
            FDanhGia beanGD = new FDanhGia();
            BDanhGia bo = new BDanhGia();

            //beanDGs = bo.getAllByNktId(beanT.getId(),bean.getKyBC(),bean.getNamBC());
            beanDG = bo.getDanhGia_GiaoDuc(beanT.getId(), bean.getKyBC(), beanT.getNamBC());
            rowDG = rowDG + 15;

            int rowT = rowDG;

            if (beanDG.getYteSucKhoe() == 1) {
                createCell(sheet.createRow(rowT), (5), bean.ncrToString("Ti&#7871;n b&#7897; nhi&#7873;u"), wb,
                           style2);
            } else if (beanDG.getYteSucKhoe() == 2) {
                createCell(sheet.createRow(rowT), (5), bean.ncrToString("Ti&#7871;n b&#7897; &#237;t"), wb,
                           style2);
            } else if (beanDG.getYteSucKhoe() == 3) {
                createCell(sheet.createRow(rowT), (5), bean.ncrToString("Kh&#244;ng ti&#7871;n b&#7897;"), wb,
                           style2);
            } else if (beanDG.getYteSucKhoe() == 4) {
                createCell(sheet.createRow(rowT), (5), bean.ncrToString("X&#7845;u &#273;i"), wb, style2);
            }

            if (beanDG.getKinhteXaHoi() == 1) {
                createCell(sheet.createRow(rowT + 2), (5), bean.ncrToString("Ti&#7871;n b&#7897; nhi&#7873;u"),
                           wb, style2);
            } else if (beanDG.getKinhteXaHoi() == 2) {
                createCell(sheet.createRow(rowT + 2), (5), bean.ncrToString("Ti&#7871;n b&#7897; &#237;t"), wb,
                           style2);
            } else if (beanDG.getKinhteXaHoi() == 3) {
                createCell(sheet.createRow(rowT + 2), (5), bean.ncrToString("Kh&#244;ng ti&#7871;n b&#7897;"),
                           wb, style2);
            } else if (beanDG.getKinhteXaHoi() == 4) {
                createCell(sheet.createRow(rowT + 2), (5), bean.ncrToString("X&#7845;u &#273;i"), wb, style2);
            }

            if (beanDG.getGiaoDuc() == 1) {
                createCell(sheet.createRow(rowT + 4), (5), bean.ncrToString("Ti&#7871;n b&#7897; nhi&#7873;u"),
                           wb, style2);
            } else if (beanDG.getGiaoDuc() == 2) {
                createCell(sheet.createRow(rowT + 4), (5), bean.ncrToString("Ti&#7871;n b&#7897; &#237;t"), wb,
                           style2);
            } else if (beanDG.getGiaoDuc() == 3) {
                createCell(sheet.createRow(rowT + 4), (5), bean.ncrToString("Kh&#244;ng ti&#7871;n b&#7897;"),
                           wb, style2);
            } else if (beanDG.getGiaoDuc() == 4) {
                createCell(sheet.createRow(rowT + 4), (5), bean.ncrToString("X&#7845;u &#273;i"), wb, style2);
            }

            sheet.setHorizontallyCenter(true);
            sheet.setMargin(sheet.TopMargin, 0.25);
            sheet.setMargin(sheet.BottomMargin, 0.25);
            sheet.setMargin(sheet.LeftMargin, 0.25);
            sheet.setMargin(sheet.RightMargin, 0.25);
            FileOutputStream fos = new FileOutputStream(excelDown);
            wb.write(fos);
            fos.close();
            return excelDown;
        }
    }
}
