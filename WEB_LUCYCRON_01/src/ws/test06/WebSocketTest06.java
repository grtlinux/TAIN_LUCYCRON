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
public class WebSocketTest06 {

	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	@OnClose
	public void onClose(Session session) {
		
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		
	}
}
