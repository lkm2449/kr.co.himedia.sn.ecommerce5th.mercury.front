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

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- result passwd start -->
	<section class="login-register bg-gray py-5 pt-lg-80">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-5" style="border: solid 2px black; padding: 20px">
					<div class="login-register-inner bg-white p-3 p-lg-40 overflow-hidden">
						<h2 class="fw-600 mb-4 text-center">비밀번호 변경</h2>
						<hr/>
						<div class="login-register-form">
							<form id="form" method="post">
								<c:choose>
									<c:when test="${empty customerDto}">
										<div class="result">
											등록된 회원이 없습니다
										</div>
										<input type="button" value="회원가입" onclick="javascript:location.href='/front/join/tosForm.web'" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2" />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="seq_cst"		value="${customerDto.seq_cst}" />
										<div class="mb-3">
											<label class="mb-2" for="passwd"><strong>새 비밀번호</strong></label>
											<input type="password" id="passwd" name="passwd" class="form-control rounded-0 border-whisper border-2 py-10">
										</div>
										<div class="mb-3">
											<label class="mb-2" for="passwd_"><strong>새 비밀번호 재확인</strong></label>
											<input type="password" id="passwd_" name="passwd_" class="form-control rounded-0 border-whisper border-2 py-10"><span id="check">
										</div>
										<input type="button" value="비밀번호 변경" onclick="changePwd()" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2">
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