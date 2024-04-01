<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/front/common/header.jsp" %>
<style>
	ifame {
		border-color: #f0f0f0;
		margin-top:10px;
		margin-left: auto; 
		margin-right: auto; 
		border: 1px solid;
	}
</style>
<script>
function checkRegister() {
	if ($("input:checkbox[id='tos-check']").is(":checked") !== true
			|| $("input:checkbox[id='financial-check']").is(":checked") !== true
			|| $("input:checkbox[id='personal-check']").is(":checked") !== true) {
		alert('필수 약관 동의에 모두 체크해 주세요');
		return;
		}
	var frmMain = document.getElementById("form");
	frmMain.action = "/front/join/joinForm.web";
	frmMain.submit();
}
</script>
</head>
<body>
<form id="form" method="post">
	<!-- Start Main Top -->
	<%@ include file="/include/front/top.jsp" %>
	<!-- End Main Top -->

	<!-- Start Main Header -->
	<%@ include file="/include/front/header.jsp" %>
	<!-- End Main Header -->
	
	<div class="container">
		<h1 style="display: block; text-align: center; margin-top:20px;">가입 약관</h1>
		<hr>
		<div style="margin-top: 10px; margin-left:60px; display:flex; flex-direction: column;">
			<div style="display:block">
				<input type="checkbox" id="tos-check"> 
				<label for="tos-check" style="margin-left:5px">[필수] 함께할개 이용약관 동의(*)<br /></label>
			</div>
			<iframe src="/front/join/readTerm.web?url=/WEB-INF/view/front/join/termsOfService.txt" width="980px" height="130px"></iframe>
			<br />
		</div>
		
				<div style="margin-top: 10px; margin-left:60px; display:flex; flex-direction: column;">
			<div style="display:block">
				<input type="checkbox" id="financial-check"> 
				<label for="financial-check" style="margin-left:5px">[필수] 전자금융거래 이용약관 동의(*)<br /></label>
			</div>
			<iframe src="/front/join/readTerm.web?url=/WEB-INF/view/front/join/financial.txt" width="980px" height="130px"></iframe>
			<br />
		</div>
		
		<div style="margin-top: 10px; margin-left:60px; display:flex; flex-direction: column;">
			<div style="display:block">
				<input type="checkbox" id="personal-check"> 
				<label for="personal-check" style="margin-left:5px">[필수] 개인정보 수집 및 이용 동의(*)<br /></label>
			</div>
			<iframe src="/front/join/readTerm.web?url=/WEB-INF/view/front/join/personal.txt" width="980px" height="130px"></iframe>
			<br />
		</div>
		
		<div style="margin-top: 10px; margin-left:60px; display:flex; flex-direction: column;">
			<div style="display:block">
				<input type="checkbox" name="flg_sms" id="flg_sms" value="Y"/> 
				<label for="flg_sms" style="margin-left:5px">[선택] SMS 수신 동의<br /></label>
			</div>
			<br />
		</div>
		
		<div style="margin-top: 10px; margin-left:60px; display:flex; flex-direction: column;">
			<div style="display:block">
				<input type="checkbox" name="flg_email" id="flg_email" value="Y"/>
				<label for="flg_email" style="margin-left:5px">[선택] 이메일 수신 동의<br /></label>
			</div>
			<br />
		</div>

		<div style="text-align: center;">
			<input type="button" value="다음" onclick="checkRegister()" class="btn btn-dark py-11 lh-lg-22 rounded-0 fs-14 fw-600 border-2 mb-3"/>
		</div>
	</div>
<!-- Start Footer  -->
<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<!-- End Footer  -->
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</form>
</body>
</html>