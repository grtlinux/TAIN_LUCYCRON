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
package ws.test06;

import java.io.IOException;
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
 *   -. FileName   : WebSocketTest06.java
 *   -. Package    : ws.test06
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint("/ws/test06")
public final class WebSocketTest06 {

	private final static  Log log = LogFactory.getLog(WebSocketTest06.class);
	
	private final static String GUEST_PREFIX = "TEST06";
	private final static AtomicInteger connectionIDs = new AtomicInteger(0);
	private final static Set<WebSocketTest06> connections = new CopyOnWriteArraySet<WebSocketTest06>();
	
	private final String nickName;
	private Session session;
	
	public WebSocketTest06() {
		log.info("INFO: constructor nickName: " + this.nickName);

		this.nickName = String.format("%s-%02d", GUEST_PREFIX, connectionIDs.getAndIncrement());
	}
	
	@OnOpen
	public void onOpen(Session session) {
		log.info("INFO: OnOpen nickName: " + this.nickName);

		this.session = session;
		connections.add(this);
		String message = String.format("* %s %s", this.nickName, "has joined.");
		broadcast(message);
	}
	
	@OnClose
	public void onClose(Session session) {
		log.info("INFO: OnClose nickName: " + this.nickName);

		connections.remove(this);
		String message = String.format("* %s %s", this.nickName, "has disconnected.");
		broadcast(message);
	}
	
	@OnError
	public void onError(Throwable e, Session session) throws Throwable {
		log.info("INFO: OnError nickName: " + this.nickName);

		log.error("ERROR: " + e.toString(), e);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("INFO: OnMessage nickName: " + this.nickName);
		
		String filteredMessage = String.format("%s: %s", this.nickName, HTMLFilter.filter(message.toString()));
		broadcast(filteredMessage);
	}
	
	private static void broadcast(String message) {
		synchronized (connections) {
			for (WebSocketTest06 client : connections) {
				try {
					client.session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					log.debug("ERROR: Failed to send message to client.", e);
					connections.remove(client);
					try {
						client.session.close();
					} catch (IOException e2) {
						// ignore
					}
				}
			}
		}
	}
}
