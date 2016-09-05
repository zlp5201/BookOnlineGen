/**
 * 
 */
package com.tuniu.zhangliping.gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tuniu.zhangliping.bean.ColName;
import com.tuniu.zhangliping.gendata.IPreClass;
import com.tuniu.zhangliping.type.DianPing;
import com.tuniu.zhangliping.type.Distribution;
import com.tuniu.zhangliping.type.MeiTuan;
import com.tuniu.zhangliping.type.Qunaer;
import com.tuniu.zhangliping.type.QunaerPre;
import com.tuniu.zhangliping.type.Total;
import com.tuniu.zhangliping.type.XiechengPre;
import com.tuniu.zhangliping.util.ExcelImport;
import com.tuniu.zhangliping.util.PropertiesUtils;

/**
 * 
 * 文件报表生成
 * @author zhangliping
 *
 */
public class ReportGen{

	private static Logger log = Logger.getLogger(ReportGen.class);
	
	/**
	 *  主入口
	 * @see com.tuniu.zhangliping.type.IReportGen#gen()
	 */
	public static void main(String[] args) throws Exception{
		ReportGen reportGen = new ReportGen();
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();
		
		deal(wb);
		
		
    	// 第六步，将文件存到指定位置  
    	try  
    	{  
    		String exportPath = PropertiesUtils.getValue("exportPath");
    		String exportName = PropertiesUtils.getValue("exportName");
    		FileOutputStream fout = new FileOutputStream(exportPath + File.separatorChar + exportName);  
    		wb.write(fout);  
    		fout.close();  
    		log.info("报表生成完成，路径为：" + exportPath + File.separatorChar + exportName);
    	}
    	catch (Exception e)  
    	{  
    		e.printStackTrace();  
    	}
	}
	
	public static void deal(HSSFWorkbook wb) throws Exception
	{
    	Map<String, String> header = ColMap.GenMap();
		ExcelImport export = new ExcelImport(header);
		// 如果配置了具体的文件则读取文件，否则读取路径
		String reportSorcePath = PropertiesUtils.getValue("reportSorcePath");
		File filePath = new File(reportSorcePath);
		if (!filePath.isDirectory())
		{
			log.error("配置的reportSorcePath不是一个有效路径");
			return;
		}
		File[] fileList = filePath.listFiles();
		List<ColName> colNameList = null;
		for (File file : fileList) {
			if(file.isFile())
			{
				if (!file.getName().endsWith(".xls") && !file.getName().endsWith(".xlsx"))
				{
					continue;
				}
				log.info("正在处理文件" + file.getName());
				File file1 = new File(reportSorcePath + File.separatorChar + file.getName());
				export.init(new FileInputStream(file1));
				colNameList = export.bindToModels(ColName.class, false);
				String fileName = file.getName().replace(".xls", "").replace(".xlsx", "");
				genTable(colNameList, wb, fileName);
			}
		}
	}
	
	
	public static void genTable(List<ColName> colNameList, HSSFWorkbook wb, String name)
	{
		if (colNameList != null && colNameList.size() > 0)
    	{
    		//0、开始生成整体数据报表
    		log.info("开始生成整体数据报表");
    		IPreClass total = new Total(wb, colNameList, "整体数据", name);
    		
    		//1、开始生成携程预付报表
    		log.info("开始生成携程预付报表");
    		IPreClass xiechengPre = new XiechengPre(wb, colNameList, "携程预付",name);
    		
    		// 2、开始生成美团预付报表
    		log.info("开始生成美团预付报表");
    		IPreClass meiTuan = new MeiTuan(wb, colNameList, "美团预付", name);
    		
    		// 3、开始生成去哪儿预付
    		log.info("开始生成去哪儿预付报表");
    		IPreClass qunaerPre = new QunaerPre(wb, colNameList, "去哪儿预付", name);
    		// 4、开始生成点评团购数据
    		log.info("开始生成点评团购数据报表");
    		IPreClass dianpin = new DianPing(wb, colNameList, "点评团购数据", name);
    		// 5、开始生成去哪儿数据
    		log.info("开始生成去哪儿报表");
    		IPreClass qunaer = new Qunaer(wb, colNameList, "去哪儿", name);
    		
    		// 6、开始生成分销数据
    		log.info("开始生成分销报表");
    		IPreClass distribution = new Distribution(wb, colNameList, "分销", name);
    	}
		
	}
}
