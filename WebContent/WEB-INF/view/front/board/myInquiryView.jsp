<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn"	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- Start Board Page  -->
<form id="frmMain" method="POST">
	<input type="hidden" name="cd_bbs_type"	value="${boardDto.cd_bbs_type}" />
	<input type="hidden" name="seq_bbs"		value="${boardDto.seq_bbs}" />
	<input type="hidden" name="file_save"	value="${boardDto.file_save}" />
	<input type="hidden" name="file_orig"	value="${boardDto.file_orig}" />
	<div class="shop-box-inner">
		<div class="container">
			<div class="row">
				<%@ include file="/include/front/board/lnb.jsp" %>
			
				<div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<h2 style="display: block; width: 100%; text-align: center">내 문의 내역</h2>
						</div>
						<table  class="table" style="word-break:keep-all">
							<tr>
								<th width="15%">제목</th>
								<td>
									${boardDto.title}
								</td>
							</tr>
							<tr>
								<th>분류</th>
								<td>
								${boardDto.cd_bbs_tab}
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									${boardDto.contents}
								</td>
							</tr>
							<c:if test="${boardDto.file_save != null}">
								<tr>
									<th>이미지</th>
									<td>
										<img id="img" src="/image/${boardDto.file_save}" height="250px" alt="이미지" style="cursor: pointer" onclick="window.open(this.src)" />
									</td>
								</tr>
							</c:if>
							<tr>
								<th>등록일</th>
								<td>
									${fn:substring(boardDto.dt_reg, 0, 10)}
								</td>
							</tr>
								
							
						</table>
						<br />
						<div id="comment">
							<table class="table">		
								<tr>
									<th width="15%">작성일</th>
									<td>
										${replyDto.dt_reg}
									</td>
									<th width="15%">답글 내용</th>
									<td>
										${replyDto.contents}
									</td>
								</tr>
							</table>
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
</html>l>