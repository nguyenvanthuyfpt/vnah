package com.dao.disability.report;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FPopulation;
import com.form.disability.report.FReportTotal;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DReportTotal  extends DSqlDisability{

    public FReportTotal getById(Connection cnn,int id) throws EException
    {
        final String LOCATION = this.toString() + "getAll()";
        FReportTotal bean = new FReportTotal();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn,SQL_SELECT_ALL_REPORT_TEMP_BY_PERIOD,params);
            rs = prstm.executeQuery();
            if(rs != null && rs.next()){
                bean=new FReportTotal();
                for(int i=0;i<bean.getParamValue().length;i++){
                    bean.setParamValue(rs.getString(i+1),i);       
                }
            }
        }
        catch(SQLException sqle)
        {
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;        
    }
    
    public boolean insert(Connection cnn, FSeed seed,FPopulation beanPo) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
        PreparedStatement prstm1 = null;
        String SQL="INSERT INTO dr_temp_report(\n" + 
        "           id,commune, district, province, period, yearoperiod, nomaleunder18, nomaleabove18, nomaletotal, \n" + 
        "           nofemaleunder18, nofemaleabove18, nofemaletotal, nodansototal,  \n" + 
        "           kinhte_giau, kinhte_kha, kinhte_trungbinh, kinhte_canngheo, kinhte_ngheo, kinhte_quangheo,  \n" + 
        "           giadinh_nuocmay, giadinh_nkt_2,  \n" + 
        "           no_disable_total, no_male_disable0, no_male_disable3, no_male_disable6, no_male_disable18, no_male_disable60,  \n" + 
        "           no_female_disable0, no_female_disable3, no_female_disable6, no_female_disable18, no_female_disable60,  \n" + 
        "           no_dacam_total, no_dacam0, no_dacam3, no_dacam6, no_dacam18, no_dacam60,  \n" + 
        "           no_new_total, no_new0, no_new3, no_new6, no_new18, no_new60,  \n" + 
        "           no_plan_total, no_plan0, no_plan3, no_plan6, no_plan18, no_plan60, \n" + 
        "           no_muctieu_plan_total, no_muctieu_plan0, no_muctieu_plan3, no_muctieu_plan6, no_muctieu_plan18, no_muctieu_plan60, \n" + 
        "           no_drop_out_total, no_drop_out0, no_drop_out3, no_drop_out6, no_drop_out18, no_drop_out60, \n" + 
        "           no_bocuoc_total, no_bocuoc0, no_bocuoc3, no_bocuoc6, no_bocuoc8, no_bocuoc60, \n" + 
        "           no_theodoi_total, no_theodoi0, no_theodoi3, no_theodoi6, no_theodoi8, no_theodoi60, \n" + 
        "           no_target_total, no_target0, no_target3, no_target6, no_target18, no_target60, \n" + 
        "           no_develop_total, no_develop0, no_develop3, no_develop6, no_develop18, no_develop60, \n" + 
        "           no_develop_yte_total, no_develop_yte0, no_develop_yte3, no_develop_yte6, no_develop_yte18, no_develop_yte60, \n" + 
        "           no_develop_xahoi_total, no_develop_xahoi0, no_develop_xahoit3, no_develop_xahoi6, no_develop_xahoi18, no_develop_xahoi60, \n" + 
        "           no_develop_giaoduc_total, no_develop_giaoduc0, no_develop_giaoduc3, no_develop_giaoduc6, no_develop_giaoduc18, no_develop_giaoduc60, \n" + 
        "           no_bainao0, no_bainao18, no_bainao_truoc, no_bainao_nay, no_bainao_luyke, no_banchan_khoe0, no_banchan_khoe18, \n" + 
        "           no_banchan_khoe_truoc, no_banchan_khoe_nay, no_banchan_khoe_luyke, \n" + 
        "           no_loanduongco0, no_loanduongco18, no_loanduongco_truoc, no_loanduongco_nay, no_loanduongco_luyke, \n" + 
        "           no_veocotsong0, no_veocotsong18, no_veocotsong_truoc, no_veocotsong_nay, no_veocotsong_luyke, \n" + 
        "           no_tratkhophang0, no_tratkhophang18, no_tratkhophang_truoc, no_tratkhophang_nay, no_tratkhophang_luyke, \n" + 
        "           no_tuysong0, no_tuysong18, no_tuysong_truoc, no_tuysong_nay, no_tuysong_luyke, \n" + 
        "           no_thoaihoakhop0, no_thoaihoakhop18, no_thoaihoakhop_truoc, no_thoaihoakhop_nay, no_thoaihoakhop_luyke, \n" + 
        "           no_lietnuanguoi0, no_lietnuanguoi18, no_lietnuanguoi_truoc, no_lietnuanguoi_nay, no_lietnuanguoi_luyke, \n" + 
        "           no_cutchi0, no_cutchi18, no_cutchi_truoc, no_cutchi_nay, no_cutchi_luyke, \n" + 
        "           no_liettuchi0, no_liettuchi18, no_liettuchi_truoc, no_liettuchi_nay, no_liettuchi_luyke, \n" + 
        "           no_vandongkhac0, no_vandongkhac18, no_vandongkhac_truoc, no_vandongkhac_nay, no_vandongkhac_luyke, no_vandong_total, \n" + 
        "           no_kk_nhin0, no_kk_nhin18, no_kk_nhin_truoc, no_kk_nhin_nay, no_kk_nhin_luyke, \n" + 
        "           no_kk_noi0, no_kk_noi18, no_kk_noi_truoc, no_kk_noi_nay, no_kk_noi_luyke, no_nghenoi_total, \n" + 
        "           no_mumau0, no_mumau18, no_mumau_truoc, no_mumau_nay, no_mumau_luyke,   \n" + 
        "           no_quangga0, no_quangga18, no_quangga_truoc, no_quangga_nay, no_quangga_luyke, \n" + 
        "           no_canthi0, no_canthi18, no_canthi_truoc, no_canthi_nay, no_canthi_luyke, \n" + 
        "           no_mu2mat0, no_mu2mat18, no_mu2mat_truoc, no_mu2mat_nay, no_mu2mat_luyke, \n" + 
        "           no_mu1mat0, no_mu1mat18, no_mu1mat_truoc, no_mu1mat_nay, no_mu1mat_luyke, \n" + 
        "           no_matthitruong0, no_matthitruong18, no_matthitruong_truoc, no_matthitruong_nay, no_matthitruong_luyke, \n" + 
        "           no_vienthi0, no_vienthi18, no_vienthi_truoc, no_vienthi_nay, no_vienthi_luyke, \n" + 
        "           no_lacmat0, no_lacmat18, no_lacmat_truoc, no_lacmat_nay, no_lacmat_luyke, \n" + 
        "           no_kknhinkhac0, no_kknhinkhac18, no_kknhinkhac_truoc, no_kknhinkhac_nay, no_kknhinkhac_luyke, no_kk_nhin_total, \n" + 
        "           no_champt_tritue0, no_champt_tritue18, no_champt_tritue_truoc, no_champt_tritue_nay, no_champt_tritue_luyke,   \n" + 
        "           no_down0, no_down18, no_down_truoc, no_down_nay, no_down_luyke, no_kk_tritue_total, \n" + 
        "           no_tuky0, no_tuky18, no_tuky_truoc, no_tuky_nay, no_tuky_luyke, \n" + 
        "           no_tamthan0, no_tamthan18, no_tamthan_truoc, no_tamthan_nay, no_tamthan_luyke, no_kk_tamthan_total, \n" + 
        "           no_dongkinh0, no_dongkinh18, no_dongkinh_truoc, no_dongkinh_nay, no_dongkinh_luyke, \n" + 
        "           no_timbamsinh0, no_timbamsinh18, no_timbamsinh_truoc, no_timbamsinh_nay, no_timbamsinh_luyke, \n" + 
        "           no_dichungbong0, no_dichungbong18, no_dichungbong_truoc, no_dichungbong_nay, no_dichungbong_luyke, \n" + 
        "           no_hohap0, no_hohap18, no_hohap_truoc, no_hohap_nay, no_hohap_luyke, \n" + 
        "           no_tatkhac0, no_tatkhac18, no_tatkhac_truoc, no_tatkhac_nay, no_tatkhac_luyke, no_kk_khac_total, \n" + 
        "           no_hotro_dungcu_0, no_hotro_dungcu_18, no_hotro_dungcu_truoc, no_hotro_dungcu_nay, no_hotro_dungcu_luyke, no_hotro_dungcu_tong, \n" + 
        "           no_hotro_duoc_dungcu_0, no_hotro_duoc_dungcu_18, no_hotro_duoc_dungcu_truoc, no_hotro_duoc_dungcu_nay, no_hotro_duoc_dungcu_luyke, no_hotro_duoc_dungcu_tong, \n" + 
        "           no_hotro_thanhss_0, no_hotro_thanhss_18, no_hotro_thanhss_truoc, no_hotro_thanhss_nay, no_hotro_thanhss_luyke, \n" + 
        "           no_hotro_khungtd_0, no_hotro_khungtd_18, no_hotro_khungtd_truoc, no_hotro_khungtd_nay, no_hotro_khungtd_luyke, \n" + 
        "           no_hotro_nang_0, no_hotro_nang_18, no_hotro_nang_truoc, no_hotro_nang_nay, no_hotro_nang_luyke, \n" + 
        "           no_hotro_xelan_0, no_hotro_xelan_18, no_hotro_xelan_truoc, no_hotro_xelan_nay, no_hotro_xelan_luyke, \n" + 
        "           no_hotro_ghebn_0, no_hotro_ghebn_18, no_hotro_ghebn_truoc, no_hotro_ghebn_nay, no_hotro_ghebn_luyke, \n" + 
        "           no_hotro_bandung_0, no_hotro_bandung_18, no_hotro_bandung_truoc, no_hotro_bandung_nay, no_hotro_bandung_luyke, \n" + 
        "           no_hotro_nep_0, no_hotro_nep_18, no_hotro_nep_truoc, no_hotro_nep_nay, no_hotro_nep_luyke, \n" + 
        "           no_hotro_maytt_0, no_hotro_maytt_18, no_hotro_maytt_truoc, no_hotro_maytt_nay, no_hotro_maytt_luyke, \n" + 
        "           no_hotro_changia_0, no_hotro_changia_18, no_hotro_changia_truoc, no_hotro_changia_nay, no_hotro_changia_luyke, \n" + 
        "           no_hotro_taygia_0, no_hotro_taygia_18, no_hotro_taygia_truoc, no_hotro_taygia_nay, no_hotro_taygia_luyke, \n" + 
        "           no_hotro_matgia_0, no_hotro_matgia_18, no_hotro_matgia_truoc, no_hotro_matgia_nay, no_hotro_matgia_luyke, \n" + 
        "           no_hotro_xelac_0, no_hotro_xelac_18, no_hotro_xelac_truoc, no_hotro_xelac_nay, no_hotro_xelac_luyke, \n" + 
        "           no_hotro_dckhac_0, no_hotro_dckhac_18, no_hotro_dckhac_truoc, no_hotro_dckhac_nay, no_hotro_dckhac_luyke, \n" + 
        "           no_hotro_tainha_0, no_hotro_tainha_18, no_hotro_tainha_truoc, no_hotro_tainha_nay, no_hotro_tainha_luyke, no_hotro_tainha_tong, \n" + 
        "           no_hotro_dctainha_0, no_hotro_dctainha_18, no_hotro_dctainha_truoc, no_hotro_dctainha_nay, no_hotro_dctainha_luyke, no_hotro_dctainha_tong, \n" + 
        "           no_hotro_nhacua_0, no_hotro_nhacua_18, no_hotro_nhacua_truoc, no_hotro_nhacua_nay, no_hotro_nhacua_luyke, no_hotro_nhacua_tong, \n" + 
        "           no_hotro_dcnhacua_0, no_hotro_dcnhacua_18, no_hotro_dcnhacua_truoc, no_hotro_dcnhacua_nay, no_hotro_dcnhacua_luyke, no_hotro_dcnhacua_tong, \n" + 
        "           no_hotro_phauthuat_0, no_hotro_phauthuat_18, no_hotro_phauthuat_truoc, no_hotro_phauthuat_nay, no_hotro_phauthuat_luyke, no_hotro_phauthuat_tong, \n" + 
        "           no_hotro_dcphauthuat_0, no_hotro_dcphauthuat_18, no_hotro_dcphauthuat_truoc, no_hotro_dcphauthuat_nay, no_hotro_dcphauthuat_luyke, no_hotro_dcphauthuat_tong, \n" + 
        "           no_tiepcan_giaoduc, no_hotro_vonlaman, no_hotro_duoc_vonlaman, no_tukiemsong, no_coquanhevochong, no_tochucxh, no_nhomtuluc     \n" +                   
        "            )" + 
        "    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" +                    
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" + 
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, \n" +         
        "            ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      try
      {
          DReportTotal_params dao=new DReportTotal_params();
          List params = dao.setParams(cnn,seed,beanPo);
          prstm1 = prepareStatement(cnn,"DELETE FROM dr_temp_report",null);
          prstm1.executeUpdate();
          prstm = prepareStatement(cnn,SQL,params);
          result = prstm.executeUpdate()>0;

      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm1);
        closePreparedStatement(prstm);
      }
      return result;    
    }

}
