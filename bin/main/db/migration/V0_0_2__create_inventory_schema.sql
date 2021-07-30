create table if not exists item (
	item_uid		uuid primary key default uuid_generate_v4(),
	item_name		varchar(25)			not null unique,
	description		varchar(255)		not null,
	price 			double precision	not null,
	image			bytea
);

create table if not exists inventory (
	inventory_uid	uuid primary key default uuid_generate_v4(),
	item_uid		uuid				not null unique references item(item_uid),
	item_name		varchar(50)			not null unique references item(item_name),
	quantity		smallint			not null
);