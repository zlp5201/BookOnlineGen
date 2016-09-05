/**
 * 
 */
package com.tuniu.zhangliping.gendata;


import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tuniu.zhangliping.bean.BookOnline;
import com.tuniu.zhangliping.bean.ColName;

/**
 * @author zhangliping
 *
 */
public interface IPreClass {
	
	/**
	 * 
	 * @param wb  execl文件
	 * @param typeName sheet页文件
	 * @return
	 */
	public abstract HSSFCell genHeader(HSSFRow row, HSSFWorkbook wb, HSSFSheet sheet);
	
	/**
	 * 
	 * @param colNameList execl读取的文件
	 * @return 在线预订率计算结果
	 */
	public abstract void genData(HSSFRow row, BookOnline bookOnline, HSSFSheet sheet);
	
	/**
	 * 
	 * @param colNameList execl读取的文件
	 * @return 在线预订率计算结果
	 */
	public abstract BookOnline filter(List<ColName> colNameList);
	
	/**
	 * 模版模式执行报表生成
	 */
	public abstract void run();
	
}
