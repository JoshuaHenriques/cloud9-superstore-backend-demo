create extension if not exists "uuid-ossp";
create extension if not exists "pgcrypto";

create table if not exists login (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    login_id        uuid			not null primary key default uuid_generate_v4(),
    roles           text[]          not null,
    email           varchar(255)    not null unique,
    phone_number    varchar(10)     not null unique,
    password        text    		not null,
    enabled         boolean         default true
);

create table if not exists login_attempts (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    login_id        uuid            primary key default uuid_generate_v4(),
    attempts        int             default 0
);

create table if not exists review (
        created_at              timestamp       default current_timestamp,
        updated_at              timestamp       default current_timestamp,
        review_id               uuid            not null primary key default uuid_generate_v4(),
        text                    text,
        rating                  int             not null check ((rating >= 0) and (rating <= 5)),
        image                   bytea
);

create table if not exists item_reviews (
        created_at              timestamp       default current_timestamp,
        updated_at              timestamp       default current_timestamp,
        item_reviews_id			uuid			not null primary key default uuid_generate_v4(),
        item_id                 uuid,
        review_id               uuid            references review(review_id)
);

create table if not exists item (
        created_at              timestamp       default current_timestamp,
        updated_at              timestamp       default current_timestamp,
        item_id                 uuid            not null primary key default uuid_generate_v4(),
        item_reviews_id			uuid			unique references item_reviews(item_reviews_id),
        item_name               varchar(50)     not null,
        item_description        text            not null,
        price                   double precision not null,
        image                   bytea
);

alter table item_reviews add foreign key (item_id) references item(item_id);

create table if not exists order_items (
	   	created_at              timestamp       default current_timestamp,
       	updated_at              timestamp       default current_timestamp,
       	order_items_id			uuid			primary key default uuid_generate_v4(),
        orders_id				uuid,
        item_id                 uuid            references item(item_id)
);

create table if not exists orders (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
	orders_id     	uuid			primary key default uuid_generate_v4(),
	order_items_id	uuid			references order_items(order_items_id),
	order_status	varchar(50)		not null,
	total			double precision not null
);

alter table order_items add foreign key (orders_id) references orders(orders_id);

create table if not exists customer_orders (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    customer_orders_id	uuid		primary key default uuid_generate_v4(),
    orders_id		uuid			references orders(orders_id),
    customer_id		uuid
);

create table if not exists store_inventory (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	inventory_id	uuid			primary key default uuid_generate_v4(),
	item_id			uuid            unique references item(item_id),
	store_id		uuid			not null,
	item_name       varchar(50)     not null,
	quantity		int		        not null,
	price 			double precision not null
);

create table if not exists online_inventory (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	inventory_id	uuid			primary key default uuid_generate_v4(),
	item_id			uuid            unique references item(item_id),
	item_name       varchar(50)     not null,
	quantity		int		        not null,
	price 			double precision not null
);

create table if not exists address (
	created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
	address_id      uuid            primary key default uuid_generate_v4(),
	street_name     varchar(25)     not null,
	street_number   varchar(10)     not null,
	unit_number     varchar(10)     not null,
	city            varchar(25)     not null,
	postal_code     varchar(7)      not null,
	province        varchar(25)     not null
);

create table if not exists store (
    created_at          timestamp   default current_timestamp,
    updated_at          timestamp   default current_timestamp,
    store_id            uuid        primary key default uuid_generate_v4(),
    store_name          varchar(25) not null,
    address_id          uuid        unique references address(address_id)
);

alter table store_inventory add foreign key (store_id) references store(store_id);

create table if not exists employee (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	employee_id     uuid            primary key default uuid_generate_v4(),
	address_id		uuid			unique references address(address_id),
	email           varchar(50)     not null unique,
	login_id		uuid			unique references login(login_id),
	store_id		uuid			references store(store_id),
	first_name      varchar(25)     not null,
	last_name       varchar(25)     not null,
	phone_number    varchar(10)     not null,
	date_of_birth   varchar(10)  	not null,
	image 			bytea
);

create table if not exists credit_card (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
	credit_card_id  uuid         	primary key default uuid_generate_v4(),
	full_name       varchar(25)     not null,
	ccn				varchar(16)		not null unique,
	four_dig        varchar(4)      not null unique,
	cvc				varchar(3)		not null,
	exp_date		varchar(10)		not null
);

create table if not exists wallet (
    created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    wallet_id		uuid			primary key default uuid_generate_v4(),
    customer_id		uuid,
    credit_card_id	uuid			unique references credit_card(credit_card_id)
);

create table if not exists cart_items (
	created_at      timestamp       default current_timestamp,
    updated_at      timestamp       default current_timestamp,
    cart_items_id	uuid			primary	key default uuid_generate_v4(),
    cart_id			uuid,
    item_id			uuid			references item(item_id)
);

create table if not exists cart (
	created_at      timestamp           default current_timestamp,
    updated_at      timestamp           default current_timestamp,
    cart_id			uuid				primary key default uuid_generate_v4(),
	customer_id 	uuid,
	cart_items_id	uuid				unique references cart_items(cart_items_id),
	total           double precision	not null
);

alter table cart_items add foreign key (cart_id) references cart(cart_id);

create table if not exists customer (
	created_at      timestamp       default current_timestamp,
	updated_at      timestamp       default current_timestamp,
	customer_id     uuid            primary key default uuid_generate_v4(),
	email           varchar(50)     not null unique,
	login_id		uuid			unique references login(login_id),
	cart_id			uuid			unique references cart(cart_id),
	address_id		uuid			unique references address(address_id),
	wallet_id		    uuid          	unique references wallet(wallet_id),
	customer_orders_id       uuid	        unique references customer_orders(customer_orders_id),
	first_name      varchar(25)     not null,
	last_name       varchar(25)     not null,
	phone_number    varchar(10)     not null,
	date_of_birth   varchar(10)  	not null
);

alter table wallet add foreign key (customer_id) references customer(customer_id);
alter table cart add foreign key (customer_id) references customer(customer_id);
alter table customer_orders add foreign key (customer_id) references customer(customer_id);