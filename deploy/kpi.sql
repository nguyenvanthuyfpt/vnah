CREATE OR REPLACE FUNCTION public.get_time_homevisit(bigint, date)
 RETURNS varchar
 LANGUAGE plpgsql
AS $function$
    DECLARE
	str_time varchar:='';
   BEGIN
	select 'Từ ' || to_char(min(start_at), 'HH:mm') || ' - đến ' || to_char(max(end_at) , 'HH:mm')
	|| ' ngày ' || to_char(min(start_at), 'dd/MM/yyyy')
	into str_time
	from dr_home_visit where id_nkt = $1
	and DATE_TRUNC('day', start_at) = $2;
	RETURN str_time;
    END;
$function$
;

CREATE OR REPLACE FUNCTION public.get_num_homevisit(bigint)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
	num int:=0;
   BEGIN
	select count(1) into num from dr_disabilitypeople dis, dr_home_visit hs
	where dis.id=hs.id_nkt and dis.id = $1;
	RETURN num;
    END;
$function$
;

alter table dr_home_visit add column rehab int8 default 0;
alter table dr_home_visit add column homecare int8 default 0;

CREATE OR REPLACE FUNCTION public.count_support_where(bigint, date, integer, bigint)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
    --$1: nkt_id,
    --$2: create_date
    --$3: status
    --$4: level
    DECLARE
        retval TEXT := 1;
       	len INT := 1;
        support TEXT := '';          
    BEGIN		
	
	SELECT INTO len max(stt) as total from kpi_v_support where id_nkt = $1 and datecreate  = $2 and status_id = $3; 	
	IF len > 1 THEN
		retval:=len;	
	END IF;
	
	RETURN retval;
    END;
$function$
;
   

CREATE OR REPLACE FUNCTION public.get_support_where(bigint, date, integer, bigint)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
    --$1: nkt_id,
    --$2: create_date
    --$3: status
    --$4: level
    DECLARE
        retval TEXT := '';
       	len INT := 1;
        support TEXT := '';          
    BEGIN		
	
	SELECT INTO support array_to_string(ARRAY(
		SELECT 
			CASE WHEN (a.stt=0 and a.dm_hotro_ids='61') THEN a.dungcu_khac	
				 WHEN (a.stt=0) then a.hotro
				 ELSE (
					CASE WHEN a.num > 1 THEN a.hotro 
						ELSE 
						'Lượt ' || a.stt || ': '|| a.hotro end
					) 
				END FROM  
		(SELECT stt, hotro, dungcu_khac, dm_hotro_ids, row_number() over (PARTITION BY stt ORDER by stt) num
		FROM kpi_v_support data, kpi_v_hotro dm 
		WHERE data.dm_hotro_ids = dm.hotro_id::text AND dm.level>2
		AND (
		(($4=-1) 	
		OR ($4=0) AND (dm.parent_id=12)) -- Tap PHCN
		OR (($4=1) AND (dm.parent_id=302)) -- Can thiep CN
		OR (($4=2) AND (dm.parent_id=13))  -- Dung cu
		OR (($4=3) AND (dm.parent_id=157)) -- CTVS
		)
		AND data.id_nkt=$1 AND data.datecreate=$2 AND data.status_id=$3 ORDER BY data.dm_hotro_ids) a),', ');	
	
	retval:=support;
	
	RETURN retval;
    END;
$function$
;

DROP TABLE public.kpi_report_temp;
CREATE TABLE public.kpi_report_temp (
	stt int8 NULL,
	id int8 NULL,
	tinh_id int8 NULL,
	tinh_name varchar(100) NULL DEFAULT NULL::character varying,
	qhu_id int8 NULL,
	qhu_name varchar(100) NULL DEFAULT NULL::character varying,
	pxa_id int8 NULL,
	pxa_name varchar(100) NULL DEFAULT NULL::character varying,
	maso varchar(100) NULL DEFAULT NULL::character varying,
	ten varchar(200) NULL DEFAULT NULL::character varying,
	year_of_birthday float8 NULL,
	sex text NULL,
	sonha varchar(1000) NULL DEFAULT NULL::character varying,
	dienthoai varchar(100) NULL DEFAULT NULL::character varying,
	create_date text NULL,
	ten_ncs varchar(200) NULL DEFAULT NULL::character varying,
	sdt_ncs varchar(200) NULL DEFAULT NULL::character varying,
	gioitinh_ncs varchar(50) NULL DEFAULT NULL::character varying,
	trangthai varchar(100) NULL DEFAULT NULL::character varying,
	ngay_dong_hs varchar(50) NULL DEFAULT NULL::character varying,
	dang_tat text NULL,
	muc_do varchar(100) NULL,
	ngay_phat_hien_ktat text NULL,
	da_cam text NULL,
	nc_id int4 NULL,
	nhu_cau text NULL,
	nhucau_noi_nhan text NULL,
	nhucau_can_thiep_cn text NULL,
	nhucau_ten_dung_cu text NULL,
	nhucau_cai_thien_ctvs text NULL,
	ngay_nhu_cau varchar(50) NULL,
	ht_id int4 NULL,
	hotro_da_nhan text NULL,
	hotro_noi_nhan text NULL,
	hotro_can_thiep_cn text NULL,
	hotro_ten_dung_cu text NULL,
	hotro_cai_thien_ctvs text NULL,
	ngay_ho_tro varchar(50) NULL,
	hotro_nguon varchar(100) NULL,
	hotro_luot varchar(10) NULL,
	order_by date NULL
);

CREATE INDEX idx_id_report_temp ON public.kpi_report_temp (id);
CREATE INDEX idx_suport_date_report_temp ON public.kpi_report_temp (ngay_ho_tro);

-- Index
CREATE INDEX idx_kpi_data_rank_idx3 ON public.kpi_data_rank (nkt_id, create_date, has_sp);
CREATE INDEX idx_kpi_data_rank_idx2 ON public.kpi_data_rank (nkt_id, create_date);
CREATE INDEX idx_kpi_data_rank_idx1 ON public.kpi_data_rank (nkt_id);
CREATE INDEX idx_kpi_dis_report_idx1 ON public.kpi_dis_report (nkt_id);
CREATE UNIQUE INDEX kpi_data_rank_pkey ON public.kpi_data_rank USING btree (id);

CREATE OR REPLACE FUNCTION public.kpi_gen_data(bigint, bigint, character varying, character varying)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
    DECLARE
        retval TEXT := '';  
        support TEXT := '';
        cur_data CURSOR FOR 
        SELECT dr.id, dr.id_tinh as tinh_id, dr.id_district as qhu_id, dr.id_commune as pxa_id, 
			dr.maso, dr.ten, extract(year from dr.ngaysinh) as year_of_birthday, 
			CASE WHEN dr.sex=0 then 'Nữ' ELSE 'Nam' END AS sex, dr.sonha, dr.dienthoai, 
			to_char(dr.date_last_update, 'dd/MM/yyyy') create_date, 
			dr.ten_ncs, dr.sdt_ncs, 
			CASE WHEN dr.ten_ncs='' THEN '' 
			ELSE 
				CASE WHEN dr.gioitinh_ncs=0 then 'Nữ' ELSE 'Nam' END
			END as gioitinh_ncs, 
			CASE when dr.trangthai = '1' then 'Đã đóng hồ sơ' ELSE 'Chưa đóng hồ sơ' end as trangthai,
			CASE WHEN dr.trangthai = 1 THEN to_char(dis.create_on,'dd/MM/yyyy') ELSE '' END ngay_dong_hs,
			CASE WHEN dr.dacam=1 THEN 'Da cam' ELSE '' END AS da_cam, 
			get_dangtat(dr.id) AS dang_tat, get_mucdo(dr.id) AS muc_do, to_char(pl.datecreate,'dd/MM/yyyy') as ngay_phat_hien_ktat
			FROM dr_disabilitypeople dr 
			LEFT JOIN kpi_v_phanloai pl ON dr.id=pl.id_nkt
			LEFT JOIN kpi_dis_profile dis ON dr.id=dis.nkt_id
		WHERE 1=1 AND (($1=1 AND id_tinh=$2) OR ($1=2 AND id_district=$2) OR ($1=3 AND id_commune=$2) OR ($2=0)) 
		AND (($3='' AND 1=1) OR ($3<>'' AND dr.date_last_update>=to_date($3,'DD/MM/YYYY')))
		AND (($4='' AND 1=1) OR ($4<>'' AND dr.date_last_update<=to_date($4,'DD/MM/YYYY')))
		-- AND dr.id = 502495
		ORDER BY maso;

	cur_temp CURSOR FOR SELECT * FROM kpi_report_temp;
	cur_temp_2020 CURSOR FOR SELECT * FROM kpi_report_temp_2020;	
	
	rec_data RECORD;
	rec_data_2020 RECORD;
	rec_temp RECORD;
	rec_temp_2020 RECORD;

	cur_final cursor for select * from kpi_report_temp;
	rec_final RECORD;

	c int:=0;
	arr_create_date date[];
	len int;
	i int:=1;
	row_ins int:=0;
	v_dgia_gannhat date;
	v_dgia_bandau date;
	v_hotro_gannhat date;
	v_cth_tyle text:='';
	v_int_tyle int:=0;
    BEGIN	
	    TRUNCATE TABLE kpi_report_temp;
		TRUNCATE TABLE kpi_report_temp_2020;
	    
		OPEN cur_data;
		    LOOP
		    FETCH cur_data into rec_data;
			EXIT WHEN NOT FOUND;
			
			INSERT INTO kpi_report_temp(stt, id, tinh_id, qhu_id, pxa_id,  
				    maso, ten, year_of_birthday, sex, sonha, dienthoai ,create_date, ten_ncs, sdt_ncs, 
				    gioitinh_ncs, trangthai, ngay_dong_hs, dang_tat, muc_do, ngay_phat_hien_ktat, da_cam
				    )
			    VALUES (i, rec_data.id, rec_data.tinh_id,rec_data.qhu_id,rec_data.pxa_id, rec_data.maso, 
						rec_data.ten, rec_data.year_of_birthday, rec_data.sex, 
						rec_data.sonha, rec_data.dienthoai, rec_data.create_date, rec_data.ten_ncs, rec_data.sdt_ncs, 
						rec_data.gioitinh_ncs, rec_data.trangthai, rec_data.ngay_dong_hs, 
						rec_data.dang_tat, rec_data.muc_do, rec_data.ngay_phat_hien_ktat, rec_data.da_cam);
			i:=i+1;
		    END LOOP;
	    CLOSE cur_data;	   
		
		UPDATE kpi_report_temp a SET tinh_name = (select name from dr_area dm where dm.tinh_id=a.tinh_id);
		UPDATE kpi_report_temp a SET qhu_name = (select name from dr_area dm where dm.tinh_id=a.qhu_id);
		UPDATE kpi_report_temp a SET pxa_name = (select name from dr_area dm where dm.tinh_id=a.pxa_id);
	   
	    -- Process NhuCau
	    OPEN cur_temp;
	    LOOP
	    FETCH cur_temp INTO rec_temp;
	    EXIT WHEN NOT FOUND;		
		-- Nhu-cau
		SELECT INTO arr_create_date ARRAY(SELECT DISTINCT datecreate FROM dr_support data
							WHERE data.id_nkt= rec_temp.id
							AND data.status_id=0 
							ORDER BY data.datecreate DESC);
		len := ARRAY_LENGTH(arr_create_date, 1);
		row_ins := len;
		IF len>0 THEN
			FOR i IN 1 .. array_upper(arr_create_date,1) LOOP				
				IF i=1 THEN
					UPDATE kpi_report_temp 
					SET nhu_cau = get_support_where(rec_temp.id, arr_create_date[i], 0, -1), 
						nhucau_noi_nhan = get_support_where(rec_temp.id, arr_create_date[i], 0, 0), 
						nhucau_can_thiep_cn = get_support_where(rec_temp.id, arr_create_date[i], 0, 1),
						nhucau_ten_dung_cu = get_support_where(rec_temp.id, arr_create_date[i], 0, 2),
						nhucau_cai_thien_ctvs = get_support_where(rec_temp.id, arr_create_date[i], 0, 3),
						ngay_nhu_cau = to_char(arr_create_date[i], 'dd/MM/yyyy')
					WHERE id=rec_temp.id;
				ELSE
					INSERT INTO kpi_report_temp(stt, id, nhu_cau, 
						nhucau_noi_nhan, 
						nhucau_can_thiep_cn, 
						nhucau_ten_dung_cu, 
						nhucau_cai_thien_ctvs, 
						ngay_nhu_cau) 
					VALUES (rec_temp.stt, rec_temp.id, 
						get_support_where(rec_temp.id, arr_create_date[i], 0, -1),
						get_support_where(rec_temp.id, arr_create_date[i], 0, 0),
						get_support_where(rec_temp.id, arr_create_date[i], 0, 1),
						get_support_where(rec_temp.id, arr_create_date[i], 0, 2),
						get_support_where(rec_temp.id, arr_create_date[i], 0, 3),
						 to_char(arr_create_date[i], 'dd/MM/yyyy'));
				END IF;
			END LOOP;
		ELSE
			UPDATE kpi_report_temp 
			SET nhu_cau = get_support_where(rec_temp.id, arr_create_date[i], 0, -1), 
				nhucau_noi_nhan = get_support_where(rec_temp.id, arr_create_date[i], 0, 0), 
				nhucau_can_thiep_cn = get_support_where(rec_temp.id, arr_create_date[i], 0, 1),
				nhucau_ten_dung_cu = get_support_where(rec_temp.id, arr_create_date[i], 0, 2),
				nhucau_cai_thien_ctvs = get_support_where(rec_temp.id, arr_create_date[i], 0, 3),					
				ngay_nhu_cau = to_char(arr_create_date[i], 'dd/MM/yyyy')
			WHERE id=rec_temp.id;
		END IF;

		-- Ho-tro
		SELECT INTO arr_create_date ARRAY(SELECT DISTINCT datecreate FROM dr_support data
							WHERE data.id_nkt= rec_temp.id
							AND data.status_id=1 
							ORDER BY data.datecreate DESC);
		len := ARRAY_LENGTH(arr_create_date, 1);
		IF len>0 THEN
			FOR i IN 1 .. array_upper(arr_create_date,1) LOOP				
				IF i<=row_ins THEN
					UPDATE kpi_report_temp 
					SET hotro_da_nhan = get_support_where(rec_temp.id, arr_create_date[i], 1, -1), 
						hotro_noi_nhan = get_support_where(rec_temp.id, arr_create_date[i], 1, 0), 
						hotro_can_thiep_cn = get_support_where(rec_temp.id, arr_create_date[i], 1, 1),
						hotro_ten_dung_cu = get_support_where(rec_temp.id, arr_create_date[i], 1, 2),
						hotro_cai_thien_ctvs = get_support_where(rec_temp.id, arr_create_date[i], 1, 3),					
						ngay_ho_tro = to_char(arr_create_date[i], 'dd/MM/yyyy'),
						hotro_nguon = get_support_nguon(rec_temp.id, arr_create_date[i]),
						hotro_luot = count_support_where(rec_temp.id, arr_create_date[i], 1, -1),
						order_by = arr_create_date[i]
					WHERE id=rec_temp.id;
				ELSE
					INSERT INTO kpi_report_temp(stt, id, 
							hotro_da_nhan, 
							hotro_noi_nhan, 
							hotro_can_thiep_cn, 
							hotro_ten_dung_cu, 
							hotro_cai_thien_ctvs, 
							ngay_ho_tro, 							
							hotro_nguon, 
							hotro_luot,
							order_by) 
					VALUES (rec_temp.stt, rec_temp.id, 
							get_support_where(rec_temp.id, arr_create_date[i], 1, -1),
							get_support_where(rec_temp.id, arr_create_date[i], 1, 0), 
							get_support_where(rec_temp.id, arr_create_date[i], 1, 1),
							get_support_where(rec_temp.id, arr_create_date[i], 1, 2),
							get_support_where(rec_temp.id, arr_create_date[i], 1, 3),
							to_char(arr_create_date[i], 'dd/MM/yyyy'),
							get_support_nguon(rec_temp.id, arr_create_date[i]),
							count_support_where(rec_temp.id, arr_create_date[i], 1, -1),
							arr_create_date[i]);
				END IF;
			END LOOP;
		ELSE
			UPDATE kpi_report_temp 
			SET hotro_da_nhan = get_support_where(rec_temp.id, arr_create_date[i], 1, -1), 
				hotro_noi_nhan = get_support_where(rec_temp.id, arr_create_date[i], 1, 0), 
				hotro_can_thiep_cn = get_support_where(rec_temp.id, arr_create_date[i], 1, 1),
				hotro_ten_dung_cu = get_support_where(rec_temp.id, arr_create_date[i], 1, 2),
				hotro_cai_thien_ctvs = get_support_where(rec_temp.id, arr_create_date[i], 1, 3),					
				ngay_ho_tro = to_char(arr_create_date[i], 'dd/MM/yyyy'),				
				hotro_nguon = get_support_nguon(rec_temp.id, arr_create_date[i]),
				hotro_luot = count_support_where(rec_temp.id, arr_create_date[i], 1, -1),
				order_by = arr_create_date[i]
			WHERE id=rec_temp.id;
		END IF;
	
		-- Tuyen-Xa
		SELECT INTO arr_create_date ARRAY(SELECT create_date FROM kpi_dis_report WHERE nkt_id=rec_temp.id ORDER BY create_date DESC);
		len := ARRAY_LENGTH(arr_create_date, 1);
		IF len>0 THEN
			FOR i IN 1 .. array_upper(arr_create_date,1) LOOP
				INSERT INTO kpi_report_temp(stt, id, hotro_noi_nhan, hotro_can_thiep_cn, ngay_ho_tro,order_by) 
				VALUES (rec_temp.stt, rec_temp.id, 
					'Tập PHCN tại nhà',
					'Chuyên trách xã hỗ trợ',
					 to_char(arr_create_date[i], 'dd/MM/yyyy'),
					 arr_create_date[i]);
			END LOOP;
		END IF;
		
		--RAISE INFO '% % %', rec_temp.id, arr_create_date[0], len;
	    END LOOP;
	    CLOSE cur_temp;
		
		INSERT INTO kpi_report_temp_2020 (stt, id, tinh_id, tinh_name, qhu_id, qhu_name, pxa_id, pxa_name, maso, ten, year_of_birthday, sex, sonha, dienthoai, 
										  create_date, ten_ncs, sdt_ncs, gioitinh_ncs, trangthai, ngay_dong_hs, dang_tat, muc_do, ngay_phat_hien_ktat, da_cam, 
										  nc_id, nhu_cau, nhucau_noi_nhan, nhucau_can_thiep_cn, nhucau_ten_dung_cu, nhucau_cai_thien_ctvs, ngay_nhu_cau, 
										  ht_id, hotro_da_nhan, hotro_noi_nhan, hotro_can_thiep_cn, hotro_ten_dung_cu, hotro_cai_thien_ctvs, ngay_ho_tro, 
										  order_by)
		SELECT stt, id, tinh_id, tinh_name, qhu_id, qhu_name, pxa_id, pxa_name, maso, ten, year_of_birthday, sex, sonha, dienthoai, 
						create_date, ten_ncs, sdt_ncs, gioitinh_ncs, trangthai, ngay_dong_hs, dang_tat, muc_do, ngay_phat_hien_ktat, da_cam, 
						nc_id, nhu_cau, nhucau_noi_nhan, nhucau_can_thiep_cn, nhucau_ten_dung_cu, nhucau_cai_thien_ctvs, ngay_nhu_cau, 
						ht_id, hotro_da_nhan, hotro_noi_nhan, hotro_can_thiep_cn, hotro_ten_dung_cu, hotro_cai_thien_ctvs, ngay_ho_tro, 
						order_by
		FROM kpi_report_temp
		WHERE tinh_id IS NOT NULL;
		
		-- Process 2020
		OPEN cur_temp_2020;
	    LOOP
	    FETCH cur_temp_2020 into rec_data_2020;
		EXIT WHEN NOT FOUND;
			SELECT INTO v_dgia_gannhat DISTINCT create_date FROM kpi_data_rank 
				WHERE nkt_id = rec_data_2020.id ORDER BY create_date DESC LIMIT 1;	
			
			SELECT INTO v_dgia_bandau DISTINCT create_date FROM kpi_data_rank 
				WHERE nkt_id = rec_data_2020.id ORDER BY create_date ASC LIMIT 1;
			
			SELECT INTO v_hotro_gannhat DISTINCT datecreate FROM dr_support 
				WHERE status_id=1 AND id_nkt = rec_data_2020.id ORDER BY datecreate DESC LIMIT 1;
			
			-- Nhu cau
			UPDATE kpi_report_temp_2020 
			SET ncau_vltl = check_support_where(rec_data_2020.id, 0, 299), 
				ncau_hdtl = check_support_where(rec_data_2020.id, 0, 301),
				ncau_nntl = check_support_where(rec_data_2020.id, 0, 300),
				ncau_dcht = check_support_where(rec_data_2020.id, 0, 13),
				ncau_wc = check_support_where(rec_data_2020.id, 0, 157)
			WHERE id=rec_data_2020.id;
			
			-- SUM #num theo diem
			Update kpi_report_temp_2020 set dg_num_0 = data.num0,
			dg_num_1 = data.num1, 
			dg_num_2 = data.num2,
			dg_num_3 = data.num3,
			dg_num_4 = data.num4
			FROM 
			(SELECT nkt_id, SUM(case when p0=1 then 1 else 0 end) num0,
			SUM(case when p1=1 then 1 else 0 end) num1, 
			SUM(case when p2=1 then 1 else 0 end) num2,
			SUM(case when p3=1 then 1 else 0 end) num3,
			SUM(case when p4=1 then 1 else 0 end) num4 
			 from kpi_data_rank where nkt_id=rec_data_2020.id AND create_date=v_dgia_bandau GROUP BY nkt_id) data
			WHERE kpi_report_temp_2020.id=data.nkt_id;
			
			-- COUNT #num co ho tro
			Update kpi_report_temp_2020 set 
			dg_cth_1 = data.num1, 
			dg_cth_2 = data.num2,
			dg_cth_3 = data.num3,
			dg_cth_4 = data.num4
			FROM 
			(SELECT a.nkt_id, SUM(a.num1) num1, SUM(a.num2) num2, SUM(a.num3) num3, SUM(a.num4) num4 FROM
			(select nkt_id, 
			SUM(case when p1=1 then 1 else 0 end) num1, 
			SUM(case when p2=1 then 1 else 0 end) num2,
			SUM(case when p3=1 then 1 else 0 end) num3,
			SUM(case when p4=1 then 1 else 0 end) num4 
			from kpi_data_rank where nkt_id=rec_data_2020.id  
			 AND create_date=v_dgia_bandau 
			 AND has_sp=1 GROUP BY nkt_id, rank_id) a group by nkt_id
			) DATA WHERE kpi_report_temp_2020.id=data.nkt_id;
			
			-- Ho tro: Dung cu tro giup -- Ho tro xay moi/sua chua WC
			Update kpi_report_temp_2020 set hotro_ten_dung_cu = get_support_where_2020(rec_data_2020.id, 1, 2) 
				WHERE id=rec_data_2020.id; -- 2: Dung cu
				
			Update kpi_report_temp_2020 set hotro_cai_thien_ctvs = get_support_where_2020(rec_data_2020.id, 1, 3) 
				WHERE id=rec_data_2020.id; -- 3: WC
			
			-- COUNT:: So luot tap PHCN : vnah; ttp; qhu; pxa
			Update kpi_report_temp_2020 set
				htro_phcn_vnah = data.vnah,
				htro_phcn_ttp = data.ttp,
				htro_phcn_qhu = data.qhu,
				htro_phcn_pxa = data.pxa
			FROM
			(			
				SELECT rec_data_2020.id nktId, SUM(vnah) vnah, SUM(ttp) ttp, SUM(qhu) qhu, SUM(pxa) pxa
				FROM (SELECT (CASE WHEN  doi_tuong = 1 then 1 ELSE 0 end) vnah,
				(CASE WHEN  doi_tuong = 2 then 1 ELSE 0 end) ttp, 
				(CASE WHEN  doi_tuong = 3 then 1 ELSE 0 end) qhu, 
				0 pxa FROM
				(SELECT datecreate, max(doi_tuong) doi_tuong FROM dr_support
				WHERE id_nkt = rec_data_2020.id AND status_id = 1 GROUP BY datecreate) a
				UNION ALL 
				(SELECT 0 vnah, 0 ttp, 0 qhu, count(1) FROM kpi_dis_report where nkt_id = rec_data_2020.id)) b			
			) 
			DATA where kpi_report_temp_2020.id=data.nktId;
			
			-- Kqua danh gia gan nhat
			IF v_dgia_gannhat IS NOT NULL THEN
				--RAISE INFO 'v_dgia_gannhat % % ', rec_data_2020.id, v_dgia_gannhat;
				UPDATE kpi_report_temp_2020 SET dg_ngay = to_char(v_dgia_gannhat,'dd/MM/yyyy') where id=rec_data_2020.id;
			ELSE
				--RAISE INFO 'v_hotro_gannhat %  %', rec_data_2020.id, v_hotro_gannhat;
				UPDATE kpi_report_temp_2020 SET dg_ngay = to_char(v_hotro_gannhat,'dd/MM/yyyy') where id=rec_data_2020.id;
			END IF;
			
			-- Ty le tien bo
			SELECT INTO v_cth_tyle get_percent_rank(rec_data_2020.id, v_dgia_bandau, v_dgia_gannhat);			
			IF (position('.' in v_cth_tyle)>0) THEN
				v_int_tyle:= substring(v_cth_tyle, 0, position('.' in v_cth_tyle))::int;
			ELSE
				v_int_tyle:= v_cth_tyle::int;
			END IF;			
			
			-- Muc do tien bo
			UPDATE kpi_report_temp_2020 set cth_tyle = concat(v_cth_tyle,'%') WHERE id=rec_data_2020.id; 			
			IF v_int_tyle>=0 AND v_int_tyle<4 THEN
				UPDATE kpi_report_temp_2020 set cth_muc = 1 WHERE id=rec_data_2020.id; 
			ELSIF  v_int_tyle>=5 AND v_int_tyle<24 THEN 
				UPDATE kpi_report_temp_2020 set cth_muc = 2 WHERE id=rec_data_2020.id; 
			ELSIF  v_int_tyle>=25 AND v_int_tyle<49 THEN 
				UPDATE kpi_report_temp_2020 set cth_muc = 3 WHERE id=rec_data_2020.id; 
			ELSIF v_int_tyle>=50 AND v_int_tyle<95 THEN 
				UPDATE kpi_report_temp_2020 set cth_muc = 4 WHERE id=rec_data_2020.id; 
			ELSIF v_int_tyle>=95 AND v_int_tyle<100 THEN
				UPDATE kpi_report_temp_2020 set cth_muc = 5 WHERE id=rec_data_2020.id; 
			ELSE 
				UPDATE kpi_report_temp_2020 set cth_muc = null WHERE id=rec_data_2020.id; 
			END IF;
			
			IF v_dgia_gannhat IS NULL THEN
				UPDATE kpi_report_temp_2020 set cth_muc = '', cth_tyle = ''  WHERE id=rec_data_2020.id; 
			END IF;
			
			-- Ket qua sau can thiep
			Update kpi_report_temp_2020 set
				cth_num_0 = data.num0,
				cth_num_1 = data.num1,
				cth_num_2 = data.num2,
				cth_num_3 = data.num3,
				cth_num_4 = data.num4
			FROM 
			(SELECT a.nkt_id, SUM(a.num0) num0, SUM(a.num1) num1, SUM(a.num2) num2, SUM(a.num3) num3, SUM(a.num4) num4 FROM
			(SELECT nkt_id, 
				SUM(case when p0=1 then 1 else 0 end) num0,
				SUM(case when p1=1 then 1 else 0 end) num1, 
				SUM(case when p2=1 then 1 else 0 end) num2,
				SUM(case when p3=1 then 1 else 0 end) num3,
				SUM(case when p4=1 then 1 else 0 end) num4 
			FROM kpi_data_rank where nkt_id=rec_data_2020.id AND create_date=v_dgia_gannhat 
				GROUP BY nkt_id, rank_id) a group by nkt_id) 
			DATA WHERE kpi_report_temp_2020.id=data.nkt_id;
			
			i:=i+1;
	    END LOOP;
	    CLOSE cur_temp_2020;
	RETURN retval;
    END; 
$function$
;