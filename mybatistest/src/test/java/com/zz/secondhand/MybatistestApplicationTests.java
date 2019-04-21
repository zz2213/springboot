package com.zz.secondhand;

import com.alibaba.fastjson.JSONObject;
import com.zz.secondhand.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatistestApplicationTests {

    private JSONObject json = new JSONObject();
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        stringRedisTemplate.opsForValue().set("teststring","赵振");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {
      stringRedisTemplate.opsForValue().get("teststring");
    }

    /**
     * 插入对象
     */
    @Test
    public void setObject() {

    }

    /**
     * 获取对象
     */
    @Test
    public void getObject() {

    }

    /**
     * 插入对象List
     */
    @Test
    public void setList() {

    }

    /**
     * 获取list
     */
    @Test
    public void getList() {

    }

    @Test
    public void remove() {

    }


}
class Person {
    private String name;
    private String sex;

    public Person() {

    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
