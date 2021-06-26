<%--
  Created by IntelliJ IDEA.
  User: pasvitas
  Date: 24.06.2021
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${item.getName()}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<a href="items?catalogId=${item.getCatalog().getId()}">Назад</a>
<br/>
<a href="cart">Корзина</a>
<a href="login">Личный кабинет</a>

<%
    if ((boolean) request.getAttribute("inCart")) {
        out.println("<h2> Товар добавлен в <a href=\"cart\">корзину!</a> </h2>");
    }
%>

<h1>${item.getName()}</h1>
<p>Цена: ${item.getPrice()}</p>
<p>${item.getDescription()}</p>

<br/>
<br/>

<form action="item?itemId=${item.getId()}" method="POST">
    <input type="submit" value="Купить" />
</form>
</body>
</html>
