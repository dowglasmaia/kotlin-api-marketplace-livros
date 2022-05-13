create table customer(
id int auto_increment primary key,
name varchar(100) not null,
email varchar(100) not null unique
);