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

    <header id="header">
        <div class="header_area box_inner clear">
            <h1><a href="/">Tourist in tour</a></h1>
            <p class="openMOgnb"><a href="#"><b class="hdd">메뉴열기</b> <span></span><span></span><span></span></a></p>
            <!-- header_cont -->
            <div class="header_cont">
                <ul class="util clear">
                    <li><a href="login.html">로그인</a></li>
                    <li><a href="join.html">회원가입</a></li>
                </ul>
                <nav>
                    <ul class="gnb clear">
                        <li><a href="javascript:;" class="openAll1">여행정보</a>

                            <div class="gnb_depth gnb_depth2_1">
                                <ul class="submenu_list">
                                    <li><a href="javascript:;">국내</a></li>
                                    <li><a href="javascript:;">해외</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="javascript:;" class="openAll2">고객센터</a>
                            <div class="gnb_depth gnb_depth2_2">
                                <ul class="submenu_list">
                                    <li><a href="/noticelist">공지사항</a></li>
                                    <li><a href="javascript:;">문의하기</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="javascript:;" class="openAll3">상품투어</a>
                            <div class="gnb_depth gnb_depth2_3">
                                <ul class="submenu_list">
                                    <li><a href="program.html">프로그램 소개</a></li>
                                    <li><a href="javascript:;">여행자료</a></li>
                                </ul>
                            </div>
                        </li>
                        <li><a href="javascript:;" class="openAll4">티켓/가이드</a>
                            <div class="gnb_depth gnb_depth2_4">
                                <ul class="submenu_list">
                                    <li><a href="javascript:;">항공</a></li>
                                    <li><a href="javascript:;">호텔</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </nav>
                <p class="closePop"><a href="javascript:;">닫기</a></p>
            </div>
            <!-- //header_cont -->
        </div>
    </header>

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
    <footer>
        <div class="foot_area box_inner">
            <ul class="foot_list clear">
                <li><a href="javascript:;">이용약관</a></li>
                <li><a href="javascript:;">개인정보취급방침</a></li>
            </ul>
            <h2>투어리스트인투어</h2>
            <p class="addr">서울특별시 종로구 혜화동 9길 청운빌딩 5층 <span class="gubun">/</span>
                <span class="br_line">대표전화 <span class="space0">02-1234-5678</span> <span class="gubun">/</span>
					<span class="br_line">E-mail : <span class="space0"> titour@touristintour.com</span></span>
				</span>
            </p>
            <p class="copy box_inner">Copyright(c) TouristInTour all right reserved</p>
            <ul class="snslink clear">
                <li><a href="javascript:;">blog</a></li>
                <li><a href="javascript:;">facebook</a></li>
                <li><a href="javascript:;">instargram</a></li>
            </ul>
        </div>
    </footer>

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
