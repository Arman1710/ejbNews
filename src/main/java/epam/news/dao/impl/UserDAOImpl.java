package epam.news.dao.impl;

import epam.news.dao.UserDAO;
import epam.news.model.entity.News;
import epam.news.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;


import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<User> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List userList = session.createCriteria(User.class).list();

        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public boolean update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public User delete(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(user);

        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean create(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public User getByUserName(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));

        session.getTransaction().commit();

        return (User) criteria.uniqueResult();
    }

}
