package com.sxt.utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private  static JedisPool pool = null;
	static {
		/*要注意：我们用configReader这个类时，
		只有静态的方法才可以用类+方法名来用，
		如果不是静态的方法，则不用直接用
		只能new一个实例后再调用该类里面的方法*/
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(ConfigReader.getValue("redis.pool.max.total").equals(null)?"10":ConfigReader.getValue("redis.pool.max.total")));
		poolConfig.setMaxIdle(Integer.parseInt(ConfigReader.getValue("redis.pool.max.idle").equals(null)?"10":ConfigReader.getValue("redis.pool.max.idle")));
		poolConfig.setMinIdle(Integer.parseInt(ConfigReader.getValue("redis.pool.min.minidle").equals(null)?"10":ConfigReader.getValue("redis.pool.min.minidle")));
		String host=ConfigReader.getValue("redis.pool.host");
		 pool = new JedisPool(poolConfig, host);
	}
	//得到池
	public JedisPool getPool () {
		return pool;
	}
	//得到资源
	public static    Jedis getJedis() {
		return pool.getResource();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
