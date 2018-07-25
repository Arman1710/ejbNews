package epam.news.restServices;

import epam.news.model.entity.News;
import epam.news.services.NewsService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/news")
public class NewsRest {

    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObjectBuilder showAllNews () {
        List<News> newsList = newsService.showAllNews();

        JsonObjectBuilder newsBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (News news : newsList){
            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonObject newsJson = builder.add("newsId", news.getNewsId())
                    .add("title", news.getTitle())
                    .add("brief", news.getBrief())
                    .add("content", news.getContent())
                    .build();
            arrayBuilder.add(newsJson);
        }

        newsBuilder.add("news", arrayBuilder);
        return newsBuilder;
    }
}
