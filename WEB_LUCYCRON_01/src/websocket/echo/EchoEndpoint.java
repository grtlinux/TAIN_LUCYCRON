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

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EchoEndpoint.java
 *   -. Package    : websocket.echo
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class EchoEndpoint extends Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
		session.addMessageHandler(new EchoMessageHandlerText(remoteEndpointBasic));
		session.addMessageHandler(new EchoMessageHandlerBinary(remoteEndpointBasic));
	}
	
	private static class EchoMessageHandlerText implements MessageHandler.Partial<String> {
		
		private final RemoteEndpoint.Basic remoteEndpointBasic;
		
		private EchoMessageHandlerText(RemoteEndpoint.Basic remoteEndpointBasic) {
			this.remoteEndpointBasic = remoteEndpointBasic;
		}
		
		@Override
		public void onMessage(String message, boolean last) {
			try {
				if (this.remoteEndpointBasic != null) {
					this.remoteEndpointBasic.sendText(message, last);
				}
			} catch (IOException e) {
				// ignore
				e.printStackTrace();
			}
		}
	}
	
	private static class EchoMessageHandlerBinary implements MessageHandler.Partial<ByteBuffer> {
		
		private final RemoteEndpoint.Basic remoteEndpointBasic;
		
		private EchoMessageHandlerBinary(RemoteEndpoint.Basic remoteEndpointBasic) {
			this.remoteEndpointBasic = remoteEndpointBasic;
		}
		
		@Override
		public void onMessage(ByteBuffer message, boolean last) {
			try {
				if (this.remoteEndpointBasic != null) {
					this.remoteEndpointBasic.sendBinary(message, last);
				}
			} catch (IOException e) {
				// ignore
				e.printStackTrace();
			}
		}
	}
}
