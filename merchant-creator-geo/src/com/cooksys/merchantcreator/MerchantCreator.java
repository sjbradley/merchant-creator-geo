package com.cooksys.merchantcreator;

import java.util.Set;

import com.cooksys.merchantcreator.model.MerchantLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MerchantCreator {

	private static final int DATA_ENTRIES = 1000;
	private static final long START_ID = 1000001;
	
	private static final String KEY_PREFIX = "merchants:";
	private static final int DEFAULT_PORT = 6379;

	private JedisPool pool;
	private Jedis jedis;
	private final ObjectMapper mapper = new ObjectMapper();

	public MerchantCreator() {
		super();
		pool = new JedisPool();
	}

	public MerchantCreator(String redisHost, int redisPort) {
		System.out.println("Redis host provided: " + redisHost + ":" + redisPort);
		pool = new JedisPool(redisHost, redisPort);
	}

	public void createMerchants() {
		MerchantGenerator generator = new MerchantGenerator();
		jedis = pool.getResource();
		
		clearRedis((KEY_PREFIX + "*"));		
		
		for (int i = 0; i < DATA_ENTRIES; i++) {
			writeToRedis(generator.generateMerchant(START_ID + i));
		}

	}
	private void clearRedis(String pattern){

		Set<String> delkeys = jedis.keys(pattern);

		for (String key : delkeys) {
			jedis.del(key);
		}
	}
	private void writeToRedis(MerchantLocation merchant) {

		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "id", merchant.getMerchantLocationId().toString());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "name", merchant.getLocationName());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "street", merchant.getAddressStreet());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "city", merchant.getAddressCity());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "state", merchant.getAddressState());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "zip", merchant.getAddressZip());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "phone", merchant.getPhone());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "lattitude", merchant.getLattitude().toString());
		jedis.hset(KEY_PREFIX + merchant.getMerchantLocationId(), "longitude", merchant.getLongitude().toString());

		jedis.geoadd(KEY_PREFIX + "geo", merchant.getLongitude(), merchant.getLattitude(),
				merchant.getMerchantLocationId().toString());
		
		try {
			jedis.zadd(KEY_PREFIX.substring(0, KEY_PREFIX.length() - 1), merchant.getMerchantLocationId(),
					mapper.writeValueAsString(merchant));
			jedis.sadd(KEY_PREFIX + "all", mapper.writeValueAsString(merchant));
			jedis.lpush(KEY_PREFIX + merchant.getAddressZip(), mapper.writeValueAsString(merchant));
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("stored " + KEY_PREFIX + merchant.getMerchantLocationId() + " in Redis");
	}
	
	public static void main(String[] args) {
		String redisHost = null;
		int redisPort = DEFAULT_PORT;

		if (args != null && args.length > 1) {
			if ("-rhost".equals(args[0])) {
				redisHost = args[1];
			}
			if (args.length > 2 && "-rport".equals(args[2])) {
				redisPort = Integer.parseInt(args[3]);
			}
		}

		MerchantCreator mc = null;

		if (redisHost == null) {
			mc = new MerchantCreator();
		} else {
			mc = new MerchantCreator(redisHost, redisPort);
		}
		mc.createMerchants();
	}

}
