CREATE TABLE IF NOT EXISTS order (
	order_uid		UUID				PRIMARY KEY DEFAULT uuid_generate_v4(),
	item_uid		UUID[]				NOT NULL FOREIGN KEY REFERENCES item(item_uid),
	order_status	VARCHAR(25)			NOT NULL,
	customer_email	VARCHAR(100)		NOT NULL UNIQUE IDENTITY,
	sub_total		DOUBLE PRECISION	NOT NULL,
	total			DOUBLE PRECISION	NOT NULL
);