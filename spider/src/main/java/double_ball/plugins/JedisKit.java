package double_ball.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : redis缓存类
 * @date 2017/9/8 上午11:19
 */
@Repository
public class JedisKit {
    @Autowired
    private StringRedisTemplate template;

    // #### String
    public String get(String key) {
        return this.template.opsForValue().get(key);
    }

    public void set(String key, String value) {
        this.template.opsForValue().set(key, value);
    }

    public void set(String key, String value, int ttl){
        this.template.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    public boolean exists(String key) {
        return this.template.hasKey(key);
    }

    public boolean exists(String key, String field){
        return this.template.opsForHash().hasKey(key, field);
    }

    public long incrementAndGet(final String key) {
        return this.template.execute((connection) -> {
            RedisSerializer<String> serializer = template.getStringSerializer();
            byte[] keybytes = serializer.serialize(key);
            return connection.incr(keybytes);
        }, true);
    }

    public long hincrementAndGet(final String key,final String field) {
        return this.template.execute((connection) -> {
            RedisSerializer<String> serializer = template.getStringSerializer();
            byte[] keybytes = serializer.serialize(key);
            byte[] fieldbytes = serializer.serialize(field);
            return connection.hIncrBy(keybytes,fieldbytes,1);
        }, true);
    }

    public void del(final String... keys) {
        template.delete(Arrays.asList(keys));
    }

    public void expire(String key, int seconds) {
        this.template.expire(key, seconds, TimeUnit.SECONDS);
    }

    public long getExpire(String key){
        return this.template.getExpire(key);
    }

    // #### HASH
    public String hget(String key, String field) {
        Object ret = this.template.opsForHash().get(key, field);
        return ret == null ? null : String.valueOf(ret);
    }

    public Map<String, Object> hmget(String key){
        Object ret = this.template.opsForHash().entries(key);
        return ret == null? null : (Map<String, Object>)ret;
    }

    public void hset(String key, String field, String value) {
        this.template.opsForHash().put(key, field, value);
    }

    public long hdel(String key, String... fields) {
        return this.template.opsForHash().delete(key, fields);
    }

    public boolean hexist(String key, String field) {
        return this.template.opsForHash().hasKey(key, field);
    }

    public void hmset(String key, Map<String, String> hash) {
        this.template.opsForHash().putAll(key, hash);
    }

    public Set hkeys(String key) {
        return this.template.opsForHash().keys(key);
    }

    // #### LIST
    public boolean llistpush(String key, String value) {
        return this.template.execute((connection) -> {
            RedisSerializer<String> serializer = template.getStringSerializer();
            byte[] keybytes = serializer.serialize(key);
            byte[] vbytes = serializer.serialize(value);

            long len = connection.lLen(key.getBytes());
            long retVal = connection.lPush(keybytes, vbytes);

            return retVal - len == 1;
        }, true);
    }


    /**
     * 取出所有list
     * @param key
     * @return
     */
    public List<String> lallList(String key) {
        ListOperations<String,String> operations = this.template.opsForList();

        return operations.range(key,0,-1);
    }

    public Long lrem(String key, String value) {
        return this.template.opsForList().remove(key, 0, value);
    }

    // #### SET
    public long sadd(String key, String... values) {
        return this.template.opsForSet().add(key, values);
    }

    public boolean sismember(String key, String member) {
        return this.template.opsForSet().isMember(key, member);
    }

    public long srem(String key, String... values) {
        return this.template.opsForSet().remove(key, values);
    }

    public Set<String> smembers(String key) {
        return this.template.opsForSet().members(key);
    }

    /**
     * 毫秒级过期
     * @param key
     * @param millsSecond
     */
    public void pexpire(String key,int millsSecond){
        this.template.expire(key,millsSecond,TimeUnit.MILLISECONDS);
    }

    public void zSetAdd(String key,String field){
        this.template.opsForZSet().incrementScore(key,field,1);
    }

    public Set zSetGet(String key,int begin,int end){
        return this.template.opsForZSet().range(key,begin,end);
    }
}
