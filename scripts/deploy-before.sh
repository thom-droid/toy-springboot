#!/bin/bash

if [ -d /home/ec2-user/app/step2 ]; then
  sudo rm -rf /home/ec2-user/app/step2/
fi
sudo mkdir -vp /home/ec2-user/app/step2/