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
package util;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : HTMLFilter.java
 *   -. Package    : util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class HTMLFilter {

	public static String filter(String message) {
		
		if (message == null)
			return null;
		
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		
		for (int i=0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
				break;
			}
		}
		
		return result.toString();
	}
}
