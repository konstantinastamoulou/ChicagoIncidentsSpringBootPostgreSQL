CREATE TYPE avg_completion_per_type AS (request_count interval, service_request_type varchar);

CREATE FUNCTION find_most_common_request_on_bounding_box(start_date date, end_date date)
	RETURNS avg_completion_per_type AS $$
  select service_request_type
  from service_requests
  where longitude > -100.1 and longitude < 1000.1 and latitude > -100.1 and latitude < 1000.1 and creation_date = date '2010-12-26'
  group by service_request_type
  order by count(*) desc
  limit 1;
$$ LANGUAGE SQL;