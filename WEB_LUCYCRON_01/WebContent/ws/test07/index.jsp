<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "ws.test07.Addr2Type" %>
<%
	String addr = request.getRemoteAddr();
	String type = request.getParameter("TYPE");
	if (type == null) {
		if (!Addr2Type.getInstance().isContentType(type)) {
			type = Addr2Type.getInstance().getType(addr);
		}
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>type00_index.jsp</title>
	<style type="text/css">
		div#page {
			margin: 0;
			padding: 0;
			background-color: #ffaaaa;
		}
		
		div#websocket {
			margin: 0;
			padding: 0;
			background-color: #cccccc;
		}
		
		html, body {
			height: 100%;
			pargin: 0;
			padding: 0;
		}
	</style>
	<script type="text/javascript">
		// setTimeout(function() { alert("Hello"); location.href = "/WEB_LUCYCRON_01/ws/test07/<%=type%>/index.jsp"; }, 1000);
		setTimeout(function() { location.href = "/WEB_LUCYCRON_01/ws/test07/<%=type%>/index.jsp"; }, 1000);
	</script>
</head>
<body>
<%=addr %>:<%=type %>
</body>
</html>
