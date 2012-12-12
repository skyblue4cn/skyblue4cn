package cn.insurance.service.impl;

import java.util.List;

import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPeiKuanDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPeiKuan;
import cn.insurance.service.ITbPeiKuanServ;
import cn.insurance.utils.CommonWords;

public class TbPeiKuanServ implements ITbPeiKuanServ {

	private ITbPeiKuanDao tbPeiKuanDao  ;
	
	private ITbBillDao tbBillDao ;
	
	/*
	 * 添加赔款记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPeiKuanServ#addPeiKuanLog(cn.insurance.model.TbPeiKuan)
	 */
	public int addPeiKuanLog(TbPeiKuan tbPeiKuan) {
		/*保单添加赔款记录*/
		tbPeiKuan.getTbBill().setIntIsPeiKuan(CommonWords.peiKuan1) ;
		tbBillDao.update(tbPeiKuan.getTbBill())  ;
		return tbPeiKuanDao.addPeiKuanLog(tbPeiKuan);
	}

	/*
	 * 查询所有的赔款记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPeiKuanServ#getAllPeiKuanLog(cn.insurance.model.PageBean)
	 */
	public PageBean getAllPeiKuanLog(PageBean pagebean) {
		// TODO Auto-generated method stub
		pagebean = tbPeiKuanDao.getAllPeiKuanLog(pagebean);
		//查询这个list中的保单详细信息
		for(int i=0 ; i <pagebean.getDataList().size() ; i++){
			TbPeiKuan tbPeiKuan = (TbPeiKuan)pagebean.getDataList().get(i) ;
			tbPeiKuan.setTbBill(tbBillDao.getObjectInfoById(tbPeiKuan.getIntBillId())) ;
		}
		return pagebean ;
	}

	public ITbPeiKuanDao getTbPeiKuanDao() {
		return tbPeiKuanDao;
	}

	public void setTbPeiKuanDao(ITbPeiKuanDao tbPeiKuanDao) {
		this.tbPeiKuanDao = tbPeiKuanDao;
	}

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}

	

}
