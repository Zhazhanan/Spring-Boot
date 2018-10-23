create table user
(
  id         bigint       not null
    primary key,
  name       varchar(255) null,
  password   varchar(255) null,
  createTime datetime     null
)
  engine = MyISAM;