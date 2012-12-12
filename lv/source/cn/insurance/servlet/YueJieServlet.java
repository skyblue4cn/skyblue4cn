package cn.insurance.servlet;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import cn.insurance.action.SupportAction;

/** 月份自动收取 */
@Deprecated
public class YueJieServlet extends SupportAction {

	private static final long serialVersionUID = -2025251185196363244L;

	static Timer time;

	public static boolean isRunning = false; //默认false
	
	public static Logger logger = Logger.getLogger(YueJieServlet.class);
	
	public String c;

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public void yuejie() {
		if(c == null) return;
		if (c.equals("0")) {
			distroyTimer();logger.info("月结费用关闭");
		}
		if (c.equals("1")) {
			createTimer();logger.info("月结费用开启");
		}
	}

	static void createTimer() {
		YueJieServlet.isRunning = true;
		YueJieServlet.time = new Timer();
		// 首先计算离第一次启动的时间
		long now = Calendar.getInstance().getTime().getTime(); // 获得现在的时间
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) + 1);// 设置月份为下个月
		c.set(Calendar.DATE, 1);// 设置天为1号
		c.set(Calendar.HOUR, 0); // 0小时
		c.set(Calendar.MINUTE, 0);// 0分
		c.set(Calendar.SECOND, 0);// 0秒
		long releaseTime = c.getTime().getTime() - now; // 离第一次开始执行的时间
		YueJieServlet.time.schedule(new TimerTask() {
			public void run() {
				System.out.println("---");
			}
		}, 0, 3000);// 每隔1天请求一次 releaseTime || 1000 * 60 * 60 * 24
	}

	static void distroyTimer() {
		YueJieServlet.isRunning = false;
		if(YueJieServlet.time!=null)
			YueJieServlet.time.cancel();
	}

}
