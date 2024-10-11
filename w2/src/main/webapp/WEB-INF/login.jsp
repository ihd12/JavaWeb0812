<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- c:if : JSTL의 IF문으로 test안에 조건식을 넣어서 사용한다. 조건식이 true라면 안의 내용을 출력, false라면 출력하지 않음    --%>
    <c:if test="${param.result == 'error'}">
        <h1>로그인 에러 : 아이디나 비밀번호가 틀립니다.</h1>
    </c:if>
    <form action="/login" method="post">
        <input type="text" name="mid">
        <input type="password" name="mpw">
        <input type="checkbox" name="auto">
        <button type="submit">LOGIN</button>
    </form>
</body>
</html>
