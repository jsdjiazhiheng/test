<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
		String path = request.getContextPath();
		request.setAttribute("root", path + "/");
	%>

	<script src="${root }resources/extjs/ext-all-debug.js"></script>
	<script src="${root }resources/extjs/SearchField.js"></script>
	<script src="${root }resources/extjs/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${root }resources/extjs/packages/ext-theme-neptune/build/resources/ext-theme-neptune-all.css">
	<link rel="stylesheet" type="text/css" href="${root }resources/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${root }resources/base-css.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<c:set var="SESSION_USER" value="${SESSION_USER.userName}" />
<script type="text/javascript">
	var root = '${root}';
	var SESSION_USER = '${SESSION_USER}';
</script>
