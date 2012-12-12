package cn.insurance.service;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPeiKuan;

public interface ITbPeiKuanServ {
	
	/**
	 * 添加赔款记录
	 * @param tbPeiKuan
	 * @return
	 */
	public int addPeiKuanLog(TbPeiKuan tbPeiKuan) ;
	
	/**
	 * 分页查询所有的赔款记录
	 * @param pagebean
	 * @return
	 */
	public PageBean getAllPeiKuanLog(PageBean pagebean) ;
	
	
}
