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
package tain.kr.test.sigar.v00;

import java.util.List;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.ProcStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.hyperic.sigar.cmd.Ps;
import org.hyperic.sigar.cmd.Shell;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTestSigar.java
 *   -. Package    : tain.kr.test.sigar.v00
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 4. 14. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTestSigar {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestSigar.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestSigar() {
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
	 * PTQL (Process Table Query Language)
	 * 
	 * Hyperic SIGAR provides a mechanism to identify processes called Process Table Query Language.
	 * All operating systems assign a unique id (PID) to each running process. However, the PID is
	 * a random number that may also change at any point in time when a process is restarted. PTQL
	 * uses process attributes that will persist over time to identify a process.
	 * 
	 * Class.Attribute.operator=value
	 * 
	 *     e.g.) Exe.Name.ct=Program Files
	 * 
	 * Class.Attribute
	 * 
	 *     Pid.Pid        - The process ID
	 *     Pid.PidFile    - File containing the process ID
	 *     Pid.Service    - Windows Service name used to pid from the service manager
	 *     State.Name     - Base name of the process executable
	 *     CredName.User  - User Name of the process owner
	 *     CredName.Group - Group Name of the process owner
	 *     Cred.Uid       - User ID of the process owner
	 *     Cred.Gid       - Group ID of the process owner
	 *     Cred.Euid      - Effective User ID of the process owner
	 *     Cred.Egid      - Effective Group ID of the process owner
	 *     Exe.Name       - Full path name of the process executable
	 *     Exe.Cwd        - Current Working Directory of the process
	 *     Args.*         - Command line argument passed to the process
	 *     Env.*          - Environment variable within the process
	 *     Modules.*      - Shared library loaded within the process
	 *     
	 * operator for String values
	 * 
	 *     eq - equal to value
	 *     ne - not equal to value
	 *     ew - ends with value
	 *     sw - start with value
	 *     ct - contains value (substring)
	 *     re - regular expression value matches
	 *     Pne - Parent process not equal to value
	 *     
	 * operator for Numeric values
	 * 
	 *     eq - equal to value
	 *     ne - not equal to value
	 *     gt - greater than value
	 *     ge - greater than or equal value
	 *     lt - less than value
	 *     le - less than or equal value
	 *     
	 * e.g.) do not make a space in the below
	 * 
	 *     State.Name.eq = crond
	 *     
	 *     State.Name.eq = sshd
	 *     
	 *     Pid.PidFile.eq = /var/run/sshd.pid
	 *     
	 *     Pid.Service.eq = Eventlog
	 *     
	 *     Pid.Service.eq = sshd
	 *     
	 *     State.Name.re = ^(https?d.*|[Aa]pache2?)$
	 *     
	 *     State.Name.eq = httpd, State.Name.Pne = httpd
	 *     
	 *     State.Name.eq = httpd, State.Name.Pne = $1   <- $1 is the return value of the attribute (State.Name)
	 *                                                     in the first branch of the query
	 *     
	 *     CredName.User.eq = dougm, State.Name.eq = httpd, State.Name.Pne = $2 <- the return value of State.Name
	 *     
	 *     State.Name.eq = java
	 *     
	 *     State.Name.sw = java
	 *     
	 *     State.Name.eq = java, Args.7.eq = org.jboss.Main
	 *     
	 *     State.Name.eq = java, Args.-1.eq = org.jboss.Main
	 *     
	 *     State.Name.eq = java, Args.*.eq = org.jboss.Main
	 *     
	 *     
	 */
	/*
	 * static test method
	 */
	@SuppressWarnings("unchecked")
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainTestSigar();

		if (!flag) {
			/*
			 * 1st
			 */
			final int SLEEP_TIME = 5 * 1000;
			SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), SLEEP_TIME);
			
			while (true) {
				ProcStat stat = sigar.getProcStat();
				
				System.out.printf("TOT-%d SLEEP-%d RUN-%d ZOMB-%d STOP-%d \t"
						, stat.getTotal()
						, stat.getSleeping()
						, stat.getRunning()
						, stat.getZombie()
						, stat.getStopped()
						);
				System.out.printf("%s\t", sigar.getCpuPerc());
				System.out.printf("%s\t", sigar.getMem());
				System.out.printf("%s\t", sigar.getSwap());
				System.out.println();
				
				if (flag) {
					try { Thread.sleep(SLEEP_TIME); } catch (InterruptedException e) {}
					SigarProxyCache.clear(sigar);
				}
			}
		}
		
		if (flag) {
			/*
			 * 2nd
			 */
			final int SLEEP_TIME = 5 * 1000;
			SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), SLEEP_TIME);
			
			args = new String[] {
					"CredName.User.eq=taincokr",
					//"State.Name.eq=java",
			};
			
			while (true) {
				
				long[] pids = Shell.getPids(sigar, args);
				
				for (int i=0; i < pids.length; i++) {
					long pid = pids[i];
					
					List<String> info;
					try {
						info = Ps.getInfo(sigar, pid);
					} catch (SigarException e) {
						continue;
					}
					
					String cpuPerc = "?";
					try {
						cpuPerc = CpuPerc.format(sigar.getProcCpu(pid).getPercent());
					} catch (SigarException e) {
						// TODO: handle exception
					}
					
					info.add(info.size() - 1, cpuPerc);
					
					System.out.printf("%s\n", Ps.join(info));
				}

				if (flag) {
					try { Thread.sleep(SLEEP_TIME); } catch (InterruptedException e) {}
					SigarProxyCache.clear(sigar);
				}
				
				if (flag) break;
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
