CREATE FUNCTION find_most_common_request_on_bounding_box(a1 double precision, a2 double precision, b1 double precision, b2 double precision, c1 double precision, c2 double precision, d1 double precision, d2 double precision)
	RETURNS setof varchar AS $$
  select service_request_type
  from service_requests
  where longitude<=a1 and longitude<=b1 and longitude>=d1 and longitude>=c1
  and latitude<=b2 and latitude<=d2 and latitude>=a2 and latitude>=c2
  group by service_request_type
  order by count(*) desc
  limit 1;
$$ LANGUAGE SQL;