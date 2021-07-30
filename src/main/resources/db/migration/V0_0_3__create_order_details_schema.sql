create table if not exists order_details (
	order_details_uid		uuid primary key default uuid_generate_v4(),
	customer_email	varchar(100)		not null unique,
	item_uids		uuid[]				not null,
	order_status	varchar(25)			not null,
	sub_total		double precision	not null,
	total			double precision	not null
);