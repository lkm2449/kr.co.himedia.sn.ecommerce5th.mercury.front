<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<header class="main-header">
	<!-- Start Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
		<div class="container">
			<!-- Start Header Navigation -->
			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
				<i class="fa fa-bars"></i>
			</button>
				<a class="navbar-brand" href="/">
					<img src="/image/logo.png" class="logo" alt="" style="width: 80px; height: 81px;">
					<img src="/image/logo_letter.png" class="logo" alt="" style="width: 80px; height: 81px;">
				</a>
			</div>
			<!-- End Header Navigation -->

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-menu">
				<ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
					<li class="nav-item active"><a class="nav-link" href="/">Home</a></li>
					<li class="dropdown">
						<a href="/front/buy/salesList.web?cd_ctg=0101" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">사료</a>
						<ul class="dropdown-menu">
							<li><a href="/front/buy/salesList.web?cd_ctg=010101">전연령용</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010102">자견용</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010103">성견용</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010104">노견용</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010105">분유</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="/front/buy/salesList.web?cd_ctg=0102" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">산책/이동장</a>
						<ul class="dropdown-menu">
							<li><a href="/front/buy/salesList.web?cd_ctg=010201">가슴줄/하네스</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010202">리드줄</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010203">산책용품</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010204">이동가방</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010205">켄넬/이동장</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010206">유모차</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="/front/buy/salesList.web?cd_ctg=0103" class="nav-link dropdown-toggle arrow" data-toggle="dropdown">장난감</a>
						<ul class="dropdown-menu">
							<li><a href="/front/buy/salesList.web?cd_ctg=010301">봉제</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010302">공/원반</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010303">라텍스</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010304">치실/로프</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010305">터그놀이</a></li>
							<li><a href="/front/buy/salesList.web?cd_ctg=010306">노즈워크</a></li>
						</ul>
					</li>
					<li class="nav-item"><a class="nav-link" href="/front/animal/animalList.web">입양</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->

			<!-- Start Atribute Navigation -->
			<div class="attr-nav">
				<ul>
					<li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
					<li>
						<a href="/front/cart/main.web">
							<i class="fa fa-shopping-bag"></i>
							<span class="badge"></span>
						</a>
					</li>
				</ul>
			</div>
			<!-- End Atribute Navigation -->
		</div>
	</nav>
</header>
	<!-- End Navigation -->

	
<!-- Start Top Search -->
<div class="top-search">
	<div class="container">
		<div class="input-group">
			<span class="input-group-addon"><a href="javascript:searchList()"><i class="fa fa-search"></i></a></span>
			<input type="text" id="searchWord" class="form-control" placeholder="검색" autocomplete="off">
			<span class="input-group-addon close-search"><i class="fa fa-times"></i></span>		
		</div>
	</div>
</div>
<!-- End Top Search -->