<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(function(){
			$("#emailDomainSelect").on("change", function(){
				$("#emailDomainValue").attr("value", $(this).val());
			});
		});
		
		$(function(){
			$("#num1, #num2, #num3").on("keypress", keyEventFnc);
			
			function keyEventFnc(event) {
				
				if (event.key >= 0 && event.key <= 9) {
					return true;
				}
				return false;
			}
		});
		
	</script>
</head>
<body>
<form id="form" method="POST" action="/front/customer/modifyProc.web">
<input type="hidden" name="before_flg_sms"		value="${customerDto.flg_sms}" />
<input type="hidden" name="before_flg_email"	value="${customerDto.flg_email}" />

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/myPage/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">개인정보 변경</h2>
						</div>

						<div class="row product-categorie-box">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
									<div class="row">
										<table class="table">
											<tr>
												<th style="width:200px;">아이디</th>
												<td>
													${customerDto.id}
												</td>
											</tr>
											<tr>
												<th>성명</th>
												<td>
													${customerDto.cst_nm}
												</td>
											</tr>
											<tr>
												<th>연락처</th>
												<td>
													<input type="text" id="phone" name="phone" value="${customerDto.phone}" />
												</td>
											</tr>
											<tr>
												<th>주소</th>
												<td>
													<label for="postcode">우편번호</label>
													<input type="text" id="postcode" name="postcode" size="5" value="${customerDto.postcode}" />
													
													<label for="addr1">도로명 주소</label>
													<input type="text"		id="addr1"			name="addr1" size="40" value="${customerDto.addr1}" />
													<input type="hidden"	id="roadAddr"		name="roadAddr" />
													
													<span id="guide" style="color:#999; display:none"></span>
													<br />
													
													<label for="addr3">상세 주소</label>
													<input type="text"		id="addr3"			name="addr3" size="20" value="${customerDto.addr3}" >
													<input type="hidden"	id="extraAddress"	name="extraAddress" />
													
													<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="btn btn-dark py-11 lh-lg-22 rounded-0 fs-14 fw-600 border-2">
												</td>
											</tr>
											<tr>
												<th>이메일</th>
												<td>
													<input type="text" id="cst_email" name="cst_email" value="${customerDto.cst_email}" />
												</td>
											</tr>
											<tr>
												<th>마케팅 수신 동의</th>
												<td>
													SMS <input type="checkbox" name="flg_sms"<c:if test="${customerDto.flg_sms == 'Y'}"> checked</c:if> value="Y" />
													Email <input type="checkbox" name="flg_email"<c:if test="${customerDto.flg_email == 'Y'}"> checked</c:if> value="Y" />
												</td>
											</tr>
										</table>
										<div style="text-align:center;">
											<input type="submit" value="수정" class="btn btn-dark py-11 lh-lg-22 rounded-0 fs-14 fw-600 border-2 mb-3" />
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
</form>

<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>