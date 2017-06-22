<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>index.jsp</title>
	
	<script type="text/javascript">
		var id;
		var newWindow;
		
		function timer() {
			id = window.setInterval( function() {
				var now = new Date();
				now = now.toLocaleTimeString();
				var div = document.getElementById("result");
				div.innerHTML = "<h2>" + now + "</h2>";
			}, 1000);
		}
		
		function timer_stop() {
			window.clearInterval(id);
		}
		
		function new_open() {
			newWindow = window.open("index.html", "제목", "width=400, height=300, top=100, left=200, resizable=no, status='상태바'");
			newWindow.document.write("<h1>새 창을 였었습니다.</h1>");
		}
		
		function new_close() {
			if (newWindow && !newWindow.closed) {
				newWindow.close();
			}
		}
		
		// location.assign("http://www.naver.com");
		// location.href = "http://www.naver.com";
		// window.location = "http://www.naver.com";
		setTimeout(function() { alert("Hello"); location.href = "type00/index.jsp"; }, 1000);
	</script>
</head>
<body>
<h1>index.jsp : <%=request.getRemoteAddr() %></h1>
<br>

<input id="button1" type="button" value="현재시간표시" onclick="timer()">
<input id="button2" type="button" value="현재시간표시중지" onclick="timer_stop()">
<input id="button3" type="button" value="새창열기" onclick="new_open()">
<input id="button4" type="button" value="새창닫기" onclick="new_close()">
<br>
<div id="result"></div>



</body>
</html>
