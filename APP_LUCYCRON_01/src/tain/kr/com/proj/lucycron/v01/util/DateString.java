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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : DateFormat.java
 *   -. Package    : tain.kr.com.proj.lucycron.v00.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 17. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class DateString {

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public final static String get(String format, Date date) {
		return new SimpleDateFormat(format, Locale.KOREA).format(date);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public final static String get(String format) {
		return get(format, new Date());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static String DEFAULT_FORMAT = "HH:mm";
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public final static String get(Date date) {
		return get(DEFAULT_FORMAT, date);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public final static String get() {
		return get(DEFAULT_FORMAT, new Date());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public final static String parse(String string) {
		return null;
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
}
