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
	<title>type00_index.jsp</title>
	<style type="text/css">
		div#page {
			margin: 0;
			padding: 0;
			background-color: #ffaaaa;
		}
		
		div#websocket {
			margin: 0;
			padding: 0;
			background-color: #cccccc;
		}
		
		html, body {
			height: 100%;
			pargin: 0;
			padding: 0;
		}
	</style>
	<script type="text/javascript">
	
		function launchFullscreen1() {
			if (document.fullscreenEnabled ||
			document.webkitFullscreenEnabled ||
			document.msFullscreenEnabled ||
			document.mozFullScreenEnabled) {
				if (this.requestFullscreen) {
					this.requestFullscreen();
				} else if (this.webkitRequestFullscreen) {
					this.webkitRequestFullscreen();
				} else if (this.msRequestFullscreen) {
					this.msRequestFullscreen();
				} else if (this.mozRequestFullScreen) {
					this.mozRequestFullScreen();
				}
			} else {
				alert("Your browser doesn't support the fullscreen API.");
			}
		}
		
		function launchFullscreen2() {
			if (document.fullscreenEnabled ||
			document.webkitFullscreenEnabled ||
			document.msFullscreenEnabled ||
			document.mozFullScreenEnabled) {
				if (document.documentElement.requestFullscreen) {
					document.documentElement.requestFullscreen();
				} else if (document.documentElement.webkitRequestFullscreen) {
					document.documentElement.webkitRequestFullscreen();
				} else if (document.documentElement.msRequestFullscreen) {
					document.documentElement.msRequestFullscreen();
				} else if (document.documentElement.mozRequestFullScreen) {
					document.documentElement.mozRequestFullScreen();
				}
			} else {
				alert("Your browser doesn't support the fullscreen API.");
			}
		}
		
		function exitFullscreen() {
			if (document.fullscreenEnabled ||
			document.webkitFullscreenEnabled ||
			document.msFullscreenEnabled ||
			document.mozFullScreenEnabled) {
				if (document.exitFullscreen) {
					document.exitFullscreen();
				} else if (document.webkitExitFullscreen) {
					document.webkitExitFullscreen();
				} else if (document.msExitFullscreen) {
					document.msExitFullscreen();
				} else if (document.mozCancelFullScreen) {
					document.mozCancelFullScreen();
				}
			} else {
				alert("Your browser doesn't support the fullscreen API.");
			}
		}
		
		function launchFullscreen(element) {
	        var img = document.getElementById("f-img");
	        if (element.requestFullscreen) {
	            img.requestFullscreen();
	        } else if (element.mozRequestFullScreen) {
	            img.mozRequestFullScreen();                   // Firefox
	        } else if (element.webkitRequestFullscreen) {
	            img.webkitRequestFullscreen();                // Chrome & Safari
	        } else if (element.msRequestFullscreen) {
	            img.msRequestFullscreen();                    // IE
	        }
		}
		
		//setTimeout(function() { alert("Hello"); launchFullscreen2(); }, 1000);  // no action of fullscreen
		
	</script>
</head>
<body>

<h1>T00.index.jsp</h1><input id="button1" type="button" value="fullscreen" onclick="launchFullscreen2();">

<div id='page'><iframe name='ipage' src='Page000.jsp?addr=<%=addr%>&type=<%=type%>' style='border:0;width:100%;height:300px;padding:0;margin:0;'></iframe></div>
<div id='websocket'><iframe name='iwebsocket' src='WebSocket000.jsp?addr=<%=addr%>&type=<%=type%>' style='border:0;width:100%;height:50px;padding:0;margin:0;'></iframe></div>

</body>
</html>
