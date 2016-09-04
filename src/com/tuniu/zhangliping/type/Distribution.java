/**
 * 
 */
package com.tuniu.zhangliping.type;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tuniu.zhangliping.bean.BookOnline;
import com.tuniu.zhangliping.bean.ColName;
import com.tuniu.zhangliping.bean.SourceEnum;
import com.tuniu.zhangliping.gendata.PreClassAbstract;
import com.tuniu.zhangliping.util.NumberUtil;

/**
 * 分销
 * @author zhangliping
 * 
 */
public class Distribution extends PreClassAbstract {

	public Distribution(HSSFWorkbook wb, List<ColName> colNameList,
			String sheetName) {
		super(wb, colNameList, sheetName);
	}

	/* (non-Javadoc)
	 * @see com.tuniu.zhangliping.gendata.PreClassAbstract#filter(java.util.List)
	 */
	@Override
	public BookOnline filter(List<ColName> colNameList) {
		BookOnline bookOnline = null;
		Integer callNum = 0;
		Integer onlineNum = 0;
		Integer netNum = 0;
		Integer onlineNetNum = 0;
		Integer total = 0;
		Integer distributionToNet = 0;
		Integer occupyTimeout = 0;
		Integer occupyFail = 0;
		Integer unkown = 0;
		for (ColName colName : colNameList) {
			bookOnline = new BookOnline();
			bookOnline.setDate(new Date().toString());
			// 取：客户端明细为分销
			if (StringUtils.isNotEmpty(colName.getClient_type_detail()) && colName.getClient_type_detail().contains("分销"))
			{
				total++;
				if (SourceEnum.CALL.getDesc().equals(colName.getSource()))
				{
					callNum++;
				} else if (SourceEnum.ONLINE.getDesc().equals(colName.getSource()))
				{
					onlineNetNum++;
					onlineNum++;
				} else if (SourceEnum.NET.getDesc().equals(colName.getSource()))
				{
					onlineNetNum++;
					netNum++;
				}
				
				if (StringUtils.isEmpty(colName.getReason_type()))
				{
					unkown++;
				}
				// 分销前台转网络单(取：客服端明细为分销且网络单原因大类为网络订单)
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("网络订单"))
				{
					distributionToNet++;
				}
				// 分占位超时(取：客服端明细为分销且网络单原因大类为占位超时)
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("占位超时"))
				{
					occupyTimeout++;
				}
				// 占位失败(取：客服端明细为分销且网络单原因大类为占位失败)
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("占位失败"))
				{
					occupyFail++;
				}
			}
		}
		// 电话
		bookOnline.setCallNum(callNum);
		// 在线
		bookOnline.setOnlineNum(onlineNum);
		// 网络
		bookOnline.setNetNum(netNum);
		// 在线+网络
		bookOnline.setOnlineNetNum(onlineNetNum);
		// 总订单
		bookOnline.setTotal(total);
		// 完全在线预订率
		bookOnline.setFullOnlineBookingRate(NumberUtil.double2String(onlineNum * 100.0/ total) + "%");
		// 在线预订率
		bookOnline.setOnlineBookingRate(NumberUtil.double2String(onlineNetNum * 100.0/ total) + "%");
		// 分销前台转网络单
		bookOnline.setDistributionToNet(distributionToNet);
		// 分销前台转占比
		bookOnline.setDistributionToRate(NumberUtil.double2String(distributionToNet * 100.0/ total) + "%");
		// 占位超时
		bookOnline.setOccupyTimeout(occupyTimeout);
		// 占位失败
		bookOnline.setOccupyFail(occupyFail);
		// 未知
		bookOnline.setUnkown(unkown);
		return bookOnline;
	}

	@Override
	public HSSFCell genHeader(HSSFWorkbook wb, HSSFSheet sheet) {
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFCellStyle style = wb.createCellStyle();  
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFRow row = sheet.createRow((int) 0);  
		// 第四步，创建单元格，并设置值表头 设置表头居中  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		HSSFCell cell = row.createCell((short) 0);  
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
		
		cell = row.createCell((short)8);
		cell.setCellValue("分销前台转网络单");
		cell.setCellStyle(style);
		
		cell = row.createCell((short)9);
		cell.setCellValue("分销前台转占比");
		cell.setCellStyle(style);
		
		cell = row.createCell((short)10);
		cell.setCellValue("占位超时");
		cell.setCellStyle(style);
		
		cell = row.createCell((short)11);
		cell.setCellValue("占位失败");
		cell.setCellStyle(style);
		cell = row.createCell((short)12);
		cell.setCellValue("未知");
		cell.setCellStyle(style);
		return cell;
	}

	@Override
	public void genData(BookOnline bookOnline, HSSFSheet sheet) {
		HSSFRow row = null;
		row = sheet.createRow((int) 0 + 1);  
		    // 第四步，创建单元格，并设置值  
	    row.createCell((short) 0).setCellValue("2016-08-07");  
	    row.createCell((short) 1).setCellValue(bookOnline.getOnlineNum());  
	    row.createCell((short) 2).setCellValue(bookOnline.getNetNum());  
	    row.createCell((short) 3).setCellValue(bookOnline.getOnlineNetNum());  
	    row.createCell((short) 4).setCellValue(bookOnline.getCallNum());  
	    row.createCell((short) 5).setCellValue(bookOnline.getTotal());  
	    row.createCell((short) 6).setCellValue(bookOnline.getFullOnlineBookingRate());  
	    row.createCell((short) 7).setCellValue(bookOnline.getOnlineBookingRate());
	    
	    
	    
	    
	    
	    row.createCell((short) 8).setCellValue(bookOnline.getDistributionToNet());
	    row.createCell((short) 9).setCellValue(bookOnline.getDistributionToRate());
	    row.createCell((short) 10).setCellValue(bookOnline.getOccupyTimeout());
	    row.createCell((short) 11).setCellValue(bookOnline.getOccupyFail());
	    row.createCell((short) 12).setCellValue(bookOnline.getUnkown());
	}
}
