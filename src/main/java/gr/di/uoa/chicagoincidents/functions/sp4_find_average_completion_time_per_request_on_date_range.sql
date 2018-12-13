CREATE TYPE request_count_per_day_for_date_range AS (request_count bigint, creation_date date);

CREATE FUNCTION find_total_requests_for_specific_type_per_day_for_date_range(service_request_type varchar, start_date date, end_date date)
	RETURNS request_count_per_day_for_date_range AS $$
  select avg(completion_date-creation_date) as avg_completion_time,service_request_type
  from service_requests
  where creation_date between date '2011-11-11' and date '2018-11-11'
  group by service_request_type;
$$ LANGUAGE SQL;






