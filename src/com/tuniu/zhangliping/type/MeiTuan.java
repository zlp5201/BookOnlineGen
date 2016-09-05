/**
 * 
 */
package com.tuniu.zhangliping.type;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
public class MeiTuan extends PreClassAbstract{


	public MeiTuan(HSSFWorkbook wb, List<ColName> colNameList,
			String sheetName, String fileName) {
		super(wb, colNameList, sheetName, fileName);
	}

	/**
	 * 
	 * 根据规则生成美团数据
	 */
	@Override
	public BookOnline filter(List<ColName> colNameList) {
		BookOnline bookOnline = null;
		Integer callNum = 0;
		Integer onlineNum = 0;
		Integer netNum = 0;
		Integer onlineNetNum = 0;
		Integer total = 0;
		
		for (ColName colName : colNameList) {
			bookOnline = new BookOnline();
			bookOnline.setDate(new Date().toString());
			// 取：产品线目的地含美团预付字段
			if (StringUtils.isNotEmpty(colName.getDest_name()) && colName.getDest_name().contains("美团预付"))
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
		
		return bookOnline;
	}

}
