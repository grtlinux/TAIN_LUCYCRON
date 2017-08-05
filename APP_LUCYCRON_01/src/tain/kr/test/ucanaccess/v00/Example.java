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

import java.io.File;
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
 *
 *     데이터 형식 크기
 *     ----------- --------------------------------------------
 *     텍스트      최대 255B
 *     메모        최대 1G 문자, 2GB, 그 중 65,535는 컨트롤 표시
 *     숫자        1B, 2B, 4B, 8B, 16B
 *     날짜/시간   8B
 *     통화        8B
 *     일련번호    4B, 16B
 *     예/아니오   1bit
 *     OLE개체     최대 1G
 *     첨부파일
 *     하이퍼링크
 *     조회마법사
 *
 *
 *     1. CREATE TABLE 문을 이용하여 테이블을 만듭니다.
 *
 *         CREATE TABLE 테이블
 *         (
 *             ID          AUTOINCREMENT(1,1)
 *                         CONSTRAINT PK_테이블 PRIMARY KEY,
 *             이름        TEXT(50) NOT NULL,
 *             전화번호    TEXT(50) NOT NULL,
 *             주소        TEXT(100) NOT NULL,
 *             이메일      TEXT(50)
 *         )
 *
 *     2. DROP TABLE 문을 이용하여 테이블을 삭제합니다.
 *
 *         DROP TABLE 테이블
 *
 *     3. ALTER TABLE 문을 이용하여 테이블을 수정합니다.
 *
 *         ALTER TABLE 테이블 ADD COLUMN 우편번호 TEXT(20)
 *         ALTER TABLE 테이블 ALTER COLUMN 우편번호 TEXT(15)
 *         ALTER TABLE 테이블 DROP COLUMN 우편번호
 *
 *     4. CREATE INDEX 문을 이용하여 테이블의 기본키 인덱스를 추가합니다.
 *
 *         CREATE INDEX PK_테이블 ON 테이블 (ID) WITH PRIMARY
 *
 *     5. CREATE INDEX 문을 이용하여 테이블의 NULL 허용을 설정
 *
 *         CREATE INDEX IDX_테이블 ON 테이블 (이름) WITH DISALLOW NULL
 *
 *     6. 데이터 타입
 *
 *         문자열   : char, varchar, text     -> text
 *         정수     : int, long               -> long
 *         실수     : float, double           -> double
 *         통화형   : currency
 *         메모     : longtext
 *         날짜     : datetime, timestamp     -> datetime
 *         BLOB     : oleobject (RichEdit(RTF)는 이 형태를 써야함)
 *         BOOLEAN  : YesNo
 *
 *     -------------------------------------------------------------------------------------------------------------------
 *
 *         CREATE TABLE table_name
 *         (
 *             attribute_1 data_type constraint,
 *             attribute_2 data_type constraint,
 *             ....
 *         )
 *
 *
 *         CREATE TABLE CUSTOMER
 *         (
 *             CustomerID  AUTOINCREMENT NOT NULL PRIMARY KEY,
 *             LastName    varchar(25) NOT NULL,
 *             FirstName   varchar(35) NOT NULL,
 *             Address     varchar(35),
 *             City        varchar(35),
 *             State       varchar(2),
 *             ZIP         varchar(10),
 *             Phone       varchar(12) NOT NULL,
 *             FAX         varchar(12),
 *             Email       varchar(100)
 *         )
 *
 *         CREATE TABLE CONTACT
 *         (
 *             ContactID   AUTOINCREMENT NOT NULL,
 *             CustomerID  INT,
 *             ContactDate DATETIME NOT NULL,
 *             ContactType varchar(10)NOT NULL,
 *             Remarks     LONGTEXT,
 *
 *             CONSTRAINT  CONTACT_PK  PRIMARY KEY(ContactID),
 *             CONSTRAINT  CUS_CONT_FK FOREIGN KEY(CustomerID)
 *                                      REFERENCES CUSTOMER(CustomerID)
 *         )
 *
 *
 *         ALTER TABLE table
 *         {
 *             ADD {COLUMN field type[(size)] [NOT NULL] [CONSTRAINT index] |
 *             ALTER COLUMN field type[(size)] |
 *             CONSTRAINT multifieldindex} |
 *             DROP {COLUMN field I CONSTRAINT indexname}
 *         }
 *
 *         ALTER TABLE table ADD COLUMN field type(size) (NOT NULL) (CONSTRAINT index)
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
		
		// 삭제
		if (flag)
			new File(pathNewDB).delete();
		
		/*
		 * open access file or create access file
		 */
		String url = UcanaccessDriver.URL_PREFIX + pathNewDB + ";newDatabaseVersion=V2007";
		log.debug(">>>>> url: " + url);
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

	private void test() throws SQLException {
	
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = this.ucaConn.createStatement();
			stmt.execute("CREATE TABLE tab01 (seq LONG PRIMARY KEY, descr varchar(50)) ");
			stmt.close();

			this.ucaConn.setAutoCommit(false);
			
			stmt = this.ucaConn.createStatement();
			
			stmt.execute("INSERT INTO tab01 (seq, descr) VALUES ( 123, 'Bible' )");
			stmt.execute("INSERT INTO tab01 (seq, descr) VALUES ( 456, 'Superman' )");

			this.ucaConn.commit();
			
			rs = stmt.executeQuery("SELECT * from tab01");
			dump(rs, "executeQuery");

		} catch (Exception e) {
			e.printStackTrace();
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
	 * Microsoft Access 프로그램에서 아래와 같이 작업 후에 _TESTDB01.accdb 새파일 작성한다.
	 * 
	 *     1. Access 옵션 > 기본 설정 > 새 데이터베이스 정렬 순서 : 일반 (General) 로 변경한다.
	 *     2. 새파일 _TESTDB01.accdb 를 생성한다.
	 *     
	 */
	private void createTablesExample() throws SQLException {
		
		Statement stmt;
		
		stmt = this.ucaConn.createStatement();
		//stmt.execute("CREATE TABLE example1 (id COUNTER PRIMARY KEY, descr text(400), number numeric(12,3), date0 datetime) ");   // java OK!!, access No
		//stmt.execute("CREATE TABLE example1 (id AUTOINCREMENT (1, 1) CONSTRAINT PK_EXAMPLE1 PRIMARY KEY, descr text(400), number numeric(12,3), date0 datetime) ");   // java error
		//stmt.execute("CREATE TABLE example1 (id AUTOINCREMENT NOT NULL PRIMARY KEY, descr text(400), number numeric(12,3), date0 datetime) ");  // java OK!!! access No
		//stmt.execute("CREATE TABLE example1 (id AUTOINCREMENT NOT NULL, descr text(400), number numeric(12,3), date0 datetime, CONSTRAINT PK_EXAMPLE1 PRIMARY KEY(id) ) ");  // java OK!!! access No
		stmt.execute("CREATE TABLE example1 (id AUTOINCREMENT NOT NULL CONSTRAINT pk_example1 PRIMARY KEY, descr text(400), number numeric(12,3), date0 datetime ) ");  // java OK!!! access No
		stmt.close();
		
		stmt = this.ucaConn.createStatement();
		//stmt.execute("CREATE TABLE example2 (id COUNTER PRIMARY KEY, descr text(400)) ");
		stmt.execute("CREATE TABLE example2 (id AUTOINCREMENT NOT NULL PRIMARY KEY, descr text(400)) ");
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
			
			ResultSet rs = stmt.executeQuery("SELECT iif(descr='Show must go off','tizio','caio&sempronio' & '&Macro Amadei' & ' ' & now() & RTRIM('  I''m proud of you   ')) from example1");
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
				
				if (!flag) {
					ex.test();
					return;
				}
				
				ex.createTablesExample();
				ex.insertData();
				ex.executeQuery();
				ex.executeQueryWithFunctions();
				ex.executeQueryWithCustomFunction();
				ex.executeLikeExample();
				ex.showExtensions();
				ex.transaction();
				
			} catch (Exception e) {
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
		
		if (flag) {
			//test01(new String[] { "G:/11. Matrix System ver1.0/_TESTDB01.accdb" });
			test01(new String[] { "N:/_TESTDB01.accdb" });
		}
	}
}
