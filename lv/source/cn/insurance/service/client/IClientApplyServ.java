package cn.insurance.service.client;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;

public interface IClientApplyServ {
	
	/**
	 * 添加申请
	 * @param tbApply
	 * @return
	 */
	public int addTbApply(TbApply tbApply) ;
	
	/**
	 * 修改申请
	 * @param tbApply
	 * @return
	 */
	public int updateTbApply(TbApply tbApply) ;
	
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
	
}
