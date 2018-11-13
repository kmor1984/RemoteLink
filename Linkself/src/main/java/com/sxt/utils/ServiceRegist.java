package com.sxt.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxt.anno.ExposeService;
import com.sxt.api.Regist;
import com.sxt.impl.RegistImpl;

public class ServiceRegist {
	public static Map<String,Object> ioss= new HashMap<>();
public static Map<String, Object> regist(String packages,int port) throws InstantiationException, IllegalAccessException {
	Map<String, Object> iocs = new HashMap<String,Object>();
	/*得到所要暴露的类的class文件list集合之后，进行遍历注册*/
	List<Class<?>> classs=PackageScanner.scanner(packages);
	Regist regist= new RegistImpl();
	for(Class<?> service :classs) {
		ExposeService server = service.getAnnotation(ExposeService.class);
		if(server!=null) {
			String serverName=server.serverName();
			if(serverName.equals("")) {
				serverName=service.getName();
			}
			
			Object instance =service.newInstance();   
			iocs.put(instance.getClass().getInterfaces()[0].getName(), instance);
			regist.serverRegist(serverName, "localhost:"+port);
			
		}
		ioss.putAll(iocs);
	}
	return iocs;
}
//测试：
/*public static void main(String [] args) throws InstantiationException, IllegalAccessException {
	regist("com.sxt.impl");
	System.out.println();
	System.out.println("注册完成！");
	
}*/
}
