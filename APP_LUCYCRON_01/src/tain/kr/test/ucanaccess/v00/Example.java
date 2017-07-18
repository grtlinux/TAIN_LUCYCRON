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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.ucanaccess.converters.TypesMap.AccessType;
import net.ucanaccess.ext.FunctionType;
import net.ucanaccess.jdbc.UcanaccessConnection;
import net.ucanaccess.jdbc.UcanaccessDriver;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Example.java
 *   -. Package    : tain.kr.test.ucanaccess.v00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 7. 18. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class Example {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Example.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Connection ucaConn;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public Example(String pathNewDB) {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
		
		try {
			this.ucaConn = getUcanaccessConnection(pathNewDB);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@FunctionType(functionName="justconcat", argumentTypes={AccessType.TEXT, AccessType.TEXT}, returnType=AccessType.TEXT)
	public static String concat(String str1, String str2) {
		return str1 + " >>>>" + str2;
	}
	
	private static void dump(ResultSet rs, String exName) throws SQLException {
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		System.out.println(exName + " result:");
		System.out.println();
		
		while (rs.next()) {
			System.out.print("| ");
			
			int cnt = rs.getMetaData().getColumnCount();
			for (int i=1; i <= cnt; ++i) {
				Object o = rs.getObject(i);
				System.out.print(o + " | ");
			}
			
			System.out.println();
			System.out.println();
		}
	}
	
	private static Connection getUcanaccessConnection(String pathNewDB) throws SQLException, IOException {
		
		String url = UcanaccessDriver.URL_PREFIX + pathNewDB + ";newDatabaseVersion=V2003";
		return DriverManager.getConnection(url, "sa", "");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void createTablesExample() throws SQLException {
		
		Statement stmt;
		
		stmt = this.ucaConn.createStatement();
		stmt.execute("CREATE TABLE example1 (id COUNTER PRIMARY KEY, descr text(400), number numeric(12,3), date0 datetime) ");
		stmt.close();
		
		stmt = this.ucaConn.createStatement();
		stmt.execute("CREATE TABLE example2 (id COUNTER PRIMARY KEY, descr text(400)) ");
		stmt.close();
		
		stmt = this.ucaConn.createStatement();
		stmt.execute("CREATE TABLE example3 (id LONG PRIMARY KEY, descr text(400)) ");
		stmt.close();
		
		stmt = this.ucaConn.createStatement();
		stmt.execute("CREATE TABLE example4 (id LONG PRIMARY KEY, descr text(400)) ");
		stmt.close();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void insertData() throws SQLException {
	
		Statement stmt = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			
			stmt.execute("INSERT INTO example1 (descr, number, date0) VALUES ('Show must go off', -1110.55446, #11/22/2003 10:42:58 PM#)");
			stmt.execute("INSERT INTO example1 (descr, number, date0) VALUES (\"Show must go up and down\", -113.55446, #11/23/2003 10:42:58 PM#)");
			
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'dsdsds' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'aa' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'aBa' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'aBBBa' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'PB123' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'C1V23' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'aca' )");
			stmt.execute("INSERT INTO example2 (descr) VALUES ( 'Ada' )");
			
			stmt.execute("INSERT INTO example3 (ID, descr) VALUES (1, 'DALLAS')");
			stmt.execute("INSERT INTO example3 (ID, descr) VALUES (2, 'MILANO')");

			stmt.execute("INSERT INTO example4 (ID, descr) VALUES (2, 'PARIS')");
			stmt.execute("INSERT INTO example4 (ID, descr) VALUES (3, 'LONDON')");
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void executeQuery() throws SQLException {
		
		Statement stmt = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * from example1");
			dump(rs, "executeQuery");
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void executeQueryWithFunctions() throws SQLException {
		
		Statement stmt = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT llf(descr='Show must go off','tizio','caio&sempronio' & '&Macro Amadei' & ' ' & now() & RTRIM('  I''m proud of you   ')) from example1");
			dump(rs, "executeQueryWithFunctions");
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void executeQueryWithCustomFunction() throws SQLException {
		
		Statement stmt = null;
		
		try {
			UcanaccessConnection uc = (UcanaccessConnection) this.ucaConn;
			uc.addFunctions(this.getClass());
			
			stmt = this.ucaConn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT justConcat(descr, '' & now()) from example1");
			dump(rs, "executeQueryWithCustomFunction");
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void executeLikeExample() throws SQLException {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("select descr from example2 where descr like 'P%'");
			dump(rs, "executeLikeExample STEP 1: like with standard % jolly");
			stmt.close();

			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("select descr from example2 where descr like 'P*'");
			dump(rs, "executeLikeExample STEP 2: like with standard * jolly");
			stmt.close();

			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("select descr from example2 where descr like 'P[A-F]###'");
			dump(rs, "executeLikeExample STEP 3: number and interval patterns");
			stmt.close();

			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("select descr from example2 where descr like 'C#V##'");
			dump(rs, "executeLikeExample STEP 4: number pattern");
			stmt.close();
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void showExtensions() throws SQLException {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM example3 full outer join example4 on (example3.id = example4.id)");
			dump(rs, "showExcention STEP 1: full outer join");
			stmt.close();
			
			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM example2 order by id desc limit 5 offset 1");
			dump(rs, "showExcention STEP 2: limit and offset");
			stmt.close();
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void transaction() throws SQLException {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			this.ucaConn.setAutoCommit(false);
			
			stmt = this.ucaConn.createStatement();
			stmt.executeUpdate("update example4 set descr='Lugo di Romagna'");
			rs = stmt.executeQuery("SELECT * FROM example4");
			dump(rs, "transaction: before rollback");
			this.ucaConn.rollback();
			stmt.close();
			
			stmt = this.ucaConn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM example4");
			dump(rs, "transaction: after rollback");
			
			stmt.executeUpdate("update example4 set descr='Lugo di Romagna'");
			stmt.execute("insert into example4 (ID, descr) values (5, 'DALLAS')");
			this.ucaConn.commit();
			rs = stmt.executeQuery("SELECT * FROM example4");
			dump(rs, "transaction: after commit");
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
		}
	}
	
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

		if (flag) {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			try {
				if (args.length == 0) {
					System.err.println("You must specfy new Database Access location (full path)");
					return;
				}
				
				Example ex = new Example(args[0]);
				
				ex.createTablesExample();
				ex.insertData();
				ex.executeQuery();
				ex.executeQueryWithFunctions();
				ex.executeQueryWithCustomFunction();
				ex.executeLikeExample();
				ex.showExtensions();
				ex.transaction();
				
			} catch (Exception e) {
				// TODO: handle exception
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
			test01(new String[] { "N:/" });
	}
}
