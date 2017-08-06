drop table IF EXISTS sys_user;
create table sys_user (
  id          BIGINT PRIMARY KEY   AUTO_INCREMENT,
  username    VARCHAR(32) NOT NULL UNIQUE,
  name        VARCHAR(64) NOT NULL,
  password    VARCHAR(32) NOT NULL,
  status      INT         NOT NULL DEFAULT 1,
  email       VARCHAR(256),
  qq          VARCHAR(16),
  wechat      VARCHAR(16),
  telephone   VARCHAR(16),
  cellphone   VARCHAR(16),
  photo       VARCHAR(256),
  super_admin  INT,
  remark      VARCHAR(512),
  create_time DATETIME,
  update_time DATETIME,
  creator     BIGINT,
  updater     BIGINT,
  version     INT         NOT NULL DEFAULT 0
);

drop table IF EXISTS sys_role;
create table sys_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  code varchar(32) not null UNIQUE ,
  name varchar(64) not null,
  description VARCHAR(256),
  create_time DATETIME,
  update_time DATETIME,
  creator     BIGINT,
  updater     BIGINT,
  version     INT         NOT NULL DEFAULT 0
);

drop table if exists sys_permission;
create table sys_permission (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  code varchar(32) not null UNIQUE ,
  name varchar(64) not null,
  action varchar(256),
  icon varchar(256),
  category bigint not null,
  description VARCHAR(256),
  create_time DATETIME,
  update_time DATETIME,
  creator     BIGINT,
  updater     BIGINT,
  version     INT         NOT NULL DEFAULT 0
);

drop table if exists sys_user_role;
create table sys_user_role (
  id bigint primary key auto_increment,
  u_id bigint not null,
  r_id bigint not null
);

drop table if exists sys_role_permission;
create table sys_role_permission (
  id bigint primary key auto_increment,
  r_id bigint not null,
  p_id bigint not null
);


insert into sys_user(id, username, name, password, super_admin, create_time, creator)
    values(1, 'admin', 'Administrator', '431759ab506d1e4af78e7fe08b818edb', 1, now(), 1);




