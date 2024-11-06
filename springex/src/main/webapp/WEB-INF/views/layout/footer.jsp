<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row footer">
    <%--        <h1>Footer</h1>--%>
    <div class="row fixed-bottom" style="z-index:-100">
        <footer class="py-1 my-1">
            <p class="text-center text-muted">Footer</p>
        </footer>
    </div>
</div>
<script>
    document.querySelector(".removeBtn").addEventListener("click",(e)=>{
        e.preventDefault();
        e.stopPropagation();
        let formObj = document.createElement("form");
        formObj.action="/member/remove"
        formObj.method="post"
        document.body.append(formObj);
        formObj.submit();
    })
</script>
