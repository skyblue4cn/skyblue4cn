package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbMessage;

public class TbMessageDao extends BaseDao implements ITbMessageDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbMessage tbMessage = new TbMessage() ;
		tbMessage.setId(rs.getInt("id")) ;
		tbMessage.setIntMessageType(rs.getInt("intMessageType")) ;
		tbMessage.setDteTime(rs.getTimestamp("dteTime")) ;
		tbMessage.setIntFromPartment(rs.getInt("intFromPartment")) ;
		tbMessage.setIntToPartmentId(rs.getInt("intToPartmentId")) ;
		tbMessage.setStrContent(rs.getString("strContent")) ;
		return tbMessage;
	}

	/*
	 * 添加一条消息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#addMessage(cn.insurance.model.TbMessage)
	 */
	public int addMessage(TbMessage tbMessage) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbMessage(dteTime,strContent,intMessageType,intFromPartment,intToPartmentId) VALUES(?,?,?,?,?)" ;
		Object[] objs = {
				tbMessage.getDteTime() ,
				tbMessage.getStrContent(),
				tbMessage.getIntMessageType(),
				tbMessage.getIntFromPartment(),
				tbMessage.getIntToPartmentId()
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
	}
	
	
	
	/*
	 * 发送消息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#sendMessage(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public int sendMessage(Integer intMessageType ,Integer intPartmentId, String content){
		TbMessage tbMessage = new TbMessage() ;
		tbMessage.setDteTime(new Date()) ;
		tbMessage.setIntMessageType(intMessageType) ;
		tbMessage.setIntToPartmentId(intPartmentId) ;
		tbMessage.setStrContent(content) ;
		addMessage(tbMessage) ;
		return 1 ;
	}
	/*
	 * 按ID查询消息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#getMessageById(java.lang.Integer)
	 */
	public TbMessage getMessageById(Integer id) {
		String sql = "SELECT * FROM tbMessage WHERE id=" + id ;
		return (TbMessage)super.queryByObj(sql) ;
	}
	/*
	 * 按部门ID查询所有的信息并分页
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#getMessageByPartmentId(cn.insurance.model.PageBean, java.lang.Integer, java.lang.Integer)
	 */
	public PageBean getMessageByPartmentId(PageBean pageBean, Integer partmentId , Integer messageType){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intToPartmentId=").append(partmentId) ;
		sql.append(" AND intMessageType=").append(messageType) ;
		sql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, sql.toString(), "tbMessage") ;
	}
	
	/*
	 * 查询保险公司的信息并分页
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#getMessageForBx(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getMessageForBx(PageBean pageBean ,Integer messageType){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intToPartmentId=").append(0) ;
		sql.append(" AND intMessageType=").append(messageType) ;
		sql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, sql.toString(), "tbMessage") ;
	}
	
	/*
	 * 按ID删除
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#deleteMessageById(java.lang.Integer)
	 */
	public int deleteMessageById(Integer id){
		String sql = "DELETE FROM tbMessage WHERE id=" + id ;
		return super.update(sql) ;
	}
	
	
	/*
	 * 删除某个时间之前的所有的信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMessageDao#deleteMessageByDate(java.lang.String)
	 */
	public int deleteMessageByDate(String date){
		String sql = "DELETE FROM tbMessage WHERE dteTime<'" + date + "'" ;
		return super.update(sql.toString()) ;
	}

}













