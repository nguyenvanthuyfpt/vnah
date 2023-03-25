package com.util;

import java.text.SimpleDateFormat;

import java.util.ResourceBundle;


public class Constant {
    
    public static final ResourceBundle config = ResourceBundle.getBundle("com.res.app-config");
    
    public static final String export_dir = "e:\\temp\\";
    public static final String report_dir = "f:/tph/";
    public static final int max_row = 65000;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM/yyyy");
    
    /* Defined Format DateTime */
    public static final String FOMRAT_DATE = "dd/MM/yyyy";
    public static final String FOMRAT_DATETIME = "dd/MM/yyyy hh:mm aa";
    public static final String FOMRAT_FULL_DATETIME = "dd/MM/yyyy hh:mm:ss aa";
    
    public static final String REPORT_PERIOD_TYPE_MONTH = "0";
    public static final String REPORT_PERIOD_TYPE_QUARTER = "1";
    public static final String REPORT_PERIOD_TYPE_YEAR = "2";
    public static final String REPORT_PERIOD_TYPE_FT = "3";
    public static final String REPORT_PERIOD_TYPE_FROM_TO = "4";
    
    public static final String SESS_OPT_TREE_OBJECT = "OPT_TREE_OBJECT";
    public static final String SESS_OPT_TREE_INDICATOR = "OPT_TREE_INDICATOR";
    
    public static final String SESS_LIST_NGUONHOTRO = "LIST_NGUONHOTRO";
    public static final String SESS_LIST_DIEUKIEN = "LIST_DIEUKIEN";
    public static final String SESS_LIST_NGUYENNHAN = "LIST_NGUYENNHAN";
    public static final String SESS_LIST_DANGTAT = "LIST_DANGTAT";
    public static final String SESS_LIST_MUCDO = "LIST_MUCDO";
    public static final String SESS_LIST_DANTOC = "LIST_DANTOC";
    public static final String SESS_LIST_TINHTRANG = "LIST_TINHTRANG";
    public static final String SESS_LIST_HOTRO = "LIST_HOTRO";
    
    public static final int KPI_DATA_VALUE = 0;
    public static final int KPI_DATA_DIS = 1;
    public static final int KPI_DATA_PERSON = 2;
    public static final int KPI_DATA_HOURS = 3;    
    public static final int KPI_DATA_EVENT = 4;
    public static final int KPI_DATA_LIST_DIS = 5;
    public static final int KPI_DATA_LIST_PERSON_HOURS = 6;
    public static final int KPI_DATA_LIST_COUNT = 7;
    public static final int KPI_DATA_LIST_COUNT_LATE = 8;
    public static final int KPI_DATA_DIS_COMMUNE = 9;
    
    public static final int KPI_SELECT_EVENT = 1;
    public static final int KPI_SELECT_PERSON = 2;
    
    public static final int KPI_STATE_INPUT = 0;
    public static final int KPI_STATE_SELECT = 1;
    public static final int KPI_STATE_AUTO = 2;
    
    public static final String map_columns = "map_columns";
    
    public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh &#273;&#224; n&#7861;ng";    // DA NANG
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh &#273;&#7891;ng nai"; // DONG NAI
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh b&#236;nh &#273;&#7883;nh"; // BINH DINH
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh qu&#7843;ng nam"; // QUANG NAM
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh th&#225;i b&#236;nh"; // THAI BINH
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh t&#226;y ninh"; // TAY NINH
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh b&#236;nh ph&#432;&#7899;c"; // BINH PHUOC
    //public static final String report_donvi_1 = "s&#7903; lao &#273;&#7897;ng - tbxh t&#7881;nh ninh b&#236;nh"; // NINH BINH
    //public static final String report_donvi_3 = "b&#7843;o tr&#7907;, ch&#259;m s&#243;c tr&#7867; em";
    
    public static final String report_title_sldtbxh = "s&#7903; lao &#273;&#7897;ng th&#432;&#417;ng binh - x&#227; h&#7897;i";
    public static final String report_title_sgddt = "s&#7903; gi&#225;o d&#7909;c v&#224; &#273;&#224;o t&#7841;o";
    public static final String report_title_syte  = "s&#7903; y t&#7871;";
    
    public static final String report_title_diaban = "th&#224;nh ph&#7889; &#273;&#224; n&#7861;ng";  // DA NANG
    //public static final String report_title_diaban = "t&#7881;nh &#273;&#7891;ng nai";                // DONG NAI
    //public static final String report_title_diaban = "t&#7881;nh b&#236;nh &#273;&#7883;nh";            // BINH DINH
    //public static final String report_title_diaban = "t&#7881;nh qu&#7843;ng nam";                // QUANG NAM
    //public static final String report_title_diaban = "t&#7881;nh th&#225;i b&#236;nh";            // THAI BINH
    //public static final String report_title_diaban = "t&#7881;nh t&#226;y ninh";                    // TAY NINH
    //public static final String report_title_diaban = "t&#7881;nh b&#236;nh ph&#432;&#7899;c";                    // BINH PHUOC
    //public static final String report_title_diaban = "t&#7881;nh ninh b&#236;nh";                    // BINH PHUOC
    
    public static final String report_title_1 = "danh s&#225;ch tr&#7867; khuy&#7871;t t&#7853;t t&#7915; 0 &#273;&#7871;n d&#432;&#7899;i 6 tu&#7893;i";
    public static final String report_title_2 = "danh s&#225;ch tr&#7867; khuy&#7871;t t&#7853;t t&#7915; 6 &#273;&#7871;n d&#432;&#7899;i 13 tu&#7893;i";
    public static final String report_title_3 = "danh s&#225;ch tr&#7867; khuy&#7871;t t&#7853;t t&#7915; 13 &#273;&#7871;n d&#432;&#7899;i 16 tu&#7893;i";
    public static final String report_title_4 = "danh s&#225;ch tr&#7867; khuy&#7871;t t&#7853;t t&#7915; 15 &#273;&#7871;n d&#432;&#7899;i 16 tu&#7893;i";
    public static final String report_title_5 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t t&#7915; 15 &#273;&#7871;n 55 tu&#7893;i (N&#7919;), 60 tu&#7893;i (Nam)";
    public static final String report_title_6 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t tr&#234;n 55 tu&#7893;i (N&#7919;), 60 tu&#7893;i (Nam)";
    public static final String report_title_8 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t chia theo d&#7841;ng t&#7853;t v&#224; m&#7913;c &#273;&#7897; khuy&#7871;t t&#7853;t";
    public static final String report_title_9 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t c&#243; kh&#7843; n&#259;ng lao &#273;&#7897;ng, c&#243; nhu c&#7847;u" +
                                                " h&#7885;c ngh&#7873;, vi&#7879;c l&#224;m t&#7915; 13 &#273;&#7871;n d&#432;&#7899;i 16 tu&#7893;i";
    public static final String report_title_10 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t c&#243; kh&#7843; n&#259;ng lao &#273;&#7897;ng, c&#243; nhu c&#7847;u" +
                                                " h&#7885;c ngh&#7873;, vi&#7879;c l&#224;m t&#7915; 15 &#273;&#7871;n 55 tu&#7889;i (N&#7919;), 60 tu&#7893;i (Nam)";
    public static final String report_title_11 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t c&#243; tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n, c&#243; nhu c&#7847;u" +
                                                " h&#7885;c v&#259;n h&#243;a t&#7915; 6 &#273;&#7871;n 18 tu&#7893;i";
    public static final String report_title_12 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t cao tu&#7893;i t&#7915; 60 tu&#7893;i &#273;&#7871;n 79 tu&#7893;i";
    public static final String report_title_13 = "danh s&#225;ch ng&#432;&#7901;i khuy&#7871;t t&#7853;t cao tu&#7893;i t&#7915; 80 tu&#7893;i tr&#7903; l&#234;n";
    
    public static final String report_title_14 = "t&#7893;ng h&#7907;p ng&#432;&#7901;i khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i";
    public static final String report_title_15 = "t&#7893;ng h&#7907;p chi ti&#7871;t c&#225;c d&#7841;ng v&#224; nguy&#234;n nh&#226;n khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i";
    public static final String report_title_16 = "t&#7893;ng h&#7907;p ng&#432;&#7901;i khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i";    
    public static final String report_title_17 = "t&#7893;ng h&#7907;p tr&#7867; khuy&#7871;t t&#7853;t t&#7915; 0 &#273;&#7871;n d&#432;&#7899;i 16 chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n";
    public static final String report_title_18 = "t&#7893;ng h&#7907;p ng&#432;&#7901;i khuy&#7871;t t&#7853;t t&#7915; 16 &#273;&#7871;n d&#432;&#7899;i 60 chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n";
    public static final String report_title_19 = "t&#7893;ng h&#7907;p ng&#432;&#7901;i khuy&#7871;t t&#7853;t t&#7915; 60 tu&#7893;i tr&#7903; l&#234;n chia theo tr&#236;nh &#273;&#7897; h&#7885;c v&#7845;n";
    public static final String report_title_20 = "t&#7893;ng h&#7907;p ng&#432;&#7901;i khuy&#7871;t t&#7853;t chia theo &#273;&#7897; tu&#7893;i";
    
    public static final String APP_PROJECT_YEAR = config.getString("config.year");
}
