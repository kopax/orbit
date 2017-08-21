drop table IF EXISTS sys_user;
create table sys_user (
  id          char(32) PRIMARY KEY,
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
  creator     char(32),
  updater     char(32),
  version     INT         NOT NULL DEFAULT 0
);

drop table IF EXISTS sys_role;
create table sys_role (
  id char(32) PRIMARY KEY,
  code varchar(32) not null UNIQUE ,
  name varchar(64) not null,
  description VARCHAR(256),
  create_time DATETIME,
  update_time DATETIME,
  creator     char(32),
  updater     char(32),
  version     INT         NOT NULL DEFAULT 0
);

drop table if exists sys_permission;
create table sys_permission (
  id char(32) PRIMARY KEY,
  parent char(32) not null default -1,
  code varchar(32) not null UNIQUE ,
  name varchar(64) not null,
  action varchar(256),
  icon varchar(256),
  category INT not null,
  description VARCHAR(256),
  create_time DATETIME,
  update_time DATETIME,
  creator     char(32),
  updater     char(32),
  version     INT         NOT NULL DEFAULT 0
);

drop table if exists sys_user_role;
create table sys_user_role (
  id char(32) primary key,
  u_id char(32) not null,
  r_id char(32) not null
);

drop table if exists sys_role_permission;
create table sys_role_permission (
  id char(32) primary key,
  r_id char(32) not null,
  p_id char(32) not null
);


insert into sys_user(id, username, name, password, super_admin, create_time, creator)
    values('999', 'admin', 'Administrator', '431759ab506d1e4af78e7fe08b818edb', 1, now(), '999');

insert into sys_role(id, code, name, create_time, creator)
    values('999', 'admin', 'Administrator', now(), '999');

insert into sys_user_role(id, u_id, r_id)
    values(uuid_short(), '999', '999');

set @id1 = uuid_short();
set @id2 = uuid_short();
set @id3 = uuid_short();
insert into sys_permission(id, code, name, action, create_time, creator, parent, category)
    values(@id1, 'authc', '', 'home/content', now(), 1, 'root', 1);
insert into sys_permission(id, code, name, action, create_time, creator, parent, category)
    values(@id2, 'sysmanagement', 'System Managment', null, now(), 1, 'root', 1);
insert into sys_permission(id, code, name, action, create_time, creator, parent, category)
    values(@id3, 'user:list', 'User Managment', 'home/user_list', now(), 1, @id1, 1);

insert into sys_role_permission(id, r_id, p_id)
    values(uuid_short(), '999', @id1), (uuid_short(), '999', @id2), (uuid_short(), '999', @id3);




