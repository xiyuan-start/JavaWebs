package com.easybuy.other.redis;//package com.easybuy.other.redis;//package com.easybuy.other.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
@PropertySource(value = {"classpath:redis.properties"} ,encoding="UTF-8")
public class JedisConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${spring.redis.shard.hostname}")
    private String hostname;
    @Value("#{${spring.redis.shard.port}}")
    private int port;
    //@Value("${spring.redis.shard.password}")
    //private String password;
    @Value("#{${spring.redis.pool.maxIdle}}")
    private Integer maxTotal;
    @Value("#{${spring.redis.pool.maxTotal}}")
    private Integer maxIdle;

    private boolean testOnBorrow;
    private boolean testOnReturn;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }




    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }


}

