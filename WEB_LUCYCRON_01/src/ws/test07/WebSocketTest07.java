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
package ws.test07;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

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
 *   -. FileName   : WebSocketTest07.java
 *   -. Package    : ws.test07
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint("/ws/test07")
public class WebSocketTest07 {

	private static final String PREFIX = "TEST07";
	private static final AtomicInteger connectionIDs = new AtomicInteger(0);
	private static final Set<WebSocketTest07> clients = new CopyOnWriteArraySet<WebSocketTest07>();
	
	private final String nickName;
	private Session session;
	private Properties prop;
	private String addr;
	private String type;
	
	public WebSocketTest07() {
		this.nickName = String.format("%s-%04d", PREFIX, connectionIDs.getAndIncrement());
	}
	
	@OnOpen
	public void onOpen(Session session) {
		this.prop = getProperties(session);
		this.addr = this.prop.getProperty("addr");
		this.type = this.prop.getProperty("type");
		
		System.out.printf("%s onOpen %s %s\n", this.nickName, this.addr, this.type);
		this.session = session;
		clients.add(this);
		//broadcast(String.format("* %s %s", this.nickName, "has joined."));
	}
	
	private Properties getProperties(Session session) {
		Properties prop = new Properties();
		String query = session.getQueryString();
		
		String[] articles = query.split("&");
		for (String article : articles) {
			String[] items = article.split("=");
			if (items.length != 2)
				continue;
			
			prop.put(items[0], items[1]);
		}
		
		//prop.list(System.out);
		
		return prop;
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.printf("%s onClose\n", this.nickName);
		clients.remove(this);
		//broadcast(String.format("* %s %s", this.nickName, "has disconnected."));
	}
	
	@OnError
	public void onError(Throwable throwable, Session session) {
		// System.out.printf("%s onError <-", this.nickName);
		System.out.printf("ERROR(%s): %s\n", this.nickName, throwable.toString());
		throwable.printStackTrace();
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		//String filteredMessage = String.format("%s: %s", this.nickName, HTMLFilter.filter(message.toString()));
		System.out.printf("%s onMessage <- [%s]\n", this.nickName, message);
		broadcast(message);
	}
	
	private static final Set<String> setTypes = new HashSet<String>();
	
	private static synchronized void broadcast(String message) {
		setTypes.clear();

		String[] items = message.trim().split("\\s*=\\s*");
		String[] types = items[0].split("\\s*,\\s*");
		for (String type : types) {
			setTypes.add(type);
		}
		//System.out.println(setTypes.toString());
		
		String msg = items[1];
		
		boolean flgSend = false;
		
		for (WebSocketTest07 client : clients) {
			try {
				if (setTypes.contains(client.type)) {
					System.out.printf("%s %s %s %s\n", client.nickName, client.addr, client.type, msg);
					client.session.getBasicRemote().sendText(msg);
					flgSend = true;
				}
			} catch (IOException e) {
				System.out.printf("ERROR: Failed to send message to client.\n", e);
				clients.remove(client);
				try {
					client.session.close();
				} catch (Exception e2) {
					// ignore
				}
				
				//broadcast(String.format("* %s %s", client.nickName, "has been disconnected."));
			}
		}
		
		if (!flgSend) {
			System.out.printf("########## NO SEND: %s\n", message);
		}
	}
}
