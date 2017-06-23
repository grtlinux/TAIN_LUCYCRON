/**
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc.
 *
 */
package ws.test01;

import java.net.URLDecoder;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : WebSocketTest01.java
 *   -. Package    : ws.test01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
//@ServerEndpoint("/ws/test01")
@ServerEndpoint(value = "/ws/test01")
public class WebSocketTest01 {

	private String nickName;
	
	@OnOpen
	public void onOpen(Session session) {
		String queryString = session.getQueryString();
		System.out.println(queryString);
		this.nickName = decoder(queryString);
		System.out.printf("* 클라이언트가 접속함: %s\n", this.nickName);
	}
	
	@OnClose
	public void onClose() {
		System.out.printf("* 클라이언트가 접속 종료\n");
	}
	
	@OnMessage
	public String onMessage(String message) {
		System.out.printf("* 클라언트로부터 메시지: %s\n", message);
		String serverMsg = String.format("[%s] %s\n", this.nickName, message);
		return serverMsg;
	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
	public String decoder(String string) {
		String result = "";
		
		try {
			result = URLDecoder.decode(string, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

