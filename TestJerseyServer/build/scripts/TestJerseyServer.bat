@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  TestJerseyServer startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and TEST_JERSEY_SERVER_OPTS to pass JVM options to this script.
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

set CLASSPATH=%APP_HOME%\lib\TestJerseyServer.jar;%APP_HOME%\lib\jetty-server-9.4.0.RC3.jar;%APP_HOME%\lib\jetty-servlet-9.4.0.RC3.jar;%APP_HOME%\lib\guice-servlet-4.0-beta.jar;%APP_HOME%\lib\jersey-guice-1.9.1.jar;%APP_HOME%\lib\jersey-server-1.9.1.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.5.4.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\logback-core-0.9.21.jar;%APP_HOME%\lib\logback-access-0.9.21.jar;%APP_HOME%\lib\metrics-servlet-3.0.0-BETA3.jar;%APP_HOME%\lib\jersey-json-1.16.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\jersey-server-2.7.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.7.jar;%APP_HOME%\lib\jersey-container-jetty-http-2.7.jar;%APP_HOME%\lib\jersey-media-moxy-2.7.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\jetty-http-9.4.0.RC3.jar;%APP_HOME%\lib\jetty-io-9.4.0.RC3.jar;%APP_HOME%\lib\jetty-security-9.4.0.RC3.jar;%APP_HOME%\lib\asm-3.1.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.5.4.jar;%APP_HOME%\lib\jackson-core-2.5.4.jar;%APP_HOME%\lib\jackson-databind-2.5.4.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.5.4.jar;%APP_HOME%\lib\metrics-core-3.0.0-BETA3.jar;%APP_HOME%\lib\jettison-1.1.jar;%APP_HOME%\lib\jaxb-impl-2.2.3-1.jar;%APP_HOME%\lib\jackson-core-asl-1.9.2.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.2.jar;%APP_HOME%\lib\jackson-jaxrs-1.9.2.jar;%APP_HOME%\lib\jackson-xc-1.9.2.jar;%APP_HOME%\lib\jersey-common-2.7.jar;%APP_HOME%\lib\jersey-client-2.7.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\hk2-api-2.2.0.jar;%APP_HOME%\lib\javax.inject-2.2.0.jar;%APP_HOME%\lib\hk2-locator-2.2.0.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jetty-continuation-9.1.1.v20140108.jar;%APP_HOME%\lib\jersey-entity-filtering-2.7.jar;%APP_HOME%\lib\org.eclipse.persistence.moxy-2.5.0.jar;%APP_HOME%\lib\org.eclipse.persistence.antlr-2.5.0.jar;%APP_HOME%\lib\jackson-annotations-2.5.0.jar;%APP_HOME%\lib\jaxb-api-2.2.2.jar;%APP_HOME%\lib\jersey-guava-2.7.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.2.0.jar;%APP_HOME%\lib\aopalliance-repackaged-2.2.0.jar;%APP_HOME%\lib\javassist-3.18.1-GA.jar;%APP_HOME%\lib\org.eclipse.persistence.core-2.5.0.jar;%APP_HOME%\lib\stax-api-1.0-2.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\org.eclipse.persistence.asm-2.5.0.jar;%APP_HOME%\lib\guice-4.0-beta.jar;%APP_HOME%\lib\cglib-3.0.jar;%APP_HOME%\lib\asm-util-4.0.jar;%APP_HOME%\lib\asm-4.0.jar;%APP_HOME%\lib\asm-tree-4.0.jar;%APP_HOME%\lib\jersey-core-1.16.jar;%APP_HOME%\lib\jetty-util-9.4.0.RC3.jar

@rem Execute TestJerseyServer
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %TEST_JERSEY_SERVER_OPTS%  -classpath "%CLASSPATH%" App %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable TEST_JERSEY_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%TEST_JERSEY_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
