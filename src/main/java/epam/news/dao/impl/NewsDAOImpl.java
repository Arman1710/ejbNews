package epam.news.dao.impl;

import epam.news.dao.NewsDAO;
import epam.news.model.entity.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@SuppressWarnings("Duplicates")
public class NewsDAOImpl implements NewsDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<News> read() throws HibernateException {
        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        List newsList = session.createQuery("FROM News order by newsId DESC").list();
//        session.getTransaction().commit();

        session.close();
        return newsList;
    }

    @Override
    public News update(News news) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(news);

        session.getTransaction().commit();
        session.close();
        return news;
    }

    @Override
    public News delete(News news) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(news);

        session.getTransaction().commit();
        session.close();
        return news;
    }

    @Override
    public News create(News news) throws HibernateException {
        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        session.saveOrUpdate(news);

//        session.getTransaction().commit();
        session.close();
        return news;
    }


    @Override
    public News findById(Long newsId) throws HibernateException {
        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        News news = session.get(News.class, newsId);

//        session.getTransaction().commit();
        session.close();

        return news;
    }

}
