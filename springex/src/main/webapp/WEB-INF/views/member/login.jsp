<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Todo List</title>
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
                        <h5 class="card-title">로그인</h5>
                        <form action="/member/login" method="post">
                            <div class="input-group mb-3">
                                <span class="input-group-text">ID</span>
                                <input type="text" name="id" class="form-control" placeholder="id">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">PASSWORD</span>
                                <input type="text" name="pw" class="form-control" placeholder="pw">
                            </div>
                            <div class="my-4">
                                <div class="float-end">
                                    <button type="submit" class="btn btn-primary">로그인</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>