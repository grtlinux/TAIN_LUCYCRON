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
package tain.kr.test.vfs.v01;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTestVfs03.java
 *   -. Package    : tain.kr.test.vfs.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 24. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainTestVfs03 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestVfs03.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestVfs03() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * progress
			 */
		}
		
		if (flag) {
			/*
			 * event listener
			 */
			try {
				FileSystemManager fileSystemManager = VFS.getManager();
				FileObject listenFolder = fileSystemManager.resolveFile("N:/tain/products/LucyCron/test");
				
				DefaultFileMonitor fileMonitor = new DefaultFileMonitor(new FileListener() {
					@Override
					public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
					}
					@Override
					public void fileCreated(FileChangeEvent fileChangeEvent) throws Exception {
					}
					@Override
					public void fileDeleted(FileChangeEvent fileChangeEvent) throws Exception {
					}
				});
				
				fileMonitor.addFile(listenFolder);
				fileMonitor.setRecursive(true);
				fileMonitor.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	///////////////////////////////////////////////////////////////////////////////////////////////
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
			new MainTestVfs03().execute();
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
