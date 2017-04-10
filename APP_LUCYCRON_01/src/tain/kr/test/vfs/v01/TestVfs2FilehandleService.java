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

import org.apache.commons.vfs2.CacheStrategy;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider;
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
	
	@Test
	public void testCaching() throws Exception {
		String path = TestVfs2FilehandleService.class.getResource("").getPath();
		if (flag) System.out.printf("[%s]\n", path);
		
		String testFolder = path + "/testfolder";
		FileSystemManager manager = VFS.getManager();
		
		FileObject scratchFolder = manager.resolveFile(testFolder);
		
		// testfolder 내의 모든 파일 삭제
		scratchFolder.delete(Selectors.EXCLUDE_SELF);
		
		FileObject file = manager.resolveFile(path +  "/testfolder/dummy.txt");
		file.createFile();
		
		// 캐싱 Manager 생성
		DefaultFileSystemManager fs = new DefaultFileSystemManager();
		fs.setFilesCache(manager.getFilesCache());
		
		// zip, jar, tgz, tar, tbz2, file
		if (!fs.hasProvider("file")) {
		fs.addProvider("file", new DefaultLocalFileProvider());
		}
		
		fs.setCacheStrategy(CacheStrategy.ON_RESOLVE);
		fs.init();
		
		// 캐싱 객체 생성
		FileObject foBase2 = fs.resolveFile(testFolder);
		if (flag) System.out.printf("## scratchFolder.getName().getPath() : %s\n", scratchFolder.getName().getPath());
		
		FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());
		
		// 파일이 존재하지 않음
		FileObject[] fos = cachedFolder.getChildren();
		assertFalse(contains(fos, "file1.txt"));
		
		// 파일생성
		scratchFolder.resolveFile("file1.txt").createFile();
		
		// 파일 존재함
		// BUT cachedFolder 에는 파일이 존재하지 않음
		fos = cachedFolder.getChildren();
		assertFalse(contains(fos, "file1.txt"));
		
		// 새로고침
		cachedFolder.refresh();
		// 파일이 존재함
		fos = cachedFolder.getChildren();
		assertTrue(contains(fos, "file1.txt"));
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
	
	private boolean contains(FileObject[] fos, String string) {
		for (int i = 0; i < fos.length; i++) {
			if (string.equals(fos[i].getName().getBaseName())) {
				if (flag) System.out.printf("# %s", string);
				return true;
			}
		}

		if (flag) System.out.printf("# %s should be seen", string);
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
}
