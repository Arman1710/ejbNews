package epam.news.action;

import epam.news.services.impl.NewsServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteComment")
public class DeleteComment extends HttpServlet {

    @Inject
    private NewsServiceImpl newsServiceImpl;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.valueOf(request.getParameter("newsId"));
        String[] checkedComments = request.getParameterValues("checkedComments");
        if (checkedComments != null) {
            for (String checkboxValue : checkedComments) {
                newsServiceImpl.deleteComment(newsId, Long.valueOf(checkboxValue));
            }
        }
        request.getRequestDispatcher("/selectedNews").forward(request, response);
    }
}
