package cn.insurance.service.message;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbMessage;

public interface ITbMessageServ {

	
	/**
	 * 按ID查询信息
	 * @param id
	 * @return
	 */
	public TbMessage getMessageById(Integer id) ;
	

	/**
	 * 按部门ID查询消息并分页
	 * @return
	 */
	public PageBean getMessageByPartmentId(PageBean pageBean ,Integer partmentId , Integer messageType) ;
	
	
	/**
	 * 按类型查询保险公司的消息 
	 * @return
	 */
	public PageBean getMessageForBx(PageBean pageBean ,Integer messageType) ;
	
	
	
	
	/**
	 * 按ID删除信息
	 * @return
	 */
	public int deleteMessageById(Integer id) ;
	
}	
