# Rock Paper Scissors
## The backend has the following features:
* Latest Spring Boot with REST interface
* Stores all played games in an H2 database
* Metrics outcome matrix API
* Uses spring boot validator
* 100% test coverage
* Generates typescript models.d.ts using the typescript-generator-maven-plugin

## The frontend has the following features:
* Latest Angular
* Latest Boostrap
* Cool animation
* Metrics page
* Minimal effort was put into the frontend

## Setup
* On the frontend, run `npm install` and `npm run build`. The built frontend will be added to the backend static.
* Now run/package the backend as a normal Spring Boot application `mvn package`
* Run the jar in the target directory and visit http://localhost:8080