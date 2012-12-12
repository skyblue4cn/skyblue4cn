/**
 * 自定义标签
 */
package com.sunnydale.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sunnydale.include.TreeNodeBean;

/**
 * @author Administrator
 * 
 * 显示树型菜单的自定义标签，通过此标签将角色对应的树型菜单打印到页面上
 */
public class TreeTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	/** 菜单节点数据列表 */
	@SuppressWarnings("unchecked")
	private List tree;

	/**
	 * 设计标签开始执行时的动作，这里将树型菜单打印到页面上
	 */
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspTagException {
		List list = formatTree(tree);
		try {
			for (int i = 0; i < list.size(); i++) {
				// 打印树型菜单
				pageContext.getOut().println((String) list.get(i));
			}
		} catch (IOException io) {}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 设计标签执行完毕后的动作，这里不做任何动作，只返回一个整形数，通知容器标签执行完毕
	 */
	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;//SKIP_PAGE:容器在标签结束时停止计算JSP页面其他的部分;EVAL_PAGE:容器在标签结束时继续计算JSP页面的其他部分.
	}

	/**
	 * 格式化树型菜单数据，控制其显示样式
	 * 
	 * @param lists
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	private List<String> formatTree(List lists) {
		List<String> trees = new ArrayList<String>();
		trees.add("<div class=\"dtree\">");
		if (lists == null || lists.size() <= 0) {
			trees.add("<div><span style=\"font-size:12px; font-weight:blod; ");
			trees.add("color:red;\">对不起，您还没有权限使用此系统。</span></div>");
			trees.add("<div><span style=\"font-size:10px; color:blue;\">");
			trees.add("联系系统管理员为您分配角色和权限</span></div>");
		} else {
			trees.add("<div style='border-bottom:1px dashed #ccc;'>" +
					"<form method='post' action='queryValue.action'><input type='text' name='uservalue' style='border:1px solid #ccc;' />&nbsp;&nbsp;&nbsp;&nbsp;" +
					"<input type='submit' value='查询' style='border:1px solid #ccc;width:60px;'/></form>" +
					"</div>");
			trees.add("<div><a href=\"javascript:d.openAll();\">展开菜单</a> | ");
			trees.add("<a href=\"javascript:d.closeAll();\">收起菜单</a></div>");
			trees.add("<div><script type=\"text/javascript\">");
			trees.add("var d = new dTree(\"d\");");
			//格式化添加菜单节点子项
			for (int i = 0; i < lists.size(); i++) {
				TreeNodeBean bean = (TreeNodeBean) lists.get(i);
			    if(bean == null) continue;		
					StringBuffer stb = new StringBuffer();
					stb.append("d.add(");
					stb.append(bean.getNodeId());
					stb.append(",");
					stb.append(bean.getParentId());
					stb.append(",\"");
					stb.append(bean.getNodeName());
					stb.append("\"");
					//boolean urlIsNull = true;
				if (bean.getUrlPath() != null && !bean.getUrlPath().equals("")) {
					stb.append(",\"");
					stb.append(bean.getUrlPath());
					stb.append("\"");
					//urlIsNull = false;
				}else{
					stb.append(",");
					stb.append("null");
					//stb.append("");
				}
				if (bean.getNodeTitle() != null
						&& !bean.getNodeTitle().equals("")) {
					stb.append(",\"");
					stb.append(bean.getNodeTitle());
					stb.append("\"");					
				}else{					
					stb.append(",");
					stb.append("null");
				}
				//是否在节点上使用超链接.
				stb.append(",\"framepage\"");
				
				if (bean.getTitleImg() != null
						&& !bean.getTitleImg().equals("")) {
					stb.append(",\"");
					stb.append(bean.getTitleImg());
					stb.append("\"");
					stb.append(",\"");
					stb.append(bean.getTitleImg());
					stb.append("\"");
				}
				// stb.append(",\"img/folder.gif\",");
				// stb.append("\"img/page.gif\",");
				// stb.append("\"img/line.gif\"");
				stb.append(");");
				trees.add(stb.toString());
			}
			trees.add("document.write(d);");
			trees.add("</script></div>");
		}
		trees.add("</div>");
		return trees;
	}

	/**
	 * 返回菜单节点列表
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getTree() {
		return tree;
	}

	/**
	 * 获得菜单节点列表
	 * 
	 * @param tree
	 */
	@SuppressWarnings("unchecked")
	public void setTree(List tree) {
		this.tree = tree;
	}

}
