<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--데이터를 post방식으로 전달하기 위한 form태그--%>
<%-- TodoRegisterController(/todo/register)의 doPost메서드 실행--%>
    <form action="/todo/register" method="post">
        <div>
<%--            name의 내용으로 request.getParameter()를 실행해야 input에 있는 데이터를 받을 수 있음--%>
            <input type="text" name="title" placeholder="INSERT TITLE">
        </div>
        <div>
            <input type="date" name="dueDate">
        </div>
        <div>
            <button type="reset">RESET</button>
            <button type="submit">SUBMIT</button>
        </div>
    </form>
</body>
</html>
