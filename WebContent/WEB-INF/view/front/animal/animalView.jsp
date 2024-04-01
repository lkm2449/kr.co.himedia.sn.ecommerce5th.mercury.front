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
	
<form id="frmMain" method="POST">
	<!-- Start Shop Page  -->
	<div class="shop-detail-box-main">
		<div class="container">
			<div class="row">
				<div class="col-xl-5 col-lg-5 col-md-6">
					<div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active"> <img id="img" class="d-block w-100" src="${view.img}" alt="First slide"> </div>
						</div>
					</div>
				</div>
			<div class="col-xl-7 col-lg-7 col-md-6">
					<div class="single-product-details">
						<h2>${view.spe_nm} | ${view.age} | ${view.col_nm}</h2>
						<h5>
							${view.sex}|
							${view.neut_yn}|
							${view.bdwgh}
						</h5>
						<div class="rate">
							공고번호 : ${view.pbl_no}<br/>
							공고기간 : ${view.dt_pbl_start} ~ ${view.dt_pbl_end}<br/>
							발견장소 : ${view.disc_info}
						</div>
						<h4>특이사항:</h4>
						<p>${view.sfert}</p>
						<ul>
							<li>
								<p>
									<h3>보호소 : ${view.shter_nm}</h3>
								</p>
								<div>
									<h3>보호소 연락처 : ${view.shter_telno}</h3>
								</div>
								<div >
									<h3>보호소 주소 : ${view.addr1}</h3>
								</div>
							</li>
						</ul>
						<div class="price-box-bar">
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