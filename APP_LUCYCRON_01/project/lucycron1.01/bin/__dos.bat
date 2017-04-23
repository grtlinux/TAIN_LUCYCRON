@echo off
@setlocal

::----------------------------------------------
:label_env1
set CURRENT_DIR=%CD%
set ENV_CMD=%CURRENT_DIR%\__env.cmd

call %ENV_CMD% __env.cmd
if errorlevel 1 goto label_end

::----------------------------------------------
:label_env2




goto label_start

::----------------------------------------------
:: administrator
::net user administrator /active:yes

::----------------------------------------------
:label_start
start

::----------------------------------------------
:label_end
echo The end of the command
pause

@endlocal

:: --------------------------------------------
:: if close then exit
echo "EXIT_IF_CLOSE=%EXIT_IF_CLOSE%"
if "%EXIT_IF_CLOSE%"=="true" exit /b 0
