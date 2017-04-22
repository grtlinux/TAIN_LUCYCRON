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
package tain.kr.com.proj.lucycron.v01.enums;


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileType.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.enums
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 22. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public enum FileType {

	FILE   ("File"  , "����"),
	FOLDER ("Folder", "����"),
	;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private final String strEngName;
	private final String strHanName;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	FileType(String strEngName, String strHanName) {
		this.strEngName = strEngName;
		this.strHanName = strHanName;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getEngName() {
		return this.strEngName;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getHanName() {
		return this.strHanName;
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
	
	public static void main(String[] args) throws Exception {
		
		for (FileType type : FileType.values()) {
			System.out.printf("[%s] [%s] [%s]\n", type, type.getEngName(), type.getHanName());
		}
	}
}