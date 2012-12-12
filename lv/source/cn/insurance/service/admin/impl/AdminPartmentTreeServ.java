package cn.insurance.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import com.sunnydale.include.RoleTreeBean;
import com.sunnydale.include.TreeNodeBean;

import cn.insurance.dao.impl.TbPartmentDao;
import cn.insurance.model.TbPartment;
import cn.insurance.service.admin.IAdminPartmentTreeServ;

public class AdminPartmentTreeServ implements IAdminPartmentTreeServ {

	private TbPartmentDao tbPartmentDao ;
	
	/**
	 * 根节点的图
	 * */
	public static final String TREE_ROOT_PIC = "img/treeroot.gif";
	/**
	 * 旅行社节点的图
	 */
	public static final String TREE_COMPANY_PIC = "img/treecompany.gif"; 
	
	/**
	 * 旅行社部门的节点的图
	 */
	public static final String TREE_COMPANY_PARTMENT_PIC = "img/treepartment.gif";
	
	
	public RoleTreeBean getPartmentTreeUserValue(String value) {
		RoleTreeBean roleTreeBean = new RoleTreeBean();
		/**
		 * 用于存储该分类列表创建的树节点列表
		 */
		List<TreeNodeBean> list = new ArrayList<TreeNodeBean>();
		/*根节点*/
		TreeNodeBean root = new TreeNodeBean();
		root.setNodeId(0);
		root.setParentId(-1);
		root.setNodeName("旅行社管理");
		root.setNodeTitle("旅行社管理");
		root.setUrlPath("nodeSelect_root.action");
		root.setTitleImg(TREE_ROOT_PIC);
		list.add(root);
		
		/*旅行社*/
		List<TbPartment> companyList = tbPartmentDao.getAllCompanyList(value);
	  System.out.println("---------------------------------------"+companyList.size());
	  List<TbPartment> companyList2 = tbPartmentDao.getAllCompanyList();
	  System.out.println("---------------------------------------"+companyList2.size());
	  
		for (TbPartment company : companyList) {
			TreeNodeBean tn = new TreeNodeBean();
			tn.setNodeId(company.getId());
			tn.setNodeName(company.getStrPartmentName());
			tn.setNodeTitle(company.getStrPartmentName());
			tn.setParentId(company.getIntParentId());
			tn.setUrlPath("nodeSelect_company.action?nodeid=" + company.getId());
			tn.setTitleImg(TREE_COMPANY_PIC);
			list.add(tn);
		}

		/*旅行社的部门*/
		List<TbPartment> allPartmentList = tbPartmentDao.getAllCompanyPartmentList();
		System.out.println("数组大小"+allPartmentList.size());
		for (TbPartment p : allPartmentList) {
			/**
			 * 构建一级业务关键词的节点
			 */
			TreeNodeBean tn2 = new TreeNodeBean();
			tn2.setNodeId(p.getId());
			tn2.setNodeName(p.getStrPartmentName());
			tn2.setNodeTitle(p.getStrPartmentName());
			tn2.setParentId(p.getIntParentId());
			tn2.setUrlPath("nodeSelect_partment.action?nodeid="+ p.getId());
			tn2.setTitleImg(TREE_COMPANY_PARTMENT_PIC);
			list.add(tn2);
		}
		roleTreeBean.setTreeList(list);
		return roleTreeBean;
	}
	
	/*
	 * 取得旅行社的属性结构
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IPartmentTreeServ#getPartmentTree()
	 */
	public RoleTreeBean getPartmentTree() {
		RoleTreeBean roleTreeBean = new RoleTreeBean();
		/**
		 * 用于存储该分类列表创建的树节点列表
		 */
		List<TreeNodeBean> list = new ArrayList<TreeNodeBean>();
		/*根节点*/
		TreeNodeBean root = new TreeNodeBean();
		root.setNodeId(0);
		root.setParentId(-1);
		root.setNodeName("旅行社管理");
		root.setNodeTitle("旅行社管理");
		root.setUrlPath("nodeSelect_root.action");
		root.setTitleImg(TREE_ROOT_PIC);
		list.add(root);
		
		/*旅行社*/
		List<TbPartment> companyList = tbPartmentDao.getAllCompanyList();
		for (TbPartment company : companyList) {
			TreeNodeBean tn = new TreeNodeBean();
			tn.setNodeId(company.getId());
			tn.setNodeName(company.getStrPartmentName());
			tn.setNodeTitle(company.getStrPartmentName());
			tn.setParentId(company.getIntParentId());
			tn.setUrlPath("nodeSelect_company.action?nodeid=" + company.getId());
			tn.setTitleImg(TREE_COMPANY_PIC);
			list.add(tn);
		}

		/*旅行社的部门*/
		List<TbPartment> allPartmentList = tbPartmentDao.getAllCompanyPartmentList();
		for (TbPartment p : allPartmentList) {
			/**
			 * 构建一级业务关键词的节点
			 */
			TreeNodeBean tn2 = new TreeNodeBean();
			tn2.setNodeId(p.getId());
			tn2.setNodeName(p.getStrPartmentName());
			tn2.setNodeTitle(p.getStrPartmentName());
			tn2.setParentId(p.getIntParentId());
			tn2.setUrlPath("nodeSelect_partment.action?nodeid="+ p.getId());
			tn2.setTitleImg(TREE_COMPANY_PARTMENT_PIC);
			list.add(tn2);
		}
		roleTreeBean.setTreeList(list);
		return roleTreeBean;
	}

	public TbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(TbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}


}
