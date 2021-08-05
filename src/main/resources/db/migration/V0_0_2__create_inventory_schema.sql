create extension if not exists "uuid-ossp";

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