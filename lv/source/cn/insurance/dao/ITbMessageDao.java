package cn.insurance.dao;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbMessage;

public interface ITbMessageDao {
	
	
	
	/**
	 * 添加一条消息
	 * @param tbMessage
	 * @return
	 */
	public int addMessage(TbMessage tbMessage) ;
	
	/**
	 * 按ID查询消息
	 * @param id
	 * @return
	 */
	public TbMessage getMessageById(Integer id) ;
	
	
	/**
	 * 发送消息
	 * @param intMessageType
	 * @param intPartmentId
	 * @param content
	 * @return
	 */
	public int sendMessage(Integer intMessageType ,Integer intPartmentId, String content) ;
	
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
	 * 按ID删除消息
	 * @param id
	 * @return
	 */
	public int deleteMessageById(Integer id);
	
	/**
	 * 删除某个时间之间的所有信息
	 * @param date
	 * @return
	 */
	public int deleteMessageByDate(String date) ;
	
}
