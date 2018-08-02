package epam.news.services;

import epam.news.model.dto.CommentDTO;
import epam.news.model.dto.NewsDTO;
import epam.news.model.entity.News;

import java.util.List;

public interface NewsService {
    public List<News> showAllNews();

    public NewsDTO selectedNews(Long newsId);

    public News editNews(NewsDTO newsDTO, Long newsId);

    public News addNews(NewsDTO newsDTO);

    public void addComment(Long newsId, CommentDTO commentDTO);

    public void deleteNews(final Long newsId);

    public void deleteComment(final Long newsId, final Long commentId);
}
