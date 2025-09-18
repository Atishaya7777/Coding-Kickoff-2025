@echo off
if not exist out mkdir out
javac -d out src\com\coding_kickoff\asciiquest\*.java src\com\coding_kickoff\asciiquest\*\*.java
java -cp out com.coding_kickoff.asciiquest.App