package top.zy.common.util.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;


    private static final Long SUCCESS=1L;

    private static final Long EXECUTE_SUCCESS =1L ;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_SUCCESS = "OK";
    private static final String UNLOCK_LUA =
            "local key=KEYS[1]" +
                    "local value=ARGV[2]" +
                    "if redis.call(\"GET\",key)==value then" +
                    " return redis.call(\"DEL\",key)" +
                    "else" +
                    "return 0" +
                    "end";

    //设置分布式锁
    public boolean  setnx(String key,String value,long timeout){
        Object result = redisTemplate.execute((RedisCallback<String>) redisConnection -> {
            JedisCommands jedis = (JedisCommands ) redisConnection.getNativeConnection();
            return jedis.set(key,value,"NX","PX",timeout);
        });
        if ("OK".equals(result)){
            return true;
        }
        return false;
    }
    //释放分布式锁
    public boolean unlock(String key,String value){
        Object result = redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
           Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            return   jedis.evalsha(UNLOCK_LUA,Collections.unmodifiableList(Arrays.asList(key)),Collections.unmodifiableList(Arrays.asList(value)));
        });
        if (SUCCESS.equals(result)){
            return true;
        }
        return false;
    }



    /**
     * 执行lua脚本
     * @param keys  key集合
     * @param argv   value集合
     * @param luaScript  执行脚本
     * @return
     */
       public Object executeLua(List<String> keys, List<String> argv,String luaScript){
        return redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            Jedis jedis= (Jedis) redisConnection.getNativeConnection();
            return  jedis.eval(luaScript,keys,argv);
         });
       }
}
