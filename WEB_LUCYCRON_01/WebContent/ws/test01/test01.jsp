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
		var nickName = prompt("�г����� �Է��ϼ���.", "");
		
		var ws;     // websocket ��ü�� ������ ����
		
		function connect() {       // websocket ����
			//ws = new WebSocket("ws://192.168.1.11:8080/WEB_LUCYCRON_01/ws/test01?id=" + nickName);
			ws = new WebSocket("ws://192.168.1.11:8080/WEB_LUCYCRON_01/ws/test01");
			ws.onmessage = onMessage;    // �޽����� �޾��� �� ȣ���ϴ� function ����
		}
		
		window.addEventListener("load", connect, false);    // â�� ������ websocket ��ü ȣ��
		
		function onMessage(event) {   // �����κ��� �޽����� �޾��� �� ȣ��Ǵ� function
			document.getElementById("textArea").innerHTML += event.data;     // textArea�� �޽��� �߰�
		}
		
		function submitMessage(event) {   // text�� ������ ������ �޽��� ����
			if (event.keyCode == 13) {
				var message = document.getElementById("text").value;
				send(message);
				document.getElementById("text").value = "";
			}
		
			function send(data) {    // ������ ������ ����
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