CREATE TYPE rodent_baiting_with_rats AS (request_number varchar, num_of_premises_with_rats integer);

CREATE FUNCTION find_rodent_baitings_number_of_premises_with_rats_less_than(max_number integer)
	RETURNS setof rodent_baiting_with_rats AS $$
  select service_request_number, num_of_premises_with_rats
  from rodent_baitings as r inner join service_requests as s on r.id = s.id
  where num_of_premises_with_rats < max_number;
$$ LANGUAGE SQL;


