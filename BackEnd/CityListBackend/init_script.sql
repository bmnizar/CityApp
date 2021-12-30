create schema cityapp;
CREATE SEQUENCE cityapp.application_role_seq INCREMENT 100 START 10;
CREATE SEQUENCE cityapp.application_user_seq INCREMENT 100 START 10;
CREATE SEQUENCE cityapp.city_seq INCREMENT 100 START 10;

insert into cityapp.application_role values (1,'StandardUser');
insert into cityapp.application_role values (2,'AdminUser');
insert into cityapp.application_role values (3,'ROLE_ALLOW_EDIT');
