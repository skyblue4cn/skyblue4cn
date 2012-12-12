package cn.insurance.dao.tongji;

import java.util.List;

import cn.insurance.model.tongji2.PartmentTimeStat;

public interface ITongJiByTimeSpaceDao {
	
	/**
	 * 取得时间段类的的统计数据
	 * @param partmentId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PartmentTimeStat getPartmentTimeStatList(int partmentId , String startTime , String endTime) ;
	
	
}
