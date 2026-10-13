# Student API (Spring Boot REST + H2 + Postman)

## Run
- IntelliJ: open this folder as a Maven project, run `StudentApiApplication`.
- Terminal: `mvn spring-boot:run`

API base URL: http://localhost:8080/api/students
H2 console:   http://localhost:8080/h2-console  (JDBC URL `jdbc:h2:mem:studentdb`, user `sa`, empty password)

## Test with Postman
Import `postman/StudentApi.postman_collection.json` and run the requests in order
(or use Collection Runner).

## Endpoints
| Method | URL | Purpose | Success |
|--------|-----|---------|---------|
| POST   | /api/students | create | 201 |
| GET    | /api/students | list (`?name=` / `?course=` filters) | 200 |
| GET    | /api/students/{id} | get one | 200 / 404 |
| PUT    | /api/students/{id} | full update | 200 / 404 |
| DELETE | /api/students/{id} | delete | 204 / 404 |
| GET    | /api/students/stats | count, average, topper | 200 |
