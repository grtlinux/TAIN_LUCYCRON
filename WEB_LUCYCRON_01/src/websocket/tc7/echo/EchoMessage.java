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
package websocket.tc7.echo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EchoMessage.java
 *   -. Package    : websocket.tc7.echo
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@Deprecated
public class EchoMessage extends WebSocketServlet {

	private static final long serialVersionUID = 1L;
	private volatile int byteBufSize;
	private volatile int charBufSize;
	
	@Override
	public void init() throws ServletException {
		super.init();
		byteBufSize = getInitParameterIntValue("byteBufferMaxSize", 2097152);
		charBufSize = getInitParameterIntValue("charBufferMaxSize", 2097152);
		
		System.out.printf("init(%d, %d)\n", byteBufSize, charBufSize);
	}
	
	public int getInitParameterIntValue(String name, int defaultValue) {
		String val = this.getInitParameter(name);
		int result;
		if (val != null) {
			try {
				result = Integer.parseInt(val);
			} catch (Exception e) {
				result = defaultValue;
			}
		} else {
			result = defaultValue;
		}
		
		return result;
	}
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		System.out.printf("createWebSocketInbound('%s', request)\n", subProtocol);
		return new EchoMessageInbound(byteBufSize, charBufSize);
	}
	
	private static final class EchoMessageInbound extends MessageInbound {
		
		public EchoMessageInbound(int byteBufferMaxSize, int charBufferMaxSize) {
			super();
			setByteBufferMaxSize(byteBufferMaxSize);
			setCharBufferMaxSize(charBufferMaxSize);
		}
		
		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException {
			System.out.printf("onBinaryMessage(%s)\n", message.toString());
			getWsOutbound().writeBinaryMessage(message);
		}
		
		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			System.out.printf("onTextMessage(%s)\n", message.toString());
			getWsOutbound().writeTextMessage(message);
		}
	}
}
