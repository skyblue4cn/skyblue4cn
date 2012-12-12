#创建公司：
insert into tbCompany (id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(1,'人保财险成都市武侯支公司',0,'成都市武侯区','028-85599496','028-85597743','mumayqg@163.com','028-85597744 或 028-85597742') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(2,'测试旅行社',0,'小南街12号','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(3,'测试旅行社1',2,'小南街13号','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(4,'青城旅行社',0,'创业路12号','46123123','456456456','qingcheng@sohu.com','32356564') ;
insert into tbCompany(id,strCompanyName,intParentId,strAddress,strPhoneNumber1,strPhoneNumber2,strEamil,strFax) values(5,'青城旅行社2',4,'创业路12号','46123123','456456456','qingcheng@sohu.com','32356564') ;

#创建公司帐户
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(1,2,10000.00,'RMB',0) ;
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(2,3,5000.00,'RMB',0) ;
insert into tbAccount(id,intCompanyId,dbeResidual,strMoneyType,intIsSunUse) values(3,4,10000.00,'RMB',1) ;

