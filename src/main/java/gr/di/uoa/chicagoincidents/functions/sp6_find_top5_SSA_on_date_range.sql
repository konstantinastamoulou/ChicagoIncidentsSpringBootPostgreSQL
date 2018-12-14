CREATE TYPE ssa_count AS (ssa varchar, ssa_count numeric );

CREATE FUNCTION find_top5_SSA_on_date_range(start_date date, end_date date)
	RETURNS setof ssa_count AS $$
	select ssa,sum(num) from
    (select ssa,count(*) as num,creation_date from abandoned_vehicles as v,service_requests as s
       where (s.id=v.id) and (creation_date between start_date and end_date) and ssa!=''
       group by creation_date,ssa
      union
      select ssa,count(*) as num,creation_date from pot_holes as h,service_requests as s
       where (s.id=h.id) and (creation_date between start_date and end_date)and ssa!=''
       group by creation_date,ssa
      union
      select ssa,count(*) as num,creation_date from graffiti_removals as g,service_requests as s
       where (s.id=g.id) and (creation_date between start_date and end_date) and ssa!=''
       group by creation_date,ssa
      union
      select ssa,count(*) as num,creation_date from garbage_carts as c,service_requests as s
      where (s.id=c.id) and (creation_date between start_date and end_date) and ssa!=''
      group by creation_date,ssa) as test
    group by ssa
    order by sum(num) desc
    limit 5;
$$ LANGUAGE SQL;

