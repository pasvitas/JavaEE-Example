package ru.pasvitas.lab.servlet;


import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.service.OrderService;
import ru.pasvitas.lab.service.UserService;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends ExtendedServlet {

    private final OrderService orderService;

    @Inject
    public UserServlet(UserService userService, OrderService orderService) {
        super(userService);
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.setAttribute("orders", orderService.getUserOrders(user));
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/user.jsp");
        dispatcher.forward(req, resp);
    }
}
