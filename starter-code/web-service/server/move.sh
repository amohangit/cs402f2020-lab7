#!/bin/bash
chmod 600 amohan_aws_auto.pem
scp -i amohan_aws_auto.pem -r dob-service ubuntu@35.174.204.25:/home/ubuntu
