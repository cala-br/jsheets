JDK=17

build:
	javac --release $(JDK) -d bin -cp bin -sourcepath "src" src/com/jsheets/JSheets.java

charts:
	javac --release $(JDK) -d bin -cp bin -sourcepath "lib/jfreechart/src/main/java" lib/jfreechart/src/main/java/org/jfree/chart/*.java

run: build
	java -cp "bin" com.jsheets.JSheets
