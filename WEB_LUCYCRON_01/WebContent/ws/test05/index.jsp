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
			div.innerHTML = "�ݰ����ϴ�.";
			
			document.getElementById("messageWindow2").appendChild(div);
			
			var clear = document.createElement("div");
			clear.style["clear"] = "both";
			
			document.getElementById("messageWindow2").appendChild(clear);
			
			websocket.send("<%=nick%>|\|�ȳ��ϼ���^^");
		}
		
		function onClose(event) {
			var div = document.createElement("div");
			
			websocket.send("<%=nick%> is disconnected\n");
		}
		
		function onError(event) {
			alert("chat_server connecting error: " + event.data);
		}
		
		function onMessage(event) {
			
			// Ŭ���̾�Ʈ���� ���ƿ� �޽����� |\| ������ �и��Ѵ�.
			var message = event.data.split("|\|");
			
			if (message[0] != re_send) {
				// �ݹ� ���� �̸� re_send�� �����ϰ�
				// �ݹ� ���� �̰� �ٽ� ���� ��� ������ ��� ������ ��.
				
				var who = document.createElement("div");
				who.style["padding"] = "3px";
				who.style["margin-left"] = "3px";
				who.innerHTML = message[0];      // nick
				
				document.getElementById("messageWindow2").appendChild(who);
				
				re_send = message[0];
			}
			
			// div ���� �޽��� ���
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
			
			// clear div �߰� �ٹٲ�
			var clear = document.createElement("div");
			clear.style["clear"] = "both";
			
			document.getElementById("messageWindow2").appendChild(clear);
			
			// div ��ũ�� �Ʒ���
			messageWindow2.scrollTop = messageWindow2.scrollHeight;
		}
		
		function send() {
			// inputMessage�� ���� ���� �޼����� �����Ѵ�.
			if (document.getElementById("inputMessage").value != "") {
				var message = document.getElementById("inputMessage").value;
				
				// ������ ������ �޽���
				websocket.send("<%=nick%>|\|" + message);
				
				// ä��ȭ�� div�� ���� ����
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
				
				// clear �߰�
				var clear = document.createElement("div");
				clear.style["clear"] = "both";
				
				document.getElementById("messageWindow2").appendChild(clear);

				// inputMessage value ���� �����.
				document.getElementById("inputMessage").value = "";
				
				// div ��ũ�� �Ʒ���
				messageWindow2.scrollTop = messageWindow2.scrollHeight;
				
				// �ݹ� ���� ����� �ӽ� �����Ѵ�.
				re_send = "<%=nick%>";
			}
		}
	</script>
</head>
<body>

	<!-- onkeydown�� ���ؼ� EnterŰ�ε� �Էµǵ��� ����. -->
	<input id="inputMessage" type="text" onkeydown="if(event.keyCode == 13) {send();}" />
	<input type="submit" value="SEND" onclick="send();" />
	
	<div id='messageWindow2' style='padding:10px 0; height:20em; overflow:auto; background-color:#a0c0d7;'></div>

</body>
</html>
