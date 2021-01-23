create database sellinglaptop_ltw;

use sellinglaptop_ltw ;

Create table Account
(
	id int auto_increment,
    username varchar(255) unique not null,
    password varchar(255) not null,
    email varchar(255),
    phonenumber char(15),
    address nvarchar(255),
    avatar varchar(255),
    role_id int, 			-- 1: admin 0:client
    Primary	Key(id)
);


Create table Category
(
	id int auto_increment,
	cate_name nvarchar(255) not null,
    image varchar(255),
    Primary	Key(id)
);

Create table DetailCategory
(
	id int auto_increment,
	detail_cate_name nvarchar(255) not null,
    image varchar(255),
    cate_id int ,
    Foreign Key(cate_id) references Category(id),
    Primary	Key(id)
);

Create table product
(
	id int auto_increment,
	product_name nvarchar(255) not null,
    image varchar(255),
    price real,
    describe_pro nvarchar(255),
    detail_cate_id int ,
    Foreign Key(detail_cate_id) references DetailCategory(id),
    Primary	Key(id)
);

Create table cart
(
	id int auto_increment,
    user_id int,
    total_price real,
    buydate date,
    Foreign Key(user_id) references Account(id),
    Primary Key(id)
);
alter table cart
add column customer_id int references customer(id);

create table cartitem
(
	cart_id int auto_increment not null,
    product_id int,
    quantity int,
    unit_price real,
    Foreign Key(cart_id) references Cart(id),
    Foreign Key(product_id) references Product(id),
    Primary Key(cart_id,product_id)
);
create table customer
(
	id int auto_increment,
    fullname varchar(255),
    email varchar(255),
    phonenumber char(15),
    address nvarchar(255),
    Primary	Key(id)
);

alter table cart
add foreign key (customer_id) references Customer(id);

create table brand
(
	id int primary key auto_increment,
    brand_name nvarchar(255)
);


alter table product
add column brand_id int;

alter table product
add  Foreign Key (brand_id)  references brand(id);


alter table product
add column quantity int default 0;


alter table cart
add column status int default 0;

alter table cart
add column option_pay int default 0;

