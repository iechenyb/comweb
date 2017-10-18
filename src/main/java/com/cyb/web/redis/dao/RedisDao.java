package com.cyb.web.redis.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisListCommands.Position;
import org.springframework.data.redis.connection.RedisZSetCommands.Tuple;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao{

	@Resource(name = "redisTemplate")
	protected RedisTemplate<String, Object> redisTemplate;
	
	
	@Resource(name = "stringRedisTemplate")
	protected StringRedisTemplate stringRedisTemplate;
	
	
	public Set<String> keys(String pattern){
		return stringRedisTemplate.keys(pattern);	
	}
	
	/**
	 * 设置key
	 */
	public Boolean set(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					connection.set(keys, values);
					return true;
				}
			});
		}
		return false;
	}

	/**
	 * 根据key获取对象
	 */
	public String get(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = connection.get(keys);
					if (values == null) {
						return null;
					}
					String value = serializer.deserialize(values);
					return value;
				}
			});
		}
		return null;
	}

	/**
	 * 根据key删除
	 * 
	 * @param key
	 * @return
	 */
	public Long del(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.del(keys);
				}
			});
		}
		return null;
	}

	/**
	 * 某段时间后执行
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean expire(final String key, final long value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.expire(keys, value);
				}
			});
		}
		return false;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean expireAt(final String key, final long value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.expireAt(keys, value);
				}
			});
		}
		return false;
	}

	/**
	 * 查询剩余时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long ttl(final String key, final long value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.ttl(keys);
				}
			});
		}
		return 0l;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.exists(keys);
				}
			});
		}
		return false;
	}

	/**
	 * 返回 key 所储存的值的类型
	 * 
	 * @param key
	 * @return
	 */
	public DataType type(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<DataType>() {
				public DataType doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.type(keys);
				}
			});
		}
		return null;
	}

	/**
	 * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Boolean setBit(final String key, final long offset,
			final boolean value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					connection.setBit(keys, offset, value);
					return true;
				}
			});
		}
		return false;
	}

	/**
	 * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean getBit(final String key, final long value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.getBit(keys, value);
				}
			});
		}
		return false;
	}

	/**
	 * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Boolean setRange(final String key, final Long offset,
			final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					connection.setRange(keys, values, offset);
					return true;
				}
			});
		}
		return false;
	}

	/**
	 * 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定
	 * 
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 */
	public byte[] getRange(final String key, final long startOffset,
			final long endOffset) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<byte[]>() {
				public byte[] doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.getRange(keys, startOffset, endOffset);
				}
			});
		}
		return null;
	}

	/**
	 * 删除对象 ,依赖key
	 */
	public void delete(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		delete(list);
	}

	/**
	 * 删除集合 ,依赖key集合
	 */
	private void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素
	 * 
	 * @param keyStr
	 * @param count
	 * @param valueStr
	 * @return
	 */
	public Long lrem(final String key, final long count, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.lRem(keys, count, values);
				}
			});
		}
		return null;
	}

	/**
	 * 将一个或多个值 value 插入到列表 key 的表头
	 * 
	 * @param keyStr
	 * @param valueStr
	 * @return
	 */
	public Long lpush(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.lPush(keys, values);
				}
			});
		}
		return null;
	}

	public byte[] getSet(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<byte[]>() {
				public byte[] doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.getSet(keys, values);
				}
			});
		}
		return null;
	}

	public Boolean setNX(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.setNX(keys, values);
				}
			});
		}
		return null;
	}

	public Boolean setEx(final String key, final Long seconds,
			final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					connection.setEx(keys, seconds, values);
					return true;
				}
			});
		}
		return false;
	}

	public Long decrBy(final String key, final long integer) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.decrBy(keys, integer);
				}
			});
		}
		return null;
	}

	public Long decr(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.decr(keys);
				}
			});
		}
		return null;
	}

	public Long incrBy(final String key, final long integer) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.incrBy(keys, integer);
				}
			});
		}
		return null;
	}

	public Long incr(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.incr(keys);
				}
			});
		}
		return null;
	}

	public Long append(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.append(keys, values);
				}
			});
		}
		return null;
	}

	public Boolean hSet(final String key, final String field, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					byte[] values = serializer.serialize(value);
					return connection.hSet(keys, fields, values);
				}
			});
		}
		return null;
	}

	public byte[] hGet(final String key, final String field) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<byte[]>() {
				public byte[] doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					return connection.hGet(keys, fields);
				}
			});
		}
		return null;
	}

	public Boolean hSetNX(final String key, final String field,
			final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					byte[] values = serializer.serialize(value);
					return connection.hSetNX(keys, fields, values);
				}
			});
		}
		return null;
	}

	public Boolean hMSet(final String key, final Map<byte[], byte[]> hash) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					connection.hMSet(keys, hash);
					return true;
				}
			});
		}
		return false;
	}

	/**
	 * 返回哈希表 key 中，一个或多个给定域的值
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hMGet(final String key, final byte[]... fields) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<List<String>>() {
				public List<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					List<String> data = new ArrayList<String>();
					byte[] keys = serializer.serialize(key);
					List<byte[]> re = connection.hMGet(keys, fields);
					for (byte[] by : re) {
						data.add(serializer.deserialize(by));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Long hIncrBy(final String key, final String field, final long value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					return connection.hIncrBy(keys, fields, value);
				}
			});
		}
		return null;
	}

	public Boolean hExists(final String key, final String field) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					return connection.hExists(keys, fields);
				}
			});
		}
		return null;
	}

	public Long hDel(final String key, final String field) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] fields = serializer.serialize(field);
					return connection.hDel(keys, fields);
				}
			});
		}
		return null;
	}

	public Long hLen(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.hLen(keys);
				}
			});
		}
		return null;
	}

	public Set<String> hKeys(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<String> data = new HashSet<String>();
					Set<byte[]> re = connection.hKeys(keys);
					for (byte[] by : re) {
						data.add(serializer.deserialize(by));
					}
					return data;
				}
			});
		}
		return null;
	}

	public List<String> hVals(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<List<String>>() {
				public List<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					List<String> data = new ArrayList<String>();
					List<byte[]> re = connection.hVals(keys);
					for (byte[] by : re) {
						data.add(serializer.deserialize(by));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Map<byte[], byte[]> hGetAll(final String key) {
		if (redisTemplate != null) {
			return redisTemplate
					.execute(new RedisCallback<Map<byte[], byte[]>>() {
						public Map<byte[], byte[]> doInRedis(
								RedisConnection connection)
								throws DataAccessException {
							RedisSerializer<String> serializer = getRedisSerializer();
							byte[] keys = serializer.serialize(key);
							return connection.hGetAll(keys);

						}
					});
		}
		return null;
	}

	// ================list ====== l表示 list或 left, r表示right====================
	public Long rPush(final String key, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					return connection.rPush(keys, values);

				}
			});
		}
		return null;
	}

	public Long lLen(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.lLen(keys);
				}
			});
		}
		return null;
	}

	public List<String> lRange(final String key, final Long start,
			final Long end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<List<String>>() {
				public List<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					List<String> data = new ArrayList<String>();
					List<byte[]> re = connection.lRange(keys, start, end);
					for (byte[] by : re) {
						data.add(serializer.deserialize(by));
					}
					return data;
				}
			});
		}
		return null;
	}
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 保留指定区间的元素<br>
     *创建时间: 2017年7月15日hj12
     *@param key
     *@param start
     *@param end
     *@return
     */
	public Boolean lTrim(final String key, final long start, final long end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					connection.lTrim(keys, start, end);
					return true;
				}
			});
		}
		return false;
	}

	public String lIndex(final String key, final long index) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] re = connection.lIndex(keys, index);
					return serializer.deserialize(re);
				}
			});
		}
		return null;
	}

	public Boolean lSet(final String key, final long index, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] values = serializer.serialize(value);
					connection.lSet(keys, index, values);
					return true;
				}
			});
		}
		return false;
	}

	public String lPop(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] re = connection.lPop(keys);
					return serializer.deserialize(re);
				}
			});
		}
		return null;
	}

	public String rPop(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] re = connection.rPop(keys);
					return serializer.deserialize(re);
				}
			});
		}
		return null;
	}

	public Long sAdd(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.sAdd(keys, members);
				}
			});
		}
		return null;
	}

	public Set<String> sMembers(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.sMembers(keys);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Long sRem(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.sRem(keys, members);
				}
			});
		}
		return null;
	}

	public String sPop(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] re = connection.sPop(keys);
					return serializer.deserialize(re);
				}
			});
		}
		return null;
	}

	public Long sCard(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.sCard(keys);
				}
			});
		}
		return null;
	}

	public Boolean sIsMember(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.sIsMember(keys, members);
				}
			});
		}
		return null;
	}

	public String sRandMember(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] re = connection.sRandMember(keys);
					return serializer.deserialize(re);
				}
			});
		}
		return null;
	}

	public Boolean zAdd(final String key, final double score,
			final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zAdd(keys, score, members);
				}
			});
		}
		return null;
	}

	public Set<String> zRange(final String key, final int start, final int end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRange(keys, start, end);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Long zRem(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zRem(keys, members);
				}
			});
		}
		return null;
	}

	public Double zIncrBy(final String key, final double score,
			final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Double>() {
				public Double doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zIncrBy(keys, score, members);
				}
			});
		}
		return null;
	}

	public Long zRank(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zRank(keys, members);
				}
			});
		}
		return null;
	}

	public Long zRevRank(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zRevRank(keys, members);
				}
			});
		}
		return null;
	}

	public Set<String> zRevRange(final String key, final int start,
			final int end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRevRange(keys, start, end);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRangeWithScores(final String key, final int start,
			final int end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRangeWithScores(keys, start, end);
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRevRangeWithScores(final String key, final int start,
			final int end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRevRangeWithScores(keys, start, end);
				}
			});
		}
		return null;
	}

	public Long zCard(final String key) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zCard(keys);
				}
			});
		}
		return null;
	}

	public Double zScore(final String key, final String member) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Double>() {
				public Double doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] members = serializer.serialize(member);
					return connection.zScore(keys, members);
				}
			});
		}
		return null;
	}

	public List<String> sort(final String key, final SortParameters params) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<List<String>>() {
				public List<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					List<String> data = new ArrayList<String>();
					List<byte[]> re = connection.sort(keys, params);
					for (byte[] by : re) {
						data.add(serializer.deserialize(by));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Long zCount(final String key, final double min, final double max) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zCount(keys, min, max);
				}
			});
		}
		return null;
	}

	public Long linsert(final String key, final Position where,
			final String pivot, final String value) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					byte[] pivots = serializer.serialize(pivot);
					byte[] values = serializer.serialize(value);
					return connection.lInsert(keys, where, pivots, values);
				}
			});
		}
		return null;
	}

	public Set<String> zRangeByScore(final String key, final double min,
			final double max) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRangeByScore(keys, max, min);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Set<String> zRangeByScore(final String key, final double min,
			final double max, final int offset, final int count) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRangeByScore(keys, max, min,
							offset, count);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRangeByScoreWithScores(final String key,
			final double min, final double max) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRangeByScoreWithScores(keys, max, min);
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRangeByScoreWithScores(final String key,
			final double min, final double max, final int offset,
			final int count) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRangeByScoreWithScores(keys, max, min,
							offset, count);
				}
			});
		}
		return null;
	}

	public Set<String> zRevRangeByScore(final String key, final double max,
			final double min) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRevRangeByScore(keys, max,
							min);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Set<String> zRevRangeByScore(final String key, final double max,
			final double min, final int offset, final int count) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<String>>() {
				public Set<String> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					Set<byte[]> set = connection.zRevRangeByScore(keys, max,
							min, offset, count);
					Set<String> data = new HashSet<String>();
					for (byte[] s : set) {
						data.add(serializer.deserialize(s));
					}
					return data;
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRevRangeByScoreWithScores(final String key,
			final double max, final double min) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection
							.zRevRangeByScoreWithScores(keys, max, min);
				}
			});
		}
		return null;
	}

	public Set<Tuple> zRevRangeByScoreWithScores(final String key,
			final double max, final double min, final int offset,
			final int count) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Set<Tuple>>() {
				public Set<Tuple> doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRevRangeByScoreWithScores(keys, max,
							min, offset, count);
				}
			});
		}
		return null;
	}

	public Long zRemRangeByScore(final String key, final double start,
			final double end) {
		if (redisTemplate != null) {
			return redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					RedisSerializer<String> serializer = getRedisSerializer();
					byte[] keys = serializer.serialize(key);
					return connection.zRemRangeByScore(keys, start, end);
				}
			});
		}
		return null;
	}

	/**
	 * 获取 RedisSerializer
	 * 
	 */
	protected RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

}