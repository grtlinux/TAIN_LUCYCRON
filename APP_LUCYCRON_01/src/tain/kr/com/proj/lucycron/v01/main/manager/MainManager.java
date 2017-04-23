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
package tain.kr.com.proj.lucycron.v01.main.manager;

import java.io.File;
import java.io.FileFilter;

import org.apache.log4j.Logger;

import tain.kr.com.proj.lucycron.v01.controller.SchController;
import tain.kr.com.proj.lucycron.v01.controller.SchRequest;
import tain.kr.com.proj.lucycron.v01.controller.SchRequestHandler;
import tain.kr.com.proj.lucycron.v01.enums.OsType;
import tain.kr.com.proj.lucycron.v01.util.CheckSystem;
import tain.kr.com.proj.lucycron.v01.util.Params;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainManager.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.main.manager
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 22. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainManager {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainManager.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String strOsName;
	private boolean isWindows;
	private String strExtName;
	private String strFolder;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainManager() {
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void manage() throws Exception {
		
		if (flag) log.debug("##### start manager.manage() #####");

		if (flag) {
			/*
			 * initialize
			 */
			initialize();
		}
		
		if (flag) {
			/*
			 * loadSchController()
			 */
			loadSchController();
		}
		
		if (flag) {
			/*
			 * preLoadSchInfoToDb
			 *   1. startDb
			 */
			preLoadSchInfoToDb();
		}
		
		if (flag) {
			/*
			 * loadSchInfoToDb
			 */
		}
		
		if (flag) {
			/*
			 * running Thread
			 */
			
			if (flag) {
				/*
				 * ThrSchListenerToDb
				 */
			}
			
			if (flag) {
				/*
				 * ThrRunSchInfoFromDb
				 */
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void initialize() throws Exception {
		
		if (flag) {
			this.strOsName = CheckSystem.getInstance().getOsName();
			this.isWindows = CheckSystem.getInstance().isWindows();
			if (this.isWindows) {
				this.strExtName = OsType.WINDOWS.getExtName();
			} else {
				this.strExtName = OsType.LINUX.getExtName();
			}
			
			if (flag) log.debug(String.format("[strOsName] = [%s][%s], [isWindows] = [%s]"
					, this.strOsName, this.strExtName, this.isWindows));
		}

		if (flag) {
			this.strFolder = Params.getInstance().getString("tain.lucy.tab.folder");
			if (flag) log.debug(String.format("[strFolder] = [%s]", this.strFolder));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void loadSchController() throws Exception {
		
		File[] arrFiles = null;

		if (flag) {
			/*
			 * read Files on the folder of tain.lucy.tab.folder parameter value
			 */
			File folder = null;

			try {
				folder = new File(this.strFolder);
				arrFiles = folder.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						
						if (!flag) {
							/*
							 * print File method for checking
							 */
							try {
								System.out.printf("file.getAbsolutePath()  = [%s]\n", file.getAbsolutePath());
								System.out.printf("file.getCanonicalPath() = [%s]\n", file.getCanonicalPath());   // <- getAbsolutePath
								System.out.printf("file.getName()          = [%s]\n", file.getName());            // <- fileName
								System.out.printf("file.getParent()        = [%s]\n", file.getParent());          // <- path contains fileName
								System.out.printf("file.getPath()          = [%s]\n", file.getPath());            // <- be equals to toString if fileName
								System.out.printf("file.toString()         = [%s]\n", file.toString());
								System.out.printf("file                    = [%s]\n", file);                      // <- toString
								System.out.printf("file.getName().indexOf(.) = [%d]\n", file.getName().indexOf('.'));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						if (file.isDirectory()) {
							/*
							 * if directory, return false
							 */
							return false;
						}

						int idx = file.getName().indexOf('.');
						if (idx <= 0) {
							/*
							 * no extension of SCHID file
							 */
							return false;
						}
						
						if (flag) {
							/*
							 * check extension of SCHID file
							 */
							String extension = file.getName().substring(idx+1);
							if (!extension.equals(strExtName))
								return false;
						}

						if (flag && !file.getName().startsWith("START"))
							return false;
						
						return true;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}

			if (!flag) {
				/*
				 * print the result of above processing
				 */
				for (File file : arrFiles) {
					if (flag) log.debug(String.format("[%s]\n", file.getName()));
				}
			}
		}
		
		if (flag) {
			/*
			 * create request handler
			 */
			for (File file : arrFiles) {
				if (flag) log.debug(String.format("[%s]", file.getName()));
				
				SchRequest request = new SchRequest(file.getName());
				SchRequestHandler handler = new SchRequestHandler(request);
				handler.readSchFile();    // read scheduler file content
				
				SchController.getInstance().addHandler(request, handler);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void preLoadSchInfoToDb() throws Exception {
		
		if (flag) {
			/*
			 * run the schedule of the parameter tain.lucy.start.prejob
			 */
			String key = "tain.lucy.start.prejob";
			String[] arrPreSchId = Params.getInstance().getString(key).split("\\s+");
			
			for (String preSchId : arrPreSchId) {
				if (flag) log.debug(String.format("[preSchId] = [%s]", preSchId));
				
				SchRequestHandler handler = SchController.getInstance().getHandler(preSchId);
				handler.runSchInfo();
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
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static MainManager instance = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static synchronized MainManager getInstance() throws Exception {
		
		if (MainManager.instance == null) {
			MainManager.instance = new MainManager();
		}
		
		return MainManager.instance;
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
			MainManager.getInstance().manage();
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
