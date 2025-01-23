run:
	mvn clean package
	java -jar target/*jar-with-dependencies.jar

build:
	mvn clean package

test:
	mvn test

.PHONY: run
