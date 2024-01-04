package com.dao.disability;


import com.dao.DSqlAdmin;


public class DSqlDisability extends DSqlAdmin {
    //dr_category_unit
    public final String SQL_SELECT_CATEGORY_UNIT_BY_ID = SELECT + STAR + FROM + TABLE_DR_CATEGORY_UNIT + WHERE + DR_CATEGORY_UNIT_ID + EQUAL + QUESTION;    
    
    public final String SQL_INSERT_INTO_TABLE_DR_CATEGORY_UNIT = INSERT_INTO + TABLE_DR_CATEGORY_UNIT + FIELDS(DR_CATEGORY_UNIT_ALL_FIELDS,true) + VALUES(DR_CATEGORY_UNIT_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_INTO_TABLE_DR_CATEGORY_UNIT = UPDATE + TABLE_DR_CATEGORY_UNIT + SET + SETS(DR_CATEGORY_UNIT_ALL_FIELDS) + WHERE + DR_CATEGORY_UNIT_ID + EQUAL + QUESTION;
    
    //dr_unit
    public final String SQL_SELECT_ALL_CATEGORY_UNIT = SELECT + STAR + FROM + TABLE_DR_CATEGORY_UNIT + ORDER_BY + DR_CATEGORY_UNIT_ID;

    public final String SQL_SELECT_ALL_UNIT = SELECT + STAR + FROM + TABLE_DR_UNIT + ORDER_BY + DR_UNIT_CATEGORY_ID;
  
    public final String SQL_COUNT_CATEGORY_UNIT="SELECT COUNT(B.CATEGORY_ID),A.ID,A.NAME FROM DR_CATEGORY_UNIT AS A LEFT JOIN DR_UNIT B ON B.CATEGORY_ID = A.ID GROUP BY B.CATEGORY_ID,A.ID,A.NAME ORDER BY A.ID ASC";
  
    public final String SQL_SELECT_COUNT_UNIT="SELECT * FROM DR_UNIT WHERE CATEGORY_ID = ? AND TINH_ID = ?";
  
    public final String SQL_SELECT_ALL_DISABILITY_UNIT=SELECT + STAR + FROM + TABLE_DR_UNIT + WHERE + TRUE;
  
    public final String SQL_INSERT_INTO_TABLE_DR_UNIT=  INSERT_INTO + TABLE_DR_UNIT + FIELDS(DR_UNIT_ALL_FIELDS,true) + VALUES(DR_UNIT_ALL_FIELDS.length);
  
    public final String SQL_UPDATE_INTO_TABLE_DR_UNIT=  UPDATE + TABLE_DR_UNIT + SET + SETS(DR_UNIT_ALL_FIELDS) + WHERE + DR_UNIT_ID + EQUAL + QUESTION;
      
    public final String SQL_SELECT_BY_ID_FROM_DISABILITY_UNIT=
            SELECT + TABLE_DR_CATEGORY_UNIT + STOP + DR_CATEGORY_UNIT_NAME + AS + " NAME_CATEGORY " + COMMA +  TABLE_DR_UNIT + STOP + STAR + FROM + TABLE_DR_UNIT + 
            LEFT_JOIN + TABLE_DR_CATEGORY_UNIT + ON + TABLE_DR_UNIT + STOP + DR_UNIT_CATEGORY_ID + EQUAL + TABLE_DR_CATEGORY_UNIT + STOP + DR_CATEGORY_UNIT_ID +
            WHERE + TABLE_DR_UNIT + STOP + DR_UNIT_ID + EQUAL + QUESTION ;
    
    //TN_NKT
    public final String SELECT_ALL_FROM_TABLE_RELATIVE_BY_NKT=
            SELECT + TABLE_RELATIVE + STOP + STAR + COMMA + TABLE_DISABILITYPEOPLE + STOP + NKT_TEN + COMMA + TABLE_QUANHE + STOP + LYDO_NAME + FROM + TABLE_RELATIVE + 
            LEFT_JOIN + TABLE_DISABILITYPEOPLE + ON + TABLE_RELATIVE + STOP + TN_NKT_ID_RELATIVE_NKT + EQUAL + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + 
            LEFT_JOIN + TABLE_QUANHE + ON + TABLE_RELATIVE + STOP + TN_NKT_ID_LYDO + EQUAL + TABLE_QUANHE + STOP + QUANHE_ID + 
            WHERE + TN_NKT_ID_NKT + EQUAL + QUESTION + ORDER_BY + TN_NKT_ID;
    
    public final String SELECT_ALL_FROM_TABLE_RELATIVE=SELECT + STAR + FROM + TABLE_RELATIVE;
  
    public final String SELECT_GET_BY_ID_FROM_TABLE_RELATIVE=SELECT + STAR + FROM + TABLE_RELATIVE + WHERE + TN_NKT_ID + EQUAL + QUESTION;
  
    public final String SQL_INSERT_INTO_TABLE_RELATIVE=INSERT_INTO + TABLE_RELATIVE + FIELDS(TN_NKT_ALL_FIELDS,true) + VALUES(TN_NKT_ALL_FIELDS.length);
  
    public final String SQL_UPDATE_INTO_TABLE_RELATIVE=  UPDATE + TABLE_RELATIVE + SET + SETS(TN_NKT_ALL_FIELDS) + WHERE + TN_NKT_ID + EQUAL + QUESTION;
    //DANG TAT
 
    //NKT   
    //public final String SQL_SELECT_DISABILITY_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DISABILITYPEOPLE +  WHERE + NKT_MA + EQUAL + QUESTION + AND +  NKT_ID + DIFF + QUESTION;
    public final String SQL_SELECT_DISABILITY_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DISABILITYPEOPLE +  WHERE + NKT_MA + EQUAL + QUESTION + AND + NKT_TRANGTHAI + EQUAL + QUESTION + AND +  NKT_ID + DIFF + QUESTION;
    public final String SQL_SELECT_DISABILITY_BY_CODE = SELECT + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE + NKT_MA + EQUAL + QUESTION;
   
    public final String SQL_SELECT_DISABILITY_BY_NAME = SELECT + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE 
            + NKT_TEN + EQUAL + QUESTION
            + AND + NKT_SONHA + EQUAL + QUESTION
            + AND + NKT_ID_TINH + EQUAL + QUESTION     
            + AND +  NKT_ID + DIFF + QUESTION;    
    
    public final String SQL_SELECT_DISABILITY_BY_SONHA = SELECT + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE 
            + NKT_SONHA + EQUAL + QUESTION 
            + AND + NKT_ID_TINH + EQUAL + QUESTION     
            + AND +  NKT_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_DISABILITY_BY_NGAYSINH =  SELECT + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE 
            + NKT_NGAYSINH + EQUAL + QUESTION 
            + AND + NKT_ID_TINH + EQUAL + QUESTION     
            + AND +  NKT_ID + DIFF + QUESTION;

    //public final String SELECT_ALL_SEARCH_TEXT_DANG_TAT=" SELECT B.ID_NKT FROM dr_classification AS A LEFT JOIN DR_PHANLOAI B ON B.DANGTAT_IDS LIKE '%'|| A.DANGTAT_ID||'%' WHERE A.NAME ILIKE ? OR B.RESON ILIKE ? ";
    
    public final String SELECT_ALL_SEARCH_TEXT_DANG_TAT=" SELECT B.ID_NKT FROM DR_CLASSIFICATION AS A LEFT JOIN DR_PHANLOAI B ON B.DANGTAT_IDS LIKE '%#'|| A.DANGTAT_ID||'#%' WHERE A.NAME ILIKE ?";
  
    //public final String SELECT_ALL_SEARCH_TEXT_HO_TRO=" SELECT B.ID_NKT FROM DR_HOTRO AS A LEFT JOIN DR_SUPPORT B ON B.DM_HOTRO_IDS LIKE '%'|| A.HOTRO_ID||'%'  WHERE A.NAME ILIKE ?  OR B.RESON ILIKE ?  OR B.NGUONHOTRO ILIKE ? ";
    
    //public final String SELECT_ALL_SEARCH_NGUON_HO_TRO=" SELECT B.ID_NKT FROM DR_HOTRO AS A LEFT JOIN DR_SUPPORT B ON B.DM_HOTRO_IDS LIKE '%'|| A.HOTRO_ID||'%'  WHERE A.NAME ILIKE ?  OR B.NGUONHOTRO ILIKE ? OR B.NGUONHOTRO_ID = ?";

    public final String SELECT_ALL_SEARCH_NGUON_HO_TRO=" SELECT DISTINCT ON (DR_SUPPORT.ID_NKT) DR_SUPPORT.ID_NKT FROM DR_SUPPORT WHERE NGUONHOTRO_ID = ? AND STATUS_ID = 1";

    //public final String SELECT_ALL_SEARCH_NGUON_HO_TRO_ID = " SELECT DISTINCT B.ID_NKT FROM DR_HOTRO AS A LEFT JOIN DR_SUPPORT B ON B.DM_HOTRO_IDS LIKE '%'|| A.HOTRO_ID||'%'  WHERE B.NGUONHOTRO_ID = ? AND B.STATUS_ID = 1";

    public final String SELECT_ALL_SEARCH_NGUON_HO_TRO_ID = " SELECT DISTINCT B.ID_NKT FROM DR_SUPPORT AS B WHERE B.NGUONHOTRO_ID = ? AND B.STATUS_ID = 1";
     
    public final String SELECT_ALL_SEARCH_TEXT_TINH_TRANG_HIENTAI=" SELECT B.ID_NKT FROM DR_TINHTRANG AS A LEFT JOIN DR_RANK B ON B.DANHGIA_IDS LIKE '%'|| A.TINHTRANG_ID||'%'  WHERE A.NAME ILIKE ?  OR B.RESON ILIKE ? ";

    /*public final String SELECT_ALL_FROM_TABLE_DISABILITY = SELECT + " ROW_NUMBER, OLDTABLE.* FROM (" +  SELECT + DISTINCT +  ON + " (DR_DISABILITYPEOPLE.ID) "  
            + TABLE_DISABILITYPEOPLE + STOP + STAR 
            + COMMA + TABLE_TINH + STOP + TINH_NAME 
            + COMMA + TABLE_HOTRO + STOP + HOTRO_NGUONHOTRO 
            + COMMA + TABLE_HOTRO + STOP + HOTRO_NGUONHOTRO_ID 
            + COMMA + TABLE_TINH + STOP + TINH_CODE 
            + COMMA + TABLE_RANK + STOP + RANK_ID + AS + "RANK_ID"
            + FROM + TABLE_DISABILITYPEOPLE 
            + LEFT_JOIN + TABLE_TINH + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + TABLE_TINH + STOP + TINH_TINH_ID
            + LEFT_JOIN + TABLE_PHANLOAI + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_PHANLOAI + STOP + PHANLOAI_ID_NKT
            + LEFT_JOIN + TABLE_HOTRO + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_HOTRO + STOP + HOTRO_ID_NKT
            + LEFT_JOIN + TABLE_RANK + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_RANK + STOP + RANK_ID_NKT + " ) AS OLDTABLE ";
            + "CROSS JOIN (SELECT ARRAY(SELECT id FROM DR_DISABILITYPEOPLE ORDER BY id) As id)  AS oldids "
            + "CROSS JOIN generate_series(1, (SELECT COUNT(*) FROM DR_DISABILITYPEOPLE)) AS ROW_NUMBER ";*/
            
     public final String SELECT_ALL_FROM_TABLE_DISABILITY = SELECT + DISTINCT +  ON + " (DR_DISABILITYPEOPLE.ID) "  
            + TABLE_DISABILITYPEOPLE + STOP + STAR 
            + COMMA + TABLE_DANTOC + STOP + DANTOC_NAME + " as dantoc_name"                                                            
            + COMMA + TABLE_TINH + STOP + TINH_NAME 
            + ", dis.name qhu_name "
            + COMMA + "sp.id_nkt, sp.datecreate, sp.reson, sp.dm_hotro_ids, sp.user_id, sp.status_id, sp.\n" + 
            "       dateform, sp.dateto, sp.nguonhotro, sp.nguonhotro_id, sp.macbenh, sp.dungcu_khac, sp.\n" + 
            "       phauthuat_khac, sp.yte_khac, sp.trocap_thuongxuyen_khac, sp.trocap_dotxuat_khac, sp.\n" + 
            "       caithien_khac, sp.loaivay_khac, sp.sotienvay_khac, sp.mucdichvay_khac, sp.\n" + 
            "       tochucxahoi_khac, sp.nhucau_doisong_khac, sp.nhucau_giaoduc_khac, sp.ketqua, sp.\n" + 
            "       kn_chitra, sp.the_bhyt, sp.sd_the, sp.sd_the_phcn, sp.mtieu_gdinh, sp.mtieu_dtri, sp.\n" + 
            "       ct_vltl, sp.ct_hdtl, sp.ct_antl, sp.mdo_ptdl, sp.mdo_hlong, " + 
            "       rpt.ktbt_thuongxuyen::text as P1, rpt.ktbt_tapdung::text as P2, rpt.dctg_phuhop::text as P3, rpt.dctg_thuongxuyen::text as P4, " + 
            "       rpt.dctg_baoquan::text as P5, rpt.hd_ncs::text as P6, rpt.huong_ct::text as P7, rpt.htro_dkien as P8, "
            + " get_num_homevisit(DR_DISABILITYPEOPLE.ID) num_homevisit"                                                
            + FROM + TABLE_DISABILITYPEOPLE 
            + LEFT_JOIN + TABLE_TINH + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + TABLE_TINH + STOP + TINH_TINH_ID
            + " left join dr_area dis on DR_DISABILITYPEOPLE.id_district = dis.TINH_ID "                                                  
            + LEFT_JOIN + TABLE_PHANLOAI + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_PHANLOAI + STOP + PHANLOAI_ID_NKT
            + LEFT_JOIN + TABLE_HOTRO + " sp " + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + "sp" + STOP + HOTRO_ID_NKT
            + LEFT_JOIN + TABLE_KPI_DIS_PROFILE + " pf " + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + "pf" + STOP + "nkt_id"
            + LEFT_JOIN + TABLE_RANK + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_RANK + STOP + RANK_ID_NKT
            + LEFT_JOIN + TABLE_DANTOC + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_DANTOC_ID + EQUAL + TABLE_DANTOC + STOP + DANTOC_DANTOC_ID
            + LEFT_JOIN + " kpi_dis_report rpt " + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + " rpt.nkt_id ";
                 
    public final String COUNT_ALL_FROM_TABLE_DISABILITY = SELECT + " COUNT(DISTINCT DR_DISABILITYPEOPLE.ID) as TOTAL"
            + FROM + TABLE_DISABILITYPEOPLE 
            + LEFT_JOIN + TABLE_TINH + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + TABLE_TINH + STOP + TINH_TINH_ID
            + " left join dr_area dis on DR_DISABILITYPEOPLE.id_district = dis.TINH_ID "
            + LEFT_JOIN + TABLE_PHANLOAI + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_PHANLOAI + STOP + PHANLOAI_ID_NKT
            + LEFT_JOIN + TABLE_HOTRO + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_HOTRO + STOP + HOTRO_ID_NKT
            + LEFT_JOIN + TABLE_HOTRO + " sp " + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + "sp" + STOP + HOTRO_ID_NKT
            + LEFT_JOIN + TABLE_KPI_DIS_PROFILE + " pf " + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + "pf" + STOP + "nkt_id"
            + LEFT_JOIN + TABLE_RANK + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_RANK + STOP + RANK_ID_NKT
            + LEFT_JOIN + TABLE_DANTOC + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_DANTOC_ID + EQUAL + TABLE_DANTOC + STOP + DANTOC_DANTOC_ID;

    // Quan ly ca    
    public final String SELECT_DISABILITY_FROM_TABLE_DISABILITY = SELECT + DISTINCT 
            + ON + OPEN + TABLE_DISABILITYPEOPLE + STOP + NKT_ID +  CLOSE + " "
            + TABLE_DISABILITYPEOPLE + STOP + STAR 
            + COMMA + TABLE_TINH + STOP + TINH_NAME 
            + COMMA + TABLE_HOTRO + STOP + HOTRO_NGUONHOTRO 
            + COMMA + TABLE_HOTRO + STOP + HOTRO_NGUONHOTRO_ID 
            + COMMA + TABLE_TINH + STOP + TINH_CODE 
            + COMMA + TABLE_RANK + STOP + RANK_ID + AS + "RANK_ID"
            + FROM + TABLE_DISABILITYPEOPLE 
            + LEFT_JOIN + TABLE_TINH + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + TABLE_TINH + STOP + TINH_TINH_ID
            + LEFT_JOIN + TABLE_PHANLOAI + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_PHANLOAI + STOP + PHANLOAI_ID_NKT
            + LEFT_JOIN + TABLE_HOTRO + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_HOTRO + STOP + HOTRO_ID_NKT
            + LEFT_JOIN + TABLE_RANK + ON + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + EQUAL + TABLE_RANK + STOP + RANK_ID_NKT;
   
    public final String SELECT_GET_BY_ID_FROM_TABLE_DISABILITY= SELECT + TABLE_DISABILITYPEOPLE + STOP + STAR + COMMA + TABLE_DANTOC + STOP + DANTOC_NAME + " as dantoc_name " + 
                                                               FROM + TABLE_DISABILITYPEOPLE + LEFT_JOIN + TABLE_DANTOC + ON + 
                                                               TABLE_DISABILITYPEOPLE + STOP + NKT_DANTOC_ID + EQUAL + TABLE_DANTOC + STOP + DANTOC_DANTOC_ID +
                                                               WHERE + NKT_ID + EQUAL + QUESTION;

    public final String SELECT_GET_BY_ID_FROM_TABLE_DISABILITY_MAX_ID=SELECT + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE + NKT_ID + EQUAL + OPEN + SELECT + MAX + OPEN + NKT_ID + CLOSE + FROM + TABLE_DISABILITYPEOPLE + CLOSE;    
    
    public final String SQL_INSERT_INTO_TABLE_DISABILITY=  INSERT_INTO + TABLE_DISABILITYPEOPLE + FIELDS(NKT_ALL_FIELDS,true) + VALUES(NKT_ALL_FIELDS.length);
    
    public final String SQL_INSERT_INTO_TABLE_DISABILITY_KPI =  INSERT_INTO + TABLE_DISABILITYPEOPLE + FIELDS(NKT_ALL_FIELDS_KPI,true) + VALUES(NKT_ALL_FIELDS_KPI.length) + " RETURNING id";
    
    public final String SQL_UPDATE_INTO_TABLE_DISABILITY=  UPDATE + TABLE_DISABILITYPEOPLE + SET + SETS(NKT_ALL_FIELDS) + WHERE + NKT_ID + EQUAL + QUESTION;
    
    public final String SQL_APPROVE_STATUS_NKT = UPDATE + TABLE_DISABILITYPEOPLE + SET + NKT_TRANGTHAI + EQUAL + QUESTION + WHERE + NKT_ID + EQUAL + QUESTION;    
    
    public final String SQL_DELETE_DISABILITY = UPDATE + TABLE_DISABILITYPEOPLE + SET + NKT_TRANGTHAI + EQUAL + QUESTION + WHERE + NKT_ID + EQUAL + QUESTION;
        
    //DANG TAT    
     
    public final String SQL_SELECT_TREE_TINH  =SELECT + STAR + FROM + " dr_area_tree(?)" ;
    
    public final String SQL_SELECT_TINH  =  SELECT + FIELDS("A",new String[]{TINH_TINH_ID ,TINH_NAME,TINH_PARENT_ID }) + COMMA + 
                                                            FIELDS("B",new String[]{TINH_TINH_ID ,TINH_NAME,TINH_PARENT_ID }) + 
                                                            FROM + TABLE_TINH + " A" + LEFT_OUTER_JOIN + TABLE_TINH + " B" + 
                                                            ON + "B." + TINH_PARENT_ID + EQUAL + "A." + TINH_TINH_ID +
                                                            ORDER_BY + "A." + TINH_PARENT_ID + COMMA +"A.ORDER_BY,B.ORDER_BY";
                                                            
    public final String SQL_SELECT_TINH_RULE  = SELECT + FIELDS("A",new String[]{TINH_TINH_ID ,TINH_NAME,TINH_PARENT_ID }) + COMMA + 
                                                            FIELDS("B",new String[]{TINH_TINH_ID ,TINH_NAME,TINH_PARENT_ID }) + 
                                                            FROM + TABLE_TINH + " A" + LEFT_OUTER_JOIN + TABLE_TINH + " B" + 
                                                            ON + "B." + TINH_PARENT_ID + EQUAL + "A." + TINH_TINH_ID ;
                                                            
    //public final String SQL_SELECT_TINH_IDS  = SELECT + DISTINCT + "A.TINH_ID FROM DR_AREA A LEFT OUTER JOIN DR_AREA B ON B.PARENT_ID=A.TINH_ID";

    public final String SQL_SELECT_TINH_BY_ID   = SELECT  + STAR + FROM + TABLE_TINH + WHERE + TINH_TINH_ID + EQUAL + QUESTION;

    public final String SQL_SELECT_TINH_BY_PARENT_ID = "select 0 as tinh_id, 0 as parent_id, '-- L&#7921;a ch&#7885;n --' as name from dr_area\n" + 
                                                        "union\n" + 
                                                        "select tinh_id, parent_id, name from dr_area where parent_id = ? ORDER BY tinh_id";

    public final String SQL_SELECT_TINH_ID_BY_PARENT_ID = SELECT  + TINH_TINH_ID + FROM + TABLE_TINH + WHERE + TINH_PARENT_ID + EQUAL + QUESTION + ORDER_BY + TINH_ORDER;

    public final String SQL_SELECT_TINH_NAME_BY_TINH_ID = SELECT  + TINH_NAME + FROM + TABLE_TINH + WHERE + TINH_TINH_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_TINH_BY_CODE = SELECT  + STAR + FROM + TABLE_TINH + WHERE + TINH_CODE + LIKE + '%' + QUESTION + '%';
    
    public final String SQL_SELECT_TINH_MAX_STT = SELECT  + COUNT + OPEN + STAR + CLOSE + FROM + TABLE_TINH + WHERE + TINH_PARENT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_DIS_MAX_STT  = SELECT  + COUNT + OPEN + STAR + CLOSE + FROM + TABLE_DISABILITYPEOPLE + WHERE + NKT_ID_TINH + EQUAL + QUESTION;
      
    public final String SQL_SELECT_MAX_CODE_4 =  "SELECT MAX(CAST(SPLIT_PART(MA, '.', 4) AS INT)) FROM DR_DISABILITYPEOPLE WHERE ID_TINH= ?";

    public final String SQL_SELECT_MAX_CODE_3 =  "SELECT MAX(CAST(SPLIT_PART(MA, '.', 3) AS INT)) FROM DR_DISABILITYPEOPLE WHERE ID_TINH= ?";
    
    public final String SQL_SELECT_MAX_CODE_2 =  "SELECT MAX(CAST(SPLIT_PART(MA, '.', 2) AS INT)) as num FROM DR_DISABILITYPEOPLE WHERE ID_TINH= ?";
    
    //public final String SQL_SELECT_MAX_CODE_4 =  "SELECT MAX(CAST(SPLIT_PART(MA, '.', 4) AS INT)) FROM DR_DISABILITYPEOPLE WHERE ID_TINH= ?";
    
    public final String SQL_SELECT_MAX_CODE_NKT = "Select ma from dr_disabilitypeople where id = (SELECT MAX(ID) FROM DR_DISABILITYPEOPLE WHERE ID_TINH= ?)";

    public final String SQL_INSERT_TINH_TINH  =  INSERT_INTO + TABLE_TINH + FIELDS(TINH_ALL_FIELDS,true) + VALUES(TINH_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_TINH_TINH  =  UPDATE + TABLE_TINH + SET + SETS(TINH_ALL_FIELDS) + WHERE + TINH_TINH_ID + EQUAL + QUESTION;
    
    public final String SQL_UPDATE_USERS_AREA  =  UPDATE + TABLE_USERS + SET + USERS_AREA + EQUAL + QUESTION + WHERE  + USERS_USER_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_TINH_INFORMATION  =  SELECT  + STAR + FROM + TABLE_TINH +  WHERE + TINH_CODE + EQUAL + QUESTION + AND + TINH_TINH_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_TINH_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_TINH +  WHERE + TINH_PARENT_ID + EQUAL + QUESTION;
    //DANG TAT
    public final String SQL_SELECT_DANGTAT  =  SELECT + FIELDS("A",new String[]{DANGTAT_DANGTAT_ID ,DANGTAT_NAME,DANGTAT_PARENT_ID }) + COMMA + 
                                                            FIELDS("B",new String[]{DANGTAT_DANGTAT_ID ,DANGTAT_NAME,DANGTAT_PARENT_ID }) + 
                                                            FROM + TABLE_DANGTAT + " A" + LEFT_OUTER_JOIN + TABLE_DANGTAT + " B" + 
                                                            ON + "B." + DANGTAT_PARENT_ID + EQUAL + "A." + DANGTAT_DANGTAT_ID +
                                                            ORDER_BY + "A." + DANGTAT_PARENT_ID + COMMA +"A.ORDER_BY,B.ORDER_BY";
                                                            
    public final String SQL_SELECT_DANGTAT_STRIMG_BY_PHANLOAI_ID="SELECT NAME,DANGTAT_ID FROM dr_classification WHERE (SELECT DANGTAT_IDS FROM DR_PHANLOAI WHERE ID_NKT=? AND ID=? ORDER BY DATECREATE DESC LIMIT 1) LIKE '%#'|| DANGTAT_ID || '#%'";

    public final String SQL_SELECT_DANGTAT_STRIMG="SELECT NAME,DANGTAT_ID FROM dr_classification WHERE (SELECT DANGTAT_IDS FROM DR_PHANLOAI WHERE ID_NKT=? ORDER BY DATECREATE DESC LIMIT 1) LIKE '%#'|| DANGTAT_ID || '#%'";
    
    public final String SQL_SELECT_NGUYENNHAN_STRING = "SELECT NAME FROM dr_phanloai INNER JOIN dr_donvi ON dr_phanloai.nguyennhan_id = dr_donvi.donvi_id WHERE id_nkt=?";

    public final String SQL_SELECT_NGUYENNHAN_STRING_BY_PHANLOAI_ID = "SELECT NAME FROM dr_phanloai INNER JOIN dr_donvi ON dr_phanloai.nguyennhan_id = dr_donvi.donvi_id WHERE id_nkt=? AND id=?";
    
    // Add By ThuyNV
    public final String SQL_SELECT_VANDONG_KHAC_STRING = "SELECT VANDONG_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? ORDER BY DATECREATE DESC LIMIT 1";

    public final String SQL_SELECT_VANDONG_KHAC_STRING_BY_PHANLOAI_ID = "SELECT VANDONG_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? AND ID = ?";
    
    public final String SQL_SELECT_KHUYETTAT_KHAC_STRING = "SELECT KHUYETTAT_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? ORDER BY DATECREATE DESC LIMIT 1";

    public final String SQL_SELECT_KHUYETTAT_KHAC_STRING_BY_PHANLOAI_ID = "SELECT KHUYETTAT_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? AND ID = ?";
    
    public final String SQL_SELECT_NGUYENNHAN_KHAC_STRING = "SELECT NGUYENNHAN_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? ORDER BY DATECREATE DESC LIMIT 1";

    public final String SQL_SELECT_NGUYENNHAN_KHAC_STRING_BY_PHANLOAI_ID = "SELECT NGUYENNHAN_KHAC FROM DR_PHANLOAI WHERE ID_NKT = ? AND ID = ?";
    
    public final String SQL_SELECT_HOTRO_STRIMG="SELECT NAME FROM dr_HOTRO WHERE (select dm_hotro_ids from DR_SUPPORT WHERE ID_NKT=? AND STATUS_ID=? ORDER BY DATECREATE DESC LIMIT 1) LIKE '%#'|| HOTRO_ID || '#%'";
    
    public final String SQL_SELECT_DANGTAT_BY_ID  =  SELECT  + STAR + FROM + TABLE_DANGTAT + WHERE + DANGTAT_DANGTAT_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_DANGTAT_DANGTAT  =  INSERT_INTO + TABLE_DANGTAT + FIELDS(DANGTAT_ALL_FIELDS,true) + VALUES(DANGTAT_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_DANGTAT_DANGTAT  =  UPDATE + TABLE_DANGTAT + SET + SETS(DANGTAT_ALL_FIELDS) + WHERE + DANGTAT_DANGTAT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_DANGTAT_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DANGTAT +  WHERE + DANGTAT_CODE + EQUAL + QUESTION + AND + DANGTAT_DANGTAT_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_DANGTAT_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DANGTAT +  WHERE + DANGTAT_PARENT_ID + EQUAL + QUESTION;
    //DIEU KIEN
    public final String SQL_SELECT_DIEUKIEN  =  SELECT + FIELDS("A",new String[]{DIEUKIEN_DIEUKIEN_ID ,DIEUKIEN_NAME,DIEUKIEN_PARENT_ID }) + COMMA + 
                                                         FIELDS("B",new String[]{DIEUKIEN_DIEUKIEN_ID ,DIEUKIEN_NAME,DIEUKIEN_PARENT_ID }) + 
                                                         FROM + TABLE_DIEUKIEN + " A" + LEFT_OUTER_JOIN + TABLE_DIEUKIEN + " B" + 
                                                         ON + "B." + DIEUKIEN_PARENT_ID + EQUAL + "A." + DIEUKIEN_DIEUKIEN_ID +
                                                         ORDER_BY + "A." + DIEUKIEN_PARENT_ID + COMMA +"A." + DIEUKIEN_DIEUKIEN_ID;
 
    public final String SQL_SELECT_DIEUKIEN_BY_ID  =  SELECT  + STAR + FROM + TABLE_DIEUKIEN + WHERE + DIEUKIEN_DIEUKIEN_ID + EQUAL + QUESTION;
 
    public final String SQL_INSERT_DIEUKIEN_DIEUKIEN  =  INSERT_INTO + TABLE_DIEUKIEN + FIELDS(DIEUKIEN_ALL_FIELDS,true) + VALUES(DIEUKIEN_ALL_FIELDS.length);
 
    public final String SQL_UPDATE_DIEUKIEN_DIEUKIEN  =  UPDATE + TABLE_DIEUKIEN + SET + SETS(DIEUKIEN_ALL_FIELDS) + WHERE + DIEUKIEN_DIEUKIEN_ID + EQUAL + QUESTION;
 
    public final String SQL_SELECT_DIEUKIEN_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DIEUKIEN +  WHERE + DIEUKIEN_CODE + EQUAL + QUESTION + AND + DIEUKIEN_DIEUKIEN_ID + DIFF + QUESTION;
 
    public final String SQL_SELECT_DIEUKIEN_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DIEUKIEN +  WHERE + DIEUKIEN_PARENT_ID + EQUAL + QUESTION;
    //LY DO
    public final String SQL_SELECT_QUANHE  =  SELECT + FIELDS("A",new String[]{QUANHE_ID ,QUANHE_NAME,QUANHE_PARENT_ID }) + COMMA + 
                                                         FIELDS("B",new String[]{QUANHE_ID ,QUANHE_NAME,QUANHE_PARENT_ID }) + 
                                                         FROM + TABLE_QUANHE + " A" + LEFT_OUTER_JOIN + TABLE_QUANHE + " B" + 
                                                         ON + "B." + QUANHE_PARENT_ID + EQUAL + "A." + QUANHE_ID +
                                                         ORDER_BY + "A." + QUANHE_PARENT_ID + COMMA +"A." + QUANHE_ID;
 
    public final String SQL_SELECT_QUANHE_BY_ID  =  SELECT  + STAR + FROM + TABLE_QUANHE + WHERE + LYDO_LYDO_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_QUANHE  =  INSERT_INTO + TABLE_QUANHE + FIELDS(QUANHE_ALL_FIELDS,true) + VALUES(QUANHE_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_QUANHE  =  UPDATE + TABLE_QUANHE + SET + SETS(QUANHE_ALL_FIELDS) + WHERE + QUANHE_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_QUANHE_INFORMATION  =  SELECT  + STAR + FROM + TABLE_QUANHE +  WHERE + QUANHE_CODE + EQUAL + QUESTION + AND + QUANHE_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_QUANHE_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_QUANHE +  WHERE + QUANHE_PARENT_ID + EQUAL + QUESTION;
 
    //DON VI
    public final String SQL_SELECT_DONVI  =  SELECT + FIELDS("A",new String[]{DONVI_DONVI_ID ,DONVI_NAME,DONVI_PARENT_ID }) + COMMA +
                                                     FIELDS("B",new String[]{DONVI_DONVI_ID ,DONVI_NAME,DONVI_PARENT_ID }) + 
                                                     FROM + TABLE_DONVI + " A" + LEFT_OUTER_JOIN + TABLE_DONVI + " B" + 
                                                     ON + "B." + DONVI_PARENT_ID + EQUAL + "A." + DONVI_DONVI_ID +
                                                     ORDER_BY + "A." + DONVI_PARENT_ID + COMMA +"A." + DONVI_DONVI_ID;

    public final String SQL_SELECT_DONVI_BY_ID  =  SELECT  + STAR + FROM + TABLE_DONVI + WHERE + DONVI_DONVI_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_DONVI_DONVI  =  INSERT_INTO + TABLE_DONVI + FIELDS(DONVI_ALL_FIELDS,true) + VALUES(DONVI_ALL_FIELDS.length);

    public final String SQL_UPDATE_DONVI_DONVI  =  UPDATE + TABLE_DONVI + SET + SETS(DONVI_ALL_FIELDS) + WHERE + DONVI_DONVI_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_DONVI_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DONVI +  WHERE + DONVI_CODE + EQUAL + QUESTION + AND + DONVI_DONVI_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_DONVI_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DONVI +  WHERE + DONVI_PARENT_ID + EQUAL + QUESTION;

    // NGUYEN NHAN KHUYET TAT
    public final String SQL_SELECT_NGUYENNHAN  =  SELECT + FIELDS("A",new String[]{NGUYENNHAN_NNHAN_ID ,NGUYENNHAN_NNHAN_NAME,NGUYENNHAN_NNHAN_PARENT_ID }) + COMMA +
                                                     FIELDS("B",new String[]{NGUYENNHAN_NNHAN_ID ,NGUYENNHAN_NNHAN_NAME,NGUYENNHAN_NNHAN_PARENT_ID }) + 
                                                     FROM + TABLE_NGUYENNHAN + " A" + LEFT_OUTER_JOIN + TABLE_NGUYENNHAN + " B" + 
                                                     ON + "B." + NGUYENNHAN_NNHAN_PARENT_ID + EQUAL + "A." + NGUYENNHAN_NNHAN_ID +
                                                     ORDER_BY + "A." + NGUYENNHAN_NNHAN_PARENT_ID + COMMA +"A." + NGUYENNHAN_NNHAN_ID;

    public final String SQL_SELECT_NGUYENNHAN_BY_ID  =  SELECT  + STAR + FROM + TABLE_NGUYENNHAN + WHERE + NGUYENNHAN_NNHAN_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_NGUYENNHAN  =  INSERT_INTO + TABLE_NGUYENNHAN + FIELDS(NGUYENNHAN_ALL_FIELDS,true) + VALUES(NGUYENNHAN_ALL_FIELDS.length);

    public final String SQL_UPDATE_NGUYENNHAN  =  UPDATE + TABLE_NGUYENNHAN + SET + SETS(NGUYENNHAN_ALL_FIELDS) + WHERE + NGUYENNHAN_NNHAN_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_NGUYENNHAN_INFORMATION  =  SELECT  + STAR + FROM + TABLE_NGUYENNHAN +  WHERE + NGUYENNHAN_NNHAN_CODE + EQUAL + QUESTION + AND + NGUYENNHAN_NNHAN_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_NGUYENNHAN_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_NGUYENNHAN +  WHERE + NGUYENNHAN_NNHAN_PARENT_ID + EQUAL + QUESTION;
    
  // NGUYEN NHAN KHUYET TAT
    public final String SQL_SELECT_DOITUONG  =  SELECT + FIELDS("A",new String[]{DOITUONG_ID ,DOITUONG_NAME,DOITUONG_PARENT_ID }) + COMMA +
                                                     FIELDS("B",new String[]{DOITUONG_ID ,DOITUONG_NAME,DOITUONG_PARENT_ID }) + 
                                                     FROM + TABLE_DOITUONG + " A" + LEFT_OUTER_JOIN + TABLE_DOITUONG + " B" + 
                                                     ON + "B." + DOITUONG_PARENT_ID + EQUAL + "A." + DOITUONG_ID +
                                                     ORDER_BY + "A." + DOITUONG_PARENT_ID + COMMA +"A." + DOITUONG_ID;
  
    public final String SQL_SELECT_DOITUONG_BY_ID  =  SELECT  + STAR + FROM + TABLE_DOITUONG + WHERE + DOITUONG_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_DOITUONG  =  INSERT_INTO + TABLE_DOITUONG + FIELDS(DOITUONG_ALL_FIELDS,true) + VALUES(DOITUONG_ALL_FIELDS.length);
  
    public final String SQL_UPDATE_DOITUONG  =  UPDATE + TABLE_DOITUONG + SET + SETS(DOITUONG_ALL_FIELDS) + WHERE + DOITUONG_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_DOITUONG_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DOITUONG +  WHERE + DOITUONG_CODE + EQUAL + QUESTION + AND + DOITUONG_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_DOITUONG_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DOITUONG +  WHERE + DOITUONG_PARENT_ID + EQUAL + QUESTION;
    
    //DAN TOC
    public final String SQL_SELECT_DANTOC  =  SELECT + FIELDS("A",new String[]{DANTOC_DANTOC_ID ,DANTOC_NAME,DANTOC_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{DANTOC_DANTOC_ID ,DANTOC_NAME,DANTOC_PARENT_ID }) + 
                                                         FROM + TABLE_DANTOC + " A" + LEFT_OUTER_JOIN + TABLE_DANTOC + " B" + 
                                                         ON + "B." + DANTOC_PARENT_ID + EQUAL + "A." + DANTOC_DANTOC_ID +
                                                         ORDER_BY + "A." + DANTOC_PARENT_ID + COMMA +"A." + DANTOC_DANTOC_ID;
    
    public final String SQL_SELECT_DANTOC_BY_ID  =  SELECT  + STAR + FROM + TABLE_DANTOC + WHERE + DANTOC_DANTOC_ID + EQUAL + QUESTION;
   
    public final String SQL_INSERT_DANTOC_DANTOC  =  INSERT_INTO + TABLE_DANTOC + FIELDS(DANTOC_ALL_FIELDS,true) + VALUES(DANTOC_ALL_FIELDS.length);
   
    public final String SQL_UPDATE_DANTOC_DANTOC  =  UPDATE + TABLE_DANTOC + SET + SETS(DANTOC_ALL_FIELDS) + WHERE + DANTOC_DANTOC_ID + EQUAL + QUESTION;
   
    public final String SQL_SELECT_DANTOC_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DANTOC +  WHERE + DANTOC_CODE + EQUAL + QUESTION + AND + DANTOC_DANTOC_ID + DIFF + QUESTION;
   
    public final String SQL_SELECT_DANTOC_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DANTOC +  WHERE + DANTOC_PARENT_ID + EQUAL + QUESTION;

    //DUNGCU    
    public final String SQL_SELECT_DUNGCU  =  SELECT + FIELDS("A",new String[]{DUNGCU_DUNGCU_ID ,DUNGCU_NAME,DUNGCU_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{DUNGCU_DUNGCU_ID ,DUNGCU_NAME,DUNGCU_PARENT_ID }) + 
                                                         FROM + TABLE_DUNGCU + " A" + LEFT_OUTER_JOIN + TABLE_DUNGCU + " B" + 
                                                         ON + "B." + DUNGCU_PARENT_ID + EQUAL + "A." + DUNGCU_DUNGCU_ID +
                                                         ORDER_BY + "A." + DUNGCU_PARENT_ID + COMMA +"A." + DUNGCU_DUNGCU_ID;
    
    public final String SQL_SELECT_DUNGCU_BY_ID  =  SELECT  + STAR + FROM + TABLE_DUNGCU + WHERE + DUNGCU_DUNGCU_ID + EQUAL + QUESTION;
   
    public final String SQL_INSERT_DUNGCU_DUNGCU  =  INSERT_INTO + TABLE_DUNGCU + FIELDS(DUNGCU_ALL_FIELDS,true) + VALUES(DUNGCU_ALL_FIELDS.length);
   
    public final String SQL_UPDATE_DUNGCU_DUNGCU  =  UPDATE + TABLE_DUNGCU + SET + SETS(DUNGCU_ALL_FIELDS) + WHERE + DUNGCU_DUNGCU_ID + EQUAL + QUESTION;
   
    public final String SQL_SELECT_DUNGCU_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DUNGCU +  WHERE + DUNGCU_CODE + EQUAL + QUESTION + AND + DUNGCU_DUNGCU_ID + DIFF + QUESTION;
   
    public final String SQL_SELECT_DUNGCU_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DUNGCU +  WHERE + DUNGCU_PARENT_ID + EQUAL + QUESTION;
    
    //CATEGORY HOTRO
        
    public final String SQL_SELECT_DM_HOTRO  =  SELECT + FIELDS("A",new String[]{DM_HOTRO_HOTRO_ID ,DM_HOTRO_NAME,DM_HOTRO_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{DM_HOTRO_HOTRO_ID ,DM_HOTRO_NAME,DM_HOTRO_PARENT_ID }) + 
                                                         FROM + TABLE_DM_HOTRO + " A" + LEFT_OUTER_JOIN + TABLE_DM_HOTRO + " B" + 
                                                         ON + "B." + DM_HOTRO_PARENT_ID + EQUAL + "A." + DM_HOTRO_HOTRO_ID +
                                                         ORDER_BY + "A." + DM_HOTRO_PARENT_ID + COMMA +"A.ORDER_BY,B.ORDER_BY";
    
    public final String SQL_SELECT_DM_HOTRO_BY_ID  =  SELECT  + STAR + FROM + TABLE_DM_HOTRO + WHERE + DM_HOTRO_HOTRO_ID + EQUAL + QUESTION;

    public final String SQL_SELECT_DM_HOTRO_BY_PARENT_ID  =  SELECT  + STAR + FROM + TABLE_DM_HOTRO + WHERE + DM_HOTRO_PARENT_ID + EQUAL + QUESTION;
  
    public final String SQL_INSERT_DM_HOTRO_HOTRO  =  INSERT_INTO + TABLE_DM_HOTRO + FIELDS(DM_HOTRO_ALL_FIELDS,true) + VALUES(DM_HOTRO_ALL_FIELDS.length);
  
    public final String SQL_UPDATE_DM_HOTRO_HOTRO  =  UPDATE + TABLE_DM_HOTRO + SET + SETS(DM_HOTRO_ALL_FIELDS) + WHERE + DM_HOTRO_HOTRO_ID + EQUAL + QUESTION;
  
    public final String SQL_SELECT_DM_HOTRO_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DM_HOTRO +  WHERE + DM_HOTRO_CODE + EQUAL + QUESTION + AND + DM_HOTRO_HOTRO_ID + DIFF + QUESTION;
  
    public final String SQL_SELECT_DM_HOTRO_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DM_HOTRO +  WHERE + DM_HOTRO_PARENT_ID + EQUAL + QUESTION;
    //danhgia
    
    public final String SQL_SELECT_DANHGIA  =  SELECT + FIELDS("A",new String[]{DANHGIA_DANHGIA_ID ,DANHGIA_NAME,DANHGIA_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{DANHGIA_DANHGIA_ID ,DANHGIA_NAME,DANHGIA_PARENT_ID }) + 
                                                         FROM + TABLE_DANHGIA + " A" + LEFT_OUTER_JOIN + TABLE_DANHGIA + " B" + 
                                                         ON + "B." + DANHGIA_PARENT_ID + EQUAL + "A." + DANHGIA_DANHGIA_ID +
                                                         ORDER_BY + "A." + DANHGIA_PARENT_ID + COMMA +"A.ORDER_BY,B.ORDER_BY";
    
    public final String SQL_SELECT_DANHGIA_BY_ID  =  SELECT  + STAR + FROM + TABLE_DANHGIA + WHERE + DANHGIA_DANHGIA_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_DANHGIA_DANHGIA  =  INSERT_INTO + TABLE_DANHGIA + FIELDS(DANHGIA_ALL_FIELDS,true) + VALUES(DANHGIA_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_DANHGIA_DANHGIA  =  UPDATE + TABLE_DANHGIA + SET + SETS(DANHGIA_ALL_FIELDS) + WHERE + DANHGIA_DANHGIA_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_DANHGIA_INFORMATION  =  SELECT  + STAR + FROM + TABLE_DANHGIA +  WHERE + DANHGIA_CODE + EQUAL + QUESTION + AND + DANHGIA_DANHGIA_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_DANHGIA_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_DANHGIA +  WHERE + DANHGIA_PARENT_ID + EQUAL + QUESTION;
    
    // PHAN LOAI
    public final String SQL_INSERT_PHANLOAI_DANGTAT=INSERT_INTO + TABLE_PHANLOAI + FIELDS(PHANLOAI_ALL_FIELDS,true) + VALUES(PHANLOAI_ALL_FIELDS.length);
    
    public final String SQL_SELECT_ALL_PHANLOAI_BY_ID_NKT=
                      " SELECT USERS.FULLNAME,DR_PHANLOAI.*, get_dangtat(DR_PHANLOAI.ID, DR_PHANLOAI.ID_NKT) DANGTAT, NGUYENNHAN.NAME NNHAN_NAME, MUCDO.NAME MUCDO_NAME FROM DR_PHANLOAI \n" + 
                      " LEFT JOIN USERS ON DR_PHANLOAI.USER_ID=USERS.USER_ID \n" + 
                      " LEFT JOIN DR_NGUYENNHAN NGUYENNHAN ON DR_PHANLOAI.NGUYENNHAN_ID=NGUYENNHAN.NGUYENNHAN_ID\n" + 
                      " LEFT JOIN DR_MUCDO MUCDO ON DR_PHANLOAI.MUCDO_ID=MUCDO.MUCDO_ID\n" + 
                      " WHERE ID_NKT=? ORDER BY DR_PHANLOAI.DATECREATE DESC ";

    public final String SQL_SELECT_ALL_PHANLOAI_BY_ID=
                      " SELECT USERS.FULLNAME,DR_PHANLOAI.*, get_dangtat(DR_PHANLOAI.ID, DR_PHANLOAI.ID_NKT) DANGTAT, NGUYENNHAN.NAME NNHAN_NAME, MUCDO.NAME MUCDO_NAME FROM DR_PHANLOAI \n" + 
                      " LEFT JOIN USERS ON DR_PHANLOAI.USER_ID=USERS.USER_ID \n" + 
                      " LEFT JOIN DR_NGUYENNHAN NGUYENNHAN ON DR_PHANLOAI.NGUYENNHAN_ID=NGUYENNHAN.NGUYENNHAN_ID\n" + 
                      " LEFT JOIN DR_MUCDO MUCDO ON DR_PHANLOAI.MUCDO_ID=MUCDO.MUCDO_ID\n" + 
                      " WHERE DR_PHANLOAI.ID=? ORDER BY DR_PHANLOAI.DATECREATE DESC ";

    public final String SQL_UPDATE_PHANLOAI=  UPDATE + TABLE_PHANLOAI + SET + SETS(PHANLOAI_ALL_FIELDS) + WHERE + PHANLOAI_ID + EQUAL + QUESTION;
    
    public final String SQL_GET_ROW_EXPORT = "select max(b.total) total from \n" + 
                        "(select a.status_id, sum(a.total) total from\n" + 
                        "(select status_id, count(distinct id_nkt) total from dr_support where id_nkt=? group by status_id, id_nkt, datecreate\n" + 
                        " union all\n" + 
                        " select -1 status_id, count(distinct id_nkt) total from dr_phanloai where id_nkt=? group by id_nkt) a\n" + 
                        "group by status_id) b";
    
    // NGUYEN NHAN
    public final String SQL_SELECT_ALL_NGUYENNHAN  =  SELECT + FIELDS("A",new String[]{DONVI_DONVI_ID ,DONVI_NAME, DONVI_PARENT_ID}) + COMMA + 
                                                            FIELDS("B",new String[]{DONVI_DONVI_ID ,DONVI_NAME,DONVI_PARENT_ID }) + 
                                                            FROM + TABLE_DONVI + " A" + LEFT_OUTER_JOIN + TABLE_DONVI + " B" + 
                                                            ON + "B." + DONVI_PARENT_ID + EQUAL + "A." + DONVI_DONVI_ID +
                                                            ORDER_BY + "A." + DONVI_PARENT_ID + COMMA +"A.ORDER_BY,B.ORDER_BY";
    
    // RANK
     public final String SQL_CHECK_INSERT_1_12_TINHTRANG=SELECT + STAR + FROM + TABLE_RANK + WHERE + RANK_DATECREATE + " >= " + QUESTION + AND + RANK_DATECREATE + " < " + QUESTION + AND + RANK_ID_NKT + EQUAL + QUESTION;
     
    public final String SQL_INSERT_RANK_DANGTAT=INSERT_INTO + TABLE_RANK + FIELDS(RANK_ALL_FIELDS,true) + VALUES(RANK_ALL_FIELDS.length);
    
    public final String SQL_SELECT_ALL_RANK_BY_ID_NKT=
    SELECT + TABLE_USERS + STOP + USERS_FULLNAME + COMMA +  TABLE_RANK + STOP + STAR + FROM + TABLE_RANK + 
    LEFT_JOIN + TABLE_USERS + ON + TABLE_RANK + STOP + RANK_USER_ID + EQUAL + TABLE_USERS + STOP + USERS_USER_ID +
    WHERE + RANK_ID_NKT + EQUAL + QUESTION + ORDER_BY + RANK_ID + DESC ;
    public final String SQL_SELECT_ALL_RANK_BY_ID=SELECT + STAR + FROM + TABLE_RANK + WHERE + RANK_ID + EQUAL + QUESTION;
    public final String SQL_UPDATE_RANK=  UPDATE + TABLE_RANK + SET + SETS(RANK_ALL_FIELDS) + WHERE + RANK_ID + EQUAL + QUESTION;
    // POPULATION
     public final String SQL_SELECT_POPULATION_INFORMATION  =  SELECT  + STAR + FROM + TABLE_POPULATION +  WHERE + PPLT_PERIOD + EQUAL + QUESTION + AND + PPLT_YEAROFPERIOD + EQUAL + QUESTION + AND + PPLT_ID_TINH + EQUAL + QUESTION + AND + PPLT_ID + DIFF + QUESTION;
    public final String SQL_INSERT_POPULATION=INSERT_INTO + TABLE_POPULATION + FIELDS(POPULATION_ALL_FIELDS,true) + VALUES(POPULATION_ALL_FIELDS.length);
    public final String SQL_UPDATE_POPULATION=  UPDATE + TABLE_POPULATION + SET + SETS(POPULATION_ALL_FIELDS) + WHERE + PPLT_ID + EQUAL + QUESTION;
    public final String SQL_SELECT_ALL_POPULATION_BY_ID=SELECT + STAR + FROM + TABLE_POPULATION + WHERE + PPLT_ID + EQUAL + QUESTION;    
    public final String SQL_SELECT_ALL_POPULATION_BY_PERIOD=SELECT + STAR + FROM + TABLE_POPULATION + WHERE + PPLT_PERIOD + EQUAL + QUESTION + AND + PPLT_YEAROFPERIOD + EQUAL + QUESTION + AND + PPLT_ID_TINH + EQUAL + QUESTION;    

    public final String SQL_SELECT_ALL_POPULATION_BY_ID_TINH=SELECT + STAR + FROM + TABLE_POPULATION + WHERE + PPLT_ID_TINH + EQUAL + QUESTION;    
    public final String SQL_SELECT_ALL_POPULATION_SUM_BY_PERIOD="SELECT sum(male_less_than_18) as male_less_than_18, sum(male_more_than_18) as male_more_than_18,sum(famale_less_than_18) as famale_less_than_18, sum(famale_more_than_18) as famale_more_than_18, sum(number_1) as number_1, " +
    "sum(number_2) as number_2, sum(number_3) as number_3, sum(number_4) as number_4, sum(number_5) as number_5, sum(number_6) as number_6, sum(number_7) as number_7 " +
    "FROM dr_population where period=? and yearofperiod=? and id_tinh in ?";
    
    // DANSOHUYEN
    public final String SQL_SELECT_ALL_DANSOHUYEN_BY_PERIOD=SELECT + STAR + FROM + TABLE_DANSOHUYEN + WHERE + DANSOHUYEN_PERIOD + EQUAL + QUESTION + AND + DANSOHUYEN_YEAROFPERIOD + EQUAL + QUESTION + AND + DANSOHUYEN_ID_PROVINCE + EQUAL + QUESTION + ORDER_BY + DANSOHUYEN_YEAROFPERIOD + DESC;

    public final String SQL_INSERT_DANSOHUYEN=INSERT_INTO + TABLE_DANSOHUYEN + FIELDS(DANSOHUYEN_ALL_FIELDS,true) + VALUES(DANSOHUYEN_ALL_FIELDS.length);
    
    public final String SQL_CHECK_ID_TINH_IS_EXITS=SELECT + STAR + FROM + TABLE_DANSOHUYEN + WHERE + DANSOHUYEN_PERIOD + EQUAL + QUESTION + AND + DANSOHUYEN_YEAROFPERIOD + EQUAL + QUESTION + AND + DANSOHUYEN_ID_PROVINCE + EQUAL + QUESTION + AND + DANSOHUYEN_ID + DIFF + QUESTION ;
    
    public final String SQL_UPDATE_DANSOHUYEN=  UPDATE + TABLE_DANSOHUYEN + SET + SETS(DANSOHUYEN_ALL_FIELDS) + WHERE + DANSOHUYEN_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_ALL_DANSOHUYEN_BY_ID=SELECT + STAR + FROM + TABLE_DANSOHUYEN + WHERE + DANSOHUYEN_ID + EQUAL + QUESTION;    
    
    public final String SQL_SELECT_ALL_DANSOHUYEN_BY_ID_TINH=
        SELECT  + TABLE_TINH + STOP + TINH_NAME + COMMA +  TABLE_DANSOHUYEN + STOP + STAR + FROM + TABLE_DANSOHUYEN + 
        LEFT_JOIN + TABLE_TINH + ON + TABLE_TINH + STOP + TINH_TINH_ID + EQUAL + TABLE_DANSOHUYEN + STOP + DANSOHUYEN_ID_PROVINCE +
        WHERE + DANSOHUYEN_ID_PROVINCE + EQUAL + QUESTION + ORDER_BY + TABLE_DANSOHUYEN + STOP + DANSOHUYEN_YEAROFPERIOD + DESC;    
    
    //DANSO TINH
    public final String SQL_INSERT_INFOR_NKT=INSERT_INTO + TABLE_DR_TEMP_TG + FIELDS(INFOR_NKT_ALL_FIELDS,true) + VALUES(INFOR_NKT_ALL_FIELDS.length);

     // DANSOHUYEN
    public final String SQL_SELECT_ALL_DANSOTINH_BY_PERIOD=SELECT + STAR + FROM + TABLE_DANSOTINH + WHERE + DANSOTINH_PERIOD + EQUAL + QUESTION + AND + DANSOTINH_YEAROFPERIOD + EQUAL + QUESTION + AND + DANSOTINH_ID_PROVINCE + EQUAL + QUESTION;    

    public final String SQL_INSERT_DANSOTINH=INSERT_INTO + TABLE_DANSOTINH + FIELDS(DANSOTINH_ALL_FIELDS,true) + VALUES(DANSOTINH_ALL_FIELDS.length);
    
    public final String SQL_CHECK_ID_TINH_IS_EXITS_DANSOTINH=SELECT + STAR + FROM + TABLE_DANSOTINH + WHERE + DANSOTINH_PERIOD + EQUAL + QUESTION + AND + DANSOTINH_YEAROFPERIOD + EQUAL + QUESTION + AND + DANSOTINH_ID_PROVINCE + EQUAL + QUESTION + AND + DANSOTINH_ID + DIFF + QUESTION;
    
    public final String SQL_UPDATE_DANSOTINH=  UPDATE + TABLE_DANSOTINH + SET + SETS(DANSOTINH_ALL_FIELDS) + WHERE + DANSOTINH_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_ALL_DANSOTINH_BY_ID=SELECT + STAR + FROM + TABLE_DANSOTINH + WHERE + DANSOTINH_ID + EQUAL + QUESTION;    
    
    public final String SQL_SELECT_ALL_DANSOTINH_BY_ID_TINH=
        SELECT  + TABLE_TINH + STOP + TINH_NAME + COMMA +  TABLE_DANSOTINH + STOP + STAR + FROM + TABLE_DANSOTINH + 
        LEFT_JOIN + TABLE_TINH + ON + TABLE_TINH + STOP + TINH_TINH_ID + EQUAL + TABLE_DANSOTINH + STOP + DANSOTINH_ID_PROVINCE +
        WHERE + DANSOTINH_ID_PROVINCE + EQUAL + QUESTION;    
    
    // HOTRO
    public final String SQL_CHECK_INSERT_1_12=SELECT + STAR + FROM + TABLE_HOTRO + WHERE + HOTRO_DATECREATE + " >= " + QUESTION + AND + HOTRO_DATECREATE + " < " + QUESTION + AND + HOTRO_ID_NKT + EQUAL + QUESTION + AND + HOTRO_STATUS_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_HOTRO_DANGTAT=INSERT_INTO + TABLE_HOTRO + FIELDS(HOTRO_ALL_FIELDS,true) + VALUES(HOTRO_ALL_FIELDS.length);
    
    public final String SQL_SELECT_ALL_HOTRO_BY_ID_NKT=
        SELECT + TABLE_USERS + STOP + USERS_FULLNAME + COMMA +  TABLE_HOTRO + STOP + STAR + FROM + TABLE_HOTRO + 
        LEFT_JOIN + TABLE_USERS + ON + TABLE_HOTRO + STOP + HOTRO_USER_ID + EQUAL + TABLE_USERS + STOP + USERS_USER_ID +
        WHERE + HOTRO_ID_NKT + EQUAL + QUESTION + AND + HOTRO_STATUS_ID + EQUAL + QUESTION + 
        ORDER_BY + HOTRO_ID + DESC ;

    public final String SQL_SELECT_ALL_HOTRO_BY_ID = SELECT + STAR + FROM + TABLE_HOTRO + WHERE + HOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_ALL_HOTRO_NGUON_HOTRO_BY=" select * from DR_SUPPORT WHERE ID_NKT=? AND STATUS_ID=1 ORDER BY DATECREATE DESC LIMIT 1 ";    
    
    public final String SQL_UPDATE_HOTRO=  UPDATE + TABLE_HOTRO + SET + SETS(HOTRO_ALL_FIELDS) + WHERE + HOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_UPDATE_HOTRO_DANHGIA =  "UPDATE DR_SUPPORT SET MDO_HLONG=? WHERE DATECREATE=? AND ID_NKT=? AND STATUS_ID=? AND DM_HOTRO_IDS=?";
    
    //DANHGIA HOTRO
    
     public final String SQL_CHECK_INSERT_1_12_DANHGIA = SELECT + STAR + FROM + TABLE_DANHGIA_NKT + WHERE + DANHGIA_NKT_DATECREATE + " >= " + QUESTION + AND + DANHGIA_NKT_DATECREATE + " < " + QUESTION + AND + DANHGIA_NKT_ID_NKT + EQUAL + QUESTION;
    
    public final String SQL_INSERT_DANHGIA=INSERT_INTO + TABLE_DANHGIA_NKT + FIELDS(DANHGIA_NKT_ALL_FIELDS,true) + VALUES(DANHGIA_NKT_ALL_FIELDS.length);
 
    public final String SQL_SELECT_ALL_DANHGIA_NKT_BY_ID_NKT= SELECT + STAR + FROM + TABLE_DANHGIA_NKT + 
                                                                WHERE + DANHGIA_NKT_ID_NKT + EQUAL + QUESTION + AND + DANHGIA_NKT_DATECREATE + " BETWEEN " + QUESTION + AND + QUESTION +
                                                                ORDER_BY + DANHGIA_NKT_DANHGIA ;
                                                                
    public final String SQL_SELECT_DANHGIA_YTE_BY_ID_NKT = SELECT + STAR + FROM + TABLE_DANHGIA_NKT + 
                                                                WHERE + DANHGIA_NKT_ID_NKT + EQUAL + QUESTION + AND + DANHGIA_NKT_DATECREATE + " BETWEEN " + QUESTION + AND + QUESTION + 
                                                                ORDER_BY + DANHGIA_NKT_DATECREATE + DESC;

    public final String SQL_SELECT_DANHGIA_KINHTE_XAHOI_BY_ID_NKT = SELECT + STAR + FROM + TABLE_DANHGIA_NKT + 
                                                                WHERE + DANHGIA_NKT_ID_NKT + EQUAL + QUESTION + AND + DANHGIA_NKT_DATECREATE + " BETWEEN " + QUESTION + AND + QUESTION + 
                                                                ORDER_BY + DANHGIA_NKT_DATECREATE + DESC;
                                                                
    public final String SQL_SELECT_DANHGIA_GIAODUC_BY_ID_NKT = SELECT + STAR + FROM + TABLE_DANHGIA_NKT + 
                                                                WHERE + DANHGIA_NKT_ID_NKT + EQUAL + QUESTION + AND + DANHGIA_NKT_DATECREATE + " BETWEEN " + QUESTION + AND + QUESTION + 
                                                                ORDER_BY + DANHGIA_NKT_DATECREATE + DESC;
    
    public final String SQL_SELECT_ALL_DANHGIA_NKT_BY_ID=SELECT + STAR + FROM + TABLE_DANHGIA_NKT + WHERE + DANHGIA_NKT_ID + EQUAL + QUESTION;
    
    public final String SQL_UPDATE_DANHGIA_NKT=  UPDATE + TABLE_DANHGIA_NKT + SET + SETS(DANHGIA_NKT_ALL_FIELDS) + WHERE + DANHGIA_NKT_ID + EQUAL + QUESTION;

    //LISTREPORT IN HERE
    public final String SQL_SELECT_LISTREPORT_ADDNEW  = INSERT_INTO + TABLE_LISTREPORT + FIELDS(LISTREPORT_ALL_FIELDS,true) + VALUES(LISTREPORT_ALL_FIELDS.length);
    public final String  SQL_SELECT_ALL_LISTREPORT    = SELECT +  STAR + FROM + TABLE_LISTREPORT  + WHERE + LISTREPORT_USER_ID + EQUAL + QUESTION + ORDER_BY + LISTREPORT_LIST_ID + DESC;
    public final String  SQL_SELECT_AMOUNT_EMP        = SELECT + COUNT + OPEN  + LIST_EMP_EMPLOYEE_ID + CLOSE + AS + LIST_EMP_AMOUNT + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID + EQUAL + QUESTION +  GROUP_BY + LIST_EMP_LIST_ID;
    public final String SQL_ADDNEW_LIST_EMP           = INSERT_INTO + TABLE_LIST_EMP + FIELDS(LIST_EMP_ALL_FIELDS,true) + VALUES(LIST_EMP_ALL_FIELDS.length);
    public final String SQL_ADDNEW_LIST_EMP_TEMP           = INSERT_INTO + TABLE_LIST_EMP +  OPEN + LIST_EMP_LIST_ID_TEMP + COMMA + LIST_EMP_EMPLOYEE_ID_TEMP + CLOSE  + VALUES + OPEN + QUESTION + COMMA + QUESTION + CLOSE;
    public final String SQL_ADDNEW_LIST_EMP_ALL           = INSERT_INTO + TABLE_LIST_EMP + OPEN + LIST_EMP_LIST_ID + COMMA + LIST_EMP_EMPLOYEE_ID + CLOSE  + SELECT + LIST_EMP_LIST_ID_TEMP + COMMA + LIST_EMP_EMPLOYEE_ID_TEMP + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_EMPLOYEE_ID_TEMP + IN  + OPEN + SELECT + LIST_EMP_EMPLOYEE_ID_TEMP + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID_TEMP + EQUAL + QUESTION + CLOSE  + AND + LIST_EMP_EMPLOYEE_ID_TEMP + NOT + IN + OPEN + SELECT + LIST_EMP_EMPLOYEE_ID + FROM + TABLE_LIST_EMP +  WHERE  + LIST_EMP_LIST_ID + EQUAL + QUESTION + CLOSE;
    public final String SQL_ADDNEW_LIST_EMP_CHECK_EMPLOYEE_ID  = SELECT + STAR + FROM +  TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID + EQUAL + QUESTION + AND + LIST_EMP_EMPLOYEE_ID + EQUAL + QUESTION;
    public final String  SQL_SELECT_TOP_LISTREPORT    = SELECT +  STAR + FROM + TABLE_LISTREPORT  + ORDER_BY + LISTREPORT_LIST_ID + DESC;
    public final String SQL_UPDATE_LISTREPORT         =   UPDATE + TABLE_LISTREPORT + SET + SETS(LISTREPORT_ALL_FIELDS) + WHERE + LISTREPORT_LIST_ID + EQUAL + QUESTION;
    public final String SQL_GET_LISTREPORT_BY_ID      = SELECT + STAR + FROM + TABLE_LISTREPORT + WHERE + LISTREPORT_LIST_ID + EQUAL + QUESTION  ;
    public final String SQL_LIST_EMP_UPDATE           = INSERT_INTO + TABLE_LIST_EMP + OPEN + LIST_EMP_LIST_ID + COMMA + LIST_EMP_EMPLOYEE_ID + CLOSE + SELECT + QUESTION + COMMA + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + FROM + TABLE_DISABILITYPEOPLE + WHERE + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + IN + OPEN + "#" + CLOSE + AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + NOT + IN + OPEN + SELECT + TABLE_LIST_EMP + STOP + LIST_EMP_EMPLOYEE_ID + FROM + TABLE_LIST_EMP + WHERE + TABLE_LIST_EMP + STOP + LIST_EMP_LIST_ID + EQUAL + QUESTION + CLOSE ;
    public final String SQL_GET_LISTREPORT_EMP_BY_EMP_ID = SELECT + LIST_EMP_EMPLOYEE_ID + FROM +  TABLE_LIST_EMP +
                                                       WHERE + LIST_EMP_LIST_ID + EQUAL + QUESTION;
//    public final String  SQL_ADDWHERE_AGE="YEAR(GETDATE())-YEAR("+TABLE_DISABILITYPEOPLE + STOP + EMPLOYEE_BIRTHDAY+")";
    public final String  SQL_ADDWHERE_EMPID_FROM_LIST_EMP=SELECT + LIST_EMP_EMPLOYEE_ID + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID + EQUAL + QUESTION;
    public final String  SQL_SELECT_EMP_BY_LISTREPORT    = SELECT + TABLE_DISABILITYPEOPLE + STOP + STAR + FROM + TABLE_DISABILITYPEOPLE + WHERE + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + IN + OPEN + SQL_ADDWHERE_EMPID_FROM_LIST_EMP + CLOSE;
    public final String  SQL_GET_LISTREPORT_BY_LISTID=SELECT + STAR + FROM + TABLE_LISTREPORT + WHERE + LISTREPORT_LIST_ID + EQUAL + QUESTION;
    public final String  SQL_GET_LISTREPORT=SELECT + STAR + FROM + OPEN + SELECT + STAR + FROM + TABLE_LISTREPORT + ORDER_BY + LISTREPORT_LIST_ID + DESC + CLOSE + WHERE + "rownum<=1";
    public final String  SQL_SELECT_ALL_EMPLISTREPORT    = SELECT +  STAR + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID + EQUAL + QUESTION  + ORDER_BY + LIST_EMP_EMPLOYEE_ID;
    public final String  SQL_SELECT_ALL_EMPLISTREPORT_BYID=SELECT +  STAR + FROM + TABLE_LIST_EMP + WHERE + LIST_EMP_LIST_ID + IN + OPEN +  SELECT + LISTREPORT_LIST_ID + FROM + OPEN  + SELECT + LISTREPORT_LIST_ID + FROM + TABLE_LISTREPORT + ORDER_BY + LISTREPORT_LIST_ID + DESC + CLOSE + WHERE + "rownum<=1" + CLOSE;
    
    
    public final String  SQL_SELECT_INFOR_NKT="SELECT A.ID,A.MA,A.TEN,A.NGAYSINH,A.SEX,C.NAME AS QHUYEN, B.NAME AS PXA, A.SONHA,A.DACAM, D.DANHGIA_IDS" +
                        " FROM DR_DISABILITYPEOPLE A LEFT JOIN DR_RANK D ON A.ID = D.ID_NKT " +
                        " RIGHT JOIN DR_AREA B ON A.ID_TINH = B.TINH_ID " + 
                        " RIGHT JOIN DR_AREA C ON C.TINH_ID = B.PARENT_ID " +
                        " WHERE A.ID_TINH IN (#) ORDER BY A.TEN"; 
    
    public final String SQL_COUNT_REPORT_INFOR_NKT = "select count(1) from dr_report_infor_nkt";
    
    public final String SQL_SELECT_REPORT_INFOR_NKT = "select * from dr_report_infor_nkt";
    
    public final String SQL_SELECT_STR_TINH_ID = "select get_tinhid_by_parentid(?)";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT = "select * from dr_report_group where loai_bc=? and tinh_export=? and qlc=? order by tinh_id, hoten_nkt ";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_TP = "SELECT b.name, a.* FROM\n" + 
        "(SELECT parent_id, SUM(total), SUM(total_f), SUM(total_m), \n" + 
        "SUM(total_0_6), SUM(total_f_0_6), SUM(total_m_0_6), \n" + 
        "SUM(total_6_15), SUM(total_f_6_15), SUM(total_m_6_15), \n" + 
        "SUM(total_15_60), SUM(total_f_15_60), SUM(total_m_15_60), \n" + 
        "SUM(total_60_80), SUM(total_f_60_80), SUM(total_m_60_80),\n" + 
        "SUM(total_80), SUM(total_f_80), SUM(total_m_80), '' ghichu FROM \n" + 
        "(select v.tinh_id, v.parent_id, v.total, v.total_f, v.total_m, \n" + 
        " v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        " v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        " v.total_15_60, v.total_f_15_60, v.total_m_15_60, \n" + 
        " v.total_60_80, v.total_f_60_80, v.total_m_60_80, \n" + 
        " v.total_80, v.total_f_80, v.total_m_80  \n" + 
        " FROM v_report_group_14 v \n" + 
        " WHERE v.parent_id in (SELECT tinh_id FROM dr_area WHERE parent_id = ? )) a  \n" + 
        " GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
        " WHERE b.parent_id = ? \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_QH = " SELECT b.name, a.* FROM\n" + 
        " (select v.tinh_id, v.total, v.total_f, v.total_m, " +
        " v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        " v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        " v.total_15_60, v.total_f_15_60, v.total_m_15_60, v.total_60_80, v.total_f_60_80, \n" + 
        " v.total_m_60_80, v.total_80, v.total_f_80, v.total_m_80, '' ghichu  \n" + 
        " from v_report_group_14 v \n" + 
        " where v.tinh_id in (select tinh_id from dr_area where parent_id = ? )) a\n" + 
        " RIGHT JOIN dr_area b ON a.tinh_id = b.tinh_id \n" + 
        " WHERE b.parent_id = ? \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_PX = " SELECT b.name, a.* FROM\n" + 
        " (select v.tinh_id, v.total, v.total_f, v.total_m, " +
        " v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        " v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        " v.total_15_60, v.total_f_15_60, v.total_m_15_60, v.total_60_80, v.total_f_60_80, \n" + 
        " v.total_m_60_80, v.total_80, v.total_f_80, v.total_m_80, '' ghichu  \n" + 
        " from v_report_group_14 v \n" + 
        " where v.tinh_id = ?) a\n" + 
        " INNER JOIN dr_area b ON a.tinh_id = b.tinh_id \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_QLC_TP = "SELECT b.name, a.* FROM\n" + 
        "(SELECT parent_id, SUM(total), SUM(total_f), SUM(total_m), \n" + 
        "SUM(total_0_6), SUM(total_f_0_6), SUM(total_m_0_6), \n" +                                                                 
        "SUM(total_6_15), SUM(total_f_6_15), SUM(total_m_6_15), \n" + 
        "SUM(total_15_60), SUM(total_f_15_60), SUM(total_m_15_60), \n" + 
        "SUM(total_60_80), SUM(total_f_60_80), SUM(total_m_60_80),\n" + 
        "SUM(total_80), SUM(total_f_80), SUM(total_m_80), '' ghichu FROM \n" + 
        "(select v.tinh_id, v.parent_id, v.total, v.total_f, v.total_m, \n" + 
        "       v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        "       v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        "       v.total_15_60, v.total_f_15_60, v.total_m_15_60, \n" + 
        "       v.total_60_80, v.total_f_60_80, v.total_m_60_80, \n" + 
        "       v.total_80, v.total_f_80, v.total_m_80  \n" + 
        " FROM v_report_group_14_qlc v \n" + 
        " WHERE v.parent_id in (SELECT tinh_id FROM dr_area WHERE parent_id = ? )) a  \n" + 
        " GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
        " WHERE b.parent_id = ? \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_QLC_QH = " SELECT b.name, a.* FROM\n" + 
        " (SELECT v.tinh_id, v.total, v.total_f, v.total_m, " +
        " v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        " v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        " v.total_15_60, v.total_f_15_60, v.total_m_15_60, \n" +
        " v.total_60_80, v.total_f_60_80, v.total_m_60_80, \n" + 
        " v.total_80, v.total_f_80, v.total_m_80, '' ghichu  \n" + 
        " FROM v_report_group_14_qlc v \n" + 
        " WHERE v.tinh_id IN (SELECT tinh_id FROM dr_area where parent_id = ? )) a\n" + 
        " RIGHT JOIN dr_area b ON a.tinh_id = b.tinh_id \n" + 
        " WHERE b.parent_id = ? \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_14_QLC_PX = " SELECT b.name, a.* FROM\n" + 
        " (select v.tinh_id, v.total, v.total_f, v.total_m, " +
        " v.total_0_6, v.total_f_0_6, v.total_m_0_6, \n" + 
        " v.total_6_15, v.total_f_6_15, v.total_m_6_15, \n" + 
        " v.total_15_60, v.total_f_15_60, v.total_m_15_60, \n" +
        " v.total_60_80, v.total_f_60_80, v.total_m_60_80, \n" + 
        " v.total_80, v.total_f_80, v.total_m_80, '' ghichu  \n" + 
        " from v_report_group_14_qlc v \n" + 
        " where v.tinh_id = ?) a\n" + 
        " INNER JOIN dr_area b ON a.tinh_id = b.tinh_id \n" + 
        " ORDER BY b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_15 = "select map.name, a.total, a.total_15_60, a.t_bamsinh, a.t_benh, a.t_laodong, a.t_giaothong, a.t_chientranh, a.t_khac, \n" + 
                    "a.total_60,  a.t_60_bamsinh, a.t_60_benh, a.t_60_laodong, a.t_60_giaothong, a.t_60_chientranh, a.t_60_khac, '' ghichu\n" + 
                    "from (select dangtat_id, sum(total) total, \n" + 
                    "sum(total_15_60) total_15_60, sum(t_bamsinh) t_bamsinh, \n" +
                    "sum(t_benh) t_benh, sum(t_laodong) t_laodong, \n" +
                    "sum(t_giaothong) t_giaothong, sum(t_chientranh) t_chientranh, sum(t_khac) t_khac, \n" + 
                    "sum(total_60) total_60, sum(t_60_bamsinh) t_60_bamsinh, sum(t_60_benh) t_60_benh, \n" +
                    "sum(t_60_laodong) t_60_laodong, sum(t_60_giaothong) t_60_giaothong, \n" +
                    "sum(t_60_chientranh) t_60_chientranh, sum(t_60_khac) t_60_khac \n" + 
                    "from v_report_group_15 v where v.id_tinh in # group by dangtat_id)  a right join dr_map_dangtat map\n" + 
                    "on a.dangtat_id = map.dangtat_id order by map.stt asc";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_15_QLC = "select map.name, a.total, a.total_15_60, a.t_bamsinh, a.t_benh, a.t_laodong, a.t_giaothong, a.t_chientranh, a.t_khac, \n" + 
                    "a.total_60,  a.t_60_bamsinh, a.t_60_benh, a.t_60_laodong, a.t_60_giaothong, a.t_60_chientranh, a.t_60_khac, '' ghichu\n" + 
                    "from (select dangtat_id, sum(total) total, \n" + 
                    "sum(total_15_60) total_15_60, sum(t_bamsinh) t_bamsinh, \n" +
                    "sum(t_benh) t_benh, sum(t_laodong) t_laodong, \n" +
                    "sum(t_giaothong) t_giaothong, sum(t_chientranh) t_chientranh, sum(t_khac) t_khac, \n" + 
                    "sum(total_60) total_60, sum(t_60_bamsinh) t_60_bamsinh, sum(t_60_benh) t_60_benh, \n" +
                    "sum(t_60_laodong) t_60_laodong, sum(t_60_giaothong) t_60_giaothong, \n" +
                    "sum(t_60_chientranh) t_60_chientranh, sum(t_60_khac) t_60_khac \n" + 
                    "from v_report_group_15_qlc v where v.id_tinh in # group by dangtat_id)  a right join dr_map_dangtat map\n" + 
                    "on a.dangtat_id = map.dangtat_id order by map.stt asc";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16 = "select b.name, a.total, a.total_yte, a.hotro_1, a.hotro_2, a.hotro_3, a.hotro_4, a.hotro_5, a.hotro_6, \n" + 
                    "a.nhucau_1, a.nhucau_2, a.nhucau_3, a.nhucau_4, a.nhucau_5, a.nhucau_6, a.nhucau_7, '' ghichu FROM\n" + 
                    "(select * from v_report_group_16 where id_tinh in #) a right join dr_area b on a.id_tinh = b.tinh_id\n" + 
                    "where b.parent_id = ? order by a.id_tinh";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_TP = "SELECT b.name, a.* FROM\n" + 
                    "(SELECT a.parent_id, sum(a.total), sum(hotro_1), sum(hotro_2), sum(hotro_3), \n" + 
                    "sum(hotro_4), sum(hotro_5), sum(hotro_6), sum(nhucau_1), sum(nhucau_2), \n" + 
                    "sum(nhucau_3), sum(nhucau_4), sum(nhucau_5), sum(nhucau_6), sum(nhucau_7), '' ghichu FROM \n" + 
                    "(SELECT b.parent_id, a.* FROM v_report_group_16 a \n" + 
                    "INNER JOIN dr_area b ON a.id_tinh = b.tinh_id) a\n" + 
                    "GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
                    "WHERE b.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_QH = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_16 \n" + 
                    "WHERE id_tinh IN (SELECT tinh_id FROM dr_area WHERE parent_id = ?))\n" + 
                    "a RIGHT JOIN dr_area b ON a.id_tinh = b.tinh_id\n" + 
                    "WHERE b.parent_id = ? ORDER BY a.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_PX = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_16 \n" + 
                    "WHERE id_tinh = ?)\n" + 
                    "a INNER JOIN dr_area b ON a.id_tinh = b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_QLC_TP = "SELECT b.name, a.* FROM\n" + 
                    "(SELECT a.parent_id, sum(a.total), sum(hotro_1), sum(hotro_2), sum(hotro_3), \n" + 
                    "sum(hotro_4), sum(hotro_5), sum(hotro_6), sum(nhucau_1), sum(nhucau_2), \n" + 
                    "sum(nhucau_3), sum(nhucau_4), sum(nhucau_5), sum(nhucau_6), sum(nhucau_7), '' ghichu FROM \n" + 
                    "(SELECT b.parent_id, a.* FROM v_report_group_16 a \n" + 
                    "INNER JOIN dr_area b ON a.id_tinh = b.tinh_id) a\n" + 
                    "GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
                    "WHERE b.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_QLC_QH = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_16 \n" + 
                    "WHERE id_tinh IN (SELECT tinh_id FROM dr_area WHERE parent_id = ?))\n" + 
                    "a RIGHT JOIN dr_area b ON a.id_tinh = b.tinh_id\n" + 
                    "WHERE b.parent_id = ? ORDER BY a.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_QLC_PX = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_16 \n" + 
                    "WHERE id_tinh = ?)\n" + 
                    "a INNER JOIN dr_area b ON a.id_tinh = b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_16_QLC = "select b.name, a.total, a.total_yte, a.hotro_1, a.hotro_2, a.hotro_3, a.hotro_4, a.hotro_5, a.hotro_6, \n" + 
                    "a.nhucau_1, a.nhucau_2, a.nhucau_3, a.nhucau_4, a.nhucau_5, a.nhucau_6, a.nhucau_7, '' ghichu FROM\n" + 
                    "(SELECT * from v_report_group_16_qlc WHERE id_tinh IN #) a RIGHT JOIN dr_area b ON a.id_tinh = b.tinh_id\n" + 
                    "WHRE b.parent_id = ? ORDER BY a.id_tinh";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_TP = "SELECT d.name, e.* FROM\n" + 
                    "(SELECT e.parent_id, sum(e.total) total, sum(e.dantoc_kinh) dantoc_kinh, sum(e.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(e.trinhdo_1) trinhdo_1, sum(e.trinhdo_2) trinhdo_2, sum(e.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(e.trinhdo_4) trinhdo_4, sum(e.trinhdo_5) trinhdo_5, sum(e.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(e.hotro_1) hotro_1, sum(e.hotro_2) hotro_2, sum(e.hotro_3) hotro_3, sum(e.hotro_4) hotro_4, \n" + 
                    "sum(e.nhucau_1) nhucau_1, sum(e.nhucau_2) nhucau_2, sum(e.nhucau_3) nhucau_3,  \n" + 
                    "sum(e.nhucau_4) nhucau_4, sum(e.nhucau_5) nhucau_5, sum(e.nhucau_6) nhucau_6 FROM\n" + 
                    "(SELECT d.parent_id, c.* FROM                                \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    " (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    " sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    " sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    " sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    " sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    " sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    " FROM v_report_group_171819  \n" + 
                    " WHERE 1=1 GROUP BY id_tinh, age  \n" + 
                    " HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c INNER JOIN dr_area d\n" + 
                    " ON c.id_tinh = d.tinh_id) e GROUP By e.parent_id) e RIGHT JOIN dr_area d\n" + 
                    " ON e.parent_id = d.tinh_id WHERE d.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_QH = "SELECT b.name, c.* FROM  \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    " (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    " sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    " sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    " sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    " sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    " sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    " FROM v_report_group_171819  \n" + 
                    " WHERE id_tinh IN #  \n" + 
                    " GROUP BY id_tinh, age  \n" + 
                    " HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c  \n" + 
                    " INNER JOIN dr_area b ON c.id_tinh = b.tinh_id AND b.parent_id = ? ORDER BY c.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_PX = "SELECT b.name, c.* FROM  \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    " (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    " sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    " sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    " sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    " sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    " sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    " FROM v_report_group_171819  \n" + 
                    " WHERE id_tinh = ?\n" + 
                    " GROUP BY id_tinh, age  \n" + 
                    " HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c  \n" + 
                    " INNER JOIN dr_area b ON c.id_tinh = b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_TP = "SELECT d.name, e.* FROM\n" + 
                    "(SELECT e.parent_id, sum(e.total) total, sum(e.dantoc_kinh) dantoc_kinh, sum(e.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(e.trinhdo_1) trinhdo_1, sum(e.trinhdo_2) trinhdo_2, sum(e.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(e.trinhdo_4) trinhdo_4, sum(e.trinhdo_5) trinhdo_5, sum(e.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(e.hotro_1) hotro_1, sum(e.hotro_2) hotro_2, sum(e.hotro_3) hotro_3, sum(e.hotro_4) hotro_4, \n" + 
                    "sum(e.nhucau_1) nhucau_1, sum(e.nhucau_2) nhucau_2, sum(e.nhucau_3) nhucau_3,  \n" + 
                    "sum(e.nhucau_4) nhucau_4, sum(e.nhucau_5) nhucau_5, sum(e.nhucau_6) nhucau_6 FROM\n" + 
                    "(SELECT d.parent_id, c.* FROM                                \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    "   (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    "   sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    "   sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    "   sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    "   sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    "   sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    "   FROM v_report_group_171819_qlc  \n" + 
                    "   WHERE 1=1 GROUP BY id_tinh, age  \n" + 
                    "   HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c INNER JOIN dr_area d\n" + 
                    "   ON c.id_tinh = d.tinh_id) e GROUP By e.parent_id) e RIGHT JOIN dr_area d\n" + 
                    "   ON e.parent_id = d.tinh_id WHERE d.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_QH = "SELECT b.name, c.* FROM  \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    "   (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    "   sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    "   sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    "   sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    "   sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    "   sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    "   FROM v_report_group_171819  \n" + 
                    "   WHERE id_tinh IN #  \n" + 
                    "   GROUP BY id_tinh, age  \n" + 
                    "   HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c  \n" + 
                    "   INNER JOIN dr_area b ON c.id_tinh = b.tinh_id AND b.parent_id = ? ORDER BY c.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_PX = "SELECT b.name, c.* FROM  \n" + 
                    "(SELECT a.id_tinh, sum(a.total) total, sum(a.dantoc_kinh) dantoc_kinh, sum(a.dantoc_khac) dantoc_khac,  \n" + 
                    "sum(a.trinhdo_1) trinhdo_1, sum(a.trinhdo_2) trinhdo_2, sum(a.trinhdo_3) trinhdo_3,  \n" + 
                    "sum(a.trinhdo_4) trinhdo_4, sum(a.trinhdo_5) trinhdo_5, sum(a.trinhdo_6) trinhdo_6,  \n" + 
                    "sum(a.hotro_1) hotro_1, sum(a.hotro_2) hotro_2, sum(a.hotro_3) hotro_3, sum(a.hotro_4) hotro_4, \n" + 
                    "sum(a.nhucau_1) nhucau_1, sum(a.nhucau_2) nhucau_2, sum(a.nhucau_3) nhucau_3,  \n" + 
                    "sum(a.nhucau_4) nhucau_4, sum(a.nhucau_5) nhucau_5, sum(a.nhucau_6) nhucau_6 FROM  \n" + 
                    "   (SELECT id_tinh, sum(total) total, sum(dantoc_kinh) dantoc_kinh, sum(dantoc_khac) dantoc_khac,  \n" + 
                    "   sum(trinhdo_1) trinhdo_1, sum(trinhdo_2) trinhdo_2, sum(trinhdo_3) trinhdo_3,  \n" + 
                    "   sum(trinhdo_4) trinhdo_4, sum(trinhdo_5) trinhdo_5, sum(trinhdo_6) trinhdo_6,  \n" + 
                    "   sum(hotro_1) hotro_1, sum(hotro_2) hotro_2, sum(hotro_3) hotro_3, sum(hotro_4) hotro_4, \n" + 
                    "   sum(nhucau_1) nhucau_1, sum(nhucau_2) nhucau_2, sum(nhucau_3) nhucau_3,  \n" + 
                    "   sum(nhucau_4) nhucau_4, sum(nhucau_5) nhucau_5, sum(nhucau_6) nhucau_6 \n" + 
                    "   FROM v_report_group_171819_qlc  \n" + 
                    "   WHERE id_tinh = ?\n" + 
                    "   GROUP BY id_tinh, age  \n" + 
                    "   HAVING age >= ? and age < ?) a GROUP BY a.id_tinh) c  \n" + 
                    "   INNER JOIN dr_area b ON c.id_tinh = b.tinh_id";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_TP = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT a.parent_id, sum(a.total), \n" + 
                    "sum(dangtat_1), sum(dangtat_2), sum(dangtat_3), \n" + 
                    "sum(dangtat_4), sum(dangtat_5), sum(dangtat_6), \n" + 
                    "sum(mucdo_1), sum(mucdo_2), sum(mucdo_3) FROM \n" + 
                    "(SELECT b.parent_id, a.* FROM v_report_group_20 a \n" + 
                    "INNER JOIN dr_area b ON a.id_tinh = b.tinh_id) a\n" + 
                    "GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
                    "WHERE b.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_QH = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_20 \n" + 
                    "WHERE id_tinh IN (SELECT tinh_id FROM dr_area WHERE parent_id = ?))\n" + 
                    "a RIGHT JOIN dr_area b ON a.id_tinh = b.tinh_id\n" + 
                    "WHERE b.parent_id = ? ORDER BY a.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_PX = "SELECT b.name, a.*, '' ghichu FROM\n" + 
                    "(SELECT * FROM v_report_group_20 \n" + 
                    "WHERE id_tinh = ? )\n" + 
                    "a INNER JOIN dr_area b ON a.id_tinh = b.tinh_id";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_QLC_TP = "SELECT b.name, a.* FROM\n" + 
                    "(SELECT a.parent_id, sum(a.total), \n" + 
                    "sum(dangtat_1), sum(dangtat_2), sum(dangtat_3), \n" + 
                    "sum(dangtat_4), sum(dangtat_5), sum(dangtat_6), \n" + 
                    "sum(mucdo_1), sum(mucdo_2), sum(mucdo_3) FROM \n" + 
                    "(SELECT b.parent_id, a.* FROM v_report_group_20 a \n" + 
                    "INNER JOIN dr_area b ON a.id_tinh = b.tinh_id) a\n" + 
                    "GROUP BY a.parent_id) a RIGHT JOIN dr_area b ON a.parent_id = b.tinh_id\n" + 
                    "WHERE b.parent_id = ?";
    
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_QLC_QH = "SELECT b.name, a.* FROM\n" + 
                    "(SELECT * FROM v_report_group_20 \n" + 
                    "WHERE id_tinh IN (SELECT tinh_id FROM dr_area WHERE parent_id = ?))\n" + 
                    "a RIGHT JOIN dr_area b ON a.id_tinh = b.tinh_id\n" + 
                    "WHERE b.parent_id = ? ORDER BY a.id_tinh";
        
    public final String SQL_SELECT_REPORT_GROUP_NKT_20_QLC_PX = "SELECT b.name, a.* FROM\n" + 
                    "(SELECT * FROM v_report_group_20 \n" + 
                    "WHERE id_tinh = ? )\n" + 
                    "a INNER JOIN dr_area b ON a.id_tinh = b.tinh_id\n";
  
    public final String SQL_SELECT_REPORT_STATISTICS_NKT = "select * from dr_report_statistics order by tinh_id, hoten_nkt";

    public final String SQL_SELECT_REPORT_ANALYSIS_NKT = "select * from dr_report_analysis_nkt";
    
    public final String SQL_SELECT_REPORT_COLLECT_NKT = "select * from dr_report_collect_nkt";
    
    public final String SQL_SELECT_REPORT_QLC_DETAIL1_QH = "SELECT b.name, a.tgia, a.tgia_laodong, a.tgia_yte, a.tgia_giaoduc," +
                    "a.lke, a.lke_laodong, a.lke_yte, a.lke_giaoduc " +
                    "FROM (SELECT a.tinh_id, \n" + 
                    "SUM(a.tgia) tgia, SUM(a.tgia_laodong) tgia_laodong, SUM(a.tgia_yte) tgia_yte, SUM(a.tgia_giaoduc) tgia_giaoduc ,\n" + 
                    "SUM(a.lke) lke, SUM(a.lke_laodong) lke_laodong, SUM(a.lke_yte) lke_yte, SUM(a.lke_giaoduc) lke_giaoduc \n" + 
                    "FROM (select a.tinh_id, \n" + 
                    "CASE WHEN a.create_date between fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) THEN COUNT(1) ELSE 0 END as tgia, \n" + 
                    "CASE WHEN (a.create_date between fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) and a.laodong=1 ) THEN COUNT(1) ELSE 0 END as tgia_laodong, \n" + 
                    "CASE WHEN (a.create_date between fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) and a.yte=1 ) THEN COUNT(1) ELSE 0 END as tgia_yte, \n" + 
                    "CASE WHEN (a.create_date between fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) and a.giaoduc=1 ) THEN COUNT(1) ELSE 0 END as tgia_giaoduc,\n" + 
                    "CASE WHEN a.create_date <= fn_lastdate_of_month(?) THEN COUNT(1) ELSE 0 END as lke, \n" + 
                    "CASE WHEN (a.create_date <= fn_lastdate_of_month(?) AND a.laodong=1 ) THEN COUNT(1) else 0 END as lke_laodong, \n" + 
                    "CASE WHEN (a.create_date <= fn_lastdate_of_month(?) and a.yte=1 ) THEN COUNT(1) ELSE 0 END as lke_yte, \n" + 
                    "CASE WHEN (a.create_date <= fn_lastdate_of_month(?) and a.giaoduc=1 ) THEN COUNT(1) ELSE 0 END as lke_giaoduc \n" + 
                    "FROM (SELECT a.id, MAX(a.tinh_id) tinh_id, MAX(a.create_date) create_date, MAX(a.linhvuc)  linhvuc \n" + 
                    ", MAX(a.total) total, MAX(a.laodong) laodong, MAX(a.yte) yte, MAX(a.giaoduc) giaoduc, MAX(a.status) status \n" + 
                    "FROM v_report_qlc a\n" + 
                    "group by a.id) a \n" + 
                    "WHERE tinh_id IN # AND status ='QLTH' \n" + 
                    "GROUP by tinh_id, create_date, laodong, yte, giaoduc) a \n" + 
                    "GROUP BY a.tinh_id) a RIGHT JOIN dr_area b on a.tinh_id = b.tinh_id \n" +
                    "WHERE b.parent_id = ? ORDER BY b.name";
                    
    
    public final String SQL_SELECT_REPORT_QLC_DETAIL2_QH = "SELECT b.name, a.total, a.nguyennhan_1, a.nguyennhan_2, a.nguyennhan_3, a.nguyennhan_4\n" + 
                    "FROM (SELECT tinh_id, COUNT(DISTINCT nkt_id) total, \n" + 
                    "SUM(nguyennhan_1) nguyennhan_1, SUM(nguyennhan_2) nguyennhan_2, \n" + 
                    "SUM(nguyennhan_3) nguyennhan_3, SUM(nguyennhan_4) nguyennhan_4 \n" + 
                    "FROM dr_biendong_qlc WHERE status = 'O_QLTH' \n" + 
                    "AND create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) \n" +
                    "AND tinh_id IN # \n" +                                                           
                    "GROUP BY tinh_id) a RIGHT JOIN dr_area b ON a.tinh_id = b.tinh_id \n" + 
                    "WHERE b.parent_id = ? ORDER BY b.name";
    
    public final String SQL_SELECT_REPORT_QLC_DETAIL1_PX = "SELECT a.ten, a.maso, a.namsinh, a.diachi, a.dangtat, a.mucdo, a.dieukien_kt, a.ten_chuho, a.ten_nql\n" + 
                    "FROM (SELECT * FROM v_disabilitypeople WHERE tinh_id = ?) a, \n" + 
                    "(SELECT b.id FROM v_report_qlc b WHERE b.tinh_id = ? AND b.status='QLTH' \n" + 
                    "AND b.create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?)) b \n" + 
                    "WHERE a.nkt_id = b.id ORDER BY a.ten";
    
    public final String SQL_SELECT_REPORT_QLC_DETAIL2_PX = "SELECT a.ten, a.maso, a.namsinh, a.diachi, b.nguyennhan_1, b.nguyennhan_2, b.nguyennhan_3, b.nguyennhan_4, a.ten_nql\n" + 
                    "FROM (SELECT * FROM v_disabilitypeople WHERE tinh_id = ?) a, \n" + 
                    "(SELECT DISTINCT * FROM dr_biendong_qlc WHERE tinh_id = ? \n" +
                    "AND create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) \n" +
                    "AND status = 'O_QLTH') b\n" + 
                    "WHERE a.nkt_id = b.nkt_id ORDER BY a.ten";
    
    public final String SQL_SELECT_REPORT_QLC_COLLECT_NKT="SELECT SUM(dauky) dauky, SUM(dauky_laodong) dauky_ldong, SUM(dauky_yte) dauky_yte, SUM(dauky_giaoduc) dauky_gduc,\n" + 
                    "SUM(tang) tang, SUM(tang_laodong) tang_ldong, SUM(tang_yte) tang_yte, SUM(tang_giaoduc) tang_gduc,\n" + 
                    "SUM(giam) giam, SUM(giam_laodong) giam_ldong, SUM(giam_yte) giam_yte, SUM(giam_giaoduc) giam_gduc,\n" + 
                    "SUM(cuoiky) cuoiky, SUM(cuoiky_laodong) cuoiky_ldong, SUM(cuoiky_yte) cuoiky_yte, SUM(cuoiky_giaoduc) cuoiky_gduc \n" + 
                    "FROM dr_report_collect_qlc WHERE loai_dl = ?";
    
    public final String SQL_SELECT_REPORT_QLC_COLLECT_QLC="SELECT SUM(a.qlc_dauky), SUM(a.qlc_tang), SUM(a.qlc_giam), SUM(a.qlc_cuoiky) FROM (\n" + 
                    "SELECT a.tinh_id, SUM(a.qlc_dauky) qlc_dauky, SUM(a.qlc_tang) qlc_tang, SUM(a.qlc_giam) qlc_giam, SUM(a.qlc_cuoiky) qlc_cuoiky FROM (\n" + 
                    "SELECT tinh_id,\n" + 
                    "CASE WHEN create_date < fn_firstdate_of_month(?) THEN COUNT(1) ELSE 0 END AS qlc_dauky, \n" + 
                    "CASE WHEN create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) THEN COUNT(1) ELSE 0 END AS qlc_tang, \n" + 
                    "0 qlc_giam, \n" + 
                    "CASE WHEN create_date <= fn_lastdate_of_month(?) THEN COUNT(1) ELSE 0 END AS qlc_cuoiky \n" + 
                    "FROM v_report_qlc WHERE 1=1 AND status='I' AND tinh_id IN # \n" + 
                    "GROUP BY tinh_id, create_date\n" + 
                    "UNION ALL \n" + 
                    "SELECT tinh_id, 0 qlc_dauky, 0 qlc_thang, \n" + 
                    "CASE WHEN create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?) THEN COUNT(1) ELSE 0 END AS qlc_giam, \n" + 
                    "0 qlc_cuoiky\n" + 
                    "FROM v_report_qlc WHERE 1=1 AND status='O' AND tinh_id IN # \n" + 
                    "GROUP BY tinh_id, create_date) a GROUP BY a.tinh_id) a";
    
    public final String SQL_SELECT_REPORT_QLC_COLLECT_CTKN_QLTH="SELECT SUM(a.total), SUM(a.total_thang), SUM(a.total_luyke), \n" +
                    "SUM(a.total_xahoi), SUM(a.total_xahoi_thang), SUM(a.total_xahoi_luyke), \n" +
                    "SUM(a.total_yte), SUM(a.total_yte_thang), SUM(a.total_yte_luyke), \n" +
                    "SUM(a.total_giaoduc), SUM(a.total_giaoduc_thang), SUM(a.total_giaoduc_luyke) \n" +
                    "FROM dr_report_collect_qlc_ctkn a WHERE a.stt='1'";
    
    public final String SQL_SELECT_REPORT_QLC_COLLECT_CTKN_NCQLTH="SELECT SUM(a.total), SUM(a.total_thang), SUM(a.total_luyke), \n" +
                    "SUM(a.total_xahoi), SUM(a.total_xahoi_thang), SUM(a.total_xahoi_luyke), \n" +
                    "SUM(a.total_yte), SUM(a.total_yte_thang), SUM(a.total_yte_luyke), \n" +
                    "SUM(a.total_giaoduc), SUM(a.total_giaoduc_thang), SUM(a.total_giaoduc_luyke) \n" +
                    "FROM dr_report_collect_qlc_ctkn a WHERE a.stt='2'";
     
    public final String  SQL_SELECT_INFOR_NKT_DANG_TATS="SELECT A.DANGTAT_IDS FROM dr_PHANLOAI A WHERE ID_NKT=? AND A.ID IN (SELECT MAX(ID) FROM dr_PHANLOAI WHERE ID_NKT=?)";
    
    //public final String  SQL_SELECT_INFOR_NKT_HOTRO_IDS="SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=1 AND A.ID IN (SELECT MAX(ID) FROM dr_support WHERE ID_NKT=?)";
    
    //public final String  SQL_SELECT_INFOR_NKT_HOTRO_IDS="SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=0 AND A.ID IN (SELECT MAX(ID) FROM dr_support WHERE ID_NKT=?)";
    
    public final String  SQL_SELECT_INFOR_NKT_HOTRO_IDS="SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=0 AND A.ID_NKT=? LIMIT 1";
    
    public final String  SQL_SELECT_INFOR_NKT_DADUOCHOTRO="SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=1 AND A.ID IN (SELECT MAX(ID) FROM dr_support WHERE ID_NKT=? AND dateto < ? OR dateto IS NULL)";

    public final String  SQL_SELECT_INFOR_NKT_DADUOCHOTRO_DATE_NULL = "SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=1 AND A.ID IN (SELECT MAX(ID) FROM dr_support WHERE ID_NKT=?)";
    
    public final String  SQL_SELECT_INFOR_NKT_DANGDUOCHOTRO="SELECT A.DM_HOTRO_IDS FROM dr_support A  WHERE A.STATUS_ID=1 AND A.ID IN (SELECT MAX(ID) FROM dr_support WHERE ID_NKT=? AND (dateform <=? AND dateto>?) OR (dateform IS NULL) OR (dateto IS NULL))";
    
    public final String  SQL_SELECT_INFOR_NKT_TEMP="select DANHGIA_IDS from dr_rank where id_nkt=? and id in (select max(id) from dr_rank where id_nkt=?)";

    // REPORT_TEMP

    public final String SQL_SELECT_ALL_REPORT_TEMP_BY_ID=SELECT + STAR + FROM + TABLE_REPORT_TEMP + WHERE + PPLT_ID + EQUAL + QUESTION;    
    
    public final String SQL_SELECT_ALL_REPORT_TEMP_BY_PERIOD=SELECT + STAR + FROM + TABLE_REPORT_TEMP + " LIMIT 1";    
    
    public final String SQL_SELECT_ALL_REPORT_TEMP_BY_ID_TINH=SELECT + STAR + FROM + TABLE_REPORT_TEMP + WHERE + PPLT_ID_TINH + EQUAL + QUESTION;    


    // THONG TIN TUYEN
    public final String SQL_SELECT_THONGTIN_TUYEN       = "SELECT A.*, B.NAME as TINH_NAME FROM dr_thongtin_tuyen A, dr_area B WHERE A.tinh_id = B.tinh_id ";
    
    public final String SQL_SELECT_THONGTIN_TUYEN_BY_ID = "SELECT A.*, B.NAME as TINH_NAME FROM dr_thongtin_tuyen A, dr_area B WHERE A.tinh_id = B.tinh_id AND A.id = ?";

    public final String SQL_SELECT_THONGTIN_BY_PARAM    = "SELECT A.*, B.NAME as TINH_NAME FROM dr_thongtin_tuyen A, dr_area B WHERE A.tinh_id = B.tinh_id AND A.tinh_id = ? AND A.year = ? and A.period = ?";        
   
    public final String SQL_INSERT_INTO_TABLE_DR_THONGTIN_TUYEN  =  INSERT_INTO + TABLE_DR_THONGTIN_TUYEN + FIELDS(DR_TT_TUYEN_ALL_FIELDS,true) + VALUES(DR_TT_TUYEN_ALL_FIELDS.length);
     
    public final String SQL_UPDATE_INTO_TABLE_DR_THONGTIN_TUYEN  =  UPDATE + TABLE_DR_THONGTIN_TUYEN + SET + SETS(DR_TT_TUYEN_ALL_FIELDS) + WHERE + DR_TT_TUYEN_ID + EQUAL + QUESTION;
    
    public final String SQL_CHECK_INSERT_DR_THONGTIN_TUYEN = SELECT + STAR + FROM + TABLE_DR_THONGTIN_TUYEN + WHERE + DR_TT_TUYEN_KY_BC + EQUAL + QUESTION + AND + DR_TT_TUYEN_NAM_BC + EQUAL + QUESTION + AND + DR_TT_TUYEN_TINH_ID + EQUAL + QUESTION;    
    
    
    // MUC DO KHUYET TAT
    public final String SQL_SELECT_MUCDO  =  SELECT + FIELDS("A",new String[]{MUCDO_ID ,MUCDO_NAME,MUCDO_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{MUCDO_ID ,MUCDO_NAME,MUCDO_PARENT_ID }) + 
                                                         FROM + TABLE_MUCDO + " A" + LEFT_OUTER_JOIN + TABLE_MUCDO + " B" + 
                                                         ON + "B." + MUCDO_PARENT_ID + EQUAL + "A." + MUCDO_ID +
                                                         ORDER_BY + "A." + MUCDO_PARENT_ID + COMMA +"A." + MUCDO_ID;
    
    public final String SQL_SELECT_MUCDO_BY_ID  =  SELECT  + STAR + FROM + TABLE_MUCDO + WHERE + MUCDO_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_MUCDO  =  INSERT_INTO + TABLE_MUCDO + FIELDS(MUCDO_ALL_FIELDS,true) + VALUES(MUCDO_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_MUCDO  =  UPDATE + TABLE_MUCDO + SET + SETS(MUCDO_ALL_FIELDS) + WHERE + MUCDO_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_MUCDO_INFORMATION  =  SELECT  + STAR + FROM + TABLE_MUCDO +  WHERE + MUCDO_CODE + EQUAL + QUESTION + AND + MUCDO_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_MUCDO_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_MUCDO +  WHERE + MUCDO_PARENT_ID + EQUAL + QUESTION;
    
    // NGUON HO TRO
    public final String SQL_SELECT_NGUONHOTRO  =  SELECT + FIELDS("A",new String[]{NGUONHOTRO_ID ,NGUONHOTRO_NAME, NGUONHOTRO_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{NGUONHOTRO_ID ,DANTOC_NAME,NGUONHOTRO_PARENT_ID }) + 
                                                         FROM + TABLE_NGUONHOTRO + " A" + LEFT_OUTER_JOIN + TABLE_NGUONHOTRO + " B" + 
                                                         ON + "B." + NGUONHOTRO_PARENT_ID + EQUAL + "A." + NGUONHOTRO_ID +
                                                         ORDER_BY + "A." + NGUONHOTRO_PARENT_ID + COMMA +"A." + NGUONHOTRO_ID;
    
    public final String SQL_SELECT_NGUONHOTRO_BY_ID  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO + WHERE + NGUONHOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_NGUONHOTRO  =  INSERT_INTO + TABLE_NGUONHOTRO + FIELDS(NGUONHOTRO_ALL_FIELDS,true) + VALUES(NGUONHOTRO_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_NGUONHOTRO  =  UPDATE + TABLE_NGUONHOTRO + SET + SETS(NGUONHOTRO_ALL_FIELDS) + WHERE + NGUONHOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_NGUONHOTRO_INFORMATION  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO +  WHERE + NGUONHOTRO_CODE + EQUAL + QUESTION + AND + NGUONHOTRO_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_NGUONHOTRO_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO +  WHERE + NGUONHOTRO_PARENT_ID + EQUAL + QUESTION;
    
    
    // Ly do
    public final String SQL_SELECT_LYDO  =  SELECT + FIELDS("A",new String[]{NGUONHOTRO_ID ,NGUONHOTRO_NAME, NGUONHOTRO_PARENT_ID }) + COMMA +
                                                         FIELDS("B",new String[]{NGUONHOTRO_ID ,DANTOC_NAME,NGUONHOTRO_PARENT_ID }) + 
                                                         FROM + TABLE_NGUONHOTRO + " A" + LEFT_OUTER_JOIN + TABLE_NGUONHOTRO + " B" + 
                                                         ON + "B." + NGUONHOTRO_PARENT_ID + EQUAL + "A." + NGUONHOTRO_ID +
                                                         ORDER_BY + "A." + NGUONHOTRO_PARENT_ID + COMMA +"A." + NGUONHOTRO_ID;
    
    public final String SQL_SELECT_LYDO_BY_ID  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO + WHERE + NGUONHOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_LYDO_LYDO  =  INSERT_INTO + TABLE_NGUONHOTRO + FIELDS(NGUONHOTRO_ALL_FIELDS,true) + VALUES(NGUONHOTRO_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_LYDO_LYDO  =  UPDATE + TABLE_NGUONHOTRO + SET + SETS(NGUONHOTRO_ALL_FIELDS) + WHERE + NGUONHOTRO_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_LYDO_INFORMATION  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO +  WHERE + NGUONHOTRO_CODE + EQUAL + QUESTION + AND + NGUONHOTRO_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_LYDO_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_NGUONHOTRO +  WHERE + NGUONHOTRO_PARENT_ID + EQUAL + QUESTION;

    // Object
    public final String SQL_SELECT_OBJECT  =  SELECT + FIELDS("A",new String[]{KPI_OBJECT_ID,KPI_OBJECT_CODE,KPI_OBJECT_NAME,KPI_OBJECT_PARENT_ID,KPI_OBJECT_TYPE }) + COMMA +
                                                     FIELDS("B",new String[]{KPI_OBJECT_ID, KPI_OBJECT_CODE, KPI_OBJECT_NAME,KPI_OBJECT_PARENT_ID,KPI_OBJECT_TYPE }) + 
                                                     FROM + TABLE_KPI_OBJECT + " A" + LEFT_OUTER_JOIN + TABLE_KPI_OBJECT + " B" + 
                                                     ON + "B." + KPI_OBJECT_PARENT_ID + EQUAL + "A." + KPI_OBJECT_ID +
                                                     ORDER_BY + "B." + KPI_OBJECT_CODE;
    //"A." + KPI_OBJECT_PARENT_ID + COMMA +"A." + KPI_OBJECT_ID + COMMA +
    public final String SQL_SELECT_OBJECT_LEVEL  =  SELECT + FIELDS("A",new String[]{KPI_OBJECT_ID,KPI_OBJECT_CODE,KPI_OBJECT_NAME,KPI_OBJECT_PARENT_ID }) + COMMA +
                                                     FIELDS("B",new String[]{KPI_OBJECT_ID, KPI_OBJECT_CODE, KPI_OBJECT_NAME,KPI_OBJECT_PARENT_ID }) + 
                                                     FROM + VIEW_KPI_OBJECT + " A" + LEFT_OUTER_JOIN + VIEW_KPI_OBJECT + " B" + 
                                                     ON + "B." + KPI_OBJECT_PARENT_ID + EQUAL + "A." + KPI_OBJECT_ID +
                                                     ORDER_BY + "A." + KPI_OBJECT_ID + COMMA +"A." + KPI_OBJECT_PARENT_ID;
    
    public final String SQL_SELECT_OBJECT_BY_PARAM = SELECT + STAR + FROM + TABLE_KPI_OBJECT + WHERE + TRUE;
    
    public final String SQL_SELECT_OBJECT_BY_PARENT  =  SELECT + STAR + FROM + TABLE_KPI_OBJECT + WHERE + KPI_OBJECT_PARENT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_OBJECT_BY_ID  =  SELECT  + STAR + FROM + TABLE_KPI_OBJECT + WHERE + KPI_OBJECT_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_OBJECT  =  INSERT_INTO + TABLE_KPI_OBJECT + FIELDS(KPI_OBJECT_ALL_FIELDS,true) + VALUES(KPI_OBJECT_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_OBJECT  =  UPDATE + TABLE_KPI_OBJECT + SET + SETS(KPI_OBJECT_ALL_FIELDS) + WHERE + KPI_OBJECT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_OBJECT_INFORMATION  =  SELECT  + STAR + FROM + TABLE_KPI_OBJECT +  WHERE + KPI_OBJECT_CODE + EQUAL + QUESTION + AND + KPI_OBJECT_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_OBJECT_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_KPI_OBJECT +  WHERE + KPI_OBJECT_PARENT_ID + EQUAL + QUESTION;
    
    // INDICATOR
    public final String SQL_SELECT_INDICATOR  =  SELECT + FIELDS("A",new String[]{KPI_INDICATOR_ID ,KPI_INDICATOR_CODE, KPI_INDICATOR_NAME,KPI_INDICATOR_PARENT_ID }) + COMMA +
                                                   FIELDS("B",new String[]{KPI_INDICATOR_ID, KPI_INDICATOR_CODE, KPI_INDICATOR_NAME,KPI_INDICATOR_PARENT_ID }) + 
                                                   FROM + TABLE_KPI_INDICATOR + " A" + LEFT_OUTER_JOIN + TABLE_KPI_INDICATOR + " B" + 
                                                   ON + "B." + KPI_INDICATOR_PARENT_ID + EQUAL + "A." + KPI_INDICATOR_ID +
                                                   ORDER_BY + "A." + KPI_INDICATOR_CODE; 
    // + COMMA +"A." + KPI_INDICATOR_ID;
  
    public final String SQL_SELECT_INDICATOR_BY_PARAM = SELECT + STAR + FROM + TABLE_KPI_INDICATOR + WHERE + TRUE;
    
    public final String SQL_SELECT_INDICATOR_BY_ID  =  SELECT  + STAR + FROM + TABLE_KPI_INDICATOR + WHERE + KPI_INDICATOR_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_INDICATOR_BY_PARENT_ID  =  SELECT  + STAR + FROM + TABLE_KPI_INDICATOR + WHERE + KPI_INDICATOR_PARENT_ID + EQUAL + QUESTION;
  
    public final String SQL_INSERT_INDICATOR  =  INSERT_INTO + TABLE_KPI_INDICATOR + FIELDS(KPI_INDICATOR_ALL_FIELDS,true) + VALUES(KPI_INDICATOR_ALL_FIELDS.length) + " RETURNING id";
  
    public final String SQL_UPDATE_INDICATOR  =  UPDATE + TABLE_KPI_INDICATOR + SET + SETS(KPI_INDICATOR_ALL_FIELDS) + WHERE + KPI_INDICATOR_ID + EQUAL + QUESTION;
  
    public final String SQL_SELECT_INDICATOR_INFORMATION  =  SELECT  + STAR + FROM + TABLE_KPI_INDICATOR +  WHERE + KPI_INDICATOR_CODE + EQUAL + QUESTION + AND + KPI_INDICATOR_ID + DIFF + QUESTION;
  
    public final String SQL_SELECT_INDICATOR_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_KPI_INDICATOR +  WHERE + KPI_INDICATOR_PARENT_ID + EQUAL + QUESTION;

    // DATA EVENT
    public final String SQL_SELECT_EVENT_BY_PARAM = SELECT + STAR + FROM + TABLE_KPI_EVENT + WHERE + TRUE;
    
    public final String SQL_SELECT_EVENT_BY_ID  =  SELECT  + STAR + FROM + TABLE_KPI_EVENT + WHERE + KPI_EVENT_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_EVENT  =  INSERT_INTO + TABLE_KPI_EVENT + FIELDS(KPI_EVENT_ALL_FIELDS,true) + VALUES(KPI_EVENT_ALL_FIELDS.length) + " RETURNING id";
    
    public final String SQL_UPDATE_EVENT  =  UPDATE + TABLE_KPI_EVENT + SET + SETS(KPI_EVENT_ALL_FIELDS) + WHERE + KPI_EVENT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_EVENT_INFORMATION  =  SELECT  + STAR + FROM + TABLE_KPI_EVENT +  WHERE + KPI_EVENT_CODE + EQUAL + QUESTION + AND + KPI_EVENT_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_EVENT  =  SELECT + FIELDS("A",new String[]{"id" ,"code", "name", "parent_id" }) + COMMA +
                                                   FIELDS("B",new String[]{"id" ,"code", "name", "parent_id"}) + 
                                                   FROM + "kpi_v_event" + " A" + LEFT_OUTER_JOIN + "kpi_v_event" + " B" + 
                                                   ON + "B." + "parent_id" + EQUAL + "A." + "id" +
                                                   ORDER_BY + "A." + "CODE";

    // DATA INDICATOR    
    public final String SQL_INSERT_DATA_INDCATOR_HDR = "INSERT INTO kpi_data_hdr " + FIELDS(KPI_DATA_HDR_ALL_FIELDS,true) + VALUES(KPI_DATA_HDR_ALL_FIELDS.length) + " RETURNING id";

    public final String SQL_UPDATE_DATA_INDCATOR_HDR = UPDATE + TABLE_KPI_DATA_HDR + SET + SETS(KPI_DATA_HDR_ALL_FIELDS) + WHERE + KPI_DATA_HDR_ID + EQUAL + QUESTION;
    
    public final String SQL_EXISTS_DATA_INDICATOR_HDR = "SELECT count(1) FROM kpi_data_hdr WHERE ind_id=?";
    
    public final String SQL_SELECT_DATA_INDICATOR_HDR = "SELECT hdr.id, hdr.event_id, hdr.event_type, hdr.event_field, hdr.start_date, hdr.end_date, hdr.location_id, hdr.location, hdr.activity, current_date create_date, " +
                                                          "hdr.obj_id, ind.id ind_id, ind.code, ind.name, ind.description, ind.target_year, ind.target_justification, ind.type, dtl.period, dtl. " +
                                                          "FROM kpi_data_hdr hdr RIGHT JOIN kpi_indicator ind ON hdr.ind_id=ind.id WHERE ind.id=? ";
    
    public final String SQL_SELECT_DATA_INDICATOR_CODE = "select LPAD((count(1)+1)::text, 5, '0') from kpi_data_per per, kpi_data_hdr hdr RIGHT JOIN kpi_indicator ind on hdr.ind_id=ind.id WHERE 1=1 and per.data_id = hdr.id and ind.id=?";
    
    public final String SQL_DELETE_DATA_INDCATOR_HDR = "DELETE FROM kpi_data_hdr WHERE id=?";
    
    // KPI_DATA_DTL
    public final String SQL_EXISTS_DATA_INDICATOR_DTL = "SELECT count(1) FROM kpi_data_dtl WHERE data_id=? and month=? and year=?";
    
    public final String SQL_INSERT_DATA_INDICATOR_DTL = "INSERT INTO kpi_data_dtl " + FIELDS(KPI_DATA_DTL_ALL_FIELDS,true) + VALUES(KPI_DATA_DTL_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_DATA_INDICATOR_DTL = "UPDATE kpi_data_dtl set " + SETS(KPI_DATA_DTL_ALL_FIELDS) + " WHERE id=?";

    public final String SQL_DELETE_DATA_INDICATOR_DTL = "DELETE FROM kpi_data_dtl WHERE id=?";
    
    public final String SQL_SELECT_DATA_INDICATOR_DTL_BY_HDR = "SELECT hdr.obj_id, hdr.ind_id, dtl.*, users.username, users.fullname FROM kpi_data_hdr hdr, kpi_data_dtl dtl, users \n" + 
                                                                "WHERE hdr.id=dtl.data_id AND dtl.user_id=users.user_id and dtl.data_id=?\n";
    
    public final String SQL_SELECT_DATA_INDICATOR_DTL_BY_ID = "SELECT hdr.obj_id, hdr.ind_id, dtl.*, users.username, users.fullname FROM kpi_data_hdr hdr, kpi_data_dtl dtl, users \n" + 
                                                              "WHERE hdr.id=dtl.data_id AND dtl.user_id=users.user_id and dtl.id=? \n" +
                                                              "AND hdr.obj_id=? AND hdr.ind_id=?";

    // KPI_DATA_DTL
    public final String SQL_INSERT_DATA_INDICATOR_NKT = "INSERT INTO kpi_data_nkt " + FIELDS(KPI_DATA_NKT_ALL_FIELDS, true) + VALUES(KPI_DATA_NKT_ALL_FIELDS.length);
    
    public final String SQL_SELECT_DATA_INDICATOR_NKT_BY_HDR = "SELECT dis.id, dis.date_last_update, a.name location_name, dis.ma, dis.ten, dis.sex, dis.ngaysinh, dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs \n" + 
                                                                "FROM dr_disabilitypeople dis LEFT JOIN kpi_data_nkt data ON dis.id=data.nkt_id\n" + 
                                                                "LEFT JOIN dr_area a ON dis.id_tinh=a.tinh_id\n" + 
                                                                "WHERE 1=1 ";
    
    public final String SQL_SELECT_DATA_INDICATOR_NKT_BY_ID = "select dis.id, dis.date_last_update, dis.ma, dis.ten, dis.sex, dis.ngaysinh, dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs \n" + 
                                                              "from dr_disabilitypeople dis, kpi_data_nkt data WHERE dis.id=data.nkt_id and nkt_id=?";
        
    public final String SQL_DELETE_DATA_INDICATOR_NKT = "DELETE FROM kpi_data_nkt WHERE nkt_id=?";

    // KPI_PERSON
    public final String SQL_EXISTS_DATA_INDICATOR_PER = "SELECT count(1) FROM kpi_data_per WHERE data_id=? ";
    
    public final String SQL_INSERT_KPI_PERSON = "INSERT INTO kpi_person " + FIELDS(KPI_DATA_PER_ALL_FIELDS,true) + VALUES(KPI_DATA_PER_ALL_FIELDS.length) + " RETURNING id";
    
    public final String SQL_UPDATE_KPI_PERSON = "UPDATE kpi_person set " + SETS(KPI_DATA_PER_ALL_FIELDS) + " WHERE id=?";
    
    public final String SQL_UPDATE_KPI_PERSON_UPDATE = "UPDATE kpi_person set " + SETS(KPI_DATA_PER_UPDATE_FIELDS) + " WHERE id=?";

    public final String SQL_DELETE_DATA_INDICATOR_PER = "DELETE FROM kpi_person WHERE id=?";
    
    public final String SQL_SELECT_PERSON_BY_HDR = "SELECT p.id, p.modify_date, p.user_id, p.location_id, p.address, p.contact, p.code, p.name, p.sex, p.agency, \n" +
                                                    "p.title, m.create_date, m.event_id, m.result, m.hours, u.username, u.fullname, a.name location_name, '' checked FROM kpi_person p \n" + 
                                                    "INNER JOIN kpi_data_per m ON p.id=m.per_id \n" + 
                                                    "LEFT JOIN users u ON p.user_id=u.user_id \n" +
                                                    "LEFT JOIN dr_area a ON p.location_id=a.tinh_id \n" +
                                                    "WHERE  1=1 and m.data_id=?";
    
    public final String SQL_SELECT_PERSON_BY_PARAM = "SELECT p.*, a.name  as location_name FROM kpi_person p LEFT JOIN dr_area a ON p.location_id=a.tinh_id WHERE 1=1 ORDER BY location_id";
    
    public final String SQL_SELECT_PERSON = "SELECT DISTINCT on (p.id) id, p.create_date, p.modify_date,\n" +
                                            "p.user_id, p.location_id, p.address, p.contact, \n" +
                                            "p.code, p.name, p.sex, p.agency, p.title,\n" +
                                            "m.event_id, m.result, m.hours, u.fullname, a.name location_name, \n" +
                                            "case when (m.per_id is null) then '' else 'checked' end checked\n" +
                                            "FROM kpi_person p \n" +
                                            "LEFT JOIN kpi_data_per m ON p.id=m.per_id \n" +
                                            "LEFT JOIN users u ON p.user_id=u.user_id \n" +
                                            "LEFT JOIN dr_area a ON p.location_id=a.tinh_id \n" +
                                            "WHERE  1=1";
    
    public final String SQL_SELECT_PERSON_AC = "SELECT DISTINCT on (p.id) id, p.create_date, p.modify_date,\n" +
                                          "p.user_id, p.location_id, p.address, p.contact, \n" +
                                          "p.code, p.name, p.sex, p.agency, p.title,\n" +
                                          "m.event_id, m.result, m.hours, u.fullname, a.name location_name, \n" +
                                          "case when (m.per_id is null) then '' else 'checked' end checked\n" +
                                          "FROM kpi_person p \n" +
                                          "INNER JOIN kpi_data_per m ON p.id=m.per_id \n" +
                                          "LEFT JOIN users u ON p.user_id=u.user_id \n" +
                                          "LEFT JOIN dr_area a ON p.location_id=a.tinh_id \n" +
                                          "WHERE  1=1 AND p.id=?" ;
  
    public final String SQL_SELECT_DATA_INDICATOR_PER_BY_ID = "SELECT p.*, m.event_id, m.result, m.hours, u.fullname, a.name location_name,'' checked  FROM kpi_person p \n" + 
                                                                "LEFT JOIN kpi_data_per m ON p.id=m.per_id\n" + 
                                                                "LEFT JOIN users u ON p.user_id=u.user_id \n" + 
                                                                "LEFT JOIN dr_area a ON p.location_id=a.tinh_id \n" +
                                                                "WHERE  1=1 and p.id=? ";
    
    // KPI_OBJ_IND
    public final String SQL_INSERT_OBJ_IND = "INSERT INTO kpi_obj_ind " + FIELDS(KPI_OBJ_IND_ALL_FIELDS,true) + VALUES(KPI_OBJ_IND_ALL_FIELDS.length);    
    
    // KPI_DATA_NKT
    public final String SQL_INSERT_DATA_NKT = "INSERT INTO kpi_data_nkt " + FIELDS(KPI_DATA_NKT_ALL_FIELDS,true) + VALUES(KPI_DATA_NKT_ALL_FIELDS.length);    
    
    // KPI_DATA_PERSON
    public final String SQL_INSERT_DATA_PERSON = "INSERT INTO kpi_data_per " + FIELDS(KPI_DATA_PER_RESULT_ALL_FIELDS,true) + VALUES(KPI_DATA_PER_RESULT_ALL_FIELDS.length);
    
    public final String SQL_CHECK_DATA_PERSON = "SELECT count(1) FROM kpi_data_per WHERE data_id=? AND per_id=? AND event_id=?";
    
    public final String SQL_UPDATE_DATA_PERSON = "UPDATE kpi_data_per set " + SETS(KPI_DATA_PER_RESULT_ALL_FIELDS_UPDATE) + " WHERE data_id=?  AND per_id=? AND event_id=?";
    
    // KPI_EVENT_IND
    public final String SQL_INSERT_EVENT_IND = "INSERT INTO kpi_event_ind " + FIELDS(KPI_EVENT_IND_ALL_FIELDS,true) + VALUES(KPI_EVENT_IND_ALL_FIELDS.length);
    
    // KPI_EVENT_OBJ_IND
    public final String SQL_INSERT_EVENT_OBJ_IND = "INSERT INTO kpi_event_obj_ind " + FIELDS(KPI_EVENT_OBJ_IND_ALL_FIELDS,true) + VALUES(KPI_EVENT_OBJ_IND_ALL_FIELDS.length);
    
    // KPI_INDICATOR_VALUE
    public final String SQL_INSERT_INDICATOR_VALUE = "INSERT INTO kpi_ind_val " + FIELDS(KPI_EVENT_INDICATOR_VALUE_ALL_FIELDS,true) + VALUES(KPI_EVENT_INDICATOR_VALUE_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_INDICATOR_VALUE = "UPDATE kpi_ind_val set " + SETS(KPI_EVENT_INDICATOR_VALUE_ALL_FIELDS) + " WHERE id=?";
    
    // KPI_DATA_RANK
    public final String SQL_INSERT_DATA_RANK = "INSERT INTO kpi_data_rank " + FIELDS(KPI_DATA_RANK_ALL_FIELDS,true) + VALUES(KPI_DATA_RANK_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_DATA_RANK = "UPDATE kpi_data_rank set " + SETS(KPI_DATA_RANK_ALL_FIELDS) + " WHERE id=?";
    
    public final String SQL_CHECK_EXISTS_INDICATOR = "SELECT count(1) FROM kpi_ind_val WHERE location_id=? AND ind_id=? AND quarter=0 AND year=? AND type=?";
    
    public final String SQL_SELECT_INDICATOR_VALUE = "SELECT a.*, b.name FROM kpi_ind_val a, dr_area b WHERE a.location_id=b.tinh_id ";
        
    // Rank
    public final String SQL_SELECT_RANK  =  SELECT + FIELDS("A",new String[]{KPI_RANK_ID,KPI_RANK_CODE,KPI_RANK_NAME,KPI_RANK_PARENT_ID}) + COMMA +
                                                     FIELDS("B",new String[]{KPI_RANK_ID, KPI_RANK_CODE, KPI_RANK_NAME, KPI_RANK_PARENT_ID}) + 
                                                     FROM + TABLE_KPI_RANK + " A" + LEFT_OUTER_JOIN + TABLE_KPI_RANK + " B" + 
                                                     ON + "B." + KPI_RANK_PARENT_ID + EQUAL + "A." + KPI_RANK_ID +
                                                     ORDER_BY + "B." + KPI_RANK_CODE;   
    
    public final String SQL_SELECT_RANK_BY_PARAM = SELECT + STAR + FROM + TABLE_KPI_RANK + WHERE + TRUE;
    
    public final String SQL_SELECT_RANK_BY_PARENT  =  SELECT + STAR + FROM + TABLE_KPI_RANK + WHERE + KPI_RANK_PARENT_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_RANK_BY_ID  =  SELECT  + STAR + FROM + TABLE_KPI_RANK + WHERE + KPI_RANK_ID + EQUAL + QUESTION;
    
    public final String SQL_INSERT_KPI_RANK  =  INSERT_INTO + TABLE_KPI_RANK + FIELDS(KPI_RANK_ALL_FIELDS,true) + VALUES(KPI_RANK_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_KPI_RANK  =  UPDATE + TABLE_KPI_RANK + SET + SETS(KPI_RANK_ALL_FIELDS) + WHERE + KPI_RANK_ID + EQUAL + QUESTION;
    
    public final String SQL_SELECT_RANK_INFORMATION  =  SELECT  + STAR + FROM + TABLE_KPI_RANK +  WHERE + KPI_RANK_CODE + EQUAL + QUESTION + AND + KPI_RANK_ID + DIFF + QUESTION;
    
    public final String SQL_SELECT_RANK_HAVECHILD  =  SELECT  + STAR + FROM + TABLE_KPI_RANK +  WHERE + KPI_RANK_PARENT_ID + EQUAL + QUESTION;
    
    
    // DATA DIS PROFILE   
    public final String SQL_INSERT_KPI_DIS_PROFILE = INSERT_INTO + TABLE_KPI_DIS_PROFILE + FIELDS(KPI_DIS_PROFILE_ALL_FIELDS,true) + VALUES(KPI_DIS_PROFILE_ALL_FIELDS.length);
  
    public final String SQL_UPDATE_KPI_DIS_PROFILE = UPDATE + TABLE_KPI_DIS_PROFILE + SET + SETS(KPI_DIS_PROFILE_ALL_FIELDS) + WHERE + KPI_DIS_PROFILE_ID + EQUAL + QUESTION;
    
    // DATA DIS REPORT    
    public final String SQL_INSERT_KPI_DIS_REPORT = INSERT_INTO + TABLE_KPI_DIS_REPORT + FIELDS(KPI_DIS_REPORT_ALL_FIELDS,true) + VALUES(KPI_DIS_REPORT_ALL_FIELDS.length);
    
    public final String SQL_UPDATE_KPI_DIS_REPORT = UPDATE + TABLE_KPI_DIS_REPORT + SET + SETS(KPI_DIS_REPORT_ALL_FIELDS) + WHERE + KPI_DIS_REPORT_ID + EQUAL + QUESTION;
    
    // DATA DIS EXPORT
    /*
    public static String SQL_SELECT_KPI_DIS_EXPORT = "SELECT stt, tinh_name, maso, ten, year_of_birthday, sex, \n" + 
                                                    "       sonha, dienthoai, create_date, ten_ncs, sdt_ncs, gioitinh_ncs, trangthai, ngay_dong_hs, \n" + 
                                                    "       dang_tat, muc_do, ngay_phat_hien_ktat, da_cam, nc_id, nhu_cau, nhucau_noi_nhan, nhucau_can_thiep_cn, nhucau_ten_dung_cu, nhucau_cai_thien_ctvs,  \n" + 
                                                    "       ngay_nhu_cau, ht_id, hotro_da_nhan, hotro_noi_nhan, hotro_can_thiep_cn, hotro_ten_dung_cu, \n" + 
                                                    "       hotro_cai_thien_ctvs, ngay_ho_tro\n" + 
                                                    "  FROM kpi_report_temp ORDER BY stt ASC, maso ASC, order_by ASC\n";
    */
    
    public String SQL_SELECT_KPI_DIS_EXPORT = "SELECT a.* FROM \n" + 
                                                      "(SELECT a.*, b.du_an, b.id_tinh as id_tinh, b.id_district as id_district,  b.id_commune as id_commune \n" +
                                                      "FROM kpi_report_temp a LEFT JOIN dr_disabilitypeople b ON a.id = b.id \n" + 
                                                      "WHERE 1=1 [$TU_DVU$] [$DEN_DVU$] \n" +                                                      
                                                      "ORDER BY a.stt ASC, a.maso ASC, a.order_by ASC) a \n" + 
                                                      "LEFT JOIN kpi_report_temp_2020 b ON a.id=b.id WHERE 1=1 \n" +
                                                       " [$TU_NGAY$] [$DEN_NGAY$] \n" +                                                        
                                                       " [$TU_TDG$] [$DEN_TDG$] \n" +
                                                       " [$TU_DMC$] [$DEN_DMC$] \n";
  
    
   /*  public String SQL_SELECT_KPI_DIS_EXPORT_2020 = "SELECT a.* FROM (\n" + 
                                                      "SELECT * FROM kpi_report_temp_2020 a WHERE \n" + 
                                                      "EXISTS (SELECT * FROM kpi_report_temp_2020 b \n" + 
                                                      "WHERE 1=1 [$TU_NGAY$] [$DEN_NGAY$] " +                                                       
                                                      "AND a.id=b.id) \n" + 
                                                      "ORDER BY a.stt ASC, a.maso ASC, a.order_by ASC) a INNER JOIN dr_disabilitypeople b ON a.id=b.id \n" + 
                                                      "WHERE 1=1 "; */
    
   public String SQL_SELECT_KPI_DIS_EXPORT_2020 = "SELECT a.*, b.du_an FROM kpi_report_temp_2020 a INNER JOIN dr_disabilitypeople b ON a.id = b.id " +
                                                  " [$TU_NGAY$] [$DEN_NGAY$] " +
                                                  " [$TU_DVU$] [$DEN_DVU$] " +
                                                  " [$TU_TDG$] [$DEN_TDG$] " +
                                                  " [$TU_DMC$] [$DEN_DMC$] ";
    
    // DATA DIS COMMUNE
    public static String SQL_SELECT_KPI_DIS_COMMUNE_SUMMARY = "SELECT total_visit, total_visit_male, total_visit_female, total_a, total_b, \n" + 
                                                    "       total_c, total_d, total_dis_visit, total_dis_male, total_dis_female, \n" + 
                                                    "       total_dis_lv1, total_dis_lv2, total_dis_lv3, total_dis_lv4, stt\n" + 
                                                    "  FROM kpi_report_commune";
    
    public static String SQL_SELECT_KPI_DIS_COMMUNE_DETAIL = "SELECT rpt.create_date, dis.ten, dis.maso, CASE WHEN dis.sex=1 THEN '1' ELSE '0' END gioitinh, pl.mucdo_id, dis.sonha, \n" + 
                                                    "rpt.ktbt_thuongxuyen::text as P1, rpt.ktbt_tapdung::text as P2, rpt.dctg_phuhop::text as P3, rpt.dctg_thuongxuyen::text as P4, \n" + 
                                                    "rpt.dctg_baoquan::text as P5, rpt.hd_ncs::text as P6, rpt.huong_ct::text as P7, rpt.htro_dkien as P8\n" + 
                                                    "FROM dr_disabilitypeople dis, kpi_dis_report rpt, dr_v_phanloai pl \n" + 
                                                    "WHERE dis.id=rpt.nkt_id AND dis.id=pl.id_nkt AND rpt.create_date BETWEEN fn_firstdate_of_month(?) AND fn_lastdate_of_month(?)";
    
    public final String SQL_INSERT_KPI_JOB_LOG  =  INSERT_INTO + TABLE_KPI_JOB_LOG + FIELDS(KPI_JOB_LOG_ALL_FIELDS,true) + VALUES(KPI_JOB_LOG_ALL_FIELDS.length) + " RETURNING id";
    
    public final String SQL_SELECT_HOME_VISIT_BY_PARAM = SELECT + "a.id, a.support_id, a.id_nkt, to_char(a.start_at, 'dd/MM/YYYY HH:mm:ss') as start_at,\n" + 
          "to_char(a.end_at, 'dd/MM/YYYY HH:mm:ss') as end_at, a.create_by, a.latitude , a.longitude, a.location  " + FROM + TABLE_DR_HOME_VISIT + AS + " a " + WHERE + TRUE ;
}
  