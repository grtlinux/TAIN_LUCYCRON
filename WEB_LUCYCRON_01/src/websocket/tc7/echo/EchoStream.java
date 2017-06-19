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
import java.io.InputStream;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : EchoStream.java
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
public class EchoStream extends WebSocketServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		return new EchoStreamInbound();
	}
	
	private static final class EchoStreamInbound extends StreamInbound {
		
		@Override
		protected void onBinaryData(InputStream is) throws IOException {
			// Simply echo the data to back to the client
			WsOutbound outbound = getWsOutbound();
			
			int i = is.read();
			while (i != -1) {
				outbound.writeBinaryData(i);
				i = is.read();
			}
			
			outbound.flush();
		}
		
		@Override
		protected void onTextData(Reader reader) throws IOException {
			// Simply echo the data to back to the client
			WsOutbound outbound = getWsOutbound();
			
			int c = reader.read();
			while (c != -1) {
				outbound.writeTextData((char) c);
				c = reader.read();
			}
			
			outbound.flush();
		}
	}
}
