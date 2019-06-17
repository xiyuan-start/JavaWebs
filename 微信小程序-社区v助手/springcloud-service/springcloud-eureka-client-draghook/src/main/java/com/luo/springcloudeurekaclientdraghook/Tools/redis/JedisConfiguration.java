package com.luo.springcloudeurekaclientdraghook.Tools.redis;//package com.easybuy.other.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfiguration {
    @Autowired
    JedisConfig redisConfig;
    public JedisConnectionFactory convertJedisConnectionFactory() {

        System.out.println(redisConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfig.getHostname());
        jedisConnectionFactory.setPort(redisConfig.getPort());
       // jedisConnectionFactory.setPassword(redisConfig.getPassword());

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(20);
       jedisPoolConfig.setMaxWaitMillis(30000);
        jedisPoolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisConfig.isTestOnReturn());
       // jedisPoolConfig.setTestWhileIdle();

        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public StringRedisTemplate convertStringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(convertJedisConnectionFactory());
        stringRedisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());   //在这里可以设置序列化方式  这种方式可以将保存在redis 以json的形式
        return stringRedisTemplate;
    }



    @Bean(value = "template")
    public RedisTemplate<String,Object> redisTemplate()
    {
        RedisTemplate<String,Object> template =new RedisTemplate<>();
        template.setConnectionFactory(convertJedisConnectionFactory());

        Jackson2JsonRedisSerializer jsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om=new ObjectMapper();
        //指定序列化的域
        om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型(排除final类型)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(om);

        //主体选择序列化方式 JSON
        template.setDefaultSerializer(jsonRedisSerializer);

        //Map部分
        //序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        //设置hash key和value的序列化模式
        template.setHashKeySerializer( new StringRedisSerializer());
        template.setHashValueSerializer(jsonRedisSerializer);

        //表示设定结束
        template.afterPropertiesSet();
        return template;
    }


    //一对一的数据类型
    @Bean(name ="ObjectredisTemplate")
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> template)
    {
        return  template.opsForValue();
    }

}
