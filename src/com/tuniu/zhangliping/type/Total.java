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
public class Total extends PreClassAbstract {


	public Total(HSSFWorkbook wb, List<ColName> colNameList, String sheetName,
			String fileName) {
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
		Integer noKuncunToNetTotal = 0;
		Integer distributionToNetTotal = 0;
		Integer occupyFailTotal = 0;
		Integer taobaoTotal = 0;
		Integer dianpinToNetTotal = 0;
		Integer beforeToNet = 0;
		Integer isJibao = 0;
		Integer unKownTotal = 0;
		
		for (ColName colName : colNameList) {
			bookOnline = new BookOnline();
			bookOnline.setDate(new Date().toString());
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
				
				// 取：订单来源为网络订单且客户端明细为分销
				if (StringUtils.isNotEmpty(colName.getClient_type_detail()) && colName.getClient_type_detail().contains("分销"))
				{
					if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("网络订单"))
					{
						distributionToNetTotal++;
					}
				}
				// 取：网络单原因大类中占位超时
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("占位超时"))
				{
					noKuncunToNetTotal++;
				}
				
				// 取：网络单原因大类中占位失败
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("占位失败"))
				{
					occupyFailTotal++;
				}
				
				
				// 取：网络单原因大类为网络单订单且产品目的地含淘宝两字
				if (StringUtils.isNotEmpty(colName.getReason_type()) && colName.getReason_type().contains("网络订单"))
				{
					if (StringUtils.isNotEmpty(colName.getDest_name()) && colName.getDest_name().contains("淘宝"))
					{
						taobaoTotal++;
					}
					if (StringUtils.isNotEmpty(colName.getDest_name()) && colName.getDest_name().contains("大众点评"))
					{
						dianpinToNetTotal++;
					}
					beforeToNet++;
				}
				// 取：转网络单原因为：散客酒店资源中包含即保房
				if (StringUtils.isNotEmpty(colName.getReason()) && colName.getReason().contains("散客酒店资源中包含即保房"))
				{
					isJibao++;
				}
				// 取转网络订单原因为：空白的
				if (StringUtils.isEmpty(colName.getReason()) && StringUtils.isEmpty(colName.getReason_type()))
				{
					unKownTotal++;
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
		
		// 无库存转网络单
		bookOnline.setNoKuncunToNetTotal(noKuncunToNetTotal);
		// 无库存占总订单占比
		bookOnline.setNoKuncunToNetTotalRate(NumberUtil.double2String(noKuncunToNetTotal * 100.0/ total) + "%");
		// 分销转网络单
		bookOnline.setDistributionToNetTotal(distributionToNetTotal);
		// 分销占总订单
		bookOnline.setDistributionToNetTotalRate(NumberUtil.double2String(distributionToNetTotal * 100.0/ total) + "%");
		// 占位失败
		bookOnline.setOccupyFailTotal(occupyFailTotal);
		// 占位失败占比
		bookOnline.setOccupyFailTotalRate(NumberUtil.double2String(occupyFailTotal * 100.0/ total) + "%");
		// 淘宝转网络订单
		bookOnline.setTaobaoTotal(taobaoTotal);
		// 淘宝转网络订单占比
		bookOnline.setTaobaoTotalRate(NumberUtil.double2String(taobaoTotal * 100.0/ total) + "%");
		// 点评前台转网路订单
		bookOnline.setDianpinToNetTotal(dianpinToNetTotal);
		// 点评前台转网路订单占比
		
		bookOnline.setDianpinToNetTotalRate(NumberUtil.double2String(dianpinToNetTotal * 100.0/ total) + "%");
		// 前台转网路单
		bookOnline.setBeforeToNet(beforeToNet-taobaoTotal-dianpinToNetTotal-distributionToNetTotal);
		// 即保房
		bookOnline.setIsJibao(isJibao);
		// 未知原因
		
		bookOnline.setUnKownTotal(unKownTotal);
		return bookOnline;
	}

	@Override
	public HSSFCell customGenHeader(HSSFRow row, HSSFWorkbook wb,
			HSSFSheet sheet) {
		HSSFCell cell = null;
		HSSFCellStyle style = wb.createCellStyle();  
		cell = row.createCell((short) 8);  
		cell.setCellValue("无库存转网络单");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 9);  
		cell.setCellValue("无库存占总订单占比");  
		cell.setCellStyle(style);	
		
		
		cell = row.createCell((short) 10);  
		cell.setCellValue("分销转网络单");  
		cell.setCellStyle(style);	
		
		cell = row.createCell((short) 11);  
		cell.setCellValue("分销占总订单");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 12);  
		cell.setCellValue("占位失败");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 13);  
		cell.setCellValue("占位失败占比");  
		cell.setCellStyle(style);
		
		
		cell = row.createCell((short) 14);  
		cell.setCellValue("淘宝转网络订单");  
		cell.setCellStyle(style);
		
		
		cell = row.createCell((short) 15);  
		cell.setCellValue("淘宝转网络订单占比");  
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);  
		cell.setCellValue("点评前台转网路订单");  
		cell.setCellStyle(style);
		cell = row.createCell((short) 17);  
		cell.setCellValue("点评前台转网路订单占比");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 18);  
		cell.setCellValue("前台转网路单");  
		cell.setCellStyle(style);
		
		cell = row.createCell((short) 19);  
		cell.setCellValue("即保房");  
		cell.setCellStyle(style);
		
		
		cell = row.createCell((short) 20);  
		cell.setCellValue("未知原因");  
		cell.setCellStyle(style);
		return cell;
	}

	@Override
	public void customGenData(HSSFRow row, BookOnline bookOnline,
			HSSFSheet sheet) {
		row.createCell((short) 8).setCellValue(bookOnline.getNoKuncunToNetTotal());
	    row.createCell((short) 9).setCellValue(bookOnline.getNoKuncunToNetTotalRate());
	    
	    row.createCell((short) 10).setCellValue(bookOnline.getDistributionToNetTotal());
	    row.createCell((short) 11).setCellValue(bookOnline.getDistributionToNetTotalRate());
	    row.createCell((short) 12).setCellValue(bookOnline.getOccupyFailTotal());
	    row.createCell((short) 13).setCellValue(bookOnline.getOccupyFailTotalRate());
	    row.createCell((short) 14).setCellValue(bookOnline.getTaobaoTotal());
	    row.createCell((short) 15).setCellValue(bookOnline.getTaobaoTotalRate());
	    row.createCell((short) 16).setCellValue(bookOnline.getDianpinToNetTotal());
	    row.createCell((short) 17).setCellValue(bookOnline.getDianpinToNetTotalRate());
	    row.createCell((short) 18).setCellValue(bookOnline.getBeforeToNet());
	    row.createCell((short) 19).setCellValue(bookOnline.getIsJibao());
	    row.createCell((short) 20).setCellValue(bookOnline.getUnKownTotal());
	}
}
