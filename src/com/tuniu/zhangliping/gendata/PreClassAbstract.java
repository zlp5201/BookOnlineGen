/**
 * 
 */
package com.tuniu.zhangliping.gendata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tuniu.zhangliping.bean.BookOnline;
import com.tuniu.zhangliping.bean.ColName;

/**
 * @author zhangliping
 *
 */
public abstract class PreClassAbstract implements IPreClass{

	
	private HSSFWorkbook wb;
	
	private List<ColName> colNameList;
	
	private String sheetName;
	
	public String fileName;
	
	
	public PreClassAbstract(HSSFWorkbook wb, List<ColName> colNameList,
			String sheetName, String fileName) {
		super();
		this.wb = wb;
		this.colNameList = colNameList;
		this.sheetName = sheetName;
		this.fileName = fileName;
		run();
	}


	/**
	 * 
	 * @param wb  execl文件
	 * @param typeName sheet页文件
	 * @return
	 */
	public HSSFCell genHeader(HSSFRow row, HSSFWorkbook wb, HSSFSheet sheet)
	{
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFCellStyle style = wb.createCellStyle();  
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFCell cell = null;
//		if (sheet.getLastRowNum() == 0)
//		{
//			HSSFRow row = sheet.createRow(sheet.getLastRowNum());  
			cell = row.createCell((short) 0);  
			// 第四步，创建单元格，并设置值表头 设置表头居中  
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
			cell = row.createCell((short) 0);  
			cell.setCellValue("日期");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1);
			cell.setCellValue("在线");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2);  
			cell.setCellValue("网络");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("在线+网络");  
			cell.setCellStyle(style);  
			
			cell = row.createCell((short) 4);  
			cell.setCellValue("电话");  
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 5);  
			cell.setCellValue("总订单");  
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 6);  
			cell.setCellValue("完全在线预订率");  
			cell.setCellStyle(style);
			
			cell = row.createCell((short) 7);  
			cell.setCellValue("在线预订率");  
			cell.setCellStyle(style);
			
			customGenHeader(row, wb, sheet);
//		}
		
		return cell;
	}
	
	
	/**
	 * 
	 * @param colNameList execl读取的文件
	 * @return 在线预订率计算结果
	 */
	public void genData(HSSFRow row, BookOnline bookOnline, HSSFSheet sheet)
	{
//		row = sheet.createRow(sheet.getLastRowNum() + 1);  
		    // 第四步，创建单元格，并设置值  

	    row.createCell((short) 0).setCellValue(fileName);  
	    row.createCell((short) 1).setCellValue(bookOnline.getOnlineNum());  
	    row.createCell((short) 2).setCellValue(bookOnline.getNetNum());  
	    row.createCell((short) 3).setCellValue(bookOnline.getOnlineNetNum());  
	    row.createCell((short) 4).setCellValue(bookOnline.getCallNum());  
	    row.createCell((short) 5).setCellValue(bookOnline.getTotal());  
	    row.createCell((short) 6).setCellValue(bookOnline.getFullOnlineBookingRate());  
	    row.createCell((short) 7).setCellValue(bookOnline.getOnlineBookingRate()); 
	    customGenData(row, bookOnline, sheet);
	}
	
	/**
	 * 
	 * @param colNameList execl读取的文件
	 * @return 在线预订率计算结果
	 */
	public abstract BookOnline filter(List<ColName> colNameList);
	
	
	/**
	 * 
	 * @param colNameList execl读取的文件
	 * @return 在线预订率计算结果
	 */
	public void customGenData(HSSFRow row, BookOnline bookOnline, HSSFSheet sheet){
		
	}
	
	
	public HSSFCell customGenHeader(HSSFRow row, HSSFWorkbook wb, HSSFSheet sheet){
		return null;
	}

	@Override
	public void run() {
		// 判断sheet存在不存在，存在则直接写入，不存在在生成
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (null == sheet)
		{
			sheet  = wb.createSheet(sheetName);
		}
		HSSFRow row = null;
		if (sheet.getLastRowNum() == 0)
		{
			row = sheet.createRow(sheet.getLastRowNum());  
			genHeader(row, wb, sheet);
		}
		BookOnline bookOnline  = filter(colNameList);
		row = sheet.createRow(sheet.getLastRowNum() + 1);  
		genData(row, bookOnline, sheet);
		
	}
}
