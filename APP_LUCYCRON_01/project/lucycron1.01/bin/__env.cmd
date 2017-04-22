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
set UNX_HOME=%TOOL_DIR%\UnxUpdates
::set M2_HOME=%TOOL_DIR%\apache\apache-maven-3.3.3
set M2_HOME=%TOOL_DIR%\apache\apache-maven-3.5.0
::set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
set ANT_HOME=%TOOL_DIR%\apache\apache-ant-1.9.9

:: path env
set PATH=%JRE_HOME%\bin;%PATH%
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%CATALINA_HOME%\bin;%PATH%
set PATH=%DERBY_HOME%\bin;%PATH%
set PATH=%LUCY_HOME%\bin;%PATH%
set PATH=%NSSM_HOME%\win64;%PATH%
set PATH=%UNX_HOME%;%PATH%
set PATH=%M2_HOME%\bin;%PATH%
set PATH=%ANT_HOME%\bin;%PATH%

::----------------------------------------------
:label_start
echo ----- first env --------------------------
echo CURRENT_DIR=%CURRENT_DIR%
echo ENV_CMD=%ENV_CMD%
echo ----- base env --------------------------
echo TAIN_HOME=%TAIN_HOME%
echo PRODUCT_HOME=%PRODUCT_HOME%
echo ----- project env --------------------------
echo LUCY_BASE=%LUCY_BASE%
echo LUCY_HOME=%LUCY_HOME%
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
echo ----- path env --------------------------
echo PATH=%PATH%

echo -------------------------------------------------------------------
echo -------------------------------------------------------------------
echo "##### jre version > java -version"
echo "##### jdk version > javac -version"
echo "##### apache-tomcat version > version"
echo "##### derby information > sysinfo"
echo "##### nssm windows service > nssm"
echo "##### UnxUpdates command > tail one, dir | grep bat"
echo "##### apache-maven version > mvn -version"
echo "##### apache-ant version > ant -version"
echo "The environment is OK!!!"
::pause

goto label_end

::----------------------------------------------
:label_exit
echo ERROR: don't make the enviroment
exit /b 1

::----------------------------------------------
:label_end
exit /b 0

