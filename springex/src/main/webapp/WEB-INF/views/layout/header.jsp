<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--        <h1>Header</h1>--%>
<div class="row">
    <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="/todo/list">할 일 목록</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <c:if test="${loginInfo==null}">
                            <a class="nav-link" href="/member/login">로그인</a>
                            <a class="nav-link" href="/member/join">회원가입</a>
                        </c:if>
                        <c:if test="${loginInfo!=null}">
                            <a class="nav-link" href="/todo/register">할일등록</a>
                            <a class="nav-link" href="/member/logout">로그아웃</a>
                            <a class="nav-link removeBtn">회원탈퇴</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
