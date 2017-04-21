package org.datasupport.test;

import org.datasupport.test.dao.JdbcDaoImpl;
import org.datasupport.test.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcDaoImpl jdbcDaoImpl = applicationContext.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

        Circle circle = jdbcDaoImpl.getCircle(1);
        System.out.println(circle.getName());



    }

}
