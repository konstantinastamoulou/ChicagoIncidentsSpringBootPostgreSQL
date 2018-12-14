CREATE TYPE request_count_per_day_for_date_range AS (request_count bigint, creation_date timestamp);

CREATE FUNCTION find_total_requests_for_specific_type_per_day_for_date_range(service_request_type varchar, start_date date, end_date date)
	RETURNS setof request_count_per_day_for_date_range AS $$
  select count(*),creation_date
  from service_requests
  where service_request_type = service_request_type and creation_date between start_date and end_date
  group by creation_date;
$$ LANGUAGE SQL;