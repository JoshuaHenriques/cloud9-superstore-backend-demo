create table if not exists item (
	item_id		    uuid primary key default uuid_generate_v4(),
	item_name		varchar(25)			not null unique,
	description		varchar(255)		not null unique,
	price 			double precision	not null,
	image			bytea
);

create table if not exists inventory (
	inventory_id	uuid primary key default uuid_generate_v4(),
	item_id		    uuid				not null unique references item(item_id),,
	quantity		smallint			not null
);

insert into item (item_name, description, price)
values ('iPad', '2023 Model', 399.99);

insert into inventory (item_id, quantity)
values ((select item_id from item where item_name = 'iPad'), 39);