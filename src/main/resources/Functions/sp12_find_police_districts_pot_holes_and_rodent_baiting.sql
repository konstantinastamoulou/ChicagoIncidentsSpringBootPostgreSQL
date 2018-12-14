CREATE FUNCTION find_police_districts_pot_holes_and_rodent_baiting(input_date date)
	RETURNS setof integer AS $$
  select distinct s1.police_district
  from service_requests as s1,service_requests as s2,pot_holes as p,rodent_baiting as r
  where p.num_of_potholes>1
  and r.num_of_premises_baited>1
  and s1.id=p.id and s2.id=r.id
  and s1.police_district=s2.police_district
  and s1.completion_date=s2.completion_date
  and s1.completion_date= date input_date
$$ LANGUAGE SQL;


