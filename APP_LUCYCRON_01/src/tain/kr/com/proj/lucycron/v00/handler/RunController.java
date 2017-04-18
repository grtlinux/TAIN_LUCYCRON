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
package tain.kr.com.proj.lucycron.v00.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : RunController.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.handler
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 18. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class RunController implements ImpController {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(RunController.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Map<String, ImpRequestHandler> map = new HashMap<String, ImpRequestHandler>();
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public RunController() {
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void addHandler(ImpRequest request, ImpRequestHandler handler) {
		this.map.put(request.getName(), handler);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ImpRequestHandler getHandler(ImpRequest request) {
		return this.map.get(request.getName());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public ImpResponse getResponse(ImpRequest request) {
		ImpResponse response = null;
		
		try {
			response = this.map.get(request.getName()).process(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new RunController();

		if (flag) {

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
