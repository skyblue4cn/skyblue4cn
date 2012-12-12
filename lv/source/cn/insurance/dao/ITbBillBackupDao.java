package cn.insurance.dao;

import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;

public interface ITbBillBackupDao {
	
	/**
	 * 添加保单备份(bill是要备份的保单信息，billbackup是备份记录，包括更新的用户信息，备注等)
	 * @param bill
	 * @param intRemarkId
	 * @return
	 */
	public int addBillBackup(TbBill bill ,TbBillBackup tbBillBackup)  ;
}
