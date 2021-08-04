create table if not exists cus_address (
	cus_address_id  uuid primary key default uuid_generate_v4(),
	customer_id 	uuid,
	street_name     varchar(25)     not null,
	street_number   varchar(10)     not null,
	unit_number     varchar(10)     not null,
	city            varchar(25)     not null,
	postal_code     varchar(7)      not null,
	province        varchar(25)     not null
);

create table if not exists credit_card (
	credit_card_id  uuid primary key default uuid_generate_v4(),
	customer_id 	uuid,
	cus_name        varchar(25)     not null,
	ccn				varchar(16)		not null unique,
	four_dig        varchar(4)      not null unique,
	cvc				varchar(3)		not null,
	exp_date		varchar(10)		not null
);

create table if not exists cart (
	cart_id         uuid primary key default uuid_generate_v4(),
	customer_id 	uuid,
	item_names		varchar[],
	total           double precision
);

create table if not exists customer (
	customer_id     uuid primary key default uuid_generate_v4(),
	created_at      timestamp       default current_timestamp,
	cus_address_id  uuid,
	cart_id			uuid,
	email           varchar(50)     not null unique,
	credit_card_ids uuid[],
	order_detail_ids uuid[],
	first_name      varchar(25)     not null,
	last_name       varchar(25)     not null,
	phone_number    varchar(10)     not null,
	password        varchar(128)    not null,
	date_of_birth   varchar(10)  	not null
);

alter table customer
	add foreign key (cus_address_id) references cus_address(cus_address_id);

alter table cus_address
	add foreign key (customer_id) references customer (customer_id);

alter table cart
	add foreign key (customer_id) references customer (customer_id);

alter table customer
	add foreign key (cart_id) references cart (cart_id);

do $$
declare
	ret_cart_id uuid;
	ret_credit_card_id uuid;
	ret_cus_address_id uuid;
	ret_customer_id uuid;
begin

	insert into customer (email, credit_card_ids, order_detail_ids, first_name, last_name, phone_number, password, date_of_birth)
	values ('glory.scott@gmail.com', '{}', '{}', 'Scott', 'Glory', '4375923041', 'password123', '05/29/1990')
	returning customer_id into ret_customer_id;

	insert into cus_address (street_name, street_number, unit_number, city, postal_code, province)
	values ('137th Avenue', '3648', '0', 'Calgary', 'T5B 3V4', 'Alberta')
	returning cus_address_id into ret_cus_address_id;

	update customer set cus_address_id = ret_cus_address_id where customer_id = ret_customer_id;

	update cus_address set customer_id = ret_customer_id where cus_address_id = ret_cus_address_id;

	insert into cart (item_names, total)
	values ('{}', 0.00)
	returning cart_id into ret_cart_id;

	update customer set cart_id = ret_cart_id where customer_id = ret_customer_id;

	update cart set customer_id = ret_customer_id where cart_id = ret_cart_id;

	insert into credit_card (cus_name, ccn, four_dig, cvc, exp_date)
	values ('Scott Glory', '5412625377173302', '3302', '372', '01/01/2025')
	returning credit_card_id into ret_credit_card_id;

	update customer set credit_card_ids = array_append(credit_card_ids, ret_credit_card_id) where customer_id = ret_customer_id;

	update credit_card set customer_id = ret_customer_id where credit_card_id = ret_credit_card_id;


end$$;