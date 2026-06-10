# REST Assured Practice

API Test Automation Framework for **reqres.in** and **Spotify Web API**

![Java](https://img.shields.io/badge/Java-21-orange)
![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-blue)
![Allure](https://img.shields.io/badge/Allure-2.21.0-yellow)
![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-black)

---

## About

Practical API test automation framework covering two real-world APIs.
Built to demonstrate REST Assured skills including authentication, serialization, schema validation, and advanced assertions.

---

## Technologies

| Technology | Version |
|---|---|
| Java | 21 |
| REST Assured | 5.4.0 |
| TestNG | 7.10.2 |
| AssertJ | 3.25.1 |
| Jackson | 2.17.0 |
| Allure Reports | 2.21.0 |
| commons-csv | 1.10.0 |

---

## Projects

### reqres.in
REST API testing with full CRUD coverage, authentication, schema validation and negative scenarios.

### Spotify Web API
OAuth 2.0 Authorization Code Flow with playlist CRUD operations, Stream API practice and CSV-driven tests.

---

## Key Features

- OAuth 2.0 Authorization Code Flow with automatic token refresh
- POJO Serialization & Deserialization (Jackson)
- JSON Schema Validation
- Soft Assertions (AssertJ)
- Hamcrest Matchers — hasItems, hasSize, containsString, greaterThan
- Stream API — filter, map, collect, distinct, sorted, allMatch
- CSV DataProvider — data-driven testing
- Parallel Execution — TestNG thread-count=3
- Allure Reports with @Epic, @Feature, @Story
- RetryAnalyzer for flaky tests
- CI/CD — GitHub Actions

---

## How to Run

**Prerequisites:** Java 21, Maven 3.9+, `spotify.properties` with credentials

```bash
# Run all tests
mvn test

# Run parallel (TestNG suite)
mvn test -DsuiteXmlFile=testng.xml

# Generate Allure report
allure serve target/allure-results
```

---

## CI/CD

GitHub Actions runs automatically on every push to `main`.

```
Push → Java 21 setup → mvn test → Allure Report
```

---

