package com.sxt.impl;

import java.util.List;
import java.util.Random;

import com.sxt.api.ServerLoadBanlance;

public class ServerLoadBanlanceImpl implements ServerLoadBanlance {

	@Override
	public String serverBanlance(List<String> services) {
		// TODO Auto-generated method stub
		//nextInt()这个方法是从0~n,但不包括n。
		int index = new Random().nextInt(services.size());
		return services.get(index);
	}

}
