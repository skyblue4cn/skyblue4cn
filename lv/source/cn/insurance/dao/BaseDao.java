package cn.insurance.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.insurance.model.PageBean;

public abstract class BaseDao {

	
	protected JdbcTemplate jdbcTemplate  ;
	
	/**
	 * 通用方法：插入、更新和删除数据库数据
	 * 
	 * @param sql
	 * @return boolean
	 * @throws SQLException 
	 */
	protected int update(String sql){
		if(sql == null){
			return 0 ;
		}
		return jdbcTemplate.update(sql) ;
	}
	
	
	
	 /**
     * 取得刚插入的新记录的ID
     * 
     * @param ds
     */
    public int getNewRecordID()
    {
        String sql = "SELECT LAST_INSERT_ID() ";
        int n = jdbcTemplate.queryForInt(sql);
        return n;
    }
	
	/**
	 * 通用方法：查询一个表中的最大值，第一个参数是表名，第二个参数是要查询的id名（有可能ID，id  Id）
	 * @param tableName
	 * @param idName
	 * @return
	 */
	protected Integer getMaxId(String tableName,String idName){
		String sql = "SELECT MAX(" + idName +") AS count FROM " + tableName ;
		return jdbcTemplate.queryForInt(sql)+1 ;
	}
	/**
	 * 通用方法：查询所有的对象，返回结果有可能是null 
	 * @param sql
	 * @return List
	 */
	protected List query(String sql){
		return jdbcTemplate.query(sql,new ObjRowMapper());
	}
	
	/**
	 * 通用方法：对数据库进行查询，返回一个对象，返回结果有可能是null
	 * @param sql
	 * @return Object
	 */
	protected Object queryByObj(String sql){
		Object obj = null ;
		try{
			obj = jdbcTemplate.queryForObject(sql,new ObjRowMapper()) ;
		}catch(Exception e){
			return null ;
		}
		return obj ;
	}

	/**
	 * 根据表名和id的数组来得到批量删除的sql语句
	 * @param tableName
	 * @param idName
	 * @param ids
	 * @return
	 */
	protected String getDeleteListSql(String tableName ,String idName , List<Integer> ids ){
		if(ids==null || ids.size() ==0){
			return null ;
		}
		StringBuffer sql = new StringBuffer() ;
		sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(idName).append(" in (") ;
		for(Integer id: ids){
			sql.append(id).append(",") ;
		}
		/*去掉最后一个多于的逗号*/
		sql.deleteCharAt(sql.lastIndexOf(",")).append(")") ;
		return sql.toString() ;
	}

	/**根据每个对象的不同而更改建模对象的方法*/
	protected abstract Object mapObj(ResultSet rs) throws SQLException;
	
	/**对象建模*/
	class ObjRowMapper implements RowMapper{

		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			if(rs == null){
				return null ;
			}
			return mapObj(rs);
		}
		
	}
	
	
	/**
	 * 有些查询需要传参数来查询，防止注入
	 */
	protected Object queryForObject(String sql , Object[] objs){
		
		return jdbcTemplate.queryForObject(sql, objs, new ObjRowMapper()) ;
	}
	
	
	/**专门用来检测是否有相同的数据存在使用的通用方法方法
	 * 存在返回true
	 * 不存在返回false
	 * 注意：sql语句只查一个字段（count（*））
	 * */
	public boolean checkData(String sql){
		Integer num = jdbcTemplate.queryForInt(sql) ;
		if(num>0){
			return true ;
		}
		return false ;
	}
	
	/*分页程序*/
	
	public PageBean getPageBean(PageBean pageBean  , String conditionSql , String tableName ){
		//System.out.println("SELECT * FROM " + tableName +" WHERE id=id " + conditionSql ) ;
		/*首先查处所有记录个数*/
		String getAllNumberSql = "SELECT COUNT(*) FROM " + tableName + " WHERE id=id " + conditionSql ;
		pageBean.setAllNumber(jdbcTemplate.queryForInt(getAllNumberSql)) ;
		/*设置最大页数*/
		pageBean.setMaxPage(pageBean) ;
		/*设置当前页的第一个数据的索引*/
		pageBean.setCurFromIndex(pageBean.getCurPage(), pageBean.getRowsPerPage()) ;
		/*设置分页的页数*/
		pageBean.setNumList(pageBean.getMaxPage()) ;
		if(pageBean.getCurPage()<1 || pageBean.getCurPage()>pageBean.getMaxPage()){
			return pageBean ;
		}
		String querySql = "SELECT * FROM " + tableName +" WHERE id=id " + conditionSql ;
		querySql += " limit " + ((pageBean.getCurPage()-1)*pageBean.getRowsPerPage() )+ "," + pageBean.getRowsPerPage() ; 
		pageBean.setDataList(query(querySql)) ;
		return pageBean ;
	}
	
	
	

	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	

	
}