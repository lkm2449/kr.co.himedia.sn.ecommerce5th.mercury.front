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
		frmMain.action = "/front/animal/animalList.web";
		frmMain.submit();
	}
	
	function onSubmit() {
		var frmMain = document.getElementById("frmMain");
		frmMain.submit();
	}
	
	
	function sortList(){
		var frmMain = document.getElementById("frmMain");
		
		if($('input[name=currentPage]').val() === ''){
			frmMain.currentPage.setAttribute("value", 0);
		}
		
		frmMain.submit();
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
					<h2>함께할개</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->
	
<form id="frmMain" method="POST" action="/front/animal/animalList.web">
<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<!-- Start Shop Page  -->
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<div class="col-xl-3 col-lg-3 col-sm-12 col-xs-12 sidebar-shop-left">
					<div class="product-categori">
						<div class="search-product">
						</div>
						<div class="filter-sidebar-left">
							<div class="title-left">
								<h3>입양 정보 정렬</h3>
							</div>
							<div class="col-12 col-sm-8 text-center text-sm-left">
								<div class="toolbar-sorter-right">
									<select id="basic" name="sort" onchange="sortList()" style = "font-size:20px;border-radius:10px; background-color:#F5F6CE; height:45px; width:200px;">
										<option data-display="Select" value="">공고 고유번호순</option>
										<option value="dt_recept desc" <c:if test="${sort == 'dt_recept desc'}">selected</c:if>>접수일순</option>
										<option value="dt_pbl_start desc" <c:if test="${sort == 'dt_pbl_start desc'}">selected</c:if>>공고 시작일순</option>
										<option value="dt_pbl_end desc" <c:if test="${sort == 'dt_pbl_end desc'}">selected</c:if>>공고 종료일순</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
					<div class="product-categori">
						<div class="product-item-filter row" style ="background-color : gold; height: 73px;">
							<div class="search-product" style = "margin: 5px;">
								<select name="searchKey" style = "font-size:20px; border-radius:10px; background-color:#F5F6CE; height:35px;">
									<option value="jurisd"<c:if test="${paging.searchKey == 'jurisd'}"> selected</c:if>>관할 기관</option>
									<option value="spe_nm"<c:if test="${paging.searchKey == 'spe_nm'}"> selected</c:if>>품종</option>
								</select>
									<input id="searchSales" name="searchWord" style = "width: 200px; background-color:#F5F6CE;" placeholder="을 입력해주세요" type="text" value="${paging.searchWord }" autocomplete="off">
								<select name="sex" style = "font-size:18px; border-radius:10px; background-color:#F5F6CE; height:35px;">
									<option value="">성별(전체)</option>
									<option value="M"<c:if test="${paging.sex == 'M'}"> selected</c:if>>남아</option>
									<option value="F"<c:if test="${paging.sex == 'F'}"> selected</c:if>>여아</option>
									<option value="Q"<c:if test="${paging.sex == 'Q'}"> selected</c:if>>알수없음</option>
								</select>
								<select name="neut_yn" style = "font-size:18px; border-radius:10px; background-color:#F5F6CE; height:35px;">
									<option value="">중성화 여부(전체)</option>
									<option value="Y"<c:if test="${paging.neut_yn == 'Y'}"> selected</c:if>>중성화 완료</option>
									<option value="N"<c:if test="${paging.neut_yn == 'N'}"> selected</c:if>>중성화 미완료</option>
									<option value="U"<c:if test="${paging.neut_yn == 'U'}"> selected</c:if>>중성화 알수없음</option>
								</select>
								<select name="age" style = "font-size:18px; border-radius:10px; background-color:#F5F6CE; height:35px;">
									<option value="">생년(전체)</option>
									<option value="2024(60일미만)(년생)"<c:if test="${paging.age == '2024(60일미만)(년생)'}"> selected</c:if>>2024(60일미만)(년생)</option>
									<option value="2024(년생)"<c:if test="${paging.age == '2024(년생)'}"> selected</c:if>>2024(년생)</option>
									<option value="2023(년생)"<c:if test="${paging.age == '2023(년생)'}"> selected</c:if>>2023(년생)</option>
									<option value="2022(년생)"<c:if test="${paging.age == '2022(년생)'}"> selected</c:if>>2022(년생)</option>
									<option value="2021(년생)"<c:if test="${paging.age == '2021(년생)'}"> selected</c:if>>2021(년생)</option>
									<option value="2020(년생)"<c:if test="${paging.age == '2020(년생)'}"> selected</c:if>>2020(년생)</option>
									<option value="2019(년생)"<c:if test="${paging.age == '2019(년생)'}"> selected</c:if>>2019(년생)</option>
									<option value="2018(년생)"<c:if test="${paging.age == '2018(년생)'}"> selected</c:if>>2018(년생)</option>
									<option value="2017(년생)"<c:if test="${paging.age == '2017(년생)'}"> selected</c:if>>2017(년생)</option>
									<option value="2016(년생)"<c:if test="${paging.age == '2016(년생)'}"> selected</c:if>>2016(년생)</option>
									<option value="2015(년생)"<c:if test="${paging.age == '2015(년생)'}"> selected</c:if>>2015(년생)</option>
									<option value="2014(년생)"<c:if test="${paging.age == '2014(년생)'}"> selected</c:if>>2014(년생)</option>
									<option value="2013(년생)"<c:if test="${paging.age == '2013(년생)'}"> selected</c:if>>2013(년생)</option>
									<option value="2012(년생)"<c:if test="${paging.age == '2012(년생)'}"> selected</c:if>>2012(년생)</option>
									<option value="2011(년생)"<c:if test="${paging.age == '2011(년생)'}"> selected</c:if>>2011(년생)</option>
									<option value="2010(년생)"<c:if test="${paging.age == '2010(년생)'}"> selected</c:if>>2010(년생)</option>
									<option value="2009(년생)"<c:if test="${paging.age == '2009(년생)'}"> selected</c:if>>2009(년생)</option>
								</select>
								<input type="submit" value="조회" style ="background-color:#F5F6CE; border-radius:10px; height:35px;"/>
							</div>
						</div>
					</div>
						<c:choose>
							<c:when test="${empty list}">
								공고 중인 정보가 없습니다!
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
																<img src="${list.img}" class="img-fluid" alt="Image" style="height:260px;">
															</div>
															<div class="why-text">
																<h5><a href="/front/animal/animalView.web?abd_no=${list.abd_no}">${list.spe_nm} | ${list.sex}</a></h5><br/>
																${list.shter_nm }
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