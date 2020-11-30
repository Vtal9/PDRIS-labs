<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<c:if test="${requestScope.isErrorAcquired eq true}">
    <div>Error: ${requestScope.errorMessage}</div>
</c:if>

<form action="login" modelAttribute="user" method="post">
    Username: <input type="text" name="name"><br>
    Password: <input type="password" name="password"><br>
    <input type="Submit" value="login">
</form>
<form action="registration" method="get">
    <input type="Submit" value="Sign up">
</form>
</body>
</html>
