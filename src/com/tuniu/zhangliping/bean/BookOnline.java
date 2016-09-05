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
	/**
	 *  日期
	 */
	private String date;
	/**
	 *  在线
	 */
	private Integer onlineNum;
	/**
	 *  网络
	 */
	private Integer netNum;
	/**
	 *  在线+网络
	 */
	private Integer onlineNetNum;
	/**
	 *  电话 
	 */
	private Integer callNum;
	/**
	 *  总订单
	 */
	private Integer total;
	/**
	 *  完全在线预订率
	 */
	private String fullOnlineBookingRate;
	/** 
	 * 在线预订率
	 * 
	 */
	private String onlineBookingRate;
	
	/** 分销 */
	/**
	 *  分销前台转网络单
	 */
	private Integer distributionToNet;
	/**
	 *  分销前台转占比
	 */
	private String distributionToRate;
	/**
	 *  占位超时
	 */
	private Integer occupyTimeout;
	/**
	 *  占位失败
	 */
	private Integer occupyFail;
	/**
	 *  未知
	 */
	private Integer unkown;
	
	
	/** 整体数据 */
	/**
	 *  无库存转网络单
	 */
	private Integer noKuncunToNetTotal;
	/**
	 *  无库存占总订单占比
	 */
	private String noKuncunToNetTotalRate;
	/**
	 *  分销转网络单
	 */
	private Integer distributionToNetTotal;
	/**
	 *  分销占总订单
	 */
	private String distributionToNetTotalRate;
	/**
	 *  占位失败
	 */
	private Integer occupyFailTotal;
	/**
	 *  占位失败占比
	 */
	private String occupyFailTotalRate;
	/**
	 *  淘宝转网络订单
	 */
	private Integer taobaoTotal;
	/**
	 *  淘宝转网络订单占比
	 */
	private String taobaoTotalRate;
	/**
	 *  点评前台转网路订单
	 */
	private Integer dianpinToNetTotal;
	/**
	 *  点评前台转网路订单占比
	 */
	private String dianpinToNetTotalRate;
	/**
	 *  前台转网路单
	 */
	private Integer beforeToNet;
	/**
	 *  即保房
	 */
	private Integer isJibao;
	/**
	 *  未知原因
	 */
	private Integer unKownTotal;
	
	/**点评团购数据***/
	// 资源未匹配订单量
	private Integer resourceNoMatch;
	// 资源未匹配占比
	private String resourceNoMatchRate;
	
	
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
	public String getDistributionToRate() {
		return distributionToRate;
	}
	public void setDistributionToRate(String distributionToRate) {
		this.distributionToRate = distributionToRate;
	}
	public Integer getDistributionToNet() {
		return distributionToNet;
	}
	public void setDistributionToNet(Integer distributionToNet) {
		this.distributionToNet = distributionToNet;
	}
	public Integer getOccupyTimeout() {
		return occupyTimeout;
	}
	public void setOccupyTimeout(Integer occupyTimeout) {
		this.occupyTimeout = occupyTimeout;
	}
	public Integer getOccupyFail() {
		return occupyFail;
	}
	public void setOccupyFail(Integer occupyFail) {
		this.occupyFail = occupyFail;
	}
	public Integer getUnkown() {
		return unkown;
	}
	public void setUnkown(Integer unkown) {
		this.unkown = unkown;
	}
	public Integer getNoKuncunToNetTotal() {
		return noKuncunToNetTotal;
	}
	public void setNoKuncunToNetTotal(Integer noKuncunToNetTotal) {
		this.noKuncunToNetTotal = noKuncunToNetTotal;
	}
	public String getNoKuncunToNetTotalRate() {
		return noKuncunToNetTotalRate;
	}
	public void setNoKuncunToNetTotalRate(String noKuncunToNetTotalRate) {
		this.noKuncunToNetTotalRate = noKuncunToNetTotalRate;
	}
	public Integer getDistributionToNetTotal() {
		return distributionToNetTotal;
	}
	public void setDistributionToNetTotal(Integer distributionToNetTotal) {
		this.distributionToNetTotal = distributionToNetTotal;
	}
	public String getDistributionToNetTotalRate() {
		return distributionToNetTotalRate;
	}
	public void setDistributionToNetTotalRate(String distributionToNetTotalRate) {
		this.distributionToNetTotalRate = distributionToNetTotalRate;
	}
	public Integer getOccupyFailTotal() {
		return occupyFailTotal;
	}
	public void setOccupyFailTotal(Integer occupyFailTotal) {
		this.occupyFailTotal = occupyFailTotal;
	}
	public String getOccupyFailTotalRate() {
		return occupyFailTotalRate;
	}
	public void setOccupyFailTotalRate(String occupyFailTotalRate) {
		this.occupyFailTotalRate = occupyFailTotalRate;
	}
	public Integer getTaobaoTotal() {
		return taobaoTotal;
	}
	public void setTaobaoTotal(Integer taobaoTotal) {
		this.taobaoTotal = taobaoTotal;
	}
	public String getTaobaoTotalRate() {
		return taobaoTotalRate;
	}
	public void setTaobaoTotalRate(String taobaoTotalRate) {
		this.taobaoTotalRate = taobaoTotalRate;
	}
	public Integer getDianpinToNetTotal() {
		return dianpinToNetTotal;
	}
	public void setDianpinToNetTotal(Integer dianpinToNetTotal) {
		this.dianpinToNetTotal = dianpinToNetTotal;
	}
	public String getDianpinToNetTotalRate() {
		return dianpinToNetTotalRate;
	}
	public void setDianpinToNetTotalRate(String dianpinToNetTotalRate) {
		this.dianpinToNetTotalRate = dianpinToNetTotalRate;
	}
	public Integer getBeforeToNet() {
		return beforeToNet;
	}
	public void setBeforeToNet(Integer beforeToNet) {
		this.beforeToNet = beforeToNet;
	}
	public Integer getIsJibao() {
		return isJibao;
	}
	public void setIsJibao(Integer isJibao) {
		this.isJibao = isJibao;
	}
	public Integer getUnKownTotal() {
		return unKownTotal;
	}
	public void setUnKownTotal(Integer unKownTotal) {
		this.unKownTotal = unKownTotal;
	}
	public Integer getResourceNoMatch() {
		return resourceNoMatch;
	}
	public void setResourceNoMatch(Integer resourceNoMatch) {
		this.resourceNoMatch = resourceNoMatch;
	}
	public String getResourceNoMatchRate() {
		return resourceNoMatchRate;
	}
	public void setResourceNoMatchRate(String resourceNoMatchRate) {
		this.resourceNoMatchRate = resourceNoMatchRate;
	}
	
}
