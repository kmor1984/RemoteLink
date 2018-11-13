package com.sxt.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sxt.anno.ExposeService;
import com.sxt.api.Discover;
import com.sxt.utils.JedisUtil;

import redis.clients.jedis.Jedis;
@ExposeService
public class DiscoverImpl implements Discover {

	//从redis里面读取注册的服务
	@Override
	public List<String> serversDiscover(String serverName) {
		// TODO Auto-generated method stub
		Jedis jedis = JedisUtil.getJedis();
		Set<String> zrange=jedis.zrange(serverName, 0, 99);
		List<String> serverList = new ArrayList<> ();
		for(String e:zrange) {
			serverList.add(e);
		}
		return serverList;
		
	}

}
