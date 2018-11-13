package com.sxt.test;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.sxt.ServerApp;
import com.sxt.utils.ConfigReader;
import com.sxt.utils.ServiceRegist;

public class TestPublish {
public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
	Integer port = Integer.parseInt(ConfigReader.getValue("port"));  
	ServiceRegist.regist("com.sxt.test.impl",port);
	System.out.println("注册成功！");
	
	System.out.println("该服务注册在"+port+"上面");
	ServerApp.serverPublish(Integer.valueOf(port.equals(null)?8888:port));
    
}
}
