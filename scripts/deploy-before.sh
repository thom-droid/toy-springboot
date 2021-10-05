#!/bin/bash

if [ -d /home/ec2-user/app/step2/zip ]; then
  sudo rm -rf /home/ec2-user/app/step2/zip
fi
sudo mkdir -vp /home/ec2-user/app/step2/zip