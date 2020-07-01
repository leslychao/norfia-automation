create table statements
(
  id bigint NOT NULL PRIMARY KEY,
  credit  varchar,
	name varchar,
	inn varchar,
	payment_details varchar,
	last_updated timestamp,
	pack_id varchar,
	sync_state varchar
);

create table dictionaries
(
  id bigint NOT NULL PRIMARY KEY,
  dictionary  varchar,
	key varchar,
	value varchar,
	last_updated timestamp
);

create index statements_idx_01 on statements (id);

create sequence statements_seq
    increment by 1
    start with 1
    minvalue 1
    NO MAXVALUE
    NO CYCLE;

create sequence dictionaries_seq
    increment by 1
    start with 1
    minvalue 1
    NO MAXVALUE
    NO CYCLE;

create sequence companies_seq
    increment by 1
    start with 1
    minvalue 1
    NO MAXVALUE
    NO CYCLE;
