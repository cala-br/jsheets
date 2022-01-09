JDK=17

build:
	javac --release $(JDK) -d bin -sourcepath src src/com/jsheets/JSheets.java

run: build
	java -cp "bin" com.jsheets.JSheets