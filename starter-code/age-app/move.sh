#!/bin/bash
jar -cvf age.war *
scp -i amohan_os.pem age.war ubuntu@141.195.7.174:/home/ubuntu
