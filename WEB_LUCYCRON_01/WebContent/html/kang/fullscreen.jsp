<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>fullscreen.jsp</title>
	
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
		
	</script>
</head>
<body>
<h1>index.jsp : <%=request.getRemoteAddr() %></h1>
<br>

<button onclick="launchFullscreen2();">Full Screen</button>
<button onclick="exitFullscreen();">Exit Full Screen</button>
<br>

<img src="/WEB_LUCYCRON_01/image/imagemap.jpg" onclick="launchFullscreen(document.documentElement);" id="f-img" />

</body>
</html>