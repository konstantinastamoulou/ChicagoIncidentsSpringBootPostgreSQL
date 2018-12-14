CREATE FUNCTION find_most_common_request_on_bounding_box(a1 double precision, a2 double precision, b1 double precision, b2 double precision, c1 double precision, c2 double precision, d1 double precision, d2 double precision)
	RETURNS varchar AS $$
  select service_request_type
  from service_requests
  where longitude>=d2 and longitude<=a2 and longitude>=c2 and longitude<=b2
  and latitude>=a1 and latitude<=b1 and latitude>=d1 and latitude<=c1
  group by service_request_type
  order by count(*) desc
  limit 1;
$$ LANGUAGE SQL;