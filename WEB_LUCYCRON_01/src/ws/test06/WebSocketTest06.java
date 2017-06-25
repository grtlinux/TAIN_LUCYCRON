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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	// private final static Set<WebSocketTest06> connections = new CopyOnWriteArraySet<WebSocketTest06>();
	private final static Set<TypeBean> typeBeans = new CopyOnWriteArraySet<TypeBean>();
	
	private final String nickName;
	private Session session;
	private TypeBean bean;
	
	public WebSocketTest06() {
		this.nickName = String.format("%s-%02d", GUEST_PREFIX, connectionIDs.getAndIncrement());
		System.out.printf("INFO: constructor nickName: %s\n", this.nickName);
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("INFO: OnOpen nickName: %s\n", this.nickName);
		
		Properties prop = getProperties(session.getQueryString());
		String ip = prop.getProperty("ip");
		if (ip == null) {
			try {
				session.close();
			} catch (IOException e) {
				// ignore
			}
			
			return;
		}
		
		String type = mapType.get(ip);
		if (type == null) {
			try {
				session.close();
			} catch (IOException e) {
				// ignore
			}
			
			return;
		}
		
		System.out.printf("INFO: OnOpen nickName: %s %s %s\n", this.nickName, ip, type);

		this.session = session;
		this.bean = new TypeBean(ip, type, this);
		
		typeBeans.add(this.bean);
		//connections.add(this);
		
		String message = String.format("* %s %s", this.nickName, "has joined.");
		broadcast(message);
	}
	
	private Properties getProperties(String query) {
		//System.out.println("[" + query + "]");
		if (query == null)
			return null;
		
		Properties prop = new Properties();
		
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
		System.out.printf("INFO: OnClose nickName: %s\n", this.nickName);

		typeBeans.remove(this.bean);
		//connections.remove(this);
		String message = String.format("* %s %s", this.nickName, "has disconnected.");
		broadcast(message);
	}
	
	@OnError
	public void onError(Throwable e, Session session) throws Throwable {
		System.out.printf("INFO: OnError nickName: %s\n", this.nickName);

		log.error("ERROR: " + e.toString(), e);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.printf("INFO: OnMessage nickName: %s\n", this.nickName);
		
		String filteredMessage = String.format("%s: %s", this.nickName, HTMLFilter.filter(message.toString()));
		broadcast(filteredMessage);
	}
	
	private static void broadcast(String message) {
		//synchronized (connections) {
		synchronized (typeBeans) {
			for (TypeBean client : typeBeans) {
				try {
					if (setType.contains(client.getType())) {
						client.getObj().session.getBasicRemote().sendText(message);
					}
				} catch (IOException e) {
					log.debug("ERROR: Failed to send message to client.", e);
					typeBeans.remove(client);
					try {
						client.getObj().session.close();
					} catch (IOException e2) {
						// ignore
					}
				}
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	private static Map<String, String> mapType = null;
	private static Set<String> setType = null;
	
	static {
		mapType = new HashMap<String, String>();
		mapType.put("192.168.1.11",  "T00");
		
		mapType.put("192.168.1.15",  "T01");
		
		mapType.put("192.168.1.20",  "T02");
		mapType.put("192.168.1.112", "T02");
		
		//mapType.put("192.168.1.11", "T00");
		//mapType.put("192.168.1.11", "T00");
		//mapType.put("192.168.1.11", "T00");
		
		setType = new HashSet<String>();
		setType.add("T00");
		//setType.add("T01");
		setType.add("T02");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	private class TypeBean {
		private String ip;
		private String type;
		private WebSocketTest06 obj;

		public TypeBean(String ip, String type, WebSocketTest06 obj) {
			this.ip = ip;
			this.type = type;
			this.obj = obj;
		}
		
		public String getIp() {
			return this.ip;
		}

		public String getType() {
			return this.type;
		}

		public WebSocketTest06 getObj() {
			return this.obj;
		}
		
		public String toString() {
			return String.format("[%s-%s]", this.getIp(), this.getType());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

}
