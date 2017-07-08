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
package tain.kr.test.ucanaccess.v00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTest.java
 *   -. Package    : tain.kr.test.ucanaccess.v00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 7. 9. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTest {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTest.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTest() {
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
	 * 
	 * [ UCanAccess-4.0.2-bin.zip ]
	 * 
	 *     1. 환경
	 *     
	 *         JAVA : jdk1.7.0_79
	 *         
	 *         아래 jar들을 libs에 포함.
	 *             
	 *             ucanaccess-4.0.2.jar
	 *             ucanload.jar
	 *             
	 *             commons-lang-2.6.jar
	 *             commons-logging-1.1.1.jar
	 *             
	 *             hsqldb.jar
	 *             jackcess-2.1.6.jar
	 * 
	 *     2. 테스트 소소
	 * 
	 *             try {
	 *                 String dbFileSpec = "jdbc:ucanaccess://G:\\11. Matrix System ver1.0\\_DB.accdb";
	 *                 Connection conn = DriverManager.getConnection(dbFileSpec, "admin", "123");
	 *                 Statement stmt = conn.createStatement();
	 *                 ResultSet rs = stmt.executeQuery("select 코드명 from 코드기타");
	 *                 
	 *                 while (rs.next()) {
	 *                     String codeName = rs.getString("코드명");
	 *                     System.out.printf("[%s]\n", codeName);
	 *                 }
	 *                 
	 *                 rs.close();
	 *                 conn.close();
	 *             } catch (SQLException e) {
	 *                 e.printStackTrace();
	 *             }
	 * 
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainTest();

		if (flag) {
			try {
				String dbFileSpec = "jdbc:ucanaccess://G:\\11. Matrix System ver1.0\\_DB.accdb";
				Connection conn = DriverManager.getConnection(dbFileSpec, "admin", "");
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select 코드명 from 코드기타");
				
				while (rs.next()) {
					String codeName = rs.getString("코드명");
					System.out.printf("[%s]\n", codeName);
				}
				
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
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
