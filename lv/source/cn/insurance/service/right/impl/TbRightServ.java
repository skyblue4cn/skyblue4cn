package cn.insurance.service.right.impl;

import java.util.List;

import cn.insurance.dao.right.ITbRightDao;
import cn.insurance.model.right.TbRight;
import cn.insurance.service.right.ITbRightServ;

public class TbRightServ implements ITbRightServ {
	
	private ITbRightDao tbRightDao ;
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRightServ#addTbRight(cn.insurance.model.right.TbRight)
	 */
	public int addTbRight(TbRight tbRight) {
		return tbRightDao.addTbRight(tbRight);
	}
	
	/*
	 * 更新
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRightServ#updateTbRight(cn.insurance.model.right.TbRight)
	 */
	public int updateTbRight(TbRight tbRight) {
		return tbRightDao.updateTbRight(tbRight);
	}
	
	/*
	 * 查询保险公司的权限
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRightServ#getAllBxRightList()
	 */
	public List<TbRight> getAllBxRightList() {
		return tbRightDao.getRightListByType(TbRight.BX_RIGHT_TYPE);
	}
	
	/*
	 * 查询旅行社的权限
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRightServ#getAllLxsRightList()
	 */
	public List<TbRight> getAllLxsRightList() {
		return tbRightDao.getRightListByType(TbRight.LXS_RIGHT_TYPE);
	}

	public ITbRightDao getTbRightDao() {
		return tbRightDao;
	}

	public void setTbRightDao(ITbRightDao tbRightDao) {
		this.tbRightDao = tbRightDao;
	}



}
