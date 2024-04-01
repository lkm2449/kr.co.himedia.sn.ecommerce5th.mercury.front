<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>

	</style>
	<script>
	$(document).ready(function(){
		// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
		var key = getCookie("key");
		$("#id").val(key); 
		 
		// 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
		if($("#id").val() != ""){ 
			$("#remember-check").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}
	});
	</script>
	<script>
	function checkLogin() {		
					
		var frmMain = document.getElementById("form");
		frmMain.action = "/front/login/loginProc.web";
		frmMain.submit();
		
	}
	</script>
</head>
<body>
	<!-- Start Main Top -->
	<%@ include file="/include/front/top.jsp" %>
	<!-- End Main Top -->

	<!-- Start Main Header -->
	<%@ include file="/include/front/header.jsp" %>
	<!-- End Main Header -->
	
	
	
	<!-- login register start -->
	<section class="login-register bg-gray py-5 pt-lg-80">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-5" style="border: solid 2px black; padding: 20px">
					<div class="login-register-inner bg-white p-3 p-lg-40 overflow-hidden">
						<h2 class="fw-600 mb-4 text-center">로그인</h2>
						<hr />
						<div class="login-register-form">
							<form id="form" method="POST" encType="UTF-8">
								<div class="mb-3">
									<label class="mb-2" for="id"><strong>아이디</strong></label>
									<input type="text" id="id" name="id" class="form-control rounded-0 border-whisper border-2 py-10" autocomplete="off">
								</div>
								<div class="mb-3">
									<label class="mb-2" for="passwd"><strong>비밀번호</strong></label>
									<input type="password" id="passwd" name="passwd" class="form-control rounded-0 border-whisper border-2 py-10" autocomplete="off">
								</div>
								<div class="mb-4 mb-lg-40 d-flex justify-content-between align-items-center">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="remember-check" name="remember-check">
										<label class="form-check-label" for="remember-check">
											아이디 저장
										</label>
									</div>
									<a href="/front/login/searchIdForm.web" class="text-dark">아이디 찾기</a>
									<a href="/front/login/searchPwdForm.web" class="text-dark">비밀번호 찾기</a>
								</div>
								<input type="button" value="로그인" onclick="checkLogin()" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3">
								<div class="d-flex justify-content-center">
									<span class="text-dove me-1">
										<a href="/front/join/tosForm.web" class="text-dark">회원 가입</a>
									</span> 
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
				
	
<!-- Start Footer  -->
<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<!-- End Footer  -->
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>