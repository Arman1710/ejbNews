package epam.news.action.movePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginPage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String error = request.getParameter("error");
       String logout = request.getParameter("logout");
        request.setAttribute("error", error !=null);
        request.setAttribute("logout", logout != null);
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request,response);
    }
}
