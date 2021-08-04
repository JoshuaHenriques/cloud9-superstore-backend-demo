create table if not exists order_details (
	order_details_id uuid primary key default uuid_generate_v4(),
	customer_email	varchar(100)		not null unique,
	item_names		varchar[]			not null,
	order_status	varchar(10)			not null,
	total			double precision	not null
);

insert into order_details (customer_email, item_names, order_status, total)
values ('tom.jerry@gmail.com', '{"iPad", "iPhone"}', 'PREPARING', 992.51);