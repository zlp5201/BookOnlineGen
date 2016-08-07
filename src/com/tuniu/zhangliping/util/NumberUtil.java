/**
 * 
 */
package com.tuniu.zhangliping.util;


/**
 * @author zhangliping
 *
 */
public class NumberUtil {
	
	public static String double2String(Double source)
	{
		String get_double = String.format("%.2f",source); 
		return get_double;
	}
	
	public static void main(String[] args) {
		System.out.println(double2String(25.655));
	}
}
