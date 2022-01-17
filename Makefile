JDK=17

build:
	javac --release $(JDK) -d bin -cp bin -sourcepath "src" src/com/jsheets/JSheets.java

charts:
	javac --release $(JDK) -d bin @chart_srcs.txt

run: build
	java -cp "bin" com.jsheets.JSheets
