create table statements
(
  id bigint NOT NULL PRIMARY KEY,
  credit  varchar,
	name varchar,
	short_name varchar,
	inn varchar,
	payment_details varchar,
	last_updated timestamp,
	pack_id varchar,
	sync_state varchar
);

create index statements_idx_01 on statements (id);
create index statements_idx_02 on statements (short_name);

create table dictionaries
(
  id bigint NOT NULL PRIMARY KEY,
  dictionary  varchar,
	key varchar,
	value varchar,
	last_updated timestamp
);

create table companies
(
  id bigint NOT NULL PRIMARY KEY,
  external_company_id  bigint,
	company_name varchar,
	managers varchar,
	credit varchar,
	payment_number varchar,
	inn_matched boolean DEFAULT FALSE,
	last_updated timestamp
);

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
