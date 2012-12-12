package cn.insurance.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.log.LogUtils;

import cn.insurance.dao.conn.ConnectionManager;
import cn.insurance.model.TbPartment;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.service.admin.impl.AdminPartmentServ;

/**
 * 旅行社管理中的树的节点ID 
 * 无法判断是旅行社还是旅行社的部门（关系到导航条中显示下级部门）
 * @author yqg
 *
 */
public class TreeUtil {
	
	/**
	 * 通过节点ID判断是不是旅行社总社
	 * @param nodeid
	 * @return
	 */
	public static boolean isCompany(int nodeid){
		Connection conn = null ;
		PreparedStatement st = null ;
		ResultSet rs = null ;
		String sql = "SELECT intParentId FROM tbPartment WHERE id=?" ;
		try{
			conn = ConnectionManager.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, nodeid);
			rs = st.executeQuery();
			if(rs.next()){
				int parentId = rs.getInt(1) ;
				if(parentId == 0){
					return true ;
				}
			}
		}catch(Exception e){
			PrintLog.getLog().error( e);
		}finally{
			ConnectionManager.close(rs, st, conn);
		}
		return false ;
	}
	
	
}
