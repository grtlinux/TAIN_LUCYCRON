<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<style type="text/css">
		#console-container {
			float: left;
			margin-left: 15px;
			width: 500px;
		}
		
		#console {
			padding: 5px;
			border: 1px solid #cccccc;
			border-right-color: #999999;
			border-bottom-color: #999999;
			height: 170px;
			width: 100%;
			overflow-y: scroll;
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
				Console.log('Error: WebSocket is not supported by this browser.');
				return;
			}
			
			ws.socket.onopen = function() {
				Console.log('Info: WebSocket connection opened.');
				document.getElementById('chat').onkeydown = function(event) {
					if (event.keyCode == 13) {
						ws.sendMessage();
					}
				};
			};
			
			ws.socket.onclose = function() {
				document.getElementById('chat').onkeydown = null;
				Console.log('Info: WebSocket closed.');
			};
			
			ws.socket.onmessage = function(message) {
				Console.log(message.data);
			}
		});
		
		ws.initialize = function() {
			if (window.location.protocol == 'http:') {
				ws.connect('ws://' + window.location.host + '/WEB_LUCYCRON_01/ws/test06');
			} else {
				ws.connect('wss://' + window.location.host + '/WEB_LUCYCRON_01/ws/test06');
			}
		};
		
		ws.sendMessage = ( function() {
			var message = document.getElementById('chat').value;
			if (message != '') {
				ws.socket.send(message);
				document.getElementById('chat').value = '';
			}
		});
		
		ws.initialize();
		
		var Console = {};
		
		Console.log = ( function(message) {
			var console = document.getElementById('console');
			var p = document.createElement('p');
			p.style.wordWrap = 'break-word';
			p.innerHTML = message;
			
			console.appendChild(p);
			
			while (console.childNodes.length > 25) {
				console.removeChild(console.firstChild);
			}
			
			console.scrollTop = console.scrollHeight;
		});
		
		document.addEventListener("DOMContentLoaded", function() {
			// remove elements with 'noscript' class - <noscript> is not allowed in XHTML
			var noscripts = document.getElementsByClassName("noscript");
			for (var i=0; i < noscripts.length; i++) {
				noscripts[i].parentNode.removeChild(noscripts[i]);
			}
		}, false);
	</script>
</head>
<body>

	<div class="noscript"><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable Javascript and reload this page!</h2></div>

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