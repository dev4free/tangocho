

select question, answer, last_time , CAST(next_time AS TEXT), last_time +next_time * interval '1 second' as new_time from cards where last_time is not null order by last_time 

