CREATE TABLE IF NOT EXISTS address (
	address_uid     UUID			PRIMARY KEY DEFAULT uuid_generate_v4(),
	street_name     VARCHAR(50)     NOT NULL,
	street_number   SMALLINT        NOT NULL,
	unit_number     SMALLINT        NOT NULL,
	city            VARCHAR(50)     NOT NULL,
	postal_code     VARCHAR(6)      NOT NULL,
	province        VARCHAR(50)     NOT NULL
);

CREATE TABLE IF NOT EXISTS creditcard (
	creditcard_uid  UUID            PRIMARY KEY DEFAULT uuid_generate_v4(),
	first_name      VARCHAR(50)     NOT NULL,
	last_name       VARCHAR(50)		NOT	NULL,
	ccn				VARCHAR(20)		NOT NULL UNIQUE,
	cvc				VARCHAR(3)		NOT NULL,
	exp_date		DATE			NOT NULL
);

CREATE TABLE IF NOT EXISTS cart (
	cart_uid    UUID                PRIMARY KEY DEFAULT uuid_generate_v4(),
	item_uid	UUID				NOT NULL FOREIGN KEY REFERENCES item(item_uid),
	total       DOUBLE PRECISION    NOT NULL
);

CREATE TABLE IF NOT EXISTS customer (
	customer_uid    UUID            PRIMARY KEY DEFAULT uuid_generate_v4(),
	address_uid     UUID            NOT NULL UNIQUE FOREIGN KEY REFERENCES address(address_id),
	cart_uid        UUID            NOT NULL UNIQUE FOREIGN KEY REFERENCES cart(cart_id),
	creditcard_uid  UUID            FOREIGN KEY REFERENCES creditcard(creditcard_id),
	order_uid       UUID            FOREIGN KEY REFERENCES order(order_id),
	email           VARCHAR(50)     NOT NULL IDENTITY UNIQUE,
	first_name      VARCHAR(50)     NOT NULL,
	last_name       VARCHAR(50)     NOT NULL,
	phone_number    VARCHAR(15)     NOT NULL,
	password        VARCHAR(100)    NOT NULL,
	date_of_birth   DATE     		NOT NULL,
);

