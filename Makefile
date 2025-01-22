run:
	mvn clean compile assembly:single
	java -jar target/*jar-with-dependencies.jar

build:
	mvn clean compile assembly:single

test:
	mvn test

.PHONY: run
