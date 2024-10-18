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
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="#">Link</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link" disabled>Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>

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
    <div class="row footer">
        <%--        <h1>Footer</h1>--%>
        <div class="row fixed-bottom" style="z-index:-100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>