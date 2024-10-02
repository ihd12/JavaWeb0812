<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%-- ctrl + alt + l : 인텔리제이 코드 자동 정렬--%>
<head>
    <title>Title</title>
</head>
<body>
    <p>num1 : <%= request.getParameter("num1") %></p>
    <p>num2 : <%= request.getParameter("num2") %></p>
    <form action="/calc/input" method="post">
        <input type="text" name="num1">
        <input type="text" name="num2">
        <button type="submit">SEND</button>
    </form>
</body>
</html>
