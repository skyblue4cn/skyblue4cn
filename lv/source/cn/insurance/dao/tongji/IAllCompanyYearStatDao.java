package cn.insurance.dao.tongji;

import java.util.List;

import cn.insurance.model.tongji2.AllCompanyYearStat;
import cn.insurance.model.tongji2.CompanyYearStat;

public interface IAllCompanyYearStatDao {
	
	/**
	 * 按年统计所有旅行社的所有保单
	 * @return
	 */
	public List<AllCompanyYearStat> getAllCompanyYearStatList(int year) ;
	

	
}
