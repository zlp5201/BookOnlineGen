/**
 * 
 */
package com.tuniu.zhangliping.bean;

/**
 * @author zhangliping
 *
 */
public enum SourceEnum {
	CALL(1, "电话订单"), NET(2, "网络订单"),ONLINE(3, "在线预订"), ONLINENETNUM(4, "在线+网络");

    private int value;

    private String desc;

    private SourceEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
	
	
}
