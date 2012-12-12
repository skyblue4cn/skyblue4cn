package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;

public interface ITbApplyDao {
	
	/**
	 * 添加申请
	 * @param tbApply
	 * @return
	 */
	public int addTbApply(TbApply tbApply) ;
	
	/**
	 * 用户修改申请内容
	 * @param tbApply
	 * @return
	 */
	public int updateTbApply(TbApply tbApply) ;
	
	/**
	 * 修改申请
	 * @param tbApply
	 * @return
	 */
	public int addReplyByBx(TbApply tbApply) ;
	

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
	 * 
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public TbApply getApplyById(Integer id) ;
	
	
	
	/**
	 * 查询所有的未处理的申请
	 * @return
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) ;
	
	/**
	 * 查询所有新申请的数目，用于信息提示
	 * @return
	 */
	public int getAllNeedApplyCount() ;
	
	/**
	 * 查询所有已处理的申请
	 * @return
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) ;
}
