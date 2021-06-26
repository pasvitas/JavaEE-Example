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
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.service.CatalogService;

@WebServlet(name = "categoriesServlet", value = "")
public class CategoriesServlet extends HttpServlet {

    private final CatalogService catalogService;

    @Inject
    public CategoriesServlet(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Catalog> catalogList = catalogService.getAllCatalogs();
        req.setAttribute("catalogList", catalogList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
