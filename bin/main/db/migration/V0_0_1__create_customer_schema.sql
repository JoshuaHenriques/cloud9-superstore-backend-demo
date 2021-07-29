create table if not exists address (
	address_uid uuid primary key default uuid_generate_v4(),
	street_name     varchar(50)     not null,
	street_number   smallint        not null,
	unit_number     smallint        not null,
	city            varchar(50)     not null,
	postal_code     varchar(6)      not null,
	province        varchar(50)     not null
);

create table if not exists creditcard (
	creditcard_uid  uuid            primary key default uuid_generate_v4(),
	first_name      varchar(50)     not null,
	last_name       varchar(50)		not	null,
	ccn				varchar(20)		not null unique,
	cvc				varchar(3)		not null,
	exp_date		date			not null
);

create table if not exists cart (
	cart_uid    uuid                primary key default uuid_generate_v4(),
	item_uid	uuid				not null foreign key references item(item_uid),
	total       double precision    not null
);

create table if not exists customer (
	customer_uid    uuid            primary key default uuid_generate_v4(),
	address_uid     uuid            not null unique foreign key references address(address_id),
	cart_uid        uuid            not null unique foreign key references cart(cart_id),
	creditcard_uid  uuid[]          foreign key references creditcard(creditcard_id),
	order_uid       uuid[]          foreign key references order(order_id),
	email           varchar(50)     not null identity unique,
	first_name      varchar(50)     not null,
	last_name       varchar(50)     not null,
	phone_number    varchar(15)     not null,
	password        varchar(100)    not null,
	date_of_birth   date     		not null,
);

