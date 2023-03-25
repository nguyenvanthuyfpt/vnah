-- public.dr_report_param definition

-- Drop table

-- DROP TABLE public.dr_report_param;

CREATE TABLE public.dr_report_param (
	stt varchar(100) NULL,
	location_id varchar(50) NULL,
	location_name varchar(100) NULL,
	qhu_id varchar(50) null,
	qhu_name varchar(100) null,
	create_date varchar(12) NULL,
	nkt_id int8 NULL,
	nkt_stt varchar(50) NULL,
	nkt_ten varchar(100) NULL,
	nkt_maso varchar(100) NULL,
	nkt_ngaysinh varchar(50) NULL,
	nkt_cmnd varchar(50) NULL,
	nkt_sdt varchar(50) NULL,
	nkt_dantoc varchar(50) NULL,
	nkt_gioitinh varchar(50) NULL,
	nkt_dacam varchar(10) NULL,
	nkt_nnghiep varchar(50) NULL,
	nkt_ttrang_hso varchar(50) NULL,
	nkt_diachi varchar(1000) NULL,
	nkt_namsinh varchar(10) NULL,
	nkt_ngaydong_hs varchar(100) NULL,
	nkt_lydo_donghs varchar(500) NULL,
	nkt_nguoi_donghs varchar(100) null,
	
	ncs_ten varchar(100) NULL,
	ncs_namsinh varchar(50) NULL,
	ncs_qhe_nkt varchar(50) NULL,
	ncs_dthoai varchar(50) NULL,
	ncs_gioitinh varchar(5) NULL,
	
	dtat_createdate varchar(12) NULL,
	dtat_ten varchar(1000) NULL,
	dtat_ngay_kham varchar(12) NULL,
	dtat_ddiem_kham varchar(100) NULL,
	dtat_tdiem_ktat varchar(10) NULL,
	dtat_ttrang_ktat varchar(500) NULL,
	dtat_nnhan_ktat varchar(100) NULL,
	dtat_mdo_ktat varchar(100) NULL,
	
	ncau_createdate varchar(12) NULL,
	ncau_ten varchar(1000) NULL,
	ncau_dungcu_khac varchar(1000) NULL,
	
	htro_createdate varchar(12) NULL,
	htro_ten varchar(1000) NULL,
	htro_tgian_nhan varchar(100) NULL,
	htro_nguon_htro varchar(1000) NULL,
	htro_kn_chitra varchar(100) NULL,
	htro_the_bhyt varchar(100) NULL,
	htro_sd_bhyt varchar(100) NULL,
	htro_the_phcn varchar(100) NULL,
	htro_mtieu_gdinh text NULL,
	htro_mtieu_dtri text NULL,
	htro_ct_vltl text NULL,
	htro_ct_hdtl text NULL,
	htro_ct_antl text NULL,
	htro_mdo_dlpt varchar(500) NULL,
	htro_mdo_hlong varchar(500) NULL,
	htro_dungcu_khac varchar(500) NULL,
	htro_nhao varchar(500) null,
	htro_ctvs varchar(500) null,
	
	htro_phcn_canthiep varchar(500) NULL,
	htro_phcn_dungcu varchar(500) NULL,
	htro_tcan_nhao varchar(500) NULL,
	htro_phcn_ngay varchar(50) NULL	
);