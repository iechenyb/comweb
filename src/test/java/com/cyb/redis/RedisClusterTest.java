package com.cyb.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashSet;
//需要再pom.xml中引入jedis依赖  
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月25日
 */
public class RedisClusterTest {
	Log log = LogFactory.getLog(RedisClusterTest.class);
	public static void main(String[] args) {
		// 初始化集合，用于装下面的多个主机和端口
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
		String ip="127.0.0.1";
		// 创建多个主机和端口实例
		HostAndPort hostAndPort0 = new HostAndPort(ip, 6379);
		HostAndPort hostAndPort = new HostAndPort(ip, 8600);
		HostAndPort hostAndPort1 = new HostAndPort(ip, 7001);
		HostAndPort hostAndPort2 = new HostAndPort(ip, 8602);
		HostAndPort hostAndPort3 = new HostAndPort(ip, 8603);
		HostAndPort hostAndPort4 = new HostAndPort(ip, 8604);
		HostAndPort hostAndPort5 = new HostAndPort(ip, 8605);

		// 添加多个主机和端口到集合中
		nodes.add(hostAndPort0);
		nodes.add(hostAndPort);
		nodes.add(hostAndPort1);
		nodes.add(hostAndPort2);
		nodes.add(hostAndPort3);
		nodes.add(hostAndPort4);
		nodes.add(hostAndPort5);

		// 创建config
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// 通过config创建集群实例
		JedisCluster jedisCluster = new JedisCluster(nodes, poolConfig);
		// 获取集群中的key为name键的值
		String str = jedisCluster.get("who");
		jedisCluster.set("pro", "ddddd");
		System.out.println(str);
	}
}
