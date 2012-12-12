package cn.insurance.service.admin.impl;

import java.io.File;
import java.util.List;

import cn.insurance.dao.ITbAccidentalFeeDao;
import cn.insurance.dao.ITbAccountDao;
import cn.insurance.dao.ITbAdjustFeeDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccidentalFee;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbAdjustFee;
import cn.insurance.model.TbPartment;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.utils.CommonWords;
import cn.insurance.utils.FileUpload;

public class AdminPartmentServ implements IAdminPartmentServ{
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbAccountDao tbAccountDao ;
	
	private ITbAdjustFeeDao tbAdjustFeeDao ;
	
	private ITbAccidentalFeeDao tbAccidentalFeeDao ;
	
	
	
	/*
	 * 添加部门
	 * 给每个添加的部门创建一个默认的帐户
	 * 给每个添加的部门创建一个费率表
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#addTbPartment(cn.insurance.model.TbPartment)
	 */
	public boolean addTbPartment(TbPartment tbPartment , File imgFile , String fileName) {
		/*在dao里专门返回这个添加的部门ID，就是为了 添加对应的帐户*/
		Integer id = tbPartmentDao.create(tbPartment) ;
		if(id == 0){
			return false ;
		}
		/*上传旅行社或部门的图片盖章*/
		if(imgFile != null){
			try{
				
					fileName ="partment" + id + fileName.substring(fileName.lastIndexOf(".")) ;
					FileUpload.uploadFile(imgFile, "partmentFile", fileName) ;
					tbPartment.setId(id) ;
					tbPartment.setStrImgUrl("partmentFile/" + fileName) ;
					tbPartmentDao.update(tbPartment) ;
			}catch(Exception e){
				return false ;
			}
		}
		
		/*在创建旅行社或部门的时候自动的创建一个帐户*/
		TbAccount tbAccount = new TbAccount() ;
		tbAccount.setIntPartmentId(id) ;
		/*帐户初始资金为0*/
		tbAccount.setDbeResidual(0.0) ;
		/*默认为下属部门使用本部门的资金*/
		tbAccount.setIntIsSonUse(0) ;
		/*默认为月结费方式支付*/
		tbAccount.setIntPayTypeId(CommonWords.payType2) ;
		/*帐户状态设置为可用*/
		tbAccount.setIntAccountState(CommonWords.accountState2) ;
		tbAccountDao.create(tbAccount) ;
		/*给每个旅行社设定一个价格，暂时为通用价格（id=1）*/
		if(tbPartment.getIntParentId() ==0){
			TbAdjustFee tbAdjustFee = (TbAdjustFee)tbAdjustFeeDao.getObjectInfoById(1) ;
			TbAccidentalFee tbAccidentalFee = (TbAccidentalFee) tbAccidentalFeeDao.getObjectInfoById(1) ;
			tbAdjustFee.setIntPartmentId(id) ;
			tbAccidentalFee.setIntPartmentId(id) ;
			/*ID可设可不设，这里设定为NULL*/
			tbAdjustFee.setId(null) ;
			tbAccidentalFee.setId(null) ;
			/*添加，为了以后扩展对每个旅行社单独设定价格*/
			tbAdjustFeeDao.create(tbAdjustFee) ;
			tbAccidentalFeeDao.create(tbAccidentalFee) ;
		}
		return true ;
	}
	
	/*
	 * 更新部门信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#updateTbPartment(cn.insurance.model.TbPartment)
	 */
	public int updateTbPartment(TbPartment tbPartment ,File imgFile,String fileName){
		/*上传旅行社或部门的图片盖章*/
		if(imgFile != null){
			try{
				fileName ="partment" + tbPartment.getId() + fileName.substring(fileName.lastIndexOf(".")) ;
				FileUpload.uploadFile(imgFile, "partmentFile", fileName) ;
				tbPartment.setId(tbPartment.getId()) ;
				tbPartment.setStrImgUrl("partmentFile/" + fileName) ;
			}catch(Exception e){
				return 0 ;
			}
		}
		return tbPartmentDao.update(tbPartment) ;
	}
	
	/*
	 * 查询所有旅行社的信息,intParentId=0
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#queryAllCompanyList()
	 */
	public List<TbPartment> getAllCompanyList() {
		List<TbPartment> list = tbPartmentDao.getAllCompanyList() ;
		return list;
	}
	
	/*
	 * 根据旅行社ID查询所有的部门，不分页
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#getAllPartmentByParentId(java.lang.Integer)
	 */
	public List<TbPartment> getAllPartmentByParentId(Integer companyId){
		return tbPartmentDao.getAllPartmentListByCompanyId(companyId) ;
	}
	
	/*
	 * 按旅行社ID查询所有的部门并分页
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#getAllPartmentByCompanyId(java.lang.Integer)
	 */
	public PageBean getAllPartmentByCompanyId(PageBean pageBean ,Integer companyId) {
		return tbPartmentDao.getAllPartmentPageBeanByCompanyId(pageBean, companyId) ;
	}


	/*
	 * 根据ID查询旅行社或部门信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#queryPartmentInfoById(java.lang.Integer)
	 */
	public TbPartment getPartmentInfoById(Integer id) {
		// TODO Auto-generated method stub
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(id) ;
		return tbPartment;
	}
	
	

	/*
	 * 通过帐户ID查询部门
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#getPartmentInfoByAccountId(java.lang.Integer)
	 */
	public TbPartment getPartmentInfoByAccountId(Integer accountId){
		TbPartment tbPartment = tbPartmentDao.getPartmentInfoByAccountId(accountId) ;
		return tbPartment;
	}

	
	/*
	 * 按ID删除部门
	 * 删除实际上时将部门隐藏起来
	 * 并不是真正的删除
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPartmentServ#deletePartmentById(java.lang.Integer)
	 */
	public int deletePartmentById(Integer id) {
		return tbPartmentDao.delete(id) ;
	}

	public ITbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}

	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbAccidentalFeeDao getTbAccidentalFeeDao() {
		return tbAccidentalFeeDao;
	}

	public void setTbAccidentalFeeDao(ITbAccidentalFeeDao tbAccidentalFeeDao) {
		this.tbAccidentalFeeDao = tbAccidentalFeeDao;
	}

	public ITbAdjustFeeDao getTbAdjustFeeDao() {
		return tbAdjustFeeDao;
	}

	public void setTbAdjustFeeDao(ITbAdjustFeeDao tbAdjustFeeDao) {
		this.tbAdjustFeeDao = tbAdjustFeeDao;
	}

}
