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
package ws.test00;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import util.HTMLFilter;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : WebSocketTest00.java
 *   -. Package    : ws.test00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint(value = "/ws/test00")
public class WebSocketTest00 {

	private static final Log log = LogFactory.getLog(WebSocketTest00.class);
	
	private static final String GUEST_PREFIX = "TEST00";
	private static final AtomicInteger connectionIDs = new AtomicInteger(0);
	private static final Set<WebSocketTest00> connections = new CopyOnWriteArraySet<WebSocketTest00>();
	
	private final String nickName;
	private Session session;
	
	public WebSocketTest00() {
		this.nickName = String.format("%s-%03d", GUEST_PREFIX, connectionIDs.getAndIncrement());
	}
	
	@OnOpen
	public void start(Session session) {
		this.session = session;
		System.out.println(this.session.toString());
		System.out.println(this.session.getQueryString());
		
		Map<String,Object> map = this.session.getUserProperties();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = String.valueOf(entry.getValue());
			System.out.printf("[%s] = [%s]\n", key, val);
		}
		
		connections.add(this);
		String message = String.format("* %s %s", nickName, "has joined.");
		broadcast(message);
	}
	
	@OnClose
	public void end() {
		connections.remove(this);
		String message = String.format("* %s %s", nickName, "has disconnected.");
		broadcast(message);
	}
	
	@OnMessage
	public void incoming(String message) {
		// never trust the client
		String filteredMessage = String.format("%s: %s", nickName, HTMLFilter.filter(message.toString()));
		broadcast(filteredMessage);
	}
	
	@OnError
	public void onError(Throwable t) throws Throwable {
		log.error("Chat Error: " + t.toString(), t);
	}
	
	private static void broadcast(String msg) {
		for (WebSocketTest00 client : connections) {
			try {
				synchronized(client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				log.debug("Chat Error: Failed to send message to client", e);
				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e2) {
					// ignore
				}
				
				String message = String.format("* %s %s", client.nickName, "has been disconnected.");
				broadcast(message);
			}
		}
	}
}
