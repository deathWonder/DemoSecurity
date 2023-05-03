--liquibase formatted sql

--changeset Sabir:1
create table server_user(
login varchar(20) not null primary key,
password varchar(100) not null,
position  varchar(20) not null not null,
authority varchar(20) not null not null
);
--rollback drop table server_user;

--changeset Sabir:2
insert into server_user(login, position, password, authority)
values('user', '1', '$2a$12$F9GLJCuZKyKsv4HCF4Dnyu2GdzhO.iW1Qld0kHcpchtPWSnMKOnWG', 'ROLE_USER');
insert into server_user(login, position, password, authority)
values('admin', '2', '$2a$12$gO36Tm3ClzY/xgoyp4oBH.lEoD3W8PmaKKdkVmELuIbX/Uz4fMPkK', 'ROLE_ADMIN');
--rollback drop values from table server_user;