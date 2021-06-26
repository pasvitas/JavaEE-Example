package ru.pasvitas.lab.servlet;

import java.io.IOException;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.service.CartService;
import ru.pasvitas.lab.service.ItemService;
import ru.pasvitas.lab.service.OrderService;
import ru.pasvitas.lab.service.UserService;

@WebServlet(name = "cartServlet", value = "/cart")
public class CartServlet extends ExtendedServlet {

    private final CartService cartService;

    private final ItemService itemService;

    private final OrderService orderService;

    @Inject
    public CartServlet(UserService userService, CartService cartService, ItemService itemService, OrderService orderService) {
        super(userService);
        this.cartService = cartService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        req.setAttribute("cart", cartService.getCart(user));
        processDispatcher("pages/cart.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if (user.isTemp()) {
            resp.sendRedirect("/login");
            return;
        }
        Optional<Cart> cartOptional = cartService.getCart(user);
        if (cartOptional.isPresent() && !cartOptional.get().getCartItemList().isEmpty()) {
            orderService.createOrder(user, req.getParameter("details"), cartOptional.get());
            resp.sendRedirect("/user");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        super.doDelete(req, resp, user);
        Optional<Cart> cartOptional = cartService.getCart(user);
        if (cartOptional.isPresent() && !cartOptional.get().getCartItemList().isEmpty()) {
            Optional<Item> item = itemService.getItemById(Long.parseLong(req.getParameter("itemId")));
            item.ifPresent(value -> cartService.removeItemFromCart(user, value));
            req.setAttribute("cart", cartService.getCart(user));
            processDispatcher("pages/cart.jsp", req, resp);
        }
    }
}
