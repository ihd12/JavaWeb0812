<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Todo List</title>
</head>
<body>
    <h1>Todo List</h1>
    <h3>${loginInfo.mname}님 반갑습니다.</h3>
    <ul>
        <c:forEach items="${dtoList}" var="dto">
            <li>
<%--               controller의 /todo/read 를 실행하고 tno데이터를 전달하도록 하는 a태크--%>
                <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
                <span>${dto.title}</span>
                <span>${dto.dueDate}</span>
                <span>${dto.finished ? "DONE" : "NOT YET"}</span>
            </li>
        </c:forEach>
    </ul>
    <a href="/todo/register">REGISTER</a>
    <form action="/logout" method="post">
        <button>LOGOUT</button>
    </form>
</body>
</html>














