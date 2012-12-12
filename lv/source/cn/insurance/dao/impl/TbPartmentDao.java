package cn.insurance.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import cn.insurance.model.PageBean;
import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.TbPartment;

public class TbPartmentDao extends BaseDao implements ITbPartmentDao{
	
	private TbAccountDao tbAccountDao ;
	
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbPartment tbPartment = new TbPartment() ;
		tbPartment.setId(rs.getInt("id")) ;
		tbPartment.setIntParentId(rs.getInt("intParentId")) ;
		tbPartment.setStrAddress(rs.getString("strAddress")) ;
		tbPartment.setStrFax(rs.getString("strFax")) ;
		tbPartment.setStrLicenceNumber(rs.getString("strLicenceNumber")) ;
		tbPartment.setStrPartmentName(rs.getString("strPartmentName")) ;
		tbPartment.setStrPhoneNumber(rs.getString("strPhoneNumber")) ;
		tbPartment.setStrZipcode(rs.getString("strZipcode")) ;
		tbPartment.setIntIsNeedSureBill(rs.getInt("intIsNeedSureBill")) ;
		tbPartment.setDteResEndDate(rs.getTimestamp("dteResEndDate")) ;
		tbPartment.setDteResStartDate(rs.getTimestamp("dteResStartDate")) ;
		tbPartment.setStrConnectPeople(rs.getString("strConnectPeople")) ;
		tbPartment.setStrImgUrl(rs.getString("strImgUrl")) ;
		tbPartment.setStrLxsBillNumber(rs.getString("strLxsBillNumber")) ;
		tbPartment.setIntIsSeePartmentBill(rs.getInt("intIsSeePartmentBill")) ;
		tbPartment.setIsShowHistoryBill(rs.getInt("isShowHistoryBill")) ;
		tbPartment.setTbAccount(tbAccountDao.getAccountByPartmentId(tbPartment.getId())) ;
		
		if(tbPartment.getIntParentId() ==0 ){
			/*如果该用户是旅行社用户*/
			tbPartment.setCompany(tbPartment);
		}else{
			/*如果是部门的用户，则查出该旅行社的名称*/
			tbPartment.setCompany((TbPartment)getObjectInfoById(tbPartment.getIntParentId()));
		}
		
		
		
		return tbPartment ;
	}

	public Integer create(TbPartment tbPartment) {
		// TODO Auto-generated method stub
		if(checkData(tbPartment)){
			/*检查名称是否相同*/
			return 0 ;
		}
		/*因为除了添加部门，还需要同时添加这个部门的帐户，所有需要添加的部门ID来作为返回值*/
		Integer maxId = super.getMaxId("tbPartment", "id") ;
		String sql = "INSERT INTO tbPartment(id,strPartmentName,intParentId,strAddress,strLicenceNumber,strPhoneNumber,strZipcode,strFax,strConnectPeople,intIsNeedSureBill,dteResStartDate,dteResEndDate,strImgUrl,strLxsBillNumber,intIsSeePartmentBill,isShowHistoryBill) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		Object[] objs={
				maxId,
				tbPartment.getStrPartmentName(),
				tbPartment.getIntParentId(),
				tbPartment.getStrAddress(),
				tbPartment.getStrLicenceNumber(),
				tbPartment.getStrPhoneNumber(),
				tbPartment.getStrZipcode(),
				tbPartment.getStrFax(),
				tbPartment.getStrConnectPeople(),
				tbPartment.getIntIsNeedSureBill(),
				tbPartment.getDteResStartDate(),
				tbPartment.getDteResEndDate(),
				tbPartment.getStrImgUrl(),
				tbPartment.getStrLxsBillNumber(),
				tbPartment.getIntIsSeePartmentBill(),
				tbPartment.getIsShowHistoryBill()
		} ;
		Integer result = jdbcTemplate.update(sql,objs);
		if(result !=0){
			return maxId ;
		}
		return 0 ;
	}
	


	/**检查部门是否有重复*/
	private boolean checkData(TbPartment tbPartment){
		String sql = "SELECT COUNT(*) FROM tbPartment WHERE strPartmentName='" + tbPartment.getStrPartmentName() + "' AND intParentId=" + tbPartment.getIntParentId() ;
		return super.checkData(sql) ;
	}
	
	/*更新*/
	public int update(TbPartment tbPartment) {
		String sql = "UPDATE tbPartment SET strPartmentName=?,intParentId=?,strAddress=?,strLicenceNumber=?,strPhoneNumber=?,strZipcode=?,strFax=?,strConnectPeople=?,intIsNeedSureBill=?,dteResStartDate=?,dteResEndDate=?,strImgUrl=?,strLxsBillNumber=?, intIsSeePartmentBill=?,isShowHistoryBill=? WHERE id=?" ;
		Object[] objs={
				tbPartment.getStrPartmentName(),
				tbPartment.getIntParentId(),
				tbPartment.getStrAddress(),
				tbPartment.getStrLicenceNumber(),
				tbPartment.getStrPhoneNumber(),
				tbPartment.getStrZipcode(),
				tbPartment.getStrFax(),
				tbPartment.getStrConnectPeople(),
				tbPartment.getIntIsNeedSureBill(),
				tbPartment.getDteResStartDate(),
				tbPartment.getDteResEndDate(),
				tbPartment.getStrImgUrl(),
				tbPartment.getStrLxsBillNumber(),
				tbPartment.getIntIsSeePartmentBill(),
				tbPartment.getIsShowHistoryBill(),
				tbPartment.getId()
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
		
	}
	
	/*查询全部*/
	@SuppressWarnings("unchecked")
	public List<TbPartment> getAllObjectList() {
		String sql = "SELECT * FROM tbPartment WHERE intIsUse=" + TbPartment.IS_USE_PARTMENT ;
		return super.query(sql);
	}
	

	/*
	 * 查询所有的旅行社
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getAllCompanyList()
	 */
	@SuppressWarnings("unchecked")
	public List<TbPartment> getAllCompanyList(String value){
		//String sql = "SELECT * FROM tbPartment WHERE intParentId=0 AND intIsUse=0 ;" ;
		String sql="SELECT * FROM tbPartment WHERE intParentId=0 AND intIsUse=" + TbPartment.IS_USE_PARTMENT +" AND strPartmentName LIKE '%"+value+"%'";
		//super.getJdbcTemplate().queryForList(sql);  

	//	System.out.println(list.size());
		return super.query(sql) ;
	}

	/*
	 * 查询所有的旅行社
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getAllCompanyList()
	 */
	@SuppressWarnings("unchecked")
	public List<TbPartment> getAllCompanyList(){
		String sql = "SELECT * FROM tbPartment WHERE intParentId=0 AND intIsUse=" + TbPartment.IS_USE_PARTMENT ;
		System.out.println(sql);
		return super.query(sql) ;
	}

	public TbPartment getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbPartment WHERE id=" + id;
		return (TbPartment)super.queryByObj(sql);
	}

	/*
	 * 根据帐户ID查询部门
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getTbPartmentByAccountId(java.lang.Integer)
	 */
	public TbPartment getTbPartmentByAccountId(Integer accountId){
		String sql = "SELECT * FROM tbPartment WHERE id=(SELECT intPartmentId FROM tbAccount WHERE id=" + accountId +" LIMIT 1)" ;
		return (TbPartment)super.queryByObj(sql) ;
	}
	
	/*
	 * 通过帐户ID查询部门(一个部门对应一个帐户)
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getPartmentInfoByAccountId(java.lang.Integer)
	 */
	public TbPartment getPartmentInfoByAccountId(Integer accountId) {
		String sql = "SELECT a.* from tbPartment a,tbAccount b WHERE a.id=b.intPartmentId AND b.id="+accountId ;
		return (TbPartment)super.queryByObj(sql) ;
	}
	
	/**根据ID查询旅行社或部门名称*/
	@SuppressWarnings("unused")
	private String getPartmentName(Integer id){
		String sql = "SELECT strPartmentName from tbPartment WHERE id=" + id;
		return (String)super.jdbcTemplate.queryForObject(sql, String.class) ;
	}
	
	/*
	 * 根据旅行社ID查询其所有的部门并分页
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getAllPartmentPageBeanByCompanyId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getAllPartmentPageBeanByCompanyId(PageBean pageBean , Integer companyId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intParentId=").append(companyId) ;
		sql.append(" AND intIsUse=" + TbPartment.IS_USE_PARTMENT ) ;
		return super.getPageBean(pageBean, sql.toString(), "tbPartment") ;
		
	}

	/*
	 * 根据旅行社ID查询所有的部门并分页
	 * non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#getAllPartmentListByCompanyId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<TbPartment> getAllPartmentListByCompanyId(Integer companyId){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbPartment WHERE intParentId=").append(companyId) ;
		sql.append(" AND intIsUse=" + TbPartment.IS_USE_PARTMENT ) ;
		return super.query(sql.toString()) ;
	}
	
	/*
	 *  * 查询所有旅行社的下级部门(不包括总社)
	 * 用来生成树形结构里
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<TbPartment> getAllCompanyPartmentList(){
		StringBuffer sql = new StringBuffer() ;
		sql.append("SELECT * FROM tbPartment WHERE intParentId!=0 ");
		sql.append(" AND intIsUse=" + TbPartment.IS_USE_PARTMENT ) ;
		return super.query(sql.toString()) ;
	}
	
	
	
	/*
	 * 删除
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPartmentDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbPartment SET intIsUse=" +TbPartment.IS_NOT_USER_PARTMENT +" WHERE id=" + id ;
		return super.update(sql);
	}


	public PageBean getPageBean(PageBean pageBean, String conditionSql) {
		// TODO Auto-generated method stub
		return super.getPageBean(pageBean, conditionSql, "tbPartment") ;
		
	}

	
	

	public TbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}

	public void setTbAccountDao(TbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}
}
