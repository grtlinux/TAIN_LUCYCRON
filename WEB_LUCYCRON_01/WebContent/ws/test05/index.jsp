<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String id = "";
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
	
	String nick = "";
	if (session.getAttribute("nick") != null) {
		nick = (String) session.getAttribute("nick");
	} else {
		nick = "NICK NULL";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<script type="text/javascript">
		var websocket = new WebSocket("ws://" + window.location.host + "/WEB_LUCYCRON_01/ws/test05");
		//var inputMessage = document.getElementById("inputMessage");
		var re_send = "";
		
		websocket.onopen = function(event) {
			onOpen(event);
		}
		
		websocket.onclose = function(event) {
			onClose(event);
		}
		
		websocket.onerror = function(event) {
			onError(event);
		}
		
		websocket.onmessage = function(event) {
			onMessage(event);
		}
		
		function onOpen(event) {
			var div = document.createElement("div");
			div.style["text-align"] = "center";
			div.innerHTML = "반갑습니다.";
			
			document.getElementById("messageWindow2").appendChild(div);
			
			var clear = document.createElement("div");
			clear.style["clear"] = "both";
			
			document.getElementById("messageWindow2").appendChild(clear);
			
			websocket.send("<%=nick%>|\|안녕하세요^^");
		}
		
		function onClose(event) {
			var div = document.createElement("div");
			
			websocket.send("<%=nick%> is disconnected\n");
		}
		
		function onError(event) {
			alert("chat_server connecting error: " + event.data);
		}
		
		function onMessage(event) {
			
			// 클라이언트에서 날아온 메시지를 |\| 단위로 분리한다.
			var message = event.data.split("|\|");
			
			if (message[0] != re_send) {
				// 금방 보낸 이름 re_send에 저장하고
				// 금방 보낸 이가 다시 보낼 경우 보낸이 출력 없도록 함.
				
				var who = document.createElement("div");
				who.style["padding"] = "3px";
				who.style["margin-left"] = "3px";
				who.innerHTML = message[0];      // nick
				
				document.getElementById("messageWindow2").appendChild(who);
				
				re_send = message[0];
			}
			
			// div 받은 메시지 출력
			var div = document.createElement("div");
			div.style["width"] = "auto";
			div.style["word-wrap"] = "break-word";
			div.style["display"] = "inline-block";
			div.style["background-color"] = "#fcfcfc";
			div.style["border-radius"] = "3px";
			div.style["padding"] = "3px";
			div.style["margin-left"] = "3px";
			div.innerHTML = message[1];
			
			document.getElementById("messageWindow2").appendChild(div);
			
			// clear div 추가 줄바꿈
			var clear = document.createElement("div");
			clear.style["clear"] = "both";
			
			document.getElementById("messageWindow2").appendChild(clear);
			
			// div 스크롤 아래로
			messageWindow2.scrollTop = messageWindow2.scrollHeight;
		}
		
		function send() {
			// inputMessage가 있을 때만 메세지를 전송한다.
			if (document.getElementById("inputMessage").value != "") {
				var message = document.getElementById("inputMessage").value;
				
				// 서버에 보내는 메시지
				websocket.send("<%=nick%>|\|" + message);
				
				// 채팅화면 div에 붙일 내용
				var div = document.createElement("div");
				div.style["width"] = "auto";
				div.style["word-wrap"] = "break-word";
				div.style["float"] = "right";
				div.style["display"] = "inline-block";
				div.style["background-color"] = "#ffea00";
				div.style["border-radius"] = "3px";
				div.style["padding"] = "3px";
				div.style["margin-left"] = "3px";
				div.innerHTML = message;
				
				document.getElementById("messageWindow2").appendChild(div);
				
				// clear 추가
				var clear = document.createElement("div");
				clear.style["clear"] = "both";
				
				document.getElementById("messageWindow2").appendChild(clear);

				// inputMessage value 값을 지운다.
				document.getElementById("inputMessage").value = "";
				
				// div 스크롤 아래로
				messageWindow2.scrollTop = messageWindow2.scrollHeight;
				
				// 금방 보낸 사람을 임시 저장한다.
				re_send = "<%=nick%>";
			}
		}
	</script>
</head>
<body>

	<!-- onkeydown을 통해서 Enter키로도 입력되도록 설정. -->
	<input id="inputMessage" type="text" onkeydown="if(event.keyCode == 13) {send();}" />
	<input type="submit" value="SEND" onclick="send();" />
	
	<div id='messageWindow2' style='padding:10px 0; height:20em; overflow:auto; background-color:#a0c0d7;'></div>

</body>
</html>
