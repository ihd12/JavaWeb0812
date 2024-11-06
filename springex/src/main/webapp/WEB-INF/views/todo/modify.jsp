<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Todo Modify</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%--        <h1>Header</h1>--%>
        <%@ include file="/WEB-INF/views/layout/header.jsp"%>
        <div class="row content">
            <%--        <h1>Content</h1>--%>
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <form action="/todo/modify" method="post">
                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control" value="${dto.tno}" readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" value="${dto.title}" >
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control" value="${dto.dueDate}" >
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="writer" class="form-control" value="${dto.writer}" readonly>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                Finished &nbsp;
                            </label>
                            <input type="checkbox" name="finished" class="form-check-input" ${dto.finished ? "checked":""} >
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Image</span>
                            <input type="file" name="file" class="form-control" >
                        </div>

                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-danger">Remove</button>
                                <button type="submit" class="btn btn-primary">Modify</button>
                                <button type="button" class="btn btn-secondary">List</button>
                            </div>
                        </div>
                        </form>
                        <script>
                            const formObj = document.querySelector("form");
                            document.querySelector(".btn-danger").addEventListener("click",function(e){
                                //태그의 기본적인 기능을 실행하지 않도록 하는 함수, a태그
                                e.preventDefault();
                                // 자식태그를 클릭하면 부모태그에 존재하는 스크립트나 기능이 실행되는 것을 막아주는 함수
                                e.stopPropagation();
                                // form태그의 action과 method의 설정을 remove에 맞게 변경하는 코드
                                formObj.action = `/todo/remove?${pageRequestDTO.link}`;
                                formObj.method = "post";
                                // form태그를 실행하는 함수
                                formObj.submit();
                            },false)
                            // 자바스크립트를 이용한 Get 메서드 실행 방식
                            document.querySelector(".btn-secondary").addEventListener("click",function(event){
                                self.location = `/todo/list?${pageRequestDTO.link}`;
                            }, false)
                        </script>
                        <script>
                            const serverValidResult = {}
                            <c:forEach items="${errors}" var="error">
                            serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
                            </c:forEach>
                            console.log(serverValidResult.length)
                            // alert('title : '+serverValidResult['title']
                            //     +'\ndueDate : '+serverValidResult['dueDate']
                            //     +'\nwriter : '+serverValidResult['writer'])
                            console.log(serverValidResult)
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>