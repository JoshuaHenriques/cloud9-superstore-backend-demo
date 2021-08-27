-- Store
insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('8bd3b9a9-2938-4ca0-8978-049d0c601181', 'Brand Road', '4209', '233', 'Saskatoon', 'S7K 1W8', 'Saskatchewan');

insert into store (store_id, store_name, address_id) values ('c95363dc-de29-4c11-ad66-56693af48a57', 'Cloud9 Superstore', '8bd3b9a9-2938-4ca0-8978-049d0c601181');

-- Item
insert into item (item_id, item_name, item_description, price) values ('2c9bc6f5-8024-4bbc-80cb-155f8bb1d4f0', 'Pasta - Lasagne, Fresh', 'Vivamus vel nulla eget eros elementum pellentesque.', 7.46);
insert into item (item_id, item_name, item_description, price) values ('0a247241-47ac-44ce-a90d-e770ffe74592', 'Cheese - Cottage Cheese', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 8.85);
insert into item (item_id, item_name, item_description, price) values ('201fd017-20d7-45aa-a18f-45c85de0cc8c', 'Lettuce - Frisee', 'Nulla nisl. Nunc nisl.', 0.99);
insert into item (item_id, item_name, item_description, price) values ('68012fe3-c7eb-405e-a9ae-57ca1646414a', 'Oil - Hazelnut', 'Fusce consequat. Nulla nisl. Nunc nisl.', 4.99);
insert into item (item_id, item_name, item_description, price) values ('3c0a4bfa-ae1b-4bf7-b7d9-656c1a4e4108', 'Browning Caramel Glace', 'Cras pellentesque volutpat dui.', 5.39);
insert into item (item_id, item_name, item_description, price) values ('726bb8f2-7516-4e56-baab-e2471defcb81', 'Bread - 10 Grain', 'Sed ante.', 3.99);
insert into item (item_id, item_name, item_description, price) values ('fdaf6094-2fb9-40db-ba4a-d79baec395ab', '24inch Gaming Monitor', 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.', 299.99);
insert into item (item_id, item_name, item_description, price) values ('f9df4666-9cf1-4d8d-b5f3-9e83eb5febb7', '50inch Samsung TV', 'Nulla ut erat id mauris vulputate elementum.', 1799.99);
insert into item (item_id, item_name, item_description, price) values ('c45a9649-183d-4883-8e03-2e76ab3a6077', 'Air Conditioner', 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 499.99);
insert into item (item_id, item_name, item_description, price) values ('51aed8cd-21b9-40d8-8a42-7f07c2dfa9b2', 'Xbox Series X', 'In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl.', 599.99);

-- Online Inventory
insert into online_inventory (item_id, item_name, quantity, price) values('51aed8cd-21b9-40d8-8a42-7f07c2dfa9b2', 'Xbox Series X', 1000, 599.99);

insert into online_inventory (item_id, item_name, quantity, price) values('c45a9649-183d-4883-8e03-2e76ab3a6077', 'Air Conditioner', 500, 499.99);

insert into online_inventory (item_id, item_name, quantity, price) values('f9df4666-9cf1-4d8d-b5f3-9e83eb5febb7', '50inch Samsung TV', 65, 1799.99);

insert into online_inventory (item_id, item_name, quantity, price) values('fdaf6094-2fb9-40db-ba4a-d79baec395ab', '24inch Gaming Monitor', 300, 299.99);

-- Store Inventory
insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('ece1bb78-c0b2-4a4e-8169-1d197c371770', '51aed8cd-21b9-40d8-8a42-7f07c2dfa9b2', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Xbox Series X', 1000, 599.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('e212acfa-767f-4e93-8cdb-778d524aa5fa', 'c45a9649-183d-4883-8e03-2e76ab3a6077', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Air Conditioner', 500, 499.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('3f347726-b116-41e8-8a0c-0280e23991f4', 'f9df4666-9cf1-4d8d-b5f3-9e83eb5febb7', 'c95363dc-de29-4c11-ad66-56693af48a57', '50inch Samsung TV', 65, 1799.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('5bffcae9-86f7-4569-b222-ff77eb2d0d8e', 'fdaf6094-2fb9-40db-ba4a-d79baec395ab', 'c95363dc-de29-4c11-ad66-56693af48a57', '24inch Gaming Monitor', 300, 299.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('7ed0335d-b4c7-4c87-b191-ef97957ce7f2', '726bb8f2-7516-4e56-baab-e2471defcb81', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Bread - 10 Grain', 400, 3.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('07d3b99a-bf80-4ba2-8638-f283bd94156e', '3c0a4bfa-ae1b-4bf7-b7d9-656c1a4e4108', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Browning Caramel Glace', 377, 3.95);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('1ab951a8-3969-4ad2-a6ef-7db9805d430c', '68012fe3-c7eb-405e-a9ae-57ca1646414a', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Oil - Hazelnut', 564, 4.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('ac7d2119-0ca9-4a6d-89af-4ecbfe6b6ec8', '201fd017-20d7-45aa-a18f-45c85de0cc8c', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Lettuce - Frisee', 798, 0.99);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('6c6eb4f7-7f6b-430c-bd6c-8f19745187dc', '0a247241-47ac-44ce-a90d-e770ffe74592', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Cheese - Cottage Cheese', 250, 8.85);

insert into store_inventory (inventory_id, item_id, store_id, item_name, quantity, price) values('8b1a2df7-8f25-4813-a243-e05c0290043b', '2c9bc6f5-8024-4bbc-80cb-155f8bb1d4f0', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Pasta - Lasagne, Fresh', 300, 7.46);

-- Manager Logins
insert into login (login_id, roles, email, phone_number, password, enabled) values ('e932dc43-e08e-4ae8-9580-2c2efc05f6e1', '{"USER", "MOD", "ADMIN"}', 'feman.mark@gmail.com',  '2503229896', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('a71ac053-1e17-4d5a-81a2-6a5956a90bfa', '{"USER", "MOD", "ADMIN", "SUPER_ADMIN"}', 'feldman.jonah@gmail.com', '2503529846', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('967f0ab9-ab04-4970-a1ca-c79ace5ce98e', '{"USER", "MOD", "ADMIN", "SUPER_ADMIN"}', 'ash.dina@gmail.com', '2502139846', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('97b618f4-5f95-4a54-b87d-845d797b633c', '{"USER", "MOD", "ADMIN"}', 'dunn.garrett@gmail.com',  '2503529346', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('c8593941-b3b0-49d7-9540-3d74e066bee5', '{"USER", "MOD"}', 'santos.mateo@gmail.com', '2509045738', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('031988c5-78ba-42d3-a76e-d7b2b3b1c1d6', '{"USER", "MOD"}','mckinney.glenn@gmail.com','2509942342', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('cce539d7-3ce2-43f7-a850-75c39eec0ebb', '{"USER", "MOD"}', 'sakura.cheyenne@gmail.com', '2503434446', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('fad1b651-6b99-4e11-82e0-ee04072f9975', '{"USER", "MOD", "ADMIN", "SUPER_ADMIN"}', 'ferrera.amy@gmail.com', '2503917099', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('fa8bf02b-0b59-440e-92b1-855f7d253ffa', '{"USER", "MOD", "ADMIN"}', 'kauahi.sandra@gmail.com', '2503629841', 'password', true);

insert into login (login_id, roles, email, phone_number, password, enabled) values ('7c1cd3a1-38bb-4c57-9ca0-a36fcf4eb9b1', '{"USER", "MOD", "ADMIN"}', 'schumann.justine@gmail.com', '2500439036', 'password', true);

-- Manager Addresses
insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('236797e4-d7b3-433d-b04a-d48832e98210', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('fc18fe60-cced-49ea-bb30-0f131f4da99f', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('8166402d-2d58-4492-8e98-ce4b6092cc99', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('833858a7-a3de-4e04-b586-f99c4f24b408', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('1a82151b-b9d1-44f1-9808-f3e279452e8c', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('8eee68b1-a35a-4fad-9047-6d56307f6b26', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('6b6d16ec-f474-4dfa-bbaf-f4fed642bd84', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('a1a0ec02-ab21-4eb7-92e0-6fecc4a0dfe7', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('a25b17f3-1d7a-44ed-998e-13665c3aa23b', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

insert into address (address_id, street_name, street_number, unit_number, city, postal_code, province) values ('3519a32c-ab64-47b7-b8fc-ff9c514721ec', '102nd Avenue', '2304', '101', 'Nelson', 'V1L 5Y5', 'British Columbia');

-- Employee Managers
insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('236797e4-d7b3-433d-b04a-d48832e98210', 'feman.mark@gmail.com', 'e932dc43-e08e-4ae8-9580-2c2efc05f6e1', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Mark', 'Feman', '2503229896', '02/20/1991');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('fc18fe60-cced-49ea-bb30-0f131f4da99f', 'feldman.jonah@gmail.com', '97b618f4-5f95-4a54-b87d-845d797b633c', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Jonah', 'Feldman', '2503529846', '02/27/1977');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('8166402d-2d58-4492-8e98-ce4b6092cc99', 'ash.dina@gmail.com', 'a71ac053-1e17-4d5a-81a2-6a5956a90bfa', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Dina', 'Ash', '2502139846', '01/07/1983');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('833858a7-a3de-4e04-b586-f99c4f24b408', 'dunn.garrett@gmail.com', '967f0ab9-ab04-4970-a1ca-c79ace5ce98e', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Garrett', 'Dunn', '2503894839', '08/22/1992');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('1a82151b-b9d1-44f1-9808-f3e279452e8c', 'santos.mateo@gmail.com', 'c8593941-b3b0-49d7-9540-3d74e066bee5', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Mateo', 'Santos', '2509045738', '05/09/1990');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('8eee68b1-a35a-4fad-9047-6d56307f6b26', 'mckinney.glenn@gmail.com', '031988c5-78ba-42d3-a76e-d7b2b3b1c1d6', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Glenn', 'McKinney', '2509942342', '11/01/1989');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('6b6d16ec-f474-4dfa-bbaf-f4fed642bd84', 'sakura.cheyenne@gmail.com', 'cce539d7-3ce2-43f7-a850-75c39eec0ebb', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Cheyenne', 'Sakura', '2503434446', '03/20/1982');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('a1a0ec02-ab21-4eb7-92e0-6fecc4a0dfe7', 'ferrera.amy@gmail.com', 'fad1b651-6b99-4e11-82e0-ee04072f9975', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Amy', 'Ferrera', '2503917099', '04/15/1985');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('a25b17f3-1d7a-44ed-998e-13665c3aa23b', 'kauahi.sandra@gmail.com', 'fa8bf02b-0b59-440e-92b1-855f7d253ffa', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Sandra', 'Kauahi', '2503629841', '07/12/1986');

insert into employee (address_id, email, login_id, store_id, first_name, last_name, phone_number, date_of_birth) values ('3519a32c-ab64-47b7-b8fc-ff9c514721ec', 'schumann.justine@gmail.com', '7c1cd3a1-38bb-4c57-9ca0-a36fcf4eb9b1', 'c95363dc-de29-4c11-ad66-56693af48a57', 'Justine', 'Schumann', '2500439036', '08/19/1977');