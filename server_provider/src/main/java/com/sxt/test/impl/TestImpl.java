package com.sxt.test.impl;

import com.sxt.anno.ExposeService;
import com.sxt.test.Test;

@ExposeService(serverName="com.sxt.test")
public class TestImpl implements Test {

	@Override
	public String test(String info) {
		// TODO Auto-generated method stub
		return null;
	}

}
