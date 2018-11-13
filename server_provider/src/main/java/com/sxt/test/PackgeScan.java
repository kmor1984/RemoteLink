package com.sxt.test;

import java.io.File;

import com.sxt.anno.ExposeService;

public class PackgeScan {
   /*该方法就是测试根据文件在服务器上存放的地址
    * ，去拿到实际的存储路径，
    * 然后进行字符串截取，得到包的名称
    *再得到文件名，就是类名了，根据类名进行反射得到需要暴露的服务名 
    * 得到服务之后就可以进行服务发布了
    * 
    * */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//判断如下路径下的所有文件
       File dir = new File ("D:\\workspace-sts\\0528-adv\\server_provider\\src\\main\\java\\com\\sxt\\test\\impl");
		if(dir.isDirectory()) {
			File [] listFiles = dir.listFiles();
			for (File file : listFiles) {
				//System.out.println(file);查看后为D:\workspace-sts\0528-adv\server_provider\src\main\java\com\sxt\test\impl\TestImpl.java
			String fileName=file.getPath().replace("D:\\workspace-sts\\0528-adv\\server_provider\\src\\main\\java\\", "").replace("\\", ".");
			String className = fileName.substring(0, fileName.lastIndexOf("."));
			//根据这个类的名称来反射这个类
				Class<?> clazz = Class.forName(className);
			//再扫描这个类是否是有暴露服务
				if(clazz.getAnnotation(ExposeService.class)!=null) {
					System.out.println("该类为暴露的类！");
					
				}
				
			}
			
			}
		
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


