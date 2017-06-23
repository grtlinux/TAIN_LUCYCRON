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
package ws.test04;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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
 *   -. FileName   : WebSocketTest04.java
 *   -. Package    : ws.test04
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 24. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@ServerEndpoint(value="/ws/test04")
public class WebSocketTest04 {

	private BufferedOutputStream bos;
	private String path = "N:\\";
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("WebSocket File Server Open.....");
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("WebSocket connection is closed.");
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		System.out.println("WebSocket error....");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		
		if (!"end".equals(message)) {
			String fileName = message.substring(message.indexOf(":") + 1);
			System.out.println("fileName: " + fileName);
			File file = new File(path + fileName);
			
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				bos.flush();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnMessage
	public void onMessage(ByteBuffer message, boolean last, Session session) {
		while (message.hasRemaining()) {
			try {
				bos.write(message.get());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
