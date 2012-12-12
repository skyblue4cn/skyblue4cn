package cn.insurance.service.admin;

import java.util.List;

import cn.insurance.model.TbPartment;

import com.sunnydale.include.RoleTreeBean;

/**
 * 旅行社的树形结构管理
 * @author yqg
 *
 */
public interface IAdminPartmentTreeServ {
	
	/**
	 * 取得旅行社的树形结构
	 * @return
	 */
	public RoleTreeBean getPartmentTree();
	
	/**
	 * 用户给定的值查询节点
	 * 
	 * 何青松
	 * 修改时间 2012/11/21 12:24
	 * */
	public RoleTreeBean getPartmentTreeUserValue(String value);
}
