@echo off

::----------------------------------------------
:label_check
if not ""%1"" == ""__env.cmd"" goto label_exit

::----------------------------------------------
:label_env
if not "%TAIN_HOME%" == "" goto label_end

set TAIN_HOME=N:\tain
set TAIN_TOOL=%TAIN_HOME%\tools
set TAIN_TEST=%TAIN_HOME%\test

set LUCY_HOME=%TAIN_TEST%\LucyCron\lucycron1.01
set JAVA_HOME=%TAIN_HOME%\jdk1.7.0_79
set CATALINA_HOME=%TAIN_HOME%\apache-tomcat-7.0.75

set PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%PATH%

::----------------------------------------------
:label_start
echo TAIN_HOME=%TAIN_HOME%
echo TAIN_TOOL=%TAIN_TOOL%
echo TAIN_TEST=%TAIN_TEST%
echo -------------------------------
echo LUCY_HOME=%LUCY_HOME%
echo JAVA_HOME=%JAVA_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo -------------------------------
echo PATH=%PATH%
echo CURRENT_DIR=%CURRENT_DIR%
echo ENV_CMD=%ENV_CMD%
echo The environment is OK!!!
pause
goto label_end

::----------------------------------------------
:label_exit
echo ERROR: don't make the enviroment
exit /b 1

::----------------------------------------------
:label_end
exit /b 0

