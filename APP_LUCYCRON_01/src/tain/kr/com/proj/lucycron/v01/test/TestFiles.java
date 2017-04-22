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
import java.io.FileFilter;
import java.io.FilenameFilter;

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
				file = new File(Params.getInstance().getString("tain.lucy.tab.folder"));
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

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * static test method
	 */
	private static void test02(String[] args) throws Exception {

		if (flag) {
			/*
			 * 2. String[] File.list(FilenameFilter)
			 */
			System.out.println("2. String[] File.list(FilenameFilter);");
			
			File file = null;
			String[] arrStrFileNames = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.lucy.tab.folder"));
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
			
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * static test method
	 */
	private static void test03(String[] args) throws Exception {

		if (flag) {
			/*
			 * 3. File[] File.listFiles()
			 */
			System.out.println("3. File[] File.listFiles();");
			
			File file = null;
			File[] arrFiles = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.lucy.tab.folder"));
				arrFiles = file.listFiles();
				
				for (File f : arrFiles) {
					System.out.printf("3> [%s]\n", f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
			
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * static test method
	 */
	private static void test04(String[] args) throws Exception {

		if (flag) {
			/*
			 * 4. File[] File.listFiles(FileFilter)
			 */
			System.out.println("4. File[] File.listFiles(FileFilter);");
			
			File file = null;
			File[] arrFiles = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.lucy.tab.folder"));
				arrFiles = file.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						
						if (flag) return true;
						
						return false;
					}
				});
				
				for (File f : arrFiles) {
					System.out.printf("4> [%s]\n", f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
			
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * static test method
	 */
	private static void test05(String[] args) throws Exception {

		if (flag) {
			/*
			 * 5. File[] File.listFiles(FilenameFilter)
			 */
			System.out.println("5. File[] File.listFiles(FilenameFilter);");
			
			File file = null;
			File[] arrFiles = null;
			
			try {
				file = new File(Params.getInstance().getString("tain.lucy.tab.folder"));
				arrFiles = file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						
						if (flag) return true;
						
						return false;
					}
				});
				
				for (File f : arrFiles) {
					System.out.printf("5> [%s]\n", f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
			
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * static test method
	 */
	private static void test06(String[] args) throws Exception {

		if (flag) {
			/*
			 * 6. File[] File.listRoots()
			 */
			System.out.println("6. File[] File.listRoots();");
			
			File[] files = null;
			
			try {
				files = File.listRoots();
				
				for (File f : files) {
					System.out.printf("6> [%s]\n", f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {}
			
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (flag) test01(args);
		if (flag) test02(args);
		if (flag) test03(args);
		if (flag) test04(args);
		if (flag) test05(args);
		if (flag) test06(args);
	}
}
