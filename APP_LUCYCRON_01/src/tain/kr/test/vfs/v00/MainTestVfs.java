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
package tain.kr.test.vfs.v00;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
 *   -. FileName   : MainTestVfs.java
 *   -. Package    : tain.kr.test.vfs.v00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 14. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainTestVfs {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestVfs.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestVfs() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * event listener
			 */
			FileSystemManager fileSystemManager = VFS.getManager();
			FileObject listenFolder = fileSystemManager.resolveFile("N:/tain/products/LucyCron/test");
			
			DefaultFileMonitor fileMonitor = new DefaultFileMonitor(new FolderListener());
			fileMonitor.addFile(listenFolder);
			fileMonitor.setRecursive(true);
			fileMonitor.start();
		}
		
		if (flag) {
			/*
			 * progress
			 */
			try {
				new Thread(new ThrProgress()).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class ThrProgress implements Runnable {
	
		private final static long SLEEP_LOOP = 60 * 1000;  // millisecond
		private final static long LOOP_VALUE = 24 * 60;
		
		@Override
		public void run() {
			if (flag) {
				String oldTime = "";
				String curTime = null;
				
				for (long i=0; ; i = ++i % LOOP_VALUE) {
					curTime = DateFormat.get();
					if (!curTime.equals(oldTime)) {
						if (flag) System.out.printf("[%s]\n", curTime);
						oldTime = curTime;
					}
					
					try { Thread.sleep(SLEEP_LOOP); } catch (InterruptedException e) {}
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final class FolderListener implements FileListener {
		
		@Override
		public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
			if (flag) System.out.printf("[%s] change the file [%s].\n"
					, DateFormat.get("HH:mm:ss.SSS")
					, fileChangeEvent.getFile().getName());
		}
		
		@Override
		public void fileCreated(FileChangeEvent fileChangeEvent) throws Exception {
			if (flag) System.out.printf("[%s] create the file [%s].\n"
					, DateFormat.get("HH:mm:ss.SSS")
					, fileChangeEvent.getFile().getName());
		}
		
		@Override
		public void fileDeleted(FileChangeEvent fileChangeEvent) throws Exception {
			if (flag) System.out.printf("[%s] delete the file [%s].\n"
					, DateFormat.get("HH:mm:ss.SSS")
					, fileChangeEvent.getFile().getName());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final static class DateFormat {
		
		private static String DEFAULT_FORMAT = "HH:mm";
		
		public final static String get() {
			return get(DEFAULT_FORMAT);
		}
		
		public final static String get(String format) {
			return new SimpleDateFormat(format, Locale.KOREA).format(new Date());
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

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * begin
			 */
			new MainTestVfs().execute();
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
