<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 공지사항 | 고객센터 | 투어리스트인투어 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/resources/css/common.css">
    <script src="/resources/js/jquery-1.11.3.min.js"></script>
    <script src="/resources/js/common.js"></script>
    <script src="/resources/js/jquery.smooth-scroll.min.js"></script>
    <!--[if lte IE 9]>
    <script src="/resources/js/html5shiv.js"></script>
    <script src="/resources/js/placeholders.min.js"></script>
    <![endif]-->
</head>

<body>
<ul class="skipnavi">
    <li><a href="#container">본문내용</a></li>
</ul>
<!-- wrap -->
<div id="wrap">

    <%@ include file="/WEB-INF/views/layout/menu.jsp" %>

    <div id="container">
        <!-- location_area -->
        <div class="location_area customer">
            <div class="box_inner">
                <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
                <p class="location">고객센터 <span class="path">/</span> 공지사항</p>
                <ul class="page_menu clear">
                    <li><a href="#" class="on">공지사항</a></li>
                    <li><a href="#">문의하기</a></li>
                </ul>
            </div>
        </div>
        <!-- //location_area -->

        <!-- bodytext_area -->
        <div class="bodytext_area box_inner">
            <form action="/notice/list" class="minisrch_form">
                <fieldset>
                    <legend>검색</legend>
                    <input type="text" class="tbox" name="keyword" title="검색어를 입력해주세요" placeholder="검색어를 입력해주세요">
                    <button class="btn_srch">검색</button>
                    <a href="/notice/add" class="btn_srch">글작성</a>
                </fieldset>
            </form>
            <table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
                <caption class="hdd">공지사항  목록</caption>

                <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">조회수</th>
                    <th scope="col">작성일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageResponseDTO.dtoList}" var="notice">
                <tr>
                    <td>${notice.tno}</td>
                    <td class="tit_notice"><a href="/notice/read?tno=${notice.tno}">${notice.title}</a> </td>
                    <td>${notice.view}</td>
                    <td>${notice.createDate}</td>
                </tr>
                </c:forEach>


                </tbody>
            </table>
            <!-- pagination -->
            <div class="pagination">
                <a href="javascript:;" class="firstpage  pbtn" data-num="1"><img data-num="1" src="/resources/img/btn_firstpage.png" alt="첫 페이지로 이동"></a>
                <c:if test="${pageResponseDTO.prev}">
                    <a href="javascript:;" class="prevpage pbtn" data-num="${pageResponseDTO.start-1}"><img data-num="${pageResponseDTO.start-1}" src="/resources/img/btn_prevpage.png" alt="이전 페이지로 이동"></a>
                </c:if>
                <c:forEach begin="${pageResponseDTO.start}" end="${pageResponseDTO.end}" var="num">
                    <a href="javascript:;"><span class="pagenum ${pageResponseDTO.page==num ?"currentpage":""}" data-num="${num}">${num}</span></a>
                </c:forEach>
                <c:if test="${pageResponseDTO.next}">
                    <a href="javascript:;" class="nextpage pbtn" data-num="${pageResponseDTO.end+1}"><img data-num="${pageResponseDTO.end+1}" src="/resources/img/btn_nextpage.png" alt="다음 페이지로 이동"></a>
                </c:if>
                <a href="javascript:;" class="lastpage  pbtn" data-num="${pageResponseDTO.last}"><img data-num="${pageResponseDTO.last}" src="/resources/img/btn_lastpage.png" alt="마지막 페이지로 이동"></a>
            </div>
            <!-- //pagination -->

        </div>
        <!-- //bodytext_area -->

    </div>
    <!-- //container -->

    <%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</div>
<!-- //wrap -->

<h2 class="hdd">빠른 링크 : 전화 문의,카카오톡,오시는 길,꼭대기로</h2>
<div class="quick_area">
    <ul class="quick_list">
        <li><a href="tel:010-7184-4403"><h3>전화 문의</h3><p>010-1234-5678</p></a></li>
        <li><a href="javascript:;"><h3>카카오톡 <em>상담</em></h3><p>1:1상담</p></a></li>
        <li><a href="javascript:;"><h3 class="to_contact">오시는 길</h3></a></li>
    </ul>
    <p class="to_top"><a href="#layout0" class="s_point">TOP</a></p>
</div>
<script>
    document.querySelector(".pagination").addEventListener("click", (e)=>{
        e.preventDefault();
        e.stopPropagation();
        const target = e.target;
        if(target.tagName !== 'IMG' && target.tagName !== 'A' && target.tagName !== 'SPAN'){
            return;
        }
        const num = target.getAttribute("data-num");
        const formObj = document.querySelector("form");
        formObj.innerHTML += `<input type="hidden" name="page" value="\${num}">`
        formObj.submit();
    },false)
</script>
</body>
</html>
