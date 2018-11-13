package com.sxt.test.impl;

import com.sxt.anno.ExposeService;
import com.sxt.core.TestRegist;
@ExposeService(serverName="com.sxt.core.TestRegist")
public class TestRegistImpl implements TestRegist {

	@Override
	public String add(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return String.valueOf(a+b);
	}

}
