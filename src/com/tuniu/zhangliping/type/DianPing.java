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
 * @author zhangliping
 *
 */
public class DianPing extends PreClassAbstract {

	
	
	public DianPing(HSSFWorkbook wb, List<ColName> colNameList,
			String sheetName, String fileName) {
		super(wb, colNameList, sheetName, fileName);
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
		Integer resourceNoMatch = 0;
		
		for (ColName colName : colNameList) {
			bookOnline = new BookOnline();
			bookOnline.setDate(new Date().toString());
			// 取：产品线目的地含大众点评字段
			if (StringUtils.isNotEmpty(colName.getDest_name()) && colName.getDest_name().contains("大众点评"))
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
				
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("网络订单"))
				{
					resourceNoMatch++;
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
		// 资源未匹配订单量
		bookOnline.setResourceNoMatch(resourceNoMatch);
		// 资源未匹配占比
		bookOnline.setResourceNoMatchRate(NumberUtil.double2String(resourceNoMatch * 100.0/ total) + "%");
		
		return bookOnline;
	}

	@Override
	public void customGenData(HSSFRow row, BookOnline bookOnline,
			HSSFSheet sheet) {
	    row.createCell((short) 8).setCellValue(bookOnline.getResourceNoMatch());
	    row.createCell((short) 9).setCellValue(bookOnline.getResourceNoMatchRate());
	}

	@Override
	public HSSFCell customGenHeader(HSSFRow row, HSSFWorkbook wb,
			HSSFSheet sheet) {
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFCellStyle style = wb.createCellStyle();  
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short 
		HSSFCell cell = null;
		cell = row.createCell((short) 8);  
		cell.setCellValue("资源未匹配订单量");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 9);  
		cell.setCellValue("资源未匹配占比");  
		cell.setCellStyle(style);
		
		return cell;
	}
}
