package com.sxt.impl;
import java.util.HashMap;
import java.util.Map;

import com.sxt.anno.ExposeService;
import com.sxt.api.Regist;
import com.sxt.utils.JedisUtil;

import redis.clients.jedis.Jedis;
@ExposeService(serverName="Regist")
public class RegistImpl implements Regist {
   public double score = 1.0;
	@Override
	public void serverRegist(String serverName, String host) {
		// TODO Auto-generated method stub
		Jedis jedis = JedisUtil.getJedis();
		Map<String,Double> info = new HashMap<String,Double>(); 
		info.put(host, score);
		jedis.zadd(serverName,info );
		score++;
		jedis.close();
	}

}
