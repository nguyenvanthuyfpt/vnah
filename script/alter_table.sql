
CREATE SEQUENCE kpi_job_scheduler_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE kpi_job_scheduler_seq OWNER TO postgres;

create table kpi_job_scheduler
(
	id bigint NOT NULL DEFAULT nextval('kpi_job_scheduler_seq'::regclass),
	create_date timestamp without time zone,
	job_code VARCHAR(50),
	job_name VARCHAR(50),
	job_exec VARCHAR(50),
	job_cron VARCHAR(50),
	job_status INT DEFAULT 0
)

CREATE SEQUENCE kpi_job_log_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE kpi_job_log_seq OWNER TO postgres;

CREATE TABLE kpi_job_log
(
	id bigint NOT NULL DEFAULT nextval('kpi_job_log_seq'::regclass) ,
	start_exec timestamp without time zone,
	end_exec timestamp without time zone,
	job_id INT, 
	msg_exec VARCHAR(100)
)

/* Add Giao duc dac biet + Cham soc giam nhe */
alter table dr_support add column ct_gddb text;
alter table dr_support add column ct_csgn text;

/* Add Danh muc */
INSERT INTO dr_hotro (hotro_id, name,code,parent_id,order_by,name_htro) 
values (303,'Chăm sóc giảm nhẹ','CSGN',6,303,'Chăm sóc giảm nhẹ');

INSERT INTO dr_hotro (hotro_id, name,code,parent_id,order_by,name_htro) 
values (304,'Giáo dục đặc biệt','GDDB',302,302,'Giáo dục đặc biệt');

update dr_hotro set name = 'Chăm sóc tại nhà', name_htro = 'Chăm sóc tại nhà'
where hotro_id = 303;

alter  table dr_support add column ngthien_ten varchar(100);
alter  table dr_support add column ngthien_cvu int;

-- public.dr_home_visit definition

-- Drop table

-- DROP TABLE public.dr_home_visit;
CREATE SEQUENCE public.dr_home_visit_sid
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.dr_home_visit (
	id int8 NULL DEFAULT nextval('dr_home_visit_sid'::regclass),
	support_id int8 NOT NULL,
	id_nkt int8 NOT NULL,
	start_at timestamp NULL,
	end_at timestamp NULL,
	create_by int8 NOT NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	"location" varchar(255) NULL
);


zuh6WEu2juuTUOUr/667Gw==