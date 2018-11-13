package com.sxt.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {
	
	
	
	public static List<Class<?>> scanner (String packages){
		/*从当前线程里获取类的全路径，找到这个类，再把类信息存到List集合里面*/
		String projectDir=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String path=projectDir+packages.replace(".", "/");
		File dir=new File(path);
		List<Class<?>> classs= new ArrayList<>();
		if(dir.isDirectory()) {
			File[] files= dir.listFiles();
			for (File file : files) {
				String javaClassName = file.getPath().replace(dir.getPath(), "").replace("\\", ".");
				String className = packages+javaClassName.substring(0, javaClassName.lastIndexOf("."));
				Class<?> clazz=null;
				try {
					clazz = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				classs.add(clazz);
			}
			
		}
		return classs;
	}	

	/*//测试：
	 
	  public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Class<?>> result=scanner("com.sxt.model");
		result.forEach(e->System.out.println(e.getName()));
		
	}*/

}
