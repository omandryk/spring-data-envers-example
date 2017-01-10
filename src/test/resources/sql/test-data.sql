drop table if exists users;

create table users (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `fullname` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  	PRIMARY KEY (`id`)
);

insert into users (username, password, fullname, email) values ('habuma', 'password', 'Craig Walls', 'craig@habuma.com');
insert into users (username, password, fullname, email) values ('mwalls', 'password', 'Michael Walls', 'mwalls@habuma.com');
insert into users (username, password, fullname, email) values ('chuck', 'password', 'Chuck Wagon', 'chuck@habuma.com');
insert into users (username, password, fullname, email) values ('artnames', 'password', 'Art Names', 'art@habuma.com');