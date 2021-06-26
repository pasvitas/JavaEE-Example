package ru.pasvitas.lab.servlet;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.service.ItemService;

@WebServlet(name = "itemsServlet", value = "/items")
public class ItemsServlet extends HttpServlet {

    private final ItemService itemService;

    @Inject
    public ItemsServlet(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = itemService.getItemsByCatalog(Long.parseLong(req.getParameter("catalogId")));
        req.setAttribute("itemList", items);
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/items.jsp");
        dispatcher.forward(req, resp);
    }
}
