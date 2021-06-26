<%@ page import="ru.pasvitas.lab.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.pasvitas.lab.model.Item" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">

    <a href="${pageContext.request.contextPath}/">Главная</a>
    <a href="cart">Корзина</a>

    <h2>Пользователь ${user.getUsername()}}</h2>

    <h3>Заказы:</h3>

        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            for(Order order: orders){
                out.println("<ul>");
                for(Item item: order.getOrderItems()){
                    out.println("<li><a href=\"item?itemId=" + item.getId() + "\">"+ item.getName() + " - " + item.getPrice() + " д.е.</a></li>");
                }
                out.println("</ul>");
            }
        %>

</div>
</body>
</html>