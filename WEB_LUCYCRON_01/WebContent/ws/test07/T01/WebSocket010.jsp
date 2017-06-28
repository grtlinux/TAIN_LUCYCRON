<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "ws.test07.Addr2Type" %>
<%
	String addr = request.getRemoteAddr();
	String type = request.getParameter("type");
	if (type == null) {
		if (!Addr2Type.getInstance().isContentType(type)) {
			type = Addr2Type.getInstance().getType(addr);
		}
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>WebSocket010.jsp</title>
	
	<style type="text/css">
		#console-container {
			float: left;
			margin-left: 15px;
			width: 400px;
		}
		
		#console {
			border: 1px solid #cccccc;
			border-right-color: #999999;
			border-bottom-color: #999999;
			height: 170px;
			width: 100%;
			overflow-y: scroll;
			padding: 5px;
		}
		
		#console p {
			padding: 0;
			margin; 0;
		}
	</style>
	
	<script type="text/javascript">
		// make WebSocket Object
		var ws = {}
		ws.socket = null;
		
		ws.connect = ( function(host) {
			if ('WebSocket' in window) {
				ws.socket = new WebSocket(host);
			} else if ('MozWebSocket' in window) {
				ws.socket = new MozWebSocket(host);
			} else {
				console.log('ERROR: WebSocket is not supported by this browser.');
				return;
			}
			
			ws.socket.onopen = function() {
				console.log('INFO: WebSocket connection opened.');
			};
			
			ws.socket.onclose = function() {
				console.log('INFO: WebSocket closed.');
			};
			
			ws.socket.onmessage = function(message) {
				console.log('RCV MSG: [' + message.data + "]");
				top.ipage.onRecvMsg(message.data);
			};
		});
		
		ws.initialize = function() {
			if (window.location.protocol == 'http:') {
				ws.connect('ws://' + window.location.host + '/WEB_LUCYCRON_01/ws/test07?addr=<%=addr%>&type=<%=type%>');
			} else {
				ws.connect('wss://' + window.location.host + "/WEB_LUCYCRON_01/ws/test07?addr=<%=addr%>&type=<%=type%>");
			}
			
			console.log("INFO: initialize [<%=addr%>] [<%=type%>]");
		};
		
		ws.sendMessage = ( function(message) {
			if (message != '') {
				ws.socket.send(message);
				console.log("SND MSG: [" + message + "]");
			}
		});
		
		// start
		ws.initialize();
	</script>
	
	<script type="text/javascript">
	
		function onTest(msg) {
			//alert(msg);
			ws.sendMessage(msg);
		}

	</script>
</head>
<body>
	<h3>WebSocket010.jsp</h3>
</body>
</html>
