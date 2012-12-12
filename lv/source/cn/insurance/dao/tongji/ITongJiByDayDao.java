package cn.insurance.dao.tongji;

import java.util.List;

import cn.insurance.model.tongji2.PartmentDayStat;

/**
 * 按天统计
 * @author 系统管理员
 *
 */
public interface ITongJiByDayDao {
	
	/**
	 * 统计该月每天的数据
	 * @param partmentId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<PartmentDayStat> getPartmentDayStatList(int partmentId , int year,int month) ;
	
}
