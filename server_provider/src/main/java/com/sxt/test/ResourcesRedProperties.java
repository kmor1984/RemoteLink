package com.sxt.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourcesRedProperties {
//此类就是用来读取properties的配置文件的
	 //类加载时就开始初始化配置文件
	static {
		InputStream stream = ResourcesRedProperties.class.getClassLoader().getResourceAsStream("server.properties");
				try {
					ResourceBundle resourceUtils= new PropertyResourceBundle(stream) ;
					String port=resourceUtils.getString("port");
					System.out.println(port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
