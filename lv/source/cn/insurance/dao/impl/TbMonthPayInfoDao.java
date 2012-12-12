package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbListForMonthPayDao;
import cn.insurance.dao.ITbMonthPayInfoDao;
import cn.insurance.dao.ITbMonthPayOutLogDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbListForMonthPay;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbUser;
import cn.insurance.utils.CommonWords;
import cn.insurance.utils.DateUtil;

public class TbMonthPayInfoDao extends BaseDao implements ITbMonthPayInfoDao {
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbListForMonthPayDao tbListForMonthPayDao ;
	
	private ITbMonthPayOutLogDao tbMonthPayOutLogDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
		tbMonthPayInfo.setId(rs.getInt("id")) ;
		tbMonthPayInfo.setIntAccountId(rs.getInt("intAccountId")) ;
		tbMonthPayInfo.setDbeNeedToPay(rs.getDouble("dbeNeedToPay")) ;
		tbMonthPayInfo.setDbePayNumber(rs.getDouble("dbePayNumber")) ;
		tbMonthPayInfo.setStrYear(rs.getString("strYear")) ;
		tbMonthPayInfo.setStrMonth(rs.getString("strMonth")) ;
		tbMonthPayInfo.setIntIsPay(rs.getInt("intIsPay")) ;
		tbMonthPayInfo.setTbPartment(tbPartmentDao.getTbPartmentByAccountId(tbMonthPayInfo.getIntAccountId())) ;
		tbMonthPayInfo.setDteStartTime(rs.getTimestamp("dteStartTime")) ;
		tbMonthPayInfo.setDteEndTime(rs.getDate("dteEndTime")) ;
		/*看这个月费记录是否应该支付费用*/
		if(tbMonthPayInfo.getDteEndTime().before(new Date())){
			//需要支付
			tbMonthPayInfo.setIsNeedPay(1) ;
		}else{
			//暂时不需要支付
			tbMonthPayInfo.setIsNeedPay(-1) ;
		}
		/*查询这个月的应付清单*/
		tbMonthPayInfo.setYingFuListForMonthPay(tbListForMonthPayDao.getYingFuListForMonthPay(tbMonthPayInfo.getId())) ;
		/*支付记录*/
		tbMonthPayInfo.setTbMonthPayOutLog(tbMonthPayOutLogDao.getMonthPauOutLogByMonthPayId(tbMonthPayInfo.getId())) ;
		return tbMonthPayInfo;
	}
	
	/*
	 * 添加一条月结算的支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#create(cn.insurance.model.TbMonthPayInfo)
	 */
	public int create(TbMonthPayInfo tbMonthPayInfo) {
		// TODO Auto-generated method stub
//		tbMonthPayInfo.setDteStartTime(DateUtil.getDteTimeForMonthFeeLog(tbMonthPayInfo.getStrYear(), tbMonthPayInfo.getStrMonth(), 1)) ;
//		tbMonthPayInfo.setDteEndTime(DateUtil.getDteTimeForMonthFeeLog(tbMonthPayInfo.getStrYear(), tbMonthPayInfo.getStrMonth(), 2)) ;
		String sql = "INSERT INTO tbMonthPayInfo(intAccountId,strYear,strMonth,dteStartTime,dteEndTime,dbeNeedToPay,dbePayNumber,intIsPay)VALUES(?,?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				tbMonthPayInfo.getIntAccountId(),
				tbMonthPayInfo.getStrYear(),
				tbMonthPayInfo.getStrMonth(),
				tbMonthPayInfo.getDteStartTime(),
				tbMonthPayInfo.getDteEndTime(),
				tbMonthPayInfo.getDbeNeedToPay() ,
				tbMonthPayInfo.getDbePayNumber(),
				tbMonthPayInfo.getIntIsPay()
		} ;
		
		return super.jdbcTemplate.update(sql,objs);
	}

	/*
	 * 更新支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#update(cn.insurance.model.TbMonthPayInfo)
	 */
	public TbMonthPayInfo update(TbMonthPayInfo tbMonthPayInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbMonthPayInfo SET intAccountId=?,strYear=?,strMonth=?,dbeNeedToPay=?,dbePayNumber=?,intIsPay=? WHERE id=?" ;
		Object[] objs = {
				tbMonthPayInfo.getIntAccountId(),
				tbMonthPayInfo.getStrYear(),
				tbMonthPayInfo.getStrMonth(),
				tbMonthPayInfo.getDbeNeedToPay() ,
				tbMonthPayInfo.getDbePayNumber(),
				tbMonthPayInfo.getIntIsPay(),
				tbMonthPayInfo.getId()
		} ;
		super.jdbcTemplate.update(sql, objs);
		return tbMonthPayInfo ;
	}

	
	/**
	 * 根据年月查询该月记录
	 */
	public List getMonthPayByYearAndMonth(String year , String month ,Integer accountId){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbMonthPayInfo WHERE")
				.append(" strYear='").append(year).append("'")
				.append(" AND strMonth='").append(month).append("'")
				.append(" AND intAccountId=").append(accountId) ;
				;
		return query(sql.toString());
	}
	
	
	public TbMonthPayInfo getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbMonthPayInfo WHERE id=" + id;
		return (TbMonthPayInfo)super.queryByObj(sql);
	}
	

	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbMonthPayInfo WHERE id=" + id ;
		return super.update(sql);
	}
	
	/*
	 * 根据帐户ID查询该帐户的所有月结算记录，并分页
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#getMonthPayByAccontId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getMonthPayByAccontId(PageBean pageBean , Integer accountId ){
		StringBuffer conditionSql = new StringBuffer()  ;
		conditionSql.append(" AND intAccountId=").append(accountId) ;
		conditionSql.append(" ORDER BY id DESC ") ;
		return super.getPageBean(pageBean, conditionSql.toString(), "tbMonthPayInfo") ;
	}

	
	/**
	 * 添加结算记录并返回相应的ID
	 * @param tbMonthPayInfo
	 * @return
	 */
	public Integer addMonthPayInfoReturnId(TbMonthPayInfo tbMonthPayInfo) {
//		tbMonthPayInfo.setDteStartTime(DateUtil.getDteTimeForMonthFeeLog(tbMonthPayInfo.getStrYear(), tbMonthPayInfo.getStrMonth(), 1)) ;
//		tbMonthPayInfo.setDteEndTime(DateUtil.getDteTimeForMonthFeeLog(tbMonthPayInfo.getStrYear(), tbMonthPayInfo.getStrMonth(), 2)) ;
		Integer maxId = super.getMaxId("tbMonthPayInfo", "id") ;
		String sql = "INSERT INTO tbMonthPayInfo(id,intAccountId,strYear,strMonth,dteStartTime,dteEndTime,dbeNeedToPay,dbePayNumber,intIsPay)VALUES(?,?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				maxId,
				tbMonthPayInfo.getIntAccountId(),
				tbMonthPayInfo.getStrYear(),
				tbMonthPayInfo.getStrMonth(),
				tbMonthPayInfo.getDteStartTime(),
				tbMonthPayInfo.getDteEndTime(),
				tbMonthPayInfo.getDbeNeedToPay() ,
				tbMonthPayInfo.getDbePayNumber(),
				tbMonthPayInfo.getIntIsPay()
		} ;
		super.jdbcTemplate.update(sql,objs);
		return maxId ;
	}

	/*
	 * 查询用户所在部门所有未交费的月费记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#checkTheUserPartmentMonthFee(cn.insurance.model.TbUser)
	 */
	public List<TbMonthPayInfo> getAllNotPayMonthLog(Integer accountId){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbMonthPayInfo WHERE intIsPay=0 AND intAccountId=").append(accountId) ;
		return super.query(sql.toString()) ;
	}
	
	/*
	 * 根据月费IDs查询所有月费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#getPartmentFeeByIdList(int)
	 */
	public List<TbMonthPayInfo> getPartmentFeeByIdList(int[] monthPayInfoIdList){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbMonthPayInfo WHERE id IN (") ;
		for(int id : monthPayInfoIdList){
			sql.append(id).append(",") ;
		}
		sql.deleteCharAt(sql.lastIndexOf(",")) ;
		sql.append(")") ;
		return super.query(sql.toString()) ;
	}
	
	
	/*
	 * 检查用户帐户在该月之前（1个月是否有月费未付）
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#checkTheUserPartmentMonthFee(cn.insurance.model.TbUser)
	 */
	public boolean checkTheUserPartmentMonthFee(int accountId){
		Calendar calendar = new GregorianCalendar() ;
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) -1 ) ;
		String date = DateUtil.getFormatDate(calendar, "yyyy-MM-dd") ;
		String sql = "SELECT COUNT(*)  as count FROM tbMonthPayInfo WHERE intIsPay=0 AND intAccountId=" + accountId + " AND dteEndTime<'" + date + "'" ;
		if(super.jdbcTemplate.queryForInt(sql) >0){
			return true ;
		}
		return false ;
	}
	
	
	/*
	 * 查询该年月所以未交费的月费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#getAllNotPayMonthFee(java.lang.String, java.lang.String)
	 */
	public List<TbMonthPayInfo> getAllNotPayMonthFee(String year, String month){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM tbMonthPayInfo WHERE ") ;
		sb.append(" intIsPay=" + CommonWords.monthPayState1) ;
		sb.append(" AND strYear='" + year +"' ") ;
		sb.append(" AND strMonth='" + month +"' ") ;
		return super.query(sb.toString()) ;
	}
	
	/*
	 * 查询该年月所有未交费的预存账户月费
	 * 用来对预存账户自动收费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbMonthPayInfoDao#getAllNotPayYuCunMonthFee(int, int)
	 */
	public List<TbMonthPayInfo> getAllNotPayYuCunMonthFee(String year, String month){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM tbMonthPayInfo WHERE ") ;
		sb.append(" intIsPay=" + CommonWords.monthPayState1) ;
		sb.append(" AND strYear='" + year +"' ") ;
		sb.append(" AND strMonth='" + month +"' ") ;
		sb.append(" AND intAccountId IN(SELECT id FROM tbAccount WHERE intPayTypeId="+ CommonWords.payType1+")") ;
		return super.query(sb.toString()) ;
	}
	
	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbMonthPayOutLogDao getTbMonthPayOutLogDao() {
		return tbMonthPayOutLogDao;
	}

	public void setTbMonthPayOutLogDao(ITbMonthPayOutLogDao tbMonthPayOutLogDao) {
		this.tbMonthPayOutLogDao = tbMonthPayOutLogDao;
	}

	public ITbListForMonthPayDao getTbListForMonthPayDao() {
		return tbListForMonthPayDao;
	}

	public void setTbListForMonthPayDao(ITbListForMonthPayDao tbListForMonthPayDao) {
		this.tbListForMonthPayDao = tbListForMonthPayDao;
	}

	/*** 修改于2010-6-18*/
	public List<String> getMessage_2010(Integer partmentId) {
		List<String> strs = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 7);
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(d);
		String e = sdf.format(Calendar.getInstance().getTime());
		String sql = "select * from tbmonthpayinfo where  dteEndTime > '"+e+"' and  dteEndTime < '"+s+"' and intispay = 0 and intAccountId = (select id from tbaccount where intPartmentId = '"+partmentId+"' limit 1)";
		List l = super.query(sql); //
		if(l.size() == 0){
			return null;
		}else{
			for (Iterator iterator = l.iterator(); iterator.hasNext();) {
				TbMonthPayInfo m = (TbMonthPayInfo) iterator.next();
				SimpleDateFormat scc = new SimpleDateFormat("yyyy年MM月dd日");
				if((int)m.getTbPartment().getId() == (int)partmentId && m.getTbPartment().getTbAccount().getIntPayTypeId() == 2){
					String str = m.getTbPartment().getStrPartmentName() + 
					"部门(分社)，你部门(分社)上一期的应交款共计"
					+m.getDbeNeedToPay()+"元，交款截至时间为："
					+scc.format(m.getDteEndTime())+"，请及时交款，否则将暂停你部门(分社)的投保";
					strs.add(str);
				}
				if((int)m.getTbPartment().getId() == (int)partmentId 
						&& m.getTbPartment().getTbAccount().getIntPayTypeId() == 1 
						&& (m.getTbPartment().getTbAccount().getDbeResidual() - m.getDbeNeedToPay())< 0){
					String str = m.getTbPartment().getStrPartmentName() + 
						"部门(分社)，你部门(分社)的余额还有"+
						m.getTbPartment().getTbAccount().getDbeResidual()
						+"元，请及时交款";
					strs.add(str);
				}
			}
			return strs;
		}
	}


}
