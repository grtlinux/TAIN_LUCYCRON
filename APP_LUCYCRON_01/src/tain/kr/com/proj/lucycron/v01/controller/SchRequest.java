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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import tain.kr.com.proj.lucycron.v01.util.Params;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : SchRequest.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.controller
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class SchRequest {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(SchRequest.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final String tabFolder;
	private final String name;
	
	private final List<String> lstLine;
	
	private final List<String> lstCwd;
	private final List<String> lstCmd;
	private final List<String> lstEnv;
	private final Set<String> setHHMM;
	private final List<String> lstProp;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public SchRequest(String name) {
		
		this.name = name;
		this.tabFolder = Params.getInstance().getString("tain.lucy.tab.folder");
	
		this.lstLine = new ArrayList<String>();
	
		this.lstCwd = new ArrayList<String>();
		this.lstCmd = new ArrayList<String>();
		this.lstEnv = new ArrayList<String>();
		this.setHHMM = new TreeSet<String>();
		this.lstProp = new ArrayList<String>();
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getName() {
		return this.name;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void readSchFile() throws Exception {
		
		if (flag) {
			/*
			 * reading scheduler file
			 */
			BufferedReader reader = null;
			
			try {
				String fileName = this.tabFolder + File.separator + this.name;
				reader = new BufferedReader(new FileReader(fileName));
				
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.split("#", 2)[0].trim();
					if ("".equals(line))
						continue;
					
					if (!flag) System.out.printf("[%s]\n", line);
					
					this.lstLine.add(line);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) try { reader.close(); } catch (IOException e) {}
			}
		}
		
		if (flag) {
			/*
			 * loadSchVariables
			 */
			Set<Integer> setHour = new TreeSet<Integer>();
			Set<Integer> setMin = new TreeSet<Integer>();

			for (String line : lstLine) {
				
				String[] item = line.split(":=");
				if (item.length != 2) {
					throw new RuntimeException(String.format("wrong schedule file [%s].", this.getName()));
				}
				
				item[0].trim();
				
				switch (item[0].trim().toUpperCase()) {
				case "CWD" : this.lstCwd.add(item[1].trim()); break;
				case "CMD" : this.lstCmd.add(item[1].trim()); break;
				case "ENV" : this.lstEnv.add(item[1].trim()); break;
				case "PROPERTIES": this.lstProp.add(item[1].trim()); break;
				case "HHMM": {
					/*
					 * analyze hhmm parameter for schedule condition
					 */
					String[] arrHhmm = item[1].trim().split("\\s+");  // be separated by spaces
					
					for (String hhmm : arrHhmm) {
						String[] tm = hhmm.split(":");  // be separated into hour and min
						if (tm.length != 2) {
							throw new RuntimeException(String.format("wrong schedule file [%s].", this.getName()));
						}
						
						setHour.clear();    // hour set to clear
						if (flag) {
							/*
							 * analyze hour
							 */
							if ("*".equals(tm[0].trim())) {
								for (int hour=0; hour < 24; hour++) {    // all hour
									setHour.add(hour);
								}
							} else {
								String[] arrHour = tm[0].split(",");      // partial
								
								for (String hour : arrHour) {
									String[] arrH = hour.split("~");      // sequence
									if (arrH.length > 2) {
										throw new RuntimeException(String.format("wrong schedule file [%s].", this.getName()));
									}
									
									if (arrH.length == 1) {
										setHour.add(Integer.parseInt(arrH[0]));
									} else {
										int startHour = Integer.parseInt(arrH[0]);
										int endHour = Integer.parseInt(arrH[1]);
										for (int i=startHour; i < 24 && i <= endHour; i++) {
											setHour.add(i);
										}
									}
								}
							}
						}
						
						setMin.clear();    // min set to clear
						if (flag) {
							/*
							 * analyze min
							 */
							if ("*".equals(tm[1])) {
								for (int min=0; min < 60; min++) {    // all min
									setMin.add(min);
								}
							} else {
								String[] arrMin = tm[1].split(",");    // partial
								for (String min : arrMin) {
									String[] arrM = min.split("~");    // sequence
									if (arrM.length > 2) {
										throw new RuntimeException(String.format("wrong schedule file [%s].", this.getName()));
									}
									
									if (arrM.length == 1) {
										setMin.add(Integer.parseInt(arrM[0]));
									} else {
										int startMin = Integer.parseInt(arrM[0]);
										int endMin = Integer.parseInt(arrM[1]);
										for (int i=startMin; i < 60 && i <= endMin; i++) {
											setMin.add(i);
										}
									}
								}
							}
						}

						for (Integer hour : setHour) {
							for (Integer min : setMin) {
								this.setHHMM.add(String.format("%02d:%02d", hour, min));
							}
						}
					}
					break;
				}
				default: break;
				}  // end of switch
			}
			
			if (flag) {
				/*
				 * print data for checking
				 */
				if (flag) log.debug(String.format("========== START of [ %s ] ==========\n"
						+ "\tlstCwd  = %s\n"
						+ "\tlstCmd  = %s\n"
						+ "\tlstEnv  = %s\n"
						+ "\tlstProp = %s\n"
						+ "\tsetHHMM = %s\n"
						+ "\t========== END of [ %s ] ==========\n"
						, this.getName()
						, this.lstCwd
						, this.lstCmd
						, this.lstEnv
						, this.lstProp
						, this.setHHMM
						, this.getName()
						));
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void runSchInfo() throws Exception {
		
		if (flag) {
			/*
			 * run schedule
			 */
			
			
			
			
			
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
