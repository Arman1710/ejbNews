package epam.news.action;

import epam.news.model.dto.NewsDTO;
import epam.news.services.impl.NewsServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editNews")
public class EditNews extends HttpServlet {

    @Inject
    private NewsServiceImpl newsServiceImpl;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.valueOf(request.getParameter("newsId"));
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");

        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(title);
        newsDTO.setBrief(brief);
        newsDTO.setContent(content);
        newsServiceImpl.editNews(newsDTO, newsId);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
