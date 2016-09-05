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
			String sheetName, String fileName) {
		super(wb, colNameList, sheetName, fileName);
		// TODO Auto-generated constructor stub
	}

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
	public void customGenData(HSSFRow row, BookOnline bookOnline,
			HSSFSheet sheet) {
	    row.createCell((short) 8).setCellValue(bookOnline.getDistributionToNet());
	    row.createCell((short) 9).setCellValue(bookOnline.getDistributionToRate());
	    row.createCell((short) 10).setCellValue(bookOnline.getOccupyTimeout());
	    row.createCell((short) 11).setCellValue(bookOnline.getOccupyFail());
	    row.createCell((short) 12).setCellValue(bookOnline.getUnkown());
		
	}

	@Override
	public HSSFCell customGenHeader(HSSFRow row, HSSFWorkbook wb,
			HSSFSheet sheet) {
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFCellStyle style = wb.createCellStyle();  
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFCell cell = null;
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
}
