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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestVfs2FilehandleService.java
 *   -. Package    : tain.kr.test.vfs.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 11. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class TestVfs2FilehandleService {

	private static boolean flag = true;

	private static final Logger log = Logger
			.getLogger(TestVfs2FilehandleService.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String filename;
	private String text;
	private String tmppath;
	private String absoluteFilePath;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Before
	public void onSetUp() throws Exception {
		
		this.filename = "test.txt";
		this.text = "test입니다.";
		this.tmppath = "tmp";
		this.absoluteFilePath = "N:/tain/products/LucyCron/test";
		
		if (flag) System.out.printf("[user.dir] = [%s]\n", System.getProperty("user.dir"));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testCreateFile() throws Exception {
		
		FileSystemManager manager = VFS.getManager();
		
		FileObject baseDir = manager.resolveFile(this.absoluteFilePath);
		final FileObject file = manager.resolveFile(baseDir, "testfolder/file1.txt");
		
		// delete a file
		file.delete(Selectors.SELECT_FILES);
		assertFalse(file.exists());
		
		// create a file
		file.createFile();
		assertTrue(file.exists());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testAccessFile() throws Exception  {
		
		FileSystemManager manager = VFS.getManager();

		FileObject baseDir = manager.resolveFile(this.absoluteFilePath);
		FileObject file = manager.resolveFile(baseDir, "testfolder/file1.txt");

		// 모든 파일 삭제
		file.delete(Selectors.SELECT_FILES);
		assertFalse(file.exists());

		// 파일 생성
		file.createFile();
		assertTrue(file.exists());

		FileContent fileContent = file.getContent();
		assertEquals(0, fileContent.getSize());

		// 파일 쓰기
		String string = "test입니다.";
		OutputStream os = fileContent.getOutputStream();

		try {
			os.write(string.getBytes());
			os.flush();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception ignore) {
					// no-op
				}
			}
		}
		assertNotSame(0, fileContent.getSize());

		// 파일 읽기
		StringBuffer sb = new StringBuffer();
		FileObject writtenFile = manager.resolveFile(baseDir, "testfolder/file1.txt");
		FileContent writtenContents = writtenFile.getContent();
		InputStream is = writtenContents.getInputStream();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
					// no-op
				}
			}
		}

		// 파일내용 검증
		assertEquals(sb.toString(), string);
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
}
