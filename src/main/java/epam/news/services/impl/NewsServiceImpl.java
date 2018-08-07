package epam.news.services.impl;

import epam.news.converter.CommentConverter;
import epam.news.converter.NewsConverter;
import epam.news.dao.NewsDAO;

import epam.news.model.dto.CommentDTO;
import epam.news.model.dto.NewsDTO;
import epam.news.model.entity.Comment;
import epam.news.model.entity.News;
import epam.news.services.NewsService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Stateless
@Local
public class NewsServiceImpl implements NewsService {
    private final static Logger LOGGER = Logger.getLogger(NewsServiceImpl.class);

    @Inject
    private NewsDAO newsDAO;

    @Inject
    private NewsConverter newsConverter;

    @Inject
    private CommentConverter commentConverter;

    @Override
    public List<News> showAllNews() {
        List<News> newsList = new ArrayList<>();
        try {
            newsList = newsDAO.read();
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't show all news : " + hibernateEx);
        }
        return newsList;
    }

    @Override
    public NewsDTO selectedNews(Long newsId) {
        LOGGER.info("selected news :" + newsId);
        NewsDTO newsDTO = new NewsDTO();
        try {
            newsDTO = newsConverter.entityToDTO(newsDAO.findById(newsId));
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't read selected news with ID= " + newsId + " : " + hibernateEx);
        }
        LOGGER.info("News :" + newsId + " is selected");
        return newsDTO;
    }


    @Override
    public News editNews(NewsDTO newsDTO, Long newsId) {
        LOGGER.info("Updating news :" + newsId);
        News editedNews = newsDAO.findById(newsId);
        editedNews.setContent(newsDTO.getContent());
        editedNews.setBrief(newsDTO.getBrief());
        editedNews.setTitle(newsDTO.getTitle());
        try {
            newsDAO.update(editedNews);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't edit selected news with ID= " + newsId + " : " + hibernateEx);
        }
        LOGGER.info("News :" + newsId + " is updated");
        return editedNews;
    }

    @Override
    public News addNews(NewsDTO newsDTO) {
        LOGGER.info("Creating news:" + newsDTO);
        News news = newsConverter.DTOToEntity(newsDTO);
        News createdNews = new News();
        try {
            createdNews = newsDAO.create(news);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't create news : " + hibernateEx);
        }
        LOGGER.info("News :" + news.getTitle() + "is created");
        return createdNews;
    }

    @Override
    public News addComment(Long newsId, CommentDTO commentDTO) {
        LOGGER.info("Creating comment :" + newsId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        commentDTO.setDateCreated(new Date());
        Comment comment = commentConverter.DTOToEntity(commentDTO);
        News news = new News();

        try {
            news = newsDAO.findById(newsId);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't find news with ID=  "+ newsId + " : " + hibernateEx);
        }

        comment.setNewsId(news.getNewsId());
        comment.setAuthor(auth.getName());
        news.getCommentList().add(comment);
        News newWithAddedComment = new News();
        try {
            newWithAddedComment = newsDAO.update(news);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't add comment to news with ID = "+ newsId + " : " + hibernateEx);
        }
        LOGGER.info("Comments :" + news + "is created");
        return newWithAddedComment;
    }

    @Override
    public News deleteNews(Long newsId) {
        LOGGER.info("delete news :" + newsId);
        News deletedObject = new News();
        try {
            deletedObject = newsDAO.delete(newsDAO.findById(newsId));
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't delete news with ID= "+ newsId + " : " + hibernateEx);
        }
        LOGGER.info("News :" + newsId + " is deleted");
        return deletedObject;
    }

    @Override
    public News deleteComment(final Long newsId, final Long commentId) {
        LOGGER.info("deleting comment :" + commentId);
        News news = new News();
        try {
            news = newsDAO.findById(newsId);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't find news with ID=  " + newsId + " : " + hibernateEx);
        }
        Iterator<Comment> itr = news.getCommentList().iterator();

        while (itr.hasNext()) {
            Comment comment = itr.next();
            if (comment.getCommentId().equals(commentId)) {
                itr.remove();
            }
        }
        News newsWithDeletedComment = new News();
        try {
            newsWithDeletedComment = newsDAO.update(news);
        } catch (HibernateException hibernateEx) {
            LOGGER.error("Couldn't update news with ID=  " + newsId + " : " + hibernateEx);
        }
        LOGGER.info("News :" + commentId + " is deleted");
        return newsWithDeletedComment;
    }
}

