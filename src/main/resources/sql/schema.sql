drop table if exists users;

create table users (
  id identity,
  username varchar(25) not null,
  password varchar(25) not null,
  fullname varchar(100) not null,
  email varchar(50) not null,
);
