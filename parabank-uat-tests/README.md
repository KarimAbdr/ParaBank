# ParaBank UAT regression tests

Automated regression suite for [ParaBank](https://parabank.parasoft.com/), a demo online-banking
application (Java/Spring backend, web frontend, SQL database).
The tests are written from user stories and check the application on three levels:

| Level | Tool | What it checks |
|-------|------|----------------|
| UI    | Playwright for Java + JUnit | main customer journeys in a real browser |
| API   | REST Assured | business rules on the REST service the frontend uses |
| DB    | JDBC (HSQLDB) | that what the user sees is really stored |
| BDD   | Cucumber | the same user stories written as Given / When / Then |

## Tech stack

Java 21 · Maven · Playwright 1.64 · JUnit (Jupiter) · REST Assured · Cucumber · HSQLDB JDBC ·
GitHub Actions · Jenkins (Jenkinsfile)

## Project structure

```
src/test/java/com/parabank/uat
├── config/        Config - environment settings (config.properties, overridable with -Dkey=value)
├── ui/
│   ├── BaseUiTest     browser lifecycle, clean context per test, Playwright trace per test
│   ├── pages/         Page Objects (LoginPage, AccountsOverviewPage, OpenAccountPage, TransferFundsPage)
│   └── tests/         UI tests, one class per user story
├── api/           REST API tests + TestData (resets the demo database before every test)
├── db/            DbClient (JDBC helper) + data-level tests
└── bdd/           Cucumber runner and step definitions
src/test/resources
├── config.properties
└── features/      Gherkin .feature files
```

## Running locally

1. Start the application under test:
   ```bash
   docker run -d --name parabank -p 8080:8080 -p 9001:9001 parasoft/parabank
   ```
2. Run the tests (first run downloads Chromium):
   ```bash
   mvn test                         # everything
   mvn test -Dgroups=smoke          # only tests tagged "smoke"
   mvn test -Dgroups=api            # ui | api | db | bdd | regression
   mvn test -Dheadless=false -DslowMo=500   # watch the browser
   ```
3. Reports: `target/surefire-reports/`, `target/cucumber-report.html`.
   A Playwright trace for every UI test is saved to `target/traces/` - open it at
   <https://trace.playwright.dev> to replay the test step by step.

## Design decisions

- **Page Object Model** - locators live in page classes, tests read like the acceptance criteria.
- **Independent tests** - each test resets the data and gets a fresh browser context, so tests can run in any order.
- **No sleeps** - Playwright auto-waits for elements and its assertions retry, which keeps the suite stable.
- **Test pyramid** - business rules are checked through the API and DB (fast), UI tests cover the key journeys.
- **CI** - GitHub Actions starts ParaBank as a service container and runs the suite on every push;
  reports and traces are uploaded as artifacts. A `Jenkinsfile` with the same stages is included.
