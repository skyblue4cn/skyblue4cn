#������˾��
insert into tbCompany (id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(1,'�˱����ճɶ������֧��˾',0,'�ɶ��������','028-85599496','028-85597743','mumayqg@163.com','028-85597744 �� 028-85597742') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(2,'����������',0,'С�Ͻ�12��','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(3,'����������1',2,'С�Ͻ�13��','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(4,'���������',0,'��ҵ·12��','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(5,'���������2',4,'��ҵ·12��','46123123','456456456','qingcheng@sohu.com','32356564') ;

#������˾�ʻ�
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(1,2,10000.00,'RMB',0) ;
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(2,3,5000.00,'RMB',0) ;
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(3,4,10000.00,'RMB',1) ;

