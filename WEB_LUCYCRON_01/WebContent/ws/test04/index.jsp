<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	
	<script type="text/javascript">
		var ws = null;
		
		function connectWebSocket() {
			var url = "ws://" + window.location.host + "/WEB_LUCYCRON_01/ws/test04";
			
			ws = new WebSocket(url);
			
			ws.binaryType = "arraybuffer";
			ws.onopen = function() {
				alert("연결 완료");
			};
			
			ws.onmessage = function(event) {
				alert(event.msg);
			};
			
			ws.onclose = function() {
				alert("연결 종료");
			};
			
			ws.onerror = function(event) {
				alert(event.msg);
			}
		}
		
		function sendFile() {
			var file = document.getElementById("file").files[0];
			
			ws.send('filename:' + file.name);
			alert('filename:' + file.name);
			
			var reader = new FileReader();
			var rawData = new ArrayBuffer();
			
			reader.loadend = function() {
				
			}
			
			reader.onload = function(event) {
				rawData = event.target.result;
				ws.send(rawData);
				alert("파일 전송이 완료 되었습니다.");
				ws.send('end');
			}
			
			reader.readAsArrayBuffer(file);
		}
		
		function addEvent() {
			document.getElementById("connect").addEventListener("click", connectWebSocket, false);
			document.getElementById("send").addEventListener("click", sendFile, false);
		}
		
		window.addEventListener("load", addEvent, false);
	</script>
</head>
<body>

	<input id="file" type="file">
	<input id="connect" type="button" value="connect">
	<input id="send" type="button" value="send">
	
</body>
</html>