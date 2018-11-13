package com.sxt;

import java.util.Map;

import com.sxt.impl.ConsumerServerImpl;
import com.sxt.utils.ConsumerUtils;

public class TestConsumer {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Map<String, Object> consumer = ConsumerUtils.consumer("com.sxt.impl");
		ConsumerServerImpl result = (ConsumerServerImpl) consumer.get("com.sxt.impl.ConsumerServerImpl");
    String consumer2 = result.consumer(1, 2);
    System.out.println("远程调用结果为："+consumer2);
	}

}
