<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>
		h2{
		font-size: 2em; 
    line-height: 1.8; 
    color: black;}
	</style>
	<script>
	</script>
</head>
<body>
	<!-- Start Main Top -->
	<%@ include file="/include/front/top.jsp" %>
	<!-- End Main Top -->

	<!-- Start Main Header -->
	<%@ include file="/include/front/header.jsp" %>
	<!-- End Main Header -->
	
	<!-- result id start -->
	<section class="login-register bg-gray py-5 pt-lg-80">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-5" style="border: solid 2px black; padding: 20px">
					<div class="login-register-inner bg-white p-3 p-lg-40 overflow-hidden">
						<h2 class="fw-600 mb-4 text-center">아이디 찾기 결과</h2>
						<hr/>
						<div class="login-register-form">
							<form id="form" method="post">
								<c:choose>
									<c:when test="${empty id}">
										<div class="result">
											등록된 회원이 없습니다
										</div>
										<input type="button" value="회원가입" onclick="javascript:location.href='/front/join/tosForm.web'" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2" />
									</c:when>
									<c:otherwise>
										<div class="result" style="margin-top:20px; margin-bottom:20px;">
											<h3>아이디 : 
											<span style="color:#980000;">${id}</span></h3>
										</div>
										<input type="button" value="로그인" onclick="javascript:location.href='/front/login/loginForm.web'" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3" />
										<input type="button" value="비밀번호 찾기" onclick="javascript:location.href='/front/login/searchPwdForm.web'" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3" />
									</c:otherwise>
								</c:choose>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer><%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>