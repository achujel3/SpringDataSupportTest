package org.datasupport.test.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;
    private BasicDataSource dataSource;

    public int getCircleCount() {
        String hql = "select count(*) from Circle";
        Query query = getSessionFactory().openSession().createQuery(hql);
        return ((Long) query.uniqueResult()).intValue();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
