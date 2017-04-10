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
set LUCY_HOME=%PRODUCT_HOME%\LucyCron
set LUCY_JOB_HOME=%LUCY_HOME%\lucycron1.01

:: test tool env
set TEST_DIR=%LUCY_HOME%\test
set LOG_DIR=%LUCY_HOME%\logs
set TOOL_DIR=%LUCY_HOME%\tools
set JAVA_HOME=%TOOL_DIR%\jdk\jdk1.7.0_79
set JRE_HOME=%TOOL_DIR%\jre\jre1.7.0_79
set CATALINA_HOME=%TOOL_DIR%\apache\apache-tomcat-7.0.75
set DERBY_HOME=%TOOL_DIR%\db-derby\db10.8
set DERBY_OPTS=-Dderby.system.home=%LUCY_HOME%\data\derbyDB

:: path env
set PATH=%JRE_HOME%\bin;%PATH%
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%CATALINA_HOME%\bin;%PATH%
set PATH=%DERBY_HOME%\bin;%PATH%
set PATH=%LUCY_JOB_HOME%\bin;%PATH%

::----------------------------------------------
:label_start
echo ----- first env --------------------------
echo CURRENT_DIR=%CURRENT_DIR%
echo ENV_CMD=%ENV_CMD%
echo ----- base env --------------------------
echo TAIN_HOME=%TAIN_HOME%
echo PRODUCT_HOME=%PRODUCT_HOME%
echo ----- project env --------------------------
echo LUCY_HOME=%LUCY_HOME%
echo LUCY_JOB_HOME=%LUCY_JOB_HOME%
echo ----- test tool env --------------------------
echo TEST_DIR=%TEST_DIR%
echo LOG_DIR=%LOG_DIR%
echo TOOL_DIR=%TOOL_DIR%
echo JRE_HOME=%JRE_HOME%
echo JAVA_HOME=%JAVA_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo DERBY_HOME=%DERBY_HOME%
echo DERBY_OPTS=%DERBY_OPTS%
echo ----- path env --------------------------
echo PATH=%PATH%
echo The environment is OK!!!

echo -------------------------------------------------------------------
echo -------------------------------------------------------------------
echo "##### jre version > java -version"
echo "##### jdk version > javac -version"
echo "##### apache-tomcat version > version"
echo "##### derby information > sysinfo"
pause

goto label_end

::----------------------------------------------
:label_exit
echo ERROR: don't make the enviroment
exit /b 1

::----------------------------------------------
:label_end
exit /b 0

