/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2008-5-8 9:55:34                             */
/*==============================================================*/


drop table if exists tbAccount;

drop table if exists tbBillKind;

drop table if exists tbBillType;

drop table if exists tbCancelBillInfo;

drop table if exists tbCompany;

drop table if exists tbInsuranceBill;

drop table if exists tbPayOutInfo;

drop table if exists tbPayoutType;

drop table if exists tbTravelerInfo;

drop table if exists tbUser;

/*==============================================================*/
/* Table: tbAccount                                             */
/*==============================================================*/
create table tbAccount
(
   id                             int                            not null,
   intCompanyId                   int,
   dbeResidual                    double,
   strMoneyType                   varchar(10)                    default 'RMB',
   intIsSunUse                    bit,
   Column_6                       varchar(50),
   Column_7                       varchar(50),
   Column_8                       varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbBillKind                                            */
/*==============================================================*/
create table tbBillKind
(
   id                             int                            not null,
   strKindName                    varchar(50),
   strKindDesc                    varchar(200),
   Column_4                       varchar(50),
   Column_5                       varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbBillType                                            */
/*==============================================================*/
create table tbBillType
(
   id                             int                            not null,
   strTypeName                    varchar(50),
   strTypeDesc                    varchar(200),
   Column_4                       varchar(50),
   Column_5                       varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbCancelBillInfo                                      */
/*==============================================================*/
create table tbCancelBillInfo
(
   id                             int                            not null,
   intBillId                      int,
   dteApplyDate                   datetime,
   intApplyUserId                 int,
   strUserName                    varchar(50),
   strReason                      varchar(200),
   dteCancelDate                  datetime,
   intOperateUserId               int,
   Column_8                       varchar(50),
   Column_9                       varchar(50),
   Column_10                      varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbCompany                                             */
/*==============================================================*/
create table tbCompany
(
   id                             int                            not null,
   strCompanyName                 varchar(50),
   intParentId                    int,
   strAddress                     varchar(100),
   strPhoneNumber1                varchar(50),
   strPhoneNumber2                varchar(50),
   strEamil                       varchar(50),
   strFax                         varchar(50),
   Column_10                      varchar(50),
   Column_11                      varchar(50),
   Column_12                      varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbInsuranceBill                                       */
/*==============================================================*/
create table tbInsuranceBill
(
   id                             int                            not null,
   intKindId                      int,
   intTypeId                      int,
   intUserId                      int,
   dteApplyDate                   datetime,
   strPartmentName                varchar(50),
   strSignatoryName               varchar(50),
   strDragoman                    varchar(50),
   strPhoneNumber                 varchar(50),
   strFax                         varchar(50),
   intTeamType                    tinyint,
   strTeamNumber                  varchar(50),
   strTravelRold                  varchar(200),
   intChinaTravelerNumber         int,
   intOtherTravelerNumber         int,
   dteStartTime                   datetime,
   dteEndTime                     datetime,
   strTravelType                  char,
   intIsHasHighDanger             bit,
   intIsEffective                 tinyint                        default 1,
   Column_21                      varchar(50),
   Column_22                      varchar(50),
   Column_23                      varchar(50),
   strCompanyName                 varchar(50),
   dbeNeedToCharge                double,
   intIsSendSure                  bit,
   intIsTravelSure                bit,
   Column_28                      varchar(50),
   Column_29                      varchar(50),
   Column_30                      varchar(50),
   primary key (id)
)
type = InnoDB;





/*==============================================================*/
/* Table: tbPayOutInfo                                          */
/*==============================================================*/
create table tbPayOutInfo
(
   id                             int                            not null,
   intAccountId                   int,
   intPayoutTypeId                int,
   dbePayoutNumber                double,
   dtePayoutTime                  datetime,
   strDesc                        varchar(200),
   intFromUserId                  int,
   intBillId                      int,
   strSaveUserName                varchar(30),
   Column_9                       varchar(50),
   Column_10                      varchar(50),
   Column_11                      varchar(50),
   primary key (id)
)
type = InnoDB;






/*==============================================================*/
/* Table: tbPayoutType                                          */
/*==============================================================*/
create table tbPayoutType
(
   id                             int                            not null,
   strName                        varchar(50),
   strDesc                        varchar(50),
   Column_4                       varchar(50),
   Column_5                       varchar(50),
   Column_6                       varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbTravelerInfo                                        */
/*==============================================================*/
create table tbTravelerInfo
(
   id                             int                            not null,
   intBillId                      int,
   strTravelerName                varchar(50),
   strCountry                     varchar(50),
   strIndentyNumber               varchar(50),
   intIsForeigner                 tinyint,
   Column_7                       varchar(50),
   Column_8                       varchar(50),
   Column_9                       varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbUser                                                */
/*==============================================================*/
create table tbUser
(
   id                             int                            not null,
   strUserLogName                 varchar(50),
   strUserPassword                varchar(100),
   intCompanyId                   int,
   strUserName                    varchar(50),
   strPhoneNumber                 varchar(50),
   strEmail                       varchar(50),
   intRoleId                      int,
   Column_10                      varchar(50),
   Column_11                      varchar(50),
   Column_12                      varchar(50),
   primary key (id)
)
type = InnoDB;



alter table tbAccount add constraint FK_Reference_9 foreign key (intCompanyId)
      references tbCompany (id) on delete restrict on update restrict;

alter table tbCancelBillInfo add constraint FK_Reference_13 foreign key (intBillId)
      references tbInsuranceBill (id) on delete restrict on update restrict;

alter table tbInsuranceBill add constraint FK_Reference_5 foreign key (intKindId)
      references tbBillKind (id) on delete restrict on update restrict;

alter table tbInsuranceBill add constraint FK_Reference_6 foreign key (intTypeId)
      references tbBillType (id) on delete restrict on update restrict;

alter table tbInsuranceBill add constraint FK_Reference_7 foreign key (intUserId)
      references tbUser (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_10 foreign key (intFromUserId)
      references tbUser (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_12 foreign key (intBillId)
      references tbInsuranceBill (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_14 foreign key (intPayoutTypeId)
      references tbPayoutType (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_8 foreign key (intAccountId)
      references tbAccount (id) on delete restrict on update restrict;

alter table tbTravelerInfo add constraint FK_Reference_11 foreign key (intBillId)
      references tbInsuranceBill (id) on delete restrict on update restrict;

alter table tbUser add constraint FK_Reference_4 foreign key (intCompanyId)
      references tbCompany (id) on delete restrict on update restrict;

