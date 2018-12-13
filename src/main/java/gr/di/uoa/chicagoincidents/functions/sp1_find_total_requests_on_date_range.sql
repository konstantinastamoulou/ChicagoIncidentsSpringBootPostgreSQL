CREATE TYPE request_count_per_type AS (request_count bigint, request_type varchar);

CREATE FUNCTION find_total_requests_on_date_range(start_date date, end_date date)
	RETURNS request_count_per_type AS $$
	select count(*) as requests_count, service_request_type
	from service_requests
	where creation_date between start_date and end_date
	group by service_request_type
	order by count(*);
$$ LANGUAGE SQL;


