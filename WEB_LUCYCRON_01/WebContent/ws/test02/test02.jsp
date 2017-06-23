<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<script type="text/javascript">
		var ws = new WebSocket("ws://" + window.location.host + "/WEB_LUCYCRON_01/ws/test02");
		var messageTextArea = document.getElementById("messageTestArea");
		
		ws.onmessage = function processMessage(message) {
			var jsonData = JSON.parse(message.data);
			if (jsonData.message != null) {
				messageTextArea.value += jsonData.message + "\n";
			}
		};
		
		function sendMessage() {
			var messageText = document.getElementById("messageText");
			ws.send(messageText.value);
			messageText.value = "";
		}
	</script>
</head>
<body>
	<textarea id="messageTextArea" readonly="readonly" rows="10" cols="45"></textarea>
	<br>
	
	<input id="messageText" type="text" size="50">
	<input type="button" value="Send" onclick="sendMessage()">
	
</body>
</html>