@echo off

::----------------------------------------------
:label_check
if not ""%1"" == ""__env.cmd"" goto label_exit

::----------------------------------------------
:label_env
if not "%TAIN_HOME%" == "" goto label_end

:: base env
set TAIN_HOME=N:\tain
set TAIN_TOOL=%TAIN_HOME%\tools
set TAIN_TEST=%TAIN_HOME%\test
set TAIN_PRODUCT=%TAIN_HOME%\products

:: tool env
set JAVA_HOME=%TAIN_TOOL%\jdk1.7.0_79
set CATALINA_HOME=%TAIN_TOOL%\apache-tomcat-7.0.75

:: product env
set LUCY_HOME=%TAIN_PRODUCT%\LucyCron\lucycron1.01

:: other env
set PATH=%JAVA_HOME%\bin;%CATALINA_HOME%\bin;%PATH%

::----------------------------------------------
:label_start
echo ----- base env --------------------------
echo TAIN_HOME=%TAIN_HOME%
echo TAIN_TOOL=%TAIN_TOOL%
echo TAIN_TEST=%TAIN_TEST%
echo TAIN_PRODUCT=%TAIN_PRODUCT%
echo ----- tool env --------------------------
echo JAVA_HOME=%JAVA_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo ----- products env --------------------------
echo LUCY_HOME=%LUCY_HOME%
echo ----- path env --------------------------
echo PATH=%PATH%
echo ----- other env --------------------------
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

