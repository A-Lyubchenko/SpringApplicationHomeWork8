CREATE TABLE customers
(id uuid PRIMARY KEY,
 name VARCHAR(50));

CREATE TABLE products
(id uuid PRIMARY KEY,
 name VARCHAR(50),
 price numeric(10,6),
 customer_id uuid,
 FOREIGN KEY (customer_id) references customers(id));

CREATE TABLE users
(id uuid PRIMARY KEY,
 username varchar(50),
 email varchar(50),
 password varchar(150),
 firstName varchar(15),
 lastName varchar(20));

CREATE TABLE authorities
(id uuid PRIMARY KEY,
 authority VARCHAR(25),
 user_id uuid,
 FOREIGN KEY (user_id) references users(id));


INSERT INTO users(id, username, email, password, firstName, lastName)
VALUES('20354d7a-e4fe-47af-8ff6-187bca92f3f9', 'admin', 'admin.admin@gmail.com',
       '{bcrypt}$2a$12$EyGaNhLqAr89nL89DWIAbu3mh6dSSKKlqfXXn/dw1FxZAS0nckhIi', 'admin', 'admin');


INSERT INTO authorities(id, authority, user_id)
VALUES('b5e2cf01-8bb6-4fcd-ad88-0efb611195da', 'admin',
       '20354d7a-e4fe-47af-8ff6-187bca92f3f9');