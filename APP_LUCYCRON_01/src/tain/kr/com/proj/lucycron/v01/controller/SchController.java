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
package tain.kr.com.proj.lucycron.v01.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : SchController.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.controller
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class SchController {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(SchController.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Map<String, SchRequestHandler> mapHandler = new HashMap<String, SchRequestHandler>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public SchController() {
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void addHandler(SchRequest request, SchRequestHandler handler) throws Exception {
		
		String key = request.getName();
		
		if (this.mapHandler.containsKey(key)) {
			throw new RuntimeException(String.format("a request handler has already been registered for request name [%s].", key));
		}
		
		this.mapHandler.put(key, handler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public SchRequestHandler getHandler(String key) throws Exception {
		
		if (!this.mapHandler.containsKey(key)) {
			throw new RuntimeException(String.format("Cannot find handler for request name [%s].", key));
		}
		
		return this.mapHandler.get(key);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public SchResponse getResponse(String key) throws Exception {
		
		return this.getHandler(key).process();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public Map<String, SchRequestHandler> getMapHandler() throws Exception {
		return this.mapHandler;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static SchController instance = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static synchronized SchController getInstance() throws Exception {
		
		if (SchController.instance == null) {
			SchController.instance = new SchController();
		}
		
		return SchController.instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * test
			 */
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (flag)
			test01(args);
	}
}
