<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<style type="text/css">
		#textArea {
			width: 500px;
			height: 300px;
		}
		
		#text {
			width: 500px;
		}
	</style>
	<script type="text/javascript">
		var nickName = prompt("닉네임을 입력하세요.", "");
		
		var ws;     // websocket 객체를 저장할 변수
		
		function connect() {       // websocket 생성
			//ws = new WebSocket("ws://192.168.1.11:8080/WEB_LUCYCRON_01/ws/test01?id=" + nickName);
			ws = new WebSocket("ws://192.168.1.11:8080/WEB_LUCYCRON_01/ws/test01");
			ws.onmessage = onMessage;    // 메시지를 받았을 때 호출하는 function 지정
		}
		
		window.addEventListener("load", connect, false);    // 창이 열리면 websocket 객체 호출
		
		function onMessage(event) {   // 서버로부터 메시지를 받았을 때 호출되는 function
			document.getElementById("textArea").innerHTML += event.data;     // textArea에 메시지 추가
		}
		
		function submitMessage(event) {   // text의 내용을 서버로 메시지 전송
			if (event.keyCode == 13) {
				var message = document.getElementById("text").value;
				send(message);
				document.getElementById("text").value = "";
			}
		
			function send(data) {    // 서버로 데이터 보냄
				ws.send(data);
			}
		}
	</script>
</head>
<body>

	<textarea id='textArea'></textarea>
	<br>
	
	<input id='text' type='text' onkeydown='submitMessage(event);'>
	<br>
	
	<div id='test'></div>
</body>
</html>