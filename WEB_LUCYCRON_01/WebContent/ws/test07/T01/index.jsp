<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "ws.test07.Addr2Type" %>
<%
	String addr = request.getRemoteAddr();
	String type = request.getParameter("type");
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
</head>
<body>

<h1>T01.index.jsp</h1>
<div id='page'><iframe name='ipage' src='Page010.jsp?addr=<%=addr%>&type=<%=type%>' style='border:0;width:100%;height:300px;padding:0;margin:0;'></iframe></div>
<div id='websocket'><iframe name='iwebsocket' src='WebSocket010.jsp?addr=<%=addr%>&type=<%=type%>' style='border:0;width:100%;height:50px;padding:0;margin:0;'></iframe></div>

</body>
</html>
