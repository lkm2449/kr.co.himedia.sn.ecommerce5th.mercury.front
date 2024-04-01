<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>
	.rate{background: url(https://aldo814.github.io/jobcloud/html/images/user/star_bg02.png) no-repeat;width: 121px;height: 20px;position: relative;}
	.rate span{position: absolute;background: url(https://aldo814.github.io/jobcloud/html/images/user/star02.png);width: auto;height: 20px;}
	</style>
	<script>
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>
<form id="frmMain" method="POST" >
<input type="hidden" name="currentPage" value="${paging.currentPage}" />
<!-- Start My Page  -->
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/myPage/lnb.jsp" %>
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">상품 리뷰</h2>
						</div>
						<table class="table" style="word-break:keep-all">
							<thead>
								<tr>
									<th style="width: 5%">NO</th>
									<th>상품명</th>
									<th>상품 이미지</th>
									<th>리뷰 내용</th>
									<th>별점</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty list}">
										<tr>
											<td colspan="7">아직 리뷰한 상품이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<div class="row product-categorie-box">
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
													<div class="row">
														<c:forEach items="${list}" var="list">
															<tr>
																<td>
																	${list.rnum}
																</td>
																<td>
																	<a href="/front/buy/writeForm.web?seq_sle=${list.seq_sle }">
																		${list.sle_nm}
																	</a>
																</td>
																<td>
																	<img src="${list.img}" width="100" height="100" />
																</td>
																<td>
																	${list.contents}
																</td>
																<td>
																	<div class="rate">
																		<span style="width:${list.rating * 20}%"></span>
																	</div>
																</td>
															</tr>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
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

<footer>
	<%@ include file="/include/front/common/footer.jsp" %>
</footer>
<%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>


