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
package ws.test03;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : WebSocketTest03.java
 *   -. Package    : ws.test03
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 24. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint(value="/ws/test03")
public class WebSocketTest03 {

	private final static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id: " + session.getId());
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId() + " has ended.");
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		e.printStackTrace();
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + ": " + message);
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to: " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sendMessageToAllSessions(session, message);
	}
	
	private void sendMessageToAllSessions(Session mySession, String message) {
		try {
			for (Session session : WebSocketTest03.sessions) {
				if (!mySession.getId().equals(session.getId()))
					session.getBasicRemote().sendText("All: " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
