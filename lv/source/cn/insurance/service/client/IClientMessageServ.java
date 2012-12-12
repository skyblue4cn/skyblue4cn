package cn.insurance.service.client;

import java.util.List;

import cn.insurance.model.PageBean;

public interface IClientMessageServ {
	


	/**
	 * 按部门ID查询待阅消息
	 * @return
	 */
	public PageBean getDYMessage(PageBean pageBean ,Integer partmentId) ;
	

	public List<String> getMessage_2010(Integer partmentId);
	
}
