#设定价格
INSERT INTO tbAccidentalFee(id,intPartmentId,dbeOneDayFee,dbeTwoDayFee,dbeThreeDayFee,dbeFourDayFee,dbeFiveToSevenDayFee,dbeEightToNightDayFee,dbeTenToTwelveDayFee,dbeAboveTwelveDayFee,dbeThirtyDayFee,dbeAboveThirtyDayFee,dbeSelfDriveFee,dbeFreeManFeeRate,dbeSpecialItemFeeRate) VALUES(1,null,1.0,1.5,2,3,4,6,8,1,8,15,2,50,100) ;

INSERT INTO tbAdjustFee(id,intPartmentId,dbeChinaStandard,dbeOtherStandard)VALUES(1,null,0.2,1) ;

INSERT INTO tbFeeRate(id,intPartmentId,dbeRate,strDesc)VALUES(1,null,100,'通用费率') ;

#设定保险种类
INSERT INTO tbBillKind(id,strKindName,strKindDesc,Column_4,Column_5) VALUES(1,'责任险','旅行社责任保险人员调节确认表',null,null) ;

INSERT INTO tbBillKind(id,strKindName,strKindDesc,Column_4,Column_5) VALUES(2,'意外险','境内、境外旅游团队人身伤害保险预约投保申请表',null,null) ;


#帐户支出类型
INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(1,'预存','给帐户存钱') ;

INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(2,'交费','从帐户扣钱交费') ;

INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(3,'退费','退还费用给帐户') ;
