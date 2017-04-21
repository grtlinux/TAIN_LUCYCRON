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
package tain.kr.com.proj.lucycron.v01.util;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : CheckSystem.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 18. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class CheckSystem {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(CheckSystem.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String OS_NAME = "os.name";
	private static final String LINE_SEPARATOR = "line.separator";
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private CheckSystem() {
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getOsName() {
		return Params.getInstance().getString(OS_NAME);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getLineSeparator() {
		return Params.getInstance().getString(LINE_SEPARATOR, "\n");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isWindows() {
		
		if (this.getOsName().indexOf("Win", 0) >= 0) {
			return true;
		}
		
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isLinux() {
		return isWindows() ? false : true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static CheckSystem instance = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static synchronized CheckSystem getInstance() {
		
		if (CheckSystem.instance == null) {
			CheckSystem.instance = new CheckSystem();
		}
		
		return CheckSystem.instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * begin
			 */
			log.debug(">>>>> " + CheckSystem.getInstance().getOsName());
			log.debug(">>>>> [" + CheckSystem.getInstance().getLineSeparator() + "]");
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
