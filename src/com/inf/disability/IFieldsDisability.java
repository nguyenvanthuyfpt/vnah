package com.inf.disability;


import com.inf.IParams;

public interface IFieldsDisability extends IParams {
        
        // SET OF FIELDS ON DR_CATEGORY_UNIT
        public final String DR_CATEGORY_UNIT_ID="ID";
        public final String DR_CATEGORY_UNIT_NAME="NAME";
        public final String[] DR_CATEGORY_UNIT_ALL_FIELDS = {DR_CATEGORY_UNIT_NAME};

        //  SET OF FIELDS ON DR_UNIT TABLES
        public final String DR_UNIT_ID="ID";
        public final String DR_UNIT_NAME="NAME";
        public final String DR_UNIT_ADDRESS="ADDRESS";
        public final String DR_UNIT_PHONE="PHONE";
        public final String DR_UNIT_FAX="FAX";
        public final String DR_UNIT_EMAIL="EMAIL";
        public final String DR_UNIT_CATEGORY_ID="CATEGORY_ID";
        public final String DR_UNIT_GIOI_THIEU="GIOI_THIEU";
        public final String DR_UNIT_NGUOI_LIEN_HE="NGUOI_LIEN_HE";
        public final String DR_UNIT_TINH_ID="TINH_ID";
        public final String[] DR_UNIT_ALL_FIELDS ={DR_UNIT_NAME,DR_UNIT_ADDRESS,DR_UNIT_PHONE,DR_UNIT_FAX,DR_UNIT_EMAIL,DR_UNIT_CATEGORY_ID,DR_UNIT_GIOI_THIEU,DR_UNIT_NGUOI_LIEN_HE,DR_UNIT_TINH_ID};
    
        // Set of Fields on DR_THONGTIN_TUYEN
        public final String DR_TT_TUYEN_ID              = "ID";
        public final String DR_TT_TUYEN_TINH_ID         = "TINH_ID";        
        public final String DR_TT_TUYEN_KY_BC           = "PERIOD";
        public final String DR_TT_TUYEN_NAM_BC          = "YEAR";
        public final String DR_TT_TUYEN_TOTAL_DANSO     = "TOTAL_POPULATION";
        public final String DR_TT_TUYEN_TOTAL_MALE      = "TOTAL_MALE";
        public final String DR_TT_TUYEN_TOTAL_FEMALE    = "TOTAL_FEMALE";
        public final String DR_TT_TUYEN_FEMALE_HAS_JOBS = "FEMALE_HAS_JOBS";        
        public final String DR_TT_TUYEN_HASJOBS         = "TOTAL_HAS_JOBS";
        public final String DR_TT_TUYEN_FEMALE_JOBSLESS = "FEMALE_JOBSLESS";
        public final String DR_TT_TUYEN_JOBSLESS        = "TOTAL_JOBSLESS";
        public final String DR_TT_TUYEN_CREATEDATE      = "CREATE_DATE";
        public final String[] DR_TT_TUYEN_ALL_FIELDS    = {DR_TT_TUYEN_TINH_ID,DR_TT_TUYEN_KY_BC, DR_TT_TUYEN_NAM_BC, DR_TT_TUYEN_TOTAL_DANSO,
                    DR_TT_TUYEN_TOTAL_MALE, DR_TT_TUYEN_TOTAL_FEMALE, 
                    DR_TT_TUYEN_FEMALE_HAS_JOBS, DR_TT_TUYEN_HASJOBS, 
                    DR_TT_TUYEN_FEMALE_JOBSLESS, DR_TT_TUYEN_JOBSLESS, DR_TT_TUYEN_CREATEDATE};
        
    
        //  SET OF FIELDS ON TINH TABLES
        public final String NKT_ID="ID";
        public final String NKT_MA="MA";
        public final String NKT_MASO="MASO";
        public final String NKT_TEN="TEN";
        public final String NKT_CMND="CMND";
        public final String NKT_SEX="SEX";
        public final String NKT_NGAYSINH="NGAYSINH";
        public final String NKT_SONHA="SONHA";
        public final String NKT_ID_TINH="ID_TINH";
        public final String NKT_ID_QHUYEN="ID_DISTRICT";
        public final String NKT_ID_PXA="ID_COMMUNE";
        public final String NKT_ID_DIEUKIEN="ID_DIEUKIEN";
        public final String NKT_CHUANDOAN="CHUANDOAN";
        public final String NKT_DIENTHOAI="DIENTHOAI";
        public final String NKT_THONTO="THONTO";
        public final String NKT_TRINHDO_ID="TRINHDO_ID";
        public final String NKT_VISIT_EMP_ID="VISIT_EMP_ID";
        public final String NKT_FILE_STORE="FILE_STORE";
        public final String NKT_FILE_READ="FILE_READ";
        public final String NKT_DINHDANG_NS="DINHDANG_NS";
        public final String NKT_DATE_LAST_UPDATE="DATE_LAST_UPDATE";
        
        public final String NKT_TT_HONNHAN="TT_HONNHAN";
        public final String NKT_TD_CHUYENMON="TD_CHUYENMON";
        public final String NKT_NGHE_NGHIEP_HT="NGHE_NGHIEP_HT";
        public final String NKT_CHUC_VU_HT="CHUC_VU_HT";    
        public final String NKT_DANTOC_ID="DANTOC_ID";  
        public final String NKT_TEN_NQL="ten_nql";    
        public final String NKT_DONVI_NQL="donvi_nql";
        public final String NKT_LINHVUC_NQL="linhvuc_nql";
        public final String NKT_TONGSOCON="tongsocon";    
        public final String NKT_CONDUOI16="conduoi16";    
        public final String NKT_DACAM="dacam";    
        public final String NKT_TEN_CHUHO="ten_chuho";    
        public final String NKT_NAMSINH_CHUHO="namsinh_chuho";    
        public final String NKT_QUANHE_CHUHO="quanhe_chuho";    
        public final String NKT_SONGUOI_CHUHO="songuoi_chuho";    
        public final String NKT_NKT_CHUHO="nkt_chuho";    
        public final String NKT_NGUONNUOC_CHUHO="nguonnuoc_chuho";    
        public final String NKT_NHAVS_CHUHO="nhavs_chuho";    
        public final String NKT_NHAVS_NKT_CHUHO="nhavs_nkt_chuho";    
        public final String NKT_NHAO_CHUHO="nhao_chuho"; 
        public final String NKT_TEN_NCS="ten_ncs";    
        public final String NKT_NAMSINH_NCS="namsinh_ncs";    
        public final String NKT_QUANHE_NCS="quanhe_ncs";    
        public final String NKT_SDT_NCS="sdt_ncs";
        
        // Add By ThuyNV      
        public final String NKT_CHUYENMON_KHAC="chuyenmon_khac";
        public final String NKT_NGHENGHIEP_KHAC="nghenghiep_khac";
        public final String NKT_HONNHAN_KHAC="honnhan_khac";
        public final String NKT_GIAODUC_KHAC = "giaoduc_khac";
        public final String NKT_CAPDO_ID = "capdo";
        public final String NKT_ID_TROCAP = "trocap_id";
        public final String NKT_TROCAP_KHAC = "trocap_khac";
        public final String NKT_TRANGTHAI = "trangthai";
        public final String NKT_KHANGCHIEN = "khangchien";
        public final String NKT_DOITUONG = "doituong_id";
        public final String NKT_GIOITINH_NCS = "gioitinh_ncs";
        public final String NKT_DUAN = "du_an";
        
        public final String[] NKT_ALL_FIELDS = {NKT_MA,NKT_MASO,NKT_TEN,NKT_CMND,NKT_SEX,NKT_NGAYSINH,NKT_SONHA,NKT_ID_TINH,NKT_ID_QHUYEN, NKT_ID_PXA,
            NKT_ID_DIEUKIEN,NKT_CHUANDOAN,NKT_DIENTHOAI,NKT_THONTO,NKT_TRINHDO_ID,NKT_VISIT_EMP_ID,
            NKT_FILE_STORE,NKT_FILE_READ,NKT_DINHDANG_NS,NKT_DATE_LAST_UPDATE,NKT_TT_HONNHAN,
            NKT_TD_CHUYENMON,NKT_NGHE_NGHIEP_HT,NKT_CHUC_VU_HT,NKT_DANTOC_ID,
            NKT_TEN_NQL,NKT_DONVI_NQL,NKT_LINHVUC_NQL,NKT_TONGSOCON,NKT_CONDUOI16,NKT_DACAM,
            NKT_TEN_CHUHO,NKT_NAMSINH_CHUHO,NKT_QUANHE_CHUHO,NKT_SONGUOI_CHUHO, NKT_NKT_CHUHO,
            NKT_NGUONNUOC_CHUHO,NKT_NHAVS_CHUHO,NKT_NHAVS_NKT_CHUHO,NKT_NHAO_CHUHO,NKT_TEN_NCS,NKT_NAMSINH_NCS,
            NKT_QUANHE_NCS,NKT_SDT_NCS,NKT_CHUYENMON_KHAC,NKT_NGHENGHIEP_KHAC,NKT_HONNHAN_KHAC,NKT_GIAODUC_KHAC,
            NKT_ID_TROCAP,NKT_TROCAP_KHAC,NKT_KHANGCHIEN,NKT_DOITUONG,NKT_GIOITINH_NCS,NKT_DUAN
        }; 
        
        public final String[] NKT_ALL_FIELDS_KPI = {NKT_ID_TINH,NKT_ID_QHUYEN,NKT_ID_PXA,NKT_MA,NKT_DATE_LAST_UPDATE,
                                                    NKT_TEN, NKT_CMND, NKT_DIENTHOAI, NKT_NGAYSINH, NKT_SEX, NKT_SONHA,
                                                    NKT_DANTOC_ID, NKT_NGHE_NGHIEP_HT, NKT_DACAM,
                                                    NKT_TEN_NCS, NKT_NAMSINH_NCS, NKT_QUANHE_NCS, NKT_SDT_NCS, NKT_GIOITINH_NCS, NKT_DUAN};  
    
        //  SET OF FIELDS ON TN_NKT TABLES
        public final String TN_NKT_ID="ID";
        public final String TN_NKT_ID_NKT="ID_NKT";
        public final String TN_NKT_ID_RELATIVE_NKT="ID_RELATIVE_NKT";
        public final String TN_NKT_ID_LYDO="ID_LYDO";
    
        public final String[] TN_NKT_ALL_FIELDS = {TN_NKT_ID_NKT,TN_NKT_ID_RELATIVE_NKT,TN_NKT_ID_LYDO}; 
    
    
        //  SET OF FIELDS ON TINH TABLES
         public final String TINH_TINH_ID="TINH_ID";
         public final String TINH_CODE="CODE";
         public final String TINH_NAME="NAME";
         public final String TINH_PARENT_ID="PARENT_ID";
         public final String TINH_MAX_USERS="MAX_USERS";
         public final String TINH_ORDER="order_by";
         
         public final String[] TINH_ALL_FIELDS = {TINH_CODE,TINH_NAME,TINH_PARENT_ID}; 
         
    //  SET OF FIELDS ON TINH TABLES
         public final String DANGTAT_DANGTAT_ID="DANGTAT_ID";
         public final String DANGTAT_CODE="CODE";
         public final String DANGTAT_NAME="NAME";
         public final String DANGTAT_PARENT_ID="PARENT_ID";
         public final String DANGTAT_MAX_USERS="MAX_USERS";
         public final String DANGTAT_OTHER="IS_OTHER";   // Add By ThuyNV
         public final String DANGTAT_OTHER_CONTENT="OTHER";
         public final String[] DANGTAT_ALL_FIELDS = {DANGTAT_CODE,DANGTAT_NAME,DANGTAT_PARENT_ID,DANGTAT_OTHER,DANGTAT_OTHER_CONTENT}; 
         
    //  SET OF FIELDS ON DIEU KIEN TABLES
         public final String DIEUKIEN_DIEUKIEN_ID="DIEUKIEN_ID";
         public final String DIEUKIEN_CODE="CODE";
         public final String DIEUKIEN_NAME="NAME";
         public final String DIEUKIEN_PARENT_ID="PARENT_ID";
         public final String DIEUKIEN_MAX_USERS="MAX_USERS";
         public final String[] DIEUKIEN_ALL_FIELDS = {DIEUKIEN_CODE,DIEUKIEN_NAME,DIEUKIEN_PARENT_ID}; 
         
    //  SET OF FIELDS ON QUAN HE TABLES
         public final String LYDO_LYDO_ID="LYDO_ID";
         public final String LYDO_CODE="CODE";
         public final String LYDO_NAME="NAME";
         public final String LYDO_PARENT_ID="PARENT_ID";
         public final String LYDO_MAX_USERS="MAX_USERS";
         public final String[] LYDO_ALL_FIELDS = {LYDO_CODE,LYDO_NAME,LYDO_PARENT_ID}; 
        
        // QUAN HE 
        public final String QUANHE_ID="QUANHE_ID";
        public final String QUANHE_CODE="CODE";
        public final String QUANHE_NAME="NAME";
        public final String QUANHE_PARENT_ID="PARENT_ID";
        public final String QUANHE_MAX_USERS="MAX_USERS";
        public final String[] QUANHE_ALL_FIELDS = {QUANHE_CODE,QUANHE_NAME,QUANHE_PARENT_ID}; 

         
    //  SET OF FIELDS ON DON VI  TABLES
         public final String DONVI_DONVI_ID="DONVI_ID";
         public final String DONVI_CODE="CODE";
         public final String DONVI_NAME="NAME";
         public final String DONVI_PARENT_ID="PARENT_ID";
         public final String DONVI_MAX_USERS="MAX_USERS";
         public final String[] DONVI_ALL_FIELDS = {DONVI_CODE,DONVI_NAME,DONVI_PARENT_ID}; 

    //  SET OF FIELDS ON NGUYEN NHAN KHUYET TAT  TABLES
         public final String NGUYENNHAN_NNHAN_ID="NGUYENNHAN_ID";
         public final String NGUYENNHAN_NNHAN_CODE="CODE";
         public final String NGUYENNHAN_NNHAN_NAME="NAME";
         public final String NGUYENNHAN_NNHAN_PARENT_ID="PARENT_ID";
         public final String NGUYENNHAN_NNHAN_POSITION="POSITION";
         public final String[] NGUYENNHAN_ALL_FIELDS = {NGUYENNHAN_NNHAN_CODE,NGUYENNHAN_NNHAN_NAME,NGUYENNHAN_NNHAN_PARENT_ID}; 
         
   //  SET OF FIELDS ON NGUYEN NHAN DOI TUONG  TABLES
       public final String DOITUONG_ID="DOITUONG_ID";
       public final String DOITUONG_CODE="CODE";
       public final String DOITUONG_NAME="NAME";
       public final String DOITUONG_PARENT_ID="PARENT_ID";
       public final String DOITUONG_POSITION="POSITION";
       public final String[] DOITUONG_ALL_FIELDS = {DOITUONG_CODE,DOITUONG_NAME,DOITUONG_PARENT_ID}; 
         
    //  SET OF FIELDS ON MUC DO  TABLES
        public final String MUCDO_ID="MUCDO_ID";
        public final String MUCDO_CODE="CODE";
        public final String MUCDO_NAME="NAME";
        public final String MUCDO_PARENT_ID="PARENT_ID";
        public final String MUCDO_MAX_USERS="MAX_USERS";
        public final String[] MUCDO_ALL_FIELDS = {MUCDO_CODE,MUCDO_NAME,MUCDO_PARENT_ID};     
         
    //  SET OF FIELDS ON DAN TOC  TABLES
         public final String DANTOC_DANTOC_ID="DANTOC_ID";
         public final String DANTOC_CODE="CODE";
         public final String DANTOC_NAME="NAME";
         public final String DANTOC_PARENT_ID="PARENT_ID";
         public final String DANTOC_MAX_USERS="MAX_USERS";
         public final String[] DANTOC_ALL_FIELDS = {DANTOC_CODE,DANTOC_NAME,DANTOC_PARENT_ID}; 
         
    //  SET OF FIELDS ON DIEU KIEN TABLES
         public final String DUNGCU_DUNGCU_ID="DUNGCU_ID";
         public final String DUNGCU_CODE="CODE";
         public final String DUNGCU_NAME="NAME";
         public final String DUNGCU_PARENT_ID="PARENT_ID";
         public final String DUNGCU_MAX_USERS="MAX_USERS";
         public final String[] DUNGCU_ALL_FIELDS = {DUNGCU_CODE,DUNGCU_NAME,DUNGCU_PARENT_ID}; 
         
    //  SET OF FIELDS ON HOTRO TABLES
         public final String DM_HOTRO_HOTRO_ID="HOTRO_ID";
         public final String DM_HOTRO_CODE="CODE";
         public final String DM_HOTRO_NAME="NAME";
         public final String DM_HOTRO_PARENT_ID="PARENT_ID";
         public final String DM_HOTRO_MAX_USERS="MAX_USERS";
         public final String[] DM_HOTRO_ALL_FIELDS = {DM_HOTRO_CODE,DM_HOTRO_NAME,DM_HOTRO_PARENT_ID}; 
         
    //  SET OF FIELDS ON DANH GIA TABLES
         public final String DANHGIA_DANHGIA_ID="TINHTRANG_ID";
         public final String DANHGIA_CODE="CODE";
         public final String DANHGIA_NAME="NAME";
         public final String DANHGIA_PARENT_ID="PARENT_ID";
         public final String DANHGIA_MAX_USERS="MAX_USERS";
         public final String[] DANHGIA_ALL_FIELDS = {DANHGIA_CODE,DANHGIA_NAME,DANHGIA_PARENT_ID}; 
         
        //  SET OF FIELDS ON PHAN LOAI TABLES
        public final String PHANLOAI_ID="ID";
        public final String PHANLOAI_ID_NKT="ID_NKT";
        public final String PHANLOAI_USER_ID="USER_ID";
        public final String PHANLOAI_DATECREATE="DATECREATE";
        public final String PHANLOAI_RESON="RESON";
        public final String PHANLOAI_DANGTAT_IDS="DANGTAT_IDS";        
        public final String PHANLOAI_NGUYENNHAN_ID="NGUYENNHAN_ID";
        public final String PHANLOAI_VANDONG_KHAC="VANDONG_KHAC";
        public final String PHANLOAI_KHUYETTAT_KHAC="KHUYETTAT_KHAC";
        public final String PHANLOAI_NGUYENNHAN_KHAC="NGUYENNHAN_KHAC";
        public final String PHANLOAI_CAPDO = "MUCDO_ID";
        public final String PHANLOAI_THOIDIEM = "THOIDIEM_KT";
        public final String PHANLOAI_THOIDIEM_TAIKHAM = "thoidiem_taikham"; 
        public final String PHANLOAI_DIADIEM = "diadiem_kham";
        public final String[] PHANLOAI_ALL_FIELDS = {PHANLOAI_ID_NKT,PHANLOAI_USER_ID,PHANLOAI_DATECREATE,PHANLOAI_RESON,
            PHANLOAI_DANGTAT_IDS,PHANLOAI_NGUYENNHAN_ID,
            PHANLOAI_VANDONG_KHAC,PHANLOAI_KHUYETTAT_KHAC,PHANLOAI_NGUYENNHAN_KHAC,PHANLOAI_CAPDO,
            PHANLOAI_THOIDIEM, PHANLOAI_THOIDIEM_TAIKHAM, PHANLOAI_DIADIEM}; 
        
       //  SET OF FIELDS ON RANK TABLES
        public final String RANK_ID="ID";
        public final String RANK_ID_NKT="ID_NKT";
        public final String RANK_USER_ID="USER_ID";
        public final String RANK_DATECREATE="DATECREATE";
        public final String RANK_RESON="RESON";
        public final String RANK_DANHGIA_IDS="DANHGIA_IDS";
        public final String RANK_TOCHUC_KHAC="TOCHUC_KHAC";
        
        public final String[] RANK_ALL_FIELDS = {RANK_ID_NKT,RANK_USER_ID,RANK_DATECREATE,RANK_RESON,RANK_DANHGIA_IDS,RANK_TOCHUC_KHAC};          
     
        //  SET OF FIELDS ON POPULATION TABLES
        public final String PPLT_ID="ID";
        public final String PPLT_ID_TINH="ID_TINH";
        public final String PPLT_DATEEDIT="DATEEDIT";
        public final String PPLT_MALE_LESS_THAN_18="MALE_LESS_THAN_18";
        public final String PPLT_MALE_MORE_THAN_18="MALE_MORE_THAN_18";
        public final String PPLT_FAMALE_LESS_THAN_18="FAMALE_LESS_THAN_18";
        public final String PPLT_FAMALE_MORE_THAN_18="FAMALE_MORE_THAN_18";
        public final String PPLT_INFO_ACTIVE="INFO_ACTIVE";
        public final String PPLT_NUMBER_1="NUMBER_1";
        public final String PPLT_NUMBER_2="NUMBER_2";
        public final String PPLT_NUMBER_3="NUMBER_3";
        public final String PPLT_NUMBER_4="NUMBER_4";
        public final String PPLT_NUMBER_5="NUMBER_5";
        public final String PPLT_NUMBER_6="NUMBER_6";
        public final String PPLT_NUMBER_7="NUMBER_7";
        public final String PPLT_NUMBER_8="NUMBER_8";
        public final String PPLT_NUMBER_9="NUMBER_9";
        public final String PPLT_NUMBER_10="NUMBER_10";
        public final String PPLT_NUMBER_11="NUMBER_11";
        public final String PPLT_NUMBER_12="NUMBER_12";
        public final String PPLT_NUMBER_13="NUMBER_13";
        public final String PPLT_NUMBER_14="NUMBER_14";
        public final String PPLT_NUMBER_15="NUMBER_15";
    
        public final String PPLT_PERIOD="PERIOD";
        public final String PPLT_YEAROFPERIOD="YEAROFPERIOD";
    
        public final String[] POPULATION_ALL_FIELDS = {PPLT_ID_TINH,PPLT_DATEEDIT,PPLT_MALE_LESS_THAN_18,PPLT_MALE_MORE_THAN_18,PPLT_FAMALE_LESS_THAN_18,PPLT_FAMALE_MORE_THAN_18,PPLT_INFO_ACTIVE,PPLT_NUMBER_1,PPLT_NUMBER_2,PPLT_NUMBER_3,PPLT_NUMBER_4,PPLT_NUMBER_5,PPLT_NUMBER_6,PPLT_NUMBER_7,PPLT_NUMBER_8,PPLT_NUMBER_9,PPLT_NUMBER_10,PPLT_NUMBER_11,PPLT_NUMBER_12,PPLT_NUMBER_13,PPLT_NUMBER_14,PPLT_NUMBER_15,PPLT_PERIOD,PPLT_YEAROFPERIOD}; 

    //  SET OF FIELDS ON POPULATION TABLES
        public final String DR_REPORT_TEMP_ID="TEMP_ID";
        public final String DR_REPORT_NKT_ID="NKT_ID";
        public final String[] NKT_REPORT_ALL_FIELDS = {DR_REPORT_TEMP_ID,DR_REPORT_NKT_ID}; 

         
    //  SET OF FIELDS ON PHAN LOAI TABLES
        public final String HOTRO_ID="ID";
        public final String HOTRO_ID_NKT="ID_NKT";
        public final String HOTRO_USER_ID="USER_ID";
        public final String HOTRO_DATECREATE="DATECREATE";
        public final String HOTRO_RESON="RESON";
        public final String HOTRO_DM_HOTRO_IDS="DM_HOTRO_IDS";
        public final String HOTRO_STATUS_ID="STATUS_ID";
        public final String HOTRO_DATEFORM="DATEFORM";
        public final String HOTRO_DATETO="DATETO";
        public final String HOTRO_NGUONHOTRO="NGUONHOTRO";
        public final String HOTRO_NGUONHOTRO_ID="NGUONHOTRO_ID";
        
        public final String HOTRO_MACBENH = "MACBENH";
        public final String HOTRO_DUNGCU_KHAC = "DUNGCU_KHAC";
        public final String HOTRO_PHAUTHUAT_KHAC = "PHAUTHUAT_KHAC";
        public final String HOTRO_YTE_KHAC = "YTE_KHAC";
        public final String HOTRO_TROCAP_THUONGXUYEN_KHAC = "TROCAP_THUONGXUYEN_KHAC";
        public final String HOTRO_TROCAP_DOTXUAT_KHAC = "TROCAP_DOTXUAT_KHAC";
        public final String HOTRO_CAITHIEN_KHAC = "CAITHIEN_KHAC";
        public final String HOTRO_LOAIVAY_KHAC = "LOAIVAY_KHAC";
        public final String HOTRO_SOTIENVAY_KHAC = "SOTIENVAY_KHAC";
        public final String HOTRO_MUCDICHVAY_KHAC = "MUCDICHVAY_KHAC";
        public final String HOTRO_TOCHUCXAHOI_KHAC = "TOCHUCXAHOI_KHAC";
        public final String HOTRO_NHUCAU_DOISONG_KHAC = "NHUCAU_DOISONG_KHAC";
        public final String HOTRO_NHUCAU_GIAODUC_KHAC = "NHUCAU_GIAODUC_KHAC";
        
        public final String HOTRO_KN_CHITRA = "kn_chitra";
        public final String HOTRO_THE_BHYT = "the_bhyt";
        public final String HOTRO_SD_THE = "sd_the";
        public final String HOTRO_SD_THE_PHCN = "sd_the_phcn";
        public final String HOTRO_MTIEU_GDINH = "mtieu_gdinh";
        public final String HOTRO_MTIEU_DTRI = "mtieu_dtri";
        public final String HOTRO_CT_VLTL = "ct_vltl";
        public final String HOTRO_CT_HDTL = "ct_hdtl";
        public final String HOTRO_MDO_PTDL = "mdo_ptdl";
        public final String HOTRO_MDO_HLONG = "mdo_hlong";
        public final String HOTRO_CT_ANTL = "ct_antl";
        public final String HOTRO_THOIDIEM_TAIKHAM = "thoidiem_taikham"; 
        public final String HOTRO_DIADIEM = "diadiem_kham";
        public final String HOTRO_DOITUONG = "doi_tuong";
        public final String HOTRO_CT_GDDB = "ct_gddb";
        public final String HOTRO_CT_CSGN = "ct_csgn";
        public final String HOTRO_STT = "stt";
        public final String HOTRO_THIEN_TEN = "ngthien_ten";
        public final String HOTRO_THIEN_CVU = "ngthien_cvu";
        
        public final String[] HOTRO_ALL_FIELDS = {HOTRO_ID_NKT,HOTRO_USER_ID,HOTRO_DATECREATE,HOTRO_RESON,HOTRO_DM_HOTRO_IDS,HOTRO_STATUS_ID,
            HOTRO_DATEFORM,HOTRO_DATETO,HOTRO_NGUONHOTRO,HOTRO_NGUONHOTRO_ID,
            HOTRO_MACBENH,HOTRO_DUNGCU_KHAC,HOTRO_PHAUTHUAT_KHAC,HOTRO_YTE_KHAC,
            HOTRO_TROCAP_THUONGXUYEN_KHAC,HOTRO_TROCAP_DOTXUAT_KHAC,HOTRO_CAITHIEN_KHAC,
            HOTRO_LOAIVAY_KHAC,HOTRO_SOTIENVAY_KHAC,HOTRO_MUCDICHVAY_KHAC,HOTRO_TOCHUCXAHOI_KHAC,
            HOTRO_NHUCAU_DOISONG_KHAC,HOTRO_NHUCAU_GIAODUC_KHAC,
            HOTRO_KN_CHITRA,HOTRO_THE_BHYT,HOTRO_SD_THE,HOTRO_SD_THE_PHCN,
            HOTRO_MTIEU_GDINH,HOTRO_MTIEU_DTRI,HOTRO_CT_VLTL,HOTRO_CT_HDTL,HOTRO_MDO_PTDL,HOTRO_MDO_HLONG,
            HOTRO_CT_ANTL,HOTRO_THOIDIEM_TAIKHAM,HOTRO_DIADIEM, HOTRO_DOITUONG, HOTRO_CT_GDDB, HOTRO_CT_CSGN, 
            HOTRO_STT,HOTRO_THIEN_TEN,HOTRO_THIEN_CVU}; 
    
    //  SET OF FIELDS ON DANH GIA TABLES
         public final String DANHGIA_NKT_ID="ID";
         public final String DANHGIA_NKT_ID_NKT="ID_NKT";
         public final String DANHGIA_NKT_DATECREATE="DATECREATE";
         public final String DANHGIA_NKT_USER_ID="USER_ID";
         public final String DANHGIA_NKT_STATUS="STATUS";
         public final String DANHGIA_NKT_DANHGIA="DANHGIA";
         public final String DANHGIA_NKT_TRONGKY="TRONGKY";
         public final String DANHGIA_NKT_BATDAU="BATDAU";
         
         public final String DANHGIA_NKT_YTE_SUCKHOE="YTE";
         public final String DANHGIA_NKT_KINHTE_XAHOI="KINHTE_XAHOI";
         public final String DANHGIA_NKT_GIAODUC="GIAODUC";
         public final String[] DANHGIA_NKT_ALL_FIELDS = {DANHGIA_NKT_ID_NKT,DANHGIA_NKT_DATECREATE,DANHGIA_NKT_USER_ID,DANHGIA_NKT_STATUS,
            DANHGIA_NKT_DANHGIA,DANHGIA_NKT_TRONGKY,DANHGIA_NKT_BATDAU,DANHGIA_NKT_YTE_SUCKHOE,DANHGIA_NKT_KINHTE_XAHOI, DANHGIA_NKT_GIAODUC}; 
    
    //  SET OF FIELDS ON LISTREPORT TABLES                                                     
         public final String LISTREPORT_LIST_ID="LIST_ID";
         public final String LISTREPORT_LIST_CODE="CODE";
         public final String LISTREPORT_LIST_NAME="LIST_NAME";
         public final String LISTREPORT_EMP_ESTABLISHED="EMP_ESTABLISHED";
         public final String LISTREPORT_DATE_CREATE="DATE_CREATE";
         public final String LISTREPORT_USER_ID="USER_ID";     
         public final String[] LISTREPORT_ALL_FIELDS = {LISTREPORT_LIST_CODE,LISTREPORT_LIST_NAME,LISTREPORT_DATE_CREATE,LISTREPORT_USER_ID}; 
         
     //  SET OF FIELDS ON LIST_EMP TABLES                                                     
         public final String LIST_EMP_LIST_ID="LIST_ID";
         public final String LIST_EMP_EMPLOYEE_ID="EMPLOYEE_ID";   
         public final String LIST_EMP_LIST_ID_TEMP="LIST_ID_TEMP";
         public final String LIST_EMP_EMPLOYEE_ID_TEMP="EMPLOYEE_ID_TEMP";   
         public final String LIST_EMP_AMOUNT="AMOUNT";  
         public final String[] LIST_EMP_ALL_FIELDS={LIST_EMP_LIST_ID,LIST_EMP_EMPLOYEE_ID};
         
    //  SET OF FIELDS ON  DANSOHUYEN TABLES
        public final String DANSOHUYEN_ID="ID";
        public final String DANSOHUYEN_ID_PROVINCE="ID_PROVINCE";//TINH
        public final String DANSOHUYEN_PERIOD="PERIOD";//KY BAO CAO 
        public final String DANSOHUYEN_YEAROFPERIOD="YEAROFPERIOD";//NAM BAO CAO 
        public final String DANSOHUYEN_NOOFCOMMUNE="NOOFCOMMUNE";//TONG XA TRONG HUYEN
        public final String DANSOHUYEN_NOOFCOMMUNEVNAH="NOOFCOMMUNEVNAH";//SO XA CO CHUONG TRINH VNAH
        public final String DANSOHUYEN_LASTUPDATE="LASTUPDATE";//NGAY CAP NHAT
        public final String DANSOHUYEN_PARAMVALUE_1="PARAMVALUE_1";
        public final String DANSOHUYEN_PARAMVALUE_2="PARAMVALUE_2";
        public final String DANSOHUYEN_PARAMVALUE_3="PARAMVALUE_3";
        public final String DANSOHUYEN_PARAMVALUE_4="PARAMVALUE_4";
        public final String DANSOHUYEN_PARAMVALUE_5="PARAMVALUE_5";
        public final String DANSOHUYEN_PARAMVALUE_6="PARAMVALUE_6";
        public final String DANSOHUYEN_PARAMVALUE_7="PARAMVALUE_7";
        public final String DANSOHUYEN_PARAMVALUE_8="PARAMVALUE_8";
        public final String DANSOHUYEN_PARAMVALUE_9="PARAMVALUE_9";
        public final String DANSOHUYEN_PARAMVALUE_10="PARAMVALUE_10";
        public final String DANSOHUYEN_PARAMVALUE_11="PARAMVALUE_11";
        public final String DANSOHUYEN_PARAMVALUE_12="PARAMVALUE_12";
        public final String DANSOHUYEN_PARAMVALUE_13="PARAMVALUE_13";
        public final String DANSOHUYEN_PARAMVALUE_14="PARAMVALUE_14";
        public final String DANSOHUYEN_PARAMVALUE_15="PARAMVALUE_15";
        public final String DANSOHUYEN_PARAMVALUE_16="PARAMVALUE_16";
        public final String DANSOHUYEN_PARAMVALUE_17="PARAMVALUE_17";
        public final String DANSOHUYEN_PARAMVALUE_18="PARAMVALUE_18";
        public final String DANSOHUYEN_PARAMVALUE_19="PARAMVALUE_19";
        public final String DANSOHUYEN_PARAMVALUE_20="PARAMVALUE_20";
        public final String DANSOHUYEN_PARAMVALUE_21="PARAMVALUE_21";
        public final String DANSOHUYEN_PARAMVALUE_22="PARAMVALUE_22";
        public final String DANSOHUYEN_PARAMVALUE_23="PARAMVALUE_23";
        public final String DANSOHUYEN_PARAMVALUE_24="PARAMVALUE_24";
        public final String DANSOHUYEN_PARAMVALUE_25="PARAMVALUE_25";
        public final String DANSOHUYEN_PARAMVALUE_26="PARAMVALUE_26";
        
        
        public final String[] DANSOHUYEN_ALL_FIELDS = {DANSOHUYEN_ID_PROVINCE,DANSOHUYEN_PERIOD,DANSOHUYEN_YEAROFPERIOD,DANSOHUYEN_NOOFCOMMUNE,DANSOHUYEN_NOOFCOMMUNEVNAH,DANSOHUYEN_LASTUPDATE,DANSOHUYEN_PARAMVALUE_1,DANSOHUYEN_PARAMVALUE_2,DANSOHUYEN_PARAMVALUE_3,DANSOHUYEN_PARAMVALUE_4,DANSOHUYEN_PARAMVALUE_5,DANSOHUYEN_PARAMVALUE_6,DANSOHUYEN_PARAMVALUE_7,DANSOHUYEN_PARAMVALUE_8,DANSOHUYEN_PARAMVALUE_9,DANSOHUYEN_PARAMVALUE_10,DANSOHUYEN_PARAMVALUE_11,DANSOHUYEN_PARAMVALUE_12,DANSOHUYEN_PARAMVALUE_13,DANSOHUYEN_PARAMVALUE_14,DANSOHUYEN_PARAMVALUE_15,DANSOHUYEN_PARAMVALUE_16,DANSOHUYEN_PARAMVALUE_17,DANSOHUYEN_PARAMVALUE_18,DANSOHUYEN_PARAMVALUE_19,DANSOHUYEN_PARAMVALUE_20,DANSOHUYEN_PARAMVALUE_21,DANSOHUYEN_PARAMVALUE_22,DANSOHUYEN_PARAMVALUE_23,DANSOHUYEN_PARAMVALUE_24,DANSOHUYEN_PARAMVALUE_25,DANSOHUYEN_PARAMVALUE_26}; 
    
    //  SET OF FIELDS ON  DANSOTINH TABLES
        public final String DANSOTINH_ID="ID";
        public final String DANSOTINH_ID_PROVINCE="ID_PROVINCE";//TINH
        public final String DANSOTINH_PERIOD="PERIOD";//KY BAO CAO 
        public final String DANSOTINH_YEAROFPERIOD="YEAROFPERIOD";//NAM BAO CAO 
        public final String DANSOTINH_LASTUPDATE="LASTUPDATE";//TONG XA TRONG HUYEN
        public final String DANSOTINH_NOOFDISTRICT="NO_OF_DISTRICT";//SO XA CO CHUONG TRINH VNAH
        public final String DANSOTINH_NOOFDISTRICTCBR="NO_OF_DISTRICT_CBR";//
        public final String DANSOTINH_NOOFCOMMUNE="NO_OF_COMMUNE";//
        public final String DANSOTINH_NOOFCOMMUNECBR="NO_OF_COMMUNE_CBR";//
        public final String DANSOTINH_NOTEACHER="NO_TEACHER";//
        public final String DANSOTINH_PARAMVALUE_1="PARAMVALUE_1";
        public final String DANSOTINH_PARAMVALUE_2="PARAMVALUE_2";
        public final String DANSOTINH_PARAMVALUE_3="PARAMVALUE_3";
        public final String DANSOTINH_PARAMVALUE_4="PARAMVALUE_4";
        public final String DANSOTINH_PARAMVALUE_5="PARAMVALUE_5";
        public final String DANSOTINH_PARAMVALUE_6="PARAMVALUE_6";
        public final String DANSOTINH_PARAMVALUE_7="PARAMVALUE_7";
        public final String DANSOTINH_PARAMVALUE_8="PARAMVALUE_8";
        public final String DANSOTINH_PARAMVALUE_9="PARAMVALUE_9";
        public final String DANSOTINH_PARAMVALUE_10="PARAMVALUE_10";
        public final String DANSOTINH_PARAMVALUE_11="PARAMVALUE_11";
        public final String DANSOTINH_PARAMVALUE_12="PARAMVALUE_12";
        public final String DANSOTINH_PARAMVALUE_13="PARAMVALUE_13";
        public final String DANSOTINH_PARAMVALUE_14="PARAMVALUE_14";
        public final String DANSOTINH_PARAMVALUE_15="PARAMVALUE_15";
        public final String DANSOTINH_PARAMVALUE_16="PARAMVALUE_16";
        public final String DANSOTINH_PARAMVALUE_17="PARAMVALUE_17";
        public final String DANSOTINH_PARAMVALUE_18="PARAMVALUE_18";
        public final String DANSOTINH_PARAMVALUE_19="PARAMVALUE_19";
        public final String DANSOTINH_PARAMVALUE_20="PARAMVALUE_20";
        public final String DANSOTINH_PARAMVALUE_21="PARAMVALUE_21";
        public final String DANSOTINH_PARAMVALUE_22="PARAMVALUE_22";
        public final String DANSOTINH_PARAMVALUE_23="PARAMVALUE_23";
        public final String DANSOTINH_PARAMVALUE_24="PARAMVALUE_24";
        public final String DANSOTINH_PARAMVALUE_25="PARAMVALUE_25";
        public final String DANSOTINH_PARAMVALUE_26="PARAMVALUE_26";
        public final String DANSOTINH_PARAMVALUE_27="PARAMVALUE_27";
        public final String DANSOTINH_PARAMVALUE_28="PARAMVALUE_28";
        public final String[] DANSOTINH_ALL_FIELDS = {DANSOTINH_ID_PROVINCE,DANSOTINH_PERIOD,DANSOTINH_YEAROFPERIOD,DANSOTINH_LASTUPDATE,DANSOTINH_NOOFDISTRICT,DANSOTINH_NOOFDISTRICTCBR,DANSOTINH_NOOFCOMMUNE,DANSOTINH_NOOFCOMMUNECBR,DANSOTINH_NOTEACHER,DANSOTINH_PARAMVALUE_1,DANSOTINH_PARAMVALUE_2,DANSOTINH_PARAMVALUE_3,DANSOTINH_PARAMVALUE_4,DANSOTINH_PARAMVALUE_5,DANSOTINH_PARAMVALUE_6,DANSOTINH_PARAMVALUE_7,DANSOTINH_PARAMVALUE_8,DANSOTINH_PARAMVALUE_9,DANSOTINH_PARAMVALUE_10,DANSOTINH_PARAMVALUE_11,DANSOTINH_PARAMVALUE_12,DANSOTINH_PARAMVALUE_13,DANSOTINH_PARAMVALUE_14,DANSOTINH_PARAMVALUE_15,DANSOTINH_PARAMVALUE_16,DANSOTINH_PARAMVALUE_17,DANSOTINH_PARAMVALUE_18,DANSOTINH_PARAMVALUE_19,DANSOTINH_PARAMVALUE_20,DANSOTINH_PARAMVALUE_21,DANSOTINH_PARAMVALUE_22,DANSOTINH_PARAMVALUE_23,DANSOTINH_PARAMVALUE_24,DANSOTINH_PARAMVALUE_25,DANSOTINH_PARAMVALUE_26,DANSOTINH_PARAMVALUE_27,DANSOTINH_PARAMVALUE_28}; 
        
        
        public final String INFOR_NKT_TEMP_IDS="TEMP_ID";
        public final String INFOR_NKT_NKT_ID="NKT_ID";
        public final String[] INFOR_NKT_ALL_FIELDS = {INFOR_NKT_TEMP_IDS,INFOR_NKT_NKT_ID}; 
        
        // Table Nguon Ho Tro
        public final String NGUONHOTRO_ID="nguonhotro_id";
        public final String NGUONHOTRO_PARENT_ID="parent_id";
        public final String NGUONHOTRO_CODE="code";
        public final String NGUONHOTRO_NAME="name";
        public final String NGUONHOTRO_DESCRIPTION="description";
        public final String NGUONHOTRO_POSITION="position";
        public final String NGUONHOTRO_CREATEDATE="datecreate";
        public final String[] NGUONHOTRO_ALL_FIELDS = {NGUONHOTRO_PARENT_ID, NGUONHOTRO_CODE, NGUONHOTRO_NAME, NGUONHOTRO_DESCRIPTION, NGUONHOTRO_POSITION,NGUONHOTRO_CREATEDATE};
        
        // Table Trinh do van hoa
        public final String VANHOA_ID="id";
        public final String VANHOA_CODE="CODE";
        public final String VANHOA_NAME="NAME";
        public final String VANHOA_PARENT_ID="PARENT_ID";
        public final String VANHOA_MAX_USERS="MAX_USERS";
        public final String VANHOA_POSITION="position";
        public final String[] VANHOA_ALL_FIELDS = {VANHOA_CODE,VANHOA_NAME,VANHOA_PARENT_ID};
        
        // 1. KPI_OBJECT
        public final String KPI_OBJECT_ID="id";
        public final String KPI_OBJECT_PARENT_ID="parent_id";
        public final String KPI_OBJECT_CREATE_DATE="create_date";
        public final String KPI_OBJECT_MODIFY_DATE="modify_date";
        public final String KPI_OBJECT_CODE="code";
        public final String KPI_OBJECT_NAME="name";
        public final String KPI_OBJECT_DESCRIPTION="description";
        public final String KPI_OBJECT_TYPE="type";
        public final String[] KPI_OBJECT_ALL_FIELDS = {KPI_OBJECT_PARENT_ID, KPI_OBJECT_CREATE_DATE, KPI_OBJECT_MODIFY_DATE, 
                                                       KPI_OBJECT_CODE, KPI_OBJECT_NAME,KPI_OBJECT_DESCRIPTION,KPI_OBJECT_TYPE};
        
        // 2. KPI_EVENT
        public final String KPI_EVENT_ID="id";
        public final String KPI_EVENT_LOCATION_ID="location_id";
        public final String KPI_EVENT_CODE="code";
        public final String KPI_EVENT_EVENT_TYPE="event_type";
        public final String KPI_EVENT_EVENT_FIELD="event_field";
        public final String KPI_EVENT_CREATE_DATE="create_date";
        public final String KPI_EVENT_MODIFY_DATE="modify_date";
        public final String KPI_EVENT_START_DATE="start_date";
        public final String KPI_EVENT_END_DATE="end_date";
        public final String KPI_EVENT_ACTIVITY="activity";        
        public final String KPI_EVENT_LOCATION="location";
        public final String[] KPI_EVENT_ALL_FIELDS = {KPI_EVENT_LOCATION_ID, KPI_EVENT_CODE, KPI_EVENT_EVENT_TYPE, KPI_EVENT_EVENT_FIELD, 
                                                      KPI_EVENT_CREATE_DATE, KPI_EVENT_MODIFY_DATE, 
                                                      KPI_EVENT_START_DATE, KPI_EVENT_END_DATE,
                                                      KPI_EVENT_ACTIVITY, KPI_EVENT_LOCATION};
        
        // 3. KPI_INDICATOR
        public final String KPI_INDICATOR_ID="id";
        public final String KPI_INDICATOR_PARENT_ID="parent_id";
        public final String KPI_INDICATOR_CREATE_DATE="create_date";
        public final String KPI_INDICATOR_MODIFY_DATE="modify_date";
        public final String KPI_INDICATOR_CODE="code";
        public final String KPI_INDICATOR_NAME="name";
        public final String KPI_INDICATOR_DESCRIPTION="description";        
        public final String KPI_INDICATOR_BASELINE="baseline";
        public final String KPI_INDICATOR_TARGET_YEAR="target_year";
        public final String KPI_INDICATOR_TARGET_JUSTIFICATION="target_justification";
        
        public final String KPI_INDICATOR_M1="m1";
        public final String KPI_INDICATOR_M2="m2";
        public final String KPI_INDICATOR_M3="m3";
        public final String KPI_INDICATOR_M4="m4";
        public final String KPI_INDICATOR_M5="m5";
        public final String KPI_INDICATOR_M6="m6";
        public final String KPI_INDICATOR_M7="m7";
        public final String KPI_INDICATOR_M8="m8";
        public final String KPI_INDICATOR_M9="m9";
        public final String KPI_INDICATOR_M10="m10";
        public final String KPI_INDICATOR_M11="m11";
        public final String KPI_INDICATOR_M12="m12";
        
        public final String KPI_INDICATOR_Q1="q1";
        public final String KPI_INDICATOR_Q2="q2";
        public final String KPI_INDICATOR_Q3="q3";
        public final String KPI_INDICATOR_Q4="q4";
        
        public final String KPI_INDICATOR_LVL="lvl";       
        public final String KPI_INDICATOR_TYPE="type";
        public final String[] KPI_INDICATOR_ALL_FIELDS = {KPI_INDICATOR_PARENT_ID, KPI_INDICATOR_CREATE_DATE, KPI_INDICATOR_MODIFY_DATE, 
                                                 KPI_INDICATOR_CODE, KPI_INDICATOR_NAME, KPI_INDICATOR_DESCRIPTION,
                                                 KPI_INDICATOR_BASELINE, KPI_INDICATOR_TARGET_YEAR, KPI_INDICATOR_TARGET_JUSTIFICATION,
                                                KPI_INDICATOR_M1,KPI_INDICATOR_M2,KPI_INDICATOR_M3,KPI_INDICATOR_M4,KPI_INDICATOR_M5,
                                                KPI_INDICATOR_M6,KPI_INDICATOR_M7,KPI_INDICATOR_M8,KPI_INDICATOR_M9,KPI_INDICATOR_M10,
                                                KPI_INDICATOR_M11,KPI_INDICATOR_M12, KPI_INDICATOR_Q1, KPI_INDICATOR_Q2, KPI_INDICATOR_Q3, KPI_INDICATOR_Q4,
                                                KPI_INDICATOR_TYPE,KPI_INDICATOR_LVL};
        
        // 4. KPI_OBJ_IND
        public final String KPI_OBJ_ID="obj_id";
        public final String KPI_IND_ID="ind_id";
        public final String[] KPI_OBJ_IND_ALL_FIELDS = {KPI_OBJ_ID, KPI_IND_ID};
        
        // 5. KPI_DATA_HDR
        public final String KPI_DATA_HDR_ID="id";
        public final String KPI_DATA_HDR_CREATE_DATE="create_date"; 
        public final String KPI_DATA_HDR_MODIFY_DATE="modify_date";
        public final String KPI_DATA_HDR_USER_ID="user_id";
        public final String KPI_DATA_HDR_OBJ_ID="obj_id";
        public final String KPI_DATA_HDR_IND_ID="ind_id";
        public final String KPI_DATA_HDR_EVENT_ID="event_id";      
        public final String KPI_DATA_HDR_LOCATION_ID="location_id";       
        public final String[] KPI_DATA_HDR_ALL_FIELDS = {KPI_DATA_HDR_CREATE_DATE,KPI_DATA_HDR_MODIFY_DATE,KPI_DATA_HDR_USER_ID,KPI_DATA_HDR_OBJ_ID,
                                                         KPI_DATA_HDR_IND_ID,KPI_DATA_HDR_EVENT_ID,KPI_DATA_HDR_LOCATION_ID};
        
        // 6. KPI_DATA_DTL        
        public final String KPI_DATA_DTL_ID="id";
        public final String KPI_DATA_DTL_DATA_ID="data_id";
        public final String KPI_DATA_DTL_USER_ID="user_id";        
        public final String KPI_DATA_DTL_CREATE_DATE="create_date";
        public final String KPI_DATA_DTL_MODIFY_DATE="modify_date";
        public final String KPI_DATA_DTL_LOCATION_ID="location_id";
        public final String KPI_DATA_DTL_LOCATION="location";
        public final String KPI_DATA_DTL_PERIOD="period";        
        public final String KPI_DATA_DTL_MONTH="month";
        public final String KPI_DATA_DTL_QUARTER="quarter";
        public final String KPI_DATA_DTL_YEAR="year";
        public final String KPI_DATA_DTL_ACTIVITY="activity";
        public final String KPI_DATA_DTL_TIME="time";        
        public final String KPI_DATA_DTL_ACTUAL="actual";
        public final String KPI_DATA_DTL_TARGET="target";
        public final String KPI_DATA_DTL_TW="tw";
        public final String KPI_DATA_DTL_TTP="ttp";
        public final String KPI_DATA_DTL_QHU="qhu";
        public final String KPI_DATA_DTL_PXA="pxa";
        
        public final String KPI_DATA_DTL_TARGET_TW="target_tw";
        public final String KPI_DATA_DTL_TARGET_TTP="target_ttp";
        public final String KPI_DATA_DTL_TARGET_QHU="target_qhu";
        public final String KPI_DATA_DTL_TARGET_PXA="target_pxa";
        
        public final String KPI_DATA_DTL_TARGET_M="target_m";
        public final String KPI_DATA_DTL_TARGET_Q="target_q";
        public final String KPI_DATA_DTL_TARGET_Y="target_y";
        
        public final String KPI_DATA_DTL_ACC_M="acc_m";
        public final String KPI_DATA_DTL_ACC_Q="acc_q";
        public final String KPI_DATA_DTL_ACC_Y="acc_y";
        public final String KPI_DATA_DTL_NOTE="note";
        
        public final String[] KPI_DATA_DTL_ALL_FIELDS = {KPI_DATA_DTL_DATA_ID, KPI_DATA_DTL_USER_ID,  
                            KPI_DATA_DTL_CREATE_DATE, KPI_DATA_DTL_MODIFY_DATE, KPI_DATA_DTL_LOCATION_ID, KPI_DATA_DTL_LOCATION, KPI_DATA_DTL_PERIOD,
                            KPI_DATA_DTL_MONTH, KPI_DATA_DTL_QUARTER, KPI_DATA_DTL_YEAR, KPI_DATA_DTL_ACTIVITY, KPI_DATA_DTL_TIME,  
                            KPI_DATA_DTL_ACTUAL, KPI_DATA_DTL_TARGET, KPI_DATA_DTL_TW, KPI_DATA_DTL_TTP, KPI_DATA_DTL_QHU, KPI_DATA_DTL_PXA,
                            KPI_DATA_DTL_TARGET_TW, KPI_DATA_DTL_TARGET_TTP, KPI_DATA_DTL_TARGET_QHU, KPI_DATA_DTL_TARGET_PXA,
                            KPI_DATA_DTL_TARGET_M, KPI_DATA_DTL_TARGET_Q, KPI_DATA_DTL_TARGET_Y,
                            KPI_DATA_DTL_ACC_M, KPI_DATA_DTL_ACC_Q, KPI_DATA_DTL_ACC_Y, KPI_DATA_DTL_NOTE};
        
        // 7. KPI_PERSON
        public final String KPI_DATA_PER_ID="id";        
        public final String KPI_DATA_PER_CREATE_DATE="create_date";
        public final String KPI_DATA_PER_MODIFY_DATE="modify_date";
        public final String KPI_DATA_PER_USER_ID="user_id";                
        public final String KPI_DATA_PER_LOCATION_ID="location_id";
        public final String KPI_DATA_PER_ADDRESS="address";
        public final String KPI_DATA_PER_CONTACT="contact";
        public final String KPI_DATA_PER_CODE="code";
        public final String KPI_DATA_PER_NAME="name";
        public final String KPI_DATA_PER_SEX="sex";
        public final String KPI_DATA_PER_AGENCY="agency";
        public final String KPI_DATA_PER_TITLE="title";        
        public final String[] KPI_DATA_PER_ALL_FIELDS = {KPI_DATA_PER_CREATE_DATE, KPI_DATA_PER_MODIFY_DATE, KPI_DATA_PER_USER_ID, 
                            KPI_DATA_PER_LOCATION_ID, KPI_DATA_PER_ADDRESS, KPI_DATA_PER_CONTACT, KPI_DATA_PER_CODE, KPI_DATA_PER_NAME, 
                            KPI_DATA_PER_SEX, KPI_DATA_PER_AGENCY, KPI_DATA_PER_TITLE};
        
        public final String[] KPI_DATA_PER_UPDATE_FIELDS = {KPI_DATA_PER_NAME, KPI_DATA_PER_SEX, KPI_DATA_PER_TITLE, KPI_DATA_PER_AGENCY, KPI_DATA_PER_ADDRESS, KPI_DATA_PER_CONTACT};
        
        // 8. KPI_DATA_NKT
        public final String KPI_DATA_NKT_DATA_ID="data_id";
        public final String KPI_DATA_NKT_NKT_ID="nkt_id";
        public final String[] KPI_DATA_NKT_ALL_FIELDS =  {KPI_DATA_NKT_DATA_ID, KPI_DATA_NKT_NKT_ID};
        
        // 9. KPI_DATA_PER
        public final String KPI_DATA_HDR_PER_CREATE_DATE="create_date";
        public final String KPI_DATA_HDR_PER_RESULT_DATA_ID="data_id";
        public final String KPI_DATA_HDR_PER_RESULT_PER_ID="per_id";
        public final String KPI_DATA_HDR_PER_RESULT_EVENT_ID="event_id";
        public final String KPI_DATA_HDR_PER_RESULT_RESULT="result";
        public final String KPI_DATA_HDR_PER_RESULT_HOURS="hours";
        public final String[] KPI_DATA_PER_RESULT_ALL_FIELDS =  {KPI_DATA_HDR_PER_CREATE_DATE, 
                                                                 KPI_DATA_HDR_PER_RESULT_DATA_ID, 
                                                                 KPI_DATA_HDR_PER_RESULT_PER_ID, 
                                                                 KPI_DATA_HDR_PER_RESULT_EVENT_ID, 
                                                                 KPI_DATA_HDR_PER_RESULT_RESULT, 
                                                                 KPI_DATA_HDR_PER_RESULT_HOURS};
        public final String[] KPI_DATA_PER_RESULT_ALL_FIELDS_UPDATE =  {KPI_DATA_HDR_PER_RESULT_RESULT, KPI_DATA_HDR_PER_RESULT_HOURS};

        // 10. KPI_EVENT_IND
        public final String KPI_EVENT_IND_EVENT_ID="event_id";
        public final String KPI_EVENT_IND_IND_ID="ind_id";
        public final String[] KPI_EVENT_IND_ALL_FIELDS =  {KPI_EVENT_IND_EVENT_ID, KPI_EVENT_IND_IND_ID};
        
        // 12. KPI_RANK
        public final String KPI_RANK_ID="id";
        public final String KPI_RANK_PARENT_ID="parent_id";
        public final String KPI_RANK_CREATE_DATE="create_date";
        public final String KPI_RANK_MODIFY_DATE="modify_date";
        public final String KPI_RANK_CODE="code";
        public final String KPI_RANK_NAME="name";
        public final String KPI_RANK_REPORT="report";
        public final String[] KPI_RANK_ALL_FIELDS = {KPI_RANK_PARENT_ID, KPI_RANK_CREATE_DATE, KPI_RANK_MODIFY_DATE, 
                                                       KPI_RANK_CODE, KPI_RANK_NAME, KPI_RANK_REPORT};
        
        // 13. KPI_DATA_RANK
        public final String KPI_DATA_RANK_ID="id";        
        public final String KPI_DATA_RANK_CREATE_DATE="create_date";
        public final String KPI_DATA_RANK_MODIFY_DATE="modify_date";
        public final String KPI_DATA_RANK_USER_ID="user_id";        
        public final String KPI_DATA_RANK_NKT_ID="nkt_id";
        public final String KPI_DATA_RANK_LOCATION_ID="location_id";
        public final String KPI_DATA_RANK_RANK_ID="rank_id";
        public final String KPI_DATA_RANK_P0="p0";
        public final String KPI_DATA_RANK_P1="p1";
        public final String KPI_DATA_RANK_P2="p2";
        public final String KPI_DATA_RANK_P3="p3";
        public final String KPI_DATA_RANK_P4="p4";
        public final String KPI_DATA_RANK_HAS_SUPPORT = "has_sp";
        public final String KPI_DATA_RANK_HAS_RANK = "has_rank";
        public final String KPI_DATA_RANK_HAS_REQUIRE = "has_req";
        
        public final String[] KPI_DATA_RANK_ALL_FIELDS = {KPI_DATA_RANK_CREATE_DATE, KPI_DATA_RANK_MODIFY_DATE, 
                                                          KPI_DATA_RANK_USER_ID,
                                                          KPI_DATA_RANK_NKT_ID, 
                                                          KPI_DATA_RANK_LOCATION_ID, 
                                                          KPI_DATA_RANK_RANK_ID,
                                                          KPI_DATA_RANK_P0, 
                                                          KPI_DATA_RANK_P1,
                                                          KPI_DATA_RANK_P2,
                                                          KPI_DATA_RANK_P3,
                                                          KPI_DATA_RANK_P4,
                                                          KPI_DATA_RANK_HAS_RANK,
                                                          KPI_DATA_RANK_HAS_REQUIRE,
                                                          KPI_DATA_RANK_HAS_SUPPORT};
        
        
        // 14. KPI_EVENT_OBJ_IN
        public final String TABLE_KPI_EVENT_OBJ_IND_YEAR="year";
        public final String TABLE_KPI_EVENT_OBJ_IND_OBJ_ID="obj_id";
        public final String TABLE_KPI_EVENT_OBJ_IND_IND_ID="ind_id";
        public final String TABLE_KPI_EVENT_OBJ_IND_EVENT_ID="event_id";
        public final String[] KPI_EVENT_OBJ_IND_ALL_FIELDS = {TABLE_KPI_EVENT_OBJ_IND_YEAR, 
                                                              TABLE_KPI_EVENT_OBJ_IND_OBJ_ID, 
                                                              TABLE_KPI_EVENT_OBJ_IND_IND_ID, 
                                                              TABLE_KPI_EVENT_OBJ_IND_EVENT_ID};
        
        
        // 15. KPI_INDICATOR_VALUE
        public final String TABLE_KPI_INDICATOR_VALUE_CREATE_DATE="create_date";
        public final String TABLE_KPI_INDICATOR_VALUE_MODIFY_DATE="modify_date";
        public final String TABLE_KPI_INDICATOR_VALUE_IND_ID="ind_id";
        public final String TABLE_KPI_INDICATOR_VALUE_LOCATION_ID="location_id";
        public final String TABLE_KPI_INDICATOR_VALUE_QUARTER="quarter";
        public final String TABLE_KPI_INDICATOR_VALUE_YEAR="year";
        public final String TABLE_KPI_INDICATOR_VALUE_VAL="val";
        public final String TABLE_KPI_INDICATOR_VALUE_TYPE="type";
        public final String[] KPI_EVENT_INDICATOR_VALUE_ALL_FIELDS = {TABLE_KPI_INDICATOR_VALUE_CREATE_DATE,
                                                                      TABLE_KPI_INDICATOR_VALUE_MODIFY_DATE,
                                                                      TABLE_KPI_INDICATOR_VALUE_IND_ID, 
                                                                      TABLE_KPI_INDICATOR_VALUE_LOCATION_ID,
                                                                      TABLE_KPI_INDICATOR_VALUE_QUARTER, 
                                                                      TABLE_KPI_INDICATOR_VALUE_YEAR,
                                                                      TABLE_KPI_INDICATOR_VALUE_VAL,
                                                                      TABLE_KPI_INDICATOR_VALUE_TYPE};
        
        // 16. KPI_DIS_PROFILE
        public final String KPI_DIS_PROFILE_ID  = "id";
        public final String KPI_DIS_PROFILE_NKT_ID  = "nkt_id";
        public final String KPI_DIS_PROFILE_STATUS  = "status";
        public final String KPI_DIS_PROFILE_RESON_ID  = "reson_id";
        public final String KPI_DIS_PROFILE_CREATE_ON  = "create_on";
        public final String KPI_DIS_PROFILE_CREATE_BY  = "create_by";
        public final String KPI_DIS_PROFILE_UPDATE_ON  = "update_on";
        public final String KPI_DIS_PROFILE_UPDATE_BY  = "update_by";
        public final String KPI_DIS_PROFILE_ASSESSMENT  = "assessment";
        public final String[] KPI_DIS_PROFILE_ALL_FIELDS = {
                                        KPI_DIS_PROFILE_NKT_ID,
                                        KPI_DIS_PROFILE_STATUS,
                                        KPI_DIS_PROFILE_RESON_ID,
                                        KPI_DIS_PROFILE_CREATE_ON,
                                        KPI_DIS_PROFILE_CREATE_BY,
                                        KPI_DIS_PROFILE_UPDATE_ON,
                                        KPI_DIS_PROFILE_UPDATE_BY,
                                        KPI_DIS_PROFILE_ASSESSMENT
        };
        
        // 17. KPI_DIS_EXPORT
        public final String KPI_DIS_EXPORT_STT = "stt";
        public final String KPI_DIS_EXPORT_ID = "id";
        public final String KPI_DIS_EXPORT_TINH_ID = "tinh_id";
        public final String KPI_DIS_EXPORT_TINH_NAME = "tinh_name";
        public final String KPI_DIS_EXPORT_MASO = "maso";
        public final String KPI_DIS_EXPORT_TEN = "ten";
        public final String KPI_DIS_EXPORT_YEAR_BIRTH = "year_of_birthday";
        public final String KPI_DIS_EXPORT_SEX = "sex";
        public final String KPI_DIS_EXPORT_SONHA = "sonha";
        public final String KPI_DIS_EXPORT_DIENTHOAI = "dienthoai";
        public final String KPI_DIS_EXPORT_CREATE_DATE = "create_date";
        public final String KPI_DIS_EXPORT_NCS_TEN = "ten_ncs";
        public final String KPI_DIS_EXPORT_NCS_SDT = "sdt_ncs";
        public final String KPI_DIS_EXPORT_NCS_SEX = "gioitinh_ncs";
        public final String KPI_DIS_EXPORT_STATUS = "trangthai";
        public final String KPI_DIS_EXPORT_NGAY_DONG_HS = "ngay_dong_hs";
        public final String KPI_DIS_EXPORT_DANGTAT = "dang_tat";
        public final String KPI_DIS_EXPORT_MUCDO = "muc_do";
        public final String KPI_DIS_EXPORT_NGAYDANGTAT = "ngay_phat_hien_ktat";
        public final String KPI_DIS_EXPORT_DACAM = "da_cam";
        
        // Nhu-cau
        public final String KPI_DIS_EXPORT_NCAU_ID = "nc_id";
        public final String KPI_DIS_EXPORT_NCAU = "nhu_cau";  
        
        public final String KPI_DIS_EXPORT_NCAU_NOINHAN = "nhucau_noi_nhan";  
        public final String KPI_DIS_EXPORT_NCAU_CANTHIEP_CN = "nhucau_can_thiep_cn";  
        public final String KPI_DIS_EXPORT_NCAU_DUNGCU = "nhucau_ten_dung_cu";  
        public final String KPI_DIS_EXPORT_NCAU_CAITHIEN_CTVS = "nhucau_cai_thien_ctvs";          
        public final String KPI_DIS_EXPORT_NGAY_NCAU = "ngay_nhu_cau";
        
        // Ho-tro
        public final String KPI_DIS_EXPORT_HTRO_ID = "ht_id";
        public final String KPI_DIS_EXPORT_HOTRO = "hotro_da_nhan";
        
        public final String KPI_DIS_EXPORT_HOTRO_NOINHAN = "hotro_noi_nhan";
        public final String KPI_DIS_EXPORT_HOTRO_CANTHIEP_CN = "hotro_can_thiep_cn";        
        public final String KPI_DIS_EXPORT_HOTRO_DUNGCU = "hotro_ten_dung_cu";
        public final String KPI_DIS_EXPORT_HOTRO_CAITHIEN_CTVS = "hotro_cai_thien_ctvs";
        public final String KPI_DIS_EXPORT_NGAY_HTRO = "ngay_ho_tro";
        
        // 18. KPI_DIS_REPORT
        public final String KPI_DIS_REPORT_ID = "id";
        public final String KPI_DIS_REPORT_NKT_ID = "nkt_id";
        public final String KPI_DIS_REPORT_CREATE_DATE= "create_date";
        public final String KPI_DIS_REPORT_CREATE_BY = "create_by";       
        public final String KPI_DIS_REPORT_KTBT_THUONGXUYEN = "ktbt_thuongxuyen";
        public final String KPI_DIS_REPORT_KTBT_TAPDUNG = "ktbt_tapdung";
        
        public final String KPI_DIS_REPORT_DCTG_PHUHOP = "dctg_phuhop";
        public final String KPI_DIS_REPORT_DCTG_THUONGXUYEN = "dctg_thuongxuyen";
        public final String KPI_DIS_REPORT_DCTG_BAOQUAN = "dctg_baoquan";
        
        public final String KPI_DIS_REPORT_HD_NCS = "hd_ncs";
        public final String KPI_DIS_REPORT_CANTHIEP = "huong_ct";
        public final String KPI_DIS_REPORT_HUONGCANTHIEP = "canthiep";
        public final String KPI_DIS_REPORT_HTRO_DKIEN = "htro_dkien";
        public final String KPI_DIS_REPORT_DOITUONG = "doi_tuong";
       
        public final String[] KPI_DIS_REPORT_ALL_FIELDS = {
                                        KPI_DIS_REPORT_NKT_ID,
                                        KPI_DIS_REPORT_CREATE_DATE,
                                        KPI_DIS_REPORT_CREATE_BY,
                                        KPI_DIS_REPORT_KTBT_THUONGXUYEN,
                                        KPI_DIS_REPORT_KTBT_TAPDUNG,
                                                                              
                                        KPI_DIS_REPORT_DCTG_PHUHOP,
                                        KPI_DIS_REPORT_DCTG_THUONGXUYEN,
                                        KPI_DIS_REPORT_DCTG_BAOQUAN, 
                                                                              
                                        KPI_DIS_REPORT_HD_NCS,
                                        KPI_DIS_REPORT_CANTHIEP,
                                        KPI_DIS_REPORT_HUONGCANTHIEP,
                                        KPI_DIS_REPORT_HTRO_DKIEN, 
                                        KPI_DIS_REPORT_DOITUONG
        };
        
        public final String TABLE_KPI_JOB_SCHEDULER_ID = "id";
        public final String TABLE_KPI_JOB_SCHEDULER_CREATE_DATE = "create_date";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_CODE = "job_code";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_NAME = "job_name";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_EXEC = "job_exec";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_CRON = "job_cron";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_STATUS = "job_status";
        public final String TABLE_KPI_JOB_SCHEDULER_JOB_LOCATION_ID = "location_id";
        
        public final String[] KPI_JOB_SCHEDULER_ALL_FIELDS = {
                                          TABLE_KPI_JOB_SCHEDULER_ID,
                                          TABLE_KPI_JOB_SCHEDULER_CREATE_DATE,
                                          TABLE_KPI_JOB_SCHEDULER_JOB_CODE,
                                          TABLE_KPI_JOB_SCHEDULER_JOB_NAME,
                                          TABLE_KPI_JOB_SCHEDULER_JOB_EXEC,
                                          TABLE_KPI_JOB_SCHEDULER_JOB_CRON,     
                                          TABLE_KPI_JOB_SCHEDULER_JOB_STATUS,
                                          TABLE_KPI_JOB_SCHEDULER_JOB_LOCATION_ID
        };
        
        public final String TABLE_KPI_JOB_LOG_ID = "id";
        public final String TABLE_KPI_JOB_LOG_START_EXEC = "start_exec";
        public final String TABLE_KPI_JOB_LOG_END_EXEC = "end_exec";
        public final String TABLE_KPI_JOB_LOG_JOB_ID = "job_id";
        public final String TABLE_KPI_JOB_LOG_MSG_EXEC = "msg_exec";
        public final String TABLE_KPI_JOB_LOG_LOCATION_ID = "location_id";
        
        public final String[] KPI_JOB_LOG_ALL_FIELDS = {
                                          TABLE_KPI_JOB_LOG_START_EXEC,
                                          TABLE_KPI_JOB_LOG_END_EXEC,
                                          TABLE_KPI_JOB_LOG_JOB_ID,
                                          TABLE_KPI_JOB_LOG_MSG_EXEC,
                                          TABLE_KPI_JOB_LOG_LOCATION_ID
        };
        
        public final String TABLE_DR_HOME_VISIT_ID = "id";   
        public final String TABLE_DR_HOME_VISIT_SUPPORT_ID = "support_id";
        public final String TABLE_DR_HOME_VISIT_DIS_ID = "id_nkt";
        public final String TABLE_DR_HOME_VISIT_START_AT = "start_at";
        public final String TABLE_DR_HOME_VISIT_END_AT = "end_at";
        public final String TABLE_DR_HOME_VISIT_CREATE_BY = "create_by";
        public final String TABLE_DR_HOME_VISIT_LATITUDE = "latitude";
        public final String TABLE_DR_HOME_VISIT_LONGITUDE = "longitude";
        public final String TABLE_DR_HOME_VISIT_LOCATION = "location";
        
        public final String[] DR_HOME_VISIT_ALL_FILEDS = {
                                  TABLE_DR_HOME_VISIT_SUPPORT_ID,
                                  TABLE_DR_HOME_VISIT_DIS_ID,
                                  TABLE_DR_HOME_VISIT_START_AT,
                                  TABLE_DR_HOME_VISIT_END_AT,
                                  TABLE_DR_HOME_VISIT_CREATE_BY,
                                  TABLE_DR_HOME_VISIT_LATITUDE,
                                  TABLE_DR_HOME_VISIT_LONGITUDE,
                                  TABLE_DR_HOME_VISIT_LOCATION
        };
}
