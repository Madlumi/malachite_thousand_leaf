# malachite_thousand_leaf

A codetest mortgage calculator made in Java using Spring Boot.

---

## How to Use

### Run webUi(default port: 8080)

>./mvnw spring-boot:run -Dspring-boot.run.arguments="--input prospects.txt -w"


### Run tests:

>./mvnw test


### Docker stuff:

>./mvnw install && docker build -t com.madanie/malachite_thousand_leaf . && docker run -v $(pwd)/prospects.txt:/prospects.txt -p 8080:8080 com.madanie/malachite_thousand_leaf -w -i "prospects.txt"

---

## some limitations:
Uses an h2 in memory datbase, meaning no persistant memory.

No user verification or login system meaning it's not really suitable for the open web. 
If that's what you want the static page(/mortagecalculator.html) without db integration would be a more suitable choice.
