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
package tain.kr.com.proj.lucycron.v00.test;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;

import tain.kr.com.proj.lucycron.v00.util.Params;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestMainFile.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.test
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TestMainFile {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TestMainFile.class);

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
		
		if (flag) {
			/*
			 * 2. String[] File.list(FilenameFilter)
			 */
			File file = null;
			String[] arrStrFileNames = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.tab.folder"));
				arrStrFileNames = file.list(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						
						if (flag) System.out.printf("2> [%s] [%s]\n", dir.getAbsolutePath(), name);
						if (flag) return true;
						
						return false;
					}
				});
				
				for (String strFileName : arrStrFileNames) {
					System.out.printf("2> [%s]\n", strFileName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
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
