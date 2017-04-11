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

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ShowProperties.java
 *   -. Package    : tain.kr.test.vfs.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 12. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ShowProperties {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ShowProperties.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Maximum number of children to show. */
	private static final int SHOW_MAX = 5;

	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ShowProperties() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
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

		if (flag)
			new ShowProperties();

		if (flag) {

			if (args.length == 0)
			{
				System.err.println("Please pass the name of a file as parameter.");
				System.err.println("e.g. java org.apache.commons.vfs2.example.ShowProperties LICENSE.txt");
				return;
			}
			for (final String arg : args)
			{
				try
				{
					final FileSystemManager mgr = VFS.getManager();
					System.out.println();
					System.out.println("Parsing: " + arg);
					final FileObject file = mgr.resolveFile(arg);
					System.out.println("URL: " + file.getURL());
					System.out.println("getName(): " + file.getName());
					System.out.println("BaseName: " + file.getName().getBaseName());
					System.out.println("Extension: " + file.getName().getExtension());
					System.out.println("Path: " + file.getName().getPath());
					System.out.println("Scheme: " + file.getName().getScheme());
					System.out.println("URI: " + file.getName().getURI());
					System.out.println("Root URI: " + file.getName().getRootURI());
					System.out.println("Parent: " + file.getName().getParent());
					System.out.println("Type: " + file.getType());
					System.out.println("Exists: " + file.exists());
					System.out.println("Readable: " + file.isReadable());
					System.out.println("Writeable: " + file.isWriteable());
					System.out.println("Root path: " + file.getFileSystem().getRoot().getName().getPath());
					if (file.exists())
					{
						if (file.getType().equals(FileType.FILE))
						{
							System.out.println("Size: " + file.getContent().getSize() + " bytes");
						}
						else if (file.getType().equals(FileType.FOLDER) && file.isReadable())
						{
							final FileObject[] children = file.getChildren();
							System.out.println("Directory with " + children.length + " files");
							for (int iterChildren = 0; iterChildren < children.length; iterChildren++)
							{
								System.out.println("#" + iterChildren + ": " + children[iterChildren].getName());
								if (iterChildren > SHOW_MAX)
								{
									break;
								}
							}
						}
						System.out.println("Last modified: " + DateFormat.getInstance().format(
								new Date(file.getContent().getLastModifiedTime())));
					}
					else
					{
						System.out.println("The file does not exist");
					}
					file.close();
				}
				catch (final FileSystemException ex)
				{
					ex.printStackTrace();
				}
			}
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
