#�趨�۸�
INSERT INTO tbAccidentalFee(id,intPartmentId,dbeOneDayFee,dbeTwoDayFee,dbeThreeDayFee,dbeFourDayFee,dbeFiveToSevenDayFee,dbeEightToNightDayFee,dbeTenToTwelveDayFee,dbeAboveTwelveDayFee,dbeThirtyDayFee,dbeAboveThirtyDayFee,dbeSelfDriveFee,dbeFreeManFeeRate,dbeSpecialItemFeeRate) VALUES(1,null,1.0,1.5,2,3,4,6,8,1,8,15,2,50,100) ;

INSERT INTO tbAdjustFee(id,intPartmentId,dbeChinaStandard,dbeOtherStandard)VALUES(1,null,0.2,1) ;

INSERT INTO tbFeeRate(id,intPartmentId,dbeRate,strDesc)VALUES(1,null,100,'ͨ�÷���') ;

#�趨��������
INSERT INTO tbBillKind(id,strKindName,strKindDesc,Column_4,Column_5) VALUES(1,'������','���������α�����Ա����ȷ�ϱ�',null,null) ;

INSERT INTO tbBillKind(id,strKindName,strKindDesc,Column_4,Column_5) VALUES(2,'������','���ڡ����������Ŷ������˺�����ԤԼͶ�������',null,null) ;


#�ʻ�֧������
INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(1,'Ԥ��','���ʻ���Ǯ') ;

INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(2,'����','���ʻ���Ǯ����') ;

INSERT INTO tbPayoutType (id,strName,strDesc) VALUES(3,'�˷�','�˻����ø��ʻ�') ;
