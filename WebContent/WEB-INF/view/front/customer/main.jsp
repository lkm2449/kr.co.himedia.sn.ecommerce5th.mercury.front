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

<!-- Start My Account  -->
<div class="my-account-box-main">
	<div class="container">
		<div class="my-account-page">
			<div class="row">
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/buylist.web"> <i class="fa fa-gift"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/buylist.web">구매 이력</a></h4>
								<p>구매 이력</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/revlist.web"> <i class="fa fa-edit"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/revlist.web">상품 리뷰</a></h4>
								<p>상품 리뷰</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/wislist.web"> <i class="fas fa-heart"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/wislist.web">찜한 상품</a></h4>
								<p>찜한 상품</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/modifyForm.web"> <i class="fa fa-user-circle"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/modifyForm.web">개인정보 변경</a></h4>
								<p>개인정보 변경</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/modifyPwdForm.web"><i class="fa fa-lock"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/modifyPwdForm.web">비밀번호 변경</a></h4>
								<p>비밀번호 변경</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="account-box">
						<div class="service-box">
							<div class="service-icon">
								<a href="/front/customer/withdrawalForm.web"> <i class="fa fa-user-times"></i></a>
							</div>
							<div class="service-desc">
								<h4><a href="/front/customer/withdrawalForm.web">회원 탈퇴</a></h4>
								<p>회원 탈퇴</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer><%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>