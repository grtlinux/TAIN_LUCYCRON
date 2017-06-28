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

import java.util.HashMap;
import java.util.Map;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Addr2Type.java
 *   -. Package    : ws.test07
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 28. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class Addr2Type {
	
	private Map<String, String> map = null;
	
	////////////////////////////////////////////////////////////////////////////////////
	
	private Addr2Type() {
	
		this.map = new HashMap<String, String>();
		
		this.map.put("192.168.1.11",  "T00");
		
		this.map.put("192.168.1.15",  "T01");
		
		this.map.put("192.168.1.20",  "T02");
		this.map.put("192.168.1.112", "T02");
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	public String getType(String addr) {
		String type = null;
		
		type = this.map.get(addr);
		
		return type;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	private static Addr2Type instance = null;
	
	public static synchronized Addr2Type getInstance() throws Exception {
		
		if (instance == null) {
			instance = new Addr2Type();
		}
		
		return instance;
	}
}
