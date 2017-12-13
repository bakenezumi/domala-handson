# Users schema
 
# --- !Ups
create table emp(
    id int not null primary key,
    name varchar(20),
    age int,
    version int not null
);

create sequence emp_id_seq start with 3;

insert into emp (id, name, age, version) values(1, 'SMITH', 10, 1);
insert into emp (id, name, age, version) values(2, 'ALLEN', 20, 1);
