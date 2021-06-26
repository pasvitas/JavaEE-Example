package ru.pasvitas.lab.servlet;

import java.io.IOException;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.service.CartService;
import ru.pasvitas.lab.service.ItemService;
import ru.pasvitas.lab.service.UserService;

@WebServlet(name = "itemServlet", value = "/item")
public class ItemServlet extends ExtendedServlet {

    private final CartService cartService;

    private final ItemService itemService;

    @Inject
    public ItemServlet(UserService userService, CartService cartService, ItemService itemService) {
        super(userService);
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Optional<Item> item = itemService.getItemById(Long.parseLong(req.getParameter("itemId")));
        req.setAttribute("inCart", false);
        if (item.isPresent()) {
            req.setAttribute("item", item.get());
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/item.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Optional<Item> item = itemService.getItemById(Long.parseLong(req.getParameter("itemId")));
        req.setAttribute("inCart", false);
        if (item.isPresent()) {
            cartService.addItemToCart(user, item.get());
            req.setAttribute("item", item.get());
            req.setAttribute("inCart", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/item.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
