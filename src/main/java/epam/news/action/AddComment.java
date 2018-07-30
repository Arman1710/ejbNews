package epam.news.action;

import epam.news.model.dto.CommentDTO;
import epam.news.services.impl.NewsServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addComment")
public class AddComment extends HttpServlet {

    @Inject
    private NewsServiceImpl newsServiceImpl;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.valueOf(request.getParameter("newsId"));

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(request.getParameter("author"));
        commentDTO.setDescription(request.getParameter("description"));

        newsServiceImpl.addComment(newsId, commentDTO);
        request.getRequestDispatcher("/selectedNews").forward(request, response);
    }
}
