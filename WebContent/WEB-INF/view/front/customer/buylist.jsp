<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/common/header.jsp" %>
	<style></style>
	<script>
		
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/customer/buylist.web";
			frmMain.submit();
		}
		
		function viewdetail(value){
			if(document.getElementById(value).style.visibility == "collapse"){
				document.getElementById("button" + value).innerHTML = "<i class='fa fa-angle-up' ></i>"
				document.getElementById(value).style.visibility= "visible";
			}else{
				document.getElementById("button" + value).innerHTML = "<i class='fa fa-angle-down' ></i>"
				document.getElementById(value).style.visibility = "collapse";
			}
		}
		
		function cancelPay(value){
			if(confirm("결제를 취소하시겠습니까?")){
				$.ajax({
					type:"post",
					async:false, 
					url:"/front/buy/cancelPay.json",
					contentType: "application/json; charset=utf-8",
					data:"{\"seq_buy_mst\":\"" + value + "\"}",
					success:function(data, textStatus) {
						if (data == true) {
							alert("결제 취소가 완료되었습니다");
							
							var frmMain = document.getElementById("frmMain");
							
							frmMain.action = "/front/customer/buylist.web";
							frmMain.submit();
						}
						else {
							alert("결제 취소에 실패하였습니다! 관리자에게 문의 부탁드립니다");
						}
					},
	 				error:function(data, textStatus) {
						alert("에러");
					}
				});	
			}
		}
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start My Page  -->
<form id="frmMain" method="POST" >
<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/myPage/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">구매 이력</h2>
						</div>
						<table  class="table" style="word-break:keep-all">
							<thead>
								<tr>
									<th style="width: 5%">NO</th>
									<th>구매 정보</th>
									<th>상품 가격</th>
									<th style="width: 15%">결제 여부</th>
									<th style="width: 15%">배송 정보</th>
									<th style="width: 15%">구매일</th>
									<th></th>
									<th style="width: 2%"></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty mstList}">
										<tr>
											<td colspan="8">주문한 상품이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<div class="row product-categorie-box">
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
													<div class="row">
														<c:forEach items="${mstList}" var="mstList" varStatus="status">
															<tr <c:if test="${mstList.cd_state_pay == '결제취소'}">style="color:red"</c:if>>
																<td>${mstList.rnum }</td>
																<td style="width: 30%">${mstList.buy_info }</td>
																<td><fmt:formatNumber value="${mstList.buy_t_price }" type="number" />원</td>
																<td>${mstList.cd_state_pay }</td>
																<td>${mstList.cd_state_delivery }</td>
																<td style="width: 30%;">${mstList.dt_reg }</td>
																<!-- 결제 취소 -->
																<td>
																	<c:choose>
																		<c:when test="${mstList.cd_state_pay == '결제취소'}">
																			결제 취소
																		</c:when>
																		<c:otherwise>
																			<input type="button" class="btn btn-dark py-11 lh-lg-22 rounded-0 w-100 fs-14 fw-600 border-2 mb-3" value="구매 취소" onclick="cancelPay(${mstList.seq_buy_mst})" />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td align="right">
																	<button type="button" id="button${mstList.seq_buy_mst }" onclick="viewdetail(${mstList.seq_buy_mst })" >
																		<i class="fa fa-angle-down" ></i>
																	</button>
																</td>
															</tr>
																<c:choose>
																	<c:when test="${empty dtlList}">
																	</c:when>
																	<c:otherwise>
																	<tbody id="${mstList.seq_buy_mst }" style="visibility: collapse;">
																		<c:forEach items="${dtlList}" var="dtlList">
																			<c:if test="${mstList.seq_buy_mst eq dtlList.seq_buy_mst}" >
																				<tr>
																					<td colspan="3">상품명 : <a href="/front/buy/writeForm.web?seq_sle=${dtlList.seq_sle }">${dtlList.sle_nm }</a></td>
																					<td>개당 가격 : <fmt:formatNumber value="${dtlList.price}" type="number" />원</td>
																					<td>구매 개수 : ${dtlList.count }</td>
																					<td colspan="2">
																						<a href="/front/buy/writeForm.web?seq_sle=${dtlList.seq_sle }">
																							<img src="${dtlList.img }" class="thumbnail round" style="width: 100px; height: 100px;"/>
																						</a>
																					</td>
																					<td>
																						<c:choose>
																							<c:when test="${mstList.cd_state_pay == '결제취소'}">
																								결제 취소
																							</c:when>
																							<c:when test="${dtlList.cd_state_rev != 'Y'}">
																								<a href="/front/customer/revForm.web?seq_sle=${dtlList.seq_sle}&seq_buy_dtl=${dtlList.seq_buy_dtl}">리뷰 작성</a>
																							</c:when>
																							<c:otherwise>
																								이미 리뷰를 작성하였습니다
																							</c:otherwise>
																						</c:choose>
																					</td>
																				</tr>
																				<c:set var="count" value="${count + 1 }" />
																			</c:if>
																		</c:forEach>
																	</tbody>
																	</c:otherwise>
																</c:choose>
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