<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 라이브러 추가 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Page</h1>
    <ul>
        <%-- c:forEach : 반복문, var:list안의 데이터를 한개씩 꺼내때 사용할 변수이름 , items:반복할 리스트 --%>
        <c:forEach var="dto" items="${list}">
            <li>글 번호: ${dto.getTno()}, 제목:${dto.getTitle()}, 날짜:${dto.getDueDate()}, 완료:${dto.isFinished()}</li>
        </c:forEach>
    </ul>
    ${list}
</body>
</html>
