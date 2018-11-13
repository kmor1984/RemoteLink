package com.sxt.impl;
import com.sxt.anno.ConsumerService;
import com.sxt.anno.Ref;
import com.sxt.api.ConsumerServer;
import com.sxt.core.TestRegist;
//这个是自定义注解用作在类上
@ConsumerService
public class ConsumerServerImpl implements ConsumerServer {
	//这个是一个引用的注解，是作用在字段上面的注意看它的引用类型：@Target({ ElementType.FIELD })
    @Ref
      private TestRegist testRegist;
	@Override 
	public String consumer(Integer a ,Integer b) {
		String add = testRegist.add(a, b);
		return add;
	}

}
