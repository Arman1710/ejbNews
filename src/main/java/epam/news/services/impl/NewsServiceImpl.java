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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Stateless
@Local
public class NewsServiceImpl implements NewsService {

    @Inject
    private NewsDAO newsDAO;

    @Inject
    private NewsConverter newsConverter;

    @Inject
    private CommentConverter commentConverter;

    private final static Logger LOGGER = Logger.getLogger(NewsServiceImpl.class);

    @Override
    public List<News> showAllNews() {
        LOGGER.info("show all news");
        return newsDAO.read();

    }

    @Override
    public NewsDTO selectedNews(Long newsId) {
        LOGGER.info("selected news :" + newsId);
        NewsDTO newsDTO = newsConverter.entityToDTO(newsDAO.findById(newsId));
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
        newsDAO.update(editedNews);
        LOGGER.info("News :" + newsId + " is updated");
        return editedNews;
    }

    @Override
    public News addNews(NewsDTO newsDTO) {
        News news = newsConverter.DTOToEntity(newsDTO);
        News createdNews = newsDAO.create(news);
        LOGGER.info("News :" + news.getTitle() + "is created");
        return createdNews;
    }

    @Override
    public News addComment(Long newsId, CommentDTO commentDTO) {
        LOGGER.info("Creating comment :" + newsId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        commentDTO.setDateCreated(new Date());
        Comment comment = commentConverter.DTOToEntity(commentDTO);

        News news = newsDAO.findById(newsId);
        comment.setNewsId(news.getNewsId());
        comment.setAuthor(auth.getName());

        news.getCommentList().add(comment);
        News newWithAddedComment = newsDAO.update(news);

        LOGGER.info("Comments :" + news + "is created");
        return newWithAddedComment;
    }

    @Override
    public News deleteNews(Long newsId) {
        LOGGER.info("delete news :" + newsId);

        News deletedObject = newsDAO.delete(newsDAO.findById(newsId));

        LOGGER.info("News :" + newsId + " is deleted");
        return deletedObject;
    }

    @Override
    public News deleteComment(final Long newsId, final Long commentId) {
        LOGGER.info("delete comment :" + commentId);
        final News news = newsDAO.findById(newsId);
        final Iterator<Comment> itr = news.getCommentList().iterator();

        while (itr.hasNext()) {
            Comment comment = itr.next();
            if (comment.getCommentId().equals(commentId)) {
                itr.remove();
            }
        }
        News newsWithDeletedComment = newsDAO.update(news);
        LOGGER.info("News :" + commentId + " is deleted");
        return newsWithDeletedComment;
    }

}
