@ECHO OFF
if exist "stub" (
del /f/q/s stub  
rmdir /q/s stub
)
cd axis2-bin
cd bin
call wsdl2java.bat -uri http://54.160.98.24/axis2/services/DobService?wsdl -o ../../stub/
ECHO Client Stub Created
cd ../../stub/
copy ..\Driver\DobDriver.java src\com\web
javac -d classes -cp ..\axis2-bin\lib\* src/com/web/*.java
ECHO Compilation success
cd classes
java -cp ..\..\axis2-bin\lib\*;. com.web.DobDriver
cd ../../
PAUSE