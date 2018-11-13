package com.sxt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
public  class ConfigReader {
/*此类的作用是读取所有的配置文件，
 * 把里面的key和value对应的储存于该类所提供的容器里面
 * 
 * */
	//说是容器，说得那么高大上，就是个Map集合，哈哈哈！
	private   static Map<String,String> env = new HashMap<>();
	/*集合建好，开始来找我们的配置文件里的KV吧。
	  * 先加载配置文件吧 
	 */
	static {
	InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream("server.properties");
	try {
		ResourceBundle config = new PropertyResourceBundle(stream);
		//得到所有的key
		Enumeration<String> keys = config.getKeys();
		//得到所有的key之后，不能用foreach来进行迭代了。没有实现此方法，所以只能用while循环来做了。
		while(keys.hasMoreElements()) {
				String key=keys.nextElement();
				String value= config.getString(key);
			    env.put(key, value);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	//对外界提供所有的配置集合
	public  static Map<String,String> getConfig(){
		return env;
	}
	//根据key对外界提供一个value
	public static String getValue(String key) {
		if(env.containsKey(key)) {
		return env.get(key);
	}else {
		return null;
	}
	
	}

	
//测试
public static void main(String [] args) {
	String host = getValue("host");
	System.out.println(host);
}
	
}
	
	
	
	
	
	

