package cn.insurance.service;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;

public interface ITbApplyServ {
	/**
	 * 添加申请
	 * @param tbApply
	 * @return
	 */
	public int addTbApply(TbApply tbApply) ;
	
	/**
	 * 保险公司回复申请
	 * @param tbApply
	 * @return
	 */
	public int addReplyByBx(TbApply tbApply) ;
	
	/**
	 * 根据ID查询申请
	 * 
	 * @param id
	 * @return
	 */
	public TbApply getApplyById(Integer id) ;
	
	
	/**
	 * 按部门查询已回复的申请
	 * @param pageBean
	 * @return
	 */
	public PageBean getHasReplyApplyByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	/**
	 * 按部门查询未回复的申请
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getNotReplyApplyByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	
	
	/**
	 * 查询所有的未处理的申请
	 * @return
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) ;
	
	/**
	 * 查询所有未处理的申请的数目，用于消息提示 
	 * @return
	 */
	public int getAllNeedApplyCount() ;
	
	/**
	 * 查询所有已处理的申请
	 * @return
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) ;
}
