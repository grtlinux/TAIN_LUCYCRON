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
package odbc.v00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTest.java
 *   -. Package    : tain.kr.com.test.odbc.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 20. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainTest {

	private static boolean flag = true;

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTest() {
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
			new MainTest();

		if (!flag) {
			/*
			 * begin: C:/Windows/SysWOW64/odbcad32.exe
			 * 
			 * Access���� �ѱ��� 8859_1(ISO-8859-1)�� ����ϱ� ������ �Ʒ��� ���� �Ͽ� �ڷḦ �ִ´�.
			 * 
			 *     String str = new String("�ѱ�".getBytes("euc-kr"), "ISO-8859-1");
			 * 
			 * �ݴ�� Access���� �ѱ� �ڷḦ �о� �Ʒ��� ���� ��ȯ�Ͽ� ó���Ѵ�.
			 * 
			 *     String str = new String("�ѱ�".getBytes("ISO-8859-1"), "euc-kr");
			 *     
			 */
			
			DBConnection dbConn = new DBConnection();
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from �ڵ��Ÿ";
				conn = dbConn.getConnection();
				psmt = conn.prepareStatement(sql);
				
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					String grpName = new String(rs.getString("�׷��").getBytes("8859_1"), "euc-kr");
					String codeName = new String(rs.getString("�ڵ��").getBytes("8859_1"), "euc-kr");
					System.out.printf("[%s] [%s]\n", grpName, codeName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbConn.disConnection(rs, psmt, conn);
			}
		}

		if (flag) {
			
			DBConnection dbConn = new DBConnection();
			Connection conn = null;
			PreparedStatement psmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from �ڵ��Ÿ";
				conn = dbConn.getConnection();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					String grpName = new String(rs.getString("�׷��").getBytes("8859_1"), "euc-kr");
					String codeName = new String(rs.getString("�ڵ��").getBytes("8859_1"), "euc-kr");
					System.out.printf("[%s] [%s]\n", grpName, codeName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbConn.disConnection(rs, psmt, conn);
			}
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			test01(args);
	}
}


final class DBConnection {
	
	private String DB_URL = "jdbc:odbc:USRDB";   // Testdata
	private String DB_USER = "admin";
	private String DB_PASS = "";
	
	private Connection conn = null;
	
	public Connection getConnection() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Properties props = new Properties();
			//props.put("charSet", "8859_1");
			props.put("user", DB_USER);
			//props.put("password", DB_PASS);
			
			// this.conn = DriverManager.getConnection(DB_URL, props);
			this.conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.conn;
	}
	
	public void disConnection(ResultSet rs, PreparedStatement psmt, Connection conn) {
		
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (psmt != null) {
			try {
				psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}