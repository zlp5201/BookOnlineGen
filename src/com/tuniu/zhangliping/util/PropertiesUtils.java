/**
 * 
 */
package com.tuniu.zhangliping.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取工具类
 * @author zhangliping
 *
 */
public class PropertiesUtils {
	  private static Properties prop = null;

	  static { prop = new Properties();
	    InputStream in = PropertiesUtils.class.getResourceAsStream("/config.properties");
	    try {
	      BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));  
	      prop.load(bf);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  public static String getValue(String key)
	  {
	    return prop.getProperty(key);
	  }

	  public static void SetValue(String key, String value)
	  {
	    prop.put(key, value);
	  }
}
