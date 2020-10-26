#!/bin/sh
rm -rf stub
../axis2-bin/bin/./wsdl2java.sh -uri http://35.174.204.25/axis2/services/DobService\?wsdl -o stub/
cd stub
rm -rf classes
mkdir classes
cp ../Driver/* src/com/web
javac -d "classes" -cp ".:../../axis2-bin/lib/*" src/com/web/*.java
cd classes
java -cp ".:../../../axis2-bin/lib/*" com.web.DobDriver
cd ../../
