/**
 * 封装菜单相关数据
 */
package com.sunnydale.include;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 * TreeNodeBean,用于封装树型菜单的节点属性数据；
 * 生成树型菜单时从此封装类中获取，菜单节点的各属性值。然后调用dtree来生成菜单；
 * 最后在页面tree.jsp上打印生成跟角色权限对应的功能菜单。
 */
public class TreeNodeBean implements Serializable{
	
	/**节点ID，指定节点在树型菜单中的位置；*/
	private int nodeId;
	
	/**父节点ID，指定此节点所属父节点ID，目录节点为-1，根节点为0*/
	private int parentId;
	
	/**节点名字，将打印在页面上*/
	private String nodeName;
	
	/**节点说明性文字，定义TITLE属性的值，可为NULL值*/
	private String nodeTitle;
	
	/**节点连接地址,父节点没有此属性*/
	private String urlPath;
	
	/**节点系统图片，可为NULL值*/
	private String titleImg;
	
	/**
	 * 返回节点ID
	 * @return int
	 */
	public int getNodeId(){
		return nodeId;
	}
	
	/**
	 * 获得接点ID
	 * @param nodeId
	 */
	public void setNodeId(int nodeId){
		this.nodeId = nodeId;
	}
	
	/**
	 * 返回父节点ID
	 * @return int
	 */
	public int getParentId(){
		return parentId;
	}
	
	/**
	 * 获得父节点ID
	 * @param parentId
	 */
	public void setParentId(int parentId){
		this.parentId = parentId;
	}
	
	/**
	 * 返回此节点名称
	 * @return java.lang.String
	 */
	public String getNodeName(){
		return nodeName;
	}
	
	/**
	 * 获得此节点名称
	 * @param nodeName
	 */
	public void setNodeName(String nodeName){
		this.nodeName = nodeName;
	}
	
	/**
	 * 返回节点说明文字
	 * @return java.lang.String
	 */
	public String getNodeTitle(){
		return nodeTitle;
	}
	
	/**
	 * 获得节点说明文字
	 * @param nodeTitle
	 */
	public void setNodeTitle(String nodeTitle){
		this.nodeTitle = nodeTitle;
	}
	
	/**
	 * 返回节点连接地址
	 * @return java.lang.String
	 */
	public String getUrlPath(){
		return urlPath;
	}
	
	/**
	 * 获得节点连接地址
	 * @param urlPath
	 */
	public void setUrlPath(String urlPath){
		this.urlPath = urlPath;
	}
	
	/**
	 * 返回节点系统图片
	 * @return java.lang.String
	 */
	public String getTitleImg(){
		return titleImg;
	}
	
	/**
	 * 获得节点系统图片
	 * @param titleImg
	 */
	public void setTitleImg(String titleImg){
		this.titleImg = titleImg;
	}

}
