create table if not exists address (
	address_id      uuid primary key default uuid_generate_v4(),
	street_name     varchar(25)     not null,
	street_number   varchar(10)     not null,
	unit_number     varchar(10)     not null,
	city            varchar(25)     not null,
	postal_code     varchar(7)      not null,
	province        varchar(25)     not null
);

create table if not exists credit_card (
	credit_card_id  uuid primary key default uuid_generate_v4(),
	name            varchar(25)     not null,
	ccn				varchar(16)		not null unique,
	four_dig        varchar(4)      not null unique,
	cvc				varchar(3)		not null,
	exp_date		varchar(10)		not null
);

create table if not exists cart (
	cart_id         uuid primary key default uuid_generate_v4(),
	item_ids		uuid[],
	total           double precision
);

create table if not exists customer (
	customer_id     uuid primary key default uuid_generate_v4(),
	created_at      timestamp       default current_timestamp,
	email           varchar(50)     not null unique,
	address_id      uuid            not null unique references address(address_id),
	cart_id         uuid            not null unique references cart(cart_id),
	credit_card_ids uuid[],
	order_detail_ids uuid[],
	first_name      varchar(25)     not null,
	last_name       varchar(25)     not null,
	phone_number    varchar(10)     not null,
	password        varchar(128)    not null,
	date_of_birth   varchar(10)  	not null
);