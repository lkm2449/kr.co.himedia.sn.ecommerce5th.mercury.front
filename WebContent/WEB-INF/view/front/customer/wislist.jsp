<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style>
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
							<h2 style="display: block; width: 100%; text-align: center">찜한 상품</h2>
						</div>
						<table class="table" style="word-break:keep-all">
							<thead>
								<tr>
									<th style="width: 5%">NO</th>
									<th>상품명</th>
									<th>상품 이미지</th>
									<th>상품 가격</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty list}">
										<tr>
											<td colspan="7">아직 찜한 상품이 없습니다.</td>
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
																	<a href="/front/buy/writeForm.web?seq_sle=${list.seq_sle}">
																		${list.sle_nm}
																	</a>
																</td>
																<td>
																	<img src="${list.img}" width="100" height="100" />
																</td>
																<td>
																	${list.price_sale}원
																</td>
																<td>
																	<a href="/front/customer/deleteProc.web?seq_sle=${list.seq_sle}"><i class="fas fa-times"></i></a>
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
</footer><%@ include file="/include/front/common/copyAndJS.jsp" %>
</body>
</html>


