create table item
(
    id          integer primary key,
    category    varchar(100),
    description varchar(100),
    price       numeric,
    width       integer,
    height      integer,
    length      integer,
    weight      integer
);

insert into item (id, category, description, price, width, height, length, weight)
values (1, 'Instrumentos Musicais', 'Guitarra', 1000, 100, 50, 15, 3);
insert into item (id, category, description, price, width, height, length, weight)
values (2, 'Instrumentos Musicais', 'Amplificador', 5000, 50, 50, 50, 22);
insert into item (id, category, description, price, width, height, length, weight)
values (3, 'Acessórios', 'Cabo', 30, 10, 10, 10, 1);

create table coupon
(
    code        varchar(100),
    percentage  numeric,
    expire_date timestamp,
    primary key (code)
);

insert into coupon (code, percentage, expire_date)
values ('VALE20', 20, timestamp '2021-10-10 10:00:00');
insert into coupon (code, percentage, expire_date)
values ('VALE20_EXPIRED', 20, timestamp '2020-10-10 10:00:00');

create sequence order_seq start with 1 increment by 1;

create table `order`
(
    id          integer auto_increment,
    coupon_code varchar(100),
    code        varchar(20),
    cpf         varchar(20),
    issue_date  timestamp,
    freight     numeric,
    serial      integer,
    primary key (id)
);

create table order_item
(
    id_order integer,
    id_item  integer,
    price    numeric,
    quantity integer,
    primary key (id_order, id_item)
);