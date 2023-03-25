package com.inf.disability;

public interface ITablesDisability {
    //  SET OF ADMIN TABLES IN DATABASE
    public final String TABLE_DISABILITYPEOPLE      =   "DR_DISABILITYPEOPLE";  //COMMENT
    public final String TABLE_RELATIVE              =   "DR_RELATIVE";          //quan he gia dinh     
    public final String TABLE_TINH                  =   "DR_AREA";              //danh muc dia danh
    
    public final String TABLE_DANGTAT               =   "DR_CLASSIFICATION";    //Dang tat
    public final String TABLE_PHANLOAI              =   "DR_PHANLOAI";          //Qua trinh phan loai dang tat  NKT
    public final String TABLE_RANK                  =   "DR_RANK";              //Qua trinh danh gia NKT
    
    public final String TABLE_DIEUKIEN              =   "DR_DIEUKIEN";          //dieu kien kinh te 
    //public final String TABLE_LYDO                =   "DR_LYDO";              //danh muc quan he 
    public final String TABLE_QUANHE                =   "DR_QUANHE";            //danh muc quan he 
    public final String TABLE_DONVI                 =   "DR_DONVI";             //danh muc ly do 
    public final String TABLE_DANTOC                =   "DR_DANTOC";            //danh muc dan toc
    public final String TABLE_NGUYENNHAN            =   "DR_NGUYENNHAN";        //danh muc nguyen nhan
    public final String TABLE_DOITUONG              =   "DR_DOITUONG";        //danh muc doi tuong
    public final String TABLE_MUCDO                 =   "DR_MUCDO";             //danh muc muc do khuyet tat
    
    public final String TABLE_DUNGCU                =   "DR_DUNGCU";            //danh muc dung cu     
    public final String TABLE_DM_HOTRO              =   "DR_HOTRO";             //danh muc ho tro
    public final String TABLE_HOTRO                 =   "DR_SUPPORT";           //qua trinh ho tro 
    public final String TABLE_DANHGIA_NKT           =   "DR_DANHGIA_NKT";       //danhgia nkt
    
    public final String TABLE_TRINHDO_VANHOA        =   "DR_TRINHDO_VANHOA";    // Trinh do van hoa
    public final String TABLE_TRINHDO_CHUYENMON     =   "DR_TRINHDO_CHUYENMON"; // Trinh do chuyen mon
    public final String TABLE_TTRANG_GIAODUC        =   "DR_TINHTRANG_GIAODUC"; // Tinh trang giao duc
    public final String TABLE_TTRANG_HONNHAN        =   "DR_TINHTRANG_HONNHAN"; // Tinh trang hon nhan
    public final String TABLE_NGUONHOTRO            =   "DR_NGUONHOTRO";        // Nguon ho tro
    public final String TABLE_NGHENGHIEP            =   "DR_NGHENGHIEP";        // Nghe nghiep    
    
    public final String TABLE_DANHGIA               =   "DR_TINHTRANG";         // COMMENT      
    public final String TABLE_LISTREPORT            =   "DR_LISTREPORT";        // DANH SACH BAO CAO
    public final String TABLE_LIST_EMP              =   "DR_LIST_EMP";          // QUAN HE DANH SACH BAO CAO VA EMPLOYEE
    public final String TABLE_POPULATION            =   "DR_POPULATION";        // DAN SO TRONG TINH, XA, HUYEN
    public final String TABLE_REPORT_TEMP           =   "DR_TEMP_REPORT";       // BAO CAO XA
    
    public final String TABLE_DANSOHUYEN            =   "DR_DISTRICT_REPORT";
    public final String TABLE_DANSOTINH             =   "DR_PROVINCE_REPORT";
    public final String TABLE_DR_TEMP_TG            =   "dr_temp_tg";
    public final String TABLE_DR_UNIT               =   "dr_unit";
    public final String TABLE_DR_CATEGORY_UNIT      =   "dr_category_unit";
    
    public final String TABLE_DR_THONGTIN_TUYEN     =   "DR_THONGTIN_TUYEN";

    public final String TABLE_KPI_OBJECT            =   "kpi_object";           //1
    public final String TABLE_KPI_INDICATOR         =   "kpi_indicator";        //2
    public final String TABLE_KPI_EVENT             =   "kpi_event";            //3
    public final String TABLE_KPI_EVENT_IND         =   "kpi_event_ind";        //4 MAP EVENT & INDICATOR
    public final String TABLE_KPI_OBJ_IND           =   "kpi_obj_ind";          //5 MAP OBJECT & INDICATOR
    public final String TABLE_KPI_DATA_HDR          =   "kpi_data_hdr";         //6
    public final String TABLE_KPI_DATA_PER          =   "kpi_data_per";         //7 Map HDR & PER        
    public final String TABLE_KPI_DATA_DTL          =   "kpi_data_dtl";         //8 Input value
    public final String TABLE_KPI_DATA_NKT          =   "kpi_data_nkt";         //9 Map HDR & NKT
    public final String TABLE_KPI_PERSON            =   "kpi_person";           //10
    public final String VIEW_KPI_OBJECT             =   "kpi_v_object";         //11
    public final String TABLE_KPI_RANK              =   "kpi_rank";             //12
    public final String TABLE_KPI_DATA_RANK         =   "kpi_data_rank";        //13
    public final String TABLE_KPI_EVENT_OBJ_IND     =   "kpi_event_obj_ind";    //14 MAP OBJ-IND-EVENT
    public final String TABLE_KPI_INDICATOR_VAL     =   "kpi_ind_val";           //15 indicator_val
    public final String TABLE_DR_HOME_VISIT         =   "dr_home_visit";
    
    public final String TABLE_KPI_DIS_PROFILE       =   "kpi_dis_profile";
    public final String TABLE_KPI_DIS_REPORT        =   "kpi_dis_report";
    public final String TABLE_KPI_DIS_EXPORT        =   "kpi_report_temp";
    
    public final String TABLE_KPI_JOB_SCHEDULER     =    "kpi_job_scheduler";
    public final String TABLE_KPI_JOB_LOG           =    "kpi_job_log";
}
