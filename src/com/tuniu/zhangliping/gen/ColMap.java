/**
 * 
 */
package com.tuniu.zhangliping.gen;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangliping
 *
 */
public class ColMap {
	
	public static Map<String, String> GenMap()
	{
		Map<String, String> header = new HashMap<String, String>();
		header.put("订单编号", "orderId");
		header.put("产品编号", "productId");
		header.put("售卖形式", "product_business_type");
		header.put("采购方式", "pro_mode");
		header.put("订单来源", "source");
		header.put("客户端", "client_type");
		header.put("客户端明细", "client_type_detail");
		header.put("出游人数", "nums");
		header.put("预订城市", "book_city");
		header.put("下单时间", "add_time");
		header.put("一级品类", "product_class_name");
		header.put("二级品类", "product_child_class_name");
		header.put("产品线目的地", "dest_name");
		header.put("目的地大类", "dest_type_name");
		header.put("签约状态", "real_sign_status");
		header.put("转网络单原因", "reason");
		header.put("网络单原因大类", "reason_type");
		header.put("占位总时长", "revert_duration");
		header.put("资源编号", "res_main_id");
		header.put("资源名称", "resource_name");
		header.put("资源类型", "resource_type");
		header.put("酒店名称", "hotel_chinese_name");
		header.put("房型", "room_name");
		header.put("床型", "bed_name");
		header.put("staff_id", "staff_id");
		header.put("是否超级自由行", "auto_package");
		header.put("产品专员", "a1");
		header.put("三级部门名_产品", "a2");
		header.put("三级部门领导_产品", "a3");
		header.put("二级部门名_产品", "a4");
		header.put("二级部门领导_产品", "a5");
		header.put("一级部门名_产品", "a6");
		header.put("一级部门领导_产品", "a7");
		header.put("接单客服ID", "a8");
		header.put("接单客服名称", "a9");
		header.put("三级部门名_客服", "a10");
		header.put("三级部门领导_客服", "a11");
		header.put("二级部门名_客服", "a12");
		header.put("二级部门领导_客服", "a13");
		header.put("一级部门名_客服", "a14");
		header.put("一级部门领导_客服", "a15");
		return header;
	}
}
