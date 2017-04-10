

/*
 * Copyright 2002-2008 MOPAS(Ministry of Public Administration and Security). 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package egovframework.rte.fdl.filehandling; 
 
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertNotSame; 
import static org.junit.Assert.assertTrue; 
 
import java.io.BufferedReader; 
import java.io.File; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.net.URL; 
import java.util.Iterator; 
import java.util.List; 
 
import org.apache.commons.io.FileSystemUtils; 
import org.apache.commons.io.FileUtils; 
import org.apache.commons.io.IOUtils; 
import org.apache.commons.io.LineIterator; 
import org.apache.commons.vfs2.CacheStrategy; 
import org.apache.commons.vfs2.FileContent; 
import org.apache.commons.vfs2.FileName; 
import org.apache.commons.vfs2.FileObject; 
import org.apache.commons.vfs2.FileSystemManager; 
import org.apache.commons.vfs2.FileSystemOptions; 
import org.apache.commons.vfs2.FilesCache; 
import org.apache.commons.vfs2.Selectors; 
import org.apache.commons.vfs2.VFS; 
import org.apache.commons.vfs2.auth.StaticUserAuthenticator; 
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder; 
import org.apache.commons.vfs2.impl.DefaultFileSystemManager; 
import org.apache.commons.vfs2.provider.local.DefaultLocalFileProvider; 
import org.junit.Before; 
import org.junit.Test; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
 
/**
 * FileServiceTest is TestCase of File Handling Service 
 * @author Seongjong Yoon 
 */ 
//@RunWith(SpringJUnit4ClassRunner.class) 
//@ContextConfiguration(locations = {"classpath*:spring/context-*.xml" }) 
public class FilehandlingServiceTest { 
 
 private static final Logger LOGGER = LoggerFactory.getLogger(FilehandlingServiceTest.class); 
 
 private String filename = ""; 
 private String text = ""; 
 private String tmppath = ""; 
 private String absoluteFilePath = ""; 
 
    @Before 
    public void onSetUp() throws Exception { 
     filename = "test.txt"; 
     text = "test�Դϴ�."; 
     tmppath = "tmp"; 
      
     LOGGER.debug("System's temporary directory : {}", EgovFileUtil.getTmpDirectory()); 
   
     absoluteFilePath = EgovFileUtil.getTmpDirectory() + "/testing.txt";  
      
     EgovFileUtil.cd(System.getProperty("user.dir")); 
    } 
 
    /**
     * Ư�� ��ġ�� ������ �����ϰ� �ʿ信 ���� ������ ������ ĳ���Ѵ�. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testCeateFile() throws Exception { 
     FileSystemManager manager = VFS.getManager(); 
 
     FileObject baseDir = manager.resolveFile(System.getProperty("user.dir")); 
     final FileObject file = manager.resolveFile(baseDir, "testfolder/file1.txt"); 
 
     // ��� ���� ���� 
     file.delete(Selectors.SELECT_FILES); 
     assertFalse(file.exists()); 
 
     // ���� ���� 
     file.createFile(); 
     assertTrue(file.exists()); 
    } 
 
    /**
     * Ư�� ��ġ�� �����ϴ� ���Ͽ� �����Ͽ� ���� ������ �����Ѵ�. 
     * ���� ��ġ�� ���� ��� �Ǵ� ��� ��� �� �پ��� ������ �����Ѵ�. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testAccessFile() throws Exception { 
 
     FileSystemManager manager = VFS.getManager(); 
 
     FileObject baseDir = manager.resolveFile(System.getProperty("user.dir")); 
     FileObject file = manager.resolveFile(baseDir, "testfolder/file1.txt"); 
 
     // ��� ���� ���� 
     file.delete(Selectors.SELECT_FILES); 
     assertFalse(file.exists()); 
 
     // ���� ���� 
     file.createFile(); 
     assertTrue(file.exists()); 
 
     FileContent fileContent = file.getContent(); 
     assertEquals(0, fileContent.getSize()); 
 
     // ���� ���� 
     String string = "test�Դϴ�."; 
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
 
     // ���� �б� 
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
 
     // ���ϳ��� ���� 
     assertEquals(sb.toString(), string); 
    } 
 
    /**
     * ĳ�� ����� ����Ͽ�, �����ϰų� ������ ������ �޸� �� �ε������ν� 
     * ���� ���� �ÿ� �ҿ�Ǵ� �ð��� �����Ѵ�. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testCaching() throws Exception { 
     String path = FilehandlingServiceTest.class.getResource("").getPath(); 
     String testFolder = path + "/testfolder"; 
     FileSystemManager manager = VFS.getManager(); 
 
     FileObject scratchFolder = manager.resolveFile(testFolder); 
 
     // testfolder ���� ��� ���� ���� 
        scratchFolder.delete(Selectors.EXCLUDE_SELF); 
         
        FileObject file = manager.resolveFile(path +  "/testfolder/dummy.txt"); 
        file.createFile(); 
 
        // ĳ�� Manager ���� 
        DefaultFileSystemManager fs = new DefaultFileSystemManager(); 
     fs.setFilesCache(manager.getFilesCache()); 
 
     // zip, jar, tgz, tar, tbz2, file 
     if (!fs.hasProvider("file")) { 
         fs.addProvider("file", new DefaultLocalFileProvider()); 
     } 
 
     fs.setCacheStrategy(CacheStrategy.ON_RESOLVE); 
        fs.init(); 
 
        // ĳ�� ��ü ���� 
        FileObject foBase2 = fs.resolveFile(testFolder); 
        LOGGER.debug("## scratchFolder.getName().getPath() : {}", scratchFolder.getName().getPath()); 
 
        FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath()); 
 
        // ������ �������� ���� 
        FileObject[] fos = cachedFolder.getChildren(); 
        assertFalse(contains(fos, "file1.txt")); 
 
        // ���ϻ��� 
        scratchFolder.resolveFile("file1.txt").createFile(); 
 
        // ���� ������ 
        // BUT cachedFolder ���� ������ �������� ���� 
        fos = cachedFolder.getChildren(); 
        assertFalse(contains(fos, "file1.txt")); 
 
        // ���ΰ�ħ 
        cachedFolder.refresh(); 
        // ������ ������ 
        fos = cachedFolder.getChildren(); 
        assertTrue(contains(fos, "file1.txt")); 
 
    } 
 
    /**
     * ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testWriteFile() throws Exception { 
 
     // delete file 
     if (EgovFileUtil.isExistsFile(filename)) { 
      EgovFileUtil.delete(new File(filename)); 
     } 
 
     EgovFileUtil.writeFile(filename, text, "UTF-8"); 
     assertTrue(EgovFileUtil.isExistsFile(filename)); 
    } 
     
    /**
     * ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testWriteFileWithAbsolutePath() throws Exception { 
      
     // delete file 
     if (EgovFileUtil.isExistsFile(absoluteFilePath)) { 
      EgovFileUtil.delete(new File(absoluteFilePath)); 
     } 
 
     EgovFileUtil.writeFile(absoluteFilePath, text, "UTF-8"); 
     assertTrue(EgovFileUtil.isExistsFile(absoluteFilePath)); 
    } 
 
 /**
  * ���� �б� �׽�Ʈ. 
  *  
  * @throws Exception 
  */ 
 @Test 
    public void testReadFile() throws Exception { 
 
     if (!EgovFileUtil.isExistsFile(filename)) { 
      EgovFileUtil.writeFile(filename, text, "UTF-8"); 
     } 
 
     assertEquals(EgovFileUtil.readFile(new File(filename), "UTF-8"), text); 
 
     //LOGGER.debug(EgovFileUtil.readTextFile(filename, false)); 
 
     List<String> lines = FileUtils.readLines(new File(filename), "UTF-8"); 
     LOGGER.debug(lines.toString()); 
 
     String string = lines.get(0); 
 
     assertEquals(text, string); 
    } 
  
 /**
  * ���� �б� �׽�Ʈ. 
  *  
  * @throws Exception 
  */ 
 @Test 
    public void testReadFileWithAbsolutePath() throws Exception { 
 
     if (!EgovFileUtil.isExistsFile(absoluteFilePath)) { 
      EgovFileUtil.writeFile(absoluteFilePath, text, "UTF-8"); 
     } 
 
     assertEquals(EgovFileUtil.readFile(new File(absoluteFilePath), "UTF-8"), text); 
 
     //LOGGER.debug(EgovFileUtil.readTextFile(filename, false)); 
 
     List<String> lines = FileUtils.readLines(new File(absoluteFilePath), "UTF-8"); 
     LOGGER.debug(lines.toString()); 
 
     String string = lines.get(0); 
 
     assertEquals(text, string); 
    } 
 
    /**
     * ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testCp() throws Exception { 
 
     if (!EgovFileUtil.isExistsFile(filename)) { 
      EgovFileUtil.writeFile(filename, text); 
     } 
 
     EgovFileUtil.cp(filename, tmppath + "/" + filename); 
 
     assertEquals( 
       EgovFileUtil.readFile(new File(filename), "UTF-8"), 
       EgovFileUtil.readFile(new File(tmppath + "/" + filename), "UTF-8") 
     ); 
    } 
     
    /**
     * ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testCpWithAbsolutePath() throws Exception { 
 
     if (!EgovFileUtil.isExistsFile(absoluteFilePath)) { 
      EgovFileUtil.writeFile(absoluteFilePath, text); 
     } 
 
     EgovFileUtil.cp(absoluteFilePath, tmppath + "/" + filename); 
 
     assertEquals( 
       EgovFileUtil.readFile(new File(absoluteFilePath), "UTF-8"), 
       EgovFileUtil.readFile(new File(tmppath + "/" + filename), "UTF-8") 
     ); 
    } 
 
 
    /**
     * ���� �̵� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testMv() throws Exception { 
 
     if (!EgovFileUtil.isExistsFile(tmppath + "/" + filename)) { 
      EgovFileUtil.writeFile(tmppath + "/" + filename, text); 
     } 
 
     EgovFileUtil.mv(tmppath + "/" + filename, tmppath + "/movedfile.txt"); 
 
     assertEquals( 
       EgovFileUtil.readFile(new File(filename), "UTF-8"), 
       EgovFileUtil.readFile(new File(tmppath + "/movedfile.txt"), "UTF-8") 
     ); 
    } 
 
    /**
     * ���� ��ġ �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testTouch() throws Exception { 
 
     String path = tmppath + "/movedfile.txt"; 
     FileObject file = EgovFileUtil.getFileObject(path); 
 
     long setTime = EgovFileUtil.touch(path); 
 
     assertEquals(file.getContent().getLastModifiedTime(), setTime); 
 
    } 
 
    /**
     * ���� Ȯ���� ó�� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testGetFileExtension() throws Exception { 
 
     assertTrue(EgovFileUtil.isExistsFile(filename)); 
     assertEquals(EgovFileUtil.getFileExtension(filename), "txt"); 
 
    } 
 
    /**
     * ���� ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testIsExistsFile() throws Exception { 
 
        assertTrue(EgovFileUtil.isExistsFile(filename)); 
 
    } 
 
    /**
     * ���ϸ� Ȯ�� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testStripFilename() throws Exception { 
 
     assertTrue(EgovFileUtil.isExistsFile(filename)); 
     assertEquals("test", EgovFileUtil.stripFilename(filename)); 
 
    } 
 
    /**
     * ���� ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testRm() throws Exception { 
 
     String tmptarget = tmppath; 
      
     if (!EgovFileUtil.isExistsFile(tmptarget)) { 
      EgovFileUtil.writeFile(tmptarget, text); 
     } 
 
     int result = EgovFileUtil.rm(tmptarget); 
 
     assertTrue(result > 0); 
     assertFalse(EgovFileUtil.isExistsFile(tmptarget)); 
    } 
 
    /**
     * ���丮 ���� �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testCd() throws Exception { 
 
     String path = "c:/windows"; 
     FileName foldername = EgovFileUtil.getFileObject(path).getName(); 
 
     EgovFileUtil.cd(""); 
 
     String uri = EgovFileUtil.pwd().getURI(); 
     LOGGER.debug("EgovFileUtil.pwd().getURI() : {}", uri); 
     LOGGER.debug("foldername.getURI() : {}", foldername.getURI()); 
 
     assertFalse(foldername.getURI().equals(uri)); 
 
     ///////////////////////////////////////////////////////////////// 
     // c:\windows �� �̵� 
     EgovFileUtil.cd(path); 
 
     uri = EgovFileUtil.pwd().getURI(); 
     LOGGER.debug("EgovFileUtil.pwd().getURI() : {}", uri); 
     LOGGER.debug("foldername.getURI() : {}", foldername.getURI()); 
     assertEquals(foldername.getURI(), EgovFileUtil.pwd().getURI()); 
    } 
 
    /**
     * Stream �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testIOUtils() throws Exception { 
  InputStream in = new URL("http://jakarta.apache.org").openStream(); 
  try { 
 
   assertFalse(IOUtils.toString(in).equals("")); 
 
  } finally { 
   IOUtils.closeQuietly(in); 
  } 
    } 
 
    /**
     * FileSystemUtils �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
 @Test 
    public void testFileSystemUtils() throws Exception { 
 
     try { 
      long freeSpace = FileSystemUtils.freeSpaceKb("C:/"); 
 
      assertTrue(freeSpace > 0); 
 
     } catch (Exception e) { 
      LOGGER.error("error: {}", e.getCause()); 
     } 
    } 
 
    /**
     * GREP �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testGrep() throws Exception { 
 
     try { 
      String[] search = {"abcdefg", "efghijklmn", "12", "3"}; 
 
      List<String> lists = EgovFileUtil.grep(search, "\\d{1,2}"); 
 
      for (Iterator<String> it = lists.iterator(); it.hasNext();) { 
       LOGGER.info(it.next()); 
      } 
 
 
      lists = EgovFileUtil.grep(new File("pom.xml"), "egovframework.rte"); 
 
      for (Iterator<String> it = lists.iterator(); it.hasNext();) { 
       LOGGER.info(it.next()); 
      } 
 
 
     } catch (Exception e) { 
      LOGGER.error("error: {}", e.getCause()); 
     } 
    } 
 
    /**
     * Line iterator �׽�Ʈ. 
     *  
     * @throws Exception 
     */ 
    @Test 
    public void testLineIterator() throws Exception { 
 
     String[] string = { 
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", 
      " xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">", 
      " <modelVersion>4.0.0</modelVersion>", 
      " <groupId>egovframework.rte</groupId>", 
      " <artifactId>egovframework.rte.fdl.filehandling</artifactId>", 
      " <packaging>jar</packaging>", 
      " <version>3.2.0</version>", 
      " <name>egovframework.rte.fdl.filehandling</name>" 
     }; 
 
 
     try { 
      File file = new File("pom.xml"); 
 
      LineIterator it = FileUtils.lineIterator(file, "UTF-8"); 
 
      try { 
       LOGGER.debug("############################# LineIterator ###############################"); 
 
        for (int i = 0; it.hasNext(); i++) { 
           String line = it.nextLine(); 
           LOGGER.info(line); 
 
           assertEquals(string[i], line); 
        } 
       } finally { 
        LineIterator.closeQuietly(it); 
       } 
 
     } catch (Exception e) { 
      LOGGER.error("error: {}", e.getCause()); 
     } 
    } 
 
    @Test 
    public void testUserAuthentication() throws Exception { 
     StaticUserAuthenticator auth = new StaticUserAuthenticator(null, "username", "password"); 
     FileSystemOptions opts = new FileSystemOptions(); 
     DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth); 
 
     FileSystemManager manager = VFS.getManager(); 
 
     FileObject baseDir = manager.resolveFile(System.getProperty("user.dir")); 
     FileObject file = manager.resolveFile(baseDir, "testfolder/file1.txt"); 
     FileObject fo = manager.resolveFile("d:" + file.getName().getPath(), opts); 
 
     fo.createFile(); 
 
    } 
 
    @Test 
    public void testCaching1() throws Exception { 
     String testFolder = FilehandlingServiceTest.class.getResource(".").getPath(); 
      
     LOGGER.debug("testFolder = {}", testFolder); 
 
     FileSystemManager manager = VFS.getManager(); 
 
     EgovFileUtil.writeFile(testFolder + "/file1.txt", text, "UTF-8"); 
 
     /*
      * ĳ�� Manager ���� 
      * CacheStrategy.MANUAL : Deal with cached data manually. Call FileObject.refresh() to refresh the object data. 
      * CacheStrategy.ON_RESOLVE : Refresh the data every time you request a file from FileSystemManager.resolveFile 
      * CacheStrategy.ON_CALL : Refresh the data every time you call a method on the fileObject.  
      *  You'll use this only if you really need the latest info as this setting is a major performance loss. 
      */ 
        DefaultFileSystemManager fs = new DefaultFileSystemManager(); 
     fs.setFilesCache(manager.getFilesCache()); 
 
     // zip, jar, tgz, tar, tbz2, file 
     if (!fs.hasProvider("file")) { 
         fs.addProvider("file", new DefaultLocalFileProvider()); 
     } 
     //  StandardFileSystemManager fs = new StandardFileSystemManager(); 
 
     fs.setCacheStrategy(CacheStrategy.ON_RESOLVE); 
        fs.init(); 
 
 
        // ĳ�� ��ü ���� 
        //FileObject foBase2 = fs.resolveFile(testFolder); 
        LOGGER.debug("####1"); 
        FileObject cachedFile = fs.toFileObject(new File(testFolder + "/file1.txt")); 
        LOGGER.debug("####2"); 
 
        FilesCache filesCache = fs.getFilesCache(); 
        LOGGER.debug("####3"); 
        filesCache.putFile(cachedFile); 
        FileObject obj = filesCache.getFile(cachedFile.getFileSystem(), cachedFile.getName()); 
 
        // FileObject baseFile = fs.getBaseFile(); 
        // LOGGER.debug("### cachedFile.getContent().getSize() is {}", cachedFile.getContent().getSize()); 
 
 
        // long fileSize = cachedFile.getContent().getSize(); 
        // LOGGER.debug("#########size is {}", fileSize); 
        // FileObject cachedFile1 = cachedFile.resolveFile("file2.txt"); 
 
        //  FileObject scratchFolder = manager.resolveFile(testFolder); 
        //  scratchFolder.delete(Selectors.EXCLUDE_SELF); 
 
        EgovFileUtil.delete(new File(testFolder + "/file1.txt")); 
 
        //  obj.createFile(); 
 
        // LOGGER.debug("#########obj is {}", obj.toString()); 
        // LOGGER.debug("#########size is {}", obj.getContent().getSize()); 
        LOGGER.debug("#########file is {}", obj.exists()); 
 
        fs.close(); 
    } 
 
 @Test 
    public void testCaching3() throws Exception { 
     FileSystemManager manager = VFS.getManager(); 
 
     String testFolder = FilehandlingServiceTest.class.getResource(".").getPath(); 
     FileObject scratchFolder = manager.resolveFile(testFolder + "/testfolder"); 
 
     // releaseable 
        FileObject dir1 = scratchFolder.resolveFile("file1.txt"); 
 
        // avoid cache removal 
        FileObject dir2 = scratchFolder.resolveFile("file2.txt"); 
        dir2.getContent(); 
 
        // check if the cache still holds the right instance 
  FileObject dir22 = scratchFolder.resolveFile("file2.txt"); 
        assertTrue(dir2 == dir22); 
 
        // check if the cache still holds the right instance 
       /* FileObject dir1_2 = scratchFolder.resolveFile("file1.txt");        assertFalse(dir1 == dir1_2);*/ 
    } 
 
 private boolean contains(FileObject[] fos, String string) { 
  for (int i = 0; i < fos.length; i++) { 
   if (string.equals(fos[i].getName().getBaseName())) { 
    LOGGER.debug("# {}", string); 
    return true; 
   } 
  } 
 
  LOGGER.debug("# {} should be seen", string); 
  return false; 
 } 
}

