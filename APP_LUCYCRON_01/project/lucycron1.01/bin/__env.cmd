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
echo "> taskmgr       : 작업관리자"
echo "> explorer      : 탐색기"
echo "> tasklist      : 프로세스 목록(ex. tasklist /fi 'pid eq 1234')"
echo "> taskkill      : 프로세스 종료(ex. taskkill /fi 'imagename eq acme*')"
echo ""
echo "> dxdiag        : 다이렉트X 진단도구(간단한 시스템정보)"
echo "> cleanmgr      : 디스크 정리"
echo "> dfrgui        : 디스크 조각 모음"
echo "> msconfig      : 시스템 구성"
echo "> msinfo32      : 시스템 정보"
echo "> charmap       : 시스템 문자표"
echo "> mstsc         : 원격 데스크톱 연결"
echo "> perfmon       : 성능 모니터"
echo "> resmon        : 리소스 모니터"
echo "> utilman       : 유틸리티 관리자"
echo "> control       : 제어판"
echo "> control system         : 시스템"
echo "> control admintools     : 관리도구"
echo "> control folders        : 폴더 옵션"
echo "> control desktop        : 개인 설정"
echo "> control netconnections : 네트워크 연결"
echo "> control printers       : 장치 및 프린터"
echo "> control schedtasks     : 작업 스케줄러"
echo "> control userpasswords  : 사용자 계정"
echo "> control userpasswords2 : 사용자 계정2"
echo ""
echo "C:\> dir /s *.cpl : 모든 CPL(Control Panel applets) 파일 찾기"
echo "> appwiz.cpl    : 프로그램 제거 또는 변경"
echo "> desk.cpl      : 화면 해상도"
echo "> firewall.cpl  : Windows 방화벽"
echo "> ncpa.cpl      : 네트워크 연결"
echo "> sysdm.cpl     : 시스템 속성"
echo "> hdwwiz.cpl    : 장치 관리자"
echo "> wscui.cpl     : 관리 센터 > 최근 메시지 검토 및 문제 해결"
echo ""
echo "C:\> dir /s *.msc : 모든 MSC 파일 찾기"
echo "> compmgmt.msc        : 컴퓨터 관리"
echo "> services.msc        : 서비스"
echo "> devmgmt.msc         : 장치 관리자"
echo "> diskmgmt.msc        : 디스크 관리"
echo "> fsmgmt.msc          : 공유 폴더"
echo "> gpedit.msc          : 로컬 그룹 정책 편집기"
echo "> lusrmgr.msc         : 로컬 사용자 및 그룹"
echo "> secpol.msc          : 로컬 보안 정책"
echo "> comexp.msc          : 구성 요소 서비스"
echo "> certmgr.msc         : 인증서 - 현재 사용자"
echo "> printmanagement.msc : 인쇄 관리"
echo "> taskschd.msc        : 작업 스케줄러"
echo "> wf.msc              : 고급 보안이 포함된 Windows 방화벽"
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

