@ECHO OFF
javac -d classes -cp jar\* src/com/web/*.java
ECHO Compilation success
cd classes
java -cp ..\jar\*;. com.web.LyricsDriver
PAUSE