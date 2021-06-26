package ru.pasvitas.lab.servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.service.UserService;

public abstract class ExtendedServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public ExtendedServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp, getUserSession(req));
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp, getUserSession(req));
    }

    @Override
    protected final void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp, getUserSession(req));
    }

    @Override
    protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp, getUserSession(req));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

    }

    private User getUserSession(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = userService.getTempUser();
        }
        httpSession.setAttribute("user", user);
        return user;
    }

    protected void processDispatcher(String name, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(name);
        requestDispatcher.forward(req, resp);
    }
}
