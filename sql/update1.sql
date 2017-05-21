alter table tangocho.decks add column last_access timestamp default null;
alter table tangocho.cards add column skip  boolean not null default false;