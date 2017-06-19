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
package websocket.tc7.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import util.HTMLFilter;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ChatWebSocketServlet.java
 *   -. Package    : websocket.tc7.chat
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@Deprecated
public class ChatWebSocketServlet extends WebSocketServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String GUEST_PREFIX = "Guest";
	
	private final AtomicInteger connectionIds = new AtomicInteger(0);
	private final Set<ChatMessageInbound> connections = new CopyOnWriteArraySet<ChatMessageInbound>();
	
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		return new ChatMessageInbound(connectionIds.incrementAndGet());
	}
	
	private final class ChatMessageInbound extends MessageInbound {
		
		private final String nickName;
		
		private ChatMessageInbound(int id) {
			this.nickName = String.format("%s-%03d", GUEST_PREFIX, id);
		}
		
		@Override
		protected void onOpen(WsOutbound outbound) {
			connections.add(this);
			String message = String.format("* %s %s", nickName, "has joined...");
			broadcast(message);
		}
		
		@Override
		protected void onClose(int status) {
			connections.remove(this);
			String message = String.format("* %s %s", nickName, "has disconnected.");
			broadcast(message);
		}
		
		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException {
			throw new UnsupportedOperationException("Binary message not supported.");
		}
		
		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			// never trust the client
			String filteredMessage = String.format("%s: %s", nickName, HTMLFilter.filter(message.toString()));
			broadcast(filteredMessage);
		}
		
		private void broadcast(String message) {
			CharBuffer buffer = CharBuffer.wrap(message);

			for (ChatMessageInbound connection : connections) {
				try {
					connection.getWsOutbound().writeTextMessage(buffer);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
