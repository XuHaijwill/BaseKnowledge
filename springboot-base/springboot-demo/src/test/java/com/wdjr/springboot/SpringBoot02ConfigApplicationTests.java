package com.wdjr.springboot;

import com.wdjr.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Boot的单元你测试
 * 可以在测试期间 方便的类似编码一样进行自动注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoadsLog() {

        //日志的级别
        //从低到高
        //可以调整输出的日志级别；日志就只会在这个级别以后的高级别生效
        logger.trace("这是trace日志");
        logger.debug("这是debug信息");
        //SpringBoot默认给的是info级别，如果没指定就是默认的root级别
        logger.info("这是info日志");
        logger.warn("这是warn信息");
        logger.error("这是Error信息");
    }

}
