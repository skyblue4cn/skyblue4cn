package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbPeiKuanDao;
import cn.insurance.dao.ITbTravelerInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.model.tongji.PartmentDayStat;
import cn.insurance.model.tongji.PartmentMonthStat;
import cn.insurance.model.tongji.PartmentTimeStat;
import cn.insurance.utils.CommonWords;

public class TbBillDao extends BaseDao implements ITbBillDao{
	private Log logger=LogFactory.getLog(TbBillDao.class);
	
	private ITbTravelerInfoDao tbTravelerInfoDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbPeiKuanDao tbPeiKuanDao ;
	
	


	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbBill tbBill = new TbBill() ;
		tbBill.setId(rs.getInt("id")) ;
		tbBill.setStrBillNumber(rs.getString("strBillNumber")) ;
		tbBill.setStrBillName(rs.getString("strBillName")) ;
		tbBill.setStrCompanyName(rs.getString("strCompanyName")) ;
		tbBill.setIntKindId(rs.getInt("intKindId")) ;
		tbBill.setDteApplyDate(rs.getTimestamp("dteApplyDate")) ;
		tbBill.setStrPartmentName(rs.getString("strPartmentName")) ;
		tbBill.setIntPartmentId(rs.getInt("intPartmentId")) ;
		tbBill.setStrSignatoryName(rs.getString("strSignatoryName")) ;
		tbBill.setStrDragoman(rs.getString("strDragoman")) ;
		tbBill.setStrPhoneNumber(rs.getString("strPhoneNumber")) ;
		tbBill.setStrFax(rs.getString("strFax")) ;
		tbBill.setIntTeamType(rs.getInt("intTeamType")) ;
		tbBill.setStrTeamNumber(rs.getString("strTeamNumber")) ;
		tbBill.setIntTravelProperty(rs.getInt("intTravelProperty")) ;
		tbBill.setStrTravelRold(rs.getString("strTravelRold")) ;
		tbBill.setIntChinaTravelerNumber(rs.getInt("intChinaTravelerNumber")) ;
		tbBill.setIntOtherTravelerNumber(rs.getInt("intOtherTravelerNumber")) ;
		tbBill.setDteStartTime(rs.getTimestamp("dteStartTime")) ;
		tbBill.setDteEndTime(rs.getTimestamp("dteEndTime")) ;
		tbBill.setIntTravelType(rs.getInt("intTravelType")) ;
		tbBill.setIntIsHasHighDanger(rs.getInt("intIsHasHighDanger")) ;
		
		tbBill.setDbeChinaFee(rs.getDouble("dbeChinaFee")) ;
		tbBill.setDbeOtherFee(rs.getDouble("dbeOtherFee")) ;
		
		tbBill.setDbeAllFee(rs.getDouble("dbeAllFee")) ;
		tbBill.setIntBillStateId(rs.getInt("intBillStateId")) ;
		tbBill.setIntIsAddTraveler(rs.getInt("intIsAddTraveler")) ;
		tbBill.setIntIsPay(rs.getInt("intIsPay")) ;
		
		tbBill.setIntApplyUserId(rs.getInt("intApplyUserId")) ;
		
		tbBill.setStrFileUrl(rs.getString("strFileUrl")) ;
		tbBill.setIntBeiAnReason(rs.getInt("intBeiAnReason")) ;
		tbBill.setStrReturnReason(rs.getString("strReturnReason")) ;
		tbBill.setIntIsPeiKuan(rs.getInt("intIsPeiKuan")) ;
		
		tbBill.setStrSureUserName(rs.getString("strSureUserName")) ;
		tbBill.setDteSureTime(rs.getDate("dteSureTime")) ;
		tbBill.setIntIsSureByZs(rs.getInt("intIsSureByZs")) ;
		tbBill.setIntNBFeeType(rs.getInt("intNBFeeType"));
		tbBill.setIntWBFeeType(rs.getInt("intWBFeeType"));
		
		/*每个保单都会有对应的游客信息*/
		tbBill.setTbTravelerInfoList(tbTravelerInfoDao.getAllObjectListByBillId(tbBill.getId())) ;
		
		/*申请保单的部门信息*/
		tbBill.setTbPartment((TbPartment)tbPartmentDao.getObjectInfoById(tbBill.getIntPartmentId())) ;
		
		/*保险种类*/
//		tbBill.setTbBillKind((TbBillKind)tbBillKindDao.getObjectInfoById(tbBill.getIntKindId())) ;
		
		/*如果保单有赔款，则查出保单的赔款情况*/
		if(tbBill.getIntIsPeiKuan() == 1){
			tbBill.setTbPeiKuan(tbPeiKuanDao.getPeiKuanLogByBillId(tbBill.getId())) ;
		}
		
		/*保单种类*/
		if(tbBill.getIntKindId()==1){
			tbBill.setStrKindName("责任险") ;
		}else{
			tbBill.setStrKindName("意外险") ;
		}
		
		return tbBill;
	}
	
	/**
	 * 因为添加保险单后需要添加游客的信息，需要这个保险单的ID，所以返回这个ID值
	 */
	public int create(TbBill bill) {
		logger.debug("-------------------------------------");
		bill.setStrBillName(CommonWords.getBillName(bill.getIntKindId())) ;
		bill.setIntIsSureByZs(0) ;
		Integer maxId = super.getMaxId("tbBill", "id") ;
		logger.debug("bill.getIntNBFeeType():"+bill.getIntNBFeeType());
		logger.debug("bill.getIntWBFeeType():"+bill.getIntWBFeeType());
		String sql = "INSERT INTO tbBill(id,strBillNumber,strBillName,strCompanyName,intKindId,dteApplyDate,strPartmentName,intPartmentId,strSignatoryName,strDragoman,strPhoneNumber,strFax,intTeamType,strTeamNumber,intTravelProperty,strTravelRold,intChinaTravelerNumber,intOtherTravelerNumber,dbeChinaFee,dbeOtherFee,dbeAllFee,dteStartTime,dteEndTime,intTravelType,intIsHasHighDanger,intApplyUserId,intPayType,intBillStateId,intIsAddTraveler,intIsPay,intIsSureByZs) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				maxId,							bill.getStrBillNumber(),
				bill.getStrBillName() ,			
				bill.getStrCompanyName(),		bill.getIntKindId(),
				bill.getDteApplyDate(), 		bill.getStrPartmentName(),
				bill.getIntPartmentId(),		bill.getStrSignatoryName(),
				bill.getStrDragoman(),			bill.getStrPhoneNumber(),
				bill.getStrFax(),				bill.getIntTeamType(),
				bill.getStrTeamNumber(),		bill.getIntTravelProperty(),
				bill.getStrTravelRold(),		bill.getIntChinaTravelerNumber(),
				bill.getIntOtherTravelerNumber(),bill.getDbeChinaFee(),
				bill.getDbeOtherFee(),			bill.getDbeAllFee(),
				bill.getDteStartTime(),			bill.getDteEndTime(),			
				bill.getIntTravelType(),		bill.getIntIsHasHighDanger(),
				bill.getIntApplyUserId(),       bill.getIntPayType(),			
				bill.getIntBillStateId(),		bill.getIntIsAddTraveler(),		
				bill.getIntIsPay(),				bill.getIntIsSureByZs()
		} ;
		super.jdbcTemplate.update(sql, objs);
		return maxId ;
	}
	
	/*
	 * 查询部门投保的数目，用来给保单编号用
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getNumberOfPartmentBill(int)
	 */
	public TbBill getThePartmentMaxIdBill(int partmentId) {
		String sql = "SELECT * FROM tbBill WHERE intPartmentId=" + partmentId + " ORDER BY id desc limit 1 " ;
		return (TbBill)super.queryByObj(sql) ;
	}
	
	
	/*
	 * 检查是否存在相同的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#checkBillIsExist(cn.insurance.model.TbBill)
	 */
	public int checkBillIsExist(TbBill tbBill){
		return 1 ;
	}
	
	
	/**更新保单。。。还需要结合实际情况考虑
	 * 除了确认等信息可以更新外，其他信息均不能更新
	 * */
	public int update(TbBill bill) {
		String sql = "UPDATE tbBill SET "+
			"strCompanyName=?,intKindId=?,dteApplyDate=?,strPartmentName=?,intPartmentID=?,strSignatoryName=?,strDragoman=?,strPhoneNumber=?,strFax=?,intTeamType=?,strTeamNumber=?,intTravelProperty=?,strTravelRold=?,intChinaTravelerNumber=?,intOtherTravelerNumber=?,dbeChinaFee=?,dbeOtherFee=?,dbeAllFee=?,dteStartTime=?,dteEndTime=?,intTravelType=?,intIsHasHighDanger=?,intApplyUserId=?,intPayType=?,intBillStateId=?,intIsAddTraveler=?,intIsPay=? ,strFileUrl=?,intBeiAnReason=? , strReturnReason=? , intIsPeiKuan=?,strSureUserName=?,dteSureTime=? ,intIsSureByZs=?,intNBFeeType=?,intWBFeeType=?"
			+ " WHERE id=?" ;
		Object[] objs = {
				bill.getStrCompanyName(),		bill.getIntKindId(),
				bill.getDteApplyDate(), 		bill.getStrPartmentName(),
				bill.getIntPartmentId(),		bill.getStrSignatoryName(),
				bill.getStrDragoman(),			bill.getStrPhoneNumber(),
				bill.getStrFax(),				bill.getIntTeamType(),
				bill.getStrTeamNumber(),		bill.getIntTravelProperty(),
				bill.getStrTravelRold(),		bill.getIntChinaTravelerNumber(),
				bill.getIntOtherTravelerNumber(),bill.getDbeChinaFee(),
				bill.getDbeOtherFee(),			bill.getDbeAllFee(),
				bill.getDteStartTime(),			bill.getDteEndTime(),			
				bill.getIntTravelType(),		bill.getIntIsHasHighDanger(),
				bill.getIntApplyUserId(),     	bill.getIntPayType(),			
				bill.getIntBillStateId(),		bill.getIntIsAddTraveler(),		
				bill.getIntIsPay(),				bill.getStrFileUrl(),
				bill.getIntBeiAnReason(),      	bill.getStrReturnReason(),
				bill.getIntIsPeiKuan(),			bill.getStrSureUserName(),
				bill.getDteSureTime(), 			bill.getIntIsSureByZs(),
				bill.getIntNBFeeType(),			bill.getIntWBFeeType(),
				bill.getId()
		
		} ;
		return super.jdbcTemplate.update(sql,objs);

	}

	/*
	 * 保险公司人员更新保单(只更新保单信息，其他信息不更新)
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#updateBillByBx(cn.insurance.model.TbBill)
	 */
	public int updateBillByBx(TbBill bill) {
		String sql = "UPDATE tbBill SET "+
		"strBillName=?,strCompanyName=?,intKindId=?,dteApplyDate=?,strPartmentName=?,intPartmentID=?,strSignatoryName=?,strDragoman=?,strPhoneNumber=?,strFax=?,intTeamType=?,strTeamNumber=?,intTravelProperty=?,strTravelRold=?,intChinaTravelerNumber=?,intOtherTravelerNumber=?,dbeChinaFee=?,dbeOtherFee=?,dbeAllFee=?,dteStartTime=?,dteEndTime=?,intTravelType=?,intIsHasHighDanger=?,intApplyUserId=?,intIsAddTraveler=?,strFileUrl=?,intNBFeeType=?,intWBFeeType=?"
		+ " WHERE id=?" ;
	Object[] objs = {
			bill.getStrBillName(),
			bill.getStrCompanyName(),		bill.getIntKindId(),
			bill.getDteApplyDate(), 		bill.getStrPartmentName(),
			bill.getIntPartmentId(),		bill.getStrSignatoryName(),
			bill.getStrDragoman(),			bill.getStrPhoneNumber(),
			bill.getStrFax(),				bill.getIntTeamType(),
			bill.getStrTeamNumber(),		bill.getIntTravelProperty(),
			bill.getStrTravelRold(),		bill.getIntChinaTravelerNumber(),
			bill.getIntOtherTravelerNumber(),bill.getDbeChinaFee(),
			bill.getDbeOtherFee(),			bill.getDbeAllFee(),
			bill.getDteStartTime(),			bill.getDteEndTime(),			
			bill.getIntTravelType(),		bill.getIntIsHasHighDanger(),
			bill.getIntApplyUserId(),     	bill.getIntIsAddTraveler(),		
			bill.getStrFileUrl(),			bill.getIntNBFeeType(),
			bill.getIntWBFeeType(),			bill.getId()
	
	} ;
	return super.jdbcTemplate.update(sql,objs);
	}

	/*
	 * 按ID查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbBill getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbBill WHERE id=" + id ;
		return (TbBill)super.queryByObj(sql) ;
		
	}
	
	/*
	 * 根据保单号查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillByNumber(java.lang.String)
	 */
	public TbBill getBillByNumber(String billNumber){
		String sql = "SELECT * FROM tbBill WHERE strBillNumber='" + billNumber + "'" ;
		try{
			return (TbBill)super.queryByObj(sql) ;
		}catch(Exception e){
			return null ;
		}
	}
	
	
	/*
	 * 删除保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		/*先删除相关联的游客信息*/
		String sql = "DELETE FROM tbTravelerInfo WHERE intBillId=" +id ;
		super.update(sql) ;
		sql = "DELETE FROM tbBill WHERE id=" + id ; 
		super.update(sql) ;
		return 0 ;
	}



	
	/*
	 * 根据保单的id数组批量查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillListByIdList(java.lang.Integer[])
	 */
	public List<TbBill> getBillListByIdList(int[] ids){
		if(ids != null && ids.length>0){
			StringBuffer sql = new StringBuffer() ;
			sql.append(" SELECT * FROM tbBill WHERE id IN(") ;
			for(int id :ids){
				sql.append(id).append(",") ;
			}
			/*去掉最后一个多余的逗号*/
			sql.deleteCharAt(sql.lastIndexOf(",")) ;
			sql.append(")") ;
			return super.query(sql.toString()) ;
		}
		return null ;
	}

	
	
	/*
	 * 查询部门的已确认保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getEffectedBillByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getEffectedBillByPartmentId(PageBean pageBean ,Integer partmentId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intPartmentId=").append(partmentId) ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState4) ;
		sql.append(" ORDER BY id desc ") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill");
	}
	
	/*
	 * 查询部门的待审核保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getReferedBillByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getReferedBillByPartmentId(PageBean pageBean , Integer partmentID) {
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intPartmentId=").append(partmentID) ;
		sql.append(" AND (").append("intBillStateId=").append(CommonWords.billState2)
			.append(" OR intBillStateId=").append(CommonWords.billState3)
			.append(")") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	/*
	 * 查询部门的已备案保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBeiAnBillByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBeiAnBillByPartmentId(PageBean pageBean , Integer partmentId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intPartmentId=").append(partmentId) ;
		sql.append(" AND (intBillStateId=").append(CommonWords.billState7).append(" OR") ;
		sql.append(" intBillStateId=").append(CommonWords.billState8).append(") ") ;
		sql.append(" ORDER BY intBillStateId DESC" ) ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	/*
	 * 查询部门的已退回保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getReturnBillByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getReturnBillByPartmentId(PageBean pageBean , Integer partmentId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intPartmentId=").append(partmentId) ;
		sql.append(" AND (").append("intBillStateId=").append(CommonWords.billState5)
			.append(" OR intBillStateId=").append(CommonWords.billState6).append(")") ;
		sql.append(" ORDER BY id desc ") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
		
		
	}
	
	/*
	 * 总社查询需要总社审核的新保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForCompanyToSure(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyToSure(PageBean pageBean , Integer companyId) {
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intIsSureByZs=0 ") ;
		sql.append(" AND intBillStateId IN(").append(CommonWords.billState3).append(",").append(CommonWords.billState7).append(")") ;
		sql.append(" AND intPartmentId IN (SELECT id FROM tbPartment WHERE intParentId=").append(companyId).append(")") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	/*
	 * 总社查询总社已确认的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForCompanyHasSure(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyHasSure(PageBean bean , Integer companyId){
		/*对于旅行社已经审核过的保单，有很多种情况
		 * 保险公司未审查的
		 * 保险公司审查了的
		 * 保险公司退回的
		 * 
		 * */
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND (intBillStateId=").append(CommonWords.billState3)
//			.append(" OR intBillStateId=").append(CommonWords.billState4)
//			.append(" OR intBillStateId=").append(CommonWords.billState6)
			.append(")") ;
		;
		sql.append(" AND intPartmentId IN (SELECT id FROM tbPartment WHERE intParentId=").append(companyId).append(")") ;
		return super.getPageBean(bean, sql.toString(), "tbBill");
	}
	
	/*
	 * 查询总社退回的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForCompanyHasReturn(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyHasReturn(PageBean pageBean , Integer companyId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState5) ;
		sql.append(" AND intPartmentId IN (SELECT id FROM tbPartment WHERE intParentId=").append(companyId).append(")") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	/*
	 * 查询需要保险公司确认的新保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillNeedBxSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillNeedBxSure(PageBean pageBean) {
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState3) ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	/*
	 * 查询保险公司已备案的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForBxBeiAn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxBeiAn(PageBean pageBean){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND (intBillStateId=").append(CommonWords.billState7).append(" OR") ;
		sql.append(" intBillStateId=").append(CommonWords.billState8).append(") ") ;
		sql.append(" ORDER BY intBillStateId DESC" ) ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	
	/*
	 * 
	 * 查询保险公司已确认的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForBxHasSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasSure(PageBean pageBean){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState4) ;
		sql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	/*
	 * 查询保险公司已退回的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillForBxHasReturn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasReturn(PageBean pageBean){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState6) ;
		sql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	
	/*
	 * 部门综合各种条件查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillByComditionForUser(cn.insurance.model.PageBean, cn.insurance.model.TbBill, int[], java.lang.String, java.lang.String)
	 */
	public PageBean getBillByComditionForUser(PageBean pageBean , TbBill tbBill , int[] state , String startTime , String endTime) {
		StringBuffer sql = new StringBuffer() ;
		/*先加上部门*/
		sql.append(" AND intPartmentId=").append(tbBill.getIntPartmentId()) ;
		/*保单编号*/
		if(tbBill.getStrBillNumber() != null && !"".equals(tbBill.getStrBillNumber().trim())){
			sql.append(" AND strBillNumber='").append(tbBill.getStrBillNumber()).append("' ") ;
		}
		if(tbBill.getIntApplyUserId() != -1){
			sql.append(" AND intApplyUserId=").append(tbBill.getIntApplyUserId()) ;
		}
		/*时间段查询*/
		if(startTime != null && !"".equals(startTime.trim())){
			startTime += " 00:00:00" ;
			sql.append(" AND dteApplyDate>='").append(startTime).append("' ") ;
		}
		if(endTime != null && !"".equals(endTime.trim())){
			endTime += " 23:59:59" ;
			sql.append(" AND dteApplyDate<='").append(endTime).append("'") ;
		}
		/*保单状态*/
		if(state != null && state.length !=0){
			sql.append(" AND (id != id") ;
			for(int s : state){
				/*备案保单*/
				if(s ==1 ){
					sql.append(" OR intBillStateId=").append(CommonWords.billState7)
					.append(" OR intBillStateId=").append(CommonWords.billState8) ;
				}
				/*已确认保单*/
				if(s == 2){
					sql.append(" OR intBillStateId=").append(CommonWords.billState4) ;
				}
				/*已退回保单*/
				if(s == 3){
					sql.append(" OR intBillStateId=").append(CommonWords.billState5)
					.append(" OR intBillStateId=").append(CommonWords.billState6) ;
				}
			}
			sql.append(")") ;
		}
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	
	public PageBean getBillByComditionForUser_2010(PageBean pageBean ,String strTravelerName,String strIndentyNumber, TbBill tbBill , int[] state , String startTime , String endTime) {
		StringBuffer sql = new StringBuffer() ;
		/*先加上部门*/
		sql.append(" AND intPartmentId=").append(tbBill.getIntPartmentId()) ;
		/*保单编号*/
		if(tbBill.getStrBillNumber() != null && !"".equals(tbBill.getStrBillNumber().trim())){
			sql.append(" AND strBillNumber='").append(tbBill.getStrBillNumber()).append("' ") ;
		}
		if(tbBill.getIntApplyUserId() != -1){
			sql.append(" AND intApplyUserId=").append(tbBill.getIntApplyUserId()) ;
		}
		/*时间段查询*/
		if(startTime != null && !"".equals(startTime.trim())){
			startTime += " 00:00:00" ;
			sql.append(" AND dteApplyDate>='").append(startTime).append("' ") ;
		}
		if(endTime != null && !"".equals(endTime.trim())){
			endTime += " 23:59:59" ;
			sql.append(" AND dteApplyDate<='").append(endTime).append("'") ;
		}
		if(!strIndentyNumber.trim().equals("")){
			sql.append(" AND ID IN (SELECT intBillId FROM tbTravelerInfo WHERE strIndentyNumber = '" + strIndentyNumber +"') ");
		}
		if(!strTravelerName.trim().equals("")){
			sql.append(" AND ID IN (SELECT intBillId FROM tbTravelerInfo WHERE strTravelerName like '%" + strTravelerName + "%') ");
		}
		/*保单状态*/
		if(state != null && state.length !=0){
			sql.append(" AND (id != id") ;
			for(int s : state){
				/*备案保单*/
				if(s ==1 ){
					sql.append(" OR intBillStateId=").append(CommonWords.billState7)
					.append(" OR intBillStateId=").append(CommonWords.billState8) ;
				}
				/*已确认保单*/
				if(s == 2){
					sql.append(" OR intBillStateId=").append(CommonWords.billState4) ;
				}
				/*已退回保单*/
				if(s == 3){
					sql.append(" OR intBillStateId=").append(CommonWords.billState5)
					.append(" OR intBillStateId=").append(CommonWords.billState6) ;
				}
			}
			sql.append(")") ;
		}
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	/*
	 * 保险公司根据各种条件查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getBillByComditionForBx(cn.insurance.model.PageBean, cn.insurance.model.TbBill, java.lang.Integer, java.lang.Integer, int[], java.lang.String, java.lang.String)
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime){
		StringBuffer sql = new StringBuffer() ;
		/*部门*/
		if(partmentId != -1){
			sql.append(" AND intPartmentId=").append(partmentId) ;
		}else{
			if(companyId != -1){
				sql.append(" AND intPartmentId IN (SELECT id FROM tbPartment WHERE intParentId=").append(companyId).append(" OR id=").append(companyId).append(")") ;
			}
		}
		/*保单编号*/
		if(tbBill.getStrBillNumber() != null && !"".equals(tbBill.getStrBillNumber().trim())){
			sql.append(" AND strBillNumber='").append(tbBill.getStrBillNumber()).append("' ") ;
		}
		/*核单人*/
//		if(tbBill.getIntBxSureUserId() != -1){
//			sql.append(" AND intBxSureUserId=").append(tbBill.getIntBxSureUserId()) ;
//		}
		/*时间段查询*/
		if(startTime != null && !"".equals(startTime.trim())){
			startTime += " 00:00:00" ;
			sql.append(" AND dteApplyDate>='").append(startTime).append("'") ;
		}
		if(endTime != null && !"".equals(endTime.trim())){
			endTime += " 23:59:59" ;
			sql.append(" AND dteApplyDate<='").append(endTime).append("'") ;
		}
		/*保单状态*/
		if(state != null && state.length !=0){
			sql.append(" AND (id != id") ;
			for(int s : state){
				/*备案保单*/
				if(s ==1 ){
					sql.append(" OR intBillStateId=").append(CommonWords.billState7)
					.append(" OR intBillStateId=").append(CommonWords.billState8) ;
				}
				/*已确认保单*/
				if(s == 2){
					sql.append(" OR intBillStateId=").append(CommonWords.billState4) ;
				}
				/*已退回保单*/
				if(s == 3){
					sql.append(" OR intBillStateId=").append(CommonWords.billState5)
					.append(" OR intBillStateId=").append(CommonWords.billState6) ;
				}
			}
			sql.append(")") ;
		}else{
			/*如果一个状态都不选，则什么都查不出来*/
			sql.append(" AND id != id")  ;
		}
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	/** 修改于2010-6-9*/
	public PageBean getBillByComditionForBx_2010(PageBean pageBean ,String strIndentyNumber,String strTravelerName, TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime){
		StringBuffer sql = new StringBuffer() ;
		/*部门*/
		if(partmentId != -1){
			sql.append(" AND intPartmentId=").append(partmentId) ;
		}else{
			if(companyId != -1){
				sql.append(" AND intPartmentId IN (SELECT id FROM tbPartment WHERE intParentId=").append(companyId).append(" OR id=").append(companyId).append(")") ;
			}
		}
		/*保单编号*/
		if(tbBill.getStrBillNumber() != null && !"".equals(tbBill.getStrBillNumber().trim())){
			sql.append(" AND strBillNumber='").append(tbBill.getStrBillNumber()).append("' ") ;
		}
		/*核单人*/
//		if(tbBill.getIntBxSureUserId() != -1){
//			sql.append(" AND intBxSureUserId=").append(tbBill.getIntBxSureUserId()) ;
//		}
		/*时间段查询*/
		if(startTime != null && !"".equals(startTime.trim())){
			startTime += " 00:00:00" ;
			sql.append(" AND dteApplyDate>='").append(startTime).append("'") ;
		}
		if(endTime != null && !"".equals(endTime.trim())){
			endTime += " 23:59:59" ;
			sql.append(" AND dteApplyDate<='").append(endTime).append("'") ;
		}
		if(!strIndentyNumber.trim().equals("")){
			sql.append(" AND ID IN (SELECT intBillId FROM tbTravelerInfo WHERE strIndentyNumber = '" + strIndentyNumber +"') ");
		}
		if(!strTravelerName.trim().equals("")){
			sql.append(" AND ID IN (SELECT intBillId FROM tbTravelerInfo WHERE strTravelerName like '%" + strTravelerName + "%') ");
		}
		/*保单状态*/
		if(state != null && state.length !=0){
			sql.append(" AND (id != id") ;
			for(int s : state){
				/*备案保单*/
				if(s ==1 ){
					sql.append(" OR intBillStateId=").append(CommonWords.billState7)
					.append(" OR intBillStateId=").append(CommonWords.billState8) ;
				}
				/*已确认保单*/
				if(s == 2){
					sql.append(" OR intBillStateId=").append(CommonWords.billState4) ;
				}
				/*已退回保单*/
				if(s == 3){
					sql.append(" OR intBillStateId=").append(CommonWords.billState5)
					.append(" OR intBillStateId=").append(CommonWords.billState6) ;
				}
			}
			sql.append(")") ;
		}else{
			/*如果一个状态都不选，则什么都查不出来*/
			sql.append(" AND id != id")  ;
		}
		return super.getPageBean(pageBean, sql.toString(), "tbBill") ;
	}
	
	

	
	
	
	/*
	 * 统计有多少个新的保单需要确认
	 * 主要是为了给保险公司的业务人员提示信息(代办事务)
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getNewBillNumberNeedSureByBx()
	 */
	public int getNewBillNumberNeedSureByBx(){
		String sql = "SELECT COUNT(*) FROM tbBill WHERE intBillStateId=" + CommonWords.billState3 ;
		return super.jdbcTemplate.queryForInt(sql) ;
	}
	
	/*
	 * 统计更新后的备案保单数
	 * 主要是为了给保险公司的业务人员提示信息（代办事务）
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getNewBeiAnBillNumberNeedSureByBx()
	 */
	public int getNewBeiAnBillNumberNeedSureByBx(){
		String sql = "SELECT COUNT(*) FROM tbBill WHERE intBillStateId=" + CommonWords.billState8 ;
		return super.jdbcTemplate.queryForInt(sql) ;
	}
	
	
	/*
	 * 定时处理保单
	 * 1. 处理作废的保单（将前一天的还没有正式提交的保单清除）
	 * 2. 将已到期但还没有更新的保单退回
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#updateBillAtTheTimeByQuartz()
	 */
	public int updateBillAtTheTimeByQuartz(){
		Calendar calendar = new GregorianCalendar() ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
		/*当天*/
		String theDate = sf.format(calendar.getTime()) ;
		System.out.println("现在时间："+ theDate + ",现在执行删除无用保单和退回备案保单操作！");
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1) ;
		/*前一天*/
		String curDate = sf.format(calendar.getTime()) ;
		StringBuffer sql = new StringBuffer() ;
		/*删除前一天还没有正式提交的保单，先删除这些保单的关联信息（游客信息）*/
		sql.append("DELETE FROM tbTravelerInfo WHERE intBillId IN(SELECT id FROM tbBill WHERE intBillStateId=1 AND dteApplyDate<'").append(curDate).append("')") ;
		super.update(sql.toString()) ;
		sql = new StringBuffer() ;
		/*删除前一天的所有没有正式提交的保单，这些保单由于没有提交或删除而成为作废的保单，是垃圾数据*/
		sql.append("DELETE FROM tbBill WHERE intBillStateId=1 AND dteApplyDate<'").append(curDate).append("'") ;
		super.update(sql.toString()) ;
		sql = new StringBuffer() ;
		/*保险公司规定如果在旅游当天16点前如果没有填写旅客明细的保单将全部退回*/
		sql.append("UPDATE  tbBill SET intBillStateId=6 WHERE dteStartTime<'").append(theDate).append("' AND intBillStateId=7 AND intIsAddTraveler=0 ") ;
		return super.update(sql.toString()) ;
	}
	 
	
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(PartmentMonthStat pm) {
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbBill WHERE intPartmentId=").append(pm.getTbPartment().getId());
//		sql.append(" AND dteApplyDate>'").append(pm.getDteStartTime()).append("'") ;
//		sql.append(" AND dteApplyDate<'").append(pm.getDteEndTime()).append("'");
		sql.append(" AND YEAR(dteApplyDate)=").append(pm.getYear()) ;
		sql.append(" AND MONTH(dteApplyDate)=").append(pm.getMonth()) ;
		StringBuffer sql_1 = new StringBuffer() ;
		/*查询已确认的保单*/
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4) ;
		pm.setHasSureBillList(super.query(sql_1.toString())) ;
		
		/*查询已付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=1") ;
		pm.setHasPayBillList(super.query(sql_1.toString())) ;
		
		/*查询未付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=0") ;
		pm.setNotPayBillList(super.query(sql_1.toString())) ;
		
		/*查询被保险公司退回的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState6) ;
		pm.setReturnBillList(super.query(sql_1.toString())) ;
		
		/*查询已赔款的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intIsPeiKuan=1 ") ;
		pm.setHasPeiKuanBillList(super.query(sql_1.toString())) ;
		return pm ;
	}
	
	/*
	 * 根据年月按天统计每天的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getPartmentDayStatByDay(cn.insurance.model.tongji.PartmentDayStat)
	 */
	public PartmentDayStat getPartmentDayStatByDay(PartmentDayStat psd){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbBill WHERE intPartmentId=").append(psd.getTbPartment().getId());
//		sql.append(" AND dteApplyDate>'").append(psd.getDteStartTime()).append("'") ;
//		sql.append(" AND dteApplyDate<'").append(psd.getDteEndTime()).append("'");
		sql.append(" AND YEAR(dteApplyDate)=").append(psd.getYear()) ;
		sql.append(" AND MONTH(dteApplyDate)=").append(psd.getMonth()) ;
		sql.append(" AND DAY(dteApplyDate)=").append(psd.getDay()) ;
		StringBuffer sql_1 = new StringBuffer() ;
		/*查询已确认的保单*/
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4) ;
		psd.setHasSureBillList(super.query(sql_1.toString())) ;
		
		/*查询已付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=1") ;
		psd.setHasPayBillList(super.query(sql_1.toString())) ;
		
		/*查询未付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=0") ;
		psd.setNotPayBillList(super.query(sql_1.toString())) ;
		
		/*查询被保险公司退回的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState6) ;
		psd.setReturnBillList(super.query(sql_1.toString())) ;
		
		/*查询已赔款的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intIsPeiKuan=1 ") ;
		psd.setHasPeiKuanBillList(super.query(sql_1.toString())) ;
		return psd ;
	}
	
	/*
	 * 按时间段查询部门统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getPartmentMonthStatByTime(cn.insurance.model.tongji.PartmentTimeStat)
	 */
	public PartmentTimeStat getPartmentTimeStatByTime(PartmentTimeStat pts){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbBill WHERE intPartmentId=").append(pts.getTbPartment().getId());
		if(pts.getStartTime()!=null && !"".equals(pts.getStartTime().trim())){
			sql.append(" AND dteApplyDate>='").append(pts.getStartTime()).append("'") ;
		}
		if(pts.getEndTime()!=null && !"".equals(pts.getEndTime().trim())){
			sql.append(" AND dteApplyDate<='").append(pts.getEndTime()).append("'") ;
		}
		System.out.println(sql) ;
		StringBuffer sql_1 = new StringBuffer() ;
		/*查询已确认的保单*/
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4) ;
		pts.setHasSureBillList(super.query(sql_1.toString())) ;
		
		/*查询已付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=1") ;
		pts.setHasPayBillList(super.query(sql_1.toString())) ;
		
		/*查询未付费的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState4).append(" AND intIsPay=0") ;
		pts.setNotPayBillList(super.query(sql_1.toString())) ;
		
		/*查询被保险公司退回的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intBillStateId=").append(CommonWords.billState6) ;
		pts.setReturnBillList(super.query(sql_1.toString())) ;
		
		/*查询已赔款的保单*/
		sql_1 = new StringBuffer() ;
		sql_1.append(sql).append(" AND intIsPeiKuan=1 ") ;
		pts.setHasPeiKuanBillList(super.query(sql_1.toString())) ;
		
		return pts ;
	}
	
	
	/*
	 * 查询所有的已赔款的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getAllHasPeiKuanBillList(cn.insurance.model.PageBean)
	 */
	public PageBean getAllHasPeiKuanBillList(PageBean pageBean){
		String sql = " AND intIsPeiKuan=1" ;
		return super.getPageBean(pageBean, sql, "tbBill") ;
	}
	
	/*
	 * 通过旅行社总社的ID查询该旅行社总社及下属部门的所有已确认保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillDao#getAllPartmentEffectedBillByCompanyId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getAllPartmentEffectedBillByCompanyId(PageBean pageBean ,Integer companyId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intPartmentId IN ");
		sql.append("(SELECT id FROM tbPartment WHERE id=" +companyId +" OR intParentId=" + companyId +")") ;
		sql.append(" AND intBillStateId=").append(CommonWords.billState4) ;
		sql.append(" ORDER BY id desc ") ;
		return super.getPageBean(pageBean, sql.toString(), "tbBill");
	}
	
	
	
	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbTravelerInfoDao getTbTravelerInfoDao() {
		return tbTravelerInfoDao;
	}

	public void setTbTravelerInfoDao(ITbTravelerInfoDao tbTravelerInfoDao) {
		this.tbTravelerInfoDao = tbTravelerInfoDao;
	}

	public ITbPeiKuanDao getTbPeiKuanDao() {
		return tbPeiKuanDao;
	}

	public void setTbPeiKuanDao(ITbPeiKuanDao tbPeiKuanDao) {
		this.tbPeiKuanDao = tbPeiKuanDao;
	}



}
