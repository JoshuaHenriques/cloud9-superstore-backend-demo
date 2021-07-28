CREATE TABLE IF NOT EXISTS item (
	item_uid		UUID				PRIMARY KEY DEFAULT uuid_generate_v4(),
	item_name		VARCHAR(25)			NOT NULL,
	description		VARCHAR(255)		NOT NULL,
	price 			DOUBLE PRECISION	NOT NULL,
	image			BYTEA				NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory (
	inventory_uid	UUID				PRIMARY KEY DEFAULT uuid_generate_v4(),
	item_uid		UUID				NOT NULL UNIQUE FOREIGN KEY REFERENCES item(item_id),
	item_name		VARCHAR(50)			NOT NULL REFERENCES item(item_name),
	quantity		SMALLINT			NOT NULL
);

