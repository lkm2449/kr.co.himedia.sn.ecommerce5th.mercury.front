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
		frmMain.action = "/front/board/noticeList.web";
		frmMain.submit();
	}
	</script>
</head>
<body>

	<%@ include file="/include/front/top.jsp" %>
	<%@ include file="/include/front/header.jsp" %>

<!-- Start Board Page  -->
<form id="frmMain" method="POST" action="/front/board/noticeList.web">
	<input type="hidden" name="cd_bbs_type" value="${paging.cd_bbs_type}" />
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/board/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">공지 사항</h2>
						</div>
						<table  class="table" style="word-break:keep-all">
							<thead>
								<tr>
									<th style="width: 5%">NO</th>
									<th>제목</th>
									<th style="width: 15%">등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty list}">
										<tr>
											<td colspan="3">등록된 공지사항이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<div class="row product-categorie-box">
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane fade show active" id="grid-view">
													<div class="row">
														<c:forEach items="${list}" var="list">
															<c:set var="trColor" value="${list.rnum % 2}"/>
															<tr bgcolor="#<c:if test="${trColor == 1}">FFFFFF</c:if><c:if test="${trColor != 1}">F8F8F8</c:if>">
																<td>
																	${list.rnum}
																</td>
																<td style="text-align: left">
																<a href="/front/board/noticeView.web?seq_bbs=${list.seq_bbs}">
																<c:if test="${paging.cd_bbs_type == 1}">
																</c:if>
																	${list.title}
																	</a>	
																</td>
																<td>
																	${list.dt_reg}
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