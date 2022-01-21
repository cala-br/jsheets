JDK=11

build:
	javac --release $(JDK) -d bin -sourcepath src src/com/jsheets/JSheets.java

docs:
	javadoc -d docs @docfiles.txt

start:
	java -cp "bin" com.jsheets.JSheets

run: build start
