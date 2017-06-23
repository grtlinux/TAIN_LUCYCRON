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
package ws;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : WsConfig.java
 *   -. Package    : ws
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class WsConfig implements ServerApplicationConfig {

	/* (non-Javadoc)
	 * @see javax.websocket.server.ServerApplicationConfig#getAnnotatedEndpointClasses(java.util.Set)
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		// Deploy all WebSocket endpoints defined by annotations in the examples
		// web application. Filter out all others to avoid issues when running
		// tests on Gump
		
		Set<Class<?>> results = new HashSet<Class<?>>();
		for (Class<?> clazz : scanned) {
			if (clazz.getPackage().getName().startsWith("ws.")) {
				results.add(clazz);
			}
		}
		
		return results;
	}

	/* (non-Javadoc)
	 * @see javax.websocket.server.ServerApplicationConfig#getEndpointConfigs(java.util.Set)
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

		Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();
		
		//if (scanned.contains(EchoEndpoint.class)) {
		//	result.add(ServerEndpointConfig.Builder.create(EchoEndpoint.class, "/websocket/echoProgrammatic").build());
		//}
		
		//if (scanned.contains(DrawboardEndpoint.class)) {
		//	result.add(ServerEndpointConfig.Builder.create(DrawboardEndpoint.class, "/websocket/drawboard").build());
		//}
		
		return result;
	}
}
