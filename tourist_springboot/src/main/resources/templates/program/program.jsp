<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> 프로그램 소개 | 상품투어 | 투어리스트인투어 </title>
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
		<div class="location_area package">
			<div class="box_inner">
				<h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
				<p class="location">상품투어 <span class="path">/</span> 프로그램 소개</p>
				<ul class="page_menu clear">
					<li><a href="javascript:;" class="on">프로그램 소개</a></li>
					<li><a href="javascript:;">여행 자료</a></li>
				</ul>
			</div>
		</div>	
		<!-- //location_area -->

		<!-- bodytext_area -->
		<div class="bodytext_area place_area box_inner">
			<ul class="program_list clear">
				<c:forEach items="${dtoList}" var="dto">
					<li>
						<img class="img_place" src="/resources/img/${dto.img}" alt="${dto.title}" />
						<h3>${dto.title}</h3>
						<p class="subttl">${dto.schedule}</p>
						<div class="program_content">
							<p>${dto.text} <span class="subtxt">${dto.subtext}</span></p>
						</div>
						<p class="btn_more"><a href="javascript:;">더보기</a><a href="/cookie?title=${dto.title}">저장</a></p>
					</li>
				</c:forEach>

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
