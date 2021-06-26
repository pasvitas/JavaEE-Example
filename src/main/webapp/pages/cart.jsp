<%@ page import="ru.pasvitas.lab.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.pasvitas.lab.model.Cart" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/">Главная</a>

<ul>
    <%
        Optional<Cart> cart = (Optional<Cart>) request.getAttribute("cart");
        if (cart.isPresent()) {
            out.println("<h2> Корзина: </h2>");
            List<Item> items = cart.get().getCartItemList();
            for (Item item : items) {
                out.println("<li><a href=\"item?itemId=" + item.getId() + "\">" + item.getName() + " - " + item.getPrice() + " д.е.</a></li>" +
                        "    <form action=\"cart?itemId=" + item.getId() + "\" method=\"DELETE\">\n" +
                        "        <input type=\"submit\" value=\"Удалить\" />\n" +
                        "    </form>");
            }
        }
        else {
            out.println("<h2> Корзина пуста! </h2>");
        }
    %>
</ul>


    <form action="cart" method="POST">
        <h3>Адрес доставки:</h3>
        <label>
            <input type="text" name="detals" />
        </label>
        <input type="submit" value="Заказать" />
    </form>

</body>
</html>
