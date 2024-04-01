<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="content-script-type" content="text/javascript" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title></title>
	<style></style>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<div class="container">
<form name="form" action="${pay_url}" method="post" accept-charset="euc-kr">
<input type="hidden" name="ordr_idxx" value="${ordr_idxx}">
<input type="hidden" name="good_name" value="${good_name}">
<input type="hidden" name="good_mny" value="${good_mny}">
<input type="hidden" name="buyr_name" value="${buyr_name}">
<input type="hidden" name="site_cd" value="${site_cd}">
<input type="hidden" name="Ret_URL" value="${Ret_URL}">
<input type="hidden" name="approval_key" value="${approval_key}">
<!-- 고정값 -->
<input type="hidden" name="req_tx" value="pay">
<input type="hidden" name="pay_method" value="CARD">
<input type="hidden" name="currency" value="410">
<input type="hidden" name="escw_used" value="N">
<input type="hidden" name="AppUrl" value="">
</form>
<script>
	document.form.submit();
</script>
</div>
</body>
</html>