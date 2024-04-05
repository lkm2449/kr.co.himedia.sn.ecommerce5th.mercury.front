<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<!-- Basic -->
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
<style>
</style>
<script>
</script>
</head>
<body>

	<!-- Start Main Top -->
	<%@ include file="/include/front/top.jsp" %>
	<!-- End Main Top -->

	<!-- Start Main Header -->
	<%@ include file="/include/front/header.jsp" %>
	<!-- End Main Header -->

	<!-- Start Slider -->
	<div id="slides-shop" class="cover-slides">
		<ul class="slides-container">
			<li class="text-center">
				<img src="/image/mainbanner/animalMain1.jpg" alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20"><strong>입양<br></strong></h1>
							<p class="m-b-40">이 아이들은 하루하루 가족을 기다리고있습니다<br> 이 아이들의 소중한 가족이 되어주세요.</p>
							<p><a class="btn hvr-hover" href="/front/animal/animalList.web">입양 정보 보기</a></p>
						</div>
					</div>
				</div>
			</li>
			<li class="text-right">
				<img src="/image/mainbanner/animalMain5.jpg" alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20"><strong>사료<br></strong></h1>
							<p class="m-b-40">사랑하는 만큼<br> 맛있고 좋은 사료를 주세요.</p>
							<p><a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0101">상품 보기</a></p>
						</div>
					</div>
				</div>
			</li>
			<li class="text-center">
				<img src="/image/mainbanner/animalMain3.jpg" alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20"><strong>산책/이동장<br></strong></h1>
							<p class="m-b-40">가벼운 산책부터 여행까지 <br> 즐거운 추억을 만들어봐요.</p>
							<p><a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0102">상품 보기</a></p>
						</div>
					</div>
				</div>
			</li>
			<li class="text-left">
				<img src="/image/mainbanner/animalMain4.jpg" alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20"><strong>장난감<br></strong></h1>
							<p class="m-b-40">다양한 장난감으로 <br> 건강하고 즐겁게 놀아주세요.</p>
							<p><a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0103">상품 보기</a></p>
						</div>
					</div>
				</div>
			</li>
		</ul>
		<div class="slides-navigation">
			<a href="#" class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
			<a href="#" class="prev"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
		</div>
	</div>
	<!-- End Slider -->

	<!-- Start Categories  -->
	<div class="categories-shop">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="shop-cat-box">
						<img class="img-fluid" src="/image/feed.jpg" alt="" />
						<a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0101">사료</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="shop-cat-box">
						<img class="img-fluid" src="/image/walk.jpg" alt="" />
						<a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0102">산책/이동장</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="shop-cat-box">
						<img class="img-fluid" src="/image/toy.jpg" alt="" />
						<a class="btn hvr-hover" href="/front/buy/salesList.web?cd_ctg=0103">장난감</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Categories -->
	
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

	<!-- Start Footer  -->
	<footer>
		<%@ include file="/include/front/common/footer.jsp" %>
	</footer>
	<!-- End Footer  -->
	<%@ include file="/include/front/common/copyAndJS.jsp" %>

</body>

</html>