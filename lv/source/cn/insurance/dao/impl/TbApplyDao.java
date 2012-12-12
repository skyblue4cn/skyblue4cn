package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.insurance.commonwords.TbApplyKey;
import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbApplyDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;

public class TbApplyDao extends BaseDao implements ITbApplyDao{
	
	private ITbBillDao tbBillDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbApply tbApply = new TbApply() ;
		tbApply.setId(rs.getInt("id")) ;
		tbApply.setIntFromUserId(rs.getInt("intFromUserId")) ;
		tbApply.setStrApplyUserName(rs.getString("strApplyUserName")) ;
		tbApply.setStrApplyContent(rs.getString("strApplyContent")) ;
		tbApply.setIntBillId(rs.getInt("intBillId")) ;
		tbApply.setIntIsReply(rs.getInt("intIsReply")) ;
		tbApply.setIntPartmentId(rs.getInt("intPartmentId")) ;
		tbApply.setIntReplyUserId(rs.getInt("intReplyUserId")) ;
		tbApply.setStrReplyUserName(rs.getString("strReplyUserName")) ;
		tbApply.setDteApplyTime(rs.getDate("dteApplyTime")) ;
		tbApply.setDteReplyTime(rs.getDate("dteReplyTime")) ;
		tbApply.setStrReplyContent(rs.getString("strReplyContent")) ;
		tbApply.setStrBillNumber(tbBillDao.getObjectInfoById(tbApply.getIntBillId()).getStrBillNumber()) ;
		/*部门*/
		tbApply.setTbPartment(tbPartmentDao.getObjectInfoById(tbApply.getIntPartmentId())) ;
		
		return tbApply;
	}
	
	/*
	 * 添加申请
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#addTbApply(cn.insurance.model.TbApply)
	 */
	public int addTbApply(TbApply tbApply) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbApply(intFromUserId,strApplyUserName,intBillId,intPartmentId,dteApplyTime,strApplyContent,intIsReply) VALUES(?,?,?,?,?,?,?)" ;
		Object[] objs={
				tbApply.getIntFromUserId(),		tbApply.getStrApplyUserName(),
				tbApply.getIntBillId(),			tbApply.getIntPartmentId(),
				tbApply.getDteApplyTime(),		tbApply.getStrApplyContent(),
				tbApply.getIntIsReply()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 用户修改申请内容
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#updateTbApply(cn.insurance.model.TbApply)
	 */
	public int updateTbApply(TbApply tbApply){
		String sql = "UPDATE tbApply SET intFromUserId=?,strApplyUserName=?,intBillId=?,intPartmentId=?,dteApplyTime=?,strApplyContent=?,intIsReply=? WHERE id=?" ;
		Object[] objs={
				tbApply.getIntFromUserId(),		tbApply.getStrApplyUserName(),
				tbApply.getIntBillId(),			tbApply.getIntPartmentId(),
				tbApply.getDteApplyTime(),		tbApply.getStrApplyContent(),
				tbApply.getIntIsReply(),		tbApply.getId()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 回复申请
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#updateTbApply(cn.insurance.model.TbApply)
	 */
	public int addReplyByBx(TbApply tbApply){
		String sql = "UPDATE tbApply SET intIsReply=?,intReplyUserId=?,strReplyUserName=?,strReplyContent=?,dteReplyTime=? WHERE id=?" ;
		Object[] objs = {
				tbApply.getIntIsReply(),		tbApply.getIntReplyUserId(),
				tbApply.getStrReplyUserName(),	tbApply.getStrReplyContent(),
				tbApply.getDteReplyTime(),
				tbApply.getId()
				
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 根据ID查询申请
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getApplyById(java.lang.Integer)
	 */
	public TbApply getApplyById(Integer id) {
		String sql = "SELECT * FROM tbApply WHERE id=" + id ;
		return (TbApply)super.queryByObj(sql) ;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getNotReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getNotReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		String conditionSql = " AND intIsReply=" + TbApplyKey.Not_Reply_state  + " AND intPartmentId=" + partmentId;
		return super.getPageBean(pageBean, conditionSql, "tbApply") ;
	}

	/*
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getHasReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getHasReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		String conditionSql = " AND intIsReply=" + TbApplyKey.Has_Reply_State + " AND intPartmentId=" + partmentId;
		return super.getPageBean(pageBean, conditionSql, "tbApply") ;
	}

	
	
	/*
	 * 查询所有的已回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getAllHasApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		String conditionSql = " AND intIsReply=1"  + " ORDER BY id DESC";
		return super.getPageBean(pageBean, conditionSql, "tbApply");
	}
	
	/*
	 * 查询所有未回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getAllNeedApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		String conditionSql = " AND intIsReply=0"  + " ORDER BY id DESC" ;
		return super.getPageBean(pageBean, conditionSql, "tbApply");
	}

	/*
	 * 查询所有新的申请的数目，用于信息提示
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbApplyDao#getAllNeedApplyCount()
	 */
	public int getAllNeedApplyCount(){
		String sql = "SELECT COUNT(*) FROM tbApply WHERE intIsReply=0" ;
		return super.jdbcTemplate.queryForInt(sql) ;
	}
	
	
	
	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}


	
}
