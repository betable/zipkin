@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  zipkin-collector-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and ZIPKIN_COLLECTOR_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

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
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\zipkin-collector-service-1.2.1-rc5.jar;%APP_HOME%\lib\scala-library-2.10.5.jar;%APP_HOME%\lib\zipkin-collector-scribe-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-receiver-kafka-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-cassandra-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-redis-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-anormdb-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-collector-1.2.1-rc5.jar;%APP_HOME%\lib\scrooge-serializer_2.10-3.20.0.jar;%APP_HOME%\lib\twitter-server_2.10-1.12.0.jar;%APP_HOME%\lib\kafka_2.10-0.8.2.1.jar;%APP_HOME%\lib\commons-io-1.3.2.jar;%APP_HOME%\lib\zipkin-scrooge-1.2.1-rc5.jar;%APP_HOME%\lib\zipkin-scrooge-1.2.1-rc5-idl.jar;%APP_HOME%\lib\zipkin-cassandra-core-1.2.1-rc5.jar;%APP_HOME%\lib\snappy-0.4.jar;%APP_HOME%\lib\util-app_2.10-6.26.0.jar;%APP_HOME%\lib\finagle-redis_2.10-6.27.0.jar;%APP_HOME%\lib\util-logging_2.10-6.26.0.jar;%APP_HOME%\lib\slf4j-log4j12-1.6.4.jar;%APP_HOME%\lib\anorm_2.10-2.3.9.jar;%APP_HOME%\lib\HikariCP-2.4.0.jar;%APP_HOME%\lib\sqlite-jdbc-3.8.11.jar;%APP_HOME%\lib\finagle-core_2.10-6.27.0.jar;%APP_HOME%\lib\util-core_2.10-6.26.0.jar;%APP_HOME%\lib\scrooge-core_2.10-3.20.0.jar;%APP_HOME%\lib\util-codec_2.10-6.26.0.jar;%APP_HOME%\lib\compiler-0.8.12.1.jar;%APP_HOME%\lib\finagle-http_2.10-6.27.0.jar;%APP_HOME%\lib\finagle-httpx_2.10-6.27.0.jar;%APP_HOME%\lib\finagle-httpx-compat_2.10-6.27.0.jar;%APP_HOME%\lib\finagle-zipkin_2.10-6.27.0.jar;%APP_HOME%\lib\util-jvm_2.10-6.26.0.jar;%APP_HOME%\lib\util-registry_2.10-6.26.0.jar;%APP_HOME%\lib\jackson-core-2.4.4.jar;%APP_HOME%\lib\jackson-databind-2.4.4.jar;%APP_HOME%\lib\jackson-module-scala_2.10-2.4.4.jar;%APP_HOME%\lib\guava-16.0.1.jar;%APP_HOME%\lib\metrics-core-2.2.0.jar;%APP_HOME%\lib\kafka-clients-0.8.2.1.jar;%APP_HOME%\lib\zookeeper-3.4.6.jar;%APP_HOME%\lib\jopt-simple-3.2.jar;%APP_HOME%\lib\zkclient-0.3.jar;%APP_HOME%\lib\zipkin-common-1.2.1-rc5.jar;%APP_HOME%\lib\algebird-core_2.10-0.10.2.jar;%APP_HOME%\lib\ostrich_2.10-9.10.0.jar;%APP_HOME%\lib\finagle-ostrich4_2.10-6.27.0.jar;%APP_HOME%\lib\finagle-thrift_2.10-6.27.0.jar;%APP_HOME%\lib\cassandra-driver-core-2.1.6.jar;%APP_HOME%\lib\util-stats_2.10-6.26.0.jar;%APP_HOME%\lib\log4j-1.2.16.jar;%APP_HOME%\lib\scala-arm_2.10-1.4.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\joda-convert-1.6.jar;%APP_HOME%\lib\netty-3.10.1.Final.jar;%APP_HOME%\lib\util-collection_2.10-6.26.0.jar;%APP_HOME%\lib\util-hashing_2.10-6.26.0.jar;%APP_HOME%\lib\jsr166e-1.0.0.jar;%APP_HOME%\lib\util-function_2.10-6.26.0.jar;%APP_HOME%\lib\commons-codec-1.6.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\util-events_2.10-6.26.0.jar;%APP_HOME%\lib\libthrift-0.5.0.jar;%APP_HOME%\lib\paranamer-2.6.jar;%APP_HOME%\lib\jsr305-2.0.1.jar;%APP_HOME%\lib\lz4-1.2.0.jar;%APP_HOME%\lib\snappy-java-1.1.1.6.jar;%APP_HOME%\lib\jline-0.9.94.jar;%APP_HOME%\lib\finagle-exception_2.10-6.27.0.jar;%APP_HOME%\lib\JavaEWAH-0.6.6.jar;%APP_HOME%\lib\util-eval_2.10-6.26.0.jar;%APP_HOME%\lib\scala-json_2.10-3.0.2.jar;%APP_HOME%\lib\netty-handler-4.0.27.Final.jar;%APP_HOME%\lib\metrics-core-3.0.2.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar;%APP_HOME%\lib\scala-compiler-2.10.5.jar;%APP_HOME%\lib\netty-buffer-4.0.27.Final.jar;%APP_HOME%\lib\netty-transport-4.0.27.Final.jar;%APP_HOME%\lib\netty-codec-4.0.27.Final.jar;%APP_HOME%\lib\netty-common-4.0.27.Final.jar;%APP_HOME%\lib\slf4j-api-1.7.12.jar;%APP_HOME%\lib\jackson-annotations-2.4.4.jar;%APP_HOME%\lib\junit-3.8.1.jar;%APP_HOME%\lib\scala-reflect-2.10.5.jar

@rem Execute zipkin-collector-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ZIPKIN_COLLECTOR_SERVICE_OPTS%  -classpath "%CLASSPATH%" com.twitter.zipkin.collector.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ZIPKIN_COLLECTOR_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ZIPKIN_COLLECTOR_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
