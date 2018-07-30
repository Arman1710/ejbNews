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

@WebServlet("/selectedNews")
public class SelectedNews extends HttpServlet {

    @Inject
    private NewsServiceImpl newsServiceImpl;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.valueOf(request.getParameter("newsId"));
        NewsDTO newsDTO = newsServiceImpl.selectedNews(newsId);
        request.setAttribute("news", newsDTO);
        request.getRequestDispatcher("/WEB-INF/view/selectedNews.jsp").forward(request, response);
    }
}
