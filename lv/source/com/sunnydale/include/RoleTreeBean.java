/**
 * 封装菜单相关数据
 */
package com.sunnydale.include;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * 
 * 封装菜单节点子项列表
 */
public class RoleTreeBean implements Serializable {
	
	/**菜单子项列表*/
	private List treeList;
	
	/**
	 * 返回菜单子项列表
	 * @return java.util.List
	 */
	public List getTreeList(){
		return treeList;
	}
	
	/**
	 * 获得菜单子项列表
	 * @param treeList
	 */
	public void setTreeList(List treeList){
		this.treeList = treeList;
	}

}
