<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style></style>
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
	
	<!-- search id start -->
	<section class="login-register bg-gray py-5 pt-lg-80">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-5" style="border: solid 2px black; padding: 20px">
					<div class="login-register-inner bg-white p-3 p-lg-40 overflow-hidden">
						<h2 class="fw-600 mb-4 text-center">아이디 찾기</h2>
						<hr />
						<div class="login-register-form">
							<form id="form" method="post" action="/front/login/resultIdForm.web" >
								<div class="mb-3">
									<label class="mb-2" for="cst_nm"><strong>이름</strong></label>
									<input type="text" name="cst_nm" id="cst_nm" class="form-control rounded-0 border-whisper border-2 py-10" autocomplete="off">
								</div>
								<div class="mb-3">
									<label class="mb-2" for="cst_email"><strong>이메일</strong></label>
									<input type="text" name="cst_email" id="cst_email" class="form-control rounded-0 border-whisper border-2 py-10" autocomplete="off">
								</div>
								<input type="submit" value="아이디 찾기" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3">
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