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
package websocket.echo;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EchoAnnotation.java
 *   -. Package    : websocket.echo
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint("/websocket/echoAnnotation")
public class EchoAnnotation {

	@OnMessage
	public void echoTextMessage(Session session, String msg, boolean last) {
		try {
			if (session.isOpen()) {
				session.getBasicRemote().sendText(msg, last);
			}
		} catch (IOException e) {
			// TODO: handle exception
			try {
				session.close();
			} catch (IOException e2) {
				// ignore
				e2.printStackTrace();
			}
		}
	}
	
	@OnMessage
	public void echoBinaryMessage(Session session, ByteBuffer buffer, boolean last) {
		try {
			if (session.isOpen()) {
				session.getBasicRemote().sendBinary(buffer, last);
			}
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e2) {
				// ignore
				e2.printStackTrace();
			}
		}
	}
	
	@OnMessage
	public void echoPongMessage(PongMessage pongMessage) {
		// no operation
	}
}
