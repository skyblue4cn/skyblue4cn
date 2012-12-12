package cn.insurance.utils;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import cn.insurance.model.TbBill;

/**
 * 给保单生成确认码
 * 还是用的acegi的加密方法加密
 * @author zhuyan
 *
 */
public class BillCheckMd5Code {

	private static String CHECKKEY = "PICC_SUD_LVYOU";
	
	/**
	 * 通过保单号 + 内宾人数 + 外宾人数 + 确认日期
	 * 可以通过查看保单上的数据即可判断该保单是否是有系统投保生成
	 * 确认日期可以兼顾到时效性
	 * @param tbBill 保单对象 主要包括以下几个内容
	 * @param billNumber 保单号
	 * @param nbNumber 保单上内宾人数
	 * @param wbNumber 保单上的外宾人数
	 * @param sureDate 保单的确认日期格式为2008-02-10
	 * @return
	 */
	public static String getTheMd5Code(TbBill tbBill){
		StringBuffer sb = new StringBuffer();
		sb.append(tbBill.getStrBillNumber().trim()) ;
		sb.append(tbBill.getIntChinaTravelerNumber()) ;
		sb.append(tbBill.getIntOtherTravelerNumber()) ;
		String sureTime = DateUtil.getFormatDate(tbBill.getDteSureTime(), "yyyy-MM-dd") ;
		sb.append(sureTime.trim()) ;
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder() ;
		String md5Code = md5PasswordEncoder.encodePassword(sb.toString(), CHECKKEY) ;
		return md5Code.toUpperCase() ;
	}
	
	
	public static void main(String[] args){
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder() ;
		String md5Code = md5PasswordEncoder.encodePassword("123",CHECKKEY) ;
		System.out.println(md5Code) ;
		
	}
	
}
