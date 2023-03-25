package com.dao.disability;

import com.dao.DSqlAdmin;

public class DSqlReportUtil  extends DSqlAdmin{
    
    public final String SQL_REPORT_COUNT_NKT            = "SELECT COUNT(*) AS tong FROM dr_disabilitypeople WHERE 1=1 "; 
    
    public final String SQL_REPORT_COUNT_NKT_PHANLOAI   = "SELECT COUNT(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai \n" + 
                                                            "INNER JOIN dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id ";
                                                            
    public final String SQL_REPORT_COUNT_NKT_NGUYENNHAN = "SELECT COUNT(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai \n" + 
                                                            "INNER JOIN dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id \n" +
                                                            "WHERE 1=1 ";
                                                            
    public final String SQL_REPORT_COUNT_NKT_HOTRO      = "SELECT COUNT(DISTINCT dr_support.id_nkt) AS tong FROM dr_support \n" +
                                                            "INNER JOIN dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id \n " + 
                                                            "WHERE 1=1 ";
                                                            
//    SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support 
//    inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and 
//    EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 
//    and dr_disabilitypeople.id_tinh in ("+member+") 
//    and dr_support.status_id=0 
//    and dr_support.dm_hotro_ids like '%#18#%' 
//    and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') 
//    and to_date("+denNgay+",'dd/mm/yyyy') 
//    and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') 
//    and to_date("+denNgay+",'dd/mm/yyyy')                                                            
}
