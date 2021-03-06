package epam.news.services;

import epam.news.model.dto.CommentDTO;
import epam.news.model.dto.NewsDTO;
import epam.news.model.entity.News;

import java.util.List;

public interface NewsService {
    List<News> showAllNews();

    NewsDTO selectedNews(Long newsId);

    News editNews(NewsDTO newsDTO, Long newsId);

    News addNews(NewsDTO newsDTO);

    News addComment(Long newsId, CommentDTO commentDTO);

    News deleteNews(final Long newsId);

    News deleteComment(final Long newsId, final Long commentId);
}
