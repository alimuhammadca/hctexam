create database hctdb;

use hctdb;

create table role (
id int primary key,
name varchar(25) not null
);

INSERT INTO role VALUES(1, 'ADMIN'), (2, 'USER');

create table appuser (
id varchar(25) primary key,
role_id int REFERENCES role(id), 
firstname varchar(25) not null,
lastname varchar(25) not null,
email varchar(25) not null,
password varchar(25) not null
);

create table exam (
id int auto_increment primary key,
semester varchar(25) not null,
head_inv varchar(25) not null,
inv_1 varchar(25) null,
inv_2 varchar(25) null,
inv_3 varchar(25) null,
bkup_inv varchar(25) null,
floater varchar(25) null,
course_code varchar(25) not null,
delivery varchar(25) not null,
student_count int not null,
reg_time varchar(25) null,
start_time varchar(25) null,
end_time varchar(25) null,
building varchar(25) null,
room varchar(25) null,
campus varchar(25) null,
password1 varchar(20) null,
password2 varchar(20) null
);

create table irr_form (
id int auto_increment primary key,
semester varchar(25) not null,
course_name varchar(50) null,
course_code varchar(25) not null,
exam_date varchar(25) not null,
exam_time varchar(25) null,
campus varchar(25) null,
head_inv varchar(25) not null,
inv_1 varchar(25) null,
inv_2 varchar(25) null,
inv_3 varchar(25) null
);

create table incidence (
id int auto_increment primary key,
exam_id int references exam(id),
student_name varchar(50) not null,
student_id varchar(15) not null,
incidence_time varchar(25) not null,
desk_number int not null,
observation varchar(255) null,
evidence varchar(255) null
);
