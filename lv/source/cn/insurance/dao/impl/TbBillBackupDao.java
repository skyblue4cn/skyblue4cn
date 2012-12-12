package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillBackupDao;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;

public class TbBillBackupDao extends BaseDao implements ITbBillBackupDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	
	public int addBillBackup(TbBill bill ,TbBillBackup tbBillBackup) {
		String sql = "INSERT INTO tbBillBackup(intBillId,strBillNumber,strBillName,strCompanyName,intKindId,dteApplyDate,strPartmentName,intPartmentId,strSignatoryName,strDragoman,strPhoneNumber,strFax,intTeamType,strTeamNumber,intTravelProperty,strTravelRold,intChinaTravelerNumber,intOtherTravelerNumber,dbeChinaFee,dbeOtherFee,dbeAllFee,dteStartTime,dteEndTime,intTravelType,intIsHasHighDanger,intApplyUserId,intPayType,intBillStateId,intIsAddTraveler,intIsPay ,strFileUrl,intBeiAnReason , strReturnReason , intIsPeiKuan,strSureUserName,dteSureTime ,intIsSureByZs,intBxUpdateUserId,strBxUpdateUserName,dteBxUpdateTime,strBxUpdateDesc)"
			+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				bill.getId(),					bill.getStrBillNumber(),
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
				bill.getIntApplyUserId(),     	bill.getIntPayType(),			
				bill.getIntBillStateId(),		bill.getIntIsAddTraveler(),		
				bill.getIntIsPay(),				bill.getStrFileUrl(),
				bill.getIntBeiAnReason(),      	bill.getStrReturnReason(),
				bill.getIntIsPeiKuan(),			bill.getStrSureUserName(),
				bill.getDteSureTime(), 			bill.getIntIsSureByZs(),
				tbBillBackup.getIntBxUpdateUserId(), tbBillBackup.getStrBxUpdateUserName(),
				new Date(),						tbBillBackup.getStrBxUpdateDesc()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
}
