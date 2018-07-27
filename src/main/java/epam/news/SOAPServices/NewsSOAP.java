package epam.news.SOAPServices;

import epam.news.model.dto.CommentDTO;
import epam.news.model.dto.NewsDTO;
import epam.news.model.entity.News;
import epam.news.services.NewsService;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;


@WebService
public class NewsSOAP {

    @Inject
    private NewsService newsService;

    public List<News> showAllNews () {
        return newsService.showAllNews();
    }

    public void addNews (NewsDTO newsDTO) {
        newsService.addNews(newsDTO);
    }

}
