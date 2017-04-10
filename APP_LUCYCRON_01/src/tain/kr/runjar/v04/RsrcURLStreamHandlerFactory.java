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
package tain.kr.runjar.v04;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : RsrcURLStreamHandlerFactory.java
 *   -. Package    : tain.kr.runjar.v04
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 3. 4. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class RsrcURLStreamHandlerFactory implements URLStreamHandlerFactory {

	private static boolean flag = true;

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ClassLoader classLoader = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public RsrcURLStreamHandlerFactory(ClassLoader classLoader) {
		
		this.classLoader = classLoader;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see java.net.URLStreamHandlerFactory#createURLStreamHandler(java.lang.String)
	 */
	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		
		if (!flag) System.out.printf("%s >>>>> protocol=%s\n", this.getClass().getName(), protocol);
		
		if ("rsrc".equals(protocol))
			return new RsrcURLStreamHandler(classLoader);
		
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
