<%--
  Created by IntelliJ IDEA.
  User: pasvitas
  Date: 24.06.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/">Главная</a>

<form action="login" method="POST">
    <h3>Логин:</h3>
    <label>
        <input type="text" name="login" />
    </label>
    <h3>Пароль:</h3>
    <label>
        <input type="password" name="password" />
    </label>
    <input type="submit" value="Вход" />
</form>

</body>
</html>
