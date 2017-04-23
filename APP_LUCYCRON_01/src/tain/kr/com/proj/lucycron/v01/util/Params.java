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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Params.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 15. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class Params {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Params.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String FILE_RESOURCES = "resources/lucycron";
	
	private final Map<String, String> env;
	private final Properties prop;
	private final ResourceBundle resourceBundle;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private Params() {
		
		this.env = System.getenv();
		this.prop = System.getProperties();
		this.resourceBundle = ResourceBundle.getBundle(FILE_RESOURCES);
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private String getStringFromEnv(String key) {
		return this.env.get(key);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String getStringFromSystem(String key) {
		return this.prop.getProperty(key);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String getStringFromResourceBundle(String key) {
		
		String strValue;
		
		try {
			strValue = this.resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			strValue = null;
		}
		
		return strValue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getString(String key) {
		
		String value;
		
		/*
		 * ENV
		 */
		value = this.getStringFromEnv(key);
		if (value != null)
			return value;
		
		/*
		 * System properties
		 */
		value = this.getStringFromSystem(key);
		if (value != null)
			return value;
		
		/*
		 * resources properties
		 */
		value = this.getStringFromResourceBundle(key);
		
		return value;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getString(String key, String defaultValue) {
		
		String value = this.getString(key);
		if (value != null)
			return value;
		
		return defaultValue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public String[] getEnv() {

		String[] arrEnv = null;
		
		if (flag) {
			/*
			 * System properties
			 */
			List<String> lstEnv = new ArrayList<String>();
			
			for (Map.Entry<String, String> entry : this.env.entrySet()) {
				String strKey = entry.getKey();
				String strVal = entry.getValue();
				
				if (!flag) System.out.printf("env [%s] = [%s]\n", strKey, strVal);
				
				lstEnv.add(String.format("%s=%s", strKey, strVal));
			}
		}
		
		return arrEnv;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void printAll() {
		
		if (flag) {
			/*
			 * System properties
			 */
			if (flag) System.out.println("########### System env ##########");
			
			for (Map.Entry<String, String> entry : this.env.entrySet()) {
				String strKey = entry.getKey();
				String strVal = entry.getValue();
				
				System.out.printf("env [%s] = [%s]\n", strKey, strVal);
			}
		}
		
		if (flag) {
			/*
			 * System properties
			 */
			if (flag) System.out.println("########### System properties ##########");
			
			this.prop.list(System.out);
		}
		
		if (flag) {
			/*
			 * Resource properties
			 */
			if (flag) System.out.println("########### resources/resources.properties ##########");

			Enumeration<String> enumKeys = this.resourceBundle.getKeys();
			while (enumKeys.hasMoreElements()) {
				String strKey = (String) enumKeys.nextElement();
				String strVal = (String) this.resourceBundle.getString(strKey);
				
				System.out.printf("resources [%s] = [%s]\n", strKey, strVal);
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
	
	private static Params instance = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static synchronized Params getInstance() {
		
		if (Params.instance == null) {
			Params.instance = new Params();
		}
		
		return Params.instance;
	}
	
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
			Params.getInstance().printAll();
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
