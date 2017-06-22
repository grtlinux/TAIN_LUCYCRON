<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>WebSocket00.jsp</title>
	<script type="text/javascript">
	
		function onChange(html) {
			//parent.document.ipage.location.href = page;
			//parent.ipage.location.href = html;
			top.ipage.location.href = html;
		}

	</script>
</head>
<body>

<input id="button1" type="button" value="Page001" onclick="onChange('Page001.jsp')">
<input id="button2" type="button" value="Page002" onclick="onChange('Page002.jsp')">

<h1>WebSocket00.jsp</h1>



</body>
</html>
