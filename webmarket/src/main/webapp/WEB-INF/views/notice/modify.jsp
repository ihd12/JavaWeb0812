<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 박물관 미션 투어 당첨자 발표 | 공지사항 | 고객센터 | 투어리스트인투어 </title>
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
            <form action="/notice/modify" method="POST">
                <input type="hidden" name="tno" value="${dto.tno}">
                <ul class="bbsview_list">
                    <li class="bbs_title"><input type="text" name="title" size="110" value="${dto.title}" ></li>
                    <li class="bbs_hit">작성일 : <span>${dto.createDate}</span></li>
                    <li class="bbs_date">조회수 : <span>${dto.view}</span></li>
                    <li class="bbs_content">
                        <div class="editer_content">
                            <textarea name="content" cols="110" rows="20" >${dto.content}</textarea>
                        </div>
                    </li>
                </ul>
                <p class="btn_line txt_right">
                    <input type="submit" value="수정" class="btn_srch">
                    <a class="btn_srch btn-remove">삭제</a>
                    <a href="/notice/list" class="btn_bbs">목록</a>
                </p>

            </form>
            <script>
                const formObj = document.querySelector("form");
                document.querySelector(".btn-remove").addEventListener("click", function (e){
                    e.preventDefault();
                    // 태그의 기본적인 실행하지 않도록 하는 함수, a태그
                    e.stopPropagation();
                    // 자식태그를 클릭하면 부모태그에 존재하는 스크립트나 기능이 실행되는 것을 막아주는 함수
                    formObj.action = `/notice/remove`;
                    // form태그의 action과 method의 설정을 remove에 맞게 변경하는 코드
                    formObj.method = "post";
                    formObj.submit();
                    // form태그를 실행하는 함수
                }, false)
            </script>
            <ul class="near_list mt20">
                <li><h4 class="prev">다음글</h4><a href="javascript:;">추석 연휴 티켓/투어 배송 및 직접 수령 안내</a></li>
                <li><h4 class="next">이전글</h4><a href="javascript:;">이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)</a></li>
            </ul>
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

</body>
</html>
