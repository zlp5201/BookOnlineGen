/**
 * 
 */
package com.tuniu.zhangliping.bean;

/**
 * 在线预订结果
 * @author zhangliping
 *
 */
public class BookOnline {
	// 日期
	private String date;
	// 在线
	private Integer onlineNum;
	// 网络
	private Integer netNum;
	// 在线+网络
	private Integer onlineNetNum;
	// 电话 
	private Integer callNum;
	// 总订单
	private Integer total;
	// 完全在线预订率
	private String fullOnlineBookingRate;
	// 在线预订率
	private String onlineBookingRate;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}
	public Integer getNetNum() {
		return netNum;
	}
	public void setNetNum(Integer netNum) {
		this.netNum = netNum;
	}
	public Integer getOnlineNetNum() {
		return onlineNetNum;
	}
	public void setOnlineNetNum(Integer onlineNetNum) {
		this.onlineNetNum = onlineNetNum;
	}
	public Integer getCallNum() {
		return callNum;
	}
	public void setCallNum(Integer callNum) {
		this.callNum = callNum;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getFullOnlineBookingRate() {
		return fullOnlineBookingRate;
	}
	public void setFullOnlineBookingRate(String fullOnlineBookingRate) {
		this.fullOnlineBookingRate = fullOnlineBookingRate;
	}
	public String getOnlineBookingRate() {
		return onlineBookingRate;
	}
	public void setOnlineBookingRate(String onlineBookingRate) {
		this.onlineBookingRate = onlineBookingRate;
	}
	
}
