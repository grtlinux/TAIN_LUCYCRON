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
package ws.test02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : WebSocketTest02.java
 *   -. Package    : ws.test02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint("/ws/test02")
public class WebSocketTest02 {

	private final static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	@OnClose
	public void onClose() {
		
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		
	}
	
	public String buildJsonData(String userName, String message) {
		JsonObject jsonObject = Json.createObjectBuilder().add("message", userName + " : " + message).build();
	}
}
