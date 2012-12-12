alter table tbpartment add column intIsUse int default 0 ;

alter table tbmonthpayoutlog change strDesc strDesc varchar(200) ;

alter table tbmonthpayoutlog change strDesc strDesc varchar(200) ;

alter table tbmonthpayinfo add column payType int ;

alter table tbmonthpayinfo add column dbeCurResidual double(7,1) ;

alter table tbmonthpayinfo add column dbeAftResidual double(7,1) ;

alter table tbusertype RENAME to tbroletype;

drop table if exists tbright;
create table tbright
(
   id                             int                            not null AUTO_INCREMENT,
   strName                        varchar(40),
   intRightType                   int,         
   strDesc						  varchar(200),
   primary key (id)
)
type = InnoDB;


create table tbroleright
(
   id                             int                            not null AUTO_INCREMENT,
   intRoleId                      int,
   intRightId                     int,
   primary key (id)
)
type = InnoDB;


create table tbresource
(
   id                             int                            not null AUTO_INCREMENT,
   intRightId                     int,
   strActionName                  varchar(30),
   strModuleName                  varchar(50),
   primary key (id)
)
type = InnoDB;

INSERT INTO `tbright` VALUES (1,'确认保单',1,'对保单进行确认');
INSERT INTO `tbright` VALUES (2,'处理批改申请',1,'处理批改申请');
INSERT INTO `tbright` VALUES (3,'修改已确认保单',1,'修改已确认保单');
INSERT INTO `tbright` VALUES (4,'管理旅行社及部门',1,'管理旅行社及部门');
INSERT INTO `tbright` VALUES (5,'管理账户',1,'管理旅行社账户');
INSERT INTO `tbright` VALUES (6,'收取费用',1,'对月费进行收取');
INSERT INTO `tbright` VALUES (7,'内部人员管理',1,'管理保险公司用户');
INSERT INTO `tbright` VALUES (8,'价格管理',1,'价格管理');
INSERT INTO `tbright` VALUES (9,'角色及权限管理',1,'角色及权限管理');

INSERT INTO `tbresource` VALUES (2,1,'toSureBillByBX','核单');
INSERT INTO `tbresource` VALUES (3,1,'sureBillByBxSuccess','确认保单');
INSERT INTO `tbresource` VALUES (4,1,'toReturnBillByBx','退回保单');
INSERT INTO `tbresource` VALUES (5,1,'toBeiAnBillbyBx','备案保单');
INSERT INTO `tbresource` VALUES (6,2,'toDealWithTheApplyBybx','回复批改申请');
INSERT INTO `tbresource` VALUES (7,3,'toUpdateBillByBx','修改已确认保单');
INSERT INTO `tbresource` VALUES (8,4,'toAddCompany','添加旅行社');
INSERT INTO `tbresource` VALUES (9,4,'toAddPartment','添加部门');
INSERT INTO `tbresource` VALUES (10,4,'getAllUserByPartmentId','管理部门用户');
INSERT INTO `tbresource` VALUES (11,4,'toAddLxsUser','添加旅行社的用户');
INSERT INTO `tbresource` VALUES (12,4,'toUpdateCompanyInfo','修改旅行社信息');
INSERT INTO `tbresource` VALUES (13,4,'toDeletePartment','删除旅行社');
INSERT INTO `tbresource` VALUES (14,5,'toUpdateAccountProperty','修改旅行社账户设置');
INSERT INTO `tbresource` VALUES (15,5,'toCunKuanForAccount','存款');
INSERT INTO `tbresource` VALUES (16,6,'toPayMonthFee','收费');
INSERT INTO `tbresource` VALUES (17,8,'toManagerPrice','管理价格');
INSERT INTO `tbresource` VALUES (18,7,'toManagerBxUser','管理内部用户');
INSERT INTO `tbresource` VALUES (19,9,'roleList','角色及权限管理');