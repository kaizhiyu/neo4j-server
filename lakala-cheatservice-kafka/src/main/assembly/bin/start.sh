#!/bin/bash
libjars=""

for i in ../lib/*.jar ; do
    libjars=$libjars:$i
done
echo $libjars

nohup java -cp $libjars com.lakala.cheatservice.kafka.ApplyInfoConsumer &
