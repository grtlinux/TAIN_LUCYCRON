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
package ws.test05;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
 *   -. FileName   : WebSocketTest05.java
 *   -. Package    : ws.test05
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 24. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint("/ws/test05")
public class WebSocketTest05 {
	
	private final static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("OnOpen: " + session);
		clients.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("OnClose: " + session);
		clients.remove(session);
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		e.printStackTrace();
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("OnMessage: " + message);
		
		try {
			synchronized (clients) {
				for (Session client : clients) {
					if (!client.equals(session))
						client.getBasicRemote().sendText(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
