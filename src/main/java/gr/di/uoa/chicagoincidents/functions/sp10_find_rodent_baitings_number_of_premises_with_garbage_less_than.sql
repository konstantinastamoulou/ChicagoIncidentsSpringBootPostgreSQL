CREATE TYPE rodent_baiting_with_garbage AS (request_number varchar, num_of_premises_with_garbage integer);

CREATE FUNCTION find_rodent_baitings_number_of_premises_with_garbage_less_than(max_number integer)
	RETURNS setof rodent_baiting_with_garbage AS $$
  select service_request_number, num_of_premises_with_garbage
  from rodent_baitings as r inner join service_requests as s on r.id = s.id
  where num_of_premises_with_garbage > max_number;
$$ LANGUAGE SQL;


