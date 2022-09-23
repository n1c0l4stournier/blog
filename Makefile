build:
	mvn clean install -DskipTests

run: build
	java -jar target/*.jar