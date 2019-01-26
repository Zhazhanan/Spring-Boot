drop table if exists city;
CREATE TABLE city (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    state VARCHAR,
    country VARCHAR
);

drop table if exists demo;
create table demo
(
  id          bigint auto_increment
  comment 'ID'
    primary key,
  name        varchar(50)                         not null
  comment '名字',
  age         smallint(6)                         not null
  comment '年龄',
  sex         bit                                 not null
  comment '性别',
  birthday    datetime                            not null
  comment '出生日期',
  address     varchar(500)                        not null
  comment '家庭住址',
  create_time timestamp default CURRENT_TIMESTAMP not null,
  valid       bit default '1'                    not null
);