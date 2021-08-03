create table if not exists address (
	address_id      uuid primary key default uuid_generate_v4(),
	street_name     varchar(50)     not null,
	street_number   smallint        not null,
	unit_number     smallint        not null,
	city            varchar(50)     not null,
	postal_code     varchar(6)      not null,
	province        varchar(50)     not null
);

create table if not exists credit_card (
	credit_card_id  uuid primary key default uuid_generate_v4(),
	first_name      varchar(50)     not null,
	last_name       varchar(50)		not	null,
	ccn				varchar(20)		not null unique,
	cvc				varchar(3)		not null,
	exp_date		date			not null
);

create table if not exists cart (
	cart_id         uuid primary key default uuid_generate_v4(),
	item_id			uuid[]		        not null,
	total           double precision    not null
);

create table if not exists customer (
	customer_id     uuid primary key default uuid_generate_v4(),
	email           varchar(50)     not null unique,
	address_id      uuid            not null unique references address(address_id),
	cart_uid        uuid            not null unique references cart(cart_id),
	credit_card_ids uuid[],
	order_ids       uuid[],
	first_name      varchar(50)     not null,
	last_name       varchar(50)     not null,
	phone_number    varchar(15)     not null,
	password        varchar(100)    not null,
	date_of_birth   varchar(10)  	not null
);