<%@ page import="ru.pasvitas.lab.model.Catalog" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Каталог</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

    <a href="cart">Корзина</a>
    <a href="login">Личный кабинет</a>

    <h1><%= "Интернет-магазин" %></h1>
    <br/>
    <ul>
        <%
            List<Catalog> catalogs = (List<Catalog>) request.getAttribute("catalogList");
            for(Catalog catalog: catalogs){
                out.println("<li><a href=\"items?catalogId=" + catalog.getId() + "\">"+ catalog.getName() + "</a></li>");
            }
        %>
    </ul>
</body>
</html>
