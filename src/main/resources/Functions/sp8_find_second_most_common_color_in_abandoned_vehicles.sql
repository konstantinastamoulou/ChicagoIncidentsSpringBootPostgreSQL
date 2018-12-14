CREATE FUNCTION find_second_most_common_color_in_abandoned_vehicles()
	RETURNS varchar AS $$
	select vehicle_color
  from abandoned_vehicles as v,service_requests as s
  where s.service_request_type = 'Abandoned Vehicle Complaint' and s.id = v.id
  group by vehicle_color
  order by count(*) desc
  limit 1
  offset 1;
$$ LANGUAGE SQL;


