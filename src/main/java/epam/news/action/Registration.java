package epam.news.action;

import epam.news.model.dto.UserDTO;
import epam.news.services.UserService;
import epam.news.util.UserValidator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private UserValidator userValidator;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setConfirmPassword(confirmPassword);
        Set<String> errors = userValidator.validateUser(userDTO);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
        } else {
            //        securityService.autoLogin(userDTO.getUsername(), userDTO.getConfirmPassword());
            userService.createUser(userDTO);
            request.removeAttribute("errors");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}
