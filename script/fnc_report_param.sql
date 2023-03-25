CREATE OR REPLACE FUNCTION public.report_param(text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
   DECLARE
	v_whe text := '';
	v_sql text := '';
	v_sel text := '';
	v_ins text := '';
	v_support_id text :='';
	v_phcn_dungcu text := '';

	-- cursDangTat
	cursDangTat CURSOR FOR SELECT pl.id, pl.id_nkt, to_char(pl.datecreate, 'dd/MM/YYYY') as dtat_createdate, 
		get_dangtat(pl.id, pl.id_nkt) AS dtat_ten, to_char(pl.thoidiem_taikham, 'dd/MM/YYYY') as dtat_ngay_kham,
		CASE WHEN pl.diadiem_kham=3 THEN 'Tại trạm Y tế Xã/Phường' 
		WHEN pl.diadiem_kham=2 THEN 'Tại BV Tỉnh/Huyện' 
		WHEN pl.diadiem_kham=1 THEN 'Tại nhà' 
		WHEN pl.diadiem_kham=4 THEN 'Khác' 
		ELSE '' END as dtat_ddiem_kham,
		pl.thoidiem_kt as dtat_tdiem_ktat,
		pl.reson as dtat_ttrang_ktat,
		dm.name as dtat_nnhan_ktat,
		CASE WHEN pl.mucdo_id=5 THEN 'Đặc biệt nặng' 
		WHEN pl.mucdo_id=4 THEN 'Nặng' 
		WHEN pl.mucdo_id=3 THEN 'Nhẹ' 
		WHEN pl.mucdo_id=2 THEN 'Không xác định' 
		ELSE '' END dtat_mdo_ktat
		FROM dr_phanloai pl INNER JOIN dr_report_param rpt ON pl.id_nkt = rpt.nkt_id
		LEFT JOIN dr_nguyennhan dm ON pl.nguyennhan_id = dm.nguyennhan_id;

	--cursHoTro CURSOR FOR SELECT 
	cursSupport CURSOR(p_status INTEGER) FOR 
	SELECT a.id_nkt, a.datecreate, a.datefrom, a.dateto, a.nguonhotro, a.nguonhotro_id, a.dungcu_khac,
	a.ketqua, CASE WHEN a.kn_chitra = '3' THEN 'Không'
		       WHEN a.kn_chitra = '2' THEN 'Toàn bộ'
		       WHEN a.kn_chitra = '1' THEN 'Một phần'
		       ELSE '' END as kn_chitra,
		  CASE WHEN a.the_bhyt = '1' THEN 'Có'		       
		       ELSE 'Không' END as the_bhyt,
		  CASE WHEN a.sd_the = '2' THEN 'Tập PHCN'
		       WHEN a.sd_the = '1' THEN 'Khám chữa bệnh thông thường'
		       ELSE '' END as sd_the,
		  CASE WHEN a.sd_the_phcn = '3' THEN 'Không biết'
		       WHEN a.sd_the_phcn = '3' THEN 'NKT trả toàn bộ'
		       WHEN a.sd_the_phcn = '2' THEN 'NKT trả một phần'
		       WHEN a.sd_the_phcn = '1' THEN 'BHYT trả toàn bộ'
		       ELSE '' END as sd_the_phcn,	
	 a.mtieu_gdtinh, a.mtieu_dtri, a.ct_vltl, a.ct_hdtl, a.ct_antl, a.mdo_ptdl, a.mdo_hlong,
	 a.support_id, a.support_name
	 FROM (
		SELECT sp.id_nkt, max(sp.datecreate) datecreate , max(dateform) datefrom, max(dateto) dateto,
		max(nguonhotro) nguonhotro, max(nguonhotro_id) nguonhotro_id, max(dungcu_khac) dungcu_khac, 
		max(ketqua) ketqua, max(kn_chitra) kn_chitra, max(the_bhyt) the_bhyt, max(sd_the) sd_the,
		max(sd_the_phcn) sd_the_phcn, max(mtieu_gdinh) mtieu_gdtinh, max(mtieu_dtri) mtieu_dtri, 
		max(ct_vltl) ct_vltl, max(ct_hdtl) ct_hdtl, max(ct_antl) ct_antl, max(mdo_ptdl) mdo_ptdl, max(mdo_hlong) mdo_hlong,
		'#'||array_to_string(array_agg(DISTINCT sp.dm_hotro_ids),'#')||'#' AS support_id,
		array_to_string(array_agg(DISTINCT dm.name),', ') AS support_name
		FROM dr_support sp 
		INNER JOIN dr_report_param rpt ON sp.id_nkt = rpt.nkt_id 
		LEFT JOIN dr_hotro dm ON sp.dm_hotro_ids = dm.hotro_id::text
		WHERE 1=1 
		AND sp.status_id = p_status GROUP BY sp.id_nkt, sp.status_id) a 
	ORDER BY a.id_nkt;

	--cursProfile
	cursProfile CURSOR FOR 
	SELECT pro.nkt_id AS nkt_id, MAX(create_on) AS date_close, MAX(ld.name) AS lydo, MAX(pro.create_by) AS nguoidong  FROM kpi_dis_profile pro 
	INNER JOIN dr_report_param rpt ON pro.nkt_id=rpt.nkt_id 
	LEFT JOIN dr_lydo ld ON pro.reson_id=ld.lydo_id
	GROUP BY pro.nkt_id;

	recProfile record;
	recSupport record;
	recDangTat record;
    BEGIN		
	TRUNCATE TABLE DR_REPORT_PARAM;

	-- select * from dr_report_param
	
	-- INSERT
	v_ins := v_ins || 'INSERT INTO dr_report_param(location_id, location_name, qhu_name, create_date, nkt_id, nkt_stt, nkt_ten, nkt_maso, ';
	v_ins := v_ins || 'nkt_ngaysinh, nkt_namsinh, nkt_cmnd, nkt_sdt, nkt_dantoc, nkt_gioitinh, nkt_dacam, nkt_nnghiep, ';
	v_ins := v_ins || 'nkt_ttrang_hso, nkt_diachi, ncs_ten, ncs_namsinh, ncs_qhe_nkt, ncs_dthoai, ncs_gioitinh ';
	v_ins := v_ins || ') ';

	-- SELECT
        v_sel := v_sel || '(SELECT a.id_tinh as location_id, a.name as location_name, a.qhu_name, ';
        v_sel := v_sel || 'to_char(a.date_last_update, ''dd/mm/YYYY'') as create_date, a.id as nkt_id, ';
        v_sel := v_sel || 'a.ma as nkt_stt, a.ten as nkt_ten, a.maso as nkt_maso, to_char(a.ngaysinh, ''dd/mm/YYYY'') as nkt_ngaysinh, ';
        v_sel := v_sel || 'extract(YEAR FROM a.ngaysinh) as nkt_nsinh, a.cmnd as nkt_cmnd, a.dienthoai as nkt_sdt, a.dantoc_name as nkt_dantoc, ';
	v_sel := v_sel || 'CASE WHEN a.sex=1 THEN ''Nam'' ELSE ''Nữ'' END as nkt_gioitinh,';
	v_sel := v_sel || 'CASE WHEN a.dacam=1 THEN ''Có'' ELSE ''Không'' END as nkt_dacam, ';
	v_sel := v_sel || 'CASE WHEN a.nghe_nghiep_ht=1	THEN ''Còn nhỏ'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=2	THEN ''Nội trợ'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=3	THEN ''Nông nghiệp'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=4	THEN ''Công nhân - viên chức'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=5	THEN ''Công nhân'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=6	THEN ''Thợ thủ công'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=7	THEN ''Dịch vụ/Buôn bán'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=8	THEN ''Thất nghiệp'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=9	THEN ''Bệnh tật không làm gì được'' ';
	v_sel := v_sel || 'WHEN a.nghe_nghiep_ht=10	THEN ''Khác'' ';
	v_sel := v_sel || 'ELSE '''' ';
	v_sel := v_sel || 'END as nkt_nnghiep, ';
	v_sel := v_sel || 'CASE WHEN a.trangthai=''1'' THEN ''Đã đóng'' ELSE ''Chưa đóng'' END as nkt_ttrang_hso, a.sonha as nkt_diachi, ';
	v_sel := v_sel || 'a.ten_ncs as ncs_ten, a.namsinh_ncs::text as ncs_namsinh, ';
	v_sel := v_sel || 'CASE WHEN a.quanhe_ncs=1	THEN ''Ông'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=2	THEN ''Bà'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=3	THEN ''Cô'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=4	THEN ''Dì'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=5	THEN ''Chú'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=6	THEN ''Bác'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=7	THEN ''Anh'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=8	THEN ''Chị'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=9	THEN ''Em'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=10	THEN ''Bố'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=11	THEN ''Mẹ'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=12	THEN ''Chồng'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=13	THEN ''Vợ'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=14	THEN ''Khác'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=15	THEN ''Con'' ';
	v_sel := v_sel || 'WHEN a.quanhe_ncs=16	THEN ''Cháu'' ';
	v_sel := v_sel || 'ELSE '''' ';
	v_sel := v_sel || 'END as ncs_qhe_nkt, ';	
	v_sel := v_sel || 'a.sdt_ncs as ncs_dthoai, CASE WHEN a.gioitinh_ncs=1 THEN ''Nam'' ELSE ''Nữ'' END as ncs_gioitinh ';
	v_sel := v_sel || ' ';	
        v_sel := v_sel || 'FROM (';
	v_sql := v_ins || v_sel || $1 || ') a);';
	EXECUTE v_sql;
	RAISE INFO '## v_sel ## %', v_sql;

	-- DangTat
	OPEN cursDangTat;
	LOOP FETCH cursDangTat INTO recDangTat;
		EXIT WHEN NOT FOUND;
		UPDATE dr_report_param 
		SET dtat_createdate = recDangTat.dtat_createdate,
		dtat_ten = recDangTat.dtat_ten,
		dtat_ngay_kham = recDangTat.dtat_ngay_kham,
		dtat_ddiem_kham = recDangTat.dtat_ddiem_kham,
		dtat_tdiem_ktat = recDangTat.dtat_tdiem_ktat,
		dtat_ttrang_ktat = recDangTat.dtat_ttrang_ktat,
		dtat_nnhan_ktat = recDangTat.dtat_nnhan_ktat,
		dtat_mdo_ktat = recDangTat.dtat_mdo_ktat
		WHERE nkt_id = recDangTat.id_nkt;
	END LOOP;
	CLOSE cursDangTat;		
	-- RAISE INFO 'cursDangTat %', v_sql;

	-- NhuCau
	OPEN cursSupport(0);
	LOOP FETCH cursSupport INTO recSupport;
		EXIT WHEN NOT FOUND;
		UPDATE dr_report_param 
		SET ncau_createdate = to_char(recSupport.datecreate, 'dd/mm/YYYY'),
		ncau_ten = recSupport.support_name,
		ncau_dungcu_khac = recSupport.dungcu_khac		
		WHERE nkt_id = recSupport.id_nkt;
	END LOOP;
	CLOSE cursSupport; 

	-- HoTro
	OPEN cursSupport(1);
	LOOP FETCH cursSupport INTO recSupport;
		EXIT WHEN NOT FOUND;
		v_support_id := recSupport.support_id;		
		UPDATE dr_report_param 
		SET htro_createdate = to_char(recSupport.datecreate, 'dd/mm/YYYY'),
		htro_ten = recSupport.support_name,
		htro_tgian_nhan = to_char(recSupport.datefrom, 'dd/mm/YYYY') || ' - ' || to_char(recSupport.dateto,'dd/mm/YYYY'),
		htro_nguon_htro = recSupport.nguonhotro_id,
		htro_kn_chitra = recSupport.kn_chitra, 
		htro_the_bhyt = recSupport.the_bhyt,
		htro_sd_bhyt = recSupport.sd_the,
		htro_the_phcn = recSupport.sd_the_phcn,
		htro_mtieu_gdinh = recSupport.mtieu_gdtinh,
		htro_mtieu_dtri = recSupport.mtieu_dtri,
		htro_ct_vltl = recSupport.ct_vltl,  
		htro_ct_hdtl = recSupport.ct_hdtl,
		htro_ct_antl = recSupport.ct_antl,
		htro_mdo_dlpt = recSupport.mdo_ptdl,
		htro_mdo_hlong = recSupport.mdo_hlong,
		htro_dungcu_khac = recSupport.dungcu_khac,
		htro_phcn_canthiep = get_support_detail(12, ''||v_support_id||''),
		htro_phcn_dungcu = get_support_detail(13, ''||v_support_id||''),
		htro_tcan_nhao = get_support_detail(24, ''||v_support_id||''), 
		htro_phcn_ngay = to_char(recSupport.datecreate, 'dd/mm/YYYY')
		WHERE nkt_id = recSupport.id_nkt;
	END LOOP;
	CLOSE cursSupport;

	-- Profile
	OPEN cursProfile;
	LOOP FETCH cursProfile INTO recProfile;
		EXIT WHEN NOT FOUND;
		RAISE INFO 'rec %', recProfile.nkt_id;
		UPDATE dr_report_param SET nkt_ngaydong_hs = to_char(recProfile.date_close, 'dd/mm/YYYY'),
			nkt_lydo_donghs = recProfile.lydo,
			nkt_nguoi_donghs = recProfile.nguoidong
		WHERE nkt_id=recProfile.nkt_id;
	END LOOP;
	CLOSE cursProfile;
    EXCEPTION WHEN others then 
	raise notice 'EXCEPTION % %', SQLERRM, SQLSTATE; 
    END;
$function$
;
;
