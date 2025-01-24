<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Todo List</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%--        <h1>Header</h1>--%>
        <%@ include file="/WEB-INF/views/layout/header.jsp" %>
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">검색</h5>
                        <form action="/todo/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished" ${pageRequestDTO.finished ? "checked": ""}>완료여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types"
                                       value="t" ${pageRequestDTO.checkType("t") ? "checked":""}>제목
                                <input type="checkbox" name="types"
                                       value="w" ${pageRequestDTO.checkType("w") ? "checked":""}>작성자
                                <input type="text" name="keyword" class="form-control"
                                       value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">검색</button>
                                    <button class="btn btn-info clearBtn" type="reset">초기화</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row content">

            <%--        <h1>Content</h1>--%>
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">할 일 목록</h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">기한</th>
                                <th scope="col">완료여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.tno}"/></th>
                                    <td>
                                        <a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}"
                                           class="text-decoration-none" data-tno="${dto.tno}">
                                            <c:out value="${dto.title}"/></a>
                                    </td>
                                    <td><c:out value="${dto.writer}"/></td>
                                    <td><c:out value="${dto.dueDate}"/></td>
                                    <td><c:out value="${dto.finished?'O':'X'}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="float-end">
                            <ul class="pagination flax-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num ? "active": ""}">
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <script>
                            document.querySelector(".pagination").addEventListener("click", function (event) {
                                event.preventDefault();
                                event.stopPropagation();
                                // 클릭한 태크를 취득
                                const target = event.target;
                                if (target.tagName !== 'A') {
                                    return;
                                }
                                // target의 a태그에 저장되어 있는 data-num인 page정보를 취득
                                const num = target.getAttribute("data-num");
                                //검색 조건들은 모두 form태그안에 존재하기 때문에 form태그를 실행하면 데이터를 전달 가능
                                const formObj = document.querySelector("form");
                                //페이지 데이터를 함께 보내어 선택한 페이지의 데이터를 취득하도록 페이지 데이터 설정
                                formObj.innerHTML += `<input type="hidden" name="page" value="\${num}">`
                                // form 태그 실행
                                formObj.submit();
                            }, false)
                            // 클리어 버튼을 누르면 제일 첫번째 페이지로 이동하는 이벤트
                            document.querySelector(".clearBtn").addEventListener("click", function (e) {
                                e.preventDefault();
                                e.stopPropagation();
                                self.location = `/todo/list`;
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <%@ include file="/WEB-INF/views/layout/footer.jsp" %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>