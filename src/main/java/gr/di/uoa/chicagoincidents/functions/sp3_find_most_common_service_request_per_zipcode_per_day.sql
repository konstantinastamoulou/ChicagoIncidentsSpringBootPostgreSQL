CREATE TYPE request_count_per_day_for_date_range AS (request_count bigint, creation_date date);

CREATE FUNCTION find_total_requests_for_specific_type_per_day_for_date_range(service_request_type varchar, start_date date, end_date date)
	RETURNS request_count_per_day_for_date_range AS $$
  select service_request_type, zip_code
  from service_requests
  where creation_date = input_date
  group by zip_code, service_request_type
  order by count(*) desc;
$$ LANGUAGE SQL;



