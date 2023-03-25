create view kpi_v_report_comm AS
WITH cte AS
(
   SELECT *,
         ROW_NUMBER() OVER (PARTITION BY nkt_id ORDER BY create_date DESC) AS rn
   FROM kpi_dis_report
)
SELECT *
FROM cte
WHERE rn = 1