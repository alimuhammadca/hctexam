create database hctdb;

use hctdb;

create table role (
id int primary key,
name varchar(25) not null
);

create table appuser (
id varchar(25) primary key,
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
campus varchar(25) null
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
