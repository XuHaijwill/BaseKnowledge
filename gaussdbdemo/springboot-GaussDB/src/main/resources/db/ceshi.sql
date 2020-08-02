-- 创建脚本
CREATE TABLE enjoy_user (
      id TINYINT
     ,passwd VARCHAR2 ( 255 )
     ,username VARCHAR2 ( 255 )
     ,PRIMARY KEY ( id ,passwd ,username )
          USING index tablespace fastspace
) tablespace fastspace pctfree 10 initrans 1 maxtrans 255 storage ( initial 3 M NEXT 1 M minextents 1 maxextents unlimited );

select * from enjoy_user;

-- 测试事务
CREATE TABLE account (
      id TINYINT
     ,money NUMBER
     ,name VARCHAR2 ( 255 )
     ,PRIMARY KEY ( id )
          USING index tablespace fastspace
) tablespace fastspace pctfree 10 initrans 1 maxtrans 255 storage ( initial 3 M NEXT 1 M minextents 1 maxextents unlimited );

insert INTO account(id,money,name) VALUES (1,22.00,'A');
insert INTO account(id,money,name) VALUES (2,22.00,'B');

select * from account;
