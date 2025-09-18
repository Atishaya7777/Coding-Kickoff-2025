#!/bin/bash
rm -rf out
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.coding_kickoff.asciiquest.App