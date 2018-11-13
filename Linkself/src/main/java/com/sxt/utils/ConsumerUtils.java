package com.sxt.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sxt.anno.ConsumerService;
import com.sxt.anno.Ref;
import com.sxt.proxy.ProxyFactory;

public class ConsumerUtils {
public static Map<String,Object> consumer(String packages) throws InstantiationException, IllegalAccessException {
	/*此类的作用是通过传过来的包信息，扫描该包，扫描该类中的属性，反射成对象信息*/
	Map<String,Object> iocs=new HashMap<>();
	List<Class<?>> clazzs=PackageScanner.scanner(packages);
	for(Class<?> clazz:clazzs) {
		if(null!=clazz.getAnnotation(ConsumerService.class)) {
			Object newInstance = clazz.newInstance();
			//得到该类的所有属性
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field field : declaredFields) {
				if(null!=field.getAnnotation(Ref.class)) {
					/*该字段需要一个实例，为什么需要一个实例，
					你想一下，
					SpringMVC里面我们注入一些接口为作为一个类的一个属性，
					那注入的这个属性是一个接口啊。我们利用这个接口去new对象，
					再去引用它的实现类所以必须需要用到动态代理*/
					 //我们要做的是：实例化一个接口，两种解决方式：1.动态代理，2匿名内部类
					/*String fieldName = field.getName();
					Class<?> fieldType=field.getType();
					System.out.println("这个属性是："+fieldName+"该字段的类型是："+fieldType);*/
					//需要说明的是以下的这个field.set方是：field.set(Object obj,Object value):obj是这个类的对象
					/*value,是要往这个类里注的属性，因为这个属性是个接口，所以要要用到动态代码，往这个动态代理的方法里
					传一个该接口的类型，得到一个该接口的对象，但是该接口得到的代理对象只能看作是这个类的一个属性值。*/
					field.setAccessible(true);
					field.set(newInstance, ProxyFactory.getProxyObject(field.getType()));
					iocs.put(clazz.getName(), newInstance);
				}
			}
		}
	}
	return iocs;
}
}
