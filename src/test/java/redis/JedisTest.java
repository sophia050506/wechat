package redis;

import redis.clients.jedis.Jedis;

public class JedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.88.128",6379);
		jedis.auth("admin");
		jedis.set("age", "20");
		System.out.println(jedis.get("name"));
	}
}
