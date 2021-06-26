package ru.pasvitas.lab.servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.service.LoginService;
import ru.pasvitas.lab.service.UserService;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends ExtendedServlet {

    private final LoginService loginService;

    @Inject
    public LoginServlet(UserService userService, LoginService loginService) {
        super(userService);
        this.loginService = loginService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (!user.isTemp() && (session.getAttribute("authStatus") != null && (boolean) session.getAttribute("authStatus"))) {
            resp.sendRedirect(req.getContextPath() + "/user");
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User authUser = loginService.authorize(login, password);
        if (authUser != null) {
            session.setAttribute("user", authUser);
            session.setAttribute("authStatus", false);
            resp.sendRedirect(req.getContextPath() + "/user");
        }
        else {
            session.setAttribute("authStatus", true);
        }
    }
}
