<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style></style>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
	// HTML Editor
	tinymce.init({selector:'textarea'});

	$(function(){
		$('#datepicker').datepicker();
	})
	
	function viewImage(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
				reader.onload = function (e) {
					$("#preview").attr("src", e.target.result);
				}
			reader.readAsDataURL(input.files[0]);
		}
	}
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start Board Page  -->
<form method="POST" action="/front/board/inquiryProc.web" enctype="multipart/form-data">
	<input type="hidden" name="cd_bbs_type" value="${cd_bbs_type}" />
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/board/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">1:1 문의</h2>
						</div>
						<table  class="table" style="word-break:keep-all">
							<tr>
								<th style="width: 15%">제목</th>
								<td>
									<input type="text" name="title" style="width: 80%;" />
								</td>
							</tr>
							<tr>
								<th>분류</th>
								<td>
									<select name="cd_bbs_tab">
										<option value="1">상품</option>
										<option value="2">구매</option>
										<option value="3">결제</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea name="contents" id="contents" style="width: 650px;height:200px;" maxlength="1000"></textarea>
								</td>
							</tr>
							<tr>
								<th>파일</th>
								<td>
									<input type="file" name="fileName" />
								</td>
							</tr>
						</table>
						<div style="text-align:center;">
							<input type="submit" value="등록" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer><%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>