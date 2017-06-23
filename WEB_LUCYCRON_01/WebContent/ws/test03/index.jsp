<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<script type="text/javascript">
		var ws;
		var messages = document.getElementById("messages");
		
		function connect() {
			// ensure only one connection is open at a time
			if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
				writeResponse("WebSocket is already connected.");
				return;
			}
			
			// create a new instance of websocket
			ws = new WebSocket("ws://" + window.location.host + "/WEB_LUCYCRON_01/ws/test03");
			
			// binds functions to the listeners for the websocket
			ws.onopen = function(event) {
				// for reasons i can't determine, onopen gets called twice
				// and the first time event.data is undefined.
				// leave a comment if you know the answer.
				if (event.data === undefined)
					return;
				
				writeResponse(event.data);
			};
			
			ws.onclose = function(event) {
				writeResponse("Connection is closed.");
			};
			
			ws.onmessage = function(event) {
				writeResponse(event.data);
			};
		}
		
		function send() {
			var message = document.getElementById("messageInput").value;
			ws.send(message);
		}
		
		function close() {
			ws.close();
		}
		
		function writeResponse(text) {
			document.getElementById("messages").innerHTML += "<br/>" + text;
			//messages.innerHTML += "<br/>" + test;
		}
	</script>
</head>
<body>

	<div>
		<input id="messageInput" type="text" />
	</div>
	
	<div>
		<button type="button" onclick="connect();">Connect</button>
		<button type="button" onclick="send();">Send</button>
		<button type="button" onclick="close();">Close</button>
	</div>
	
	<div id="messages">
	</div>
</body>
</html>