<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/front/common/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(function(){

		$("#passwd, #passwd_").keyup(function(){
			if (document.getElementById('passwd').value == document.getElementById('passwd_').value) {
				document.getElementById('check').innerHTML = '비밀번호가 일치합니다.';
				document.getElementById('check').style.color = 'blue';
			} else {
				document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
				document.getElementById('check').style.color = 'red';
			}
		});
		
		$("#id").keyup(function(){
			
			var value = document.getElementById("id").value;
			
			if (value == null || value == "") {
				document.getElementById('checkId').innerHTML = '';
				document.getElementById('checkId').style.color = 'black';
				document.getElementById("checkIdResult").value = "true";
				return;
			}
			$.ajax({
				type:"post",
				async:false, 
				url:"/front/join/joinForm.json",
				contentType: "application/json; charset=utf-8",
				data:"{\"id\":\"" + value + "\"}",
				success:function(data, textStatus) {
					if (data == true) {
						document.getElementById('checkId').innerHTML = '중복된 아이디입니다';
						document.getElementById('checkId').style.color = 'red';
						document.getElementById("checkIdResult").value = "true";
					}
					else {
						document.getElementById('checkId').innerHTML = '사용 가능한 아이디입니다';
						document.getElementById('checkId').style.color = 'blue';
						document.getElementById("checkIdResult").value = "false";
					}
				},
					error:function(data, textStatus) {
						document.getElementById('checkId').innerHTML = '중복된 아이디입니다';
						document.getElementById('checkId').style.color = 'red';
				}
			});	
			
		});
	});
</script>
<style>
</style>
</head>
<body>

	<!-- Start Main Top -->
	<%@ include file="/include/front/top.jsp" %>
	<!-- End Main Top -->

	<!-- Start Main Header -->
	<%@ include file="/include/front/header.jsp" %>
	<!-- End Main Header -->
	
	<div class="shop-box-inner">
		<div class="container" style="border: solid black; padding: 50px">
			<form id="form" method="POST" encType="UTF-8">
			<input type="hidden" id="checkIdResult" value="true" />
			<input type="hidden" name="flg_sms" value="${flg_sms}" />
			<input type="hidden" name="flg_email" value="${flg_email}" />
			<input type="hidden" name="sso" value="H" />
				<h2 style="display: block; width: 100%; text-align: center">회원 가입</h2>
				<table class="table">
				<tr>
					<th style="width:200px;">아이디(*)</th>
					<td>
						<input type="text" id="id" name="id" />
						<br/><span id="checkId"></span>
					</td>
				</tr>
				<tr>
					<th>암호(*)</th>
					<td>
						<input type="password" id="passwd" name="passwd"/> 
						<br/><span>※ 비밀번호는 특수문자(!,@,#,$,%) 포함 8~16자</span>
					</td>
				</tr>
				<tr>
					<th>암호 재확인(*)</th>  
					<td>
						<input type="password" id="passwd_" name="passwd_"/> <span id="check"></span>
					</td>
				</tr>
				<tr>
					<th>성명(*)</th>
					<td>
						<input type="text" id="cst_nm" name="cst_nm" />
					</td>
				</tr>
				<tr>
					<th>연락처(*)</th>
					<td>
						<input type="text" id="phone" name="phone" />
					</td>
				</tr>
				<tr>
					<th>주소(*)</th>
					<td>
						<label for="postcode">우편번호</label>
						<input type="text" id="postcode" name="postcode" size="5" />
						
						<label for="addr1">도로명 주소</label>
						<input type="text"		id="addr1"			name="addr1" size="40" />
						<input type="hidden"	id="roadAddr"		name="roadAddr" />
						
						<span id="guide" style="color:#999; display:none"></span>
						<br />
						<label for="addr3">상세 주소</label>
						<input type="text"		id="addr3"			name="addr3" size="20" >
						<input type="hidden"	id="extraAddress"	name="extraAddress" />
						
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="btn btn-dark py-11 lh-lg-22 rounded-0 fs-14 fw-600 border-2 ml-3"/>
						</td>
					</tr>
					<tr>
						<th>이메일(*)</th>
						<td>
							<input type="text" id="cst_email" name="cst_email" />
						</td>
					</tr>
				</table>
				<div style="margin: 20px; text-align: center">
					<input type="button" value="회원가입" onclick="register()" class="btn btn-dark py-11 lh-lg-22 rounded-0 fs-14 fw-600 border-2"/>	
				</div>
			</form>
		</div>
	</div>

<!-- Start Footer  -->
<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<!-- End Footer  -->
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>