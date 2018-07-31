package epam.news.restServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.news.model.dto.CommentDTO;
import epam.news.model.dto.NewsDTO;
import epam.news.model.entity.Comment;
import epam.news.model.entity.News;
import epam.news.services.NewsService;
import oracle.jdbc.proxy.annotation.Post;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;


@Path("/news")
public class NewsRest {

    @Inject
    private NewsService newsService;

    @Path("/main")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showAllNews () {
        List<News> newsList = newsService.showAllNews();
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(newsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Path("/selectedNews/{newsId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String selectedNews (@PathParam("newsId") Long newsId) {
        NewsDTO newsDTO = newsService.selectedNews(newsId);
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(newsDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Path("/createNews")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNews (NewsDTO newsDTO) {
        newsService.addNews(newsDTO);
    }

    @Path("/createComment/{newsId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNews (@PathParam("newsId") Long newsId, CommentDTO commentDTO) {
        newsService.addComment(newsId, commentDTO);
    }

    @Path("/deleteComment/{newsId}/{commentId}")
    @POST
    public void deleteComment (@PathParam("newsId") Long newsId,
                               @PathParam("commentId") Long commentId) {
        newsService.deleteComment(newsId, commentId);
    }

    @Path("/editNews/{newsId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNews (@PathParam("newsId") Long newsId, NewsDTO newsDTO) {
        newsService.editNews(newsDTO, newsId);
    }

    @Path("/deleteNews/{newsId}")
    @POST
    public void deleteNews (@PathParam("newsId") Long newsId) {
        newsService.deleteNews(newsId);
    }



//    @Path("/showDTO")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String showDTO (){
//        NewsDTO newsDTO = new NewsDTO();
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setAuthor("22");
//        commentDTO.setDescription("22");
//        commentDTO.setNewsId(2L);
//        commentDTO.setDateCreated(new Date());
//
//        newsDTO.setContent("22");
//        newsDTO.setBrief("22");
//        newsDTO.setTitle("22");
//        newsDTO.getCommentList().add(commentDTO);
//        String json = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            json = objectMapper.writeValueAsString(newsDTO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }
}
