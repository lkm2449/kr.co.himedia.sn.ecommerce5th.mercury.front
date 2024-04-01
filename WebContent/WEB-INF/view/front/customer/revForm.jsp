<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>
	.star-rating {
		display: flex;
		flex-direction: row-reverse;
		font-size: 2.25rem;
		line-height: 2.5rem;
		justify-content: space-around;
		padding: 0 0.2em;
		text-align: center;
		width: 5em;
	}
	
	.star-rating input {
		display: none;
	}
	
	.star-rating label {
		-webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
		-webkit-text-stroke-width: 2.3px;
		-webkit-text-stroke-color: #2b2a29;
		cursor: pointer;
	}
	
	.star-rating :checked ~ label {
		-webkit-text-fill-color: gold;
	}
	
	.star-rating label:hover,
	.star-rating label:hover ~ label {
		-webkit-text-fill-color: #fff58c;
	}
	</style>
	<script>
	
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start My Page  -->
<form id="frmMain" method="POST" action="/front/customer/revProc.web">
	<input type="hidden" id = "seq_sle" name = "seq_sle" value="${buyDtlDto.seq_sle}" />
	<input type="hidden" id = "seq_buy_dtl" name = "seq_buy_dtl" value="${buyDtlDto.seq_buy_dtl}" />
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/myPage/lnb.jsp" %>
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">상품 리뷰</h2>
						</div>
						<table  class="table" style="word-break:keep-all">
							<tr>
								<th style="width:200px;">상품 이름</th>
								<td>
									${buyDtlDto.sle_nm}
								</td>
							</tr>
							<tr>
								<th>상품 설명</th>
								<td>
									${buyDtlDto.desces}
								</td>
							</tr>
							<tr>
								<th>리뷰</th>
								<td>
									<input type="text" id="contents" name="contents" />
								</td>
							</tr>
							<tr>
								<th>별점</th>
								<td>
									<div class="star-rating space-x-4 mx-auto">
										<input type="radio" id="5-stars" name="rating" value="5" />
										<label for="5-stars" class="star pr-4">★</label>
										<input type="radio" id="4-stars" name="rating" value="4" />
										<label for="4-stars" class="star">★</label>
										<input type="radio" id="3-stars" name="rating" value="3" />
										<label for="3-stars" class="star">★</label>
										<input type="radio" id="2-stars" name="rating" value="2" />
										<label for="2-stars" class="star">★</label>
										<input type="radio" id="1-star" name="rating" value="1" />
										<label for="1-star" class="star">★</label>
									</div>
								</td>
							</tr>
						</table>
						<div style="text-align:center;">
							<input type="submit" value="리뷰 등록" />
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