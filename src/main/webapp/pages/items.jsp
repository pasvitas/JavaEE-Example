<%@ page import="ru.pasvitas.lab.model.Catalog" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.pasvitas.lab.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Назад</a>
<br/>
<a href="cart">Корзина</a>


<h1><%= "Товары" %></h1>
<br/>
<ul>
    <%
        List<Item> items = (List<Item>) request.getAttribute("itemList");
        for(Item item: items){
            out.println("<li><a href=\"item?itemId=" + item.getId() + "\">"+ item.getName() + " - " + item.getPrice() + " д.е.</a></li>");
        }
    %>
</ul>
</body>
</html>
