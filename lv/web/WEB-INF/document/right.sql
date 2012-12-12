/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2009-6-6 21:11:11                            */
/*==============================================================*/


drop table if exists tbAction;

drop table if exists tbRight;

drop table if exists tbRightType;

drop table if exists tbRoleRight;

/*==============================================================*/
/* Table: tbAction                                              */
/*==============================================================*/
create table tbAction
(
   id                   int not null,
   strActionName        varchar(50),
   intRightId           int,
   primary key (id)
);

/*==============================================================*/
/* Table: tbRight                                               */
/*==============================================================*/
create table tbRight
(
   id                   int not null auto_increment,
   strName              varchar(50),
   primary key (id)
);


/*==============================================================*/
/* Table: tbRoleRight                                           */
/*==============================================================*/
create table tbRoleRight
(
   id                   int not null auto_increment,
   intRoleId            int,
   intRightId           int,
   primary key (id)
);
