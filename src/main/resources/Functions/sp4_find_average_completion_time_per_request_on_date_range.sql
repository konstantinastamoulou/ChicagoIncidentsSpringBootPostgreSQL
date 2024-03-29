CREATE TYPE avg_completion_per_type AS (request_count numeric, service_request_type varchar);

CREATE FUNCTION avg_completion_per_type_for_date_range(start_date date, end_date date)
	RETURNS setof avg_completion_per_type AS $$
  select avg(completion_date::date - creation_date::date) as avg_completion_time,service_request_type
  from service_requests
  where creation_date between start_date and end_date
  group by service_request_type;
$$ LANGUAGE SQL;