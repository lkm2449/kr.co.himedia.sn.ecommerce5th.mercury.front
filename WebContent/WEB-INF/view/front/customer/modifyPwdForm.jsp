<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<script>
		function change() {
			
			var pw = document.getElementById('passwd').value;
			var SC = ["!", "@", "#", "$", "%"];
			var check_SC = 0;

			if (pw.length < 8 || pw.length > 16) {
				alert('비밀번호는 8글자 이상, 16글자 이하만 이용 가능합니다.');
				return;
			}

			for (var i = 0; i < SC.length; i++) {
				if (pw.indexOf(SC[i]) != -1) {
					check_SC = 1;
				}
			}

			if (check_SC == 0) {
				alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.');
				return;
			}
			
			if (document.getElementById('passwd').value != '' && document.getElementById('passwd_').value != '') {
				if (document.getElementById('passwd').value == document.getElementById('passwd_').value) {

					var frmMain = document.getElementById("form");
					frmMain.action = "/front/customer/modifyPwdProc.web";
					frmMain.submit();
					
				} else {
					alert("비밀번호를 확인해주세요!");
				}
			}	
		}
	</script>
	<style>
	.form{
		text-align: center;
		display: flex;
		flex-direction: column;
		margin: 0 auto;
	}
	</style>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start My Page  -->

	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/myPage/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">비밀번호 변경</h2>
						</div>

						<div class="row product-categorie-box">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
									<div class="row">
									
										<div class="form">
											<form id="form" method="POST" encType="UTF-8">
												<div class="mb-3">
													<label class="mb-2" for="obPasswd_"><strong>기존 비밀번호</strong></label>
													<input type="password" id="obPasswd_" name="obPasswd_" class="form-control rounded-0 border-whisper border-2 py-10">
												</div>
												<div class="mb-3">
													<label class="mb-2" for="passwd"><strong>새 비밀번호</strong></label>
													<input type="password" id="passwd" name="passwd" class="form-control rounded-0 border-whisper border-2 py-10">
												</div>
												<div class="mb-3">
													<label class="mb-2" for="passwd_"><strong>새 비밀번호 재 확인</strong></label>
													<input type="password" id="passwd_" name="passwd_" class="form-control rounded-0 border-whisper border-2 py-10">
													<span id="check"></span>
												</div>
												<input type="button" value="수정" onclick="change()" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3">
											</form>
										</div>

									</div>
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
</footer>
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>