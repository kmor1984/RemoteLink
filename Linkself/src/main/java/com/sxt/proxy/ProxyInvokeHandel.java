package com.sxt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.sxt.ClientApp;
import com.sxt.impl.DiscoverImpl;
import com.sxt.impl.ServerLoadBanlanceImpl;
import com.sxt.model.Request;

public class ProxyInvokeHandel implements InvocationHandler {
private Class<?> clazz;
private DiscoverImpl discoverImpl = new DiscoverImpl() ;
private ServerLoadBanlanceImpl banlance = new ServerLoadBanlanceImpl();
	
	public ProxyInvokeHandel() {
	}
	public ProxyInvokeHandel(Class<?> clazz) {
		this.clazz = clazz;
	}
	//每次调用对象时，这个代理的方法都要去拦截这个调用，去创建一个代理，然后调用这个代理对象即可
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 如上解释，所以这里要调用发现服务这个方法，去获取这些个服务
		List<String> serversDiscover = discoverImpl.serversDiscover(clazz.getName());
		String hostPort=banlance.serverBanlance(serversDiscover);
		Request request = new Request(clazz.getName(), method.getName(), args);
		//ip+port,远程调用就是这些个参数了
		Object invoker = ClientApp.invoker(hostPort.split(":")[0], Integer.valueOf(hostPort.split(":")[1]), request);
		return invoker;
	}
	

}
