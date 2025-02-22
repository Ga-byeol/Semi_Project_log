<%@page import="com.notice.db.NoticeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main/mainHome</title>
<link href="./css/mainHome.css" rel="stylesheet">
<script type="text/javascript">
window.addEventListener('scroll', function() {
	   
	   var navElem = document.getElementsByClassName('nav-menu');
	   if(window.scrollY > 100) {
	      navElem[0].classList.add('nav-action');
	   } else {
	      navElem[0].classList.remove('nav-action');
	   }
	});
	
window.addEventListener('scroll', function() {
	   
	   var navElem2 = document.getElementsByClassName('log-bar');
	   if(window.scrollY > 100) {
	      navElem2[0].classList.add('log-action');
	   } else {
	      navElem2[0].classList.remove('log-action');
	   }
	});
window.addEventListener('scroll', function() {
	   
	   var navElem3 = document.getElementsByClassName('sct02');
	   if(window.scrollY < 300) {
	      navElem3[0].classList.remove('sct02-action');
	   } else {
	      navElem3[0].classList.add('sct02-action');
	   }
	});
window.addEventListener('scroll', function() {
	   
	   var navElem4 = document.getElementsByClassName('scroll_circle');
	   if(window.scrollY > 300) {
	      navElem4[0].classList.add('scroll_circle_action1');
	   } else {
	      navElem4[0].classList.remove('scroll_circle_action1');
	   }
	});
window.addEventListener('scroll', function() {
	   
	   var navElem5 = document.getElementsByClassName('scroll_circle_action1');
	   if( window.scrollY > 500) {
	      navElem5[0].classList.add('scroll_circle_action2');
	   } else {
	      navElem5[0].classList.remove('scroll_circle_action2');
	   }
	});
	
window.addEventListener('scroll', function() {
	   
	   var navElem6 = document.getElementsByClassName('main_scrl1');
	   if( window.scrollY > 350) {
	      navElem6[0].classList.add('main_scrl1_after');
	   } else {
	      navElem6[0].classList.remove('main_scrl1_after');
	   }
	});
window.addEventListener('scroll', function() {
	   
	   var navElem7 = document.getElementsByClassName('main_scrl2');
	   if( window.scrollY > 350) {
	      navElem7[0].classList.add('main_scrl2_after');
	   } else {
	      navElem7[0].classList.remove('main_scrl2_after');
	   }
	});
window.addEventListener('scroll', function() {
	   
	   var navElem8 = document.getElementsByClassName('main_scrl3');
	   if( window.scrollY > 350) {
	      navElem8[0].classList.add('main_scrl3_after');
	   } else {
	      navElem8[0].classList.remove('main_scrl3_after');
	   }
	});
</script>
</head>
<body>

<%
	List<NoticeDTO> noticeList = (List<NoticeDTO>) request.getAttribute("noticeList");
	//startPage pageBlock currentPage endPage pageCount
	int currentPage = (Integer) request.getAttribute("currentPage");
	int count = (Integer) request.getAttribute("count");
	int pageSize = (Integer) request.getAttribute("pageSize");
	%>


	 <div id="wrapper">
		<!-- 대가리 -->
		<header class="nav-menu">
			<div>
				<a href="./UserHome.us"><img class="logo" src="./car_images/카차차.png"></a>
				<nav>
					<div>
						<p>
							<a href="./CarListPro.ci">차량예약</a>
						</p>
					</div>
					<div>
						<p>
							<a href="./information.ma">이용안내</a>
						</p>
					</div>
					<div>
						<p>회사소개</p>
						<p>
							<a href="./guide.ma">회사소개</a>
							<a href="./map.ma">찾아오시는 길</a>
						</p>
					</div>
					<div>
						<p>고객지원</p>
						<p>
							<a href="./NoticeList.no">공지사항</a>
							<a href="./QnaList.qn">문의사항</a>
						</p>
					</div>
				</nav>
			</div>
			<div class="log-bar"></div>
			<!-- 로그인 -->
			<%
			String sessionId = (String)session.getAttribute("sessionId");
			System.out.println(sessionId);
			if(sessionId == null){
				%>
				<div class="log_btn">
				<button Onclick="location.href='./UserLoginForm.us'">Login</button>
				<button Onclick="location.href='./UserInsertForm.us'">Sign up</button>
				</div>
				<%
			     
			} %>
			
			<%
			if(sessionId != null) {
				
				%> 
				<div class="log_btn">
					<button Onclick="location.href='./UserLogout.us'">Logout</button>
					<button Onclick="location.href='./UserInfoMypage.me'">My Page</button>
				</div>
				<%
			}
			%>
			<!-- 로그인 -->
		</header>
		
		
		<!-- 몸통 -->	
		<main>
			<div class="main_info">
				<section class="sct01">
					<video preload="auto" class="video-bg" loop muted autoplay playsinline>
						<source src="./car_images/car4.mp4" type="video/mp4">
					</video>
					<div class="scroll_msg">Scroll Down</div>
					<div class="scroll_bar"></div>
				</section>
				<section class="sct02">
					<div>
						<div> 
							<div class="popular_list"  style="overflow: hidden;"><!-- 차 리스트 -->
								<div class="main_cont main_scrl1 main_slide">
									<!-- 캐러셀 -->
									<input type="radio" name="slide" id="slide1">
									<input type="radio" name="slide" id="slide2">
									<input type="radio" name="slide" id="slide3">
									<div class="slider">
										<div class="img1">
											<span>인기차량</span>
											<h2>The all-new GRANDEUR</h2>
											<h4>기술과 감성의 경계를 지운 새로운 경험</h4>
											<img src="./car_images/grandeur_slide.png">
										</div>
										<div class="img2">
											<span>인기차량</span>
											<h2>VENUE</h2>
											<h4>오로지 당신을 위한 즐거움</h4>
											<img src="./car_images/venue_slide.png">
										</div>
										<div class="img3">
											<span>인기차량</span>
											<h2>IONIQ 6</h2>
											<h4>내가 만드는 세상</h4>
											<img src="./car_images/IONIQ6_slide.png">
										</div>
										<!-- 3페이지에서 2페이지, 1페이지로 -->
										<label for="slide1" class="p3_middle"></label>
										<label for="slide3" class="p3_first"></label>
										<!-- 2페이지에서 1페이지, 3페이지로 -->
										<label for="slide3" class="p2_first"></label>
										<label for="slide2" class="p2_last"></label>
										<!-- 1페이지에서 3페이지, 2페이지로 -->
										<label for="slide2" class="p1_last"></label>
										<label for="slide1" class="p1_middle"></label>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="booking_check"><!-- 예약 확인 -->
								<div class="main_cont main_scrl2">
									<div class="book_cont">
										<div class="book_input">
											<p>대여일<input type="text" placeholder="예약하고자 하는 날짜를 입력하세요."></p>
											<p>반납일<input type="text" placeholder="반납하고자 하는 날짜를 입력하세요."></p>
										</div>
										<div class="book_button">
											<button type="submit">날짜검색</button>
										</div>
									</div>
								</div>
							</div>
							<div class="notice_box"><!-- 공지 -->
								<div class="main_cont main_scrl3">
									<div>
										<table border="1">
											<tr>
												<td>제목</td>
												<td>등록일</td>
												<td onclick="location.href='./NoticeList.no'">+</td>
											</tr>
											<%
											int index = 0;
											for (int i = count - pageSize * (currentPage - 1); i > count - pageSize * currentPage && i > 0; i--) {
												NoticeDTO dto = noticeList.get(index++);
											%>
											<tr onclick="location.href='./NoticeContent.no?num=<%=dto.getNotice_num()%>'" style="cursor: Pointer">
												<td><%=dto.getNotice_sub()%></td>
												<td colspan="2"><%=dto.getNotice_date()%></td>
											</tr>
											<%
											}
											%>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				
				<div class="scroll_nav_bar">
					<span class="scroll_circle"></span>
				</div>
			</div>
		</main> 	
	</div>
</body>
</html>