JDK=17

build:
	javac --release $(JDK) -d bin -sourcepath src src/com/jsheets/JSheets.java

docs:
	javadoc -d docs @docfiles.txt

run: build
	java -cp "bin" com.jsheets.JSheets