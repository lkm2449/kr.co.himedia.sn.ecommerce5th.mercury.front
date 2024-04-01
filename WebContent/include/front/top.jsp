<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div class="main-top">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="text-slid-box">
					<div id="offer-box" class="carouselTicker">
						<ul class="offer-box">
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
							<li>
								<img src="/image/top.png" class="logo" alt="" style="width: 25px; height: 25px;">
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					
<!-- 				<div class="custom-select-box"> -->
<!-- 					<select id="basic" class="selectpicker show-tick form-control" data-placeholder=""> -->
<!-- 					</select> -->
<!-- 				</div> -->
				<div class="right-phone-box">
					<p><i class="fa fa-phone fa-rotate-180"></i> : <a href="#"> 031-736-0008</a></p>
				</div>
				<div class="our-link">
					<ul>
						<%
							if(session.getAttribute("ID")==null){
								out.println("<li><a href='/front/login/loginForm.web'>로그인</a></li>");
							}else{
								out.println("<li><a href='/front/login/logout.web'>로그아웃</a></li>");
								out.println("<li><a href='/front/customer/main.web'>마이페이지</a></li>");
							}
						%>
						<li><a href="/front/board/noticeList.web">고객센터</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>