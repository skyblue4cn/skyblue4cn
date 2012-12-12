package cn.insurance.service.admin;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;

/**
 * 对批改申请的管理
 * @author yqg
 *
 */
public interface IAdminApplyServ {
	
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
	 * 查询所有的未处理的申请
	 * @return
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) ;
	
	/**
	 * 查询所有已处理的申请
	 * @return
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) ;
}
