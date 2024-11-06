<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Todo Register</title>
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
                        <form action="/todo/register" method="post">
                            <div class="input-group mb-3">
                                <span class="input-group-text">Title</span>
                                <input type="text" name="title" class="form-control" placeholder="Title">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">DueDate</span>
                                <input type="date" name="dueDate" class="form-control">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Writer</span>
                                <input type="text" name="writer" value="${loginInfo.id}" class="form-control" readonly>
                            </div>
<%--                            <div class="input-group mb-3">--%>
<%--                                <span class="input-group-text">Image</span>--%>
<%--                                <input type="file" name="file" class="form-control" placeholder="Writer">--%>
<%--                            </div>--%>

                            <div class="my-4">
                                <div class="float-end">
                                    <button type="submit" class="btn btn-primary">등록</button>
                                    <button type="reset" class="btn btn-secondary">초기화</button>
                                </div>
                            </div>
                        </form>
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