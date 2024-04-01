<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style></style>
	<script>
	function goPage(value) {
		
		var frmMain = document.getElementById("frmMain");
		
		if($('input[name=currentPage]').val() === ''){
			frmMain.currentPage.setAttribute("value", 0);
		}
		
		frmMain.currentPage.setAttribute("value", value);
		frmMain.action = "/front/buy/salesList.web";
		frmMain.submit();
	}
	
	function sortList(){
		var frmMain = document.getElementById("frmMain");
		
		if($('input[name=currentPage]').val() === ''){
			frmMain.currentPage.setAttribute("value", 0);
		}
		
		frmMain.submit();
	}
	
	function wishProc(seq_sle){
		
		$.ajax({
			type:"post",
			async:false, 
			url:"/front/customer/wishProc.json",
			contentType: "application/json; charset=utf-8",
			data:"{\"seq_sle\":\"" + seq_sle + "\"}",
			success:function(data, textStatus) {
				if (data == 0) {
					alert("에러 발생");
				} else if(data == 1){
					alert("로그인이 필요한 서비스입니다!");
					location.href="/front/login/loginForm.web";
				} else if(data == 2){
					alert("찜 등록");
				} else if(data == 3){
					alert("찜 해제");
				} else{
					alert("에러 발생");
				}
			},
				error:function(data, textStatus) {
				alert("에러 발생");
			}
		});	
		
	}
	
	function setBasket(seq_sle, sle_nm, price, img) {

		var item = seq_sle + "|" + sle_nm + "|" + price + "|" + 1 + "|" + img;
		insertBasket(item);
			
		if(confirm("장바구니에 상품이 담겼습니다. \n장바구니로 이동하시겠습니까?")) {
			location.href = "/front/cart/main.web";
		}
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
					<h2>Shop</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->
	
	<!-- Start Shop Page  -->
	<form id="frmMain" method="POST" action="/front/buy/salesList.web">
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<input type="hidden" name="cd_ctg" value="${cd_ctg}" />
	<c:set var="cd_ctg2" value="${cd_ctg}"/>
	
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<div class="col-xl-3 col-lg-3 col-sm-12 col-xs-12 sidebar-shop-left">
					<div class="product-categori">
						<div class="search-product">
							<input id="searchSales" name="searchWord" class="form-control" placeholder="검색" type="text" value="${searchWord }" autocomplete="off">
							<button type="button" onclick="searchSale()"><i class="fa fa-search"></i></button>
						</div>
						<div class="filter-sidebar-left">
							<div class="title-left">
								<h3>카테고리</h3>
							</div>
							<div class="list-group list-group-collapse list-group-sm list-group-tree" id="list-group-men" data-children=".sub-men">
								<div class="list-group-collapse sub-men">
									<a class="list-group-item list-group-item-action <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0101 }">active</c:if>" href="#sub-men1" data-toggle="collapse" aria-expanded="false" aria-controls="sub-men1">사료 
									<small class="text-muted"></small>
								</a>
									<div class="collapse <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0101 }">show</c:if>" id="sub-men1" data-parent="#list-group-men">
										<div class="list-group">
											<a href="/front/buy/salesList.web?cd_ctg=010101" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010101 }">active</c:if>">전연령용 <small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010102" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010102 }">active</c:if>">자견용 <small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010103" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010103 }">active</c:if>">성견용 <small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010104" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010104 }">active</c:if>">노견용 <small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010105" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010105 }">active</c:if>">분유 <small class="text-muted"></small></a>
										</div>
									</div>
								</div>
								<div class="list-group-collapse sub-men">
									<a class="list-group-item list-group-item-action <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0102 }">active</c:if>" href="#sub-men2" data-toggle="collapse" aria-expanded="false" aria-controls="sub-men2">산책/이동장 
										<small class="text-muted"></small>
									</a>
									<div class="collapse <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0102 }">show</c:if>" id="sub-men2" data-parent="#list-group-men">
										<div class="list-group">
											<a href="/front/buy/salesList.web?cd_ctg=010201" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010201 }">active</c:if>">가슴줄/하네스<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010202" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010202 }">active</c:if>">리드줄<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010203" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010203 }">active</c:if>">산책용품<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010204" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010204 }">active</c:if>">이동가방<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010205" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010205 }">active</c:if>">켄넬/이동장<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010206" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010206 }">active</c:if>">유모차<small class="text-muted"></small></a>
										</div>
									</div>
								</div>
								<div class="list-group-collapse sub-men">
									<a class="list-group-item list-group-item-action <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0103 }">active</c:if>" href="#sub-men3" data-toggle="collapse" aria-expanded="false" aria-controls="sub-men2">장난감 
										<small class="text-muted"></small>
									</a>
									<div class="collapse <c:if test="${fn:substring(cd_ctg2 ,0,4) eq 0103 }">show</c:if>" id="sub-men3" data-parent="#list-group-men">
										<div class="list-group">
											<a href="/front/buy/salesList.web?cd_ctg=010301" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010301 }">active</c:if>">봉제<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010302" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010302 }">active</c:if>">공/원반<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010303" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010303 }">active</c:if>">라텍스<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010304" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010304 }">active</c:if>">치실/로프<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010305" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010305 }">active</c:if>">터그놀이<small class="text-muted"></small></a>
											<a href="/front/buy/salesList.web?cd_ctg=010306" class="list-group-item list-group-item-action <c:if test="${cd_ctg eq 010306 }">active</c:if>">노즈워크<small class="text-muted"></small></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<div class="col-12 col-sm-8 text-center text-sm-left">
								<div class="toolbar-sorter-right">
									<span>정렬 기준 </span>
									<select id="basic" name="sort" class="selectpicker show-tick form-control" data-placeholder="$ USD" onchange="sortList()">
									<option data-display="Select" value="">등록일 순</option>
									<option value="price_sale asc" <c:if test="${sort == 'price_sale asc'}">selected</c:if>>낮은 가격 순</option>
									<option value="price_sale desc" <c:if test="${sort == 'price_sale desc'}">selected</c:if>>높은 가격 순</option>
								</select>
								</div>
								<p></p>
							</div>
							<div class="col-12 col-sm-4 text-center text-sm-right">
								<ul class="nav nav-tabs ml-auto">
									<li>
										<a class="nav-link active" href="#grid-view" data-toggle="tab"> <i class="fa fa-th"></i> </a>
									</li>
								</ul>
							</div>
						</div>
						<c:choose>
							<c:when test="${empty paging}">
								<tr>
									<td colspan="6">
										<c:if test="${paging.searchWord == '' || paging.searchWord == null}">검색어를 입력하세요!</c:if>
										<c:if test="${paging.searchWord != '' && paging.searchWord != null}">검색된 상품이 없습니다!</c:if>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<div class="row product-categorie-box">
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
											<div class="row">
												<c:forEach items="${list}" var="list" varStatus="status">
													<div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
														<div class="products-single fix">
															<div class="box-img-hover">
																<div class="type-lb">
																	<p class="new">New</p>
																</div>
																<img src="${list.img}" class="img-fluid" alt="Image">
																	<div class="mask-icon">
																	<ul>
																		<li><a href="/front/buy/writeForm.web?seq_sle=${list.seq_sle}" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
																		<li><a href="javascript:wishProc(${list.seq_sle })" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
																	</ul>
																	<a class="cart" href="javascript:setBasket(${list.seq_sle }, '${list.sle_nm }', ${list.price_sale }, '${list.img }')">Add to Cart</a>
																</div>
															</div>
															<div class="why-text">
																<h4><a href="/front/buy/writeForm.web?seq_sle=${list.seq_sle}">${list.sle_nm}</a></h4>
																<h5><fmt:formatNumber value="${list.price_sale}" type="number" />원</h5>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						<br />
						<div style="display : flex; justify-content : center;">
							<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</form>

	<!-- End Shop Page -->

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
	</footer>
	<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>