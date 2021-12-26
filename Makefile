build:
	javac -d bin -sourcepath src src/com/jsheets/JSheets.java

run: build
	java -cp "bin" com.jsheets.JSheets