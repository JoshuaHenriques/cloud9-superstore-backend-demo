create extension if not exists "uuid-ossp";
create table if not exists cus_address (
	created_at      timestamp           default current_timestamp,
    updated_at      timestamp           default current_timestamp,
	cus_address_id  uuid primary key default uuid_generate_v4(),
	customer_id 	uuid,
	street_name     varchar(25)     not null,
	street_number   varchar(10)     not null,
	unit_number     varchar(10)     not null,
	city            varchar(25)     not null,
	postal_code     varchar(7)      not null,
	province        varchar(25)     not null
);

create table if not exists customer (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	customer_id     uuid primary key default uuid_generate_v4(),
	email           varchar(50)     not null unique,
	wallet_id       uuid not null unique references wallet(wallet_id),
	past_orders_id  uuid not null unique references past_orders(past_orders_id),
	first_name      varchar(25)     not null,
	last_name       varchar(25)     not null,
	phone_number    varchar(10)     not null,
	password        varchar(128)    not null,
	date_of_birth   varchar(10)  	not null

create table if not exists credit_card (
    created_at      timestamp           default current_timestamp,
    updated_at      timestamp           default current_timestamp,
	customer_id  uuid primary key references customer(customer_id),
	cus_name        varchar(25)     not null,
	ccn				varchar(16)		not null unique,
	four_dig        varchar(4)      not null unique,
	cvc				varchar(3)		not null,
	exp_date		varchar(10)		not null
);

create table if not exists cart (
	created_at      timestamp           default current_timestamp,
    updated_at      timestamp           default current_timestamp,
	customer_id 	uuid,
	item_names		text[],
	total           double precision
);
CREATE TYPE public.order_status AS ENUM (
    'G',
    'PG',
    'PG-13',
    'R',
    'NC-17'
);


insert into order_details (customer_email, item_names, order_status, total)
values ('tom.jerry@gmail.com', '{"iPad"}', 'PREPARING', 992.51);

do $$
declare
	ret_cart_id uuid;
	ret_credit_card_id uuid;
	ret_address_id uuid;
	ret_customer_id uuid;
begin
	insert into customer (email, credit_card_ids, order_detail_ids, first_name, last_name, phone_number, password, date_of_birth)
	values ('glory.scott@gmail.com', '{}', '{}', 'Scott', 'Glory', '4375923041', 'password123', '05/29/1990')
	returning customer_id into ret_customer_id;

	insert into address (street_name, street_number, unit_number, city, postal_code, province)
	values ('137th Avenue', '3648', '0', 'Calgary', 'T5B 3V4', 'Alberta')
	returning address_id into ret_address_id;

	update customer set cus_address_id = ret_cus_address_id where customer_id = ret_customer_id;

	update address set customer_id = ret_customer_id where address_id = ret_address_id;

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
end $$;

create table if not exists orders (
	orders_id uuid primary key default uuid_generate_v4(),
	customer_id     uuid,
	customer_email	varchar(100)		not null unique,
	item_names		text[],
	order_status	varchar(10)			not null,
	total			double precision	not null
);

create table if not exists login (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    foreign_id      uuid,
    status          boolean,
    email           varchar(255)    not null,
    phone_number    varchar(10)     not null,
    password        varchar(128)    not null,
    first_name      varchar(25)     not null,
    last_name       varchar(25)     not null
);

create extension if not exists "uuid-ossp";

create table if not exists discount_codes; make function

create table if not exists item_review;

create table if not exists item (
    created_at      timestamp           default current_timestamp,
    updated_at      timestamp           default current_timestamp,
	item_id		    uuid primary key default uuid_generate_v4(),
	item_name		varchar(25)			not null unique,
	item_description varchar(255)		not null unique,
	image			bytea
);

create table if not exists inventory (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	item_id			uuid primary key unique references item(item_id),
	item_name       varchar(25) not null unique references item(item_name),
	quantity		int		not null,
	price 			double precision	not null
);

create table if not exists online_inventory ();

do $$
declare
    ret_item_id uuid;
	ret_item_name varchar(25);
begin
    insert into item (item_name, item_description)
    values ('iPad', '2023 Model')
    returning item_id, item_name into ret_item_id, ret_item_name;

    insert into inventory (item_id, item_name, quantity, price)
    values (ret_item_id, ret_item_name, 39, 399.99);
end $$;

create enum if not exists order_status;

create table if not exists emp;

create table if not exists store (
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    store_id uuid primary key default uuid_generate_v4(),
    manager_id uuid references manager(manager_id),
    store_name varchar(25) primary key not null,
    address_id uuid unique references address(address_id)
    inventory_id uuid unique references inventory(inventory_id),
    online_inventory_id uuid references online_inventory(online_inventory_id)
);