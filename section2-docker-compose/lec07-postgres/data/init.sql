create table product (
  id serial primary key,
  name varchar(50),
  price numeric(10,2) not null
  );

insert into product(name, price) values('tv', 100.12);
insert into product(name, price) values('ipad', 300.12);
insert into product(name, price) values('couch', 1000.12);
