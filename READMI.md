# PageHelper、springSecurity 练习
> 只完成了后台的代码

## 一、数据库表
```
-- 【创建用户 sys用户下操作】
create user travel identified by ***;
grant connect, resource to travel;
-- 【切换到 travel 用户下】


-- 【产品表 product 】
CREATE TABLE product(
    id varchar2(32) default SYS_GUID() PRIMARY KEY, -- 无意义，主键uuid
    productNum VARCHAR2(50) NOT NULL, -- 产品编号，唯一，不为空
    productName VARCHAR2(50), -- 产品名称（路线名称）
    cityName VARCHAR2(50), -- 出发城市
    DepartureTime timestamp, -- 出发时间
    productPrice Number, -- 产品价格
    productDesc VARCHAR2(500), -- 产品描述
    productStatus INT, -- 状态(0 关闭 1 开启)
    CONSTRAINT product UNIQUE (id, productNum) -- 唯一约束
)
insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice,
productdesc, productstatus)
values ('676C5BD1D35E429A8C2E114939C5685A', 'itcast-002', '北京三日游', '北京', to_timestamp('10-
10-2018 10:10:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1200, '不错的旅行', 1);
insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice,
productdesc, productstatus)
values ('12B7ABF2A4C544568B0A7C69F36BF8B7', 'itcast-003', '上海五日游', '上海', to_timestamp('25-
04-2018 14:30:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1800, '魔都我来了', 0);
insert into PRODUCT (id, productnum, productname, cityname, departuretime, productprice,
productdesc, productstatus)
values ('9F71F01CB448476DAFB309AA6DF9497F', 'itcast-001', '北京三日游', '北京', to_timestamp('10-
10-2018 10:10:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1200, '不错的旅行', 1);
commit;

----------------------------
select * from product;
----------------------------


-- 【会员表 member】
drop table member;
CREATE TABLE member(
       id varchar2(32) default SYS_GUID() PRIMARY KEY,
       NAME VARCHAR2(20),
       nickname VARCHAR2(20),
       phoneNum VARCHAR2(20),
       email VARCHAR2(20)
);
insert into MEMBER (id, name, nickname, phonenum, email)
values ('E61D65F673D54F68B0861025C69773DB', '张三', '小三', '18888888888', 'zs@163.com');
commit;


-- 【订单表 order】
drop table orders;
CREATE TABLE orders(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  orderNum VARCHAR2(20) NOT NULL UNIQUE,
  orderTime timestamp,
  peopleCount INT,
  orderDesc VARCHAR2(500),
  payType INT,
  orderStatus INT,
  productId varchar2(32),
  memberId varchar2(32),
  FOREIGN KEY (productId) REFERENCES product(id),
  FOREIGN KEY (memberId) REFERENCES member(id)
);
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('0E7231DC797C486290E8713CA3C6ECCC', '12345', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('5DC6A48DD4E94592AE904930EA866AFA', '54321', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('2FF351C4AC744E2092DCF08CFD314420', '67890', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('A0657832D93E4B10AE88A2D4B70B1A28', '98765', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('E4DD4C45EED84870ABA83574A801083E', '11111', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('96CC8BD43C734CC2ACBFF09501B4DD5D', '22222', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('55F9AF582D5A4DB28FB4EC3199385762', '33333', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('CA005CF1BE3C4EF68F88ABC7DF30E976', '44444', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
insert into ORDERS (id, ordernum, ordertime, peoplecount, orderdesc, paytype, orderstatus, productid, memberid)
values ('3081770BC3984EF092D9E99760FDABDE', '55555', to_timestamp('02-03-2018 12:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
commit;



-- 【旅客 traveller】
drop table traveller;
CREATE TABLE traveller(
  id varchar2(32) default SYS_GUID() PRIMARY KEY,
  NAME VARCHAR2(20),
  sex VARCHAR2(20),
  phoneNum VARCHAR2(20),
  credentialsType INT,
  credentialsNum VARCHAR2(50),
  travellerType INT
);
insert into TRAVELLER (id, name, sex, phonenum, credentialstype, credentialsnum, travellertype)
values ('3FE27DF2A4E44A6DBC5D0FE4651D3D3E', '张龙', '男', '13333333333', 0, '123456789009876543', 0);
insert into TRAVELLER (id, name, sex, phonenum, credentialstype, credentialsnum, travellertype)
values ('EE7A71FB6945483FBF91543DBE851960', '张小龙', '男', '15555555555', 0, '987654321123456789', 1);
commit;


-- 【订单与旅客中间表  order_traveller】
drop table order_traveller;
CREATE TABLE order_traveller(
  orderId varchar2(32),
  travellerId varchar2(32),
  PRIMARY KEY (orderId,travellerId),
  FOREIGN KEY (orderId) REFERENCES orders(id),
  FOREIGN KEY (travellerId) REFERENCES traveller(id)
);

insert into ORDER_TRAVELLER (orderid, travellerid)
values ('0E7231DC797C486290E8713CA3C6ECCC', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('2FF351C4AC744E2092DCF08CFD314420', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('3081770BC3984EF092D9E99760FDABDE', 'EE7A71FB6945483FBF91543DBE851960');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('55F9AF582D5A4DB28FB4EC3199385762', 'EE7A71FB6945483FBF91543DBE851960');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('5DC6A48DD4E94592AE904930EA866AFA', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('96CC8BD43C734CC2ACBFF09501B4DD5D', 'EE7A71FB6945483FBF91543DBE851960');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('A0657832D93E4B10AE88A2D4B70B1A28', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('CA005CF1BE3C4EF68F88ABC7DF30E976', 'EE7A71FB6945483FBF91543DBE851960');
insert into ORDER_TRAVELLER (orderid, travellerid)
values ('E4DD4C45EED84870ABA83574A801083E', 'EE7A71FB6945483FBF91543DBE851960');
commit;

【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】

-- 【用户表 users】
CREATE TABLE users(
    id varchar2(32) default SYS_GUID() PRIMARY KEY, -- 无意义，主键uuid
    email VARCHAR2(50) UNIQUE NOT NULL, -- 非空，唯一
    username VARCHAR2(50), -- 用户名
    PASSWORD VARCHAR2(100), -- 密码（加密）
    phoneNum VARCHAR2(20), -- 电话
    STATUS INT -- 状态0 未开启 1 开启
);

insert into users values
       ('GC006', 'gouchan006@tegong.com', '006', 'abc123', '18190113344', 0);
insert into users values
       ('GC007', 'gouchan007@tegong.com', '007', 'abc123', '18190998899', 1);
commit;
select * from users;

update users set status = 1 where username = '006';
update users set status = 0 where username = '008';
commit;

alter table users modify PASSWORD varchar(100);
update users set PASSWORD = '$2a$10$tAQT1ups7ZzY3F1xcqdWDu5eyZgBjaZnSqvXOROaOL7a.RLbDq7tu' where id = 'GC006';
update users set PASSWORD = '$2a$10$1hJaHC.s4SekzDnkaFjqYez9D1mS1srkUDajHjeSMdzQxsiUTTylC' where id = 'GC007';
commit;

-- 【角色表 role】
CREATE TABLE role(
    id varchar2(32) default SYS_GUID() PRIMARY KEY, -- 无意义，主键uuid
    roleName VARCHAR2(50), -- 角色名
    roleDesc VARCHAR2(50) -- 角色描述
);

insert into role(roleName, roledesc) values ('USER', '使用者');
insert into role(roleName, roledesc) values ('ADMIN', '超级管理员');
commit;
select * from role;
update role set roleName = 'BOSS', roleDesc = '大老板' where id = '2DC1144DE0EE43A89D984AA1BAE55C7B';
commit;

-- 【用户角色关联表 users_role】
CREATE TABLE users_role(
    userId varchar2(32),
    roleId varchar2(32),
    PRIMARY KEY(userId,roleId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (roleId) REFERENCES role(id)
);

insert into users_role values('GC006', 'F3F829E8C19E4D4FB10DE0AEF7198F6D');
insert into users_role values('GC007', '21B5A7D8098E4C53907E4E9E8204D2A6');
insert into users_role values('755EE41F9AAE43769D67BC7DFE1F5F2D', '21B5A7D8098E4C53907E4E9E8204D2A6');
commit;

select * from users_role;


-- 【资源权限表 permission】
CREATE TABLE permission(
    id varchar2(32) default SYS_GUID() PRIMARY KEY, -- 无意义，主键uuid
    permissionName VARCHAR2(50) , -- 权限名
    url VARCHAR2(50) -- 资源路径
);

insert into permission(permissionName, url) values('查询全部产品信息', '/product/findAll');
insert into permission(permissionName, url) values('查询全部订单信息', '/order/findAll');
insert into permission(permissionName, url) values('查询全部订单信息', '/role/findAll');
commit;

update permission set permissionName = '查询全部用户信息', url = '/user/findAll';
select * from permission;

-- 【角色权限关联表 role_permission】
CREATE TABLE role_permission(
    permissionId varchar2(32),
    roleId varchar2(32),
    PRIMARY KEY(permissionId,roleId),
    FOREIGN KEY (permissionId) REFERENCES permission(id),
    FOREIGN KEY (roleId) REFERENCES role(id)
);

insert into role_permission values('86878D65368E4C5EBA78D31E3723D4EF','F3F829E8C19E4D4FB10DE0AEF7198F6D');
commit;

select permissionId from role_permission where roleId = 'F3F829E8C19E4D4FB10DE0AEF7198F6D';
select * from permission where id in ('86878D65368E4C5EBA78D31E3723D4EF');

select * from role_permission;


【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】
【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】【0】【1】

-- 【日志表 sysLog】
create table sysLog(
       id varchar(32) default sys_guid() primary key, -- 主键 无意义uuid
       visitTime timestamp, -- 访问时间
       username varchar(50), -- 操作者用户名
       ip varchar(30), -- 访问ip
       url varchar(50), -- 访问资源url
       executionTime int, -- 执行时长
       method varchar(200) -- 访问方法
);
```


## 二、接口
> 详见 `travel后台管理系统.postman_collection.json`
