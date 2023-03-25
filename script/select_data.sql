SELECT * FROM kpi_report_temp a WHERE 
EXISTS (SELECT * FROM kpi_report_temp b
WHERE TO_DATE(b.create_date,'DD/MM/YYYY') BETWEEN '2019-01-01' AND '2019-04-30'
AND a.id=b.id) 
ORDER BY a.stt ASC, a.maso ASC, a.order_by ASC


INSERT INTO kpi_job_scheduler(create_date, job_code, job_name, job_exec)  
VALUES (CURRENT_DATE, '_REPORT_EXPORT', 'Kết xuất thông tin NKT', '{call kpi_gen_data(0, 0, '''', '''')}')

UPDATE kpi_job_scheduler SET job_cron = '0 0/15 * 1/1 * ? *' WHERE id = 3
