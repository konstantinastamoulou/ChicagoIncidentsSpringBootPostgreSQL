CREATE TYPE license_plate_more_than_once AS (license_plate varchar, plates_count bigint);

CREATE FUNCTION find_license_plates_in_abandoned_vehicles_more_than_once()
	RETURNS setof license_plate_more_than_once AS $$
  select license_plate,count(*)
  from abandoned_vehicles as v
  inner join service_requests as s on v.id=s.id
  group by license_plate
  having count(*)>=2;
$$ LANGUAGE SQL;