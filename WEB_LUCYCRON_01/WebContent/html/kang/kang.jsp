<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Apache Tomcat WebSocket Example: kang.html</title>
	<style type="text/css">
		#connection-container {
			float: left;
			width: 400px;
		}
		
		#connection-container div {
			padding: 5px;
		}
		
		#console-container {
			float: left;
			margin-left: 15px;
			width: 400px;
		}
		
		#console {
			border: 1px solid #CCCCCC;
			border-right-color: #999999;
			border-bottom-color: #999999;
			height: 170px;
			overflow-y: scroll;
			padding: 5px;
			width: 100%;
		}
		
		#console p {
			padding: 0;
			margin: 0;
		}
	</style>
	<script type="text/javascript">
		var ws = {};
		
		ws.socket = null;
		
		ws.connect = ( function(host) {
			if ('WebSocket' in window) {
				ws.socket = new WebSocket(host);
			} else if ('MozWebSocket' in window) {
				ws.socket = new MozWebSocket(host);
			} else {
				Console.log('Error: WebSocket is not supported by this browser..');
				return;
			}
			
			ws.socket.onopen = function() {
				
			};
			
			ws.socket.onclose = function() {
				
			};
			
			ws.socket.onmessage = function() {
				
			}
		});
	</script>
</head>
<body>

<div class="noscript">
	<h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable Javascript and reload this page!</h2>
</div>

<div>
	<p>
		<input id="chat" type="text" placeholder="type and press enter to chat"/>
	</p>
	<div id="console-container">
		<div id="console"></div>
	</div>
</div>

</body>
</html>
