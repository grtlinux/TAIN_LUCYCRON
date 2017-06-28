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
	<title>Page020.jsp</title>
	<style type="text/css">
		#msg {
			width: 300px;
		}
	</style>
	<script type="text/javascript">
	
		function onSendMsg(msg) {
			//parent.document.ipage.location.href = page;
			//parent.ipage.location.href = html;
			top.iwebsocket.onTest(msg);
			//top.iwebsocket.ws.socket.send(msg);
		}

		function onRecvMsg(msg) {
			document.getElementById("msg").value = msg;
		}
	</script>
</head>
<body>
<h1>Page020.jsp</h1>

<input id="button1" type="button" value="Page000" onclick="onSendMsg('T00 = 1000')">
<input id="button1" type="button" value="Page010" onclick="onSendMsg('T01 = 12345')">
<input id="button2" type="button" value="Page020" onclick="onSendMsg('T02 = ABCDEF')">
<input id="button3" type="button" value="Page010 020" onclick="onSendMsg('T02, T01 = 1234567890 ABCDEFGHIJKLMNOPQRSTU')">
<br>

<input id="msg" type="text">

<video src="JungYounSeo_20170601.mp4" poster="/WEB_LUCYCRON_01/image/imagemap.jpg" preload="auto" autoplay controls loop>
<h3>현재 사용 중인 브라우저에서는 재생할 수 없습니다.</h3>
</video>

</body>
</html>
