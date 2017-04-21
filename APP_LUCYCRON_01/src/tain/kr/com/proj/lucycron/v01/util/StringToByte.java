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

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : StringToByte.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 18. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class StringToByte {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(StringToByte.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private StringToByte() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final static String CHARSET = "EUC-KR";
	private final static int LINE_SIZE = 16;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public final static void printBytes(String str) {
		
		if (flag) {
			byte[] byt = str.getBytes(Charset.forName(CHARSET));
			
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			
			int i = 0;
			for (i=0; i < byt.length; i++) {
				sb1.append(showByte(byt[i]));
				sb2.append(String.format("%02X ", byt[i])); 
				
				if (i % LINE_SIZE == LINE_SIZE-1) {
					System.out.printf("%s %s\n", sb1.toString(), sb2.toString());
					sb1 = new StringBuffer();
					sb2 = new StringBuffer();
				}
			}
			
			if (i % LINE_SIZE != LINE_SIZE-1) {
				System.out.printf("%-16s %s\n", sb1.toString(), sb2.toString());
			}
		}
	}
	
	private final static char showByte(byte byt) {
		
		if ('0' <= byt && byt <= '9')
			return (char) byt;
		
		if ('A' <= byt && byt <= 'Z')
			return (char) byt;
		
		if ('a' <= byt && byt <= 'z')
			return (char) byt;
		
		return (char) '.';
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

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * begin
			 */
			String str = "123abcABC°¡³ª´Ù \t\b\n\r";
			System.out.println("[" + str + "]");
			StringToByte.printBytes(str);
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
