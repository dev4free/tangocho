update cards set last_time =  current_timestamp - ( interval '7 day');
update decks set last_access =  current_timestamp - ( interval '1 day');
