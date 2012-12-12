package cn.insurance.service.impl;

import cn.insurance.dao.ITbApplyDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;
import cn.insurance.model.TbBill;
import cn.insurance.service.ITbApplyServ;
import cn.insurance.utils.CommonWords;

public class TbApplyServ implements ITbApplyServ{
	
	private ITbApplyDao tbApplyDao ;
	
	private ITbBillDao tbBillDao ;
	
	/*
	 * 添加申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#addTbApply(cn.insurance.model.TbApply)
	 */
	public int addTbApply(TbApply tbApply) {
		tbApply.setStrBillNumber(tbApply.getStrBillNumber().trim()) ;
		try{
			TbBill tbBill = tbBillDao.getBillByNumber(tbApply.getStrBillNumber()) ;
			/*只有已确认的单子才可以申请更改*/
			if(tbBill.getIntBillStateId() != CommonWords.billState4){
				return 0 ;
			}
			/*一定要是本部门的单子*/
			if(tbBill.getIntPartmentId().intValue() != tbApply.getIntPartmentId().intValue()){
				return 0 ;
			}
			tbApply.setIntBillId(tbBill.getId()) ;
		}catch(Exception e){
			return 0 ;
		}
		/*设置为未回复*/
		tbApply.setIntIsReply(0) ;
		tbApplyDao.addTbApply(tbApply);
		return 1 ;
	}
	
	/*
	 * 按id查询
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getApplyById(java.lang.Integer)
	 */
	public TbApply getApplyById(Integer id){
		return tbApplyDao.getApplyById(id) ;
	}
	
	/*
	 *  按部门查询未回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getNotReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getNotReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		return tbApplyDao.getNotReplyApplyByPartmentId(pageBean, partmentId) ;
	}

	/*
	 * 按部门查询已回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getHasReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getHasReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		return tbApplyDao.getHasReplyApplyByPartmentId(pageBean, partmentId) ;
	}
	

	
	
	
	/*
	 * 
	 * 查询所有已经回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getAllHasApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		return tbApplyDao.getAllHasApplyPageBean(pageBean);
	}
	
	/*
	 * 查询所有还没有回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getAllNeedApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		return tbApplyDao.getAllNeedApplyPageBean(pageBean);
	}
	
	
	public int getAllNeedApplyCount(){
		return tbApplyDao.getAllNeedApplyCount() ;
	}
	
	
	
	/*
	 * 保险公司回复申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#updateTbApplyByBx(cn.insurance.model.TbApply)
	 */
	public int addReplyByBx(TbApply tbApply) {
		tbApply.setIntIsReply(1) ;
		return tbApplyDao.addReplyByBx(tbApply);
	}
	
	public ITbApplyDao getTbApplyDao() {
		return tbApplyDao;
	}

	public void setTbApplyDao(ITbApplyDao tbApplyDao) {
		this.tbApplyDao = tbApplyDao;
	}

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}
	
}
