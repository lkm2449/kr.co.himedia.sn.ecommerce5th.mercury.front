<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style></style>
	<script>
	
		<c:if test="${flgMobile == 'Y'}">
		$(document).ready(function() {
			$("#btnPay").on("click", function() {
				$.ajax({
					url: "/front/buy/payForm.json",
					type: "POST",
					dataType: "json",
					data: $("#frmMain").serialize(),
					success: function(data) {
						$("input[name=ordr_idxx]").val(data.ordr_idxx);
						$("input[name=good_name]").val(data.good_name);
						$("input[name=good_mny]").val(data.good_mny);
						$("input[name=buyr_name]").val(data.buyr_name);
						$("input[name=site_cd]").val(data.site_cd);
						
						$("input[name=Ret_URL]").val(data.Ret_URL);
						$("input[name=approval_key]").val(data.approval_key);
						$("input[name=AppUrl]").val(data.AppUrl);
						
						document.form.submit();
						},
						error: function(data) {
							alert("에러 발생");
						}
					});
				});
			});
		</c:if>
		
		<c:if test="${flgMobile != 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
					
					if($('input[name=chk_info]:checked').val() == 'cert'){
						
						$.ajax({
							url: "/front/buy/payForm.json",
							type: "POST",
							dataType: "json",
							data: $("#frmMain").serialize(),
							success: function(data) {
								if(data.login == "login"){
									alert("로그인이 필요한 서비스입니다!");
									location.href="/front/login/loginForm.web";
								} else if(data.cart == "cart"){
									alert("장바구니에 물건을 담아주세요!");
								} else {
									
									$("input[name=ordr_idxx]").val(data.ordr_idxx);
									$("input[name=good_name]").val(data.good_name);
									$("input[name=good_mny]").val(data.good_mny);
									$("input[name=buyr_name]").val(data.buyr_name);
									$("input[name=site_cd]").val(data.site_cd);
									
									$("input[name=Ret_URL]").val(data.Ret_URL);
									$("input[name=approval_key]").val(data.approval_key);
									$("input[name=AppUrl]").val(data.AppUrl);
									
									$("input[name=kakaopay_direct]").val("N");
									$("input[name=naverpay_direct]").val("N");
									$("input[name=site_name]").val("payup");
									$("input[name=currency]").val("WON");
									
									jsf__pay();
								}
							},
							error: function(data) {
								alert("에러 발생");
							}
						});
						
					} else if($('input[name=chk_info]:checked').val() == 'kakao'){
						
						$.ajax({
							url: "/front/buy/payKakaoForm.json",
							type: "POST",
							dataType: "json",
							data: $("#frmMain").serialize(),
							success: function(data) {
								if(data.login == "login"){
									alert("로그인이 필요한 서비스입니다!");
									location.href="/front/login/loginForm.web";
								} else if(data.cart == "cart"){
									alert("장바구니에 물건을 담아주세요!");
								} else {
									
									$("input[name=ordr_idxx]").val(data.ordr_idxx);
									$("input[name=good_name]").val(data.good_name);
									$("input[name=good_mny]").val(data.good_mny);
									$("input[name=buyr_name]").val(data.buyr_name);
									$("input[name=site_cd]").val(data.site_cd);
									
									$("input[name=Ret_URL]").val(data.Ret_URL);
									$("input[name=approval_key]").val(data.approval_key);
									$("input[name=AppUrl]").val(data.AppUrl);
									
									$("input[name=currency]").val("410");
									$("input[name=kakaopay_direct]").val("Y");
									$("input[name=naverpay_direct]").val("N");
									
									
									jsf__pay();
								}
							},
							error: function(data) {
								alert("에러 발생");
							}
						});
						
					} else if($('input[name=chk_info]:checked').val() == 'naver'){
						
						$.ajax({
							url: "/front/buy/payNaverForm.json",
							type: "POST",
							dataType: "json",
							data: $("#frmMain").serialize(),
							success: function(data) {
								if(data.login == "login"){
									alert("로그인이 필요한 서비스입니다!");
									location.href="/front/login/loginForm.web";
								} else if(data.cart == "cart"){
									alert("장바구니에 물건을 담아주세요!");
								} else {
									
									$("input[name=ordr_idxx]").val(data.ordr_idxx);
									$("input[name=good_name]").val(data.good_name);
									$("input[name=good_mny]").val(data.good_mny);
									$("input[name=buyr_name]").val(data.buyr_name);
									$("input[name=site_cd]").val(data.site_cd);
									
									$("input[name=Ret_URL]").val(data.Ret_URL);
									$("input[name=approval_key]").val(data.approval_key);
									$("input[name=AppUrl]").val(data.AppUrl);
									
									$("input[name=currency]").val("410");
									$("input[name=kakaopay_direct]").val("N");
									$("input[name=naverpay_direct]").val("Y");
									$("input[name=naverpay_point_direct]").val(data.naverpay_point_direct);

									jsf__pay();
								}
							},
							error: function(data) {
								alert("에러 발생");
							}
						});
						
					}
				});
			});
		</c:if>
		
		function deleteCookie2nd(cookieName) {
			var expireDate = new Date();
			expireDate.setDate(expireDate.getDate() - 1);
			document.cookie = cookieName + "= ; path=/; expires="
					+ expireDate.toGMTString();
		}
		
		window.onload = function() {
			
			var items		= getCookie2nd("productBasket");
			if (items) {
			var itemArray	= items.split(String.fromCharCode([15]));
			
			var totalCount = 0;
			
			var table = document.getElementById("productBasket");
			var cell_length = table.rows[0].cells.length; // 테이블 컬럼 수
			
			if (itemArray.length > 0) table.deleteRow(-1);
			
			for (loop = 0; loop < itemArray.length; loop++) {
				
				var item = itemArray[loop].split("|");
				new_row = table.insertRow();
				
				for(let i = 0; i < cell_length; i++) {
					const new_cell = new_row.insertCell(i);
					let temp_html = '';
					if(i === 0) {
							temp_html = '<td><input type="hidden" name="buyList['+loop+'].seq_sle" value="'+ item[0] + '" />' + (loop + 1) + '</td>';
					} else if(i === 1) {
							temp_html = '<td class="name-pr"><input type="hidden" name="buyList['+loop+'].sle_nm" value="'+ item[1] + '" /><a href="/front/buy/writeForm.web?seq_sle=' + item[0] + '">' + item[1] + '</a></td>';
					} else if(i === 2) {
							temp_html = '<td class="price-pr"><input type="hidden" name="buyList['+loop+'].price" value="'+ item[2] + '" />' + (item[2].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")) + '</td>';
					} else if(i === 3){
							temp_html = '<td class="thumbnail-img"><a href="#"><img class="img-fluid" style="height: 100px" src="' + item[4] + '" alt="" /></a></td>';
					} else if(i === 4){
							temp_html = '<td><p><input type="hidden" name="buyList['+loop+'].count" value="'+ item[3] + '"/><input type="number" name="count" min="1" step="1" class="c-input-text qty text" value="' + item[3] + '" onchange="updateCart()"></p></td>';
					} else if(i === 5){
							temp_html = '<td><a href="javascript:removeRow('+loop+')"><i class="fas fa-times"></i></a></td>';
					}
					new_cell.insertAdjacentHTML('afterbegin', temp_html);
				}
				
				totalCount += item[2] * item[3];
			}
			
			document.getElementById("totalCount").innerHTML = totalCount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";
			}
		}
		
		//장바구니 물품 빼기
		function removeRow(index) {
			
			var items		= getCookie2nd("productBasket");
			var item		= items.split(String.fromCharCode([15]));
			// 쿠키값을 저장할 기간(일), null일 경우는 브라우저 닫을 시 삭제됨
			var expire = null;
			
			if(item.length == 1){
				deleteCookie2nd("productBasket");
			} else {
				deleteCookie2nd("productBasket");
				
				for (i = 0; i < item.length; i++) {
					if (i != index) {
						insertBasket(item[i]);
					}
				}
			}
			// 해당 행을 삭제하고 다시 그리기
			location.href = "/front/cart/main.web";
		}
		
		//장바구니 물품 수량변경
		function updateCart() {
			
			// 변경 하고자 하는 count값 저장
			var arrCount = document.getElementsByName("count");
			var totalCount = 0;
			
			//기존 장바구니 쿠키 선언
			var items		= getCookie2nd("productBasket");
			var itemArray	= items.split(String.fromCharCode([15]));
			
			deleteCookie2nd("productBasket");
			
			// 저장한 값 개수 만큼 루프
			for (i = 0; i < arrCount.length; i++) {
				
				// i 번째 값 선언
				var count = (document.getElementsByName("count")[i].value);
				
				// 테이블 컬럼 수
				var table = document.getElementById("productBasket");
				var cell_length = table.rows[0].cells.length; 
				
				var item = itemArray[i].split("|");
				new_row = table.insertRow();
				
				totalCount += item[2] * count;
				
				// 갯수 변경
				var item = item[0] + "|" + item[1] + "|" + item[2] + "|" + count + "|" + item[4];
				
				//itemArray.splice(i, 1, item);
				
				insertBasket(item);
				
			}
			
			document.getElementById("totalCount").innerHTML = totalCount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";
		}
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Cart</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

<!-- Start Cart  -->
<form id="frmMain">
	<div class="cart-box-main">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-main table-responsive">
						<table id="productBasket" class="table">
								<tr>
									<th>No</th>
									<th>상품명</th>
									<th>가격</th>
									<th>이미지</th>
									<th>수량</th>
									<th>삭제</th>
								</tr>
								<tr>
						 			<td colspan="6">
										장바구니에 저장된 정보가 없습니다!
									</td>
								</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row my-5">
				<div class="col-lg-6 col-sm-6">
					<div class="coupon-box">
						<div class="input-group input-group-sm">
							<input class="form-control" name="requests" placeholder="요청 사항" aria-label="Coupon code" type="text">
						</div><br/>
							<input type="radio" id="cert" name="chk_info" value="cert" checked="checked">
							<label for="cert"><span class="round">인증결제</span></label>
							<input type="radio" id="kakao" name="chk_info" value="kakao" style="margin-left: 10px">
							<label for="kakao"><span class="round"><img src="/image/kakaopay.png" style="width: 65px"></span></label>
							<input type="radio" id="naver" name="chk_info" value="naver" style="margin-left: 10px">
							<label for="naver"><span class="round"><img src="/image/naverpay.png" style="width: 65px"></span></label>
					</div>
				</div>
				<div class="col-lg-6 col-sm-6">
					<div class="update-box">
						<!-- Update Cart  -->
						<input value="Update Cart" type="submit" onclick="updateCart()">
						
					</div>
				</div>
			</div>
			
			<div class="row my-5">
				<div class="col-lg-8 col-sm-12"></div>
				<div class="col-lg-4 col-sm-12">
					<div class="order-box">
						<h3>주문 요약</h3>
						<hr class="my-1">
						<div class="d-flex gr-total">
							<h5>총 금액</h5>
							<div class="ml-auto h5" id="totalCount"> 0 원</div>
						</div>
						<hr> </div>
				</div>
				<!-- 결제 -->
				<input type="hidden" name="flgMobile"	id="flgMobile" value="${flgMobile}" />
				<div class="col-12 d-flex shopping-box" style="color: white"><a class="ml-auto btn hvr-hover" id="btnPay">주문</a> </div>
			</div>
		</div>
	</div>
	</form>
	<!-- End Cart -->
	
		<!-- Start Instagram Feed  -->
	<div class="instagram-box">
		<div class="main-instagram owl-carousel owl-theme">
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-01.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-02.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-03.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-04.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-05.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-06.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-07.jpg" alt=""/>
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-08.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-09.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img src="/image/instagram-img-10.jpg" alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Instagram Feed  -->


	<footer>
		<%@ include file="/include/front/common/footer.jsp" %>
	</footer><%@ include file="/include/front/common/copyAndJS.jsp" %>
	<!-- 모바일 접근-->
	<c:if test="${flgMobile == 'Y'}">
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<form name="form" action="https://testsmpay.kcp.co.kr/pay/mobileGW.kcp" method="post" accept-charset="utf-8">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="Ret_URL" value="">
		<input type="hidden" name="approval_key" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="CARD">
		<input type="hidden" name="currency" value="410">
		<input type="hidden" name="escw_used" value="N">
		<input type="hidden" name="AppUrl" value="testApp://testURL">
	</form>
	</c:if>

	<!-- 웹 -->
	<c:if test="${flgMobile != 'Y'}">
		<script type="text/javascript">
			function m_Completepayment(FormOrJson, closeEvent) {
				var frm = document.kcp_order_info;
				GetField(frm, FormOrJson);
				//console.log(frm);
				if (frm.res_cd.value == "0000") {
					if($("input[name=kakaopay_direct]").val() == "Y"){
						
						frm.action = "/front/buy/payKakaoProcess.web";
						frm.submit();
						closeEvent();
						
						deleteCookie2nd("productBasket");
						
					} else if($("input[name=naverpay_direct]").val() == "Y"){
						
						frm.action = "/front/buy/payNaverProcess.web";
						frm.submit();
						closeEvent();
						
						deleteCookie2nd("productBasket");
						
					} else{
						frm.action = "/front/buy/payProcess.web";
						frm.submit();
						closeEvent();
						
						deleteCookie2nd("productBasket");
					}
					
				}
				else {
					alert("[" + frm.res_cd.value + "] " + frm.res_msg.value);
					closeEvent();
				}
			}
			
			function jsf__pay() {
				try {
					var form = document.kcp_order_info;
					KCP_Pay_Execute(form);
				}
				catch (e) {	}
			}

		</script>
		<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
<!-- 		<script type="text/javascript" src="https://pay.kcp.co.kr/plugin/payplus_web.jsp"></script> -->
		<!-- 인증 결제 -->
		<form name="kcp_order_info" method="post" accept-charset="utf-8">
			<input type="hidden" name="ordr_idxx" value="">
			<input type="hidden" name="good_name" value="">
			<input type="hidden" name="good_mny" value="">
			<input type="hidden" name="buyr_name" value="">
			<input type="hidden" name="site_cd" value="">
			<input type="hidden" name="Ret_URL" value="">
			
			<input type="hidden" name="req_tx" value="pay">
			<input type="hidden" name="pay_method" value="100000000000">
			<input type="hidden" name="site_name" />
			
			<input type="hidden" name="res_cd" value="" />
			<input type="hidden" name="res_msg" value="" />
			<input type="hidden" name="enc_info" value="" />
			<input type="hidden" name="enc_data" value="" />
			<input type="hidden" name="ret_pay_method" value="" />
			<input type="hidden" name="tran_cd" value="" />
			<input type="hidden" name="use_pay_method" value="" />
			<input type="hidden" name="buyr_mail" value="">
			<input type="hidden" name="ordr_chk" value="" />
			<input type="hidden" name="good_expr" value="0">
			<input type="hidden" name="module_type" value="01" />
			<input type="hidden" name="currency" value="WON" />
			
			<!-- 카카오페이 -->
			<input type="hidden" name="kakaopay_direct" >
			<input type="hidden" name="card_pay_method" value=""/>
			
			<!-- 네이버페이 -->
			<input type="hidden" name="naverpay_direct" >
			<!-- 네이버페이 포인트 결제 시 사용 -->
			<input type="hidden" name="naverpay_point_direct" value="">
			<input type="hidden" name="card_pay_method" value=""/>

		</form>
		
	</c:if>
	
	
</body>
</html>