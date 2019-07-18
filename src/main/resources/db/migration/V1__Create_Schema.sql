drop table if exists address;
drop table if exists users;

create table users(
    id serial,
    name varchar(30) not null,
    email varchar(30),
    cellphone varchar(15),
    primary key (id)
);
-- One to One
create table address(
    user_id int,
    street varchar(30) not null,
    city varchar(30) not null,
    state char(2) not null,
    country varchar(30) not null,
    zip_code varchar(9),
    primary key (user_id),
    foreign key (user_id) references users (id) on delete cascade
);
