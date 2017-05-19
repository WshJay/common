package org.wsh.common.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static javafx.scene.input.KeyCode.T;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  封装redis 缓存服务器服务接口
 * since Date： 2017/2/17 14:03
 */
@Service("redisService")
public class RedisService {

    //操作redis客户端
    private Jedis jedis;

    public Jedis getJedis() {
        return jedisConnectionFactory.getShardInfo().createResource();
    }

    @Autowired
    @Qualifier("jedisConnectionFactory") // @Qualifier解决存在多个JedisConnectionFactory问题
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        if (jedis == null) {
            System.out.println("初始化Jedis...");
            jedis = jedisConnectionFactory.getShardInfo().createResource();
        }
    }

    /**
     * 销毁
     */
    @PreDestroy
    public void destory(){
        if (jedis != null) {
            System.out.println("销毁Jedis...");
            jedis.disconnect();
        }
    }

    /**
     * 添加key value
     * @param key String
     * @param value 存储值,如存储java对象则对象必须序列化
     */
    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    /**
     * 添加key value 并且设置存活时间
     * @param key String
     * @param value 存储值,如存储java对象则对象必须序列化
     * @param liveTime 失效时间
     */
    public void set(String key, String value, int liveTime) {
        jedis.set(key, value);
        jedis.expire(key, liveTime);
    }

    /**
     * 添加key value
     * 如果已存在key则设置不成功
     * @param key String
     * @param value 存储值,如存储java对象则对象必须序列化
     */
    public boolean setnx(String key, String value) {
        long result = jedis.setnx(key, value);
        if (result > 0L){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加key value 并且设置存活时间
     * 如果已存在key则设置不成功
     * @param key String
     * @param value 存储值,如存储java对象则对象必须序列化
     * @param liveTime 失效时间
     */
    public boolean setnx(String key, String value, int liveTime) {
        long result = jedis.setnx(key, value);
        if (result > 0L){
            jedis.expire(key, liveTime);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 设置key对应的值为String类型的value,并指定此键值对应的有效期
     * @param key String
     * @param seconds 有效期(秒)
     * @param value String
     * @return String
     */
    public String setex(String key, int seconds, String value) {
        return jedis.setex(key, seconds, value);
    }

    /**
     * 替换value值
     * @param key String
     * @param offset 从第几位开始替换
     * @param value 存储值,如存储java对象则对象必须序列化
     * @return long 替换位数
     */
    public long setrange(String key, long offset, String value) {
        return jedis.setrange(key,offset,value);
    }

    /**
     * 批量添加值
     * @param keysvalues String...
     * @return 所有都设置成功则返回OK,失败返回0
     */
    public String mset(final String... keysvalues) {
        return jedis.mset(keysvalues);
    }

    /**
     * 批量添加值(如果有一个已存在则全部添加不成功)
     * @param keysvalues String...
     * @return 所有都设置成功则返回OK,失败返回0
     */
    public long msetnx(final String... keysvalues) {
        return jedis.msetnx(keysvalues);
    }

    /**
     * 获取key的值
     * @param key String
     * @return value
     */
    public String get(String key) {
        return jedis.get(key);
    }

    /**
     * 设置key的值并返回key的旧值
     * @param key String
     * @return old value
     */
    public String getSet(String key, String value) {
        return jedis.getSet(key,value);
    }

    /**
     * 获取value中的部分值
     * @param key String
     * @param startOffset 开始下标(0开始)
     * @param endOffset 结束下标
     * @return String
     */
    public String getrange(String key, long startOffset, long endOffset) {
        return jedis.getrange(key,startOffset,endOffset);
    }

    /**
     * 批量获取值
     * @param keys 多个key
     * @return List<String> 多个Value
     */
    public List<String> mget(String... keys) {
        return jedis.mget(keys);
    }

    /**
     * 递增值
     * @param key String
     * @return 返回递增后的值
     */
    public long incr(String key) {
        return jedis.incr(key);
    }

    /**
     * 递增指定值
     * 如果key不存在,则默认值为0
     * @param key String
     * @param num 增加值(负数则为减)
     * @return 返回递增后的值
     */
    public long incrBy(String key, long num) {
        return jedis.incrBy(key,num);
    }

    /**
     * 递减值
     * @param key String
     * @return 返回递减后的值
     */
    public long decr(String key) {
        return jedis.decr(key);
    }

    /**
     * 递减指定值
     * 如果key不存在,则默认值为0
     * @param key String
     * @param num 减值
     * @return 返回递减后的值
     */
    public long decrBy(String key, long num) {
        return jedis.decrBy(key,num);
    }

    /**
     * 追加值并返回追加后的值的长度
     * @param key String
     * @return new value lenght
     */
    public long append(String key, String value) {
        return jedis.append(key,value);
    }

    /**
     * 获取指定key value值的长度
     * @param key String
     * @return value lenght
     */
    public long strlen(String key) {
        return jedis.strlen(key);
    }

    /**
     * 通过key删除
     * @param key
     */
    public void del(String key) {
        jedis.del(key);
    }

    /**
     * 添加
     * @param key String 例如:user:1
     * @param field String 例如:name
     * @param value String 例如:zhangsan
     */
    public void hset(String key, String field, String value) {
        jedis.hset(key, field, value);
    }

    /**
     * 添加
     * 如果已存在key则设置不成功
     * @param key String 例如:user:1
     * @param field String 例如:name
     * @param value String 例如:zhangsan
     */
    public boolean hsetnx(String key,String field, String value) {
        long result = jedis.hsetnx(key, field, value);
        if (result > 0L){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  批量添加
     * @param key String
     * @param hash Map<String, String>
     * @return 所有都设置成功则返回OK,失败返回0
     */
    public String hmset(String key, Map<String, String> hash) {
        return jedis.hmset(key,hash);
    }

    /**
     * 获取key的值
     * @param key String
     * @return value
     */
    public String hget(String key) {
        return jedis.get(key);
    }

    /**
     *  批量获取值
     * @param key String
     * @param fileld 多个列
     * @return List<String>
     */
    public List<String> hmget(String key, String... fileld) {
        return jedis.hmget(key,fileld);
    }

    /**
     * 递增指定值
     * 如果key不存在,则默认值为0
     * @param key String
     * @param num 增加值(负数则为减)
     * @return 返回递增后的值
     */
    public long hincrBy(String key, String field, long num) {
        return jedis.hincrBy(key,field,num);
    }

    /**
     * 通过key删除
     * @param key String
     */
    public void hdel(String key) {
        jedis.hdel(key);
    }

    /**
     * 放入list
     * 栈 从list头部添加元素
     * @param key  String
     * @param strings String...
     * @return 放入个数
     */
    public long lpush(String key, String... strings) {
        return jedis.lpush(key,strings);
    }

    /**
     * 放入list
     * 栈 从list头部添加元素
     * @param key  byte[]
     * @param values byte[]
     * @return 放入个数
     */
    public long lpush(byte[] key, byte[] values) {
        return jedis.lpush(key,values);
    }

    /**
     * 放入list
     * 队列 从list尾部添加元素
     * @param key  String
     * @param strings String...
     * @return 放入个数
     */
    public long rpush(String key, String... strings) {
        return jedis.rpush(key,strings);
    }

    /**
     * 放入list
     * 队列 从list尾部添加元素
     * @param key  byte[]
     * @param values byte[]
     * @return 放入个数
     */
    public long rpush(byte[] key, byte[] values) {
        return jedis.rpush(key,values);
    }

    /**
     * 在某个元素前后插入元素
     * @param key String
     * @param where BEFORE, AFTER
     * @param pivot 元素key
     * @param value String
     * @return long
     */
    public long linsert(String key, BinaryClient.LIST_POSITION where, String pivot,String value) {
        return jedis.linsert(key,where,pivot,value);
    }

    /**
     * 从头部开始删除元素
     * @param key String
     * @return 返回删除元素值
     */
    public String lpop(String key) {
        return jedis.lpop(key);
    }

    /**
     * 从头部开始删除元素
     * @param key byte[]
     * @return 返回删除元素值
     */
    public byte[] lpop(byte[] key){
        return jedis.lpop(key);
    }

    /**
     * 从尾部开始删除元素
     * @param key String
     * @return 返回删除元素值
     */
    public String rpop(String key) {
        return jedis.rpop(key);
    }

    /**
     * 从尾部开始删除元素
     * @param key byte[]
     * @return 返回删除元素值
     */
    public byte[] rpop(byte[] key) {
        return jedis.rpop(key);
    }

    /**
     * 获取列表中的所有值
     * @param key String
     * @return List<String>
     */
    public List<String> lrange(String key) {
        return jedis.lrange(key,0,-1);
    }

    /**
     * 获取列表值数量
     * @param key String
     * @return long
     */
    public long llen(String key) {
        return jedis.llen(key);
    }

    /**
     * 获取列表中的所有值
     * @param key byte[]
     * @return List<byte[]>
     */
    public List<byte[]> lrange(byte[] key) {
        return jedis.lrange(key,0,-1);
    }

    /**
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public String rpoplpush(String srckey, String dstkey) {
        return jedis.rpoplpush(srckey,dstkey);
    }


    /**
     * 添加key value (字节)(序列化)
     * @param key byte[]
     * @param value byte[]
     */
    public void set(byte[] key, byte[] value) {
        jedis.set(key, value);
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     * @param key byte[]
     * @return byte[]
     */
    public byte[] get(byte[] key) {
        return jedis.get(key);
    }

    /**
     * 通过正则匹配keys
     * @param pattern String
     * @return Set<String>
     */
    public Set<String> keys(String pattern) {
        return jedis.keys(pattern);
    }

    /**
     * 检查key是否已经存在
     * @param key String
     * @return boolean
     */
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    /**
     * 清空redis 所有数据
     * @return String
     */
    public String flushDB() {
        return jedis.flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        return jedis.dbSize();
    }

    /**
     * 检查是否连接成功
     * @return String
     */
    public String ping() {
        return jedis.ping();
    }

    /**
     * 监控多个Key值
     * @return String
     */
    public String watch(String... keys){
        return jedis.watch(keys);
    }

    /**
     * 获取一个Jedis事务
     * @return Transaction
     */
    public Transaction multi(){
        return jedis.multi();
    }
}