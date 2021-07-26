CREATE TABLE address (
	address_id      uuid DEFAULT uuid_generate_v4();
	street_name     VARCHAR(50)     NOT NULL,
	street_number   SMALLINT        NOT NULL,
	unit_number     SMALLINT        NOT NULL,
	city            VARCHAR(50)     NOT NULL,
	postal_code     VARCHAR(6)      NOT NULL,
	province        VARCHAR(50)     NOT NULL
);

CREATE TABLE cart (
	cart_id     uuid                DEFAULT uuid_generate_v4();
	email       VARCHAR(100)        NOT NULL,
	total       DOUBLE PRECISION    NOT NULL
);

CREATE TABLE creditcard (
	creditcard_id   uuid            DEFAULT uuid_generate_v4();
	first_name      VARCHAR(50)     NOT NULL,
	last_name       VARCHAR(50)		NOT	NULL,
	ccn				VARCHAR(20)		NOT NULL,
);

CREATE TABLE item (
	item_id			uuid				DEFAULT uuid_generate_v4();
	item_name		VARCHAR(25)			NOT NULL,
	description		VARCHAR(25)			NOT NULL,
	price 			DOUBLE PRECISION	NOT NULL,
	image			BYTEA				NOT NULL
);

CREATE TABLE order (
	order_id		uuid				DEFAULT uuid_generate_v4();
	item_id			uuid				FOREIGN KEY REFERENCES item(item_id),
	order_status	VARCHAR(25)			NOT NULL,
	sub_total		DOUBLE PRECISION	NOT NULL,
	total			DOUBLE PRECISION	NOT NULL
);

CREATE TABLE inventory (
	inventory_id	uuid				DEFAULT uuid_generate_v4();
	item_id			uuid				UNIQUE FOREIGN KEY REFERENCES item(item_id),
	item_name		VARCHAR(50)			NOT NULL,
	quantity		SMALLINT			NOT NULL
);

CREATE TABLE customer (
	customer_id     uuid            DEFAULT uuid_generate_v4();
	address_id      uuid            UNIQUE FOREIGN KEY REFERENCES address(address_id),
	cart_id         uuid            UNIQUE FOREIGN KEY REFERENCES cart(cart_id),
	creditcard_id   uuid            FOREIGN KEY REFERENCES creditcard(creditcard_id),
	order_id        uuid            FOREIGN KEY REFERENCES order(order_id),
	email           VARCHAR(50)     NOT NULL,
	first_name      VARCHAR(50)     NOT NULL,
	last_name       VARCHAR(50)     NOT NULL,
	phone_number    VARCHAR(15)     NOT NULL,
	password        VARCHAR(100)    NOT NULL,
	date_of_birth   VARCHAR(10)     NOT NULL,
);