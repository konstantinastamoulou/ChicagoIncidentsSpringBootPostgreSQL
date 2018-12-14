CREATE TYPE request_type_zip_code AS (service_request_type varchar, zip_code integer);

CREATE FUNCTION find_most_common_request_per_zipcode_for_a_day(input_date date)
	RETURNS setof request_type_zip_code AS $$
  select service_request_type, zip_code
  from service_requests
  where creation_date = input_date
  group by zip_code, service_request_type
  order by count(*) desc;
$$ LANGUAGE SQL;

