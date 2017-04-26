@echo off

::----------------------------------------------
:label_check
if not ""%1"" == ""__env.cmd"" goto label_exit

::----------------------------------------------
:label_env
if not "%TAIN_HOME%" == "" goto label_end

:: base env
set TAIN_HOME=N:\tain
set PRODUCT_HOME=%TAIN_HOME%\products

:: project env
set LUCY_BASE=%PRODUCT_HOME%\LucyCron
set LUCY_HOME=%LUCY_BASE%\lucycron1.01

:: test tool env
set TEST_DIR=%LUCY_BASE%\test
set LOG_DIR=%LUCY_BASE%\logs
set TOOL_DIR=%LUCY_BASE%\tools

set JAVA_HOME=%TOOL_DIR%\jdk\jdk1.7.0_79
set JRE_HOME=%TOOL_DIR%\jre\jre1.7.0_79
set CATALINA_HOME=%TOOL_DIR%\apache\apache-tomcat-7.0.75
set DERBY_HOME=%TOOL_DIR%\db-derby\db10.8
set DERBY_OPTS=-Dderby.system.home=%LUCY_BASE%\data\derbyDB
set NSSM_HOME=%TOOL_DIR%\nssm-2.24
::set UNX_HOME=%TOOL_DIR%\UnxUpdates
set M2_HOME=%TOOL_DIR%\apache\apache-maven-3.3.3
::set M2_HOME=%TOOL_DIR%\apache\apache-maven-3.5.0
::set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
set ANT_HOME=%TOOL_DIR%\apache\apache-ant-1.9.9

:: path env
set PATH=%JRE_HOME%\bin;%PATH%
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%CATALINA_HOME%\bin;%PATH%
set PATH=%DERBY_HOME%\bin;%PATH%
set PATH=%LUCY_HOME%\bin;%PATH%
set PATH=%NSSM_HOME%\win64;%PATH%
::set PATH=%UNX_HOME%;%PATH%
set PATH=%M2_HOME%\bin;%PATH%
set PATH=%ANT_HOME%\bin;%PATH%

::----------------------------------------------
::----------------------------------------------
::----------------------------------------------
:label_start
echo ----- system base env --------------------------
echo "> APPDATA               " = "%APPDATA%"
echo "> COMPUTERNAME          " = "%COMPUTERNAME%"
echo "> HOMEPATH              " = "%HOMEPATH%"
echo "> LOCALAPPDATA          " = "%LOCALAPPDATA%"
echo "> LOGONSERVER           " = "%LOGONSERVER%"
echo "> PROCESSOR_ARCHITECTURE" = "%PROCESSOR_ARCHITECTURE%"
echo "> SystemRoot            " = "%SystemRoot%"
echo "> USERDOMAIN            " = "%USERDOMAIN%"
echo "> USERNAME              " = "%USERNAME%"
echo "> USERPROFILE           " = "%USERPROFILE%"
echo "> hostname"
echo ""
echo ----- windows command --------------------------
echo "> cmd           : Windows command"
echo "> taskmgr       : �۾�������"
echo "> explorer      : Ž����"
echo "> tasklist      : ���μ��� ���(ex. tasklist /fi 'pid eq 1234')"
echo "> taskkill      : ���μ��� ����(ex. taskkill /fi 'imagename eq acme*')"
echo ""
echo "> dxdiag        : ���̷�ƮX ���ܵ���(������ �ý�������)"
echo "> cleanmgr      : ��ũ ����"
echo "> dfrgui        : ��ũ ���� ����"
echo "> msconfig      : �ý��� ����"
echo "> msinfo32      : �ý��� ����"
echo "> charmap       : �ý��� ����ǥ"
echo "> mstsc         : ���� ����ũ�� ����"
echo "> perfmon       : ���� �����"
echo "> resmon        : ���ҽ� �����"
echo "> utilman       : ��ƿ��Ƽ ������"
echo "> control       : ������"
echo "> control system         : �ý���"
echo "> control admintools     : ��������"
echo "> control folders        : ���� �ɼ�"
echo "> control desktop        : ���� ����"
echo "> control netconnections : ��Ʈ��ũ ����"
echo "> control printers       : ��ġ �� ������"
echo "> control schedtasks     : �۾� �����ٷ�"
echo "> control userpasswords  : ����� ����"
echo "> control userpasswords2 : ����� ����2"
echo ""
echo "C:\> dir /s *.cpl : ��� CPL(Control Panel applets) ���� ã��"
echo "> appwiz.cpl    : ���α׷� ���� �Ǵ� ����"
echo "> desk.cpl      : ȭ�� �ػ�"
echo "> firewall.cpl  : Windows ��ȭ��"
echo "> ncpa.cpl      : ��Ʈ��ũ ����"
echo "> sysdm.cpl     : �ý��� �Ӽ�"
echo "> hdwwiz.cpl    : ��ġ ������"
echo "> wscui.cpl     : ���� ���� > �ֱ� �޽��� ���� �� ���� �ذ�"
echo ""
echo "C:\> dir /s *.msc : ��� MSC ���� ã��"
echo "> compmgmt.msc        : ��ǻ�� ����"
echo "> services.msc        : ����"
echo "> devmgmt.msc         : ��ġ ������"
echo "> diskmgmt.msc        : ��ũ ����"
echo "> fsmgmt.msc          : ���� ����"
echo "> gpedit.msc          : ���� �׷� ��å ������"
echo "> lusrmgr.msc         : ���� ����� �� �׷�"
echo "> secpol.msc          : ���� ���� ��å"
echo "> comexp.msc          : ���� ��� ����"
echo "> certmgr.msc         : ������ - ���� �����"
echo "> printmanagement.msc : �μ� ����"
echo "> taskschd.msc        : �۾� �����ٷ�"
echo "> wf.msc              : ��� ������ ���Ե� Windows ��ȭ��"
echo "> wmimgmt.msc         : Windows Management Instrumentation"
echo "> cmd : command"
echo ""
echo ----- first env --------------------------
echo CURRENT_DIR=%CURRENT_DIR%
echo ENV_CMD=%ENV_CMD%
echo ""
echo ----- base env --------------------------
echo TAIN_HOME=%TAIN_HOME%
echo PRODUCT_HOME=%PRODUCT_HOME%
echo ""
echo ----- project env --------------------------
echo LUCY_BASE=%LUCY_BASE%
echo LUCY_HOME=%LUCY_HOME%
echo ""
echo ----- test tool env --------------------------
echo TEST_DIR=%TEST_DIR%
echo LOG_DIR=%LOG_DIR%
echo TOOL_DIR=%TOOL_DIR%
echo JRE_HOME=%JRE_HOME%
echo JAVA_HOME=%JAVA_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo DERBY_HOME=%DERBY_HOME%
echo DERBY_OPTS=%DERBY_OPTS%
echo NSSM_HOME=%NSSM_HOME%
echo UNX_HOME=%UNX_HOME%
echo M2_HOME=%M2_HOME%
echo MAVEN_OPTS=%MAVEN_OPTS%
echo ANT_HOME=%ANT_HOME%
echo ""
echo ----- path env --------------------------
echo PATH=%PATH%

echo -------------------------------------------------------------------
echo -------------------------------------------------------------------
echo "##### jre version > java -version"
echo "##### jdk version > javac -version"
echo "##### apache-tomcat version > version"
echo "##### derby information > sysinfo"
echo "##### derby information > startNetworkServer, stopNetworkServer -user kang -password kang123!"
echo "##### nssm windows service > nssm"
echo "##### UnxUpdates command > tail one, dir | grep bat"
echo "##### apache-maven version > mvn -version"
echo "##### apache-ant version > ant -version"
echo "The environment is OK!!!"
::pause

goto label_end

::----------------------------------------------
::----------------------------------------------
::----------------------------------------------
:label_exit
echo ERROR: don't make the enviroment
exit /b 1

::----------------------------------------------
:label_end
exit /b 0

