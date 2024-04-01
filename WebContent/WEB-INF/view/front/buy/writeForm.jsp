<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>
		.rate{
			background: url(https://aldo814.github.io/jobcloud/html/images/user/star_bg02.png) no-repeat
			; width: 121px
			; height: 20px
			; position: relative
		}
		.rate span{
			position: absolute
			; background: url(https://aldo814.github.io/jobcloud/html/images/user/star02.png)
			; width: auto
			; height: 20px
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
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
							//alert("submit");
						},
						error: function(data) {
							alert("에러");
						}
					});
				});
			});
		</c:if>
		
		<c:if test="${flgMobile != 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
					$.ajax({
						url: "/front/buy/payForm.json",
						type: "POST",
						dataType: "json",
						data: $("#frmMain").serialize(),
						success: function(data) {
							if(data.login == "login"){
								alert("로그인이 필요한 서비스입니다!");
								location.href="/front/login/loginForm.web";
							} else {
								
								$("input[name=ordr_idxx]").val(data.ordr_idxx);
								$("input[name=good_name]").val(data.good_name);
								$("input[name=good_mny]").val(data.good_mny);
								$("input[name=buyr_name]").val(data.buyr_name);
								$("input[name=site_cd]").val(data.site_cd);
								
								$("input[name=Ret_URL]").val(data.Ret_URL);
								$("input[name=approval_key]").val(data.approval_key);
								$("input[name=AppUrl]").val(data.AppUrl);

								jsf__pay();
							}
						},
						error: function(data) {
							alert("에러 발생");
						}
					});
				});
			});
		</c:if>
		
		function setBasket() {
			var seq_sle	= document.getElementById("seq_sle").value;
			var sle_nm	= document.getElementById("sle_nm").value;
			var price	= document.getElementById("price").value;
			var count	= document.getElementById("count").value;
			var img		= document.getElementById("img").src;
			
			if(count < 1){
				alert("상품을 1개 이상 입력해주세요!");
			} else {
				var item = seq_sle + "|" + sle_nm + "|" + price + "|" + count + "|" + img;
				insertBasket(item);
				
				if(confirm("장바구니에 상품이 담겼습니다. \n장바구니로 이동하시겠습니까?")) {
					location.href = "/front/cart/main.web";
				}
			}
		}
		
		function setWis() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/customer/insertWish.web";
			frmMain.submit();
		}
		
		function changeTab(value){
			
			if (value === 'nav-about-tab') {
				$('#nav-about').addClass('active');
				$('#nav-mission').removeClass('active');

				$('#nav-about-tab').addClass('active');
				$('#nav-mission-tab').removeClass('active');
			} else if (value === 'nav-mission-tab') {
				$('#nav-about').removeClass('active');
				$('#nav-mission').addClass('active');

				$('#nav-about-tab').removeClass('active');
				$('#nav-mission-tab').addClass('active');
			}
		}
		
		
	</script>
</head>
<body>
	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>
<form id="frmMain" method="POST">
	<input type="hidden" name="buyList[0].seq_sle"		id="seq_sle"		value="${saleDto.seq_sle}" />

	<input type="hidden" name="buyList[0].sle_nm"		id="sle_nm"			value="${saleDto.sle_nm}" />
	<input type="hidden" name="buyList[0].price"		id="price"			value="${saleDto.price_sale}" />
	<input type="hidden" name="flgMobile"	id="flgMobile"		value="${flgMobile}" />
	<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Shop Detail</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

<!-- Start Shop Detail  -->
	<div class="shop-detail-box-main">
		<div class="container">
			<div class="row">
				<div class="col-xl-5 col-lg-5 col-md-6">
					<div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active"> <img id="img" class="d-block w-100" src="${saleDto.img}" alt="First slide"> </div>
						</div>
					</div>
				</div>
				<div class="col-xl-7 col-lg-7 col-md-6">
					<div class="single-product-details">
						<h2>${saleDto.sle_nm}</h2>
						<h5><fmt:formatNumber value="${saleDto.price_sale}" type="number" />원</h5>
						<div class="rate">
							<c:choose>
								<c:when test="${empty saleDto.rating}">
								</c:when>
								<c:otherwise>
									<span style="width:${saleDto.rating * 20}%"></span>
								</c:otherwise>
							</c:choose>
						</div>
						<h4>제조사</h4>
						<p>${saleDto.com_nm}</p>
						<ul>
							<li>
								<div class="form-group quantity-box">
									<label class="control-label">개수</label>
									<input name="buyList[0].count" id="count" class="form-control" value="1" min="1" type="number">
								</div>
							</li>
						</ul>
					<div class="price-box-bar">
						<div class="cart-and-bay-btn">
							<a class="btn hvr-hover" data-fancybox-close="" href="#" id="btnPay">구매</a>
							<a class="btn hvr-hover" data-fancybox-close="" href="javascript:setBasket();">장바구니</a>
							<a class="btn hvr-hover" href="javascript:setWis();"><i class="fas fa-heart"></i>찜</a>
						</div>
						</div>
						<div class="add-to-btn">
							<div class="share-bar">
								<a class="btn hvr-hover" href="#"><i class="fab fa-facebook" aria-hidden="true"></i></a>
								<a class="btn hvr-hover" href="#"><i class="fab fa-google-plus" aria-hidden="true"></i></a>
								<a class="btn hvr-hover" href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a>
								<a class="btn hvr-hover" href="#"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a>
								<a class="btn hvr-hover" href="#"><i class="fab fa-whatsapp" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-12">
				<nav>
					<div class="nav nav-tabs mb-3">
						<button class="nav-link active border-white border-bottom-0" type="button" role="tab" 
							id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
							aria-controls="nav-about" aria-selected="true" onclick="changeTab(this.id)">상품 설명</button>
						<button class="nav-link border-white border-bottom-0" type="button" role="tab"
							id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
							aria-controls="nav-mission" aria-selected="false" onclick="changeTab(this.id)">상품 리뷰 (${saleDto.count })</button>
					</div>
				</nav>
				<div class="tab-content mb-5">
					<div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
						${saleDto.desces }
					</div>
					<div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
						<c:choose>
							<c:when test="${empty selectRev}">
								아직 리뷰가 없습니다.
							</c:when>
							<c:otherwise>
								<c:forEach items="${selectRev}" var="selectRev">
									<div class="d-flex">
										<img src="/image/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
										<div class="">
											<p class="mb-2" style="font-size: 14px;">${selectRev.id} (${selectRev.dt_reg})</p>
											<div class="rate">
												<span style="width:${selectRev.rating * 20}%"></span>
											</div>
											<br/>
											<div class="d-flex justify-content-between">
												<h2>${selectRev.contents} </h2>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	
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

</form>
<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<%@ include file="/include/front/common/copyAndJS.jsp" %>
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

<c:if test="${flgMobile != 'Y'}">
	<script type="text/javascript">
		function m_Completepayment(FormOrJson, closeEvent) {
			var frm = document.kcp_order_info;
			GetField(frm, FormOrJson);
			//console.log(frm);
			if (frm.res_cd.value == "0000") {
				frm.action = "/front/buy/payProcess.web";
				frm.submit();
				closeEvent();
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
	<form name="kcp_order_info" method="post" accept-charset="utf-8">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="site_name" value="payup" />
		
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
	</form>
</c:if>
</body>
</html>