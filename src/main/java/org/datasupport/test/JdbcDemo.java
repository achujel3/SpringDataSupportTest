package org.datasupport.test;

import org.datasupport.test.dao.HibernateDaoImpl;
import org.datasupport.test.dao.JdbcDaoImpl;
import org.datasupport.test.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcDaoImpl jdbcDaoImpl = applicationContext.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

//        Circle circle = jdbcDaoImpl.getCircle(1);
//        System.out.println(circle.getName());
        System.out.println(jdbcDaoImpl.getCircleCount());
        System.out.println(jdbcDaoImpl.getCircleNameById(1));
        System.out.println(jdbcDaoImpl.getCircleById(1));
        Circle.toString(jdbcDaoImpl.getAllCircles());

        HibernateDaoImpl hIbernateDao = applicationContext.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
        System.out.println(hIbernateDao.getCircleCount());


    }

}
