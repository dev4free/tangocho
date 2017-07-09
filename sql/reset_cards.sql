update cards  set last_time = null, next_time = null, reviewed = false ;
delete  from  history;
update decks set last_access =  current_timestamp - ( interval '1 day');


