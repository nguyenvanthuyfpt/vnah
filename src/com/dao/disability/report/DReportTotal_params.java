package com.dao.disability.report;


import com.dao.disability.DSqlDisability;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FPopulation;
import com.form.disability.report.FReportTotal;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;


public class DReportTotal_params  extends DSqlDisability{

 
    public List setParams(Connection cnn,FSeed seed,FPopulation beanPo) throws EException
    {
        final String LOCATION = "->setParams()";
        FReportTotal bean = (FReportTotal)seed;
        List params = new ArrayList();
        
        DTinh daoTinh=new DTinh();
        String tuNgay="";
        String denNgay="";
        String tuNgayTruoc="";
        String denNgayTruoc="";
        int year=0;
        if(bean.getPeriod()==1){
            year = bean.getYearPeriod()-1;
            tuNgay="'01/01/"+bean.getYearPeriod()+"'";
            denNgay="'01/07/"+bean.getYearPeriod()+"'";
            tuNgayTruoc="'01/07/"+ String.valueOf(year) +"'";
            denNgayTruoc="'01/01/"+bean.getYearPeriod()+"'";
        }else{
            year = bean.getYearPeriod()+1;
            tuNgayTruoc="'01/01/"+bean.getYearPeriod()+"'";
            denNgayTruoc="'01/07/"+bean.getYearPeriod()+"'";
            tuNgay="'01/07/"+bean.getYearPeriod()+"'";
            denNgay="'01/01/"+String.valueOf(year)+"'";
        }
        
        String member = daoTinh.getMembers(cnn,bean.getTinhId(),"");        
        String SQL_TONG_KINHTE_GIAU="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        //String SQL_TONG_KINHTE_KHA="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=2 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_KINHTE_TRUNGBINH="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=3 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_KINHTE_CANNGHEO="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=12 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_KINHTE_NGHEO="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=5 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_KINHTE_DACBIETNGHEO="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.id_dieukien=10 and dr_disabilitypeople.id_tinh in ("+member+")";
        
        String SQL_TONG_GIADINH_NKT_NUOCMAY="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.nguonnuoc_chuho=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_GIADINH_NKT_2="SELECT count(*) AS tong FROM dr_disabilitypeople where dr_disabilitypeople.nkt_chuho>=2 and dr_disabilitypeople.id_tinh in ("+member+")";
        
        String SQL_TONG_NAM_0="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.sex=0 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NAM_3="SELECT count(*) AS tong FROM dr_disabilitypeople where  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.sex=0 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NAM_6="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.sex=0 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NAM_18="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.sex=0 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NAM_60="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.sex=0 and dr_disabilitypeople.id_tinh in ("+member+")";

        String SQL_TONG_NU_0="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.sex=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NU_3="SELECT count(*) AS tong FROM dr_disabilitypeople where  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.sex=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NU_6="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.sex=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NU_18="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.sex=1 and dr_disabilitypeople.id_tinh in ("+member+")";
        String SQL_TONG_NU_60="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.sex=1 and dr_disabilitypeople.id_tinh in ("+member+")";

        String SQL_TONG_NKT_DACAM_0="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.dacam=1";
        String SQL_TONG_NKT_DACAM_3="SELECT count(*) AS tong FROM dr_disabilitypeople where  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.dacam=1";
        String SQL_TONG_NKT_DACAM_6="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.dacam=1";
        String SQL_TONG_NKT_DACAM_18="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.dacam=1";
        String SQL_TONG_NKT_DACAM_60="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.dacam=1";

        String SQL_TONG_NKT_MOI_0="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.ngaysinh<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_MOI_3="SELECT count(*) AS tong FROM dr_disabilitypeople where  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_MOI_6="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_MOI_18="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_MOI_60="SELECT count(*) AS tong FROM dr_disabilitypeople where EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_KEHOACH_HT_0="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#141#%'";
        String SQL_TONG_NKT_KEHOACH_HT_3="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#141#%'";
        String SQL_TONG_NKT_KEHOACH_HT_6="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#141#%'";
        String SQL_TONG_NKT_KEHOACH_HT_18="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#141#%'";
        String SQL_TONG_NKT_KEHOACH_HT_60="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#141#%'";

        String SQL_TONG_NKT_MUCTEU_KEHOACH_HT_0="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#135#%'";
        String SQL_TONG_NKT_MUCTEU_KEHOACH_HT_3="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#135#%'";
        String SQL_TONG_NKT_MUCTEU_KEHOACH_HT_6="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#135#%'";
        String SQL_TONG_NKT_MUCTEU_KEHOACH_HT_18="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#135#%'";
        String SQL_TONG_NKT_MUCTEU_KEHOACH_HT_60="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#135#%'";

        String SQL_TONG_NKT_OUT_0="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#119#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_OUT_3="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#119#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_OUT_6="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#119#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_OUT_18="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#119#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_OUT_60="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#119#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_DATDUOC_MUCTIEU_0="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#117#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_DATDUOC_MUCTIEU_3="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#117#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_DATDUOC_MUCTIEU_6="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#117#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_DATDUOC_MUCTIEU_18="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#117#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_DATDUOC_MUCTIEU_60="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#117#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_BOCUOC_0="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#127#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_BOCUOC_3="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and  EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#127#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_BOCUOC_6="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#127#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_BOCUOC_18="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#127#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_BOCUOC_60="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#127#%' and dr_rank.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_rank.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        /*String SQL_TONG_NKT_TIENBO_VANDONG_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=1 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_VANDONG_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=1 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_VANDONG_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=1 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_VANDONG_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=1 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_VANDONG_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=1 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_SINHHOAT_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_SINHHOAT_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_SINHHOAT_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_SINHHOAT_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_SINHHOAT_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_GIAOTIEP_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=3 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_GIAOTIEP_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=3 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_GIAOTIEP_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=3 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_GIAOTIEP_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=3 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_GIAOTIEP_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=3 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_HOCNGHE_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=4 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_HOCNGHE_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=4 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_HOCNGHE_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=4 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_HOCNGHE_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=4 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_HOCNGHE_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=4 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_THUNHAP_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=5 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_THUNHAP_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=5 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_THUNHAP_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=5 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_THUNHAP_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=5 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_THUNHAP_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=5 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        String SQL_TONG_NKT_TIENBO_XAHOI_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=6 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_XAHOI_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=6 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_XAHOI_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=6 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_XAHOI_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=6 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_XAHOI_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.danhgia=6 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";*/
        
        // So tien bo ve PHCN ve Y te - suc khoe
        String SQL_TONG_NKT_TIENBO_PHCN_YTE_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_YTE_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_YTE_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_YTE_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_YTE_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        
        // So tien bo ve Doi song Kinh te, Hoa nhap xa hoi
        String SQL_TONG_NKT_TIENBO_PHCN_KTE_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.kinhte_xahoi<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_KTE_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.kinhte_xahoi<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_KTE_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.kinhte_xahoi<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_KTE_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.kinhte_xahoi<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_KTE_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.kinhte_xahoi<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        
        // So tien bo ve Giao duc
        String SQL_TONG_NKT_TIENBO_PHCN_GDUC_0="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<3 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.giaoduc<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_GDUC_3="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<6 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.giaoduc<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_GDUC_6="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=6 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.giaoduc<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_GDUC_18="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.giaoduc<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NKT_TIENBO_PHCN_GDUC_60="SELECT count(DISTINCT dr_danhgia_nkt.id_nkt) AS tong FROM dr_danhgia_nkt inner join dr_disabilitypeople on dr_danhgia_nkt.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_danhgia_nkt.trongky<=2 and dr_danhgia_nkt.yte<=2 and dr_danhgia_nkt.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy')and dr_danhgia_nkt.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";

        //loai khuyet tat
        String SQL_TONG_BAINAO_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#3#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_BAINAO_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#3#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_BAINAO_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#3#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_BANCHANKHEO_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#2#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_BANCHANKHEO_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#2#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_BANCHANKHEO_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#2#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_LOANDUONGCO_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#14#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LOANDUONGCO_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#14#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LOANDUONGCO_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#14#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_VEOCOTSONG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#16#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VEOCOTSONG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#16#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VEOCOTSONG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#16#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_TRATKHOPHANGBAMSING_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#18#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TRATKHOPHANGBAMSING_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#18#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TRATKHOPHANGBAMSING_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#18#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_CHANTHUONGTUYSONG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#5#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CHANTHUONGTUYSONG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#5#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CHANTHUONGTUYSONG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#5#%'  and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_VANDEKHOP_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#4#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VANDEKHOP_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#4#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VANDEKHOP_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#4#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_LIETNUA_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LIETNUA_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LIETNUA_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_CUTCHI_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CUTCHI_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CUTCHI_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_LIETTUCHI_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#234#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LIETTUCHI_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#234#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LIETTUCHI_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#234#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_DOTQUY_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DOTQUY_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DOTQUY_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#6#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_CHANTAYGIA_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CHANTAYGIA_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CHANTAYGIA_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#20#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_KHAC_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#22#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHAC_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#22#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHAC_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#22#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        //giac quan
        String SQL_TONG_KHOKHANNHIN_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#8#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNHIN_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#8#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNHIN_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#8#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        // ###### NGHE - NOI ######
        String SQL_TONG_KHOKHANNGHE_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#9#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNGHE_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#9#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNGHE_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#9#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_KHOKHANNOI_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#24#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNOI_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#24#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KHOKHANNOI_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#24#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        // ###### NHIN ######
        String SQL_TONG_MUMAU_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#236#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MUMAU_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#236#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MUMAU_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#236#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_QUANGGA_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#237#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_QUANGGA_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#237#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_QUANGGA_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#237#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_CANTHINANG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#238#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CANTHINANG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#238#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CANTHINANG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#238#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_MU2MAT_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#239#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MU2MAT_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#239#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MU2MAT_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#239#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_MU1MAT_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#240#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MU1MAT_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#240#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MU1MAT_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#240#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_MATTHITRUONG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#241#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MATTHITRUONG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#241#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MATTHITRUONG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#241#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_VIENTHINANG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#242#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VIENTHINANG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#242#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_VIENTHINANG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#242#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_LACMAT_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#243#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LACMAT_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#243#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_LACMAT_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#243#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_KK_NHINKHAC_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#244#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KK_NHINKHAC_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#244#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_KK_NHINKHAC_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#244#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_TONG_GIAMCAMGIAC_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#26#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_GIAMCAMGIAC_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#26#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_GIAMCAMGIAC_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#26#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_NGHEVANOI_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#10#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NGHEVANOI_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#10#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_NGHEVANOI_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#10#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_TRITUE_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#12#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TRITUE_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#12#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TRITUE_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#12#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_DOWN_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#13#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DOWN_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#13#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DOWN_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#13#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_TUKY_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#30#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TUKY_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#30#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TUKY_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#30#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_TAMTHAN_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#32#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TAMTHAN_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#32#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TAMTHAN_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#32#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        // ###### KHUYET TAT KHAC ######
        String SQL_TONG_DONGKINH_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#190#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DONGKINH_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#190#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_DONGKINH_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#190#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_TIMBAMSINH_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#202#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TIMBAMSINH_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#202#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_TIMBAMSINH_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#202#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_GIAMCHUCNANG_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#192#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_GIAMCHUCNANG_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#192#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_GIAMCHUCNANG_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#192#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_MANTINH_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#204#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MANTINH_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#204#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_MANTINH_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#204#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_TONG_CACTATKHAC_0="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#206#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CACTATKHAC_18="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#206#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_TONG_CACTATKHAC_TRUOC="SELECT count(DISTINCT dr_phanloai.id_nkt) AS tong FROM dr_phanloai inner join dr_disabilitypeople on dr_phanloai.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_phanloai.dangtat_ids like '%#206#%' and dr_disabilitypeople.date_last_update>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_disabilitypeople.date_last_update<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        //###### HO TRO #######
        String SQL_HOTRO_DUNGCU_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_support.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUNGCU_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.datecreate>=to_date("+tuNgay+",'dd/mm/yyyy') and dr_support.datecreate<to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUNGCU="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.datecreate>=to_date("+tuNgayTruoc+",'dd/mm/yyyy') and dr_support.datecreate<to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_DUOC_DUNGCU_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_DUNGCU_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_DUNGCU="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#13#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_THANHSONGSONG_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#28#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_THANHSONGSONG_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#28#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_THANHSONGSONG="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#28#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_KHUNGTAPDI_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#29#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_KHUNGTAPDI_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#29#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_KHUNGTAPDI="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#29#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_NANG_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#30#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NANG_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#30#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NANG="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#30#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_XELAN_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#31#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_XELAN_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#31#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_XELAN="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#31#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_GHEBAINAO_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#32#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_GHEBAINAO_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#32#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_GHEBAINAO="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#32#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_BANDUNG_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#33#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_BANDUNG_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#33#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_BANDUNG="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#33#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_NEP_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#34#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NEP_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#34#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NEP="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#34#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_MAYTROTHINH_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#35#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_MAYTROTHINH_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#35#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_MAYTROTHINH="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#35#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_HOTRO_CHANGIA_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#57#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_CHANGIA_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#57#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_CHANGIA="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#57#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_HOTRO_TAYGIA_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#59#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_TAYGIA_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#59#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_TAYGIA="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#59#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
        
        String SQL_HOTRO_MATGIA_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#131#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_MATGIA_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#131#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_MATGIA="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#131#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_XELAC_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#279#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_XELAC_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#279#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_XELAC="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#279#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";
    
        String SQL_HOTRO_DUNGCUKHAC_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#37#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUNGCUKHAC_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#37#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUNGCUKHAC="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#37#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        //ho tro PHCN tai nha
        String SQL_HOTRO_TAINHA_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_TAINHA_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_TAINHA="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_DUOC_TAINHA_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_TAINHA_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_TAINHA="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#12#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_NHAO_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NHAO_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_NHAO="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_DUOC_NHAO_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_NHAO_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_NHAO="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#18#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_PHAUTHUAT_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_PHAUTHUAT_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_PHAUTHUAT="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=0 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        String SQL_HOTRO_DUOC_PHAUTHUAT_0="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_PHAUTHUAT_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgay+",'dd/mm/yyyy') and to_date("+denNgay+",'dd/mm/yyyy') ";
        String SQL_HOTRO_DUOC_PHAUTHUAT="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#14#%' and dr_support.dateform BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') and dr_support.dateto BETWEEN to_date("+tuNgayTruoc+",'dd/mm/yyyy') and to_date("+denNgayTruoc+",'dd/mm/yyyy') ";

        //tiep can giao duc
        String SQL_TIEPCAN_GIAODUC="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=3 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#103#%' ";
        String SQL_HOTRO_LAMAN_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.dm_hotro_ids like '%#25#%' ";
        String SQL_HOTRO_DUOC_LAMAN_18="SELECT count(DISTINCT dr_support.id_nkt) AS tong FROM dr_support inner join dr_disabilitypeople on dr_support.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and dr_disabilitypeople.id_tinh in ("+member+") and dr_support.status_id=1 and dr_support.dm_hotro_ids like '%#25#%' ";
        
        String SQL_TUKIEMSONG="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#101#%' ";
        String SQL_QHVOCHONG="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#109#%' ";
        String SQL_TOCHUCXH="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#111#%' ";
        String SQL_NHOMTULUC="SELECT count(DISTINCT dr_rank.id_nkt) AS tong FROM dr_rank inner join dr_disabilitypeople on dr_rank.id_nkt=dr_disabilitypeople.id and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)>=18 and EXTRACT(year FROM CURRENT_DATE)-EXTRACT(year FROM dr_disabilitypeople.ngaysinh)<60 and dr_disabilitypeople.id_tinh in ("+member+") and dr_rank.danhgia_ids like '%#113#%' ";

        try {
            params.add(bean.getTinhId());
            params.add(bean.getCommune());
            params.add(bean.getDistrict());
            params.add(bean.getProvince());             
            params.add(bean.getPeriod());
            params.add(bean.getYearPeriod());
            params.add(beanPo.getMaleLessThan18());
            params.add(beanPo.getMaleMoreThan18());
          
            int tongNam=beanPo.getMaleLessThan18()+beanPo.getMaleMoreThan18();
            params.add(tongNam);           
            params.add(beanPo.getFamaleLessThan18());
            params.add(beanPo.getFamaleMoreThan18());
         
            int tongNu=beanPo.getFamaleLessThan18()+beanPo.getFamaleMoreThan18();
            params.add(tongNu);
            
            int tongDanSo=tongNam+tongNu;             
            params.add(bean.ncrToString("T&#7892;NG D&#194;N S&#7888;:(")+tongDanSo+")");         // Param[12]
             
            int tong_nam_0=getCount(cnn,SQL_TONG_NAM_0);
            int tong_nam_3=getCount(cnn,SQL_TONG_NAM_3);
            int tong_nam_6=getCount(cnn,SQL_TONG_NAM_6);
            int tong_nam_18=getCount(cnn,SQL_TONG_NAM_18);
            int tong_nam_60=getCount(cnn,SQL_TONG_NAM_60);            
            int tongNKT_NAM=tong_nam_0+tong_nam_3+tong_nam_6+tong_nam_18+tong_nam_60;
            
            int tong_nu_0=getCount(cnn,SQL_TONG_NU_0); 
            int tong_nu_3=getCount(cnn,SQL_TONG_NU_3);
            int tong_nu_6=getCount(cnn,SQL_TONG_NU_6);
            int tong_nu_18=getCount(cnn,SQL_TONG_NU_18);
            int tong_nu_60=getCount(cnn,SQL_TONG_NU_60);             
            int tongNKT_NU=tong_nu_0+tong_nu_3+tong_nu_6+tong_nu_18+tong_nu_60;
            
            //tong NKT <=18             
            int tong_3_18=tong_nam_3+tong_nam_6+tong_nu_3+tong_nu_6;
            int tong_18_60=tong_nam_18+tong_nu_18;           
            
            int tong0=tong_nam_0+tong_nu_0;
            int tong3=tong_nam_3+tong_nu_3;
            int tong6=tong_nam_6+tong_nu_6;
            int tong18=tong_nam_18+tong_nu_18;
            int tong60=tong_nam_60+tong_nu_60;

            //tong NKT
            int tong=tongNKT_NAM+tongNKT_NU;
            
            int tong_kinhTe_giau=getCount(cnn,SQL_TONG_KINHTE_GIAU);
            params.add(tong==0?tong_kinhTe_giau+"":tong_kinhTe_giau+"("+Math.round((float)tong_kinhTe_giau/(float)tong*100)+"%)");
            
            //int tong_kinhTe_kha=getCount(cnn,SQL_TONG_KINHTE_KHA);
            //params.add(tong==0?tong_kinhTe_kha+"":tong_kinhTe_kha+"("+Math.round((float)tong_kinhTe_kha/(float)tong*100)+"%)");
            params.add("");
            
            int tong_kinhTe_trungbinh=getCount(cnn,SQL_TONG_KINHTE_TRUNGBINH);
            params.add(tong==0?tong_kinhTe_trungbinh+"":tong_kinhTe_trungbinh+"("+Math.round((float)tong_kinhTe_trungbinh/(float)tong*100)+"%)");
            
            int tong_kinhTe_canngheo=getCount(cnn,SQL_TONG_KINHTE_CANNGHEO);
            params.add(tong==0?tong_kinhTe_canngheo+"":tong_kinhTe_canngheo+"("+Math.round((float)tong_kinhTe_canngheo/(float)tong*100)+"%)");
            
            int tong_kinhTe_ngheo=getCount(cnn,SQL_TONG_KINHTE_NGHEO);
            params.add(tong==0?tong_kinhTe_ngheo+"":tong_kinhTe_ngheo+"("+Math.round((float)tong_kinhTe_ngheo/(float)tong*100)+"%)");
            
            int tong_kinhTe_dacbietngheo=getCount(cnn,SQL_TONG_KINHTE_DACBIETNGHEO);
            params.add(tong==0?tong_kinhTe_dacbietngheo+"":tong_kinhTe_dacbietngheo+"("+Math.round((float)tong_kinhTe_dacbietngheo/(float)tong*100)+"%)");
            
            params.add(getCount(cnn,SQL_TONG_GIADINH_NKT_NUOCMAY));
            params.add(getCount(cnn,SQL_TONG_GIADINH_NKT_2));
            params.add(bean.ncrToString("S&#7889; NKT: (")+tong+")");
            
            params.add(tong_nam_0);
            params.add(tong_nam_3);
            params.add(tong_nam_6);
            params.add(tong_nam_18);
            params.add(tong_nam_60);
             
            params.add(tong_nu_0);
            params.add(tong_nu_3);
            params.add(tong_nu_6);
            params.add(tong_nu_18);
            params.add(tong_nu_60);

            //NKT da cam
            int nkt_dacam0=getCount(cnn,SQL_TONG_NKT_DACAM_0); 
            int nkt_dacam3=getCount(cnn,SQL_TONG_NKT_DACAM_3);
            int nkt_dacam6=getCount(cnn,SQL_TONG_NKT_DACAM_6);
            int nkt_dacam18=getCount(cnn,SQL_TONG_NKT_DACAM_18);
            int nkt_dacam60=getCount(cnn,SQL_TONG_NKT_DACAM_60);
            int nkt_dacam_total=nkt_dacam0+nkt_dacam3+nkt_dacam6+nkt_dacam18+nkt_dacam60;
            
            params.add(tong==0?bean.ncrToString("S&#7889; n&#7841;n nh&#226;n da cam: ")+nkt_dacam_total+""
                             :bean.ncrToString("S&#7889; n&#7841;n nh&#226;n da cam: ")+nkt_dacam_total+" ("+getPercent(nkt_dacam_total, tong)+")");
            params.add(tong==0?nkt_dacam0+"":nkt_dacam0+"("+ getPercent(nkt_dacam0, tong) +")");
            params.add(tong==0?nkt_dacam3+"":nkt_dacam3+"("+ getPercent(nkt_dacam3, tong) +")");
            params.add(tong==0?nkt_dacam6+"":nkt_dacam6+"("+ getPercent(nkt_dacam6, tong) +")");
            params.add(tong==0?nkt_dacam18+"":nkt_dacam18+"("+ getPercent(nkt_dacam18, tong) +")");
            params.add(tong==0?nkt_dacam60+"":nkt_dacam60+"("+ getPercent(nkt_dacam60, tong) +")");

            //NKT moi    
            int nkt_moi0=getCount(cnn,SQL_TONG_NKT_MOI_0);
            int nkt_moi3=getCount(cnn,SQL_TONG_NKT_MOI_3);
            int nkt_moi6=getCount(cnn,SQL_TONG_NKT_MOI_6);
            int nkt_moi18=getCount(cnn,SQL_TONG_NKT_MOI_18);
            int nkt_moi60=getCount(cnn,SQL_TONG_NKT_MOI_60);
            int nkt_moi_total=nkt_moi0+nkt_moi3+nkt_moi6+nkt_moi18+nkt_moi60;
            
            params.add(bean.ncrToString("S&#7889; NKT m&#7899;i trong k&#7923; b&#225;o c&#225;o: ")+nkt_moi_total);
            params.add(nkt_moi0);
            params.add(nkt_moi3);
            params.add(nkt_moi6);
            params.add(nkt_moi18);
            params.add(nkt_moi60);

            //NKT co ke hoach             
            int nkt_khoach0=getCount(cnn,SQL_TONG_NKT_KEHOACH_HT_0); 
            int nkt_khoach3=getCount(cnn,SQL_TONG_NKT_KEHOACH_HT_3);
            int nkt_khoach6=getCount(cnn,SQL_TONG_NKT_KEHOACH_HT_6);
            int nkt_khoach18=getCount(cnn,SQL_TONG_NKT_KEHOACH_HT_18);
            int nkt_khoach60=getCount(cnn,SQL_TONG_NKT_KEHOACH_HT_60);
            int nkt_khoach=nkt_khoach0+nkt_khoach3+nkt_khoach6+nkt_khoach18+nkt_khoach60;
            
            params.add(tong==0?bean.ncrToString("S&#7889; NKT c&#243; k&#7871; ho&#7841;ch h&#7895; tr&#7907; c&#225; nh&#226;n: ")+nkt_khoach+""
                              :bean.ncrToString("S&#7889; NKT c&#243; k&#7871; ho&#7841;ch h&#7895; tr&#7907; c&#225; nh&#226;n: ")+nkt_khoach+"("+Math.round((float)nkt_khoach/(float)tong*100)+"%)");
            params.add(tong==0?nkt_khoach0+"":nkt_khoach0+"("+getPercent(nkt_khoach0, tong)+")");
            params.add(tong==0?nkt_khoach3+"":nkt_khoach3+"("+getPercent(nkt_khoach3, tong)+")");
            params.add(tong==0?nkt_khoach6+"":nkt_khoach6+"("+getPercent(nkt_khoach3, tong)+")");
            params.add(tong==0?nkt_khoach18+"":nkt_khoach18+"("+getPercent(nkt_khoach18, tong)+")");
            params.add(tong==0?nkt_khoach60+"":nkt_khoach60+"("+getPercent(nkt_khoach60, tong)+")");

            //NKT co MUC TIEU DE RA TRONG KE HOACH            
            int nkt_muctieu_khoach0=getCount(cnn,SQL_TONG_NKT_MUCTEU_KEHOACH_HT_0); 
            int nkt_muctieu_khoach3=getCount(cnn,SQL_TONG_NKT_MUCTEU_KEHOACH_HT_3);
            int nkt_muctieu_khoach6=getCount(cnn,SQL_TONG_NKT_MUCTEU_KEHOACH_HT_6);
            int nkt_muctieu_khoach18=getCount(cnn,SQL_TONG_NKT_MUCTEU_KEHOACH_HT_18);
            int nkt_muctieu_khoach60=getCount(cnn,SQL_TONG_NKT_MUCTEU_KEHOACH_HT_60);
            int nkt_muctieu_khoach=nkt_khoach0+nkt_khoach3+nkt_khoach6+nkt_khoach18+nkt_khoach60;
            
            params.add(nkt_khoach==0?bean.ncrToString("S&#7889; NKT c&#243; m&#7909;c ti&#234;u &#273;&#7873; ra trong k&#7871; ho&#7841;ch c&#225; nh&#226;n: ")+nkt_muctieu_khoach+""
                                    :bean.ncrToString("S&#7889; NKT c&#243; m&#7909;c ti&#234;u &#273;&#7873; ra trong k&#7871; ho&#7841;ch c&#225; nh&#226;n: ")+nkt_muctieu_khoach+"("+Math.round((float)nkt_muctieu_khoach/(float)nkt_khoach*100)+"%)");
            params.add(nkt_khoach==0?nkt_muctieu_khoach0+"":nkt_muctieu_khoach0+"("+getPercent(nkt_muctieu_khoach0, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_muctieu_khoach3+"":nkt_muctieu_khoach3+"("+getPercent(nkt_muctieu_khoach3, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_muctieu_khoach6+"":nkt_muctieu_khoach6+"("+getPercent(nkt_muctieu_khoach6, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_muctieu_khoach18+"":nkt_muctieu_khoach18+"("+getPercent(nkt_muctieu_khoach18, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_muctieu_khoach60+"":nkt_muctieu_khoach60+"("+getPercent(nkt_muctieu_khoach60, nkt_khoach)+")");

            //NKT ra khoi chuong chinh     
            int nkt_out0=getCount(cnn,SQL_TONG_NKT_OUT_0);
            int nkt_out3=getCount(cnn,SQL_TONG_NKT_OUT_3);
            int nkt_out6=getCount(cnn,SQL_TONG_NKT_OUT_6);
            int nkt_out18=getCount(cnn,SQL_TONG_NKT_OUT_18);
            int nkt_out60=getCount(cnn,SQL_TONG_NKT_OUT_60);
            int nkt_out_total=nkt_out0+nkt_out3+nkt_out6+nkt_out18+nkt_out60;
            
            params.add(nkt_khoach==0?bean.ncrToString("S&#7889; NKT ra kh&#7887;i ch&#432;&#417;ng tr&#236;nh trong k&#7923; b&#225;o c&#225;o: ")+nkt_out_total+"":bean.ncrToString("S&#7889; NKT ra kh&#7887;i ch&#432;&#417;ng tr&#236;nh trong k&#7923; b&#225;o c&#225;o: ")+nkt_out_total+"("+Math.round((float)nkt_out_total/(float)nkt_khoach*100)+"%)");
            params.add(nkt_khoach==0?nkt_out0+"":nkt_out0+"("+getPercent(nkt_out0, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_out3+"":nkt_out3+"("+getPercent(nkt_out3, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_out6+"":nkt_out6+"("+getPercent(nkt_out6, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_out18+"":nkt_out18+"("+getPercent(nkt_out18, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_out60+"":nkt_out60+"("+getPercent(nkt_out60, nkt_khoach)+")");
            
            //NKT bo cuoc            
            int nkt_bo_cuoc0=getCount(cnn,SQL_TONG_NKT_BOCUOC_0); 
            int nkt_bo_cuoc3=getCount(cnn,SQL_TONG_NKT_BOCUOC_3);
            int nkt_bo_cuoc6=getCount(cnn,SQL_TONG_NKT_BOCUOC_6);
            int nkt_bo_cuoc18=getCount(cnn,SQL_TONG_NKT_BOCUOC_18);
            int nkt_bo_cuoc60=getCount(cnn,SQL_TONG_NKT_BOCUOC_60);
            int nkt_bo_cuoc_total=nkt_bo_cuoc0+nkt_bo_cuoc3+nkt_bo_cuoc6+nkt_bo_cuoc18+nkt_bo_cuoc60;
            
            params.add(nkt_khoach==0?bean.ncrToString("S&#7889; NKT b&#7887; cu&#7897;c: ")+nkt_bo_cuoc_total+ "":bean.ncrToString("S&#7889; NKT b&#7887; cu&#7897;c: ")+nkt_bo_cuoc_total+ "("+Math.round((float)nkt_bo_cuoc_total/(float)nkt_khoach*100)+"%)");
            params.add(nkt_khoach==0?nkt_bo_cuoc0+ "":nkt_bo_cuoc0+ "("+getPercent(nkt_bo_cuoc0, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_bo_cuoc3+ "":nkt_bo_cuoc3+ "("+getPercent(nkt_bo_cuoc3, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_bo_cuoc6+ "":nkt_bo_cuoc6+ "("+getPercent(nkt_bo_cuoc6, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_bo_cuoc18+ "":nkt_bo_cuoc18+ "("+getPercent(nkt_bo_cuoc18, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_bo_cuoc60+ "":nkt_bo_cuoc60+ "("+getPercent(nkt_bo_cuoc60, nkt_khoach)+")");

            //NKT tiep tuc can theo doi           
            int nkt_theo_doi0=tong0-nkt_out0; 
            int nkt_theo_doi3=tong3-nkt_out3;
            int nkt_theo_doi6=tong6-nkt_out6;
            int nkt_theo_doi18=tong18-nkt_out18;
            int nkt_theo_doi60=tong60-nkt_out60;
            int nkt_theo_doi_total=nkt_theo_doi0+nkt_theo_doi3+nkt_theo_doi6+nkt_theo_doi18+nkt_theo_doi60;
            
            params.add(tong==0?bean.ncrToString("S&#7889; ti&#7871;p t&#7909;c c&#7847;n theo d&#245;i v&#224; PHCN: ")+nkt_theo_doi_total+ "":bean.ncrToString("S&#7889; ti&#7871;p t&#7909;c c&#7847;n theo d&#245;i v&#224; PHCN:")+nkt_theo_doi_total+ "("+Math.round((float)nkt_theo_doi_total/(float)tong*100)+"%)");
            params.add(tong==0?nkt_theo_doi0+ "":nkt_theo_doi0+ "("+getPercent(nkt_theo_doi0, nkt_khoach)+")");
            params.add(tong==0?nkt_theo_doi3+ "":nkt_theo_doi3+ "("+getPercent(nkt_theo_doi3, nkt_khoach)+")");
            params.add(tong==0?nkt_theo_doi6+ "":nkt_theo_doi6+ "("+getPercent(nkt_theo_doi6, nkt_khoach)+")");
            params.add(tong==0?nkt_theo_doi18+ "":nkt_theo_doi18+ "("+getPercent(nkt_theo_doi18, nkt_khoach)+")");
            params.add(tong==0?nkt_theo_doi60+ "":nkt_theo_doi60+ "("+getPercent(nkt_theo_doi60, nkt_khoach)+")");

            //NKT dat duoc muc tieu            
            int nkt_datMucTieu0=getCount(cnn,SQL_TONG_NKT_DATDUOC_MUCTIEU_0); 
            int nkt_datMucTieu3=getCount(cnn,SQL_TONG_NKT_DATDUOC_MUCTIEU_3);
            int nkt_datMucTieu6=getCount(cnn,SQL_TONG_NKT_DATDUOC_MUCTIEU_6);
            int nkt_datMucTieu18=getCount(cnn,SQL_TONG_NKT_DATDUOC_MUCTIEU_18);
            int nkt_datMucTieu60=getCount(cnn,SQL_TONG_NKT_DATDUOC_MUCTIEU_60);
            int nkt_datMucTieu_total=nkt_datMucTieu0+nkt_datMucTieu3+nkt_datMucTieu6+nkt_datMucTieu18+nkt_datMucTieu60;
            
            params.add(nkt_khoach==0?bean.ncrToString("S&#7889; NKT &#273;&#7841;t &#273;&#432;&#7907;c m&#7909;c ti&#234;u c&#225; nh&#226;n trong k&#7923; b&#225;o c&#225;o: ")+nkt_datMucTieu_total+ ""
                                    :bean.ncrToString("S&#7889; NKT &#273;&#7841;t &#273;&#432;&#7907;c m&#7909;c ti&#234;u: ")+nkt_datMucTieu_total+ "("+getPercent(nkt_datMucTieu_total, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_datMucTieu0+ "":nkt_datMucTieu0+ "("+getPercent(nkt_datMucTieu0, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_datMucTieu3+ "":nkt_datMucTieu3+ "("+getPercent(nkt_datMucTieu3, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_datMucTieu6+ "":nkt_datMucTieu6+ "("+getPercent(nkt_datMucTieu6, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_datMucTieu18+ "":nkt_datMucTieu18+ "("+getPercent(nkt_datMucTieu18, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_datMucTieu60+ "":nkt_datMucTieu60+ "("+getPercent(nkt_datMucTieu60, nkt_khoach)+")");
        
            //NKT co tien bo           
            int nkt_tienBo0=getCount(cnn,SQL_TONG_NKT_TIENBO_0);
            int nkt_tienBo3=getCount(cnn,SQL_TONG_NKT_TIENBO_3);
            int nkt_tienBo6=getCount(cnn,SQL_TONG_NKT_TIENBO_6);
            int nkt_tienBo18=getCount(cnn,SQL_TONG_NKT_TIENBO_18);
            int nkt_tienBo60=getCount(cnn,SQL_TONG_NKT_TIENBO_60);
            int nkt_tienBo=nkt_tienBo0+nkt_tienBo3+nkt_tienBo18+nkt_tienBo60;
            params.add(nkt_khoach==0?bean.ncrToString("S&#7889; NKT &#273;&#227; c&#243; ti&#7871;n b&#7897; trong v&#242;ng 6 th&#225;ng qua: ")+nkt_tienBo+""
                                    :bean.ncrToString("S&#7889; NKT &#273;&#227; c&#243; ti&#7871;n b&#7897; trong v&#242;ng 6 th&#225;ng qua: ")+nkt_tienBo+"("+getPercent(nkt_tienBo, nkt_khoach)+")");
            
            params.add(nkt_khoach==0?nkt_tienBo0+"":nkt_tienBo0+"("+getPercent(nkt_tienBo0, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_tienBo3+"":nkt_tienBo3+"("+getPercent(nkt_tienBo3, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_tienBo6+"":nkt_tienBo6+"("+getPercent(nkt_tienBo6, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_tienBo18+"":nkt_tienBo18+"("+getPercent(nkt_tienBo18, nkt_khoach)+")");
            params.add(nkt_khoach==0?nkt_tienBo60+"":nkt_tienBo60+"("+getPercent(nkt_tienBo60, nkt_khoach)+")");
        
            // Update 2013
            // Yte - Suc khoe
            int nkt_tienBo_yte0=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_YTE_0);
            int nkt_tienBo_yte3=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_YTE_3);
            int nkt_tienBo_yte6=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_YTE_6);
            int nkt_tienBo_yte18=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_YTE_18);
            int nkt_tienBo_yte60=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_YTE_60);
            int nkt_total_yte=nkt_tienBo_yte0+nkt_tienBo_yte3+nkt_tienBo_yte6+nkt_tienBo_yte18+nkt_tienBo_yte60;
            
            params.add(bean.ncrToString("S&#7889; ti&#7871;n b&#7897; v&#7873; PHCN v&#7873; Y t&#7871; - s&#7913;c kh&#7887;e: ")+nkt_total_yte);
            params.add(nkt_tienBo_yte0);
            params.add(nkt_tienBo_yte3);
            params.add(nkt_tienBo_yte6);
            params.add(nkt_tienBo_yte18);
            params.add(nkt_tienBo_yte60);
            
            // Kinh te - Xa hoi     
            int nkt_tienBo_kte0=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_KTE_0);
            int nkt_tienBo_kte3=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_KTE_3);
            int nkt_tienBo_kte6=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_KTE_6);
            int nkt_tienBo_kte18=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_KTE_18);
            int nkt_tienBo_kte60=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_KTE_60);
            int nkt_total_kte=nkt_tienBo_kte0+nkt_tienBo_kte3+nkt_tienBo_kte6+nkt_tienBo_kte18+nkt_tienBo_kte60;
            
            params.add(bean.ncrToString("S&#7889; ti&#7871;n b&#7897; v&#7873; &#272;&#7901;i s&#7889;ng kinh t&#7871;, h&#242;a nh&#7853;p x&#227; h&#7897;i: ")+nkt_total_kte);
            params.add(nkt_tienBo_kte0);
            params.add(nkt_tienBo_kte3);
            params.add(nkt_tienBo_kte6);
            params.add(nkt_tienBo_kte18);
            params.add(nkt_tienBo_kte60);
            
            // Giao duc
            int nkt_tienBo_gduc0=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_GDUC_0);
            int nkt_tienBo_gduc3=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_GDUC_3);
            int nkt_tienBo_gduc6=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_GDUC_6);
            int nkt_tienBo_gduc18=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_GDUC_18);
            int nkt_tienBo_gduc60=getCount(cnn, SQL_TONG_NKT_TIENBO_PHCN_GDUC_60);
            int nkt_total_gduc=nkt_tienBo_gduc0+nkt_tienBo_gduc3+nkt_tienBo_gduc6+nkt_tienBo_gduc18+nkt_tienBo_gduc60;
            
            params.add(bean.ncrToString("S&#7889; ti&#7871;n b&#7897; v&#7873; Gi&#225;o d&#7909;c: ")+nkt_total_gduc);
            params.add(nkt_tienBo_gduc0);
            params.add(nkt_tienBo_gduc3);
            params.add(nkt_tienBo_gduc6);
            params.add(nkt_tienBo_gduc18);
            params.add(nkt_tienBo_gduc60);
            
            //dang tat --------------
            int bainao0=getCount(cnn,SQL_TONG_BAINAO_0);                    params.add(tong==0?bainao0+"":bainao0+"("+getPercent(bainao0, tong)+")");
            int bainao18=getCount(cnn,SQL_TONG_BAINAO_18);                  params.add(tong==0?bainao18+"":bainao18+"("+getPercent(bainao18, tong)+")");
            int bainaoTruoc=getCount(cnn,SQL_TONG_BAINAO_TRUOC);            params.add(bainaoTruoc+"");
            int bainaoNay=bainao0+ bainao18;                                params.add(bainaoNay+"");
            int bainaoLuyke=bainaoTruoc+bainaoNay;                          params.add(bainaoLuyke+"");
            
            int banchankheo0=getCount(cnn,SQL_TONG_BANCHANKHEO_0);          params.add(tong==0?banchankheo0+"":banchankheo0+"("+getPercent(banchankheo0, tong)+")");
            int banchankheo18=getCount(cnn,SQL_TONG_BANCHANKHEO_18);        params.add(tong==0?banchankheo18+"":banchankheo18+"("+getPercent(banchankheo18, tong)+")");
            int banchankheoTruoc=getCount(cnn,SQL_TONG_BANCHANKHEO_TRUOC);  params.add(banchankheoTruoc+"");
            int banchankheoNay=banchankheo0+banchankheo18;                  params.add(banchankheoNay+"");
            int banchankheoLuyKe=banchankheoTruoc+banchankheoNay;           params.add(banchankheoLuyKe+"");
            
            int loanduongco0=getCount(cnn,SQL_TONG_LOANDUONGCO_0);          params.add(tong==0?loanduongco0+"":loanduongco0+"("+getPercent(loanduongco0, tong)+")");
            int loanduongco18=getCount(cnn,SQL_TONG_LOANDUONGCO_18);        params.add(tong==0?loanduongco18+"":loanduongco18+"("+getPercent(loanduongco18, tong)+")");
            int loanduongcoTruoc=getCount(cnn,SQL_TONG_LOANDUONGCO_TRUOC);  params.add(loanduongcoTruoc+"");
            int loanduongcoNay=loanduongco0+loanduongco18;                  params.add(loanduongcoNay+"");
            int loanduongcoLuyKe=loanduongcoTruoc+loanduongcoNay;           params.add(loanduongcoLuyKe+"");
            
            int veocotsong0=getCount(cnn,SQL_TONG_VEOCOTSONG_0);            params.add(tong==0?veocotsong0+"":veocotsong0+"("+getPercent(veocotsong0, tong)+")");
            int veocotsong18=getCount(cnn,SQL_TONG_VEOCOTSONG_18);          params.add(tong==0?veocotsong18+"":veocotsong18+"("+getPercent(veocotsong18, tong)+")");
            int veocotsongTruoc=getCount(cnn,SQL_TONG_VEOCOTSONG_TRUOC);    params.add(veocotsongTruoc+"");
            int veocotsongNay=veocotsong0+veocotsong18;                     params.add(veocotsongNay+"");
            int veocotsongLuyKe=veocotsongTruoc+veocotsongNay;              params.add(veocotsongLuyKe+"");
            
            int trat_khop0=getCount(cnn,SQL_TONG_TRATKHOPHANGBAMSING_0);           params.add(tong==0?trat_khop0+"":trat_khop0+"("+getPercent(trat_khop0, tong)+")");
            int trat_khop18=getCount(cnn,SQL_TONG_TRATKHOPHANGBAMSING_18);         params.add(tong==0?trat_khop18+"":trat_khop18+"("+getPercent(trat_khop18, tong)+")");
            int trat_khopTruoc=getCount(cnn,SQL_TONG_TRATKHOPHANGBAMSING_TRUOC);   params.add(trat_khopTruoc+"");
            int trat_khopNay=trat_khop0+trat_khop18;                               params.add(trat_khopNay+"");
            int trat_khopLuyKe=trat_khopTruoc+trat_khopNay;                        params.add(trat_khopLuyKe+"");
            
            int chan_thuong0=getCount(cnn,SQL_TONG_CHANTHUONGTUYSONG_0);           params.add(tong==0?chan_thuong0+"":chan_thuong0+"("+getPercent(chan_thuong0, tong)+")");
            int chan_thuong18=getCount(cnn,SQL_TONG_CHANTHUONGTUYSONG_18);         params.add(tong==0?chan_thuong18+"":chan_thuong18+"("+getPercent(chan_thuong18, tong)+")");
            int chan_thuongTruoc=getCount(cnn,SQL_TONG_CHANTHUONGTUYSONG_TRUOC);   params.add(chan_thuongTruoc+"");
            int chan_thuongNay=chan_thuong0+chan_thuong18;                         params.add(chan_thuongNay+"");
            int chan_thuongLuyKe=chan_thuongTruoc+chan_thuongNay;                  params.add(chan_thuongLuyKe+"");
            
            int van_de_khop0=getCount(cnn,SQL_TONG_VANDEKHOP_0);            params.add(tong==0?van_de_khop0+"":van_de_khop0+"("+getPercent(van_de_khop0, tong)+")");
            int van_de_khop18=getCount(cnn,SQL_TONG_VANDEKHOP_18);          params.add(tong==0?van_de_khop18+"":van_de_khop18+"("+getPercent(van_de_khop18, tong)+")");
            int van_de_khopTruoc=getCount(cnn,SQL_TONG_VANDEKHOP_TRUOC);    params.add(van_de_khopTruoc+"");
            int van_de_khopNay=van_de_khop0+van_de_khop18;                  params.add(van_de_khopNay+"");
            int van_de_khopLuyKe=van_de_khopTruoc+van_de_khopNay;           params.add(van_de_khopLuyKe+"");
            
            int liet_nua_nguoi0=getCount(cnn, SQL_TONG_LIETNUA_0);             params.add(tong==0?liet_nua_nguoi0+"":liet_nua_nguoi0+"("+getPercent(liet_nua_nguoi0, tong)+")");
            int liet_nua_nguoi18=getCount(cnn, SQL_TONG_LIETNUA_18);           params.add(tong==0?liet_nua_nguoi18+"":liet_nua_nguoi18+"("+getPercent(liet_nua_nguoi18, tong)+")");
            int liet_nua_nguoiTruoc=getCount(cnn, SQL_TONG_LIETNUA_TRUOC);     params.add(liet_nua_nguoiTruoc+"");
            int liet_nua_nguoiNay=liet_nua_nguoi0+liet_nua_nguoi18;            params.add(liet_nua_nguoiNay+"");
            int liet_nua_nguoiLuyKe=liet_nua_nguoiTruoc+liet_nua_nguoiNay;     params.add(liet_nua_nguoiLuyKe+"");
             
            int cut_chi0=getCount(cnn, SQL_TONG_CUTCHI_0);                     params.add(tong==0?cut_chi0+"":cut_chi0+"("+getPercent(cut_chi0, tong)+")");
            int cut_chi18=getCount(cnn, SQL_TONG_CUTCHI_18);                   params.add(tong==0?cut_chi18+"":cut_chi18+"("+getPercent(cut_chi18, tong)+")");
            int cut_chiTruoc=getCount(cnn, SQL_TONG_CUTCHI_TRUOC);             params.add(cut_chiTruoc+"");
            int cut_chiNay=cut_chi0+cut_chi18;                                 params.add(cut_chiNay+"");
            int cut_chiLuyKe=cut_chiTruoc+cut_chiNay;                          params.add(cut_chiLuyKe+"");                 
            
            int liet_tu_chi0=getCount(cnn, SQL_TONG_LIETTUCHI_0);              params.add(tong==0?liet_tu_chi0+"":liet_tu_chi0+"("+getPercent(liet_nua_nguoi0, tong)+")");
            int liet_tu_chi18=getCount(cnn, SQL_TONG_LIETTUCHI_18);            params.add(tong==0?liet_tu_chi18+"":liet_tu_chi18+"("+getPercent(liet_tu_chi18, tong)+")");             
            int liet_tu_chiTruoc=getCount(cnn, SQL_TONG_LIETTUCHI_TRUOC);      params.add(liet_tu_chiTruoc+"");
            int liet_tu_chiNay=cut_chi0+cut_chi18;                             params.add(liet_tu_chiNay+"");
            int liet_tu_chiLuyKe=liet_tu_chiTruoc+liet_tu_chiNay;              params.add(liet_tu_chiLuyKe+"");

            int khac0=getCount(cnn,SQL_TONG_KHAC_0);            params.add(tong==0?khac0+"":khac0+"("+getPercent(khac0, tong)+"%)");
            int khac18=getCount(cnn,SQL_TONG_KHAC_18);          params.add(tong==0?khac18+"":khac18+"("+getPercent(khac18, tong)+"%)");
            int khacTruoc=getCount(cnn,SQL_TONG_KHAC_TRUOC);    params.add(khacTruoc+"");
            int khacNay=khac0+khac18;                           params.add(khacNay+"");
            int khacLuyKe=khacTruoc+khacNay;                    params.add(khacLuyKe+"");
            int soLuong_vanDong=bainaoNay+banchankheoNay+loanduongcoNay+veocotsongNay+trat_khopNay+chan_thuongNay+van_de_khopNay+cut_chiNay+liet_nua_nguoiNay+liet_tu_chiNay+khacNay;
            
            params.add(tong==0?bean.ncrToString("V&#7853;n &#273;&#7897;ng:")+soLuong_vanDong+"":bean.ncrToString("V&#7853;n &#273;&#7897;ng:")+soLuong_vanDong+"("+getPercent(soLuong_vanDong, tong)+")");

            //Nghe/Noi
            int khoKhanNghe0=getCount(cnn,SQL_TONG_KHOKHANNGHE_0);          params.add(tong==0?khoKhanNghe0+"":khoKhanNghe0+"("+getPercent(khoKhanNghe0, tong)+")");
            int khoKhanNghe18=getCount(cnn,SQL_TONG_KHOKHANNGHE_18);        params.add(tong==0?khoKhanNghe18+"":khoKhanNghe18+"("+getPercent(khoKhanNghe18, tong)+")");
            int khoKhanNgheTruoc=getCount(cnn,SQL_TONG_KHOKHANNGHE_TRUOC);  params.add(khoKhanNgheTruoc+"");
            int khoKhanNgheNay=khoKhanNghe0+khoKhanNghe18;                  params.add(khoKhanNgheNay+"");
            int khoKhanNgheLuyKe=khoKhanNgheTruoc+khoKhanNgheNay;           params.add(khoKhanNgheLuyKe+"");
            
            int khoKhanNoi0=getCount(cnn,SQL_TONG_KHOKHANNOI_0);            params.add(tong==0?khoKhanNoi0+"":khoKhanNoi0+"("+getPercent(khoKhanNoi0, tong)+")");
            int khoKhanNoi18=getCount(cnn,SQL_TONG_KHOKHANNOI_18);          params.add(tong==0?khoKhanNoi18+"":khoKhanNoi18+"("+getPercent(khoKhanNoi18, tong)+")");
            int khoKhanNoiTruoc=getCount(cnn,SQL_TONG_KHOKHANNOI_TRUOC);    params.add(khoKhanNoiTruoc+"");
            int khoKhanNoiNay=khoKhanNoi0+khoKhanNoi18;                     params.add(khoKhanNoiNay+"");
            int khoKhanNoiLuyKe=khoKhanNoiTruoc+khoKhanNoiNay;              params.add(khoKhanNoiLuyKe+"");            
            int totalNgheNoi=khoKhanNgheNay+khoKhanNoiNay;            
            
            params.add(tong==0?bean.ncrToString("Nghe, n&#243;i: ")+totalNgheNoi+"":bean.ncrToString("Nghe, n&#243;i: ")+totalNgheNoi+"("+getPercent(totalNgheNoi, tong)+")");
        
            // ##### Nhin #####            
            int nhinMuMau0=getCount(cnn, SQL_TONG_MUMAU_0);                 params.add(tong==0?nhinMuMau0+"":nhinMuMau0+"("+getPercent(nhinMuMau0, tong)+")");
            int nhinMuMau18=getCount(cnn, SQL_TONG_MUMAU_18);               params.add(tong==0?khoKhanNoi18+"":khoKhanNoi18+"("+getPercent(nhinMuMau18, tong)+")");
            int nhinMuMauTruoc=getCount(cnn, SQL_TONG_MUMAU_TRUOC);         params.add(nhinMuMauTruoc+"");
            int nhinMuMauNay=nhinMuMau0+nhinMuMau18;                        params.add(nhinMuMauNay+"");
            int nhinMuMauLuyKe=nhinMuMauTruoc+nhinMuMauNay;                 params.add(nhinMuMauLuyKe+"");
            
            int nhinQuangGa0=getCount(cnn, SQL_TONG_QUANGGA_0);             params.add(tong==0?nhinQuangGa0+"":nhinQuangGa0+"("+getPercent(nhinQuangGa0, tong)+")");
            int nhinQuangGa18=getCount(cnn, SQL_TONG_QUANGGA_18);           params.add(tong==0?nhinQuangGa18+"":nhinQuangGa18+"("+getPercent(nhinQuangGa18, tong)+")");
            int nhinQuangGaTruoc=getCount(cnn, SQL_TONG_QUANGGA_TRUOC);     params.add(nhinQuangGaTruoc+"");
            int nhinQuangGaNay=nhinQuangGa0+nhinQuangGa18;                  params.add(nhinQuangGaNay+"");
            int nhinQuangGaLuyKe=nhinQuangGaTruoc+nhinQuangGaNay;           params.add(nhinQuangGaLuyKe+"");
             
            int nhinCanThi0=getCount(cnn, SQL_TONG_CANTHINANG_0);           params.add(tong==0?nhinCanThi0+"":nhinCanThi0+"("+getPercent(nhinCanThi0, tong)+")");
            int nhinCanThi18=getCount(cnn, SQL_TONG_CANTHINANG_18);         params.add(tong==0?nhinCanThi18+"":nhinCanThi18+"("+getPercent(nhinCanThi18, tong)+")");
            int nhinCanThiTruoc=getCount(cnn, SQL_TONG_CANTHINANG_TRUOC);   params.add(nhinCanThiTruoc+"");
            int nhinCanThiNay=nhinCanThi0+nhinCanThi18;                     params.add(nhinCanThiNay+"");
            int nhinCanThiLuyKe=nhinCanThiTruoc+nhinCanThiNay;              params.add(nhinCanThiLuyKe+"");
            
            int nhinMu2Mat0=getCount(cnn, SQL_TONG_MU2MAT_0);               params.add(tong==0?nhinMu2Mat0+"":nhinMu2Mat0+"("+getPercent(nhinMu2Mat0, tong)+")");
            int nhinMu2Mat18=getCount(cnn, SQL_TONG_MU2MAT_18);             params.add(tong==0?nhinMu2Mat18+"":nhinMu2Mat18+"("+getPercent(nhinMu2Mat18, tong)+")");
            int nhinMu2MatTruoc=getCount(cnn, SQL_TONG_MU2MAT_TRUOC);       params.add(nhinMu2MatTruoc+"");
            int nhinMu2MatNay=nhinMu2Mat0+nhinMu2Mat18;                     params.add(nhinMu2MatNay+"");
            int nhinMu2MatLuyKe=nhinMu2MatNay+nhinMu2MatTruoc;              params.add(nhinMu2MatLuyKe+"");
            
            int nhinMu1Mat0=getCount(cnn, SQL_TONG_MU1MAT_0);               params.add(tong==0?nhinMu1Mat0+"":nhinMu1Mat0+"("+getPercent(nhinMu1Mat0, tong)+")");
            int nhinMu1Mat18=getCount(cnn, SQL_TONG_MU1MAT_18);             params.add(tong==0?nhinMu1Mat18+"":nhinMu1Mat18+"("+getPercent(nhinMu1Mat18, tong)+")");
            int nhinMu1MatTruoc=getCount(cnn, SQL_TONG_MU1MAT_TRUOC);       params.add(nhinMu1MatTruoc+"");
            int nhinMu1MatNay=nhinMu1Mat0+nhinMu1Mat18;                     params.add(nhinMu1MatNay+"");
            int nhinMu1MatLuyKe=nhinMu1MatNay+nhinMu1MatTruoc;              params.add(nhinMu1MatLuyKe+"");
            
            int nhinMatTTruong0=getCount(cnn, SQL_TONG_MATTHITRUONG_0);     params.add(tong==0?nhinMatTTruong0+"":nhinMatTTruong0+"("+getPercent(nhinMatTTruong0, tong)+")");
            int nhinMatTTruong18=getCount(cnn, SQL_TONG_MATTHITRUONG_18);   params.add(tong==0?nhinMatTTruong18+"":nhinMatTTruong18+"("+getPercent(nhinMatTTruong18, tong)+")");
            int nhinMatTTruongTruoc=getCount(cnn, SQL_TONG_MATTHITRUONG_TRUOC);params.add(nhinMatTTruongTruoc+"");
            int nhinMatTTruongNay=nhinMatTTruong0+nhinMatTTruong18;         params.add(nhinMatTTruongNay+"");
            int nhinMatTTruongLuyKe=nhinMatTTruongNay+nhinMatTTruongTruoc;  params.add(nhinMatTTruongLuyKe+"");
            
            int nhinVienThi0=getCount(cnn, SQL_TONG_VIENTHINANG_0);         params.add(tong==0?nhinVienThi0+"":nhinVienThi0+"("+getPercent(nhinVienThi0, tong)+")");
            int nhinVienThi18=getCount(cnn, SQL_TONG_VIENTHINANG_18);       params.add(tong==0?nhinVienThi18+"":nhinVienThi18+"("+getPercent(nhinVienThi18, tong)+")");
            int nhinVienThiTruoc=getCount(cnn, SQL_TONG_VIENTHINANG_TRUOC); params.add(nhinVienThiTruoc+"");
            int nhinVienThiNay=nhinVienThi0+nhinVienThi18;                  params.add(nhinVienThiNay+"");
            int nhinVienThiLuyKe=nhinVienThiNay+nhinVienThiTruoc;           params.add(nhinVienThiLuyKe+"");
            
            int nhinLacMat0=getCount(cnn, SQL_TONG_LACMAT_0);               params.add(tong==0?nhinLacMat0+"":nhinLacMat0+"("+getPercent(nhinLacMat0, tong)+")");
            int nhinLacMat18=getCount(cnn, SQL_TONG_LACMAT_18);             params.add(tong==0?nhinLacMat18+"":nhinLacMat18+"("+getPercent(nhinLacMat18, tong)+")");
            int nhinLacMatTruoc=getCount(cnn, SQL_TONG_LACMAT_TRUOC);       params.add(nhinLacMatTruoc+"");
            int nhinLacMatNay=nhinLacMat0+nhinLacMat18;                     params.add(nhinLacMatNay+"");
            int nhinLacMatLuyKe=nhinLacMatNay+nhinLacMatTruoc;              params.add(nhinLacMatLuyKe+"");
            
            int nhinKhac0=getCount(cnn, SQL_TONG_KK_NHINKHAC_0);            params.add(tong==0?nhinKhac0+"":nhinKhac0+"("+getPercent(nhinKhac0, tong)+")");
            int nhinKhac18=getCount(cnn, SQL_TONG_KK_NHINKHAC_18);          params.add(tong==0?nhinKhac18+"":nhinKhac18+"("+getPercent(nhinKhac18, tong)+")");
            int nhinKhacTruoc=getCount(cnn, SQL_TONG_KK_NHINKHAC_TRUOC);    params.add(nhinKhacTruoc+"");
            int nhinKhacNay=nhinLacMat0+nhinLacMat18;                       params.add(nhinKhacNay+"");
            int nhinKhacLuyKe=nhinLacMatNay+nhinLacMatTruoc;                params.add(nhinKhacLuyKe+"");
            
            int totalNhin=nhinKhacNay+nhinLacMatNay+nhinVienThiNay+nhinMatTTruongNay+nhinMu1MatNay+nhinMu2MatNay+nhinCanThiNay+nhinQuangGaNay+nhinMuMauNay;
            params.add(tong==0?bean.ncrToString("Nh&#236;n: ")+totalNhin+"":bean.ncrToString("Nh&#236;n: ")+totalNhin+"("+getPercent(totalNhin, tong)+")");

            // Tri tue, Nhan thuc
            int triTue0=getCount(cnn,SQL_TONG_TRITUE_0);            params.add(tong==0?triTue0+"":triTue0+"("+getPercent(triTue0, tong)+")");
            int triTue18=getCount(cnn,SQL_TONG_TRITUE_18);          params.add(tong==0?triTue18+"":triTue18+"("+getPercent(triTue18, tong)+")");
            int triTueTruoc=getCount(cnn,SQL_TONG_TRITUE_TRUOC);    params.add(triTueTruoc+"");
            int triTueNay=triTue0+triTue18;                         params.add(triTueNay+"");
            int triTueLuyKe=triTueTruoc+triTueNay;                  params.add(triTueLuyKe+"");
            
            int down0=getCount(cnn,SQL_TONG_DOWN_0);                params.add(tong==0?down0+"":down0+"("+getPercent(down0, tong)+")");
            int down18=getCount(cnn,SQL_TONG_DOWN_18);              params.add(tong==0?down18+"":down18+"("+getPercent(down18, tong)+")");
            int downTruoc=getCount(cnn,SQL_TONG_DOWN_TRUOC);        params.add(downTruoc+"");
            int downNay=down0+down18;                               params.add(downNay+"");
            int downLuyKe=downTruoc+downNay;                        params.add(downLuyKe+"");    
            
            int soLuong_nhanThuc=triTueNay+downNay;
            params.add(tong==0?bean.ncrToString("Tr&#237; tu&#7879;, nh&#7853;n th&#7913;c: ")+soLuong_nhanThuc+""
                            :bean.ncrToString("Tr&#237; tu&#7879;, nh&#7853;n th&#7913;c: ")+soLuong_nhanThuc+"("+getPercent(soLuong_nhanThuc, tong)+")");
        
            // Than kinh, Tam than
            int tuKy0=getCount(cnn,SQL_TONG_TUKY_0);                params.add(tong==0?tuKy0+"":tuKy0+"("+getPercent(tuKy0, tong)+")");
            int tuKy18=getCount(cnn,SQL_TONG_TUKY_18);              params.add(tong==0?tuKy18+"":tuKy18+"("+getPercent(tuKy18, tong)+")");
            int tuKyTruoc=getCount(cnn,SQL_TONG_TUKY_TRUOC);        params.add(tuKyTruoc+"");
            int tuKyNay=tuKy0+tuKy18;                               params.add(tuKyNay+"");
            int tuKyLuyKe=tuKyTruoc+tuKyNay;                        params.add(tuKyLuyKe+"");
            
            int tamThan0=getCount(cnn,SQL_TONG_TAMTHAN_0);          params.add(tong==0?tamThan0+"":tamThan0+"("+getPercent(tamThan0, tong)+")");
            int tamThan18=getCount(cnn,SQL_TONG_TAMTHAN_18);        params.add(tong==0?tamThan18+"":tamThan18+"("+getPercent(tamThan18, tong)+")");
            int tamThanTruoc=getCount(cnn,SQL_TONG_TAMTHAN_TRUOC);  params.add(tamThanTruoc+"");
            int tamThanNay=tamThan0+tamThan18;                      params.add(tamThanNay+"");
            int tamThanLuyKe=tamThanTruoc+tamThanNay;               params.add(tamThanLuyKe+"");
            
            int soLuong_hanhVi=tuKyNay+tamThanNay;
            params.add(tong==0?bean.ncrToString("Th&#7847;n kinh, t&#226;m th&#7847;n: ")+soLuong_hanhVi+""
                                :bean.ncrToString("Th&#7847;n kinh, t&#226;m th&#7847;n: ")+soLuong_hanhVi+"("+getPercent(soLuong_hanhVi, tong)+")");
            
            // Khuyet Tat Khac
            int dongKinh0=getCount(cnn,SQL_TONG_DONGKINH_0);                params.add(tong==0?dongKinh0+"":dongKinh0+"("+getPercent(dongKinh0, tong)+")");
            int dongKinh18=getCount(cnn,SQL_TONG_DONGKINH_18);              params.add(tong==0?dongKinh18+"":dongKinh18+"("+getPercent(dongKinh18, tong)+")");
            int dongKinhTruoc=getCount(cnn,SQL_TONG_DONGKINH_TRUOC);        params.add(dongKinhTruoc+"");
            int dongKinhNay=dongKinh0+dongKinh18;                           params.add(dongKinhNay+"");
            int dongKinhLuyKe=dongKinhTruoc+dongKinhNay;                    params.add(dongKinhLuyKe+"");
            
            int timBamSinh0=getCount(cnn,SQL_TONG_TIMBAMSINH_0);            params.add(tong==0?timBamSinh0+"":timBamSinh0+"("+getPercent(timBamSinh0, tong)+")");
            int timBamSinh18=getCount(cnn,SQL_TONG_TIMBAMSINH_18);          params.add(tong==0?timBamSinh18+"":timBamSinh18+"("+getPercent(timBamSinh18, tong)+")");
            int timBamSinhTruoc=getCount(cnn,SQL_TONG_TIMBAMSINH_TRUOC);    params.add(timBamSinhTruoc+"");
            int timBamSinhNay=timBamSinh0+timBamSinh18;                     params.add(timBamSinhNay+"");
            int timBamSinhLuyKe=timBamSinhTruoc+timBamSinhNay;              params.add(timBamSinhLuyKe+"");
            
            int giamChucNang0=getCount(cnn,SQL_TONG_GIAMCHUCNANG_0);        params.add(tong==0?giamChucNang0+"":giamChucNang0+"("+getPercent(giamChucNang0, tong)+")");
            int giamChucNang18=getCount(cnn,SQL_TONG_GIAMCHUCNANG_18);      params.add(tong==0?giamChucNang18+"":giamChucNang18+"("+getPercent(giamChucNang18, tong)+")");
            int giamChucNangTruoc=getCount(cnn,SQL_TONG_GIAMCHUCNANG_TRUOC); params.add(giamChucNangTruoc+"");
            int giamChucNangNay=giamChucNang0+giamChucNang18;               params.add(giamChucNangNay+"");
            int giamChucNangLuyKe=giamChucNangTruoc+giamChucNangNay;        params.add(giamChucNangLuyKe+"");
            
            int manTinh0=getCount(cnn,SQL_TONG_MANTINH_0);                  params.add(tong==0?manTinh0+"":manTinh0+"("+getPercent(manTinh0, tong)+")");
            int manTinh18=getCount(cnn,SQL_TONG_MANTINH_18);                params.add(tong==0?manTinh18+"":manTinh18+"("+getPercent(manTinh18, tong)+")");
            int manTinhTruoc=getCount(cnn,SQL_TONG_MANTINH_TRUOC);          params.add(manTinhTruoc+"");
            int manTinhNay=manTinh0+manTinh18;                              params.add(manTinhNay+"");
            int manTinhLuyKe=manTinhTruoc+manTinhNay;                       params.add(manTinhLuyKe+"");
            
            int cacTatKhac0=getCount(cnn,SQL_TONG_CACTATKHAC_0);            params.add(tong==0?cacTatKhac0+"":cacTatKhac0+"("+getPercent(cacTatKhac0, tong)+")");
            int cacTatKhac18=getCount(cnn,SQL_TONG_CACTATKHAC_18);          params.add(tong==0?cacTatKhac18+"":cacTatKhac18+"("+getPercent(cacTatKhac18, tong)+"%)");
            int cacTatKhacTruoc=getCount(cnn,SQL_TONG_CACTATKHAC_TRUOC);    params.add(cacTatKhacTruoc+"");
            int cacTatKhacNay=cacTatKhac0+cacTatKhac18;                     params.add(cacTatKhacNay+"");
            int cacTatKhacLuyKe=cacTatKhacTruoc+cacTatKhacNay;              params.add(cacTatKhacLuyKe+"");
            
            int soLuong_khuyetTatKhac=dongKinhNay+timBamSinhNay+giamChucNangNay+manTinhNay+cacTatKhacNay;
            params.add(tong==0?bean.ncrToString("Khuy&#7871;t t&#7853;t kh&#225;c: ")+soLuong_khuyetTatKhac+""
                       :bean.ncrToString("Khuy&#7871;t t&#7853;t kh&#225;c: ")+soLuong_khuyetTatKhac+"("+getPercent(soLuong_khuyetTatKhac, tong)+")");
        
            //ho tro
            int hoTroDungCu0=getCount(cnn,SQL_HOTRO_DUNGCU_0);      params.add(tong==0?hoTroDungCu0+"":hoTroDungCu0+"("+getPercent(hoTroDungCu0, tong)+")");
            int hoTroDungCu18=getCount(cnn,SQL_HOTRO_DUNGCU_18);    params.add(tong==0?hoTroDungCu18+"":hoTroDungCu18+"("+getPercent(hoTroDungCu18, tong)+")");
            int hoTroDungCu=getCount(cnn,SQL_HOTRO_DUNGCU);         params.add(hoTroDungCu+"");
            int hoTroDungCuNay=hoTroDungCu0+hoTroDungCu18;          params.add(hoTroDungCuNay+"");
            int hoTroDungCuLuyKe=hoTroDungCu+hoTroDungCuNay;        params.add(hoTroDungCuLuyKe+"");
            
            params.add(tong==0?bean.ncrToString("S&#7889; NKT c&#7847;n h&#7895; tr&#7907; d&#7909;ng c&#7909;: ")+hoTroDungCuNay+""
                            :bean.ncrToString("S&#7889; NKT c&#7847;n h&#7895; tr&#7907; d&#7909;ng c&#7909;: ")+hoTroDungCuNay+"("+getPercent(hoTroDungCuNay, tong)+")");
            
            int duocHoTroDungCu0=getCount(cnn,SQL_HOTRO_DUOC_DUNGCU_0);     params.add(hoTroDungCu0==0?duocHoTroDungCu0+"":duocHoTroDungCu0+"("+getPercent(duocHoTroDungCu0, hoTroDungCu0)+")");
            int duocHoTroDungCu18=getCount(cnn,SQL_HOTRO_DUOC_DUNGCU_18);   params.add(hoTroDungCu18==0?duocHoTroDungCu18+"":duocHoTroDungCu18+"("+getPercent(duocHoTroDungCu18, hoTroDungCu18)+")");
            int duocHoTroDungCu=getCount(cnn,SQL_HOTRO_DUOC_DUNGCU);        params.add(duocHoTroDungCu+"");
            int duocHoTroDungCuNay=duocHoTroDungCu0+duocHoTroDungCu18;      params.add(duocHoTroDungCuNay+"");
            int duocHoTroDungCuLuyKe=duocHoTroDungCu+duocHoTroDungCuNay;    params.add(duocHoTroDungCuLuyKe+"");
            
            params.add(hoTroDungCuNay==0?bean.ncrToString("T&#7893;ng s&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c h&#7895; tr&#7907; d&#7909;ng c&#7909;: ")+duocHoTroDungCuNay+""
                                        :bean.ncrToString("T&#7893;ng s&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c h&#7895; tr&#7907; d&#7909;ng c&#7909;: ")+duocHoTroDungCuNay+"("+getPercent(duocHoTroDungCuNay, hoTroDungCuNay)+")");
            
            //nhu cau ho tro
            int hoTroThanhSongSong0=getCount(cnn,SQL_HOTRO_THANHSONGSONG_0);        params.add(hoTroThanhSongSong0==0?hoTroThanhSongSong0+"":hoTroThanhSongSong0+"("+getPercent(hoTroThanhSongSong0, hoTroDungCu0)+")");
            int hoTroThanhSongSong18=getCount(cnn,SQL_HOTRO_THANHSONGSONG_18);      params.add(hoTroThanhSongSong18==0?hoTroThanhSongSong18+"":hoTroThanhSongSong18+"("+getPercent(hoTroThanhSongSong18, hoTroDungCu18)+")");
            int hoTroThanhSongSong=getCount(cnn,SQL_HOTRO_THANHSONGSONG);           params.add(hoTroThanhSongSong+"");
            int hoTroThanhSongSongNay=hoTroThanhSongSong0+hoTroThanhSongSong18;     params.add(hoTroThanhSongSongNay+"");
            int hoTroThanhSongSongLuyKe=hoTroThanhSongSong+hoTroThanhSongSongNay;   params.add(hoTroThanhSongSongLuyKe+"");
                          
            int hoTroKTD0=getCount(cnn,SQL_HOTRO_KHUNGTAPDI_0);                     params.add(hoTroDungCu0==0?hoTroKTD0+"":hoTroKTD0+"("+getPercent(hoTroKTD0, hoTroDungCu0)+")");
            int hoTroKTD18=getCount(cnn,SQL_HOTRO_KHUNGTAPDI_18);                   params.add(hoTroDungCu18==0?hoTroKTD18+"":hoTroKTD18+"("+getPercent(hoTroKTD18, hoTroDungCu18)+")");
            int hoTroKTD=getCount(cnn,SQL_HOTRO_KHUNGTAPDI);                        params.add(hoTroKTD+"");
            int hoTroKTDNay=hoTroKTD0+hoTroKTD18;                                   params.add(hoTroKTDNay+"");
            int hoTroKTDLuyKe=hoTroKTD+hoTroKTDNay;                                 params.add(hoTroKTDLuyKe+"");
            
            int hoTroNang0=getCount(cnn,SQL_HOTRO_NANG_0);                          params.add(hoTroNang0==0?hoTroNang0+"":hoTroNang0+"("+getPercent(hoTroNang0, hoTroDungCu0)+")");
            int hoTroNang18=getCount(cnn,SQL_HOTRO_NANG_18);                        params.add(hoTroDungCu18==0?hoTroNang18+"":hoTroNang18+"("+getPercent(hoTroNang18, hoTroDungCu18)+")");
            int hoTroNang=getCount(cnn,SQL_HOTRO_NANG);                             params.add(hoTroNang+"");
            int hoTroNangNay=hoTroNang0+hoTroNang18;                                params.add(hoTroNangNay+"");
            int hoTroNangLuyKe=hoTroNang+hoTroNangNay;                              params.add(hoTroNangLuyKe+"");
            
            int hoTroXeLan0=getCount(cnn,SQL_HOTRO_XELAN_0);                        params.add(hoTroDungCu0==0?hoTroXeLan0+"":hoTroXeLan0+"("+getPercent(hoTroXeLan0, hoTroDungCu0)+")");
            int hoTroXeLan18=getCount(cnn,SQL_HOTRO_XELAN_18);                      params.add(hoTroDungCu18==0?hoTroXeLan18+"":hoTroXeLan18+"("+getPercent(hoTroXeLan18, hoTroDungCu18)+")");
            int hoTroXeLan=getCount(cnn,SQL_HOTRO_XELAN);                           params.add(hoTroXeLan+"");
            int hoTroXeLanNay=hoTroXeLan0+hoTroXeLan18;                             params.add(hoTroXeLanNay+"");
            int hoTroXeLanLuyKe=hoTroXeLan+hoTroXeLanNay;                           params.add(hoTroXeLanLuyKe+"");
            
            int hoTroGheBN0=getCount(cnn,SQL_HOTRO_GHEBAINAO_0);                    params.add(hoTroGheBN0==0?hoTroGheBN0+"":hoTroGheBN0+"("+getPercent(hoTroGheBN0, hoTroDungCu0)+")");
            int hoTroGheBN18=getCount(cnn,SQL_HOTRO_GHEBAINAO_18);                  params.add(hoTroGheBN18==0?hoTroGheBN18+"":hoTroGheBN18+"("+getPercent(hoTroGheBN18, hoTroDungCu18)+")");
            int hoTroGheBN=getCount(cnn,SQL_HOTRO_GHEBAINAO);                       params.add(hoTroGheBN+"");
            int hoTroGheBNNay=hoTroGheBN0+hoTroGheBN18;params.add(hoTroGheBNNay+"");
            int hoTroGheBNLuyKe=hoTroGheBN+hoTroGheBNNay;params.add(hoTroGheBNLuyKe+"");
            
            int hoTroBanDung0=getCount(cnn,SQL_HOTRO_BANDUNG_0); params.add(hoTroBanDung0+hoTroDungCu0==0?hoTroBanDung0+"":hoTroBanDung0+"("+getPercent(hoTroBanDung0, hoTroDungCu0)+")");
            int hoTroBanDung18=getCount(cnn,SQL_HOTRO_BANDUNG_18); params.add(hoTroDungCu18==0?hoTroBanDung18+"":hoTroBanDung18+"("+getPercent(hoTroBanDung18, hoTroDungCu18)+")");
            int hoTroBanDung=getCount(cnn,SQL_HOTRO_BANDUNG); params.add(hoTroBanDung+"");
            int hoTroBanDungNay=hoTroBanDung0+hoTroBanDung18;params.add(hoTroBanDungNay+"");
            int hoTroBanDungLuyKe=hoTroBanDung+hoTroBanDungNay;params.add(hoTroBanDungLuyKe+"");
            
            int hoTroNep0=getCount(cnn,SQL_HOTRO_NEP_0); params.add(hoTroDungCu0==0?hoTroNep0+"":hoTroNep0+"("+getPercent(hoTroNep0, hoTroDungCu0)+")");
            int hoTroNep18=getCount(cnn,SQL_HOTRO_NEP_18); params.add(hoTroDungCu18==0?hoTroNep18+"":hoTroNep18+"("+getPercent(hoTroNep18, hoTroDungCu18)+")");
            int hoTroNep=getCount(cnn,SQL_HOTRO_NEP); params.add(hoTroNep+"");
            int hoTroNepNay=hoTroNep0+hoTroNep18;params.add(hoTroNepNay+"");
            int hoTroNepLuyKe=hoTroNep+hoTroNepNay;params.add(hoTroNepLuyKe+"");
            
            int hoTroMayTT0=getCount(cnn,SQL_HOTRO_MAYTROTHINH_0); params.add(hoTroDungCu0==0?hoTroMayTT0+"":hoTroMayTT0+"("+getPercent(hoTroMayTT0, hoTroDungCu0)+")");
            int hoTroMayTT18=getCount(cnn,SQL_HOTRO_MAYTROTHINH_18); params.add(hoTroDungCu18==0?hoTroMayTT18+"":hoTroMayTT18+"("+getPercent(hoTroMayTT18, hoTroDungCu18)+")");
            int hoTroMayTT=getCount(cnn,SQL_HOTRO_MAYTROTHINH); params.add(hoTroMayTT+"");
            int hoTroMayTTNay=hoTroMayTT0+hoTroMayTT18;params.add(hoTroMayTTNay+"");
            int hoTroMayTTLuyKe=hoTroMayTT+hoTroMayTTNay;params.add(hoTroMayTTLuyKe+"");
            
            // Add - Chan gia
            int hoTroChanGia0=getCount(cnn,SQL_HOTRO_CHANGIA_0);        params.add(hoTroDungCu0==0?hoTroChanGia0+"":hoTroChanGia0+"("+getPercent(hoTroChanGia0, hoTroDungCu0)+")");
            int hoTroChanGia18=getCount(cnn,SQL_HOTRO_CHANGIA_18);      params.add(hoTroDungCu18==0?hoTroChanGia18+"":hoTroChanGia18+"("+getPercent(hoTroChanGia18, hoTroDungCu18)+")");
            int hoTroChanGia=getCount(cnn,SQL_HOTRO_CHANGIA);           params.add(hoTroChanGia+"");
            int hoTroChanGiaNay=hoTroChanGia0+hoTroChanGia18;           params.add(hoTroChanGiaNay+"");
            int hoTroChanGiaLuyKe=hoTroChanGia+hoTroChanGiaNay;         params.add(hoTroChanGiaLuyKe+"");    
            
            // - Tay gia
            int hoTroTayGia0=getCount(cnn,SQL_HOTRO_TAYGIA_0);          params.add(hoTroDungCu0==0?hoTroTayGia0+"":hoTroTayGia0+"("+getPercent(hoTroTayGia0, hoTroDungCu0)+")");
            int hoTroTayGia18=getCount(cnn,SQL_HOTRO_TAYGIA_18);        params.add(hoTroDungCu18==0?hoTroTayGia18+"":hoTroTayGia18+"("+getPercent(hoTroTayGia18, hoTroDungCu18)+")");
            int hoTroTayGia=getCount(cnn,SQL_HOTRO_TAYGIA);             params.add(hoTroTayGia+"");
            int hoTroTayGiaNay=hoTroMayTT0+hoTroMayTT18;                params.add(hoTroTayGiaNay+"");
            int hoTroTayGiaLuyKe=hoTroMayTT+hoTroMayTTNay;              params.add(hoTroTayGiaLuyKe+"");    
            
            // - Mat gia
            int hoTroMatGia0=getCount(cnn,SQL_HOTRO_MATGIA_0);          params.add(hoTroDungCu0==0?hoTroMatGia0+"":hoTroMatGia0+"("+getPercent(hoTroMatGia0, hoTroDungCu0)+")");
            int hoTroMatGia18=getCount(cnn,SQL_HOTRO_MATGIA_18);        params.add(hoTroDungCu18==0?hoTroMatGia18+"":hoTroMatGia18+"("+getPercent(hoTroMatGia18, hoTroDungCu18)+")");
            int hoTroMatGia=getCount(cnn,SQL_HOTRO_MATGIA);             params.add(hoTroMatGia+"");
            int hoTroMatGiaNay=hoTroMatGia0+hoTroMatGia18;              params.add(hoTroMatGiaNay+"");
            int hoTroMatGiaLuyKe=hoTroMatGia+hoTroMatGiaNay;            params.add(hoTroMatGiaLuyKe+"");    
            
            // - Xe lac			
            int hoTroXeLac0=getCount(cnn,SQL_HOTRO_XELAC_0);            params.add(hoTroXeLac0==0?hoTroXeLac0+"":hoTroXeLac0+"("+getPercent(hoTroXeLac0, hoTroDungCu0)+")");
            int hoTroXeLac18=getCount(cnn,SQL_HOTRO_XELAC_18);          params.add(hoTroXeLac18==0?hoTroXeLac18+"":hoTroXeLac18+"("+getPercent(hoTroXeLac18, hoTroDungCu18)+")");
            int hoTroXeLac=getCount(cnn,SQL_HOTRO_XELAC);               params.add(hoTroXeLac+"");
            int hoTroXeLacNay=hoTroXeLac0+hoTroXeLac18;                 params.add(hoTroXeLacNay+"");
            int hoTroXeLacLuyKe=hoTroXeLac+hoTroXeLacNay;               params.add(hoTroXeLacLuyKe+"");
            
            // - Ho tro khac    
            int hoTroDCKhac0=getCount(cnn,SQL_HOTRO_DUNGCUKHAC_0);      params.add(hoTroDungCu0==0?hoTroDCKhac0+"":hoTroDCKhac0+"("+getPercent(hoTroDCKhac0, hoTroDungCu0)+")");
            int hoTroDCKhac18=getCount(cnn,SQL_HOTRO_DUNGCUKHAC_18);    params.add(hoTroDungCu18==0?hoTroDCKhac18+"":hoTroDCKhac18+"("+getPercent(hoTroDCKhac18, hoTroDungCu0)+")");
            int hoTroDCKhac=getCount(cnn,SQL_HOTRO_DUNGCUKHAC);         params.add(hoTroDCKhac+"");
            int hoTroDCKhacNay=hoTroDCKhac0+hoTroDCKhac18;              params.add(hoTroDCKhacNay+"");
            int hoTroDCKhacLuyKe=hoTroDCKhac+hoTroDCKhacNay;            params.add(hoTroDCKhacLuyKe+"");
            
            // Ho tro PHCN Tai nha
            int hoTroPHTaiNha0=getCount(cnn,SQL_HOTRO_TAINHA_0);        params.add(tong==0?hoTroPHTaiNha0+"":hoTroPHTaiNha0+"("+getPercent(hoTroPHTaiNha0, tong)+")");
            int hoTroPHTaiNha18=getCount(cnn,SQL_HOTRO_TAINHA_18);      params.add(tong==0?hoTroPHTaiNha18+"":hoTroPHTaiNha18+"("+getPercent(hoTroPHTaiNha18, tong)+")");
            int hoTroPHTaiNha=getCount(cnn,SQL_HOTRO_TAINHA);           params.add(hoTroPHTaiNha+"");
            int hoTroPHTaiNhaNay=hoTroPHTaiNha0+hoTroPHTaiNha18;        params.add(hoTroPHTaiNhaNay+"");
            int hoTroPHTaiNhaLuyKe=hoTroPHTaiNha+hoTroPHTaiNhaNay;      params.add(hoTroPHTaiNhaLuyKe+"");
            
            params.add(tong==0?bean.ncrToString("S&#7889; NKT c&#7847;n PHCN t&#7841;i nh&#224;: ")+hoTroPHTaiNhaNay+""
                                :bean.ncrToString("S&#7889; NKT c&#7847;n PHCN t&#7841;i nh&#224;: ")+hoTroPHTaiNhaNay+"("+getPercent(hoTroPHTaiNha, tong)+")");
            
            int hoTroPHDuocTaiNha0=getCount(cnn,SQL_HOTRO_DUOC_TAINHA_0);       params.add(hoTroPHTaiNha0==0?hoTroPHDuocTaiNha0+"":hoTroPHDuocTaiNha0+"("+getPercent(hoTroPHDuocTaiNha0, hoTroPHTaiNha0)+")");
            int hoTroPHDuocTaiNha18=getCount(cnn,SQL_HOTRO_DUOC_TAINHA_18);     params.add(hoTroPHTaiNha18==0?hoTroPHDuocTaiNha18+"":hoTroPHDuocTaiNha18+"("+getPercent(hoTroPHDuocTaiNha18, hoTroPHTaiNha18)+")");
            int hoTroPHDuocTaiNha=getCount(cnn,SQL_HOTRO_DUOC_TAINHA);          params.add(hoTroPHDuocTaiNha+"");
            int hoTroPHDuocTaiNhaNay=hoTroPHDuocTaiNha0+hoTroPHDuocTaiNha18;    params.add(hoTroPHDuocTaiNhaNay+"");
            int hoTroPHDuocTaiNhaLuyKe=hoTroPHDuocTaiNha+hoTroPHDuocTaiNhaNay;  params.add(hoTroPHDuocTaiNhaLuyKe+"");
            
            params.add(hoTroPHTaiNhaNay==0?bean.ncrToString("S&#7889; NKT &#273;&#432;&#7907;c PHCN t&#7841;i nh&#224;: ")+hoTroPHDuocTaiNhaNay+""
                                            :bean.ncrToString("S&#7889; NKT &#273;&#432;&#7907;c PHCN t&#7841;i nh&#224;: ")+hoTroPHDuocTaiNhaNay+"("+getPercent(hoTroPHDuocTaiNhaNay, hoTroPHTaiNhaNay)+")");
            
            // ho tro nha o
            int hoTroNhaO0=getCount(cnn,SQL_HOTRO_NHAO_0);          params.add(tong==0?hoTroNhaO0+"":hoTroNhaO0+"("+getPercent(hoTroNhaO0, tong)+")");
            int hoTroNhaO18=getCount(cnn,SQL_HOTRO_NHAO_18);        params.add(tong==0?hoTroNhaO18+"":hoTroNhaO18+"("+getPercent(hoTroNhaO18, tong)+")");
            int hoTroNhaO=getCount(cnn,SQL_HOTRO_NHAO);             params.add(hoTroNhaO+"");
            int hoTroNhaONay=hoTroNhaO0+hoTroNhaO18;                params.add(hoTroNhaONay+"");
            int hoTroNhaOLuyKe=hoTroNhaO+hoTroNhaONay;              params.add(hoTroNhaOLuyKe+"");
            params.add(tong==0?bean.ncrToString("S&#7889; NKT c&#7847;n thay &#273;&#7893;i nh&#224; c&#7917;a: ")+hoTroNhaONay+""
                                :bean.ncrToString("S&#7889; NKT c&#7847;n thay &#273;&#7893;i nh&#224; c&#7917;a: ")+hoTroNhaONay+"("+getPercent(hoTroNhaONay, tong)+")");
            
            int hoTroDuocNhaO0=getCount(cnn,SQL_HOTRO_DUOC_NHAO_0);     params.add(hoTroNhaO0==0?hoTroDuocNhaO0+"":hoTroDuocNhaO0+"("+getPercent(hoTroDuocNhaO0, hoTroNhaO0)+")");
            int hoTroDuocNhaO18=getCount(cnn,SQL_HOTRO_DUOC_NHAO_18);   params.add(hoTroNhaO18==0?hoTroDuocNhaO18+"":hoTroDuocNhaO18+"("+getPercent(hoTroDuocNhaO18, hoTroNhaO18)+")");
            int hoTroDuocNhaO=getCount(cnn,SQL_HOTRO_DUOC_NHAO);        params.add(hoTroDuocNhaO+"");
            int hoTroDuocNhaONay=hoTroDuocNhaO0+hoTroDuocNhaO18;        params.add(hoTroDuocNhaONay+"");
            int hoTroDuocNhaOLuyKe=hoTroDuocNhaO+hoTroDuocNhaONay;      params.add(hoTroDuocNhaOLuyKe+"");
            params.add(hoTroNhaONay==0?bean.ncrToString("S&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c thay &#273;&#7893;i nh&#224; c&#7917;a: ")+hoTroDuocNhaONay+""
                                        :bean.ncrToString("S&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c thay &#273;&#7893;i nh&#224; c&#7917;a: ")+hoTroDuocNhaONay+"("+getPercent(hoTroDuocNhaONay, hoTroNhaONay)+")");
            
            //ho tro phau thuat
            int hoTroPhauThuat0=getCount(cnn,SQL_HOTRO_PHAUTHUAT_0);    params.add(tong==0?hoTroPhauThuat0+"":hoTroPhauThuat0+"("+getPercent(hoTroPhauThuat0, tong)+")");
            int hoTroPhauThuat18=getCount(cnn,SQL_HOTRO_PHAUTHUAT_18);  params.add(tong==0?hoTroPhauThuat18+"":hoTroPhauThuat18+"("+getPercent(hoTroPhauThuat18, tong)+")");
            int hoTroPhauThuat=getCount(cnn,SQL_HOTRO_PHAUTHUAT);       params.add(hoTroPhauThuat+"");
            int hoTroPhauThuatNay=hoTroPhauThuat0+hoTroPhauThuat18;     params.add(hoTroPhauThuatNay+"");
            int hoTroPhauThuatLuyKe=hoTroPhauThuat+hoTroPhauThuatNay;   params.add(hoTroPhauThuatLuyKe+"");
            params.add(tong==0?bean.ncrToString("S&#7889; NKT c&#7847;n &#273;&#432;&#7907;c ph&#7851;u thu&#7853;t: ")+hoTroPhauThuatNay+""
                                :bean.ncrToString("S&#7889; NKT c&#7847;n &#273;&#432;&#7907;c ph&#7851;u thu&#7853;t: ")+hoTroPhauThuatNay+"("+getPercent(hoTroPhauThuatNay, tong)+")");
            
            int hoTroDuocPhauThuat0=getCount(cnn,SQL_HOTRO_DUOC_PHAUTHUAT_0);       params.add(hoTroPhauThuat0==0?hoTroDuocPhauThuat0+"":hoTroDuocPhauThuat0+"("+getPercent(hoTroDuocPhauThuat0, hoTroPhauThuat0)+")");
            int hoTroDuocPhauThuat18=getCount(cnn,SQL_HOTRO_DUOC_PHAUTHUAT_18);     params.add(hoTroPhauThuat18==0?hoTroDuocPhauThuat18+"":hoTroDuocPhauThuat18+"("+getPercent(hoTroDuocPhauThuat18, hoTroPhauThuat18)+")");
            int hoTroDuocPhauThuat=getCount(cnn,SQL_HOTRO_DUOC_PHAUTHUAT);          params.add(hoTroDuocPhauThuat+"");
            int hoTroDuocPhauThuatNay=hoTroDuocPhauThuat0+hoTroDuocPhauThuat18;     params.add(hoTroDuocPhauThuatNay+"");
            int hoTroDuocPhauThuatLuyKe=hoTroDuocPhauThuat+hoTroDuocPhauThuatNay;   params.add(hoTroDuocPhauThuatLuyKe+"");
            params.add(hoTroPhauThuatNay==0?bean.ncrToString("S&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c ph&#7851;u thu&#7853;t: ")+hoTroDuocPhauThuatNay+""
                                            :bean.ncrToString("S&#7889; NKT &#273;&#227; &#273;&#432;&#7907;c ph&#7851;u thu&#7853;t: ")+hoTroDuocPhauThuatNay+"("+getPercent(hoTroDuocPhauThuatNay, hoTroPhauThuatNay)+")");
            
            //tiep can giao duc
            int tiepCanGD=getCount(cnn,SQL_TIEPCAN_GIAODUC);                params.add(tong_3_18==0?tiepCanGD+"":tiepCanGD+"("+ getPercent(tiepCanGD, tong_3_18)+")");
            int hoTroLamAn18=getCount(cnn,SQL_HOTRO_LAMAN_18);              params.add(tong_18_60==0?hoTroLamAn18+"":hoTroLamAn18+"("+getPercent(hoTroLamAn18, tong_18_60)+")");
            int hoTroDuocLamAn18=getCount(cnn,SQL_HOTRO_DUOC_LAMAN_18);     params.add(hoTroLamAn18==0?hoTroDuocLamAn18+"":hoTroDuocLamAn18+"("+getPercent(hoTroDuocLamAn18, hoTroLamAn18)+")");
            
            //Tu Kiem Song + Vo Chong
            int tuKiemSong=getCount(cnn,SQL_TUKIEMSONG);    params.add(tong==0?tuKiemSong+"":tuKiemSong+"("+getPercent(tuKiemSong, tong_18_60)+")");            
            int qhVoChong=getCount(cnn,SQL_QHVOCHONG);      params.add(tong_18_60==0?qhVoChong+"":qhVoChong+"("+getPercent(qhVoChong, tong_18_60)+")");
            int toChucXH=getCount(cnn,SQL_TOCHUCXH);        params.add(tong_18_60==0?toChucXH+"":toChucXH+"("+getPercent(toChucXH, tong_18_60)+")");
            int nhomTuLuc=getCount(cnn,SQL_NHOMTULUC);      params.add(tong_18_60==0?nhomTuLuc+"":nhomTuLuc+"("+getPercent(nhomTuLuc, tong_18_60)+")");
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

    public int getCount(Connection cnn,String sql) throws EException
    {
        final String LOCATION = this.toString() + "getAll()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        int result=0;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn,sql,params);
            rs = prstm.executeQuery();
            if(rs != null && rs.next()){
                    result=rs.getInt(1);       
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
        return result;        
    }
    
    public String getPercent(int input, int total) {
        DecimalFormat df = new DecimalFormat("0.00");
        String percent = "0%";
        float fPercent = 0;
        if(total!=0)
            fPercent = (float)input/(float)total*100;
            
        percent = String.valueOf((df.format(fPercent))) + "%";        
        return percent;        
    }
}
