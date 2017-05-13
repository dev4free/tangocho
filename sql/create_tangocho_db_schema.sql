drop schema if exists tangocho cascade; 
create schema tangocho;
create table tangocho.accounts
(
    id    serial primary key not null,
    user_name varchar(50) not null,
    user_password varchar(50)  not null
);
create table tangocho.decks
(
    id serial primary key not null,
    account_id integer references accounts(id) not null,
    description varchar(200)
);
create table tangocho.cards (
    id serial primary key not null,
    deck_id integer  references decks(id) not null,
    question varchar(2000) not null,
    answer varchar(2000) not null,
    last_time timestamp default null,
    next_time integer null,
    reviewed boolean default false
);
create table tangocho.history (
	id serial primary key not null,
	card_id integer references cards(id) not null,
	reviewed_time timestamp not null,
	next_time integer not null
);
create table tangocho.tags
(
    id    serial primary key not null,
    description varchar(200) not null
);
create table tangocho.card_tags (
	id serial primary key not null,
	card_id integer references cards(id) not null,
	tag_id integer references tags(id) not null
);
