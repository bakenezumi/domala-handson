# Users schema
 
# --- !Ups
create table emp(
    id int not null primary key,
    name varchar(20),
    age int,
    sex char(1),
    version int not null
);

create sequence emp_id_seq start with 1;

insert into emp (id, name, age, sex, version) values(1, 'SMITH', 10, 'M', 1);
insert into emp (id, name, age, sex, version) values(2, 'ALLEN', 20, 'F', 1);
 
# --- !Downs
 
drop table emp;
