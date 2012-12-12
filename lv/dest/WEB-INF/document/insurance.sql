/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2008-10-23 13:29:09                          */
/*==============================================================*/


drop table if exists tbAccidentalFee;

drop table if exists tbAccount;

drop table if exists tbAdjustFee;

drop table if exists tbApply;

drop table if exists tbBill;

drop table if exists tbBillBackup;

drop table if exists tbBillKind;

drop table if exists tbBillState;

drop table if exists tbBillSureInfo;

drop table if exists tbCancelBillInfo;

drop table if exists tbFeeRate;

drop table if exists tbListForMonthPay;

drop table if exists tbMessage;

drop table if exists tbMonthPayInfo;

drop table if exists tbMonthPayOutLog;

drop table if exists tbPartment;

drop table if exists tbPayOutInfo;

drop table if exists tbPeiKuan;

drop table if exists tbRight;

drop table if exists tbRole;

drop table if exists tbTravelerInfo;

drop table if exists tbUser;

drop table if exists tbUserType;

drop table if exists tbYearPayInfo;

/*==============================================================*/
/* Table: tbAccidentalFee                                       */
/*==============================================================*/
create table tbAccidentalFee
(
   id                             int                            not null AUTO_INCREMENT,
   intPartmentId                  int,
   dbeOneDayFee                   double precision(7,1),
   dbeTwoDayFee                   double precision(7,1),
   dbeThreeDayFee                 double precision(7,1),
   dbeFourDayFee                  double precision(7,1),
   dbeFiveToSevenDayFee           double precision(7,1),
   dbeEightToNightDayFee          double precision(7,1),
   dbeTenToTwelveDayFee           double precision(7,1),
   dbeAboveTwelveDayFee           double precision(7,1),
   dbeThirtyDayFee                double precision(7,1),
   dbeAboveThirtyDayFee           double precision(7,1),
   dbeSelfDriveFee                double precision(7,1),
   dbeFreeManFeeRate              double precision(7,1),
   dbeSpecialItemFeeRate          double precision(7,1),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbAccount                                             */
/*==============================================================*/
create table tbAccount
(
   id                             int                            not null AUTO_INCREMENT,
   intPartmentId                  int,
   dbeResidual                    double precision(7,1),
   strMoneyType                   varchar(10)                    default 'RMB',
   intIsSonUse                    bit                            default 0,
   intPayTypeId                   int                            default 0,
   dteStartTimeToNoPay            datetime,
   dbeNeedMessage                 double precision(7,1)          default 100.0,
   intAcceptDays                  int                            default 30,
   dbeAcceptMaxMoney              double precision(7,1)          default 100.0,
   intAccountState                int                            default 1,
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbAdjustFee                                           */
/*==============================================================*/
create table tbAdjustFee
(
   id                             int                            not null AUTO_INCREMENT,
   intPartmentId                  int                            default 0,
   dbeChinaStandard               double precision(7,1),
   dbeOtherStandard               double precision(7,1),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbApply                                               */
/*==============================================================*/
create table tbApply
(
   id                             int                            not null AUTO_INCREMENT,
   intFromUserId                  int,
   strApplyUserName               varchar(255),
   intBillId                      int,
   intPartmentId                  int,
   dteApplyTime                   datetime,
   strApplyContent                varchar(255),
   intIsReply                     int                            default 0,
   intReplyUserId                 int,
   strReplyUserName               varchar(255),
   strReplyContent                varchar(255),
   dteReplyTime                   datetime,
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbBill                                                */
/*==============================================================*/
create table tbBill
(
   id                             int                            not null,
   strBillNumber                  varchar(50),
   strBillName                    varchar(50),
   strCompanyName                 varchar(50),
   intKindId                      int,
   intApplyUserId                 int,
   dteApplyDate                   datetime,
   strPartmentName                varchar(50),
   intPartmentID                  int,
   strSignatoryName               varchar(50),
   strDragoman                    varchar(50),
   strPhoneNumber                 varchar(50),
   strFax                         varchar(50),
   intTeamType                    tinyint                        default 0,
   strTeamNumber                  varchar(50),
   intTravelProperty              tinyint                        default 0,
   strTravelRold                  varchar(200),
   intChinaTravelerNumber         int                            default 0,
   intOtherTravelerNumber         int                            default 0,
   dbeChinaFee                    double precision(7,1),
   dbeOtherFee                    double precision(7,1),
   dbeAllFee                      double precision(7,1),
   dteStartTime                   datetime,
   dteEndTime                     datetime,
   intTravelType                  int                            default 0,
   intIsHasHighDanger             bit                            default 0,
   intIsAddtraveler               int                            default 0,
   strFileUrl                     varchar(100),
   intIsSureByZs                  int                            default 0,
   intPayType                     int,
   intIsPay                       int                            default -1,
   intBeiAnReason                 int                            default 0,
   strReturnReason                varchar(200),
   intBillStateId                 int,
   intIsPeiKuan                   int,
   strSureUserName                varchar(50),
   dteSureTime                    datetime,
   primary key (id)
)
type = InnoDB;






/*==============================================================*/
/* Table: tbBillBackup                                          */
/*==============================================================*/
create table tbBillBackup
(
   id                             int                            not null AUTO_INCREMENT,
   intBillId                      int,
   strBillNumber                  varchar(50),
   strBillName                    varchar(50),
   strCompanyName                 varchar(50),
   intKindId                      int,
   intApplyUserId                 int,
   dteApplyDate                   datetime,
   strPartmentName                varchar(50),
   intPartmentID                  int,
   strSignatoryName               varchar(50),
   strDragoman                    varchar(50),
   strPhoneNumber                 varchar(50),
   strFax                         varchar(50),
   intTeamType                    tinyint                        default 0,
   strTeamNumber                  varchar(50),
   intTravelProperty              tinyint                        default 0,
   strTravelRold                  varchar(200),
   intChinaTravelerNumber         int                            default 0,
   intOtherTravelerNumber         int                            default 0,
   dbeChinaFee                    double precision(7,1),
   dbeOtherFee                    double precision(7,1),
   dbeAllFee                      double precision(7,1),
   dteStartTime                   datetime,
   dteEndTime                     datetime,
   intTravelType                  int                            default 0,
   intIsHasHighDanger             bit                            default 0,
   intIsAddtraveler               int                            default 0,
   strFileUrl                     varchar(100),
   intIsSureByZs                  int                            default 0,
   intPayType                     int,
   intIsPay                       int                            default -1,
   intBeiAnReason                 int                            default 0,
   strReturnReason                varchar(200),
   intBillStateId                 int,
   intIsPeiKuan                   int,
   strSureUserName                varchar(50),
   dteSureTime                    datetime,
   intBxUpdateUserId              int,
   strBxUpdateUserName            varchar(50),
   strBxUpdateDesc                varchar(255),
   dteBxUpdateTime                datetime,
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbBillKind                                            */
/*==============================================================*/
create table tbBillKind
(
   id                             int                            not null AUTO_INCREMENT,
   strKindName                    varchar(50),
   strKindDesc                    varchar(200),
   Column_4                       varchar(50),
   Column_5                       varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbBillState                                           */
/*==============================================================*/
create table tbBillState
(
   id                             int                            not null,
   strDesc                        varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbBillSureInfo                                        */
/*==============================================================*/
create table tbBillSureInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intBillId                      int,
   intSureType                    int,
   intSureUserId                  int,
   strSureUserName                varchar(50),
   intCurBillState                int,
   intAftBillState                int,
   dteSureTime                    datetime,
   strAdminDesc                   varchar(100),
   strDesc                        varchar(100),
   primary key (id)
)
type = InnoDB;




/*==============================================================*/
/* Table: tbCancelBillInfo                                      */
/*==============================================================*/
create table tbCancelBillInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intBillId                      int,
   intUserId                      int,
   strReason                      varchar(200),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbFeeRate                                             */
/*==============================================================*/
create table tbFeeRate
(
   id                             int                            not null AUTO_INCREMENT,
   intPartmentId                  int                            default 0,
   dbeRate                        double precision(7,1),
   strDesc                        varchar(100),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbListForMonthPay                                     */
/*==============================================================*/
create table tbListForMonthPay
(
   id                             int                            not null AUTO_INCREMENT,
   intMonthPayId                  int,
   intBillId                      int,
   intPayMonthFeeId               int                            default 0,
   intBillState                   int                            default 1,
   primary key (id)
)
type = InnoDB;




/*==============================================================*/
/* Table: tbMessage                                             */
/*==============================================================*/
create table tbMessage
(
   id                             int                            not null AUTO_INCREMENT,
   dteTime                        datetime,
   strContent                     varchar(200),
   intMessageType                 int,
   intFromPartment                int,
   intToPartmentId                int,
   intIsHasShow                   int                            default -1,
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbMonthPayInfo                                        */
/*==============================================================*/
create table tbMonthPayInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intAccountId                   int,
   strYear                        varchar(50),
   strMonth                       varchar(50),
   dteStartTime                   datetime,
   dteEndTime                     datetime,
   dteJieSuanTime                 datetime,
   dbeNeedToPay                   double precision(7,1),
   dbeHfFee                       double precision(7,1),
   dbePayNumber                   double precision(7,1),
   intIsPay                       int,
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbMonthPayOutLog                                      */
/*==============================================================*/
create table tbMonthPayOutLog
(
   id                             int                            not null AUTO_INCREMENT,
   intMonthPayId                  int,
   dbePayNumber                   double precision(7,1),
   dtePayDate                     datetime,
   intUserId                      int,
   strUserName                    varchar(50),
   strDesc                        varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbPartment                                            */
/*==============================================================*/
create table tbPartment
(
   id                             int                            not null AUTO_INCREMENT,
   strPartmentName                varchar(50),
   intParentId                    int,
   strAddress                     varchar(50),
   strLicenceNumber               varchar(50),
   strPhoneNumber                 varchar(50),
   strZipcode                     varchar(50),
   strFax                         varchar(50),
   strConnectPeople               varchar(50),
   intIsNeedSureBill              int,
   dteResStartDate                datetime,
   dteResEndDate                  datetime,
   strImgUrl                      varchar(50),
   strLxsBillNumber               varchar(50),
   intIsSeePartmentBill           int                            default 0,
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbPayOutInfo                                          */
/*==============================================================*/
create table tbPayOutInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intAccountId                   int,
   intType                        int,
   dbePayoutNumber                double precision(7,1),
   dtePayoutTime                  datetime,
   dbeCurResidual                 double precision(7,1),
   dbeAftResidual                 double precision(7,1),
   strDesc                        varchar(200),
   intFromUserId                  int,
   intBillId                      int,
   strSaveUserName                varchar(30),
   primary key (id)
)
type = InnoDB;





/*==============================================================*/
/* Table: tbPeiKuan                                             */
/*==============================================================*/
create table tbPeiKuan
(
   id                             int                            not null AUTO_INCREMENT,
   intBillId                      int,
   dbePeiKuanFee                  double precision(7,1),
   strDesc                        varchar(100),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbRight                                               */
/*==============================================================*/
create table tbRight
(
   id                             int                            not null,
   intRoleId                      int,
   strRight                       varchar(50),
   strDesc                        varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbRole                                                */
/*==============================================================*/
create table tbRole
(
   id                             int                            not null AUTO_INCREMENT,
   strRoleName                    varchar(50),
   intTypeId                      int,
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbTravelerInfo                                        */
/*==============================================================*/
create table tbTravelerInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intBillId                      int,
   strTravelerName                varchar(50),
   strSex                         varchar(50),
   strBirthday                    varchar(50),
   strCountry                     varchar(50),
   strIndentyNumber               varchar(50),
   primary key (id)
)
type = InnoDB;



/*==============================================================*/
/* Table: tbUser                                                */
/*==============================================================*/
create table tbUser
(
   id                             int                            not null AUTO_INCREMENT,
   strUserLogName                 varchar(50),
   strUserPassword                varchar(100),
   intPartmentId                  int,
   strUserName                    varchar(50),
   strPhoneNumber                 varchar(50),
   strEmail                       varchar(50),
   intRoleId                      int,
   intUserState                   int                            default 0,
   primary key (id)
)
type = InnoDB;




/*==============================================================*/
/* Table: tbUserType                                            */
/*==============================================================*/
create table tbUserType
(
   id                             int                            not null AUTO_INCREMENT,
   strTypeName                    varchar(50),
   strDesc                        varchar(50),
   primary key (id)
)
type = InnoDB;

/*==============================================================*/
/* Table: tbYearPayInfo                                         */
/*==============================================================*/
create table tbYearPayInfo
(
   id                             int                            not null AUTO_INCREMENT,
   intAccountId                   int,
   dbeNeedFeeByYear               double precision(7,1),
   dbePayFee                      double precision(7,1),
   dbeCurResidual                 double precision(7,1),
   dbeAftResidual                 double precision(7,1),
   dtePayDate                     datetime,
   dteStartTime                   datetime,
   dteEndTime                     datetime,
   intUser                        int,
   strUserName                    varchar(50),
   strDesc                        varchar(100),
   primary key (id)
)
type = InnoDB;




alter table tbAccidentalFee add constraint FK_Reference_16 foreign key (intPartmentId)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbAccount add constraint FK_Reference_23 foreign key (intPartmentId)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbAdjustFee add constraint FK_Reference_20 foreign key (intPartmentId)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbBill add constraint FK_Reference_25 foreign key (intPartmentID)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbBill add constraint FK_Reference_31 foreign key (intBillStateId)
      references tbBillState (id) on delete restrict on update restrict;

alter table tbBill add constraint FK_Reference_5 foreign key (intKindId)
      references tbBillKind (id) on delete restrict on update restrict;

alter table tbBill add constraint FK_Reference_7 foreign key (intApplyUserId)
      references tbUser (id) on delete restrict on update restrict;

alter table tbBillSureInfo add constraint FK_Reference_28 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbBillSureInfo add constraint FK_Reference_29 foreign key (intSureUserId)
      references tbUser (id) on delete restrict on update restrict;

alter table tbCancelBillInfo add constraint FK_Reference_13 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbFeeRate add constraint FK_Reference_27 foreign key (intPartmentId)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbListForMonthPay add constraint FK_Reference_38 foreign key (intMonthPayId)
      references tbMonthPayInfo (id) on delete restrict on update restrict;

alter table tbListForMonthPay add constraint FK_Reference_39 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbMonthPayInfo add constraint FK_Reference_32 foreign key (intAccountId)
      references tbAccount (id) on delete restrict on update restrict;

alter table tbMonthPayOutLog add constraint FK_Reference_40 foreign key (intMonthPayId)
      references tbMonthPayInfo (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_10 foreign key (intFromUserId)
      references tbUser (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_12 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbPayOutInfo add constraint FK_Reference_8 foreign key (intAccountId)
      references tbAccount (id) on delete restrict on update restrict;

alter table tbPeiKuan add constraint FK_Reference_33 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbRight add constraint FK_Reference_30 foreign key (intRoleId)
      references tbRole (id) on delete restrict on update restrict;

alter table tbRole add constraint FK_Reference_22 foreign key (intTypeId)
      references tbUserType (id) on delete restrict on update restrict;

alter table tbTravelerInfo add constraint FK_Reference_11 foreign key (intBillId)
      references tbBill (id) on delete restrict on update restrict;

alter table tbUser add constraint FK_Reference_15 foreign key (intPartmentId)
      references tbPartment (id) on delete restrict on update restrict;

alter table tbUser add constraint FK_Reference_26 foreign key (intRoleId)
      references tbRole (id) on delete restrict on update restrict;

alter table tbYearPayInfo add constraint FK_Reference_34 foreign key (intUser)
      references tbUser (id) on delete restrict on update restrict;

alter table tbYearPayInfo add constraint FK_Reference_35 foreign key (intAccountId)
      references tbAccount (id) on delete restrict on update restrict;

