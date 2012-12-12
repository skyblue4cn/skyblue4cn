package cn.insurance.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbMonthPayOutLog;
import cn.insurance.model.TbUser;
import cn.insurance.service.admin.IAdminMonthFeeServ;
import cn.insurance.utils.DateUtil;
import cn.insurance.utils.PrintLog;

public class ExecuteAffairAtTimeByQuartz {
		
	private ITbBillDao tbBillDao ;
	
	/*mysql备份的命令*/
	private String beiFenCmd  ;
	
	private String filePath ;
	
	private String travelFilePath ;
	
	private ITbMessageDao tbMessageDao ;
	
	private IAdminMonthFeeServ adminMonthFeeServ ;
	
	/*删除备案保单或者是作废保单*/
	public void deleteBeiAnOrUnUseBill(){
		tbBillDao.updateBillAtTheTimeByQuartz() ;
	}
	
	/**
	 * 将数据库中的数据做备份
	 * 将公司的文件做备份
	 *
	 */
	public void beiFenSqlAndFile(){
		/*给数据库做备份，直接拷贝数据库安装目录下的data下的文件*/
		Calendar c = new GregorianCalendar() ;
		try
		{
			StringBuffer sb = new StringBuffer() ;
			/*备份文件的名称是lvyou-yyyy-MM-dd.sql*/
			sb.append("cmd /c ").append(beiFenCmd).append(" > ").append(filePath).append("/lvyou-").append(DateUtil.getFormatDate(c, "yyyy-MM-dd")).append(".sql") ;
			System.out.println("开始执行旅游保险数据备份！");
			System.out.println("执行命令" + sb);
			Runtime.getRuntime().exec(sb.toString()) ;
			System.out.println("数据备份执行完毕！") ;
		}catch(Exception e){
			System.err.println("数据备份执行错误！") ;
		}
		/*删除15天前的的备份*/
		c.set(Calendar.DATE, c.get(Calendar.DATE)-15) ;
		StringBuffer oldFilePath = new StringBuffer() ;
		oldFilePath.append(filePath).append("/lvyou-").append(DateUtil.getFormatDate(c, "yyyy-MM-dd")).append(".sql") ;
		File file = new File(oldFilePath.toString()) ;
		System.out.println("现在删除" + DateUtil.getFormatDate(c, "yyyy-MM-dd") +"的备份") ;
		if(file.exists()){
			file.delete() ;
		}
		
		/*删除一个月以前的所有信息，减少数据量*/
		deleteMessageForLastMonth() ;
	}
	
	/**
	 * 删除一个月以前的所有信息
	 *
	 */
	private void deleteMessageForLastMonth(){
		Calendar calendar = new GregorianCalendar() ;
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1) ;
		tbMessageDao.deleteMessageByDate(DateUtil.getFormatDate(calendar, "yyyy-MM-dd")) ;
	}
	
	
	/**
	 * 每个月1号凌晨3点
	 * 1.对上月月费进行结算
	 * 结算的原因：由于保单在确认后可能更改，但是月费类的费用并没有更改，所以需要在每月对月费进行一次确认性质的结算
	 * 2.收取预存账户的月费
	 */
	public void jiesuanAndShouFei(){
		jiesuan();
		shouYuCunMonthFee() ;
	}
	
	/**
	 * 对月费进行一次确认性质的结算
	 */
	public void jiesuan(){
		Calendar calendar = new GregorianCalendar() ;
		if(calendar.get(Calendar.DAY_OF_MONTH) == 1){
			PrintLog.getLog().info("开始结算") ;
			String year = String.valueOf(calendar.get(Calendar.YEAR)) ;
			String month = String.valueOf(calendar.get(Calendar.MONTH)) ;//应该是当前时间的上月，由于月是从0开始  所以是:月+1-1=月  
			adminMonthFeeServ.jieSuan(year,month) ;
			PrintLog.getLog().info("结算完成！") ;
		}
	}
	
	/**
	 * 对预存帐户收取月费
	 */
	public void shouYuCunMonthFee(){
		Calendar calendar = new GregorianCalendar() ;
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH)) ;
		if(calendar.get(Calendar.DAY_OF_MONTH) == 1 ){
			PrintLog.getLog().info("开始收取上月的预存账户的月费！") ;
			String year = String.valueOf(calendar.get(Calendar.YEAR)) ;
			String month = String.valueOf(calendar.get(Calendar.MONTH)) ; //应该是当前时间的上月，由于月是从0开始 
			List<TbMonthPayInfo> allLastNotPayYuCunMonthFeeList = adminMonthFeeServ.getAllNotPayYuCunMonthFee(year,month) ;
			if(allLastNotPayYuCunMonthFeeList != null && allLastNotPayYuCunMonthFeeList.size()>0){
				for(TbMonthPayInfo tbMonthPayInfo : allLastNotPayYuCunMonthFeeList){
					TbMonthPayOutLog tbMonthPayOutLog = new TbMonthPayOutLog();
					tbMonthPayOutLog.setDbePayNumber(tbMonthPayInfo.getDbeNeedToPay()) ;
					tbMonthPayOutLog.setDtePayDate(calendar.getTime()) ;
					tbMonthPayOutLog.setIntMonthPayId(tbMonthPayInfo.getId()) ;
					tbMonthPayOutLog.setIntUserId(TbUser.sysUser().getId()) ;
					tbMonthPayOutLog.setStrDesc("账户为预存账户，系统在1号凌晨3点自动收取上月费用！") ;
					tbMonthPayOutLog.setStrUserName(TbUser.sysUser().getStrUserName()) ;
					adminMonthFeeServ.shouFei(tbMonthPayInfo.getId(), TbMonthPayInfo.ACCOUNT_PAY_TYPE, tbMonthPayOutLog) ;
				}
			}
			PrintLog.getLog().info("收取预存账户的月费完成！") ;
		}
	}
	
	
	public static void main(String[] args){
		ExecuteAffairAtTimeByQuartz eb = new ExecuteAffairAtTimeByQuartz() ;
		eb.beiFenSqlAndFile() ;
	}

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}

	public String getBeiFenCmd() {
		return beiFenCmd;
	}

	public void setBeiFenCmd(String beiFenCmd) {
		this.beiFenCmd = beiFenCmd;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTravelFilePath() {
		return travelFilePath;
	}

	public void setTravelFilePath(String travelFilePath) {
		this.travelFilePath = travelFilePath;
	}

	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}

	public IAdminMonthFeeServ getAdminMonthFeeServ() {
		return adminMonthFeeServ;
	}

	public void setAdminMonthFeeServ(IAdminMonthFeeServ adminMonthFeeServ) {
		this.adminMonthFeeServ = adminMonthFeeServ;
	}

	
	
	
	
}
