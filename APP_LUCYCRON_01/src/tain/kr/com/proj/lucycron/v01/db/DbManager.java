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
package tain.kr.com.proj.lucycron.v01.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import tain.kr.com.proj.lucycron.v01.controller.SchRequest;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : DbManager.java
 *   -. Package    : tain.kr.com.proj.lucycron.v01.db
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 24. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class DbManager {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(DbManager.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private final String framework = "derbyClient";
	private final String driver = "org.apache.derby.jdbc.ClientDriver";
	
	/*
	 * jdbc:derby://localhost:1527/lucycron01;create=true;user=kang;password=kang123!
	 */
	private final String protocol = "jdbc:derby:";
	private final String host = "//localhost:";
	private final String port = "1527/";
	private final String dbName = "lucycron01";
	
	private Connection conn = null;
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	
	private ResultSet resultSet = null;
	private ResultSetMetaData meta = null;
	
	private enum SQL { CREATE, DROP, SELECT, INSERT, UPDATE, DELETE };
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private DbManager() throws Exception {
		
		if (flag) {
			/*
			 * loadDriver
			 */
			try {
				Class.forName(this.driver).newInstance();
				if (flag) log.debug("Loaded the appropreiate driver.");
			} catch (ClassNotFoundException e) {
				if (flag) log.debug("Unable to load the JDBC driver " + this.driver);
				throw e;
			} catch (InstantiationException e) {
				if (flag) log.debug("Unable to instantiate the JDBC driver " + this.driver);
				throw e;
			} catch (IllegalAccessException e) {
				if (flag) log.debug("Not allowed to access the JDBC driver " + this.driver);
				throw e;
			}
		}
		
		if (flag) {
			/*
			 * connection
			 */
			Properties prop = new Properties();
			
			if (!flag) prop.put("create", "true");
			if (flag) prop.put("user", "kang");
			if (flag) prop.put("password", "kang123!");
			
			this.conn = DriverManager.getConnection(this.protocol + this.host + this.port + this.dbName, prop);
			if (flag) log.debug("Connected to and created database " + this.dbName);
			
			this.conn.setAutoCommit(false);
		}
		
		if (flag) {
			/*
			 * statement
			 */
			this.stmt = this.conn.createStatement();
		}
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public synchronized void delete(SchRequest request) throws SQLException {
		
		if (flag) {
			/*
			 * delete
			 */
			if (flag) {
				/*
				 * cwd
				 */
				this.stmt.execute(String.format("delete from KANG.TB_SCHCWD where F_SCHID = '%s'", request.getName()));
			}
			
			if (flag) {
				/*
				 * cmd
				 */
				this.stmt.execute(String.format("delete from KANG.TB_SCHCMD where F_SCHID = '%s'", request.getName()));
			}
			
			if (flag) {
				/*
				 * env
				 */
				this.stmt.execute(String.format("delete from KANG.TB_SCHENV where F_SCHID = '%s'", request.getName()));
			}
			
			if (flag) {
				/*
				 * HHMM
				 */
				this.stmt.execute(String.format("delete from KANG.TB_SCHHHMM where F_SCHID = '%s'", request.getName()));
			}
		}
		
		this.conn.commit();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public synchronized void insert(SchRequest request) throws SQLException {
		
		if (flag) {
			/*
			 * insert
			 */
			if (flag) {
				/*
				 * cwd
				 */
				List<String> lstCwd = request.getLstCwd();

				String query = String.format("insert into KANG.TB_SCHCWD (F_SCHID, F_SEQ, F_CWD) values ('%s', ?, ?)", request.getName());
				this.pstmt = this.conn.prepareStatement(query);
				
				for (int i=0; i < lstCwd.size(); i++) {
					this.pstmt.setInt(1, i);
					this.pstmt.setString(2, lstCwd.get(i));
					this.pstmt.executeUpdate();
				}
			}
			
			if (flag) {
				/*
				 * cmd
				 */
				List<String> lstCmd = request.getLstCmd();
				
				String query = String.format("insert into KANG.TB_SCHCMD (F_SCHID, F_SEQ, F_CMD) values ('%s', ?, ?)", request.getName());
				this.pstmt = this.conn.prepareStatement(query);
				
				for (int i=0; i < lstCmd.size(); i++) {
					this.pstmt.setInt(1, i);
					this.pstmt.setString(2, lstCmd.get(i));
					this.pstmt.executeUpdate();
				}
			}
			
			if (flag) {
				/*
				 * env
				 */
				List<String> lstEnv = request.getLstEnv();
				
				String query = String.format("insert into KANG.TB_SCHENV (F_SCHID, F_SEQ, F_ENV) values ('%s', ?, ?)", request.getName());
				this.pstmt = this.conn.prepareStatement(query);
				
				for (int i=0; i < lstEnv.size(); i++) {
					this.pstmt.setInt(1, i);
					this.pstmt.setString(2, lstEnv.get(i));
					this.pstmt.executeUpdate();
				}
			}
			
			if (flag) {
				/*
				 * HHMM
				 */
				Set<String> setHHMM = request.getSetHHMM();
				
				String query = String.format("insert into KANG.TB_SCHHHMM (F_SCHID, F_SEQ, F_HHMM) values ('%s', ?, ?)", request.getName());
				this.pstmt = this.conn.prepareStatement(query);
				
				int i = 0;
				for (String hhmm : setHHMM) {
					this.pstmt.setInt(1, i++);
					this.pstmt.setString(2, hhmm);
					this.pstmt.executeUpdate();
				}
			}
		}

		this.conn.commit();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public synchronized void select() throws SQLException {
		
		if (flag) {
			/*
			 * select
			 */
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
	
	private synchronized void createLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.CREATE + "]");
		
		this.stmt.execute("create table location (num int, addr varchar(100))");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void insertLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.INSERT + "]");
		
		this.pstmt = this.conn.prepareStatement("insert into location values (?, ?)");
		
		this.pstmt.setInt(1, 1956);
		this.pstmt.setString(2, "Webster St.");
		this.pstmt.executeUpdate();
		
		this.pstmt.setInt(1, 1910);
		this.pstmt.setString(2, "Union St.");
		this.pstmt.executeUpdate();
		
		this.pstmt.setInt(1, 1990);
		this.pstmt.setString(2, "LasVegas St.");
		this.pstmt.executeUpdate();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void updateLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.UPDATE + "]");
		
		this.pstmt = this.conn.prepareStatement("update location set num=?, addr=? where num=?");
		
		this.pstmt.setInt(1, 2956);
		this.pstmt.setString(2, "Webster St. - 2");
		this.pstmt.setInt(3, 1956);
		this.pstmt.executeUpdate();
		
		this.pstmt.setInt(1, 2910);
		this.pstmt.setString(2, "Union St. - 2");
		this.pstmt.setInt(3, 1910);
		this.pstmt.executeUpdate();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void selectLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.SELECT + "]");
		
		this.resultSet = this.stmt.executeQuery("select num as number, addr as address from location order by number");
		this.meta = this.resultSet.getMetaData();
		
		// column information
		for (int i=1; i <= this.meta.getColumnCount(); i++) {
			System.out.printf("\t[%d] [%s] [%s] [%d], [%s] [%s], [%d] [%s], [%s] [%s].\n"
					, i
					, this.meta.getCatalogName(i)
					, this.meta.getColumnClassName(i)
					, this.meta.getColumnDisplaySize(i)

					, this.meta.getColumnLabel(i)
					, this.meta.getColumnName(i)

					, this.meta.getColumnType(i)
					, this.meta.getColumnTypeName(i)

					, this.meta.getSchemaName(i)
					, this.meta.getTableName(i)
					);
		}
		
		// column data
		while (this.resultSet.next()) {
			System.out.printf("[number, address] = [%d, '%s']\n", this.resultSet.getInt("number"), resultSet.getString("address"));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void deleteLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.DELETE + "]");
		
		this.stmt.execute("delete from location where num < 2000");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void dropLocation() throws SQLException {
		
		if (flag) log.debug(">>> [" + SQL.DROP + "]");
		
		this.stmt.execute("drop table location");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private synchronized void sampleLocation() throws SQLException {
		
		createLocation();
		
		insertLocation();
		
		updateLocation();
		
		selectLocation();

		deleteLocation();
		
		dropLocation();
		
		this.conn.commit();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static DbManager instance = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public static synchronized final DbManager getInstance() throws Exception {
		
		if (DbManager.instance == null) {
			DbManager.instance = new DbManager();
		}
		
		return DbManager.instance;
	}
	
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
			/*
			 * begin
			 */
			DbManager.getInstance().sampleLocation();
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
