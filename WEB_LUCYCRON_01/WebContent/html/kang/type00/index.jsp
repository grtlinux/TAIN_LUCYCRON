<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>type00_index.jsp</title>
	<style type="text/css">
		div#websocket {
			margin: 0;
			padding: 0;
			background-color: #cccccc;
		}
		
		div#page {
			margin: 0;
			padding: 0;
			background-color: #ffaaaa;
		}
		
		html, body {
			height: 100%;
			pargin: 0;
			padding: 0;
		}
	</style>
</head>
<body>

<div id='page'><iframe name='ipage' src='Page001.jsp' style='border:0;width:100%;height:1000px;margin:0;'></iframe></div>
<div id='websocket'><iframe name='iwebsocket' src='WebSocket00.jsp' style='border:0;width:100%;height:100px;margin:0;'></iframe></div>

</body>
</html>
