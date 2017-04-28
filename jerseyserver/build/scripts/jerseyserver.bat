@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  jerseyserver startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and JERSEYSERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\jerseyserver.jar;%APP_HOME%\lib\jersey-guice-1.9.1.jar;%APP_HOME%\lib\jersey-server-1.9.1.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\metrics-servlet-3.0.0-BETA3.jar;%APP_HOME%\lib\jersey-json-1.16.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\config-inject-1.0.0.jar;%APP_HOME%\lib\jetty-http-server-wrapper-1.2.1.jar;%APP_HOME%\lib\jersey-server-2.25.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.25.1.jar;%APP_HOME%\lib\jersey-container-servlet-2.25.1.jar;%APP_HOME%\lib\jackson-databind-2.8.7.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.25.1.jar;%APP_HOME%\lib\config-magic-0.14.jar;%APP_HOME%\lib\joda-time-2.0.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.8.7.jar;%APP_HOME%\lib\logback-classic-1.2.2.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\asm-3.1.jar;%APP_HOME%\lib\metrics-core-3.0.0-BETA3.jar;%APP_HOME%\lib\jettison-1.1.jar;%APP_HOME%\lib\jaxb-impl-2.2.3-1.jar;%APP_HOME%\lib\jackson-core-asl-1.9.2.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.2.jar;%APP_HOME%\lib\jackson-jaxrs-1.9.2.jar;%APP_HOME%\lib\jackson-xc-1.9.2.jar;%APP_HOME%\lib\commons-configuration-1.8.jar;%APP_HOME%\lib\guice-assistedinject-4.1.0.jar;%APP_HOME%\lib\jersey-common-2.25.1.jar;%APP_HOME%\lib\jersey-client-2.25.1.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\jersey-media-jaxb-2.25.1.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.5.0-b32.jar;%APP_HOME%\lib\javax.inject-2.5.0-b32.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b32.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jersey-entity-filtering-2.25.1.jar;%APP_HOME%\lib\jackson-annotations-2.8.4.jar;%APP_HOME%\lib\jaxb-api-2.2.2.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\jersey-guava-2.25.1.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b32.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b32.jar;%APP_HOME%\lib\javassist-3.20.0-GA.jar;%APP_HOME%\lib\stax-api-1.0-2.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\guice-4.1.0.jar;%APP_HOME%\lib\guice-servlet-4.1.0.jar;%APP_HOME%\lib\jersey-core-1.16.jar;%APP_HOME%\lib\jsr305-3.0.1.jar;%APP_HOME%\lib\jetty-servlet-9.4.2.v20170220.jar;%APP_HOME%\lib\logback-access-1.2.1.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.4.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.4.jar;%APP_HOME%\lib\jackson-core-2.8.7.jar;%APP_HOME%\lib\logback-core-1.2.2.jar;%APP_HOME%\lib\jetty-security-9.4.2.v20170220.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.4.jar;%APP_HOME%\lib\jetty-server-9.4.2.v20170220.jar;%APP_HOME%\lib\jetty-http-9.4.2.v20170220.jar;%APP_HOME%\lib\jetty-io-9.4.2.v20170220.jar;%APP_HOME%\lib\jetty-util-9.4.2.v20170220.jar

@rem Execute jerseyserver
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JERSEYSERVER_OPTS%  -classpath "%CLASSPATH%" jerseyserver.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable JERSEYSERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%JERSEYSERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
