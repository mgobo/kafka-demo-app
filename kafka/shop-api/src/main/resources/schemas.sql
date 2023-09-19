create table shop (
id number primary key,
identifier varchar(200) not null,
status varchar(50) not null,
date_shop date
);
create table shop_item (
id number primary key,
shop_id number,
product_identifier varchar(200) not null,
amount int not null,
price float not null,
FOREIGN KEY shop_id number REFERENCES shop(id)
);