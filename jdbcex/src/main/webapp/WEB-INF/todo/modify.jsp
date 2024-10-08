<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Modify/Remove</title>
</head>
<body>
    <form action="/todo/modify" method="post">
        <div>
            <input type="text" name="tno" value="${dto.tno}" readonly>
        </div>
        <div>
            <input type="text" name="title" value="${dto.title}">
        </div>
        <div>
            <input type="date" name="dueDate" value="${dto.dueDate}">
        </div>
        <div>
            <input type="checkbox" name="finished" ${dto.finished ? "checked":""}>
        </div>
        <div>
            <button type="submit">Modify</button>
        </div>
    </form>
    <form action="/todo/remove" method="post">
<%--    hidden의 경우 화면에 나오지 않는 저장공간을 만드는 기능, form태그가 실행되면 hidden의 데이터도 함께 전송 --%>
<%--    a태그 처럼 파라미터를 사용할 수 없기 때문에 hidden을 사용    --%>
        <input type="hidden" name="tno" value="${dto.tno} readonly">
        <button type="submit">Remove</button>
    </form>
    <div>
        <a href="/todo/list">List</a>
    </div>
</body>
</html>










