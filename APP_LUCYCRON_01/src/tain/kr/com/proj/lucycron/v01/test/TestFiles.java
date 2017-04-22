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
package tain.kr.com.proj.lucycron.v01.test;

import java.io.File;

import org.apache.log4j.Logger;

import tain.kr.com.proj.lucycron.v01.util.Params;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestFiles.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.test
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class TestFiles {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TestFiles.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
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

		if (flag) {
			/*
			 * 1. String[] File.list()
			 */
			System.out.println("1. String[] File.list();");
			
			File file = null;
			String[] arrStrFileNames = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.tab.folder"));
				arrStrFileNames = file.list();
				
				for (String strFileName : arrStrFileNames) {
					System.out.printf("1> [%s]\n", strFileName);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
			
			System.out.println();
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (flag) test01(args);
	}
}
