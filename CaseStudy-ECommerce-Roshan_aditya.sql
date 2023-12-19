create database caseStudy;

use caseStudy;

create table customers (
	customer_id int primary key,
    name varchar(50),
    email varchar(100),
    password varchar(50)
);

create table products (
	product_id int primary key,
    name varchar(50),
    price double,
    description varchar(100),
    stockQuantity int
);

create table cart (
	cart_id int primary key,
    customer_id int,
    product_id int,
    quantity int,
    foreign key (customer_id) references customers (customer_id),
    foreign key (product_id) references products (product_id)
);

create table orders (
	order_id int primary key,
    customer_id int,
    order_date date,
    total_price double,
    shipping_address varchar(100),
    foreign key (customer_id) references customers (customer_id)
);

create table order_items (
	order_item_id int primary key,
    order_id int,
    product_id int,
    quantity int,
    foreign key (order_id) references orders (order_id),
    foreign key (product_id) references products (product_id)
);

-- Customers data
insert into customers values (1, 'priya', 'priya@gmail.com', '12345');
insert into customers values (2, 'sri', 'sri@gmail.com', 'sr123');
insert into customers values (3, 'hari', 'hari@gmail.com', '123456');
insert into customers values (4, 'rya', 'rya@gmail.com', '56789');
insert into customers values (5, 'milky', 'milky@gmail.com', '45678');
insert into customers values (6, 'bala', 'bala@gmail.com', '887799');
insert into customers values (7, 'murugan', 'murugan@gmail.com', '11111');
insert into customers values (8, 'krithika', 'krithika@gmail.com', '22222');
insert into customers values (9, 'Shasthika', 'shastika@gmail.com', '33333');
insert into customers values (10, 'Soundarya', 'sound@gmail.com', '55555');

-- Products data
insert into products values (1, 'T-Shirt', 500, 'Cotton crew-neck T-shirt', 120);
insert into products values(2, 'Backpack', 300, 'Water-resistant hiking backpack', 80);
insert into products values(3, 'Coffee Mug', 80, 'Ceramic coffee mug with logo', 200);
insert into products values(4, 'Wireless Mouse', 250, 'Ergonomic wireless mouse', 100);
insert into products values(5, 'Notebook', 60, 'Hardcover lined notebook', 150);
insert into products values(6, 'Running Shoes', 600, 'Breathable running shoes', 70);
insert into products values(7, 'Portable Charger', 350, '10000mAh power bank', 90);
insert into products values(8, 'Sunglasses', 200, 'Polarized UV protection sunglasses', 180);
insert into products values(9, 'Bluetooth Speaker', 450, 'Portable waterproof speaker', 50);
insert into products values(10, 'Desk Lamp', 180, 'Adjustable LED desk lamp', 110);

-- Cart data
insert into cart values (1, 1, 2, 3);
insert into cart values (2, 3, 4, 1);
insert into cart values (3, 9, 1, 2);
insert into cart values (4, 1, 3, 1);
insert into cart values (5, 2, 2, 4);
insert into cart values (6, 4, 1, 1);
insert into cart values (7, 5, 4, 2);
insert into cart values (8, 6, 3, 1);
insert into cart values (9, 5, 2, 3);
insert into cart values (10, 7, 1, 2);

-- Orders data
insert into orders values (1, 1, '2023-12-13', 500, 'Chennai, Tamil Nadu');
insert into orders values (2, 2, '2023-12-04', 700, 'Ramnadhapuram, Tamil Nadu');
insert into orders values (3, 1, '2023-11-28', 560, 'Madurai, Tamil Nadu');
insert into orders values (4, 4, '2023-11-27', 464, 'Tirupathi, Andra');
insert into orders values (5, 5, '2023-12-12', 894, 'Kanchipuram, Tamil Nadu');
insert into orders values (6, 2, '2023-12-25', 879, 'Hosur, Tamil Nadu');
insert into orders values (7, 9, '2023-11-29', 650, 'Tirunelveli, Tamil Nadu');
insert into orders values (8, 8, '2023-11-23', 800, 'Vellore, Tamil Nadu');
insert into orders values (9, 7, '2023-11-24', 450, 'Munnar, Kerala');
insert into orders values (10, 3, '2023-11-21', 789, 'Azlapuzhla, Kerala');

-- Order_Items data
insert into order_items values (1, 1, 2, 3);
insert into order_items values (2, 2, 4, 1);
insert into order_items values (3, 1, 1, 2);
insert into order_items values (4, 4, 3, 1);
insert into order_items values (5, 5, 2, 4);
insert into order_items values (6, 2, 1, 2);
insert into order_items values (7, 9, 4, 1);
insert into order_items values (8, 8, 4, 4);
insert into order_items values (9, 7, 3, 1);
insert into order_items values (10, 3, 1, 3);

select * from customers;
select * from orders;
select * from products;
select * from cart;
select * from order_items;

select p.* from cart c join products p on c.product_id = p.product_id where c.customer_id = 1;