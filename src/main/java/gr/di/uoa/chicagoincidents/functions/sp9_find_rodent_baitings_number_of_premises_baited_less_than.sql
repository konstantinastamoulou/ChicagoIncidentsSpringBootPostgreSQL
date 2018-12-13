CREATE TYPE rodent_baiting_baited AS (request_number varchar, num_of_premises_baited integer);

CREATE FUNCTION find_rodent_baitings_number_of_premises_baited_less_than(max_number integer)
	RETURNS rodent_baiting_baited AS $$
  select service_request_number, num_of_premises_baited
  from rodent_baitings as r inner join service_requests as s on r.id = s.id
  where num_of_premises_baited < max_number;
$$ LANGUAGE SQL;


