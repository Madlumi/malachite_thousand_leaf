#Mortage Calculator

Run webUi(default port:path: 8080/mortage)
>./mvnw spring-boot:run -Dspring-boot.run.arguments="--input prospects.txt -w"

Run tests:
>./mvnw test

Docker stuff:
>./mvnw install 

>docker build -t myorg/myapp .

>docker run -v $(pwd)/prospects.txt:/prospects.txt -p 8080:8080 myorg/myapp -w -i "prospects.txt"

