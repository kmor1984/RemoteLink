package com.sxt.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {
public static Object getProxyObject(Class<?> clazz) {
	ProxyInvokeHandel proxyInvokeHandel = new ProxyInvokeHandel(clazz);
return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class<?>[] {clazz}, proxyInvokeHandel);
}
}
